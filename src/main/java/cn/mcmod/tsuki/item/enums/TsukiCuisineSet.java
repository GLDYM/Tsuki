package cn.mcmod.tsuki.item.enums;

import java.util.function.Supplier;

import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod_mmf.mmlib.item.ItemFoodBase;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum TsukiCuisineSet {
    DANANKO(
            FoodInfo.builder()
                    .name("dananko")
                    .amountAndCalories(6, 0.6F)
                    .water(1.0F)
                    .nutrients(3.0F, 0.0F, 0.0F, 0.0F, 1.0F)
                    .compostChance(0.85F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO)),
    DANMITARASHI(
            FoodInfo.builder()
                    .name("danmitarashi")
                    .amountAndCalories(6, 0.4F)
                    .water(1.0F)
                    .compostChance(0.85F)
                    .nutrients(3.0F, 0.0F, 0.0F, 0.0F, 1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO)),
    DANSANSYOKU(
            FoodInfo.builder()
                    .name("dansansyoku")
                    .amountAndCalories(6, 0.6F)
                    .water(1.0F)
                    .compostChance(0.85F)
                    .nutrients(3.0F, 0.0F, 0.0F, 0.0F, 1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO)),
    BEEF_STICK(
            FoodInfo.builder()
                    .name("beef_stick")
                    .amountAndCalories(8, 0.8F)
                    .water(2.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO)),
    CHICKEN_STICK(
            FoodInfo.builder()
                    .name("chicken_stick")
                    .amountAndCalories(6, 0.4F)
                    .water(2.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO)),
    PORK_STICK(
            FoodInfo.builder()
                    .name("pork_stick")
                    .amountAndCalories(6, 0.6F)
                    .water(2.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build(),
            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO)),
    SOUP_REDBEAN(
            FoodInfo.builder()
                    .name("soup_red_bean")
                    .amountAndCalories(6, 0.6F)
                    .water(50.0F)
                    .compostChance(0.5F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(0.0F)
                    .build()),
    SOUP_MISO(
            FoodInfo.builder()
                    .name("soup_miso")
                    .amountAndCalories(5, 0.5F)
                    .water(50.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    OSUIMONO(
            FoodInfo.builder()
                    .name("osuimono")
                    .amountAndCalories(4, 0.5F)
                    .water(50.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CABBAGE_ROLL(
            FoodInfo.builder()
                    .name("cabbage_roll")
                    .amountAndCalories(4, 0.4F)
                    .water(25.0F)
                    .compostChance(1.0F)
                    .nutrients(0.0F, 0.0F, 4.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    NIKUJAGA(
            FoodInfo.builder()
                    .name("nikujaga")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 3.0F, 4.0F, 2.0F)
                    .compostChance(1.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHICKEN_NANBAN(
            FoodInfo.builder()
                    .name("chicken_nanban")
                    .amountAndCalories(8, 0.8F)
                    .water(0.0F)
                    .compostChance(1.0F)
                    .nutrients(0.5F, 0.0F, 2.0F, 4.0F, 2.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TOFU_NANBAN(
            FoodInfo.builder()
                    .name("tofu_nanban")
                    .amountAndCalories(8, 0.8F)
                    .water(0.0F)
                    .compostChance(1.0F)
                    .nutrients(0.5F, 0.0F, 2.0F, 4.0F, 2.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FUROFUKI_DAIKON(
            FoodInfo.builder()
                    .name("furofuki_daikon")
                    .amountAndCalories(5, 0.6F)
                    .water(25.0F)
                    .compostChance(1.0F)
                    .nutrients(0.0F, 0.0F, 4.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    NIMONO_PUMPKIN(
            FoodInfo.builder()
                    .name("nimono_pumpkin")
                    .amountAndCalories(6, 0.5F)
                    .water(5.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(1.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    NIMONO_RADISH(
            FoodInfo.builder()
                    .name("nimono_radish")
                    .amountAndCalories(6, 0.5F)
                    .water(5.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(1.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    NIMONO_FISH(
            FoodInfo.builder()
                    .name("nimono_fish")
                    .amountAndCalories(8, 1.0F)
                    .water(6.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 3.0F, 3.0F)
                    .compostChance(1.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHIKUZENNI(
            FoodInfo.builder()
                    .name("chikuzenni")
                    .amountAndCalories(12, 1.0F)
                    .water(5.0F)
                    .nutrients(0.0F, 5.0F, 5.0F, 5.0F, 5.0F)
                    .compostChance(1.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    IMOTAKI(
            FoodInfo.builder()
                    .name("imotaki")
                    .amountAndCalories(12, 1.0F)
                    .water(5.0F)
                    .nutrients(0.0F, 5.0F, 5.0F, 5.0F, 5.0F)
                    .compostChance(1.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    NOPPEI_JIRU(
            FoodInfo.builder()
                    .name("noppei_jiru")
                    .amountAndCalories(12, 1.0F)
                    .water(5.0F)
                    .nutrients(0.0F, 5.0F, 5.0F, 5.0F, 5.0F)
                    .compostChance(1.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN(
            FoodInfo.builder()
                    .name("ramen")
                    .amountAndCalories(4, 0.5F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_BEEF(
            FoodInfo.builder()
                    .name("ramen_beef")
                    .amountAndCalories(9, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_EGG(
            FoodInfo.builder()
                    .name("ramen_egg")
                    .amountAndCalories(5, 0.6F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_TEMPURA(
            FoodInfo.builder()
                    .name("ramen_tempura")
                    .amountAndCalories(9, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_FRIEDTOFU(
            FoodInfo.builder()
                    .name("ramen_friedtofu")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_FRIEDCHICKEN(
            FoodInfo.builder()
                    .name("ramen_chicken")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_CROQUETTE(
            FoodInfo.builder()
                    .name("ramen_croquette")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_KATSU(
            FoodInfo.builder()
                    .name("ramen_katsu")
                    .amountAndCalories(10, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_CURRY(
            FoodInfo.builder()
                    .name("ramen_curry")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAMEN_LARGE(
            FoodInfo.builder()
                    .name("ramen_large")
                    .amountAndCalories(12, 1.0F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 5.0F, 5.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON(
            FoodInfo.builder()
                    .name("udon")
                    .amountAndCalories(4, 0.5F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_BEEF(
            FoodInfo.builder()
                    .name("udon_beef")
                    .amountAndCalories(9, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_EGG(
            FoodInfo.builder()
                    .name("udon_egg")
                    .amountAndCalories(5, 0.6F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_TEMPURA(
            FoodInfo.builder()
                    .name("udon_tempura")
                    .amountAndCalories(9, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_FRIEDTOFU(
            FoodInfo.builder()
                    .name("udon_friedtofu")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_FRIEDCHICKEN(
            FoodInfo.builder()
                    .name("udon_chicken")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_CROQUETTE(
            FoodInfo.builder()
                    .name("udon_croquette")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_KATSU(
            FoodInfo.builder()
                    .name("udon_katsu")
                    .amountAndCalories(10, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_CURRY(
            FoodInfo.builder()
                    .name("udon_curry")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UDON_LARGE(
            FoodInfo.builder()
                    .name("udon_large")
                    .amountAndCalories(12, 1.0F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 5.0F, 5.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    YAKI_UDON(
            FoodInfo.builder()
                    .name("yaki_udon")
                    .amountAndCalories(9, 0.7F)
                    .water(2.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 4.0F, 2.0F, 2.0F)
                    .decayModifier(3.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA(
            FoodInfo.builder()
                    .name("soba")
                    .amountAndCalories(4, 0.5F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_BEEF(
            FoodInfo.builder()
                    .name("soba_beef")
                    .amountAndCalories(9, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_EGG(
            FoodInfo.builder()
                    .name("soba_egg")
                    .amountAndCalories(5, 0.6F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_TEMPURA(
            FoodInfo.builder()
                    .name("soba_tempura")
                    .amountAndCalories(9, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_FRIEDTOFU(
            FoodInfo.builder()
                    .name("soba_friedtofu")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_FRIEDCHICKEN(
            FoodInfo.builder()
                    .name("soba_chicken")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_CROQUETTE(
            FoodInfo.builder()
                    .name("soba_croquette")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_KATSU(
            FoodInfo.builder()
                    .name("soba_katsu")
                    .amountAndCalories(10, 0.8F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_CURRY(
            FoodInfo.builder()
                    .name("soba_curry")
                    .amountAndCalories(9, 0.7F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 3.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_LARGE(
            FoodInfo.builder()
                    .name("soba_large")
                    .amountAndCalories(12, 1.0F)
                    .water(35.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 5.0F, 5.0F, 2.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SOBA_ZARU(
            FoodInfo.builder()
                    .name("soba_zaru")
                    .amountAndCalories(6, 0.7F)
                    .water(5.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BROWN_RICE_COOKED(
            FoodInfo.builder()
                    .name("brown_rice_cooked")
                    .amountAndCalories(4, 0.5F)
                    .water(0.5F)
                    .compostChance(0.5F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_COOKED(
            FoodInfo.builder()
                    .name("rice_cooked")
                    .amountAndCalories(4, 0.5F)
                    .water(0.5F)
                    .compostChance(0.5F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_REDBEAN(
            FoodInfo.builder()
                    .name("rice_redbean")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .compostChance(0.85F)
                    .nutrients(4.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_BAMBOO(
            FoodInfo.builder()
                    .name("rice_bamboo")
                    .amountAndCalories(5, 0.6F)
                    .water(0.5F)
                    .compostChance(0.85F)
                    .nutrients(1.5F, 1.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_BEEF(
            FoodInfo.builder()
                    .name("rice_beef")
                    .amountAndCalories(9, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_FISH(
            FoodInfo.builder()
                    .name("rice_fish")
                    .amountAndCalories(7, 0.7F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_PORK(
            FoodInfo.builder()
                    .name("rice_pork")
                    .amountAndCalories(7, 0.7F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_KATSU(
            FoodInfo.builder()
                    .name("rice_pork_fried")
                    .amountAndCalories(10, 1.0F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 0.0F, 4.0F, 4.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_MUSHROOM(
            FoodInfo.builder()
                    .name("rice_mushroom")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_MATSUTAKE(
            FoodInfo.builder()
                    .name("rice_matsutake")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 3.0F, 0.0F, 1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_EGG(
            FoodInfo.builder()
                    .name("rice_egg")
                    .amountAndCalories(5, 0.6F)
                    .water(0.5F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .compostChance(0.85F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_BEEF_EGG(
            FoodInfo.builder()
                    .name("rice_beef_egg")
                    .amountAndCalories(10, 1.0F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.5F, 2.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_PORK_EGG(
            FoodInfo.builder()
                    .name("rice_pork_egg")
                    .amountAndCalories(9, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.5F, 2.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_OYAKO(
            FoodInfo.builder()
                    .name("rice_oyako")
                    .amountAndCalories(9, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.5F, 2.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_OYAKO_FISH(
            FoodInfo.builder()
                    .name("rice_oyako_fish")
                    .amountAndCalories(9, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 3.5F, 2.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_NATTO(
            FoodInfo.builder()
                    .name("rice_natto")
                    .amountAndCalories(5, 0.6F)
                    .water(0.5F)
                    .nutrients(2.5F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.85F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_NATTO_EGG(
            FoodInfo.builder()
                    .name("rice_natto_egg")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.5F, 0.0F, 3.0F, 0.0F, 3.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    OMURICE(
            FoodInfo.builder()
                    .name("omurice")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 3.0F, 3.0F, 2.0F)
                    .compostChance(1.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_FRIED(
            FoodInfo.builder()
                    .name("rice_fried")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .nutrients(1.5F, 0.0F, 2.0F, 2.0F, 0.0F)
                    .compostChance(1.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_CURRY(
            FoodInfo.builder()
                    .name("rice_curry")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.5F, 0.0F, 1.0F, 1.0F, 1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_CURRY_KATSU(
            FoodInfo.builder()
                    .name("rice_curry_katsu")
                    .amountAndCalories(10, 0.8F)
                    .water(0.5F)
                    .nutrients(3.0F, 0.0F, 1.0F, 4.0F, 1.0F)
                    .compostChance(1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_CURRY_BURGER(
            FoodInfo.builder()
                    .name("rice_curry_burger")
                    .amountAndCalories(10, 0.8F)
                    .water(0.5F)
                    .nutrients(3.0F, 0.0F, 1.0F, 4.0F, 1.0F)
                    .compostChance(1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_CURRY_CHEESE(
            FoodInfo.builder()
                    .name("rice_curry_cheese")
                    .amountAndCalories(8, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.5F, 0.0F, 1.0F, 1.0F, 4.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_CURRY_CHEESE_KATSU(
            FoodInfo.builder()
                    .name("rice_curry_cheese_katsu")
                    .amountAndCalories(12, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(3.0F, 0.0F, 1.0F, 4.0F, 3.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_CURRY_CHEESE_BURGER(
            FoodInfo.builder()
                    .name("rice_curry_cheese_burger")
                    .amountAndCalories(12, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(3.0F, 0.0F, 1.0F, 4.0F, 3.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PASTA_TOMATO(
            FoodInfo.builder()
                    .name("pasta_tomato")
                    .amountAndCalories(9, 0.8F)
                    .water(1.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 4.0F, 4.0F, 2.0F)
                    .decayModifier(3.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PASTA_MUSHROOM(
            FoodInfo.builder()
                    .name("pasta_mushroom")
                    .amountAndCalories(9, 0.8F)
                    .water(1.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 4.0F, 4.0F, 2.0F)
                    .decayModifier(3.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PASTA_WHITESAUCE(
            FoodInfo.builder()
                    .name("pasta_whitesauce")
                    .amountAndCalories(9, 0.8F)
                    .water(1.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 4.0F, 4.0F, 2.0F)
                    .decayModifier(3.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    YAKI_PASTA(
            FoodInfo.builder()
                    .name("yaki_pasta")
                    .amountAndCalories(9, 0.7F)
                    .water(2.5F)
                    .compostChance(1.0F)
                    .nutrients(1.5F, 0.0F, 4.0F, 2.0F, 2.0F)
                    .decayModifier(3.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build());

    private final FoodInfo info;
    private final Supplier<Item> container;

    private TsukiCuisineSet(FoodInfo info, Supplier<Item> container) {
        this.info = info;
        this.container = container;
    }

    private TsukiCuisineSet(FoodInfo info) {
        this(info, () -> Items.BOWL);
    }

    public FoodInfo getFoodInfo() {
        return info;
    }

    public Supplier<ItemFoodBase> getItem() {
        return FoodRegistry.CUISINES.get(this);
    }

    public Supplier<Item> getContainer() {
        return container;
    }
}
