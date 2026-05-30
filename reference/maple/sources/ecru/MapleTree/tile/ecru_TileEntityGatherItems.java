package ecru.MapleTree.tile;

import java.util.List;
import java.util.Random;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ecru_TileEntityGatherItems extends TileEntity implements ISidedInventory {
    private ItemStack[] itemStacks = new ItemStack[54];
    private final Random random = new Random();
    private long TIMERCOUNT = 20;
    private long timer = this.TIMERCOUNT;
    private ItemStack[] filterList = new ItemStack[9];
    private int filterListNum = 0;
    private ItemStack[] itemList = new ItemStack[9];
    public int areaSize = 6;
    public int updateInterval = 10;
    public int onOff = 1;
    private static final int[] slots_all = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};

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
        func_70296_d();
    }

    public void func_70296_d() {
        super.func_70296_d();
    }

    public String func_145825_b() {
        return "gatherItems";
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return true;
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
        this.areaSize = nbttagcompound.func_74762_e("areaSize");
        this.updateInterval = nbttagcompound.func_74762_e("updateInterval");
        this.onOff = nbttagcompound.func_74762_e("onOff");
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
        nbttagcompound.func_74768_a("areaSize", this.areaSize);
        nbttagcompound.func_74768_a("updateInterval", this.updateInterval);
        nbttagcompound.func_74768_a("onOff", this.onOff);
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

    public ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K && this.onOff == 1) {
            update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public void update(World world, int i, int j, int k) {
        if (this.timer > 0) {
            this.timer--;
        } else {
            this.timer = this.TIMERCOUNT * this.updateInterval;
            itemsAreCollected(world, i, j, k);
        }
    }

    private int itemsAreCollected(World world, int i, int j, int k) {
        int DISTANCE_NUM = this.areaSize;
        getFilterList(world, i, j, k);
        List list = this.field_145850_b.func_82733_a(EntityItem.class, AxisAlignedBB.func_72330_a(i - DISTANCE_NUM, j - DISTANCE_NUM, k - DISTANCE_NUM, i + DISTANCE_NUM + 1.0d, j + DISTANCE_NUM + 1.0d, k + DISTANCE_NUM + 1.0d), IEntitySelector.field_94557_a);
        if (list == null) {
            return 0;
        }
        for (int m = 0; m < list.size(); m++) {
            EntityItem ei = (EntityItem) list.get(m);
            ItemStack it = ei.func_92059_d();
            for (int fList = 0; fList < this.filterListNum; fList++) {
                if (this.filterList[fList].func_77973_b() == it.func_77973_b() && this.filterList[fList].func_77960_j() == it.func_77960_j()) {
                    int inv = 0;
                    while (true) {
                        if (inv >= 45) {
                            break;
                        }
                        if (func_70301_a(inv) != null && func_70301_a(inv).func_77973_b() == it.func_77973_b() && func_70301_a(inv).func_77960_j() == it.func_77960_j()) {
                            if (func_70301_a(inv).func_77976_d() == func_70301_a(inv).field_77994_a) {
                                continue;
                            } else if (func_70301_a(inv).func_77976_d() - func_70301_a(inv).field_77994_a >= it.field_77994_a) {
                                if (!ei.field_70128_L) {
                                    ei.func_70106_y();
                                    func_70301_a(inv).func_77979_a(-it.field_77994_a);
                                }
                            } else {
                                if (ei.field_70128_L) {
                                    break;
                                }
                                int add = func_70301_a(inv).func_77976_d() - func_70301_a(inv).field_77994_a;
                                int num = it.field_77994_a - (func_70301_a(inv).func_77976_d() - func_70301_a(inv).field_77994_a);
                                func_70301_a(inv).func_77979_a(-add);
                                ei.func_92058_a(new ItemStack(it.func_77973_b(), num, it.func_77960_j()));
                                if (ei.func_92059_d().field_77994_a <= 0) {
                                    ei.func_70106_y();
                                    break;
                                }
                                it = ei.func_92059_d();
                            }
                            inv++;
                        } else if (func_70301_a(inv) != null) {
                            inv++;
                        } else if (!ei.field_70128_L) {
                            ei.func_70106_y();
                            func_70299_a(inv, it);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean getFilterList(World world, int i, int j, int k) {
        ecru_TileEntityGatherItems tile = (ecru_TileEntityGatherItems) world.func_147438_o(i, j, k);
        int num = 0;
        for (int m = 0; m < 9; m++) {
            ItemStack is = tile.func_70301_a(m + 45);
            if (is != null) {
                boolean ret = true;
                for (int c = 0; c < num; c++) {
                    if (this.filterList[c].func_77973_b() == is.func_77973_b() && this.filterList[c].func_77960_j() == is.func_77960_j()) {
                        ret = false;
                    }
                }
                if (ret) {
                    int i2 = num;
                    num++;
                    this.filterList[i2] = is;
                }
            }
        }
        this.filterListNum = num;
        if (this.filterListNum != 0) {
            return true;
        }
        return false;
    }

    public boolean func_94041_b(int slot, ItemStack par2ItemStack) {
        if (slot >= 0 && slot <= 44) {
            return true;
        }
        return false;
    }

    public int[] func_94128_d(int par1) {
        return slots_all;
    }

    public boolean func_102007_a(int slot, ItemStack itemStack, int par3) {
        return func_94041_b(slot, itemStack);
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        return func_94041_b(slot, itemStack);
    }
}
