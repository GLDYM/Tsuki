package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketGrainHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class ecru_TileEntityGrainHopper extends TileEntity implements IInventory {
    private long sTime;
    private long nTime;
    public int dt_itemNum = 0;
    public int dt_itemNumMax = 0;
    private ItemStack[] itemStacks = new ItemStack[104];

    public int func_70302_i_() {
        return this.itemStacks.length;
    }

    public ItemStack func_70301_a(int var1) {
        return this.itemStacks[var1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        setSlotInfo();
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
        setSlotInfo();
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
        setSlotInfo();
        func_70296_d();
    }

    public String func_145825_b() {
        return "MapleGrainHopper";
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
        this.dt_itemNum = nbttagcompound.func_74762_e("dt_itemNum");
        this.dt_itemNumMax = nbttagcompound.func_74762_e("dt_itemNumMax");
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
        nbttagcompound.func_74768_a("dt_itemNum", this.dt_itemNum);
        nbttagcompound.func_74768_a("dt_itemNumMax", this.dt_itemNumMax);
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

    public Packet func_145844_m() {
        setSlotInfo();
        sendPacket(this);
        return null;
    }

    public void deleteItem(int i) {
        this.itemStacks[i] = null;
    }

    public void setItem(ItemStack is, int slot) {
        func_70299_a(slot, is);
    }

    public ItemStack getItem(int slot) {
        return func_70301_a(slot);
    }

    public void func_145845_h() {
    }

    public int getSlotSize() {
        int num = 0;
        for (int i = 0; i < func_70302_i_(); i++) {
            if (this.itemStacks[i] != null) {
                num++;
            }
        }
        return num;
    }

    private void sendPacket(ecru_TileEntityGrainHopper tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        setSlotInfo();
        if (!this.field_145850_b.field_72995_K) {
            setSlotInfo();
            ecru_PacketHandler.network.sendToAll(new ecru_PacketGrainHopper(x, y, z, this.dt_itemNum, this.dt_itemNumMax));
        }
    }

    public void setSlotInfo() {
        this.dt_itemNum = getSlotSize();
        this.dt_itemNumMax = func_70302_i_();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}
