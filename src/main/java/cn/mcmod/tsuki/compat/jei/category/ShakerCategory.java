package cn.mcmod.tsuki.compat.jei.category;

import java.util.Collections;
import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.compat.jei.JEIPlugin;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.ShakerDataHelper;
import cn.mcmod.tsuki.recipe.ShakerRecipe;
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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class ShakerCategory implements IRecipeCategory<ShakerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shaker");

    private static final int WIDTH = 125;
    private static final int HEIGHT = 58;
    private static final int INPUT_X = 4;
    private static final int INPUT_Y = 4;
    private static final int SLOT_SIZE = 18;
    private static final int SHAKER_X = 64;
    private static final int SHAKER_Y = 13;
    private static final int SHAKER_WIDTH = 32;
    private static final int SHAKER_HEIGHT = 32;
    private static final int OUTPUT_X = 106;
    private static final int CONTAINER_Y = 6;
    private static final int OUTPUT_Y = 32;

    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slot;

    public ShakerCategory(IGuiHelper helper) {
        title = Component.translatable("tsuki.jei.shaker");
        background = helper.createBlankDrawable(WIDTH, HEIGHT);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(DrinkRegistry.SHAKER.get()));
        slot = helper.getSlotDrawable();
    }

    @Override
    public RecipeType<ShakerRecipe> getRecipeType() {
        return JEIPlugin.SHAKER_JEI_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, ShakerRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, INPUT_X + column * SLOT_SIZE, INPUT_Y + row * SLOT_SIZE)
                            .addIngredients(recipeIngredients.get(inputIndex));
                }
            }
        }

        ItemStack requiredContainer = ShakerDataHelper.getRequiredContainer(getResultStack(recipe));
        if (!requiredContainer.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.CATALYST, OUTPUT_X, CONTAINER_Y).addItemStack(requiredContainer);
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, OUTPUT_X, OUTPUT_Y).addItemStack(getResultStack(recipe));
    }

    @Override
    public void draw(ShakerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX,
            double mouseY) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipe.getIngredients().size()) {
                    slot.draw(guiGraphics, INPUT_X + column * SLOT_SIZE - 1, INPUT_Y + row * SLOT_SIZE - 1);
                }
            }
        }
        ItemStack requiredContainer = ShakerDataHelper.getRequiredContainer(getResultStack(recipe));
        if (!requiredContainer.isEmpty()) {
            slot.draw(guiGraphics, OUTPUT_X - 1, CONTAINER_Y - 1);
        }
        slot.draw(guiGraphics, OUTPUT_X - 1, OUTPUT_Y - 1);

        float time = (System.currentTimeMillis() % 600L) / 600.0F;
        float wobble = (float) Math.sin(time * Math.PI * 2.0D) * 3.0F;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(SHAKER_X + SHAKER_WIDTH / 2.0F, SHAKER_Y + SHAKER_HEIGHT / 2.0F + wobble, 0);
        guiGraphics.pose().scale(2.0F, 2.0F, 1.0F);
        guiGraphics.pose().translate(-8.0F, -8.0F, 0);
        guiGraphics.renderItem(new ItemStack(DrinkRegistry.SHAKER.get()), 0, 0);
        guiGraphics.pose().popPose();
    }

    @Override
    public List<Component> getTooltipStrings(ShakerRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        if (isCursorInsideBounds(SHAKER_X, SHAKER_Y, SHAKER_WIDTH, SHAKER_HEIGHT, mouseX, mouseY)) {
            return List.of(Component.translatable("tsuki.jei.shaker.shake_count", recipe.getShakeCount()));
        }
        return Collections.emptyList();
    }

    private static ItemStack getResultStack(ShakerRecipe recipe) {
        Minecraft minecraft = Minecraft.getInstance();
        return recipe.getResultItem(minecraft.level == null ? null : minecraft.level.registryAccess());
    }

    private static boolean isCursorInsideBounds(int x, int y, int width, int height, double mouseX, double mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
