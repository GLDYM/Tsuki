package cn.mcmod.tsuki.compat.kubejs.recipe;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.ListRecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public interface StoneMortarRecipeSchema {
    RecipeKey<Ingredient> INGREDIENT = IngredientComponent.INGREDIENT.inputKey("ingredient");
    RecipeKey<List<ItemStack>> RESULTS = ListRecipeComponent.create(ItemStackComponent.ITEM_STACK.instance(), false, true).outputKey("results");
    RecipeKey<Integer> RECIPE_TIME = NumberComponent.INT.otherKey("recipeTime").optional(200);
    RecipeKey<Float> EXPERIENCE = NumberComponent.FLOAT.otherKey("experience").optional(0F);
    RecipeSchema SCHEMA = new RecipeSchema(INGREDIENT, RESULTS, RECIPE_TIME, EXPERIENCE);
}
