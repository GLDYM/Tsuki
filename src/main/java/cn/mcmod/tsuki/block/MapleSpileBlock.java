package cn.mcmod.tsuki.block;

import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MapleSpileBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<MapleSpileBlock> CODEC = simpleCodec(MapleSpileBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape AABB_N = Block.box(5.0D, 3.0D, 3.0D, 11.0D, 11.0D, 16.0D);
    private static final VoxelShape AABB_S = Block.box(5.0D, 3.0D, 0.0D, 11.0D, 11.0D, 13.0D);
    private static final VoxelShape AABB_W = Block.box(3.0D, 3.0D, 5.0D, 16.0D, 11.0D, 11.0D);
    private static final VoxelShape AABB_E = Block.box(0.0D, 3.0D, 5.0D, 13.0D, 11.0D, 11.0D);

    public MapleSpileBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public MapleSpileBlock() {
        this(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(0.6F).sound(SoundType.LANTERN)
                .noOcclusion().randomTicks());
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> AABB_E;
            case SOUTH -> AABB_S;
            case WEST -> AABB_W;
            default -> AABB_N;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        Direction clicked = context.getClickedFace();
        if (clicked.getAxis().isHorizontal()) {
            BlockState preferred = this.defaultBlockState().setValue(FACING, clicked);
            if (preferred.canSurvive(level, pos)) {
                return preferred;
            }
        }

        for (Direction direction : context.getNearestLookingDirections()) {
            if (!direction.getAxis().isHorizontal()) {
                continue;
            }
            BlockState trial = this.defaultBlockState().setValue(FACING, direction);
            if (trial.canSurvive(level, pos)) {
                return trial;
            }
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockPos attachedPos = pos.relative(facing.getOpposite());
        BlockState attached = level.getBlockState(attachedPos);
        return attached.is(BlockRegistry.MAPLE_SAP_LOG.get()) || attached.is(BlockRegistry.MAPLE_LOG.get());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level,
            BlockPos currentPos, BlockPos neighborPos) {
        return state.canSurvive(level, currentPos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canWork(level, pos, state)) {
            return;
        }

        if (random.nextInt(5000) == 0) {
            Direction facing = state.getValue(FACING);
            BlockPos logPos = pos.relative(facing.getOpposite());
            BlockState logState = level.getBlockState(logPos);
            if (logState.is(BlockRegistry.MAPLE_SAP_LOG.get()) && !logState.getValue(MapleTreeSapLogBlock.EXHAUSTION)) {
                level.setBlockAndUpdate(logPos, logState.setValue(MapleTreeSapLogBlock.EXHAUSTION, true));
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!canWork(level, pos, state) || random.nextInt(10) != 0) {
            return;
        }

        Direction facing = state.getValue(FACING);
        // Spawn near the spile tip instead of block center.
        double x = pos.getX() + 0.5D + facing.getStepX() * 0.1D;
        double y = pos.getY() + 0.17D;
        double z = pos.getZ() + 0.5D + facing.getStepZ() * 0.1D;
        double vy = -(((random.nextFloat()) * 0.055D) + 0.015D);
        level.addParticle(ParticleRegistry.SYRUP_DROP.get(), x, y, z, 0.0D, vy, 0.0D);
    }

    public static boolean canWork(Level level, BlockPos pos, BlockState state) {
        if (!(state.getBlock() instanceof MapleSpileBlock)) {
            return false;
        }

        Direction facing = state.getValue(FACING);
        BlockPos logPos = pos.relative(facing.getOpposite());
        BlockState logState = level.getBlockState(logPos);
        return logState.is(BlockRegistry.MAPLE_SAP_LOG.get()) && !logState.getValue(MapleTreeSapLogBlock.EXHAUSTION);
    }
}
