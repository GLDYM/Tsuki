package cn.mcmod.tsuki.compat.emi.category;

import java.util.ArrayList;
import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.ShakerDataHelper;
import cn.mcmod.tsuki.recipe.ShakerRecipe;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class EmiShakerRecipe extends BasicEmiRecipe {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shaker"),
            EmiStack.of(DrinkRegistry.SHAKER.get()));

    private static final int WIDTH = 125;
    private static final int HEIGHT = 58;
    private static final int SHAKER_X = 64;
    private static final int SHAKER_Y = 13;
    private static final int OUTPUT_X = 106;
    private static final int CONTAINER_Y = 6;
    private static final int OUTPUT_Y = 32;

    private final ShakerRecipe recipe;

    private EmiShakerRecipe(ResourceLocation id, ShakerRecipe recipe, List<EmiIngredient> inputs, List<EmiStack> outputs) {
        super(CATEGORY, id, WIDTH, HEIGHT);
        this.recipe = recipe;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
    }

    public static EmiShakerRecipe of(ResourceLocation id, ShakerRecipe recipe) {
        List<EmiIngredient> inputList = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            inputList.add(EmiIngredient.of(ingredient));
        }

        ItemStack resultStack = getResultStack(recipe);
        List<EmiStack> outputList = new ArrayList<>();
        ItemStack requiredContainer = ShakerDataHelper.getRequiredContainer(resultStack);
        if (!requiredContainer.isEmpty()) {
            outputList.add(EmiStack.of(requiredContainer));
        }
        outputList.add(EmiStack.of(resultStack));
        return new EmiShakerRecipe(id, recipe, inputList, outputList);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < inputs.size()) {
                    int x = 4 + column * 18;
                    int y = 4 + row * 18;
                    widgets.addSlot(inputs.get(inputIndex), x, y).drawBack(true).recipeContext(this);
                }
            }
        }

        int outputIndex = 0;
        if (outputs.size() > 1) {
            widgets.addSlot(outputs.get(outputIndex++), OUTPUT_X, CONTAINER_Y).drawBack(true).recipeContext(this);
        }
        widgets.addSlot(outputs.get(outputIndex), OUTPUT_X, OUTPUT_Y).drawBack(true).recipeContext(this);

        widgets.addDrawable(SHAKER_X, SHAKER_Y, 32, 32, (draw, mouseX, mouseY, delta) -> {
            double time = (System.currentTimeMillis() % 600L) / 600.0D;
            float wobble = (float) Math.sin(time * Math.PI * 2.0D) * 3.0F;

            draw.pose().pushPose();
            draw.pose().translate(16.0F, 16.0F + wobble, 0.0F);
            draw.pose().scale(2.0F, 2.0F, 1.0F);
            draw.pose().translate(-8.0F, -8.0F, 0.0F);
            draw.renderItem(new ItemStack(DrinkRegistry.SHAKER.get()), 0, 0);
            draw.pose().popPose();
        });

        widgets.addTooltipText(List.of(Component.translatable("tsuki.jei.shaker.shake_count", recipe.getShakeCount())),
                SHAKER_X, SHAKER_Y, 32, 32);
    }

    private static ItemStack getResultStack(ShakerRecipe recipe) {
        Minecraft minecraft = Minecraft.getInstance();
        return recipe.getResultItem(minecraft.level == null ? null : minecraft.level.registryAccess());
    }
}
