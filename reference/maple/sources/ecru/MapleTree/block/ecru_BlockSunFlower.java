package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySunFlower;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockSunFlower extends BlockContainer {
    private final Random random;
    private IIcon tx_body;

    public ecru_BlockSunFlower() {
        super(Material.field_151585_k);
        this.random = new Random();
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderSunFlowerID;
    }

    public ecru_TileEntitySunFlower func_149915_a(World var1, int meta) {
        return new ecru_TileEntitySunFlower();
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        super.func_149674_a(world, i, j, k, random);
        if (world.func_72805_g(i, j, k) != 0) {
            return;
        }
        boolean light = false;
        if (world.func_72957_l(i, j + 1, k) >= 9) {
            light = true;
        }
        if (random.nextInt(8) == 0 && light) {
            if (random.nextInt(30) == 0 && world.func_147439_a(i, j + 1, k) == Blocks.field_150350_a) {
                world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockSunFlower, 1, 3);
                world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockSunFlower, 3, 3);
                return;
            }
            if (random.nextInt(30) == 0 && world.func_147439_a(i, j + 1, k) == Blocks.field_150350_a && world.func_147439_a(i, j + 2, k) == Blocks.field_150350_a && world.func_147439_a(i, j + 3, k) == Blocks.field_150350_a) {
                world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockSunFlower, 1, 3);
                world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockSunFlower, 2, 3);
                world.func_147465_d(i, j + 2, k, mod_ecru_MapleTree.blockSunFlower, 2, 3);
                world.func_147465_d(i, j + 3, k, mod_ecru_MapleTree.blockSunFlower, 3, 3);
                return;
            }
            if (world.func_147439_a(i, j + 1, k) == Blocks.field_150350_a && world.func_147439_a(i, j + 2, k) == Blocks.field_150350_a) {
                world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockSunFlower, 1, 3);
                world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockSunFlower, 2, 3);
                world.func_147465_d(i, j + 2, k, mod_ecru_MapleTree.blockSunFlower, 3, 3);
            }
        }
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block id = world.func_147439_a(i, j - 1, k);
        int meta = world.func_72805_g(i, j - 1, k);
        if (id == Blocks.field_150346_d || id == Blocks.field_150349_c) {
            return true;
        }
        if ((id == this && meta < 3) || id == mod_ecru_MapleTree.blockPlanter) {
            return true;
        }
        return false;
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        func_149718_j(world, i, j, k);
    }

    public boolean func_149718_j(World world, int i, int j, int k) {
        if (!func_149742_c(world, i, j, k)) {
            func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            return false;
        }
        return true;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return null;
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        int dp;
        super.func_149690_a(world, i, j, k, l, m, 0);
        int meta = l & 7;
        switch (meta) {
            case 0:
            case 1:
            case 2:
            default:
                return;
            case 3:
            case 4:
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_SunFlowerSeed, 1, 0));
                return;
            case 5:
                dp = 1 + this.random.nextInt(2);
                break;
            case 6:
                dp = 2 + this.random.nextInt(3);
                break;
            case 7:
                dp = 4 + this.random.nextInt(4);
                break;
        }
        func_149642_a(world, i, j, k, new ItemStack(Items.field_151114_aO, dp, 0));
        func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_SunFlowerSeed, this.random.nextInt(4) + 3, 0));
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
                func_149676_a(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.3125f, 0.6875f);
                break;
            default:
                func_149676_a(0.25f, 0.0f, 0.25f, 0.6875f, 1.0f, 0.75f);
                break;
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int l = world.func_72805_g(x, y, z);
        if ((l & 7) >= 7) {
            return 14;
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("MapleTree:oreLeaf_7");
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, world.func_72805_g(x, y, z));
    }
}
