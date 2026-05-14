package cn.mcmod.tsuki.compat.kubejs.recipe;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;

public interface CookingRecipeSchema {
    RecipeKey<List<Ingredient>> INGREDIENTS = IngredientComponent.INGREDIENT.instance().asListOrSelf().inputKey("ingredients");
    RecipeKey<FluidStack> FLUID = FluidStackComponent.FLUID_STACK.inputKey("fluid");
    RecipeKey<ItemStack> RESULT = ItemStackComponent.ITEM_STACK.outputKey("result");
    RecipeKey<ItemStack> CONTAINER = ItemStackComponent.ITEM_STACK.otherKey("container").optional(ItemStack.EMPTY);
    RecipeKey<Integer> RECIPE_TIME = NumberComponent.INT.otherKey("recipeTime").optional(200);
    RecipeKey<Float> EXPERIENCE = NumberComponent.FLOAT.otherKey("experience").optional(0F);
    RecipeSchema SCHEMA = new RecipeSchema(INGREDIENTS, FLUID, RESULT, CONTAINER, RECIPE_TIME, EXPERIENCE);
}
