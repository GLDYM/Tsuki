package cn.mcmod.tsuki.data.builder;

import cn.mcmod.tsuki.recipe.ShakerRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class ShakerRecipeBuilder {
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final ItemStack result;
    private int shakeCount = 12;

    private ShakerRecipeBuilder(ItemLike resultItem, int count) {
        this.result = new ItemStack(resultItem.asItem(), count);
    }

    public static ShakerRecipeBuilder shaking(ItemLike resultItem) {
        return new ShakerRecipeBuilder(resultItem, 1);
    }

    public static ShakerRecipeBuilder shaking(ItemLike resultItem, int count) {
        return new ShakerRecipeBuilder(resultItem, count);
    }

    public ShakerRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }

    public ShakerRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public ShakerRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public ShakerRecipeBuilder requires(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public ShakerRecipeBuilder shakeCount(int shakeCount) {
        this.shakeCount = Math.max(1, shakeCount);
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation resolvedId = withTypeFolder(id, "shaker");
        ShakerRecipe recipe = new ShakerRecipe();
        recipe.setId(resolvedId);
        recipe.inputItems = this.ingredients;
        recipe.output = this.result.copy();
        recipe.shakeCount = this.shakeCount;
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
