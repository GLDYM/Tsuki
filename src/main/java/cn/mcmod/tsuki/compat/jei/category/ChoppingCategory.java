package cn.mcmod.tsuki.compat.jei.category;

import com.mojang.blaze3d.systems.RenderSystem;

import cn.mcmod.mmlib.recipe.ChanceResult;
import cn.mcmod.mmlib.util.I18nUtil;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.compat.jei.JEIPlugin;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class ChoppingCategory implements IRecipeCategory<ChoppingRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chopping");
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawable chancedSlot;

    public ChoppingCategory(IGuiHelper helper) {
        title = Component.translatable("tsuki.jei.chopping");
        ResourceLocation backgroundImage = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/gui/jei_chopping.png");
        background = helper.createDrawable(backgroundImage, 4, 4, 92, 74);
        chancedSlot = helper.createDrawable(backgroundImage, 100, 0, 18, 18);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(BlockRegistry.CHOPPING_BOARD.get()));
    }

    @Override
    public RecipeType<ChoppingRecipe> getRecipeType() {
        return JEIPlugin.CHOPPING_JEI_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, ChoppingRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 7).addIngredients(recipeIngredients.get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 29).addIngredients(recipe.getTool());
        Minecraft minecraft = Minecraft.getInstance();
        builder.addSlot(RecipeIngredientRole.OUTPUT, 62, 7)
                .addItemStack(recipe.getResultItem(minecraft.level.registryAccess()));

        NonNullList<ChanceResult> byproducts = recipe.getByproducts();
        for (int i = 0; i < Math.min(4, byproducts.size()); i++) {
            ChanceResult chanceResult = byproducts.get(i);
            builder.addSlot(RecipeIngredientRole.OUTPUT, i * 18 + 11, 51).addItemStack(chanceResult.stack())
                    .addRichTooltipCallback((ingredient, tooltip) -> {
                        ChanceResult output = chanceResult;
                        float chance = output.chance();
                        if (chance != 1)
                            tooltip.add(I18nUtil.chanceComponent(chance));
                    });
        }

    }

    @Override
    public void draw(ChoppingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {

        NonNullList<ChanceResult> byproducts = recipe.getByproducts();
        for (int i = 0; i < Math.min(4, byproducts.size()); i++) {
            ChanceResult chanceResult = byproducts.get(i);
            if (chanceResult.chance() != 1) {
                chancedSlot.draw(guiGraphics, i * 18 + 10, 50);
            }
        }
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        guiGraphics.drawString(fontRenderer, Component.translatable("tsuki.jei.chopping.count", recipe.getRecipeTime()),
                33, 32, 0xFEFEFE, true);
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }

}


