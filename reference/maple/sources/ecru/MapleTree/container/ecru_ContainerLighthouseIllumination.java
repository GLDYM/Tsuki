package ecru.MapleTree.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIllumination;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_ContainerLighthouseIllumination extends Container {
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private EntityPlayer player;
    private IInventory playerInventory;
    private ecru_TileEntityLighthouseIllumination tile;
    private int last_LiColor = 0;
    private int last_LiLength = 0;
    private int last_LiWidth = 0;
    private int last_LiTransparency = 0;
    private int last_LiColorRR = 0;
    private int last_LiColorGG = 0;
    private int last_LiColorBB = 0;
    private int cl_co = Integer.MIN_VALUE;
    private int cl_le = Integer.MIN_VALUE;
    private int cl_wi = Integer.MIN_VALUE;
    private int cl_tr = Integer.MIN_VALUE;
    private int cl_co_RR = 0;
    private int cl_co_GG = 0;
    private int cl_co_BB = 0;

    public ecru_ContainerLighthouseIllumination(EntityPlayer player, ecru_TileEntityLighthouseIllumination par2TileEntity, World world, int x, int y, int z) {
        this.player = player;
        this.playerInventory = player.field_71071_by;
        this.world = world;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
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
        return this.world.func_147439_a(this.xCoord, this.yCoord, this.zCoord) == mod_ecru_MapleTree.blockLighthouseIllumination && entityPlayer.func_70092_e(((double) this.xCoord) + 0.5d, ((double) this.yCoord) + 0.5d, ((double) this.zCoord) + 0.5d) <= 64.0d;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a(this, 0, this.tile.LiColor);
        par1ICrafting.func_71112_a(this, 1, this.tile.LiLength);
        par1ICrafting.func_71112_a(this, 2, this.tile.LiWidth);
        par1ICrafting.func_71112_a(this, 3, this.tile.LiTransparency);
        par1ICrafting.func_71112_a(this, 4, this.tile.LiColorRR);
        par1ICrafting.func_71112_a(this, 5, this.tile.LiColorGG);
        par1ICrafting.func_71112_a(this, 6, this.tile.LiColorBB);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); i++) {
            ICrafting icrafting = (ICrafting) this.field_75149_d.get(i);
            if (this.last_LiColor != this.tile.LiColor) {
                icrafting.func_71112_a(this, 0, this.tile.LiColor);
            }
            if (this.last_LiLength != this.tile.LiLength) {
                icrafting.func_71112_a(this, 1, this.tile.LiLength);
            }
            if (this.last_LiWidth != this.tile.LiWidth) {
                icrafting.func_71112_a(this, 2, this.tile.LiWidth);
            }
            if (this.last_LiTransparency != this.tile.LiTransparency) {
                icrafting.func_71112_a(this, 3, this.tile.LiTransparency);
            }
            if (this.last_LiColorRR != this.tile.LiColorRR) {
                icrafting.func_71112_a(this, 4, this.tile.LiColorRR);
            }
            if (this.last_LiColorGG != this.tile.LiColorGG) {
                icrafting.func_71112_a(this, 5, this.tile.LiColorGG);
            }
            if (this.last_LiColorBB != this.tile.LiColorBB) {
                icrafting.func_71112_a(this, 6, this.tile.LiColorBB);
            }
        }
        this.last_LiColor = this.tile.LiColor;
        this.last_LiLength = this.tile.LiLength;
        this.last_LiWidth = this.tile.LiWidth;
        this.last_LiTransparency = this.tile.LiTransparency;
        this.last_LiColorRR = this.tile.LiColorRR;
        this.last_LiColorGG = this.tile.LiColorGG;
        this.last_LiColorBB = this.tile.LiColorBB;
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.LiColor = par2;
        }
        if (par1 == 1) {
            this.tile.LiLength = par2;
        }
        if (par1 == 2) {
            this.tile.LiWidth = par2;
        }
        if (par1 == 3) {
            this.tile.LiTransparency = par2;
        }
        if (par1 == 4) {
            this.tile.LiColorRR = par2;
        }
        if (par1 == 5) {
            this.tile.LiColorGG = par2;
        }
        if (par1 == 6) {
            this.tile.LiColorBB = par2;
        }
        if (par1 != 0) {
            int tmp = (this.tile.LiColorRR << 16) | (this.tile.LiColorGG << 8) | this.tile.LiColorBB;
            this.tile.LiColor = tmp;
        }
    }
}
