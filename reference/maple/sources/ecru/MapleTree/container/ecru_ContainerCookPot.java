package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityCookPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerCookPot extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityCookPot CookPot;
    private int last_dt_water = 0;
    private int last_dt_power = 0;
    private int last_dt_powerCount = 0;
    private int last_dt_cookingTime = 0;
    private int last_dt_cookingTimeMax = 0;
    private int last_dt_waterType = 0;

    public ecru_ContainerCookPot(EntityPlayer player, ecru_TileEntityCookPot par2TileEntity, World world, int x, int y, int z) {
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.CookPot = par2TileEntity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 0 + 84 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 0 + 142));
        }
        func_75146_a(new Slot(par2TileEntity, 0, 14, 0 + 46));
        func_75146_a(new Slot(par2TileEntity, 1, 35, 0 + 46));
        func_75146_a(new Slot(par2TileEntity, 2, 56, 0 + 46));
        func_75146_a(new Slot(par2TileEntity, 3, 77, 0 + 46));
        func_75146_a(new Slot(par2TileEntity, 4, 98, 0 + 46));
        func_75146_a(new ecru_SlotCookPot(player, par2TileEntity, 5, 58, 0 + 12));
        func_75146_a(new Slot(par2TileEntity, 6, 123, 0 + 55));
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockCookPot && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.CookPot.dt_water);
        par1ICrafting.func_71112_a(this, 1, this.CookPot.getPower());
        par1ICrafting.func_71112_a(this, 2, this.CookPot.getCookingTime());
        par1ICrafting.func_71112_a(this, 3, this.CookPot.getPowerCount());
        par1ICrafting.func_71112_a(this, 4, this.CookPot.getCookingTimeMax());
        par1ICrafting.func_71112_a(this, 5, this.CookPot.getWaterType());
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_dt_water != this.CookPot.dt_water) {
                icrafting.func_71112_a(this, 0, this.CookPot.dt_water);
            }
            if (this.last_dt_power != this.CookPot.getPower()) {
                icrafting.func_71112_a(this, 1, this.CookPot.getPower());
            }
            if (this.last_dt_cookingTime != this.CookPot.getCookingTime()) {
                icrafting.func_71112_a(this, 2, this.CookPot.getCookingTime());
            }
            if (this.last_dt_powerCount != this.CookPot.getPowerCount()) {
                icrafting.func_71112_a(this, 3, this.CookPot.getPowerCount());
            }
            if (this.last_dt_cookingTimeMax != this.CookPot.getCookingTimeMax()) {
                icrafting.func_71112_a(this, 4, this.CookPot.getCookingTimeMax());
            }
            if (this.last_dt_waterType != this.CookPot.getWaterType()) {
                icrafting.func_71112_a(this, 5, this.CookPot.getWaterType());
            }
        }
        this.last_dt_water = this.CookPot.dt_water;
        this.last_dt_power = this.CookPot.getPower();
        this.last_dt_cookingTime = this.CookPot.getCookingTime();
        this.last_dt_powerCount = this.CookPot.getPowerCount();
        this.last_dt_cookingTimeMax = this.CookPot.getCookingTimeMax();
        this.last_dt_waterType = this.CookPot.getWaterType();
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.CookPot.dt_water = par2;
        }
        if (par1 == 1) {
            this.CookPot.setPower(par2);
        }
        if (par1 == 2) {
            this.CookPot.setCookingTime(par2);
        }
        if (par1 == 3) {
            this.CookPot.setPowerCount(par2);
        }
        if (par1 == 4) {
            this.CookPot.setCookingTimeMax(par2);
        }
        if (par1 == 5) {
            this.CookPot.setWaterType(par2);
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
            } else if (itemstack1.func_77973_b() == Items.field_151131_as || (itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_normalItem && itemstack1.func_77960_j() == 0)) {
                if (!func_75135_a(itemstack1, 42, 43, false)) {
                    return null;
                }
            } else if (!func_75135_a(itemstack1, 36, 41, false)) {
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
