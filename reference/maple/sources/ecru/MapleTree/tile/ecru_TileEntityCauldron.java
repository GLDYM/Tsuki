package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityCauldron extends TileEntity implements IInventory {
    private long sTime;
    private long nTime;
    private ItemStack[] itemStacks = new ItemStack[9];
    public long tim;

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
        return "MapleCauldron";
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

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.tim = nbttagcompound.func_74763_f("tim");
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
        nbttagcompound.func_74772_a("tim", this.tim);
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

    public long getTime() {
        return this.tim;
    }

    public void setTime(long byte0) {
        this.tim = byte0;
    }

    public void setItem(ItemStack is, int slot) {
        func_70299_a(slot, is);
    }

    public ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public void func_145845_h() {
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        Item iid;
        int size;
        if (world.field_72995_K) {
            return;
        }
        ecru_TileEntityCauldron tileentity = (ecru_TileEntityCauldron) world.func_147438_o(i, j, k);
        this.sTime = tileentity.getTime();
        this.nTime = world.func_82737_E();
        Block id = world.func_147439_a(i, j - 1, k);
        int meta = world.func_72805_g(i, j, k);
        if (meta > 10) {
            world.func_72921_c(i, j, k, 10, 2);
            meta = 10;
        }
        if (this.sTime == 0 && id == mod_ecru_MapleTree.blockFallenLeavesFire && meta == 10) {
            this.sTime = world.func_82737_E();
            tileentity.setTime(this.sTime);
            return;
        }
        if (id != mod_ecru_MapleTree.blockFallenLeavesFire || meta != 10) {
            tileentity.setTime(0L);
            return;
        }
        if (this.nTime - this.sTime >= 2000 && this.sTime != 0) {
            world.func_72921_c(i, j, k, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
            tileentity.setTime(0L);
            int addSugar = 8;
            for (int m = 0; m < 9; m++) {
                ItemStack is = tileentity.getItem(m);
                if (is != null) {
                    iid = is.func_77973_b();
                    size = is.field_77994_a;
                } else {
                    iid = mod_ecru_MapleTree.Item_mapleSyrup;
                    size = 0;
                }
                if (iid == mod_ecru_MapleTree.Item_mapleSyrup && size < 64) {
                    int num = 64 - size;
                    if (num < addSugar) {
                        addSugar -= num;
                        tileentity.setItem(new ItemStack(mod_ecru_MapleTree.Item_mapleSyrup, 64, 0), m);
                    } else {
                        tileentity.setItem(new ItemStack(mod_ecru_MapleTree.Item_mapleSyrup, size + addSugar, 0), m);
                        return;
                    }
                }
            }
            if (addSugar > 1 && !world.field_72995_K) {
                EntityItem ei = new EntityItem(world, i + 0.1d, j + 0.3d, k + 0.1d, new ItemStack(mod_ecru_MapleTree.Item_mapleSyrup, addSugar, 0));
                world.func_72838_d(ei);
            }
            func_70296_d();
        }
    }
}
