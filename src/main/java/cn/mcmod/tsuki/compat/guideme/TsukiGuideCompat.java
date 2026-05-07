package cn.mcmod.tsuki.compat.guideme;

import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class TsukiGuideCompat {
    private TsukiGuideCompat() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(TsukiGuideCompat::onRightClickGuide);
        NeoForge.EVENT_BUS.addListener(TsukiGuideCompat::onPlayerLoggedIn);
    }

    private static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }

        if (!TsukiCommonConfig.GIVE_GUIDE_ON_FIRST_LOGIN.get()) {
            return;
        }

        event.getEntity().displayClientMessage(
                Component.translatable("tsuki.chat.sakura_guide.no_guideme"),
                false);
    }

    private static void onRightClickGuide(PlayerInteractEvent.RightClickItem event) {
        if (!event.getItemStack().is(ItemRegistry.SAKURA_GUIDE.get())) {
            return;
        }
        if (event.getEntity().level().isClientSide()) {
            return;
        }

        event.getEntity().displayClientMessage(
                Component.translatable("tsuki.chat.sakura_guide.no_guideme"),
                false);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
    }
}
