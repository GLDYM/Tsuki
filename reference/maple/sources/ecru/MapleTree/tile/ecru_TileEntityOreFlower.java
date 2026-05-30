package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityOreFlower extends TileEntity {
    private long nourishment;
    private long tim;
    private int oreType;
    private long SET_TIME = 12000;
    private long stack = 0;
    private long TIMERCOUNT = 50;
    private long timer = this.TIMERCOUNT;
    private int sCount = 0;
    private ecru_IdList blockInfo = new ecru_IdList();
    private final Random random = new Random();
    private final int GROWTH_NUM = 5;
    private int GROWTH_LV3 = 12220;
    private int GROWTH_LV2 = 8140;
    private int GROWTH_LV1 = 471;

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.nourishment = nbttagcompound.func_74763_f("nourishment");
        this.tim = nbttagcompound.func_74763_f("tim");
        this.sCount = nbttagcompound.func_74762_e("sCount");
        this.oreType = nbttagcompound.func_74762_e("oreType");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74772_a("nourishment", this.nourishment);
        nbttagcompound.func_74772_a("tim", this.tim);
        nbttagcompound.func_74768_a("sCount", this.sCount);
        nbttagcompound.func_74768_a("oreType", this.oreType);
    }

    public long getNou() {
        return this.nourishment;
    }

    public void setNou(long i) {
        this.nourishment = i;
    }

    public long getTime() {
        return this.tim;
    }

    public void setTime(long i) {
        this.tim = i;
    }

    public void setCount(int i) {
        this.sCount = i;
    }

    public void setType(int i) {
        this.oreType = i;
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        func_70296_d();
        if (world.func_72957_l(i, j + 1, k) >= 9) {
        }
        int y = j;
        do {
            y--;
            if (world.func_147439_a(i, y, k) == mod_ecru_MapleTree.blockPlanter) {
                break;
            }
        } while (y > 0);
        if (y <= 0) {
            return;
        }
        int flowerNum = j - y;
        ecru_TileEntityPlanter planterInventory = (ecru_TileEntityPlanter) world.func_147438_o(i, y, k);
        if (planterInventory == null) {
            return;
        }
        int wa = planterInventory.getWater();
        int ferId = planterInventory.getFertilizerId();
        int ferMeta = planterInventory.getFertilizerMeta();
        Item soilId = planterInventory.getSoilId();
        if (this.blockInfo.getNum(soilId) == -1) {
            return;
        }
        int fer = this.blockInfo.redFertilizerModifi[this.oreType][this.blockInfo.getFertilizer(ferId, ferMeta)];
        int aff = this.blockInfo.blockAffinityRed[this.oreType][this.blockInfo.getNum(soilId)];
        if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPlanter) {
            if (this.sCount >= 4 || this.sCount <= 0) {
                return;
            }
            if (wa <= 0) {
                dropItems(world, i, j, k, world.func_72805_g(i, j, k) & 7);
                world.func_147449_b(i, j, k, Blocks.field_150350_a);
                return;
            }
            int _fer = planterInventory.getFertilizer();
            if (_fer > 0) {
                planterInventory.setFertilizer(_fer - 1);
                this.nourishment += fer * aff;
            }
            long nTime = world.func_82737_E();
            long sTime = this.tim;
            if (nTime - sTime > this.SET_TIME) {
                this.sCount++;
                int meta = world.func_72805_g(i, j, k) & 7;
                if (this.nourishment >= this.GROWTH_LV3) {
                    int m = (meta & 7) + 3;
                    meta = (meta & 8) | (m > 7 ? 7 : m);
                } else if (this.nourishment >= this.GROWTH_LV2) {
                    int m2 = (meta & 7) + 2;
                    meta = (meta & 8) | (m2 > 7 ? 7 : m2);
                } else if (this.nourishment >= this.GROWTH_LV1) {
                    int m3 = (meta & 7) + 1;
                    meta = (meta & 8) | (m3 > 7 ? 7 : m3);
                }
                world.func_72921_c(i, j, k, meta, 3);
                this.nourishment = 0L;
                this.tim = world.func_82737_E();
                if (this.sCount >= 4 && world.func_147439_a(i, j + 1, k) == Blocks.field_150350_a && meta >= 3) {
                    Block newId = this.blockInfo.flowerBlockId[this.oreType];
                    world.func_147465_d(i, j + 1, k, newId, 0, 3);
                    ecru_TileEntityOreFlower tileentity = (ecru_TileEntityOreFlower) world.func_147438_o(i, j + 1, k);
                    if (tileentity != null) {
                        long newTime = world.func_82737_E();
                        tileentity.setTime(newTime);
                        tileentity.setNou(0L);
                        tileentity.setCount(1);
                        tileentity.setType(this.oreType);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (world.func_147439_a(i, j - 1, k) != this.blockInfo.flowerBlockId[this.oreType] || this.sCount >= 4 || this.sCount <= 0) {
            return;
        }
        if (wa <= 0) {
            dropItems(world, i, j, k, world.func_72805_g(i, j, k) & 7);
            world.func_147449_b(i, j, k, Blocks.field_150350_a);
            return;
        }
        int _fer2 = planterInventory.getFertilizer();
        if (_fer2 > 0) {
            planterInventory.setFertilizer(_fer2 - 1);
            this.nourishment += fer * aff;
        }
        long nTime2 = world.func_82737_E();
        long sTime2 = this.tim;
        int numMax = world.func_72805_g(i, j - (flowerNum - 1), k) & 7;
        if (nTime2 - sTime2 > this.SET_TIME) {
            this.sCount++;
            int meta2 = world.func_72805_g(i, j, k) & 7;
            if (this.nourishment >= this.GROWTH_LV3) {
                int m4 = (meta2 & 7) + 3;
                meta2 = (meta2 & 8) | (m4 > numMax ? numMax : m4);
            } else if (this.nourishment >= this.GROWTH_LV2) {
                int m5 = (meta2 & 7) + 2;
                meta2 = (meta2 & 8) | (m5 > numMax ? numMax : m5);
            } else if (this.nourishment >= this.GROWTH_LV1) {
                int m6 = (meta2 & 7) + 1;
                meta2 = (meta2 & 8) | (m6 > numMax ? numMax : m6);
            }
            world.func_72921_c(i, j, k, meta2, 3);
            this.nourishment = 0L;
            this.tim = world.func_82737_E();
            if (this.sCount >= 4 && flowerNum < 5 && world.func_147439_a(i, j + 1, k) == Blocks.field_150350_a && meta2 >= 3) {
                Block newId2 = this.blockInfo.flowerBlockId[this.oreType];
                world.func_147465_d(i, j + 1, k, newId2, 0, 3);
                ecru_TileEntityOreFlower tileentity2 = (ecru_TileEntityOreFlower) world.func_147438_o(i, j + 1, k);
                if (tileentity2 != null) {
                    long newTime2 = world.func_82737_E();
                    tileentity2.setTime(newTime2);
                    tileentity2.setNou(0L);
                    tileentity2.setCount(1);
                    tileentity2.setType(this.oreType);
                }
            }
        }
    }

    public void dropItems(World world, int i, int j, int k, int l) {
        int dp;
        int meta = l & 7;
        switch (meta) {
            case 0:
            default:
                return;
            case 1:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][1];
                break;
            case 2:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][2];
                break;
            case 3:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][3];
                break;
            case 4:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][4];
                break;
            case 5:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][5];
                break;
            case 6:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][6];
                break;
            case 7:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[this.oreType][7];
                break;
        }
        ecru_IdList ecru_idlist = this.blockInfo;
        EntityItem ei = new EntityItem(world, i + 0.5d, j + 0.5d, k + 0.5d, new ItemStack(ecru_IdList.dropItemId[this.oreType], dp, 0));
        world.func_72838_d(ei);
    }
}
