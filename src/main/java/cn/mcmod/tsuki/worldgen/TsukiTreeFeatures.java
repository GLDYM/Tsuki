package cn.mcmod.tsuki.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.FeatureTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import oshi.util.tuples.Pair;

public class TsukiTreeFeatures {

    public static final List<Pair<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>>> ENTRY = new ArrayList<>();
    private static boolean initialized;

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "sakura"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_SAKURA_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fancy_sakura"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_RED_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_red"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_MAPLE_RED_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fancy_maple_red"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_YELLOW_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_yellow"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_MAPLE_YELLOW_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fancy_maple_yellow"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_ORANGE_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_orange"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_MAPLE_ORANGE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fancy_maple_orange"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_GREEN_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "maple_green"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_MAPLE_GREEN_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fancy_maple_green"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> UME_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "ume"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_UME_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fancy_ume"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> MASSIVE_SAKURA_KEY = ResourceKey
            .create(Registries.CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "massive_sakura"));

    public static synchronized void bootstrapEntries() {
        if (initialized) {
            return;
        }
        initialized = true;

        registryTree(SAKURA_KEY, createCherryTree(BlockRegistry.SAKURA_LOG.get(), BlockRegistry.SAKURA_LEAVES.get(),
                vanillaCherryBranchCount()));
        registryTree(FANCY_SAKURA_KEY, createCherryTree(BlockRegistry.SAKURA_LOG.get(),
                BlockRegistry.SAKURA_LEAVES.get(), fancyCherryBranchCount()));

        registryTree(MAPLE_RED_KEY,
                createMapleStraightTree(BlockRegistry.MAPLE_LEAVES_RED.get(), BlockRegistry.FALLEN_LEAVES_RED.get()));
        registryTree(FANCY_MAPLE_RED_KEY,
                createMapleFancyTree(BlockRegistry.MAPLE_LEAVES_RED.get(), BlockRegistry.FALLEN_LEAVES_RED.get()));

        registryTree(MAPLE_YELLOW_KEY, createMapleStraightTree(BlockRegistry.MAPLE_LEAVES_YELLOW.get(),
                BlockRegistry.FALLEN_LEAVES_YELLOW.get()));
        registryTree(FANCY_MAPLE_YELLOW_KEY, createMapleFancyTree(BlockRegistry.MAPLE_LEAVES_YELLOW.get(),
                BlockRegistry.FALLEN_LEAVES_YELLOW.get()));

        registryTree(MAPLE_ORANGE_KEY, createMapleStraightTree(BlockRegistry.MAPLE_LEAVES_ORANGE.get(),
                BlockRegistry.FALLEN_LEAVES_ORANGE.get()));
        registryTree(FANCY_MAPLE_ORANGE_KEY, createMapleFancyTree(BlockRegistry.MAPLE_LEAVES_ORANGE.get(),
                BlockRegistry.FALLEN_LEAVES_ORANGE.get()));

        registryTree(MAPLE_GREEN_KEY, createGreenMapleStraightTree(BlockRegistry.MAPLE_LEAVES_GREEN.get(),
                BlockRegistry.FALLEN_LEAVES_GREEN.get()));
        registryTree(FANCY_MAPLE_GREEN_KEY, createGreenMapleFancyTree(BlockRegistry.MAPLE_LEAVES_GREEN.get(),
                BlockRegistry.FALLEN_LEAVES_GREEN.get()));

        registryTree(UME_KEY,
                createSimpleBlobTree(BlockRegistry.UME_LOG.get(), BlockRegistry.UME_LEAVES.get()).ignoreVines());
        registryTree(FANCY_UME_KEY, createFancyTree(BlockRegistry.UME_LOG.get(), BlockRegistry.UME_LEAVES.get()));

        ENTRY.add(new Pair<>(MASSIVE_SAKURA_KEY,
                new ConfiguredFeature<>(FeatureTypeRegistry.MASSIVE_SAKURA.get(), NoneFeatureConfiguration.INSTANCE)));
    }

    private static ConfiguredFeature<?, ?> registryTree(ResourceKey<ConfiguredFeature<?, ?>> key,
            TreeConfiguration.TreeConfigurationBuilder tree) {
        ConfiguredFeature<TreeConfiguration, Feature<TreeConfiguration>> feature = new ConfiguredFeature<>(Feature.TREE,
                tree.build());
        ENTRY.add(new Pair<>(key, feature));
        return feature;
    }

    private static TreeConfiguration.TreeConfigurationBuilder createSimpleBlobTree(Block log, Block leaves) {
        return createStraightBlobTree(log, leaves, 4, 2, 0, 2);
    }

    private static IntProvider vanillaCherryBranchCount() {
        return new WeightedListInt(
                SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(1), 1)
                        .add(ConstantInt.of(2), 1)
                        .add(ConstantInt.of(3), 1)
                        .build());
    }

    private static IntProvider fancyCherryBranchCount() {
        return new WeightedListInt(
                SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(2), 3)
                        .add(ConstantInt.of(3), 2)
                        .build());
    }

    private static TreeConfiguration.TreeConfigurationBuilder createCherryTree(Block log, Block leaves,
            IntProvider branchCount) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(log),
                new CherryTrunkPlacer(
                        7,
                        1,
                        0,
                        branchCount,
                        UniformInt.of(2, 4),
                        UniformInt.of(-4, -3),
                        UniformInt.of(-1, 0)),
                BlockStateProvider.simple(leaves),
                new CherryFoliagePlacer(
                        ConstantInt.of(4),
                        ConstantInt.of(0),
                        ConstantInt.of(5),
                        0.25F,
                        0.5F,
                        0.16666667F,
                        0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block log, Block leaves,
            int baseHeight, int heightRandA, int heightRandB, int leaves_radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB), BlockStateProvider.simple(leaves),
                new BlobFoliagePlacer(ConstantInt.of(leaves_radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createMapleStraightTree(Block leaves,
            Block fallenLeaves) {
        return createStraightBlobTree(BlockRegistry.MAPLE_LOG.get(), leaves, 5, 2, 0, 2)
                .decorators(List.of(new MapleSapLogDecorator(0.25F), new MapleFallenLeavesDecorator(fallenLeaves)))
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createGreenMapleStraightTree(Block leaves,
            Block fallenLeaves) {
        return createStraightBlobTree(BlockRegistry.MAPLE_LOG.get(), leaves, 5, 2, 0, 2)
                .decorators(List.of(
                        new MapleSapLogDecorator(0.25F),
                        new MapleFallenLeavesDecorator(fallenLeaves),
                        new ChestnutBurrDecorator(0.25F)))
                .ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createMapleFancyTree(Block leaves, Block fallenLeaves) {
        return createFancyTree(BlockRegistry.MAPLE_LOG.get(), leaves)
                .decorators(List.of(new MapleSapLogDecorator(0.33F), new MapleFallenLeavesDecorator(fallenLeaves)));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createGreenMapleFancyTree(Block leaves,
            Block fallenLeaves) {
        return createFancyTree(BlockRegistry.MAPLE_LOG.get(), leaves)
                .decorators(List.of(
                        new MapleSapLogDecorator(0.33F),
                        new MapleFallenLeavesDecorator(fallenLeaves),
                        new ChestnutBurrDecorator(0.25F)));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyTree(Block log, Block leaves) {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
                new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(leaves),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }
}
