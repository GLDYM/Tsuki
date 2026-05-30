package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityMortar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerMortar extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityMortar tile;
    private int last_createCounte;
    private int last_errorCode;
    private int rowsMax = 6;
    private int colsMax = 9;
    private int[] last_onOff = new int[15];
    private int[] last_num = new int[15];
    private int[] last_recipe = new int[2];

    public ecru_ContainerMortar(EntityPlayer player, ecru_TileEntityMortar par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.tile = par2TileEntity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 29 + (slotIndex * 18), 0 + 174 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 29 + (slotIndex2 * 18), 0 + 232));
        }
        int num = 0;
        for (int rows2 = 0; rows2 < 3; rows2++) {
            for (int slotIndex3 = 0; slotIndex3 < 5; slotIndex3++) {
                func_75146_a(new Slot(par2TileEntity, slotIndex3 + (rows2 * 5), 16 + (slotIndex3 * 31), 16 + (rows2 * 49)));
                num++;
            }
        }
        func_75146_a(new ecru_SlotMortar(player, par2TileEntity, 15, 190, 0 + 64));
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockMortar && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < 36) {
                if (itemstack1.func_77973_b() == mod_ecru_MapleTree.Item_SpiceList) {
                    int meta = itemstack1.func_77960_j();
                    int meta2 = (meta < 0 || meta > 12) ? -1 : meta;
                    if (meta2 != -1) {
                        if (!func_75135_a(itemstack1, 36 + meta2, 36 + meta2 + 1, false)) {
                            return null;
                        }
                    } else if (!func_75135_a(itemstack1, 36, 51, false)) {
                        return null;
                    }
                } else if (!func_75135_a(itemstack1, 36, 51, false)) {
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
        for (int i = 0; i < 15; i++) {
            par1ICrafting.func_71112_a(this, i, this.tile.onOff[i]);
        }
        for (int i2 = 0; i2 < 15; i2++) {
            par1ICrafting.func_71112_a(this, i2 + 15, this.tile.num[i2]);
        }
        par1ICrafting.func_71112_a(this, 50, this.tile.createCounte);
        par1ICrafting.func_71112_a(this, 51, this.tile.errorCode);
        par1ICrafting.func_71112_a(this, 40, this.tile.recipe[0]);
        par1ICrafting.func_71112_a(this, 41, this.tile.recipe[1]);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int j = 0; j < this.field_75149_d.size(); j++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(j);
            for (int i = 0; i < 15; i++) {
                if (this.last_onOff[i] != this.tile.onOff[i]) {
                    icrafting.func_71112_a(this, i, this.tile.onOff[i]);
                }
                this.last_onOff[i] = this.tile.onOff[i];
            }
            for (int i2 = 0; i2 < 15; i2++) {
                if (this.last_num[i2] != this.tile.num[i2]) {
                    icrafting.func_71112_a(this, i2 + 15, this.tile.num[i2]);
                }
                this.last_num[i2] = this.tile.num[i2];
            }
            if (this.last_createCounte != this.tile.createCounte) {
                icrafting.func_71112_a(this, 50, this.tile.createCounte);
            }
            this.last_createCounte = this.tile.createCounte;
            if (this.last_errorCode != this.tile.errorCode) {
                icrafting.func_71112_a(this, 51, this.tile.errorCode);
            }
            this.last_errorCode = this.tile.errorCode;
            if (this.last_recipe[0] != this.tile.recipe[0]) {
                icrafting.func_71112_a(this, 40, this.tile.recipe[0]);
            }
            this.last_recipe[0] = this.tile.recipe[0];
            if (this.last_recipe[1] != this.tile.recipe[1]) {
                icrafting.func_71112_a(this, 41, this.tile.recipe[1]);
            }
            this.last_recipe[1] = this.tile.recipe[1];
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        for (int i = 0; i < 15; i++) {
            if (par1 == i) {
                this.tile.onOff[i] = (byte) par2;
            }
        }
        for (int i2 = 0; i2 < 15; i2++) {
            if (par1 == i2 + 15) {
                this.tile.num[i2] = (byte) par2;
            }
        }
        if (par1 == 50) {
            this.tile.createCounte = par2;
        }
        if (par1 == 51) {
            this.tile.errorCode = par2;
        }
        if (par1 == 40) {
            this.tile.recipe[0] = par2;
        }
        if (par1 == 41) {
            this.tile.recipe[1] = par2;
        }
    }
}
