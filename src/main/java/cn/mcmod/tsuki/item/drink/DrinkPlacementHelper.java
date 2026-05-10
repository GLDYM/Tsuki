package cn.mcmod.tsuki.item.drink;

import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public final class DrinkPlacementHelper {

    private DrinkPlacementHelper() {
    }

    public static InteractionResult place(UseOnContext context, Block block) {
        Level level = context.getLevel();
        BlockPlaceContext placeContext = new BlockPlaceContext(context);
        BlockPos pos = placeContext.getClickedPos();
        BlockState clickedState = level.getBlockState(pos);
        if (!clickedState.canBeReplaced(placeContext)) {
            pos = pos.relative(context.getClickedFace());
            if (!level.getBlockState(pos).canBeReplaced(placeContext)) {
                return InteractionResult.FAIL;
            }
        }
        if (!level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
            return InteractionResult.FAIL;
        }
        if (!level.setBlock(pos, block.defaultBlockState(), Block.UPDATE_ALL)) {
            return InteractionResult.FAIL;
        }

        if (level.getBlockEntity(pos) instanceof DrinkDisplayBlockEntity drinkDisplay) {
            ItemStack stack = context.getItemInHand();
            int slot = choosePlacementSlot(context, pos, drinkDisplay);
            if (slot < 0) {
                level.removeBlock(pos, false);
                return InteractionResult.FAIL;
            }
            boolean creative = context.getPlayer() != null && context.getPlayer().getAbilities().instabuild;
            ItemStack toStore = creative ? stack.copyWithCount(1) : stack.split(1);
            float rotation = context.getPlayer() == null ? 0.0F : -context.getPlayer().getYRot();
            if (toStore.isEmpty() || !drinkDisplay.addItem(toStore, slot, rotation)) {
                if (!creative) {
                    stack.grow(1);
                }
                level.removeBlock(pos, false);
                return InteractionResult.FAIL;
            }
            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 0.8F, 1.0F);
            return InteractionResult.SUCCESS;
        }

        level.removeBlock(pos, false);
        return InteractionResult.FAIL;
    }

    private static int choosePlacementSlot(UseOnContext context, BlockPos pos, DrinkDisplayBlockEntity drinkDisplay) {
        int clickedSlot = getSlotFromInteraction(context, pos);
        if (clickedSlot >= 0 && drinkDisplay.getStackInSlot(clickedSlot).isEmpty()) {
            return clickedSlot;
        }
        for (int slot = 0; slot < DrinkDisplayBlockEntity.SLOT_COUNT; slot++) {
            if (drinkDisplay.getStackInSlot(slot).isEmpty()) {
                return slot;
            }
        }
        return -1;
    }

    public static Vec3 projectToPlane(Vec3 eye, Vec3 hit, double targetY) {
        double dy = hit.y - eye.y;
        if (Math.abs(dy) < 1.0E-6D) {
            return hit;
        }
        double t = (targetY - eye.y) / dy;
        return new Vec3(
                eye.x + (hit.x - eye.x) * t,
                targetY,
                eye.z + (hit.z - eye.z) * t);
    }

    private static int getSlotFromInteraction(UseOnContext context, BlockPos pos) {
        if (context.getPlayer() == null) {
            return 0;
        }
        Vec3 projected = projectToPlane(context.getPlayer().getEyePosition(), context.getClickLocation(),
                pos.getY());
        double x = projected.x - (pos.getX() + 0.5D);
        double z = projected.z - (pos.getZ() + 0.5D);
        boolean west = (x < 0.0D);
        boolean north = (z < 0.0D);
        if (north) {
            return west ? 0 : 1;
        }
        return west ? 2 : 3;
    }
}
