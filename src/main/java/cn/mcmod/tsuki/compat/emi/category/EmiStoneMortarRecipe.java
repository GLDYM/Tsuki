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
import java.util.Arrays;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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
        if (!recipe.getIngredients().isEmpty()) {
            Ingredient ingredient = recipe.getIngredients().get(0);
            ItemStack[] stacks = ingredient.getItems();
            if (stacks.length > 0) {
                List<EmiStack> countedStacks = Arrays.stream(stacks)
                        .map(ItemStack::copy)
                        .peek(stack -> stack.setCount(recipe.getInputCount()))
                        .map(EmiStack::of)
                        .toList();
                inputList.add(EmiIngredient.of(countedStacks));
            } else {
                inputList.add(EmiIngredient.of(ingredient));
            }
        }

        List<EmiStack> outputList = recipe.getResultItemList().stream().map(EmiStack::of).toList();
        return new EmiStoneMortarRecipe(id, recipe, inputList, outputList);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BG, 0, 0, WIDTH, HEIGHT, 39, 13);
        widgets.addTexture(BG, 42, 20, 14, 16, 176, 0);
        widgets.addTexture(BG, 41, 36, 16, 6, 190, 18);

        if (!inputs.isEmpty()) {
            widgets.addSlot(inputs.get(0), 0, 13).drawBack(false);
        }

        widgets.addSlot(outputs.get(0), 61, 0).large(true).drawBack(false).recipeContext(this);
        if (outputs.size() > 1) {
            widgets.addSlot(outputs.get(1), 61, 36).large(true).drawBack(false).recipeContext(this);
        }

        widgets.addTooltipText(List.of(
                Component.translatable("tsuki.recipe.time.seconds", recipe.getRecipeTime() / 20),
                Component.translatable("tsuki.recipe.experience", recipe.getExperience())),
                40, 19, 18, 24);
    }
}
