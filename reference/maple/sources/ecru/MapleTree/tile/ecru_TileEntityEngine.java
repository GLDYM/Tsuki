package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_fuelHandler;
import ecru.MapleTree.common.ecru_syntheticRecipe;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityEngine extends TileEntity implements IInventory {
    ecru_syntheticRecipe synData = new ecru_syntheticRecipe();
    private final Random random = new Random();
    private ItemStack[] itemStacks = new ItemStack[1];
    public int dt_burnTime = 0;
    public int dt_burnTimeMax = 0;
    private int timer = 100;
    private boolean last_isBurning = false;
    private boolean last_flag = false;
    public int power1 = this.random.nextInt(360);
    private float[] pistonPower = {0.0f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f};
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    ecru_fuelHandler fuelInfo = new ecru_fuelHandler();

    public int getPower1() {
        return this.power1;
    }

    public float getPPower(int i) {
        return this.pistonPower[i];
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
    }

    private void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public String func_145825_b() {
        return "engine";
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
        this.dt_burnTime = nbttagcompound.func_74762_e("dt_burnTime");
        this.dt_burnTimeMax = nbttagcompound.func_74762_e("dt_burnTimeMax");
        this.last_isBurning = nbttagcompound.func_74767_n("last_isBurning");
        this.last_flag = nbttagcompound.func_74767_n("last_flag");
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
        nbttagcompound.func_74768_a("dt_burnTime", this.dt_burnTime);
        nbttagcompound.func_74768_a("dt_burnTimeMax", this.dt_burnTimeMax);
        nbttagcompound.func_74757_a("last_isBurning", this.last_isBurning);
        nbttagcompound.func_74757_a("last_flag", this.last_flag);
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

    public int getBurnTime() {
        return this.dt_burnTime;
    }

    public int getBurnTimeMax() {
        return this.dt_burnTimeMax;
    }

    public boolean isBurning() {
        return this.dt_burnTime > 0;
    }

    private ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public void setItem(ItemStack is, int slot) {
        func_70299_a(slot, is);
    }

    public void func_145845_h() {
        World world = this.field_145850_b;
        int i = this.field_145851_c;
        int j = this.field_145848_d;
        int k = this.field_145849_e;
        powerMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.field_145850_b.field_72995_K) {
            if ((world.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) & 8) == 8) {
                int i2 = this.timer;
                this.timer = i2 + 1;
                if (i2 > 100) {
                    this.timer = 0;
                    float v = mod_ecru_MapleTree.EnvironmentSounds;
                    world.func_72980_b(i + 0.5d, j + 0.5d, k + 0.5d, "mapletree:machine.engine", v, 1.0f, true);
                    return;
                }
                return;
            }
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        ItemStack fuel = getItem(0);
        boolean flag = world.func_72864_z(i, j, k) || world.func_72864_z(i, j + 1, k);
        if (this.last_flag != flag) {
            this.last_flag = flag;
            if (!flag) {
                int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta & 14, 3);
                return;
            } else {
                int meta2 = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta2 | 1, 3);
            }
        }
        if (!flag) {
            return;
        }
        if (isBurning() != this.last_isBurning) {
            this.last_isBurning = isBurning();
            if (isBurning()) {
                int meta3 = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta3 | 8, 3);
            } else {
                int meta4 = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta4 & 7, 3);
            }
        }
        if (this.dt_burnTime <= 1) {
            if (fuel != null) {
                int max = this.synData.getFuelMax(fuel.func_77973_b());
                if (max < 1) {
                    max = this.fuelInfo.getBurnTime(fuel);
                    if (max < 1) {
                        return;
                    }
                }
                this.dt_burnTimeMax = max;
                this.dt_burnTime = max;
                if (fuel.func_77973_b() == Items.field_151129_at) {
                    setItem(new ItemStack(Items.field_151133_ar, 1, 0), 0);
                } else {
                    fuel.func_77979_a(1);
                    if (fuel.field_77994_a == 0) {
                        deleteItem(0);
                    }
                }
            } else {
                this.dt_burnTime = 0;
            }
            func_70296_d();
            return;
        }
        this.dt_burnTime--;
    }

    private void powerMove(World world, int i, int j, int k) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            int i2 = this.power1 + 1;
            this.power1 = i2;
            if (i2 >= 360) {
                this.power1 = 0;
            }
            for (int m = 1; m <= 6; m++) {
                float f = (float) (r0[r1] + 0.3d);
                this.pistonPower[m] = f;
                if (f > 6.283184d) {
                    this.pistonPower[m] = 0.0f;
                }
            }
        }
    }
}
