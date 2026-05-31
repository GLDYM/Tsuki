package cn.mcmod.tsuki.mixin;

import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MagatamaWhiteElytraUpdateMixin {
    @Inject(method = "updateFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"), cancellable = true)
    private void tsuki$updateFallFlying(CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayer player && MagatamaWhiteHelper.isElytraModeActive(player)) {
            ci.cancel();
        }
    }
}
