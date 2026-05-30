package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityPowerShaftGear;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockPowerShaftGear extends BlockContainer {
    private final Random random;
    private IIcon tx_body;

    public ecru_BlockPowerShaftGear() {
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
        return mod_ecru_MapleTree.renderPowerShaftGearID;
    }

    public ecru_TileEntityPowerShaftGear func_149915_a(World var1, int meta) {
        return new ecru_TileEntityPowerShaftGear();
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int meta = 0;
        switch (l) {
            case 0:
                meta = 5;
                break;
            case 1:
                meta = 4;
                break;
            case 2:
                meta = 0;
                break;
            case 3:
                meta = 2;
                break;
            case 4:
                meta = 3;
                break;
            case 5:
                meta = 1;
                break;
        }
        return meta;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int meta;
        if (par5EntityPlayer.func_71045_bC() != null && (par5EntityPlayer.func_71045_bC().func_77973_b() instanceof ItemHoe)) {
            int meta2 = world.func_72805_g(i, j, k) & 7;
            if (meta2 >= 0 && meta2 < 5) {
                meta = meta2 + 1;
            } else {
                meta = 0;
            }
            world.func_72921_c(i, j, k, meta, 3);
            return true;
        }
        return false;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.15625f, 0.15625f, 0.15625f, 0.84375f, 0.84375f, 0.84375f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("mapletree:deco_wood");
    }
}
