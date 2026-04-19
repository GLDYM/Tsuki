package cn.mcmod.tsuki.level.structure;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TsukiStructureRegistry {
    public static final DeferredRegister<StructurePoolElementType<?>> STRUCTURE_POOL_ELEMENT_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_POOL_ELEMENT, Tsuki.MODID);

    // public static final DeferredHolder<StructurePoolElementType<?>, StructurePoolElementType<TsukiJapaneseHouseElement>> JAPANESE_HOUSE =
    //         STRUCTURE_POOL_ELEMENT_TYPES.register("japanese_house", () -> () -> TsukiJapaneseHouseElement.CODEC);

    private TsukiStructureRegistry() {
    }
}
