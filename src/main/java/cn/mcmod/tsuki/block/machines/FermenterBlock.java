package cn.mcmod.tsuki.block.machines;

import net.minecraft.world.level.block.state.BlockBehaviour;

import javax.annotation.Nullable;

import cn.mcmod.tsuki.block.entity.BlockEntityRegistry;
import cn.mcmod.tsuki.block.entity.FermenterBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

public class FermenterBlock extends BaseEntityBlock {
    public static final MapCodec<FermenterBlock> CODEC = simpleCodec(FermenterBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public FermenterBlock(Properties properties) {
        super(properties);
    }

    public FermenterBlock() {
        this(BlockBehaviour.Properties.of());
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
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.FERMENTER.get().create(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        return state;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand handIn, BlockHitResult result) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (!(blockentity instanceof FermenterBlockEntity cookingPot)) {
            return ItemInteractionResult.FAIL;
        }
        IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack.copyWithCount(1))
                .orElse(null);
        if (handler != null && handler instanceof FluidBucketWrapper) {
            FluidTank outTank = cookingPot.getOutputFluidTank();
            if (!outTank.getFluid().isEmpty()) {
                if (FluidUtil.interactWithFluidHandler(player, handIn, outTank)) {
                    return ItemInteractionResult.sidedSuccess(level.isClientSide);
                }
            }
            FluidUtil.interactWithFluidHandler(player, handIn, cookingPot.getInputFluidTank());
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (!level.isClientSide()) {
	        ((ServerPlayer) player).openMenu(cookingPot, pos);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof FermenterBlockEntity blockEntity) {
                Containers.dropContents(worldIn, pos, blockEntity.getDroppableInventory());
                blockEntity.grantStoredRecipeExperience(worldIn, Vec3.atCenterOf(pos));
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, BlockEntityRegistry.FERMENTER.get(), FermenterBlockEntity::workingTick);
    }
}


