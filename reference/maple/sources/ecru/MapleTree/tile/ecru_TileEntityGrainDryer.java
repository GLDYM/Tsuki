package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_fuelHandler;
import ecru.MapleTree.common.ecru_syntheticRecipe;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityGrainDryer extends TileEntity implements ISidedInventory {
    private final Random random = new Random();
    ecru_syntheticRecipe synData = new ecru_syntheticRecipe();
    ecru_fuelHandler fuelInfo = new ecru_fuelHandler();
    private ItemStack[] itemStacks = new ItemStack[13];
    private long TIMERCOUNT = 60;
    private long timer = this.TIMERCOUNT;
    public int dt_burnTime = 0;
    public int dt_burnTimeMax = 0;
    public boolean last_burn = false;
    public int[] dt_completion = {0, 0, 0, 0, 0, 0};
    public int completionMax = 100;
    public int dt_powerCount = 0;
    private static ItemStack[] inItem = {new ItemStack(mod_ecru_MapleTree.Item_allspiceSeed, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_cardamonFruit, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_cuminSeed, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_cloveBud, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_corianderSeed, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_cinnamonBark, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_star_aniseFruit, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_turmericRoot, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_chili_pepperFruit, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_fennelSeed, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_nutmegSeed, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_BoiledBonito, 1, 0)};
    private static ItemStack[] outItem = {new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 0), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 1), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 2), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 3), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 4), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 5), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 6), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 7), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 8), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 9), new ItemStack(mod_ecru_MapleTree.Item_SpiceList, 1, 10), new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 24)};
    private static final int[] slots_material = {0, 1, 2, 3, 4, 5};
    private static final int[] slots_spice = {6, 7, 8, 9, 10, 11};
    private static final int[] slots_fuel = {12};

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

    public int getPowerCount() {
        return this.dt_powerCount;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_completion[0] = nbttagcompound.func_74762_e("dt_completion0");
        this.dt_completion[1] = nbttagcompound.func_74762_e("dt_completion1");
        this.dt_completion[2] = nbttagcompound.func_74762_e("dt_completion2");
        this.dt_completion[3] = nbttagcompound.func_74762_e("dt_completion3");
        this.dt_completion[4] = nbttagcompound.func_74762_e("dt_completion4");
        this.dt_completion[5] = nbttagcompound.func_74762_e("dt_completion5");
        this.completionMax = nbttagcompound.func_74762_e("completionMax");
        this.dt_burnTime = nbttagcompound.func_74762_e("dt_burnTime");
        this.dt_burnTimeMax = nbttagcompound.func_74762_e("dt_burnTimeMax");
        this.last_burn = nbttagcompound.func_74767_n("last_burn");
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
        nbttagcompound.func_74768_a("dt_completion0", this.dt_completion[0]);
        nbttagcompound.func_74768_a("dt_completion1", this.dt_completion[1]);
        nbttagcompound.func_74768_a("dt_completion2", this.dt_completion[2]);
        nbttagcompound.func_74768_a("dt_completion3", this.dt_completion[3]);
        nbttagcompound.func_74768_a("dt_completion4", this.dt_completion[4]);
        nbttagcompound.func_74768_a("dt_completion5", this.dt_completion[5]);
        nbttagcompound.func_74768_a("completionMax", this.completionMax);
        nbttagcompound.func_74768_a("dt_burnTime", this.dt_burnTime);
        nbttagcompound.func_74768_a("dt_burnTimeMax", this.dt_burnTimeMax);
        nbttagcompound.func_74757_a("last_burn", this.last_burn);
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

    public int getCompletion(int s) {
        return this.dt_completion[s];
    }

    public int getCompletionMax() {
        return this.completionMax;
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        ItemStack out;
        ItemStack[] material = {getItem(0), getItem(1), getItem(2), getItem(3), getItem(4), getItem(5)};
        ItemStack[] spice = {getItem(6), getItem(7), getItem(8), getItem(9), getItem(10), getItem(11)};
        ItemStack fuel = getItem(12);
        if (this.dt_burnTime <= 1) {
            if (fuel != null) {
                int max = this.fuelInfo.getBurnTime(fuel);
                if (max < 1) {
                    return;
                }
                boolean burn = false;
                for (int m = 0; m < 6; m++) {
                    if (material[m] != null && (out = materialCheck(material[m])) != null && (spice[m] == null || (out.func_77973_b() == spice[m].func_77973_b() && out.func_77960_j() == spice[m].func_77960_j() && spice[m].field_77994_a < spice[m].func_77976_d()))) {
                        burn = true;
                        break;
                    }
                }
                if (burn) {
                    fuel.func_77979_a(1);
                    if (fuel.field_77994_a == 0) {
                        deleteItem(12);
                    }
                    this.dt_burnTime = max;
                    this.dt_burnTimeMax = max;
                } else {
                    this.dt_burnTime = 0;
                    this.dt_completion[0] = 0;
                    this.dt_completion[1] = 0;
                    this.dt_completion[2] = 0;
                    this.dt_completion[3] = 0;
                    this.dt_completion[4] = 0;
                    this.dt_completion[5] = 0;
                }
            } else {
                this.dt_burnTime = 0;
                this.dt_completion[0] = 0;
                this.dt_completion[1] = 0;
                this.dt_completion[2] = 0;
                this.dt_completion[3] = 0;
                this.dt_completion[4] = 0;
                this.dt_completion[5] = 0;
            }
        } else {
            for (int m2 = 0; m2 < 6; m2++) {
                if (material[m2] == null || materialCheck(material[m2]) != null) {
                    if (material[m2] != null) {
                        ItemStack out2 = materialCheck(material[m2]);
                        if (spice[m2] == null || (out2.func_77973_b() == spice[m2].func_77973_b() && out2.func_77960_j() == spice[m2].func_77960_j() && spice[m2].field_77994_a < spice[m2].func_77976_d())) {
                            int[] iArr = this.dt_completion;
                            int i2 = m2;
                            iArr[i2] = iArr[i2] + 1;
                        }
                    } else {
                        this.dt_completion[m2] = 0;
                    }
                    if (this.dt_completion[m2] > 100 && materialCheck(material[m2]) != null) {
                        int meta = materialCheck(material[m2]).func_77960_j();
                        material[m2].func_77979_a(1);
                        if (material[m2].field_77994_a == 0) {
                            deleteItem(m2);
                        }
                        if (spice[m2] == null) {
                            setItem(new ItemStack(materialCheck(material[m2]).func_77973_b(), 1, meta), m2 + 6);
                        } else if (spice[m2].field_77994_a < spice[m2].func_77976_d()) {
                            spice[m2].func_77979_a(-1);
                        }
                        this.dt_completion[m2] = 0;
                    }
                }
            }
            this.dt_burnTime--;
        }
        if (this.dt_burnTime > 0) {
            int i3 = this.dt_powerCount + 1;
            this.dt_powerCount = i3;
            if (i3 >= 16) {
                this.dt_powerCount = 0;
            }
        }
        if ((this.dt_burnTime > 0) != this.last_burn) {
            this.last_burn = this.dt_burnTime > 0;
            if (this.dt_burnTime > 0) {
                int meta2 = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta2 | 8, 3);
            } else {
                int meta3 = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta3 & 7, 3);
            }
        }
    }

    private ItemStack materialCheck(ItemStack item) {
        for (int i = 0; i < inItem.length; i++) {
            if (inItem[i].func_77973_b() == item.func_77973_b() && inItem[i].func_77960_j() == item.func_77960_j()) {
                return outItem[i];
            }
        }
        return null;
    }

    private void particle(World world, int i, int j, int k) {
        double d = 0.2d;
        while (true) {
            double x = d;
            if (x <= 0.8d) {
                double d2 = 0.2d;
                while (true) {
                    double z = d2;
                    if (z <= 0.8d) {
                        if (this.random.nextInt(4) == 0) {
                            world.func_72869_a("flame", i + x, j + 0.2d, k + z, 0.0d, 0.0d, 0.0d);
                        }
                        d2 = z + 0.2d;
                    }
                }
                d = x + 0.2d;
            } else {
                return;
            }
        }
    }

    public boolean func_94041_b(int slot, ItemStack par2ItemStack) {
        if (slot >= 0 && slot <= 12) {
            return true;
        }
        return false;
    }

    public int[] func_94128_d(int par1) {
        switch (par1) {
            case 0:
                return slots_spice;
            case 1:
                return slots_fuel;
            case 2:
                return slots_material;
            case 3:
                return slots_material;
            case 4:
                return slots_material;
            case 5:
                return slots_material;
            default:
                return slots_spice;
        }
    }

    public boolean func_102007_a(int slot, ItemStack itemStack, int par3) {
        if ((slot >= 0 && slot <= 5) || slot == 12) {
            return true;
        }
        return false;
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        if (slot >= 6 && slot <= 11) {
            return true;
        }
        return false;
    }
}
