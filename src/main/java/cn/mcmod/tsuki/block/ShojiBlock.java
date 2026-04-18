package cn.mcmod.tsuki.block;

import cn.mcmod.tsuki.block.entity.BlockEntityRegistry;
import cn.mcmod.tsuki.block.entity.ShojiBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ShojiBlock extends BaseEntityBlock {
    public static final MapCodec<ShojiBlock> CODEC = simpleCodec(properties -> new ShojiBlock(0));
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    protected static final VoxelShape SHAPE_NS = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 32.0D, 9.0D);
    protected static final VoxelShape SHAPE_EW = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 32.0D, 16.0D);
    protected static final VoxelShape SHAPE_OPEN_NORTH = Block.box(13.0D, 0.0D, 7.0D, 16.0D, 32.0D, 9.0D);
    protected static final VoxelShape SHAPE_OPEN_SOUTH = Block.box(0.0D, 0.0D, 7.0D, 3.0D, 32.0D, 9.0D);
    protected static final VoxelShape SHAPE_OPEN_WEST = Block.box(7.0D, 0.0D, 13.0D, 9.0D, 32.0D, 16.0D);
    protected static final VoxelShape SHAPE_OPEN_EAST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 32.0D, 3.0D);

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
                .setValue(OPEN, false));
    }

    @Override
    public MapCodec<ShojiBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShojiBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> blockEntityType) {
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
        if (!level.isClientSide) {
            BlockState newState = state.cycle(OPEN);
            level.setBlock(pos, newState, 3);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public int getType() {
        return this.type;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, OPEN);
    }
}
