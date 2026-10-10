package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.CeilingLightBlockEntity;
import cn.mcmod.tsuki.block.machine.CeilingLightBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CeilingLightRenderer extends GeoBlockRenderer<CeilingLightBlockEntity> {
    public CeilingLightRenderer(BlockEntityRendererProvider.Context context) { super(new CeilingLightGeoModel()); }
    @Override public boolean shouldRenderOffScreen(CeilingLightBlockEntity entity) { return true; }
    @Override public int getViewDistance() { return 64; }
    @Override public AABB getRenderBoundingBox(CeilingLightBlockEntity entity) {
        return new AABB(entity.getBlockPos()).inflate(entity.getLength() * .2D + entity.getWidth() * .2D + 1D);
    }
    @Override public boolean shouldRender(CeilingLightBlockEntity entity, Vec3 cameraPosition) {
        return cameraPosition.closerThan(Vec3.atCenterOf(entity.getBlockPos()), 64D + entity.getLength() * .2D);
    }

    @Override public void render(CeilingLightBlockEntity entity, float partialTick, PoseStack poseStack,
            MultiBufferSource buffers, int light, int overlay) {
        super.render(entity, partialTick, poseStack, buffers, light, overlay);
        if (!entity.getBlockState().getValue(CeilingLightBlock.LIT) || entity.getLevel() == null) return;
        int color = entity.getColor();
        float spin = (entity.getLevel().getGameTime() + partialTick) * .03F;
        poseStack.pushPose();
        poseStack.translate(.5F, .7F, .5F);
        VertexConsumer output = buffers.getBuffer(RenderType.lightning());
        LightBeamUtil.render(output, poseStack.last().pose(), new Vec3(0, -1, 0), entity.getLength() * .2F,
                entity.getWidth() * .2F, entity.getPolygonCount(), spin, color >> 16 & 255, color >> 8 & 255,
                color & 255, Math.round(entity.getTransparency() * .3F));
        poseStack.popPose();
    }
}
