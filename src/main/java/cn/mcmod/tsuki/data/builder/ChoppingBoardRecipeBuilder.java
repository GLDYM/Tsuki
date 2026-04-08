package cn.mcmod.tsuki.data.builder;

import cn.mcmod.mmlib.recipe.ChanceResult;
import cn.mcmod.tsuki.recipes.ChoppingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class ChoppingBoardRecipeBuilder {
    private Ingredient item = Ingredient.EMPTY;
    private Ingredient tool = Ingredient.EMPTY;
    private final ItemStack result;
    private final NonNullList<ChanceResult> byproduces = NonNullList.create();
    private final float experience;
    private final int recipeTime;

    private ChoppingBoardRecipeBuilder(ItemLike resultItem, int count, float exp, int time) {
        this.result = new ItemStack(resultItem.asItem(), count);
        this.experience = exp;
        this.recipeTime = time;
    }

    public static ChoppingBoardRecipeBuilder chop(ItemLike resultItem) {
        return new ChoppingBoardRecipeBuilder(resultItem, 1, 1F, 1);
    }

    public static ChoppingBoardRecipeBuilder chop(ItemLike resultItem, int count) {
        return new ChoppingBoardRecipeBuilder(resultItem, count, 1F, 1);
    }

    public static ChoppingBoardRecipeBuilder chop(ItemLike resultItem, float exp, int time) {
        return new ChoppingBoardRecipeBuilder(resultItem, 1, exp, time);
    }

    public static ChoppingBoardRecipeBuilder chop(ItemLike resultItem, int count, float exp, int time) {
        return new ChoppingBoardRecipeBuilder(resultItem, count, exp, time);
    }

    public ChoppingBoardRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }

    public ChoppingBoardRecipeBuilder requires(ItemLike item) {
        return this.requires(Ingredient.of(item));
    }

    public ChoppingBoardRecipeBuilder requires(Ingredient ingre) {
        if(this.item.isEmpty())
            this.item = ingre;
        return this;
    }
    
    public ChoppingBoardRecipeBuilder requiresTool(TagKey<Item> tag) {
        return this.requiresTool(Ingredient.of(tag));
    }

    public ChoppingBoardRecipeBuilder requiresTool(ItemLike item) {
        return this.requiresTool(Ingredient.of(item));
    }

    public ChoppingBoardRecipeBuilder requiresTool(Ingredient ingre) {
        if(this.tool.isEmpty())
            this.tool = ingre;
        return this;
    }

    public ChoppingBoardRecipeBuilder addByproduce(ItemLike result) {
        return this.addByproduce(result, 1);
    }

    public ChoppingBoardRecipeBuilder addByproduce(ItemLike result, int count) {
        this.byproduces.add(new ChanceResult(new ItemStack(result.asItem(), count), 1));
        return this;
    }
    
    public ChoppingBoardRecipeBuilder addByproduceWithChance(ItemLike result, float chance) {
        return this.addByproduce(result, 1, chance);
    }

    public ChoppingBoardRecipeBuilder addByproduce(ItemLike result, int count, float chance) {
        this.byproduces.add(new ChanceResult(new ItemStack(result.asItem(), count), chance));
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        ResourceLocation resolvedId = withTypeFolder(id, "chopping");
        ChoppingRecipe recipe = new ChoppingRecipe();
        recipe.setId(resolvedId);
        recipe.input = this.item;
        recipe.tool = this.tool;
        recipe.output = this.result;
        recipe.extraOutput = this.byproduces;
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
