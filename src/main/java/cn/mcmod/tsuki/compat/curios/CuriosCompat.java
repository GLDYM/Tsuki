package cn.mcmod.tsuki.compat.curios;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;

public final class CuriosCompat {
    private CuriosCompat() {
    }

    public static boolean isEquipped(Player player, Item item) {
        if (!ModList.get().isLoaded("curios")) {
            return false;
        }
        return CuriosCompatImpl.isEquipped(player, item);
    }
}
