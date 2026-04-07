package cn.mcmod.tsuki.recipes;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cn.mcmod.mmlib.recipe.AbstractRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class StoneMortarRecipe extends AbstractRecipe {
    
    @Expose()
    @SerializedName("ingredients")
    public NonNullList<Ingredient> inputItems;
    
    @Expose()
    @SerializedName("results")
    public NonNullList<ItemStack> output;

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        List<ItemStack> inputs = Lists.newArrayList();
        int i = 0;

        for (int j = 0; j < 4; ++j) {
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

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.STONE_MORTAR_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.STONE_MORTAR_RECIPE_TYPE.get();
    }

}

