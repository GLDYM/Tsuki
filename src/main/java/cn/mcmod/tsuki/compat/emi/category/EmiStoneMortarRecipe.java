package cn.mcmod.tsuki.compat.emi.category;

import java.util.ArrayList;
import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.recipe.StoneMortarRecipe;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class EmiStoneMortarRecipe extends BasicEmiRecipe {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "stone_mortar"),
            EmiStack.of(BlockRegistry.STONE_MORTAR.get()));

    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/gui/stonemortar.png");

    private static final int WIDTH = 87;
    private static final int HEIGHT = 62;

    private final StoneMortarRecipe recipe;

    private EmiStoneMortarRecipe(ResourceLocation id, StoneMortarRecipe recipe, List<EmiIngredient> inputs,
            List<EmiStack> outputs) {
        super(CATEGORY, id, WIDTH, HEIGHT);
        this.recipe = recipe;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
    }

    public static EmiStoneMortarRecipe of(ResourceLocation id, StoneMortarRecipe recipe) {
        List<EmiIngredient> inputList = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            inputList.add(EmiIngredient.of(ingredient));
        }

        List<EmiStack> outputList = recipe.getResultItemList().stream().map(EmiStack::of).toList();
        return new EmiStoneMortarRecipe(id, recipe, inputList, outputList);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BG, 0, 0, WIDTH, HEIGHT, 39, 13);
        widgets.addTexture(BG, 42, 20, 14, 16, 176, 0);
        widgets.addTexture(BG, 41, 36, 16, 6, 190, 18);

        for (int row = 0; row < 2; ++row) {
            for (int column = 0; column < 2; ++column) {
                int inputIndex = row * 2 + column;
                if (inputIndex < inputs.size()) {
                    widgets.addSlot(inputs.get(inputIndex), column * 18, 13 + row * 18).drawBack(false);
                }
            }
        }

        widgets.addSlot(outputs.get(0), 65, 4).drawBack(false).recipeContext(this);
        if (outputs.size() > 1) {
            widgets.addSlot(outputs.get(1), 65, 40).drawBack(false).recipeContext(this);
        }

        widgets.addTooltipText(List.of(
                Component.translatable("gui.jei.category.smelting.time.seconds", recipe.getRecipeTime() / 20),
                Component.translatable("gui.jei.category.smelting.experience", recipe.getExperience())),
                40, 19, 18, 24);
    }
}
