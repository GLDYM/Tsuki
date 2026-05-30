package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketWineBarrel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityWineBarrel extends TileEntity implements IInventory {
    private int wineQuantity;
    private int wineFerment;
    public int extraction;
    private ItemStack[] itemStacks = new ItemStack[2];
    private long TIMERCOUNT = 60;
    private long timer = this.TIMERCOUNT;
    public long data1 = 1;
    public int data2 = 2;
    public int data3 = 3;
    int BOTTLE_CAPACITY = 1000;
    int flg = 0;
    private int wineQuantityMax = 225000;
    private int wineFermentMax = 72000;
    private int animeCounter = 0;
    public int extractionMax = 100;

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

    public String func_145825_b() {
        return "WineBarrel";
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

    public int func_70297_j_() {
        return 64;
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void sendItemInfo(ecru_TileEntityWineBarrel tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        if (!this.field_145850_b.field_72995_K) {
            ecru_PacketHandler.network.sendToAll(new ecru_PacketWineBarrel(x, y, z, this.wineQuantity, this.wineFerment, this.extraction));
        }
    }

    public void func_70296_d() {
        super.func_70296_d();
    }

    public int getAnimeCounter() {
        return this.animeCounter;
    }

    public int getWineQuantity() {
        return this.wineQuantity;
    }

    public int getWineQuantityMax() {
        return this.wineQuantityMax;
    }

    public void setWineQuantity(int n) {
        int old = this.wineQuantity;
        if (n <= this.wineQuantityMax) {
            this.wineQuantity = n;
        } else {
            this.wineQuantity = this.wineQuantityMax;
        }
        if (this.wineQuantity > old) {
            this.wineFerment = 0;
        }
        sendItemInfo(this);
    }

    public void addWineQuantity(int s) {
        int old = this.wineQuantity;
        if (this.wineQuantity + s <= this.wineQuantityMax) {
            this.wineQuantity += s;
        } else {
            this.wineQuantity = this.wineQuantityMax;
        }
        if (this.wineQuantity > old) {
            this.wineFerment = 0;
        }
        sendItemInfo(this);
    }

    public boolean subWineQuantity(int s) {
        if (this.wineQuantity - s >= 0) {
            this.wineQuantity -= s;
            return true;
        }
        return false;
    }

    public int getWineFerment() {
        return this.wineFerment;
    }

    public int getWineFermentMax() {
        return this.wineFermentMax;
    }

    public void setWineFerment(int n) {
        if (n <= this.wineFermentMax) {
            this.wineFerment = n;
        } else {
            this.wineFerment = this.wineFermentMax;
        }
    }

    public void addWineFerment(int n) {
        if (this.wineFerment + n <= this.wineFermentMax) {
            this.wineFerment += n;
        } else {
            this.wineFerment = this.wineFermentMax;
        }
    }

    public int getEextraction() {
        return this.extraction;
    }

    public int getEextractionMax() {
        return this.extractionMax;
    }

    public void setEextraction(int n) {
        if (n > this.extractionMax) {
            this.extraction = this.extractionMax;
        } else if (n < 0) {
            this.extraction = 0;
        } else {
            this.extraction = n;
        }
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.wineQuantity = nbttagcompound.func_74762_e("wineQuantity");
        this.wineFerment = nbttagcompound.func_74762_e("wineFerment");
        this.extraction = nbttagcompound.func_74762_e("extraction");
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
        nbttagcompound.func_74768_a("wineQuantity", this.wineQuantity);
        nbttagcompound.func_74768_a("wineFerment", this.wineFerment);
        nbttagcompound.func_74768_a("extraction", this.extraction);
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

    private ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    private void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public void setItem(ItemStack is, int slot) {
        func_70299_a(slot, is);
    }

    public void func_145845_h() {
        if (this.animeCounter < 31) {
            this.animeCounter++;
        } else {
            this.animeCounter = 0;
        }
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        ItemStack item0 = getItem(0);
        ItemStack item1 = getItem(1);
        if (getWineFerment() >= getWineFermentMax() && item0 != null && item0.func_77973_b() == mod_ecru_MapleTree.Item_normalItem && item0.func_77960_j() == 4 && getWineQuantity() >= this.BOTTLE_CAPACITY && ((item1 != null && item1.field_77994_a < 64 && item1.func_77973_b() == mod_ecru_MapleTree.Item_alwaysFoods && item1.func_77960_j() == 0) || item1 == null)) {
            int i2 = this.extraction;
            this.extraction = i2 + 1;
            if (i2 >= this.extractionMax) {
                this.extraction = 0;
                subWineQuantity(this.BOTTLE_CAPACITY);
                item0.func_77979_a(1);
                if (item0.field_77994_a == 0) {
                    deleteItem(0);
                }
                if (item1 == null) {
                    setItem(new ItemStack(mod_ecru_MapleTree.Item_alwaysFoods, 1, 0), 1);
                } else {
                    item1.func_77979_a(-1);
                }
                sendItemInfo(this);
            }
            this.flg = 1;
        } else {
            this.extraction = 0;
            if (this.flg == 1) {
                sendItemInfo(this);
                this.flg = 0;
            }
        }
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        if (this.wineQuantity > 0) {
            Block up_id = world.func_147439_a(i, j + 1, k);
            if (world.func_72957_l(i, j + 1, k) <= 12 && this.wineFerment < this.wineFermentMax && (up_id != mod_ecru_MapleTree.blockWineFaucet || (world.func_72805_g(i, j, k) & 12) != 0)) {
                addWineFerment((int) this.TIMERCOUNT);
                sendItemInfo(this);
            }
        } else {
            this.wineFerment = 0;
            sendItemInfo(this);
        }
        func_70296_d();
    }
}
