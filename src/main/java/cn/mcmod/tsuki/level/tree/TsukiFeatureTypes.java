package cn.mcmod.tsuki.level.tree;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TsukiFeatureTypes {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Tsuki.MODID);

    public static final DeferredHolder<Feature<?>, MassiveTreeFeature> MASSIVE_TREE = FEATURES.register("massive_tree",
            () -> new MassiveTreeFeature(NoneFeatureConfiguration.CODEC));
}
