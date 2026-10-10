package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.block.machine.WallLightBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class WallLightRenderer extends GeoBlockRenderer<WallLightBlockEntity> {
    public WallLightRenderer(BlockEntityRendererProvider.Context context) { super(new WallLightGeoModel()); }

    @Override
    protected void rotateBlock(Direction facing, PoseStack poseStack) {
        float yaw = switch (facing) {
            case EAST -> 180F;
            case SOUTH -> 90F;
            case WEST -> 0F;
            case NORTH -> 270F;
            default -> 0F;
        };
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
    }
    @Override public boolean shouldRenderOffScreen(WallLightBlockEntity entity) { return true; }
    @Override public int getViewDistance() { return 64; }
    @Override public AABB getRenderBoundingBox(WallLightBlockEntity entity) {
        return new AABB(entity.getBlockPos()).inflate(entity.getLength() * .2D + entity.getWidth() * .2D + 1D);
    }
    @Override public boolean shouldRender(WallLightBlockEntity entity, Vec3 cameraPosition) {
        return cameraPosition.closerThan(Vec3.atCenterOf(entity.getBlockPos()), 64D + entity.getLength() * .2D);
    }

    @Override public void render(WallLightBlockEntity entity, float partialTick, PoseStack poseStack,
            MultiBufferSource buffers, int light, int overlay) {
        Direction facing = entity.getBlockState().getValue(WallLightBlock.FACING);
        super.render(entity, partialTick, poseStack, buffers, light, overlay);

        if (!entity.getBlockState().getValue(WallLightBlock.LIT) || entity.getLevel() == null) return;
        int color = entity.getColor();
        float spin = (entity.getLevel().getGameTime() + partialTick) * .03F;
        poseStack.pushPose();
        poseStack.translate(.5F - facing.getStepX() * .2F, .7F, .5F - facing.getStepZ() * .2F);
        VertexConsumer output = buffers.getBuffer(RenderType.lightning());
        LightBeamUtil.render(output, poseStack.last().pose(),
                new Vec3(facing.getStepX() * .70710678D, -.70710678D, facing.getStepZ() * .70710678D),
                entity.getLength() * .2F, entity.getWidth() * .2F, entity.getPolygonCount(), spin,
                color >> 16 & 255, color >> 8 & 255, color & 255, Math.round(entity.getTransparency() * .3F));
        poseStack.popPose();
    }
}
