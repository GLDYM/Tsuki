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
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Generates both the compostables and the furnace fuels data maps.
 * <p>
 * NeoForge only allows a single {@link DataMapProvider} per datagen run, so all Tsuki data maps
 * are registered here. Furnace fuels used to be applied by a {@code FurnaceFuelBurnTimeEvent}
 * listener; keeping them in a data map makes the values data-pack overridable.
 */
public class TsukiDataMapProvider extends DataMapProvider {

    public TsukiDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(packOutput, provider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        gatherCompostables();
        gatherFurnaceFuels();
    }

    private void gatherCompostables() {
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

    private void gatherFurnaceFuels() {
        Builder<FurnaceFuel, Item> builder = builder(NeoForgeDataMaps.FURNACE_FUELS);
        registerFuel(builder, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO), 400);
        registerFuel(builder, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT), 400);
        registerFuel(builder, BlockItemRegistry.BAMBOO_BLOCK, 4000);
        registerFuel(builder, BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT, 4000);
        registerFuel(builder, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL), 1600);
        registerFuel(builder, BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK, 16000);
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

    private static void registerFuel(DataMapProvider.Builder<FurnaceFuel, Item> builder,
            DeferredHolder<Item, ? extends Item> itemHolder, int burnTime) {
        builder.add(itemHolder.getId(), new FurnaceFuel(burnTime), false);
    }
}
