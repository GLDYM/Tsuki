package cn.mcmod.tsuki.worldgen;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.block.BlockRegistry;

import java.util.List;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public final class TsukiTreePlacedFeatures {
    public static final ResourceKey<PlacedFeature> MAPLE_RED_CHECKED_KEY = pkey("maple_red_checked");
    public static final ResourceKey<PlacedFeature> MAPLE_YELLOW_CHECKED_KEY = pkey("maple_yellow_checked");
    public static final ResourceKey<PlacedFeature> MAPLE_ORANGE_CHECKED_KEY = pkey("maple_orange_checked");
    public static final ResourceKey<PlacedFeature> MAPLE_GREEN_CHECKED_KEY = pkey("maple_green_checked");
    public static final ResourceKey<PlacedFeature> FANCY_MAPLE_RED_CHECKED_KEY = pkey("fancy_maple_red_checked");
    public static final ResourceKey<PlacedFeature> FANCY_MAPLE_YELLOW_CHECKED_KEY = pkey("fancy_maple_yellow_checked");
    public static final ResourceKey<PlacedFeature> FANCY_MAPLE_ORANGE_CHECKED_KEY = pkey("fancy_maple_orange_checked");
    public static final ResourceKey<PlacedFeature> FANCY_MAPLE_GREEN_CHECKED_KEY = pkey("fancy_maple_green_checked");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_TREES_MAPLE_KEY = ckey("trees_maple");
    public static final ResourceKey<PlacedFeature> TREES_MAPLE_KEY = pkey("trees_maple");

    private TsukiTreePlacedFeatures() {
    }

    public static void bootstrapConfigured(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
        HolderGetter<PlacedFeature> placedFeatures = bootstrap.lookup(Registries.PLACED_FEATURE);
        bootstrap.register(FEATURE_TREES_MAPLE_KEY,
                new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(
                                List.of(
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(FANCY_MAPLE_RED_CHECKED_KEY),
                                                0.02F),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(MAPLE_YELLOW_CHECKED_KEY),
                                                0.15F),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(FANCY_MAPLE_YELLOW_CHECKED_KEY),
                                                0.02F),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(MAPLE_ORANGE_CHECKED_KEY),
                                                0.15F),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(FANCY_MAPLE_ORANGE_CHECKED_KEY),
                                                0.02F),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(MAPLE_GREEN_CHECKED_KEY),
                                                0.12F),
                                        new WeightedPlacedFeature(
                                                placedFeatures.getOrThrow(FANCY_MAPLE_GREEN_CHECKED_KEY),
                                                0.02F)),
                                placedFeatures.getOrThrow(MAPLE_RED_CHECKED_KEY))));
    }

    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> bootstrap) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = bootstrap.lookup(Registries.CONFIGURED_FEATURE);

        bootstrap.register(MAPLE_RED_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.MAPLE_RED_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_RED.get()))));
        bootstrap.register(MAPLE_YELLOW_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.MAPLE_YELLOW_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_YELLOW.get()))));
        bootstrap.register(MAPLE_ORANGE_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.MAPLE_ORANGE_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_ORANGE.get()))));
        bootstrap.register(MAPLE_GREEN_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.MAPLE_GREEN_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_GREEN.get()))));

        bootstrap.register(FANCY_MAPLE_RED_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.FANCY_MAPLE_RED_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_RED.get()))));
        bootstrap.register(FANCY_MAPLE_YELLOW_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.FANCY_MAPLE_YELLOW_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_YELLOW.get()))));
        bootstrap.register(FANCY_MAPLE_ORANGE_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.FANCY_MAPLE_ORANGE_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_ORANGE.get()))));
        bootstrap.register(FANCY_MAPLE_GREEN_CHECKED_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(TsukiTreeFeatures.FANCY_MAPLE_GREEN_KEY),
                        List.of(PlacementUtils.filteredByBlockSurvival(BlockRegistry.MAPLE_SAPLING_GREEN.get()))));

        bootstrap.register(TREES_MAPLE_KEY,
                new PlacedFeature(configuredFeatures.getOrThrow(FEATURE_TREES_MAPLE_KEY),
                        List.of(
                                PlacementUtils.countExtra(12, 0.1F, 1),
                                InSquarePlacement.spread(),
                                SurfaceWaterDepthFilter.forMaxDepth(0),
                                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                                BiomeFilter.biome())));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> ckey(String name) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, name));
    }

    private static ResourceKey<PlacedFeature> pkey(String name) {
        return ResourceKey.create(
                Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, name));
    }
}
