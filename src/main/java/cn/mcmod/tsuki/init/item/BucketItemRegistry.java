package cn.mcmod.tsuki.init.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BucketItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);
    public static final DeferredItem<Item> FOOD_OIL_BUCKET = ITEMS.register("food_oil_bucket",
            () -> new BucketItem(FluidRegistry.FOOD_OIL.get(), new Item.Properties().craftRemainder(Items.BUCKET)));

    public static final DeferredItem<Item> DOBUROKU_BUCKET = ITEMS.register("doburoku_bucket",
            () -> new BucketItem(FluidRegistry.DOBUROKU.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> SAKE_BUCKET = ITEMS.register("sake_bucket",
            () -> new BucketItem(FluidRegistry.SAKE.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> SHOUCHU_BUCKET = ITEMS.register("shouchu_bucket",
            () -> new BucketItem(FluidRegistry.SHOUCHU.get(), new Item.Properties().craftRemainder(Items.BUCKET)));

    public static final DeferredItem<Item> BEER_BUCKET = ITEMS.register("beer_bucket",
            () -> new BucketItem(FluidRegistry.BEER.get(), new Item.Properties().craftRemainder(Items.BUCKET)));

    public static final DeferredItem<Item> WHISKEY_BUCKET = ITEMS.register("whiskey_bucket",
            () -> new BucketItem(FluidRegistry.WHISKEY.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> RED_WINE_BUCKET = ITEMS.register("red_wine_bucket",
            () -> new BucketItem(FluidRegistry.RED_WINE.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> WHITE_WINE_BUCKET = ITEMS.register("white_wine_bucket",
            () -> new BucketItem(FluidRegistry.WHITE_WINE.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> CHAMPAGNE_BUCKET = ITEMS.register("champagne_bucket",
            () -> new BucketItem(FluidRegistry.CHAMPAGNE.get(), new Item.Properties().craftRemainder(Items.BUCKET)));

    public static final DeferredItem<Item> RUM_BUCKET = ITEMS.register("rum_bucket",
            () -> new BucketItem(FluidRegistry.RUM.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> BRANDY_BUCKET = ITEMS.register("brandy_bucket",
            () -> new BucketItem(FluidRegistry.BRANDY.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> VODKA_BUCKET = ITEMS.register("vodka_bucket",
            () -> new BucketItem(FluidRegistry.VODKA.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> LIQUEUR_BUCKET = ITEMS.register("liqueur_bucket",
            () -> new BucketItem(FluidRegistry.LIQUEUR.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> COCOA_LIQUEUR_BUCKET = ITEMS.register("cocoa_liqueur_bucket",
            () -> new BucketItem(FluidRegistry.COCOA_LIQUEUR.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> GIN_BUCKET = ITEMS.register("gin_bucket",
            () -> new BucketItem(FluidRegistry.GIN.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> TEQUILA_BUCKET = ITEMS.register("tequila_bucket",
            () -> new BucketItem(FluidRegistry.TEQUILA.get(), new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> MAPLE_SYRUP_BUCKET = ITEMS.register("maple_syrup_bucket",
            () -> new BucketItem(FluidRegistry.MAPLE_SYRUP.get(), new Item.Properties().craftRemainder(Items.BUCKET)));

}
