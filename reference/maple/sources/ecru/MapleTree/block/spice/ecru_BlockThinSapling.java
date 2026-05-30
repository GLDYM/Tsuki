package ecru.MapleTree.block.spice;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.gen.ecru_WorldGenThinTrees;
import java.util.List;
import java.util.Random;
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
import net.minecraft.world.biome.BiomeGenBase;

public class ecru_BlockThinSapling extends BlockBush {
    IIcon[] tx_sapling;
    private String[] affinityLv1 = {"Forest", "Jungle", "JungleHills", "JungleEdge", "Savanna", "Savanna Plateau", "Roofed Forest", "Birch Forest", "Birch Forest Hills"};
    private String[] affinityLv3 = {"Desert", "Ice Plains", "Ice Mountains", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Mesa", "Mesa Plateau F", "Mesa Plateau", "FrozenOcean", "FrozenRiver"};

    public ecru_BlockThinSapling() {
        func_149676_a(0.5f - 0.4f, 0.0f, 0.5f - 0.4f, 0.5f + 0.4f, 0.4f * 2.0f, 0.5f + 0.4f);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
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
                growTree(world, i, j, k, random);
            }
        }
    }

    public IIcon func_149691_a(int i, int j) {
        return this.tx_sapling[j & 7];
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

    public void growTree(World world, int i, int j, int k, Random random) {
        BiomeGenBase biomegenbase = world.func_72807_a(i, k);
        String bName = biomegenbase.field_76791_y;
        if (affinityCheck(3, bName)) {
            return;
        }
        int l = world.func_72805_g(i, j, k) & 7;
        world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
        int h = random.nextInt(3) + 3;
        Object obj = new ecru_WorldGenThinTrees(true, h, l, l);
        if (!((ecru_WorldGenThinTrees) obj).func_76484_a(world, random, i, j, k)) {
            world.func_147465_d(i, j, k, this, l, 3);
        }
    }

    public int func_149692_a(int i) {
        return i & 7;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        if (!world.field_72995_K && entityplayer.func_71045_bC() != null) {
            ItemStack Info_dyePowderWhite = new ItemStack(Items.field_151100_aR, 1, 15);
            if (entityplayer.func_71045_bC().func_77973_b() == Info_dyePowderWhite.func_77973_b() && entityplayer.func_71045_bC().func_77960_j() == 15) {
                entityplayer.func_71045_bC();
                if (!entityplayer.field_71075_bZ.field_75098_d) {
                    entityplayer.func_71045_bC().func_77979_a(1);
                }
                Random random = new Random();
                growTree(world, i, j, k, random);
                return true;
            }
            return true;
        }
        return true;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.3f, 0.0f, 0.3f, 0.7f, 0.5f, 0.7f);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_sapling = new IIcon[5];
        this.tx_sapling[0] = par1IconRegister.func_94245_a("mapletree:allspiceSapling");
        this.tx_sapling[1] = par1IconRegister.func_94245_a("mapletree:cloveSapling");
        this.tx_sapling[2] = par1IconRegister.func_94245_a("mapletree:cinnamonSapling");
        this.tx_sapling[3] = par1IconRegister.func_94245_a("mapletree:star_aniseSapling");
        this.tx_sapling[4] = par1IconRegister.func_94245_a("mapletree:nutmegSapling");
    }

    private boolean affinityCheck(int lv, String n) {
        return affinityCheck(lv, n, this.affinityLv1, this.affinityLv3);
    }

    protected boolean affinityCheck(int lv, String n, String[] affinityLv1, String[] affinityLv3) {
        if (lv == 1) {
            for (String str : affinityLv1) {
                if (str.equals(n)) {
                    return true;
                }
            }
            return false;
        }
        if (lv == 3) {
            for (String str2 : affinityLv3) {
                if (str2.equals(n)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
