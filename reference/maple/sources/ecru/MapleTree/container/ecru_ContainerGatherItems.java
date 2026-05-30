package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGatherItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerGatherItems extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityGatherItems tile;
    private int rowsMax = 6;
    private int colsMax = 9;
    private int last_areaSize = 0;
    private int last_updateInterval = 0;
    private int last_onOff = 0;

    public ecru_ContainerGatherItems(EntityPlayer player, ecru_TileEntityGatherItems par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.tile = par2TileEntity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 0 + 158 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 0 + 216));
        }
        int num = 0;
        for (int rows2 = 0; rows2 < 5; rows2++) {
            for (int slotIndex3 = 0; slotIndex3 < 9; slotIndex3++) {
                func_75146_a(new Slot(par2TileEntity, slotIndex3 + (rows2 * 9), 8 + (slotIndex3 * 18), 38 + (rows2 * 18)));
                num++;
            }
        }
        for (int slotIndex4 = 0; slotIndex4 < 9; slotIndex4++) {
            func_75146_a(new Slot(par2TileEntity, slotIndex4 + 45, 8 + (slotIndex4 * 18), 14));
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockGatherItems && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < 36) {
                if (!func_75135_a(itemstack1, 36, 81, false)) {
                    return null;
                }
            } else if (!func_75135_a(itemstack1, 0, 36, false)) {
                return null;
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack) null);
            } else {
                slot.func_75218_e();
            }
        }
        return itemstack;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.tile.areaSize);
        par1ICrafting.func_71112_a(this, 1, this.tile.updateInterval);
        par1ICrafting.func_71112_a(this, 2, this.tile.onOff);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_areaSize != this.tile.areaSize) {
                icrafting.func_71112_a(this, 0, this.tile.areaSize);
            }
            if (this.last_updateInterval != this.tile.updateInterval) {
                icrafting.func_71112_a(this, 1, this.tile.updateInterval);
            }
            if (this.last_onOff != this.tile.onOff) {
                icrafting.func_71112_a(this, 2, this.tile.onOff);
            }
        }
        this.last_areaSize = this.tile.areaSize;
        this.last_updateInterval = this.tile.updateInterval;
        this.last_onOff = this.tile.onOff;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.areaSize = par2;
        }
        if (par1 == 1) {
            this.tile.updateInterval = par2;
        }
        if (par1 == 2) {
            this.tile.onOff = par2;
        }
    }
}
