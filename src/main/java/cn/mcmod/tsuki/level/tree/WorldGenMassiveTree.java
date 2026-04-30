package cn.mcmod.tsuki.level.tree;

import cn.mcmod.tsuki.block.BlockRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;

// TODO: Need to Review, too much magic numbers. I am not completely understand Space Colonization.
public class WorldGenMassiveTree {

    private static final int ROOT_DIAMETER = 4;
    private static final int ROOT_RADIUS = ROOT_DIAMETER / 2;
    private static final double SHROOMLIGHT_CHANCE = 0.03;

    private final boolean notify;
    private final Random random = new Random();

    private LevelAccessor level;
    private int baseX;
    private int baseY;
    private int baseZ;

    private int heightLimit;
    private int minHeight = -1;
    private int height;

    private float heightAttenuation = 0.75f;
    private float scaleWidth = 1.0f;
    private float branchDensity = 1.0f;
    private boolean safeGrowth;
    private int heightLimitLimit = 120;

    private BlockState leavesState = BlockRegistry.SAKURA_LEAVES.get().defaultBlockState();
    private BlockState logState = BlockRegistry.SAKURA_LOG.get().defaultBlockState();
    private BlockState shroomlightState = Blocks.SHROOMLIGHT.defaultBlockState();

    private static final class Vec3 {
        final double x;
        final double y;
        final double z;

        Vec3(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        Vec3 add(Vec3 o) {
            return new Vec3(this.x + o.x, this.y + o.y, this.z + o.z);
        }

        Vec3 scale(double s) {
            return new Vec3(this.x * s, this.y * s, this.z * s);
        }

        double length() {
            return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        }

        Vec3 normalize() {
            double len = this.length();
            if (len < 1.0e-6) {
                return new Vec3(0.0, 1.0, 0.0);
            }
            return new Vec3(this.x / len, this.y / len, this.z / len);
        }

        double distanceTo(Vec3 o) {
            double dx = this.x - o.x;
            double dy = this.y - o.y;
            double dz = this.z - o.z;
            return Math.sqrt(dx * dx + dy * dy + dz * dz);
        }
    }

    private static final class Node {
        final Vec3 pos;
        final int parent;
        int children;

        Node(Vec3 pos, int parent) {
            this.pos = pos;
            this.parent = parent;
        }
    }

    private static final class TrunkProfile {
        final Vec3 topCenter;
        final int topRadius;

        TrunkProfile(Vec3 topCenter, int topRadius) {
            this.topCenter = topCenter;
            this.topRadius = topRadius;
        }
    }

    public WorldGenMassiveTree(boolean notify) {
        this.notify = notify;
    }

    public WorldGenMassiveTree() {
        this(false);
    }

    public WorldGenMassiveTree setTreeScale(float treeHeight, float width, float leaves) {
        this.heightLimitLimit = Math.max(40, (int) (treeHeight * 12.0f));
        this.minHeight = Math.max(24, this.heightLimitLimit / 3);
        this.scaleWidth = Math.max(0.6f, width);
        this.branchDensity = Math.max(0.4f, leaves);
        return this;
    }

    public WorldGenMassiveTree setLeafAttenuation(float attenuation) {
        this.heightAttenuation = Mth.clamp(attenuation, 0.45f, 0.95f);
        return this;
    }

    public WorldGenMassiveTree setSafe(boolean safe) {
        this.safeGrowth = safe;
        return this;
    }

    public WorldGenMassiveTree setBlocks(BlockState log, BlockState leaves) {
        this.logState = log;
        this.leavesState = leaves;
        return this;
    }

    public boolean generate(WorldGenLevel world, RandomSource rand, BlockPos pos) {
        return this.generate((LevelAccessor) world, rand, pos);
    }

    public boolean generate(LevelAccessor world, RandomSource rand, BlockPos pos) {
        this.level = world;
        this.random.setSeed(rand.nextLong());
        this.baseX = pos.getX();
        this.baseY = pos.getY();
        this.baseZ = pos.getZ();

        if (this.heightLimit == 0) {
            this.heightLimit = this.heightLimitLimit;
        }
        if (this.minHeight == -1) {
            this.minHeight = 48;
        }
        if (!this.validTreeLocation()) {
            return false;
        }

        TrunkProfile trunk = this.generateTrunk();
        List<Node> skeleton = this.generateSpaceColonizationSkeleton(trunk.topCenter);
        this.generateBranches(skeleton);
        this.generateLeafClusters(skeleton);
        return true;
    }

    private List<Node> generateSpaceColonizationSkeleton(Vec3 trunkTopCenter) {
        List<Vec3> attractors = this.sampleCrownAttractors();
        double influenceRadius = Math.max(22.0, this.height * 0.62 * this.scaleWidth);
        List<Node> nodes = new ArrayList<>();

        nodes.add(new Node(new Vec3(trunkTopCenter.x, trunkTopCenter.y, trunkTopCenter.z), -1));

        double stepLen = 1.35;
        double killDist = 3.0;
        int maxIterations = this.height * 5;

        for (int iteration = 0; iteration < maxIterations && !attractors.isEmpty(); iteration++) {
            Vec3[] dirSums = new Vec3[nodes.size()];
            int[] counts = new int[nodes.size()];

            Iterator<Vec3> it = attractors.iterator();
            while (it.hasNext()) {
                Vec3 a = it.next();
                int nearest = -1;
                double nearestDist = Double.MAX_VALUE;

                for (int i = 0; i < nodes.size(); i++) {
                    double d = nodes.get(i).pos.distanceTo(a);
                    if (d < killDist) {
                        nearest = -2;
                        break;
                    }
                    if (d <= influenceRadius && d < nearestDist) {
                        nearestDist = d;
                        nearest = i;
                    }
                }

                if (nearest == -2) {
                    it.remove();
                } else if (nearest >= 0) {
                    Node n = nodes.get(nearest);
                    Vec3 dir = new Vec3(a.x - n.pos.x, a.y - n.pos.y, a.z - n.pos.z).normalize();
                    if (dirSums[nearest] == null) {
                        dirSums[nearest] = dir;
                    } else {
                        dirSums[nearest] = dirSums[nearest].add(dir);
                    }
                    counts[nearest]++;
                }
            }

            int oldSize = nodes.size();
            for (int i = 0; i < oldSize; i++) {
                if (counts[i] == 0) {
                    continue;
                }

                Node parent = nodes.get(i);
                Vec3 averaged = dirSums[i].scale(1.0 / counts[i]).normalize();
                double rel = Mth.clamp((parent.pos.y - this.baseY) / (double) this.height, 0.0, 1.0);
                Vec3 upBias = new Vec3(0.0, 0.12 * (1.0 - rel), 0.0);
                Vec3 droopBias = new Vec3(0.0, -0.15 - 0.10 * rel, 0.0);
                Vec3 noise = new Vec3(
                        (this.random.nextDouble() * 2.0 - 1.0) * 0.22,
                        (this.random.nextDouble() * 2.0 - 1.0) * 0.16,
                        (this.random.nextDouble() * 2.0 - 1.0) * 0.22);
                Vec3 growthDir = averaged.add(upBias).add(noise).normalize();
                growthDir = growthDir.add(droopBias).normalize();
                Vec3 next = parent.pos.add(growthDir.scale(stepLen));

                if (next.y >= this.baseY + this.height - 2) {
                    continue;
                }
                if (next.y <= this.baseY + 1) {
                    continue;
                }
                if (!this.canOccupy(next)) {
                    continue;
                }

                nodes.add(new Node(next, i));
                parent.children++;
            }

            if (nodes.size() == oldSize) {
                break;
            }
        }

        if (nodes.size() <= 1) {
            this.seedFallbackBranches(nodes, trunkTopCenter);
        }
        return nodes;
    }

    private void addBasalAttractors(List<Vec3> attractors, Vec3 start) {
        int count = 36 + this.random.nextInt(14);
        for (int i = 0; i < count; i++) {
            double angle = this.random.nextDouble() * Math.PI * 2.0;
            double radius = 2.4 + this.random.nextDouble() * 2.0;
            double yOffset = -2.6 + this.random.nextDouble() * 1.4;
            attractors.add(new Vec3(
                    start.x + Math.cos(angle) * radius,
                    start.y + yOffset,
                    start.z + Math.sin(angle) * radius));
        }
    }

    private void seedFallbackBranches(List<Node> nodes, Vec3 start) {
        int limbs = 4 + this.random.nextInt(3);
        for (int i = 0; i < limbs; i++) {
            double angle = (Math.PI * 2.0 * i) / limbs + this.random.nextDouble() * 0.45;
            double len = 5.0 + this.random.nextDouble() * 3.5;
            double rise = -2.0 + this.random.nextDouble() * 2.0;
            Vec3 end = new Vec3(
                    start.x + Math.cos(angle) * len,
                    start.y + rise,
                    start.z + Math.sin(angle) * len);
            nodes.add(new Node(end, 0));
            nodes.get(0).children++;
        }
    }

    private List<Vec3> sampleCrownAttractors() {
        int count = (int) (1200 * this.branchDensity * this.scaleWidth);
        count = Mth.clamp(count, 700, 3500);

        List<Vec3> points = new ArrayList<>(count);
        double crownHeight = this.height * this.heightAttenuation;
        double rx = Math.max(10.0, this.height * 0.32 * this.scaleWidth);
        double rz = rx;
        double ry = Math.max(11.0, crownHeight * 0.42);
        double cy = this.baseY + this.height * 0.53;

        for (int i = 0; i < count; i++) {
            double x;
            double y;
            double z;
            while (true) {
                x = (this.random.nextDouble() * 2.0 - 1.0) * rx;
                y = (this.random.nextDouble() * 2.0 - 1.0) * ry;
                z = (this.random.nextDouble() * 2.0 - 1.0) * rz;
                double ellipsoid = (x * x) / (rx * rx) + (y * y) / (ry * ry) + (z * z) / (rz * rz);
                if (ellipsoid <= 1.0) {
                    break;
                }
            }

            // Push points toward the crown shell to form natural branch distribution.
            double shellBias = 0.78 + this.random.nextDouble() * 0.22;
            points.add(new Vec3(this.baseX + 0.5 + x * shellBias, cy + y * shellBias, this.baseZ + 0.5 + z * shellBias));
        }
        return points;
    }

    private TrunkProfile generateTrunk() {
        int trunkTop = this.baseY + (int) (this.height * 0.10);
        double topCenterX = this.baseX + 0.5;
        double topCenterZ = this.baseZ + 0.5;
        int topRadius = ROOT_RADIUS;
        for (int y = this.baseY; y <= trunkTop; y++) {
            double rel = (y - this.baseY) / (double) Math.max(1, (trunkTop - this.baseY));
            double centerX = this.baseX + 0.5;
            double centerZ = this.baseZ + 0.5;

            double taper = 1.0 - rel * 0.28;
            double waviness = 1.0;
            int radius = Mth.clamp((int) Math.round(ROOT_RADIUS * taper * waviness), 3, ROOT_RADIUS + 1);
            this.placeLogDisk(Mth.floor(centerX), y, Mth.floor(centerZ), radius);
            topCenterX = centerX;
            topCenterZ = centerZ;
            topRadius = radius;
        }
        return new TrunkProfile(new Vec3(topCenterX, trunkTop, topCenterZ), topRadius);
    }

    private void generateBranches(List<Node> nodes) {
        for (int i = 1; i < nodes.size(); i++) {
            Node child = nodes.get(i);
            Node parent = nodes.get(child.parent);

            double parentRel = Mth.clamp((parent.pos.y - this.baseY) / (double) this.height, 0.0, 1.0);
            int radius = Mth.clamp((int) Math.round((1.0 - parentRel) * 2), 1, 2);

            this.placeBranchSegment(parent.pos, child.pos, radius);
        }
    }

    private void generateLeafClusters(List<Node> nodes) {
        for (Node node : nodes) {
            if (node.children > 0) {
                continue;
            }
            int y = Mth.floor(node.pos.y);
            if (y < this.baseY + this.height * 0.20) {
                continue;
            }
            int x = Mth.floor(node.pos.x);
            int z = Mth.floor(node.pos.z);
            this.genLeafLayer(x, y - 2, z, 2);
            this.genLeafLayer(x, y - 1, z, 3);
            this.genLeafLayer(x, y, z, 3);
            this.genLeafLayer(x, y + 1, z, 2);
        }
    }

    private void placeBranchSegment(Vec3 from, Vec3 to, int radius) {
        double dx = to.x - from.x;
        double dy = to.y - from.y;
        double dz = to.z - from.z;
        int steps = Math.max(1, (int) Math.ceil(Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)))));

        Direction.Axis axis = Direction.Axis.Y;
        if (Math.abs(dx) > Math.abs(dz) && Math.abs(dx) > Math.abs(dy)) {
            axis = Direction.Axis.X;
        } else if (Math.abs(dz) > Math.abs(dx) && Math.abs(dz) > Math.abs(dy)) {
            axis = Direction.Axis.Z;
        }
        BlockState state = this.logState.setValue(RotatedPillarBlock.AXIS, axis);

        for (int i = 0; i <= steps; i++) {
            double t = i / (double) steps;
            int x = Mth.floor(from.x + dx * t);
            int y = Mth.floor(from.y + dy * t);
            int z = Mth.floor(from.z + dz * t);
            this.placeLogDisk(x, y, z, radius, state);
        }
    }

    private void placeLogDisk(int centerX, int y, int centerZ, int radius) {
        this.placeLogDisk(centerX, y, centerZ, radius, this.logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y));
    }

    private void placeLogDisk(int centerX, int y, int centerZ, int radius, BlockState state) {
        int rr = radius * radius;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z > rr) {
                    continue;
                }
                this.placeBlock(new BlockPos(centerX + x, y, centerZ + z), state);
            }
        }
    }

    private void genLeafLayer(int centerX, int y, int centerZ, int radius) {
        float maxDistSq = radius * radius;
        for (int xMod = -radius; xMod <= radius; ++xMod) {
            int x = centerX + xMod;
            int xDistSq = xMod * xMod + Math.abs(xMod);
            for (int zMod = 0; zMod <= radius; ++zMod) {
                float distSq = xDistSq + zMod * zMod + zMod + 0.5f;
                if (distSq > maxDistSq) {
                    break;
                }

                for (int sign = -1; sign <= 1; sign += 2) {
                    int z = centerZ + zMod * sign;
                    BlockPos placePos = new BlockPos(x, y, z);
                    BlockState state = this.level.getBlockState(placePos);
                    Block block = state.getBlock();
                    boolean canPlace = this.safeGrowth
                            ? (state.isAir() || state.canBeReplaced() || state.is(BlockTags.LEAVES))
                            : !state.is(Blocks.BEDROCK);
                    if (canPlace || block instanceof SaplingBlock) {
                        this.placeBlock(placePos, this.random.nextDouble() < SHROOMLIGHT_CHANCE ? this.shroomlightState : this.leavesState);
                    }
                }
            }
        }
    }

    private boolean validTreeLocation() {
        int maxY = this.level.getMaxBuildHeight() - 1;
        int adjusted = Math.min(this.heightLimit + this.baseY, maxY) - this.baseY;
        if (adjusted < this.minHeight) {
            return false;
        }
        this.heightLimit = adjusted;

        BlockPos soilPos = new BlockPos(this.baseX, this.baseY - 1, this.baseZ);
        BlockState soil = this.level.getBlockState(soilPos);
        if (!soil.isFaceSturdy(this.level, soilPos, Direction.UP)) {
            return false;
        }

        this.height = (int) (this.heightLimit * this.heightAttenuation);
        this.height = Mth.clamp(this.height, this.minHeight, this.heightLimit - 1);
        if (this.height < 20) {
            return false;
        }

        if (!this.safeGrowth) {
            return true;
        }

        int top = this.baseY + this.height;
        for (int y = this.baseY; y <= top; y++) {
            for (int x = -ROOT_RADIUS; x <= ROOT_RADIUS; x++) {
                for (int z = -ROOT_RADIUS; z <= ROOT_RADIUS; z++) {
                    if (x * x + z * z > ROOT_RADIUS * ROOT_RADIUS) {
                        continue;
                    }
                    BlockPos p = new BlockPos(this.baseX + x, y, this.baseZ + z);
                    BlockState s = this.level.getBlockState(p);
                    Block b = s.getBlock();
                    boolean blocked = !(s.isAir() || s.canBeReplaced() || s.is(BlockTags.LEAVES) || s.is(BlockTags.LOGS)
                            || b instanceof SaplingBlock);
                    if (blocked) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean canOccupy(Vec3 vec) {
        BlockPos pos = new BlockPos(Mth.floor(vec.x), Mth.floor(vec.y), Mth.floor(vec.z));
        if (pos.getY() < this.level.getMinBuildHeight() || pos.getY() >= this.level.getMaxBuildHeight()) {
            return false;
        }

        BlockState state = this.level.getBlockState(pos);
        Block block = state.getBlock();
        if (this.safeGrowth) {
            return state.isAir() || state.canBeReplaced() || state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)
                    || block instanceof SaplingBlock;
        }
        return !state.is(Blocks.BEDROCK);
    }

    private void placeBlock(BlockPos pos, BlockState state) {
        int y = pos.getY();
        if (y < this.level.getMinBuildHeight() || y >= this.level.getMaxBuildHeight()) {
            return;
        }
        this.level.setBlock(pos, state, this.notify ? 3 : 2);
    }
}
