package cn.mcmod.tsuki.recipe;

import cn.mcmod.mmlib.recipe.CountedIngredient;
import cn.mcmod.mmlib.recipe.AbstractRecipe;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class StoneMortarRecipe extends AbstractRecipe {
    @Expose()
    public int recipeTime;

    @Expose()
    @SerializedName("ingredient")
    public CountedIngredient input = new CountedIngredient(Ingredient.EMPTY, 1);

    @Expose()
    @SerializedName("results")
    public NonNullList<ItemStack> output;

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, this.input.ingredient());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        for (int slot = 0; slot < 4; ++slot) {
            ItemStack stack = inv.getItem(slot);
            if (matchesInput(stack)) {
                return true;
            }
        }
        return false;
    }

    public boolean matchesInput(ItemStack stack) {
        return !stack.isEmpty() && this.input.ingredient().test(stack) && stack.getCount() >= getInputCount();
    }

    public int getInputCount() {
        return this.input.getCount();
    }

    public Ingredient getInputIngredient() {
        return this.input.ingredient();
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider registries) {
        return this.output.get(0).copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.getIngredients().size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.output.get(0);
    }

    public NonNullList<ItemStack> getResultItemList() {
        return this.output;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(int recipeTime) {
        this.recipeTime = recipeTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.STONE_MORTAR_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.STONE_MORTAR_RECIPE_TYPE.get();
    }

}
