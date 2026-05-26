package cn.mcmod.tsuki.compat.emi.category;

import java.util.ArrayList;
import java.util.List;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.DistillerBlockEntity;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.recipe.DistillerRecipe;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;

public class EmiDistillerRecipe extends BasicEmiRecipe {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "distillation"),
            EmiStack.of(BlockRegistry.DISTILLER.get()));

    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/gui/distiller.png");

    private static final int WIDTH = 110;
    private static final int HEIGHT = 54;

    private final DistillerRecipe recipe;

    private EmiDistillerRecipe(ResourceLocation id, DistillerRecipe recipe, List<EmiIngredient> inputs,
            List<EmiStack> outputs) {
        super(CATEGORY, id, WIDTH, HEIGHT);
        this.recipe = recipe;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
    }

    public static EmiDistillerRecipe of(ResourceLocation id, DistillerRecipe recipe) {
        List<EmiIngredient> inputList = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            inputList.add(EmiIngredient.of(ingredient));
        }
        if (recipe.getRequiredFluid() != FluidIngredient.EMPTY) {
            inputList.add(EmiIngredient.of(recipe.getRequiredFluid().getMatchingFluidStacks().stream()
                    .map(fs -> EmiStack.of(fs.getFluid(), fs.getAmount())).toList()));
        }

        List<EmiStack> outputList = new ArrayList<>(recipe.getResultItemList().stream().map(EmiStack::of).toList());
        if (!recipe.getResultFluid().isEmpty()) {
            outputList.add(EmiStack.of(recipe.getResultFluid().getFluid(), recipe.getResultFluid().getAmount()));
        }

        return new EmiDistillerRecipe(id, recipe, inputList, outputList);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BG, 0, 0, WIDTH, HEIGHT, 32, 16);
        widgets.addTexture(BG, 47, 37, 18, 18, 176, 17);
        widgets.addAnimatedTexture(BG, 44, 18, 24, 17, 176, 0, 900, true, false, false);
        widgets.addAnimatedTexture(BG, 46, 0, 18, 18, 176, 35, 1400, false, true, false);

        for (int row = 0; row < 3; ++row) {
            if (row < recipe.getIngredients().size()) {
                widgets.addSlot(inputs.get(row), 22, row * 18).drawBack(false);
            }
        }

        if (recipe.getRequiredFluid() != FluidIngredient.EMPTY) {
            FluidStack fs = recipe.getRequiredFluid().getMatchingFluidStacks().stream().findFirst()
                    .orElse(FluidStack.EMPTY);
            widgets.addTank(EmiStack.of(fs.getFluid(), fs.getAmount()), 0, 0, 18, 54,
                    DistillerBlockEntity.TANK_CAPACITY)
                    .drawBack(false);
        }

        for (int row = 0; row < 3; ++row) {
            if (row < recipe.getResultItemList().size()) {
                widgets.addSlot(outputs.get(row), 70, row * 18).drawBack(false).recipeContext(this);
            }
        }

        if (!recipe.getResultFluid().isEmpty()) {
            widgets.addTank(EmiStack.of(recipe.getResultFluid().getFluid(), recipe.getResultFluid().getAmount()), 92, 0,
                    18, 54, DistillerBlockEntity.TANK_CAPACITY)
                    .drawBack(false)
                    .recipeContext(this);
        }

        widgets.addTooltipText(List.of(
                Component.translatable("gui.jei.category.smelting.time.seconds", recipe.getRecipeTime() / 20),
                Component.translatable("gui.jei.category.smelting.experience", recipe.getExperience())),
                43, 9, 24, 36);
    }
}
