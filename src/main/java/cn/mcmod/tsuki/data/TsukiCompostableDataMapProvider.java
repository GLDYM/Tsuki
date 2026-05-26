package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.mmlib.item.IFoodLike;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TsukiCompostableDataMapProvider extends DataMapProvider {

    public TsukiCompostableDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(packOutput, provider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        Builder<Compostable, Item> builder = builder(NeoForgeDataMaps.COMPOSTABLES);
        FoodRegistry.ITEMS.getEntries().forEach(item -> {
            register(builder, item);
        });
        registerHolder(builder, ItemRegistry.CABBAGE_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.BUCKWHEAT, 0.3F);
        registerHolder(builder, ItemRegistry.RED_BEAN, 0.3F);
        registerHolder(builder, ItemRegistry.SOYBEAN, 0.3F);
        registerHolder(builder, ItemRegistry.RADISH_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.ONION_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.RICE_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.TOMATO_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.TARO, 0.3F);
        registerHolder(builder, ItemRegistry.EGGPLANT_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.SUNFLOWER_SEEDS, 0.3F);
        registerHolder(builder, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.NUKA), 0.3F);
        registerSupplier(builder, TsukiNormalItemSet.KAESHI.getItem(), 0.3F);
        registerSupplier(builder, TsukiNormalItemSet.NOODLE_SOUP.getItem(), 0.3F);
        registerSupplier(builder, TsukiNormalItemSet.SOYSAUCE.getItem(), 0.3F);
        registerSupplier(builder, TsukiNormalItemSet.WORCESTER_SAUCE.getItem(), 0.3F);
        registerSupplier(builder, TsukiNormalItemSet.MIRIN.getItem(), 0.3F);
        registerSupplier(builder, TsukiNormalItemSet.DOUGH.getItem(), 0.5F);
        registerSupplier(builder, TsukiNormalItemSet.DOUGH_BUCKWHEAT.getItem(), 0.5F);
        registerSupplier(builder, TsukiNormalItemSet.DOUGH_RICE.getItem(), 0.5F);
        registerHolder(builder, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW), 0.3F);

        // Tree leaves and fallen leaves: 30% chance to increase compost level.
        registerHolder(builder, BlockItemRegistry.SAKURA_LEAVES, 0.3F);
        registerHolder(builder, BlockItemRegistry.MAPLE_LEAVES_RED, 0.3F);
        registerHolder(builder, BlockItemRegistry.MAPLE_LEAVES_GREEN, 0.3F);
        registerHolder(builder, BlockItemRegistry.MAPLE_LEAVES_YELLOW, 0.3F);
        registerHolder(builder, BlockItemRegistry.MAPLE_LEAVES_ORANGE, 0.3F);
        registerHolder(builder, BlockItemRegistry.FALLEN_LEAVES_RED, 0.3F);
        registerHolder(builder, BlockItemRegistry.FALLEN_LEAVES_ORANGE, 0.3F);
        registerHolder(builder, BlockItemRegistry.FALLEN_LEAVES_YELLOW, 0.3F);
        registerHolder(builder, BlockItemRegistry.FALLEN_LEAVES_GREEN, 0.3F);
        registerHolder(builder, BlockItemRegistry.MUSHROOM_FALLEN_LEAVES, 0.3F);
        registerHolder(builder, BlockItemRegistry.MATSUTAKE_FALLEN_LEAVES, 0.3F);
    }

    private static void register(DataMapProvider.Builder<Compostable, Item> builder,
            DeferredHolder<Item, ? extends Item> itemHolder) {
        Item item = itemHolder.get();
        if (item instanceof IFoodLike food) {
            if (food.getFoodInfo().getCompostChance() > 0)
                registerHolder(builder, itemHolder, food.getFoodInfo().getCompostChance());
        }
    }

    private static void registerHolder(DataMapProvider.Builder<Compostable, Item> builder,
            DeferredHolder<Item, ? extends Item> itemHolder, float chance) {
        builder.add(itemHolder.getId(), new Compostable(chance), false);
    }

    private static void registerSupplier(DataMapProvider.Builder<Compostable, Item> builder,
            Supplier<Item> itemSupplier, float chance) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(itemSupplier.get());
        builder.add(itemId, new Compostable(chance), false);
    }
}
