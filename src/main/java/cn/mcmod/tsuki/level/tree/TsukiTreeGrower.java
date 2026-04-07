package cn.mcmod.tsuki.level.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TsukiTreeGrower extends AbstractTreeGrower {

	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean pHasFlowers) {
        if (random.nextInt(10) == 0) {
            return TsukiTreeFeatures.FANCY_SAKURA_KEY;
        } else {
            return TsukiTreeFeatures.SAKURA_KEY;
        }
	}

}
