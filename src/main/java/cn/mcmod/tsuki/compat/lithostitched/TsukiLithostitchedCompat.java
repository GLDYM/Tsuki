package cn.mcmod.tsuki.compat.lithostitched;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.worldgen.TsukiBiomes;
import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector;
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class TsukiLithostitchedCompat {
    private static final double INLAND_CONTINENTALNESS = 0.03D;
    private static final double SURFACE_DEPTH = 0.0D;
    private static final double MAX_WEIRDNESS = 0.8D;

    private TsukiLithostitchedCompat() {
    }

    public static void register() {
        AddBiomeInjectorsEvent.EVENT.register((registryAccess, consumer) -> {
            Registry<Biome> biomes = registryAccess.registryOrThrow(Registries.BIOME);
            biomes.getHolder(TsukiBiomes.MAPLE_FOREST).ifPresent(mapleForest -> {
                HolderSet<Biome> targets = HolderSet.direct(
                        biomes.getHolderOrThrow(Biomes.FOREST),
                        biomes.getHolderOrThrow(Biomes.BIRCH_FOREST));
                ParameterBuilder parameters = ParameterBuilder.create()
                        .climateMin(BiomeInjector.ClimateParameter.CONTINENTALNESS, INLAND_CONTINENTALNESS)
                        .climateMin(BiomeInjector.ClimateParameter.DEPTH, SURFACE_DEPTH)
                        .climateRange(BiomeInjector.ClimateParameter.WEIRDNESS, -MAX_WEIRDNESS, MAX_WEIRDNESS);
                consumer.accept(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_forest"),
                        BiomeInjector.builder(Level.OVERWORLD)
                                .replacePartially(targets, mapleForest, parameters));
            });
        });
    }
}
