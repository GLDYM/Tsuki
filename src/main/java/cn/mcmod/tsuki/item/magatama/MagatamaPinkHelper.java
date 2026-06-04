package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;

public final class MagatamaPinkHelper {
    private static final int HOTBAR_SIZE = 9;
    public static final int RESTORE_INTERVAL_TICKS = 1200;
    public static final int ACTIVE_COOLDOWN_TICKS = 1200;

    private MagatamaPinkHelper() {
    }

    public static boolean hasActivePinkMagatama(Player player) {
        return !findActiveStack(player).isEmpty();
    }

    public static ItemStack findActiveStack(Player player) {
        for (int slot = 0; slot < HOTBAR_SIZE; slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (stack.is(ArmorToolRegistry.MAGATAMA_PINK.get())) {
                return stack;
            }
        }
        ItemStack offhand = player.getOffhandItem();
        if (offhand.is(ArmorToolRegistry.MAGATAMA_PINK.get())) {
            return offhand;
        }
        return CuriosCompat.findFirstEquippedStack(player, ArmorToolRegistry.MAGATAMA_PINK.get());
    }

    public static void restoreFood(Player player) {
        FoodData foodData = player.getFoodData();
        foodData.setFoodLevel(20);
        foodData.setSaturation(20.0F);
    }
}
