package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import cn.mcmod.tsuki.item.drink.MytheryMixItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DrinkDisplayRender implements BlockEntityRenderer<DrinkDisplayBlockEntity> {
    private static final float DISPLAY_SCALE = 0.6F;
    private static final float MYTHERY_MIX_SCALE = 0.6F;
    private static final float ITEM_RENDERER_CENTER_OFFSET = 0.3125F;
    private static final ModelResourceLocation EMPTY_GLASS_BOTTLE_MODEL = ModelResourceLocation.inventory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "empty_glass_bottle"));

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
            BakedModel renderModel = getRenderModel(itemRenderer, stack);
            poseStack.pushPose();
            translateSlot(poseStack, slot);
            float itemScale = getDisplayScale(stack);
            poseStack.scale(itemScale, itemScale, itemScale);
            float groundedOffset = ITEM_RENDERER_CENTER_OFFSET - getModelMinY(renderModel) / itemScale;
            poseStack.translate(0.0D, groundedOffset, 0.0D);
            poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getRotation(slot)));
            itemRenderer.render(stack, ItemDisplayContext.FIXED, false, poseStack, buffer, combinedLight,
                    combinedOverlay, renderModel);
            poseStack.popPose();
        }
    }

    private void translateSlot(PoseStack poseStack, int slot) {
        float x = (slot % 2 == 0) ? 0.28F : 0.72F;
        float z = slot < 2 ? 0.28F : 0.72F;
        poseStack.translate(x, 0.18F, z);
    }

    private float getDisplayScale(ItemStack stack) {
        if (stack.getItem() instanceof MytheryMixItem) {
            return MYTHERY_MIX_SCALE;
        }
        return DISPLAY_SCALE;
    }

    // TODO: 放置玻璃瓶的逻辑还没实现，而且玻璃瓶容易冲突，大概需要个新的
    private BakedModel getRenderModel(ItemRenderer itemRenderer, ItemStack stack) {
        if (stack.is(Items.GLASS_BOTTLE)) {
            return Minecraft.getInstance().getModelManager().getModel(EMPTY_GLASS_BOTTLE_MODEL);
        }
        return itemRenderer.getModel(stack, null, null, 0);
    }

    private float getModelMinY(BakedModel resolvedModel) {
        if (resolvedModel.isCustomRenderer()) {
            return 0.0F;
        }
        PoseStack transformStack = new PoseStack();
        resolvedModel.applyTransform(ItemDisplayContext.FIXED, transformStack, false);
        float fixedScaleY = transformStack.last().pose().m11();
        return 0.5F - 0.5F * fixedScaleY;
    }
}
