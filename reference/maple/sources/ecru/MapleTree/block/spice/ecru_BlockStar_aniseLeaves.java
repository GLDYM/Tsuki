package ecru.MapleTree.block.spice;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ecru_BlockStar_aniseLeaves extends ecru_BlockSpiceLeavesBase {
    private Random random;
    IIcon[] tx_leaves;
    private int[] GrowthProbability;
    private String[] affinityLv1;
    private String[] affinityLv3;
    private int affinityMagnification;

    public ecru_BlockStar_aniseLeaves() {
        super(Material.field_151584_j, false);
        this.random = new Random();
        this.tx_leaves = new IIcon[8];
        this.GrowthProbability = new int[]{30, 20, 40, 0};
        this.affinityLv1 = new String[]{"Forest", "Jungle", "JungleHills", "JungleEdge", "Savanna", "Savanna Plateau", "Roofed Forest", "Birch Forest", "Birch Forest Hills"};
        this.affinityLv3 = new String[]{"Desert", "Ice Plains", "Ice Mountains", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Mesa", "Mesa Plateau F", "Mesa Plateau", "FrozenOcean", "FrozenRiver"};
        this.affinityMagnification = 4;
        func_149675_a(true);
    }

    @Override
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    @Override
    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockThinSapling);
    }

    @Override
    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        int affinity;
        BiomeGenBase biomegenbase = par1World.func_72807_a(par2, par4);
        String bName = biomegenbase.field_76791_y;
        boolean myPut = (par1World.func_72805_g(par2, par3, par4) & 4) == 4;
        int i = this.affinityMagnification;
        if (myPut || affinityCheck(3, bName)) {
            return;
        }
        if (affinityCheck(1, bName)) {
            affinity = 1;
        } else {
            affinity = this.affinityMagnification;
        }
        int l = par1World.func_72805_g(par2, par3, par4);
        int meta = l & 3;
        if (meta < 3) {
            int rate = this.GrowthProbability[meta] * affinity;
            if (par5Random.nextInt(rate) == 0) {
                par1World.func_72921_c(par2, par3, par4, (l & 12) | (meta + 1), 2);
            }
        }
        super.func_149674_a(par1World, par2, par3, par4, par5Random);
    }

    @Override
    protected int func_150123_b(int p_150123_1_) {
        return 20;
    }

    private int damageDropped_Sapling() {
        return 3;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        int chance = func_150123_b(metadata);
        if (fortune > 0) {
            chance -= 2 << fortune;
            if (chance < 10) {
                chance = 10;
            }
        }
        if (world.field_73012_v.nextInt(chance) == 0) {
            ret.add(new ItemStack(func_149650_a(metadata, world.field_73012_v, fortune), 1, damageDropped_Sapling()));
        }
        return ret;
    }

    @Override
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

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        if (world.field_72995_K) {
            return true;
        }
        if (!world.field_72995_K && entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() == Item.func_150898_a(this)) {
            return false;
        }
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 3) == 3) {
            dropBlockAsItemSpace(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_star_aniseFruit, 1, 0));
            world.func_72921_c(i, j, k, meta & 12, 3);
            world.func_72908_a(i, j, k, "random.pop", 0.2f, (((this.random.nextFloat() - this.random.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
            return true;
        }
        return true;
    }

    @Override
    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        super.func_149690_a(world, i, j, k, l, m, 0);
        if ((l & 3) == 3) {
            func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_star_aniseFruit, 1, 0));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_leaves[0] = par1IconRegister.func_94245_a("mapletree:star_aniseLeaves_0");
        this.tx_leaves[1] = par1IconRegister.func_94245_a("mapletree:star_aniseLeavesFast_0");
        this.tx_leaves[2] = par1IconRegister.func_94245_a("mapletree:star_aniseLeaves_1");
        this.tx_leaves[3] = par1IconRegister.func_94245_a("mapletree:star_aniseLeavesFast_1");
        this.tx_leaves[4] = par1IconRegister.func_94245_a("mapletree:star_aniseLeaves_2");
        this.tx_leaves[5] = par1IconRegister.func_94245_a("mapletree:star_aniseLeavesFast_2");
        this.tx_leaves[6] = par1IconRegister.func_94245_a("mapletree:star_aniseLeaves_3");
        this.tx_leaves[7] = par1IconRegister.func_94245_a("mapletree:star_aniseLeavesFast_3");
    }

    private boolean affinityCheck(int lv, String n) {
        return super.affinityCheck(lv, n, this.affinityLv1, this.affinityLv3);
    }
}
