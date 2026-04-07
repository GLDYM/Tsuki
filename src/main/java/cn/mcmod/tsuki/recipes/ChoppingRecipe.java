package cn.mcmod.tsuki.recipes;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cn.mcmod.mmlib.recipe.AbstractRecipe;
import cn.mcmod.mmlib.recipe.ChanceResult;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class ChoppingRecipe extends AbstractRecipe {

    @Expose
    @SerializedName("ingredient")
    public Ingredient input;
    
    @Expose
    @SerializedName("tool")
    public Ingredient tool;

    @Expose
    @SerializedName("result")
    public ItemStack output;
    
    @Expose
    @SerializedName("byproducts")
    public NonNullList<ChanceResult> extraOutput;

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, this.input);
    }

    public Ingredient getTool() {
        return tool;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        if (inv.isEmpty())
            return false;
        return input.test(inv.getItem(0));
    }
    
    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.output;
    }
    
    public NonNullList<ChanceResult> getByproducts() {
        return this.extraOutput;
    }
    
    public List<ItemStack> rollByproducts(RandomSource rand, int fortuneLevel) {
        List<ItemStack> results = Lists.newArrayList();
        NonNullList<ChanceResult> rollableResults = getByproducts();
        for (ChanceResult output : rollableResults) {
            ItemStack stack = output.rollOutput(rand, fortuneLevel);
            if (!stack.isEmpty())
                results.add(stack);
        }
        return results;
    }

    @Override
    public ItemStack assemble(RecipeWrapper pContainer, HolderLookup.Provider registries) {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.getIngredients().size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.CHOPPING_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.CHOPPING_RECIPE_TYPE.get();
    }

}

