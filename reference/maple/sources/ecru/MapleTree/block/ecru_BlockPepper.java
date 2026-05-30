package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ecru_BlockPepper extends BlockBush {
    public static IIcon[] tx_peppercorn;
    public static IIcon[] tx_vine;
    public static IIcon tx_wood;
    private Random random;
    private static int stackMax = 5;
    private final int GrowthRate1 = 5;
    private final int GrowthRate2 = 5;
    private String[] affinityLv1;
    private String[] affinityLv3;
    private int affinityMagnification;

    public ecru_BlockPepper() {
        super(Material.field_151575_d);
        this.random = new Random();
        this.GrowthRate1 = 5;
        this.GrowthRate2 = 5;
        this.affinityLv1 = new String[]{"Plains", "Forest", "Swampland", "Jungle", "JungleHills", "JungleEdge", "Roofed Forest", "Birch Forest", "Birch Forest Hills"};
        this.affinityLv3 = new String[]{"Desert", "Ice Plains", "Ice Mountains", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Mesa", "Mesa Plateau F", "Mesa Plateau", "FrozenOcean", "FrozenRiver"};
        this.affinityMagnification = 4;
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
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

    private boolean fieldCheck(Block id) {
        if (id == Blocks.field_150346_d || id == Blocks.field_150349_c || id == Blocks.field_150458_ak) {
            return true;
        }
        return false;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block dbid = world.func_147439_a(i, j - 1, k);
        if (fieldCheck(dbid)) {
            return true;
        }
        if (dbid == this) {
            int y = j;
            do {
                y--;
            } while (world.func_147439_a(i, y, k) == this);
            if ((j - y) - 1 < stackMax) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void func_149695_a(World par1World, int i, int j, int k, Block l) {
        canVineStay(par1World, i, j, k);
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

    private boolean canVineStay(World world, int i, int j, int k) {
        int dmeta = world.func_72805_g(i, j - 1, k);
        Block dbid = world.func_147439_a(i, j - 1, k);
        if (dmeta == 0 && dbid == this) {
            func_149690_a(world, i, j, k, world.func_72805_g(i, j, k), 0.0f, 0);
            world.func_72921_c(i, j, k, 0, 3);
            return true;
        }
        return true;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        int affinity;
        BiomeGenBase biomegenbase = world.func_72807_a(i, k);
        String bName = biomegenbase.field_76791_y;
        int i2 = this.affinityMagnification;
        if (affinityCheck(3, bName)) {
            return;
        }
        if (affinityCheck(1, bName)) {
            affinity = 1;
        } else {
            affinity = this.affinityMagnification;
        }
        boolean light = false;
        if (world.func_72957_l(i, j + 1, k) >= 9) {
            light = true;
        }
        if (!light || world.func_72805_g(i, j, k) != 2 || random.nextInt(5 * affinity) != 0) {
            if (light && world.func_72805_g(i, j, k) >= 3 && world.func_72805_g(i, j, k) < 15 && random.nextInt(5 * affinity) == 0) {
                int meta = world.func_72805_g(i, j, k);
                world.func_72921_c(i, j, k, meta + 1, 3);
                return;
            } else {
                if (world.func_72805_g(i, j, k) == 1) {
                    if ((world.func_147439_a(i, j + 1, k) == this && world.func_72805_g(i, j + 1, k) != 1 && world.func_72805_g(i, j + 1, k) != 2) || world.func_147439_a(i, j + 1, k) != this) {
                        world.func_72805_g(i, j, k);
                        world.func_72921_c(i, j, k, 2, 3);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        int meta2 = world.func_72805_g(i, j + 1, k);
        if (world.func_147439_a(i, j + 1, k) == this && meta2 < 3) {
            world.func_72921_c(i, j + 1, k, 2, 3);
            world.func_72805_g(i, j, k);
            world.func_72921_c(i, j, k, 1, 3);
            return;
        }
        int y = j;
        world.func_72805_g(i, y, k);
        world.func_147465_d(i, y, k, this, 3, 3);
        while (true) {
            y--;
            if (world.func_147439_a(i, y, k) == this) {
                world.func_72805_g(i, y, k);
                world.func_147465_d(i, y, k, this, 3, 3);
            } else {
                return;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return tx_wood;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderPepperID;
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        super.func_149690_a(world, i, j, k, l, m, 0);
        switch (l) {
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                int dp = this.random.nextInt(3) + 1;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp, 14));
                break;
            case 15:
                int dp2 = this.random.nextInt(3) + 1;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp2, 15));
                break;
        }
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockPepper);
    }

    public int func_149692_a(int par1) {
        return 0;
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        world.func_147439_a(i, j, k);
        Block dbid = world.func_147439_a(i, j - 1, k);
        int meta = world.func_72805_g(i, j, k);
        if (entityplayer.func_71045_bC() == null) {
            return true;
        }
        if (meta == 0 && fieldCheck(dbid) && entityplayer.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_foodstuff && entityplayer.func_71045_bC().func_77960_j() == 15) {
            world.func_72921_c(i, j, k, meta | 2, 3);
            if (!entityplayer.field_71075_bZ.field_75098_d) {
                entityplayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (entityplayer.func_71045_bC().func_77973_b() instanceof ItemShears) {
            world.func_72908_a(i, j, k, "step.grass", 1.0f, 1.2f);
            System.out.println("BLOCK pepperHarvest");
            if (meta >= 10 && meta <= 14) {
                int dp = this.random.nextInt(3) + 1;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp, 14));
                world.func_72921_c(i, j, k, 3, 3);
            }
            if (meta >= 15) {
                int dp2 = this.random.nextInt(3) + 1;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp2, 15));
                world.func_72921_c(i, j, k, 3, 3);
            }
            if (meta >= 10 && !entityplayer.field_71075_bZ.field_75098_d) {
                ItemStack iInfo = entityplayer.func_71045_bC();
                iInfo.func_77972_a(1, entityplayer);
                return true;
            }
            return true;
        }
        Item iid = entityplayer.func_71045_bC().func_77973_b();
        if (iid == Items.field_151017_I || iid == Items.field_151018_J || iid == Items.field_151019_K || iid == Items.field_151013_M || iid == Items.field_151012_L) {
            if (meta >= 10 && meta <= 14) {
                world.func_72921_c(i, j, k, 0, 3);
                int dp3 = this.random.nextInt(3) + 1;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp3, 14));
            } else if (meta >= 15) {
                world.func_72921_c(i, j, k, 0, 3);
                int dp4 = this.random.nextInt(3) + 1;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_foodstuff, dp4, 15));
            } else {
                world.func_72921_c(i, j, k, 0, 3);
            }
            if (meta >= 1 && !entityplayer.field_71075_bZ.field_75098_d) {
                ItemStack iInfo2 = entityplayer.func_71045_bC();
                iInfo2.func_77972_a(1, entityplayer);
            }
            world.func_72908_a(i, j, k, "step.grass", 1.0f, 1.2f);
            return true;
        }
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return AxisAlignedBB.func_72330_a(i + this.field_149759_B, j + this.field_149760_C, k + this.field_149754_D, i + this.field_149755_E, j + this.field_149756_F, k + this.field_149757_G);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_vine = new IIcon[4];
        tx_peppercorn = new IIcon[5];
        for (int i = 1; i < 4; i++) {
            tx_vine[i] = par1IconRegister.func_94245_a("mapletree:pepperVine_" + i);
        }
        tx_wood = par1IconRegister.func_94245_a("mapletree:deco_wood");
        tx_peppercorn[0] = par1IconRegister.func_94245_a("mapletree:pepper_0");
        tx_peppercorn[1] = par1IconRegister.func_94245_a("mapletree:pepper_1");
        tx_peppercorn[2] = par1IconRegister.func_94245_a("mapletree:pepper_2");
        tx_peppercorn[3] = par1IconRegister.func_94245_a("mapletree:pepper_3");
        tx_peppercorn[4] = par1IconRegister.func_94245_a("mapletree:pepper_4");
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
