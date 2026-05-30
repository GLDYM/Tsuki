package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerPlanter extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityPlanter planter;
    private int last_dt_water = 0;
    private int last_dt_fertilizer = 0;
    private int last_dt_waterMax = 0;
    private int last_dt_fertilizerMax = 0;
    private int last_dt_fertilizerId = 0;
    private int last_dt_fertilizerMeta = 0;

    public ecru_ContainerPlanter(EntityPlayer player, ecru_TileEntityPlanter par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.planter = par2TileEntity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 0 + 84 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 0 + 142));
        }
        func_75146_a(new Slot(par2TileEntity, 0, 8, 0 + 52));
        func_75146_a(new Slot(par2TileEntity, 1, 55, 0 + 5));
        func_75146_a(new Slot(par2TileEntity, 2, 55, 0 + 26));
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockPlanter && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.planter.dt_water);
        par1ICrafting.func_71112_a(this, 1, this.planter.dt_fertilizer);
        par1ICrafting.func_71112_a(this, 2, this.planter.dt_waterMax);
        par1ICrafting.func_71112_a(this, 3, this.planter.dt_fertilizerMax);
        par1ICrafting.func_71112_a(this, 4, this.planter.dt_fertilizerId);
        par1ICrafting.func_71112_a(this, 5, this.planter.dt_fertilizerMeta);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_dt_water != this.planter.dt_water) {
                icrafting.func_71112_a(this, 0, this.planter.dt_water);
            }
            if (this.last_dt_fertilizer != this.planter.dt_fertilizer) {
                icrafting.func_71112_a(this, 1, this.planter.dt_fertilizer);
            }
            if (this.last_dt_waterMax != this.planter.dt_waterMax) {
                icrafting.func_71112_a(this, 2, this.planter.dt_waterMax);
            }
            if (this.last_dt_fertilizerMax != this.planter.dt_fertilizerMax) {
                icrafting.func_71112_a(this, 3, this.planter.dt_fertilizerMax);
            }
            if (this.last_dt_fertilizerId != this.planter.dt_fertilizerId) {
                icrafting.func_71112_a(this, 4, this.planter.dt_fertilizerId);
            }
            if (this.last_dt_fertilizerMeta != this.planter.dt_fertilizerMeta) {
                icrafting.func_71112_a(this, 5, this.planter.dt_fertilizerMeta);
            }
        }
        this.last_dt_water = this.planter.dt_water;
        this.last_dt_fertilizer = this.planter.dt_fertilizer;
        this.last_dt_waterMax = this.planter.dt_waterMax;
        this.last_dt_fertilizerMax = this.planter.dt_fertilizerMax;
        this.last_dt_fertilizerId = this.planter.dt_fertilizerId;
        this.last_dt_fertilizerMeta = this.planter.dt_fertilizerMeta;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.planter.dt_water = par2;
        }
        if (par1 == 1) {
            this.planter.dt_fertilizer = par2;
        }
        if (par1 == 2) {
            this.planter.dt_waterMax = par2;
        }
        if (par1 == 3) {
            this.planter.dt_fertilizerMax = par2;
        }
        if (par1 == 4) {
            this.planter.dt_fertilizerId = par2;
        }
        if (par1 == 5) {
            this.planter.dt_fertilizerMeta = par2;
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 > 35) {
                if (!func_75135_a(itemstack1, 0, 36, false)) {
                    return null;
                }
            } else if (itemstack1.func_77973_b() == Items.field_151131_as) {
                if (!func_75135_a(itemstack1, 37, 38, false)) {
                    return null;
                }
            } else if (!func_75135_a(itemstack1, 36, 37, false)) {
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
}
