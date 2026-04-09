package cn.mcmod.tsuki.client.gui;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.container.CookingPotContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class CookingPotScreen extends AbstractContainerScreen<CookingPotContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "textures/gui/pot.png");

    public CookingPotScreen(CookingPotContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(GuiGraphics ms, final int mouseX, final int mouseY, float partialTicks) {
        this.renderBackground(ms, mouseX, mouseY, partialTicks);
        super.render(ms, mouseX, mouseY, partialTicks);
        boolean renderedMealDisplayTooltip = this.renderMealDisplayTooltip(ms, mouseX, mouseY);
        if (!renderedMealDisplayTooltip) {
            this.renderTooltip(ms, mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics ms, int mouseX, int mouseY) {
        super.renderLabels(ms, mouseX, mouseY);
        ms.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 4210752, false);
    }

    @Override
    protected void renderBg(GuiGraphics ms, float partialTicks, int mouseX, int mouseY) {
        // Render UI background
        if (this.minecraft == null) {
            return;
        }
//        RenderUtils.setup(BACKGROUND_TEXTURE);
        ms.blit(BACKGROUND_TEXTURE,this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.isHeated()) {
            ms.blit(BACKGROUND_TEXTURE,this.leftPos + 99, this.topPos + 16, 176, 0, 17, 15);
        }
        // Render progress arrow
        int l = this.menu.getCookProgressionScaled();
        ms.blit(BACKGROUND_TEXTURE, this.leftPos + 94, this.topPos + 28, 176, 15, l + 1, 17);

        var fluidTank = this.menu.blockEntity.getFluidTank();
        int heightInd = (int) (52.0F * ((float) fluidTank.getFluidAmount() / (float) fluidTank.getCapacity()));
        if (heightInd > 0) {
            int tankX = this.leftPos + 14;
            int tankY = this.topPos + 17 + (52 - heightInd);
            renderFluid(ms, fluidTank.getFluid(), tankX, tankY, 16, heightInd);
        }
    }

    private void renderFluid(GuiGraphics graphics, FluidStack fluidStack, int x, int y, int width, int height) {
        if (this.minecraft == null || fluidStack.isEmpty() || width <= 0 || height <= 0) {
            return;
        }

        IClientFluidTypeExtensions fluidClient = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation stillTexture = fluidClient.getStillTexture(fluidStack);
        TextureAtlasSprite sprite = this.minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTexture);
        int tint = fluidClient.getTintColor(fluidStack);

        float alpha = (float) (tint >> 24 & 0xFF) / 255.0F;
        float red = (float) (tint >> 16 & 0xFF) / 255.0F;
        float green = (float) (tint >> 8 & 0xFF) / 255.0F;
        float blue = (float) (tint & 0xFF) / 255.0F;
        graphics.setColor(red, green, blue, alpha);

        for (int xOffset = 0; xOffset < width; xOffset += 16) {
            int drawWidth = Math.min(16, width - xOffset);
            for (int yOffset = 0; yOffset < height; yOffset += 16) {
                int drawHeight = Math.min(16, height - yOffset);
                graphics.blit(x + xOffset, y + yOffset, 0, drawWidth, drawHeight, sprite);
            }
        }

        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private boolean renderMealDisplayTooltip(GuiGraphics graphics, int mouseX, int mouseY) {
        if (this.minecraft == null || this.minecraft.player == null || !this.menu.getCarried().isEmpty()) {
            return false;
        }

        Slot slot = this.hoveredSlot;
        if (slot == null || slot.index != CookingPotBlockEntity.SLOT_MEAL_DISPLAY) {
            return false;
        }

        if (slot.hasItem()) {
            ItemStack mealStack = slot.getItem();
            List<Component> tooltip = new ArrayList<>();
            MutableComponent mealName = mealStack.getHoverName().copy().withStyle(mealStack.getRarity().color());
            tooltip.add(mealName);

            ItemStack containerStack = this.menu.blockEntity.getCurrentMealContainer();
            if (!containerStack.isEmpty()) {
                tooltip.add(Component.translatable("gui.tsuki.cooking_pot.served_on", containerStack.getHoverName())
                        .withColor(0x7F7F7F));
            }

            graphics.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            return true;
        }

        // graphics.renderTooltip(this.font, Component.translatable("gui.tsuki.cooking_pot.meal_display"), mouseX, mouseY);
        return true;
    }

}
