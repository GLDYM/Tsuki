package cn.mcmod.tsuki.item.drink;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.items.ItemStackHandler;

public final class ShakerDataHelper {
    public static final int SLOT_INPUT_START = 0;
    public static final int SLOT_INPUT_COUNT = 9;
    public static final int SLOT_OUTPUT = 9;
    public static final int SLOT_COUNT = 10;

    private static final String TAG_SHAKER = "ShakerData";
    private static final String TAG_INVENTORY = "Inventory";
    private static final String TAG_SHAKE_PROGRESS = "ShakeProgress";
    private static final String TAG_ITEMS = "Items";
    private static final String TAG_SLOT = "Slot";
    private static final String TAG_COUNT = "count";

    private ShakerDataHelper() {
    }

    public static ItemStackHandler createInventory() {
        return new ItemStackHandler(SLOT_COUNT) {
            @Override
            public int getSlotLimit(int slot) {
                return slot == SLOT_OUTPUT ? 64 : 1;
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return slot != SLOT_OUTPUT;
            }
        };
    }

    public static void load(ItemStack stack, ItemStackHandler inventory, HolderLookup.Provider registries) {
        CompoundTag data = getShakerData(stack);
        if (data.contains(TAG_INVENTORY)) {
            inventory.deserializeNBT(registries, data.getCompound(TAG_INVENTORY));
        }
    }

    public static int loadShakeProgress(ItemStack stack) {
        CompoundTag data = getShakerData(stack);
        return data.getInt(TAG_SHAKE_PROGRESS);
    }

    public static void save(ItemStack stack, ItemStackHandler inventory, int shakeProgress,
            HolderLookup.Provider registries) {
        CompoundTag root = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        boolean hasItems = false;
        for (int slot = 0; slot < SLOT_COUNT; ++slot) {
            if (!inventory.getStackInSlot(slot).isEmpty()) {
                hasItems = true;
                break;
            }
        }

        if (!hasItems && shakeProgress <= 0) {
            root.remove(TAG_SHAKER);
        } else {
            CompoundTag shakerData = new CompoundTag();
            shakerData.put(TAG_INVENTORY, inventory.serializeNBT(registries));
            if (shakeProgress > 0) {
                shakerData.putInt(TAG_SHAKE_PROGRESS, shakeProgress);
            }
            root.put(TAG_SHAKER, shakerData);
        }

        if (root.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(root));
        }
    }

    public static ItemStack getOutput(ItemStack shakerStack, HolderLookup.Provider registries) {
        ItemStackHandler inventory = createInventory();
        load(shakerStack, inventory, registries);
        return inventory.getStackInSlot(SLOT_OUTPUT).copy();
    }

    public static boolean canPourToContainer(ItemStack shakerStack, ItemStack containerStack,
            HolderLookup.Provider registries) {
        if (shakerStack.isEmpty() || containerStack.isEmpty()) {
            return false;
        }
        ItemStack output = getOutput(shakerStack, registries);
        if (output.isEmpty()) {
            return false;
        }
        ItemStack requiredContainer = getRequiredContainer(output);
        return !requiredContainer.isEmpty() && ItemStack.isSameItemSameComponents(requiredContainer, containerStack);
    }

    public static ItemStack removeOneOutput(ItemStack shakerStack, HolderLookup.Provider registries) {
        ItemStack output = getOutput(shakerStack, registries);
        if (output.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack poured = output.copyWithCount(1);
        decreaseOutputInCustomData(shakerStack);
        return poured;
    }

    public static ItemStack copyWithRemovedOneOutput(ItemStack shakerStack) {
        ItemStack copy = shakerStack.copyWithCount(1);
        decreaseOutputInCustomData(copy);
        return copy;
    }

    public static ItemStack getRequiredContainer(ItemStack filledStack) {
        if (filledStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        if (filledStack.getItem() instanceof DrinkItem drinkItem) {
            return new ItemStack(drinkItem.getContainerItem().get());
        }
        if (filledStack.getItem() instanceof WineBottleItem wineBottleItem) {
            return new ItemStack(wineBottleItem.getCraftingRemainingItem(filledStack).getItem());
        }
        return ItemStack.EMPTY;
    }

    private static CompoundTag getShakerData(ItemStack stack) {
        CompoundTag root = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return root.contains(TAG_SHAKER) ? root.getCompound(TAG_SHAKER) : new CompoundTag();
    }

    private static void decreaseOutputInCustomData(ItemStack shakerStack) {
        CustomData.update(DataComponents.CUSTOM_DATA, shakerStack, root -> {
            if (!root.contains(TAG_SHAKER, Tag.TAG_COMPOUND)) {
                return;
            }

            CompoundTag shakerData = root.getCompound(TAG_SHAKER);
            if (!shakerData.contains(TAG_INVENTORY, Tag.TAG_COMPOUND)) {
                return;
            }

            CompoundTag inventory = shakerData.getCompound(TAG_INVENTORY);
            if (!inventory.contains(TAG_ITEMS, Tag.TAG_LIST)) {
                return;
            }

            ListTag items = inventory.getList(TAG_ITEMS, Tag.TAG_COMPOUND);
            for (int i = 0; i < items.size(); ++i) {
                CompoundTag itemTag = items.getCompound(i);
                if (itemTag.getInt(TAG_SLOT) != SLOT_OUTPUT) {
                    continue;
                }

                int count = itemTag.getInt(TAG_COUNT);
                if (count <= 1) {
                    items.remove(i);
                } else {
                    itemTag.putInt(TAG_COUNT, count - 1);
                }
                break;
            }

            if (items.isEmpty() && shakerData.getInt(TAG_SHAKE_PROGRESS) <= 0) {
                root.remove(TAG_SHAKER);
            }
        });
    }
}
