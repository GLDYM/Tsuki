package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_syntheticRecipe;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityStoneMortar extends TileEntity implements ISidedInventory {
    private final Random random = new Random();
    ecru_syntheticRecipe synData = new ecru_syntheticRecipe();
    private long TIMERCOUNT = 20;
    private long timer = 0;
    public int WATER_MAX = this.synData.waterMax;
    public int dt_syntheticTimeMax = this.synData.syntheticTime;
    private ItemStack[] itemStacks = new ItemStack[6];
    public int dt_water = 0;
    public int dt_power = 0;
    public int dt_syntheticTime = 0;
    public int dt_recipePage = 0;
    private int gearPosX = 0;
    private int gearPosY = -1;
    private int gearPosZ = 0;
    public int connectNum = 1;
    public int connectNumMax = 1;
    private int co = this.random.nextInt(100);
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    private int power = this.random.nextInt(360);
    private boolean powerOnFlg = false;
    private boolean enginePower = false;
    private boolean shaftFlg = false;
    public Item spawnParticleItem;
    public int spawnParticleMeta;
    EntityItem ei1;
    EntityItem ei2;
    EntityItem ei3;
    private static final int[] slots_bottom = {0, 4, 5};
    private static final int[] slots_top = {2};
    private static final int[] slots_side_n = {1};
    private static final int[] slots_side_s = {1};
    private static final int[] slots_side_w = {3};
    private static final int[] slots_side_e = {0};

    public int getGearPosX() {
        return this.gearPosX;
    }

    public int getGearPosY() {
        return this.gearPosY;
    }

    public int getGearPosZ() {
        return this.gearPosZ;
    }

    public int getPower() {
        return this.power;
    }

    public Block getBlock() {
        return this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int func_70302_i_() {
        return this.itemStacks.length;
    }

    public ItemStack func_70301_a(int var1) {
        return this.itemStacks[var1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.itemStacks[par1] != null) {
            if (this.itemStacks[par1].field_77994_a <= par2) {
                ItemStack var3 = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                return var3;
            }
            ItemStack var32 = this.itemStacks[par1].func_77979_a(par2);
            if (this.itemStacks[par1].field_77994_a == 0) {
                this.itemStacks[par1] = null;
            }
            func_70296_d();
            return var32;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.itemStacks[par1] != null) {
            ItemStack var2 = this.itemStacks[par1];
            this.itemStacks[par1] = null;
            return var2;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.itemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > func_70297_j_()) {
            par2ItemStack.field_77994_a = func_70297_j_();
        }
        if (this.itemStacks[par1] != null) {
        }
    }

    private void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public String func_145825_b() {
        return "StoneMortar";
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && var1.func_70092_e(((double) this.field_145851_c) + 0.5d, ((double) this.field_145848_d) + 0.5d, ((double) this.field_145849_e) + 0.5d) <= 64.0d;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_water = nbttagcompound.func_74762_e("dt_water");
        this.dt_syntheticTime = nbttagcompound.func_74762_e("dt_syntheticTime");
        this.dt_power = nbttagcompound.func_74762_e("dt_power");
        this.dt_syntheticTimeMax = nbttagcompound.func_74762_e("dt_syntheticTimeMax");
        this.dt_recipePage = nbttagcompound.func_74762_e("dt_recipePage");
        NBTTagList itemsTagList = nbttagcompound.func_150295_c("Items", 10);
        this.itemStacks = new ItemStack[func_70302_i_()];
        for (int tagCounter = 0; tagCounter < itemsTagList.func_74745_c(); tagCounter++) {
            NBTTagCompound itemTagCompound = itemsTagList.func_150305_b(tagCounter);
            int slotIndex = itemTagCompound.func_74771_c("Slot") & 255;
            if (slotIndex >= 0 && slotIndex < this.itemStacks.length) {
                this.itemStacks[slotIndex] = ItemStack.func_77949_a(itemTagCompound);
            }
        }
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("dt_water", this.dt_water);
        nbttagcompound.func_74768_a("dt_syntheticTime", this.dt_syntheticTime);
        nbttagcompound.func_74768_a("dt_power", this.dt_power);
        nbttagcompound.func_74768_a("dt_syntheticTimeMax", this.dt_syntheticTimeMax);
        nbttagcompound.func_74768_a("dt_recipePage", this.dt_recipePage);
        NBTTagList itemsTagList = new NBTTagList();
        byte b = 0;
        while (true) {
            byte slotIndex = b;
            if (slotIndex < this.itemStacks.length) {
                if (this.itemStacks[slotIndex] != null) {
                    NBTTagCompound itemTagCompound = new NBTTagCompound();
                    itemTagCompound.func_74774_a("Slot", slotIndex);
                    this.itemStacks[slotIndex].func_77955_b(itemTagCompound);
                    itemsTagList.func_74742_a(itemTagCompound);
                }
                b = (byte) (slotIndex + 1);
            } else {
                nbttagcompound.func_74782_a("Items", itemsTagList);
                return;
            }
        }
    }

    public void setItem(ItemStack is, int slot) {
        func_70299_a(slot, is);
    }

    public int getWater() {
        return this.dt_water;
    }

    public int getWaterMax() {
        return this.WATER_MAX;
    }

    public boolean isWater() {
        return this.dt_water > 0;
    }

    public int getSyntheticTime() {
        return this.dt_syntheticTime;
    }

    public int getSyntheticTimeMax() {
        return this.dt_syntheticTimeMax;
    }

    private ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public boolean getPowerOn() {
        return this.powerOnFlg;
    }

    public void func_145845_h() {
        int eMeta;
        Block eId;
        powerMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        World world = this.field_145850_b;
        int i = this.field_145851_c;
        int j = this.field_145848_d;
        int k = this.field_145849_e;
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 3) {
            case 0:
                eMeta = world.func_72805_g(i, j, k + 1);
                eId = world.func_147439_a(i, j, k + 1);
                break;
            case 1:
                eMeta = world.func_72805_g(i - 1, j, k);
                eId = world.func_147439_a(i - 1, j, k);
                break;
            case 2:
                eMeta = world.func_72805_g(i, j, k - 1);
                eId = world.func_147439_a(i, j, k - 1);
                break;
            case 3:
                eMeta = world.func_72805_g(i + 1, j, k);
                eId = world.func_147439_a(i + 1, j, k);
                break;
            default:
                return;
        }
        this.powerOnFlg = false;
        this.enginePower = false;
        this.shaftFlg = false;
        if (((meta & 3) == 0 || (meta & 3) == 2) && eId == mod_ecru_MapleTree.blockEngine && (eMeta & 13) == 13) {
            this.powerOnFlg = true;
            this.enginePower = true;
        }
        if (((meta & 3) == 1 || (meta & 3) == 3) && eId == mod_ecru_MapleTree.blockEngine && (eMeta & 13) == 9) {
            this.powerOnFlg = true;
            this.enginePower = true;
        }
        int torque = 0;
        if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockHumanPowerDrive && (world.func_72805_g(i, j - 1, k) & 8) == 8) {
            this.powerOnFlg = true;
            this.shaftFlg = false;
        } else if (!this.enginePower) {
            if ((meta & 3) == 1) {
                if (eId == mod_ecru_MapleTree.blockPowerShaft && (eMeta & 3) == 1 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaft tile = (ecru_TileEntityPowerShaft) world.func_147438_o(i - 1, j, k);
                    this.powerOnFlg = true;
                    if (tile.getConnectCount() >= 1) {
                        torque = tile.getTorque();
                    }
                    this.gearPosX = tile.getGearPosX();
                    this.gearPosY = tile.getGearPosY();
                    this.gearPosZ = tile.getGearPosZ();
                } else if (eId == mod_ecru_MapleTree.blockPowerShaftGear && (eMeta & 7) != 3 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile2 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i - 1, j, k);
                    this.powerOnFlg = true;
                    if (tile2.getConnectCount() >= 1) {
                        torque = tile2.getTorque();
                    }
                    this.gearPosX = tile2.getGearPosX();
                    this.gearPosY = tile2.getGearPosY();
                    this.gearPosZ = tile2.getGearPosZ();
                }
            }
            if ((meta & 3) == 3) {
                if (eId == mod_ecru_MapleTree.blockPowerShaft && (eMeta & 3) == 1 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaft tile3 = (ecru_TileEntityPowerShaft) world.func_147438_o(i + 1, j, k);
                    this.powerOnFlg = true;
                    if (tile3.getConnectCount() >= 1) {
                        torque = tile3.getTorque();
                    }
                    this.gearPosX = tile3.getGearPosX();
                    this.gearPosY = tile3.getGearPosY();
                    this.gearPosZ = tile3.getGearPosZ();
                } else if (eId == mod_ecru_MapleTree.blockPowerShaftGear && (eMeta & 7) != 1 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile4 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i + 1, j, k);
                    this.powerOnFlg = true;
                    if (tile4.getConnectCount() >= 1) {
                        torque = tile4.getTorque();
                    }
                    this.gearPosX = tile4.getGearPosX();
                    this.gearPosY = tile4.getGearPosY();
                    this.gearPosZ = tile4.getGearPosZ();
                }
            }
            if ((meta & 3) == 2) {
                if (eId == mod_ecru_MapleTree.blockPowerShaft && (eMeta & 3) == 2 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaft tile5 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j, k - 1);
                    this.powerOnFlg = true;
                    if (tile5.getConnectCount() >= 1) {
                        torque = tile5.getTorque();
                    }
                    this.gearPosX = tile5.getGearPosX();
                    this.gearPosY = tile5.getGearPosY();
                    this.gearPosZ = tile5.getGearPosZ();
                } else if (eId == mod_ecru_MapleTree.blockPowerShaftGear && (eMeta & 7) != 0 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile6 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j, k - 1);
                    this.powerOnFlg = true;
                    if (tile6.getConnectCount() >= 1) {
                        torque = tile6.getTorque();
                    }
                    this.gearPosX = tile6.getGearPosX();
                    this.gearPosY = tile6.getGearPosY();
                    this.gearPosZ = tile6.getGearPosZ();
                }
            }
            if ((meta & 3) == 0) {
                if (eId == mod_ecru_MapleTree.blockPowerShaft && (eMeta & 3) == 2 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaft tile7 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j, k + 1);
                    this.powerOnFlg = true;
                    if (tile7.getConnectCount() >= 1) {
                        torque = tile7.getTorque();
                    }
                    this.gearPosX = tile7.getGearPosX();
                    this.gearPosY = tile7.getGearPosY();
                    this.gearPosZ = tile7.getGearPosZ();
                } else if (eId == mod_ecru_MapleTree.blockPowerShaftGear && (eMeta & 7) != 2 && (eMeta & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile8 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j, k + 1);
                    this.powerOnFlg = true;
                    if (tile8.getConnectCount() >= 1) {
                        torque = tile8.getTorque();
                    }
                    this.gearPosX = tile8.getGearPosX();
                    this.gearPosY = tile8.getGearPosY();
                    this.gearPosZ = tile8.getGearPosZ();
                }
            }
            if (torque > 0) {
                this.shaftFlg = true;
                this.powerOnFlg = true;
            }
        }
        this.dt_power = this.powerOnFlg ? 1 : 0;
        if (!this.powerOnFlg) {
            this.connectNum = 1;
        }
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, torque);
    }

    public void update(World world, int i, int j, int k, int torque) {
        ecru_TileEntityHumanPowerDrive tile;
        checkGrarPos(world);
        ItemStack water = getItem(0);
        ItemStack item1 = getItem(1);
        ItemStack item2 = getItem(2);
        ItemStack item3 = getItem(3);
        ItemStack item4 = getItem(4);
        ItemStack item5 = getItem(5);
        Item id1 = null;
        Item id2 = null;
        Item id3 = null;
        int idd1 = 0;
        int idd2 = 0;
        int idd3 = 0;
        float entityTimes = 6.0f;
        float shaftTimes = 6.0f;
        this.connectNumMax = 1;
        if (this.shaftFlg) {
            switch (torque) {
                case 1:
                    this.connectNumMax = 6;
                    if (this.connectNum <= 6) {
                        shaftTimes = 1.0f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 1.0f * 50.0f;
                        break;
                    }
                case 2:
                    this.connectNumMax = 3;
                    if (this.connectNum <= 3) {
                        shaftTimes = 3.0f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 3.0f * 50.0f;
                        break;
                    }
                case 3:
                    this.connectNumMax = 2;
                    if (this.connectNum <= 2) {
                        shaftTimes = 3.0f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 3.0f * 50.0f;
                        break;
                    }
                case 4:
                    this.connectNumMax = 2;
                    if (this.connectNum <= 2) {
                        shaftTimes = 3.5f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 3.5f * 50.0f;
                        break;
                    }
                case 5:
                    this.connectNumMax = 2;
                    if (this.connectNum <= 2) {
                        shaftTimes = 4.0f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 4.0f * 50.0f;
                        break;
                    }
                case 6:
                    this.connectNumMax = 2;
                    if (this.connectNum <= 2) {
                        shaftTimes = 6.0f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 6.0f * 50.0f;
                        break;
                    }
                default:
                    this.connectNumMax = 3;
                    if (this.connectNum <= 3) {
                        shaftTimes = 6.0f * this.connectNum;
                        break;
                    } else {
                        shaftTimes = 6.0f * 50.0f;
                        break;
                    }
            }
        }
        if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockHumanPowerDrive && (tile = (ecru_TileEntityHumanPowerDrive) world.func_147438_o(i, j - 1, k)) != null && (tile instanceof ecru_TileEntityHumanPowerDrive)) {
            switch (tile.getEntityNum()) {
                case 1:
                    entityTimes = 6.0f;
                    break;
                case 2:
                    entityTimes = 4.0f;
                    break;
                case 3:
                    entityTimes = 3.5f;
                    break;
                case 4:
                    entityTimes = 3.0f;
                    break;
            }
        }
        int meta = world.func_72805_g(i, j, k);
        if (this.dt_syntheticTime > 0) {
            world.func_72921_c(i, j, k, meta | 8, 3);
        } else {
            world.func_72921_c(i, j, k, meta & 7, 3);
        }
        if (water != null && water.func_77973_b() == Items.field_151131_as) {
            this.dt_water = this.synData.waterMax;
            setItem(new ItemStack(Items.field_151133_ar, 1, 0), 0);
        }
        boolean i4Chk = false;
        if (item1 != null || item2 != null || item3 != null) {
            if (item1 != null) {
                id1 = item1.func_77973_b();
                idd1 = item1.func_77960_j();
            }
            if (item2 != null) {
                id2 = item2.func_77973_b();
                idd2 = item2.func_77960_j();
            }
            if (item3 != null) {
                id3 = item3.func_77973_b();
                idd3 = item3.func_77960_j();
            }
            Item id = this.synData.getFinishedGoodsId(id1, id2, id3, idd1, idd2, idd3);
            int num = this.synData.getFinishedGoodsNum(id1, id2, id3, idd1, idd2, idd3);
            int tmp = this.synData.getFinishedGoodsTime(id1, id2, id3, idd1, idd2, idd3);
            this.dt_syntheticTimeMax = tmp != -1 ? tmp : this.synData.syntheticTime;
            if (!this.enginePower) {
                if (this.shaftFlg) {
                    this.dt_syntheticTimeMax = (int) (this.dt_syntheticTimeMax * shaftTimes);
                } else {
                    this.dt_syntheticTimeMax = (int) (this.dt_syntheticTimeMax * entityTimes);
                }
            }
            if (id == null) {
                this.dt_syntheticTime = 0;
                return;
            } else if (item4 == null) {
                i4Chk = true;
            } else if (item4.func_77973_b() == id) {
                i4Chk = item4.field_77994_a <= item4.func_77976_d() - num;
            } else {
                i4Chk = false;
            }
        }
        if ((item1 != null || item2 != null || item3 != null) && i4Chk && getPowerOn()) {
            if (this.dt_water > 0 && this.synData.getrecipeNum(id1, id2, id3, idd1, idd2, idd3) >= 0) {
                this.dt_syntheticTime++;
            } else {
                this.dt_syntheticTime = 0;
            }
            if (this.dt_syntheticTime >= this.dt_syntheticTimeMax) {
                if (id1 != null) {
                    item1.func_77979_a(1);
                    if (item1.field_77994_a == 0) {
                        deleteItem(1);
                    }
                }
                if (id2 != null) {
                    item2.func_77979_a(1);
                    if (item2.field_77994_a == 0) {
                        deleteItem(2);
                    }
                }
                if (id3 != null) {
                    item3.func_77979_a(1);
                    if (item3.field_77994_a == 0) {
                        deleteItem(3);
                    }
                }
                this.dt_water--;
                if (item4 == null) {
                    Item id4 = this.synData.getFinishedGoodsId(id1, id2, id3, idd1, idd2, idd3);
                    int m = this.synData.getFinishedGoodsMeta(id1, id2, id3, idd1, idd2, idd3);
                    int num2 = this.synData.getFinishedGoodsNum(id1, id2, id3, idd1, idd2, idd3);
                    setItem(new ItemStack(id4, num2, m), 4);
                } else {
                    int num3 = this.synData.getFinishedGoodsNum(id1, id2, id3, idd1, idd2, idd3);
                    this.synData.getFinishedGoodsRare(id1, id2, id3, idd1, idd2, idd3);
                    if (item4.field_77994_a < item4.func_77976_d()) {
                        item4.func_77979_a(-num3);
                    }
                }
                int rare = this.synData.getFinishedGoodsRare(id1, id2, id3, idd1, idd2, idd3);
                if (rare != 0) {
                    if (item5 == null) {
                        if (this.random.nextInt(rare) <= 1) {
                            Item id5 = this.synData.getRareId(id1, id2, id3, idd1, idd2, idd3);
                            int m2 = this.synData.getRareMeta(id1, id2, id3, idd1, idd2, idd3);
                            if (id5 != null && m2 != -1) {
                                setItem(new ItemStack(id5, 1, m2), 5);
                            }
                        }
                    } else if (item5.func_77973_b() == this.synData.getRareId(id1, id2, id3, idd1, idd2, idd3) && this.random.nextInt(rare) <= 1 && item5.field_77994_a < item5.func_77976_d()) {
                        item5.func_77979_a(-1);
                    }
                }
                this.dt_syntheticTime = 1;
                func_70296_d();
                return;
            }
            return;
        }
        this.dt_syntheticTime = 0;
    }

    private void powerMove(World world, int i, int j, int k) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            int i2 = this.power + 1;
            this.power = i2;
            if (i2 >= 360) {
                this.power = 0;
            }
        }
    }

    private void checkGrarPos(World world) {
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        if ((!this.powerOnFlg || !this.shaftFlg) && (this.gearPosX != 0 || this.gearPosY != -1 || this.gearPosZ != 0)) {
            if (world.func_147439_a(this.gearPosX, this.gearPosY, this.gearPosZ) == mod_ecru_MapleTree.blockPowerShaftGear) {
                String key = getStr(this.field_145851_c) + getStr(this.field_145848_d) + getStr(this.field_145849_e);
                ((ecru_TileEntityPowerShaftGear) world.func_147438_o(this.gearPosX, this.gearPosY, this.gearPosZ)).deleteMap(key);
                this.gearPosX = 0;
                this.gearPosY = -1;
                this.gearPosZ = 0;
                this.connectNum = 1;
                return;
            }
            return;
        }
        if (world.func_147439_a(this.gearPosX, this.gearPosY, this.gearPosZ) == mod_ecru_MapleTree.blockPowerShaftGear && this.shaftFlg) {
            String key2 = getStr(this.field_145851_c) + getStr(this.field_145848_d) + getStr(this.field_145849_e);
            ecru_TileEntityPowerShaftGear tile = (ecru_TileEntityPowerShaftGear) world.func_147438_o(this.gearPosX, this.gearPosY, this.gearPosZ);
            tile.setMap(key2);
            this.connectNum = tile.getMapSize();
            if (this.connectNum == 0) {
                this.connectNum = 1;
            }
        }
    }

    private String getStr(int i) {
        String s = String.valueOf(i);
        if (i < 0) {
            s = s.substring(1, s.length());
        }
        String s2 = "000" + s;
        String r = s2.substring(s2.length() - 3, s2.length());
        if (i >= 0) {
            return "0" + r;
        }
        return "1" + r;
    }

    public boolean func_94041_b(int slot, ItemStack par2ItemStack) {
        if (slot >= 0 && slot <= 4) {
            return true;
        }
        return false;
    }

    public int[] func_94128_d(int par1) {
        switch (par1) {
            case 0:
                return slots_bottom;
            case 1:
                return slots_top;
            case 2:
                return slots_side_n;
            case 3:
                return slots_side_s;
            case 4:
                return slots_side_w;
            case 5:
                return slots_side_e;
            default:
                return slots_bottom;
        }
    }

    public boolean func_102007_a(int slot, ItemStack itemStack, int par3) {
        if (slot == 4) {
            return false;
        }
        return true;
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        if (slot == 4 || slot == 0 || slot == 5) {
            return true;
        }
        return false;
    }
}
