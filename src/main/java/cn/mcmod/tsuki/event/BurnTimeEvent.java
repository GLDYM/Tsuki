package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public class BurnTimeEvent {
    @SubscribeEvent
    public static void registerBurnTime(FurnaceFuelBurnTimeEvent event) {
        Item item = event.getItemStack().getItem();
        if (item == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get()
                || item == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()) {
            event.setBurnTime(400);
        } else if (item == BlockItemRegistry.BAMBOO_BLOCK.get()
                || item == BlockItemRegistry.BAMBOO_BLOCK_SUNBURNT.get()) {
            event.setBurnTime(4000);
        } else if (item == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get()) {
            event.setBurnTime(1600);
        } else if (item == BlockItemRegistry.BAMBOO_CHARCOAL_BLOCK.get()) {
            event.setBurnTime(16000);
        }
    }
}
