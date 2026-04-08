package cn.mcmod.tsuki.data.builder;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.recipes.CookingPotRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class CookingPotRecipeBuilder {
    private final ItemStack result;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final FluidIngredient fluid;
    private ItemStack container = ItemStack.EMPTY;
    private final float experience;
    private final int recipeTime;

    private CookingPotRecipeBuilder(FluidIngredient fluid, ItemLike resultItem, int count, float exp, int time) {
        this.result = new ItemStack(resultItem.asItem(), count);
        this.fluid = fluid;
        this.experience = exp;
        this.recipeTime = time;
    }

    public static CookingPotRecipeBuilder cooking(FluidIngredient fluid, ItemLike resultItem) {
        return new CookingPotRecipeBuilder(fluid, resultItem, 1, 0F, 200);
    }

    public static CookingPotRecipeBuilder cooking(FluidIngredient fluid, ItemLike resultItem, int count) {
        return new CookingPotRecipeBuilder(fluid, resultItem, count, 0F, 200);
    }

    public static CookingPotRecipeBuilder cooking(FluidIngredient fluid, ItemLike resultItem, float exp, int time) {
        return new CookingPotRecipeBuilder(fluid, resultItem, 1, exp, time);
    }

    public static CookingPotRecipeBuilder cooking(FluidIngredient fluid, ItemLike resultItem, int count, float exp,
            int time) {
        return new CookingPotRecipeBuilder(fluid, resultItem, count, exp, time);
    }

    public CookingPotRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }

    public CookingPotRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public CookingPotRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; ++i) {
            this.requires(Ingredient.of(item));
        }
        return this;
    }

    public CookingPotRecipeBuilder requires(Ingredient ingre) {
        return this.requires(ingre, 1);
    }

    public CookingPotRecipeBuilder requires(Ingredient ingre, int count) {
        for (int i = 0; i < count; ++i) {
            this.ingredients.add(ingre);
        }
        return this;
    }

    public CookingPotRecipeBuilder container(ItemLike item) {
        this.container = new ItemStack(item);
        return this;
    }

    public CookingPotRecipeBuilder container(ItemLike item, int count) {
        this.container = new ItemStack(item, count);
        return this;
    }

    public CookingPotRecipeBuilder container(ItemStack stack) {
        this.container = stack.copy();
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation resolvedId = withTypeFolder(id, "cooking");
        CookingPotRecipe recipe = new CookingPotRecipe();
        recipe.setId(resolvedId);
        recipe.output = this.result;
        recipe.container = this.container.copy();
        recipe.fluidInput = this.fluid;
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
