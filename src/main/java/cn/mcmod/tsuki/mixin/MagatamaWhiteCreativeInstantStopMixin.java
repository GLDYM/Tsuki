package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MagatamaWhiteCreativeInstantStopMixin {
    @Inject(method = "travel", at = @At("HEAD"))
    private void tsuki$instantStopCreativeFlight(Vec3 travelVector, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if (!player.getAbilities().flying || !MagatamaWhiteHelper.isCreativeInstantStopModeActive(player)) {
            return;
        }
        if (travelVector.x != 0.0D || travelVector.z != 0.0D) {
            return;
        }
        Vec3 delta = player.getDeltaMovement();
        player.setDeltaMovement(delta.x * 0.5D, delta.y, delta.z * 0.5D);
    }
}

