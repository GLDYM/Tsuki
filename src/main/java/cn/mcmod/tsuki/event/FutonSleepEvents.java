package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class FutonSleepEvents {
    private FutonSleepEvents() {
    }

    @SubscribeEvent
    public static void onCanPlayerSleep(CanPlayerSleepEvent event) {
        if (event.getVanillaProblem() == BedSleepingProblem.NOT_POSSIBLE_NOW
                && event.getState().is(BlockRegistry.FUTON.get())) {
            event.setProblem(null);
        }
    }

    @SubscribeEvent
    public static void onCanContinueSleeping(CanContinueSleepingEvent event) {
        if (event.getProblem() != BedSleepingProblem.NOT_POSSIBLE_NOW) {
            return;
        }

        event.getEntity().getSleepingPos()
                .filter(pos -> event.getEntity().level().getBlockState(pos).is(BlockRegistry.FUTON.get()))
                .ifPresent(pos -> event.setContinueSleeping(true));
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Pre event) {
        Level level = event.getLevel();
        if (level.isClientSide() || !(level instanceof ServerLevel serverLevel)) {
            return;
        }

        float futonDaytimePerTick = TsukiCommonConfig.FUTON_DAYTIME_PER_TICK.get().floatValue();
        boolean accelerateOnlyDay = TsukiCommonConfig.FUTON_ACCELERATE_ONLY_DAY.get();
        boolean shouldAccelerate = (!accelerateOnlyDay || serverLevel.isDay()) && hasSleepingFutonPlayer(serverLevel);
        float currentDayTimePerTick = serverLevel.getDayTimePerTick();

        if (shouldAccelerate) {
            if (currentDayTimePerTick != futonDaytimePerTick) {
                serverLevel.setDayTimePerTick(futonDaytimePerTick);
            }
            return;
        }

        if (currentDayTimePerTick == futonDaytimePerTick) {
            serverLevel.setDayTimePerTick(-1.0F);
        }
    }

    private static boolean hasSleepingFutonPlayer(ServerLevel serverLevel) {
        for (ServerPlayer player : serverLevel.players()) {
            if (isSleepingInFuton(player)) {
                return true;
            }
        }
        return false;
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
