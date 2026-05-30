package ecru.MapleTree.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_EntityMomijiInventory extends ecru_EntityTameable implements ISidedInventory {
    private int MAXIMUM_HOME_DISTANCE;
    public ItemStack[] itemStacks;
    private static final int[] slots_bottom = {20};
    private static final int[] slots_top = {0, 1, 2};
    private static final int[] slots_side_n = {20};
    private static final int[] slots_side_s = {20};
    private static final int[] slots_side_w = {20};
    private static final int[] slots_side_e = {20};

    public ecru_EntityMomijiInventory(World p_i1696_1_) {
        super(p_i1696_1_);
        this.MAXIMUM_HOME_DISTANCE = 16;
        this.itemStacks = new ItemStack[21];
    }

    public int func_70302_i_() {
        return this.itemStacks.length;
    }

    public ItemStack func_70301_a(int par1) {
        return this.itemStacks[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.itemStacks[par1] != null) {
            if (this.itemStacks[par1].field_77994_a <= par2) {
                ItemStack var3 = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                if (par1 >= 0 && par1 < 3) {
                    updateInventory(par1, null);
                }
                func_70296_d();
                return var3;
            }
            ItemStack var32 = this.itemStacks[par1].func_77979_a(par2);
            if (this.itemStacks[par1].field_77994_a == 0) {
                this.itemStacks[par1] = null;
            }
            if (par1 >= 0 && par1 < 3) {
                updateInventory(par1, null);
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
        if (par1 >= 0 && par1 < 3) {
            updateInventory(par1, null);
        }
        func_70296_d();
    }

    public String func_145825_b() {
        return "Secret savings of the Momiji";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
        return null;
    }

    public int getFirstEmptyStack() {
        for (int i = 3; i < this.itemStacks.length; i++) {
            if (this.itemStacks[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void updateInventory(int i, EntityPlayer entityPlayer) {
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
        if (slot >= 0 && slot <= 2) {
            return true;
        }
        return false;
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        return false;
    }
}
