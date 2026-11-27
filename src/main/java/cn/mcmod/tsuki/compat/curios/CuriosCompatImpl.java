package cn.mcmod.tsuki.compat.curios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

final class CuriosCompatImpl {
    private CuriosCompatImpl() {
    }

    static boolean isEquipped(Player player, Item item) {
        return !findFirstEquippedStack(player, item).isEmpty();
    }

    static ItemStack findFirstEquippedStack(Player player, Item item) {
        var optionalCurios = CuriosApi.getCuriosInventory(player);
        if (optionalCurios.isEmpty()) {
            return ItemStack.EMPTY;
        }
        for (ICurioStacksHandler handler : optionalCurios.get().getCurios().values()) {
            ItemStack found = findItem(handler.getStacks(), item);
            if (!found.isEmpty()) {
                return found;
            }
        }
        return ItemStack.EMPTY;
    }

    static List<ItemStack> removeStacks(Player player, Predicate<ItemStack> predicate) {
        List<ItemStack> removed = new ArrayList<>();
        for (CuriosCompat.RemovedCurioStack entry : removeStacksWithSlots(player, predicate)) {
            removed.add(entry.stack());
        }
        return removed;
    }

    static List<CuriosCompat.RemovedCurioStack> removeStacksWithSlots(Player player, Predicate<ItemStack> predicate) {
        List<CuriosCompat.RemovedCurioStack> removed = new ArrayList<>();
        CuriosApi.getCuriosInventory(player).ifPresent(curios -> {
            for (var entry : curios.getCurios().entrySet()) {
                String slotId = entry.getKey();
                IDynamicStackHandler stacks = entry.getValue().getStacks();
                for (int slot = 0; slot < stacks.getSlots(); slot++) {
                    ItemStack stack = stacks.getStackInSlot(slot);
                    if (predicate.test(stack)) {
                        removed.add(new CuriosCompat.RemovedCurioStack(slotId, slot, stack.copy()));
                        stacks.setStackInSlot(slot, ItemStack.EMPTY);
                    }
                }
            }
        });
        return removed;
    }

    static boolean tryRestoreStack(Player player, String slotId, int slotIndex, ItemStack stack) {
        return CuriosApi.getCuriosInventory(player)
                .map(curios -> {
                    ICurioStacksHandler handler = curios.getCurios().get(slotId);
                    if (handler == null) {
                        return false;
                    }
                    IDynamicStackHandler stacks = handler.getStacks();
                    if (slotIndex >= 0 && slotIndex < stacks.getSlots() && stacks.getStackInSlot(slotIndex).isEmpty()) {
                        stacks.setStackInSlot(slotIndex, stack.copy());
                        return true;
                    }
                    for (int slot = 0; slot < stacks.getSlots(); slot++) {
                        if (stacks.getStackInSlot(slot).isEmpty()) {
                            stacks.setStackInSlot(slot, stack.copy());
                            return true;
                        }
                    }
                    return false;
                })
                .orElse(false);
    }

    private static ItemStack findItem(IDynamicStackHandler stacks, Item item) {
        for (int slot = 0; slot < stacks.getSlots(); slot++) {
            ItemStack stack = stacks.getStackInSlot(slot);
            if (stack.is(item)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
