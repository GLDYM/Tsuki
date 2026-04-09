package cn.mcmod.tsuki.level.tree;

import cn.mcmod.tsuki.block.BlockRegistry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MapleSapLogDecorator extends TreeDecorator {
    public static final MapCodec<MapleSapLogDecorator> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(d -> d.probability))
                    .apply(instance, MapleSapLogDecorator::new));

    private final float probability;

    public MapleSapLogDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TsukiTreeDecoratorTypes.MAPLE_SAP_LOG.get();
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

        int minY = logs.stream().mapToInt(BlockPos::getY).min().orElse(Integer.MIN_VALUE);
        int targetY = minY + 1;

        List<BlockPos> candidates = logs.stream().filter(pos -> pos.getY() == targetY).toList();
        if (candidates.isEmpty()) {
            return;
        }

        BlockPos selected = candidates.get(random.nextInt(candidates.size()));
        context.setBlock(selected, BlockRegistry.MAPLE_SAP_LOG.get().defaultBlockState());
    }
}
