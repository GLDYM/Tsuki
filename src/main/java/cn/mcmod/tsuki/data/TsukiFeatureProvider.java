package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.level.WorldGenerationRegistry;
import cn.mcmod.tsuki.level.tree.TsukiTreeFeatures;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TsukiFeatureProvider extends DatapackBuiltinEntriesProvider {

    public static final ResourceKey<BiomeModifier> ADD_FEATURES = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS, // The registry this key is for
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "add_features") // The registry name
    );

    public TsukiFeatureProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries, new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, bootstrap -> {
                    TsukiTreeFeatures.bootstrapEntries();
                    TsukiTreeFeatures.ENTRY.forEach(
                            e -> bootstrap.register(e.getA(), e.getB()));
                    bootstrap.register(WorldGenerationRegistry.FEATURE_PATCH_BAMBOOSHOOT_KEY,
                            WorldGenerationRegistry.FEATURE_PATCH_BAMBOOSHOOT);
                    bootstrap.register(WorldGenerationRegistry.FEATURE_PATCH_WILD_PEPPER_KEY,
                            WorldGenerationRegistry.FEATURE_PATCH_WILD_PEPPER);
                    bootstrap.register(WorldGenerationRegistry.FEATURE_PATCH_WILD_VANILLA_KEY,
                            WorldGenerationRegistry.FEATURE_PATCH_WILD_VANILLA);
                    bootstrap.register(WorldGenerationRegistry.FEATURE_ORE_SAKURA_DIAMOND_KEY,
                            WorldGenerationRegistry.FEATURE_ORE_SAKURA_DIAMOND);
                    bootstrap.register(WorldGenerationRegistry.FEATURE_ORE_IRON_SAND_KEY,
                            WorldGenerationRegistry.FEATURE_ORE_IRON_SAND);
                })
                .add(Registries.PLACED_FEATURE, bootstrap -> {
                    bootstrap.register(WorldGenerationRegistry.PATCH_BAMBOOSHOOT_KEY,
                            WorldGenerationRegistry.PATCH_BAMBOOSHOOT);
                    bootstrap.register(WorldGenerationRegistry.PATCH_WILD_PEPPER_KEY,
                            WorldGenerationRegistry.PATCH_WILD_PEPPER);
                    bootstrap.register(WorldGenerationRegistry.PATCH_WILD_VANILLA_KEY,
                            WorldGenerationRegistry.PATCH_WILD_VANILLA);
                    bootstrap.register(WorldGenerationRegistry.ORE_SAKURA_DIAMOND_KEY,
                            WorldGenerationRegistry.ORE_SAKURA_DIAMOND);
                    bootstrap.register(WorldGenerationRegistry.ORE_IRON_SAND_KEY,
                            WorldGenerationRegistry.ORE_IRON_SAND);
                }), Set.of(Tsuki.MODID));
    }

    public String getName() {
        return "Tsuki - Features";
    }
}
