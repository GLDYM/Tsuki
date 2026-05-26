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
import terrablender.worldgen.RegionUtils;

public class TsukiOverworldRegion extends Region {
    public TsukiOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addModifiedVanillaOverworldBiomes(mapper, builder -> {
            RegionUtils.getVanillaParameterPoints(Biomes.FOREST).stream()
                    .filter(TsukiOverworldRegion::isStrictInlandSurfacePoint)
                    .forEach(point -> builder.replaceBiome(point, TsukiBiomeRegistry.MAPLE_FOREST));

            RegionUtils.getVanillaParameterPoints(Biomes.BIRCH_FOREST).stream()
                    .filter(TsukiOverworldRegion::isStrictInlandSurfacePoint)
                    .forEach(point -> builder.replaceBiome(point, TsukiBiomeRegistry.MAPLE_FOREST));
        });
    }

    private static boolean isStrictInlandSurfacePoint(Climate.ParameterPoint point) {
        long coastUpper = Climate.quantizeCoord(-0.11F);
        long nearInlandUpper = Climate.quantizeCoord(0.03F);
        long maxAllowedWeirdness = Climate.quantizeCoord(0.8F);

        boolean inlandEnough = point.continentalness().min() >= nearInlandUpper;
        boolean notCoastOrOcean = point.continentalness().max() > coastUpper;
        boolean surfaceLike = point.depth().max() >= 0L;
        boolean avoidExtremeSlices = point.weirdness().min() > -maxAllowedWeirdness
                && point.weirdness().max() < maxAllowedWeirdness;
        return inlandEnough && notCoastOrOcean && surfaceLike && avoidExtremeSlices;
    }
}
