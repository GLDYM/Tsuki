package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.gen.ecru_WorldGenBigMapleTree;
import ecru.MapleTree.gen.ecru_WorldGenMapleTrees;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockMapleSapling extends BlockBush {
    private int[] png = new int[5];
    IIcon[] tx_sapling = new IIcon[5];

    public ecru_BlockMapleSapling() {
        func_149676_a(0.5f - 0.4f, 0.0f, 0.5f - 0.4f, 0.5f + 0.4f, 0.4f * 2.0f, 0.5f + 0.4f);
        this.png[0] = 2;
        this.png[1] = 3;
        this.png[2] = 4;
        this.png[3] = 5;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k) {
        iblockaccess.func_72805_g(i, j, k);
        return 16777215;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        if (world.field_72995_K) {
            return;
        }
        super.func_149674_a(world, i, j, k, random);
        if (world.func_72957_l(i, j + 1, k) >= 9 && random.nextInt(7) == 0) {
            int l = world.func_72805_g(i, j, k);
            if ((l & 8) == 0) {
                world.func_72921_c(i, j, k, l | 8, 3);
            } else {
                growTree(world, i, j, k, random, 0);
            }
        }
    }

    public IIcon func_149691_a(int i, int j) {
        return this.tx_sapling[j & 3];
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return true;
    }

    public int func_149645_b() {
        return 1;
    }

    public void growTree(World world, int i, int j, int k, Random random, int ManureLv) {
        Block LeavesId;
        int LeavesRand;
        int SaplingId = world.func_72805_g(i, j, k) & 3;
        int l = world.func_72805_g(i, j, k) & 3;
        world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
        if (SaplingId == 0) {
            LeavesId = mod_ecru_MapleTree.blockMapleLeaves;
            LeavesRand = 6;
        } else {
            LeavesId = mod_ecru_MapleTree.blockMapleLeaves;
            LeavesRand = 15;
        }
        if (ManureLv == 1) {
            Object obj = new ecru_WorldGenMapleTrees(true);
            if (!((ecru_WorldGenMapleTrees) obj).generate(world, random, i, j, k, LeavesId, SaplingId)) {
                world.func_147465_d(i, j, k, this, l, 3);
                return;
            }
            return;
        }
        if (ManureLv == 2) {
            Object obj2 = new ecru_WorldGenBigMapleTree(true);
            if (!((ecru_WorldGenBigMapleTree) obj2).generate(world, random, i, j, k, LeavesId, SaplingId, 1)) {
                world.func_147465_d(i, j, k, this, l, 3);
                return;
            }
            return;
        }
        if (random.nextInt(LeavesRand) == 0) {
            Object obj3 = new ecru_WorldGenBigMapleTree(true);
            if (!((ecru_WorldGenBigMapleTree) obj3).generate(world, random, i, j, k, LeavesId, SaplingId, 0)) {
                world.func_147465_d(i, j, k, this, l, 3);
                return;
            }
            return;
        }
        Object obj4 = new ecru_WorldGenMapleTrees(true);
        if (!((ecru_WorldGenMapleTrees) obj4).generate(world, random, i, j, k, LeavesId, SaplingId)) {
            world.func_147465_d(i, j, k, this, l, 3);
        }
    }

    public int func_149692_a(int i) {
        return i & 3;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        if (!world.field_72995_K && entityplayer.func_71045_bC() == null) {
            entityplayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdButtonId, world, i, j, k);
            return true;
        }
        if (!world.field_72995_K && entityplayer.func_71045_bC() != null) {
            ItemStack Info_dyePowderWhite = new ItemStack(Items.field_151100_aR, 1, 15);
            ItemStack Info_dyePowderRed = new ItemStack(Items.field_151100_aR, 1, 1);
            if (entityplayer.func_71045_bC().func_77973_b() == Info_dyePowderWhite.func_77973_b() && entityplayer.func_71045_bC().func_77960_j() == Info_dyePowderWhite.func_77960_j()) {
                entityplayer.func_71045_bC();
                if (!entityplayer.field_71075_bZ.field_75098_d) {
                    entityplayer.func_71045_bC().func_77979_a(1);
                }
                Random random = new Random();
                growTree(world, i, j, k, random, 0);
                return true;
            }
            if (entityplayer.func_71045_bC().func_77973_b() == Info_dyePowderRed.func_77973_b() && entityplayer.func_71045_bC().func_77960_j() == Info_dyePowderRed.func_77960_j()) {
                entityplayer.func_71045_bC();
                if (!entityplayer.field_71075_bZ.field_75098_d) {
                    entityplayer.func_71045_bC().func_77979_a(1);
                }
                Random random2 = new Random();
                growTree(world, i, j, k, random2, 1);
                return true;
            }
            if (entityplayer.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_treeManure) {
                entityplayer.func_71045_bC();
                if (!entityplayer.field_71075_bZ.field_75098_d) {
                    entityplayer.func_71045_bC().func_77979_a(1);
                }
                Random random3 = new Random();
                growTree(world, i, j, k, random3, 2);
                return true;
            }
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_sapling[0] = par1IconRegister.func_94245_a("mapletree:sapling_red");
        this.tx_sapling[1] = par1IconRegister.func_94245_a("mapletree:sapling_yellow");
        this.tx_sapling[2] = par1IconRegister.func_94245_a("mapletree:sapling_orange");
        this.tx_sapling[3] = par1IconRegister.func_94245_a("mapletree:sapling_lime");
    }
}
