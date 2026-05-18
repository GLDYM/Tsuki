package cn.mcmod.tsuki.data.builder;

import cn.mcmod.mmlib.recipe.CountedIngredient;
import cn.mcmod.tsuki.recipe.StoneMortarRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class StoneMortarRecipeBuilder {
    private final NonNullList<ItemStack> result = NonNullList.create();
    private Ingredient ingredient = Ingredient.EMPTY;
    private int ingredientCount = 1;
    private final float experience;
    private final int recipeTime;

    private StoneMortarRecipeBuilder(ItemLike resultItem, int count, float exp, int time) {
        this.result.add(new ItemStack(resultItem.asItem(), count));
        this.experience = exp;
        this.recipeTime = time;
    }

    public static StoneMortarRecipeBuilder mortar(ItemLike resultItem) {
        return new StoneMortarRecipeBuilder(resultItem, 1, 0F, 200);
    }

    public static StoneMortarRecipeBuilder mortar(ItemLike resultItem, int count) {
        return new StoneMortarRecipeBuilder(resultItem, count, 0F, 200);
    }

    public static StoneMortarRecipeBuilder mortar(ItemLike resultItem, float exp, int time) {
        return new StoneMortarRecipeBuilder(resultItem, 1, exp, time);
    }

    public static StoneMortarRecipeBuilder mortar(ItemLike resultItem, int count, float exp, int time) {
        return new StoneMortarRecipeBuilder(resultItem, count, exp, time);
    }

    public StoneMortarRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(tag, 1);
    }

    public StoneMortarRecipeBuilder requires(TagKey<Item> tag, int count) {
        return this.requires(Ingredient.of(tag), count);
    }

    public StoneMortarRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public StoneMortarRecipeBuilder requires(ItemLike item, int count) {
        return this.requires(Ingredient.of(item), count);
    }

    public StoneMortarRecipeBuilder requires(Ingredient ingre) {
        return this.requires(ingre, 1);
    }

    public StoneMortarRecipeBuilder requires(Ingredient ingre, int count) {
        this.ingredient = ingre;
        this.ingredientCount = Math.max(1, count);
        return this;
    }

    public StoneMortarRecipeBuilder addResult(ItemLike result) {
        return this.addResult(result, 1);
    }

    public StoneMortarRecipeBuilder addResult(ItemLike result, int count) {
        this.result.add(new ItemStack(result.asItem(), count));
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation resolvedId = withTypeFolder(id, "stone_mortar");
        StoneMortarRecipe recipe = new StoneMortarRecipe();
        recipe.setId(resolvedId);
        recipe.output = this.result;
        recipe.input = new CountedIngredient(this.ingredient, this.ingredientCount);
        recipe.experience = this.experience;
        recipe.recipeTime = this.recipeTime;
        output.accept(resolvedId, recipe, null);
    }

    private static ResourceLocation withTypeFolder(ResourceLocation id, String folder) {
        String path = id.getPath();
        if (path.startsWith(folder + "/")) {
            return id;
        }
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), folder + "/" + path);
    }

}
