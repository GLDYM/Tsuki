package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@ChestContainer(isLargeChest = true, rowSize = 13)
public class ecru_ContainerGrainHopper extends Container {
    ecru_TileEntityGrainHopper tile;
    private int last_dt_itemNum = 0;
    private int last_dt_itemNumMax = 0;
    private int rowsMax = 8;
    private int colsMax = 13;
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    ecru_TileEntityGrainHopper entity;

    public ecru_ContainerGrainHopper(EntityPlayer player, ecru_TileEntityGrainHopper par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.tile = par2TileEntity;
        for (int rows = 0; rows < this.rowsMax; rows++) {
            for (int slotIndex = 0; slotIndex < this.colsMax; slotIndex++) {
                func_75146_a(new Slot(par2TileEntity, slotIndex + (rows * this.colsMax), 12 + (slotIndex * 18), 11 + (rows * 18)));
            }
        }
        for (int rows2 = 0; rows2 < 3; rows2++) {
            for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex2 + (rows2 * 9) + 9, 48 + (slotIndex2 * 18), 56 + 104 + (rows2 * 18)));
            }
        }
        for (int slotIndex3 = 0; slotIndex3 < 9; slotIndex3++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex3, 48 + (slotIndex3 * 18), 56 + 162));
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < this.rowsMax * this.colsMax) {
                if (!func_75135_a(itemstack1, this.rowsMax * this.colsMax, this.field_75151_b.size(), true)) {
                    return null;
                }
            } else if (!func_75135_a(itemstack1, 0, this.rowsMax * this.colsMax, false)) {
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

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockGrainHopper && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.tile.dt_itemNum);
        par1ICrafting.func_71112_a(this, 1, this.tile.dt_itemNumMax);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_dt_itemNum != this.tile.dt_itemNum) {
                icrafting.func_71112_a(this, 0, this.tile.dt_itemNum);
            }
            if (this.last_dt_itemNumMax != this.tile.dt_itemNumMax) {
                icrafting.func_71112_a(this, 1, this.tile.dt_itemNumMax);
            }
        }
        this.last_dt_itemNum = this.tile.dt_itemNum;
        this.last_dt_itemNumMax = this.tile.dt_itemNumMax;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.dt_itemNum = par2;
        }
        if (par1 == 1) {
            this.tile.dt_itemNumMax = par2;
        }
    }
}
