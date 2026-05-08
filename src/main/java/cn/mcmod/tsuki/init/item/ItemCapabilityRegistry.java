package cn.mcmod.tsuki.init.item;

import cn.mcmod.tsuki.item.drink.WineBottleFluidHandlerItem;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public final class ItemCapabilityRegistry {
    private ItemCapabilityRegistry() {
    }

    public static void register(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> new WineBottleFluidHandlerItem(stack),
                DrinkRegistry.WINE_BOTTLE.get());
        DrinkRegistry.WINE_BOTTLES.values().forEach(item -> event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> new WineBottleFluidHandlerItem(stack),
                item.get()));
    }
}
