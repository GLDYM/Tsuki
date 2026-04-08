package cn.mcmod.tsuki.data.builder;

import cn.mcmod.tsuki.recipes.StoneMortarRecipe;
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
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
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
        return this.requires(Ingredient.of(tag));
    }

    public StoneMortarRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public StoneMortarRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public StoneMortarRecipeBuilder requires(Ingredient ingre) {
        return this.requires(ingre, 1);
    }

    public StoneMortarRecipeBuilder requires(Ingredient ingre, int count) {
        for (int i = 0; i < count; ++i) {
            this.ingredients.add(ingre);
        }
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
        recipe.inputItems = this.ingredients;
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
