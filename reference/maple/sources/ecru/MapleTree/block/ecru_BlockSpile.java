package ecru.MapleTree.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntityDripFX;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySpile;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockSpile extends BlockContainer {
    private Random random;
    private int power;
    private int GENERATION_NUM;
    public static IIcon tx_spile;
    private ecru_numericConstant nc;

    public ecru_BlockSpile() {
        super(Material.field_151578_c);
        this.random = new Random();
        this.nc = new ecru_numericConstant();
        func_149675_a(true);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public int func_149701_w() {
        return 0;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderSpileID;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return tx_spile;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockMapleWoodSyrup || world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockMapleWoodSyrup || world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockMapleWoodSyrup || world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockMapleWoodSyrup) {
            return true;
        }
        return false;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return true;
    }

    public int func_149738_a(World par1World) {
        return 1;
    }

    public void func_149674_a(World world, int i, int j, int k, Random par5Random) {
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch ((meta & 12) >> 2) {
            case 0:
                func_149676_a(this.nc.P14, this.nc.P13, this.nc.P14, this.nc.P18, this.nc.P30, this.nc.P32);
                break;
            case 1:
                func_149676_a(this.nc.P00, this.nc.P13, this.nc.P14, this.nc.P18, this.nc.P30, this.nc.P18);
                break;
            case 2:
                func_149676_a(this.nc.P14, this.nc.P13, this.nc.P00, this.nc.P18, this.nc.P30, this.nc.P18);
                break;
            case 3:
                func_149676_a(this.nc.P14, this.nc.P13, this.nc.P14, this.nc.P32, this.nc.P30, this.nc.P18);
                break;
            default:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
        }
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        world.func_72805_g(i, j, k);
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
    }

    public int func_149660_a(World world, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int data = 0;
        switch (l) {
            case 2:
                data = 0;
                break;
            case 3:
                data = 8;
                break;
            case 4:
                data = 12;
                break;
            case 5:
                data = 4;
                break;
            default:
                if (world.func_147439_a(i - 1, j, k).func_149721_r()) {
                    data = 4;
                    break;
                } else if (world.func_147439_a(i + 1, j, k).func_149721_r()) {
                    data = 12;
                    break;
                } else if (world.func_147439_a(i, j, k - 1).func_149721_r()) {
                    data = 8;
                    break;
                } else if (world.func_147439_a(i, j, k + 1).func_149721_r()) {
                    data = 0;
                    break;
                }
                break;
        }
        return (par9 & 3) | data;
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World world, int i, int j, int k, Random par5Random) {
        int xx;
        int yy;
        int zz;
        int m = world.func_72805_g(i, j, k);
        switch (m >> 2) {
            case 0:
                xx = 0;
                yy = 0;
                zz = 1;
                break;
            case 1:
                xx = -1;
                yy = 0;
                zz = 0;
                break;
            case 2:
                xx = 0;
                yy = 0;
                zz = -1;
                break;
            case 3:
                xx = 1;
                yy = 0;
                zz = 0;
                break;
            default:
                return;
        }
        if (par5Random.nextInt(6) == 0 && (world.func_72805_g(i + xx, j + yy, k + zz) & 3) == 2 && world.func_147439_a(i + xx, j + yy, k + zz) == mod_ecru_MapleTree.blockMapleWoodSyrup) {
            ecru_EntityDripFX entityFX = new ecru_EntityDripFX(world, i + 0.5d, j - 0.6d, k + 0.5d, 0.0d, 0.0d, 0.0d);
            entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(0));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        }
    }

    public ecru_TileEntitySpile func_149915_a(World world, int meta) {
        return new ecru_TileEntitySpile();
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_spile = par1IconRegister.func_94245_a("mapletree:spile");
    }
}
