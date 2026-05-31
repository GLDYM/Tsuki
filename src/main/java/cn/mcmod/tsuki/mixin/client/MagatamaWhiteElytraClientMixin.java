package cn.mcmod.tsuki.mixin.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class MagatamaWhiteElytraClientMixin {
    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"), cancellable = true)
    private void tsuki$tryToStartFallFlying(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        boolean canStartFlying = !player.onGround() && !player.isFallFlying() && !player.isInWater()
                && !player.hasEffect(MobEffects.LEVITATION);
        if (!canStartFlying || !MagatamaWhiteHelper.isElytraModeActive(player)) {
            return;
        }

        if (TsukiCommonConfig.DEBUG_MODE.get()) {
            Tsuki.getLogger().info("[MagatamaWhite] Client requested startFallFlying");
        }
        player.connection.send(new ServerboundPlayerCommandPacket(player,
                ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
        ci.cancel();
    }
}
