package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.container.CeilingLightContainer;
import cn.mcmod.tsuki.network.payload.ConfigureCeilingLightPayload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class CeilingLightScreen extends AbstractContainerScreen<CeilingLightContainer> {
    private static final int PALETTE_SIZE = 110;
    private static final ResourceLocation PALETTE = ResourceLocation.fromNamespaceAndPath("tsuki",
            "textures/gui/lighthouse_color_palette.png");
    private EditBox colorField, transparencyField;

    public CeilingLightScreen(CeilingLightContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 360;
        imageHeight = 265;
    }

    @Override
    protected void init() {
        super.init();
        int x = leftPos, y = topPos;
        colorField = new EditBox(font, x + 98, y + 43, 70, 22, Component.empty());
        colorField.setMaxLength(6);
        colorField.setFilter(value -> value.matches("[0-9A-Fa-f]*"));
        colorField.setValue(String.format("%06X", menu.blockEntity.getColor()));
        addRenderableWidget(colorField);
        transparencyField = new EditBox(font, x + 98, y + 193, 70, 22, Component.empty());
        transparencyField.setMaxLength(3);
        transparencyField.setFilter(value -> value.matches("[0-9]*"));
        transparencyField.setValue(Integer.toString(menu.blockEntity.getTransparency()));
        addRenderableWidget(transparencyField);
        addRenderableWidget(
                Button.builder(Component.literal("-"), b -> changeLength(-1)).bounds(x + 98, y + 82, 22, 22).build());
        addRenderableWidget(
                Button.builder(Component.literal("+"), b -> changeLength(1)).bounds(x + 134, y + 82, 22, 22).build());
        addRenderableWidget(
                Button.builder(Component.literal("-"), b -> changeWidth(-1)).bounds(x + 98, y + 117, 22, 22).build());
        addRenderableWidget(
                Button.builder(Component.literal("+"), b -> changeWidth(1)).bounds(x + 134, y + 117, 22, 22).build());
        addRenderableWidget(
                Button.builder(Component.literal("-"), b -> changePolygon(-1)).bounds(x + 98, y + 152, 22, 22).build());
        addRenderableWidget(
                Button.builder(Component.literal("+"), b -> changePolygon(1)).bounds(x + 134, y + 152, 22, 22).build());
        addRenderableWidget(Button.builder(Component.translatable("gui.tsuki.light.apply"), b -> apply())
                .bounds(x + 218, y + 225, 122, 24).build());
    }

    private void changeLength(int amount) {
        send(menu.blockEntity.getColor(), Math.clamp(menu.blockEntity.getLength() + amount, 5, 31),
                menu.blockEntity.getWidth(), menu.blockEntity.getTransparency(), menu.blockEntity.getPolygonCount());
    }

    private void changeWidth(int amount) {
        send(menu.blockEntity.getColor(), menu.blockEntity.getLength(),
                Math.clamp(menu.blockEntity.getWidth() + amount, 1, 10), menu.blockEntity.getTransparency(),
                menu.blockEntity.getPolygonCount());
    }

    private void changePolygon(int amount) {
        send(menu.blockEntity.getColor(), menu.blockEntity.getLength(), menu.blockEntity.getWidth(),
                menu.blockEntity.getTransparency(), Math.clamp(menu.blockEntity.getPolygonCount() + amount, 2, 32));
    }

    private void apply() {
        try {
            send(Integer.parseInt(colorField.getValue(), 16), menu.blockEntity.getLength(), menu.blockEntity.getWidth(),
                    Integer.parseInt(transparencyField.getValue()), menu.blockEntity.getPolygonCount());
            onClose();
        } catch (NumberFormatException ignored) {
        }
    }

    private void send(int color, int length, int width, int transparency, int polygonCount) {
        PacketDistributor.sendToServer(new ConfigureCeilingLightPayload(menu.blockEntity.getBlockPos(), color, length,
                width, transparency, polygonCount));
    }

    private int previewColor() {
        try {
            return Integer.parseInt(colorField.getValue(), 16) & 0xFFFFFF;
        } catch (NumberFormatException ignored) {
            return menu.blockEntity.getColor();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partial) {
        renderBackground(graphics, mouseX, mouseY, partial);
        super.render(graphics, mouseX, mouseY, partial);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics g, float partial, int mouseX, int mouseY) {
        int x = leftPos, y = topPos;
        g.fill(x, y, x + imageWidth, y + imageHeight, 0xFF1C2028);
        g.fill(x + 2, y + 2, x + imageWidth - 2, y + imageHeight - 2, 0xFF353B46);
        g.fill(x + 12, y + 30, x + 204, y + 219, 0xFF20252E);
        g.fill(x + 214, y + 30, x + 348, y + 184, 0xFF20252E);
        g.blit(PALETTE, x + 226, y + 42, 0, 0, PALETTE_SIZE, PALETTE_SIZE, PALETTE_SIZE, PALETTE_SIZE);
        g.fill(x + 22, y + 43, x + 86, y + 65, 0xFF000000 | previewColor());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int px = leftPos + 226, py = topPos + 42;
        if (button == 0 && mouseX >= px && mouseX < px + PALETTE_SIZE && mouseY >= py && mouseY < py + PALETTE_SIZE) {
            int color = java.awt.Color.HSBtoRGB((float) (mouseX - px) / PALETTE_SIZE, .78F,
                    1F - (float) (mouseY - py) / (PALETTE_SIZE + 20)) & 0xFFFFFF;
            colorField.setValue(String.format("%06X", color));
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
        g.drawString(font, title, 14, 10, 0xFFF0F0F0, false);
        g.drawString(font, Component.translatable("gui.tsuki.light.color"), 22, 34, 0xFFE0E0E0, false);
        g.drawString(font, Component.translatable("gui.tsuki.light.length", menu.blockEntity.getLength()), 22, 72,
                0xFFE0E0E0, false);
        g.drawString(font, Component.translatable("gui.tsuki.light.width", menu.blockEntity.getWidth()), 22, 107,
                0xFFE0E0E0, false);
        g.drawString(font, Component.translatable("gui.tsuki.light.polygon", menu.blockEntity.getPolygonCount()), 22,
                142, 0xFFE0E0E0, false);
        g.drawString(font, Component.translatable("gui.tsuki.light.transparency"), 22, 181, 0xFFE0E0E0, false);
    }
}
