package cn.mcmod.tsuki.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import cn.mcmod.tsuki.init.TreeDecoratorTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MapleSapLogDecorator extends TreeDecorator {
    public static final MapCodec<MapleSapLogDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
            .group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(d -> d.probability))
            .apply(instance, MapleSapLogDecorator::new));

    private final float probability;

    public MapleSapLogDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TreeDecoratorTypeRegistry.MAPLE_SAP_LOG.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        if (random.nextFloat() >= this.probability) {
            return;
        }

        List<BlockPos> logs = context.logs();
        if (logs.isEmpty()) {
            return;
        }

        Set<BlockPos> logSet = new HashSet<>(logs);
        // Prefer the second block above trunk roots, which is stable for both worldgen
        // and sapling growth.
        List<BlockPos> candidates = logs.stream()
                .filter(pos -> logSet.contains(pos.below()) && !logSet.contains(pos.below(2)))
                .toList();
        if (candidates.isEmpty()) {
            int minY = logs.stream().mapToInt(BlockPos::getY).min().orElse(Integer.MIN_VALUE);
            int targetY = minY + 1;
            candidates = logs.stream().filter(pos -> pos.getY() == targetY).toList();
        }
        if (candidates.isEmpty()) {
            return;
        }

        BlockPos selected = candidates.get(random.nextInt(candidates.size()));
        context.setBlock(selected, BlockRegistry.MAPLE_SAP_LOG.get().defaultBlockState());
    }
}
