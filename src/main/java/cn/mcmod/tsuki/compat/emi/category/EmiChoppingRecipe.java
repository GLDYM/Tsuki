package cn.mcmod.tsuki.compat.emi.category;

import java.util.ArrayList;
import java.util.List;

import cn.mcmod.mmlib.recipe.ChanceResult;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class EmiChoppingRecipe extends BasicEmiRecipe {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chopping"),
            EmiStack.of(BlockRegistry.CHOPPING_BOARD.get()));

    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/gui/jei_chopping.png");

    private static final int WIDTH = 92;
    private static final int HEIGHT = 74;

    private final ChoppingRecipe recipe;

    private EmiChoppingRecipe(ResourceLocation id, ChoppingRecipe recipe, List<EmiIngredient> inputs,
            List<EmiStack> outputs) {
        super(CATEGORY, id, WIDTH, HEIGHT);
        this.recipe = recipe;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
    }

    public static EmiChoppingRecipe of(ResourceLocation id, ChoppingRecipe recipe) {
        List<EmiIngredient> inputList = List.of(EmiIngredient.of(recipe.getIngredients().get(0)),
                EmiIngredient.of(recipe.getTool()));

        List<EmiStack> outputList = new ArrayList<>();
        outputList.add(EmiStack.of(recipe.getResultItem(null)));
        for (ChanceResult byproduct : recipe.getByproducts()) {
            outputList.add(EmiStack.of(byproduct.stack()).setChance(byproduct.chance()));
        }

        return new EmiChoppingRecipe(id, recipe, inputList, outputList);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BG, 0, 0, WIDTH, HEIGHT, 4, 4);

        widgets.addSlot(inputs.get(0), 14, 7).drawBack(false);
        widgets.addSlot(inputs.get(1), 14, 29).drawBack(false);
        widgets.addSlot(outputs.get(0), 62, 7).drawBack(false).recipeContext(this);

        List<ChanceResult> byproducts = recipe.getByproducts();
        for (int i = 0; i < Math.min(4, byproducts.size()); i++) {
            ChanceResult chanceResult = byproducts.get(i);
            if (chanceResult.chance() != 1.0f) {
                widgets.addTexture(BG, i * 18 + 10, 50, 18, 18, 100, 0);
            }
            widgets.addSlot(outputs.get(i + 1), i * 18 + 11, 51)
                    .drawBack(false)
                    .recipeContext(this)
                    .appendTooltip(Component.translatable("mmlib.gui.chance",
                            Math.round(chanceResult.chance() * 100.0f) + "%"));
        }

        widgets.addText(Component.translatable("tsuki.jei.chopping.count", recipe.getRecipeTime()), 33, 32, 0xFEFEFE,
                true);
    }
}
