package cn.mcmod.tsuki.block.crop;

import cn.mcmod.mmlib.block.BaseCropBlock;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VanillaCropBlock extends BaseCropBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(2.0D, 0.0D, 2.0D, 3.0D, 16.0D, 3.0D),
            Block.box(13.0D, 0.0D, 2.0D, 14.0D, 16.0D, 3.0D),
            Block.box(13.0D, 0.0D, 13.0D, 14.0D, 16.0D, 14.0D),
            Block.box(2.0D, 0.0D, 13.0D, 3.0D, 16.0D, 14.0D),
            Block.box(1.0D, 12.0D, 0.0D, 2.0D, 13.0D, 16.0D),
            Block.box(14.0D, 12.0D, 0.0D, 15.0D, 13.0D, 16.0D),
            Block.box(0.0D, 13.0D, 1.0D, 16.0D, 14.0D, 2.0D),
            Block.box(0.0D, 13.0D, 14.0D, 16.0D, 14.0D, 15.0D));

    public VanillaCropBlock(Properties properties, Supplier<? extends ItemLike> seeds) {
        super(properties, seeds);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isFaceSturdy(level, pos, Direction.UP);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos,
            Player player) {
        return new ItemStack(BlockRegistry.VANILLA_SPLINT.get());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if (!canHarvest(state, player)) {
            return InteractionResult.PASS;
        }
        harvest(level, pos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hit) {
        if (!canHarvest(state, player)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        harvest(level, pos);
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    private boolean canHarvest(BlockState state, Player player) {
        return !player.isShiftKeyDown() && state.getValue(getAgeProperty()) >= getMaxAge();
    }

    private void harvest(Level level, BlockPos pos) {
        if (level.isClientSide) {
            return;
        }
        popResource(level, pos, new ItemStack(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VANILLA).get()));
        level.setBlock(pos, this.defaultBlockState().setValue(getAgeProperty(), 0), 2);
    }
}
