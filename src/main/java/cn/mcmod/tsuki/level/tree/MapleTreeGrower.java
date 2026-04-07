package cn.mcmod.tsuki.level.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import javax.annotation.Nullable;

public class MapleTreeGrower extends AbstractTreeGrower {
    private final ResourceKey<ConfiguredFeature<?, ?>>  tree;
    private final ResourceKey<ConfiguredFeature<?, ?>> fancy_tree;

    public MapleTreeGrower(ResourceKey<ConfiguredFeature<?, ?>> tree,ResourceKey<ConfiguredFeature<?, ?>> fancy_tree) {
        this.tree = tree;
        this.fancy_tree = fancy_tree;
    }

    @Override
    public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
        // TODO Auto-generated method stub
        return super.growTree(level, generator, pos, state, random);
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        if (random.nextInt(10) == 0) {
            return this.fancy_tree;
        } else {
            return this.tree;
        }
    }
}
