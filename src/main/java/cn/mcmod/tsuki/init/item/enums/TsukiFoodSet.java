package cn.mcmod.tsuki.init.item.enums;

import cn.mcmod.mmlib.item.info.FoodInfo;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.mmlib.item.ItemFoodBase;

import java.util.function.Supplier;

public enum TsukiFoodSet {
    SHRIMP(
            FoodInfo.builder()
                    .name("shrimp")
                    .amountAndCalories(2, 0.6F)
                    .water(0.5F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .compostChance(0.2F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TOMATO(
            FoodInfo.builder()
                    .name("tomato")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    GRAPE(
            FoodInfo.builder()
                    .name("grape")
                    .amountAndCalories(1, 0.25F)
                    .water(4.0F)
                    .nutrients(0.25F, 0.0F, 1.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(-1.0F)
                    .build()),
    GRAPE_GREEN(
            FoodInfo.builder()
                    .name("grape_green")
                    .amountAndCalories(2, 0.2F)
                    .water(4.0F)
                    .nutrients(0.0F, 2.0F, 0.0F, 0.5F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(0.0F)
                    .build()),
    UME(
            FoodInfo.builder()
                    .name("ume")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 1.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    UMEBOSHI(
            FoodInfo.builder()
                    .name("umeboshi")
                    .amountAndCalories(2, 0.5F)
                    .water(5.0F)
                    .nutrients(0.0F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(0.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RADISH(
            FoodInfo.builder()
                    .name("radish")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    EGGPLANT(
            FoodInfo.builder()
                    .name("eggplant")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PICKELD_RADISH(
            FoodInfo.builder()
                    .name("pickled_radish")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PICKELD_EGGPLANT(
            FoodInfo.builder()
                    .name("pickled_eggplant")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CABBAGE(
            FoodInfo.builder()
                    .name("cabbage")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MATSUTAKE(
            FoodInfo.builder()
                    .name("matsutake")
                    .amountAndCalories(1, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.0F)
                    .heatCapacity(2.0F)
                    .cookingTemp(480.0F)
                    .build()),
    EDODES(
            FoodInfo.builder()
                    .name("edodes")
                    .amountAndCalories(1, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.0F)
                    .heatCapacity(2.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SHIMEJI(
            FoodInfo.builder()
                    .name("shimeji")
                    .amountAndCalories(1, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.0F)
                    .heatCapacity(2.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONION(
            FoodInfo.builder()
                    .name("onion")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SLICED_CABBAGE(
            FoodInfo.builder()
                    .name("sliced_cabbage")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .compostChance(0.3F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MACHINED_FISH(
            FoodInfo.builder()
                    .name("machined_fish")
                    .amountAndCalories(1, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 2.0F)
                    .compostChance(0.25F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MINCED_MEAT(
            FoodInfo.builder()
                    .name("minced_meat")
                    .amountAndCalories(2, 0.2F)
                    .water(1.0F)
                    .compostChance(0.25F)
                    .nutrients(0.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(200.0F)
                    .build()),
    SURIMI(
            FoodInfo.builder()
                    .name("surimi")
                    .amountAndCalories(2, 0.2F)
                    .water(1.0F)
                    .compostChance(0.25F)
                    .nutrients(0.0F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(200.0F)
                    .build()),
    BONITO(
            FoodInfo.builder()
                    .name("bonito")
                    .amountAndCalories(2, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .compostChance(0.3F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MACHINED_BONITO(
            FoodInfo.builder()
                    .name("machined_bonito")
                    .amountAndCalories(1, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .compostChance(0.25F)
                    .decayModifier(5.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BOILED_BONITO(
            FoodInfo.builder()
                    .name("boiled_bonito")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .compostChance(0.3F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DRIED_BONITO(
            FoodInfo.builder()
                    .name("dried_bonito")
                    .amountAndCalories(2, 0.2F)
                    .water(0.0F)
                    .compostChance(0.3F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BONITO_SHAVING(
            FoodInfo.builder()
                    .name("bonito_shaving")
                    .amountAndCalories(1, 0.1F)
                    .water(0.0F)
                    .compostChance(0.3F)
                    .nutrients(0.0F, 0.0F, 0.0F, 1.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FISHCAKE(
            FoodInfo.builder()
                    .name("fishcake")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(1.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    KAMABOKO(
            FoodInfo.builder()
                    .name("kamaboko")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(1.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHIKUWA_RAW(
            FoodInfo.builder()
                    .name("chikuwa_raw")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(1.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHIKUWA(
            FoodInfo.builder()
                    .name("chikuwa")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(1.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SATSUMAAGE(
            FoodInfo.builder()
                    .name("satsumaage")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(1.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MAYONAISE(
            FoodInfo.builder()
                    .name("mayo")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .compostChance(0.25F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TOMATO_SAUCE(
            FoodInfo.builder()
                    .name("tomato_sauce")
                    .amountAndCalories(2, 0.2F)
                    .water(5.0F)
                    .compostChance(0.25F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    EGGPLANT_BAKED(
            FoodInfo.builder()
                    .name("eggplant_baked")
                    .amountAndCalories(4, 0.5F)
                    .water(0.0F)
                    .compostChance(0.5F)
                    .nutrients(0.0F, 0.0F, 3.0F, 0.0F, 0.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TARO_BAKED(
            FoodInfo.builder()
                    .name("taro_baked")
                    .amountAndCalories(5, 0.6F)
                    .water(0.0F)
                    .nutrients(2.0F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHESTNUT_TOASTED(
            FoodInfo.builder()
                    .name("chestnut_toasted")
                    .amountAndCalories(4, 0.4F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.4F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ROAST_MATSUTAKE(
            FoodInfo.builder()
                    .name("roast_matsutake")
                    .amountAndCalories(5, 0.6F)
                    .water(0.0F)
                    .compostChance(0.5F)
                    .nutrients(0.0F, 0.0F, 3.0F, 0.0F, 0.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHEESE(
            FoodInfo.builder()
                    .name("cheese")
                    .amountAndCalories(2, 0.2F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .compostChance(0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(-1.0F)
                    .build()),
    TAMAGOYAKI(
            FoodInfo.builder()
                    .name("tamagoyaki")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 3.0F)
                    .compostChance(0.75F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TOFU(
            FoodInfo.builder()
                    .name("tofu")
                    .amountAndCalories(2, 0.4F)
                    .water(0.5F)
                    .nutrients(0.0F, 0.0F, 2.0F, 0.0F, 0.5F)
                    .compostChance(0.5F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TOFU_FRIED(
            FoodInfo.builder()
                    .name("tofu_fried")
                    .amountAndCalories(4, 0.5F)
                    .water(0.5F)
                    .compostChance(0.5F)
                    .nutrients(0.5F, 0.0F, 3.0F, 0.0F, 0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    NATTO(
            FoodInfo.builder()
                    .name("natto")
                    .amountAndCalories(2, 0.5F)
                    .water(0.5F)
                    .nutrients(1.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MASHED_POTATO(
            FoodInfo.builder()
                    .name("mashed_potato")
                    .amountAndCalories(5, 0.6F)
                    .water(0.5F)
                    .compostChance(0.5F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.5F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FRIES(
            FoodInfo.builder()
                    .name("fries")
                    .amountAndCalories(5, 0.6F)
                    .water(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BUN(
            FoodInfo.builder()
                    .name("bun")
                    .amountAndCalories(5, 0.6F)
                    .water(0.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(0.8F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BUCKWHEAT_BREAD(
            FoodInfo.builder()
                    .name("buckwheat_bread")
                    .amountAndCalories(5, 0.6F)
                    .water(0.0F)
                    .compostChance(0.5F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RICE_BREAD(
            FoodInfo.builder()
                    .name("rice_bread")
                    .amountAndCalories(5, 0.6F)
                    .water(0.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    REDBEAN_PASTE(
            FoodInfo.builder()
                    .name("red_bean_paste")
                    .amountAndCalories(4, 0.25F)
                    .water(4.0F)
                    .compostChance(0.5F)
                    .nutrients(0.25F, 0.0F, 1.0F, 0.0F, 0.0F)
                    .decayModifier(4.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(-1.0F)
                    .build()),
    BREADCRUMBS(
            FoodInfo.builder()
                    .name("breadcrumbs")
                    .amountAndCalories(1, 0.1F)
                    .water(0.0F)
                    .compostChance(0.5F)
                    .nutrients(0.25F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(4.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(-1.0F)
                    .build()),
    FRIED_CHICKEN(
            FoodInfo.builder()
                    .name("fried_chicken")
                    .amountAndCalories(6, 0.6F)
                    .water(2.0F)
                    .nutrients(1.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CROQUETTE(
            FoodInfo.builder()
                    .name("croquette")
                    .amountAndCalories(6, 0.6F)
                    .water(2.0F)
                    .nutrients(2.0F, 0.0F, 2.5F, 2.0F, 0.0F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    KATSU(
            FoodInfo.builder()
                    .name("katsu")
                    .amountAndCalories(9, 0.6F)
                    .water(4.0F)
                    .nutrients(0.25F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(1.25F)
                    .heatCapacity(0.0F)
                    .cookingTemp(-1.0F)
                    .build()),
    TEMPURA(
            FoodInfo.builder()
                    .name("tempura")
                    .amountAndCalories(5, 0.6F)
                    .water(0.0F)
                    .nutrients(1.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI(
            FoodInfo.builder()
                    .name("onigiri")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 1.0F, 0.0F, 0.0F)
                    .compostChance(0.85F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI_BAMBOO(
            FoodInfo.builder()
                    .name("onigiri_bamboo")
                    .amountAndCalories(7, 0.7F)
                    .water(0.5F)
                    .compostChance(0.85F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI_FISH(
            FoodInfo.builder()
                    .name("onigiri_fish")
                    .amountAndCalories(8, 0.7F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI_MUSHROOM(
            FoodInfo.builder()
                    .name("onigiri_mushroom")
                    .amountAndCalories(7, 0.7F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI_MATSUTAKE(
            FoodInfo.builder()
                    .name("onigiri_matsutake")
                    .amountAndCalories(9, 0.7F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 3.0F, 0.0F, 1.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI_SEAWEED(
            FoodInfo.builder()
                    .name("onigiri_seaweed")
                    .amountAndCalories(7, 0.7F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 0.0F, 0.5F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ONIGIRI_TEMPURA(
            FoodInfo.builder()
                    .name("onigiri_tempura")
                    .amountAndCalories(10, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 4.0F, 1.0F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    VINEGAR_RICE(
            FoodInfo.builder()
                    .name("vinegar_rice")
                    .amountAndCalories(5, 0.5F)
                    .water(0.5F)
                    .compostChance(0.5F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SUSHI(
            FoodInfo.builder()
                    .name("sushi")
                    .amountAndCalories(5, 0.6F)
                    .water(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .compostChance(0.85F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SUSHI_SHRIMP(
            FoodInfo.builder()
                    .name("sushi_shrimp")
                    .amountAndCalories(5, 0.6F)
                    .water(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 2.0F, 0.0F)
                    .compostChance(0.85F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SUSHI_TAMAGO(
            FoodInfo.builder()
                    .name("sushi_tamago")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .compostChance(0.85F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SUSHI_INARI(
            FoodInfo.builder()
                    .name("inari_sushi")
                    .amountAndCalories(5, 0.6F)
                    .water(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .compostChance(0.85F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MOCHI(
            FoodInfo.builder()
                    .name("mochi")
                    .amountAndCalories(2, 0.5F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MOCHI_TOASTED(
            FoodInfo.builder()
                    .name("mochi_toasted")
                    .amountAndCalories(4, 0.6F)
                    .water(0.5F)
                    .compostChance(0.75F)
                    .nutrients(3.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MOCHI_SAKURA(
            FoodInfo.builder()
                    .name("mochi_sakura")
                    .amountAndCalories(4, 0.6F)
                    .water(0.5F)
                    .compostChance(0.85F)
                    .nutrients(3.0F, 0.0F, 1.0F, 0.0F, 0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    OHAGI(
            FoodInfo.builder()
                    .name("ohagi")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .nutrients(3.0F, 0.0F, 0.5F, 0.0F, 0.5F)
                    .compostChance(0.85F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DAIFUKU(
            FoodInfo.builder()
                    .name("daifuku")
                    .amountAndCalories(4, 0.6F)
                    .water(0.5F)
                    .nutrients(3.0F, 0.0F, 0.5F, 0.0F, 0.5F)
                    .compostChance(0.85F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    KUSA_DAIFUKU(
            FoodInfo.builder()
                    .name("kusa_daifuku")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .compostChance(0.85F)
                    .nutrients(3.0F, 0.0F, 1.5F, 0.0F, 0.5F)
                    .decayModifier(2.25F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DANGO(
            FoodInfo.builder()
                    .name("dango")
                    .amountAndCalories(2, 0.5F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BURGER_RAW(
            FoodInfo.builder()
                    .name("burger_raw")
                    .amountAndCalories(2, 0.2F)
                    .water(1.0F)
                    .compostChance(0.5F)
                    .nutrients(0.5F, 0.0F, 0.0F, 3.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(200.0F)
                    .build()),
    BURGER(
            FoodInfo.builder()
                    .name("burger")
                    .amountAndCalories(6, 0.6F)
                    .water(2.0F)
                    .nutrients(0.5F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .compostChance(0.5F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(200.0F)
                    .build()),
    BURGER_DISH(
            FoodInfo.builder()
                    .name("burger_dish")
                    .amountAndCalories(10, 0.8F)
                    .water(2.5F)
                    .compostChance(1.0F)
                    .nutrients(0.5F, 0.0F, 2.0F, 4.0F, 0.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    KATSU_DISH(
            FoodInfo.builder()
                    .name("katsu_dish")
                    .amountAndCalories(10, 0.8F)
                    .water(0.5F)
                    .nutrients(0.5F, 0.0F, 2.0F, 5.0F, 1.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CROQUETTE_DISH(
            FoodInfo.builder()
                    .name("croquette_dish")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .nutrients(0.5F, 0.0F, 2.0F, 5.0F, 1.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    HAMBURGER(
            FoodInfo.builder()
                    .name("hamburger")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 2.0F, 4.0F, 1.0F)
                    .compostChance(1.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    CHEESE_BURGER(
            FoodInfo.builder()
                    .name("cheese_burger")
                    .amountAndCalories(10, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 4.0F, 3.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SASHIMI(
            FoodInfo.builder()
                    .name("sashimi")
                    .amountAndCalories(6, 0.6F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 1.0F, 3.0F, 0.0F)
                    .compostChance(1.0F)
                    .decayModifier(4.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FISH_BAKE_SALT(
            FoodInfo.builder()
                    .name("fish_bake_salt")
                    .amountAndCalories(8, 0.8F)
                    .water(0.5F)
                    .compostChance(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FISH_BAKE(
            FoodInfo.builder()
                    .name("fish_bake")
                    .amountAndCalories(9, 0.8F)
                    .water(0.5F)
                    .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .compostChance(1.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    YAKINIKU(
            FoodInfo.builder()
                    .name("yakiniku")
                    .amountAndCalories(10, 0.8F)
                    .water(0.5F)
                    .nutrients(0.0F, 0.0F, 0.0F, 4.0F, 0.0F)
                    .compostChance(1.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DOUGH_OKINOYAKI(
            FoodInfo.builder()
                    .name("dough_okinoyaki")
                    .amountAndCalories(2, 0.2F)
                    .water(1.0F)
                    .nutrients(2.0F, 0.0F, 2.0F, 2.0F, 2.0F)
                    .compostChance(0.5F)
                    .decayModifier(1.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    OKINOYAKI(
            FoodInfo.builder()
                    .name("okinoyaki")
                    .amountAndCalories(8, 0.8F)
                    .water(0.5F)
                    .nutrients(2.0F, 0.0F, 3.0F, 3.0F, 3.0F)
                    .compostChance(1.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    OKINOYAKI_PLUS(
            FoodInfo.builder()
                    .name("okinoyaki_plus")
                    .amountAndCalories(10, 1.0F)
                    .water(1.0F)
                    .nutrients(3.0F, 3.0F, 3.0F, 3.0F, 3.0F)
                    .compostChance(1.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    OKINOYAKI_FINAL(
            FoodInfo.builder()
                    .name("okinoyaki_final")
                    .amountAndCalories(12, 1.0F)
                    .water(1.0F)
                    .nutrients(5.0F, 5.0F, 5.0F, 5.0F, 5.0F)
                    .compostChance(1.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    HYOROGAN(
            FoodInfo.builder()
                    .name("hyorogan")
                    .amountAndCalories(6, 0.6F)
                    .water(0.0F)
                    .compostChance(1.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    BUGGYS_MEAT(
            FoodInfo.builder()
                    .name("buggys_meat")
                    .amountAndCalories(20, 0.8F)
                    .water(0.0F)
                    .compostChance(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 10.0F, 1.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    LEMON(
            FoodInfo.builder()
                    .name("lemon")
                    .amountAndCalories(1, 0.1F)
                    .water(5.0F)
                    .nutrients(0.0F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(3.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    LEMON_JUICE(
            FoodInfo.builder()
                    .name("lemon_juice")
                    .amountAndCalories(1, 0.1F)
                    .water(40.0F)
                    .nutrients(0.0F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(0.0F)
                    .build()),
    SODA_WATER(
            FoodInfo.builder()
                    .name("soda_water")
                    .amountAndCalories(1, 0.1F)
                    .water(50.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(0.0F)
                    .build()),
    BLACKCURRANT_JUICE(
            FoodInfo.builder()
                    .name("blackcurrant_juice")
                    .amountAndCalories(1, 0.1F)
                    .water(40.0F)
                    .nutrients(0.0F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(0.0F)
                    .build()),
    ORANGE_JUICE(
            FoodInfo.builder()
                    .name("orange_juice")
                    .amountAndCalories(1, 0.1F)
                    .water(40.0F)
                    .nutrients(0.0F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(5.0F)
                    .heatCapacity(0.0F)
                    .cookingTemp(0.0F)
                    .build()),
    SMOKED_BONITO(
            FoodInfo.builder()
                    .name("smoked_bonito")
                    .amountAndCalories(3, 0.3F)
                    .water(0.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 2.0F, 2.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FRIED_BROWN_RICE(
            FoodInfo.builder()
                    .name("fried_brown_rice")
                    .amountAndCalories(4, 0.5F)
                    .water(0.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DRIED_BROWN_RICE(
            FoodInfo.builder()
                    .name("dried_brown_rice")
                    .amountAndCalories(4, 0.5F)
                    .water(0.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DRIED_RICE(
            FoodInfo.builder()
                    .name("dried_rice")
                    .amountAndCalories(4, 0.5F)
                    .water(0.0F)
                    .nutrients(1.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    EGG_SOFT(
            FoodInfo.builder()
                    .name("egg_soft")
                    .amountAndCalories(2, 0.6F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 3.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    EGG_SOYSAUCE(
            FoodInfo.builder()
                    .name("egg_soysauce")
                    .amountAndCalories(4, 0.6F)
                    .water(1.0F)
                    .nutrients(0.0F, 0.0F, 0.0F, 0.0F, 3.5F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    FRUITSALAD(
            FoodInfo.builder()
                    .name("fruitsalad")
                    .amountAndCalories(6, 0.6F)
                    .water(15.0F)
                    .nutrients(2.0F, 4.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(5.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MAPLE_COOKIE(
            FoodInfo.builder()
                    .name("maple_cookie")
                    .amountAndCalories(3, 0.25F)
                    .water(0.75F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.2F)
                    .decayModifier(0.8F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PUDDING(
            FoodInfo.builder()
                    .name("pudding")
                    .amountAndCalories(4, 0.4F)
                    .water(2.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PUDDING_MAPLE(
            FoodInfo.builder()
                    .name("pudding_maple")
                    .amountAndCalories(6, 0.6F)
                    .water(2.0F)
                    .nutrients(3.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    POUND_CAKE(
            FoodInfo.builder()
                    .name("pound_cake")
                    .amountAndCalories(5, 0.6F)
                    .water(0.5F)
                    .nutrients(4.0F, 2.0F, 0.0F, 0.0F, 4.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    DORAYAKI(
            FoodInfo.builder()
                    .name("dorayaki")
                    .amountAndCalories(6, 0.6F)
                    .water(1.0F)
                    .nutrients(5.0F, 2.0F, 2.0F, 0.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(2.0F)
                    .cookingTemp(480.0F)
                    .build()),
    RAW_TAIYAKI(
            FoodInfo.builder()
                    .name("raw_taiyaki")
                    .amountAndCalories(2, 0.2F)
                    .water(0.5F)
                    .nutrients(0.5F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(2.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TAIYAKI(
            FoodInfo.builder()
                    .name("taiyaki")
                    .amountAndCalories(6, 0.6F)
                    .water(0.5F)
                    .nutrients(4.0F, 0.0F, 0.0F, 0.0F, 4.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    TAIYAKI_MOCHA(
            FoodInfo.builder()
                    .name("taiyaki_mocha")
                    .amountAndCalories(8, 0.6F)
                    .water(0.5F)
                    .nutrients(4.0F, 0.0F, 0.0F, 0.0F, 4.0F)
                    .decayModifier(1.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    MOCHA_COOKIE(
            FoodInfo.builder()
                    .name("mocha_cookie")
                    .amountAndCalories(5, 0.25F)
                    .water(0.75F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.2F)
                    .decayModifier(0.8F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    PUDDING_MOCHA(
            FoodInfo.builder()
                    .name("pudding_mocha")
                    .amountAndCalories(6, 0.6F)
                    .water(2.0F)
                    .nutrients(3.0F, 0.0F, 0.0F, 0.0F, 2.0F)
                    .decayModifier(2.5F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    POUND_CAKE_MOCHA(
            FoodInfo.builder()
                    .name("pound_cake_mocha")
                    .amountAndCalories(7, 0.6F)
                    .water(0.5F)
                    .nutrients(4.0F, 2.0F, 0.0F, 0.0F, 4.0F)
                    .decayModifier(3.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    SUIKATSUGAN(
            FoodInfo.builder()
                    .name("suikatsugan")
                    .amountAndCalories(4, 0.5F)
                    .water(20.0F)
                    .nutrients(1.5F, 2.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(480.0F)
                    .build()),
    ALMOND(
            FoodInfo.builder()
                    .name("almond")
                    .amountAndCalories(1, 0.1F)
                    .water(0.0F)
                    .nutrients(2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                    .decayModifier(0.0F)
                    .heatCapacity(1.0F)
                    .cookingTemp(0.0F)
                    .build());

    private final FoodInfo info;

    private TsukiFoodSet(FoodInfo info) {
        this.info = info;
    }

    public FoodInfo getFoodInfo() {
        return info;
    }

    public Supplier<ItemFoodBase> getItem() {
        return FoodRegistry.FOODSET.get(this);
    }
}

