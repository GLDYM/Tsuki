package cn.mcmod.tsuki.block.machines;

import cn.mcmod.tsuki.item.DrinkRegistry;
import cn.mcmod.tsuki.item.WineBottleItem;
import cn.mcmod.tsuki.item.enums.TsukiWineBottleSet;
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
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

public class FermenterBlock extends BaseEntityBlock {
    private static final int WINE_BOTTLE_TRANSFER_MB = 20;
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

        if (handleWineBottleInteraction(stack, cookingPot, player, handIn, level)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
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

    private boolean handleWineBottleInteraction(
            ItemStack heldStack,
            FermenterBlockEntity blockEntity,
            Player player,
            InteractionHand hand,
            Level level) {
        FluidTank outputTank = blockEntity.getOutputFluidTank();
        FluidTank inputTank = blockEntity.getInputFluidTank();

        if (heldStack.is(DrinkRegistry.WINE_BOTTLE.get())) {
            Fluid outFluid;
            int maxUnit;
            if (outputTank.getFluidAmount() > WINE_BOTTLE_TRANSFER_MB) {
                outFluid = outputTank.getFluid().getFluid();
                maxUnit = outputTank.getFluidAmount() / WINE_BOTTLE_TRANSFER_MB;
            } else if (inputTank.getFluidAmount() > WINE_BOTTLE_TRANSFER_MB) {
                outFluid = inputTank.getFluid().getFluid();
                maxUnit = inputTank.getFluidAmount() / WINE_BOTTLE_TRANSFER_MB;
            } else {
                return false;
            }

            TsukiWineBottleSet set = TsukiWineBottleSet.fromFluid(outFluid);
            if (set == null) {
                return false;
            }
            ItemStack resultBottle = new ItemStack(DrinkRegistry.WINE_BOTTLES.get(set).get());
            int extractUnits = Math.min(resultBottle.getMaxDamage(), maxUnit);
            int extractAmount = extractUnits * WINE_BOTTLE_TRANSFER_MB;
            // Each 20mB equals one durability unit; extract as much as possible in one
            // interaction.
            if (resultBottle.isDamageableItem()) {
                resultBottle.setDamageValue(resultBottle.getMaxDamage() - extractUnits);
            }
            if (level.isClientSide()) {
                return true;
            }
            outputTank.drain(extractAmount, FluidAction.EXECUTE);
            heldStack.shrink(1);
            if (heldStack.isEmpty()) {
                player.setItemInHand(hand, resultBottle);
            } else if (!player.addItem(resultBottle)) {
                player.drop(resultBottle, false);
            }
            return true;
        }

        TsukiWineBottleSet wineBottleSet = TsukiWineBottleSet.fromItem(heldStack.getItem());
        if (wineBottleSet == null) {
            return false;
        }
        if (!(heldStack.getItem() instanceof WineBottleItem wineBottleItem)) {
            return false;
        }
        if (heldStack.getDamageValue() >= heldStack.getMaxDamage()) {
            return false;
        }
        int inputUnit = Math.min(heldStack.getMaxDamage() - heldStack.getDamageValue(),
                (inputTank.getCapacity() - inputTank.getFluidAmount()) / WINE_BOTTLE_TRANSFER_MB);
        FluidStack toFill = new FluidStack(wineBottleItem.getFluid(), WINE_BOTTLE_TRANSFER_MB * inputUnit);
        if (inputTank.fill(toFill, FluidAction.SIMULATE) < WINE_BOTTLE_TRANSFER_MB * inputUnit) {
            return false;
        }
        if (level.isClientSide()) {
            return true;
        }
        inputTank.fill(toFill, FluidAction.EXECUTE);
        int nextDamage = heldStack.getDamageValue() + inputUnit;
        if (nextDamage >= heldStack.getMaxDamage()) {
            ItemStack emptyBottle = new ItemStack(DrinkRegistry.WINE_BOTTLE.get());
            player.setItemInHand(hand, emptyBottle);
        } else {
            heldStack.setDamageValue(nextDamage);
        }
        return true;
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = worldIn.getBlockEntity(pos);
            if (blockEntity instanceof FermenterBlockEntity fermenterBlockEntity) {
                Containers.dropContents(worldIn, pos, fermenterBlockEntity.getDroppableInventory());
                fermenterBlockEntity.grantStoredRecipeExperience(worldIn, Vec3.atCenterOf(pos));
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
