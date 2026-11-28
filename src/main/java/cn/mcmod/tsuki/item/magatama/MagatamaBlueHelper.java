package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public final class MagatamaBlueHelper {
    private static final int HOTBAR_SIZE = 9;

    private MagatamaBlueHelper() {
    }

    public static ItemStack findActiveStack(Player player) {
        return MagatamaEquipmentCache.getActiveStack(player, ArmorToolRegistry.MAGATAMA_BLUE.get());
    }

    public static MagatamaBlueItem.WeatherMode toggleMainHandMode(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (!stack.is(ArmorToolRegistry.MAGATAMA_BLUE.get())) {
            return null;
        }
        return MagatamaBlueItem.toggleWeatherMode(stack);
    }
}
