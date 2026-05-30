package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerSLight extends Container {
    private World sl_world;
    private int sl_xCoord;
    private int sl_yCoord;
    private int sl_zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntitySLight tile;
    private int last_S_LiColor = 0;
    private int last_S_LiLength = 0;
    private int last_S_LiWidth = 0;
    private int last_S_LiTransparency = 0;
    private int last_S_LiColorRR = 0;
    private int last_S_LiColorGG = 0;
    private int last_S_LiColorBB = 0;
    private int cl_co = Integer.MIN_VALUE;
    private int cl_le = Integer.MIN_VALUE;
    private int cl_wi = Integer.MIN_VALUE;
    private int cl_tr = Integer.MIN_VALUE;
    private int cl_co_RR = 0;
    private int cl_co_GG = 0;
    private int cl_co_BB = 0;

    public ecru_ContainerSLight(EntityPlayer player, ecru_TileEntitySLight par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.sl_world = world;
        this.sl_xCoord = x;
        this.sl_yCoord = y;
        this.sl_zCoord = z;
        this.tile = par2TileEntity;
        for (int rows = 0; rows < 3; rows++) {
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                func_75146_a(new Slot(this.playerInventory, slotIndex + (rows * 9) + 9, 8 + (slotIndex * 18), 74 + 84 + (rows * 18)));
            }
        }
        for (int slotIndex2 = 0; slotIndex2 < 9; slotIndex2++) {
            func_75146_a(new Slot(this.playerInventory, slotIndex2, 8 + (slotIndex2 * 18), 74 + 142));
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return (this.sl_world.func_147439_a(this.sl_xCoord, this.sl_yCoord, this.sl_zCoord) == mod_ecru_MapleTree.blockSLight || this.sl_world.func_147439_a(this.sl_xCoord, this.sl_yCoord, this.sl_zCoord) == mod_ecru_MapleTree.blockSLight2) && entityPlayer.func_70092_e(((double) this.sl_xCoord) + 0.5d, ((double) this.sl_yCoord) + 0.5d, ((double) this.sl_zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.tile.S_LiColor);
        par1ICrafting.func_71112_a(this, 1, this.tile.S_LiLength);
        par1ICrafting.func_71112_a(this, 2, this.tile.S_LiWidth);
        par1ICrafting.func_71112_a(this, 3, this.tile.S_LiTransparency);
        par1ICrafting.func_71112_a(this, 4, this.tile.S_LiColorRR);
        par1ICrafting.func_71112_a(this, 5, this.tile.S_LiColorGG);
        par1ICrafting.func_71112_a(this, 6, this.tile.S_LiColorBB);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_S_LiColor != this.tile.S_LiColor) {
                icrafting.func_71112_a(this, 0, this.tile.S_LiColor);
            }
            if (this.last_S_LiLength != this.tile.S_LiLength) {
                icrafting.func_71112_a(this, 1, this.tile.S_LiLength);
            }
            if (this.last_S_LiWidth != this.tile.S_LiWidth) {
                icrafting.func_71112_a(this, 2, this.tile.S_LiWidth);
            }
            if (this.last_S_LiTransparency != this.tile.S_LiTransparency) {
                icrafting.func_71112_a(this, 3, this.tile.S_LiTransparency);
            }
            if (this.last_S_LiColorRR != this.tile.S_LiColorRR) {
                icrafting.func_71112_a(this, 4, this.tile.S_LiColorRR);
            }
            if (this.last_S_LiColorGG != this.tile.S_LiColorGG) {
                icrafting.func_71112_a(this, 5, this.tile.S_LiColorGG);
            }
            if (this.last_S_LiColorBB != this.tile.S_LiColorBB) {
                icrafting.func_71112_a(this, 6, this.tile.S_LiColorBB);
            }
        }
        this.last_S_LiColor = this.tile.S_LiColor;
        this.last_S_LiLength = this.tile.S_LiLength;
        this.last_S_LiWidth = this.tile.S_LiWidth;
        this.last_S_LiTransparency = this.tile.S_LiTransparency;
        this.last_S_LiColorRR = this.tile.S_LiColorRR;
        this.last_S_LiColorGG = this.tile.S_LiColorGG;
        this.last_S_LiColorBB = this.tile.S_LiColorBB;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.S_LiColor = par2;
        }
        if (par1 == 1) {
            this.tile.S_LiLength = par2;
        }
        if (par1 == 2) {
            this.tile.S_LiWidth = par2;
        }
        if (par1 == 3) {
            this.tile.S_LiTransparency = par2;
        }
        if (par1 == 4) {
            this.tile.S_LiColorRR = par2;
        }
        if (par1 == 5) {
            this.tile.S_LiColorGG = par2;
        }
        if (par1 == 6) {
            this.tile.S_LiColorBB = par2;
        }
        if (par1 != 0) {
            int tmp = (this.tile.S_LiColorRR << 16) | (this.tile.S_LiColorGG << 8) | this.tile.S_LiColorBB;
            this.tile.S_LiColor = tmp;
        }
    }
}
