package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.AbstractLightBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public abstract class AbstractLightRenderer<T extends AbstractLightBlockEntity> extends GeoBlockRenderer<T> {
    protected AbstractLightRenderer(GeoModel<T> model) {
        super(model);
    }

    @Override
    public boolean shouldRenderOffScreen(T entity) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public AABB getRenderBoundingBox(T entity) {
        return new AABB(entity.getBlockPos()).inflate(entity.getLength() * .2D + entity.getWidth() * .2D + 1D);
    }

    @Override
    public boolean shouldRender(T entity, Vec3 cameraPosition) {
        return cameraPosition.closerThan(Vec3.atCenterOf(entity.getBlockPos()), 64D + entity.getLength() * .2D);
    }

    @Override
    public void render(T entity, float partialTick, PoseStack poseStack, MultiBufferSource buffers, int light,
            int overlay) {
        super.render(entity, partialTick, poseStack, buffers, light, overlay);
        if (!isLit(entity) || entity.getLevel() == null)
            return;

        int color = entity.getColor();
        float spin = (entity.getLevel().getGameTime() + partialTick) * .03F;
        poseStack.pushPose();
        translateToBeamOrigin(entity, poseStack);
        VertexConsumer output = buffers.getBuffer(RenderType.lightning());
        LightBeamUtil.render(output, poseStack.last().pose(), getBeamDirection(entity), entity.getLength() * .2F,
                entity.getWidth() * .2F, entity.getPolygonCount(), spin, color >> 16 & 255, color >> 8 & 255,
                color & 255, Math.round(entity.getTransparency() * .3F));
        poseStack.popPose();
    }

    protected abstract boolean isLit(T entity);

    protected abstract Vec3 getBeamDirection(T entity);

    protected void translateToBeamOrigin(T entity, PoseStack poseStack) {
        poseStack.translate(.5F, .7F, .5F);
    }
}
