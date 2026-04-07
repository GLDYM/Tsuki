package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tsuki.MODID);
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GROUP = TABS.register(
            Tsuki.MODID,
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI).get()))
                    .title(Component.translatable("itemGroup.tsuki"))
                    .displayItems(
                            (parameters, output) -> {
                                BlockItemRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get()))
                                );
                                ItemRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get()))
                                );
                                FoodRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get()))
                                );
                                BucketItemRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get()))
                                );
                            }
                    )
                    .build()
    );
}


