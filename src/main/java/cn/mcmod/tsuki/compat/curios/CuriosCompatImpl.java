package cn.mcmod.tsuki.compat.curios;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

final class CuriosCompatImpl {
    private CuriosCompatImpl() {
    }

    static boolean isEquipped(Player player, Item item) {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, stack -> stack.is(item)).isPresent();
    }
}
