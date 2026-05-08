package cn.mcmod.tsuki.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class MassiveTreeFeature extends Feature<NoneFeatureConfiguration> {
    public MassiveTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return new WorldGenMassiveTree(false)
                .setTreeScale(8, 1.3f, 0.68f)
                .setLeafAttenuation(0.56f)
                .setSafe(true)
                .generate(context.level(), context.random(), context.origin());
    }
}
