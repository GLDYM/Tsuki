package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.block.machine.LighthouseIlluminationBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LighthouseIlluminationRenderer extends GeoBlockRenderer<LighthouseIlluminationBlockEntity> {
    // The beam can remain visible while the lighthouse itself is outside the
    // normal block-entity range. Keep this bounded to avoid making every
    // lighthouse a permanent render cost across the whole world.
    private static final int LIGHTHOUSE_VIEW_DISTANCE = 64;
    private static final float[][] UNIT_CIRCLE_X = new float[11][];
    private static final float[][] UNIT_CIRCLE_Y = new float[11][];

    static {
        for (int sides = 3; sides <= 10; sides++) {
            UNIT_CIRCLE_X[sides] = new float[sides];
            UNIT_CIRCLE_Y[sides] = new float[sides];
            for (int index = 0; index < sides; index++) {
                double angle = Math.PI * 2.0 * index / sides;
                UNIT_CIRCLE_X[sides][index] = (float) Math.cos(angle);
                UNIT_CIRCLE_Y[sides][index] = (float) Math.sin(angle);
            }
        }
    }

    public LighthouseIlluminationRenderer(BlockEntityRendererProvider.Context context) {
        super(new LighthouseIlluminationGeoModel());
    }

    @Override
    public boolean shouldRenderOffScreen(LighthouseIlluminationBlockEntity entity) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return LIGHTHOUSE_VIEW_DISTANCE;
    }

    @Override
    public AABB getRenderBoundingBox(LighthouseIlluminationBlockEntity entity) {
        // NeoForge performs frustum culling against this box before calling
        // render(). The default one-block box would discard visible beams.
        double radius = Math.max(entity.getLength(), entity.getWidth()) + 1.0D;
        return new AABB(entity.getBlockPos()).inflate(radius);
    }

    @Override
    public boolean shouldRender(LighthouseIlluminationBlockEntity entity, Vec3 cameraPosition) {
        double range = getViewDistance() + entity.getLength();
        return cameraPosition.closerThan(Vec3.atCenterOf(entity.getBlockPos()), range);
    }

    @Override
    public void render(LighthouseIlluminationBlockEntity entity, float partialTick, PoseStack poseStack,
            MultiBufferSource buffers, int light, int overlay) {
        super.render(entity, partialTick, poseStack, buffers, light, overlay);
        if (!entity.getBlockState().getValue(LighthouseIlluminationBlock.LIT))
            return;
        int color = entity.getColor();
        int r = color >> 16 & 255, g = color >> 8 & 255, b = color & 255;
        float time = (entity.getLevel().getGameTime() + partialTick) * 2.0F;
        poseStack.pushPose();
        // The lens is at model coordinates (8, 10.5, 8).
        poseStack.translate(.5F, 10.5F / 16F, .5F);
        VertexConsumer vertices = buffers.getBuffer(RenderType.lightning());
        poseStack.pushPose();
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(time + 90.0F));
        renderBeam(vertices, poseStack.last().pose(), entity, time, r, g, b);
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(time - 90.0F));
        renderBeam(vertices, poseStack.last().pose(), entity, time, r, g, b);
        poseStack.popPose();
        poseStack.popPose();
    }

    private static void renderBeam(VertexConsumer out, Matrix4f pose, LighthouseIlluminationBlockEntity entity,
            float time, int r, int g, int b) {
        if (entity.getPolygonCount() == 2) {
            triangleBeam(out, pose, entity.getLength(), entity.getWidth(), r, g, b, entity.getTransparency());
            return;
        }
        polygonBeam(out, pose, entity.getLength(), entity.getWidth(), entity.getPolygonCount(),
                time * ((float) Math.PI / 180.0F),
                r, g, b, entity.getTransparency());
    }

    private static void polygonBeam(VertexConsumer out, Matrix4f pose, float length, float radius, int sides,
            float spin, int r, int g, int b, int alpha) {
        float sin = (float) Math.sin(spin);
        float cos = (float) Math.cos(spin);
        float[] unitX = UNIT_CIRCLE_X[sides];
        float[] unitY = UNIT_CIRCLE_Y[sides];
        for (int index = 0; index < sides; index++) {
            int next = (index + 1) % sides;
            float x1 = (unitX[index] * cos - unitY[index] * sin) * radius;
            float y1 = (unitX[index] * sin + unitY[index] * cos) * radius;
            float x2 = (unitX[next] * cos - unitY[next] * sin) * radius;
            float y2 = (unitX[next] * sin + unitY[next] * cos) * radius;
            addTriangleQuad(out, pose, x1, y1, x2, y2, length, r, g, b, alpha);
            addTriangleQuad(out, pose, x2, y2, x1, y1, length, r, g, b, alpha);
        }
    }

    private static void addTriangleQuad(VertexConsumer out, Matrix4f pose, float x1, float y1, float x2, float y2,
            float length, int r, int g, int b, int alpha) {
        out.addVertex(pose, 0, 0, 0).setColor(r, g, b, alpha);
        out.addVertex(pose, x1, y1, length).setColor(r, g, b, 0);
        out.addVertex(pose, x2, y2, length).setColor(r, g, b, 0);
        out.addVertex(pose, 0, 0, 0).setColor(r, g, b, alpha);
    }

    private static void triangleBeam(VertexConsumer out, Matrix4f pose, float length, float width,
            int r, int g, int b, int alpha) {
        // RenderType.lightning batches vertices as quads. Duplicate the apex to
        // express the triangular axial section as a degenerate quad, twice with
        // opposite winding so the beam is visible from both sides.
        float top = width;
        float bottom = -width;
        float end = length;
        out.addVertex(pose, 0, 0, 0).setColor(r, g, b, alpha);
        out.addVertex(pose, 0, top, end).setColor(r, g, b, 0);
        out.addVertex(pose, 0, bottom, end).setColor(r, g, b, 0);
        out.addVertex(pose, 0, 0, 0).setColor(r, g, b, alpha);

        out.addVertex(pose, 0, 0, 0).setColor(r, g, b, alpha);
        out.addVertex(pose, 0, bottom, end).setColor(r, g, b, 0);
        out.addVertex(pose, 0, top, end).setColor(r, g, b, 0);
        out.addVertex(pose, 0, 0, 0).setColor(r, g, b, alpha);
    }
}
