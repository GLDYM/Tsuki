package cn.mcmod.tsuki.compat.jei.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.entity.FermenterBlockEntity;
import cn.mcmod.tsuki.compat.jei.JEIPlugin;
import cn.mcmod.tsuki.recipes.FermenterRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
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

public class FermenterCategory implements IRecipeCategory<FermenterRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fermenting");
    protected final IDrawableAnimated arrow;
    protected final IDrawableAnimated bubbles;
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public FermenterCategory(IGuiHelper helper) {
        title = Component.translatable("tsuki.jei.fermenting");
        ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/gui/barrel.png");
        background = helper.createDrawable(backgroundImage, 32, 16, 110, 54);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.FERMENTER.get()));
        arrow = helper.drawableBuilder(backgroundImage, 176, 0, 24, 17).buildAnimated(200,
                IDrawableAnimated.StartDirection.LEFT, false);
        bubbles = helper.drawableBuilder(backgroundImage, 176, 35, 18, 18).buildAnimated(18,
                IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<FermenterRecipe> getRecipeType() {
        return JEIPlugin.FERMENTER_JEI_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, FermenterRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        int borderSlotSize = 18;
        for (int row = 0; row < 3; ++row) {
            int inputIndex = row;
            if (inputIndex < recipeIngredients.size()) {
                builder.addSlot(RecipeIngredientRole.INPUT, 23, 1 + row * borderSlotSize)
                        .addIngredients(recipeIngredients.get(inputIndex));
            }
        }
        if (recipe.getRequiredFluid() != FluidIngredient.EMPTY)
            builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
                    .setFluidRenderer(FermenterBlockEntity.TANK_CAPACITY, true, 16, 52)
                    .addIngredients(NeoForgeTypes.FLUID_STACK, recipe.getRequiredFluid().getMatchingFluidStacks());

        for (int row = 0; row < 3; ++row) {
            int inputIndex = row;
            if (inputIndex < recipe.getResultItemList().size()) {
                builder.addSlot(RecipeIngredientRole.OUTPUT, 71, 1 + row * borderSlotSize)
                        .addItemStack(recipe.getResultItemList().get(inputIndex));
            }
        }
        if (!recipe.getResultFluid().isEmpty())
            builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 1)
                    .setFluidRenderer(FermenterBlockEntity.TANK_CAPACITY, true, 16, 52)
                    .addIngredient(NeoForgeTypes.FLUID_STACK, recipe.getResultFluid());
    }

    @Override
    public void draw(FermenterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        bubbles.draw(guiGraphics, 46, 10);
        arrow.draw(guiGraphics, 44, 28);
    }

    @Override
    public List<Component> getTooltipStrings(FermenterRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        if (isCursorInsideBounds(43, 9, 24, 36, mouseX, mouseY)) {
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
