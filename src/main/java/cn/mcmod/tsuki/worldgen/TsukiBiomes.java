package cn.mcmod.tsuki.worldgen;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public final class TsukiBiomes {
    public static final ResourceKey<Biome> MAPLE_FOREST = ResourceKey.create(
            Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_forest"));

    private TsukiBiomes() {
    }
}
