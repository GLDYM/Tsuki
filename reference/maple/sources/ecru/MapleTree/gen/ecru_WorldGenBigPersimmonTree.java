package ecru.MapleTree.gen;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityPersimmonWood;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_WorldGenBigPersimmonTree extends WorldGenAbstractTree {
    static final byte[] otherCoordPairs = {2, 0, 0, 1, 2, 1};
    Random rand;
    World worldObj;
    int[] basePos;
    int heightLimit;
    int height;
    double heightAttenuation;
    double branchDensity;
    double branchSlope;
    double scaleWidth;
    double leafDensity;
    int trunkSize;
    int heightLimitLimit;
    int leafDistanceLimit;
    int[][] leafNodes;
    private static final String __OBFID = "CL_00000400";
    private Block log_block;
    private Block leaves_block;
    private Block log_block_0;
    private Block log_block_1;
    private Block log_block_2;

    public ecru_WorldGenBigPersimmonTree(boolean p_i2008_1_) {
        super(p_i2008_1_);
        this.rand = new Random();
        this.basePos = new int[]{0, 0, 0};
        this.heightAttenuation = 0.618d;
        this.branchDensity = 1.0d;
        this.branchSlope = 0.381d;
        this.scaleWidth = 1.0d;
        this.leafDensity = 1.0d;
        this.trunkSize = 1;
        this.heightLimitLimit = 12;
        this.leafDistanceLimit = 4;
        this.log_block_1 = Blocks.field_150339_S;
        this.log_block_2 = Blocks.field_150484_ah;
    }

    void generateLeafNodeList() {
        this.height = (int) (this.heightLimit * this.heightAttenuation);
        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }
        int i = (int) (1.382d + Math.pow((this.leafDensity * this.heightLimit) / 13.0d, 2.0d));
        if (i < 1) {
            i = 1;
        }
        int[][] aint = new int[i * this.heightLimit][4];
        int j = (this.basePos[1] + this.heightLimit) - this.leafDistanceLimit;
        int k = 1;
        int l = this.basePos[1] + this.height;
        int i1 = j - this.basePos[1];
        aint[0][0] = this.basePos[0];
        aint[0][1] = j;
        aint[0][2] = this.basePos[2];
        aint[0][3] = l;
        int j2 = j - 1;
        while (i1 >= 0) {
            float f = layerSize(i1);
            if (f < 0.0f) {
                j2--;
                i1--;
            } else {
                for (int j1 = 0; j1 < i; j1++) {
                    double d1 = this.scaleWidth * f * (this.rand.nextFloat() + 0.328d);
                    double d2 = this.rand.nextFloat() * 2.0d * 3.141592653589793d;
                    int k1 = MathHelper.func_76128_c((d1 * Math.sin(d2)) + this.basePos[0] + 0.5d);
                    int l1 = MathHelper.func_76128_c((d1 * Math.cos(d2)) + this.basePos[2] + 0.5d);
                    int[] aint1 = {k1, j2, l1};
                    int[] aint2 = {k1, j2 + this.leafDistanceLimit, l1};
                    if (checkBlockLine(aint1, aint2) == -1) {
                        int[] aint3 = {this.basePos[0], this.basePos[1], this.basePos[2]};
                        double d3 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - aint1[0]), 2.0d) + Math.pow(Math.abs(this.basePos[2] - aint1[2]), 2.0d));
                        double d4 = d3 * this.branchSlope;
                        if (aint1[1] - d4 > l) {
                            aint3[1] = l;
                        } else {
                            aint3[1] = (int) (aint1[1] - d4);
                        }
                        if (checkBlockLine(aint3, aint1) == -1) {
                            aint[k][0] = k1;
                            aint[k][1] = j2;
                            aint[k][2] = l1;
                            aint[k][3] = aint3[1];
                            k++;
                        }
                    }
                }
                j2--;
                i1--;
            }
        }
        this.leafNodes = new int[k][4];
        System.arraycopy(aint, 0, this.leafNodes, 0, k);
    }

    void func_150529_a(int p_150529_1_, int p_150529_2_, int p_150529_3_, float p_150529_4_, byte p_150529_5_, Block p_150529_6_) {
        int l = (int) (p_150529_4_ + 0.618d);
        byte b1 = otherCoordPairs[p_150529_5_];
        byte b2 = otherCoordPairs[p_150529_5_ + 3];
        int[] aint = {p_150529_1_, p_150529_2_, p_150529_3_};
        int[] aint1 = new int[3];
        aint1[0] = 0;
        aint1[1] = 0;
        aint1[2] = 0;
        int i = -l;
        aint1[p_150529_5_] = aint[p_150529_5_];
        for (int i1 = -l; i1 <= l; i1++) {
            aint1[b1] = aint[b1] + i1;
            int j1 = -l;
            while (j1 <= l) {
                double d0 = Math.pow(Math.abs(i1) + 0.5d, 2.0d) + Math.pow(Math.abs(j1) + 0.5d, 2.0d);
                if (d0 > p_150529_4_ * p_150529_4_) {
                    j1++;
                } else {
                    aint1[b2] = aint[b2] + j1;
                    Block block1 = this.worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);
                    if (!block1.isAir(this.worldObj, aint1[0], aint1[1], aint1[2]) && !block1.isLeaves(this.worldObj, aint1[0], aint1[1], aint1[2])) {
                        j1++;
                    } else {
                        func_150516_a(this.worldObj, aint1[0], aint1[1], aint1[2], p_150529_6_, 0);
                        j1++;
                    }
                }
            }
        }
    }

    float layerSize(int p_76490_1_) {
        float f2;
        if (p_76490_1_ < this.heightLimit * 0.3d) {
            return -1.618f;
        }
        float f = this.heightLimit / 2.0f;
        float f1 = (this.heightLimit / 2.0f) - p_76490_1_;
        if (f1 == 0.0f) {
            f2 = f;
        } else if (Math.abs(f1) >= f) {
            f2 = 0.0f;
        } else {
            f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2.0d) - Math.pow(Math.abs(f1), 2.0d));
        }
        return f2 * 0.5f;
    }

    float leafSize(int p_76495_1_) {
        if (p_76495_1_ < 0 || p_76495_1_ >= this.leafDistanceLimit) {
            return -1.0f;
        }
        return (p_76495_1_ == 0 || p_76495_1_ == this.leafDistanceLimit - 1) ? 2.0f : 3.0f;
    }

    void generateLeafNode(int p_76491_1_, int p_76491_2_, int p_76491_3_) {
        int i1 = p_76491_2_ + this.leafDistanceLimit;
        for (int l = p_76491_2_; l < i1; l++) {
            float f = leafSize(l - p_76491_2_);
            func_150529_a(p_76491_1_, l, p_76491_3_, f, (byte) 1, this.leaves_block);
        }
    }

    void func_150530_a(int[] p_150530_1_, int[] p_150530_2_, Block p_150530_3_, int t) {
        byte b4;
        int[] aint2 = new int[3];
        aint2[0] = 0;
        aint2[1] = 0;
        aint2[2] = 0;
        byte b1 = 0;
        for (byte b0 = 0; b0 < 3; b0 = (byte) (b0 + 1)) {
            aint2[b0] = p_150530_2_[b0] - p_150530_1_[b0];
            if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }
        if (aint2[b1] != 0) {
            byte b2 = otherCoordPairs[b1];
            byte b3 = otherCoordPairs[b1 + 3];
            if (aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }
            double d0 = aint2[b2] / aint2[b1];
            double d1 = aint2[b3] / aint2[b1];
            int[] aint3 = new int[3];
            aint3[0] = 0;
            aint3[1] = 0;
            aint3[2] = 0;
            int j = aint2[b1] + b4;
            for (int i = 0; i != j; i += b4) {
                aint3[b1] = MathHelper.func_76128_c(p_150530_1_[b1] + i + 0.5d);
                aint3[b2] = MathHelper.func_76128_c(p_150530_1_[b2] + (i * d0) + 0.5d);
                aint3[b3] = MathHelper.func_76128_c(p_150530_1_[b3] + (i * d1) + 0.5d);
                byte b5 = 0;
                int k = Math.abs(aint3[0] - p_150530_1_[0]);
                int l = Math.abs(aint3[2] - p_150530_1_[2]);
                int i1 = Math.max(k, l);
                if (i1 > 0) {
                    if (k == i1) {
                        b5 = 4;
                    } else if (l == i1) {
                        b5 = 8;
                    }
                }
                func_150516_a(this.worldObj, aint3[0], aint3[1], aint3[2], p_150530_3_, b5);
                setWoodType(t, aint3[0], aint3[1], aint3[2]);
            }
        }
    }

    void generateLeaves() {
        int j = this.leafNodes.length;
        for (int i = 0; i < j; i++) {
            int k = this.leafNodes[i][0];
            int l = this.leafNodes[i][1];
            int i1 = this.leafNodes[i][2];
            generateLeafNode(k, l, i1);
        }
    }

    boolean leafNodeNeedsBase(int p_76493_1_) {
        return ((double) p_76493_1_) >= ((double) this.heightLimit) * 0.2d;
    }

    void generateTrunk() {
        int i = this.basePos[0];
        int j = this.basePos[1];
        int k = this.basePos[1] + this.height;
        int l = this.basePos[2];
        int[] aint = {i, j, l};
        int[] aint1 = {i, k, l};
        func_150530_a(aint, aint1, this.log_block, 0);
        if (this.trunkSize == 2) {
            aint[0] = aint[0] + 1;
            aint1[0] = aint1[0] + 1;
            func_150530_a(aint, aint1, this.log_block, 0);
            aint[2] = aint[2] + 1;
            aint1[2] = aint1[2] + 1;
            func_150530_a(aint, aint1, this.log_block, 0);
            aint[0] = aint[0] - 1;
            aint1[0] = aint1[0] - 1;
            func_150530_a(aint, aint1, this.log_block, 0);
        }
    }

    void generateLeafNodeBases() {
        int j = this.leafNodes.length;
        int[] aint = {this.basePos[0], this.basePos[1], this.basePos[2]};
        for (int i = 0; i < j; i++) {
            int[] aint1 = this.leafNodes[i];
            int[] aint2 = {aint1[0], aint1[1], aint1[2]};
            aint[1] = aint1[3];
            int k = aint[1] - this.basePos[1];
            if (leafNodeNeedsBase(k)) {
                func_150530_a(aint, aint2, this.log_block, 2);
            }
        }
    }

    int checkBlockLine(int[] p_76496_1_, int[] p_76496_2_) {
        byte b4;
        int[] aint2 = new int[3];
        aint2[0] = 0;
        aint2[1] = 0;
        aint2[2] = 0;
        byte b1 = 0;
        for (byte b0 = 0; b0 < 3; b0 = (byte) (b0 + 1)) {
            aint2[b0] = p_76496_2_[b0] - p_76496_1_[b0];
            if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }
        if (aint2[b1] == 0) {
            return -1;
        }
        byte b2 = otherCoordPairs[b1];
        byte b3 = otherCoordPairs[b1 + 3];
        if (aint2[b1] > 0) {
            b4 = 1;
        } else {
            b4 = -1;
        }
        double d0 = aint2[b2] / aint2[b1];
        double d1 = aint2[b3] / aint2[b1];
        int[] aint3 = new int[3];
        aint3[0] = 0;
        aint3[1] = 0;
        aint3[2] = 0;
        int i = 0;
        int j = aint2[b1] + b4;
        while (i != j) {
            aint3[b1] = p_76496_1_[b1] + i;
            aint3[b2] = MathHelper.func_76128_c(p_76496_1_[b2] + (i * d0));
            aint3[b3] = MathHelper.func_76128_c(p_76496_1_[b3] + (i * d1));
            this.worldObj.func_147439_a(aint3[0], aint3[1], aint3[2]);
            if (!isReplaceable(this.worldObj, aint3[0], aint3[1], aint3[2])) {
                break;
            }
            i += b4;
        }
        if (i == j) {
            return -1;
        }
        return Math.abs(i);
    }

    boolean validTreeLocation() {
        int[] aint = {this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] aint1 = {this.basePos[0], (this.basePos[1] + this.heightLimit) - 1, this.basePos[2]};
        Block block = this.worldObj.func_147439_a(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
        boolean isSoil = block.canSustainPlant(this.worldObj, this.basePos[0], this.basePos[1] - 1, this.basePos[2], ForgeDirection.UP, Blocks.field_150345_g);
        if (!isSoil) {
            return false;
        }
        int i = checkBlockLine(aint, aint1);
        if (i == -1) {
            return true;
        }
        if (i < 6) {
            return false;
        }
        this.heightLimit = i;
        return true;
    }

    public void func_76487_a(double p_76487_1_, double p_76487_3_, double p_76487_5_) {
        this.heightLimitLimit = (int) (p_76487_1_ * 12.0d);
        if (p_76487_1_ > 0.5d) {
            this.leafDistanceLimit = 5;
        }
        this.scaleWidth = p_76487_3_;
        this.leafDensity = p_76487_5_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        return generate(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_, mod_ecru_MapleTree.blockPersimmonWood, mod_ecru_MapleTree.blockPersimmonLeaves);
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_, Block block1, Block block2) {
        this.worldObj = p_76484_1_;
        long l = p_76484_2_.nextLong();
        this.rand.setSeed(l);
        this.basePos[0] = p_76484_3_;
        this.basePos[1] = p_76484_4_;
        this.basePos[2] = p_76484_5_;
        this.log_block = block1;
        this.leaves_block = block2;
        if (this.heightLimit == 0) {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit) + 3;
        }
        if (!validTreeLocation()) {
            this.worldObj = null;
            return false;
        }
        generateLeafNodeList();
        generateTrunk();
        generateLeafNodeBases();
        setNodeList(p_76484_3_, p_76484_4_, p_76484_5_);
        this.worldObj = null;
        return true;
    }

    private void setNodeList(int i, int j, int k) {
        ecru_TileEntityPersimmonWood tileentity = (ecru_TileEntityPersimmonWood) this.worldObj.func_147438_o(i, j, k);
        if (tileentity == null) {
            return;
        }
        tileentity.setNumberOfNodes(this.leafNodes.length);
        for (int m = 0; m < this.leafNodes.length; m++) {
            tileentity.setNumberOfNodeList(this.leafNodes[m][0], this.leafNodes[m][1], this.leafNodes[m][2]);
        }
        setWoodType(1, i, j, k);
    }

    private void setWoodType(int t, int i, int j, int k) {
        ecru_TileEntityPersimmonWood tileentity = (ecru_TileEntityPersimmonWood) this.worldObj.func_147438_o(i, j, k);
        if (tileentity == null) {
            return;
        }
        tileentity.setWoodType(t);
    }
}
