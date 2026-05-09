package cn.mcmod.tsuki.block.drink;

import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.item.drink.DrinkPlacementHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractDrinkDisplayBlock extends BaseEntityBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    protected AbstractDrinkDisplayBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockPos supportPos = context.getLevel().getBlockState(pos).canBeReplaced(context) ? pos.below() : pos;
        if (!context.getLevel().getBlockState(supportPos).isFaceSturdy(context.getLevel(), supportPos, Direction.UP)) {
            return null;
        }
        return defaultBlockState();
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN
                && !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.DRINK_DISPLAY.get().create(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof DrinkDisplayBlockEntity drinkDisplay)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        int slot = getSlotFromInteraction(player, hit, pos);
        if (stack.isEmpty() && slot < 0) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (stack.isEmpty()) {
            if (removeItem(level, pos, player, drinkDisplay, slot)) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (player != null && !player.isShiftKeyDown()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (placeItem(level, pos, player, drinkDisplay, stack, slot)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide && state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DrinkDisplayBlockEntity drinkDisplay) {
                for (int i = 0; i < DrinkDisplayBlockEntity.SLOT_COUNT; i++) {
                    ItemStack stack = drinkDisplay.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        Block.popResource(level, pos, stack.copy());
                    }
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    protected boolean placeItem(Level level, BlockPos pos, Player player, DrinkDisplayBlockEntity blockEntity, ItemStack stack,
            int slot) {
        if (!canAccept(stack) || !blockEntity.getStackInSlot(slot).isEmpty()) {
            return false;
        }
        if (level.isClientSide) {
            return true;
        }
        ItemStack toStore = player != null && player.getAbilities().instabuild ? stack.copyWithCount(1) : stack.split(1);
        if (toStore.isEmpty()) {
            return false;
        }
        float rotation = player == null ? 0.0F : -player.getYRot();
        if (!blockEntity.addItem(toStore, slot, rotation)) {
            stack.grow(toStore.getCount());
            return false;
        }
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), Block.UPDATE_CLIENTS);
        return true;
    }

    protected boolean removeItem(Level level, BlockPos pos, Player player, DrinkDisplayBlockEntity blockEntity, int slot) {
        if (blockEntity.getStackInSlot(slot).isEmpty()) {
            return false;
        }
        if (level.isClientSide) {
            return true;
        }
        ItemStack removed = blockEntity.removeItem(slot);
        if (removed.isEmpty()) {
            return false;
        }
        if (player != null && !player.addItem(removed)) {
            Block.popResource(level, pos, removed);
        }
        if (blockEntity.findFirstOccupiedSlot() < 0) {
            level.removeBlock(pos, false);
        } else {
            level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), Block.UPDATE_CLIENTS);
        }
        return true;
    }

    protected abstract boolean canAccept(ItemStack stack);

    protected int getSlotFromHit(BlockHitResult hit, BlockPos pos) {
        Vec3 local = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
        int x = local.x >= 0.5D ? 1 : 0;
        int z = local.z >= 0.5D ? 1 : 0;
        return z * 2 + x;
    }

    protected int getSlotFromInteraction(Player player, BlockHitResult hit, BlockPos pos) {
        Vec3 projected = player == null ? hit.getLocation()
                : DrinkPlacementHelper.projectToPlane(player.getEyePosition(), hit.getLocation(), pos.getY());
        Vec3 local = projected.subtract(pos.getX(), pos.getY(), pos.getZ());
        double x = local.x - 0.5D;
        double z = local.z - 0.5D;

        boolean west = (x < 0.0D);
        boolean north = (z < 0.0D);

        if (north) {
            return west ? 0 : 1;
        }
        return west ? 2 : 3;
    }
}
