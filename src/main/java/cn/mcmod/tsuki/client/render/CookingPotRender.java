package cn.mcmod.tsuki.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.block.machine.CookingPotBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

public class CookingPotRender implements BlockEntityRenderer<CookingPotBlockEntity> {

    private static final int FLOATING_MIN_FLUID = 250;
    private static final float FLUID_MIN_X = 3.0F / 16.0F;
    private static final float FLUID_MAX_X = 13.0F / 16.0F;
    private static final float FLUID_MIN_Z = 3.0F / 16.0F;
    private static final float FLUID_MAX_Z = 13.0F / 16.0F;
    private static final float FLUID_MIN_Y = 2.5F / 16.0F;
    private static final float FLUID_MAX_Y = 6.8F / 16.0F;
    private static final ResourceLocation FINISHED_TEXTURE = ResourceLocation.fromNamespaceAndPath("tsuki",
            "block/cooking_pot/default_finished");

    public CookingPotRender(BlockEntityRendererProvider.Context pContext) {
    }

    @Override
    public void render(CookingPotBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
            int combinedLight, int combinedOverlay) {
        BlockState state = blockEntity.getBlockState();
        if (!(state.getBlock() instanceof CookingPotBlock) || !state.getValue(CookingPotBlock.OPEN)) {
            return;
        }

        if (hasFinishedContent(blockEntity)) {
            renderFinishedSurface(poseStack, buffer, combinedLight, combinedOverlay);
            return;
        }

        float fluidSurfaceY = getFluidSurfaceY(blockEntity);
        renderInputs(blockEntity, poseStack, buffer, combinedLight, combinedOverlay, fluidSurfaceY);
        renderFluid(blockEntity, poseStack, buffer, combinedLight, fluidSurfaceY);
    }

    private boolean hasFinishedContent(CookingPotBlockEntity blockEntity) {
        return !blockEntity.getInventory().getStackInSlot(CookingPotBlockEntity.SLOT_OUTPUT).isEmpty()
                || !blockEntity.getInventory().getStackInSlot(CookingPotBlockEntity.SLOT_MEAL_DISPLAY).isEmpty();
    }

    private void renderFinishedSurface(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(FINISHED_TEXTURE);
        VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucentCull(InventoryMenu.BLOCK_ATLAS));
        var pose = poseStack.last();
        float y = FLUID_MAX_Y;

        vc.addVertex(pose.pose(), FLUID_MIN_X, y, FLUID_MIN_Z).setColor(0xFFFFFFFF).setUv(sprite.getU0(), sprite.getV0())
                .setOverlay(combinedOverlay).setLight(combinedLight).setNormal(pose, 0, 1, 0);
        vc.addVertex(pose.pose(), FLUID_MIN_X, y, FLUID_MAX_Z)
                .setColor(0xFFFFFFFF)
                .setUv(sprite.getU0(), sprite.getV(10 / 16.0F))
                .setOverlay(combinedOverlay)
                .setLight(combinedLight)
                .setNormal(pose, 0, 1, 0);
        vc.addVertex(pose.pose(), FLUID_MAX_X, y, FLUID_MAX_Z)
                .setColor(0xFFFFFFFF)
                .setUv(sprite.getU(10 / 16.0F), sprite.getV(10 / 16.0F))
                .setOverlay(combinedOverlay)
                .setLight(combinedLight)
                .setNormal(pose, 0, 1, 0);
        vc.addVertex(pose.pose(), FLUID_MAX_X, y, FLUID_MIN_Z)
                .setColor(0xFFFFFFFF)
                .setUv(sprite.getU(10 / 16.0F), sprite.getV0())
                .setOverlay(combinedOverlay)
                .setLight(combinedLight)
                .setNormal(pose, 0, 1, 0);
    }

    private void renderFluid(CookingPotBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource buffer, int combinedLight,
            float y) {
        FluidStack fluid = blockEntity.getFluidTank().getFluid();
        if (fluid.isEmpty()) {
            return;
        }

        IClientFluidTypeExtensions fluidClient = IClientFluidTypeExtensions.of(fluid.getFluid());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(fluidClient.getStillTexture(fluid));
        int tint = fluidClient.getTintColor(fluid);
        VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucentCull(InventoryMenu.BLOCK_ATLAS));
        var pose = poseStack.last();
        vc.addVertex(pose.pose(), FLUID_MIN_X, y, FLUID_MIN_Z).setColor(tint).setUv(sprite.getU0(), sprite.getV0())
                .setOverlay(OverlayTexture.NO_OVERLAY).setLight(combinedLight).setNormal(pose, 0, 1, 0);
        vc.addVertex(pose.pose(), FLUID_MIN_X, y, FLUID_MAX_Z).setColor(tint).setUv(sprite.getU0(), sprite.getV(10 / 16.0F))
                .setOverlay(OverlayTexture.NO_OVERLAY).setLight(combinedLight).setNormal(pose, 0, 1, 0);
        vc.addVertex(pose.pose(), FLUID_MAX_X, y, FLUID_MAX_Z)
                .setColor(tint)
                .setUv(sprite.getU(10 / 16.0F), sprite.getV(10 / 16.0F))
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(combinedLight)
                .setNormal(pose, 0, 1, 0);
        vc.addVertex(pose.pose(), FLUID_MAX_X, y, FLUID_MIN_Z).setColor(tint).setUv(sprite.getU(10 / 16.0F), sprite.getV0())
                .setOverlay(OverlayTexture.NO_OVERLAY).setLight(combinedLight).setNormal(pose, 0, 1, 0);
    }

    private void renderInputs(CookingPotBlockEntity blockEntity, PoseStack poseStack, MultiBufferSource buffer,
            int combinedLight, int combinedOverlay, float fluidSurfaceY) {
        int posLong = (int) blockEntity.getBlockPos().asLong();
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        long now = System.currentTimeMillis();
        boolean shouldFloat = blockEntity.getFluidTank().getFluidAmount() >= FLOATING_MIN_FLUID;

        for (int slot = 0; slot < CookingPotBlockEntity.SLOT_INPUT_COUNT; slot++) {
            ItemStack stack = blockEntity.getInventory().getStackInSlot(CookingPotBlockEntity.SLOT_INPUT_START + slot);
            if (stack.isEmpty()) {
                continue;
            }

            int random = stack.hashCode();
            long time = now + random;
            float offsetX = (random % 100) * 0.002F;
            float offsetZ = (random % 50) * 0.004F;
            float offsetY = (float) Math.sin(time * 0.0005D) * 0.2F;
            float yRot = ((random % 2 == 0) ? -1 : 1) * 20.0F + (random % 10);

            poseStack.pushPose();
            if (shouldFloat) {
                poseStack.translate(0.0F, fluidSurfaceY - 0.1F , 0.0F);
                poseStack.scale(0.5F, 0.5F, 0.5F);
                poseStack.translate(0.8F + offsetX, offsetY, 0.8F + offsetZ);
                poseStack.mulPose(Axis.XP.rotationDegrees(85.0F + (random % 10)));
                poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
                poseStack.mulPose(Axis.ZP.rotationDegrees(random % 360));
            } else {
                float stackBaseY = FLUID_MIN_Y + 0.01F;
                float stackStepY = 0.025F;
                poseStack.translate(0.0F, stackBaseY + slot * stackStepY, 0.0F);
                poseStack.scale(0.5F, 0.5F, 0.5F);
                poseStack.translate(0.8F + offsetX, 0F, 0.8F + offsetZ);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            }

            renderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer,
                    blockEntity.getLevel(), posLong + slot);
            poseStack.popPose();
        }
    }

    private float getFluidSurfaceY(CookingPotBlockEntity blockEntity) {
        FluidStack fluid = blockEntity.getFluidTank().getFluid();
        if (fluid.isEmpty()) {
            return FLUID_MIN_Y;
        }
        float ratio = Math.max(0.05F, Math.min(1.0F, (float) fluid.getAmount() / CookingPotBlockEntity.TANK_CAPACITY));
        return FLUID_MIN_Y + (FLUID_MAX_Y - FLUID_MIN_Y) * ratio;
    }
}
