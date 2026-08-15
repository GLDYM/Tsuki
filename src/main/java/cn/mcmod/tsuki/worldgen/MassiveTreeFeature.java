package cn.mcmod.tsuki.worldgen;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

/**
 * A deliberately large, hand-built style sakura tree.  The generator first
 * plans every block, verifies that the complete shape fits, then places it so
 * failed attempts cannot leave partial trees behind.
 */
public class MassiveTreeFeature extends Feature<NoneFeatureConfiguration> {
    /** A radius of two produces a five-block-wide circular trunk. */
    private static final int TRUNK_RADIUS = 2;
    private static final int TRUNK_HEIGHT = 15;
    private static final double TAU = Math.PI * 2.0D;

    public MassiveTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos base = context.origin();
        RandomSource random = context.random();

        if (!level.getBlockState(base.below()).isFaceSturdy(level, base.below(), Direction.UP)
                || base.getY() < level.getMinBuildHeight() + 1
                || base.getY() + 30 >= level.getMaxBuildHeight()) {
            return false;
        }

        Map<BlockPos, BlockState> planned = new LinkedHashMap<>();
        buildTrunk(planned, base);
        buildRoots(planned, base, random);
        buildCrown(planned, base, random);

        for (BlockPos pos : planned.keySet()) {
            if (!canReplace(level, pos)) {
                return false;
            }
        }
        planned.forEach((pos, state) -> level.setBlock(pos, state, 2));
        return true;
    }

    private static void buildTrunk(Map<BlockPos, BlockState> planned, BlockPos base) {
        for (int y = 0; y < TRUNK_HEIGHT; y++) {
            int radius = y < 3 ? TRUNK_RADIUS + 1 : TRUNK_RADIUS;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    // A five-by-five square with only its four corners removed.
                    if (!(Math.abs(x) == radius && Math.abs(z) == radius)) {
                        log(planned, base.offset(x, y, z), Direction.UP);
                    }
                }
            }
        }
    }

    private static void buildRoots(Map<BlockPos, BlockState> planned, BlockPos base, RandomSource random) {
        for (int root = 0; root < 8; root++) {
            double angle = root * TAU / 8.0D + random.nextDouble() * 0.5D;
            int length = 5 + random.nextInt(4);
            BlockPos start = radialOffset(base, angle, 3, 1);
            BlockPos wideEnd = radialOffset(base, angle, 4, 0);
            BlockPos knee = radialOffset(base, angle, 6, 0);
            BlockPos end = radialOffset(base, angle, length, 0);
            logBall(planned, start, 1);
            // Broad four-block root base, a short two-block transition, then a
            // single-block tip following its chosen radial direction.
            line(planned, start, wideEnd, 1);
            line(planned, wideEnd, knee, 0);
            line(planned, knee, end, 0);
        }
    }

    private static void buildCrown(Map<BlockPos, BlockState> planned, BlockPos base, RandomSource random) {
        int[] heights = { 6, 8, 10, 12, 14 };

        for (int height : heights) {
            int branches = height >= 12 ? 4 : 3;
            double layerRotation = random.nextDouble() * TAU;
            for (int index = 0; index < branches; index++) {
                double angle = layerRotation + index * TAU / branches + (random.nextDouble() - 0.5D) * 0.45D;
                int length = 8 + random.nextInt(5);
                BlockPos joint = base.above(height);
                int lift = height <= 9 ? 2 + random.nextInt(2) : 0;
                BlockPos branchStart = joint.above(lift);
                BlockPos end = radialOffset(branchStart, angle, length, random.nextInt(2));

                // A thicker connection makes the horizontal branches read as part
                // of the massive trunk rather than as thin, floating sticks.
                logBall(planned, joint, 1);
                line(planned, joint, branchStart, 0);
                line(planned, branchStart, end, 0);

                if (height >= 9 && random.nextBoolean()) {
                    double forkAngle = angle + (random.nextBoolean() ? Math.PI / 2.0D : -Math.PI / 2.0D)
                            + (random.nextDouble() - 0.5D) * 0.35D;
                    BlockPos forkStart = radialOffset(branchStart, angle, length / 2, 1);
                    BlockPos forkEnd = radialOffset(forkStart, forkAngle, 4 + random.nextInt(3), 2);
                    line(planned, forkStart, forkEnd, 0);
                    buildSmallSakura(planned, forkEnd, forkAngle, random);
                }
                buildSmallSakura(planned, end, angle, random);
            }
        }

        buildSmallSakura(planned, base.above(TRUNK_HEIGHT - 1),
                random.nextDouble() * TAU, random);
    }

    /** Mirrors the vanilla cherry silhouette: rise, one or two lateral limbs, rise again, then foliage. */
    private static void buildSmallSakura(Map<BlockPos, BlockState> planned, BlockPos start, double angle,
            RandomSource random) {
        BlockPos stemTop = start.above(3 + random.nextInt(3));
        line(planned, start, stemTop, 0);

        int limbLength = 2 + random.nextInt(3);
        BlockPos firstTip = radialOffset(stemTop, angle, limbLength, random.nextInt(2));
        line(planned, stemTop, firstTip, 0);
        BlockPos firstCanopy = firstTip.above(2 + random.nextInt(2));
        line(planned, firstTip, firstCanopy, 0);
        addCanopy(planned, firstCanopy, random);

        if (random.nextBoolean()) {
            BlockPos secondTip = radialOffset(stemTop, angle + Math.PI, limbLength - 1, random.nextInt(2));
            line(planned, stemTop, secondTip, 0);
            BlockPos secondCanopy = secondTip.above(2 + random.nextInt(2));
            line(planned, secondTip, secondCanopy, 0);
            addCanopy(planned, secondCanopy, random);
        }
    }

    private static void addCanopy(Map<BlockPos, BlockState> planned, BlockPos center, RandomSource random) {
        for (int y = -3; y <= 3; y++) {
            int radius = y == -3 || y == 3 ? 2 : 3;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z <= radius * radius) {
                        leaf(planned, center.offset(x, y, z));
                    }
                }
            }
        }
        // Sparse pendant leaves preserve the characteristic cherry-tree drape.
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(5) - 2;
            int z = random.nextInt(5) - 2;
            if (x * x + z * z > 4) {
                continue;
            }
            leaf(planned, center.offset(x, -4, z));
            if (random.nextBoolean()) {
                leaf(planned, center.offset(x, -5, z));
            }
        }
    }

    private static void line(Map<BlockPos, BlockState> planned, BlockPos from, BlockPos to, int radius) {
        int steps = Math.max(Math.abs(to.getX() - from.getX()),
                Math.max(Math.abs(to.getY() - from.getY()), Math.abs(to.getZ() - from.getZ())));
        Direction axis = dominantAxis(from, to);
        BlockPos previous = null;
        for (int step = 0; step <= steps; step++) {
            double progress = steps == 0 ? 0 : (double) step / steps;
            BlockPos point = new BlockPos(
                    (int) Math.round(from.getX() + (to.getX() - from.getX()) * progress),
                    (int) Math.round(from.getY() + (to.getY() - from.getY()) * progress),
                    (int) Math.round(from.getZ() + (to.getZ() - from.getZ()) * progress));
            if (previous != null) {
                connect(planned, previous, point, radius, axis);
            }
            logBall(planned, point, radius, axis);
            previous = point;
        }
    }

    /** Adds orthogonal bridge blocks so a diagonal voxel line never joins only at corners. */
    private static void connect(Map<BlockPos, BlockState> planned, BlockPos from, BlockPos to, int radius,
            Direction axis) {
        BlockPos cursor = from;
        while (cursor.getX() != to.getX()) {
            cursor = cursor.offset(Integer.compare(to.getX(), cursor.getX()), 0, 0);
            logBall(planned, cursor, radius, axis);
        }
        while (cursor.getZ() != to.getZ()) {
            cursor = cursor.offset(0, 0, Integer.compare(to.getZ(), cursor.getZ()));
            logBall(planned, cursor, radius, axis);
        }
        while (cursor.getY() != to.getY()) {
            cursor = cursor.offset(0, Integer.compare(to.getY(), cursor.getY()), 0);
            logBall(planned, cursor, radius, axis);
        }
    }

    private static Direction dominantAxis(BlockPos from, BlockPos to) {
        int x = Math.abs(to.getX() - from.getX());
        int y = Math.abs(to.getY() - from.getY());
        int z = Math.abs(to.getZ() - from.getZ());
        return x >= y && x >= z ? Direction.EAST : z >= y ? Direction.SOUTH : Direction.UP;
    }

    private static BlockPos radialOffset(BlockPos origin, double angle, int distance, int yOffset) {
        return origin.offset((int) Math.round(Math.cos(angle) * distance), yOffset,
                (int) Math.round(Math.sin(angle) * distance));
    }

    private static void logBall(Map<BlockPos, BlockState> planned, BlockPos center, int radius) {
        logBall(planned, center, radius, Direction.UP);
    }

    private static void logBall(Map<BlockPos, BlockState> planned, BlockPos center, int radius, Direction axis) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius) {
                        log(planned, center.offset(x, y, z), axis);
                    }
                }
            }
        }
    }

    private static void log(Map<BlockPos, BlockState> planned, BlockPos pos, Direction axis) {
        planned.put(pos, BlockRegistry.SAKURA_WOOD.get().defaultBlockState()
                .setValue(RotatedPillarBlock.AXIS, axis.getAxis()));
    }

    private static void leaf(Map<BlockPos, BlockState> planned, BlockPos pos) {
        planned.putIfAbsent(pos, BlockRegistry.SAKURA_LEAVES.get().defaultBlockState()
                .setValue(LeavesBlock.PERSISTENT, false)
                .setValue(LeavesBlock.DISTANCE, 7));
    }

    private static boolean canReplace(WorldGenLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.isAir() || state.is(BlockRegistry.SAKURA_LEAVES.get()) || state.is(Blocks.CHERRY_LEAVES)
                || state.canBeReplaced();
    }
}
