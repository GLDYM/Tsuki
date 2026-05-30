package ecru.MapleTree.entity.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ecru_ContainerMomiji extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_EntityMomiji entityMomiji;
    public int last_stateBonusAttackLv = 0;
    public int last_stateBonusDefenseLv = 0;
    public int last_stateBonusSpeedLv = 0;
    public int last_momijiLv = 0;
    public int last_bonusPoint = 0;
    public int last_expPoint = 0;
    public int last_attackDamage = 0;
    public int last_fishingLv = 0;
    public int last_fishingCount = 0;

    public ecru_ContainerMomiji(EntityPlayer player, ecru_EntityMomiji entity) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.entityMomiji = entity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 0 + 162 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 0 + 220));
        }
        for (int slotIndex3 = 0; slotIndex3 < 3; slotIndex3++) {
            func_75146_a(new Slot(entity, slotIndex3, 8 + (slotIndex3 * 18), 0 + 84));
        }
        for (int rows2 = 0; rows2 < 2; rows2++) {
            for (int slotIndex4 = 0; slotIndex4 < 9; slotIndex4++) {
                func_75146_a(new Slot(entity, slotIndex4 + (rows2 * 9) + 3, 8 + (slotIndex4 * 18), 0 + 113 + (rows2 * 18)));
            }
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return !this.entityMomiji.field_70128_L && this.player.func_70068_e(this.entityMomiji) <= 64.0d;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < 36) {
                if ((itemstack1.func_77973_b() instanceof ItemShears) || (itemstack1.func_77973_b() instanceof ItemHoe) || (itemstack1.func_77973_b() instanceof ItemAxe) || (itemstack1.func_77973_b() instanceof ItemSword) || (itemstack1.func_77973_b() instanceof ItemFishingRod) || itemstack1.func_77973_b() == Items.field_151117_aB || itemstack1.func_77973_b() == Items.field_151111_aL) {
                    if (!func_75135_a(itemstack1, 36, 39, false)) {
                        return null;
                    }
                } else if (!func_75135_a(itemstack1, 39, 57, false)) {
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
        par1ICrafting.func_71112_a(this, 0, this.entityMomiji.stateBonusAttackLv);
        par1ICrafting.func_71112_a(this, 1, this.entityMomiji.stateBonusDefenseLv);
        par1ICrafting.func_71112_a(this, 2, this.entityMomiji.stateBonusSpeedLv);
        par1ICrafting.func_71112_a(this, 3, this.entityMomiji.momijiLv);
        par1ICrafting.func_71112_a(this, 4, this.entityMomiji.bonusPoint);
        par1ICrafting.func_71112_a(this, 5, this.entityMomiji.expPoint);
        par1ICrafting.func_71112_a(this, 6, this.entityMomiji.fishingLv);
        par1ICrafting.func_71112_a(this, 7, this.entityMomiji.fishingCount);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_stateBonusAttackLv != this.entityMomiji.stateBonusAttackLv) {
                icrafting.func_71112_a(this, 0, this.entityMomiji.stateBonusAttackLv);
            }
            if (this.last_stateBonusDefenseLv != this.entityMomiji.stateBonusDefenseLv) {
                icrafting.func_71112_a(this, 1, this.entityMomiji.stateBonusDefenseLv);
            }
            if (this.last_stateBonusSpeedLv != this.entityMomiji.stateBonusSpeedLv) {
                icrafting.func_71112_a(this, 2, this.entityMomiji.stateBonusSpeedLv);
            }
            if (this.last_momijiLv != this.entityMomiji.momijiLv) {
                icrafting.func_71112_a(this, 3, this.entityMomiji.momijiLv);
            }
            if (this.last_bonusPoint != this.entityMomiji.bonusPoint) {
                icrafting.func_71112_a(this, 4, this.entityMomiji.bonusPoint);
            }
            if (this.last_expPoint != this.entityMomiji.expPoint) {
                icrafting.func_71112_a(this, 5, this.entityMomiji.expPoint);
            }
            if (this.last_fishingLv != this.entityMomiji.fishingLv) {
                icrafting.func_71112_a(this, 6, this.entityMomiji.fishingLv);
            }
            if (this.last_fishingCount != this.entityMomiji.fishingCount) {
                icrafting.func_71112_a(this, 7, this.entityMomiji.fishingCount);
            }
        }
        this.last_stateBonusAttackLv = this.entityMomiji.stateBonusAttackLv;
        this.last_stateBonusDefenseLv = this.entityMomiji.stateBonusDefenseLv;
        this.last_stateBonusSpeedLv = this.entityMomiji.stateBonusSpeedLv;
        this.last_momijiLv = this.entityMomiji.momijiLv;
        this.last_bonusPoint = this.entityMomiji.bonusPoint;
        this.last_expPoint = this.entityMomiji.expPoint;
        this.last_fishingLv = this.entityMomiji.fishingLv;
        this.last_fishingCount = this.entityMomiji.fishingCount;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.entityMomiji.stateBonusAttackLv = par2;
        }
        if (par1 == 1) {
            this.entityMomiji.stateBonusDefenseLv = par2;
        }
        if (par1 == 2) {
            this.entityMomiji.stateBonusSpeedLv = par2;
        }
        if (par1 == 3) {
            this.entityMomiji.momijiLv = par2;
        }
        if (par1 == 4) {
            this.entityMomiji.bonusPoint = par2;
        }
        if (par1 == 5) {
            this.entityMomiji.expPoint = par2;
        }
        if (par1 == 6) {
            this.entityMomiji.fishingLv = par2;
        }
        if (par1 == 7) {
            this.entityMomiji.fishingCount = par2;
        }
    }
}
