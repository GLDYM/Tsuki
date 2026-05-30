package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_BlockKelp extends Block {

    @SideOnly(Side.CLIENT)
    public static IIcon[] tx_Kelp;
    final int GROWTH_RATE = 1;

    public ecru_BlockKelp() {
        super(Material.field_151586_h);
        this.GROWTH_RATE = 1;
        func_149675_a(true);
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderKelpID;
    }

    @SideOnly(Side.CLIENT)
    public int func_149701_w() {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149637_q() {
        return false;
    }

    public boolean func_149686_d() {
        return true;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block ubid = world.func_147439_a(i, j + 1, k);
        BlockSand blockSandFunc_147439_a = world.func_147439_a(i, j - 1, k);
        if ((blockSandFunc_147439_a == Blocks.field_150348_b || blockSandFunc_147439_a == Blocks.field_150346_d || blockSandFunc_147439_a == Blocks.field_150349_c || blockSandFunc_147439_a == Blocks.field_150354_m) && (ubid == Blocks.field_150358_i || ubid == Blocks.field_150355_j || ubid == this)) {
            return true;
        }
        if (blockSandFunc_147439_a != this) {
            return false;
        }
        if (ubid == Blocks.field_150358_i || ubid == Blocks.field_150355_j || ubid == this) {
            return true;
        }
        return false;
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
        int meta = world.func_72805_g(i, j, k) & 7;
        int type = meta & 4;
        BlockLiquid blockLiquidFunc_147439_a = world.func_147439_a(i, j + 1, k);
        BlockLiquid blockLiquidFunc_147439_a2 = world.func_147439_a(i, j + 2, k);
        if (blockLiquidFunc_147439_a != Blocks.field_150355_j && blockLiquidFunc_147439_a != Blocks.field_150358_i) {
            return;
        }
        int growthRate = getGrowthRate(world, i, j, k);
        if ((meta & 3) >= 3 && random.nextInt(growthRate) == 0) {
            if (blockLiquidFunc_147439_a2 == Blocks.field_150355_j || blockLiquidFunc_147439_a2 == Blocks.field_150358_i) {
                if (blockLiquidFunc_147439_a != Blocks.field_150355_j && blockLiquidFunc_147439_a != Blocks.field_150358_i) {
                    return;
                }
                int y = j;
                do {
                    y--;
                    if (world.func_147439_a(i, y, k) != this) {
                        break;
                    }
                } while (y > 0);
                int num = j - y;
                if (num >= 5) {
                    return;
                }
                world.func_147465_d(i, j + 1, k, this, 4, 2);
                return;
            }
            return;
        }
        int l = meta & 3;
        if (random.nextInt(growthRate) == 0) {
            world.func_72921_c(i, j, k, ((l + 1) & 3) | type, 2);
        }
    }

    private int getGrowthRate(World world, int i, int j, int k) {
        int y = j;
        while (y > 0 && world.func_147439_a(i, y, k) == this && (world.func_72805_g(i, y, k) & 4) != 0) {
            y--;
        }
        for (int x = i - 1; x <= i + 1; x++) {
            for (int z = k - 1; z <= k + 1; z++) {
                if (world.func_147439_a(x, y, z) == this && (x != i || z != k)) {
                    return 10;
                }
            }
        }
        return 4;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return tx_Kelp[par2 & 7];
    }

    protected Item func_149866_i() {
        return mod_ecru_MapleTree.Item_kelpSporophyte;
    }

    protected Item func_149865_P() {
        return mod_ecru_MapleTree.Item_foodstuff;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if ((metadata & 3) >= 3) {
            ret.add(new ItemStack(func_149865_P(), 1, 22));
            for (int n = 0; n < 3 + fortune; n++) {
                if (world.field_73012_v.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(func_149865_P(), 1, 22));
                }
            }
        }
        ret.add(new ItemStack(func_149866_i(), world.field_73012_v.nextInt(2) + 1, 0));
        return ret;
    }

    public Item func_149650_a(int i, Random r, int l) {
        if (i >= 7) {
            return func_149865_P();
        }
        return func_149866_i();
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return func_149866_i();
    }

    public int func_149692_a(int par1) {
        return 0;
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_Kelp = new IIcon[8];
        for (int i = 0; i < tx_Kelp.length; i++) {
            tx_Kelp[i] = par1IconRegister.func_94245_a("mapletree:kelp_" + i);
        }
    }
}
