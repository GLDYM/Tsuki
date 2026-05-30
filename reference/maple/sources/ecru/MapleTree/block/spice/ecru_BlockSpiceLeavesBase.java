package ecru.MapleTree.block.spice;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ecru_BlockSpiceLeavesBase extends BlockLeavesBase implements IShearable {
    int[] field_150128_a;

    @SideOnly(Side.CLIENT)
    protected int field_150127_b;
    private static final String __OBFID = "CL_00000263";
    IIcon[] tx_leaves;

    public ecru_BlockSpiceLeavesBase(Material m, boolean b) {
        super(m, b);
        this.tx_leaves = new IIcon[8];
        func_149675_a(true);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int p_149741_1_) {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return 16777215;
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        int i1 = 1 + 1;
        if (p_149749_1_.func_72904_c(p_149749_2_ - i1, p_149749_3_ - i1, p_149749_4_ - i1, p_149749_2_ + i1, p_149749_3_ + i1, p_149749_4_ + i1)) {
            for (int j1 = -1; j1 <= 1; j1++) {
                for (int k1 = -1; k1 <= 1; k1++) {
                    for (int l1 = -1; l1 <= 1; l1++) {
                        Block block = p_149749_1_.func_147439_a(p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        if (block.isLeaves(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1)) {
                            block.beginLeavesDecay(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        }
                    }
                }
            }
        }
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        if (!p_149674_1_.field_72995_K) {
            int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);
            if ((l & 8) != 0 && (l & 4) == 0) {
                int i1 = 4 + 1;
                int j1 = 32 * 32;
                int k1 = 32 / 2;
                if (this.field_150128_a == null) {
                    this.field_150128_a = new int[32 * 32 * 32];
                }
                if (p_149674_1_.func_72904_c(p_149674_2_ - i1, p_149674_3_ - i1, p_149674_4_ - i1, p_149674_2_ + i1, p_149674_3_ + i1, p_149674_4_ + i1)) {
                    for (int l1 = -4; l1 <= 4; l1++) {
                        for (int i2 = -4; i2 <= 4; i2++) {
                            for (int j2 = -4; j2 <= 4; j2++) {
                                Block block = p_149674_1_.func_147439_a(p_149674_2_ + l1, p_149674_3_ + i2, p_149674_4_ + j2);
                                if (block.canSustainLeaves(p_149674_1_, p_149674_2_ + l1, p_149674_3_ + i2, p_149674_4_ + j2)) {
                                    this.field_150128_a[((l1 + k1) * j1) + ((i2 + k1) * 32) + j2 + k1] = 0;
                                } else if (block.isLeaves(p_149674_1_, p_149674_2_ + l1, p_149674_3_ + i2, p_149674_4_ + j2)) {
                                    this.field_150128_a[((l1 + k1) * j1) + ((i2 + k1) * 32) + j2 + k1] = -2;
                                } else {
                                    this.field_150128_a[((l1 + k1) * j1) + ((i2 + k1) * 32) + j2 + k1] = -1;
                                }
                            }
                        }
                    }
                    for (int l12 = 1; l12 <= 4; l12++) {
                        for (int i22 = -4; i22 <= 4; i22++) {
                            for (int j22 = -4; j22 <= 4; j22++) {
                                for (int k2 = -4; k2 <= 4; k2++) {
                                    if (this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1) * 32) + k2 + k1] == l12 - 1) {
                                        if (this.field_150128_a[(((i22 + k1) - 1) * j1) + ((j22 + k1) * 32) + k2 + k1] == -2) {
                                            this.field_150128_a[(((i22 + k1) - 1) * j1) + ((j22 + k1) * 32) + k2 + k1] = l12;
                                        }
                                        if (this.field_150128_a[((i22 + k1 + 1) * j1) + ((j22 + k1) * 32) + k2 + k1] == -2) {
                                            this.field_150128_a[((i22 + k1 + 1) * j1) + ((j22 + k1) * 32) + k2 + k1] = l12;
                                        }
                                        if (this.field_150128_a[((i22 + k1) * j1) + (((j22 + k1) - 1) * 32) + k2 + k1] == -2) {
                                            this.field_150128_a[((i22 + k1) * j1) + (((j22 + k1) - 1) * 32) + k2 + k1] = l12;
                                        }
                                        if (this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1 + 1) * 32) + k2 + k1] == -2) {
                                            this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1 + 1) * 32) + k2 + k1] = l12;
                                        }
                                        if (this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1) * 32) + ((k2 + k1) - 1)] == -2) {
                                            this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1) * 32) + ((k2 + k1) - 1)] = l12;
                                        }
                                        if (this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1) * 32) + k2 + k1 + 1] == -2) {
                                            this.field_150128_a[((i22 + k1) * j1) + ((j22 + k1) * 32) + k2 + k1 + 1] = l12;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                int l13 = this.field_150128_a[(k1 * j1) + (k1 * 32) + k1];
                if (l13 >= 0) {
                    p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l & (-9), 4);
                } else {
                    removeLeaves(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        if (p_149734_1_.func_72951_B(p_149734_2_, p_149734_3_ + 1, p_149734_4_) && !World.func_147466_a(p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_) && p_149734_5_.nextInt(15) == 1) {
            double d0 = p_149734_2_ + p_149734_5_.nextFloat();
            double d1 = p_149734_3_ - 0.05d;
            double d2 = p_149734_4_ + p_149734_5_.nextFloat();
            p_149734_1_.func_72869_a("dripWater", d0, d1, d2, 0.0d, 0.0d, 0.0d);
        }
    }

    private void removeLeaves(World p_150126_1_, int p_150126_2_, int p_150126_3_, int p_150126_4_) {
        func_149697_b(p_150126_1_, p_150126_2_, p_150126_3_, p_150126_4_, p_150126_1_.func_72805_g(p_150126_2_, p_150126_3_, p_150126_4_), 0);
        p_150126_1_.func_147468_f(p_150126_2_, p_150126_3_, p_150126_4_);
    }

    public int func_149745_a(Random p_149745_1_) {
        return p_149745_1_.nextInt(20) == 0 ? 1 : 0;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockThinSapling);
    }

    public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
        super.func_149690_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, 1.0f, p_149690_7_);
    }

    protected void func_150124_c(World p_150124_1_, int p_150124_2_, int p_150124_3_, int p_150124_4_, int p_150124_5_, int p_150124_6_) {
    }

    protected int func_150123_b(int p_150123_1_) {
        return 20;
    }

    public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        super.func_149636_a(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    }

    public int func_149692_a(int p_149692_1_) {
        return 0;
    }

    protected ItemStack func_149644_j(int p_149644_1_) {
        return new ItemStack(Item.func_150898_a(this), 1, p_149644_1_ & 3);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }

    public void beginLeavesDecay(World world, int x, int y, int z) {
        int i2 = world.func_72805_g(x, y, z);
        if ((i2 & 8) == 0) {
            world.func_72921_c(x, y, z, i2 | 8, 4);
        }
        world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) | 8, 4);
    }

    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean func_149686_d() {
        return true;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149662_c() {
        switch (mod_ecru_MapleTree.GraphicsLevel) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                return Blocks.field_150362_t.func_149662_c();
        }
    }

    private boolean _shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (par5 == 0 && this.field_149760_C > 0.0d) {
            return true;
        }
        if (par5 == 1 && this.field_149756_F < 1.0d) {
            return true;
        }
        if (par5 == 2 && this.field_149754_D > 0.0d) {
            return true;
        }
        if (par5 == 3 && this.field_149757_G < 1.0d) {
            return true;
        }
        if (par5 != 4 || this.field_149759_B <= 0.0d) {
            return (par5 == 5 && this.field_149755_E < 1.0d) || !par1IBlockAccess.func_147439_a(par2, par3, par4).func_149662_c();
        }
        return true;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        Block i1 = iblockaccess.func_147439_a(i, j, k);
        if (mod_ecru_MapleTree.GraphicsLevel == 0) {
            return _shouldSideBeRendered(iblockaccess, i, j, k, l);
        }
        if (mod_ecru_MapleTree.GraphicsLevel == 1) {
            if (i1 == this) {
                return false;
            }
            return _shouldSideBeRendered(iblockaccess, i, j, k, l);
        }
        if (Blocks.field_150362_t.func_149662_c() && i1 == this) {
            return false;
        }
        return _shouldSideBeRendered(iblockaccess, i, j, k, l);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        int meta = j & 3;
        if (mod_ecru_MapleTree.GraphicsLevel == 0) {
            return this.tx_leaves[meta * 2];
        }
        if (mod_ecru_MapleTree.GraphicsLevel == 1) {
            return this.tx_leaves[(meta * 2) + 1];
        }
        return this.tx_leaves[(meta * 2) + (Blocks.field_150362_t.func_149662_c() ? 1 : 0)];
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        Block id = world.func_147439_a(i, j, k);
        int meta = world.func_72805_g(i, j, k);
        if (id == this) {
            world.func_72921_c(i, j, k, meta | 4, 3);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_leaves[0] = par1IconRegister.func_94245_a("mapletree:allspiceLeaves");
        this.tx_leaves[1] = par1IconRegister.func_94245_a("mapletree:allspiceLeaves");
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

    protected void dropBlockAsItemSpace(World world, int i, int j, int k, ItemStack itemstack) {
        int[] xx = {1, -1, 0, 0, 0, 0};
        int[] yy = {0, 0, 0, 0, 1, -1};
        int[] zz = {0, 0, 1, -1, 0, 0};
        for (int m = 0; m < 6; m++) {
            if (world.func_147437_c(i + xx[m], j + yy[m], k + zz[m])) {
                func_149642_a(world, i + xx[m], j + yy[m], k + zz[m], itemstack);
                return;
            }
        }
        func_149642_a(world, i, j, k, itemstack);
    }
}
