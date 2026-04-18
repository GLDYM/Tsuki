
package cn.mcmod.tsuki.block.machines;

import net.minecraft.world.level.block.state.BlockBehaviour;

import cn.mcmod.tsuki.block.entity.BlockEntityRegistry;
import cn.mcmod.tsuki.block.entity.ChoppingBoardBlockEntity;
import cn.mcmod.tsuki.tags.TsukiItemTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChoppingBoardBlock extends BaseEntityBlock {
    public static final MapCodec<ChoppingBoardBlock> CODEC = simpleCodec(ChoppingBoardBlock::new);
    protected static final VoxelShape SHAPE_NS = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 2.0D, 12.0D);
    protected static final VoxelShape SHAPE_WE = Block.box(4.0D, 0.0D, 0.0D, 12.0D, 2.0D, 16.0D);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public ChoppingBoardBlock(Properties properties) {
        super(properties);
    }

    public ChoppingBoardBlock() {
        this(BlockBehaviour.Properties.of().noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level worldIn, BlockPos pos,
            Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (blockEntity instanceof ChoppingBoardBlockEntity board) {
            ItemStack offhandStack = player.getOffhandItem();

            if (board.isEmpty()) {
                if (!offhandStack.isEmpty()) {
                    if (handIn.equals(InteractionHand.MAIN_HAND) && !offhandStack.is(TsukiItemTags.OFFHAND_EQUIPMENT)
                            && !(heldStack.getItem() instanceof BlockItem)) {
                        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; // Pass to off-hand if that item
                                                                                        // is placeable
                    }
                    if (handIn.equals(InteractionHand.OFF_HAND) && offhandStack.is(TsukiItemTags.OFFHAND_EQUIPMENT)) {
                        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; // Items in this tag should not
                                                                                        // be placed from the off-hand
                    }
                }
                if (heldStack.isEmpty()) {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                } else if (board.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
                    worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WOOD_PLACE,
                            SoundSource.BLOCKS, 1.0F, 0.8F);
                    return ItemInteractionResult.sidedSuccess(worldIn.isClientSide);
                }

            } else if (!heldStack.isEmpty()) {
                ItemStack boardStack = board.getStoredItem().copy();
                if (board.processStoredItemUsingTool(heldStack, player)) {
                    spawnCuttingParticles(worldIn, pos, boardStack, 5);
                    return ItemInteractionResult.sidedSuccess(worldIn.isClientSide);
                }
                return ItemInteractionResult.CONSUME;
            } else if (board.getRecipeTime() > 0) {
                if (player != null)
                    player.displayClientMessage(Component.translatable("tsuki.block.chopping_board.has_chopped"),
                            true);
                return ItemInteractionResult.sidedSuccess(worldIn.isClientSide);
            } else if (handIn.equals(InteractionHand.MAIN_HAND)) {
                if (!player.isCreative()) {
                    if (!player.getInventory().add(board.removeItem())) {
                        Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), board.removeItem());
                    }
                } else {
                    board.removeItem();
                }
                worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.WOOD_HIT, SoundSource.BLOCKS,
                        0.25F, 0.5F);
                return ItemInteractionResult.sidedSuccess(worldIn.isClientSide);
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static void spawnCuttingParticles(Level worldIn, BlockPos pos, ItemStack stack, int count) {
        for (int i = 0; i < count; ++i) {
            Vec3 vec3d = new Vec3(((double) worldIn.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D,
                    ((double) worldIn.random.nextFloat() - 0.5D) * 0.1D);
            if (worldIn instanceof ServerLevel serverlevel) {
                serverlevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), pos.getX() + 0.5F,
                        pos.getY() + 0.1F, pos.getZ() + 0.5F, 1, vec3d.x, vec3d.y + 0.05D, vec3d.z, 0.0D);
            } else {
                worldIn.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack), pos.getX() + 0.5F,
                        pos.getY() + 0.1F, pos.getZ() + 0.5F, vec3d.x, vec3d.y + 0.05D, vec3d.z);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = worldIn.getBlockEntity(pos);
            if (blockEntity instanceof ChoppingBoardBlockEntity board) {
                Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), board.getStoredItem());
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.CHOPPING_BOARD.get().create(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return facing == Direction.NORTH || facing == Direction.SOUTH ? SHAPE_NS : SHAPE_WE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
