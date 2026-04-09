package cn.mcmod.tsuki.compat.jei.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.compat.jei.JEIPlugin;
import cn.mcmod.tsuki.recipes.CookingPotRecipe;
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CookingPotCategory implements IRecipeCategory<CookingPotRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cooking");
    protected final IDrawable heatIndicator;
    protected final IDrawableAnimated arrow;
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public CookingPotCategory(IGuiHelper helper) {
        title = Component.translatable("tsuki.jei.cooking");
        ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "textures/gui/pot.png");
        background = helper.createDrawable(backgroundImage, 13, 16, 151, 54);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.COOKING_POT.get()));
        heatIndicator = helper.createDrawable(backgroundImage, 176, 0, 17, 15);
        arrow = helper.drawableBuilder(backgroundImage, 176, 15, 34, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<CookingPotRecipe> getRecipeType() {
        return JEIPlugin.COOKING_POT_JEI_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, CookingPotRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        int borderSlotSize = 18;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, 23 + column * borderSlotSize, 1 + row * borderSlotSize)
                    .addIngredients(recipeIngredients.get(inputIndex));
                }
            }
        }
        if(recipe.getRequiredFluid() != FluidIngredient.EMPTY)
            builder.addSlot(RecipeIngredientRole.CATALYST, 1, 1)
            .setFluidRenderer(CookingPotBlockEntity.TANK_CAPACITY, true, 16, 52)
            .addIngredients(NeoForgeTypes.FLUID_STACK, recipe.getRequiredFluid().getMatchingFluidStacks());
        Minecraft minecraft = Minecraft.getInstance();
        ItemStack resultStack = recipe.getResultItem(minecraft.level.registryAccess());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 11).addItemStack(resultStack);

        ItemStack containerStack = recipe.getContainer();
        if (!containerStack.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 95, 37).addItemStack(containerStack);
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 37).addItemStack(resultStack);
    }

    @Override
    public void draw(CookingPotRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 81, 12);
        heatIndicator.draw(guiGraphics, 86, 0);
    }

    @Override
    public List<Component> getTooltipStrings(CookingPotRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (isCursorInsideBounds(81, 0, 34, 29, mouseX, mouseY)) {
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
