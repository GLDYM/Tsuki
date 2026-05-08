package cn.mcmod.tsuki.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Comparator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MapleFallenLeavesDecorator extends TreeDecorator {
    public static final MapCodec<MapleFallenLeavesDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
            .group(BuiltInRegistries.BLOCK.byNameCodec().fieldOf("fallen_block").forGetter(d -> d.fallenBlock))
            .apply(instance, MapleFallenLeavesDecorator::new));

    private static final int RADIUS = 4;
    private static final int Y_TOP_OFFSET = 2;
    private static final int Y_BOTTOM_OFFSET = 4;

    private final Block fallenBlock;

    public MapleFallenLeavesDecorator(Block fallenBlock) {
        this.fallenBlock = fallenBlock;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return TsukiTreeDecoratorTypes.MAPLE_FALLEN_LEAVES.get();
    }

    @Override
    public void place(Context context) {
        List<BlockPos> logs = context.logs();
        if (logs.isEmpty()) {
            return;
        }

        RandomSource random = context.random();
        BlockPos base = logs.stream().min(Comparator.comparingInt(BlockPos::getY)).orElse(logs.get(0));

        for (int dx = -RADIUS; dx <= RADIUS; dx++) {
            for (int dz = -RADIUS; dz <= RADIUS; dz++) {
                if (Math.abs(dx) == RADIUS && Math.abs(dz) == RADIUS) {
                    continue;
                }

                if (Math.abs(dx) == RADIUS || Math.abs(dz) == RADIUS) {
                    if (random.nextBoolean()) {
                        continue;
                    }
                }

                int x = base.getX() + dx;
                int z = base.getZ() + dz;

                for (int y = base.getY() + Y_TOP_OFFSET; y >= base.getY() - Y_BOTTOM_OFFSET; y--) {
                    BlockPos placePos = new BlockPos(x, y, z);
                    BlockPos supportPos = placePos.below();

                    boolean canPlaceHere = context.level().isStateAtPosition(placePos,
                            state -> (state.isAir() || state.canBeReplaced()) && state.getFluidState().isEmpty());
                    if (!canPlaceHere) {
                        continue;
                    }

                    boolean hasSolidSupport = context.level().isStateAtPosition(supportPos,
                            support -> !support.isAir() && support.getFluidState().isEmpty());
                    if (!hasSolidSupport) {
                        continue;
                    }

                    context.setBlock(placePos, this.fallenBlock.defaultBlockState());
                    break;
                }
            }
        }
    }
}
