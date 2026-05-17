package cn.mcmod.tsuki.compat.kaleidoscope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import cn.mcmod.tsuki.recipe.CookingPotRecipe;

import com.github.ysbbbbbb.kaleidoscopecookery.crafting.container.SimpleInput;
import com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe.PotRecipe;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModRecipes;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public final class KCPotCompat {
    private static final String KC_MODID = "kaleidoscope_cookery";
    private static final ResourceLocation COMPAT_ID_PREFIX = ResourceLocation.fromNamespaceAndPath(KC_MODID, "pot");
    private static final int REQUIRED_OIL_AMOUNT = 125;

    private KCPotCompat() {
    }

    public static Optional<CookingPotRecipe> findMatching(Level level, RecipeWrapper wrapper, FluidStack fluid) {
        if (level == null || !isEnabled() || !matchesOil(fluid)) {
            return Optional.empty();
        }

        RecipeType<PotRecipe> potType = getPotType();
        if (potType == null) {
            return Optional.empty();
        }

        SimpleInput input = createInput(wrapper);
        RecipeHolder<PotRecipe> bestMatch = null;
        int bestIngredientCount = -1;
        for (RecipeHolder<PotRecipe> holder : level.getRecipeManager().getAllRecipesFor(potType)) {
            PotRecipe recipe = holder.value();
            if (recipe.matches(input, level)) {
                int ingredientCount = getNonEmptyIngredientCount(recipe);
                if (ingredientCount > bestIngredientCount) {
                    bestIngredientCount = ingredientCount;
                    bestMatch = holder;
                }
            }
        }

        if (bestMatch == null) {
            return Optional.empty();
        }
        return Optional.of(transform(bestMatch.id(), bestMatch.value(), level));
    }

    public static List<CookingPotRecipe> getAllForJei(Level level) {
        if (level == null || !isEnabled()) {
            return List.of();
        }

        RecipeType<PotRecipe> potType = getPotType();
        if (potType == null) {
            return List.of();
        }

        List<CookingPotRecipe> recipes = new ArrayList<>();
        for (RecipeHolder<PotRecipe> holder : level.getRecipeManager().getAllRecipesFor(potType)) {
            recipes.add(transform(holder.id(), holder.value(), level));
        }
        return recipes;
    }

    private static CookingPotRecipe transform(ResourceLocation sourceId, PotRecipe source, Level level) {
        CookingPotRecipe recipe = new CookingPotRecipe();
        String sourcePath = sourceId.getPath();
        String prefixPath = COMPAT_ID_PREFIX.getPath() + "/";
        if (sourcePath.startsWith(prefixPath)) {
            sourcePath = sourcePath.substring(prefixPath.length());
        }
        recipe.setId(ResourceLocation.fromNamespaceAndPath(COMPAT_ID_PREFIX.getNamespace(),
                COMPAT_ID_PREFIX.getPath() + "/compat_" + sourcePath));

        NonNullList<Ingredient> inputs = NonNullList.create();
        for (Ingredient ingredient : source.getIngredients()) {
            if (!ingredient.isEmpty()) {
                inputs.add(ingredient);
            }
        }

        recipe.inputItems = inputs;
        recipe.fluidInput = FluidIngredient.fromFluid(FluidRegistry.FOOD_OIL.get(), REQUIRED_OIL_AMOUNT);
        recipe.output = source.getResultItem(level.registryAccess()).copy();
        recipe.container = getCarrierItem(source.carrier());
        recipe.recipeTime = source.time();
        recipe.experience = 0.0F;
        return recipe;
    }

    private static SimpleInput createInput(RecipeWrapper wrapper) {
        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            inputs.add(wrapper.getItem(i));
        }
        return new SimpleInput(inputs);
    }

    private static ItemStack getCarrierItem(Ingredient carrier) {
        ItemStack[] items = carrier.getItems();
        if (items.length == 0) {
            return ItemStack.EMPTY;
        }
        return items[0].copy();
    }

    private static int getNonEmptyIngredientCount(PotRecipe recipe) {
        int count = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (!ingredient.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static boolean matchesOil(FluidStack fluid) {
        return !fluid.isEmpty() && fluid.getAmount() >= REQUIRED_OIL_AMOUNT && fluid.getFluid().isSame(FluidRegistry.FOOD_OIL.get());
    }

    private static boolean isEnabled() {
        return ModList.get().isLoaded(KC_MODID);
    }

    private static RecipeType<PotRecipe> getPotType() {
        return ModRecipes.POT_RECIPE;
    }
}
