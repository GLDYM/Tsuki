package cn.mcmod.tsuki.block.capability;

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
        if (slot < 0 || slot >= exposedSlots.length) {
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + exposedSlots.length + ")");
        }

        if (exposedSlots.length == CookingPotBlockEntity.SLOT_INPUT_COUNT && !stack.isEmpty()) {
            ItemStack remaining = stack.copy();
            ItemStack single = stack.copyWithCount(1);
            int[] emptyCandidates = new int[exposedSlots.length];
            int emptyCount = 0;

            // 1: query all empty slots, then insert 1 per slot in order.
            for (int exposedSlot : exposedSlots) {
                if (!itemHandler.getStackInSlot(exposedSlot).isEmpty()) {
                    continue;
                }
                if (!itemHandler.insertItem(exposedSlot, single, true).isEmpty()) {
                    continue;
                }
                emptyCandidates[emptyCount++] = exposedSlot;
            }

            for (int i = 0; i < emptyCount && !remaining.isEmpty(); ++i) {
                if (itemHandler.insertItem(emptyCandidates[i], single, simulate).isEmpty()) {
                    remaining.shrink(1);
                }
            }

            // 2: no empty slots. Repeatedly choose the compatible slot with the
            // smallest stack count (tie: earlier slot), then insert as much as possible.
            while (!remaining.isEmpty()) {
                int bestSlot = -1;
                int bestCount = Integer.MAX_VALUE;

                for (int exposedSlot : exposedSlots) {
                    ItemStack slotStack = itemHandler.getStackInSlot(exposedSlot);
                    if (slotStack.isEmpty()) {
                        continue;
                    }
                    if (!ItemStack.isSameItemSameComponents(slotStack, remaining)) {
                        continue;
                    }
                    if (!itemHandler.insertItem(exposedSlot, single, true).isEmpty()) {
                        continue;
                    }

                    int count = slotStack.getCount();
                    if (count < bestCount) {
                        bestCount = count;
                        bestSlot = exposedSlot;
                    }
                }

                if (bestSlot == -1) {
                    break;
                }

                ItemStack before = remaining;
                remaining = itemHandler.insertItem(bestSlot, remaining, simulate);
                if (remaining.getCount() == before.getCount()) {
                    break;
                }
            }
            return remaining;
        }

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
