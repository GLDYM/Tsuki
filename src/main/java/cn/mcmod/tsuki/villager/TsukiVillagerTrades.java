package cn.mcmod.tsuki.villager;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.DrinkRegistry;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.armors.TsukiArmorToolRegistry;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.item.enums.TsukiWineBottleSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class TsukiVillagerTrades {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        if (event.getType() == VillagerRegistry.WA_FARMER.get()) {
            registerWaFarmerTrades(trades);
        }
        if (event.getType() == VillagerRegistry.WA_TRADER.get()) {
            registerWaTraderTrades(trades);
        }
    }

    public static boolean isTsukiProfession(VillagerData villagerData) {
        return villagerData.getProfession() == VillagerRegistry.WA_FARMER.get()
                || villagerData.getProfession() == VillagerRegistry.WA_TRADER.get();
    }

    public static VillagerTrades.ItemListing[] selectFourWithAtLeastOneBuy(
            Villager villager,
            VillagerTrades.ItemListing[] source,
            RandomSource random) {
        if (source == null || source.length <= 4) {
            return source;
        }

        List<VillagerTrades.ItemListing> pool = new ArrayList<>(List.of(source));
        List<VillagerTrades.ItemListing> buys = new ArrayList<>();
        for (VillagerTrades.ItemListing listing : pool) {
            MerchantOffer offer = listing.getOffer(villager, random);
            if (offer != null && isCoin(offer.getResult())) {
                buys.add(listing);
            }
        }

        Collections.shuffle(pool, new java.util.Random(random.nextLong()));
        List<VillagerTrades.ItemListing> selected = new ArrayList<>(4);

        if (!buys.isEmpty()) {
            VillagerTrades.ItemListing guaranteedBuy = buys.get(random.nextInt(buys.size()));
            selected.add(guaranteedBuy);
            pool.remove(guaranteedBuy);
        }

        for (VillagerTrades.ItemListing listing : pool) {
            if (selected.size() >= 4) {
                break;
            }
            selected.add(listing);
        }

        return selected.toArray(new VillagerTrades.ItemListing[0]);
    }

    private static boolean isCoin(ItemStack stack) {
        return stack.is(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKURA_COIN).get());
    }

    private static void registerWaFarmerTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        trades.get(1).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.CABBAGE).get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.EGGPLANT).get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(ItemRegistry.BUCKWHEAT.get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(ItemRegistry.RED_BEAN.get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.ONION).get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO).get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(ItemRegistry.RAPESEEDS.get(), 8, 2, 4, 16, 2));
        trades.get(1).add(buyWithCoins(ItemRegistry.RICE_SEEDS.get(), 8, 2, 4, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.RED_BEAN.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.BUCKWHEAT.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.CABBAGE_SEEDS.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.EGGPLANT_SEEDS.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.ONION_SEEDS.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.RADISH_SEEDS.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.RAPESEEDS.get(), 1, 5, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.RICE_SEEDS.get(), 1, 4, 7, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.TOMATO_SEEDS.get(), 1, 5, 7, 16, 2));

        trades.get(2).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.HOP).get(), 8, 3, 5, 12, 5));
        trades.get(2).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.SASHIMI).get(), 1, 16, 32, 12, 5));
        trades.get(2).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.SASHIMI).get(), 1, 24, 48, 12, 5));
        trades.get(2).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON).get(), 8, 2, 4, 16, 5));
        trades.get(2).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.ALMOND).get(), 8, 2, 4, 16, 5));
        trades.get(2).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MINT).get(), 8, 2, 4, 16, 5));
        trades.get(2).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.GREEN_TEA_LEAVES).get(), 8, 2, 4, 16, 5));

        trades.get(3).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.EDODES).get(), 4, 6, 10, 12, 5));
        trades.get(3).add(buyWithCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.MATSUTAKE).get(), 4, 16, 32, 12, 5));
        trades.get(3).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SALT).get(), 8, 2, 7, 12, 10));
        trades.get(3).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SOYSAUCE).get(), 8, 3, 6, 12, 10));
        trades.get(3).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MISO).get(), 8, 3, 7, 12, 10));
        trades.get(3).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.DASHI).get(), 8, 3, 5, 12, 10));
        trades.get(3).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.KOUJI).get(), 8, 2, 5, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.TOFU).get(), 8, 4, 7, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.NATTO).get(), 8, 3, 5, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.RICE_BREAD).get(), 8, 3, 5, 12, 10));
        trades.get(3).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.MIRIN).get(), 8, 3, 5, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.LEMON_JUICE).get(), 8, 4, 7, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.SODA_WATER).get(), 8, 3, 5, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.BLACKCURRANT_JUICE).get(), 8, 4, 7, 12, 10));
        trades.get(3).add(sellForCoins(FoodRegistry.FOODSET.get(TsukiFoodSet.ORANGE_JUICE).get(), 8, 4, 7, 12, 10));

        trades.get(4).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BEER_BOTTLE).get(), 1, 20, 30, 8, 15));
        trades.get(4).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BEER_BOTTLE).get(), 1, 10, 20, 8, 15));
        trades.get(4).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.DOBUROKU_BOTTLE).get(), 1, 20, 30, 8, 15));
        trades.get(4).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.DOBUROKU_BOTTLE).get(), 1, 10, 20, 8, 15));
        trades.get(4).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RED_WINE_BOTTLE).get(), 1, 20, 30, 8, 15));
        trades.get(4).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RED_WINE_BOTTLE).get(), 1, 10, 20, 8, 15));
        trades.get(4).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHITE_WINE_BOTTLE).get(), 1, 20, 30, 8, 15));
        trades.get(4).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHITE_WINE_BOTTLE).get(), 1, 10, 20, 8, 15));
        trades.get(4).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SAKE_BOTTLE).get(), 1, 40, 60, 8, 15));
        trades.get(4).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SAKE_BOTTLE).get(), 1, 20, 35, 8, 15));
        trades.get(4).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.CHAMPAGNE_BOTTLE).get(), 1, 40, 60, 8, 15));
        trades.get(4).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.CHAMPAGNE_BOTTLE).get(), 1, 20, 35, 8, 15));

        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.SAKURA_DIAMOND.get(), 1, 30, 42, 8, 30));
        trades.get(5).add(buyWithCoins(TsukiArmorToolRegistry.SAKURA_DIAMOND.get(), 1, 32, 64, 8, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SHOUCHU_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.SHOUCHU_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RUM_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.RUM_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.VODKA_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.VODKA_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHISKEY_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.WHISKEY_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BRANDY_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.BRANDY_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.GIN_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.GIN_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.TEQUILA_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.TEQUILA_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.LIQUEUR_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.LIQUEUR_BOTTLE).get(), 1, 40, 45, 4, 30));
        trades.get(5).add(sellForCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.COCOA_LIQUEUR_BOTTLE).get(), 1, 60, 64, 4, 30));
        trades.get(5).add(buyWithCoins(DrinkRegistry.WINE_BOTTLES.get(TsukiWineBottleSet.COCOA_LIQUEUR_BOTTLE).get(), 1, 40, 45, 4, 30));
    }

    private static void registerWaTraderTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
        // Comparison of the prize is a essential part of Sakura :)
        trades.get(1).add(buyWithCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SILK).get(), 2, 1, 3, 16, 2));
        trades.get(1).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SILK).get(), 4, 2, 5, 16, 2));
        trades.get(1).add(buyWithCoins(TsukiArmorToolRegistry.KIMONO_WHITE.get(), 1, 10, 14, 4, 2));
        trades.get(1).add(buyWithCoins(TsukiArmorToolRegistry.HAORI_BLACK.get(), 1, 10, 14, 4, 2));

        trades.get(2).add(buyWithCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW).get(), 8, 6, 10, 16, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_BLACK.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_GREEN.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_CYAN.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_PURPLE.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_SAKURA.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_BROWN.get(), 1, 14, 20, 4, 10));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.HAORI_BROWN.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.HAORI_CYAN.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.HAORI_LIGHT_BLUE.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.HAORI_GREEN.get(), 1, 14, 20, 4, 5));
        trades.get(2).add(sellForCoins(TsukiArmorToolRegistry.STRAW_HAT.get(), 1, 8, 12, 4, 5));
        trades.get(2).add(sellForCoins(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SILK).get(), 8, 4, 7, 12, 5));

        trades.get(3).add(buyWithCoins(TsukiArmorToolRegistry.STRAW_HAT.get(), 1, 4, 8, 8, 5));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.KIMONO_MIKO.get(), 1, 20, 36, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.YUKATA_RED.get(), 1, 16, 22, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.YUKATA_BLUE.get(), 1, 16, 22, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.YUKATA_MAGENTA.get(), 1, 16, 22, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.YUKATA_LIME.get(), 1, 16, 22, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.YUKATA_YELLOW.get(), 1, 16, 22, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.SHINAI.get(), 1, 8, 10, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.SHEATH.get(), 1, 10, 14, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.SHINAI.get(), 1, 6, 8, 4, 10));
        trades.get(3).add(sellForCoins(TsukiArmorToolRegistry.SHEATH.get(), 1, 6, 10, 4, 10));

        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SAKURA_DIAMOND.get(), 1, 32, 64, 8, 30));
        trades.get(4).add(buyWithCoins(TsukiArmorToolRegistry.SAKURA_DIAMOND.get(), 1, 32, 48, 8, 30));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SAMURAI_HELMET_RED.get(), 1, 28, 32, 4, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SAMURAI_CHESTPLATE_RED.get(), 1, 42, 56, 4, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SAMURAI_LEGGINGS_RED.get(), 1, 38, 42, 4, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SAMURAI_BOOTS_RED.get(), 1, 10, 24, 32, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SOLDIER_HELMET_BLACK.get(), 1, 16, 20, 4, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SOLDIER_CHESTPLATE_BLACK.get(), 1, 18, 22, 4, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SOLDIER_LEGGINGS_BLACK.get(), 1, 16, 20, 4, 15));
        trades.get(4).add(sellForCoins(TsukiArmorToolRegistry.SOLDIER_BOOTS_BLACK.get(), 1, 14, 18, 4, 15));

        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.SAKURA_DIAMOND.get(), 1, 30, 42, 8, 30));
        trades.get(5).add(buyWithCoins(TsukiArmorToolRegistry.SAKURA_DIAMOND.get(), 1, 32, 64, 8, 30));
        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.KATANA.get(), 1, 20, 30, 4, 30));
        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.KODACHI.get(), 1, 16, 24, 4, 30));
        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.SAKURA_KATANA.get(), 1, 30, 50, 2, 30));
        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.TACHI.get(), 1, 26, 40, 2, 30));
        trades.get(5).add(sellForCoins(TsukiArmorToolRegistry.SHEATH.get(), 1, 8, 14, 8, 30));
    }

    // All the buy and sell is from Village Perspective
    private static VillagerTrades.ItemListing buyWithCoins(ItemLike item, int count, int minCoins, int maxCoins, int maxTrades, int xp) {
        int coinPrice = pickCoinPrice(minCoins, maxCoins);
        return new BasicItemListing(
                new ItemStack(item, count),
                new ItemStack(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKURA_COIN).get(), coinPrice),
                maxTrades,
                xp,
                0.05F);
    }

    private static VillagerTrades.ItemListing sellForCoins(ItemLike item, int count, int minCoins, int maxCoins, int maxTrades, int xp) {
        int coinPrice = pickCoinPrice(minCoins, maxCoins);
        return new BasicItemListing(
                new ItemStack(ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SAKURA_COIN).get(), coinPrice),
                new ItemStack(item, count),
                maxTrades,
                xp,
                0.05F);
    }

    private static int pickCoinPrice(int minCoins, int maxCoins) {
        int boundedMin = Math.max(1, minCoins);
        int boundedMax = Math.max(boundedMin, maxCoins);
        int avg = boundedMin + (boundedMax - boundedMin) / 2;
        return Math.min(64, avg);
    }

    private TsukiVillagerTrades() {
    }
}
