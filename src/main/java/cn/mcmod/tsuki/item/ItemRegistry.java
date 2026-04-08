package cn.mcmod.tsuki.item;

import java.util.Map;
import java.util.function.Supplier;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import cn.mcmod_mmf.mmlib.item.ItemFoodSeeds;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import cn.mcmod_mmf.mmlib.registry.ItemRegistryUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);

    public static final DeferredItem<Item> RICE_SEEDS = register("rice_seeds", RiceSeedsItem::new);

    public static final DeferredItem<Item> ONION_SEEDS = register("onion_seeds",
            () -> seed(BlockRegistry.ONION_CROP.get()));
    public static final DeferredItem<Item> RADISH_SEEDS = register("radish_seeds",
            () -> seed(BlockRegistry.RADISH_CROP.get()));
    public static final DeferredItem<Item> CABBAGE_SEEDS = register("cabbage_seeds",
            () -> seed(BlockRegistry.CABBAGE_CROP.get()));
    public static final DeferredItem<Item> RAPESEEDS = register("rapeseeds",
            () -> seed(BlockRegistry.RAPESEED_CROP.get()));
    public static final DeferredItem<Item> RED_BEAN = register("red_bean",
            () -> seed(BlockRegistry.REDBEAN_CROP.get()));
    public static final DeferredItem<Item> SOYBEAN = register("soybean",
            () -> seed(BlockRegistry.SOYBEAN_CROP.get()));
    public static final DeferredItem<Item> BUCKWHEAT = register("buckwheat",
            () -> seed(BlockRegistry.BUCKWHEAT_CROP.get()));

    public static final DeferredItem<Item> EGGPLANT_SEEDS = register("eggplant_seeds",
            () -> seed(BlockRegistry.EGGPLANT_CROP.get()));
    public static final DeferredItem<Item> TOMATO_SEEDS = register("tomato_seeds",
            () -> seed(BlockRegistry.TOMATO_CROP.get()));

    public static final DeferredItem<ItemFoodSeeds> TARO = register("taro",
            () -> seed(BlockRegistry.TARO_CROP.get(),
                    FoodInfo.builder().name("taro").amountAndCalories(2, 0.2F).water(0F).nutrients(2F, 2F, 0F, 0F, 0F)
                            .decayModifier(2F).heatCapacity(1F).cookingTemp(480F).build()));

    public static final DeferredItem<Item> SAKURA_DIAMOND = register("sakura_diamond", SakuraDiamondItem::new);
    public static final Tier SAKURA_TOOL_TIER = new SimpleTier(
            TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "incorrect_for_sakura_tool")),
            4063,
            10.0F,
            6.0F,
            25,
            () -> Ingredient.of(SAKURA_DIAMOND.get()));

    public static final DeferredItem<Item> SAKURA_AXE = register("sakura_axe",
            () -> new AxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(AxeItem.createAttributes(SAKURA_TOOL_TIER, 5.0F, -3.0F))));
    public static final DeferredItem<Item> SAKURA_PICKAXE = register("sakura_pickaxe",
            () -> new PickaxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(PickaxeItem.createAttributes(SAKURA_TOOL_TIER, 1.0F, -2.8F))));
    public static final DeferredItem<Item> SAKURA_HOE = register("sakura_hoe",
            () -> new HoeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(HoeItem.createAttributes(SAKURA_TOOL_TIER, -6.0F, 0.0F))));
    public static final DeferredItem<Item> SAKURA_SHOVEL = register("sakura_shovel",
            () -> new ShovelItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(ShovelItem.createAttributes(SAKURA_TOOL_TIER, 1.5F, -3.0F))));

    public static final DeferredItem<Item> IRON_FISH_KNIFE = register("knife_fish", ()->new KnifeItem(Tiers.IRON, 1F, -2.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> IRON_NOODLE_KNIFE = register("knife_noodle", ()->new KnifeItem(Tiers.IRON, 2F, -3.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_FISH_KNIFE = register("sakura_knife_fish", () -> new KnifeItem(SAKURA_TOOL_TIER, 1F, -2.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_NOODLE_KNIFE = register("sakura_knife_noodle", () -> new KnifeItem(SAKURA_TOOL_TIER, 2F, -3.0F, Tsuki.defaultItemProperties().stacksTo(1)));

    public static final Map<TsukiNormalItemSet, DeferredItem<Item>> MATERIALS = ItemRegistryUtil
            .mapOfKeys(TsukiNormalItemSet.class, material -> register(material.getName(), ItemRegistry::normalItem));

    private static Item normalItem() {
        return new Item(Tsuki.defaultItemProperties());
    }

    private static ItemNameBlockItem seed(Block block) {
        return new ItemNameBlockItem(block, Tsuki.defaultItemProperties());
    }

    private static ItemFoodSeeds seed(Block block, FoodInfo info) {
        return new ItemFoodSeeds(block, Tsuki.defaultItemProperties(), info);
    }

        private static <V extends Item> DeferredItem<V> register(String name, Supplier<V> item) {
        return ITEMS.register(name, item);
    }
}


