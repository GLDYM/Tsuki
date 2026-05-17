package cn.mcmod.tsuki.compat.kaleidoscope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;

import com.github.ysbbbbbb.kaleidoscopecookery.crafting.recipe.ChoppingBoardRecipe;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModRecipes;

public final class KCChoppingBoardCompat {
    private static final String KC_MODID = "kaleidoscope_cookery";
    private static final ResourceLocation COMPAT_ID_PREFIX = ResourceLocation.fromNamespaceAndPath(KC_MODID,
            "chopping_board");

    private KCChoppingBoardCompat() {
    }

    public static Optional<ChoppingRecipe> findMatching(Level level, ItemStack inputStack, ItemStack toolStack) {
        if (level == null || !isEnabled() || inputStack.isEmpty() || !Ingredient.of(TsukiItemTags.TOOLS_KNIVES).test(toolStack)) {
            return Optional.empty();
        }

        RecipeType<ChoppingBoardRecipe> choppingType = getChoppingType();
        if (choppingType == null) {
            return Optional.empty();
        }

        RecipeHolder<ChoppingBoardRecipe> bestMatch = null;
        int bestIngredientCount = -1;
        SingleRecipeInput input = new SingleRecipeInput(inputStack);
        for (RecipeHolder<ChoppingBoardRecipe> holder : level.getRecipeManager().getAllRecipesFor(choppingType)) {
            ChoppingBoardRecipe recipe = holder.value();
            if (recipe.matches(input, level)) {
                int ingredientCount = recipe.getIngredient().isEmpty() ? 0 : 1;
                if (ingredientCount > bestIngredientCount) {
                    bestIngredientCount = ingredientCount;
                    bestMatch = holder;
                }
            }
        }

        if (bestMatch == null) {
            return Optional.empty();
        }
        return Optional.of(transform(bestMatch.id(), bestMatch.value()));
    }

    public static List<ChoppingRecipe> getAllForJei(Level level) {
        if (level == null || !isEnabled()) {
            return List.of();
        }

        RecipeType<ChoppingBoardRecipe> choppingType = getChoppingType();
        if (choppingType == null) {
            return List.of();
        }

        List<ChoppingRecipe> recipes = new ArrayList<>();
        for (RecipeHolder<ChoppingBoardRecipe> holder : level.getRecipeManager().getAllRecipesFor(choppingType)) {
            recipes.add(transform(holder.id(), holder.value()));
        }
        return recipes;
    }

    private static ChoppingRecipe transform(ResourceLocation sourceId, ChoppingBoardRecipe source) {
        ChoppingRecipe recipe = new ChoppingRecipe();
        String sourcePath = sourceId.getPath();
        String prefixPath = COMPAT_ID_PREFIX.getPath() + "/";
        if (sourcePath.startsWith(prefixPath)) {
            sourcePath = sourcePath.substring(prefixPath.length());
        }
        recipe.setId(ResourceLocation.fromNamespaceAndPath(COMPAT_ID_PREFIX.getNamespace(),
                COMPAT_ID_PREFIX.getPath() + "/compat_" + sourcePath));

        recipe.input = source.getIngredient();
        recipe.tool = Ingredient.of(TsukiItemTags.TOOLS_KNIVES);
        recipe.output = source.getResult().copy();
        recipe.extraOutput = NonNullList.create();
        recipe.experience = 0.0F;
        recipe.recipeTime = 1;
        return recipe;
    }

    private static boolean isEnabled() {
        return ModList.get().isLoaded(KC_MODID);
    }

    private static RecipeType<ChoppingBoardRecipe> getChoppingType() {
        return ModRecipes.CHOPPING_BOARD_RECIPE;
    }
}
