package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DrinkRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);

    public static final DeferredItem<Item> CUP = register("cup", DrinkRegistry::normalItem);
    public static final DeferredItem<Item> EMPTY_BOTTLE = register("empty_bottle", DrinkRegistry::normalItem);

    private static Item normalItem() {
        return new Item(Tsuki.defaultItemProperties());
    }

    private static <V extends Item> DeferredItem<V> register(String name, Supplier<V> item) {
        return ITEMS.register(name, item);
    }
}
