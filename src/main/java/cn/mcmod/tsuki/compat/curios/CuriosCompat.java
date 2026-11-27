package cn.mcmod.tsuki.compat.curios;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    public static ItemStack findFirstEquippedStack(Player player, Item item) {
        if (!ModList.get().isLoaded("curios")) {
            return ItemStack.EMPTY;
        }
        return CuriosCompatImpl.findFirstEquippedStack(player, item);
    }

    public record RemovedCurioStack(String slotId, int slotIndex, ItemStack stack) {
    }

    public static List<ItemStack> removeStacks(Player player, Predicate<ItemStack> predicate) {
        if (!ModList.get().isLoaded("curios")) {
            return List.of();
        }
        return CuriosCompatImpl.removeStacks(player, predicate);
    }

    public static List<RemovedCurioStack> removeStacksWithSlots(Player player, Predicate<ItemStack> predicate) {
        if (!ModList.get().isLoaded("curios")) {
            return List.of();
        }
        return CuriosCompatImpl.removeStacksWithSlots(player, predicate);
    }

    public static boolean tryRestoreStack(Player player, String slotId, int slotIndex, ItemStack stack) {
        if (!ModList.get().isLoaded("curios")) {
            return false;
        }
        return CuriosCompatImpl.tryRestoreStack(player, slotId, slotIndex, stack);
    }
}
