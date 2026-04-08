package cn.mcmod.tsuki.data;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.data.builder.ChoppingBoardRecipeBuilder;
import cn.mcmod.tsuki.data.builder.CookingPotRecipeBuilder;
import cn.mcmod.tsuki.data.builder.DistillerRecipeBuilder;
import cn.mcmod.tsuki.data.builder.FermenterRecipeBuilder;
import cn.mcmod.tsuki.data.builder.StoneMortarRecipeBuilder;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod.tsuki.fluid.FluidRegistry;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.tags.TsukiFluidTags;
import cn.mcmod.tsuki.tags.TsukiItemTags;
import cn.mcmod_mmf.mmlib.data.AbstractRecipeProvider;
import net.minecraft.core.HolderLookup;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.concurrent.CompletableFuture;
import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Ingredient.ItemValue;
import net.minecraft.world.item.crafting.Ingredient.TagValue;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.fluids.FluidStack;
import vectorwing.farmersdelight.common.registry.ModItems;

public class TsukiRecipeProvider extends AbstractRecipeProvider {
   public TsukiRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
      super(packOutput, Tsuki.MODID, provider);
   }

   protected void buildRecipes(RecipeOutput consumer) {
      this.registerCraftingRecipe(consumer);
      this.registerMortarRecipe(consumer);
      this.registerCookingRecipe(consumer);
      this.registerFermenterRecipe(consumer);
      this.registerDistillerRecipe(consumer);
      this.registerChoppingRecipes(consumer);
   }

   private void registerCraftingRecipe(RecipeOutput consumer) {
      SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(TsukiFoodSet.BOILED_BONITO.getItem().get()),
            RecipeCategory.FOOD,
            TsukiFoodSet.DRIED_BONITO.getItem().get(),
            0.5F,
            100
         )
         .unlockedBy("has_ingredient", has(TsukiFoodSet.BOILED_BONITO.getItem().get()))
         .group(Tsuki.MODID)
         .save(consumer, "smoking_bonito");
      this.makeSlab(consumer, BlockRegistry.TATAMI_SLAB, BlockRegistry.TATAMI);
      this.makeSlab(consumer, BlockRegistry.TATAMI_SLAB_WAXED, BlockRegistry.TATAMI_WAXED);
      this.makeSlab(consumer, BlockRegistry.TATAMI_SLAB_SUNBURNT, BlockRegistry.TATAMI_SUNBURNT);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TATAMI_WAXED.get(), 1)
         .requires(BlockRegistry.TATAMI.get())
         .requires(Items.HONEYCOMB)
         .unlockedBy("has_tatami", has(BlockRegistry.TATAMI.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiFoodSet.DOUGH_OKINOYAKI.getItem().get(), 2)
         .requires(TsukiItemTags.DOUGH)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.FOODS_RAW_MEAT)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.SALT)
         .unlockedBy("has_egg", has(TsukiItemTags.EGGS))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY.getItem().get())
         .requires(TsukiCuisineSet.RICE_COOKED.getItem().get())
         .requires(TsukiNormalItemSet.CURRY_SAUCE.getItem().get())
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY_KATSU.getItem().get())
         .requires(TsukiCuisineSet.RICE_CURRY.getItem().get())
         .requires(TsukiFoodSet.KATSU.getItem().get())
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY_CHEESE_KATSU.getItem().get())
         .requires(TsukiCuisineSet.RICE_CURRY_CHEESE.getItem().get())
         .requires(TsukiFoodSet.KATSU.getItem().get())
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY_BURGER.getItem().get())
         .requires(TsukiCuisineSet.RICE_CURRY.getItem().get())
         .requires(TsukiFoodSet.BURGER.getItem().get())
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY_CHEESE_BURGER.getItem().get())
         .requires(TsukiCuisineSet.RICE_CURRY_CHEESE.getItem().get())
         .requires(TsukiFoodSet.BURGER.getItem().get())
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY_CHEESE.getItem().get())
         .requires(TsukiCuisineSet.RICE_CURRY.getItem().get())
         .requires(TsukiItemTags.CHEESE)
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiCuisineSet.RICE_CURRY_CHEESE.getItem().get())
         .requires(TsukiCuisineSet.RICE_COOKED.getItem().get())
         .requires(TsukiNormalItemSet.CURRY_SAUCE.getItem().get())
         .requires(TsukiItemTags.CHEESE)
         .unlockedBy("has_curry", has(TsukiNormalItemSet.CURRY_SAUCE.getItem().get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "alter_rice_curry_cheese"));
      this.foodCooking(TsukiFoodSet.DOUGH_OKINOYAKI.getItem(), TsukiFoodSet.OKINOYAKI.getItem(), 1.0F, consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiFoodSet.OKINOYAKI_PLUS.getItem().get())
         .requires(TsukiFoodSet.OKINOYAKI.getItem().get())
         .requires(TsukiNormalItemSet.WORCESTER_SAUCE.getItem().get())
         .requires(TsukiFoodSet.MAYONAISE.getItem().get())
         .unlockedBy("has_okinoyaki", has(TsukiFoodSet.OKINOYAKI.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiFoodSet.OKINOYAKI_FINAL.getItem().get())
         .requires(TsukiFoodSet.OKINOYAKI.getItem().get())
         .requires(TsukiNormalItemSet.WORCESTER_SAUCE.getItem().get())
         .requires(TsukiFoodSet.MAYONAISE.getItem().get())
         .requires(TsukiFoodSet.BONITO_SHAVING.getItem().get())
         .unlockedBy("has_okinoyaki", has(TsukiFoodSet.OKINOYAKI.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, TsukiFoodSet.OKINOYAKI_FINAL.getItem().get())
         .requires(TsukiFoodSet.OKINOYAKI_PLUS.getItem().get())
         .requires(TsukiFoodSet.BONITO_SHAVING.getItem().get())
         .unlockedBy("has_okinoyaki", has(TsukiFoodSet.OKINOYAKI_PLUS.getItem().get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "alter_okinoyaki_final"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.STRAW_BLOCK.get(), 4)
         .pattern("LLL")
         .pattern("LLL")
         .pattern("LLL")
         .define('L', TsukiItemTags.STRAW)
         .unlockedBy("has_item", has(TsukiItemTags.STRAW))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.IRON_FISH_KNIFE.get())
         .pattern("  I")
         .pattern(" I ")
         .pattern("L  ")
         .define('I', net.neoforged.neoforge.common.Tags.Items.INGOTS_IRON)
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.IRON_NOODLE_KNIFE.get())
         .pattern("II")
         .pattern("II")
         .pattern("IL")
         .define('I', net.neoforged.neoforge.common.Tags.Items.INGOTS_IRON)
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SAKURA_AXE.get())
         .pattern("DD ")
         .pattern("DL ")
         .pattern(" L ")
         .define('D', ItemRegistry.SAKURA_DIAMOND.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(ItemRegistry.SAKURA_DIAMOND.get()))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SAKURA_PICKAXE.get())
         .pattern("DDD")
         .pattern(" L ")
         .pattern(" L ")
         .define('D', ItemRegistry.SAKURA_DIAMOND.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(ItemRegistry.SAKURA_DIAMOND.get()))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SAKURA_SHOVEL.get())
         .pattern("D")
         .pattern("L")
         .pattern("L")
         .define('D', ItemRegistry.SAKURA_DIAMOND.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(ItemRegistry.SAKURA_DIAMOND.get()))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SAKURA_HOE.get())
         .pattern("DD ")
         .pattern(" L ")
         .pattern(" L ")
         .define('D', ItemRegistry.SAKURA_DIAMOND.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(ItemRegistry.SAKURA_DIAMOND.get()))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SAKURA_FISH_KNIFE.get())
         .pattern("  D")
         .pattern(" D ")
         .pattern("L  ")
         .define('D', ItemRegistry.SAKURA_DIAMOND.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(ItemRegistry.SAKURA_DIAMOND.get()))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemRegistry.SAKURA_NOODLE_KNIFE.get())
         .pattern("DD")
         .pattern("DD")
         .pattern("DL")
         .define('D', ItemRegistry.SAKURA_DIAMOND.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(ItemRegistry.SAKURA_DIAMOND.get()))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.TATAMI.get(), 6)
         .pattern("LLL")
         .pattern("L#L")
         .pattern("LLL")
         .define('#', TsukiItemTags.LUMBER)
         .define('L', TsukiItemTags.STRAW)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TORCH, 4)
         .pattern("C")
         .pattern("#")
         .define('C', ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get())
         .define('#', net.neoforged.neoforge.common.Tags.Items.RODS_WOODEN)
         .unlockedBy("has_item", has(net.neoforged.neoforge.common.Tags.Items.RODS_WOODEN))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "torchs_from_charcoal"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.STICK, 4)
         .pattern("#")
         .pattern("#")
         .define('#', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sticks_from_lumbers"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.OBON.get())
         .pattern("LLL")
         .pattern("L#L")
         .define('#', BlockRegistry.SAKURA_LEAVES.get())
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PAPER, 4)
         .pattern("###")
         .define('#', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "papers_from_lumbers"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockItemRegistry.CHOPPING_BOARD.get())
         .pattern("###")
         .pattern("I I")
         .define('#', TsukiItemTags.LUMBER)
         .define('I', net.neoforged.neoforge.common.Tags.Items.RODS_WOODEN)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chopping_board"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockItemRegistry.FERMENTER.get())
         .pattern("SSS")
         .pattern("PPP")
         .pattern("SSS")
         .define('S', TsukiItemTags.LUMBER)
         .define('P', ItemTags.LOGS)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fermenter"));
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockItemRegistry.DISTILLER.get())
         .pattern("ISI")
         .pattern("PPP")
         .pattern("III")
         .define('S', TsukiItemTags.LUMBER)
         .define('P', ItemTags.LOGS)
         .define('I', net.neoforged.neoforge.common.Tags.Items.INGOTS_IRON)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "distiller"));
      this.registerFarmerDelightRecipes(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.COOKING_POT.get())
         .pattern("#L#")
         .pattern("###")
         .define('#', net.neoforged.neoforge.common.Tags.Items.INGOTS_IRON)
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.STONE_MORTAR.get())
         .pattern("L  ")
         .pattern("###")
         .pattern("###")
         .define('#', net.neoforged.neoforge.common.Tags.Items.COBBLESTONES)
         .define('L', TsukiItemTags.LUMBER)
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      this.foodSmeltingRecipes(
         "eggplant_bake",
         FoodRegistry.FOODSET.get(TsukiFoodSet.EGGPLANT).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.EGGPLANT_BAKED).get(),
         0.5F,
         consumer
      );
      this.foodSmeltingRecipes(
         "taro_bake", ItemRegistry.TARO.get(), FoodRegistry.FOODSET.get(TsukiFoodSet.TARO_BAKED).get(), 0.5F, consumer
      );
      this.foodSmeltingRecipes(
         "burger",
         FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER_RAW).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER).get(),
         0.5F,
         consumer
      );
      this.foodSmeltingRecipes(
         "chikuwa",
         FoodRegistry.FOODSET.get(TsukiFoodSet.CHIKUWA_RAW).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.CHIKUWA).get(),
         0.5F,
         consumer
      );
      this.foodSmeltingRecipes(
         "bun",
         ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get(),
         0.5F,
         consumer
      );
      this.foodSmeltingRecipes(
         "buckwheat_bread",
         ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH_BUCKWHEAT).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.BUCKWHEAT_BREAD).get(),
         0.5F,
         consumer
      );
      this.foodSmeltingRecipes(
         "rice_bread",
         ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH_RICE).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.RICE_BREAD).get(),
         0.5F,
         consumer
      );
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH).get(), 3)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_flour", has(TsukiItemTags.FLOUR_WHEAT))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockItemRegistry.NABE_SUKIYAKI.get())
         .requires(BlockItemRegistry.COOKING_POT.get())
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .requires(TsukiItemTags.RAW_BEEF)
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_CARROT)
         .requires(TsukiItemTags.MUSHROOMS)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.VEGETABLES)
         .unlockedBy("has_pot", has(BlockItemRegistry.COOKING_POT.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockItemRegistry.NABE_ODEN.get())
         .requires(BlockItemRegistry.COOKING_POT.get())
         .requires(TsukiItemTags.FISHCAKE)
         .requires(TsukiItemTags.FISHCAKE)
         .requires(TsukiItemTags.FISHCAKE)
         .requires(TsukiItemTags.FISHCAKE)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.DASHI)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .unlockedBy("has_pot", has(BlockItemRegistry.COOKING_POT.get()))
         .save(consumer);
      this.makeItemToBucket(BucketItemRegistry.FOOD_OIL_BUCKET, Ingredient.of(TsukiItemTags.SEEDS_RAPESEED))
         .unlockedBy("has_seeds", has(TsukiItemTags.SEEDS_RAPESEED))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH_BUCKWHEAT).get(), 3)
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_flour", has(TsukiItemTags.FLOUR_BUCKWHEAT))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH_RICE).get(), 3)
         .requires(TsukiItemTags.FLOUR_RICE)
         .requires(TsukiItemTags.FLOUR_RICE)
         .requires(TsukiItemTags.FLOUR_RICE)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_flour", has(TsukiItemTags.FLOUR_RICE))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOKO_TAMAGOYAKI.get())
         .requires(TsukiItemTags.SOUPS)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(BlockRegistry.OBON.get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TAMAGOYAKI).get())
         .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOUKU_FISH_COOKED.get())
         .requires(TsukiItemTags.SOUPS)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(BlockRegistry.OBON.get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.FISH_BAKE).get())
         .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOUKU_FISH_SALT.get())
         .requires(TsukiItemTags.SOUPS)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(BlockRegistry.OBON.get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.FISH_BAKE_SALT).get())
         .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOUKU_FISH_RAW.get())
         .requires(TsukiItemTags.SOUPS)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(BlockRegistry.OBON.get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SASHIMI).get())
         .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOKO_YAKINIKU.get())
         .requires(TsukiItemTags.SOUPS)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(BlockRegistry.OBON.get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.YAKINIKU).get())
         .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, FoodRegistry.FOODSET.get(TsukiFoodSet.SASHIMI).get())
         .requires(TsukiItemTags.SLICES_RAW_FISHES)
         .requires(TsukiItemTags.SLICES_RAW_FISHES)
         .requires(TsukiItemTags.SOYSAUCE)
         .unlockedBy("has_fish", has(TsukiItemTags.SLICES_RAW_FISHES))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, FoodRegistry.FOODSET.get(TsukiFoodSet.CHIKUWA_RAW).get(), 2)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get())
         .requires(TsukiItemTags.SALT)
         .unlockedBy("has_fish", has(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.SAKURA_SAPLING.get())
         .requires(ItemTags.SAPLINGS)
         .requires(net.neoforged.neoforge.common.Tags.Items.DYES_PINK)
         .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_RED.get())
         .requires(ItemTags.SAPLINGS)
         .requires(net.neoforged.neoforge.common.Tags.Items.DYES_RED)
         .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_GREEN.get())
         .requires(ItemTags.SAPLINGS)
         .requires(net.neoforged.neoforge.common.Tags.Items.DYES_GREEN)
         .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_YELLOW.get())
         .requires(ItemTags.SAPLINGS)
         .requires(net.neoforged.neoforge.common.Tags.Items.DYES_YELLOW)
         .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_ORANGE.get())
         .requires(ItemTags.SAPLINGS)
         .requires(net.neoforged.neoforge.common.Tags.Items.DYES_ORANGE)
         .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(Items.DRIED_KELP)
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_BAMBOO).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(Items.DRIED_KELP)
         .requires(BlockRegistry.BAMBOOSHOOT.get())
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_SEAWEED).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(Items.DRIED_KELP)
         .requires(Items.DRIED_KELP)
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_MUSHROOM).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(Items.DRIED_KELP)
         .requires(TsukiItemTags.MUSHROOMS)
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_TEMPURA).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(Items.DRIED_KELP)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TEMPURA).get())
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(TsukiItemTags.VINEGAR)
         .requires(TsukiItemTags.SUGAR)
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.SUSHI).get(), 2)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get())
         .requires(TsukiItemTags.SLICES_RAW_FISHES)
         .unlockedBy("has_rice", has(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.SUSHI_SHRIMP).get(), 2)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get())
         .requires(TsukiItemTags.SHRIMP)
         .unlockedBy("has_rice", has(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.SUSHI_TAMAGO).get(), 3)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TAMAGOYAKI).get())
         .unlockedBy("has_rice", has(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.SUSHI_INARI).get(), 2)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TOFU_FRIED).get())
         .unlockedBy("has_rice", has(FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.TEMPURA_BATTER).get(), 8)
         .requires(TsukiItemTags.FLOUR)
         .requires(TsukiItemTags.SALT)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_flour", has(TsukiItemTags.FLOUR))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.HAMBURGER).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER).get())
         .requires(TsukiItemTags.TOMATOSAUCE)
         .unlockedBy("has_bun", has(FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.CHEESE).get())
         .requires(TsukiItemTags.MILK)
         .requires(TsukiItemTags.SALT)
         .unlockedBy("has_salt", has(TsukiItemTags.SALT))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER_DISH).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER).get())
         .requires(TsukiItemTags.SALAD_INGREDIENTS_CABBAGE)
         .unlockedBy("has_burger", has(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.CHEESE_BURGER).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO_SAUCE).get())
         .requires(TsukiItemTags.CHEESE)
         .unlockedBy("has_bun", has(FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.CHEESE_BURGER).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.HAMBURGER).get())
         .requires(TsukiItemTags.CHEESE)
         .unlockedBy("has_bun", has(FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cheese_burger_from_hamburger"));
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI).get(), 8)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .unlockedBy("has_rice", has(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()))
         .save(consumer);
      this.foodSmeltingRecipes(
         "mochi_toasted",
         FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI).get(),
         FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI_TOASTED).get(),
         0.5F,
         consumer
      );
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI_SAKURA).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI).get())
         .requires(BlockRegistry.SAKURA_LEAVES.get())
         .unlockedBy("has_mochi", has(FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI).get()))
         .save(consumer);
      this.makeIngotToBlock(BlockItemRegistry.BAMBOO_BLOCK, (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO))
         .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get()))
         .save(consumer);
      this.makeIngotToBlock(BlockItemRegistry.BAMBOO_BLOCK, () -> Items.BAMBOO)
         .unlockedBy("has_item", has(Items.BAMBOO))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_block_from_vanilla_bamboo"));
      this.makeIngotToBlock(BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT, (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT))
         .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()))
         .save(consumer);
      this.makeIngotToBlock(BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK, (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL))
         .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get()))
         .save(consumer);
      this.makeBlockToIngot((Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO), BlockItemRegistry.BAMBOO_BLOCK).save(consumer);
      this.makeBlockToIngot(() -> Items.BAMBOO, BlockItemRegistry.BAMBOO_BLOCK)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_block_to_vanilla_bamboo"));
      this.makeBlockToIngot((Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL), BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK)
         .save(consumer);
      this.makeBlockToIngot((Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT), BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT)
         .save(consumer);
      this.makeLumber((Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_BAMBOO), Ingredient.of(TsukiItemTags.BAMBOO))
         .unlockedBy("has_item", has(TsukiItemTags.BAMBOO))
         .save(consumer);
      this.makeLumber(
            (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.MAPLE_LOG.get())
         )
         .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
         .save(consumer);
      this.makeLumber(
            (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.SAKURA_LOG.get())
         )
         .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
         .save(consumer);
      this.makeLumber(
            (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.MAPLE_WOOD.get())
         )
         .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_lumber_from_wood"));
      this.makeLumber(
            (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.SAKURA_WOOD.get())
         )
         .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_lumber_from_wood"));
      this.makeLumber(
            (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.STRIPPED_MAPLE_LOG.get())
         )
         .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_lumber_from_stripped"));
      this.makeLumber(
            (Supplier<? extends Item>)ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.STRIPPED_SAKURA_LOG.get())
         )
         .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_lumber_from_stripped"));
      this.makeLumberToPlank(BlockRegistry.BAMBOO_PLANK, Ingredient.of(TsukiItemTags.LUMBER_BAMBOO))
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      this.makeLumberToPlank(BlockRegistry.MAPLE_PLANK, Ingredient.of(TsukiItemTags.LUMBER_MAPLE))
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      this.makeLumberToPlank(BlockRegistry.SAKURA_PLANK, Ingredient.of(TsukiItemTags.LUMBER_SAKURA))
         .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
         .save(consumer);
      SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(BlockRegistry.BAMBOO_BLOCK.get()),
            RecipeCategory.MISC,
            BlockRegistry.BAMBOO_CHARCOAL_BLOCK.get(),
            0.5F,
            200
         )
         .group(Tsuki.MODID)
         .unlockedBy("has_item", has(BlockRegistry.BAMBOO_BLOCK.get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_block_from_smelt"));
      SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get()),
            RecipeCategory.MISC,
            BlockRegistry.BAMBOO_CHARCOAL_BLOCK.get(),
            0.5F,
            200
         )
         .group(Tsuki.MODID)
         .unlockedBy("has_item", has(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_block_sunburnt_from_smelt"));
      SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get()),
            RecipeCategory.MISC,
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get(),
            0.5F,
            200
         )
         .group(Tsuki.MODID)
         .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_charcoal_from_smelt"));
      SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()),
            RecipeCategory.MISC,
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get(),
            0.5F,
            200
         )
         .group(Tsuki.MODID)
         .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_charcoal_sunburnt_from_smelt"));
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TsukiCuisineSet.SOBA_ZARU.getItem().get())
         .requires(TsukiCuisineSet.SOBA.getItem().get())
         .requires(TsukiNormalItemSet.KAESHI.getItem().get())
         .unlockedBy("has_soba", has(TsukiCuisineSet.SOBA.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TsukiNormalItemSet.SOBA_BLOCK.getItem().get())
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.FLOUR_BUCKWHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_soba", has(TsukiNormalItemSet.FLOUR_BUCKWHEAT.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TsukiNormalItemSet.RAMEN_BLOCK.getItem().get())
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiNormalItemSet.ALKALINE.getItem().get())
         .requires(TsukiItemTags.SALT)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_ramen", has(TsukiNormalItemSet.ALKALINE.getItem().get()))
         .save(consumer);
      ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TsukiNormalItemSet.UDON_BLOCK.getItem().get())
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.FLOUR_WHEAT)
         .requires(TsukiItemTags.SALT)
         .requires(TsukiItemTags.SALT)
         .requires(TsukiItemTags.WATER)
         .unlockedBy("has_udon", has(TsukiNormalItemSet.FLOUR.getItem().get()))
         .save(consumer);
   }

   private void registerMortarRecipe(RecipeOutput consumer) {
      StoneMortarRecipeBuilder.mortar(Items.BONE_MEAL, 3)
         .addResult(Items.BONE_MEAL, 3)
         .requires(net.neoforged.neoforge.common.Tags.Items.BONES)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bonemeal_from_mortar"));
      StoneMortarRecipeBuilder.mortar(Items.SAND)
         .addResult(Items.FLINT)
         .requires(net.neoforged.neoforge.common.Tags.Items.GRAVELS)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "flint_from_mortar"));
      StoneMortarRecipeBuilder.mortar(Items.GRAVEL)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SALT).get(), 2)
         .requires(net.neoforged.neoforge.common.Tags.Items.COBBLESTONES)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "salt_from_mortar"));
      StoneMortarRecipeBuilder.mortar(Items.COBBLESTONE)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.ALKALINE).get(), 2)
         .requires(net.neoforged.neoforge.common.Tags.Items.STONES)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "alkaline_from_mortar"));
      StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.CHARCOAL_POWDER).get(), 1)
         .requires(Ingredient.of(Items.CHARCOAL, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get()))
         .requires(Ingredient.of(Items.CHARCOAL, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get()))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "charcoal_powder"));
      StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BROWN_RICE).get(), 1)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BROWN_RICE).get(), 1)
         .requires(TsukiItemTags.SEEDS_RICE)
         .requires(TsukiItemTags.SEEDS_RICE)
         .requires(TsukiItemTags.SEEDS_RICE)
         .requires(TsukiItemTags.SEEDS_RICE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "brown_rice_from_mortar"));
      StoneMortarRecipeBuilder.mortar(Items.GREEN_DYE, 1)
         .addResult(Items.GREEN_DYE, 1)
         .requires(ItemTags.LEAVES)
         .requires(ItemTags.LEAVES)
         .requires(ItemTags.LEAVES)
         .requires(ItemTags.LEAVES)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "dye_green_from_leaves"));
      StoneMortarRecipeBuilder.mortar(FoodRegistry.FOODSET.get(TsukiFoodSet.MINCED_MEAT).get(), 2)
         .addResult(FoodRegistry.FOODSET.get(TsukiFoodSet.MINCED_MEAT).get(), 2)
         .requires(
            Ingredient.fromValues(
               Stream.of(
                  new TagValue(TsukiItemTags.RAW_CHICKEN),
                  new TagValue(TsukiItemTags.RAW_PORK),
                  new TagValue(TsukiItemTags.RAW_BEEF),
                  new TagValue(TsukiItemTags.RAW_MUTTON)
               )
            )
         )
         .requires(
            Ingredient.fromValues(
               Stream.of(
                  new TagValue(TsukiItemTags.RAW_CHICKEN),
                  new TagValue(TsukiItemTags.RAW_PORK),
                  new TagValue(TsukiItemTags.RAW_BEEF),
                  new TagValue(TsukiItemTags.RAW_MUTTON)
               )
            )
         )
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "minced_meat"));
      StoneMortarRecipeBuilder.mortar(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER_RAW).get(), 2)
         .addResult(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER_RAW).get(), 2)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.MINCED_MEAT).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BREADCRUMBS).get())
         .requires(TsukiItemTags.CROPS_ONION)
         .requires(TsukiItemTags.EGGS)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "burger_raw"));
      StoneMortarRecipeBuilder.mortar(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get(), 1)
         .addResult(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get(), 1)
         .requires(TsukiItemTags.FISHES)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "surimi_from_mortar"));
      StoneMortarRecipeBuilder.mortar(FoodRegistry.FOODSET.get(TsukiFoodSet.BONITO_SHAVING).get(), 1)
         .addResult(FoodRegistry.FOODSET.get(TsukiFoodSet.BONITO_SHAVING).get(), 1)
         .requires(TsukiFoodSet.DRIED_BONITO.getItem().get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bonito_shaving_from_mortar"));
      StoneMortarRecipeBuilder.mortar(FoodRegistry.FOODSET.get(TsukiFoodSet.BREADCRUMBS).get(), 2)
         .addResult(FoodRegistry.FOODSET.get(TsukiFoodSet.BREADCRUMBS).get(), 2)
         .requires(TsukiItemTags.BREAD)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "breadcrumbs_from_breads"));
      StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RICE).get(), 1)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.NUKA).get())
         .requires(TsukiItemTags.RICE_BROWN)
         .requires(TsukiItemTags.RICE_BROWN)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_from_mortar"));
      StoneMortarRecipeBuilder.mortar(Items.SUGAR, 3)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOLASSES).get())
         .requires(Items.SUGAR_CANE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sugar_from_mortar"));
      StoneMortarRecipeBuilder.mortar(Items.SUGAR, 1)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOLASSES).get())
         .requires(Items.BEETROOT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "beetsugar_from_mortar"));
      StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FLOUR).get(), 1)
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW).get(), 1)
         .requires(TsukiItemTags.GRAIN_WHEAT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "flour_from_mortar"));
      StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FLOUR_BUCKWHEAT).get(), 1)
         .requires(TsukiItemTags.GRAIN_BUCKWHEAT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "flour_buckwheat_from_mortar"));
      StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FLOUR_RICE).get(), 1)
         .requires(TsukiItemTags.RICE_RICE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "flour_rice_from_mortar"));
   }

   private void registerFarmerDelightRecipes(RecipeOutput consumer) {
      this.whenModLoaded(
            StoneMortarRecipeBuilder.mortar(ModItems.RICE.get())
               .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW).get())
               .requires(ModItems.RICE_PANICLE.get()),
            consumer,
            "farmersdelight",
            "farmer_rice_mortar_from_sakura"
         );
      this.whenModLoaded(
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CANVAS.get())
               .pattern("##")
               .pattern("##")
               .define('#', TsukiItemTags.STRAW)
               .unlockedBy("has_straw", has(TsukiItemTags.STRAW)),
            consumer,
            "farmersdelight",
            "canvas_from_sakura"
         );
      this.whenModLoaded(
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TATAMI.get(), 2)
               .pattern("S#")
               .pattern("#S")
               .define('#', TsukiItemTags.STRAW)
               .define('S', ModItems.CANVAS.get())
               .unlockedBy("has_straw", has(TsukiItemTags.STRAW)),
            consumer,
            "farmersdelight",
            "farmer_tatami_from_sakura"
         );
      this.whenModLoaded(
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROPE.get(), 3)
               .pattern("s")
               .pattern("s")
               .pattern("s")
               .define('s', TsukiItemTags.STRAW)
               .unlockedBy("has_straw", has(TsukiItemTags.STRAW)),
            consumer,
            "farmersdelight",
            "rope_from_sakura"
         );
      this.whenModLoaded(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ORGANIC_COMPOST.get(), 1)
               .requires(Items.DIRT)
               .requires(Items.ROTTEN_FLESH)
               .requires(Items.ROTTEN_FLESH)
               .requires(TsukiItemTags.STRAW)
               .requires(TsukiItemTags.STRAW)
               .requires(Items.BONE_MEAL)
               .requires(Items.BONE_MEAL)
               .requires(Items.BONE_MEAL)
               .requires(Items.BONE_MEAL)
               .unlockedBy("has_rotten_flesh", TriggerInstance.hasItems(Items.ROTTEN_FLESH))
               .unlockedBy("has_straw", has(TsukiItemTags.STRAW)),
            consumer,
            "farmersdelight",
            "organic_compost_rotten_flesh_from_sakura"
         );
      this.whenModLoaded(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ORGANIC_COMPOST.get(), 1)
               .requires(Items.DIRT)
               .requires(TsukiItemTags.STRAW)
               .requires(TsukiItemTags.STRAW)
               .requires(Items.BONE_MEAL)
               .requires(Items.BONE_MEAL)
               .requires(ModItems.TREE_BARK.get())
               .requires(ModItems.TREE_BARK.get())
               .requires(ModItems.TREE_BARK.get())
               .requires(ModItems.TREE_BARK.get())
               .unlockedBy("has_tree_bark", TriggerInstance.hasItems(ModItems.TREE_BARK.get()))
               .unlockedBy("has_straw", has(TsukiItemTags.STRAW)),
            consumer,
            "farmersdelight",
            "organic_compost_bark_from_sakura"
         );
   }

   private void registerCookingRecipe(RecipeOutput consumer) {
      CookingPotRecipeBuilder.cooking(FluidIngredient.EMPTY, FoodRegistry.CUISINES.get(TsukiCuisineSet.BEEF_STICK).get(), 2)
         .requires(TsukiItemTags.RAW_BEEF)
         .requires(TsukiItemTags.BAMBOO)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "beef_stick_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.EMPTY, FoodRegistry.CUISINES.get(TsukiCuisineSet.CHICKEN_STICK).get(), 2)
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(TsukiItemTags.BAMBOO)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chicken_stick_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.EMPTY, FoodRegistry.CUISINES.get(TsukiCuisineSet.PORK_STICK).get(), 2)
         .requires(TsukiItemTags.RAW_PORK)
         .requires(TsukiItemTags.BAMBOO)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pork_stick_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.FOODSET.get(TsukiFoodSet.TOFU).get(), 2
         )
         .requires(TsukiItemTags.CROPS_SOYBEAN)
         .requires(TsukiItemTags.SALT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tofu_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.FOODSET.get(TsukiFoodSet.MAYONAISE).get(), 2
         )
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.VINEGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mayo_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiNormalItemSet.CURRY_SAUCE.getItem().get(), 2)
         .requires(TsukiNormalItemSet.CURRY_POWDER.getItem().get())
         .requires(CompoundIngredient.of(new Ingredient[]{Ingredient.of(TsukiItemTags.VEGETABLES), Ingredient.of(TsukiItemTags.FOODS_RAW_MEAT)}))
         .requires(CompoundIngredient.of(new Ingredient[]{Ingredient.of(TsukiItemTags.VEGETABLES), Ingredient.of(TsukiItemTags.FOODS_RAW_MEAT)}))
         .requires(TsukiItemTags.FLOUR)
         .requires(TsukiItemTags.DASHI)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "curry_sauce_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiFoodSet.BOILED_BONITO.getItem().get())
         .requires(TsukiFoodSet.MACHINED_BONITO.getItem().get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bonito_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiNormalItemSet.NOODLE_SOUP.getItem().get(), 2)
         .requires(TsukiNormalItemSet.KAESHI.getItem().get())
         .requires(TsukiNormalItemSet.DASHI.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "noodle_soup_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN).get(), 1
         )
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_BEEF.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.RAW_BEEF)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_beef_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_EGG.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_eggs_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_FRIEDTOFU.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.TOFU_FRIED.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_friedtofu_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_KATSU.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.KATSU.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_katsu_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_TEMPURA.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.TEMPURA.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_tempura_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_FRIEDCHICKEN.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.FRIED_CHICKEN.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_chicken_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_CROQUETTE.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.CROQUETTE.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_croquette_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_LARGE.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.FOODS_RAW_MEAT)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_large_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON).get(), 1
         )
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_BEEF.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.RAW_BEEF)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_beef_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_EGG.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_eggs_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_FRIEDTOFU.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.TOFU_FRIED.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_friedtofu_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_KATSU.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.KATSU.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_katsu_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_TEMPURA.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.TEMPURA.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_tempura_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_FRIEDCHICKEN.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.FRIED_CHICKEN.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_chicken_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_CROQUETTE.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.CROQUETTE.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_croquette_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_LARGE.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.FOODS_RAW_MEAT)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_large_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA).get(), 1
         )
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_BEEF.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.RAW_BEEF)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_beef_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_EGG.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_eggs_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_FRIEDTOFU.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.TOFU_FRIED.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_friedtofu_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_KATSU.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.KATSU.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_katsu_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_TEMPURA.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.TEMPURA.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_tempura_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_FRIEDCHICKEN.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.FRIED_CHICKEN.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_chicken_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_CROQUETTE.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiFoodSet.CROQUETTE.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_croquette_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_LARGE.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
         .requires(TsukiItemTags.FOODS_RAW_MEAT)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_large_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), TsukiCuisineSet.YAKI_UDON.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.KAESHI.getItem().get())
         .requires(TsukiNormalItemSet.WORCESTER_SAUCE.getItem().get())
         .requires(TsukiItemTags.VEGETABLES)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yaki_udon_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiNormalItemSet.WORCESTER_SAUCE.getItem().get(), 2
         )
         .requires(TsukiItemTags.SUGAR)
         .requires(TsukiItemTags.FRUITS)
         .requires(TsukiItemTags.SOYSAUCE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "worcester_sauce_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.TOFU_FRIED).get(), 2
         )
         .requires(TsukiItemTags.TOFU)
         .requires(TsukiItemTags.FLOUR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tofu_fried_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.KAMABOKO).get(), 2
         )
         .requires(TsukiItemTags.SALT)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kamaboko_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.HYOROGAN).get(), 2
         )
         .requires(TsukiItemTags.FLOUR)
         .requires(TsukiNormalItemSet.RICE.getItem().get())
         .requires(Ingredient.fromValues(Stream.of(new TagValue(TsukiItemTags.CROPS_TARO), new TagValue(net.neoforged.neoforge.common.Tags.Items.CROPS_POTATO))))
         .requires(TsukiItemTags.SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "hyorogan_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.SATSUMAAGE).get(), 2
         )
         .requires(TsukiItemTags.SALT)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "satsumaage_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.KATSU).get(), 2)
         .requires(TsukiItemTags.RAW_PORK)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BREADCRUMBS).get())
         .requires(TsukiItemTags.EGGS)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pork_katsu_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.CHICKEN_NANBAN).get(), 2
         )
         .requires(TsukiFoodSet.FRIED_CHICKEN.getItem().get())
         .requires(TsukiNormalItemSet.KAESHI.getItem().get())
         .requires(TsukiFoodSet.MAYONAISE.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chicken_nanban_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.TOFU_NANBAN).get(), 2
         )
         .requires(TsukiFoodSet.TOFU_FRIED.getItem().get())
         .requires(TsukiNormalItemSet.KAESHI.getItem().get())
         .requires(TsukiFoodSet.MAYONAISE.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tofu_nanban_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.FRIED_CHICKEN).get(), 2
         )
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(
            Ingredient.fromValues(
               Stream.of(new ItemValue(new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.BREADCRUMBS).get())), new TagValue(TsukiItemTags.FLOUR))
            )
         )
         .requires(TsukiItemTags.EGGS)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fried_chicken_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.CROQUETTE).get(), 2
         )
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.MASHED_POTATO).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BREADCRUMBS).get())
         .requires(TsukiItemTags.MILK)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "croquette_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get(), 4
         )
         .requires(TsukiItemTags.SUGAR)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kaeshi_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.FISHCAKE).get(), 4
         )
         .requires(TsukiItemTags.SALT)
         .requires(TsukiItemTags.EGGS)
         .requires(Ingredient.fromValues(Stream.of(new TagValue(TsukiItemTags.CROPS_TARO), new TagValue(net.neoforged.neoforge.common.Tags.Items.CROPS_POTATO))))
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SURIMI).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "hanpen_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.SOUP_REDBEAN).get(), 2
         )
         .requires(TsukiItemTags.CROPS_REDBEAN)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.MOCHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soup_redbean_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.CABBAGE_ROLL).get()
         )
         .requires(TsukiItemTags.SALAD_INGREDIENTS_CABBAGE)
         .requires(
            Ingredient.fromValues(
               Stream.of(
                  new TagValue(TsukiItemTags.RAW_CHICKEN),
                  new TagValue(TsukiItemTags.RAW_PORK),
                  new TagValue(TsukiItemTags.RAW_BEEF),
                  new TagValue(TsukiItemTags.RAW_MUTTON),
                  new TagValue(TsukiItemTags.FISHES)
               )
            )
         )
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cabbage_roll_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.REDBEAN_PASTE).get(), 2
         )
         .requires(TsukiItemTags.CROPS_REDBEAN)
         .requires(TsukiItemTags.CROPS_REDBEAN)
         .requires(TsukiItemTags.SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "redbean_paste_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO_SAUCE).get(), 2
         )
         .requires(TsukiItemTags.CROPS_TOMATO)
         .requires(TsukiItemTags.CROPS_TOMATO)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tomato_sauce_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get(), 2
         )
         .requires(TsukiItemTags.DOUGH_RICE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "dango_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.DANANKO).get()
         )
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get())
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.REDBEAN_PASTE).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "dananko_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.DANMITARASHI).get()
         )
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get())
         .requires(TsukiItemTags.SUGAR)
         .requires(TsukiItemTags.SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "danmitarashi_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.DANSANSYOKU).get()
         )
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get())
         .requires(BlockRegistry.SAKURA_LEAVES.get())
         .requires(Items.SHORT_GRASS)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "dansansyoku_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.DAIFUKU).get(), 2
         )
         .requires(TsukiItemTags.DOUGH_RICE)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.REDBEAN_PASTE).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "daifuku_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.KUSA_DAIFUKU).get(), 2
         )
         .requires(TsukiItemTags.DOUGH_RICE)
         .requires(Items.SHORT_GRASS)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.REDBEAN_PASTE).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kusa_daifuku_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.BROWN_RICE_COOKED).get()
         )
         .requires(TsukiItemTags.RICE_BROWN)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "brown_rice_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_REDBEAN).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.CROPS_REDBEAN)
         .requires(TsukiItemTags.SUGAR)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_redbean_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), TsukiFoodSet.OHAGI.getItem().get())
         .requires(TsukiFoodSet.MOCHI.getItem().get())
         .requires(TsukiFoodSet.REDBEAN_PASTE.getItem().get())
         .requires(TsukiItemTags.SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ohagi_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_NATTO).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.NATTO)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_natto_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_NATTO_EGG).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.NATTO)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_natto_egg_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BAMBOO).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(BlockRegistry.BAMBOOSHOOT.get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_bamboo_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_MUSHROOM).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.MUSHROOMS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_mushrooms_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BEEF).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_BEEF)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_beef_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_PORK).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_PORK)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_pork_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_FISH).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_FISHES)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_fish_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_EGG).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_eggs_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BEEF_EGG).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_BEEF)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_beef_eggs_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_PORK_EGG).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_PORK)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_pork_eggs_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_KATSU).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.KATSU).get())
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_katsu_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_OYAKO).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_oyako_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_OYAKO_FISH).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.RAW_FISHES)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_oyako_fish_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.OMURICE).get())
         .requires(TsukiItemTags.RICE_RICE)
         .requires(
            Ingredient.fromValues(
               Stream.of(
                  new TagValue(TsukiItemTags.RAW_CHICKEN),
                  new TagValue(TsukiItemTags.RAW_PORK),
                  new TagValue(TsukiItemTags.RAW_BEEF),
                  new TagValue(TsukiItemTags.RAW_MUTTON),
                  new TagValue(TsukiItemTags.FISHES)
               )
            )
         )
         .requires(TsukiItemTags.TOMATOSAUCE)
         .requires(TsukiItemTags.EGGS)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "omurice_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.TEMPURA).get())
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.TEMPURA_BATTER).get())
         .requires(TsukiItemTags.SHRIMP)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tempura_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.FRIES).get(), 2)
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_POTATO)
         .requires(TsukiItemTags.SALT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fries_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.MASHED_POTATO).get(), 2
         )
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_POTATO)
         .requires(TsukiItemTags.SALT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mashed_potato_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.FISH_BAKE_SALT).get()
         )
         .requires(TsukiItemTags.SALT)
         .requires(TsukiItemTags.RAW_FISHES)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fish_bake_salt_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.FISH_BAKE).get())
         .requires(TsukiItemTags.RAW_FISHES)
         .requires(TsukiItemTags.SOYSAUCE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fish_bake_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.TAMAGOYAKI).get(), 2
         )
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.SUGAR)
         .requires(TsukiItemTags.DASHI)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tamagoyaki_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.OSUIMONO).get(), 2
         )
         .requires(Items.DRIED_KELP)
         .requires(TsukiItemTags.SOYSAUCE)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "osuimono_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.SOUP_MISO).get(), 2
         )
         .requires(TsukiItemTags.MISO)
         .requires(TsukiItemTags.TOFU)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soup_miso_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIKUJAGA).get(), 2
         )
         .requires(Ingredient.fromValues(Stream.of(new TagValue(TsukiItemTags.RAW_PORK), new TagValue(TsukiItemTags.RAW_BEEF))))
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_CARROT)
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_POTATO)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nikujaga_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIMONO_PUMPKIN).get(), 2
         )
         .requires(TsukiItemTags.CROPS_PUMPKIN)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nimono_pumpkin_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIMONO_RADISH).get(), 2
         )
         .requires(TsukiItemTags.CROPS_RADISH)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nimono_radish_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.IMOTAKI).get(), 2
         )
         .requires(TsukiItemTags.CROPS_TARO)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "imotaki_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.CHIKUZENNI).get(), 2
         )
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(TsukiItemTags.MUSHROOMS)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chikuzenni_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NOPPEI_JIRU).get(), 2
         )
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(TsukiItemTags.CROPS_TARO)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.SOYSAUCE)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "noppei_jiru_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIMONO_FISH).get(), 2
         )
         .requires(TsukiItemTags.RAW_FISHES)
         .requires(TsukiItemTags.MISO)
         .requires(TsukiItemTags.SALT)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nimono_fish_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIKUJAGA).get(), 2
         )
         .requires(Ingredient.fromValues(Stream.of(new TagValue(TsukiItemTags.RAW_PORK), new TagValue(TsukiItemTags.RAW_BEEF))))
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_CARROT)
         .requires(net.neoforged.neoforge.common.Tags.Items.CROPS_POTATO)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nikujaga_cooking_kaeshi"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIMONO_PUMPKIN).get(), 2
         )
         .requires(TsukiItemTags.CROPS_PUMPKIN)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nimono_pumpkin_cooking_kaeshi"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NIMONO_RADISH).get(), 2
         )
         .requires(TsukiItemTags.CROPS_RADISH)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nimono_radish_cooking_kaeshi"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.IMOTAKI).get(), 2
         )
         .requires(TsukiItemTags.CROPS_TARO)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "imotaki_cooking_kaeshi"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.CHIKUZENNI).get(), 2
         )
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(TsukiItemTags.MUSHROOMS)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chikuzenni_cooking_kaeshi"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.NOPPEI_JIRU).get(), 2
         )
         .requires(TsukiItemTags.RAW_CHICKEN)
         .requires(TsukiItemTags.CROPS_TARO)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KAESHI).get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "noppei_jiru_cooking_kaeshi"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.FUROFUKI_DAIKON).get(), 2
         )
         .requires(TsukiItemTags.CROPS_RADISH)
         .requires(TsukiItemTags.MISO)
         .requires(TsukiItemTags.SALT)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "furofuki_daikon_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 500), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DASHI).get(), 1
         )
         .requires(Ingredient.of(TsukiFoodSet.BONITO_SHAVING.getItem().get(), Items.DRIED_KELP))
         .requires(Ingredient.of(TsukiFoodSet.BONITO_SHAVING.getItem().get(), Items.DRIED_KELP))
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "dashi_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.YAKINIKU).get())
         .requires(
            Ingredient.fromValues(
               Stream.of(new TagValue(TsukiItemTags.RAW_PORK), new TagValue(TsukiItemTags.RAW_BEEF), new TagValue(TsukiItemTags.RAW_MUTTON))
            )
         )
         .requires(TsukiItemTags.SOYSAUCE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yakiniku_cooking"));
      CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_FRIED).get()
         )
         .requires(TsukiItemTags.RICE_RICE)
         .requires(TsukiItemTags.EGGS)
         .requires(TsukiItemTags.VEGETABLES)
         .requires(TsukiItemTags.SALT)
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_fried_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.RAMEN_CURRY.getItem().get(), 1)
         .requires(TsukiNormalItemSet.RAMEN_RAW.getItem().get())
         .requires(TsukiNormalItemSet.DASHI.getItem().get())
         .requires(CompoundIngredient.of(new Ingredient[]{Ingredient.of(TsukiItemTags.VEGETABLES), Ingredient.of(TsukiItemTags.FOODS_RAW_MEAT)}))
         .requires(TsukiNormalItemSet.CURRY_POWDER.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_curry_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.UDON_CURRY.getItem().get(), 1)
         .requires(TsukiNormalItemSet.UDON_RAW.getItem().get())
         .requires(TsukiNormalItemSet.DASHI.getItem().get())
         .requires(CompoundIngredient.of(new Ingredient[]{Ingredient.of(TsukiItemTags.VEGETABLES), Ingredient.of(TsukiItemTags.FOODS_RAW_MEAT)}))
         .requires(TsukiNormalItemSet.CURRY_POWDER.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_curry_cooking"));
      CookingPotRecipeBuilder.cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), TsukiCuisineSet.SOBA_CURRY.getItem().get(), 1)
         .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
         .requires(TsukiNormalItemSet.DASHI.getItem().get())
         .requires(CompoundIngredient.of(new Ingredient[]{Ingredient.of(TsukiItemTags.VEGETABLES), Ingredient.of(TsukiItemTags.FOODS_RAW_MEAT)}))
         .requires(TsukiNormalItemSet.CURRY_POWDER.getItem().get())
         .container(Items.BOWL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_curry_cooking"));
   }

   private void registerFermenterRecipe(RecipeOutput consumer) {
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 100),
            FoodRegistry.FOODSET.get(TsukiFoodSet.PICKELD_RADISH).get(),
            2,
            FluidStack.EMPTY,
            0.0F,
            400
         )
         .requires(TsukiItemTags.CROPS_RADISH)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.NUKA).get())
         .requires(TsukiItemTags.SALT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nukazuke_radish"));
      FermenterRecipeBuilder.fermenting(FluidIngredient.EMPTY, FoodRegistry.FOODSET.get(TsukiFoodSet.NATTO).get(), 2, FluidStack.EMPTY, 0.0F, 600)
         .requires(TsukiItemTags.CROPS_SOYBEAN)
         .requires(TsukiItemTags.STRAW)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "natto_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 100),
            FoodRegistry.FOODSET.get(TsukiFoodSet.PICKELD_EGGPLANT).get(),
            2,
            FluidStack.EMPTY,
            0.0F,
            400
         )
         .requires(TsukiItemTags.CROPS_EGGPLANT)
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.NUKA).get())
         .requires(TsukiItemTags.SALT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "nukazuke_eggplant"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 500),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KOUJI).get(),
            2,
            FluidStack.EMPTY
         )
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(TsukiItemTags.SALT)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kouji_fermenting"));
      FermenterRecipeBuilder.fermenting(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000), new FluidStack((Fluid)FluidRegistry.DOBUROKU.get(), 500))
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(TsukiItemTags.KOUJI)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "doburoku_fermenting"));
      FermenterRecipeBuilder.fermenting(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), new FluidStack((Fluid)FluidRegistry.BEER.get(), 100))
         .requires(TsukiItemTags.GRAIN)
         .requires(TsukiItemTags.BROWN_MUSHROOMS)
         .requires(TsukiItemTags.SUGAR_SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "basic_beer_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), new FluidStack((Fluid)FluidRegistry.BEER.get(), 200), 0.0F, 400
         )
         .requires(TsukiItemTags.GRAIN)
         .requires(TsukiItemTags.GRAIN)
         .requires(TsukiItemTags.YEAST)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "beer_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.YEAST).get(),
            4,
            FluidStack.EMPTY,
            0.0F,
            400
         )
         .requires(TsukiItemTags.BROWN_MUSHROOMS)
         .requires(TsukiItemTags.SUGAR_SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yeast_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 100),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.YEAST).get(),
            4,
            FluidStack.EMPTY,
            0.0F,
            200
         )
         .requires(TsukiItemTags.YEAST)
         .requires(TsukiItemTags.SUGAR_SUGAR)
         .requires(TsukiItemTags.SUGAR_SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yeast_multiply"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.BREWERS_ALCOHOL, 500),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get(),
            8,
            FluidStack.EMPTY
         )
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN_KASU).get(), 1)
         .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
         .requires(TsukiItemTags.KOUJI)
         .requires(TsukiItemTags.SUGAR)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mirin_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.BREWERS_ALCOHOL, 500),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VINEGAR).get(),
            8,
            FluidStack.EMPTY
         )
         .requires(TsukiItemTags.KOUJI)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "vinger_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.DOBUROKU.get(), 500),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKE_KASU).get(),
            2,
            new FluidStack((Fluid)FluidRegistry.SAKE.get(), 250),
            10.0F,
            500
         )
         .requires(TsukiItemTags.DUST_CHARCOAL)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sake_charcoal_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.DOBUROKU.get(), 500), new FluidStack((Fluid)FluidRegistry.SAKE.get(), 100), 10.0F, 1000
         )
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sake_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MISO).get(),
            4,
            FluidStack.EMPTY
         )
         .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SOYSAUCE).get(), 4)
         .requires(TsukiItemTags.CROPS_SOYBEAN)
         .requires(TsukiItemTags.CROPS_SOYBEAN)
         .requires(TsukiItemTags.KOUJI)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "miso_fermenting"));
      FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RED_VINEGAR).get(),
            8,
            FluidStack.EMPTY
         )
         .requires(TsukiNormalItemSet.SAKE_KASU.getItem().get())
         .requires(TsukiNormalItemSet.SAKE_KASU.getItem().get())
         .requires(TsukiItemTags.KOUJI)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "red_vinger_fermenting"));
   }

   private void registerDistillerRecipe(RecipeOutput consumer) {
      DistillerRecipeBuilder.distillation(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.SAKE.get(), 1000), new FluidStack((Fluid)FluidRegistry.SHOUCHU.get(), 500)
         )
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shouchu_from_sake_distillation"));
      DistillerRecipeBuilder.distillation(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.BEER.get(), 1000), new FluidStack((Fluid)FluidRegistry.WHISKEY.get(), 500)
         )
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "whiskey_from_beer_distillation"));
      DistillerRecipeBuilder.distillation(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 500), new FluidStack((Fluid)FluidRegistry.RUM.get(), 100))
         .requires(Items.SUGAR_CANE)
         .requires(Items.SUGAR_CANE)
         .requires(TsukiItemTags.YEAST)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rum_cane_distillation"));
      DistillerRecipeBuilder.distillation(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 500), new FluidStack((Fluid)FluidRegistry.RUM.get(), 100))
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOLASSES).get())
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOLASSES).get())
         .requires(TsukiItemTags.YEAST)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rum_molasses_distillation"));
      DistillerRecipeBuilder.distillation(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 500), new FluidStack((Fluid)FluidRegistry.SHOUCHU.get(), 100))
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKE_KASU).get())
         .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKE_KASU).get())
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shouchu_from_sakekasu_distillation"));
   }

   private void registerChoppingRecipes(RecipeOutput consumer) {
      ChoppingBoardRecipeBuilder.chop(FoodRegistry.FOODSET.get(TsukiFoodSet.MACHINED_FISH).get())
         .requires(
            Ingredient.fromValues(
               Stream.of(new ItemValue(new ItemStack(Items.COD)), new ItemValue(new ItemStack(Items.SALMON)), new ItemValue(new ItemStack(Items.TROPICAL_FISH)))
            )
         )
         .requiresTool(TsukiItemTags.TOOLS_KNIVES_FISH)
         .addByproduce(FoodRegistry.FOODSET.get(TsukiFoodSet.MACHINED_FISH).get())
         .addByproduceWithChance(Items.BONE_MEAL, 0.5F)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "machined_fish_chopping"));
      ChoppingBoardRecipeBuilder.chop(FoodRegistry.FOODSET.get(TsukiFoodSet.MACHINED_BONITO).get())
         .requires(TsukiFoodSet.BONITO.getItem().get())
         .requiresTool(TsukiItemTags.TOOLS_KNIVES_FISH)
         .addByproduce(TsukiFoodSet.MACHINED_BONITO.getItem().get())
         .addByproduceWithChance(Items.BONE_MEAL, 0.5F)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "machined_bonito_chopping"));
      ChoppingBoardRecipeBuilder.chop(FoodRegistry.FOODSET.get(TsukiFoodSet.SLICED_CABBAGE).get())
         .requires(TsukiItemTags.CROPS_CABBAGE)
         .requiresTool(TsukiItemTags.TOOLS_KNIVES_FISH)
         .addByproduce(FoodRegistry.FOODSET.get(TsukiFoodSet.SLICED_CABBAGE).get())
         .addByproduceWithChance(FoodRegistry.FOODSET.get(TsukiFoodSet.SLICED_CABBAGE).get(), 0.5F)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sliced_cabbage_chopping"));
      ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.SOBA_RAW.getItem().get(), 2, 1.0F, 4)
         .requires(TsukiNormalItemSet.SOBA_BLOCK.getItem().get())
         .requiresTool(TsukiItemTags.TOOLS_KNIVES_NOODLE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soba_chopping"));
      ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.RAMEN_RAW.getItem().get(), 2, 1.0F, 4)
         .requires(TsukiNormalItemSet.RAMEN_BLOCK.getItem().get())
         .requiresTool(TsukiItemTags.TOOLS_KNIVES_NOODLE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ramen_chopping"));
      ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.UDON_RAW.getItem().get(), 2, 1.0F, 4)
         .requires(TsukiNormalItemSet.UDON_BLOCK.getItem().get())
         .requiresTool(TsukiItemTags.TOOLS_KNIVES_NOODLE)
         .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "udon_chopping"));
   }

   private void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, RecipeOutput consumer) {
      String namePrefix = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, name).toString();
      SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200)
         .unlockedBy("has_ingredient", has(ingredient))
         .group(Tsuki.MODID)
         .save(consumer);
      SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600)
         .unlockedBy("has_ingredient", has(ingredient))
         .group(Tsuki.MODID)
         .save(consumer, namePrefix + "_from_campfire_cooking");
      SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
         .unlockedBy("has_ingredient", has(ingredient))
         .group(Tsuki.MODID)
         .save(consumer, namePrefix + "_from_smoking");
   }

   public ShapedRecipeBuilder makeLumberToPlank(Supplier<? extends Block> blockOut, Ingredient ingreIn) {
      return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get()).pattern("##").pattern("##").define('#', ingreIn);
   }

   public ShapelessRecipeBuilder makeLumber(Supplier<? extends Item> ingotOut, Ingredient ingreIn) {
      return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ingotOut.get(), 8).requires(ingreIn);
   }

   public ShapelessRecipeBuilder makeItemToBucket(Supplier<? extends Item> ingotOut, Ingredient ingreIn) {
      return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingotOut.get())
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(ingreIn)
         .requires(Items.BUCKET);
   }

   public void whenModLoaded(ShapedRecipeBuilder recipe, RecipeOutput consumer, String modid, String path) {
      recipe.save(consumer.withConditions(new ModLoadedCondition(modid)), ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, path));
   }

   public void whenModLoaded(ShapelessRecipeBuilder recipe, RecipeOutput consumer, String modid, String path) {
      recipe.save(consumer.withConditions(new ModLoadedCondition(modid)), ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, path));
   }

   public void whenModLoaded(StoneMortarRecipeBuilder recipe, RecipeOutput consumer, String modid, String path) {
      recipe.save(consumer.withConditions(new ModLoadedCondition(modid)), ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, path));
   }

   public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
      return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.get(), 1)
         .pattern("###")
         .pattern("###")
         .pattern("###")
         .define('#', ingredient.get())
         .group(Tsuki.MODID)
         .unlockedBy("has_ingredient", has(ingredient.get()));
   }

   public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
      return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result.get(), 9)
         .requires(ingredient.get())
         .group(Tsuki.MODID)
         .unlockedBy("has_ingredient", has(ingredient.get()));
   }
}


