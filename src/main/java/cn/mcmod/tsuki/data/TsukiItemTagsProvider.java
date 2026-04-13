package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.item.armors.TsukiArmorToolRegistry;
import cn.mcmod.tsuki.tags.TsukiItemTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiItemTagsProvider extends ItemTagsProvider {
   public TsukiItemTagsProvider(
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
            BlockItemRegistry.MAPLE_SAP_LOG.get(),
            BlockItemRegistry.SAKURA_LOG.get(),
            BlockItemRegistry.MAPLE_WOOD.get(),
            BlockItemRegistry.SAKURA_WOOD.get(),
            BlockItemRegistry.STRIPPED_MAPLE_LOG.get(),
            BlockItemRegistry.STRIPPED_SAKURA_LOG.get()
         );
      this.tag(ItemTags.COALS).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get());
      this.tag(TsukiItemTags.TOOLS_KNIVES).addTag(TsukiItemTags.TOOLS_KNIVES_FISH).addTag(TsukiItemTags.TOOLS_KNIVES_NOODLE);
      this.tag(TsukiItemTags.TOOLS_KNIVES_FISH).add(TsukiArmorToolRegistry.IRON_FISH_KNIFE.get(), TsukiArmorToolRegistry.SAKURA_FISH_KNIFE.get());
      this.tag(TsukiItemTags.TOOLS_KNIVES_NOODLE).add(TsukiArmorToolRegistry.IRON_NOODLE_KNIFE.get(), TsukiArmorToolRegistry.SAKURA_NOODLE_KNIFE.get());
      this.tag(TsukiItemTags.SEEDS_RICE).add(ItemRegistry.RICE_SEEDS.get());
      this.tag(TsukiItemTags.SEEDS_CABBAGE).add(ItemRegistry.CABBAGE_SEEDS.get());
      this.tag(TsukiItemTags.SEEDS_EGGPLANT).add(ItemRegistry.EGGPLANT_SEEDS.get());
      this.tag(TsukiItemTags.SEEDS_BUCKWHEAT).add(ItemRegistry.BUCKWHEAT.get());
      this.tag(TsukiItemTags.SEEDS_ONION).add(ItemRegistry.ONION_SEEDS.get());
      this.tag(TsukiItemTags.SEEDS_RADISH).add(ItemRegistry.RADISH_SEEDS.get());
      this.tag(TsukiItemTags.SEEDS_RAPESEED).add(ItemRegistry.RAPESEEDS.get());
      this.tag(TsukiItemTags.SEEDS_REDBEAN).add(ItemRegistry.RED_BEAN.get());
      this.tag(TsukiItemTags.SEEDS_SOYBEAN).add(ItemRegistry.SOYBEAN.get());
      this.tag(TsukiItemTags.SEEDS_TOMATO).add(ItemRegistry.TOMATO_SEEDS.get());
      this.tag(TsukiItemTags.YEAST).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.YEAST).get());
      this.tag(TsukiItemTags.BAMBOO).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get());
      this.tag(TsukiItemTags.BAMBOO).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get());
      this.tag(TsukiItemTags.BAMBOO).add(Items.BAMBOO);
      this.tag(TsukiItemTags.SLICES).addTag(TsukiItemTags.SLICES_CABBAGE).addTag(TsukiItemTags.SLICES_RAW_FISHES);
      this.tag(TsukiItemTags.SLICES_RAW_FISHES)
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.MACHINED_BONITO).get())
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.MACHINED_FISH).get())
         .addTag(TsukiItemTags.SLICES_RAW_FISHES_COD)
         .addTag(TsukiItemTags.SLICES_RAW_FISHES_SALMON);
      this.tag(TsukiItemTags.SLICES_RAW_FISHES_COD).addOptional(ResourceLocation.parse("farmersdelight:cod_slice"));
      this.tag(TsukiItemTags.SLICES_RAW_FISHES_SALMON).addOptional(ResourceLocation.parse("farmersdelight:salmon_slice"));
      this.tag(TsukiItemTags.SLICES_CABBAGE).add(FoodRegistry.FOODSET.get(TsukiFoodSet.SLICED_CABBAGE).get());
      this.tag(TsukiItemTags.DUST_CHARCOAL).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.CHARCOAL_POWDER).get());
      this.tag(TsukiItemTags.OFFHAND_EQUIPMENT).add(Items.SHIELD);
      this.tag(TsukiItemTags.NATTO).add(FoodRegistry.FOODSET.get(TsukiFoodSet.NATTO).get());
      this.tag(TsukiItemTags.SHRIMP).add(FoodRegistry.FOODSET.get(TsukiFoodSet.SHRIMP).get());
      this.tag(TsukiItemTags.FISHCAKE)
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.FISHCAKE).get())
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.CHIKUWA).get())
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.KAMABOKO).get())
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.SATSUMAAGE).get());
      this.tag(TsukiItemTags.KOUJI).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KOUJI).get());
      this.tag(TsukiItemTags.VINEGAR)
         .add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RED_VINEGAR).get())
         .add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VINEGAR).get());
      this.tag(TsukiItemTags.TOMATOSAUCE).add(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO_SAUCE).get());
      this.tag(TsukiItemTags.TOFU).add(FoodRegistry.FOODSET.get(TsukiFoodSet.TOFU).get());
      this.tag(TsukiItemTags.TOFU_FRIED).add(FoodRegistry.FOODSET.get(TsukiFoodSet.TOFU_FRIED).get());
      this.tag(TsukiItemTags.SOYSAUCE).addTag(TsukiItemTags.SOYSAUCE_SOYSAUCE);
      this.tag(TsukiItemTags.SOYSAUCE_SOYSAUCE).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SOYSAUCE).get());
      this.tag(TsukiItemTags.MISO).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MISO).get());
      this.tag(TsukiItemTags.DASHI).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DASHI).get());
      this.tag(TsukiItemTags.SOUPS)
         .add(FoodRegistry.CUISINES.get(TsukiCuisineSet.SOUP_MISO).get())
         .add(FoodRegistry.CUISINES.get(TsukiCuisineSet.SOUP_REDBEAN).get())
         .add(FoodRegistry.CUISINES.get(TsukiCuisineSet.OSUIMONO).get());
      this.tag(TsukiItemTags.CROPS_SOYBEAN).add(ItemRegistry.SOYBEAN.get());
      this.tag(TsukiItemTags.CROPS_REDBEAN).add(ItemRegistry.RED_BEAN.get());
      this.tag(TsukiItemTags.CROPS_BUCKWHEAT).add(ItemRegistry.BUCKWHEAT.get());
      this.tag(TsukiItemTags.CROPS_RICE).add(ItemRegistry.RICE_SEEDS.get());
      this.tag(TsukiItemTags.CROPS_TARO).add(ItemRegistry.TARO.get());
      this.tag(TsukiItemTags.CROPS_CABBAGE).add(FoodRegistry.FOODSET.get(TsukiFoodSet.CABBAGE).get());
      this.tag(TsukiItemTags.CROPS_EGGPLANT).add(FoodRegistry.FOODSET.get(TsukiFoodSet.EGGPLANT).get());
      this.tag(TsukiItemTags.CROPS_ONION).add(FoodRegistry.FOODSET.get(TsukiFoodSet.ONION).get());
      this.tag(TsukiItemTags.CROPS_RADISH).add(FoodRegistry.FOODSET.get(TsukiFoodSet.RADISH).get());
      this.tag(TsukiItemTags.CROPS_TOMATO).add(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO).get());
      this.tag(TsukiItemTags.CROPS_RICE).add(ItemRegistry.RICE_SEEDS.get());
      this.tag(TsukiItemTags.CROPS_TARO).add(ItemRegistry.TARO.get());
      this.tag(TsukiItemTags.VEGETABLES_CABBAGE).add(FoodRegistry.FOODSET.get(TsukiFoodSet.CABBAGE).get());
      this.tag(TsukiItemTags.VEGETABLES_EGGPLANT).add(FoodRegistry.FOODSET.get(TsukiFoodSet.EGGPLANT).get());
      this.tag(TsukiItemTags.VEGETABLES_ONION).add(FoodRegistry.FOODSET.get(TsukiFoodSet.ONION).get());
      this.tag(TsukiItemTags.VEGETABLES_RADISH).add(FoodRegistry.FOODSET.get(TsukiFoodSet.RADISH).get());
      this.tag(TsukiItemTags.VEGETABLES_TOMATO).add(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO).get());
      this.tag(TsukiItemTags.RICE_BROWN).addTag(TsukiItemTags.GRAIN_RICE);
      this.tag(TsukiItemTags.RICE_RICE).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RICE).get());
      this.tag(TsukiItemTags.STRAW)
         .add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW).get())
         .addOptional(ResourceLocation.parse("farmersdelight:straw"));
      this.tag(TsukiItemTags.RICE).addTag(TsukiItemTags.RICE_BROWN).addTag(TsukiItemTags.RICE_RICE);
      this.tag(TsukiItemTags.LUMBER).addTag(TsukiItemTags.LUMBER_BAMBOO).addTag(TsukiItemTags.LUMBER_MAPLE).addTag(TsukiItemTags.LUMBER_SAKURA);
      this.tag(TsukiItemTags.LUMBER_TFC).addTag(TsukiItemTags.LUMBER);
      this.tag(TsukiItemTags.LUMBER_BAMBOO).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_BAMBOO).get());
      this.tag(TsukiItemTags.LUMBER_MAPLE).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_MAPLE).get());
      this.tag(TsukiItemTags.LUMBER_SAKURA).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.LUMBER_SAKURA).get());
      this.tag(TsukiItemTags.GRAIN_RICE).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BROWN_RICE).get());
      this.tag(TsukiItemTags.GRAIN_BUCKWHEAT).add(ItemRegistry.BUCKWHEAT.get());
      this.tag(TsukiItemTags.SALT).addTag(TsukiItemTags.DUST_SALT);
      this.tag(TsukiItemTags.DUST_SALT).addTag(TsukiItemTags.SALT_SALT);
      this.tag(TsukiItemTags.SALT_SALT).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SALT).get());
      this.tag(TsukiItemTags.SUGAR)
        .add(TsukiNormalItemSet.MIRIN_KASU.getItem().get())
        .add(TsukiNormalItemSet.MAPLE_SYRUP.getItem().get())
        .addTag(TsukiItemTags.SUGAR_SUGAR);
      this.tag(TsukiItemTags.SUGAR_SUGAR).add(Items.SUGAR);
      this.tag(TsukiItemTags.CHEESE).addTag(TsukiItemTags.CHEESE_CHEESE);
      this.tag(TsukiItemTags.CHEESE_CHEESE).add(FoodRegistry.FOODSET.get(TsukiFoodSet.CHEESE).get());
      this.tag(TsukiItemTags.WATER).addTag(TsukiItemTags.WATER_WATER);
      this.tag(TsukiItemTags.WATER_WATER).add(Items.WATER_BUCKET);
      this.tag(TsukiItemTags.FLOUR).addTags(TsukiItemTags.FLOUR_WHEAT, TsukiItemTags.FLOUR_BUCKWHEAT, TsukiItemTags.FLOUR_RICE);
      this.tag(TsukiItemTags.FLOUR_WHEAT).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FLOUR).get());
      this.tag(TsukiItemTags.FLOUR_BUCKWHEAT).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FLOUR_BUCKWHEAT).get());
      this.tag(TsukiItemTags.FLOUR_RICE).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.FLOUR_RICE).get());
      this.tag(TsukiItemTags.DOUGH).addTags(TsukiItemTags.DOUGH_WHEAT, TsukiItemTags.DOUGH_BUCKWHEAT, TsukiItemTags.DOUGH_RICE);
      this.tag(TsukiItemTags.DOUGH_WHEAT).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH).get());
      this.tag(TsukiItemTags.DOUGH_BUCKWHEAT).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH_BUCKWHEAT).get());
      this.tag(TsukiItemTags.DOUGH_RICE).add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DOUGH_RICE).get());
      this.tag(TsukiItemTags.KIMONO).add(
        TsukiArmorToolRegistry.KIMONO_BLACK.get(),
        TsukiArmorToolRegistry.KIMONO_GREEN.get(),
        TsukiArmorToolRegistry.KIMONO_CYAN.get(),
        TsukiArmorToolRegistry.KIMONO_PURPLE.get(),
        TsukiArmorToolRegistry.KIMONO_SAKURA.get(),
        TsukiArmorToolRegistry.KIMONO_WHITE.get(),
        TsukiArmorToolRegistry.KIMONO_BROWN.get(),
        TsukiArmorToolRegistry.KIMONO_ENE.get(),
        TsukiArmorToolRegistry.KIMONO_MIKO.get(),
        TsukiArmorToolRegistry.YUKATA_BLUE.get(),
        TsukiArmorToolRegistry.YUKATA_RED.get(),
        TsukiArmorToolRegistry.YUKATA_LIME.get(),
        TsukiArmorToolRegistry.YUKATA_YELLOW.get(),
        TsukiArmorToolRegistry.YUKATA_MAGENTA.get()
      );
      this.tag(TsukiItemTags.HAORI).add(
        TsukiArmorToolRegistry.HAORI_BLACK.get(),
        TsukiArmorToolRegistry.HAORI_GREEN.get(),
        TsukiArmorToolRegistry.HAORI_BROWN.get(),
        TsukiArmorToolRegistry.HAORI_CYAN.get(),
        TsukiArmorToolRegistry.HAORI_LIGHT_BLUE.get()
      );
      this.tag(TsukiItemTags.SAMURAI_HELMET).add(
        TsukiArmorToolRegistry.SAMURAI_HELMET_RED.get(),
        TsukiArmorToolRegistry.SAMURAI_HELMET_GREEN.get(),
        TsukiArmorToolRegistry.SAMURAI_HELMET_BLACK.get()
      );
      this.tag(TsukiItemTags.SAMURAI_CHESTPLATE).add(
        TsukiArmorToolRegistry.SAMURAI_CHESTPLATE_RED.get(),
        TsukiArmorToolRegistry.SAMURAI_CHESTPLATE_GREEN.get(),
        TsukiArmorToolRegistry.SAMURAI_CHESTPLATE_BLACK.get()
      );
      this.tag(TsukiItemTags.SAMURAI_LEGGINGS).add(
        TsukiArmorToolRegistry.SAMURAI_LEGGINGS_RED.get(),
        TsukiArmorToolRegistry.SAMURAI_LEGGINGS_GREEN.get(),
        TsukiArmorToolRegistry.SAMURAI_LEGGINGS_BLACK.get()
      );
      this.tag(TsukiItemTags.SAMURAI_BOOTS).add(
        TsukiArmorToolRegistry.SAMURAI_BOOTS_RED.get(),
        TsukiArmorToolRegistry.SAMURAI_BOOTS_GREEN.get(),
        TsukiArmorToolRegistry.SAMURAI_BOOTS_BLACK.get()
      );
      this.registerForgeTags();
   }

   private void registerForgeTags() {
      this.tag(TsukiItemTags.SEEDS)
         .addTag(TsukiItemTags.SEEDS_CABBAGE)
         .addTag(TsukiItemTags.SEEDS_ONION)
         .addTag(TsukiItemTags.SEEDS_EGGPLANT)
         .addTag(TsukiItemTags.SEEDS_RADISH)
         .addTag(TsukiItemTags.SEEDS_RICE)
         .addTag(TsukiItemTags.SEEDS_TOMATO)
         .addTag(TsukiItemTags.SEEDS_BUCKWHEAT)
         .addTag(TsukiItemTags.SEEDS_RAPESEED)
         .addTag(TsukiItemTags.SEEDS_REDBEAN);
      this.tag(TsukiItemTags.CROPS)
         .addTag(TsukiItemTags.CROPS_CABBAGE)
         .addTag(TsukiItemTags.CROPS_ONION)
         .addTag(TsukiItemTags.CROPS_BUCKWHEAT)
         .addTag(TsukiItemTags.CROPS_EGGPLANT)
         .addTag(TsukiItemTags.CROPS_RADISH)
         .addTag(TsukiItemTags.CROPS_RICE)
         .addTag(TsukiItemTags.CROPS_TOMATO)
         .addTag(TsukiItemTags.CROPS_TARO)
         .addTag(TsukiItemTags.SEEDS_RAPESEED)
         .addTag(TsukiItemTags.CROPS_REDBEAN)
         .addTag(TsukiItemTags.CROPS_PUMPKIN);
      this.tag(TsukiItemTags.CROPS_PUMPKIN).add(Items.PUMPKIN);
      this.tag(TsukiItemTags.VEGETABLES_PUMPKIN).add(Items.PUMPKIN);
      this.tag(TsukiItemTags.VEGETABLES)
         .addTag(TsukiItemTags.VEGETABLES_CABBAGE)
         .addTag(TsukiItemTags.VEGETABLES_BEETROOT)
         .addTag(TsukiItemTags.VEGETABLES_CARROT)
         .addTag(TsukiItemTags.VEGETABLES_EGGPLANT)
         .addTag(TsukiItemTags.VEGETABLES_ONION)
         .addTag(TsukiItemTags.VEGETABLES_POTATO)
         .addTag(TsukiItemTags.VEGETABLES_RADISH)
         .addTag(TsukiItemTags.VEGETABLES_TOMATO)
         .addTag(TsukiItemTags.VEGETABLES_PUMPKIN)
         .add(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.IMOGARA).get());
      this.tag(TsukiItemTags.LEAFYVEGETABLES).addTag(TsukiItemTags.LEAFYVEGETABLES_CABBAGE);
      this.tag(TsukiItemTags.LEAFYVEGETABLES_CABBAGE).addTag(TsukiItemTags.VEGETABLES_CABBAGE);
      this.tag(TsukiItemTags.MUSHROOMS).addTags(new TagKey[]{TsukiItemTags.BROWN_MUSHROOMS, TsukiItemTags.RED_MUSHROOMS});
      this.tag(TsukiItemTags.BROWN_MUSHROOMS).add(Items.BROWN_MUSHROOM);
      this.tag(TsukiItemTags.RED_MUSHROOMS).add(Items.RED_MUSHROOM);
      this.tag(TsukiItemTags.VEGETABLES_BEETROOT).add(Items.BEETROOT);
      this.tag(TsukiItemTags.VEGETABLES_CARROT).add(Items.CARROT);
      this.tag(TsukiItemTags.VEGETABLES_POTATO).add(Items.POTATO);
      this.tag(TsukiItemTags.COOKIES).add(Items.COOKIE);
      this.tag(TsukiItemTags.BREAD).addTags(TsukiItemTags.BREAD_WHEAT, TsukiItemTags.BREAD_BUCKWHEAT, TsukiItemTags.BREAD_RICE);
      this.tag(TsukiItemTags.BREAD_WHEAT).add(Items.BREAD, FoodRegistry.FOODSET.get(TsukiFoodSet.BUN).get());
      this.tag(TsukiItemTags.BREAD_BUCKWHEAT).add(FoodRegistry.FOODSET.get(TsukiFoodSet.BUCKWHEAT_BREAD).get());
      this.tag(TsukiItemTags.BREAD_RICE).add(FoodRegistry.FOODSET.get(TsukiFoodSet.RICE_BREAD).get());
      this.tag(TsukiItemTags.COOKED_BEEF).add(Items.COOKED_BEEF);
      this.tag(TsukiItemTags.COOKED_CHICKEN).add(Items.COOKED_CHICKEN);
      this.tag(TsukiItemTags.COOKED_PORK).add(Items.COOKED_PORKCHOP);
      this.tag(TsukiItemTags.COOKED_MUTTON).add(Items.COOKED_MUTTON);
      this.tag(TsukiItemTags.COOKED_FISHES)
         .addTags(TsukiItemTags.COOKED_FISHES_COD, TsukiItemTags.COOKED_FISHES_SALMON)
         .addTags(TsukiItemTags.FISHCAKE);
      this.tag(TsukiItemTags.COOKED_FISHES_COD).add(Items.COOKED_COD);
      this.tag(TsukiItemTags.COOKED_FISHES_SALMON).add(Items.COOKED_SALMON);
      this.tag(TsukiItemTags.EGGS).add(Items.EGG);
      this.tag(TsukiItemTags.GRAIN).addTags(TsukiItemTags.GRAIN_WHEAT, TsukiItemTags.GRAIN_RICE, TsukiItemTags.GRAIN_BUCKWHEAT);
      this.tag(TsukiItemTags.GRAIN_WHEAT).add(Items.WHEAT);
      this.tag(TsukiItemTags.MILK).addTags(TsukiItemTags.MILK_BUCKET);
      this.tag(TsukiItemTags.MILK_BUCKET).add(Items.MILK_BUCKET);
      this.tag(TsukiItemTags.RAW_BEEF).add(Items.BEEF);
      this.tag(TsukiItemTags.RAW_CHICKEN).add(Items.CHICKEN);
      this.tag(TsukiItemTags.RAW_PORK).add(Items.PORKCHOP);
      this.tag(TsukiItemTags.RAW_MUTTON).add(Items.MUTTON);
      this.tag(TsukiItemTags.FISHES).addTag(TsukiItemTags.RAW_FISHES);
      this.tag(TsukiItemTags.FRUITS).addTag(TsukiItemTags.FRUITS_APPLE).addTag(TsukiItemTags.FRUITS_BERRIES).addTag(TsukiItemTags.FRUITS_MELON_SLICE);
      this.tag(TsukiItemTags.FOODS_FRUITS)
         .addTag(TsukiItemTags.FOODS_FRUITS_APPLE)
         .addTag(TsukiItemTags.FOODS_FRUITS_BERRIES)
         .addTag(TsukiItemTags.FOODS_FRUITS_MELON_SLICE);
      this.tag(TsukiItemTags.FOODS_FRUITS_APPLE).addTag(TsukiItemTags.FRUITS_APPLE);
      this.tag(TsukiItemTags.FOODS_FRUITS_BERRIES).addTag(TsukiItemTags.FRUITS_BERRIES);
      this.tag(TsukiItemTags.FOODS_FRUITS_MELON_SLICE).addTag(TsukiItemTags.FRUITS_MELON_SLICE);
      this.tag(TsukiItemTags.FRUITS_APPLE).add(Items.APPLE);
      this.tag(TsukiItemTags.FRUITS_BERRIES).add(Items.SWEET_BERRIES).add(Items.GLOW_BERRIES);
      this.tag(TsukiItemTags.FRUITS_MELON_SLICE).add(Items.MELON_SLICE);
      this.tag(TsukiItemTags.FOODS_RAW_MEATS).addTag(TsukiItemTags.FOODS_RAW_MEAT);
      this.tag(TsukiItemTags.FOODS_RAW_MEAT)
         .addTags(
            TsukiItemTags.RAW_FISHES, TsukiItemTags.RAW_MUTTON, TsukiItemTags.RAW_PORK, TsukiItemTags.RAW_CHICKEN, TsukiItemTags.RAW_BEEF
         );
      this.tag(TsukiItemTags.FOODS_COOKED_MEATS).addTag(TsukiItemTags.FOODS_COOKED_MEAT);
      this.tag(TsukiItemTags.FOODS_COOKED_MEAT)
         .addTags(
            TsukiItemTags.COOKED_FISHES,
            TsukiItemTags.COOKED_MUTTON,
            TsukiItemTags.COOKED_PORK,
            TsukiItemTags.COOKED_CHICKEN,
            TsukiItemTags.COOKED_BEEF
         );
      this.tag(TsukiItemTags.RAW_FISHES)
         .addTags(
            TsukiItemTags.SLICES_RAW_FISHES,
            TsukiItemTags.RAW_FISHES_COD,
            TsukiItemTags.RAW_FISHES_SALMON,
            TsukiItemTags.RAW_FISHES_TROPICAL,
            TsukiItemTags.RAW_FISHES_BONITO
         )
         .addTags(TsukiItemTags.FISHCAKE);
      this.tag(TsukiItemTags.RAW_FISHES_COD).add(Items.COD);
      this.tag(TsukiItemTags.RAW_FISHES_SALMON).add(Items.SALMON);
      this.tag(TsukiItemTags.RAW_FISHES_BONITO).add(TsukiFoodSet.BONITO.getItem().get());
      this.tag(TsukiItemTags.RAW_FISHES_TROPICAL).add(Items.TROPICAL_FISH);
      this.tag(TsukiItemTags.SALAD_INGREDIENTS).addTags(TsukiItemTags.SALAD_INGREDIENTS_CABBAGE, TsukiItemTags.SALAD_INGREDIENTS_TOMATO);
      this.tag(TsukiItemTags.SALAD_INGREDIENTS_CABBAGE)
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.CABBAGE).get())
         .add(FoodRegistry.FOODSET.get(TsukiFoodSet.SLICED_CABBAGE).get());
      this.tag(TsukiItemTags.SALAD_INGREDIENTS_TOMATO).add(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO).get());
      this.tag(TsukiItemTags.TOOLS).addTags(TsukiItemTags.TOOLS_AXES, TsukiItemTags.TOOLS_PICKAXES, TsukiItemTags.TOOLS_SHOVELS);
      this.tag(TsukiItemTags.TOOLS_AXES)
         .add(Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE, Items.NETHERITE_AXE);
      this.tag(TsukiItemTags.TOOLS_PICKAXES)
         .add(Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE, Items.NETHERITE_PICKAXE);
      this.tag(TsukiItemTags.TOOLS_SHOVELS)
         .add(Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.GOLDEN_SHOVEL, Items.NETHERITE_SHOVEL);
   }

   public String getName() {
      return "Tsuki Items' Tags";
   }
}

