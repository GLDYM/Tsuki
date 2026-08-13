package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.block.machine.LighthouseIlluminationBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LighthouseIlluminationRenderer extends GeoBlockRenderer<LighthouseIlluminationBlockEntity> {
    private static final int LIGHTHOUSE_VIEW_DISTANCE = 64;

    public LighthouseIlluminationRenderer(BlockEntityRendererProvider.Context context) { super(new LighthouseIlluminationGeoModel()); }
    @Override public boolean shouldRenderOffScreen(LighthouseIlluminationBlockEntity entity) { return true; }
    @Override public int getViewDistance() { return LIGHTHOUSE_VIEW_DISTANCE; }

    @Override
    public boolean shouldRender(LighthouseIlluminationBlockEntity entity, Vec3 cameraPosition) {
        // Keep rendering while either beam can still reach the normal renderer range.
        double range = getViewDistance() + entity.getLength();
        return cameraPosition.closerThan(Vec3.atCenterOf(entity.getBlockPos()), range);
    }
    @Override public void render(LighthouseIlluminationBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffers, int light, int overlay) {
        super.render(entity, partialTick, poseStack, buffers, light, overlay);
        if (!entity.getBlockState().getValue(LighthouseIlluminationBlock.LIT)) return;
        int color = entity.getColor(); int r = color >> 16 & 255, g = color >> 8 & 255, b = color & 255;
        float time = (entity.getLevel().getGameTime() + partialTick) * 2.0F;
        poseStack.pushPose();
        // The lens is at model coordinates (8, 10.5, 8).
        poseStack.translate(.5F, 10.5F / 16F, .5F);
        VertexConsumer vertices = buffers.getBuffer(RenderType.lightning());
        poseStack.pushPose();
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(time + 90.0F));
        triangleBeam(vertices, poseStack.last().pose(), entity.getLength(), entity.getWidth(), r, g, b,
                entity.getTransparency());
        poseStack.popPose();
        poseStack.pushPose();
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(time - 90.0F));
        triangleBeam(vertices, poseStack.last().pose(), entity.getLength(), entity.getWidth(), r, g, b,
                entity.getTransparency());
        poseStack.popPose();
        poseStack.popPose();
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
