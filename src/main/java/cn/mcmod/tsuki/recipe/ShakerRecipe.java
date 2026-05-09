package cn.mcmod.tsuki.recipe;

import cn.mcmod.mmlib.recipe.AbstractRecipe;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class ShakerRecipe extends AbstractRecipe {
    @Expose
    @SerializedName("ingredients")
    public NonNullList<Ingredient> inputItems;

    @Expose
    @SerializedName("result")
    public ItemStack output = ItemStack.EMPTY;

    @Expose
    @SerializedName("shake_count")
    public int shakeCount = 1;

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public int getShakeCount() {
        return Math.max(1, shakeCount);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        return findMatchingSlots(inv) != null;
    }

    public int[] findMatchingSlots(RecipeWrapper inv) {
        List<ItemStack> inputs = new ArrayList<>();
        List<Integer> slotIds = new ArrayList<>();

        for (int slot = 0; slot < 9; ++slot) {
            ItemStack stack = inv.getItem(slot);
            if (!stack.isEmpty()) {
                inputs.add(stack);
                slotIds.add(slot);
            }
        }

        if (inputs.size() < this.getIngredients().size()) {
            return null;
        }

        int[] matches = matchIngredients(inputs, new boolean[inputs.size()], 0);
        if (matches == null) {
            return null;
        }

        int[] mappedSlots = new int[matches.length];
        for (int i = 0; i < matches.length; ++i) {
            mappedSlots[i] = slotIds.get(matches[i]);
        }
        return mappedSlots;
    }

    private int[] matchIngredients(List<ItemStack> inputs, boolean[] usedSlots, int ingredientIndex) {
        if (ingredientIndex >= this.getIngredients().size()) {
            return new int[this.getIngredients().size()];
        }

        Ingredient ingredient = this.getIngredients().get(ingredientIndex);
        for (int inputIndex = 0; inputIndex < inputs.size(); ++inputIndex) {
            if (usedSlots[inputIndex] || !ingredient.test(inputs.get(inputIndex))) {
                continue;
            }

            usedSlots[inputIndex] = true;
            int[] result = matchIngredients(inputs, usedSlots, ingredientIndex + 1);
            if (result != null) {
                result[ingredientIndex] = inputIndex;
                return result;
            }
            usedSlots[inputIndex] = false;
        }
        return null;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider registries) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.getIngredients().size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.SHAKER_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.SHAKER_RECIPE_TYPE.get();
    }
}
