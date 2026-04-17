package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class FloorMountedLanternBlock extends CustomLanternBlock {
    public static final MapCodec<FloorMountedLanternBlock> CODEC = simpleCodec(FloorMountedLanternBlock::new);

    public FloorMountedLanternBlock(Properties properties) {
        super(properties);
    }

    public FloorMountedLanternBlock(Properties properties, VoxelShape shape) {
        super(properties, shape);
    }

    public FloorMountedLanternBlock(Properties properties, VoxelShape shape, VoxelShape collisionShape) {
        super(properties, shape, collisionShape);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placement = super.getStateForPlacement(context);
        return placement != null && placement.canSurvive(context.getLevel(), context.getClickedPos()) ? placement : null;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        return Block.isFaceFull(belowState.getCollisionShape(level, belowPos), Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double centerX = pos.getX() + 0.5D;
        double centerY = pos.getY() + 0.45D;
        double centerZ = pos.getZ() + 0.5D;
        level.addParticle(ParticleTypes.SMOKE, centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
        level.addParticle(ParticleTypes.FLAME, centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
    }
}
