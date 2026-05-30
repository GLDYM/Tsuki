package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityLighthouseIllumination;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockLighthouseIllumination extends BlockContainer {
    private IIcon tx_body;

    public ecru_BlockLighthouseIllumination() {
        super(Material.field_151578_c);
        func_149675_a(false);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderLighthouseIlluminationID;
    }

    public ecru_TileEntityLighthouseIllumination func_149915_a(World var1, int metadata) {
        return new ecru_TileEntityLighthouseIllumination();
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        boolean flag = world.func_72864_z(i, j, k) || world.func_72864_z(i, j + 1, k);
        if (flag) {
            int meta = world.func_72805_g(i, j, k);
            Block id = world.func_147439_a(i, j, k);
            ecru_TileEntityLighthouseIllumination tile = (ecru_TileEntityLighthouseIllumination) world.func_147438_o(i, j, k);
            world.func_147465_d(i, j, k, id, meta | 8, 3);
            tile.func_145829_t();
            world.func_147455_a(i, j, k, tile);
            return;
        }
        int meta2 = world.func_72805_g(i, j, k);
        Block id2 = world.func_147439_a(i, j, k);
        ecru_TileEntityLighthouseIllumination tile2 = (ecru_TileEntityLighthouseIllumination) world.func_147438_o(i, j, k);
        world.func_147465_d(i, j, k, id2, meta2 & 7, 3);
        tile2.func_145829_t();
        world.func_147455_a(i, j, k, tile2);
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        boolean flag = world.func_72864_z(i, j, k) || world.func_72864_z(i, j + 1, k);
        if (flag) {
            int meta = world.func_72805_g(i, j, k);
            Block id = world.func_147439_a(i, j, k);
            world.func_147465_d(i, j, k, id, meta | 8, 3);
        } else {
            int meta2 = world.func_72805_g(i, j, k);
            Block id2 = world.func_147439_a(i, j, k);
            world.func_147465_d(i, j, k, id2, meta2 & 7, 3);
        }
        ecru_TileEntityLighthouseIllumination tile = (ecru_TileEntityLighthouseIllumination) world.func_147438_o(i, j, k);
        if (tile != null) {
            tile.LiColor = mod_ecru_MapleTree.LighthouseIlluminationColor;
            tile.LiLength = mod_ecru_MapleTree.LighthouseIlluminationLength;
            tile.LiWidth = mod_ecru_MapleTree.LighthouseIlluminationWidth;
            tile.LiTransparency = mod_ecru_MapleTree.LighthouseIlluminationTransparency;
            tile.LiColorBB = mod_ecru_MapleTree.LighthouseIlluminationColor & 255;
            tile.LiColorGG = (mod_ecru_MapleTree.LighthouseIlluminationColor & 65280) >> 8;
            tile.LiColorRR = (mod_ecru_MapleTree.LighthouseIlluminationColor & 16711680) >> 16;
        }
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        super.func_149674_a(world, i, j, k, random);
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int meta = world.func_72805_g(i, j, k);
        if (par5EntityPlayer.func_70093_af()) {
            Block id = world.func_147439_a(i, j, k);
            int bit = (meta ^ (-1)) & 8;
            int meta2 = (meta & 7) | bit;
            ecru_TileEntityLighthouseIllumination tile = (ecru_TileEntityLighthouseIllumination) world.func_147438_o(i, j, k);
            world.func_147465_d(i, j, k, id, meta2, 2);
            tile.func_145829_t();
            world.func_147455_a(i, j, k, tile);
            return true;
        }
        if (!world.field_72995_K) {
            par5EntityPlayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdLighthouseIllumination, world, i, j, k);
            return true;
        }
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.125f, 0.0f, 0.125f, 0.875f, 1.0f, 0.875f);
    }

    public int getLightValue(IBlockAccess world, int i, int j, int k) {
        if ((world.func_72805_g(i, j, k) & 8) == 8) {
            return 15;
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("obsidian");
    }
}
