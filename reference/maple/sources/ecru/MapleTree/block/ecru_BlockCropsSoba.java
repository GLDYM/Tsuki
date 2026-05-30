package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.block.spice.ecru_BlockCropsBase;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ecru_BlockCropsSoba extends ecru_BlockCropsBase {

    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    private int[] GrowthProbability = {3, 3, 3, 3, 8, 20, 8, 0};

    public ecru_BlockCropsSoba() {
        func_149675_a(true);
        func_149647_a((CreativeTabs) null);
        func_149711_c(0.0f);
        func_149672_a(field_149779_h);
        func_149649_H();
    }

    @Override
    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        BiomeGenBase biomegenbase = par1World.func_72807_a(par2, par4);
        String str = biomegenbase.field_76791_y;
        if (par1World.func_72957_l(par2, par3 + 1, par4) >= 9) {
            int l = par1World.func_72805_g(par2, par3, par4);
            if (l < 7) {
                float f = super.getGrowthRate(par1World, par2, par3, par4);
                int rate = (((int) (25.0f / f)) + 1 + this.GrowthProbability[l & 7]) * 1;
                if (par5Random.nextInt(rate) == 0) {
                    par1World.func_72921_c(par2, par3, par4, l + 1, 2);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 < 0 || par2 > 7) {
            par2 = 7;
        }
        return this.iconArray[par2];
    }

    @Override
    protected Item func_149866_i() {
        return mod_ecru_MapleTree.Item_buckwheatSeed;
    }

    @Override
    protected Item func_149865_P() {
        return mod_ecru_MapleTree.Item_buckwheatSeed;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if (metadata >= 7) {
            ret.add(new ItemStack(func_149865_P(), 1, 0));
            for (int n = 0; n < 3 + fortune; n++) {
                if (world.field_73012_v.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(func_149865_P(), 1, 0));
                }
            }
        }
        return ret;
    }

    @Override
    public Item func_149650_a(int i, Random r, int l) {
        if (i >= 7) {
            return func_149865_P();
        }
        return func_149866_i();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return func_149866_i();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconArray = new IIcon[8];
        for (int i = 0; i < this.iconArray.length; i++) {
            this.iconArray[i] = par1IconRegister.func_94245_a("mapletree:sobaCrops_" + i);
        }
    }

    @Override
    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
    }
}
