package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class MagatamaRedHelper {
    private static final int HOTBAR_SIZE = 9;

    private MagatamaRedHelper() {
    }

    public static boolean hasActiveRedMagatama(Player player) {
        return !findActiveStack(player).isEmpty();
    }

    public static ItemStack findActiveStack(Player player) {
        for (int slot = 0; slot < HOTBAR_SIZE; slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (stack.is(ArmorToolRegistry.MAGATAMA_RED.get())) {
                return stack;
            }
        }
        ItemStack offhand = player.getOffhandItem();
        if (offhand.is(ArmorToolRegistry.MAGATAMA_RED.get())) {
            return offhand;
        }
        return CuriosCompat.findFirstEquippedStack(player, ArmorToolRegistry.MAGATAMA_RED.get());
    }
}

