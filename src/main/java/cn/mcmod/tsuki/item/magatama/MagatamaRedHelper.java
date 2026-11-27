package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.AABB;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class MagatamaRedHelper {
    private static final int HOTBAR_SIZE = 9;

    private static final double RANGE_XZ = 32.0D;
    private static final double RANGE_UP = 48.0D;
    private static final double RANGE_DOWN = 16.0D;

    private static volatile List<? extends String> cachedConfigIds;
    private static volatile Set<EntityType<?>> cachedTargetTypes = Set.of();

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
        Set<EntityType<?>> targetTypes = getTargetEntityTypeSet();
        if (targetTypes.isEmpty()) {
            return;
        }

        AABB checkBox = new AABB(
                player.getX() - RANGE_XZ,
                player.getY() - RANGE_DOWN,
                player.getZ() - RANGE_XZ,
                player.getX() + RANGE_XZ,
                player.getY() + RANGE_UP,
                player.getZ() + RANGE_XZ);

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, checkBox,
                entity -> entity.isAlive() && targetTypes.contains(entity.getType()))) {
            entity.hurt(player.damageSources().outOfBorder(), Float.MAX_VALUE);
        }
    }

    public static List<EntityType<?>> getConfiguredTargetEntityTypes() {
        return List.copyOf(getTargetEntityTypeSet());
    }

    private static Set<EntityType<?>> getTargetEntityTypeSet() {
        List<? extends String> configuredIds = TsukiCommonConfig.MAGATAMA_RED_TARGET_ENTITIES.get();
        if (configuredIds == cachedConfigIds) {
            return cachedTargetTypes;
        }

        Set<EntityType<?>> entityTypes = new LinkedHashSet<>();
        for (String configuredId : configuredIds) {
            ResourceLocation location = ResourceLocation.tryParse(configuredId);
            if (location == null || !BuiltInRegistries.ENTITY_TYPE.containsKey(location)) {
                continue;
            }
            EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(location);
            if (entityType != null) {
                entityTypes.add(entityType);
            }
        }
        Set<EntityType<?>> resolved = Set.copyOf(entityTypes);
        cachedTargetTypes = resolved;
        cachedConfigIds = configuredIds;
        return resolved;
    }
}
