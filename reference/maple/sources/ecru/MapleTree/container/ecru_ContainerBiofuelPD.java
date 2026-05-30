package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerBiofuelPD extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityBiofuelPD tile;
    public int last_dt_convert1 = 0;
    public int last_dt_convert2 = 0;
    public int last_dt_tank1 = 0;
    public int last_dt_tank2 = 0;
    public int last_dt_extraction = 0;

    public ecru_ContainerBiofuelPD(EntityPlayer player, ecru_TileEntityBiofuelPD par2TileEntity, World world, int x, int y, int z) {
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.tile = par2TileEntity;
        func_75146_a(new Slot(par2TileEntity, 0, 12, 9));
        func_75146_a(new Slot(par2TileEntity, 1, 132, 60));
        func_75146_a(new ecru_SlotBiofuelPD(player, par2TileEntity, 2, 132, 13));
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 0 + 84 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 0 + 142));
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
            if (par2 < 3) {
                if (!func_75135_a(itemstack1, 3, this.field_75151_b.size(), false)) {
                    return null;
                }
            } else if (!func_75135_a(itemstack1, 0, 1, true)) {
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
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockBiofuelPD && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.tile.dt_convert1);
        par1ICrafting.func_71112_a(this, 1, this.tile.dt_convert2);
        par1ICrafting.func_71112_a(this, 2, this.tile.dt_tank1);
        par1ICrafting.func_71112_a(this, 3, this.tile.dt_tank2);
        par1ICrafting.func_71112_a(this, 4, this.tile.dt_extraction);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_dt_convert1 != this.tile.dt_convert1) {
                icrafting.func_71112_a(this, 0, this.tile.dt_convert1);
            }
            if (this.last_dt_convert2 != this.tile.dt_convert2) {
                icrafting.func_71112_a(this, 1, this.tile.dt_convert2);
            }
            if (this.last_dt_tank1 != this.tile.dt_tank1) {
                icrafting.func_71112_a(this, 2, this.tile.dt_tank1);
            }
            if (this.last_dt_tank2 != this.tile.dt_tank2) {
                icrafting.func_71112_a(this, 3, this.tile.dt_tank2);
            }
            if (this.last_dt_extraction != this.tile.dt_extraction) {
                icrafting.func_71112_a(this, 4, this.tile.dt_extraction);
            }
        }
        this.last_dt_convert1 = this.tile.dt_convert1;
        this.last_dt_convert2 = this.tile.dt_convert2;
        this.last_dt_tank1 = this.tile.dt_tank1;
        this.last_dt_tank2 = this.tile.dt_tank2;
        this.last_dt_extraction = this.tile.dt_extraction;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.dt_convert1 = par2;
        }
        if (par1 == 1) {
            this.tile.dt_convert2 = par2;
        }
        if (par1 == 2) {
            this.tile.dt_tank1 = par2;
        }
        if (par1 == 3) {
            this.tile.dt_tank2 = par2;
        }
        if (par1 == 4) {
            this.tile.dt_extraction = par2;
        }
    }
}
