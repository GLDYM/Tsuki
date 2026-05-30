package ecru.MapleTree.tile;

import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketPlanter;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityPlanter extends TileEntity implements ISidedInventory {
    private int[] blockIdList;
    private int target_x;
    private int target_y;
    private int target_z;
    private static final int[] slots_bottom = {1};
    private static final int[] slots_top = {0};
    private static final int[] slots_side_n = {2};
    private static final int[] slots_side_s = {2};
    private static final int[] slots_side_w = {1};
    private static final int[] slots_side_e = {1};
    private final Random random = new Random();
    private ItemStack[] itemStacks = new ItemStack[9];
    public int dt_water = 50;
    public int dt_waterMax = 50;
    public int dt_fertilizer = 50;
    public int dt_fertilizerMax = 50;
    public int dt_fertilizerId = 0;
    public int dt_fertilizerMeta = 0;
    public ItemStack dt_soilId = null;
    private int water = 0;
    private long TIMERCOUNT = 60;
    private long timer = this.TIMERCOUNT;
    private ecru_IdList blockInfo = new ecru_IdList();
    private String fieldName = "";
    public int fieldNum = 0;

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

    private void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public String func_145825_b() {
        return "Planter";
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
        this.dt_fertilizer = nbttagcompound.func_74762_e("dt_fertilizer");
        this.dt_waterMax = nbttagcompound.func_74762_e("dt_waterMax");
        this.dt_fertilizerMax = nbttagcompound.func_74762_e("dt_fertilizerMax");
        this.dt_fertilizerId = nbttagcompound.func_74762_e("dt_fertilizerId");
        this.dt_fertilizerMeta = nbttagcompound.func_74762_e("dt_fertilizerMeta");
        NBTTagCompound _dt_soilId = nbttagcompound.func_74775_l("dt_soilId");
        this.dt_soilId = ItemStack.func_77949_a(_dt_soilId);
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
        nbttagcompound.func_74768_a("dt_fertilizer", this.dt_fertilizer);
        nbttagcompound.func_74768_a("dt_waterMax", this.dt_waterMax);
        nbttagcompound.func_74768_a("dt_fertilizerMax", this.dt_fertilizerMax);
        nbttagcompound.func_74768_a("dt_fertilizerId", this.dt_fertilizerId);
        nbttagcompound.func_74768_a("dt_fertilizerMeta", this.dt_fertilizerMeta);
        NBTTagCompound soil = new NBTTagCompound();
        if (this.dt_soilId != null) {
            this.dt_soilId.func_77955_b(soil);
        }
        nbttagcompound.func_74782_a("dt_soilId", soil);
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

    public int getFieldNum() {
        return this.fieldNum;
    }

    public int getWater() {
        return this.dt_water;
    }

    public void setWater(int i) {
        this.dt_water = i;
    }

    public void setWaterMax(int i) {
        this.dt_waterMax = i;
    }

    public int getFertilizer() {
        return this.dt_fertilizer;
    }

    public void setFertilizer(int i) {
        this.dt_fertilizer = i;
    }

    public void setFertilizerMax(int i) {
        this.dt_fertilizerMax = i;
    }

    public int getFertilizerId() {
        return this.dt_fertilizerId;
    }

    public int getFertilizerMeta() {
        return this.dt_fertilizerMeta;
    }

    public int getWaterMax() {
        return this.dt_waterMax;
    }

    public int getFertilizerMax() {
        return this.dt_fertilizerMax;
    }

    public Item getSoilId() {
        if (this.dt_soilId != null) {
            return this.dt_soilId.func_77973_b();
        }
        return null;
    }

    public void addWater(int w) {
        if (this.dt_water + w >= this.dt_waterMax) {
            this.dt_water = this.dt_waterMax;
        } else {
            this.dt_water += w;
        }
    }

    public void init() {
        this.dt_water = 0;
        this.dt_waterMax = 0;
        this.dt_fertilizer = 0;
        this.dt_fertilizerMax = 0;
        this.dt_fertilizerId = 0;
        this.dt_fertilizerMeta = 0;
        this.dt_soilId = null;
    }

    public int nowWater() {
        return this.water;
    }

    public ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void sendItemInfo(ecru_TileEntityPlanter tileEntity) {
        if (!this.field_145850_b.field_72995_K) {
            int x = tileEntity.field_145851_c;
            int y = tileEntity.field_145848_d;
            int z = tileEntity.field_145849_e;
            if (!this.field_145850_b.field_72995_K) {
                ecru_PacketHandler.network.sendToAll(new ecru_PacketPlanter(x, y, z, this.dt_water, this.dt_waterMax, this.dt_fertilizer, this.dt_fertilizerMax));
            }
        }
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        ItemStack is = getItem(0);
        ItemStack water = getItem(1);
        ItemStack fertilizer = getItem(2);
        if (is != null) {
            int num = this.blockInfo.getNum(is.func_77973_b());
            this.fieldNum = num < 0 ? 0 : num;
        } else {
            this.fieldNum = 0;
        }
        this.dt_waterMax = this.blockInfo.blockWateMax[0][this.fieldNum];
        this.dt_fertilizerMax = this.blockInfo.blockFertilizerMax[0][this.fieldNum];
        if (this.fieldNum == 13) {
            this.dt_soilId = new ItemStack(this.blockInfo.blockId[0][this.fieldNum], 1, 0);
        } else {
            this.dt_soilId = new ItemStack(Block.func_149634_a(this.blockInfo.blockId[0][this.fieldNum]), 1, 0);
        }
        world.func_72921_c(i, j, k, this.fieldNum, 3);
        if (water != null && water.func_77973_b() == Items.field_151131_as) {
            this.dt_water += this.blockInfo.waterBucket;
            if (water.field_77994_a >= 2) {
                water.func_77979_a(1);
                if (water.field_77994_a == 0) {
                    deleteItem(1);
                }
                EntityItem ei = new EntityItem(world, i + 0.1d, j + 0.3d, k + 0.1d, new ItemStack(Items.field_151133_ar, 1, 0));
                world.func_72838_d(ei);
            } else {
                setItem(new ItemStack(Items.field_151133_ar, 1, 0), 1);
            }
        }
        if (fertilizer != null) {
            ecru_IdList ecru_idlist = this.blockInfo;
            fertilizer.func_77973_b();
            if (ecru_idlist.getFertilizer(Item.func_150891_b(fertilizer.func_77973_b()), fertilizer.func_77960_j()) > 0) {
                fertilizer.func_77973_b();
                if (Item.func_150891_b(fertilizer.func_77973_b()) != this.dt_fertilizerId || fertilizer.func_77960_j() != this.dt_fertilizerMeta) {
                    this.dt_fertilizer = 0;
                }
                if (this.dt_fertilizer + this.blockInfo.fertilizer1set <= this.dt_fertilizerMax) {
                    this.dt_fertilizer += this.blockInfo.fertilizer1set;
                    fertilizer.func_77979_a(1);
                    if (fertilizer.field_77994_a == 0) {
                        deleteItem(2);
                    }
                    fertilizer.func_77973_b();
                    this.dt_fertilizerId = Item.func_150891_b(fertilizer.func_77973_b());
                    this.dt_fertilizerMeta = fertilizer.func_77960_j();
                }
            }
        }
        if (this.dt_water > this.dt_waterMax) {
            this.dt_water = this.dt_waterMax;
        }
        if (this.dt_fertilizer > this.dt_fertilizerMax) {
            this.dt_fertilizer = this.dt_fertilizerMax;
        }
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        if (this.dt_water > 0) {
            this.dt_water--;
        }
        int y = j;
        while (true) {
            y++;
            if (world.func_147439_a(i, y, k) != Blocks.field_150350_a && y <= j + 5) {
                Block id = world.func_147439_a(i, y, k);
                if (id != mod_ecru_MapleTree.blockOreFlowerRed && id != mod_ecru_MapleTree.blockOreFlowerIron && id != mod_ecru_MapleTree.blockOreFlowerGold) {
                    y = j + 1;
                    break;
                }
            } else {
                break;
            }
        }
        if (world.func_72896_J() && (world.func_72951_B(i, j + (y - j), k) || world.func_72951_B(i - 1, j + 1, k) || world.func_72951_B(i + 1, j + 1, k) || world.func_72951_B(i, j + 1, k - 1) || world.func_72951_B(i, j + 1, k + 1))) {
            this.dt_water += 2;
            if (this.dt_water > this.dt_waterMax) {
                this.dt_water = this.dt_waterMax;
            }
        }
        sendItemInfo(this);
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
        return func_94041_b(slot, itemStack);
    }

    public boolean func_102008_b(int slot, ItemStack itemStack, int par3) {
        return func_94041_b(slot, itemStack);
    }
}
