package cn.mcmod.tsuki.compat.kaleidoscope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.recipes.CookingPotRecipe;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.container.StockpotInput;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe.StockpotRecipe;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModRecipes;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModSoupBases;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public final class KCCookingPotCompat {
    private static final String KC_MODID = "kaleidoscope_cookery";
    private static final ResourceLocation COMPAT_ID_PREFIX = ResourceLocation.fromNamespaceAndPath(KC_MODID,
            "stockpot");
    private static final int DEFAULT_WATER_AMOUNT = 125;
    private static final boolean DEBUG_LOG = false;
    private static String lastDebugKey = "";

    private KCCookingPotCompat() {
    }

    public static Optional<CookingPotRecipe> findMatching(Level level, RecipeWrapper wrapper, FluidStack fluid) {
        if (level == null || !isEnabled()) {
            return Optional.empty();
        }

        ResourceLocation soupBase = getSoupBaseFromFluid(fluid);
        if (soupBase == null) {
            logDebug("skip: unsupported fluid={}, inputs={}", describeFluid(fluid), describeWrapperInputs(wrapper));
            return Optional.empty();
        }

        RecipeType<StockpotRecipe> stockpotType = getStockpotType();
        if (stockpotType == null) {
            logDebug("skip: stockpot recipe type is null, soupBase={}", soupBase);
            return Optional.empty();
        }

        StockpotInput input = createStockpotInput(wrapper, soupBase);
        RecipeHolder<StockpotRecipe> bestMatch = null;
        int bestIngredientCount = -1;
        int checked = 0;
        int matched = 0;
        StringJoiner matchedIds = new StringJoiner(", ");
        for (RecipeHolder<StockpotRecipe> holder : level.getRecipeManager().getAllRecipesFor(stockpotType)) {
            checked++;
            StockpotRecipe recipe = holder.value();
            if (recipe.matches(input, level)) {
                matched++;
                int ingredientCount = getNonEmptyIngredientCount(recipe);
                matchedIds.add(holder.id() + "[ingredients=" + ingredientCount + ",result=" + recipe.result().getCount()
                        + "]");
                if (ingredientCount > bestIngredientCount) {
                    bestIngredientCount = ingredientCount;
                    bestMatch = holder;
                }
            }
        }

        if (bestMatch == null) {
            logDebugDedup(
                    "kc-no-match|" + soupBase + "|" + describeFluid(fluid) + "|" + describeWrapperInputs(wrapper),
                    "no match: soupBase={}, fluid={}, inputs={}, checked={}, matched={}",
                    soupBase,
                    describeFluid(fluid),
                    describeWrapperInputs(wrapper),
                    checked,
                    matched);
            return Optional.empty();
        }

        logDebugDedup(
                "kc-match|" + bestMatch.id() + "|" + soupBase + "|" + describeFluid(fluid) + "|"
                        + describeWrapperInputs(wrapper),
                "matched: selected={}, soupBase={}, fluid={}, inputs={}, checked={}, matched={}, candidates=[{}]",
                bestMatch.id(),
                soupBase,
                describeFluid(fluid),
                describeWrapperInputs(wrapper),
                checked,
                matched,
                matchedIds);
        return transform(bestMatch.id(), bestMatch.value(), level);
    }

    public static List<CookingPotRecipe> getAllForJei(Level level) {
        if (level == null || !isEnabled()) {
            return List.of();
        }

        RecipeType<StockpotRecipe> stockpotType = getStockpotType();
        if (stockpotType == null) {
            return List.of();
        }

        List<CookingPotRecipe> recipes = new ArrayList<>();
        for (RecipeHolder<StockpotRecipe> holder : level.getRecipeManager().getAllRecipesFor(stockpotType)) {
            transform(holder.id(), holder.value(), level).ifPresent(recipes::add);
        }
        return recipes;
    }

    private static Optional<CookingPotRecipe> transform(ResourceLocation sourceId, StockpotRecipe source, Level level) {
        FluidIngredient requiredFluid = getRequiredFluid(source.soupBase());
        if (requiredFluid == null) {
            return Optional.empty();
        }

        CookingPotRecipe recipe = new CookingPotRecipe();
        recipe.setId(ResourceLocation.fromNamespaceAndPath(COMPAT_ID_PREFIX.getNamespace(),
                COMPAT_ID_PREFIX.getPath() + "/compat_" + sourceId.getPath()));

        NonNullList<Ingredient> inputs = NonNullList.create();
        for (Ingredient ingredient : source.getIngredients()) {
            if (!ingredient.isEmpty()) {
                inputs.add(ingredient);
            }
        }

        recipe.inputItems = inputs;
        recipe.fluidInput = requiredFluid;
        recipe.output = source.getResultItem(level.registryAccess()).copy();
        recipe.container = getCarrierItem(source.carrier());
        recipe.recipeTime = source.time();
        recipe.experience = 0.0F;
        return Optional.of(recipe);
    }

    private static StockpotInput createStockpotInput(RecipeWrapper wrapper, ResourceLocation soupBase) {
        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            // Keep the full 9-slot shape (including empty slots) to match KC's
            // StockpotInput semantics.
            inputs.add(wrapper.getItem(i));
        }
        return new StockpotInput(inputs, soupBase);
    }

    private static ItemStack getCarrierItem(Ingredient carrier) {
        ItemStack[] items = carrier.getItems();
        if (items.length == 0) {
            return ItemStack.EMPTY;
        }
        return items[0].copy();
    }

    private static int getNonEmptyIngredientCount(StockpotRecipe recipe) {
        int count = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (!ingredient.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static ResourceLocation getSoupBaseFromFluid(FluidStack fluid) {
        if (fluid.isEmpty() || fluid.getAmount() < DEFAULT_WATER_AMOUNT) {
            return null;
        }

        Fluid fluidType = fluid.getFluid();
        if (fluidType == Fluids.WATER) {
            return ModSoupBases.WATER;
        }
        if (fluidType == Fluids.LAVA) {
            return ModSoupBases.LAVA;
        }
        return null;
    }

    private static FluidIngredient getRequiredFluid(ResourceLocation soupBase) {
        if (ModSoupBases.WATER.equals(soupBase)) {
            return FluidIngredient.fromFluid(Fluids.WATER, DEFAULT_WATER_AMOUNT);
        }
        if (ModSoupBases.LAVA.equals(soupBase)) {
            return FluidIngredient.fromFluid(Fluids.LAVA, DEFAULT_WATER_AMOUNT);
        }
        return null;
    }

    private static boolean isEnabled() {
        return ModList.get().isLoaded(KC_MODID);
    }

    private static RecipeType<StockpotRecipe> getStockpotType() {
        return ModRecipes.STOCKPOT_RECIPE;
    }

    private static String describeWrapperInputs(RecipeWrapper wrapper) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < 9; i++) {
            ItemStack stack = wrapper.getItem(i);
            if (!stack.isEmpty()) {
                joiner.add(i + ":" + stack.getCount() + "x" + stack.getItem());
            }
        }
        return joiner.toString();
    }

    private static String describeFluid(FluidStack fluid) {
        if (fluid.isEmpty()) {
            return "empty";
        }
        return fluid.getAmount() + "mB " + fluid.getFluid();
    }

    private static void logDebug(String message, Object... args) {
        if (DEBUG_LOG) {
            Tsuki.getLogger().info("[KC Stockpot Compat] " + message, args);
        }
    }

    private static void logDebugDedup(String key, String message, Object... args) {
        if (!DEBUG_LOG || key.equals(lastDebugKey)) {
            return;
        }
        lastDebugKey = key;
        Tsuki.getLogger().info("[KC Stockpot Compat] " + message, args);
    }
}
