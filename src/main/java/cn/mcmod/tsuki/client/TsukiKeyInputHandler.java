package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import cn.mcmod.tsuki.network.payload.MagatamaBoostPayload;
import cn.mcmod.tsuki.network.payload.ToggleMagatamaModePayload;
import cn.mcmod.tsuki.item.tool.KatanaItem;
import cn.mcmod.tsuki.item.tool.SheathItem;
import cn.mcmod.tsuki.item.tool.SheathKatanaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public class TsukiKeyInputHandler {
    private static long lastMagatamaBoostTick;
    private static long lastMagatamaBoostDebugTick;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.gameMode == null) {
            return;
        }

        while (TsukiKeyMappings.MAGATAMA_MODE_TOGGLE.consumeClick()) {
            PacketDistributor.sendToServer(ToggleMagatamaModePayload.INSTANCE);
        }

        while (TsukiKeyMappings.SHEATH_ACTION.consumeClick()) {
            InteractionHand hand = resolveActionHand(minecraft.player.getMainHandItem(),
                    minecraft.player.getOffhandItem());
            if (hand == null) {
                continue;
            }
            minecraft.gameMode.useItem(minecraft.player, hand);
        }

        boolean isFallFlying = minecraft.player.isFallFlying();
        boolean elytraModeActive = MagatamaWhiteHelper.isElytraModeActive(minecraft.player);
        boolean hasForwardImpulse = minecraft.player.input.hasForwardImpulse();
        long gameTime = minecraft.player.level().getGameTime();

        if (TsukiCommonConfig.DEBUG_MODE.get() && gameTime - lastMagatamaBoostDebugTick >= 20) {
            // Tsuki.getLogger().info(
            //         "[MagatamaWhite] Client boost check: fallFlying={}, elytraMode={}, forward={}, sprint={}, localModeStackPresent={}",
            //         isFallFlying, elytraModeActive, hasForwardImpulse, minecraft.player.isSprinting(),
            //         !MagatamaWhiteHelper.findActiveStack(minecraft.player).isEmpty());
            lastMagatamaBoostDebugTick = gameTime;
        }

        if (isFallFlying && elytraModeActive && hasForwardImpulse) {
            if (gameTime - lastMagatamaBoostTick >= 20) {
                if (TsukiCommonConfig.DEBUG_MODE.get()) {
                    Tsuki.getLogger().info("[MagatamaWhite] Client sending boost payload at tick {}", gameTime);
                }
                PacketDistributor.sendToServer(MagatamaBoostPayload.INSTANCE);
                lastMagatamaBoostTick = gameTime;
            }
        }
    }

    private static InteractionHand resolveActionHand(ItemStack main, ItemStack off) {
        if (main.getItem() instanceof SheathKatanaItem) {
            return InteractionHand.MAIN_HAND;
        }
        if (off.getItem() instanceof SheathKatanaItem) {
            return InteractionHand.OFF_HAND;
        }

        boolean mainKatana = main.getItem() instanceof KatanaItem;
        boolean offKatana = off.getItem() instanceof KatanaItem;
        boolean mainSheath = main.getItem() instanceof SheathItem;
        boolean offSheath = off.getItem() instanceof SheathItem;
        if ((mainKatana && offSheath) || (offKatana && mainSheath)) {
            return mainSheath ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        }
        return null;
    }
}
