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
            ItemStack remaining = stack.copy();
            ItemStack single = stack.copyWithCount(1);
            int[] emptyCandidates = new int[exposedSlots.length];
            int emptyCount = 0;

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

        return itemHandler.insertItem(mapSlot(slot, exposedSlots), stack, simulate);
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
