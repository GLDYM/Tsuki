package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.magatama.MagatamaRedHelper;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaRedEvent {
    private static final int CHECK_INTERVAL_TICKS = 40;

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

        MagatamaRedHelper.executeCull(player);
    }
}
