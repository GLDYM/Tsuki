package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.magatama.MagatamaRedHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Set;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaRedEvent {
    private static final int CHECK_INTERVAL_TICKS = 40;
    private static final double RANGE_XZ = 24.0D;
    private static final double RANGE_UP = 48.0D;
    private static final double RANGE_DOWN = 24.0D;
    private static final Set<ResourceLocation> TARGET_ENTITY_IDS = Set.of(
            ResourceLocation.fromNamespaceAndPath("alexsmobs", "seagull"),
            ResourceLocation.fromNamespaceAndPath("naturalist", "vulture"),
            ResourceLocation.fromNamespaceAndPath("iceandfire", "if_pixie"));

    private MagatamaRedEvent() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (player.tickCount % CHECK_INTERVAL_TICKS != 0 || !MagatamaRedHelper.hasActiveRedMagatama(player)) {
            return;
        }

        AABB checkBox = new AABB(
                player.getX() - RANGE_XZ,
                player.getY() - RANGE_DOWN,
                player.getZ() - RANGE_XZ,
                player.getX() + RANGE_XZ,
                player.getY() + RANGE_UP,
                player.getZ() + RANGE_XZ);

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, checkBox, MagatamaRedEvent::isTargetEntity)) {
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

