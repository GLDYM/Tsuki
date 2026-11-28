package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class MagatamaOrangeHelper {
    private static final int HOTBAR_SIZE = 9;

    private MagatamaOrangeHelper() {
    }

    public static boolean hasActiveOrangeMagatama(Player player) {
        return !findActiveStack(player).isEmpty();
    }

    public static ItemStack findActiveStack(Player player) {
        return MagatamaEquipmentCache.getActiveStack(player, ArmorToolRegistry.MAGATAMA_ORANGE.get());
    }
}
