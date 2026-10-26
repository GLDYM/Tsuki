package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.item.magatama.MagatamaPurpleHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MagatamaPurplePacifyMixin {
    @Inject(method = "canBeSeenAsEnemy", at = @At("HEAD"), cancellable = true)
    private void tsuki$purplePacify(CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof Player player)) {
            return;
        }
        if (MagatamaPurpleHelper.hasActivePurpleMagatama(player)
                && !MagatamaPurpleHelper.isInAttackCooldown(player)) {
            cir.setReturnValue(false);
        }
    }
}
