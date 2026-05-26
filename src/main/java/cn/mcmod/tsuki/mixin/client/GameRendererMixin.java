package cn.mcmod.tsuki.mixin.client;

import cn.mcmod.tsuki.client.ShojiBigOutlinePicker;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "pick(F)V", at = @At("TAIL"))
    private void tsuki$pickShojiBigOutline(float partialTicks, CallbackInfo ci) {
        ShojiBigOutlinePicker.pick();
    }
}
