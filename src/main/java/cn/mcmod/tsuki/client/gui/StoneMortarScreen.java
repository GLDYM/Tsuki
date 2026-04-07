package cn.mcmod.tsuki.client.gui;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.container.StoneMortarContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StoneMortarScreen extends AbstractContainerScreen<StoneMortarContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Tsuki.MODID,
            "textures/gui/stonemortar.png");

    public StoneMortarScreen(StoneMortarContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(GuiGraphics ms, final int mouseX, final int mouseY, float partialTicks) {
        this.renderBackground(ms);
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
        int n = this.menu.getRolling();
        ms.blit(BACKGROUND_TEXTURE, this.leftPos + 81, this.topPos + 33, 176, n * 16, 14, 16);
        // Render progress arrow
        int l = this.menu.getProgressionRoll();
        ms.blit(BACKGROUND_TEXTURE, this.leftPos + 80, this.topPos + 49, 190, l * 6, 16, 6);

    }

}
