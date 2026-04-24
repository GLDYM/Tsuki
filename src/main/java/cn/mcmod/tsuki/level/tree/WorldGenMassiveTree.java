package cn.mcmod.tsuki.level.tree;

import cn.mcmod.tsuki.block.BlockRegistry;
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

public class WorldGenMassiveTree {
    private static final float PI = (float) Math.PI;
    private static final byte[] OTHER_COORD_PAIRS = new byte[] {2, 0, 0, 1, 2, 1};

    private final boolean notify;
    private final Random random = new Random();
    private final int[] basePos = new int[] {0, 0, 0};
    private final int[] placeScratch = new int[3];
    private final int[] checkScratch = new int[3];

    private LevelAccessor level;
    private int heightLimit;
    private int minHeight = -1;
    private int height;
    private int leafBases;
    private int density;
    private int[][] leafNodes = new int[0][];
    private int leafNodesLength;

    private float heightAttenuation = 0.45f;
    private float branchSlope = 0.45f;
    private float scaleWidth = 4.0f;
    private float branchDensity = 3.0f;
    private int trunkSize = 11;
    private boolean slopeTrunk;
    private boolean safeGrowth;
    private int heightLimitLimit = 250;
    private int leafDistanceLimit = 4;

    private BlockState leavesState = BlockRegistry.SAKURA_LEAVES.get().defaultBlockState();
    private BlockState logState = BlockRegistry.SAKURA_LOG.get().defaultBlockState();

    public WorldGenMassiveTree(boolean notify) {
        this.notify = notify;
    }

    public WorldGenMassiveTree() {
        this(false);
    }

    public WorldGenMassiveTree setTreeScale(float treeHeight, float width, float leaves) {
        this.heightLimitLimit = (int) (treeHeight * 12.0D);
        this.minHeight = this.heightLimitLimit / 2;
        this.trunkSize = (int) Math.round(treeHeight / 2.0D);

        if (this.minHeight > 30) {
            this.leafDistanceLimit = 5;
        } else {
            this.leafDistanceLimit = Math.max(2, this.minHeight / 8);
        }

        this.scaleWidth = width;
        this.branchDensity = leaves;
        return this;
    }

    public WorldGenMassiveTree setMinTrunkSize(int radius) {
        this.trunkSize = Math.max(radius, this.trunkSize);
        return this;
    }

    public WorldGenMassiveTree setLeafAttenuation(float attenuation) {
        this.heightAttenuation = attenuation;
        return this;
    }

    public WorldGenMassiveTree setSloped(boolean sloped) {
        this.slopeTrunk = sloped;
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
        this.basePos[0] = pos.getX();
        this.basePos[1] = pos.getY();
        this.basePos[2] = pos.getZ();

        if (this.heightLimit == 0) {
            this.heightLimit = this.heightLimitLimit;
        }
        if (this.minHeight == -1) {
            this.minHeight = 80;
        }
        if (!this.validTreeLocation()) {
            return false;
        }

        this.setup();
        this.generateLeafNodeList();
        this.generateLeaves();
        this.generateLeafNodeBases();
        this.generateTrunk();
        return true;
    }

    private void setup() {
        this.leafBases = Mth.ceil(this.heightLimit * this.heightAttenuation);
        this.density = Math.max(1, (int) (1.382D + Math.pow(this.branchDensity * this.heightLimit / 13.0D, 2.0D)));
    }

    private float layerSize(int yLayer) {
        if (yLayer < this.leafBases) {
            return -1.618F;
        }

        float mid = this.heightLimit * 0.5F;
        float dist = this.heightLimit * 0.5F - yLayer;
        if (dist == 0.0F) {
            return mid;
        }
        if (Math.abs(dist) >= mid) {
            return 0.0F;
        }
        float r = (float) Math.sqrt(mid * mid - dist * dist);
        return r * 0.5F;
    }

    private void generateLeafNodeList() {
        int maxNodes = this.density;
        int[] base = this.basePos;
        int[][] nodes = new int[maxNodes * this.heightLimit][4];
        int y = base[1] + this.heightLimit - this.leafDistanceLimit;
        int added = 1;
        int trunkTop = base[1] + this.height;
        int relY = y - base[1];

        nodes[0][0] = base[0];
        nodes[0][1] = y;
        nodes[0][2] = base[2];
        nodes[0][3] = trunkTop;
        --y;

        while (relY >= 0) {
            float layerSize = this.layerSize(relY);
            if (layerSize > 0.0F) {
                for (int i = 0; i < maxNodes; ++i) {
                    float radius = this.scaleWidth * layerSize * (this.random.nextFloat() + 0.328f);
                    float angle = this.random.nextFloat() * 2.0f * PI;
                    int x = Mth.floor(radius * Mth.sin(angle) + base[0] + 0.5f);
                    int z = Mth.floor(radius * Mth.cos(angle) + base[2] + 0.5f);

                    int[] leafBottom = new int[] {x, y, z};
                    int[] leafTop = new int[] {x, y + this.leafDistanceLimit, z};
                    if (this.checkBlockLine(leafBottom, leafTop) != -1) {
                        continue;
                    }

                    int dx = base[0] - leafBottom[0];
                    int dz = base[2] - leafBottom[2];
                    double dist = Math.sqrt(dx * dx + dz * dz);
                    int branchDrop = (int) (dist * this.branchSlope);
                    int[] branchStart = new int[] {base[0], Math.min(leafBottom[1] - branchDrop, trunkTop), base[2]};
                    if (this.checkBlockLine(branchStart, leafBottom) == -1) {
                        nodes[added][0] = x;
                        nodes[added][1] = y;
                        nodes[added][2] = z;
                        nodes[added][3] = branchStart[1];
                        ++added;
                    }
                }
            }
            --y;
            --relY;
        }

        this.leafNodes = nodes;
        this.leafNodesLength = added;
    }

    private void generateLeaves() {
        for (int i = 0; i < this.leafNodesLength; ++i) {
            int[] node = this.leafNodes[i];
            int x = node[0];
            int y = node[1];
            int z = node[2];

            for (int layer = 0; layer < this.leafDistanceLimit; ++layer) {
                int size = (layer != 0 && layer != this.leafDistanceLimit - 1) ? 3 : 2;
                this.genLeafLayer(x, y + layer, z, size);
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
                        this.placeBlock(placePos, this.leavesState);
                    }
                }
            }
        }
    }

    private void generateLeafNodeBases() {
        int minBranchHeight = (int) (this.heightLimit * 0.2f);
        int[] start = new int[] {this.basePos[0], this.basePos[1], this.basePos[2]};
        for (int i = 0; i < this.leafNodesLength; ++i) {
            int[] end = this.leafNodes[i];
            start[1] = end[3];
            int relHeight = start[1] - this.basePos[1];
            if (relHeight >= minBranchHeight) {
                this.placeBlockLine(start, end, this.logState);
            }
        }
    }

    private void generateTrunk() {
        int x = this.basePos[0];
        int y = this.basePos[1];
        int maxY = this.basePos[1] + this.height;
        int z = this.basePos[2];

        int[] bottom = new int[] {x, y, z};
        int[] top = new int[] {x, maxY, z};
        double lim = 400f / this.trunkSize;

        for (int i = -this.trunkSize; i <= this.trunkSize; i++) {
            bottom[0] = x + i;
            top[0] = x + i;
            for (int j = -this.trunkSize; j <= this.trunkSize; j++) {
                if ((j * j + i * i) * 4 >= this.trunkSize * this.trunkSize * 5) {
                    continue;
                }
                bottom[2] = z + j;
                top[2] = z + j;
                if (this.slopeTrunk) {
                    top[1] = y + sinc2(lim * i, lim * j, this.height) - (this.random.nextInt(3) - 1);
                }

                this.placeBlockLine(bottom, top, this.logState);
                this.placeBlock(new BlockPos(top[0], top[1], top[2]), this.logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y));

            }
        }
    }

    private boolean validTreeLocation() {
        int maxY = this.level.getMaxBuildHeight() - 1;
        int adjusted = Math.min(this.heightLimit + this.basePos[1], maxY) - this.basePos[1];
        if (adjusted < this.minHeight) {
            return false;
        }
        this.heightLimit = adjusted;

        BlockPos soilPos = new BlockPos(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
        BlockState soil = this.level.getBlockState(soilPos);
        if (!soil.isFaceSturdy(this.level, soilPos, Direction.UP)) {
            return false;
        }

        int[] start = new int[] {this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] end = new int[] {this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        int freeHeight = this.checkBlockLine(start, end);

        if (freeHeight == -1) {
            freeHeight = this.heightLimit;
        }
        if (freeHeight < this.minHeight) {
            return false;
        }

        this.heightLimit = Math.min(freeHeight, this.heightLimitLimit);
        this.height = (int) (this.heightLimit * this.heightAttenuation);
        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }
        this.height += this.random.nextInt(this.heightLimit - this.height);

        if (!this.safeGrowth) {
            return true;
        }

        int x = this.basePos[0];
        int y = this.basePos[1];
        int z = this.basePos[2];
        int[] trunkStart = new int[] {x, y, z};
        int[] trunkEnd = new int[] {x, y + this.height, z};
        double lim = 400f / this.trunkSize;

        for (int i = -this.trunkSize; i <= this.trunkSize; i++) {
            trunkStart[0] = x + i;
            trunkEnd[0] = x + i;
            for (int j = -this.trunkSize; j <= this.trunkSize; j++) {
                if ((j * j + i * i) * 4 >= this.trunkSize * this.trunkSize * 5) {
                    continue;
                }
                trunkStart[2] = z + j;
                trunkEnd[2] = z + j;
                if (this.slopeTrunk) {
                    trunkEnd[1] = y + sinc2(lim * i, lim * j, this.height);
                }
                if (this.checkBlockLine(trunkStart, trunkEnd) != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeBlockLine(int[] start, int[] end, BlockState state) {
        int major = 0;
        for (byte i = 0; i < 3; ++i) {
            int delta = end[i] - start[i];
            this.placeScratch[i] = delta;
            if (Math.abs(delta) > Math.abs(this.placeScratch[major])) {
                major = i;
            }
        }

        if (this.placeScratch[major] == 0) {
            return;
        }

        byte sec = OTHER_COORD_PAIRS[major];
        byte tertiary = OTHER_COORD_PAIRS[major + 3];
        byte step = this.placeScratch[major] > 0 ? (byte) 1 : (byte) -1;
        float secStep = (float) this.placeScratch[sec] / (float) this.placeScratch[major];
        float thirdStep = (float) this.placeScratch[tertiary] / (float) this.placeScratch[major];
        int endStep = this.placeScratch[major] + step;
        int[] cursor = this.placeScratch;

        for (int i = 0; i != endStep; i += step) {
            cursor[major] = Mth.floor(start[major] + i + 0.5f);
            cursor[sec] = Mth.floor(start[sec] + i * secStep + 0.5f);
            cursor[tertiary] = Mth.floor(start[tertiary] + i * thirdStep + 0.5f);

            BlockPos pos = new BlockPos(cursor[0], cursor[1], cursor[2]);
            Direction.Axis axis = Direction.Axis.Y;
            int dx = Math.abs(cursor[0] - start[0]);
            int dz = Math.abs(cursor[2] - start[2]);
            int m = Math.max(dx, dz);
            if (m > 0) {
                axis = dx == m ? Direction.Axis.X : Direction.Axis.Z;
            }
            this.placeBlock(pos, state.setValue(RotatedPillarBlock.AXIS, axis));
        }
    }

    private int checkBlockLine(int[] start, int[] end) {
        int major = 0;
        for (byte i = 0; i < 3; ++i) {
            int delta = end[i] - start[i];
            this.checkScratch[i] = delta;
            if (Math.abs(delta) > Math.abs(this.checkScratch[major])) {
                major = i;
            }
        }

        if (this.checkScratch[major] == 0) {
            return -1;
        }

        byte sec = OTHER_COORD_PAIRS[major];
        byte tertiary = OTHER_COORD_PAIRS[major + 3];
        byte step = this.checkScratch[major] > 0 ? (byte) 1 : (byte) -1;
        float secStep = (float) this.checkScratch[sec] / (float) this.checkScratch[major];
        float thirdStep = (float) this.checkScratch[tertiary] / (float) this.checkScratch[major];
        int i = 0;
        int endStep = this.checkScratch[major] + step;
        int[] cursor = this.checkScratch;

        while (i != endStep) {
            cursor[major] = start[major] + i;
            cursor[sec] = Mth.floor(start[sec] + i * secStep);
            cursor[tertiary] = Mth.floor(start[tertiary] + i * thirdStep);

            BlockPos pos = new BlockPos(cursor[0], cursor[1], cursor[2]);
            BlockState state = this.level.getBlockState(pos);
            Block block = state.getBlock();

            boolean blocked;
            if (this.safeGrowth) {
                blocked = !(state.isAir()
                        || state.canBeReplaced()
                        || state.is(BlockTags.LEAVES)
                        || state.is(BlockTags.LOGS)
                        || block instanceof SaplingBlock);
            } else {
                blocked = state.is(Blocks.BEDROCK);
            }

            if (blocked) {
                break;
            }
            i += step;
        }

        return i == endStep ? -1 : Math.abs(i);
    }

    private void placeBlock(BlockPos pos, BlockState state) {
        int y = pos.getY();
        if (y < this.level.getMinBuildHeight() || y >= this.level.getMaxBuildHeight()) {
            return;
        }
        this.level.setBlock(pos, state, this.notify ? 3 : 2);
    }

    private static int sinc2(double x, double z, int y) {
        final double pi = Math.PI;
        final double pi2 = pi / 1.5;
        double r = Math.sqrt(Math.pow(x / pi, 2) + Math.pow(z / pi, 2)) * pi / 180.0;
        if (r == 0) {
            return y;
        }
        return (int) Math.round(y * (((Math.sin(r) / r) + (Math.sin(r * pi2) / (r * pi2))) / 2.0));
    }
}
