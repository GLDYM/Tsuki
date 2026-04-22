package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FutonBlock extends Block {
    public static final MapCodec<FutonBlock> CODEC = simpleCodec(p -> new FutonBlock());

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<BedPart> PART = EnumProperty.create("part", BedPart.class);
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.5D, 16.0D);

    public FutonBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.SNOW)
                .strength(0.3F)
                .sound(SoundType.WOOL)
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, BedPart.FOOT)
                .setValue(OCCUPIED, false));
    }

    @Override
    public MapCodec<FutonBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }

        if (state.getValue(PART) != BedPart.HEAD) {
            pos = pos.relative(state.getValue(FACING));
            state = level.getBlockState(pos);
            if (!state.is(this)) {
                return InteractionResult.CONSUME;
            }
        }

        if (!BedBlock.canSetSpawn(level)) {
            level.removeBlock(pos, false);
            BlockPos footPos = pos.relative(state.getValue(FACING).getOpposite());
            if (level.getBlockState(footPos).is(this)) {
                level.removeBlock(footPos, false);
            }
            level.explode(
                    null,
                    level.damageSources().badRespawnPointExplosion(pos.getCenter()),
                    null,
                    pos.getX() + 0.5D,
                    pos.getY() + 0.5D,
                    pos.getZ() + 0.5D,
                    5.0F,
                    true,
                    Level.ExplosionInteraction.BLOCK);
            return InteractionResult.SUCCESS;
        }

        if (state.getValue(OCCUPIED)) {
            if (!kickVillagerOutOfBed(level, pos)) {
                player.displayClientMessage(Component.translatable("block.minecraft.bed.occupied"), true);
            }
            return InteractionResult.SUCCESS;
        }

        player.startSleepInBed(pos).ifLeft(problem -> {
            if (problem.getMessage() != null) {
                player.displayClientMessage(problem.getMessage(), true);
            }
        });
        return InteractionResult.SUCCESS;
    }

    private boolean kickVillagerOutOfBed(Level level, BlockPos pos) {
        List<Villager> villagers = level.getEntitiesOfClass(Villager.class, new AABB(pos), LivingEntity::isSleeping);
        if (villagers.isEmpty()) {
            return false;
        }
        villagers.get(0).stopSleeping();
        return true;
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, @Nullable LivingEntity sleeper) {
        return true;
    }

    @Override
    public Direction getBedDirection(BlockState state, LevelReader level, BlockPos pos) {
        return state.getValue(FACING);
    }

    @Override
    public void setBedOccupied(BlockState state, Level level, BlockPos pos, LivingEntity sleeper, boolean occupied) {
        level.setBlock(pos, state.setValue(OCCUPIED, occupied), 3);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        BlockPos headPos = context.getClickedPos().relative(facing);
        Level level = context.getLevel();
        if (level.getBlockState(headPos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(headPos)) {
            return this.defaultBlockState().setValue(FACING, facing);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
            ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockPos headPos = pos.relative(state.getValue(FACING));
            level.setBlock(headPos, state.setValue(PART, BedPart.HEAD), 3);
            level.blockUpdated(pos, Blocks.AIR);
            state.updateNeighbourShapes(level, pos, 3);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == getNeighborDirection(state.getValue(PART), state.getValue(FACING))) {
            return neighborState.is(this) && neighborState.getValue(PART) != state.getValue(PART)
                    ? state.setValue(OCCUPIED, neighborState.getValue(OCCUPIED))
                    : Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    private static Direction getNeighborDirection(BedPart part, Direction facing) {
        return part == BedPart.FOOT ? facing : facing.getOpposite();
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && player.isCreative()) {
            BedPart part = state.getValue(PART);
            if (part == BedPart.HEAD) {
                BlockPos footPos = pos.relative(state.getValue(FACING).getOpposite());
                BlockState footState = level.getBlockState(footPos);
                if (footState.is(this) && footState.getValue(PART) == BedPart.FOOT) {
                    level.setBlock(footPos, Blocks.AIR.defaultBlockState(), 35);
                    level.levelEvent(player, 2001, footPos, Block.getId(footState));
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public long getSeed(BlockState state, BlockPos pos) {
        BlockPos footPos = pos.relative(state.getValue(FACING), state.getValue(PART) == BedPart.HEAD ? 0 : 1);
        return BlockPos.asLong(footPos.getX(), pos.getY(), footPos.getZ());
    }

    public enum BedPart implements StringRepresentable {
        HEAD("head"),
        FOOT("foot");

        private final String name;

        BedPart(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
