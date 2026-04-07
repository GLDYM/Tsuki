package cn.mcmod.tsuki.fluid;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.world.item.*;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Tsuki.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BucketItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tsuki.MODID);
    public static final RegistryObject<Item> FOOD_OIL_BUCKET = ITEMS.register("food_oil_bucket", () -> 
        new BucketItem(FluidRegistry.FOOD_OIL, new Item.Properties().craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> DOBUROKU_BUCKET = ITEMS.register("doburoku_bucket", () -> 
        new BucketItem(FluidRegistry.DOBUROKU, new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> SAKE_BUCKET = ITEMS.register("sake_bucket", () -> 
        new BucketItem(FluidRegistry.SAKE, new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> SHOUCHU_BUCKET = ITEMS.register("shouchu_bucket", () -> 
        new BucketItem(FluidRegistry.SHOUCHU, new Item.Properties().craftRemainder(Items.BUCKET)));
    
    public static final RegistryObject<Item> BEER_BUCKET = ITEMS.register("beer_bucket", () -> 
        new BucketItem(FluidRegistry.BEER, new Item.Properties().craftRemainder(Items.BUCKET)));
    
    public static final RegistryObject<Item> WHISKEY_BUCKET = ITEMS.register("whiskey_bucket", () -> 
        new BucketItem(FluidRegistry.WHISKEY, new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> RED_WINE_BUCKET = ITEMS.register("red_wine_bucket", () -> 
        new BucketItem(FluidRegistry.RED_WINE, new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> WHITE_WINE_BUCKET = ITEMS.register("white_wine_bucket", () -> 
        new BucketItem(FluidRegistry.WHITE_WINE, new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> CHAMPAGNE_BUCKET = ITEMS.register("champagne_bucket", () -> 
        new BucketItem(FluidRegistry.CHAMPAGNE, new Item.Properties().craftRemainder(Items.BUCKET)));
    
    public static final RegistryObject<Item> RUM_BUCKET = ITEMS.register("rum_bucket", () -> 
        new BucketItem(FluidRegistry.RUM, new Item.Properties().craftRemainder(Items.BUCKET)));
    public static final RegistryObject<Item> BRANDY_BUCKET = ITEMS.register("brandy_bucket", () -> 
        new BucketItem(FluidRegistry.BRANDY, new Item.Properties().craftRemainder(Items.BUCKET)));
    
    
    @SubscribeEvent
    public static void onAddCreativeModeTab(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey()== CreativeModeTabs.INGREDIENTS){
            ITEMS.getEntries().forEach(entry -> event.accept(entry.get()));
        }
    }
}
