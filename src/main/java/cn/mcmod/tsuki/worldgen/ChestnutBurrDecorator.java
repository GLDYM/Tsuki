package cn.mcmod.tsuki.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ChestnutBurrDecorator extends TreeDecorator {
    public static final MapCodec<ChestnutBurrDecorator> CODEC = RecordCodecBuilder
            .mapCodec(instance -> instance.group(Codec.FLOAT.fieldOf("probability").forGetter(d -> d.probability))
                    .apply(instance, ChestnutBurrDecorator::new));

    private final float probability;

    public ChestnutBurrDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TsukiTreeDecoratorTypes.CHESTNUT_BURR.get();
    }

    @Override
    public void place(Context context) {
        RandomSource rand = context.random();

        for (BlockPos leafPos : context.leaves()) {
            if (rand.nextFloat() >= probability) {
                continue;
            }

            BlockPos burrPos = leafPos.below();
            BlockPos belowBurrPos = burrPos.below();

            if (context.isAir(burrPos) && context.isAir(belowBurrPos)) {
                context.setBlock(burrPos, BlockRegistry.CHESTNUT_BURR.get().defaultBlockState());
            }
        }
    }
}
