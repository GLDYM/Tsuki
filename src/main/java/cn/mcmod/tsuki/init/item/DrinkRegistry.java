package cn.mcmod.tsuki.init.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.enums.TsukiAlcoholSet;
import cn.mcmod.tsuki.init.item.enums.TsukiCocktailSet;
import cn.mcmod.tsuki.init.item.enums.TsukiTeaSet;
import cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet;
import cn.mcmod.tsuki.item.drink.DrinkItem;
import cn.mcmod.tsuki.item.drink.WineBottleItem;
import cn.mcmod.mmlib.registry.ItemRegistryUtil;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

public class DrinkRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);

    public static final DeferredItem<Item> CUP = register("cup", DrinkRegistry::normalItem);
    public static final DeferredItem<Item> WINE_BOTTLE = register("wine_bottle", DrinkRegistry::normalItem);
    public static final DeferredItem<Item> GLASS_CUP = register("glass_cup", DrinkRegistry::normalItem);

    public static final Map<TsukiTeaSet, DeferredItem<Item>> TEAS = ItemRegistryUtil.mapOfKeys(
            TsukiTeaSet.class,
            tea -> register(tea.getName(), () -> new DrinkItem(
                    Tsuki.defaultItemProperties(),
                    DrinkRegistry::cupContainerItem,
                    false,
                    tea.getEffects())));
    public static final Map<TsukiWineBottleSet, DeferredItem<Item>> WINE_BOTTLES = ItemRegistryUtil.mapOfKeys(
            TsukiWineBottleSet.class,
            wineBottle -> register(wineBottle.getName(), () -> new WineBottleItem(
                    Tsuki.defaultItemProperties(),
                    DrinkRegistry::bottleContainerItem,
                    wineBottle::getFluid,
                    true,
                    wineBottle.getEffects())));
    public static final Map<TsukiAlcoholSet, DeferredItem<Item>> ALCOHOLS = ItemRegistryUtil.mapOfKeys(
            TsukiAlcoholSet.class,
            alcohol -> register(alcohol.getName(), () -> new DrinkItem(
                    Tsuki.defaultItemProperties(),
                    DrinkRegistry::glassCupContainerItem,
                    true,
                    alcohol.getEffects())));
    public static final Map<TsukiCocktailSet, DeferredItem<Item>> COCKTAILS = ItemRegistryUtil.mapOfKeys(
            TsukiCocktailSet.class,
            cocktail -> register(cocktail.getName(), () -> new DrinkItem(
                    Tsuki.defaultItemProperties(),
                    DrinkRegistry::glassCupContainerItem,
                    true,
                    cocktail.getEffects())));

    private static Item normalItem() {
        return new Item(Tsuki.defaultItemProperties());
    }

    private static Item cupContainerItem() {
        return CUP.get();
    }

    private static Item bottleContainerItem() {
        return WINE_BOTTLE.get();
    }

    private static Item glassCupContainerItem() {
        return GLASS_CUP.get();
    }

    private static <V extends Item> DeferredItem<V> register(String name, Supplier<V> item) {
        return ITEMS.register(name, item);
    }
}


