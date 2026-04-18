package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod.tsuki.item.armors.TsukiArmorToolRegistry;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            Tsuki.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GROUP = TABS.register(
            "items",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(BlockItemRegistry.COOKING_POT.get()))
                    .title(Component.translatable("item_group.tsuki.items"))
                    .displayItems(
                            (parameters, output) -> {
                                BlockItemRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get())));
                                ItemRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get())));
                                BucketItemRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get())));
                            })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FOOD_GROUP = TABS.register(
            "food",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.ONIGIRI).get()))
                    .title(Component.translatable("item_group.tsuki.food"))
                    .displayItems(
                            (parameters, output) -> FoodRegistry.ITEMS.getEntries().forEach(
                                    (entry) -> output.accept(new ItemStack(entry.get()))))
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DRINKS_GROUP = TABS.register(
            "drinks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(DrinkRegistry.CUP.get()))
                    .title(Component.translatable("item_group.tsuki.drinks"))
                    .displayItems(
                            (parameters, output) -> {
                                DrinkRegistry.ITEMS.getEntries().forEach(
                                        (entry) -> output.accept(new ItemStack(entry.get())));
                            })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ARMORS_AND_TOOLS_GROUP = TABS.register(
            "armors_and_tools",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(TsukiArmorToolRegistry.SAKURA_DIAMOND.get()))
                    .title(Component.translatable("item_group.tsuki.armors_and_tools"))
                    .displayItems(
                            (parameters, output) -> TsukiArmorToolRegistry.ITEMS.getEntries().forEach(
                                    (entry) -> output.accept(new ItemStack(entry.get()))))
                    .build());
}
