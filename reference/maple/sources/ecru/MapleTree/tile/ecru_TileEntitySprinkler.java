package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntitySprinkler extends TileEntity {
    private final Random random = new Random();
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    private int power = this.random.nextInt(360);
    private int[] rank = {130, 110, 90, 80};
    private int[] rank2 = {5, 4, 3, 2};
    private int[] rank3 = {2, 1, 0, 0};
    private int[] sty = {-1, -4};
    private int[] eny = {1, -2};

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int getPower() {
        return this.power;
    }

    private void powerMove(World world, int i, int j, int k) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            int i2 = this.power - 10;
            this.power = i2;
            if (i2 <= 0) {
                this.power = 360;
            }
        }
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
    }

    public void func_145845_h() {
        powerMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        boolean momiji = getMomiji(world, i, j, k);
        int offset = momiji ? 4 : 1;
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 8) == 8 && this.random.nextInt(this.rank[meta & 3] * offset) == 0 && !this.field_145850_b.field_72995_K) {
            for (int yy = j + this.sty[(meta & 4) >> 2]; yy <= j + this.eny[(meta & 4) >> 2]; yy++) {
                for (int xx = i - 5; xx <= i + 5; xx++) {
                    for (int zz = k - 5; zz <= k + 5; zz++) {
                        Block id = world.func_147439_a(xx, yy, zz);
                        if (world.func_147439_a(xx, yy - 1, zz) == Blocks.field_150458_ak && world.func_72805_g(xx, yy - 1, zz) < 7) {
                            world.func_72921_c(xx, yy - 1, zz, 7, 3);
                        }
                        if (((id instanceof BlockBush) || world.func_147439_a(xx, yy, zz).func_149688_o() == Material.field_151585_k) && mod_ecru_MapleTree.blockSprinkler != id) {
                            int cou = 0;
                            int meme = 0;
                            int cropMeta = world.func_72805_g(xx, yy, zz);
                            Block cid = world.func_147439_a(xx + 1, yy, zz);
                            if (cid == id) {
                                cou = 0 + 1;
                                meme = 0 + (world.func_72805_g(xx + 1, yy, zz) & 7);
                            }
                            Block cid2 = world.func_147439_a(xx - 1, yy, zz);
                            if (cid2 == id) {
                                cou++;
                                meme += world.func_72805_g(xx - 1, yy, zz) & 7;
                            }
                            Block cid3 = world.func_147439_a(xx, yy, zz + 1);
                            if (cid3 == id) {
                                cou++;
                                meme += world.func_72805_g(xx, yy, zz + 1) & 7;
                            }
                            Block cid4 = world.func_147439_a(xx, yy, zz - 1);
                            if (cid4 == id) {
                                cou++;
                                meme += world.func_72805_g(xx, yy, zz - 1) & 7;
                            }
                            if (cou != 0 && (cropMeta & 7) + this.rank3[meta & 3] < meme / cou) {
                                id.func_149674_a(world, xx, yy, zz, this.random);
                            } else if (this.random.nextInt(this.rank2[meta & 3]) == 0) {
                                id.func_149674_a(world, xx, yy, zz, this.random);
                            }
                        }
                    }
                }
            }
        }
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        func_70296_d();
    }

    private boolean getMomiji(World world, int i, int j, int k) {
        for (int m = 0; m < world.field_72996_f.size(); m++) {
            Entity ei = (Entity) world.field_72996_f.get(m);
            if (ei.getClass().getSimpleName().equals("ecru_EntityMomiji")) {
                double px = ei.field_70165_t;
                double py = ei.field_70163_u;
                double pz = ei.field_70161_v;
                if (px >= i - 10 && px <= i + 10 + 1 && py >= j - 10 && py <= j + 3 + 1 && pz >= k - 10 && pz <= k + 10 + 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
