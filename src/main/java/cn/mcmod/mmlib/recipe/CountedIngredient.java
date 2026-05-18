package cn.mcmod.mmlib.recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.world.item.crafting.Ingredient;

public record CountedIngredient(
        @Expose @SerializedName("ingredient") Ingredient ingredient,
        @Expose @SerializedName("count") int count) {

    public Ingredient ingredient() {
        return this.ingredient;
    }

    public int getCount() {
        return Math.max(1, this.count);
    }
}
