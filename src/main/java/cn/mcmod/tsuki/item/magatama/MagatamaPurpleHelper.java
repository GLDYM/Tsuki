package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

public final class MagatamaPurpleHelper {
    private static final int HOTBAR_SIZE = 9;
    private static final String ATTACK_COOLDOWN_UNTIL_TAG = "TsukiMagatamaPurpleCooldownUntil";
    private static final int ATTACK_COOLDOWN_TICKS = 600;
    private static final double HATE_CLEAR_RANGE = 32.0D;

    private MagatamaPurpleHelper() {
    }

    public static boolean hasActivePurpleMagatama(Player player) {
        return !findActiveStack(player).isEmpty();
    }

    public static ItemStack findActiveStack(Player player) {
        for (int slot = 0; slot < HOTBAR_SIZE; slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (stack.is(ArmorToolRegistry.MAGATAMA_PURPLE.get())) {
                return stack;
            }
        }
        ItemStack offhand = player.getOffhandItem();
        if (offhand.is(ArmorToolRegistry.MAGATAMA_PURPLE.get())) {
            return offhand;
        }
        return CuriosCompat.findFirstEquippedStack(player, ArmorToolRegistry.MAGATAMA_PURPLE.get());
    }

    public static void triggerAttackCooldown(Player player) {
        long now = player.level().getGameTime();
        player.getPersistentData().putLong(ATTACK_COOLDOWN_UNTIL_TAG, now + ATTACK_COOLDOWN_TICKS);
    }

    public static boolean isInAttackCooldown(Player player) {
        long until = player.getPersistentData().getLong(ATTACK_COOLDOWN_UNTIL_TAG);
        return player.level().getGameTime() < until;
    }

    public static void clearNearbyMobTargets(Player player) {
        AABB aabb = new AABB(
                player.getX() - HATE_CLEAR_RANGE,
                player.getY() - HATE_CLEAR_RANGE,
                player.getZ() - HATE_CLEAR_RANGE,
                player.getX() + HATE_CLEAR_RANGE,
                player.getY() + HATE_CLEAR_RANGE,
                player.getZ() + HATE_CLEAR_RANGE);
        for (Mob mob : player.level().getEntitiesOfClass(Mob.class, aabb)) {
            if (mob.getTarget() == player) {
                mob.setTarget(null);
            }
        }
    }
}
