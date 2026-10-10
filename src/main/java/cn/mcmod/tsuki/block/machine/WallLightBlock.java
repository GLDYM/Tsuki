package cn.mcmod.tsuki.block.machine;

import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallLightBlock extends BaseEntityBlock {
    public static final MapCodec<WallLightBlock> CODEC = simpleCodec(WallLightBlock::new);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape EAST = box(0, 6, 5, 9, 15, 11);
    private static final VoxelShape WEST = box(7, 6, 5, 16, 15, 11);
    private static final VoxelShape SOUTH = box(5, 6, 0, 11, 15, 9);
    private static final VoxelShape NORTH = box(5, 6, 7, 11, 15, 16);
    private final boolean wooden;

    public WallLightBlock(Properties properties) { this(properties, false); }
    public WallLightBlock(Properties properties, boolean wooden) {
        super(properties);
        this.wooden = wooden;
        registerDefaultState(stateDefinition.any().setValue(LIT, false).setValue(FACING, Direction.EAST));
    }
    public WallLightBlock() { this(false); }
    public WallLightBlock(boolean wooden) {
        this(Properties.of().strength(.2F).noOcclusion()
                .sound(wooden ? SoundType.WOOD : SoundType.STONE)
                .lightLevel(state -> state.getValue(LIT) ? 15 : 0), wooden);
    }
    public boolean isWooden() { return wooden; }

    @Override protected MapCodec<? extends WallLightBlock> codec() { return CODEC; }
    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.ENTITYBLOCK_ANIMATED; }
    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.WALL_LIGHT.get().create(pos, state);
    }

    @Override public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clicked = context.getClickedFace();
        if (!clicked.getAxis().isHorizontal()) return null;
        BlockState state = defaultBlockState().setValue(FACING, clicked);
        return state.canSurvive(context.getLevel(), context.getClickedPos()) ? state : null;
    }

    @Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockPos support = pos.relative(facing.getOpposite());
        return level.getBlockState(support).isFaceSturdy(level, support, facing);
    }

    @Override protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == state.getValue(FACING).getOpposite() && !canSurvive(state, level, pos))
            return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, net.minecraft.world.InteractionHand hand, BlockHitResult hit) {
        interact(state, level, pos, player);
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hit) {
        interact(state, level, pos, player);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    private void interact(BlockState state, Level level, BlockPos pos, Player player) {
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide()) level.setBlock(pos, state.cycle(LIT), 3);
        } else if (!level.isClientSide() && player instanceof ServerPlayer server
                && level.getBlockEntity(pos) instanceof WallLightBlockEntity light) {
            server.openMenu(light, pos);
        }
    }

    @Override public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) { return Shapes.empty(); }
    @Override public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> EAST; case WEST -> WEST; case NORTH -> NORTH; case SOUTH -> SOUTH; default -> EAST;
        };
    }
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, FACING);
    }
}
