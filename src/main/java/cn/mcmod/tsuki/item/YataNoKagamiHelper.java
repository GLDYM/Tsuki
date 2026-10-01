package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.player.Player;

public final class YataNoKagamiHelper {
    private static final int HOTBAR_SIZE = 9;

    private YataNoKagamiHelper() {
    }

    public static boolean isActive(Player player) {
        if (!player.getAbilities().flying) {
            return false;
        }
        for (int slot = 0; slot < HOTBAR_SIZE; slot++) {
            if (player.getInventory().getItem(slot).is(ArmorToolRegistry.YATA_NO_KAGAMI.get())) {
                return true;
            }
        }
        return !CuriosCompat.findFirstEquippedStack(player, ArmorToolRegistry.YATA_NO_KAGAMI.get()).isEmpty();
    }
}
