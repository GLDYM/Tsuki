package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_syntheticRecipe;
import ecru.MapleTree.item.ecru_ItemFoodstuff;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityBiofuelPD extends TileEntity implements ISidedInventory {
    public int dt_convert1;
    public int dt_convert2;
    public int dt_tank1;
    public int dt_tank2;
    public int dt_extraction;
    EntityItem ei1;
    EntityItem ei2;
    EntityItem ei3;
    private static final int[] slots_bottom = {2};
    private static final int[] slots_top = {0};
    private static final int[] slots_side_n = {1};
    private static final int[] slots_side_s = {1};
    private static final int[] slots_side_w = {1};
    private static final int[] slots_side_e = {1};
    private final Random random = new Random();
    ecru_syntheticRecipe synData = new ecru_syntheticRecipe();
    private ItemStack[] itemStacks = new ItemStack[3];
    private long TIMERCOUNT = 60;
    private long timer = this.TIMERCOUNT;
    public int[] CONVERT1_MAX = {200, 130, 80, 50};
    public int[] CONVERT2_MAX = {600, 500, 400, 300};
    public int[] TANK1_MAX = {2000, 2500, 3000, 4000};
    public int[] TANK2_MAX = {300, 500, 700, 1000};
    public int EXTRACTION_MAX = 100;
    public int[] rate = {220, 170, 110, 80};
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    public boolean last_convert1 = false;
    public boolean last_convert2 = false;
    public final int power = 0;
    public int[] pow = {0, 0, 0, 0, 0, 0, 0, 0};
    private boolean powerOnFlg = false;

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
        return "BiofuelPD";
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
        this.dt_convert1 = nbttagcompound.func_74762_e("dt_convert1");
        this.dt_convert2 = nbttagcompound.func_74762_e("dt_convert2");
        this.dt_tank1 = nbttagcompound.func_74762_e("dt_tank1");
        this.dt_tank2 = nbttagcompound.func_74762_e("dt_tank2");
        this.dt_extraction = nbttagcompound.func_74762_e("dt_extraction");
        this.last_convert1 = nbttagcompound.func_74767_n("last_convert1");
        this.last_convert2 = nbttagcompound.func_74767_n("last_convert2");
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
        nbttagcompound.func_74768_a("dt_convert1", this.dt_convert1);
        nbttagcompound.func_74768_a("dt_convert2", this.dt_convert2);
        nbttagcompound.func_74768_a("dt_tank1", this.dt_tank1);
        nbttagcompound.func_74768_a("dt_tank2", this.dt_tank2);
        nbttagcompound.func_74768_a("dt_extraction", this.dt_extraction);
        nbttagcompound.func_74757_a("last_convert1", this.last_convert1);
        nbttagcompound.func_74757_a("last_convert2", this.last_convert2);
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

    private ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public int getTank1() {
        return this.dt_tank1;
    }

    public int getTank2() {
        return this.dt_tank2;
    }

    public void setTank1(int t) {
        this.dt_tank1 = t;
    }

    public void setTank2(int t) {
        this.dt_tank2 = t;
    }

    public void func_145845_h() {
        powerMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        ItemStack item0 = getItem(0);
        ItemStack item1 = getItem(1);
        ItemStack item2 = getItem(2);
        int rank = world.func_72805_g(i, j, k) & 3;
        int meta = world.func_72805_g(i, j, k);
        if (item0 == null && world.func_147439_a(i, j + 1, k) == mod_ecru_MapleTree.blockGrainHopper) {
            if (this.timer > 0) {
                this.timer--;
            } else {
                ecru_TileEntityGrainHopper chest1 = (ecru_TileEntityGrainHopper) world.func_147438_o(i, j + 1, k);
                if (chest1 != null) {
                    int inv = 0;
                    while (true) {
                        if (inv < chest1.func_70302_i_()) {
                            ItemStack itemstack = chest1.func_70301_a(inv);
                            if (itemstack == null || !chkItemInstance(itemstack)) {
                                inv++;
                            } else {
                                world.func_147471_g(i, j + 1, k);
                                setItem(itemstack, 0);
                                chest1.deleteItem(inv);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                this.timer = this.TIMERCOUNT;
            }
        }
        if ((this.dt_convert1 > 0) != this.last_convert1) {
            this.last_convert1 = this.dt_convert1 > 0;
            if (this.dt_convert1 > 0) {
                world.func_72921_c(i, j, k, meta | 8, 3);
            } else {
                world.func_72921_c(i, j, k, meta & 7, 3);
            }
        }
        if ((this.dt_convert2 > 0) != this.last_convert2) {
            this.last_convert2 = this.dt_convert2 > 0;
            int meta2 = world.func_72805_g(i, j, k);
            if (this.dt_convert2 > 0) {
                world.func_72921_c(i, j, k, meta2 | 4, 3);
            } else {
                world.func_72921_c(i, j, k, meta2 & 11, 3);
            }
        }
        if (item0 != null && this.dt_tank1 < this.TANK1_MAX[rank] && chkItemInstance(item0)) {
            int i2 = this.dt_convert1;
            this.dt_convert1 = i2 + 1;
            if (i2 >= this.CONVERT1_MAX[rank]) {
                this.dt_tank1++;
                item0.func_77979_a(1);
                if (item0.field_77994_a == 0) {
                    deleteItem(0);
                }
                this.dt_convert1 = 1;
            }
        } else {
            this.dt_convert1 = 0;
        }
        if (this.dt_tank1 >= this.rate[rank] && this.dt_tank2 < this.TANK2_MAX[rank]) {
            int i3 = this.dt_convert2;
            this.dt_convert2 = i3 + 1;
            if (i3 >= this.CONVERT2_MAX[rank]) {
                this.dt_tank1 -= this.rate[rank];
                this.dt_tank2++;
                this.dt_convert2 = 1;
            }
        } else {
            this.dt_convert2 = 0;
        }
        if (item1 != null && item1.func_77973_b() == mod_ecru_MapleTree.Item_normalItem && item1.func_77960_j() == 3 && this.dt_tank2 > 0 && ((item2 != null && item2.field_77994_a < 64 && item2.func_77973_b() == mod_ecru_MapleTree.Item_normalItem && item2.func_77960_j() == 2) || item2 == null)) {
            int i4 = this.dt_extraction;
            this.dt_extraction = i4 + 1;
            if (i4 >= this.EXTRACTION_MAX) {
                this.dt_extraction = 0;
                this.dt_tank2--;
                item1.func_77979_a(1);
                if (item1.field_77994_a == 0) {
                    deleteItem(1);
                }
                if (item2 == null) {
                    setItem(new ItemStack(mod_ecru_MapleTree.Item_normalItem, 1, 2), 2);
                } else {
                    item2.func_77979_a(-1);
                }
            }
        } else {
            this.dt_extraction = 0;
        }
        func_70296_d();
    }

    private boolean chkItemInstance(ItemStack m) {
        Item i = m.func_77973_b();
        if (i == mod_ecru_MapleTree.Item_foodstuff && m.func_77960_j() == 1) {
            return false;
        }
        if ((i instanceof ItemSeeds) || (i instanceof ItemFood) || (i instanceof ItemSeedFood) || (i instanceof ecru_ItemFoodstuff) || i == Items.field_151015_O || i == Items.field_151105_aU || i == Items.field_151120_aE || i == mod_ecru_MapleTree.Item_salt || i == mod_ecru_MapleTree.Item_vanillaSeed || i == mod_ecru_MapleTree.Item_vanillaSheath || i == mod_ecru_MapleTree.Item_vanillaBeans || i == mod_ecru_MapleTree.Item_chestnut || i == mod_ecru_MapleTree.Item_chestnutsBburrs || i == mod_ecru_MapleTree.Item_SunFlowerSeed || i == mod_ecru_MapleTree.Item_flour || i == mod_ecru_MapleTree.Item_dough || i == mod_ecru_MapleTree.Item_hamburger_meat || i == mod_ecru_MapleTree.Item_unhulledStickyRice || i == mod_ecru_MapleTree.Item_grapeSeed || i == Item.func_150898_a(mod_ecru_MapleTree.blockCabbage) || i == mod_ecru_MapleTree.Item_cloveBud || i == mod_ecru_MapleTree.Item_star_aniseFruit || i == mod_ecru_MapleTree.Item_nutmegFruit || i == mod_ecru_MapleTree.Item_machinedBonito || i == mod_ecru_MapleTree.Item_BoiledBonito) {
            return true;
        }
        if (i == mod_ecru_MapleTree.Item_normalItem && m.func_77960_j() == 1) {
            return true;
        }
        return false;
    }

    private void powerMove(World world, int i, int j, int k) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if ((meta & 8) == 8) {
                int[] iArr = this.pow;
                iArr[1] = iArr[1] + 1;
                int[] iArr2 = this.pow;
                iArr2[3] = iArr2[3] + 2;
                int[] iArr3 = this.pow;
                iArr3[5] = iArr3[5] + 3;
                int[] iArr4 = this.pow;
                iArr4[7] = iArr4[7] + 4;
            }
            if ((meta & 4) == 4) {
                int[] iArr5 = this.pow;
                iArr5[2] = iArr5[2] - 1;
                int[] iArr6 = this.pow;
                iArr6[4] = iArr6[4] - 2;
                int[] iArr7 = this.pow;
                iArr7[6] = iArr7[6] - 3;
            }
            if (this.pow[1] >= 360) {
                this.pow[1] = this.pow[1] - 360;
            }
            if (this.pow[3] >= 360) {
                this.pow[3] = this.pow[3] - 360;
            }
            if (this.pow[5] >= 360) {
                this.pow[5] = this.pow[5] - 360;
            }
            if (this.pow[7] >= 360) {
                this.pow[7] = this.pow[7] - 360;
            }
            if (this.pow[2] <= 0) {
                this.pow[2] = 360 + this.pow[2];
            }
            if (this.pow[4] <= 0) {
                this.pow[4] = 360 + this.pow[4];
            }
            if (this.pow[6] <= 0) {
                this.pow[6] = 360 + this.pow[6];
            }
        }
    }

    public boolean func_94041_b(int slot, ItemStack par2ItemStack) {
        if (slot >= 0 && slot <= 2) {
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
        if (slot == 0 || slot == 1) {
            return true;
        }
        return false;
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        if (slot == 2) {
            return true;
        }
        return false;
    }
}
