package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;

import java.util.ArrayList;
import java.util.List;

public class FallenLeavesBlock extends BushBlock implements IShearable, BonemealableBlock {
    public static final MapCodec<FallenLeavesBlock> CODEC = simpleCodec(FallenLeavesBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    public FallenLeavesBlock(Properties properties) {
        super(properties);
    }

    public FallenLeavesBlock() {
        this(BlockBehaviour.Properties.of().noCollission().instabreak());
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.LEAVES) || state.isFaceSturdy(level, pos, Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return state.canSurvive(level, pos)
                ? super.updateShape(state, direction, neighborState, level, pos, neighborPos)
                : Blocks.AIR.defaultBlockState();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return this.mayPlaceOn(level.getBlockState(pos.below()), level, pos.below());
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return true;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return true;
    }

    @Override
    public boolean isShearable(Player player, ItemStack itemStack, Level level,
            BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(Player player, ItemStack itemStack, Level level,
            BlockPos pos) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(this));
        return drops;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState below = level.getBlockState(pos.below());
        if (!(below.getBlock() instanceof RotatedPillarBlock)) {
            return;
        }
        if (below.getBlock() instanceof MapleTreeLogBlock) {
            if (random.nextInt(10) == 0) {
                level.setBlock(pos, BlockRegistry.MATSUTAKE_FALLEN_LEAVES.get().defaultBlockState(), 3);
            } else {
                level.setBlock(pos, BlockRegistry.MUSHROOM_FALLEN_LEAVES.get().defaultBlockState(), 3);
            }
            return;
        }
        level.setBlock(pos, BlockRegistry.MUSHROOM_FALLEN_LEAVES.get().defaultBlockState(), 3);
    }
}
