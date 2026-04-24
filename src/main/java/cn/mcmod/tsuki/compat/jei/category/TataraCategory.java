package cn.mcmod.tsuki.compat.jei.category;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.compat.jei.JEIPlugin;
import cn.mcmod.tsuki.compat.jei.recipe.TataraJeiRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class TataraCategory implements IRecipeCategory<TataraJeiRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tatara");
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable arrow;

    public TataraCategory(IGuiHelper helper) {
        title = Component.translatable("tsuki.jei.tatara");
        background = helper.createBlankDrawable(119, 58);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.TATARA.get()));
        ResourceLocation arrowTexture = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "textures/gui/pot.png");
        arrow = helper.drawableBuilder(arrowTexture, 176, 15, 34, 17).build();
    }

    @Override
    public RecipeType<TataraJeiRecipe> getRecipeType() {
        return JEIPlugin.TATARA_JEI_TYPE;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TataraJeiRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 36, 1).addItemStack(recipe.ignition());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 99, 7)
                .addItemStack(recipe.ironResult())
                .addRichTooltipCallback((slotView, tooltip) -> {
                    tooltip.add(Component.translatable("tsuki.jei.tatara.iron.amount", "0-9"));
                    tooltip.add(Component.translatable("tsuki.jei.tatara.iron.chance", "8/9", "9"));
                });

        builder.addSlot(RecipeIngredientRole.OUTPUT, 99, 33)
                .addItemStack(recipe.tamahaganeResult())
                .addRichTooltipCallback((slotView, tooltip) -> {
                    tooltip.add(Component.translatable("tsuki.jei.tatara.tamahagane.amount", "0-2"));
                    tooltip.add(Component.translatable("tsuki.jei.tatara.tamahagane.chance", "20%"));
                });
    }

    @Override
    public void draw(TataraJeiRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(8, 14, 0);
        guiGraphics.pose().scale(2.0F, 2.0F, 1.0F);
        guiGraphics.renderItem(recipe.furnace(), 0, 0);
        guiGraphics.pose().popPose();

        arrow.draw(guiGraphics, 57, 20);
    }
}
