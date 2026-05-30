package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrainDryer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerGrainDryer extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityGrainDryer tile;
    private int last_dt_powerCount;
    private int rowsMax = 6;
    private int colsMax = 9;
    private int last_dt_burnTime = 0;
    private int last_dt_burnTimeMax = 0;
    private int[] last_dt_completion = {0, 0, 0, 0, 0, 0};
    private int last_completionMax = 0;

    public ecru_ContainerGrainDryer(EntityPlayer player, ecru_TileEntityGrainDryer par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.tile = par2TileEntity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 0 + 84 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 0 + 142));
        }
        for (int slotIndex3 = 0; slotIndex3 < 6; slotIndex3++) {
            func_75146_a(new Slot(par2TileEntity, slotIndex3, 38 + (slotIndex3 * 21), 14));
        }
        for (int slotIndex4 = 0; slotIndex4 < 6; slotIndex4++) {
            func_75146_a(new ecru_SlotGrainDryer(player, par2TileEntity, slotIndex4 + 6, 38 + (slotIndex4 * 21), 57));
        }
        func_75146_a(new Slot(par2TileEntity, 12, 8, 62));
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockGrainDryer && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < 36) {
                if (itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_allspiceSeed || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_allspiceSeed || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_cloveBud || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_cinnamonBark || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_star_aniseFruit || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_nutmegSeed || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_cardamonFruit || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_cuminSeed || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_corianderSeed || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_turmericRoot || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_chili_pepperFruit || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_fennelSeed || itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_BoiledBonito || (itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_foodstuff && itemstack1.func_77960_j() == 22)) {
                    if (!func_75135_a(itemstack1, 36, 42, false)) {
                        return null;
                    }
                } else if (!func_75135_a(itemstack1, 48, 49, false)) {
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
        par1ICrafting.func_71112_a(this, 0, this.tile.dt_completion[0]);
        par1ICrafting.func_71112_a(this, 1, this.tile.dt_completion[1]);
        par1ICrafting.func_71112_a(this, 2, this.tile.dt_completion[2]);
        par1ICrafting.func_71112_a(this, 3, this.tile.dt_completion[3]);
        par1ICrafting.func_71112_a(this, 4, this.tile.dt_completion[4]);
        par1ICrafting.func_71112_a(this, 5, this.tile.dt_completion[5]);
        par1ICrafting.func_71112_a(this, 6, this.tile.completionMax);
        par1ICrafting.func_71112_a(this, 7, this.tile.dt_burnTime);
        par1ICrafting.func_71112_a(this, 8, this.tile.dt_burnTimeMax);
        par1ICrafting.func_71112_a(this, 9, this.tile.dt_powerCount);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_dt_completion[0] != this.tile.dt_completion[0]) {
                icrafting.func_71112_a(this, 0, this.tile.dt_completion[0]);
            }
            if (this.last_dt_completion[1] != this.tile.dt_completion[1]) {
                icrafting.func_71112_a(this, 1, this.tile.dt_completion[1]);
            }
            if (this.last_dt_completion[2] != this.tile.dt_completion[2]) {
                icrafting.func_71112_a(this, 2, this.tile.dt_completion[2]);
            }
            if (this.last_dt_completion[3] != this.tile.dt_completion[3]) {
                icrafting.func_71112_a(this, 3, this.tile.dt_completion[3]);
            }
            if (this.last_dt_completion[4] != this.tile.dt_completion[4]) {
                icrafting.func_71112_a(this, 4, this.tile.dt_completion[4]);
            }
            if (this.last_dt_completion[5] != this.tile.dt_completion[5]) {
                icrafting.func_71112_a(this, 5, this.tile.dt_completion[5]);
            }
            if (this.last_completionMax != this.tile.completionMax) {
                icrafting.func_71112_a(this, 6, this.tile.completionMax);
            }
            if (this.last_dt_burnTime != this.tile.dt_burnTime) {
                icrafting.func_71112_a(this, 7, this.tile.dt_burnTime);
            }
            if (this.last_dt_burnTimeMax != this.tile.dt_burnTimeMax) {
                icrafting.func_71112_a(this, 8, this.tile.dt_burnTimeMax);
            }
            if (this.last_dt_powerCount != this.tile.dt_powerCount) {
                icrafting.func_71112_a(this, 9, this.tile.dt_powerCount);
            }
        }
        this.last_dt_completion[0] = this.tile.dt_completion[0];
        this.last_dt_completion[1] = this.tile.dt_completion[1];
        this.last_dt_completion[2] = this.tile.dt_completion[2];
        this.last_dt_completion[3] = this.tile.dt_completion[3];
        this.last_dt_completion[4] = this.tile.dt_completion[4];
        this.last_dt_completion[5] = this.tile.dt_completion[5];
        this.last_completionMax = this.tile.completionMax;
        this.last_dt_burnTime = this.tile.dt_burnTime;
        this.last_dt_burnTimeMax = this.tile.dt_burnTimeMax;
        this.last_dt_powerCount = this.tile.dt_powerCount;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.dt_completion[0] = par2;
        }
        if (par1 == 1) {
            this.tile.dt_completion[1] = par2;
        }
        if (par1 == 2) {
            this.tile.dt_completion[2] = par2;
        }
        if (par1 == 3) {
            this.tile.dt_completion[3] = par2;
        }
        if (par1 == 4) {
            this.tile.dt_completion[4] = par2;
        }
        if (par1 == 5) {
            this.tile.dt_completion[5] = par2;
        }
        if (par1 == 6) {
            this.tile.completionMax = par2;
        }
        if (par1 == 7) {
            this.tile.dt_burnTime = par2;
        }
        if (par1 == 8) {
            this.tile.dt_burnTimeMax = par2;
        }
        if (par1 == 9) {
            this.tile.dt_powerCount = par2;
        }
    }
}
