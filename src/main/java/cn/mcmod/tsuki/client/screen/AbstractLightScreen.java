package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.block.entity.AbstractLightBlockEntity;
import cn.mcmod.tsuki.container.AbstractLightContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractLightScreen<E extends AbstractLightBlockEntity,
        M extends AbstractLightContainer<E>> extends AbstractContainerScreen<M> {
    private static final int PALETTE_SIZE = 110;
    private static final ResourceLocation PALETTE = ResourceLocation.fromNamespaceAndPath("tsuki",
            "textures/gui/lighthouse_color_palette.png");
    private EditBox colorField;
    private EditBox transparencyField;

    protected AbstractLightScreen(M menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 360;
        imageHeight = 265;
    }

    @Override
    protected void init() {
        super.init();
        int x = leftPos;
        int y = topPos;
        E light = menu.blockEntity;

        colorField = new EditBox(font, x + 98, y + 43, 70, 22, Component.empty());
        colorField.setMaxLength(6);
        colorField.setFilter(value -> value.matches("[0-9A-Fa-f]*"));
        colorField.setValue(String.format("%06X", light.getColor()));
        addRenderableWidget(colorField);

        transparencyField = new EditBox(font, x + 98, y + 193, 70, 22, Component.empty());
        transparencyField.setMaxLength(3);
        transparencyField.setFilter(value -> value.matches("[0-9]*"));
        transparencyField.setValue(Integer.toString(light.getTransparency()));
        addRenderableWidget(transparencyField);

        addRenderableWidget(Button.builder(Component.literal("-"), button -> changeLength(-1))
                .bounds(x + 98, y + 82, 22, 22).build());
        addRenderableWidget(Button.builder(Component.literal("+"), button -> changeLength(1))
                .bounds(x + 134, y + 82, 22, 22).build());
        addRenderableWidget(Button.builder(Component.literal("-"), button -> changeWidth(-1))
                .bounds(x + 98, y + 117, 22, 22).build());
        addRenderableWidget(Button.builder(Component.literal("+"), button -> changeWidth(1))
                .bounds(x + 134, y + 117, 22, 22).build());
        addRenderableWidget(Button.builder(Component.literal("-"), button -> changePolygonCount(-1))
                .bounds(x + 98, y + 152, 22, 22).build());
        addRenderableWidget(Button.builder(Component.literal("+"), button -> changePolygonCount(1))
                .bounds(x + 134, y + 152, 22, 22).build());
        addRenderableWidget(Button.builder(Component.translatable(translationKey("apply")), button -> apply())
                .bounds(x + 218, y + 225, 122, 24).build());
    }

    private void changeLength(int amount) {
        E light = menu.blockEntity;
        sendConfiguration(light.getColor(), light.clampLength(light.getLength() + amount), light.getWidth(),
                light.getTransparency(), light.getPolygonCount());
    }

    private void changeWidth(int amount) {
        E light = menu.blockEntity;
        sendConfiguration(light.getColor(), light.getLength(), light.clampWidth(light.getWidth() + amount),
                light.getTransparency(), light.getPolygonCount());
    }

    private void changePolygonCount(int amount) {
        E light = menu.blockEntity;
        sendConfiguration(light.getColor(), light.getLength(), light.getWidth(), light.getTransparency(),
                light.clampPolygonCount(light.getPolygonCount() + amount));
    }

    private void apply() {
        try {
            E light = menu.blockEntity;
            int color = Integer.parseInt(colorField.getValue(), 16);
            int transparency = Integer.parseInt(transparencyField.getValue());
            sendConfiguration(color, light.getLength(), light.getWidth(), transparency, light.getPolygonCount());
            onClose();
        } catch (NumberFormatException ignored) {
        }
    }

    protected abstract void sendConfiguration(int color, int length, int width, int transparency, int polygonCount);

    protected String translationGroup() {
        return "light";
    }

    protected String polygonTranslationKey() {
        return "polygon";
    }

    private String translationKey(String key) {
        return "gui.tsuki." + translationGroup() + "." + key;
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
    protected void renderBg(GuiGraphics graphics, float partial, int mouseX, int mouseY) {
        int x = leftPos;
        int y = topPos;
        graphics.fill(x, y, x + imageWidth, y + imageHeight, 0xFF1C2028);
        graphics.fill(x + 2, y + 2, x + imageWidth - 2, y + imageHeight - 2, 0xFF353B46);
        graphics.fill(x + 12, y + 30, x + 204, y + 219, 0xFF20252E);
        graphics.fill(x + 214, y + 30, x + 348, y + 184, 0xFF20252E);
        graphics.blit(PALETTE, x + 226, y + 42, 0, 0, PALETTE_SIZE, PALETTE_SIZE, PALETTE_SIZE, PALETTE_SIZE);
        graphics.fill(x + 22, y + 43, x + 86, y + 65, 0xFF000000 | previewColor());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int paletteX = leftPos + 226;
        int paletteY = topPos + 42;
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
        E light = menu.blockEntity;
        graphics.drawString(font, title, 14, 10, 0xFFF0F0F0, false);
        graphics.drawString(font, Component.translatable(translationKey("color")), 22, 34, 0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable(translationKey("length"), light.getLength()), 22, 72,
                0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable(translationKey("width"), light.getWidth()), 22, 107,
                0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable(translationKey(polygonTranslationKey()),
                light.getPolygonCount()), 22, 142, 0xFFE0E0E0, false);
        graphics.drawString(font, Component.translatable(translationKey("transparency")), 22, 181, 0xFFE0E0E0,
                false);
    }
}
