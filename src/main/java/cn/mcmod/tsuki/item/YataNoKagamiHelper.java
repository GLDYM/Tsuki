package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.item.magatama.MagatamaEquipmentCache;
import net.minecraft.world.entity.player.Player;

public final class YataNoKagamiHelper {
    private static final int HOTBAR_SIZE = 9;

    private YataNoKagamiHelper() {
    }

    public static boolean isActive(Player player) {
        return player.getAbilities().flying
                && MagatamaEquipmentCache.isActive(player, ArmorToolRegistry.YATA_NO_KAGAMI.get());
    }
}
