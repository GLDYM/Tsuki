package cn.mcmod.tsuki.block.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class FermenterItemHandler implements IItemHandler {
    private static final int SLOTS_INPUT = 3;
    private static final int SLOT_OUTPUT_BEGIN = 3;
    private static final int SLOT_OUTPUT_END = 5;

    private final IItemHandler itemHandler;

    public FermenterItemHandler(IItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    public IItemHandler forSide(@Nullable Direction side) {
        if (side == null) {
            return itemHandler;
        }
        return new IItemHandler() {
            @Override
            public int getSlots() {
                return FermenterItemHandler.this.getSlots();
            }

            @Override
            @Nonnull
            public ItemStack getStackInSlot(int slot) {
                return FermenterItemHandler.this.getStackInSlot(slot);
            }

            @Override
            @Nonnull
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return FermenterItemHandler.this.insertItem(slot, stack, side, simulate);
            }

            @Override
            @Nonnull
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return FermenterItemHandler.this.extractItem(slot, amount, side, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                return FermenterItemHandler.this.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return FermenterItemHandler.this.isItemValid(slot, stack);
            }
        };
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return itemHandler.isItemValid(slot, stack);
    }

    @Override
    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, @Nullable Direction side, boolean simulate) {
        return slot < SLOTS_INPUT ? itemHandler.insertItem(slot, stack, simulate) : stack;
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return insertItem(slot, stack, null, simulate);
    }

    public ItemStack extractItem(int slot, int amount, @Nullable Direction side, boolean simulate) {
        if (side == null || side == Direction.UP) {
            return slot < SLOTS_INPUT ? itemHandler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
        }
        return slot >= SLOT_OUTPUT_BEGIN && slot <= SLOT_OUTPUT_END
                ? itemHandler.extractItem(slot, amount, simulate)
                : ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return extractItem(slot, amount, null, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }
}
