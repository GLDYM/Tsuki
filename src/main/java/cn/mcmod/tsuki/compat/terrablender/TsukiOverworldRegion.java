package cn.mcmod.tsuki.compat.terrablender;

import com.mojang.datafixers.util.Pair;
import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class TsukiOverworldRegion extends Region {
    public TsukiOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiomeSimilar(mapper, Biomes.FOREST, TsukiBiomeRegistry.MAPLE_FOREST);
        // addBiomeSimilar(mapper, Biomes.FLOWER_FOREST, TsukiBiomeRegistry.MAPLE_FOREST);
        addBiomeSimilar(mapper, Biomes.BIRCH_FOREST, TsukiBiomeRegistry.MAPLE_FOREST);
        // addBiomeSimilar(mapper, Biomes.OLD_GROWTH_BIRCH_FOREST, TsukiBiomeRegistry.MAPLE_FOREST);
        // addBiomeSimilar(mapper, Biomes.PLAINS, TsukiBiomeRegistry.MAPLE_FOREST);
        // addBiomeSimilar(mapper, Biomes.SUNFLOWER_PLAINS, TsukiBiomeRegistry.MAPLE_FOREST);
    }
}
