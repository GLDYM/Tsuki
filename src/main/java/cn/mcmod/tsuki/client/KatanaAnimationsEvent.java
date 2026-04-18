package cn.mcmod.tsuki.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.KatanaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public class KatanaAnimationsEvent {
    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.isUsingItem() && player.getUseItem().getItem() instanceof KatanaItem) {
            PoseStack poseStack = event.getPoseStack();
            // Rotate
            poseStack.mulPose(Axis.ZP.rotationDegrees(60.0F));
            // Move
            poseStack.translate(-1.0F, 0F, 0F);
        }
    }
}
