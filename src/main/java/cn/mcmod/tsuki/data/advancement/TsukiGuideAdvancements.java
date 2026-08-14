package cn.mcmod.tsuki.data.advancement;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiGuideAdvancements implements AdvancementProvider.AdvancementGenerator {
    private static final ResourceLocation ROOT_BACKGROUND = ResourceLocation.withDefaultNamespace(
            "textures/gui/advancements/backgrounds/adventure.png");

    private static final ItemLike[] RICE_DISHES = new ItemLike[] {
            FoodRegistry.CUISINES.get(TsukiCuisineSet.BROWN_RICE_COOKED).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_COOKED).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_REDBEAN).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BAMBOO).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BEEF).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_FISH).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_PORK).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_KATSU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_MUSHROOM).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_MATSUTAKE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BEEF_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_PORK_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_OYAKO).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_OYAKO_FISH).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_NATTO).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_NATTO_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.OMURICE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.CURRY_OMURICE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_FRIED).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_CURRY).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_CURRY_KATSU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_CURRY_BURGER).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_CURRY_CHEESE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_CURRY_CHEESE_KATSU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_CURRY_CHEESE_BURGER).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.OCHAZUKE).get(),
            // FoodRegistry.CUISINES.get(TsukiCuisineSet.ZOSUI).get(),
            // FoodRegistry.CUISINES.get(TsukiCuisineSet.ZOSUI_ZUIKI).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_BAMBOO).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_FISH).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_MUSHROOM).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_MATSUTAKE).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_SEAWEED).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI_TEMPURA).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.VINEGAR_RICE).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.FRIED_BROWN_RICE).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.DRIED_BROWN_RICE).get(),
            FoodRegistry.FOODSET.get(TsukiFoodSet.DRIED_RICE).get()
    };

    private static final ItemLike[] NOODLE_DISHES = new ItemLike[] {
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_BEEF).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_TEMPURA).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_FRIEDTOFU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_FRIEDCHICKEN).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_CROQUETTE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_KATSU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_CURRY).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN_LARGE).get(),
            // FoodRegistry.CUISINES.get(TsukiCuisineSet.HYDRA_RAMEN).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_BEEF).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_TEMPURA).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_FRIEDTOFU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_FRIEDCHICKEN).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_CROQUETTE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_KATSU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_CURRY).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.UDON_LARGE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.YAKI_UDON).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_BEEF).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_EGG).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_TEMPURA).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_FRIEDTOFU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_FRIEDCHICKEN).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_CROQUETTE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_KATSU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_CURRY).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_LARGE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.SOBA_ZARU).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.YAKI_SOBA).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.PASTA_TOMATO).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.PASTA_MUSHROOM).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.PASTA_WHITESAUCE).get(),
            FoodRegistry.CUISINES.get(TsukiCuisineSet.YAKI_PASTA).get()
    };

    private static final ItemLike[] BASE_WINE_BOTTLES = new ItemLike[] {
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.BEER_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.DOBUROKU_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.SAKE_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.SHOUCHU_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.RED_WINE_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.WHITE_WINE_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.CHAMPAGNE_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.RUM_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.VODKA_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.WHISKEY_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.BRANDY_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.GIN_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.TEQUILA_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.LIQUEUR_BOTTLE).get(),
            DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.COCOA_LIQUEUR_BOTTLE)
                    .get()
    };

    @SuppressWarnings("unused")
    @Override
    public void generate(
            HolderLookup.Provider provider,
            Consumer<AdvancementHolder> consumer,
            ExistingFileHelper existingFileHelper) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ItemRegistry.SAKURA_GUIDE.get(),
                        Component.translatable("advancement.tsuki.guide.root.title"),
                        Component.translatable("advancement.tsuki.guide.root.description"),
                        ROOT_BACKGROUND,
                        AdvancementType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .save(consumer, rl("guide/root"), existingFileHelper);

        AdvancementHolder stoneMortar = chapter(root, consumer, existingFileHelper, "guide/stone_mortar",
                "stone_mortar", BlockItemRegistry.STONE_MORTAR.get(), BlockItemRegistry.STONE_MORTAR.get());
        AdvancementHolder choppingBoard = chapter(root, consumer, existingFileHelper, "guide/chopping_board",
                "chopping_board", BlockItemRegistry.CHOPPING_BOARD.get(), BlockItemRegistry.CHOPPING_BOARD.get());
        AdvancementHolder cookingPot = chapter(root, consumer, existingFileHelper, "guide/cooking_pot",
                "cooking_pot", BlockItemRegistry.COOKING_POT.get(), BlockItemRegistry.COOKING_POT.get());
        AdvancementHolder drinks = chapter(root, consumer, existingFileHelper, "guide/tea_drinks",
                "tea_drinks", DrinkRegistry.CUP.get(), DrinkRegistry.CUP.get());
        AdvancementHolder brewersCraft = child(
                root,
                BlockItemRegistry.FERMENTER.get(),
                "brewers_craft",
                AdvancementType.TASK,
                null)
                .addCriterion("has_fermenter",
                        InventoryChangeTrigger.TriggerInstance.hasItems(BlockItemRegistry.FERMENTER.get()))
                .addCriterion("has_distiller",
                        InventoryChangeTrigger.TriggerInstance.hasItems(BlockItemRegistry.DISTILLER.get()))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(consumer, rl("guide/brewers_craft"), existingFileHelper);
        AdvancementHolder moonlitShaker = chapter(root, consumer, existingFileHelper, "guide/moonlit_shaker",
                "moonlit_shaker", DrinkRegistry.SHAKER.get(), DrinkRegistry.SHAKER.get());
        AdvancementHolder oreSmelting = chapter(root, consumer, existingFileHelper, "guide/ore_smelting",
                "ore_smelting", ArmorToolRegistry.SAKURA_DIAMOND.get(), ArmorToolRegistry.SAKURA_DIAMOND.get());
        AdvancementHolder tataraForging = chapter(root, consumer, existingFileHelper, "guide/tatara_forging",
                "tatara_forging", BlockItemRegistry.TATARA.get(), BlockItemRegistry.TATARA.get());
        AdvancementHolder drawTheBlade = child(
                root,
                ArmorToolRegistry.KATANA.get(),
                "draw_the_blade",
                AdvancementType.TASK,
                null)
                .addCriterion("has_katana",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.KATANA.get()))
                .addCriterion("has_sakura_katana",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.SAKURA_KATANA.get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(consumer, rl("guide/draw_the_blade"), existingFileHelper);

        Advancement.Builder riceBuilder = child(cookingPot, FoodRegistry.CUISINES.get(TsukiCuisineSet.RICE_BEEF).get(),
                "rice_dishes", AdvancementType.GOAL, rewardLoot("magatama_green"));
        addConsumeCriteria(riceBuilder, RICE_DISHES);
        riceBuilder.save(consumer, rl("guide/rice_dishes"), existingFileHelper);

        Advancement.Builder noodleBuilder = child(choppingBoard, FoodRegistry.CUISINES.get(TsukiCuisineSet.RAMEN).get(),
                "noodle_dishes", AdvancementType.GOAL, rewardLoot("magatama_pink"));
        addConsumeCriteria(noodleBuilder, NOODLE_DISHES);
        noodleBuilder.save(consumer, rl("guide/noodle_dishes"), existingFileHelper);

        Advancement.Builder baseWineBuilder = child(brewersCraft,
                DrinkRegistry.WINE_BOTTLES.get(cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet.SAKE_BOTTLE).get(),
                "base_wine_collection", AdvancementType.GOAL, rewardLoot("magatama_red"));
        addInventoryCriteria(baseWineBuilder, BASE_WINE_BOTTLES);
        baseWineBuilder.save(consumer, rl("guide/base_wine_collection"), existingFileHelper);

        child(moonlitShaker, DrinkRegistry.MYTHERY_MIX.get(), "sixteen_mythery_mix", AdvancementType.CHALLENGE,
                rewardLoot("magatama_blue"))
                .addCriterion("has_16_mythery_mix", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item()
                                .of(DrinkRegistry.MYTHERY_MIX.get())
                                .withCount(MinMaxBounds.Ints.atLeast(16))))
                .save(consumer, rl("guide/sixteen_mythery_mix"), existingFileHelper);

        child(drawTheBlade, ArmorToolRegistry.SAKURA_KATANA_SHEATH.get(), "blossom_sheath", AdvancementType.GOAL,
                rewardLoot("magatama_orange"))
                .addCriterion("has_sakura_katana_sheath",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.SAKURA_KATANA_SHEATH.get()))
                .save(consumer, rl("guide/blossom_sheath"), existingFileHelper);

        child(oreSmelting, ArmorToolRegistry.MYTHIC_PICKAXE.get(), "nameless_pickaxe", AdvancementType.CHALLENGE,
                rewardLoot("magatama_purple"))
                .addCriterion("has_mythic_pickaxe",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MYTHIC_PICKAXE.get()))
                .save(consumer, rl("guide/nameless_pickaxe"), existingFileHelper);

        child(root, ArmorToolRegistry.MAGATAMA_WHITE.get(), "sevenfold_return", AdvancementType.CHALLENGE,
                rewardLoot("magatama_white"))
                .addCriterion("has_magatama_blue",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MAGATAMA_BLUE.get()))
                .addCriterion("has_magatama_green",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MAGATAMA_GREEN.get()))
                .addCriterion("has_magatama_orange",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MAGATAMA_ORANGE.get()))
                .addCriterion("has_magatama_pink",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MAGATAMA_PINK.get()))
                .addCriterion("has_magatama_purple",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MAGATAMA_PURPLE.get()))
                .addCriterion("has_magatama_red",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ArmorToolRegistry.MAGATAMA_RED.get()))
                .requirements(AdvancementRequirements.Strategy.AND)
                .save(consumer, rl("guide/sevenfold_return"), existingFileHelper);
    }

    private static AdvancementHolder chapter(
            AdvancementHolder parent,
            Consumer<AdvancementHolder> consumer,
            ExistingFileHelper existingFileHelper,
            String path,
            String key,
            ItemLike icon,
            ItemLike criterionItem) {
        return child(parent, icon, key, AdvancementType.TASK, null)
                .addCriterion("has_" + idFromItem(criterionItem),
                        InventoryChangeTrigger.TriggerInstance.hasItems(criterionItem))
                .save(consumer, rl(path), existingFileHelper);
    }

    private static Advancement.Builder child(
            AdvancementHolder parent,
            ItemLike icon,
            String key,
            AdvancementType type,
            AdvancementRewards.Builder rewards) {
        Advancement.Builder builder = Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        icon,
                        Component.translatable("advancement.tsuki.guide." + key + ".title"),
                        Component.translatable("advancement.tsuki.guide." + key + ".description"),
                        null,
                        type,
                        true,
                        true,
                        false);
        if (rewards != null) {
            builder.rewards(rewards);
        }
        return builder;
    }

    private static void addConsumeCriteria(Advancement.Builder builder, ItemLike[] items) {
        for (ItemLike item : items) {
            builder.addCriterion(idFromItem(item), ConsumeItemTrigger.TriggerInstance.usedItem(item));
        }
        builder.requirements(AdvancementRequirements.Strategy.AND);
    }

    private static void addInventoryCriteria(Advancement.Builder builder, ItemLike[] items) {
        for (ItemLike item : items) {
            builder.addCriterion(idFromItem(item), InventoryChangeTrigger.TriggerInstance.hasItems(item));
        }
        builder.requirements(AdvancementRequirements.Strategy.AND);
    }

    private static AdvancementRewards.Builder rewardLoot(String rewardId) {
        ResourceKey<net.minecraft.world.level.storage.loot.LootTable> lootTable = ResourceKey.create(
                Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "advancements/" + rewardId));
        return AdvancementRewards.Builder.loot(lootTable);
    }

    private static String idFromItem(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }

    private static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, path);
    }
}
