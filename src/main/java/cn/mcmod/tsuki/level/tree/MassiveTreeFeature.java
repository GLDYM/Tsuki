package cn.mcmod.tsuki.level.tree;

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
                .setTreeScale(7, 1.2f, 0.7f)
                .setLeafAttenuation(0.6f)
                .setMinTrunkSize(8)
                .setSloped(true)
                .setSafe(true)
                .generate(context.level(), context.random(), context.origin());
    }
}
