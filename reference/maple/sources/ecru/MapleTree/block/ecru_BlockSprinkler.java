package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySprinkler;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockSprinkler extends BlockContainer {
    private IIcon tx_body;

    public ecru_BlockSprinkler() {
        super(Material.field_151578_c);
        func_149675_a(false);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
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
        return mod_ecru_MapleTree.renderSprinklerID;
    }

    public ecru_TileEntitySprinkler func_149915_a(World var1, int meta) {
        return new ecru_TileEntitySprinkler();
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        boolean flag = world.func_72864_z(i, j, k) || world.func_72864_z(i, j + 1, k);
        if (flag) {
            world.func_147464_a(i, j, k, this, func_149738_a(world));
        }
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
    }

    public int func_149660_a(World world, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        if (l == 0) {
            return par9 | 4;
        }
        return par9;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        super.func_149674_a(world, i, j, k, random);
        if (!world.field_72995_K && world.func_72864_z(i, j, k)) {
            switchOnOff(world, i, j, k);
        }
    }

    private boolean switchOnOff(World world, int i, int j, int k) {
        if (world.field_72995_K) {
            return true;
        }
        int meta = world.func_72805_g(i, j, k);
        int bit = (meta ^ (-1)) & 8;
        world.func_72921_c(i, j, k, (meta & 7) | bit, 3);
        return true;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockSprinkler);
    }

    public int func_149692_a(int i) {
        return i & 3;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par5EntityPlayer.func_71045_bC() != null && (par5EntityPlayer.func_71045_bC().func_77973_b() instanceof ItemHoe)) {
            int OFFSET = (world.func_72805_g(i, j, k) & 4) == 0 ? 2 : 5;
            for (int sty = j - OFFSET; sty <= (j - OFFSET) + 2; sty++) {
                for (int stx = i - 4; stx <= i + 4; stx++) {
                    for (int stz = k - 4; stz <= k + 4; stz++) {
                        if (world.func_147439_a(stx, sty + 1, stz) == Blocks.field_150350_a && (world.func_147439_a(stx, sty, stz) == Blocks.field_150346_d || world.func_147439_a(stx, sty, stz) == Blocks.field_150349_c)) {
                            world.func_147465_d(stx, sty, stz, Blocks.field_150458_ak, 7, 3);
                            world.func_72908_a(i, j, k, "step.grass", 1.0f, 1.2f);
                            if (par5EntityPlayer.field_71075_bZ.field_75098_d) {
                                continue;
                            } else {
                                ItemStack iInfo = par5EntityPlayer.func_71045_bC();
                                iInfo.func_77972_a(1, par5EntityPlayer);
                                if (!iInfo.func_77951_h()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        int meta = world.func_72805_g(i, j, k);
        int bit = (meta ^ (-1)) & 8;
        world.func_72921_c(i, j, k, (meta & 7) | bit, 3);
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        if ((par1IBlockAccess.func_72805_g(i, j, k) & 4) == 4) {
            func_149676_a(0.1875f, 0.7f, 0.1875f, 0.8125f, 1.0f, 0.8125f);
        } else {
            func_149676_a(0.1875f, 0.0f, 0.1875f, 0.8125f, 0.3f, 0.8125f);
        }
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
