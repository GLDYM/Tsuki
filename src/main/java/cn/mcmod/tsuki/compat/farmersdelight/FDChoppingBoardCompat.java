package cn.mcmod.tsuki.compat.farmersdelight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.mcmod.mmlib.recipe.ChanceResult;
import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipeInput;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

public final class FDChoppingBoardCompat {
    private static final String FD_MODID = "farmersdelight";
    private static final ResourceLocation COMPAT_ID_PREFIX = ResourceLocation.fromNamespaceAndPath(FD_MODID, "cutting");

    private FDChoppingBoardCompat() {
    }

    public static Optional<ChoppingRecipe> findMatching(Level level, RecipeWrapper wrapper, ItemStack toolStack) {
        if (level == null || !isEnabled()) {
            return Optional.empty();
        }

        RecipeType<CuttingBoardRecipe> cuttingType = getCuttingType();
        if (cuttingType == null) {
            return Optional.empty();
        }

        RecipeHolder<CuttingBoardRecipe> bestMatch = null;
        int bestIngredientCount = -1;
        for (RecipeHolder<CuttingBoardRecipe> holder : level.getRecipeManager().getAllRecipesFor(cuttingType)) {
            CuttingBoardRecipe recipe = holder.value();
            if (recipe.matches(new CuttingBoardRecipeInput(wrapper.getItem(0), toolStack), level)) {
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

    public static List<ChoppingRecipe> getAllForJei(Level level) {
        if (level == null || !isEnabled()) {
            return List.of();
        }

        RecipeType<CuttingBoardRecipe> cuttingType = getCuttingType();
        if (cuttingType == null) {
            return List.of();
        }

        List<ChoppingRecipe> recipes = new ArrayList<>();
        for (RecipeHolder<CuttingBoardRecipe> holder : level.getRecipeManager().getAllRecipesFor(cuttingType)) {
            recipes.add(transform(holder.id(), holder.value(), level));
        }
        return recipes;
    }

    private static ChoppingRecipe transform(ResourceLocation sourceId, CuttingBoardRecipe source, Level level) {
        ChoppingRecipe recipe = new ChoppingRecipe();
        String sourcePath = sourceId.getPath();
        String prefixPath = COMPAT_ID_PREFIX.getPath() + "/";
        if (sourcePath.startsWith(prefixPath)) {
            sourcePath = sourcePath.substring(prefixPath.length());
        }
        recipe.setId(ResourceLocation.fromNamespaceAndPath(COMPAT_ID_PREFIX.getNamespace(),
                COMPAT_ID_PREFIX.getPath() + "/compat_" + sourcePath));

        recipe.input = source.getIngredients().isEmpty() ? Ingredient.EMPTY : source.getIngredients().getFirst();
        recipe.tool = source.getTool();
        recipe.output = source.getResultItem(level.registryAccess()).copy();
        recipe.extraOutput = NonNullList.create();
        boolean skippedPrimaryResult = false;
        for (vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult result : source.getRollableResults()) {
            if (!result.stack().isEmpty()) {
                if (!skippedPrimaryResult && ItemStack.isSameItemSameComponents(result.stack(), recipe.output)
                        && result.stack().getCount() == recipe.output.getCount()) {
                    skippedPrimaryResult = true;
                    continue;
                }
                recipe.extraOutput.add(new ChanceResult(result.stack().copy(), result.chance()));
            }
        }
        recipe.experience = 0.0F;
        recipe.recipeTime = 1;
        return recipe;
    }

    private static int getNonEmptyIngredientCount(CuttingBoardRecipe recipe) {
        int count = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (!ingredient.isEmpty()) {
                count++;
            }
        }
        if (!recipe.getTool().isEmpty()) {
            count++;
        }
        return count;
    }

    private static boolean isEnabled() {
        return ModList.get().isLoaded(FD_MODID);
    }

    private static RecipeType<CuttingBoardRecipe> getCuttingType() {
        return ModRecipeTypes.CUTTING.get();
    }
}
