package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_cookingRecipe;
import ecru.MapleTree.common.ecru_itemNormalList;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketCookPot;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityCookPot extends TileEntity implements IInventory {
    private final Random random = new Random();
    ecru_cookingRecipe cookData = new ecru_cookingRecipe();
    private int FOODS1 = 0;
    private int FOODS2 = 1;
    private int FOODS3 = 2;
    private int FOODS4 = 3;
    private int FOODS5 = 4;
    private int DISH = 5;
    private int WATER = 6;
    public int WATER_MAX = this.cookData.waterMax;
    public int COOKING_TIME = this.cookData.cookingTime;
    private ItemStack[] itemStacks = new ItemStack[7];
    public int dt_water = 0;
    private int dt_power = 0;
    private int dt_cookingTime = 0;
    private int dt_cookingTimeMax = 0;
    private int dt_powerCount = 0;
    private int dt_waterType = 0;
    public int dt_recipePage = 0;
    public int isCooking = 0;
    private int isCookingTime = 0;
    private int timer = 100;
    public ItemStack[] viewItemStack = new ItemStack[5];
    public EntityItem[] viewEntityItem = new EntityItem[5];
    private boolean powerOnFlg = false;
    public int waCount = 1;
    private int packetMode = 0;
    private int tickCount = 0;
    private final int TICK_COUNT_MAX = 2;
    public double[] ang = {this.random.nextInt(360), this.random.nextInt(360), this.random.nextInt(360), this.random.nextInt(360), this.random.nextInt(360)};
    ecru_itemNormalList.itemNormalList[] inl = ecru_itemNormalList.itemNormalList.values();

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
        sendPacket();
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
            slotInfoUpdate();
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
        slotInfoUpdate();
    }

    private void slotInfoUpdate() {
        if (this.field_145850_b != null) {
            if (func_70301_a(0) != null) {
                this.viewItemStack[0] = func_70301_a(0);
                this.viewEntityItem[0] = new EntityItem(this.field_145850_b, 0.0d, 0.0d, 0.0d, this.viewItemStack[0]);
            } else {
                this.viewItemStack[0] = null;
                this.viewEntityItem[0] = null;
            }
            if (func_70301_a(1) != null) {
                this.viewItemStack[1] = func_70301_a(1);
                this.viewEntityItem[1] = new EntityItem(this.field_145850_b, 0.0d, 0.0d, 0.0d, this.viewItemStack[1]);
            } else {
                this.viewItemStack[1] = null;
                this.viewEntityItem[1] = null;
            }
            if (func_70301_a(2) != null) {
                this.viewItemStack[2] = func_70301_a(2);
                this.viewEntityItem[2] = new EntityItem(this.field_145850_b, 0.0d, 0.0d, 0.0d, this.viewItemStack[2]);
            } else {
                this.viewItemStack[2] = null;
                this.viewEntityItem[2] = null;
            }
            if (func_70301_a(3) != null) {
                this.viewItemStack[3] = func_70301_a(3);
                this.viewEntityItem[3] = new EntityItem(this.field_145850_b, 0.0d, 0.0d, 0.0d, this.viewItemStack[3]);
            } else {
                this.viewItemStack[3] = null;
                this.viewEntityItem[3] = null;
            }
            if (func_70301_a(4) != null) {
                this.viewItemStack[4] = func_70301_a(4);
                this.viewEntityItem[4] = new EntityItem(this.field_145850_b, 0.0d, 0.0d, 0.0d, this.viewItemStack[4]);
            } else {
                this.viewItemStack[4] = null;
                this.viewEntityItem[4] = null;
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    private void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public String func_145825_b() {
        return "CookPot";
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

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_water = nbttagcompound.func_74762_e("dt_water");
        this.dt_cookingTime = nbttagcompound.func_74762_e("dt_cookingTime");
        this.dt_cookingTimeMax = nbttagcompound.func_74762_e("dt_cookingTimeMax");
        this.dt_power = nbttagcompound.func_74762_e("dt_power");
        this.dt_powerCount = nbttagcompound.func_74762_e("dt_powerCount");
        this.dt_waterType = nbttagcompound.func_74762_e("dt_waterType");
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
        nbttagcompound.func_74768_a("dt_cookingTime", this.dt_cookingTime);
        nbttagcompound.func_74768_a("dt_cookingTimeMax", this.dt_cookingTimeMax);
        nbttagcompound.func_74768_a("dt_power", this.dt_power);
        nbttagcompound.func_74768_a("dt_powerCount", this.dt_powerCount);
        nbttagcompound.func_74768_a("dt_waterType", this.dt_waterType);
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

    private void sendPacket() {
        if (!this.field_145850_b.field_72995_K) {
            slotInfoUpdate();
            sendItemInfo(this);
        }
    }

    public Packet func_145844_m() {
        slotInfoUpdate();
        sendItemInfo(this);
        return null;
    }

    public EntityItem getEntityItem(int i) {
        return this.viewEntityItem[i];
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

    private ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public boolean getPowerOn() {
        return this.powerOnFlg;
    }

    public int getPower() {
        return this.dt_power;
    }

    public int getPowerCount() {
        return this.dt_powerCount;
    }

    public int getCookingTime() {
        return this.dt_cookingTime;
    }

    public int getCookingTimeMax() {
        return this.dt_cookingTimeMax;
    }

    public int getWaterType() {
        return this.dt_waterType;
    }

    public void setPower(int i) {
        this.dt_power = i;
    }

    public void setPowerCount(int i) {
        this.dt_powerCount = i;
    }

    public void setCookingTime(int i) {
        this.dt_cookingTime = i;
    }

    public void setCookingTimeMax(int i) {
        this.dt_cookingTimeMax = i;
    }

    public void setWaterType(int i) {
        this.dt_waterType = i;
    }

    public void func_145845_h() {
        World world = this.field_145850_b;
        int i = this.field_145851_c;
        int j = this.field_145848_d;
        int k = this.field_145849_e;
        int i2 = this.tickCount + 1;
        this.tickCount = i2;
        if (i2 >= 2) {
            this.tickCount = 1;
            int i3 = this.waCount;
            this.waCount = i3 + 1;
            if (i3 > 32) {
                this.waCount = 0;
            }
        }
        this.powerOnFlg = false;
        if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockFallenLeavesFire) {
            int i4 = this.dt_powerCount + 1;
            this.dt_powerCount = i4;
            if (i4 >= 16) {
                this.dt_powerCount = 0;
            }
            this.powerOnFlg = true;
        }
        this.dt_power = this.powerOnFlg ? 1 : 0;
        if (this.viewEntityItem[0] != null) {
            EntityItem entityItem = this.viewEntityItem[0];
            int i5 = entityItem.field_70292_b;
            entityItem.field_70292_b = i5 + 1;
            if (i5 > 12000) {
                this.viewEntityItem[0].field_70292_b = 0;
            }
        }
        if (this.viewEntityItem[1] != null) {
            EntityItem entityItem2 = this.viewEntityItem[1];
            int i6 = entityItem2.field_70292_b;
            entityItem2.field_70292_b = i6 + 1;
            if (i6 > 12000) {
                this.viewEntityItem[1].field_70292_b = 0;
            }
        }
        if (this.viewEntityItem[2] != null) {
            EntityItem entityItem3 = this.viewEntityItem[2];
            int i7 = entityItem3.field_70292_b;
            entityItem3.field_70292_b = i7 + 1;
            if (i7 > 12000) {
                this.viewEntityItem[2].field_70292_b = 0;
            }
        }
        if (this.viewEntityItem[3] != null) {
            EntityItem entityItem4 = this.viewEntityItem[3];
            int i8 = entityItem4.field_70292_b;
            entityItem4.field_70292_b = i8 + 1;
            if (i8 > 12000) {
                this.viewEntityItem[3].field_70292_b = 0;
            }
        }
        if (this.viewEntityItem[4] != null) {
            EntityItem entityItem5 = this.viewEntityItem[4];
            int i9 = entityItem5.field_70292_b;
            entityItem5.field_70292_b = i9 + 1;
            if (i9 > 12000) {
                this.viewEntityItem[4].field_70292_b = 0;
            }
        }
        if (this.isCooking > 0) {
            move();
        }
        if (this.field_145850_b.field_72995_K) {
            if (world.func_147439_a(i, j - 1, k) != mod_ecru_MapleTree.blockFallenLeavesFire || (world.func_72805_g(i, j, k) & 3) <= 0) {
                return;
            }
            int i10 = this.timer;
            this.timer = i10 + 1;
            if (i10 > 100 && mod_ecru_MapleTree.EnvironmentSounds > 0.0f && this.isCooking > 0) {
                this.timer = 0;
                int wd = (world.func_72805_g(i, j, k) & 8) >> 3;
                if (wd == 0) {
                    float v = mod_ecru_MapleTree.EnvironmentSounds;
                    this.field_145850_b.func_72980_b(i + 0.5d, j + 0.5d, k + 0.5d, "mapletree:cooking.boil", v, 1.0f, true);
                    return;
                } else {
                    float v2 = mod_ecru_MapleTree.EnvironmentSounds;
                    this.field_145850_b.func_72980_b(i + 0.5d, j + 0.5d, k + 0.5d, "mapletree:cooking.fly", v2, 1.0f, true);
                    return;
                }
            }
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        ItemStack item1 = getItem(this.FOODS1);
        ItemStack item2 = getItem(this.FOODS2);
        ItemStack item3 = getItem(this.FOODS3);
        ItemStack item4 = getItem(this.FOODS4);
        ItemStack item5 = getItem(this.FOODS5);
        ItemStack itemFG = getItem(this.DISH);
        ItemStack water = getItem(this.WATER);
        Item id1 = null;
        Item id2 = null;
        Item id3 = null;
        Item id4 = null;
        Item id5 = null;
        int idd1 = 0;
        int idd2 = 0;
        int idd3 = 0;
        int idd4 = 0;
        int idd5 = 0;
        if (water != null) {
            if (water.func_77973_b() == Items.field_151131_as) {
                this.dt_waterType = 0;
                this.dt_water = this.cookData.waterMax;
                setItem(new ItemStack(Items.field_151133_ar, 1, 0), this.WATER);
                setWaterMeta();
                int meta = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta & 7, 3);
            } else if (water.func_77973_b() == mod_ecru_MapleTree.Item_normalItem && water.func_77960_j() == this.inl[0].getMeta("rapeseedOil") && (this.dt_water <= 0 || this.dt_waterType != 1)) {
                water.func_77979_a(1);
                if (water.field_77994_a == 0) {
                    deleteItem(this.WATER);
                }
                this.dt_waterType = 1;
                this.dt_water = this.cookData.waterMax;
                setWaterMeta();
                int meta2 = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta2 | 8, 3);
            }
        }
        this.isCookingTime = this.dt_cookingTime == 0 ? 0 : 1;
        if (this.isCooking != this.isCookingTime && !world.field_72995_K) {
            this.isCooking = this.isCookingTime;
            sendPacket();
        }
        boolean i4Chk = false;
        if (item1 != null || item2 != null || item3 != null || item4 != null || item5 != null) {
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
            if (item4 != null) {
                id4 = item4.func_77973_b();
                idd4 = item4.func_77960_j();
            }
            if (item5 != null) {
                id5 = item5.func_77973_b();
                idd5 = item5.func_77960_j();
            }
            Item id = this.cookData.getFinishedGoodsId(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
            int num = this.cookData.getFinishedGoodsNum(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
            int meta3 = this.cookData.getFinishedGoodsMeta(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
            this.COOKING_TIME = this.cookData.getFinishedGoodsTime(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
            this.dt_cookingTimeMax = this.COOKING_TIME != -1 ? this.COOKING_TIME : this.cookData.cookingTime;
            if (id == null) {
                this.dt_cookingTime = 0;
                return;
            } else if (itemFG == null) {
                i4Chk = true;
            } else if (itemFG.func_77973_b() == id && itemFG.func_77960_j() == meta3) {
                i4Chk = itemFG.field_77994_a <= 64 - num;
            } else {
                i4Chk = false;
            }
        }
        if ((item1 != null || item2 != null || item3 != null || item4 != null || item5 != null) && i4Chk && getPowerOn()) {
            if (this.dt_water > 0 && this.cookData.getrecipeNum(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType) >= 0) {
                this.dt_cookingTime++;
            } else {
                this.dt_cookingTime = 0;
            }
            if (this.dt_cookingTime >= this.dt_cookingTimeMax) {
                if (id1 != null) {
                    item1.func_77979_a(1);
                    if (item1.field_77994_a == 0) {
                        deleteItem(this.FOODS1);
                    }
                }
                if (id2 != null) {
                    item2.func_77979_a(1);
                    if (item2.field_77994_a == 0) {
                        deleteItem(this.FOODS2);
                    }
                }
                if (id3 != null) {
                    item3.func_77979_a(1);
                    if (item3.field_77994_a == 0) {
                        deleteItem(this.FOODS3);
                    }
                }
                if (id4 != null) {
                    item4.func_77979_a(1);
                    if (item4.field_77994_a == 0) {
                        deleteItem(this.FOODS4);
                    }
                }
                if (id5 != null) {
                    item5.func_77979_a(1);
                    if (item5.field_77994_a == 0) {
                        deleteItem(this.FOODS5);
                    }
                }
                this.dt_water--;
                setWaterMeta();
                if (itemFG == null) {
                    Item id6 = this.cookData.getFinishedGoodsId(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
                    int m = this.cookData.getFinishedGoodsMeta(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
                    int num2 = this.cookData.getFinishedGoodsNum(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
                    int rare = this.cookData.getFinishedGoodsRare(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
                    if (rare == 0 || this.random.nextInt(rare) <= 1) {
                        setItem(new ItemStack(id6, num2, m), this.DISH);
                    }
                } else {
                    int num3 = this.cookData.getFinishedGoodsNum(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
                    int rare2 = this.cookData.getFinishedGoodsRare(id1, id2, id3, id4, id5, idd1, idd2, idd3, idd4, idd5, this.dt_waterType);
                    if (rare2 == 0 || this.random.nextInt(rare2) <= 1) {
                        itemFG.func_77979_a(-num3);
                    }
                }
                this.dt_cookingTime = 1;
                slotInfoUpdate();
                func_70296_d();
                return;
            }
            return;
        }
        this.dt_cookingTime = 0;
    }

    private void setWaterMeta() {
        int data;
        int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) & 12;
        if (this.dt_water / this.WATER_MAX > 0.66d) {
            data = 3;
        } else if (this.dt_water / this.WATER_MAX > 0.33d) {
            data = 2;
        } else if (this.dt_water / this.WATER_MAX > 0.0d) {
            data = 1;
        } else {
            data = 0;
        }
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta | data, 3);
    }

    private void move() {
        double[] dArr = this.ang;
        double d = dArr[0] + 1.0d;
        dArr[0] = d;
        if (d >= 360.0d) {
            this.ang[0] = 0.0d;
        }
        double[] dArr2 = this.ang;
        double d2 = dArr2[1] + 1.5d;
        dArr2[1] = d2;
        if (d2 >= 360.0d) {
            this.ang[1] = this.ang[1] - 360.0d;
        }
        double[] dArr3 = this.ang;
        double d3 = dArr3[2] + 2.0d;
        dArr3[2] = d3;
        if (d3 >= 360.0d) {
            this.ang[2] = this.ang[2] - 360.0d;
        }
        double[] dArr4 = this.ang;
        double d4 = dArr4[3] + 2.3d;
        dArr4[3] = d4;
        if (d4 >= 360.0d) {
            this.ang[3] = this.ang[3] - 360.0d;
        }
        double[] dArr5 = this.ang;
        double d5 = dArr5[4] + 2.8d;
        dArr5[4] = d5;
        if (d5 >= 360.0d) {
            this.ang[4] = this.ang[4] - 360.0d;
        }
    }

    private void sendItemInfo(ecru_TileEntityCookPot tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        int water = tileEntity.getWater();
        int power = tileEntity.getPower();
        int cookingTime = tileEntity.getCookingTime();
        int cookingTimeMax = tileEntity.getCookingTimeMax();
        int powerCount = tileEntity.getPowerCount();
        int waterType = tileEntity.getWaterType();
        int[] itemId = new int[5];
        int[] meta = new int[5];
        int[] stack = new int[5];
        Arrays.fill(itemId, 0);
        Arrays.fill(meta, 0);
        Arrays.fill(stack, 0);
        int cTime = tileEntity.isCooking;
        if (tileEntity.viewItemStack[0] != null) {
            tileEntity.viewItemStack[0].func_77973_b();
            itemId[0] = Item.func_150891_b(tileEntity.viewItemStack[0].func_77973_b());
            meta[0] = tileEntity.viewItemStack[0].func_77960_j();
            stack[0] = tileEntity.viewItemStack[0].field_77994_a;
        }
        if (tileEntity.viewItemStack[1] != null) {
            tileEntity.viewItemStack[1].func_77973_b();
            itemId[1] = Item.func_150891_b(tileEntity.viewItemStack[1].func_77973_b());
            meta[1] = tileEntity.viewItemStack[1].func_77960_j();
            stack[1] = tileEntity.viewItemStack[1].field_77994_a;
        }
        if (tileEntity.viewItemStack[2] != null) {
            tileEntity.viewItemStack[2].func_77973_b();
            itemId[2] = Item.func_150891_b(tileEntity.viewItemStack[2].func_77973_b());
            meta[2] = tileEntity.viewItemStack[2].func_77960_j();
            stack[2] = tileEntity.viewItemStack[2].field_77994_a;
        }
        if (tileEntity.viewItemStack[3] != null) {
            tileEntity.viewItemStack[3].func_77973_b();
            itemId[3] = Item.func_150891_b(tileEntity.viewItemStack[3].func_77973_b());
            meta[3] = tileEntity.viewItemStack[3].func_77960_j();
            stack[3] = tileEntity.viewItemStack[3].field_77994_a;
        }
        if (tileEntity.viewItemStack[4] != null) {
            tileEntity.viewItemStack[4].func_77973_b();
            itemId[4] = Item.func_150891_b(tileEntity.viewItemStack[4].func_77973_b());
            meta[4] = tileEntity.viewItemStack[4].func_77960_j();
            stack[4] = tileEntity.viewItemStack[4].field_77994_a;
        }
        ecru_PacketHandler.network.sendToAll(new ecru_PacketCookPot(x, y, z, water, power, cookingTime, cookingTimeMax, powerCount, waterType, itemId, meta, stack, cTime));
    }
}
