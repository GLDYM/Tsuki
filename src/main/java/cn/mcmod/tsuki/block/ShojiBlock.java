package cn.mcmod.tsuki.block;

import cn.mcmod.tsuki.block.entity.BlockEntityRegistry;
import cn.mcmod.tsuki.block.entity.ShojiBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ShojiBlock extends BaseEntityBlock {
    public static final MapCodec<ShojiBlock> CODEC = simpleCodec(properties -> new ShojiBlock(0));
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    protected static final VoxelShape SHAPE_NS = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    protected static final VoxelShape SHAPE_EW = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_OPEN_NORTH = Block.box(13.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    protected static final VoxelShape SHAPE_OPEN_SOUTH = Block.box(0.0D, 0.0D, 7.0D, 3.0D, 16.0D, 9.0D);
    protected static final VoxelShape SHAPE_OPEN_WEST = Block.box(7.0D, 0.0D, 13.0D, 9.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_OPEN_EAST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 3.0D);

    private final int type;

    public ShojiBlock() {
        this(0);
    }

    public ShojiBlock(int type) {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(0.5F)
                .sound(SoundType.WOOD)
                .noOcclusion());
        this.type = type;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public MapCodec<ShojiBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? RenderShape.ENTITYBLOCK_ANIMATED : RenderShape.INVISIBLE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return null;
        }
        return new ShojiBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> blockEntityType) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return null;
        }
        return createTickerHelper(blockEntityType, BlockEntityRegistry.SHOJI.get(), ShojiBlockEntity::animationTick);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        if (state.getValue(OPEN)) {
            return switch (facing) {
                case NORTH -> SHAPE_OPEN_NORTH;
                case SOUTH -> SHAPE_OPEN_SOUTH;
                case WEST -> SHAPE_OPEN_WEST;
                case EAST -> SHAPE_OPEN_EAST;
                default -> SHAPE_NS;
            };
        }
        return (facing == Direction.NORTH || facing == Direction.SOUTH) ? SHAPE_NS : SHAPE_EW;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) {
        if (state.getValue(OPEN)) {
            return switch (state.getValue(FACING)) {
                case NORTH -> SHAPE_OPEN_NORTH;
                case SOUTH -> SHAPE_OPEN_SOUTH;
                case WEST -> SHAPE_OPEN_WEST;
                case EAST -> SHAPE_OPEN_EAST;
                default -> SHAPE_NS;
            };
        }
        Direction facing = state.getValue(FACING);
        return (facing == Direction.NORTH || facing == Direction.SOUTH) ? SHAPE_NS : SHAPE_EW;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        BlockPos basePos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
        BlockState baseState = level.getBlockState(basePos);
        if (!baseState.is(this) || baseState.getValue(HALF) != DoubleBlockHalf.LOWER) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            boolean open = !baseState.getValue(OPEN);
            BlockState newLower = baseState.setValue(OPEN, open);
            level.setBlock(basePos, newLower, 3);

            BlockPos upperPos = basePos.above();
            BlockState upperState = level.getBlockState(upperPos);
            if (upperState.is(this) && upperState.getValue(HALF) == DoubleBlockHalf.UPPER) {
                level.setBlock(upperPos, upperState.setValue(OPEN, open), 3);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        if (pos.getY() >= level.getMaxBuildHeight() - 1 || !level.getBlockState(pos.above()).canBeReplaced(context)) {
            return null;
        }
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
            ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
            state.updateNeighbourShapes(level, pos, 3);
        }
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (direction.getAxis() == Direction.Axis.Y) {
            if (half == DoubleBlockHalf.LOWER && direction == Direction.UP) {
                return neighborState.is(this) && neighborState.getValue(HALF) == DoubleBlockHalf.UPPER
                        ? state.setValue(OPEN, neighborState.getValue(OPEN))
                                .setValue(FACING, neighborState.getValue(FACING))
                        : Blocks.AIR.defaultBlockState();
            }
            if (half == DoubleBlockHalf.UPPER && direction == Direction.DOWN) {
                return neighborState.is(this) && neighborState.getValue(HALF) == DoubleBlockHalf.LOWER
                        ? state.setValue(OPEN, neighborState.getValue(OPEN))
                                .setValue(FACING, neighborState.getValue(FACING))
                        : Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && player.isCreative()) {
            DoubleBlockHalf half = state.getValue(HALF);
            BlockPos otherPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
            BlockState otherState = level.getBlockState(otherPos);
            if (otherState.is(this) && otherState.getValue(HALF) != half) {
                level.setBlock(otherPos, Blocks.AIR.defaultBlockState(), 35);
                level.levelEvent(player, 2001, otherPos, Block.getId(otherState));
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    public int getType() {
        return this.type;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, OPEN, HALF);
    }
}
