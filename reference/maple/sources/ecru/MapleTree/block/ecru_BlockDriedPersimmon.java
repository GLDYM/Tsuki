package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockDriedPersimmon extends Block {
    public static IIcon[] driedPersimmons;
    public static IIcon tx_wood;
    private Random random;

    public ecru_BlockDriedPersimmon() {
        super(Material.field_151572_C);
        this.random = new Random();
        func_149675_a(true);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 7));
    }

    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k) {
        int tmpxz = Math.abs((((i % 10) * (j % 10)) * (k % 10)) % 10);
        switch (tmpxz) {
            case 1:
                return 16768477;
            case 2:
            case 3:
                return 14540287;
            case 4:
            case 5:
                return 16764125;
            case 6:
            case 7:
                return 16777147;
            case 8:
            case 9:
            default:
                return 16777215;
        }
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        if (world.func_147437_c(i, j + 1, k)) {
            return false;
        }
        return true;
    }

    public void func_149695_a(World par1World, int i, int j, int k, Block l) {
        func_149718_j(par1World, i, j, k);
    }

    public boolean func_149718_j(World par1World, int i, int j, int k) {
        if (!func_149742_c(par1World, i, j, k)) {
            func_149697_b(par1World, i, j, k, par1World.func_72805_g(i, j, k), 0);
            par1World.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            return false;
        }
        return true;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        boolean light = world.func_72957_l(i, j, k) >= 9;
        long ti2 = world.func_72820_D() % 24000;
        if (light && ti2 > 0 && ti2 <= 12000) {
            int meta = world.func_72805_g(i, j, k);
            if ((meta & 7) < 7 && random.nextInt(5) == 0) {
                world.func_72921_c(i, j, k, meta + 1, 3);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        switch (par2) {
            case 0:
            case 1:
            case 2:
            default:
                return driedPersimmons[0];
            case 3:
            case 4:
            case 5:
            case 6:
                return driedPersimmons[1];
            case 7:
                return driedPersimmons[2];
        }
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderDriedPersimmonID;
    }

    protected Item getCropItem() {
        return mod_ecru_MapleTree.Item_alwaysFoods;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if ((metadata & 7) == 7) {
            ret.add(new ItemStack(getCropItem(), 4, 4));
        } else {
            ret.add(new ItemStack(Item.func_150898_a(mod_ecru_MapleTree.blockDriedPersimmons), 1, metadata & 7));
        }
        return ret;
    }

    public int func_149692_a(int par1) {
        if ((par1 & 7) == 7) {
            return 4;
        }
        return par1 & 7;
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World world, int par1, int par2, int par3) {
        int meta = world.func_72805_g(par1, par2, par3);
        if ((meta & 7) == 7) {
            return getCropItem();
        }
        return Item.func_150898_a(mod_ecru_MapleTree.blockDriedPersimmons);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        if ((par1IBlockAccess.func_72805_g(i, j, k) & 8) == 0) {
            func_149676_a(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 0.75f);
        } else {
            func_149676_a(0.25f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f);
        }
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        int muki;
        int meta = world.func_72805_g(i, j, k);
        if (world.func_147439_a(i, j + 1, k) == this) {
            muki = world.func_72805_g(i, j + 1, k) & 8;
        } else {
            int muki2 = MathHelper.func_76128_c(((entityliving.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
            if (muki2 == 1 || muki2 == 3) {
                muki = 8;
            } else {
                muki = 0;
            }
        }
        world.func_72921_c(i, j, k, meta | muki, 3);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        driedPersimmons = new IIcon[3];
        driedPersimmons[0] = par1IconRegister.func_94245_a("mapletree:driedPersimmonBlock_0");
        driedPersimmons[1] = par1IconRegister.func_94245_a("mapletree:driedPersimmonBlock_1");
        driedPersimmons[2] = par1IconRegister.func_94245_a("mapletree:driedPersimmonBlock_2");
        tx_wood = par1IconRegister.func_94245_a("mapletree:deco_wood");
    }
}
