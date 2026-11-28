package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiClientConfig;
import cn.mcmod.tsuki.network.payload.CookingPotStackingPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Sends the cooking-pot stacking preference after connecting and whenever the
 * player changes it. The server cannot load CLIENT configs, so this keeps the
 * authoritative inventory code in sync with the player's client setting.
 */
@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public final class CookingPotConfigSync {
    private static Boolean lastSentValue;

    private CookingPotConfigSync() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.getConnection() == null || minecraft.player == null) {
            lastSentValue = null;
            return;
        }

        boolean allowStacking = TsukiClientConfig.ALLOW_COOKING_POT_INPUT_STACKING.get();
        if (lastSentValue == null || lastSentValue != allowStacking) {
            PacketDistributor.sendToServer(new CookingPotStackingPayload(allowStacking));
            lastSentValue = allowStacking;
        }
    }
}
