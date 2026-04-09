package cn.mcmod.tsuki.block.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cn.mcmod.mmlib.block.entity.HeatableBlockEntity;
import cn.mcmod.tsuki.block.MapleCauldronBlock;
import cn.mcmod.tsuki.block.MapleSpileBlock;
import cn.mcmod.tsuki.fluid.FluidRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class MapleCauldronBlockEntity extends net.minecraft.world.level.block.entity.BlockEntity implements HeatableBlockEntity {
    public static final int TANK_CAPACITY = 5000;

    private final ItemStackHandler inventory;
    private final FluidTank fluidTank;

    private int cookTime;
    private int mapleTime;

    public MapleCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.MAPLE_CAULDRON.get(), pos, state);
        this.inventory = createHandler();
        this.fluidTank = createFluidTank();
    }

    public static void workingTick(Level level, BlockPos pos, BlockState state, MapleCauldronBlockEntity blockEntity) {
        if (level.isClientSide) {
            return;
        }
        blockEntity.drawingTick(level, pos);
        blockEntity.cookingTick(level, pos);
    }

    private void drawingTick(Level level, BlockPos pos) {
        if (!canDraw(level, pos)) {
            mapleTime = 0;
            return;
        }

        mapleTime += level.random.nextInt(9) + 1;
        if (mapleTime >= 20) {
            mapleTime = 0;
            fluidTank.fill(new FluidStack(FluidRegistry.MAPLE_SYRUP.get(), 10), FluidAction.EXECUTE);
        }
    }

    private void cookingTick(Level level, BlockPos pos) {
        if (!isBurning(level, pos)) {
            cookTime = 0;
            return;
        }

        ItemStack out = inventory.getStackInSlot(0);
        ItemStack syrupItem = new ItemStack(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MAPLE_SYRUP).get(), 8);

        if (!out.isEmpty() && !ItemStack.isSameItemSameComponents(out, syrupItem)) {
            cookTime = 0;
            return;
        }
        if (!out.isEmpty() && out.getCount() + syrupItem.getCount() > out.getMaxStackSize()) {
            cookTime = 0;
            return;
        }

        cookTime++;
        if (cookTime >= 1200) {
            cookTime = 0;
            fluidTank.drain(500, FluidAction.EXECUTE);
            if (out.isEmpty()) {
                inventory.setStackInSlot(0, syrupItem.copy());
            } else {
                out.grow(8);
                inventory.setStackInSlot(0, out);
            }
            inventoryChanged();
        }
    }

    private boolean isBurning(Level level, BlockPos pos) {
        FluidStack fluid = fluidTank.getFluid();
        if (fluid.isEmpty() || !fluid.getFluid().isSame(FluidRegistry.MAPLE_SYRUP.get()) || fluid.getAmount() < 500) {
            return false;
        }
        return isHeated(level, pos);
    }

    private boolean canDraw(Level level, BlockPos pos) {
        BlockState above = level.getBlockState(pos.above());
        if (!(above.getBlock() instanceof MapleSpileBlock)) {
            return false;
        }
        return MapleSpileBlock.canWork(level, pos.above(), above);
    }

    public boolean tryTakeOutput(Player player, InteractionHand hand) {
        ItemStack output = inventory.getStackInSlot(0);
        if (output.isEmpty()) {
            return false;
        }

        ItemStack one = output.copyWithCount(1);
        if (!player.getInventory().add(one)) {
            player.drop(one, false);
        }
        output.shrink(1);
        inventoryChanged();
        return true;
    }

    @Nonnull
    public IItemHandler getItemHandler(@Nullable Direction side) {
        return inventory;
    }

    @Nonnull
    public FluidTank getFluidHandler(@Nullable Direction side) {
        return fluidTank;
    }

    public FluidTank getFluidTank() {
        return fluidTank;
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        drops.add(inventory.getStackInSlot(0));
        return drops;
    }

    public void inventoryChanged() {
        setChanged();
        if (level != null && !level.isClientSide) {
            syncLevelState();
            BlockState state = getBlockState();
            level.sendBlockUpdated(worldPosition, state, state, 3);
        }
    }

    private void syncLevelState() {
        if (level == null) {
            return;
        }

        BlockState state = getBlockState();
        if (!(state.getBlock() instanceof MapleCauldronBlock)) {
            return;
        }

        int amount = fluidTank.getFluidAmount();
        int visualLevel = amount <= 0 ? 0 : Mth.clamp((int) Math.ceil((amount * 7.0D) / TANK_CAPACITY), 1, 7);
        int currentLevel = state.getValue(MapleCauldronBlock.LEVEL);
        if (currentLevel != visualLevel) {
            level.setBlock(worldPosition, state.setValue(MapleCauldronBlock.LEVEL, visualLevel), 3);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        fluidTank.readFromNBT(registries, tag.getCompound("FluidTank"));
        mapleTime = tag.getInt("MapleTime");
        cookTime = tag.getInt("CookTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.put("FluidTank", fluidTank.writeToNBT(registries, new CompoundTag()));
        tag.putInt("MapleTime", mapleTime);
        tag.putInt("CookTime", cookTime);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return false;
            }
        };
    }

    private FluidTank createFluidTank() {
        return new FluidTank(TANK_CAPACITY) {
            @Override
            protected void onContentsChanged() {
                inventoryChanged();
                super.onContentsChanged();
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid().isSame(FluidRegistry.MAPLE_SYRUP.get());
            }
        };
    }
}
