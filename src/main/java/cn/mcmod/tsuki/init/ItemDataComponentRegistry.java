package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.component.ItemStackWrapper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemDataComponentRegistry {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS = DeferredRegister
            .create(Registries.DATA_COMPONENT_TYPE, Tsuki.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackWrapper>> MEAL = DATA_COMPONENTS
            .register("meal", () -> DataComponentType.<ItemStackWrapper>builder()
                    .persistent(ItemStackWrapper.CODEC)
                    .networkSynchronized(ItemStackWrapper.STREAM_CODEC)
                    .cacheEncoding()
                    .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackWrapper>> CONTAINER = DATA_COMPONENTS
            .register("container", () -> DataComponentType.<ItemStackWrapper>builder()
                    .persistent(ItemStackWrapper.CODEC)
                    .networkSynchronized(ItemStackWrapper.STREAM_CODEC)
                    .cacheEncoding()
                    .build());
}
