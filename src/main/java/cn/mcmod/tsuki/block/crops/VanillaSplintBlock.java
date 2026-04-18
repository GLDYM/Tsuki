package cn.mcmod.tsuki.block.crops;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VanillaSplintBlock extends Block {
    public static final MapCodec<VanillaSplintBlock> CODEC = simpleCodec(p -> new VanillaSplintBlock());
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(2.0D, 0.0D, 2.0D, 3.0D, 16.0D, 3.0D),
            Block.box(13.0D, 0.0D, 2.0D, 14.0D, 16.0D, 3.0D),
            Block.box(13.0D, 0.0D, 13.0D, 14.0D, 16.0D, 14.0D),
            Block.box(2.0D, 0.0D, 13.0D, 3.0D, 16.0D, 14.0D),
            Block.box(1.0D, 12.0D, 0.0D, 2.0D, 13.0D, 16.0D),
            Block.box(14.0D, 12.0D, 0.0D, 15.0D, 13.0D, 16.0D),
            Block.box(0.0D, 13.0D, 1.0D, 16.0D, 14.0D, 2.0D),
            Block.box(0.0D, 13.0D, 14.0D, 16.0D, 14.0D, 15.0D));

    public VanillaSplintBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .randomTicks());
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
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
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide())
            return ItemInteractionResult.SUCCESS;

        if (stack.is(ItemRegistry.VANILLA_SEEDS.get())) {
            level.setBlock(pos, BlockRegistry.VANILLA_CROP.get().defaultBlockState(), 3);
            if (!player.isCreative())
                stack.shrink(1);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        return below.isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos,
            boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        if (!this.canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!this.canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
        }
    }
}
