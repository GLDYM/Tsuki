package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Per-player cache of equipped magatama stacks. Items in the hotbar/off-hand
 * mark themselves from {@link Item#inventoryTick}; Curios (which do not tick
 * through the vanilla inventory) are scanned once per player tick. Helpers only
 * read these markers instead of rescanning the hotbar and Curios every call.
 */
public final class MagatamaEquipmentCache {
    private static final int OFFHAND_SLOT = 40;
    private static final Map<Player, State> STATES = Collections.synchronizedMap(new WeakHashMap<>());

    private MagatamaEquipmentCache() {
    }

    public static void mark(Player player, ItemStack stack, int slotId) {
        if (stack.isEmpty() || (slotId >= 9 && slotId != OFFHAND_SLOT)) {
            return;
        }
        stateForTick(player).inventory.putIfAbsent(stack.getItem(), stack);
    }

    public static boolean isActive(Player player, Item item) {
        return !getActiveStack(player, item).isEmpty();
    }

    public static ItemStack getActiveStack(Player player, Item item) {
        if (player == null) {
            return ItemStack.EMPTY;
        }
        State state = stateForTick(player);
        ItemStack stack = state.inventory.get(item);
        if (stack != null && !stack.isEmpty()) {
            return stack;
        }
        return state.curios.getOrDefault(item, ItemStack.EMPTY);
    }

    private static State stateForTick(Player player) {
        int tick = player.tickCount;
        synchronized (STATES) {
            State state = STATES.get(player);
            if (state != null && state.tick == tick) {
                return state;
            }
        }

        State refreshed = new State(tick);
        ItemStack offhand = player.getOffhandItem();
        if (!offhand.isEmpty()
                && (offhand.is(TsukiItemTags.MAGATAMAS)
                        || offhand.is(ArmorToolRegistry.YATA_NO_KAGAMI.get()))) {
            refreshed.inventory.put(offhand.getItem(), offhand);
        }
        CuriosCompat.findEquippedStacks(player,
                stack -> stack.is(TsukiItemTags.MAGATAMAS) || stack.is(ArmorToolRegistry.YATA_NO_KAGAMI.get()))
                .forEach(refreshed.curios::putIfAbsent);
        synchronized (STATES) {
            STATES.put(player, refreshed);
        }
        return refreshed;
    }

    private static final class State {
        private final int tick;
        private final Map<Item, ItemStack> inventory = new LinkedHashMap<>();
        private final Map<Item, ItemStack> curios = new LinkedHashMap<>();

        private State(int tick) {
            this.tick = tick;
        }
    }
}
