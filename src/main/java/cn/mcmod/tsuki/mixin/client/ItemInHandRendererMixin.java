package cn.mcmod.tsuki.mixin.client;

import cn.mcmod.tsuki.item.drink.ShakerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/client/ClientHooks;shouldCauseReequipAnimation(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;I)Z"))
    private boolean tsuki$skipShakerReequip(ItemStack from, ItemStack to, int slotChanged) {
        LocalPlayer player = this.minecraft.player;
        if (player != null && player.isUsingItem()) {
            InteractionHand usedHand = player.getUsedItemHand();
            ItemStack handStack = usedHand == InteractionHand.MAIN_HAND ? player.getMainHandItem()
                    : player.getOffhandItem();

            if (handStack.getItem() instanceof ShakerItem
                    && from.getItem() instanceof ShakerItem
                    && to.getItem() instanceof ShakerItem) {
                return false;
            }
        }

        return net.neoforged.neoforge.client.ClientHooks.shouldCauseReequipAnimation(from, to, slotChanged);
    }
}
