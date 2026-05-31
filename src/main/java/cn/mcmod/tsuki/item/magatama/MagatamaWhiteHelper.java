package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class MagatamaWhiteHelper {
    private static final int HOTBAR_SIZE = 9;

    private MagatamaWhiteHelper() {
    }

    public static boolean hasActiveWhiteMagatama(Player player) {
        return !findActiveStack(player).isEmpty();
    }

    public static boolean isCreativeModeActive(Player player) {
        ItemStack stack = findActiveStack(player);
        return !stack.isEmpty() && MagatamaWhiteItem.getFlightMode(stack) == MagatamaWhiteItem.FlightMode.CREATIVE;
    }

    public static boolean isElytraModeActive(Player player) {
        ItemStack stack = findActiveStack(player);
        return !stack.isEmpty() && MagatamaWhiteItem.getFlightMode(stack) == MagatamaWhiteItem.FlightMode.ELYTRA;
    }

    public static MagatamaWhiteItem.FlightMode toggleActiveMode(Player player) {
        ItemStack stack = findActiveStack(player);
        if (stack.isEmpty()) {
            return null;
        }
        return MagatamaWhiteItem.toggleFlightMode(stack);
    }

    public static ItemStack findActiveStack(Player player) {
        for (int slot = 0; slot < HOTBAR_SIZE; slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (stack.is(ArmorToolRegistry.MAGATAMA_WHITE.get())) {
                return stack;
            }
        }
        return CuriosCompat.findFirstEquippedStack(player, ArmorToolRegistry.MAGATAMA_WHITE.get());
    }
}
