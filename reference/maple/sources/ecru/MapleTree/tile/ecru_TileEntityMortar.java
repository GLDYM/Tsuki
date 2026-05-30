package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityMortar extends TileEntity implements ISidedInventory {
    private final Random random = new Random();
    private ItemStack[] itemStacks = new ItemStack[16];
    public byte[] onOff = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    public byte[] num = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    public int run = 0;
    public int[] recipe = new int[2];
    private boolean creating = false;
    private boolean lastCreating = false;
    public int createCounte = 0;
    public int createCounteMax = 200;
    public int createMeta = -1;
    public int curryNum = 0;
    public int errorCode = 0;
    private boolean bootFlg = true;
    private static final int[] slots_spice = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private static final int[] slots_curry = {15};

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
                func_70296_d();
                updateInventory(1);
                return var3;
            }
            ItemStack var32 = this.itemStacks[par1].func_77979_a(par2);
            if (this.itemStacks[par1].field_77994_a == 0) {
                this.itemStacks[par1] = null;
            }
            func_70296_d();
            updateInventory(2);
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
        func_70296_d();
        updateInventory(3);
    }

    public String func_145825_b() {
        return "Mortar";
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

    public void func_70296_d() {
        super.func_70296_d();
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        for (int i = 0; i < 15; i++) {
            this.onOff[i] = nbttagcompound.func_74771_c("onOff" + i);
        }
        for (int j = 0; j < 15; j++) {
            this.num[j] = nbttagcompound.func_74771_c("spice_num" + j);
        }
        this.creating = nbttagcompound.func_74767_n("creating");
        this.lastCreating = nbttagcompound.func_74767_n("lastCreating");
        this.createCounte = nbttagcompound.func_74762_e("createCounte");
        this.createMeta = nbttagcompound.func_74762_e("createMeta");
        this.run = nbttagcompound.func_74762_e("run");
        this.curryNum = nbttagcompound.func_74762_e("curryNum");
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
        for (int i = 0; i < 15; i++) {
            nbttagcompound.func_74774_a("onOff" + i, this.onOff[i]);
        }
        for (int j = 0; j < 15; j++) {
            nbttagcompound.func_74774_a("spice_num" + j, this.num[j]);
        }
        nbttagcompound.func_74757_a("creating", this.creating);
        nbttagcompound.func_74757_a("lastCreating", this.lastCreating);
        nbttagcompound.func_74768_a("createCounte", this.createCounte);
        nbttagcompound.func_74768_a("createMeta", this.createMeta);
        nbttagcompound.func_74768_a("run", this.run);
        nbttagcompound.func_74768_a("curryNum", this.curryNum);
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

    private ItemStack getItems(int slot) {
        return func_70301_a(slot);
    }

    public void updateInventory(int c) {
        int _aroma_sweet = 0;
        int _aroma_refreshing = 0;
        int _aroma_stimulating = 0;
        int _taste_sweet = 0;
        int _taste_hot = 0;
        int _taste_bitter = 0;
        for (int m = 0; m < 15; m++) {
            if (this.itemStacks[m] != null && this.onOff[m] == 1 && this.num[m] > 0) {
                this.itemStacks[m].func_77973_b();
                if ("mod_ecru_MapleTree:spices".equals(Item.field_150901_e.func_148750_c(this.itemStacks[m].func_77973_b()))) {
                    int meta = this.itemStacks[m].func_77960_j();
                    _aroma_sweet += mod_ecru_MapleTree.spiceList[meta].aroma_sweet * this.num[m];
                    _aroma_refreshing += mod_ecru_MapleTree.spiceList[meta].aroma_refreshing * this.num[m];
                    _aroma_stimulating += mod_ecru_MapleTree.spiceList[meta].aroma_stimulating * this.num[m];
                    _taste_sweet += mod_ecru_MapleTree.spiceList[meta].taste_sweet * this.num[m];
                    _taste_hot += mod_ecru_MapleTree.spiceList[meta].taste_hot * this.num[m];
                    _taste_bitter += mod_ecru_MapleTree.spiceList[meta].taste_bitter * this.num[m];
                }
            }
        }
        this.recipe = mod_ecru_MapleTree.curryspiceList[0].getCurryRecipe(_aroma_sweet, _aroma_refreshing, _aroma_stimulating, _taste_sweet, _taste_hot, _taste_bitter);
    }

    public void func_145845_h() {
        World world = this.field_145850_b;
        int i = this.field_145851_c;
        int i2 = this.field_145848_d;
        int i3 = this.field_145849_e;
        if (world.field_72995_K) {
            return;
        }
        if (this.bootFlg) {
            this.bootFlg = false;
            updateInventory(5);
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        int aroma_sweet = 0;
        int aroma_refreshing = 0;
        int aroma_stimulating = 0;
        int taste_sweet = 0;
        int taste_hot = 0;
        int taste_bitter = 0;
        int color = 0;
        int cou = 0;
        if (!this.creating && (this.run == 1 || this.run == 2)) {
            if (this.run == 1) {
                this.run = 0;
            }
            int indispensableSpiceNum = 0;
            this.errorCode = 0;
            this.curryNum = 0;
            for (int m = 0; m < 15; m++) {
                if (this.itemStacks[m] != null && this.onOff[m] == 1 && this.num[m] > 0) {
                    this.itemStacks[m].func_77973_b();
                    if ("mod_ecru_MapleTree:spices".equals(Item.field_150901_e.func_148750_c(this.itemStacks[m].func_77973_b()))) {
                        int meta = this.itemStacks[m].func_77960_j();
                        if (meta == 2) {
                            indispensableSpiceNum |= 1;
                        }
                        if (meta == 7) {
                            indispensableSpiceNum |= 2;
                        }
                        if (meta == 8) {
                            indispensableSpiceNum |= 4;
                        }
                        if (this.itemStacks[m].field_77994_a >= this.num[m]) {
                            aroma_sweet += mod_ecru_MapleTree.spiceList[meta].aroma_sweet * this.num[m];
                            aroma_refreshing += mod_ecru_MapleTree.spiceList[meta].aroma_refreshing * this.num[m];
                            aroma_stimulating += mod_ecru_MapleTree.spiceList[meta].aroma_stimulating * this.num[m];
                            taste_sweet += mod_ecru_MapleTree.spiceList[meta].taste_sweet * this.num[m];
                            taste_hot += mod_ecru_MapleTree.spiceList[meta].taste_hot * this.num[m];
                            taste_bitter += mod_ecru_MapleTree.spiceList[meta].taste_bitter * this.num[m];
                            color += mod_ecru_MapleTree.spiceList[meta].color * this.num[m];
                            cou++;
                            this.curryNum += this.num[m];
                        } else {
                            this.run = 0;
                            this.errorCode = 1;
                            return;
                        }
                    } else {
                        continue;
                    }
                }
            }
            this.curryNum /= 8;
            this.curryNum = this.curryNum == 0 ? 1 : this.curryNum;
            if (indispensableSpiceNum != 7) {
                this.run = 0;
                this.errorCode = 3;
                return;
            }
            int curry = mod_ecru_MapleTree.curryspiceList[0].getCurry(aroma_sweet, aroma_refreshing, aroma_stimulating, taste_sweet, taste_hot, taste_bitter);
            if (curry == 0) {
                this.errorCode = 0;
            }
            ItemStack it = getItems(15);
            boolean flg = false;
            if ((it != null && it.func_77973_b() == mod_ecru_MapleTree.Item_Curryspice && it.func_77960_j() == curry && it.field_77994_a + this.curryNum <= it.func_77976_d()) || it == null) {
                flg = true;
            }
            if (!flg) {
                this.errorCode = 2;
                this.run = 0;
                return;
            }
            for (int m2 = 0; m2 < 15; m2++) {
                if (this.itemStacks[m2] != null && this.onOff[m2] == 1 && this.num[m2] > 0) {
                    this.itemStacks[m2].func_77979_a(this.num[m2]);
                    if (this.itemStacks[m2].field_77994_a <= 0) {
                        this.itemStacks[m2] = null;
                    }
                }
            }
            this.createMeta = curry;
            this.creating = true;
            func_70296_d();
        } else if (this.creating) {
            if (this.run != 2) {
                this.run = 0;
                this.errorCode = 0;
            } else {
                this.errorCode = 4;
            }
            int i2 = this.createCounte;
            this.createCounte = i2 + 1;
            if (i2 > this.createCounteMax) {
                this.creating = false;
                this.createCounte = 0;
                this.errorCode = 0;
                if (this.createMeta != -1) {
                    ItemStack it2 = getItems(15);
                    if (it2 != null && it2.func_77973_b() == mod_ecru_MapleTree.Item_Curryspice && it2.func_77960_j() == this.createMeta && it2.field_77994_a + this.curryNum <= it2.func_77976_d()) {
                        it2.func_77979_a(-this.curryNum);
                    } else if (it2 == null) {
                        setItem(new ItemStack(mod_ecru_MapleTree.Item_Curryspice, this.curryNum, this.createMeta), 15);
                    }
                }
                func_70296_d();
            }
        }
        if (this.creating != this.lastCreating) {
            if (this.creating && (world.func_72805_g(i, j, k) & 8) != 8) {
                int meta2 = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta2 | 8, 3);
            } else {
                int meta3 = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta3 & 7, 3);
            }
            this.lastCreating = this.creating;
        }
    }

    public boolean func_94041_b(int slot, ItemStack par2ItemStack) {
        if (slot >= 0 && slot <= 15) {
            return true;
        }
        return false;
    }

    public int[] func_94128_d(int par1) {
        switch (par1) {
            case 0:
                return slots_curry;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                return slots_spice;
        }
    }

    public boolean func_102007_a(int slot, ItemStack itemStack, int par3) {
        if (slot >= 0 && slot <= 14) {
            return true;
        }
        return false;
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        if (slot == 15) {
            return true;
        }
        return false;
    }
}
