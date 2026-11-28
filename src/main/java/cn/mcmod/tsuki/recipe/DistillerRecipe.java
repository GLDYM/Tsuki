package cn.mcmod.tsuki.recipe;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.mmlib.recipe.AbstractRecipe;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class DistillerRecipe extends AbstractRecipe {
    @Expose
    public int recipeTime;

    @Expose
    @SerializedName("ingredients")
    public NonNullList<Ingredient> inputItems = NonNullList.create();
    @Expose
    @SerializedName("fluid")
    public FluidIngredient inputFluid = FluidIngredient.EMPTY;

    @Expose
    @SerializedName("results")
    public NonNullList<ItemStack> outputItems = NonNullList.create();
    @Expose
    @SerializedName("result_fluid")
    public FluidStack outputFluid = FluidStack.EMPTY;

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems == null ? NonNullList.create() : this.inputItems;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public FluidIngredient getRequiredFluid() {
        return inputFluid == null ? FluidIngredient.EMPTY : inputFluid;
    }

    public boolean matchesWithFluid(FluidStack fluid, RecipeWrapper inv, Level worldIn) {
        if (this.getRequiredFluid() == FluidIngredient.EMPTY)
            return fluid.isEmpty() && matches(inv, worldIn);
        return this.getRequiredFluid().test(fluid) && matches(inv, worldIn);
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        List<ItemStack> inputs = Lists.newArrayList();
        int i = 0;
        for (int j = 0; j < 3; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        return i == this.getIngredients().size() && RecipeMatcher.findMatches(inputs, this.getIngredients()) != null;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider registries) {
        NonNullList<ItemStack> results = this.getResultItemList();
        return results.isEmpty() ? ItemStack.EMPTY : results.get(0).copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.getIngredients().size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        NonNullList<ItemStack> results = this.getResultItemList();
        return results.isEmpty() ? ItemStack.EMPTY : results.get(0);
    }

    public NonNullList<ItemStack> getResultItemList() {
        return this.outputItems == null ? NonNullList.create() : this.outputItems;
    }

    public FluidStack getResultFluid() {
        return outputFluid == null ? FluidStack.EMPTY : outputFluid;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.DISTILLER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.DISTILLER_RECIPE_TYPE.get();
    }

}
