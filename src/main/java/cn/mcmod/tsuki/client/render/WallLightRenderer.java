package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.block.machine.WallLightBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class WallLightRenderer extends AbstractLightRenderer<WallLightBlockEntity> {
    public WallLightRenderer(BlockEntityRendererProvider.Context context) {
        super(new WallLightGeoModel());
    }

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

    @Override
    protected boolean isLit(WallLightBlockEntity entity) {
        return entity.getBlockState().getValue(WallLightBlock.LIT);
    }

    @Override
    protected Vec3 getBeamDirection(WallLightBlockEntity entity) {
        Direction facing = entity.getBlockState().getValue(WallLightBlock.FACING);
        return new Vec3(facing.getStepX() * .70710678D, -.70710678D, facing.getStepZ() * .70710678D);
    }

    @Override
    protected void translateToBeamOrigin(WallLightBlockEntity entity, PoseStack poseStack) {
        Direction facing = entity.getBlockState().getValue(WallLightBlock.FACING);
        poseStack.translate(.5F - facing.getStepX() * .2F, .7F, .5F - facing.getStepZ() * .2F);
    }
}
