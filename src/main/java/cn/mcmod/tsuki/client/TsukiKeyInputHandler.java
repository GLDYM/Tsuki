package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.KatanaItem;
import cn.mcmod.tsuki.item.SheathItem;
import cn.mcmod.tsuki.item.SheathKatanaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class TsukiKeyInputHandler {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.gameMode == null) {
            return;
        }

        while (TsukiKeyMappings.SHEATH_ACTION.consumeClick()) {
            InteractionHand hand = resolveActionHand(minecraft.player.getMainHandItem(), minecraft.player.getOffhandItem());
            if (hand == null) {
                continue;
            }
            minecraft.gameMode.useItem(minecraft.player, hand);
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

