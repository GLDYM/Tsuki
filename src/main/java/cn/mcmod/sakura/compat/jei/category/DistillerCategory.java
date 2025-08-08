package cn.mcmod.sakura.compat.jei.category;

import cn.mcmod.sakura.SakuraMod;
import cn.mcmod.sakura.block.BlockRegistry;
import cn.mcmod.sakura.block.entity.DistillerBlockEntity;
import cn.mcmod.sakura.compat.jei.JEIPlugin;
import cn.mcmod.sakura.recipes.DistillerRecipe;
import cn.mcmod_mmf.mmlib.fluid.FluidIngredient;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
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

public class DistillerCategory implements IRecipeCategory<DistillerRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(SakuraMod.MODID, "distillation");
    protected final IDrawable heatIndicator;
    protected final IDrawableAnimated arrow;
    protected final IDrawableAnimated bubbles;
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    public DistillerCategory(IGuiHelper helper) {
        title = Component.translatable("sakura.jei.distillation");
        ResourceLocation backgroundImage = new ResourceLocation(SakuraMod.MODID, "textures/gui/distiller.png");
        background = helper.createDrawable(backgroundImage, 32, 16, 110, 54);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.DISTILLER.get()));
        heatIndicator = helper.createDrawable(backgroundImage, 176, 17, 18, 18);
        arrow = helper.drawableBuilder(backgroundImage, 176, 0, 24, 17).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
        bubbles = helper.drawableBuilder(backgroundImage, 176, 35, 18, 18).buildAnimated(18, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public RecipeType<DistillerRecipe> getRecipeType() {
        return JEIPlugin.DISTILLER_JEI_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, DistillerRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        int borderSlotSize = 18;
        for (int row = 0; row < 3; ++row) {
                int inputIndex = row;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, 23, 1 + row * borderSlotSize)
                    .addIngredients(recipeIngredients.get(inputIndex));
                }
        }
        if(recipe.getRequiredFluid() != FluidIngredient.EMPTY)
            builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
            .setFluidRenderer(DistillerBlockEntity.TANK_CAPACITY, true, 16, 52)
            .addIngredients(ForgeTypes.FLUID_STACK, recipe.getRequiredFluid().getMatchingFluidStacks());
        
        for (int row = 0; row < 3; ++row) {
            int inputIndex = row;
            if (inputIndex < recipe.getResultItemList().size()) {
                builder.addSlot(RecipeIngredientRole.OUTPUT, 71, 1 + row * borderSlotSize)
                .addItemStack(recipe.getResultItemList().get(inputIndex));
            }
        }
        if(!recipe.getResultFluid().isEmpty())
            builder.addSlot(RecipeIngredientRole.INPUT, 93, 1)
            .setFluidRenderer(DistillerBlockEntity.TANK_CAPACITY, true, 16, 52)
            .addIngredient(ForgeTypes.FLUID_STACK, recipe.getResultFluid());
    }

    @Override
    public void draw(DistillerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        bubbles.draw(guiGraphics, 46, 0);
        arrow.draw(guiGraphics, 44, 18);
        heatIndicator.draw(guiGraphics, 47, 37);
    }
}
