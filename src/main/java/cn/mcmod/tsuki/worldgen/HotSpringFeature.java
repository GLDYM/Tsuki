package cn.mcmod.tsuki.worldgen;

import com.mojang.serialization.Codec;
import cn.mcmod.tsuki.init.block.FluidBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HotSpringFeature extends Feature<NoneFeatureConfiguration> {
    public HotSpringFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos base = context.origin().below(4);
        if (base.getY() <= level.getMinBuildHeight() + 1 || base.getY() + 8 >= level.getMaxBuildHeight()) {
            return false;
        }

        boolean[] cavity = new boolean[16 * 16 * 8];
        int blobs = random.nextInt(4) + 4;
        for (int n = 0; n < blobs; n++) {
            double sx = random.nextDouble() * 6 + 3, sy = random.nextDouble() * 4 + 2, sz = random.nextDouble() * 6 + 3;
            double cx = random.nextDouble() * (16 - sx - 2) + 1 + sx / 2;
            double cy = random.nextDouble() * (8 - sy - 4) + 2 + sy / 2;
            double cz = random.nextDouble() * (16 - sz - 2) + 1 + sz / 2;
            for (int x = 1; x < 15; x++)
                for (int z = 1; z < 15; z++)
                    for (int y = 1; y < 7; y++) {
                        double dx = (x - cx) / (sx / 2), dy = (y - cy) / (sy / 2), dz = (z - cz) / (sz / 2);
                        if (dx * dx + dy * dy + dz * dz < 1)
                            cavity[index(x, z, y)] = true;
                    }
        }
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++) {
                int lowestWaterY = -1;
                for (int y = 0; y < 4; y++) {
                    if (cavity[index(x, z, y)]) {
                        lowestWaterY = y;
                        break;
                    }
                }
                if (lowestWaterY >= 0) {
                    BlockPos floorPos = base.offset(x, lowestWaterY - 1, z);
                    if (!level.getBlockState(floorPos).isFaceSturdy(level, floorPos, Direction.UP)) {
                        return false;
                    }
                }
            }
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++)
                for (int y = 0; y < 8; y++) {
                    if (cavity[index(x, z, y)])
                        level.setBlock(base.offset(x, y, z), y >= 4 ? Blocks.AIR.defaultBlockState()
                                : FluidBlockRegistry.HOT_SPRING_WATER_BLOCK.get().defaultBlockState(), 2);
                }
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++)
                for (int y = 0; y < 8; y++) {
                    if (adjacent(cavity, x, z, y) && (y < 4 || random.nextBoolean())) {
                        BlockPos pos = base.offset(x, y, z);
                        if (level.getBlockState(pos).isFaceSturdy(level, pos, Direction.UP)) {
                            level.setBlock(pos, Blocks.STONE.defaultBlockState(), 2);
                        }
                    }
                }
        return true;
    }

    private static int index(int x, int z, int y) {
        return (x * 16 + z) * 8 + y;
    }

    private static boolean adjacent(boolean[] c, int x, int z, int y) {
        return (x > 0 && c[index(x - 1, z, y)]) || (x < 15 && c[index(x + 1, z, y)])
                || (z > 0 && c[index(x, z - 1, y)]) || (z < 15 && c[index(x, z + 1, y)])
                || (y > 0 && c[index(x, z, y - 1)]) || (y < 7 && c[index(x, z, y + 1)]);
    }
}
