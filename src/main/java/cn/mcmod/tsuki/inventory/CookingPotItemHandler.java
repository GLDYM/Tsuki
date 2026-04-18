package cn.mcmod.tsuki.inventory;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class CookingPotItemHandler implements IItemHandler {
    private final IItemHandler itemHandler;
    private final int[] exposedSlots;

    public CookingPotItemHandler(IItemHandler itemHandler, @Nullable Direction side) {
        this.itemHandler = itemHandler;
        this.exposedSlots = createExposedSlots(side);
    }

    private static int[] createExposedSlots(@Nullable Direction side) {
        if (side == null) {
            int[] allSlots = new int[CookingPotBlockEntity.SLOT_COUNT];
            for (int i = 0; i < allSlots.length; i++) {
                allSlots[i] = i;
            }
            return allSlots;
        }

        if (side == Direction.UP) {
            int[] inputSlots = new int[CookingPotBlockEntity.SLOT_INPUT_COUNT];
            for (int i = 0; i < inputSlots.length; i++) {
                inputSlots[i] = CookingPotBlockEntity.SLOT_INPUT_START + i;
            }
            return inputSlots;
        }

        if (side == Direction.DOWN) {
            return new int[] { CookingPotBlockEntity.SLOT_OUTPUT };
        }

        return new int[] { CookingPotBlockEntity.SLOT_CONTAINER_INPUT };
    }

    private int mapSlot(int slot) {
        if (slot < 0 || slot >= exposedSlots.length) {
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + exposedSlots.length + ")");
        }
        return exposedSlots[slot];
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return itemHandler.isItemValid(mapSlot(slot), stack);
    }

    @Override
    public int getSlots() {
        return exposedSlots.length;
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(mapSlot(slot));
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return itemHandler.insertItem(mapSlot(slot), stack, simulate);
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return itemHandler.extractItem(mapSlot(slot), amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(mapSlot(slot));
    }
}
