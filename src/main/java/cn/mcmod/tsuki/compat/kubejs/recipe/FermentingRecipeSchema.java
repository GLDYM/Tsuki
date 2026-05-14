package cn.mcmod.tsuki.compat.kubejs.recipe;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.ListRecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;

public interface FermentingRecipeSchema {
    RecipeKey<List<Ingredient>> INGREDIENTS = IngredientComponent.INGREDIENT.instance().asListOrSelf().inputKey("ingredients");
    RecipeKey<FluidStack> FLUID = FluidStackComponent.FLUID_STACK.inputKey("fluid");
    RecipeKey<List<ItemStack>> RESULTS = ListRecipeComponent.create(ItemStackComponent.ITEM_STACK.instance(), false, true).outputKey("results").optional(List.of());
    RecipeKey<FluidStack> RESULT_FLUID = FluidStackComponent.FLUID_STACK.outputKey("result_fluid");
    RecipeKey<Integer> RECIPE_TIME = NumberComponent.INT.otherKey("recipeTime").optional(400);
    RecipeKey<Float> EXPERIENCE = NumberComponent.FLOAT.otherKey("experience").optional(0F);
    RecipeSchema SCHEMA = new RecipeSchema(INGREDIENTS, FLUID, RESULTS, RESULT_FLUID, RECIPE_TIME, EXPERIENCE);
}
