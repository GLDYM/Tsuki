package cn.mcmod.tsuki.level;

import java.util.List;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class WorldGenerationRegistry {

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_BAMBOOSHOOT_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "patch_bambooshoot"));
    public static final ConfiguredFeature<?, ?> FEATURE_PATCH_BAMBOOSHOOT = new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchConfiguration(
            64, 1, 3,PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new NoiseThresholdProvider(496156461L,
            new NormalNoise.NoiseParameters(0, 1.0), 0.005F, -0.8F, 0.33333334F, BlockRegistry.BAMBOOSHOOT.get().defaultBlockState(),
            List.of(BlockRegistry.BAMBOOSHOOT.get().defaultBlockState()),
            List.of(BlockRegistry.BAMBOOSHOOT.get().defaultBlockState()))))));
    public static final ResourceKey<PlacedFeature> PATCH_BAMBOOSHOOT_KEY = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "patch_bambooshoot"));
    public static final PlacedFeature PATCH_BAMBOOSHOOT = new PlacedFeature(Holder.direct(FEATURE_PATCH_BAMBOOSHOOT),
            List.of(PlacementUtils.HEIGHTMAP,
                    InSquarePlacement.spread(),
                    BiomeFilter.biome(),
                    PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING),
                    RarityFilter.onAverageOnceEvery(30)));

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_ORE_SAKURA_DIAMOND_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ore_sakura_diamond"));
    public static final ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>> FEATURE_ORE_SAKURA_DIAMOND = new ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>(
            Feature.ORE,
            new OreConfiguration(List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockRegistry.SAKURA_DIAMOND_ORE.get().defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get().defaultBlockState())),
                    4,
                    0.5F));

    public static final ResourceKey<PlacedFeature> ORE_SAKURA_DIAMOND_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ore_sakura_diamond"));
    public static final PlacedFeature ORE_SAKURA_DIAMOND = new PlacedFeature(
            Holder.direct(FEATURE_ORE_SAKURA_DIAMOND),
            List.of(
                    CountPlacement.of(7),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.absolute(-1), 1)),
                    BiomeFilter.biome()));

//    public static final BlockPos BLOCK_BELOW = new BlockPos(0, -1, 0);
//
//    private static ConfiguredFeature<?, ?> wildPlantFeature(Supplier<Block> wildCrop, TagKey<Block> blockTag) {
//        return new ConfiguredFeature<>(Feature.RANDOM_PATCH, getWildCropConfiguration(wildCrop.get(),
//                64, 1, BlockPredicate.matchesTag(BLOCK_BELOW,blockTag)));
//    }
//    private static PlacedFeature wildPlantPatch(ConfiguredFeature<?, ?> feature,
//            PlacementModifier... modifiers) {
//        return new PlacedFeature(Holder.direct(feature), Lists.newArrayList(modifiers));
//    }
//
//    private static RandomPatchConfiguration getWildCropConfiguration(Block block, int tries, int xzSpread, BlockPredicate plantedOn) {
//        return new RandomPatchConfiguration(tries, xzSpread, 3, PlacementUtils.filtered(
//                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)),
//                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, plantedOn)));
//    }
}
