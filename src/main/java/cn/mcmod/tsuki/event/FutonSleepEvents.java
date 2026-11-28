package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class FutonSleepEvents {
    private static final Map<ResourceKey<Level>, Float> ACCELERATED_LEVELS = new ConcurrentHashMap<>();
    private static final Set<ServerPlayer> FUTON_SLEEPERS = ConcurrentHashMap.newKeySet();

    private FutonSleepEvents() {
    }

    @SubscribeEvent
    public static void onCanPlayerSleep(CanPlayerSleepEvent event) {
        if (!event.getState().is(BlockRegistry.FUTON.get())) {
            return;
        }

        BedSleepingProblem problem = event.getVanillaProblem();
        if (problem == BedSleepingProblem.NOT_POSSIBLE_NOW) {
            event.setProblem(null);
        } else if (problem != null) {
            return;
        }

        if (event.getEntity() instanceof ServerPlayer player) {
            FUTON_SLEEPERS.add(player);
        }
    }

    @SubscribeEvent
    public static void onCanContinueSleeping(CanContinueSleepingEvent event) {
        if (!(event.getEntity() instanceof Player entity)) {
            return;
        }
        if (entity.getSleepingPos()
                .filter(pos -> entity.level().getBlockState(pos).is(BlockRegistry.FUTON.get()))
                .isEmpty()) {
            return;
        }

        if (event.getProblem() == BedSleepingProblem.NOT_POSSIBLE_NOW) {
            event.setContinueSleeping(true);
        } else if (event.getProblem() != null) {
            return;
        }

        if (entity instanceof ServerPlayer player) {
            FUTON_SLEEPERS.add(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            FUTON_SLEEPERS.remove(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            FUTON_SLEEPERS.remove(player);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        if (level.isClientSide() || !(level instanceof ServerLevel serverLevel)) {
            return;
        }

        ResourceKey<Level> dimension = serverLevel.dimension();
        float futonDaytimePerTick = TsukiCommonConfig.FUTON_DAYTIME_PER_TICK.get().floatValue();
        boolean accelerateOnlyDay = TsukiCommonConfig.FUTON_ACCELERATE_ONLY_DAY.get();
        boolean shouldAccelerate = (!accelerateOnlyDay || serverLevel.isDay()) && hasSleepingFutonPlayer(serverLevel);
        float currentDayTimePerTick = serverLevel.getDayTimePerTick();

        if (shouldAccelerate) {
            ACCELERATED_LEVELS.putIfAbsent(dimension, currentDayTimePerTick);
            if (Float.compare(currentDayTimePerTick, futonDaytimePerTick) != 0) {
                serverLevel.setDayTimePerTick(futonDaytimePerTick);
            }
            return;
        }

        Float previousRate = ACCELERATED_LEVELS.remove(dimension);
        if (previousRate != null) {
            serverLevel.setDayTimePerTick(previousRate);
        }
    }

    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            FUTON_SLEEPERS.removeIf(player -> player.level() == serverLevel);
            Float previousRate = ACCELERATED_LEVELS.remove(serverLevel.dimension());
            if (previousRate != null) {
                serverLevel.setDayTimePerTick(previousRate);
            }
        }
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        ACCELERATED_LEVELS.clear();
        FUTON_SLEEPERS.clear();
    }

    private static boolean hasSleepingFutonPlayer(ServerLevel serverLevel) {
        boolean found = false;
        Iterator<ServerPlayer> iterator = FUTON_SLEEPERS.iterator();
        while (iterator.hasNext()) {
            ServerPlayer player = iterator.next();
            if (player.isRemoved() || !isSleepingInFuton(player)) {
                iterator.remove();
                continue;
            }
            if (player.level() == serverLevel) {
                found = true;
            }
        }
        return found;
    }

    private static boolean isSleepingInFuton(Player player) {
        if (!player.isSleeping()) {
            return false;
        }

        return player.getSleepingPos()
                .map(pos -> player.level().getBlockState(pos).is(BlockRegistry.FUTON.get()))
                .orElse(false);
    }
}
