package cn.mcmod.tsuki.compat.emi.category;

import java.util.ArrayList;
import java.util.List;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.recipe.CookingPotRecipe;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;

public class EmiCookingPotRecipe extends BasicEmiRecipe {
    public static final EmiRecipeCategory CATEGORY = new EmiRecipeCategory(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cooking"),
            EmiStack.of(BlockRegistry.COOKING_POT.get()));

    private static final ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/gui/pot.png");

    private static final int WIDTH = 151;
    private static final int HEIGHT = 54;

    private final CookingPotRecipe recipe;

    private EmiCookingPotRecipe(ResourceLocation id, CookingPotRecipe recipe, List<EmiIngredient> inputs,
            List<EmiStack> outputs) {
        super(CATEGORY, id, WIDTH, HEIGHT);
        this.recipe = recipe;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static void register(EmiRegistry registry) {
        registry.addCategory(CATEGORY);
    }

    public static EmiCookingPotRecipe of(ResourceLocation id, CookingPotRecipe recipe) {
        List<EmiIngredient> inputList = new ArrayList<>();
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            inputList.add(EmiIngredient.of(ingredient));
        }

        FluidIngredient fluid = recipe.getRequiredFluid();
        if (fluid != FluidIngredient.EMPTY) {
            inputList.add(EmiIngredient.of(fluid.getMatchingFluidStacks().stream()
                    .map(fs -> EmiStack.of(fs.getFluid(), fs.getAmount())).toList()));
        }

        RegistryAccess access = Minecraft.getInstance().level == null ? RegistryAccess.EMPTY
                : Minecraft.getInstance().level.registryAccess();
        ItemStack resultStack = recipe.getResultItem(access);

        List<EmiStack> outputList = new ArrayList<>();
        outputList.add(EmiStack.of(resultStack));
        outputList.add(EmiStack.of(resultStack.copy()));
        if (!recipe.getContainer().isEmpty()) {
            outputList.add(EmiStack.of(recipe.getContainer()));
        }

        return new EmiCookingPotRecipe(id, recipe, inputList, outputList);
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BG, 0, 0, WIDTH, HEIGHT, 13, 16);
        widgets.addTexture(BG, 86, 0, 17, 15, 176, 0);
        widgets.addAnimatedTexture(BG, 81, 12, 34, 17, 176, 15, 900, true, false, false);

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipe.getIngredients().size()) {
                    widgets.addSlot(inputs.get(inputIndex), 22 + column * 18, row * 18).drawBack(false);
                }
            }
        }

        if (recipe.getRequiredFluid() != FluidIngredient.EMPTY) {
            FluidStack fs = recipe.getRequiredFluid().getMatchingFluidStacks().stream().findFirst().orElse(FluidStack.EMPTY);
            widgets.addTank(EmiStack.of(fs.getFluid(), fs.getAmount()), 0, 0, 18, 54, CookingPotBlockEntity.TANK_CAPACITY)
                    .drawBack(false);
        }

        widgets.addSlot(outputs.get(0), 126, 10).drawBack(false).recipeContext(this);

        if (!recipe.getContainer().isEmpty()) {
            widgets.addSlot(EmiStack.of(recipe.getContainer()), 94, 36).drawBack(false).recipeContext(this);
        }

        widgets.addSlot(outputs.get(1), 126, 36).drawBack(false).recipeContext(this);

        widgets.addTooltipText(List.of(
                Component.translatable("gui.jei.category.smelting.time.seconds", recipe.getRecipeTime() / 20),
                Component.translatable("gui.jei.category.smelting.experience", recipe.getExperience())),
                81, 0, 34, 29);
    }
}
