package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class MagatamaWhiteElytraServerMixin {
    @Shadow
    public ServerPlayer player;

    @Inject(method = "handlePlayerCommand", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/level/ServerPlayer;tryToStartFallFlying()Z"), cancellable = true)
    private void tsuki$startFallFlying(CallbackInfo ci) {
        if (player.isFallFlying() || player.isInWater() || player.hasEffect(MobEffects.LEVITATION)) {
            return;
        }
        if (!MagatamaWhiteHelper.isElytraModeActive(player)) {
            return;
        }

        player.startFallFlying();
        if (TsukiCommonConfig.DEBUG_MODE.get()) {
            Tsuki.getLogger().info("[MagatamaWhite] Server allowed startFallFlying for {}",
                    player.getGameProfile().getName());
        }
        ci.cancel();
    }
}
