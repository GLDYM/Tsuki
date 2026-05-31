package cn.mcmod.tsuki.compat.curios;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

final class CuriosCompatImpl {
    private CuriosCompatImpl() {
    }

    static boolean isEquipped(Player player, Item item) {
        return CuriosApi.getCuriosInventory(player)
                .map(ICuriosItemHandler::getCurios)
                .stream()
                .flatMap(curios -> curios.values().stream())
                .map(ICurioStacksHandler::getStacks)
                .anyMatch(stacks -> containsItem(stacks, item));
    }

    private static boolean containsItem(IDynamicStackHandler stacks, Item item) {
        for (int slot = 0; slot < stacks.getSlots(); slot++) {
            if (stacks.getStackInSlot(slot).is(item)) {
                return true;
            }
        }
        return false;
    }
}
