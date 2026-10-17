package cn.mcmod.tsuki.block.capability;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class CookingPotItemHandler implements IItemHandler {
    private final IItemHandler itemHandler;

    public CookingPotItemHandler(IItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    public IItemHandler forSide(@Nullable Direction side) {
        if (side == null) {
            return itemHandler;
        }
        int[] exposedSlots = createExposedSlots(side);
        return new IItemHandler() {
            @Override
            public int getSlots() {
                return exposedSlots.length;
            }

            @Override
            @Nonnull
            public ItemStack getStackInSlot(int slot) {
                return CookingPotItemHandler.this.getStackInSlot(slot, exposedSlots);
            }

            @Override
            @Nonnull
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return CookingPotItemHandler.this.insertItem(slot, stack, side, simulate, exposedSlots);
            }

            @Override
            @Nonnull
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return CookingPotItemHandler.this.extractItem(slot, amount, side, simulate, exposedSlots);
            }

            @Override
            public int getSlotLimit(int slot) {
                return CookingPotItemHandler.this.getSlotLimit(slot, exposedSlots);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return CookingPotItemHandler.this.isItemValid(slot, stack, exposedSlots);
            }
        };
    }

    private static int[] createExposedSlots(@Nullable Direction side) {
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

    private int mapSlot(int slot, int[] exposedSlots) {
        if (slot < 0 || slot >= exposedSlots.length) {
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + exposedSlots.length + ")");
        }
        return exposedSlots[slot];
    }

    private boolean isInputView(@Nullable Direction side, int[] exposedSlots) {
        return side == Direction.UP && exposedSlots.length == CookingPotBlockEntity.SLOT_INPUT_COUNT;
    }

    public boolean isItemValid(int slot, @Nonnull ItemStack stack, int[] exposedSlots) {
        return itemHandler.isItemValid(mapSlot(slot, exposedSlots), stack);
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

    public ItemStack getStackInSlot(int slot, int[] exposedSlots) {
        return itemHandler.getStackInSlot(mapSlot(slot, exposedSlots));
    }

    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, @Nullable Direction side, boolean simulate,
            int[] exposedSlots) {
        if (isInputView(side, exposedSlots) && !stack.isEmpty()) {
            ItemStack[] plannedStacks = new ItemStack[exposedSlots.length];
            int[] insertionCounts = new int[exposedSlots.length];
            for (int i = 0; i < exposedSlots.length; ++i) {
                plannedStacks[i] = itemHandler.getStackInSlot(exposedSlots[i]).copy();
            }

            int insertedCount = 0;
            while (insertedCount < stack.getCount()) {
                int targetIndex = findInputSlot(plannedStacks, exposedSlots, stack);
                if (targetIndex < 0) {
                    break;
                }

                ItemStack targetStack = plannedStacks[targetIndex];
                if (targetStack.isEmpty()) {
                    plannedStacks[targetIndex] = stack.copyWithCount(1);
                } else {
                    targetStack.grow(1);
                }
                ++insertionCounts[targetIndex];
                ++insertedCount;
            }

            if (!simulate) {
                ItemStack single = stack.copyWithCount(1);
                for (int i = 0; i < exposedSlots.length; ++i) {
                    if (insertionCounts[i] > 0) {
                        itemHandler.insertItem(exposedSlots[i], single.copyWithCount(insertionCounts[i]), false);
                    }
                }
            }

            return insertedCount == stack.getCount()
                    ? ItemStack.EMPTY
                    : stack.copyWithCount(stack.getCount() - insertedCount);
        }

        return itemHandler.insertItem(mapSlot(slot, exposedSlots), stack, simulate);
    }

    private int findInputSlot(ItemStack[] plannedStacks, int[] exposedSlots, ItemStack stack) {
        for (int i = 0; i < plannedStacks.length; ++i) {
            ItemStack plannedStack = plannedStacks[i];
            if (plannedStack.isEmpty()) {
                if (canInsertIntoSlot(exposedSlots[i], plannedStack, stack)) {
                    return i;
                }
                continue;
            }
            if (!ItemStack.isSameItemSameComponents(plannedStack, stack)
                    || !canInsertIntoSlot(exposedSlots[i], plannedStack, stack)) {
                continue;
            }

            boolean smallerStackExists = false;
            for (int j = i + 1; j < plannedStacks.length; ++j) {
                ItemStack laterStack = plannedStacks[j];
                if (laterStack.isEmpty()
                        || (ItemStack.isSameItemSameComponents(laterStack, stack)
                                && laterStack.getCount() < plannedStack.getCount())) {
                    smallerStackExists = true;
                    break;
                }
            }
            if (!smallerStackExists) {
                return i;
            }
        }
        return -1;
    }

    private boolean canInsertIntoSlot(int slot, ItemStack existing, ItemStack stack) {
        if (!itemHandler.isItemValid(slot, stack)) {
            return false;
        }
        int limit = Math.min(itemHandler.getSlotLimit(slot), stack.getMaxStackSize());
        return existing.isEmpty()
                ? limit > 0
                : ItemStack.isSameItemSameComponents(existing, stack) && existing.getCount() < limit;
    }

    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, @Nullable Direction side, boolean simulate) {
        return insertItem(slot, stack, side, simulate, createExposedSlots(side == null ? Direction.NORTH : side));
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return itemHandler.insertItem(slot, stack, simulate);
    }

    public ItemStack extractItem(int slot, int amount, @Nullable Direction side, boolean simulate, int[] exposedSlots) {
        return itemHandler.extractItem(mapSlot(slot, exposedSlots), amount, simulate);
    }

    public ItemStack extractItem(int slot, int amount, @Nullable Direction side, boolean simulate) {
        return extractItem(slot, amount, side, simulate, createExposedSlots(side == null ? Direction.NORTH : side));
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return itemHandler.extractItem(slot, amount, simulate);
    }

    public int getSlotLimit(int slot, int[] exposedSlots) {
        return itemHandler.getSlotLimit(mapSlot(slot, exposedSlots));
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }
}
