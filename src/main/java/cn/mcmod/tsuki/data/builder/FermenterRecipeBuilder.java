package cn.mcmod.tsuki.data.builder;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.recipes.FermenterRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;

public class FermenterRecipeBuilder {
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final NonNullList<ItemStack> result = NonNullList.create();
    private final FluidIngredient fluid;
    private final FluidStack result_fluid;
    private final float experience;
    private final int recipeTime;

    private FermenterRecipeBuilder(FluidIngredient fluid, ItemLike resultItem, int count, FluidStack result_fluid, float exp, int time) {
        this.result.add(new ItemStack(resultItem.asItem(), count));
        this.fluid = fluid;
        this.result_fluid = result_fluid;
        this.experience = exp;
        this.recipeTime = time;
    }
    
    private FermenterRecipeBuilder(FluidIngredient fluid, FluidStack result_fluid, float exp, int time) {
        this.fluid = fluid;
        this.result_fluid = result_fluid;
        this.experience = exp;
        this.recipeTime = time;
    }
    
    public static FermenterRecipeBuilder fermenting(FluidIngredient fluid, FluidStack result_fluid, float exp, int time) {
        return new FermenterRecipeBuilder(fluid, result_fluid, exp, time);
    }
    
    public static FermenterRecipeBuilder fermenting(FluidIngredient fluid, FluidStack result_fluid) {
        return new FermenterRecipeBuilder(fluid, result_fluid, 0F, 800);
    }

    public static FermenterRecipeBuilder fermenting(FluidIngredient fluid, ItemLike resultItem, FluidStack result_fluid) {
        return new FermenterRecipeBuilder(fluid, resultItem, 1, result_fluid, 0F, 800);
    }

    public static FermenterRecipeBuilder fermenting(FluidIngredient fluid, ItemLike resultItem, int count, FluidStack result_fluid) {
        return new FermenterRecipeBuilder(fluid, resultItem, count, result_fluid, 0F, 800);
    }

    public static FermenterRecipeBuilder fermenting(FluidIngredient fluid, ItemLike resultItem, FluidStack result_fluid, float exp, int time) {
        return new FermenterRecipeBuilder(fluid, resultItem, 1, result_fluid, exp, time);
    }

    public static FermenterRecipeBuilder fermenting(FluidIngredient fluid, ItemLike resultItem, int count, FluidStack result_fluid, float exp,
            int time) {
        return new FermenterRecipeBuilder(fluid, resultItem, count, result_fluid, exp, time);
    }

    public FermenterRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }

    public FermenterRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public FermenterRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public FermenterRecipeBuilder requires(Ingredient ingre) {
        return this.requires(ingre, 1);
    }

    public FermenterRecipeBuilder requires(Ingredient ingre, int count) {
        for (int i = 0; i < count; ++i) {
            this.ingredients.add(ingre);
        }
        return this;
    }
    
    public FermenterRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1);
    }

    public FermenterRecipeBuilder addResult(ItemLike result, int count) {
        this.result.add(new ItemStack(result.asItem(), count));
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        FermenterRecipe recipe = new FermenterRecipe();
        recipe.setId(id);
        recipe.outputItems = this.result;
        recipe.inputFluid = this.fluid;
        recipe.inputItems = this.ingredients;
        recipe.outputFluid = this.result_fluid;
        recipe.experience = this.experience;
        recipe.recipeTime = this.recipeTime;
        output.accept(id, recipe, null);
    }

}

