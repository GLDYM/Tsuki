package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityPersimmonWood extends TileEntity {
    static final byte[] otherCoordPairs = {2, 0, 0, 1, 2, 1};
    private int numberOfNodes;
    private int woodType;
    int leafDistanceLimit = 4;
    List<Integer> nlX = new ArrayList();
    List<Integer> nlY = new ArrayList();
    List<Integer> nlZ = new ArrayList();
    private long elapsedTime = 0;
    private int leavesCount = 0;
    private int mode = 0;
    private int CYCLE_TIME = 7200;
    private int GROWS_TIME = 1380;
    private int CHANGE_COLOR1 = 1200;
    private int CHANGE_COLOR2 = 1500;
    private int CHANGE_COLOR3 = 2100;
    private int FALL_TIME = 3000;
    private long TIMERCOUNT = 20;
    private long timer = this.TIMERCOUNT;
    private final Random random = new Random();

    public Packet func_145844_m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        func_145841_b(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, nbttagcompound);
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.woodType = nbttagcompound.func_74762_e("woodType");
        this.numberOfNodes = nbttagcompound.func_74762_e("numberOfNodes");
        this.elapsedTime = nbttagcompound.func_74763_f("elapsedTime");
        this.leavesCount = nbttagcompound.func_74762_e("leavesCount");
        for (int i = 0; i < this.numberOfNodes; i++) {
            if (this.nlX.size() < i + 1) {
                this.nlX.add(Integer.valueOf(nbttagcompound.func_74762_e("nlX" + i)));
                this.nlY.add(Integer.valueOf(nbttagcompound.func_74762_e("nlY" + i)));
                this.nlZ.add(Integer.valueOf(nbttagcompound.func_74762_e("nlZ" + i)));
            } else {
                this.nlX.set(i, Integer.valueOf(nbttagcompound.func_74762_e("nlX" + i)));
                this.nlY.set(i, Integer.valueOf(nbttagcompound.func_74762_e("nlY" + i)));
                this.nlZ.set(i, Integer.valueOf(nbttagcompound.func_74762_e("nlZ" + i)));
            }
        }
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("woodType", this.woodType);
        nbttagcompound.func_74768_a("numberOfNodes", this.numberOfNodes);
        nbttagcompound.func_74772_a("elapsedTime", this.elapsedTime);
        nbttagcompound.func_74768_a("leavesCount", this.leavesCount);
        for (int i = 0; i < this.numberOfNodes; i++) {
            nbttagcompound.func_74768_a("nlX" + i, this.nlX.get(i).intValue());
            nbttagcompound.func_74768_a("nlY" + i, this.nlY.get(i).intValue());
            nbttagcompound.func_74768_a("nlZ" + i, this.nlZ.get(i).intValue());
        }
    }

    public int getWoodType() {
        return this.woodType;
    }

    public void resetElapsedTime() {
        this.elapsedTime = 0L;
    }

    public void setNumberOfNodes(int num) {
        this.numberOfNodes = num;
    }

    public void setNumberOfNodeList(int i, int j, int k) {
        this.nlX.add(Integer.valueOf(i));
        this.nlY.add(Integer.valueOf(j));
        this.nlZ.add(Integer.valueOf(k));
    }

    public void setWoodType(int t) {
        this.woodType = t;
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.timer > 0) {
            this.timer--;
        } else {
            this.timer = this.TIMERCOUNT;
            update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    private void update(World world, int i, int j, int k) {
        if (this.elapsedTime >= this.CYCLE_TIME) {
            this.elapsedTime = 0L;
        } else {
            this.elapsedTime++;
        }
        if (this.woodType == 1) {
            if (this.elapsedTime % this.CYCLE_TIME >= 0 && this.elapsedTime % this.CYCLE_TIME < 60) {
                generateLeaves(0, 0);
            } else if ((this.elapsedTime - this.CHANGE_COLOR1) % this.CYCLE_TIME >= 0 && (this.elapsedTime - this.CHANGE_COLOR1) % this.CYCLE_TIME < 60) {
                generateLeaves(0, 1);
            } else if ((this.elapsedTime - this.CHANGE_COLOR2) % this.CYCLE_TIME >= 0 && (this.elapsedTime - this.CHANGE_COLOR2) % this.CYCLE_TIME < 60) {
                generateLeaves(0, 2);
            } else if ((this.elapsedTime - this.CHANGE_COLOR3) % this.CYCLE_TIME >= 0 && (this.elapsedTime - this.CHANGE_COLOR3) % this.CYCLE_TIME < 60) {
                generateLeaves(0, 3);
            } else if ((this.elapsedTime - this.FALL_TIME) % this.CYCLE_TIME >= 0 && (this.elapsedTime - this.FALL_TIME) % this.CYCLE_TIME < 60) {
                generateLeaves(1, 0);
            } else {
                this.leavesCount = 0;
            }
        }
        if (this.woodType == 2 && (this.elapsedTime - this.GROWS_TIME) % this.CYCLE_TIME >= 0 && (this.elapsedTime - this.GROWS_TIME) % this.CYCLE_TIME < 5) {
            persimmonGrows(world, i, j, k);
        }
        func_70296_d();
    }

    void generateLeaves(int mode, int color) {
        int rnd = mode == 0 ? 3 : 1;
        if (this.leavesCount < this.numberOfNodes) {
            if (this.random.nextInt(rnd) <= 1) {
                generateLeafNode(this.nlX.get(this.leavesCount).intValue(), this.nlY.get(this.leavesCount).intValue(), this.nlZ.get(this.leavesCount).intValue(), mode, color);
                this.leavesCount++;
                return;
            }
            return;
        }
        this.leavesCount = 0;
    }

    void generateLeafNode(int i, int j, int k, int mode, int color) {
        int i1 = j + this.leafDistanceLimit;
        for (int l = j; l < i1; l++) {
            float f = leafSize(l - j);
            func_150529_a(i, l, k, f, (byte) 1, mod_ecru_MapleTree.blockPersimmonLeaves, mode, color);
        }
    }

    float leafSize(int s) {
        if (s < 0 || s >= this.leafDistanceLimit) {
            return -1.0f;
        }
        return (s == 0 || s == this.leafDistanceLimit - 1) ? 2.0f : 3.0f;
    }

    void func_150529_a(int i, int j, int k, float m, byte Pairs, Block leaves, int mode, int color) {
        int l = (int) (m + 0.618d);
        byte b1 = otherCoordPairs[Pairs];
        byte b2 = otherCoordPairs[Pairs + 3];
        int[] aint = {i, j, k};
        int[] aint1 = new int[3];
        aint1[0] = 0;
        aint1[1] = 0;
        aint1[2] = 0;
        int i2 = -l;
        aint1[Pairs] = aint[Pairs];
        for (int i1 = -l; i1 <= l; i1++) {
            aint1[b1] = aint[b1] + i1;
            int j1 = -l;
            while (j1 <= l) {
                double d0 = Math.pow(Math.abs(i1) + 0.5d, 2.0d) + Math.pow(Math.abs(j1) + 0.5d, 2.0d);
                if (d0 > m * m) {
                    j1++;
                } else {
                    aint1[b2] = aint[b2] + j1;
                    Block block1 = this.field_145850_b.func_147439_a(aint1[0], aint1[1], aint1[2]);
                    if (mode == 0) {
                        if (!block1.isAir(this.field_145850_b, aint1[0], aint1[1], aint1[2]) && !block1.isLeaves(this.field_145850_b, aint1[0], aint1[1], aint1[2])) {
                            j1++;
                        } else {
                            if (block1.isAir(this.field_145850_b, aint1[0], aint1[1], aint1[2])) {
                                this.field_145850_b.func_147465_d(aint1[0], aint1[1], aint1[2], leaves, color, 2);
                            } else {
                                this.field_145850_b.func_72921_c(aint1[0], aint1[1], aint1[2], color, 2);
                            }
                            j1++;
                        }
                    } else {
                        if (this.field_145850_b.func_147439_a(aint1[0], aint1[1], aint1[2]) == leaves) {
                            this.field_145850_b.func_147465_d(aint1[0], aint1[1], aint1[2], Blocks.field_150350_a, 0, 2);
                        }
                        j1++;
                    }
                }
            }
        }
    }

    private void persimmonGrows(World world, int i, int j, int k) {
        if (world.func_147437_c(i, j - 1, k) || world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPersimmonLeaves) {
            world.func_147465_d(i, j - 1, k, mod_ecru_MapleTree.blockPersimmon, 0, 2);
        }
    }
}
