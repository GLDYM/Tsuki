package cn.mcmod.tsuki.data;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.data.builder.ChoppingBoardRecipeBuilder;
import cn.mcmod.tsuki.data.builder.CookingPotRecipeBuilder;
import cn.mcmod.tsuki.data.builder.DistillerRecipeBuilder;
import cn.mcmod.tsuki.data.builder.FermenterRecipeBuilder;
import cn.mcmod.tsuki.data.builder.StoneMortarRecipeBuilder;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.BucketItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiAlcoholSet;
import cn.mcmod.tsuki.init.item.enums.TsukiCocktailSet;
import cn.mcmod.tsuki.init.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.init.item.enums.TsukiTeaSet;
import cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet;
import cn.mcmod.tsuki.tag.TsukiFluidTags;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import cn.mcmod.mmlib.data.AbstractRecipeProvider;
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
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
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
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.fluids.FluidStack;

import vectorwing.farmersdelight.common.registry.ModItems;

public class TsukiRecipeProvider extends AbstractRecipeProvider {
    public TsukiRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(packOutput, Tsuki.MODID, provider);
    }

    protected void buildRecipes(RecipeOutput consumer) {
        this.registerSmithingRecipe(consumer);
        this.registerCraftingRecipe(consumer);
        this.registerDecorativeBlockRecipes(consumer);
        this.registerMortarRecipe(consumer);
        this.registerCookingRecipe(consumer);
        this.registerFermenterRecipe(consumer);
        this.registerDistillerRecipe(consumer);
        this.registerDrinkRecipes(consumer);
        this.registerChoppingRecipes(consumer);
    }

    private void registerSmithingRecipe(RecipeOutput consumer) {
        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.PAPER),
            Ingredient.of(ArmorToolRegistry.SAKURA_PICKAXE.get()),
            Ingredient.of(Items.NETHERITE_INGOT),
            RecipeCategory.TOOLS,
            ArmorToolRegistry.MYTHIC_PICKAXE.get()
        )
        .unlocks("has_sakura_pickaxe", has(ArmorToolRegistry.SAKURA_PICKAXE.get()))
        .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mythic_pickaxe_smithing"));
    }

    private void registerCraftingRecipe(RecipeOutput consumer) {
        this.foodSmeltingRecipes(
            "boiled_bonito_to_smoked_bonito",
            TsukiFoodSet.BOILED_BONITO.getItem().get(),
            TsukiFoodSet.SMOKED_BONITO.getItem().get(),
            0.5F,
            consumer
        );
        this.foodSmeltingRecipes(
            "smoked_bonito_to_dried_bonito",
            TsukiFoodSet.SMOKED_BONITO.getItem().get(),
            TsukiFoodSet.DRIED_BONITO.getItem().get(),
            0.5F,
            consumer
        );
        this.foodSmeltingRecipes(
            "brown_rice_cooked_to_dried_brown_rice",
            TsukiCuisineSet.BROWN_RICE_COOKED.getItem().get(),
            TsukiFoodSet.DRIED_BROWN_RICE.getItem().get(),
            0.1F,
            consumer
        );
        this.foodSmeltingRecipes(
            "rice_cooked_to_dried_rice",
            TsukiCuisineSet.RICE_COOKED.getItem().get(),
            TsukiFoodSet.DRIED_RICE.getItem().get(),
            0.1F,
            consumer
        );
        this.foodSmeltingRecipes(
            "dried_brown_rice_to_fried_brown_rice",
            TsukiFoodSet.DRIED_BROWN_RICE.getItem().get(),
            TsukiFoodSet.FRIED_BROWN_RICE.getItem().get(),
            0.1F,
            consumer
        );
        this.foodSmeltingRecipes(
            "raw_taiyaki_to_taiyaki",
            TsukiFoodSet.RAW_TAIYAKI.getItem().get(),
            TsukiFoodSet.TAIYAKI.getItem().get(),
            0.1F,
            consumer
        );
        this.makeSlab(consumer, BlockRegistry.TATAMI_SLAB, BlockRegistry.TATAMI);
        this.makeSlab(consumer, BlockRegistry.TATAMI_SLAB_WAXED, BlockRegistry.TATAMI_WAXED);
        this.makeSlab(consumer, BlockRegistry.TATAMI_SLAB_SUNBURNT, BlockRegistry.TATAMI_SUNBURNT);
        this.makeSlab(consumer, BlockRegistry.SAKURA_PLANK_SLAB, BlockRegistry.SAKURA_PLANK);
        this.makeSlab(consumer, BlockRegistry.MAPLE_PLANK_SLAB, BlockRegistry.MAPLE_PLANK);
        this.makeSlab(consumer, BlockRegistry.UME_PLANK_SLAB, BlockRegistry.UME_PLANK);
        this.makeSlab(consumer, BlockRegistry.BAMBOO_PLANK_SLAB, BlockRegistry.BAMBOO_PLANK);
        this.makeStair(consumer, BlockRegistry.TATAMI_STAIRS, BlockRegistry.TATAMI);
        this.makeStair(consumer, BlockRegistry.TATAMI_STAIRS_WAXED, BlockRegistry.TATAMI_WAXED);
        this.makeStair(consumer, BlockRegistry.TATAMI_STAIRS_SUNBURNT, BlockRegistry.TATAMI_SUNBURNT);
        this.makeStair(consumer, BlockRegistry.SAKURA_PLANK_STAIRS, BlockRegistry.SAKURA_PLANK);
        this.makeStair(consumer, BlockRegistry.MAPLE_PLANK_STAIRS, BlockRegistry.MAPLE_PLANK);
        this.makeStair(consumer, BlockRegistry.UME_PLANK_STAIRS, BlockRegistry.UME_PLANK);
        this.makeStair(consumer, BlockRegistry.BAMBOO_PLANK_STAIRS, BlockRegistry.BAMBOO_PLANK);
        this.makeSlab(consumer, BlockRegistry.BAMBOO_BLOCK_SLAB, BlockRegistry.BAMBOO_BLOCK);
        this.makeSlab(consumer, BlockRegistry.BAMBOO_BLOCK_SUNBURNT_SLAB, BlockRegistry.BAMBOO_BLOCK_SUNBURNT);
        this.makeSlab(consumer, BlockRegistry.STRAW_BLOCK_SLAB, BlockRegistry.STRAW_BLOCK);
        this.makeStair(consumer, BlockRegistry.BAMBOO_BLOCK_STAIRS, BlockRegistry.BAMBOO_BLOCK);
        this.makeStair(consumer, BlockRegistry.BAMBOO_BLOCK_SUNBURNT_STAIRS, BlockRegistry.BAMBOO_BLOCK_SUNBURNT);
        this.makeStair(consumer, BlockRegistry.STRAW_BLOCK_STAIRS, BlockRegistry.STRAW_BLOCK);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SAKURA_WOOD.get(), 3)
            .pattern("##")
            .pattern("##")
            .define('#', BlockItemRegistry.SAKURA_LOG.get())
            .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_WOOD.get(), 4)
            .pattern("##")
            .pattern("##")
            .define('#', BlockItemRegistry.MAPLE_LOG.get())
            .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.UME_WOOD.get(), 4)
            .pattern("##")
            .pattern("##")
            .define('#', BlockItemRegistry.UME_LOG.get())
            .unlockedBy("has_item", has(BlockItemRegistry.UME_LOG.get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TATAMI_WAXED.get(), 1)
            .requires(BlockRegistry.TATAMI.get())
            .requires(Items.HONEYCOMB)
            .unlockedBy("has_tatami", has(BlockRegistry.TATAMI.get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.HONEY_BOTTLE)
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MAPLE_SYRUP).get())
            .requires(Items.GLASS_BOTTLE)
            .unlockedBy("has_maple_syrup", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MAPLE_SYRUP).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "honey_bottle_from_maple_syrup"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.UMEBOSHI).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.UME).get())
            .requires(TsukiItemTags.SALT)
            .unlockedBy("has_ume", has(FoodRegistry.FOODSET.get(TsukiFoodSet.UME).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "umeboshi"));
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
        whenModLoaded(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.SAKURA_GUIDE.get())
                .requires(Items.BOOK)
                .requires(TsukiNormalItemSet.BAMBOO.getItem().get())
                .unlockedBy("has_book", has(Items.BOOK)),
            consumer,
            "guideme",
            "sakura_guide_from_bamboo_and_book");
        whenModLoaded(
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.SAKURA_GUIDE.get())
                .requires(Items.BOOK)
                .requires(BlockItemRegistry.SAKURA_SAPLING.get())
                .unlockedBy("has_sakura_sapling", has(BlockItemRegistry.SAKURA_SAPLING.get())),
            consumer,
            "guideme",
            "sakura_guide_from_sakura_sapling_and_book");
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
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.TATAMI.get(), 6)
            .pattern("LLL")
            .pattern("L#L")
            .pattern("LLL")
            .define('#', TsukiItemTags.LUMBER)
            .define('L', TsukiItemTags.STRAW)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.FUTON.get(), 1)
            .pattern("WWW")
            .pattern("SSS")
            .define('W', Items.WHITE_WOOL)
            .define('S', TsukiItemTags.STRAW)
            .unlockedBy("has_straw", has(TsukiItemTags.STRAW))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "futon"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TORCH, 4)
            .pattern("C")
            .pattern("#")
            .define('C', ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get())
            .define('#', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_item", has(Tags.Items.RODS_WOODEN))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "torchs_from_charcoal"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.STICK, 4)
            .pattern("#")
            .pattern("#")
            .define('#', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sticks_from_lumbers"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.PAPER, 4)
            .pattern("###")
            .define('#', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "papers_from_lumbers"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .unlockedBy("has_green_tea_leaves", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "black_tea_leaves"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.EARL_GREY_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
            .unlockedBy("has_black_tea_leaves", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "earl_grey_leaves"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FRUIT_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get())
            .requires(TsukiItemTags.FRUITS)
            .requires(TsukiItemTags.FRUITS)
            .unlockedBy("has_black_tea_leaves", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fruit_tea_leaves"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MINT_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MINT).get())
            .unlockedBy("has_green_tea_leaves", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mint_tea_leaves"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RICE_TEA_LEAVES).get())
            .requires(TsukiItemTags.RICE_BROWN)
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .unlockedBy("has_green_tea_leaves", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rice_tea_leaves"));


        // Working Stations
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.OBON.get())
            .pattern("LLL")
            .pattern("L#L")
            .define('#', BlockRegistry.SAKURA_LEAVES.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockItemRegistry.TATARA.get())
            .requires(Tags.Items.ORES_IRON) // Accepting all iron ores
            .requires(BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK.get())
            .requires(TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_iron_ore", has(Tags.Items.ORES_IRON))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tatara"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockItemRegistry.TATARA.get())
            .requires(Items.RAW_IRON) // Accepting all iron ores
            .requires(BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK.get())
            .requires(TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_iron_ore", has(Items.RAW_IRON))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tatara_alt"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockItemRegistry.CHOPPING_BOARD.get())
            .pattern("###")
            .pattern("I I")
            .define('#', TsukiItemTags.LUMBER)
            .define('I', Tags.Items.RODS_WOODEN)
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
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "distiller"));
        this.registerFarmerDelightRecipes(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.COOKING_POT.get())
            .pattern("#L#")
            .pattern("###")
            .define('#', Tags.Items.INGOTS_IRON)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.STONE_MORTAR.get())
            .pattern("L  ")
            .pattern("###")
            .pattern("###")
            .define('#', Tags.Items.COBBLESTONES)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.KITUNEBI.get(), 16)
            .pattern("###")
            .pattern("LJL")
            .pattern("###")
            .define('L', TsukiItemTags.LUMBER)
            .define('#', Items.LAPIS_LAZULI)
            .define('J', Items.JACK_O_LANTERN)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.GRAPE_SPLINT.get())
            .pattern(" S ")
            .pattern("SSS")
            .pattern(" S ")
            .define('S', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "grape_splint"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.GRAPE_SPLINT_STAND.get(), 2)
            .pattern(" S ")
            .pattern("S#S")
            .pattern(" S ")
            .define('#', Tags.Items.FENCES_WOODEN)
            .define('S', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "grape_splint_stand"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.PEPPER_SPLINT.get(), 2)
            .pattern("S S")
            .pattern(" # ")
            .pattern("S S")
            .define('#', TsukiItemTags.LUMBER)
            .define('S', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pepper_splint"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.VANILLA_SPLINT.get(), 2)
            .pattern("#S#")
            .pattern("S S")
            .pattern("#S#")
            .define('#', TsukiItemTags.LUMBER)
            .define('S', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "vanilla_splint"));
        foodSmeltingRecipes("vanilla_roast",
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VANILLA).get(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VANILLA_ROAST).get(),
            0.35F,
            consumer);

        // Recipes for tools
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TsukiNormalItemSet.SILK.getItem().get(), 4)
                .pattern("#S#")
                .pattern("SLS")
                .pattern("#S#")
                .define('L', TsukiItemTags.LUMBER)
                .define('S', Tags.Items.STRINGS)
                .define('#', ItemTags.WOOL)
                .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.STONE_HAMMER.get())
                .pattern("SS")
                .pattern(" L")
                .define('S', Tags.Items.COBBLESTONES)
                .define('L', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_cobblestone", has(Tags.Items.COBBLESTONES))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.IRON_HAMMER.get())
                .pattern("II")
                .pattern(" L")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('L', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.STEEL_HAMMER.get())
                .pattern("SS")
                .pattern(" L")
                .define('S', ArmorToolRegistry.STEEL_INGOT.get())
                .define('L', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_steel_ingot", has(ArmorToolRegistry.STEEL_INGOT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.SAKURA_HAMMER.get())
                .pattern("DD")
                .pattern(" L")
                .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
                .define('L', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_sakura_diamond", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.BROOM.get())
                .pattern("S")
                .pattern("B")
                .pattern("B")
                .define('S', Tags.Items.STRINGS)
                .define('B', TsukiItemTags.BAMBOO)
                .unlockedBy("has_bamboo", has(TsukiItemTags.BAMBOO))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "broom"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DrinkRegistry.CUP.get(), 4)
                .pattern("# #")
                .pattern(" # ")
                .define('#', Items.CLAY_BALL)
                .unlockedBy("has_clay", has(Items.CLAY_BALL))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cup"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DrinkRegistry.WINE_BOTTLE.get(), 4)
                .pattern("# #")
                .pattern("# #")
                .pattern(" # ")
                .define('#', Tags.Items.GLASS_BLOCKS)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "wine_bottle"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DrinkRegistry.GLASS_CUP.get(), 4)
                .pattern("# #")
                .pattern(" # ")
                .define('#', Tags.Items.GLASS_PANES)
                .unlockedBy("has_glass", has(Tags.Items.GLASS_PANES))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_cup"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.IRON_FISH_KNIFE.get())
            .pattern("  I")
            .pattern(" I ")
            .pattern("L  ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.IRON_FISH_KNIFE.get())
            .pattern("I  ")
            .pattern(" I ")
            .pattern("  L")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "iron_fish_knife_mirror"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.IRON_NOODLE_KNIFE.get())
            .pattern("II")
            .pattern("II")
            .pattern("IL")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.IRON_NOODLE_KNIFE.get())
            .pattern("II")
            .pattern("II")
            .pattern("LI")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "iron_noodle_knife_mirror"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.KATANA.get())
            .pattern("  M")
            .pattern(" M ")
            .pattern("L H")
            .define('M', ArmorToolRegistry.STEEL_INGOT.get())
            .define('L', TsukiItemTags.LUMBER)
            .define('H', TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_tamahagane", has(ArmorToolRegistry.TAMAHAGANE.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "katana"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.KODACHI.get())
            .pattern(" M")
            .pattern("LH")
            .define('M', ArmorToolRegistry.STEEL_INGOT.get())
            .define('L', TsukiItemTags.LUMBER)
            .define('H', TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_steel_ingot", has(ArmorToolRegistry.STEEL_INGOT.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kodachi"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.SHINAI.get())
            .pattern("  B")
            .pattern(" B ")
            .pattern("L  ")
            .define('B', TsukiItemTags.BAMBOO)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_bamboo", has(TsukiItemTags.BAMBOO))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shinai"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.TACHI.get())
            .pattern("  S")
            .pattern("SS ")
            .pattern("L H")
            .define('S', ArmorToolRegistry.STEEL_INGOT.get())
            .define('L', TsukiItemTags.LUMBER)
            .define('H', TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_steel_ingot", has(ArmorToolRegistry.STEEL_INGOT.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tachi"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.SAKURA_KATANA.get())
            .pattern("  M")
            .pattern(" M ")
            .pattern("L H")
            .define('M', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .define('H', TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_sakura_diamond", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_katana"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.SAKURA_KODACHI.get())
            .pattern(" M")
            .pattern("LH")
            .define('M', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .define('H', TsukiItemTags.TOOLS_HAMMERS)
            .unlockedBy("has_sakura_diamond", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_kodachi"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorToolRegistry.SHEATH.get())
            .pattern("L")
            .pattern("W")
            .define('L', Items.LEATHER)
            .define('W', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sheath"));
        // ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, TsukiArmorToolRegistry.KATANA_SHEATH.get())
        //     .requires(TsukiArmorToolRegistry.KATANA.get())
        //     .requires(TsukiArmorToolRegistry.SHEATH.get())
        //     .unlockedBy("has_katana", has(TsukiArmorToolRegistry.KATANA.get()))
        //     .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "katana_sheath"));
        // ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, TsukiArmorToolRegistry.SAKURA_KATANA_SHEATH.get())
        //     .requires(TsukiArmorToolRegistry.SAKURA_KATANA.get())
        //     .requires(TsukiArmorToolRegistry.SHEATH.get())
        //     .unlockedBy("has_sakura_katana", has(TsukiArmorToolRegistry.SAKURA_KATANA.get()))
        //     .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_katana_sheath"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.SAKURA_AXE.get())
            .pattern("DD ")
            .pattern("DL ")
            .pattern(" L ")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.SAKURA_AXE.get())
            .pattern(" DD")
            .pattern(" LD")
            .pattern(" L ")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_axe_mirror"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.SAKURA_PICKAXE.get())
            .pattern("DDD")
            .pattern(" L ")
            .pattern(" L ")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,  ArmorToolRegistry.SAKURA_SHOVEL.get())
            .pattern("D")
            .pattern("L")
            .pattern("L")
            .define('D',  ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has( ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,  ArmorToolRegistry.SAKURA_HOE.get())
            .pattern("DD ")
            .pattern(" L ")
            .pattern(" L ")
            .define('D',  ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has( ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,  ArmorToolRegistry.SAKURA_HOE.get())
            .pattern(" DD")
            .pattern(" L ")
            .pattern(" L ")
            .define('D',  ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has( ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_hoe_mirror"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,  ArmorToolRegistry.SAKURA_FISH_KNIFE.get())
            .pattern("  D")
            .pattern(" D ")
            .pattern("L  ")
            .define('D',  ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has( ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,  ArmorToolRegistry.SAKURA_FISH_KNIFE.get())
            .pattern("D  ")
            .pattern(" D ")
            .pattern("  L")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has( ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_fish_knife_mirror"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.SAKURA_NOODLE_KNIFE.get())
            .pattern("DD")
            .pattern("DD")
            .pattern("DL")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ArmorToolRegistry.SAKURA_NOODLE_KNIFE.get())
            .pattern("DD")
            .pattern("DD")
            .pattern("LD")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_noodle_knife_mirror"));

        // SP
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,
            ArmorToolRegistry.MUSIC_DISC_MIKO.get())
            .requires(ArmorToolRegistry.KIMONO_MIKO.get())
            .requires(ArmorToolRegistry.SAKURA_DIAMOND.get())
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);

        // Straw Hat
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.STRAW_HAT.get())
            .pattern("###")
            .pattern("# #")
            .define('#', TsukiItemTags.STRAW)
            .unlockedBy("has_item", has(TsukiItemTags.STRAW))
            .save(consumer);

        // Kimono
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_WHITE.get())
            .pattern("# #")
            .pattern("S#S")
            .pattern("###")
            .define('S', Tags.Items.STRINGS)
            .define('#', TsukiNormalItemSet.SILK.getItem().get())
            .unlockedBy("has_item", has(TsukiNormalItemSet.SILK.getItem().get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_WHITE.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_WHITE)
            .unlockedBy("has_item", has(ArmorToolRegistry.KIMONO_WHITE.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kimono_white_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_BLACK.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_CYAN.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_CYAN)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_PURPLE.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_PURPLE)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_BROWN.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_BROWN)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_GREEN.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_SAKURA.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_PINK)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_MIKO.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Tags.Items.DYES_WHITE)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.KIMONO_ENE.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(Items.NETHER_STAR)
            .requires(Tags.Items.DYES_CYAN)
            .requires(Tags.Items.DYES_BLUE)
            .requires(Tags.Items.DYES_PINK)
            .requires(Tags.Items.DYES_WHITE)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.YUKATA_RED.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(TsukiNormalItemSet.SILK.getItem().get())
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.YUKATA_MAGENTA.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(TsukiNormalItemSet.SILK.getItem().get())
            .requires(Tags.Items.DYES_MAGENTA)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.YUKATA_BLUE.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(TsukiNormalItemSet.SILK.getItem().get())
            .requires(Tags.Items.DYES_BLUE)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.YUKATA_LIME.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(TsukiNormalItemSet.SILK.getItem().get())
            .requires(Tags.Items.DYES_LIME)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.YUKATA_YELLOW.get())
            .requires(TsukiItemTags.KIMONO)
            .requires(TsukiNormalItemSet.SILK.getItem().get())
            .requires(Tags.Items.DYES_YELLOW)
            .unlockedBy("has_item", has(TsukiItemTags.KIMONO))
            .save(consumer);

        // Haori
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.HAORI_BLACK.get())
            .pattern("# #")
            .pattern("#S#")
            .pattern("#B#")
            .define('S', Tags.Items.STRINGS)
            .define('#', TsukiNormalItemSet.SILK.getItem().get())
            .define('B', Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiNormalItemSet.SILK.getItem().get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.HAORI_BLACK.get())
            .requires(TsukiItemTags.HAORI)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.HAORI))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "haori_black_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.HAORI_CYAN.get())
            .requires(TsukiItemTags.HAORI)
            .requires(Tags.Items.DYES_CYAN)
            .unlockedBy("has_item", has(TsukiItemTags.HAORI))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.HAORI_BROWN.get())
            .requires(TsukiItemTags.HAORI)
            .requires(Tags.Items.DYES_BROWN)
            .unlockedBy("has_item", has(TsukiItemTags.HAORI))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.HAORI_LIGHT_BLUE.get())
            .requires(TsukiItemTags.HAORI)
            .requires(Tags.Items.DYES_LIGHT_BLUE)
            .unlockedBy("has_item", has(TsukiItemTags.HAORI))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.HAORI_GREEN.get())
            .requires(TsukiItemTags.HAORI)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_item", has(TsukiItemTags.HAORI))
            .save(consumer);

        // Samurai
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_HELMET_RED.get())
            .pattern("DGD")
            .pattern("DID")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('G', Tags.Items.INGOTS_GOLD)
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_CHESTPLATE_RED.get())
            .pattern("D D")
            .pattern("DID")
            .pattern("DDD")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_LEGGINGS_RED.get())
            .pattern("DID")
            .pattern("DID")
            .pattern("I I")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .define('I', Tags.Items.INGOTS_IRON)
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_BOOTS_RED.get())
            .pattern("D D")
            .pattern("D D")
            .define('D', ArmorToolRegistry.SAKURA_DIAMOND.get())
            .unlockedBy("has_item", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_HELMET_RED.get())
            .requires(TsukiItemTags.SAMURAI_HELMET)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_HELMET))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai_helmet_red_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_CHESTPLATE_RED.get())
            .requires(TsukiItemTags.SAMURAI_CHESTPLATE)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_CHESTPLATE))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai_chestplate_red_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_LEGGINGS_RED.get())
            .requires(TsukiItemTags.SAMURAI_LEGGINGS)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_LEGGINGS))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai_leggings_red_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_BOOTS_RED.get())
            .requires(TsukiItemTags.SAMURAI_BOOTS)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_BOOTS))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai_boots_red_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_HELMET_GREEN.get())
            .requires(TsukiItemTags.SAMURAI_HELMET)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_HELMET))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_CHESTPLATE_GREEN.get())
            .requires(TsukiItemTags.SAMURAI_CHESTPLATE)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_CHESTPLATE))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_LEGGINGS_GREEN.get())
            .requires(TsukiItemTags.SAMURAI_LEGGINGS)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_LEGGINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_BOOTS_GREEN.get())
            .requires(TsukiItemTags.SAMURAI_BOOTS)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_BOOTS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_HELMET_BLACK.get())
            .requires(TsukiItemTags.SAMURAI_HELMET)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_HELMET))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_CHESTPLATE_BLACK.get())
            .requires(TsukiItemTags.SAMURAI_CHESTPLATE)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_CHESTPLATE))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_LEGGINGS_BLACK.get())
            .requires(TsukiItemTags.SAMURAI_LEGGINGS)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_LEGGINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SAMURAI_BOOTS_BLACK.get())
            .requires(TsukiItemTags.SAMURAI_BOOTS)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SAMURAI_BOOTS))
            .save(consumer);

        // Soldier
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_HELMET_GRAY.get())
            .pattern("ILI")
            .pattern("L L")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', Items.LEATHER)
            .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_CHESTPLATE_GRAY.get())
            .pattern("L L")
            .pattern("ILI")
            .pattern("LIL")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', Items.LEATHER)
            .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_LEGGINGS_GRAY.get())
            .pattern("ILI")
            .pattern("L L")
            .pattern("I I")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', Items.LEATHER)
            .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_BOOTS_GRAY.get())
            .pattern("I I")
            .pattern("L L")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('L', Items.LEATHER)
            .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
            .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_HELMET_GRAY.get())
            .requires(TsukiItemTags.SOLDIER_HELMET)
            .requires(Tags.Items.DYES_GRAY)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_HELMET))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soldier_helmet_gray_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_CHESTPLATE_GRAY.get())
            .requires(TsukiItemTags.SOLDIER_CHESTPLATE)
            .requires(Tags.Items.DYES_GRAY)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_CHESTPLATE))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soldier_chestplate_gray_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_LEGGINGS_GRAY.get())
            .requires(TsukiItemTags.SOLDIER_LEGGINGS)
            .requires(Tags.Items.DYES_GRAY)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_LEGGINGS))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soldier_leggings_gray_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_BOOTS_GRAY.get())
            .requires(TsukiItemTags.SOLDIER_BOOTS)
            .requires(Tags.Items.DYES_GRAY)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_BOOTS))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "soldier_boots_gray_from_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_HELMET_BLACK.get())
            .requires(TsukiItemTags.SOLDIER_HELMET)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_HELMET))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_CHESTPLATE_BLACK.get())
            .requires(TsukiItemTags.SOLDIER_CHESTPLATE)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_CHESTPLATE))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_LEGGINGS_BLACK.get())
            .requires(TsukiItemTags.SOLDIER_LEGGINGS)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_LEGGINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,
            ArmorToolRegistry.SOLDIER_BOOTS_BLACK.get())
            .requires(TsukiItemTags.SOLDIER_BOOTS)
            .requires(Tags.Items.DYES_BLACK)
            .unlockedBy("has_item", has(TsukiItemTags.SOLDIER_BOOTS))
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
            "chestnut_toasted",
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.CHESTNUT).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.CHESTNUT_TOASTED).get(),
            0.35F,
            consumer
        );
        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(BlockItemRegistry.CHESTNUT_BURRS.get()),
                RecipeCategory.MISC,
                ItemRegistry.MATERIALS.get(TsukiNormalItemSet.CHESTNUT).get(),
                0.15F,
                200
            )
            .group(Tsuki.MODID)
            .unlockedBy("has_chestnut_burrs", has(BlockItemRegistry.CHESTNUT_BURRS.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chestnut_from_smelting"));
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockItemRegistry.NABE_SUKIYAKI.get())
            .requires(BlockItemRegistry.COOKING_POT.get())
            .requires(TsukiItemTags.SOYSAUCE)
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get())
            .requires(TsukiItemTags.RAW_BEEF)
            .requires(Tags.Items.CROPS_CARROT)
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH).get(), 3)
            .requires(TsukiItemTags.FLOUR_WHEAT)
            .requires(TsukiItemTags.FLOUR_WHEAT)
            .requires(TsukiItemTags.FLOUR_WHEAT)
            .requires(TsukiItemTags.WATER)
            .unlockedBy("has_flour", has(TsukiItemTags.FLOUR_WHEAT))
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOKU_TEMPURA.get())
            .requires(TsukiItemTags.SOUPS)
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(BlockRegistry.OBON.get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TEMPURA).get())
            .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOKU_FRIED.get())
            .requires(TsukiItemTags.SOUPS)
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(BlockRegistry.OBON.get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.FRIED_CHICKEN).get())
            .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOKU_KATSU.get())
            .requires(TsukiItemTags.SOUPS)
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(BlockRegistry.OBON.get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.KATSU).get())
            .unlockedBy("has_obon", has(BlockRegistry.OBON.get()))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.TEISHOKU_BURGER.get())
            .requires(TsukiItemTags.SOUPS)
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(BlockRegistry.OBON.get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BURGER_DISH).get())
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
            .requires(Tags.Items.DYES_PINK)
            .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_RED.get())
            .requires(ItemTags.SAPLINGS)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_GREEN.get())
            .requires(ItemTags.SAPLINGS)
            .requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_YELLOW.get())
            .requires(ItemTags.SAPLINGS)
            .requires(Tags.Items.DYES_YELLOW)
            .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MAPLE_SAPLING_ORANGE.get())
            .requires(ItemTags.SAPLINGS)
            .requires(Tags.Items.DYES_ORANGE)
            .unlockedBy("has_sapling", has(ItemTags.SAPLINGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.UME_SAPLING.get())
            .requires(ItemTags.SAPLINGS)
            .requires(Tags.Items.DYES_LIME)
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.KATSU_DISH).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.KATSU).get())
            .requires(TsukiItemTags.SALAD_INGREDIENTS_CABBAGE)
            .unlockedBy("has_katsu", has(FoodRegistry.FOODSET.get(TsukiFoodSet.KATSU).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "katsu_dish"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.FOODSET.get(TsukiFoodSet.CROQUETTE_DISH).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.CROQUETTE).get())
            .requires(TsukiItemTags.SALAD_INGREDIENTS_CABBAGE)
            .unlockedBy("has_croquette", has(FoodRegistry.FOODSET.get(TsukiFoodSet.CROQUETTE).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "croquette_dish"));
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
        this.makeIngotToBlock(BlockItemRegistry.BAMBOO_BLOCK, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO))
            .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get()))
            .save(consumer);
        this.makeIngotToBlock(BlockItemRegistry.BAMBOO_BLOCK, () -> Items.BAMBOO)
            .unlockedBy("has_item", has(Items.BAMBOO))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_block_from_vanilla_bamboo"));
        this.makeIngotToBlock(BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT))
            .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()))
            .save(consumer);
        this.makeIngotToBlock(BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL))
            .unlockedBy("has_item", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get()))
            .save(consumer);
        this.makeBlockToIngot(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO), BlockItemRegistry.BAMBOO_BLOCK).save(consumer);
        this.makeBlockToIngot(() -> Items.BAMBOO, BlockItemRegistry.BAMBOO_BLOCK)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_block_to_vanilla_bamboo"));
        this.makeBlockToIngot(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL), BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK)
            .save(consumer);
        this.makeBlockToIngot(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT), BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT)
            .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get())
            .pattern("L L")
            .pattern("LLL")
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bento_box"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.CUISINES.get(TsukiCuisineSet.BENTO_STANDARD).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get())
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TEMPURA).get())
            .requires(TsukiItemTags.VEGETABLES)
            .requires(TsukiItemTags.FOODS_COOKED_MEAT)
            .unlockedBy("has_bento_box", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bento_standard"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.CUISINES.get(TsukiCuisineSet.BENTO_DELUXE).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get())
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TEMPURA).get())
            .requires(TsukiItemTags.VEGETABLES)
            .requires(Items.DRIED_KELP)
            .requires(TsukiItemTags.FOODS_COOKED_MEAT)
            .unlockedBy("has_bento_box", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bento_deluxe"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.CUISINES.get(TsukiCuisineSet.BENTO_PREMIUM).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get())
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.OMURICE).get())
            .requires(TsukiItemTags.VEGETABLES)
            .unlockedBy("has_bento_box", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bento_premium"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FoodRegistry.CUISINES.get(TsukiCuisineSet.BENTO_SUPREME).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get())
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.CURRY_SAUCE).get())
            .requires(TsukiItemTags.VEGETABLES)
            .requires(TsukiItemTags.FOODS_COOKED_MEAT)
            .unlockedBy("has_bento_box", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bento_supreme"));
        this.makeIngotToBlock(BlockItemRegistry.SAKURA_DIAMOND_BLOCK, () -> ArmorToolRegistry.SAKURA_DIAMOND.get())
            .unlockedBy("has_sakura_diamond", has(ArmorToolRegistry.SAKURA_DIAMOND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_diamond_block"));
        this.makeBlockToIngot(() -> ArmorToolRegistry.SAKURA_DIAMOND.get(), BlockItemRegistry.SAKURA_DIAMOND_BLOCK)
            .unlockedBy("has_sakura_diamond_block", has(BlockItemRegistry.SAKURA_DIAMOND_BLOCK.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_diamond_from_block"));
        this.makeLumber(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_BAMBOO), Ingredient.of(TsukiItemTags.BAMBOO))
            .unlockedBy("has_item", has(TsukiItemTags.BAMBOO))
            .save(consumer);

        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.MAPLE_LOG.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
            .save(consumer);
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.SAKURA_LOG.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
            .save(consumer);
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_UME),
            Ingredient.of(BlockRegistry.UME_LOG.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.UME_LOG.get()))
            .save(consumer);

        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.MAPLE_WOOD.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_lumber_from_wood"));
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.SAKURA_WOOD.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_lumber_from_wood"));
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_UME),
            Ingredient.of(BlockRegistry.UME_WOOD.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.UME_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ume_lumber_from_wood"));

        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.STRIPPED_MAPLE_LOG.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_lumber_from_stripped"));
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.STRIPPED_SAKURA_LOG.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_lumber_from_stripped"));
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_UME),
            Ingredient.of(BlockRegistry.STRIPPED_UME_LOG.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.UME_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ume_lumber_from_stripped"));

        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE),
            Ingredient.of(BlockRegistry.STRIPPED_MAPLE_WOOD.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.MAPLE_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_lumber_from_stripped_wood"));
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA),
            Ingredient.of(BlockRegistry.STRIPPED_SAKURA_WOOD.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.SAKURA_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura_lumber_from_stripped_wood"));
        this.makeLumber(
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_UME),
            Ingredient.of(BlockRegistry.STRIPPED_UME_WOOD.get())
            )
            .unlockedBy("has_item", has(BlockItemRegistry.UME_LOG.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ume_lumber_from_stripped_wood"));

        this.makeLumberToPlank(BlockRegistry.BAMBOO_PLANK, Ingredient.of(TsukiItemTags.LUMBER_BAMBOO))
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        this.makeLumberToPlank(BlockRegistry.MAPLE_PLANK, Ingredient.of(TsukiItemTags.LUMBER_MAPLE))
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        this.makeLumberToPlank(BlockRegistry.SAKURA_PLANK, Ingredient.of(TsukiItemTags.LUMBER_SAKURA))
            .unlockedBy("has_item", has(TsukiItemTags.LUMBER))
            .save(consumer);
        this.makeLumberToPlank(BlockRegistry.UME_PLANK, Ingredient.of(TsukiItemTags.LUMBER_UME))
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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockRegistry.IRON_SAND.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, 200)
            .group(Tsuki.MODID)
            .unlockedBy("has_iron_sand", has(BlockRegistry.IRON_SAND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "iron_ingot_from_iron_sand"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(BlockRegistry.IRON_SAND.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, 100)
            .group(Tsuki.MODID)
            .unlockedBy("has_iron_sand", has(BlockRegistry.IRON_SAND.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "iron_ingot_from_iron_sand_blasting"));
        // Actuallly Zuku cannot be gotten, because Iron Farm is very easy to build after Minecraft 1.14
        // Those rubbish is only useful for modpack creators. LOL
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ArmorToolRegistry.ZUKU.get()), RecipeCategory.MISC, ArmorToolRegistry.ZUKU_INGOT.get(), 0.5F, 200)
            .group(Tsuki.MODID)
            .unlockedBy("has_zuku", has(ArmorToolRegistry.ZUKU.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "zuku_ingot_from_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ArmorToolRegistry.ZUKU_INGOT.get()), RecipeCategory.MISC, ArmorToolRegistry.SAGEGANE.get(), 0.7F, 200)
            .group(Tsuki.MODID)
            .unlockedBy("has_zuku_ingot", has(ArmorToolRegistry.ZUKU_INGOT.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sagegane_from_smelting"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ArmorToolRegistry.SAGEGANE.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, 200)
            .group(Tsuki.MODID)
            .unlockedBy("has_sagegane", has(ArmorToolRegistry.SAGEGANE.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sagegane_to_iron_ingot_from_smelting"));
        // End Annotation
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ArmorToolRegistry.TAMAHAGANE.get()), RecipeCategory.MISC, ArmorToolRegistry.STEEL_INGOT.get(), 0.7F, 200)
            .group(Tsuki.MODID)
            .unlockedBy("has_tamahagane", has(ArmorToolRegistry.TAMAHAGANE.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "steel_ingot_from_smelting"));
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TsukiNormalItemSet.PASTA_BLOCK.getItem().get())
            .requires(TsukiItemTags.FLOUR_WHEAT)
            .requires(TsukiItemTags.FLOUR_WHEAT)
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiItemTags.WATER)
            .unlockedBy("has_eggs", has(TsukiItemTags.EGGS))
            .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, TsukiCuisineSet.CURRY_OMURICE.getItem().get())
            .requires(TsukiCuisineSet.OMURICE.getItem().get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.CURRY_SAUCE).get())
            .unlockedBy("has_omurice", has(TsukiCuisineSet.OMURICE.getItem().get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "curry_omurice"));
    }

    private void registerDecorativeBlockRecipes(RecipeOutput consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_BLOCK.get(), 8)
            .pattern("###")
            .pattern("#L#")
            .pattern("###")
            .define('#', Items.BRICK)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA.get(), 4)
            .pattern("#  ")
            .pattern("L# ")
            .pattern("LL#")
            .define('#', TsukiItemTags.KAWARA_BLOCK)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_kawara_block", has(TsukiItemTags.KAWARA_BLOCK))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA.get(), 4)
            .pattern("  #")
            .pattern(" #L")
            .pattern("#LL")
            .define('#', TsukiItemTags.KAWARA_BLOCK)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_kawara_block", has(TsukiItemTags.KAWARA_BLOCK))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_STAIRS.get(), 4)
            .pattern("#  ")
            .pattern("## ")
            .pattern("###")
            .define('#', BlockRegistry.KAWARA_BLOCK.get())
            .unlockedBy("has_kawara_block", has(BlockRegistry.KAWARA_BLOCK.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_stairs"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_STAIRS.get(), 4)
            .pattern("  #")
            .pattern(" ##")
            .pattern("###")
            .define('#', BlockRegistry.KAWARA_BLOCK.get())
            .unlockedBy("has_kawara_block", has(BlockRegistry.KAWARA_BLOCK.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_stairs_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_SLAB.get(), 6)
            .pattern("###")
            .define('#', BlockRegistry.KAWARA_BLOCK.get())
            .unlockedBy("has_kawara_block", has(BlockRegistry.KAWARA_BLOCK.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_STAIRS_ALTER.get(), 4)
            .pattern("#  ")
            .pattern("## ")
            .pattern("###")
            .define('#', BlockRegistry.KAWARA_BLOCK_ALTER.get())
            .unlockedBy("has_kawara_block_alter", has(BlockRegistry.KAWARA_BLOCK_ALTER.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_stairs_alter"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_STAIRS_ALTER.get(), 4)
            .pattern("  #")
            .pattern(" ##")
            .pattern("###")
            .define('#', BlockRegistry.KAWARA_BLOCK_ALTER.get())
            .unlockedBy("has_kawara_block_alter", has(BlockRegistry.KAWARA_BLOCK_ALTER.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_stairs_alter_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_SLAB_ALTER.get(), 6)
            .pattern("###")
            .define('#', BlockRegistry.KAWARA_BLOCK_ALTER.get())
            .unlockedBy("has_kawara_block_alter", has(BlockRegistry.KAWARA_BLOCK_ALTER.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_slab_alter"));

        // Kawara block conversion recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_BLOCK_ALTER.get())
                .requires(BlockRegistry.KAWARA_BLOCK.get())
                .unlockedBy("has_kawara_block", has(BlockRegistry.KAWARA_BLOCK.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_block_to_alter"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_BLOCK.get())
                .requires(BlockRegistry.KAWARA_BLOCK_ALTER.get())
                .unlockedBy("has_kawara_block_alter", has(BlockRegistry.KAWARA_BLOCK_ALTER.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_block_from_alter"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_STAIRS_ALTER.get())
                .requires(BlockRegistry.KAWARA_STAIRS.get())
                .unlockedBy("has_kawara_stairs", has(BlockRegistry.KAWARA_STAIRS.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_stairs_to_alter"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_STAIRS.get())
                .requires(BlockRegistry.KAWARA_STAIRS_ALTER.get())
                .unlockedBy("has_kawara_stairs_alter", has(BlockRegistry.KAWARA_STAIRS_ALTER.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_stairs_from_alter"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_SLAB_ALTER.get())
                .requires(BlockRegistry.KAWARA_SLAB.get())
                .unlockedBy("has_kawara_slab", has(BlockRegistry.KAWARA_SLAB.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_slab_to_alter"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_SLAB.get())
                .requires(BlockRegistry.KAWARA_SLAB_ALTER.get())
                .unlockedBy("has_kawara_slab_alter", has(BlockRegistry.KAWARA_SLAB_ALTER.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_slab_from_alter"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_BLOCK.get())
                .requires(BlockRegistry.KAWARA_SLAB.get(), 2)
                .unlockedBy("has_kawara_slab", has(BlockRegistry.KAWARA_SLAB.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_block_from_slabs"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockRegistry.KAWARA_BLOCK_ALTER.get())
                .requires(BlockRegistry.KAWARA_SLAB_ALTER.get(), 2)
                .unlockedBy("has_kawara_slab_alter", has(BlockRegistry.KAWARA_SLAB_ALTER.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kawara_block_alter_from_slabs"));


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.STONE_LANTERN.get())
            .pattern(" S ")
            .pattern("SLS")
            .pattern(" S ")
            .define('S', Items.STONE)
            .define('L', Items.LANTERN)
            .unlockedBy("has_stone", has(Items.STONE))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "stone_lantern"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.COBBLESTONE_LANTERN.get())
            .pattern(" C ")
            .pattern("CLC")
            .pattern(" C ")
            .define('C', Items.COBBLESTONE)
            .define('L', Items.LANTERN)
            .unlockedBy("has_cobblestone", has(Items.COBBLESTONE))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cobblestone_lantern"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BlockRegistry.MOSSY_STONE_LANTERN.get())
            .requires(BlockRegistry.STONE_LANTERN.get())
            .requires(Items.VINE)
            .unlockedBy("has_stone_lantern", has(BlockRegistry.STONE_LANTERN.get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mossy_stone_lantern"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.RED_LANTERN.get())
            .pattern(" I ")
            .pattern("PTP")
            .pattern(" I ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.RED_WOOL)
            .define('T', Items.TORCH)
            .unlockedBy("has_red_wool", has(Items.RED_WOOL))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "red_lantern"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.WHITE_LANTERN.get())
            .pattern(" I ")
            .pattern("PTP")
            .pattern(" I ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('P', Items.WHITE_WOOL)
            .define('T', Items.TORCH)
            .unlockedBy("has_white_wool", has(Items.WHITE_WOOL))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "white_lantern"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.BAMBOO_LANTERN.get())
            .pattern(" B ")
            .pattern("BTB")
            .pattern(" B ")
            .define('B', TsukiItemTags.BAMBOO)
            .define('T', Items.TORCH)
            .unlockedBy("has_bamboo", has(TsukiItemTags.BAMBOO))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_lantern"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.ANDON.get())
            .pattern("PPP")
            .pattern("PTP")
            .pattern("LLL")
            .define('P', Items.PAPER)
            .define('T', Items.TORCH)
            .define('L', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "andon"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.WINDBELL.get())
            .pattern(" S ")
            .pattern("IGI")
            .pattern(" I ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('G', Items.GLASS)
            .define('S', Items.STRING)
            .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "windbell"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.ZABUTON.get())
            .pattern("LLL")
            .pattern("WWW")
            .define('W', Items.BLUE_WOOL)
            .define('L', Items.LAPIS_LAZULI)
            .unlockedBy("has_blue_wool", has(Items.BLUE_WOOL))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "zabuton"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.TAIKO.get())
            .pattern("LLL")
            .pattern("LSL")
            .pattern("LLL")
            .define('L', TsukiItemTags.LUMBER)
            .define('S', Items.LEATHER)
            .unlockedBy("has_leather", has(Items.LEATHER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "taiko"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.BAMBOO_DOOR.get(), 3)
            .pattern("##")
            .pattern("##")
            .pattern("##")
            .define('#', TsukiItemTags.BAMBOO)
            .unlockedBy("has_bamboo", has(TsukiItemTags.BAMBOO))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_door"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.BAMBOO_FENCE.get(), 3)
            .pattern("#I#")
            .pattern("#I#")
            .define('#', TsukiItemTags.BAMBOO)
            .define('I', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_bamboo", has(TsukiItemTags.BAMBOO))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_fence"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.BAMBOO_FENCE_SUNBURNT.get(), 3)
            .pattern("#I#")
            .pattern("#I#")
            .define('#', ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get())
            .define('I', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_bamboo_sunburnt", has(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bamboo_fence_sunburnt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI.get())
            .pattern("SPP")
            .pattern("SPP")
            .pattern("SPP")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .unlockedBy("has_paper", has(Items.PAPER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI.get())
            .pattern("PPS")
            .pattern("PPS")
            .pattern("PPS")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .unlockedBy("has_paper", has(Items.PAPER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI_1.get())
            .pattern("SPP")
            .pattern("SPP")
            .pattern("SII")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .define('I', Tags.Items.DYES_BLUE)
            .unlockedBy("has_blue_dye", has(Tags.Items.DYES_BLUE))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI_1.get())
            .pattern("PPS")
            .pattern("PPS")
            .pattern("IIS")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .define('I', Tags.Items.DYES_BLUE)
            .unlockedBy("has_blue_dye", has(Tags.Items.DYES_BLUE))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_1_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI_2.get())
            .pattern("SPS")
            .pattern("SPS")
            .pattern("SSS")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .unlockedBy("has_paper", has(Items.PAPER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_2"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI_3.get())
            .pattern("SPS")
            .pattern("PMP")
            .pattern("SPS")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .define('M', Tags.Items.DYES)
            .unlockedBy("has_dye", has(Tags.Items.DYES))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_3"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI_4.get())
            .pattern("SSS")
            .pattern("PPP")
            .pattern("SSS")
            .define('S', TsukiItemTags.LUMBER)
            .define('P', Items.PAPER)
            .unlockedBy("has_paper", has(Items.PAPER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_4"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.SHOJI_5.get())
            .pattern("SS")
            .pattern("SS")
            .pattern("SS")
            .define('S', TsukiItemTags.LUMBER)
            .unlockedBy("has_lumber", has(TsukiItemTags.LUMBER))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shoji_5"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.NOREN_WHITE.get())
            .pattern("LLL")
            .pattern("W W")
            .pattern("W W")
            .define('L', TsukiItemTags.LUMBER)
            .define('W', Items.WHITE_WOOL)
            .unlockedBy("has_white_wool", has(Items.WHITE_WOOL))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "noren_white"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.NOREN_BLUE.get())
            .pattern("LLL")
            .pattern("W W")
            .pattern("W W")
            .define('L', TsukiItemTags.LUMBER)
            .define('W', Items.BLUE_WOOL)
            .unlockedBy("has_blue_wool", has(Items.BLUE_WOOL))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "noren_blue"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.NOREN_PINK.get())
            .pattern("LLL")
            .pattern("W W")
            .pattern("W W")
            .define('L', TsukiItemTags.LUMBER)
            .define('W', Items.PINK_WOOL)
            .unlockedBy("has_pink_wool", has(Items.PINK_WOOL))
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "noren_pink"));
    }

    private void registerMortarRecipe(RecipeOutput consumer) {
        StoneMortarRecipeBuilder.mortar(Items.BONE_MEAL, 3)
            .addResult(Items.BONE_MEAL, 3)
            .requires(Tags.Items.BONES)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "bonemeal_from_mortar"));
        StoneMortarRecipeBuilder.mortar(Items.SAND)
            .addResult(Items.FLINT)
            .requires(Tags.Items.GRAVELS)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "flint_from_mortar"));
        StoneMortarRecipeBuilder.mortar(Items.GRAVEL)
            .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SALT).get(), 2)
            .requires(Tags.Items.COBBLESTONES)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "salt_from_mortar"));
        StoneMortarRecipeBuilder.mortar(Items.COBBLESTONE)
            .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.ALKALINE).get(), 2)
            .requires(Tags.Items.STONES)
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
        StoneMortarRecipeBuilder.mortar(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOCHA).get(), 3)
            .addResult(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOCHA).get(), 3)
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mocha_from_mortar"));
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
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.PASTA_TOMATO).get(), 1
            )
            .requires(TsukiNormalItemSet.PASTA_RAW.getItem().get())
            .requires(TsukiItemTags.TOMATOSAUCE)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pasta_tomato_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.PASTA_MUSHROOM).get(), 1
            )
            .requires(TsukiNormalItemSet.PASTA_RAW.getItem().get())
            .requires(TsukiItemTags.MUSHROOMS)
            .requires(TsukiItemTags.MUSHROOMS)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pasta_mushroom_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 250), FoodRegistry.CUISINES.get(TsukiCuisineSet.PASTA_WHITESAUCE).get(), 1
            )
            .requires(TsukiNormalItemSet.PASTA_RAW.getItem().get())
            .requires(TsukiItemTags.MILK)
            .requires(TsukiItemTags.FLOUR)
            .requires(TsukiItemTags.SALT)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pasta_whitesauce_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.YAKI_PASTA).get(), 2
            )
            .requires(TsukiNormalItemSet.PASTA_RAW.getItem().get())
            .requires(Ingredient.fromValues(Stream.of(
                    new TagValue(TsukiItemTags.RAW_CHICKEN),
                    new TagValue(TsukiItemTags.RAW_PORK),
                    new TagValue(TsukiItemTags.RAW_BEEF),
                    new TagValue(TsukiItemTags.RAW_MUTTON))))
            .requires(TsukiItemTags.VEGETABLES)
            .requires(TsukiItemTags.SOYSAUCE)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yaki_pasta_cooking"));
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
            .requires(Ingredient.fromValues(Stream.of(new TagValue(TsukiItemTags.CROPS_TARO), new TagValue(Tags.Items.CROPS_POTATO))))
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
            .requires(Ingredient.fromValues(Stream.of(new TagValue(TsukiItemTags.CROPS_TARO), new TagValue(Tags.Items.CROPS_POTATO))))
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
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.DANANKO).get()
            )
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.REDBEAN_PASTE).get())
            .container(TsukiNormalItemSet.BAMBOO.getItem().get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "dananko_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.DANMITARASHI).get()
            )
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get())
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.SUGAR)
            .container(TsukiNormalItemSet.BAMBOO.getItem().get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "danmitarashi_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.CUISINES.get(TsukiCuisineSet.DANSANSYOKU).get()
            )
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.DANGO).get())
            .requires(BlockRegistry.SAKURA_LEAVES.get())
            .requires(Items.SHORT_GRASS)
            .container(TsukiNormalItemSet.BAMBOO.getItem().get())
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
            .requires(Tags.Items.CROPS_POTATO)
            .requires(TsukiItemTags.SALT)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fries_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125), FoodRegistry.FOODSET.get(TsukiFoodSet.MASHED_POTATO).get(), 2
            )
            .requires(Tags.Items.CROPS_POTATO)
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
            .requires(Tags.Items.CROPS_CARROT)
            .requires(Tags.Items.CROPS_POTATO)
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
            .requires(Tags.Items.CROPS_CARROT)
            .requires(Tags.Items.CROPS_POTATO)
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
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.FOODSET.get(TsukiFoodSet.EGG_SOFT).get(), 2
            )
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiItemTags.EGGS)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "egg_soft_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.FOODSET.get(TsukiFoodSet.EGG_SOYSAUCE).get(), 2
            )
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiItemTags.SOYSAUCE)
            .requires(TsukiItemTags.SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "egg_soysauce_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.CUISINES.get(TsukiCuisineSet.OCHAZUKE).get(), 2
            )
            .requires(TsukiItemTags.RICE_RICE)
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiNormalItemSet.WHITE_PEPPER.getItem().get())
            .requires(TsukiItemTags.SOYSAUCE)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ochazuke_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.CUISINES.get(TsukiCuisineSet.CHAWANMUSHI).get(), 2
            )
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiItemTags.VEGETABLES)
            .requires(TsukiNormalItemSet.NOODLE_SOUP.getItem().get())
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chawanmushi_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.CUISINES.get(TsukiCuisineSet.WHITE_STEW).get(), 2
            )
            .requires(TsukiItemTags.MILK)
            .requires(TsukiItemTags.FLOUR)
            .requires(TsukiItemTags.SALT)
            .requires(TsukiItemTags.RAW_CHICKEN)
            .requires(Tags.Items.CROPS_CARROT)
            .requires(Tags.Items.CROPS_POTATO)
            .requires(TsukiItemTags.MUSHROOMS)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "white_stew_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.CUISINES.get(TsukiCuisineSet.ODEN).get(), 2
            )
            .requires(TsukiFoodSet.FISHCAKE.getItem().get())
            .requires(TsukiItemTags.CROPS_RADISH)
            .requires(TsukiItemTags.EGGS)
            .container(Items.STICK)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "oden_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.FOODSET.get(TsukiFoodSet.PUDDING).get(), 2
            )
            .requires(TsukiItemTags.MILK)
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiNormalItemSet.VANILLA.getItem().get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pudding_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.FOODSET.get(TsukiFoodSet.PUDDING_MAPLE).get(), 2
            )
            .requires(TsukiItemTags.MILK)
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiNormalItemSet.VANILLA.getItem().get())
            .requires(TsukiNormalItemSet.MAPLE_SYRUP.getItem().get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pudding_maple_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.FOODSET.get(TsukiFoodSet.PUDDING_MOCHA).get(), 2
            )
            .requires(TsukiItemTags.MILK)
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.EGGS)
            .requires(TsukiNormalItemSet.VANILLA.getItem().get())
            .requires(TsukiNormalItemSet.MOCHA.getItem().get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pudding_mocha_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 100), FoodRegistry.FOODSET.get(TsukiFoodSet.FRUITSALAD).get(), 2
            )
            .requires(Ingredient.fromValues(Stream.of(new ItemValue(new ItemStack(Items.APPLE)), new ItemValue(new ItemStack(Items.CHORUS_FRUIT)))))
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.FRUITS)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fruitsalad_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.FOOD_OIL, 200), FoodRegistry.CUISINES.get(TsukiCuisineSet.YAKI_SOBA).get(), 2
            )
            .requires(TsukiNormalItemSet.SOBA_RAW.getItem().get())
            .requires(TsukiItemTags.FOODS_RAW_MEAT)
            .requires(TsukiItemTags.VEGETABLES)
            .requires(TsukiItemTags.SOYSAUCE)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yaki_soba_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200), FoodRegistry.CUISINES.get(TsukiCuisineSet.ZOSUI).get(), 2
            )
            .requires(TsukiItemTags.RICE_RICE)
            .requires(TsukiItemTags.FOODS_RAW_MEAT)
            .requires(TsukiItemTags.VEGETABLES)
            .requires(TsukiItemTags.SOYSAUCE)
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "zosui_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 100), FoodRegistry.CUISINES.get(TsukiCuisineSet.ZOSUI_ZUIKI).get()
            )
            .requires(TsukiItemTags.RICE_RICE)
            .requires(TsukiNormalItemSet.IMOGARA.getItem().get())
            .container(Items.BOWL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "zosui_zuiki_cooking"));
        CookingPotRecipeBuilder.cooking(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 0), FoodRegistry.FOODSET.get(TsukiFoodSet.SUIKATSUGAN).get(), 2
            )
            .requires(TsukiItemTags.FLOUR)
            .requires(TsukiFoodSet.UMEBOSHI.getItem().get())
            .requires(TsukiItemTags.SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "suikatsugan_cooking"));
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
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200),
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
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 200),
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
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.DOBUROKU.get(), 500))
            .requires(FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get())
            .requires(TsukiItemTags.KOUJI)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "doburoku_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.BEER.get(), 500))
            .requires(TsukiItemTags.GRAIN)
            .requires(TsukiItemTags.BROWN_MUSHROOMS)
            .requires(TsukiItemTags.SUGAR_SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "basic_beer_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(
                TsukiFluidTags.WATER_WATER, 1000),
                new FluidStack((Fluid)FluidRegistry.BEER.get(), 500), 0.0F, 400
            )
            .requires(TsukiItemTags.GRAIN)
            .requires(TsukiItemTags.GRAIN)
            .requires(TsukiItemTags.YEAST)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "beer_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.YEAST).get(),
            40,
            FluidStack.EMPTY,
            0.0F,
            400
            )
            .requires(TsukiItemTags.BROWN_MUSHROOMS)
            .requires(TsukiItemTags.SUGAR_SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "yeast_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 500),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.YEAST).get(),
            20,
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
            FluidIngredient.fromFluid((Fluid)FluidRegistry.DOBUROKU.get(), 1000),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKE_KASU).get(),
            2,
            new FluidStack((Fluid)FluidRegistry.SAKE.get(), 500),
            10.0F,
            500
            )
            .requires(TsukiItemTags.DUST_CHARCOAL)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sake_charcoal_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.DOBUROKU.get(), 1000),
            new FluidStack((Fluid)FluidRegistry.SAKE.get(), 500), 10.0F, 1000
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
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.RED_WINE.get(), 500)
            )
            .requires(TsukiItemTags.CROPS_GRAPE)
            .requires(TsukiItemTags.CROPS_GRAPE)
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.YEAST)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "red_wine_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.WHITE_WINE.get(), 500)
            )
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.GRAPE_GREEN).get())
            .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.GRAPE_GREEN).get())
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.YEAST)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "white_wine_fermenting"));
        FermenterRecipeBuilder.fermenting(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.WHITE_WINE.get(), 1000),
            new FluidStack((Fluid)FluidRegistry.CHAMPAGNE.get(), 500)
            )
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.YEAST)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "champagne_fermenting"));
    }

    private void registerDistillerRecipe(RecipeOutput consumer) {
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.SAKE.get(), 1000),
            new FluidStack((Fluid)FluidRegistry.SHOUCHU.get(), 500)
            )
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shouchu_from_sake_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromFluid((Fluid)FluidRegistry.BEER.get(), 1000),
            new FluidStack((Fluid)FluidRegistry.WHISKEY.get(), 500)
            )
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "whiskey_from_beer_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.RUM.get(), 500)
        )
            .requires(Items.SUGAR_CANE)
            .requires(Items.SUGAR_CANE)
            .requires(TsukiItemTags.YEAST)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rum_cane_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.RUM.get(), 500)
        )
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOLASSES).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MOLASSES).get())
            .requires(TsukiItemTags.YEAST)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "rum_molasses_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.SHOUCHU.get(), 500)
        )
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKE_KASU).get())
            .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKE_KASU).get())
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shouchu_from_sakekasu_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.VODKA.get(), 500)
        )
            .requires(Items.POTATO)
            .requires(Items.POTATO)
            .requires(Items.POTATO)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "vodka_distillation"));
        DistillerRecipeBuilder.distillation(FluidIngredient.fromTag(
            TsukiFluidTags.BREWERS_ALCOHOL, 1000),
            new FluidStack((Fluid)FluidRegistry.LIQUEUR.get(), 1000)
        )
            .requires(TsukiItemTags.FRUITS)
            .requires(TsukiItemTags.FRUITS)
            .requires(Items.SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "liqueur_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromTag(TsukiFluidTags.BREWERS_ALCOHOL, 1000),
            new FluidStack((Fluid)FluidRegistry.COCOA_LIQUEUR.get(), 1000)
        )
            .requires(Items.COCOA_BEANS)
            .requires(Items.COCOA_BEANS)
            .requires(Items.SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cocoa_liqueur_distillation"));
        DistillerRecipeBuilder.distillation(FluidIngredient.fromTag(
            TsukiFluidTags.BREWERS_ALCOHOL, 1000),
            new FluidStack((Fluid)FluidRegistry.GIN.get(), 1000)
        )
            .requires(TsukiItemTags.GRAIN)
            .requires(TsukiItemTags.FRUITS_BERRIES)
            .requires(TsukiItemTags.FRUITS_BERRIES)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "gin_distillation"));
        DistillerRecipeBuilder.distillation(
            FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 1000),
            new FluidStack((Fluid)FluidRegistry.TEQUILA.get(), 500)
        )
            .requires(Items.CACTUS)
            .requires(Items.CACTUS)
            .requires(Items.SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "tequila_distillation"));
        DistillerRecipeBuilder.distillation(FluidIngredient.fromTag(
            TsukiFluidTags.GRAPE_WINE, 1000),
            new FluidStack((Fluid)FluidRegistry.BRANDY.get(), 1000)
        )
            .requires(TsukiItemTags.SUGAR)
            .requires(TsukiItemTags.SUGAR)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "brandy_grape_distillation"));
    }

    private void registerDrinkRecipes(RecipeOutput consumer) {
        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.GREEN_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "green_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.BLACK_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "black_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.EARL_GREY).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.EARL_GREY_LEAVES).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "earl_grey_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.FRUIT_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FRUIT_TEA_LEAVES).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fruit_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.MINT_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MINT_TEA_LEAVES).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "mint_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.BARLEY_TEA).get())
                .requires(TsukiItemTags.GRAIN_WHEAT)
                .requires(TsukiItemTags.GRAIN_WHEAT)
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "barley_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.BROWN_RICE_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RICE_TEA_LEAVES).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "brown_rice_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.MILK_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get())
                .requires(TsukiItemTags.MILK)
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "milk_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.MILK_GREEN_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
                .requires(TsukiItemTags.MILK)
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "milk_green_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.MILK_EARL_GREY).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.EARL_GREY_LEAVES).get())
                .requires(TsukiItemTags.MILK)
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "milk_earl_grey_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.MILK_FRUIT_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FRUIT_TEA_LEAVES).get())
                .requires(TsukiItemTags.MILK)
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "milk_fruit_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.LEMON_BLACK_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BLACK_TEA_LEAVES).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "lemon_black_tea_cooking"));

        CookingPotRecipeBuilder
                .cooking(FluidIngredient.fromTag(TsukiFluidTags.WATER_WATER, 125),
                        DrinkRegistry.TEAS.get(TsukiTeaSet.LEMON_GREEN_TEA).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .container(DrinkRegistry.CUP.get())
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "lemon_green_tea_cooking"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BEER).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BEER_BOTTLE).get())
                .unlockedBy("has_beer", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BEER_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_beer"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_DOBUROKU).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.DOBUROKU_BOTTLE).get())
                .unlockedBy("has_doburoku", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.DOBUROKU_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_doburoku"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_SAKE).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SAKE_BOTTLE).get())
                .unlockedBy("has_sake", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SAKE_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_sake"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_SHOUCHU).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SHOUCHU_BOTTLE).get())
                .unlockedBy("has_shouchu", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SHOUCHU_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_shouchu"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RED_WINE_BOTTLE).get())
                .unlockedBy("has_red_wine", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RED_WINE_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_red_wine"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHITE_WINE_BOTTLE).get())
                .unlockedBy("has_white_wine", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHITE_WINE_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_white_wine"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.CHAMPAGNE_BOTTLE).get())
                .unlockedBy("has_champagne", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.CHAMPAGNE_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_champagne"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RUM_BOTTLE).get())
                .unlockedBy("has_rum", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RUM_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_rum"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHISKEY_BOTTLE).get())
                .unlockedBy("has_whiskey", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHISKEY_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_whiskey"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BRANDY_BOTTLE).get())
                .unlockedBy("has_brandy", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BRANDY_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_brandy"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.GIN_BOTTLE).get())
                .unlockedBy("has_gin", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.GIN_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_gin"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.TEQUILA_BOTTLE).get())
                .unlockedBy("has_tequila", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.TEQUILA_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_tequila"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.VODKA_BOTTLE).get())
                .unlockedBy("has_vodka", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.VODKA_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_vodka"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.LIQUEUR_BOTTLE).get())
                .unlockedBy("has_liqueur", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.LIQUEUR_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_liqueur"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_COCOA_LIQUEUR).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.COCOA_LIQUEUR_BOTTLE).get())
                .unlockedBy("has_cocoa_liqueur", has(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.COCOA_LIQUEUR_BOTTLE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_cocoa_liqueur"));


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_KIR).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get())
                .unlockedBy("has_white_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_kir"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_KIR_ROYALE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get())
                .unlockedBy("has_champagne", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_kir_royale"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_CASSIS_ORANGE).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .requires(DrinkRegistry.WINE_BOTTLE.get())
                .unlockedBy("has_juice", has(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_cassis_orange"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_CASSIS_SODA).get())
                .requires(DrinkRegistry.GLASS_CUP.get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .requires(DrinkRegistry.WINE_BOTTLE.get())
                .unlockedBy("has_juice", has(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_cassis_soda"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_MIMOSA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .unlockedBy("has_champagne", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_mimosa"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SHANDY_GAFF).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BEER).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_beer", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BEER).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_shandy_gaff"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_RED_EYE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BEER).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO_SAUCE).get())
                .unlockedBy("has_beer", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BEER).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_red_eye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_HIGHBALL).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_whiskey", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_highball"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_GIN_TONIC).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .unlockedBy("has_gin", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_gin_tonic"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_GIMLET).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .unlockedBy("has_gin", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_gimlet"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_GIN_FIZZ).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_gin", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_gin_fizz"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SCREWDRIVER).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .unlockedBy("has_vodka", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_screwdriver"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SALTY_DOG).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SALT)
                .unlockedBy("has_vodka", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_salty_dog"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_MOSCOW_MULE).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BEER).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .unlockedBy("has_vodka", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_moscow_mule"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_TEQUILA_SUNRISE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .unlockedBy("has_tequila", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_tequila_sunrise"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_MARGARITA).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SALT)
                .unlockedBy("has_tequila", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_margarita"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_CUBA_LIBRE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .unlockedBy("has_rum", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_cuba_libre"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_DAIQUIRI).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SUGAR)
                .unlockedBy("has_rum", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_daiquiri"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_GROG).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SUGAR).requires(TsukiItemTags.SUGAR)
                .unlockedBy("has_rum", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_grog"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_HOT_TODDY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SUGAR)
                .unlockedBy("has_whiskey", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_hot_toddy"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SANGRIA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .requires(Items.APPLE)
                .unlockedBy("has_red_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_sangria"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SPRITZER).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_white_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_spritzer"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_MARTINI).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get())
                .unlockedBy("has_gin", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_martini"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_AMERICANO).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_red_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_americano"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_NEGRONI).get(), 3)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .unlockedBy("has_gin", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_negroni"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_ALEXANDER).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_COCOA_LIQUEUR).get())
                .requires(TsukiItemTags.MILK)
                .unlockedBy("has_brandy", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_alexander"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_BELLINI).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .unlockedBy("has_champagne", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_CHAMPAGNE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_bellini"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_KALIMOTXO).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_red_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_kalimotxo"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_KITTY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .unlockedBy("has_red_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RED_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_kitty"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_OPERATOR).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_white_wine", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHITE_WINE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_operator"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_MATADOR).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .unlockedBy("has_tequila", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_TEQUILA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_matador"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_HOT_BUTTERED_RUM).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(TsukiItemTags.SUGAR)
                .requires(TsukiItemTags.MILK)
                .unlockedBy("has_rum", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_hot_buttered_rum"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_PINA_COLADA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(TsukiItemTags.MILK)
                .requires(TsukiItemTags.SUGAR).requires(TsukiItemTags.SUGAR)
                .unlockedBy("has_rum", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_pina_colada"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_BLACK_RUSSIAN).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_COCOA_LIQUEUR).get())
                .unlockedBy("has_vodka", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_black_russian"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_GODFATHER).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ALMOND).get())
                .unlockedBy("has_whiskey", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_godfather"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_GODMOTHER).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ALMOND).get())
                .unlockedBy("has_vodka", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_godmother"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SIDECAR).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .unlockedBy("has_brandy", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_sidecar"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_BLOODY_MARY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO_SAUCE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SALT)
                .unlockedBy("has_vodka", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_VODKA).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_bloody_mary"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_OLD_FASHIONED).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(TsukiItemTags.SUGAR)
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get())
                .unlockedBy("has_whiskey", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_old_fashioned"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_WHISKEY_SOUR).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SUGAR)
                .unlockedBy("has_whiskey", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_whiskey_sour"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_MOJITO).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .requires(TsukiItemTags.SUGAR)
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get())
                .unlockedBy("has_rum", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_mojito"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_RUSTY_NAIL).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .unlockedBy("has_whiskey", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_WHISKEY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_rusty_nail"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SAKETINI).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_SAKE).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_GIN).get())
                .unlockedBy("has_sake", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_SAKE).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_saketini"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_STINGER).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_LIQUEUR).get())
                .requires(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MINT).get())
                .unlockedBy("has_brandy", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_stinger"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DrinkRegistry.COCKTAILS.get(TsukiCocktailSet.GLASS_SCORPION).get(), 2)
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get())
                .requires(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_RUM).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get())
                .requires(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get())
                .unlockedBy("has_brandy", has(DrinkRegistry.ALCOHOLS.get(TsukiAlcoholSet.GLASS_BRANDY).get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "glass_scorpion"));
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
        ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.CHESTNUT.getItem().get(), 2)
            .requires(BlockItemRegistry.CHESTNUT_BURRS.get())
            .requiresTool(TsukiItemTags.TOOLS_KNIVES_FISH)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "chestnut_chopping"));
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
        ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.PASTA_RAW.getItem().get(), 2, 1.0F, 4)
            .requires(TsukiNormalItemSet.PASTA_BLOCK.getItem().get())
            .requiresTool(TsukiItemTags.TOOLS_KNIVES_NOODLE)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "pasta_raw_chopping"));

        ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.BLACK_PEPPER.getItem().get(), 2)
            .requires(TsukiNormalItemSet.PEPPERCORN_GREEN.getItem().get())
            .requiresTool(TsukiItemTags.TOOLS_KNIVES_FISH)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "black_pepper_chopping"));

        ChoppingBoardRecipeBuilder.chop(TsukiNormalItemSet.WHITE_PEPPER.getItem().get(), 2)
            .requires(TsukiNormalItemSet.PEPPERCORN_RED.getItem().get())
            .requiresTool(TsukiItemTags.TOOLS_KNIVES_FISH)
            .save(consumer, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "white_pepper_chopping"));
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

    public void makeStair(RecipeOutput consumer, Supplier<? extends Block> out, Supplier<? extends Block> in) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, out.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', in.get())
                .unlockedBy("has_item", has(in.get()))
                .save(consumer);
    }

    public ShapelessRecipeBuilder makeLumber(Supplier<? extends Item> ingotOut, Ingredient ingreIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ingotOut.get(), 16).requires(ingreIn);
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


