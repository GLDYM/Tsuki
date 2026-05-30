package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaft;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockPowerShaft extends BlockContainer {
    private final Random random;
    private IIcon tx_body;

    public ecru_BlockPowerShaft() {
        super(Material.field_151575_d);
        this.random = new Random();
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderPowerShaftID;
    }

    public ecru_TileEntityPowerShaft func_149915_a(World var1, int meta) {
        return new ecru_TileEntityPowerShaft();
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int meta = 0;
        switch (l) {
            case 0:
            case 1:
                meta = 0;
                break;
            case 2:
            case 3:
                meta = 2;
                break;
            case 4:
            case 5:
                meta = 1;
                break;
        }
        return meta;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        return false;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        if ((meta & 3) == 1) {
            func_149676_a(0.0f, 0.28125f, 0.28125f, 1.0f, 0.71875f, 0.71875f);
        } else if ((meta & 3) == 2) {
            func_149676_a(0.28125f, 0.28125f, 0.0f, 0.71875f, 0.71875f, 1.0f);
        } else {
            func_149676_a(0.15625f, 0.0f, 0.15625f, 0.84375f, 1.0f, 0.84375f);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("mapletree:deco_wood");
    }
}
