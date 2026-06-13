package cn.mcmod.tsuki.compat.jei.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.compat.jei.JEIPlugin;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.recipe.StoneMortarRecipe;
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
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class StoneMortarCategory implements IRecipeCategory<StoneMortarRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "stone_mortar");
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    protected final IDrawable mortar;
    protected final IDrawable basket;

    public StoneMortarCategory(IGuiHelper helper) {
        title = Component.translatable("tsuki.jei.stone_mortar");
        ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/gui/stonemortar.png");
        background = helper.createDrawable(backgroundImage, 39, 13, 87, 62);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(BlockRegistry.STONE_MORTAR.get()));
        mortar = helper.createDrawable(backgroundImage, 176, 0, 14, 16);
        basket = helper.createDrawable(backgroundImage, 190, 18, 16, 6);
    }

    @Override
    public RecipeType<StoneMortarRecipe> getRecipeType() {
        return JEIPlugin.STONE_MORTAR_JEI_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, StoneMortarRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        if (!recipeIngredients.isEmpty()) {
            Ingredient ingredient = recipeIngredients.get(0);
            ItemStack[] stacks = ingredient.getItems();
            if (stacks.length > 0) {
                List<ItemStack> countedStacks = new ArrayList<>();
                for (ItemStack stack : stacks) {
                    ItemStack copy = stack.copy();
                    copy.setCount(recipe.getInputCount());
                    countedStacks.add(copy);
                }
                builder.addSlot(RecipeIngredientRole.INPUT, 1, 14).addItemStacks(countedStacks);
            } else {
                builder.addSlot(RecipeIngredientRole.INPUT, 1, 14).addIngredients(ingredient);
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 5)
                .setOutputSlotBackground()
                .addItemStack(recipe.getResultItemList().get(0));
        if (recipe.getResultItemList().size() > 1) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 41)
                .setOutputSlotBackground()
                .addItemStack(recipe.getResultItemList().get(1));
        }
    }

    @Override
    public void draw(StoneMortarRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        mortar.draw(guiGraphics, 42, 20);
        basket.draw(guiGraphics, 41, 36);
    }

    @Override
    public List<Component> getTooltipStrings(StoneMortarRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        if (isCursorInsideBounds(40, 19, 18, 24, mouseX, mouseY)) {
            List<Component> tooltip = new ArrayList<>();

            int recipeTime = recipe.getRecipeTime();
            if (recipeTime > 0) {
                tooltip.add(Component.translatable("gui.jei.category.smelting.time.seconds", recipeTime / 20));
            }

            float experience = recipe.getExperience();
            if (experience > 0) {
                tooltip.add(Component.translatable("gui.jei.category.smelting.experience", experience));
            }
            return tooltip;
        }
        return Collections.emptyList();
    }

    private static boolean isCursorInsideBounds(int x, int y, int width, int height, double mouseX, double mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
