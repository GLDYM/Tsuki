// package cn.mcmod.tsuki.item.armors;

// import cn.mcmod.tsuki.Tsuki;
// import com.mojang.serialization.Codec;
// import net.minecraft.core.component.DataComponentType;
// import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.network.codec.ByteBufCodecs;
// import net.neoforged.neoforge.registries.DeferredRegister;
// import net.neoforged.neoforge.registries.DeferredHolder;


// public class TsukiDataComponentRegistry {
//     public static final DeferredRegister<DataComponentType<?>> COMPONENTS =
//         DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Tsuki.MODID);

//     public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> KIMONO_BASE =
//         COMPONENTS.register("kimono_base",
//             () -> new DataComponentType.Builder<String>()
//                     .persistent(Codec.STRING) // 持久化方式
//                     .networkSynchronized(ByteBufCodecs.STRING_UTF8) // 网络同步
//                     .build());

//     public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> KIMONO_OVERLAY =
//         COMPONENTS.register("kimono_overlay",
//             () -> new DataComponentType.Builder<String>()
//                     .persistent(Codec.STRING)
//                     .networkSynchronized(ByteBufCodecs.STRING_UTF8)
//                     .build());  
// }
