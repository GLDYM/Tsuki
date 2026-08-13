package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.container.LighthouseIlluminationContainer;
import cn.mcmod.tsuki.network.payload.ConfigureLighthousePayload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class LighthouseIlluminationScreen extends AbstractContainerScreen<LighthouseIlluminationContainer> {
    private static final int PALETTE_SIZE = 110;
    private static final ResourceLocation PALETTE_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "tsuki", "textures/gui/lighthouse_color_palette.png");
    private EditBox colorField, transparencyField;

    public LighthouseIlluminationScreen(LighthouseIlluminationContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 360;
        imageHeight = 230;
    }

    @Override
    protected void init() {
        super.init();
        int x = leftPos, y = topPos;

        colorField = new EditBox(font, x + 98, y + 43, 70, 22, Component.empty());
        colorField.setMaxLength(6);
        colorField.setValue(String.format("%06X", menu.blockEntity.getColor()));
        addRenderableWidget(colorField);

        transparencyField = new EditBox(font, x + 98, y + 158, 70, 22, Component.empty());
        transparencyField.setFilter(value -> value.matches("[0-9]*"));
        transparencyField.setValue(Integer.toString(menu.blockEntity.getTransparency()));
        addRenderableWidget(transparencyField);

        addRenderableWidget(Button.builder(Component.literal("-"), button -> changeLength(-1)).bounds(x + 98, y + 84, 30, 22).build());
        addRenderableWidget(Button.builder(Component.literal("+"), button -> changeLength(1)).bounds(x + 134, y + 84, 30, 22).build());
        addRenderableWidget(Button.builder(Component.literal("-"), button -> changeWidth(-1)).bounds(x + 98, y + 119, 30, 22).build());
        addRenderableWidget(Button.builder(Component.literal("+"), button -> changeWidth(1)).bounds(x + 134, y + 119, 30, 22).build());
        addRenderableWidget(Button.builder(Component.translatable("gui.tsuki.lighthouse.apply"), button -> apply()).bounds(x + 218, y + 190, 122, 24).build());
    }
    private void changeLength(int amount) { send(menu.blockEntity.getColor(), Math.clamp(menu.blockEntity.getLength() + amount, 5, 30), menu.blockEntity.getWidth(), menu.blockEntity.getTransparency()); }
    private void changeWidth(int amount) { send(menu.blockEntity.getColor(), menu.blockEntity.getLength(), Math.clamp(menu.blockEntity.getWidth() + amount, 1, 10), menu.blockEntity.getTransparency()); }
    private void apply() { try { int color = Integer.parseInt(colorField.getValue(), 16); int alpha = Integer.parseInt(transparencyField.getValue()); send(color, menu.blockEntity.getLength(), menu.blockEntity.getWidth(), alpha); } catch (NumberFormatException ignored) {} }
    private void send(int color, int length, int width, int alpha) { PacketDistributor.sendToServer(new ConfigureLighthousePayload(menu.blockEntity.getBlockPos(), color, length, width, alpha)); }
    @Override public void render(GuiGraphics graphics, int mouseX, int mouseY, float partial) { renderBackground(graphics, mouseX, mouseY, partial); super.render(graphics, mouseX, mouseY, partial); renderTooltip(graphics, mouseX, mouseY); }
    @Override
    protected void renderBg(GuiGraphics graphics, float partial, int mouseX, int mouseY) {
        int x = leftPos, y = topPos;
        graphics.fill(x, y, x + imageWidth, y + imageHeight, 0xFF1C2028);
        graphics.fill(x + 2, y + 2, x + imageWidth - 2, y + imageHeight - 2, 0xFF353B46);
        graphics.fill(x + 12, y + 30, x + 204, y + 184, 0xFF20252E);
        graphics.fill(x + 214, y + 30, x + 348, y + 184, 0xFF20252E);

        graphics.blit(PALETTE_TEXTURE, x + 226, y + 42, 0, 0, PALETTE_SIZE, PALETTE_SIZE, PALETTE_SIZE, PALETTE_SIZE);
        graphics.fill(x + 22, y + 43, x + 86, y + 65, 0xFF000000 | menu.blockEntity.getColor());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int paletteX = leftPos + 226, paletteY = topPos + 42;
        if (button == 0 && mouseX >= paletteX && mouseX < paletteX + PALETTE_SIZE
                && mouseY >= paletteY && mouseY < paletteY + PALETTE_SIZE) {
            int color = java.awt.Color.HSBtoRGB((float) (mouseX - paletteX) / PALETTE_SIZE, .78F,
                    1F - (float) (mouseY - paletteY) / (PALETTE_SIZE + 20)) & 0xFFFFFF;
            colorField.setValue(String.format("%06X", color));
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(font, title, 14, 10, 0xFFF0F0F0, false);
        graphics.drawString(font, Component.translatable("gui.tsuki.lighthouse.color"), 22, 34, 0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable("gui.tsuki.lighthouse.length", menu.blockEntity.getLength()), 22, 72, 0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable("gui.tsuki.lighthouse.width", menu.blockEntity.getWidth()), 22, 107, 0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable("gui.tsuki.lighthouse.transparency"), 22, 146, 0xFFE0E0E0, false);
    }
}
