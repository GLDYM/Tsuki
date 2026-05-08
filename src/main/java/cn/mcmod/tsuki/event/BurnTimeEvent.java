package cn.mcmod.tsuki.event;

import java.util.function.Supplier;

import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber()
public class BurnTimeEvent {
    @SubscribeEvent
    public static void registerBurnTime(FurnaceFuelBurnTimeEvent event) {
        register(event, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO), 400);
        register(event, BlockItemRegistry.BAMBOO_BLOCK, 4000);
        register(event, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT), 400);
        register(event, BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT, 4000);
        register(event, ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL), 1600);
        register(event, BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK, 16000);
    }

    private static void register(FurnaceFuelBurnTimeEvent event, Supplier<? extends Item> item, int burnTime) {
        register(event, item.get(), burnTime);
    }

    private static void register(FurnaceFuelBurnTimeEvent event, Item item, int burnTime) {
        register(event, new ItemStack(item), burnTime);
    }

    private static void register(FurnaceFuelBurnTimeEvent event, ItemStack item, int burnTime) {
        if (item.getItem() == event.getItemStack().getItem())
            event.setBurnTime(burnTime);
    }
}
