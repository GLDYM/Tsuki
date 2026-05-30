package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockCropsCabbage extends BlockCrops {
    private ecru_numericConstant nc = new ecru_numericConstant();
    private IIcon[] iconArray;
    public static IIcon tx_top;
    public static IIcon tx_bottom;
    public static IIcon tx_side1;
    public static IIcon tx_side2;
    public static IIcon tx_side3;
    public static IIcon tx_side4;
    public static IIcon tx_side5;
    public static IIcon[] tx_side_ex;

    public ecru_BlockCropsCabbage() {
        func_149675_a(true);
        func_149676_a(0.0f, 0.0f, 0.0f, 0.8f, 0.5f, 0.8f);
        func_149711_c(0.0f);
        func_149672_a(field_149779_h);
        func_149649_H();
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        if (world.func_147439_a(i, j - 1, k) != Blocks.field_150350_a) {
            return true;
        }
        return false;
    }

    public boolean func_149718_j(World world, int i, int j, int k) {
        if (!func_149742_c(world, i, j, k)) {
            func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            return false;
        }
        return true;
    }

    public void func_149695_a(World par1World, int i, int j, int k, Block l) {
        func_149718_j(par1World, i, j, k);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 7));
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.func_147439_a(par2, par3 - 1, par4) == Blocks.field_150458_ak && (par1World.func_72805_g(par2, par3, par4) & 8) != 8 && par1World.func_72957_l(par2, par3 + 1, par4) >= 9) {
            int l = par1World.func_72805_g(par2, par3, par4);
            if (l < 7) {
                float f = getGrowthRate(par1World, par2, par3, par4);
                if (par5Random.nextInt(((int) (25.0f / f)) + 1) == 0) {
                    par1World.func_72921_c(par2, par3, par4, l + 1, 2);
                }
            }
        }
    }

    private float getGrowthRate(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_) {
        float f = 1.0f;
        Block block = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
        Block block1 = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
        Block block2 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
        Block block3 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
        Block block4 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
        Block block5 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
        Block block6 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
        Block block7 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;
        for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; l++) {
            for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; i1++) {
                float f1 = 0.0f;
                if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, this)) {
                    f1 = 1.0f;
                    if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1)) {
                        f1 = 3.0f;
                    }
                }
                if (l != p_149864_2_ || i1 != p_149864_4_) {
                    f1 /= 4.0f;
                }
                f += f1;
            }
        }
        if (flag2 || (flag && flag1)) {
            f /= 2.0f;
        }
        return f;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        switch (par2 & 7) {
            case 0:
            case 1:
            case 2:
                return tx_side_ex[0];
            case 3:
            case 4:
            case 5:
            case 6:
                return tx_side_ex[1];
            case 7:
            default:
                return tx_side_ex[2];
        }
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderCabbageID;
    }

    public int func_149692_a(int par1) {
        return 7;
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return func_149866_i();
    }

    protected Item func_149866_i() {
        return mod_ecru_MapleTree.Item_cabbageSeeds;
    }

    protected Item func_149865_P() {
        return Item.func_150898_a(mod_ecru_MapleTree.blockCabbage);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if ((metadata & 7) >= 7) {
            ret.add(new ItemStack(func_149865_P(), 1, metadata & 7));
            if ((metadata & 8) == 0) {
                ret.add(new ItemStack(func_149866_i(), world.field_73012_v.nextInt(2) + 1, 0));
            }
        }
        return ret;
    }

    public Item func_149650_a(int i, Random r, int l) {
        if (i >= 7) {
            return func_149865_P();
        }
        return func_149866_i();
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public int func_149660_a(World world, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        world.func_72921_c(i, j, k, 15, 3);
        return 15;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k) & 7;
        switch (meta) {
            case 0:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
                break;
            case 1:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P02, this.nc.P32);
                break;
            case 2:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P03, this.nc.P32);
                break;
            case 3:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P06, this.nc.P32);
                break;
            case 4:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P09, this.nc.P32);
                break;
            case 5:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P12, this.nc.P32);
                break;
            case 6:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P15, this.nc.P32);
                break;
            case 7:
            default:
                func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P15, this.nc.P32);
                break;
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_bottom = par1IconRegister.func_94245_a("mapletree:cabbage_bottom");
        tx_top = par1IconRegister.func_94245_a("mapletree:cabbage_top");
        tx_side1 = par1IconRegister.func_94245_a("mapletree:cabbage_side1");
        tx_side2 = par1IconRegister.func_94245_a("mapletree:cabbage_side2");
        tx_side3 = par1IconRegister.func_94245_a("mapletree:cabbage_side3");
        tx_side4 = par1IconRegister.func_94245_a("mapletree:cabbage_side4");
        tx_side5 = par1IconRegister.func_94245_a("mapletree:cabbage_side5");
        tx_side_ex = new IIcon[3];
        tx_side_ex[0] = par1IconRegister.func_94245_a("mapletree:cabbage_side_ex0");
        tx_side_ex[1] = par1IconRegister.func_94245_a("mapletree:cabbage_side_ex1");
        tx_side_ex[2] = par1IconRegister.func_94245_a("mapletree:cabbage_side_ex2");
    }
}
