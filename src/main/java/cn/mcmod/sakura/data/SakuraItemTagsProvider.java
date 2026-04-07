package cn.mcmod.sakura.data;

import cn.mcmod.sakura.block.BlockItemRegistry;
import cn.mcmod.sakura.item.FoodRegistry;
import cn.mcmod.sakura.item.ItemRegistry;
import cn.mcmod.sakura.item.enums.SakuraFoodSet;
import cn.mcmod.sakura.item.enums.SakuraNormalItemSet;
import cn.mcmod.sakura.tags.SakuraItemTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SakuraItemTagsProvider extends ItemTagsProvider {
   public SakuraItemTagsProvider(
      PackOutput packOutput, CompletableFuture<Provider> lookupProvider, BlockTagsProvider blockTags, String modId, ExistingFileHelper existingFileHelper
   ) {
      super(packOutput, lookupProvider, blockTags.contentsGetter(), modId, existingFileHelper);
   }

   protected void addTags(Provider provider) {
      this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
      this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
      this.tag(ItemTags.LOGS)
         .add(
            BlockItemRegistry.MAPLE_LOG.get(),
            BlockItemRegistry.SAKURA_LOG.get(),
            BlockItemRegistry.MAPLE_WOOD.get(),
            BlockItemRegistry.SAKURA_WOOD.get(),
            BlockItemRegistry.STRIPPED_MAPLE_LOG.get(),
            BlockItemRegistry.STRIPPED_SAKURA_LOG.get()
         );
      this.tag(ItemTags.COALS).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BAMBOO_CHARCOAL).get());
      this.tag(SakuraItemTags.TOOLS_KNIVES).addTag(SakuraItemTags.TOOLS_KNIVES_FISH).addTag(SakuraItemTags.TOOLS_KNIVES_NOODLE);
      this.tag(SakuraItemTags.TOOLS_KNIVES_FISH).add(ItemRegistry.IRON_FISH_KNIFE.get());
      this.tag(SakuraItemTags.TOOLS_KNIVES_NOODLE).add(ItemRegistry.IRON_NOODLE_KNIFE.get());
      this.tag(SakuraItemTags.SEEDS_RICE).add(ItemRegistry.RICE_SEEDS.get());
      this.tag(SakuraItemTags.SEEDS_CABBAGE).add(ItemRegistry.CABBAGE_SEEDS.get());
      this.tag(SakuraItemTags.SEEDS_EGGPLANT).add(ItemRegistry.EGGPLANT_SEEDS.get());
      this.tag(SakuraItemTags.SEEDS_BUCKWHEAT).add(ItemRegistry.BUCKWHEAT.get());
      this.tag(SakuraItemTags.SEEDS_ONION).add(ItemRegistry.ONION_SEEDS.get());
      this.tag(SakuraItemTags.SEEDS_RADISH).add(ItemRegistry.RADISH_SEEDS.get());
      this.tag(SakuraItemTags.SEEDS_RAPESEED).add(ItemRegistry.RAPESEEDS.get());
      this.tag(SakuraItemTags.SEEDS_REDBEAN).add(ItemRegistry.RED_BEAN.get());
      this.tag(SakuraItemTags.SEEDS_SOYBEAN).add(ItemRegistry.SOYBEAN.get());
      this.tag(SakuraItemTags.SEEDS_TOMATO).add(ItemRegistry.TOMATO_SEEDS.get());
      this.tag(SakuraItemTags.YEAST).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.YEAST).get());
      this.tag(SakuraItemTags.BAMBOO).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BAMBOO).get());
      this.tag(SakuraItemTags.BAMBOO).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BAMBOO_SUNBURNT).get());
      this.tag(SakuraItemTags.BAMBOO).add(Items.BAMBOO);
      this.tag(SakuraItemTags.SLICES).addTag(SakuraItemTags.SLICES_CABBAGE).addTag(SakuraItemTags.SLICES_RAW_FISHES);
      this.tag(SakuraItemTags.SLICES_RAW_FISHES)
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.MACHINED_BONITO).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.MACHINED_FISH).get())
         .addTag(SakuraItemTags.SLICES_RAW_FISHES_COD)
         .addTag(SakuraItemTags.SLICES_RAW_FISHES_SALMON);
      this.tag(SakuraItemTags.SLICES_RAW_FISHES_COD).addOptional(new ResourceLocation("farmersdelight:cod_slice"));
      this.tag(SakuraItemTags.SLICES_RAW_FISHES_SALMON).addOptional(new ResourceLocation("farmersdelight:salmon_slice"));
      this.tag(SakuraItemTags.SLICES_CABBAGE).add(FoodRegistry.FOODSET.get(SakuraFoodSet.SLICED_CABBAGE).get());
      this.tag(SakuraItemTags.DUST_CHARCOAL).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.CHARCOAL_POWDER).get());
      this.tag(SakuraItemTags.OFFHAND_EQUIPMENT).add(Items.SHIELD);
      this.tag(SakuraItemTags.NATTO).add(FoodRegistry.FOODSET.get(SakuraFoodSet.NATTO).get());
      this.tag(SakuraItemTags.SHRIMP).add(FoodRegistry.FOODSET.get(SakuraFoodSet.SHRIMP).get());
      this.tag(SakuraItemTags.FISHCAKE)
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.FISHCAKE).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.CHIKUWA).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.KAMABOKO).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.SATSUMAAGE).get());
      this.tag(SakuraItemTags.KOUJI).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.KOUJI).get());
      this.tag(SakuraItemTags.VINEGAR)
         .add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.RED_VINEGAR).get())
         .add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.VINEGAR).get());
      this.tag(SakuraItemTags.TOMATOSAUCE).add(FoodRegistry.FOODSET.get(SakuraFoodSet.TOMATO_SAUCE).get());
      this.tag(SakuraItemTags.TOFU).add(FoodRegistry.FOODSET.get(SakuraFoodSet.TOFU).get());
      this.tag(SakuraItemTags.TOFU_FRIED).add(FoodRegistry.FOODSET.get(SakuraFoodSet.TOFU_FRIED).get());
      this.tag(SakuraItemTags.SOYSAUCE).addTag(SakuraItemTags.SOYSAUCE_SOYSAUCE);
      this.tag(SakuraItemTags.SOYSAUCE_SOYSAUCE).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.SOYSAUCE).get());
      this.tag(SakuraItemTags.MISO).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.MISO).get());
      this.tag(SakuraItemTags.DASHI).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.DASHI).get());
      this.tag(SakuraItemTags.SOUPS)
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.SOUP_MISO).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.SOUP_REDBEAN).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.OSUIMONO).get());
      this.tag(SakuraItemTags.CROPS_SOYBEAN).add(ItemRegistry.SOYBEAN.get());
      this.tag(SakuraItemTags.CROPS_REDBEAN).add(ItemRegistry.RED_BEAN.get());
      this.tag(SakuraItemTags.CROPS_BUCKWHEAT).add(ItemRegistry.BUCKWHEAT.get());
      this.tag(SakuraItemTags.CROPS_RICE).add(ItemRegistry.RICE_SEEDS.get());
      this.tag(SakuraItemTags.CROPS_TARO).add(ItemRegistry.TARO.get());
      this.tag(SakuraItemTags.CROPS_CABBAGE).add(FoodRegistry.FOODSET.get(SakuraFoodSet.CABBAGE).get());
      this.tag(SakuraItemTags.CROPS_EGGPLANT).add(FoodRegistry.FOODSET.get(SakuraFoodSet.EGGPLANT).get());
      this.tag(SakuraItemTags.CROPS_ONION).add(FoodRegistry.FOODSET.get(SakuraFoodSet.ONION).get());
      this.tag(SakuraItemTags.CROPS_RADISH).add(FoodRegistry.FOODSET.get(SakuraFoodSet.RADISH).get());
      this.tag(SakuraItemTags.CROPS_TOMATO).add(FoodRegistry.FOODSET.get(SakuraFoodSet.TOMATO).get());
      this.tag(SakuraItemTags.CROPS_RICE).add(ItemRegistry.RICE_SEEDS.get());
      this.tag(SakuraItemTags.CROPS_TARO).add(ItemRegistry.TARO.get());
      this.tag(SakuraItemTags.VEGETABLES_CABBAGE).add(FoodRegistry.FOODSET.get(SakuraFoodSet.CABBAGE).get());
      this.tag(SakuraItemTags.VEGETABLES_EGGPLANT).add(FoodRegistry.FOODSET.get(SakuraFoodSet.EGGPLANT).get());
      this.tag(SakuraItemTags.VEGETABLES_ONION).add(FoodRegistry.FOODSET.get(SakuraFoodSet.ONION).get());
      this.tag(SakuraItemTags.VEGETABLES_RADISH).add(FoodRegistry.FOODSET.get(SakuraFoodSet.RADISH).get());
      this.tag(SakuraItemTags.VEGETABLES_TOMATO).add(FoodRegistry.FOODSET.get(SakuraFoodSet.TOMATO).get());
      this.tag(SakuraItemTags.RICE_BROWN).addTag(SakuraItemTags.GRAIN_RICE);
      this.tag(SakuraItemTags.RICE_RICE).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.RICE).get());
      this.tag(SakuraItemTags.STRAW)
         .add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.STRAW).get())
         .addOptional(new ResourceLocation("farmersdelight:straw"));
      this.tag(SakuraItemTags.RICE).addTag(SakuraItemTags.RICE_BROWN).addTag(SakuraItemTags.RICE_RICE);
      this.tag(SakuraItemTags.LUMBER).addTag(SakuraItemTags.LUMBER_BAMBOO).addTag(SakuraItemTags.LUMBER_MAPLE).addTag(SakuraItemTags.LUMBER_SAKURA);
      this.tag(SakuraItemTags.LUMBER_TFC).addTag(SakuraItemTags.LUMBER);
      this.tag(SakuraItemTags.LUMBER_BAMBOO).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.LUMBER_BAMBOO).get());
      this.tag(SakuraItemTags.LUMBER_MAPLE).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.LUMBER_MAPLE).get());
      this.tag(SakuraItemTags.LUMBER_SAKURA).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.LUMBER_SAKURA).get());
      this.tag(SakuraItemTags.GRAIN_RICE).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.BROWN_RICE).get());
      this.tag(SakuraItemTags.GRAIN_BUCKWHEAT).add(ItemRegistry.BUCKWHEAT.get());
      this.tag(SakuraItemTags.SALT).addTag(SakuraItemTags.DUST_SALT);
      this.tag(SakuraItemTags.DUST_SALT).addTag(SakuraItemTags.SALT_SALT);
      this.tag(SakuraItemTags.SALT_SALT).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.SALT).get());
      this.tag(SakuraItemTags.SUGAR).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.MIRIN_KASU).get()).addTag(SakuraItemTags.SUGAR_SUGAR);
      this.tag(SakuraItemTags.SUGAR_SUGAR).add(Items.SUGAR);
      this.tag(SakuraItemTags.CHEESE).addTag(SakuraItemTags.CHEESE_CHEESE);
      this.tag(SakuraItemTags.CHEESE_CHEESE).add(FoodRegistry.FOODSET.get(SakuraFoodSet.CHEESE).get());
      this.tag(SakuraItemTags.WATER).addTag(SakuraItemTags.WATER_WATER);
      this.tag(SakuraItemTags.WATER_WATER).add(Items.WATER_BUCKET);
      this.tag(SakuraItemTags.FLOUR).addTags(SakuraItemTags.FLOUR_WHEAT, SakuraItemTags.FLOUR_BUCKWHEAT, SakuraItemTags.FLOUR_RICE);
      this.tag(SakuraItemTags.FLOUR_WHEAT).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.FLOUR).get());
      this.tag(SakuraItemTags.FLOUR_BUCKWHEAT).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.FLOUR_BUCKWHEAT).get());
      this.tag(SakuraItemTags.FLOUR_RICE).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.FLOUR_RICE).get());
      this.tag(SakuraItemTags.DOUGH).addTags(SakuraItemTags.DOUGH_WHEAT, SakuraItemTags.DOUGH_BUCKWHEAT, SakuraItemTags.DOUGH_RICE);
      this.tag(SakuraItemTags.DOUGH_WHEAT).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.DOUGH).get());
      this.tag(SakuraItemTags.DOUGH_BUCKWHEAT).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.DOUGH_BUCKWHEAT).get());
      this.tag(SakuraItemTags.DOUGH_RICE).add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.DOUGH_RICE).get());
      this.registerForgeTags();
   }

   private void registerForgeTags() {
      this.tag(SakuraItemTags.SEEDS)
         .addTag(SakuraItemTags.SEEDS_CABBAGE)
         .addTag(SakuraItemTags.SEEDS_ONION)
         .addTag(SakuraItemTags.SEEDS_EGGPLANT)
         .addTag(SakuraItemTags.SEEDS_RADISH)
         .addTag(SakuraItemTags.SEEDS_RICE)
         .addTag(SakuraItemTags.SEEDS_TOMATO)
         .addTag(SakuraItemTags.SEEDS_BUCKWHEAT)
         .addTag(SakuraItemTags.SEEDS_RAPESEED)
         .addTag(SakuraItemTags.SEEDS_REDBEAN);
      this.tag(SakuraItemTags.CROPS)
         .addTag(SakuraItemTags.CROPS_CABBAGE)
         .addTag(SakuraItemTags.CROPS_ONION)
         .addTag(SakuraItemTags.CROPS_BUCKWHEAT)
         .addTag(SakuraItemTags.CROPS_EGGPLANT)
         .addTag(SakuraItemTags.CROPS_RADISH)
         .addTag(SakuraItemTags.CROPS_RICE)
         .addTag(SakuraItemTags.CROPS_TOMATO)
         .addTag(SakuraItemTags.CROPS_TARO)
         .addTag(SakuraItemTags.SEEDS_RAPESEED)
         .addTag(SakuraItemTags.CROPS_REDBEAN)
         .addTag(SakuraItemTags.CROPS_PUMPKIN);
      this.tag(SakuraItemTags.CROPS_PUMPKIN).add(Items.PUMPKIN);
      this.tag(SakuraItemTags.VEGETABLES_PUMPKIN).add(Items.PUMPKIN);
      this.tag(SakuraItemTags.VEGETABLES)
         .addTag(SakuraItemTags.VEGETABLES_CABBAGE)
         .addTag(SakuraItemTags.VEGETABLES_BEETROOT)
         .addTag(SakuraItemTags.VEGETABLES_CARROT)
         .addTag(SakuraItemTags.VEGETABLES_EGGPLANT)
         .addTag(SakuraItemTags.VEGETABLES_ONION)
         .addTag(SakuraItemTags.VEGETABLES_POTATO)
         .addTag(SakuraItemTags.VEGETABLES_RADISH)
         .addTag(SakuraItemTags.VEGETABLES_TOMATO)
         .addTag(SakuraItemTags.VEGETABLES_PUMPKIN)
         .add(ItemRegistry.MATERIALS.get(SakuraNormalItemSet.IMOGARA).get());
      this.tag(SakuraItemTags.LEAFYVEGETABLES).addTag(SakuraItemTags.LEAFYVEGETABLES_CABBAGE);
      this.tag(SakuraItemTags.LEAFYVEGETABLES_CABBAGE).addTag(SakuraItemTags.VEGETABLES_CABBAGE);
      this.tag(SakuraItemTags.MUSHROOMS).addTags(new TagKey[]{SakuraItemTags.BROWN_MUSHROOMS, SakuraItemTags.RED_MUSHROOMS});
      this.tag(SakuraItemTags.BROWN_MUSHROOMS).add(Items.BROWN_MUSHROOM);
      this.tag(SakuraItemTags.RED_MUSHROOMS).add(Items.RED_MUSHROOM);
      this.tag(SakuraItemTags.VEGETABLES_BEETROOT).add(Items.BEETROOT);
      this.tag(SakuraItemTags.VEGETABLES_CARROT).add(Items.CARROT);
      this.tag(SakuraItemTags.VEGETABLES_POTATO).add(Items.POTATO);
      this.tag(SakuraItemTags.COOKIES).add(Items.COOKIE);
      this.tag(SakuraItemTags.BREAD).addTags(SakuraItemTags.BREAD_WHEAT, SakuraItemTags.BREAD_BUCKWHEAT, SakuraItemTags.BREAD_RICE);
      this.tag(SakuraItemTags.BREAD_WHEAT).add(Items.BREAD, FoodRegistry.FOODSET.get(SakuraFoodSet.BUN).get());
      this.tag(SakuraItemTags.BREAD_BUCKWHEAT).add(FoodRegistry.FOODSET.get(SakuraFoodSet.BUCKWHEAT_BREAD).get());
      this.tag(SakuraItemTags.BREAD_RICE).add(FoodRegistry.FOODSET.get(SakuraFoodSet.RICE_BREAD).get());
      this.tag(SakuraItemTags.COOKED_BEEF).add(Items.COOKED_BEEF);
      this.tag(SakuraItemTags.COOKED_CHICKEN).add(Items.COOKED_CHICKEN);
      this.tag(SakuraItemTags.COOKED_PORK).add(Items.COOKED_PORKCHOP);
      this.tag(SakuraItemTags.COOKED_MUTTON).add(Items.COOKED_MUTTON);
      this.tag(SakuraItemTags.COOKED_FISHES)
         .addTags(SakuraItemTags.COOKED_FISHES_COD, SakuraItemTags.COOKED_FISHES_SALMON)
         .addTags(SakuraItemTags.FISHCAKE);
      this.tag(SakuraItemTags.COOKED_FISHES_COD).add(Items.COOKED_COD);
      this.tag(SakuraItemTags.COOKED_FISHES_SALMON).add(Items.COOKED_SALMON);
      this.tag(SakuraItemTags.EGGS).add(Items.EGG);
      this.tag(SakuraItemTags.GRAIN).addTags(SakuraItemTags.GRAIN_WHEAT, SakuraItemTags.GRAIN_RICE, SakuraItemTags.GRAIN_BUCKWHEAT);
      this.tag(SakuraItemTags.GRAIN_WHEAT).add(Items.WHEAT);
      this.tag(SakuraItemTags.MILK).addTags(SakuraItemTags.MILK_BUCKET);
      this.tag(SakuraItemTags.MILK_BUCKET).add(Items.MILK_BUCKET);
      this.tag(SakuraItemTags.RAW_BEEF).add(Items.BEEF);
      this.tag(SakuraItemTags.RAW_CHICKEN).add(Items.CHICKEN);
      this.tag(SakuraItemTags.RAW_PORK).add(Items.PORKCHOP);
      this.tag(SakuraItemTags.RAW_MUTTON).add(Items.MUTTON);
      this.tag(SakuraItemTags.FISHES).addTag(SakuraItemTags.RAW_FISHES);
      this.tag(SakuraItemTags.FRUITS).addTag(SakuraItemTags.FRUITS_APPLE).addTag(SakuraItemTags.FRUITS_BERRIES).addTag(SakuraItemTags.FRUITS_MELON_SLICE);
      this.tag(SakuraItemTags.FOODS_FRUITS)
         .addTag(SakuraItemTags.FOODS_FRUITS_APPLE)
         .addTag(SakuraItemTags.FOODS_FRUITS_BERRIES)
         .addTag(SakuraItemTags.FOODS_FRUITS_MELON_SLICE);
      this.tag(SakuraItemTags.FOODS_FRUITS_APPLE).addTag(SakuraItemTags.FRUITS_APPLE);
      this.tag(SakuraItemTags.FOODS_FRUITS_BERRIES).addTag(SakuraItemTags.FRUITS_BERRIES);
      this.tag(SakuraItemTags.FOODS_FRUITS_MELON_SLICE).addTag(SakuraItemTags.FRUITS_MELON_SLICE);
      this.tag(SakuraItemTags.FRUITS_APPLE).add(Items.APPLE);
      this.tag(SakuraItemTags.FRUITS_BERRIES).add(Items.SWEET_BERRIES).add(Items.GLOW_BERRIES);
      this.tag(SakuraItemTags.FRUITS_MELON_SLICE).add(Items.MELON_SLICE);
      this.tag(SakuraItemTags.FOODS_RAW_MEATS).addTag(SakuraItemTags.FOODS_RAW_MEAT);
      this.tag(SakuraItemTags.FOODS_RAW_MEAT)
         .addTags(
            SakuraItemTags.RAW_FISHES, SakuraItemTags.RAW_MUTTON, SakuraItemTags.RAW_PORK, SakuraItemTags.RAW_CHICKEN, SakuraItemTags.RAW_BEEF
         );
      this.tag(SakuraItemTags.FOODS_COOKED_MEATS).addTag(SakuraItemTags.FOODS_COOKED_MEAT);
      this.tag(SakuraItemTags.FOODS_COOKED_MEAT)
         .addTags(
            SakuraItemTags.COOKED_FISHES,
            SakuraItemTags.COOKED_MUTTON,
            SakuraItemTags.COOKED_PORK,
            SakuraItemTags.COOKED_CHICKEN,
            SakuraItemTags.COOKED_BEEF
         );
      this.tag(SakuraItemTags.RAW_FISHES)
         .addTags(
            SakuraItemTags.SLICES_RAW_FISHES,
            SakuraItemTags.RAW_FISHES_COD,
            SakuraItemTags.RAW_FISHES_SALMON,
            SakuraItemTags.RAW_FISHES_TROPICAL,
            SakuraItemTags.RAW_FISHES_BONITO
         )
         .addTags(SakuraItemTags.FISHCAKE);
      this.tag(SakuraItemTags.RAW_FISHES_COD).add(Items.COD);
      this.tag(SakuraItemTags.RAW_FISHES_SALMON).add(Items.SALMON);
      this.tag(SakuraItemTags.RAW_FISHES_BONITO).add(SakuraFoodSet.BONITO.getItem().get());
      this.tag(SakuraItemTags.RAW_FISHES_TROPICAL).add(Items.TROPICAL_FISH);
      this.tag(SakuraItemTags.SALAD_INGREDIENTS).addTags(SakuraItemTags.SALAD_INGREDIENTS_CABBAGE, SakuraItemTags.SALAD_INGREDIENTS_TOMATO);
      this.tag(SakuraItemTags.SALAD_INGREDIENTS_CABBAGE)
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.CABBAGE).get())
         .add(FoodRegistry.FOODSET.get(SakuraFoodSet.SLICED_CABBAGE).get());
      this.tag(SakuraItemTags.SALAD_INGREDIENTS_TOMATO).add(FoodRegistry.FOODSET.get(SakuraFoodSet.TOMATO).get());
      this.tag(SakuraItemTags.TOOLS).addTags(SakuraItemTags.TOOLS_AXES, SakuraItemTags.TOOLS_PICKAXES, SakuraItemTags.TOOLS_SHOVELS);
      this.tag(SakuraItemTags.TOOLS_AXES)
         .add(Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE, Items.NETHERITE_AXE);
      this.tag(SakuraItemTags.TOOLS_PICKAXES)
         .add(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE, Items.NETHERITE_PICKAXE);
      this.tag(SakuraItemTags.TOOLS_SHOVELS)
         .add(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.GOLDEN_SHOVEL, Items.NETHERITE_SHOVEL);
   }

   public String getName() {
      return "Sakura Items' Tags";
   }
}
