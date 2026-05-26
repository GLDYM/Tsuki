package cn.mcmod.tsuki.init.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.EntityTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.item.RiceSeedsItem;
import cn.mcmod.mmlib.item.ItemFoodSeeds;
import cn.mcmod.mmlib.item.info.FoodInfo;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

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

    public static final DeferredItem<Item> PEPPER_SEEDS = register("pepper_seeds",
            ItemRegistry::normalItem);
    public static final DeferredItem<Item> VANILLA_SEEDS = register("vanilla_seeds",
            ItemRegistry::normalItem);
    public static final DeferredItem<Item> GRAPE_SEEDS = register("grape_seeds",
            ItemRegistry::normalItem);
    public static final DeferredItem<Item> SUNFLOWER_SEEDS = register("sunflower_seeds",
            () -> seed(BlockRegistry.SUNFLOWER_CROP.get()));

    public static final Map<TsukiNormalItemSet, DeferredItem<Item>> MATERIALS = createMaterials();

    public static final DeferredItem<Item> SAMURAI_ILLAGER_SPAWN_EGG = register("samurai_illager_spawn_egg",
            () -> new DeferredSpawnEggItem(EntityTypeRegistry.SAMURAI_ILLAGER, 9804699, 2580065,
                    Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_GUIDE = register("sakura_guide",
            () -> new Item(Tsuki.defaultItemProperties().stacksTo(1)));

    private static Map<TsukiNormalItemSet, DeferredItem<Item>> createMaterials() {
        Map<TsukiNormalItemSet, DeferredItem<Item>> materials = new EnumMap<>(TsukiNormalItemSet.class);
        for (TsukiNormalItemSet material : TsukiNormalItemSet.values()) {
            materials.put(material, register(material.getName(), ItemRegistry::normalItem));
        }
        return materials;
    }

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
