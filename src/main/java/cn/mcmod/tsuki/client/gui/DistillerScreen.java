package cn.mcmod.tsuki.client.gui;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.container.DistillerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

public class DistillerScreen extends AbstractContainerScreen<DistillerContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "textures/gui/distiller.png");

    public DistillerScreen(DistillerContainer screenContainer, Inventory inv, Component titleIn) {
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
        this.renderTooltip(ms, mouseX, mouseY);
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
        ms.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        // Render progress arrow
        int l = this.menu.getCookProgressionScaled();
        ms.blit(BACKGROUND_TEXTURE, this.leftPos + 75, this.topPos + 35, 176, 0, l + 1, 17);
        
        int m = this.menu.getWorking();
        ms.blit(BACKGROUND_TEXTURE, this.leftPos + 78, this.topPos + 34 - m, 176, 53 - m, 18, m);
        
        if (this.menu.isHeated()) {
            ms.blit(BACKGROUND_TEXTURE, this.leftPos + 78, this.topPos + 52, 176, 17, 18, 18);
        }
        
        var inputFluidTank = this.menu.tileEntity.getInputFluidTank();
        int inputHeight = (int) (52.0F * ((float) inputFluidTank.getFluidAmount() / (float) inputFluidTank.getCapacity()));
        if (inputHeight > 0) {
            int tankX = this.leftPos + 33;
            int tankY = this.topPos + 17 + (52 - inputHeight);
            renderFluid(ms, inputFluidTank.getFluid(), tankX, tankY, 16, inputHeight);
        }

        var outputFluidTank = this.menu.tileEntity.getOutputFluidTank();
        int outputHeight = (int) (52.0F * ((float) outputFluidTank.getFluidAmount() / (float) outputFluidTank.getCapacity()));
        if (outputHeight > 0) {
            int tankX = this.leftPos + 125;
            int tankY = this.topPos + 17 + (52 - outputHeight);
            renderFluid(ms, outputFluidTank.getFluid(), tankX, tankY, 16, outputHeight);
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
}
