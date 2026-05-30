package ecru.MapleTree.block.spice;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockCropsBase extends BlockCrops {

    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    private int affinityMagnification = 3;

    public ecru_BlockCropsBase() {
        func_149675_a(true);
        func_149647_a((CreativeTabs) null);
        func_149711_c(0.0f);
        func_149672_a(field_149779_h);
        func_149649_H();
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
    }

    protected boolean func_149854_a(Block i) {
        return i == Blocks.field_150458_ak;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block dbid = world.func_147439_a(i, j - 1, k);
        if (dbid == Blocks.field_150458_ak) {
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

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.func_72957_l(par2, par3 + 1, par4) >= 9) {
            int l = par1World.func_72805_g(par2, par3, par4);
            if (l < 7) {
                float f = getGrowthRate(par1World, par2, par3, par4);
                int rate = ((int) (25.0f / f)) + 1;
                if (par5Random.nextInt(rate) == 0) {
                    par1World.func_72921_c(par2, par3, par4, l + 1, 2);
                }
            }
        }
    }

    public void fertilize(World par1World, int par2, int par3, int par4) {
        int l = par1World.func_72805_g(par2, par3, par4) + MathHelper.func_76136_a(par1World.field_73012_v, 2, 5);
        if (l > 7) {
            l = 7;
        }
        par1World.func_72921_c(par2, par3, par4, l, 2);
    }

    public float getGrowthRate(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_) {
        float f = 1.0f;
        Block block = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
        Block block1 = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
        Block block2 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
        Block block3 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
        Block block4 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
        Block block5 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
        Block block6 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
        Block block7 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;
        for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; l++) {
            for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; i1++) {
                float f1 = 0.0f;
                if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, this)) {
                    f1 = 1.0f;
                    if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1)) {
                        f1 = 3.0f;
                    }
                }
                if (l != p_149864_2_ || i1 != p_149864_4_) {
                    f1 /= 4.0f;
                }
                f += f1;
            }
        }
        if (flag2 || (flag && flag1)) {
            f /= 2.0f;
        }
        return f;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 < 0 || par2 > 7) {
            par2 = 7;
        }
        return this.iconArray[par2];
    }

    public int func_149645_b() {
        return 1;
    }

    protected Item func_149866_i() {
        return mod_ecru_MapleTree.Item_AzukiBeans;
    }

    protected Item func_149865_P() {
        return mod_ecru_MapleTree.Item_AzukiBeans;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if (metadata >= 7) {
            ret.add(new ItemStack(func_149865_P(), 1, 0));
            for (int n = 0; n < 4 + fortune; n++) {
                if (world.field_73012_v.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(func_149865_P(), 1, 0));
                }
            }
        }
        return ret;
    }

    public Item func_149650_a(int i, Random r, int l) {
        if (i >= 7) {
            return func_149865_P();
        }
        return func_149866_i();
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return func_149866_i();
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconArray = new IIcon[8];
        for (int i = 0; i < this.iconArray.length; i++) {
            this.iconArray[i] = par1IconRegister.func_94245_a("mapletree:cardamon_" + i);
        }
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
    }
}
