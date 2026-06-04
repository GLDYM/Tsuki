package cn.mcmod.tsuki.compat.curios;

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
        return CuriosApi.getCuriosInventory(player)
                .map(ICuriosItemHandler::getCurios)
                .stream()
                .flatMap(curios -> curios.values().stream())
                .map(ICurioStacksHandler::getStacks)
                .map(stacks -> findItem(stacks, item))
                .filter(stack -> !stack.isEmpty())
                .findFirst()
                .orElse(ItemStack.EMPTY);
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
