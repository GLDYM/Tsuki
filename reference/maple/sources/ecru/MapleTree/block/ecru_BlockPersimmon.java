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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockPersimmon extends Block {
    public static IIcon tx_persimmon;
    private Random random;

    public ecru_BlockPersimmon() {
        super(Material.field_151578_c);
        this.random = new Random();
        func_149675_a(true);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 15));
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

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block id = world.func_147439_a(i, j + 1, k);
        if (id != mod_ecru_MapleTree.blockPersimmonWood) {
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

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        boolean light = false;
        if (world.func_72957_l(i, j, k) >= 0) {
            light = true;
        }
        int meta = world.func_72805_g(i, j, k);
        if (meta < 14 && random.nextInt(3) == 0 && light) {
            meta++;
            world.func_72921_c(i, j, k, meta, 3);
        }
        if (meta >= 12 && meta <= 14 && random.nextInt(5) == 0) {
            func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return tx_persimmon;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderPersimmonID;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if (metadata >= 7) {
            ret.add(new ItemStack(getCropItem(), 1, 3));
            for (int n = 0; n < 3 + fortune; n++) {
                if (world.field_73012_v.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(getCropItem(), 1, 3));
                }
            }
        }
        return ret;
    }

    public Item func_149650_a(int i, Random r, int l) {
        if (i >= 7) {
            return getCropItem();
        }
        return null;
    }

    protected Item getCropItem() {
        return mod_ecru_MapleTree.Item_alwaysFoods;
    }

    public int func_149692_a(int par1) {
        return 3;
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return mod_ecru_MapleTree.Item_alwaysFoods;
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.6f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public IIcon getPersimmonIcon(int i) {
        return tx_persimmon;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_persimmon = par1IconRegister.func_94245_a("mapletree:persimmonBlock");
    }
}
