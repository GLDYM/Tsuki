package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
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
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockGrape extends BlockBush {
    public static IIcon[] tx_vine;
    public static IIcon tx_wood;
    public static IIcon tx_grape0;
    public static IIcon tx_grape1;
    private Random random;
    private static int stackMax = 5;
    private final int GrowthRate1 = 5;
    private final int GrowthRate2 = 4;
    private final int GrowthRate3 = 3;
    private final int TreeDiesRate = 4;
    private int[] chkX;
    private int[] chkY;
    private int[] chkZ;
    private int[] chk;
    private int chkCounter;
    private int SEARCH_DISTANCE_LV;
    private int ARRAY_NUM_MAX;
    private ecru_numericConstant nc;

    public ecru_BlockGrape() {
        super(Material.field_151575_d);
        this.random = new Random();
        this.GrowthRate1 = 5;
        this.GrowthRate2 = 4;
        this.GrowthRate3 = 3;
        this.TreeDiesRate = 4;
        this.chkCounter = 0;
        this.nc = new ecru_numericConstant();
        func_149675_a(true);
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        func_149711_c(0.0f);
        func_149672_a(field_149779_h);
        func_149649_H();
        this.ARRAY_NUM_MAX = 2000;
        this.SEARCH_DISTANCE_LV = 21;
        this.chkCounter = 0;
        this.chkX = new int[this.ARRAY_NUM_MAX + 1];
        this.chkY = new int[this.ARRAY_NUM_MAX + 1];
        this.chkZ = new int[this.ARRAY_NUM_MAX + 1];
        this.chk = new int[this.ARRAY_NUM_MAX + 1];
        for (int q = 0; q < this.ARRAY_NUM_MAX + 1; q++) {
            this.chkX[q] = 0;
            this.chkY[q] = 0;
            this.chkZ[q] = 0;
            this.chk[q] = -1;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 8));
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
        if (id == Blocks.field_150346_d || id == Blocks.field_150349_c) {
            return true;
        }
        return false;
    }

    public boolean func_149718_j(World par1World, int i, int j, int k) {
        return true;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        boolean light = false;
        if (world.func_72957_l(i, j + 1, k) >= 9) {
            light = true;
        }
        int meta = world.func_72805_g(i, j, k);
        Block bidXp = world.func_147439_a(i + 1, j, k);
        Block bidXm = world.func_147439_a(i - 1, j, k);
        Block bidYp = world.func_147439_a(i, j + 1, k);
        Block bidYm = world.func_147439_a(i, j - 1, k);
        Block bidZp = world.func_147439_a(i, j, k + 1);
        Block bidZm = world.func_147439_a(i, j, k - 1);
        int metaXp = world.func_72805_g(i + 1, j, k);
        int metaXm = world.func_72805_g(i - 1, j, k);
        int metaYp = world.func_72805_g(i, j + 1, k);
        int metaYm = world.func_72805_g(i, j - 1, k);
        int metaZp = world.func_72805_g(i, j, k + 1);
        int metaZm = world.func_72805_g(i, j, k - 1);
        int flg1 = 1;
        int flg3 = 1;
        int flg4 = 1;
        int flg5 = 1;
        int flg6 = 1;
        if ((meta & 8) != 0 && random.nextInt(4) == 0 && vineConnectionCheck(world, i, j, k) == -1 && (meta != 2 || !fieldCheck(bidYm))) {
            if (world.func_72805_g(i, j, k) == 15) {
                func_149642_a(world, i, j - 1, k, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
            }
            world.func_72921_c(i, j, k, (meta & 8) | 0, 3);
            return;
        }
        if (meta == 10) {
            if (vineConnectionCheck(world, i, j, k) == -1 && random.nextInt(4) == 0) {
                if (world.func_72805_g(i, j, k) == 15) {
                    func_149642_a(world, i, j - 1, k, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
                }
                world.func_72921_c(i, j, k, (meta & 8) | 0, 3);
                return;
            }
            if (bidYp == this && metaYp == 0) {
                flg1 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg1 = 1;
                    world.func_72921_c(i, j + 1, k, (metaYp & 8) | 2, 3);
                }
            }
            if (bidXp == this && (metaXp & 7) < 1) {
                flg3 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg3 = 1;
                    world.func_72921_c(i + 1, j, k, (metaXp & 8) | 2, 3);
                }
            }
            if (bidXm == this && (metaXm & 7) < 1) {
                flg4 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg4 = 1;
                    world.func_72921_c(i - 1, j, k, (metaXm & 8) | 2, 3);
                }
            }
            if (bidZp == this && (metaZp & 7) < 1) {
                flg5 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg5 = 1;
                    world.func_72921_c(i, j, k + 1, (metaZp & 8) | 2, 3);
                }
            }
            if (bidZm == this && (metaZm & 7) < 1) {
                flg6 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg6 = 1;
                    world.func_72921_c(i, j, k - 1, (metaZm & 8) | 2, 3);
                }
            }
            if (flg1 * flg3 * flg4 * flg5 * flg6 != 0) {
                world.func_72921_c(i, j, k, (meta & 8) | 1, 3);
            }
        }
        int flg12 = 1;
        int flg2 = 1;
        int flg32 = 1;
        int flg42 = 1;
        int flg52 = 1;
        int flg62 = 1;
        if (meta == 2) {
            if (vineConnectionCheck(world, i, j, k) == -1 && !fieldCheck(bidYm) && random.nextInt(4) == 0) {
                if (world.func_72805_g(i, j, k) == 15) {
                    func_149642_a(world, i, j - 1, k, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
                }
                world.func_72921_c(i, j, k, (meta & 8) | 0, 3);
                return;
            }
            if (bidYp == this && metaYp == 0) {
                flg12 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg12 = 1;
                    world.func_72921_c(i, j + 1, k, (metaYp & 8) | 2, 3);
                }
            }
            if (bidYm == this && metaYm == 0) {
                flg2 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg2 = 1;
                    world.func_72921_c(i, j - 1, k, (metaYm & 8) | 2, 3);
                }
            }
            if (bidXp == this && (metaXp & 7) < 1 && (metaXp & 8) == 8) {
                flg32 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg32 = 1;
                    world.func_72921_c(i + 1, j, k, (metaXp & 8) | 2, 3);
                }
            }
            if (bidXm == this && (metaXm & 7) < 1 && (metaXm & 8) == 8) {
                flg42 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg42 = 1;
                    world.func_72921_c(i - 1, j, k, (metaXm & 8) | 2, 3);
                }
            }
            if (bidZp == this && (metaZp & 7) < 1 && (metaZp & 8) == 8) {
                flg52 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg52 = 1;
                    world.func_72921_c(i, j, k + 1, (metaZp & 8) | 2, 3);
                }
            }
            if (bidZm == this && (metaZm & 7) < 1 && (metaZm & 8) == 8) {
                flg62 = 0;
                if (light && random.nextInt(5) == 0) {
                    flg62 = 1;
                    world.func_72921_c(i, j, k - 1, (metaZm & 8) | 2, 3);
                }
            }
            if (flg12 * flg2 * flg32 * flg42 * flg52 * flg62 != 0) {
                world.func_72921_c(i, j, k, (meta & 8) | 1, 3);
            }
        }
        if (meta == 9 && random.nextInt(5) == 0) {
            if (random.nextInt(4) == 0) {
                world.func_72921_c(i, j, k, (meta & 8) | 4, 3);
            } else {
                world.func_72921_c(i, j, k, (meta & 8) | 3, 3);
            }
        }
        if (light && (meta & 8) == 8 && (meta & 7) >= 4 && (meta & 7) < 6 && random.nextInt(5) == 0) {
            int m = meta & 7;
            world.func_72921_c(i, j, k, (meta & 8) | (m + 1), 3);
        }
        if (light && meta == 14 && random.nextInt(3) == 0) {
            world.func_72921_c(i, j, k, 15, 3);
        }
        if (light && meta == 7 && random.nextInt(5) == 0) {
            world.func_72921_c(i, j, k, 2, 3);
        }
    }

    private int vineConnectionCheck(World world, int i, int j, int k) {
        for (int q = 0; q < this.ARRAY_NUM_MAX + 1; q++) {
            this.chkX[q] = 0;
            this.chkY[q] = 0;
            this.chkZ[q] = 0;
            this.chk[q] = -1;
        }
        this.chkCounter = 0;
        this.chkX[0] = i;
        this.chkY[0] = j;
        this.chkZ[0] = k;
        this.chk[0] = 0;
        this.chkCounter = 1;
        int cou = 0;
        int[] addX = {-1, 0, 1, 0, 0, 0};
        int[] addY = {0, 0, 0, 0, -1, 1};
        int[] addZ = {0, 1, 0, -1, 0, 0};
        for (int m = 0; m < this.SEARCH_DISTANCE_LV; m++) {
            while (this.chk[cou] == m) {
                int i2 = this.chkX[cou];
                int j2 = this.chkY[cou];
                int k2 = this.chkZ[cou];
                for (int n = 0; n <= 5; n++) {
                    int xx = i2 + addX[n];
                    int yy = j2 + addY[n];
                    int zz = k2 + addZ[n];
                    if (world.func_147439_a(xx, yy, zz) == this && (world.func_72805_g(xx, yy, zz) & 7) != 0 && !check(xx, yy, zz)) {
                        if (this.chkCounter > this.ARRAY_NUM_MAX) {
                            return 1;
                        }
                        this.chkX[this.chkCounter] = xx;
                        this.chkY[this.chkCounter] = yy;
                        this.chkZ[this.chkCounter] = zz;
                        this.chk[this.chkCounter] = m + 1;
                        this.chkCounter++;
                        if (world.func_72805_g(xx, yy, zz) == 1 && fieldCheck(world.func_147439_a(xx, yy - 1, zz))) {
                            return 0;
                        }
                    }
                }
                cou++;
                if (cou > this.ARRAY_NUM_MAX) {
                    return 1;
                }
            }
        }
        return -1;
    }

    private boolean check(int i, int j, int k) {
        for (int m = 0; m < this.chkCounter; m++) {
            if (this.chkX[m] == i && this.chkY[m] == j && this.chkZ[m] == k) {
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 == 0 || par2 == 8) {
            return tx_wood;
        }
        return (par2 < 13 || par2 > 15) ? tx_vine[par2] : tx_vine[11];
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderGrapeID;
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        super.func_149690_a(world, i, j, k, l, m, 0);
        switch (l) {
            case 1:
            case 2:
                func_149642_a(world, i, j, k, new ItemStack(Items.field_151055_y, 1, 0));
                break;
            case 15:
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
                break;
        }
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockGrape);
    }

    public int func_149692_a(int par1) {
        return par1 & 8;
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
        if (meta == 0 && fieldCheck(dbid) && entityplayer.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_grapeSeed) {
            world.func_72921_c(i, j, k, (meta & 8) | 7, 3);
            if (!entityplayer.field_71075_bZ.field_75098_d) {
                entityplayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (entityplayer.func_71045_bC().func_77973_b() instanceof ItemShears) {
            world.func_72908_a(i, j, k, "step.grass", 1.0f, 1.2f);
            if (meta == 15) {
                world.func_72921_c(i, j, k, 12, 3);
                func_149642_a(world, i, j - 1, k, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
                if (!entityplayer.field_71075_bZ.field_75098_d) {
                    ItemStack iInfo = entityplayer.func_71045_bC();
                    iInfo.func_77972_a(1, entityplayer);
                    return true;
                }
                return true;
            }
            return true;
        }
        Item iid = entityplayer.func_71045_bC().func_77973_b();
        if (iid instanceof ItemHoe) {
            if (meta == 15) {
                world.func_72921_c(i, j, k, 8, 3);
                func_149642_a(world, i, j - 1, k, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
            } else if (meta == 1 || meta == 2) {
                world.func_72921_c(i, j, k, 0, 3);
                func_149642_a(world, i, j - 1, k, new ItemStack(Items.field_151055_y, 1, 0));
            } else {
                world.func_72921_c(i, j, k, meta & 8, 3);
            }
            if ((meta & 7) != 0 && !entityplayer.field_71075_bZ.field_75098_d) {
                ItemStack iInfo2 = entityplayer.func_71045_bC();
                iInfo2.func_77972_a(1, entityplayer);
            }
            world.func_72908_a(i, j, k, "step.grass", 1.0f, 1.2f);
            return true;
        }
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch (meta) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                func_149676_a(this.nc.P09, 0.0f, this.nc.P09, this.nc.P23, 1.0f, this.nc.P23);
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            default:
                func_149676_a(this.nc.P02, this.nc.P20, this.nc.P02, this.nc.P30, 1.0f, this.nc.P30);
                break;
            case 15:
                func_149676_a(this.nc.P05, this.nc.P08, this.nc.P05, this.nc.P27, 1.0f, this.nc.P27);
                break;
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return AxisAlignedBB.func_72330_a(i + this.field_149759_B, j + this.field_149760_C, k + this.field_149754_D, i + this.field_149755_E, j + this.field_149756_F, k + this.field_149757_G);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_vine = new IIcon[16];
        tx_vine[1] = par1IconRegister.func_94245_a("mapletree:grape_1");
        tx_vine[2] = par1IconRegister.func_94245_a("mapletree:grape_2");
        tx_vine[7] = par1IconRegister.func_94245_a("mapletree:grape_7");
        tx_vine[9] = par1IconRegister.func_94245_a("mapletree:grape_9");
        tx_vine[10] = par1IconRegister.func_94245_a("mapletree:grape_10");
        tx_vine[11] = par1IconRegister.func_94245_a("mapletree:grape_11");
        tx_vine[12] = par1IconRegister.func_94245_a("mapletree:grape_12");
        tx_wood = par1IconRegister.func_94245_a("mapletree:deco_wood");
        tx_grape0 = par1IconRegister.func_94245_a("mapletree:grape0");
        tx_grape1 = par1IconRegister.func_94245_a("mapletree:grape1");
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, world.func_72805_g(x, y, z) & 8);
    }
}
