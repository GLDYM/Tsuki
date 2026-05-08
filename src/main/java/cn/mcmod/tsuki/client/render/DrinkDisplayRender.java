package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.DrinkItem;
import cn.mcmod.tsuki.item.drink.WineBottleItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class DrinkDisplayRender implements BlockEntityRenderer<DrinkDisplayBlockEntity> {
    public DrinkDisplayRender(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(DrinkDisplayBlockEntity blockEntity, float partialTicks, PoseStack poseStack,
            MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        int slotCount = DrinkDisplayBlockEntity.SLOT_COUNT;
        for (int slot = 0; slot < slotCount; slot++) {
            ItemStack stack = blockEntity.getStackInSlot(slot);
            if (stack.isEmpty()) {
                continue;
            }
            poseStack.pushPose();
            translateSlot(poseStack, slot);
            poseStack.scale(0.9F, 0.9F, 0.9F);
            if (stack.is(DrinkRegistry.WINE_BOTTLE.get()) || stack.getItem() instanceof WineBottleItem) {
                poseStack.translate(0.0D, 0.32D, 0.0D);
            } else if (stack.is(DrinkRegistry.CUP.get()) || (stack.getItem() instanceof DrinkItem drink && drink.getContainerItem().get() == DrinkRegistry.CUP.get())) {
                poseStack.translate(0.0D, 0.32D, 0.0D);
            }
            poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getRotation(slot)));
            int seed = (int) blockEntity.getBlockPos().asLong() + slot;
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack,
                    buffer, blockEntity.getLevel(), seed);
            poseStack.popPose();
        }
    }

    private void translateSlot(PoseStack poseStack, int slot) {
        float x = (slot % 2 == 0) ? 0.28F : 0.72F;
        float z = slot < 2 ? 0.28F : 0.72F;
        poseStack.translate(x, 0.18F, z);
    }
}
