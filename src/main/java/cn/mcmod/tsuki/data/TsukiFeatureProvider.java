package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.level.WorldGenerationRegistry;
import cn.mcmod.tsuki.level.tree.TsukiTreeFeatures;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TsukiFeatureProvider extends DatapackBuiltinEntriesProvider {

    public static final ResourceKey<BiomeModifier> ADD_FEATURES = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS, // The registry this key is for
            new ResourceLocation(Tsuki.MODID, "add_features") // The registry name
    );

    public TsukiFeatureProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, bootstrap -> {
                            TsukiTreeFeatures.ENTRY.forEach(
                                    e -> bootstrap.register(e.getA(), e.getB())
                            );
                            bootstrap.register(WorldGenerationRegistry.FEATURE_PATCH_BAMBOOSHOOT_KEY, WorldGenerationRegistry.FEATURE_PATCH_BAMBOOSHOOT);
                        })
                        .add(Registries.PLACED_FEATURE, bootstrap -> {
                            bootstrap.register(WorldGenerationRegistry.PATCH_BAMBOOSHOOT_KEY, WorldGenerationRegistry.PATCH_BAMBOOSHOOT);
                        })
                , Set.of(Tsuki.MODID));
    }

}
