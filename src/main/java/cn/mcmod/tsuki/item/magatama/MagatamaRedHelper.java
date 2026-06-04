package cn.mcmod.tsuki.item.magatama;

import java.util.Set;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.AABB;

public final class MagatamaRedHelper {
    private static final int HOTBAR_SIZE = 9;

    private static final double RANGE_XZ = 32.0D;
    private static final double RANGE_UP = 48.0D;
    private static final double RANGE_DOWN = 16.0D;
    private static final Set<ResourceLocation> TARGET_ENTITY_IDS = Set.of(
            ResourceLocation.fromNamespaceAndPath("alexsmobs", "seagull"),
            ResourceLocation.fromNamespaceAndPath("naturalist", "vulture"),
            ResourceLocation.fromNamespaceAndPath("iceandfire", "if_pixie"));

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

    public static void executeCull(ServerPlayer player) {
        AABB checkBox = new AABB(
                player.getX() - RANGE_XZ,
                player.getY() - RANGE_DOWN,
                player.getZ() - RANGE_XZ,
                player.getX() + RANGE_XZ,
                player.getY() + RANGE_UP,
                player.getZ() + RANGE_XZ);

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, checkBox, MagatamaRedHelper::isTargetEntity)) {
            entity.hurt(player.damageSources().outOfBorder(), Float.MAX_VALUE);
        }
    }

    private static boolean isTargetEntity(LivingEntity entity) {
        if (!entity.isAlive()) {
            return false;
        }
        if (entity.getType() == EntityType.PHANTOM) {
            return true;
        }
        return TARGET_ENTITY_IDS.contains(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()));
    }
}

