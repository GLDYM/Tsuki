package ecru.MapleTree.block;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockEggplant extends BlockCrops {
    public static IIcon[] tx_vine;
    public static IIcon tx_eggplant;
    private Random random = new Random();

    public ecru_BlockEggplant() {
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    private boolean fieldCheck(Block id) {
        if (id == Blocks.field_150458_ak) {
            return true;
        }
        return false;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block dbid = world.func_147439_a(i, j - 1, k);
        int meta = world.func_72805_g(i, j - 1, k);
        if (fieldCheck(dbid)) {
            return true;
        }
        if (dbid == this && meta >= 4) {
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
        boolean light;
        int tower;
        if (world.func_72957_l(i, j + 1, k) >= 9) {
            light = true;
        } else {
            light = false;
        }
        int meta = world.func_72805_g(i, j, k);
        if (meta < 7) {
            if (world.func_147439_a(i, j - 1, k) == this && world.func_147439_a(i, j - 2, k) == Blocks.field_150458_ak) {
                tower = 2;
            } else {
                tower = 1;
            }
            float var7 = getGrowthRate(world, i, j, k, tower);
            if (random.nextInt(((int) (25.0f / var7)) + 1) == 0 && light) {
                int meta2 = meta + 1;
                world.func_72921_c(i, j, k, meta2, 3);
                if (meta2 == 4 && world.func_147439_a(i, j - 1, k) == Blocks.field_150458_ak && world.func_147439_a(i, j + 1, k) == Blocks.field_150350_a) {
                    world.func_147465_d(i, j + 1, k, this, 1, 3);
                }
            }
        }
    }

    private float getGrowthRate(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_, int t) {
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
                if (p_149864_1_.func_147439_a(l, p_149864_3_ - t, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - t, i1, ForgeDirection.UP, this)) {
                    f1 = 1.0f;
                    if (p_149864_1_.func_147439_a(l, p_149864_3_ - t, i1).isFertile(p_149864_1_, l, p_149864_3_ - t, i1)) {
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
        return (par2 < 0 || par2 > 7) ? tx_vine[7] : tx_vine[par2 & 7];
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderEggplantID;
    }

    protected Item func_149866_i() {
        return mod_ecru_MapleTree.Item_eggplantSeeds;
    }

    protected Item func_149865_P() {
        return mod_ecru_MapleTree.Item_eggplant;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if (metadata >= 7) {
            ret.add(new ItemStack(func_149865_P(), 1, 0));
            for (int n = 0; n < 3 + fortune; n++) {
                if (world.field_73012_v.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(func_149865_P(), 1, 0));
                }
            }
            ret.add(new ItemStack(func_149866_i(), world.field_73012_v.nextInt(2) + 1, 0));
        }
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

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        if (par1IBlockAccess.func_72805_g(i, j, k) == 0) {
            func_149676_a(0.15625f, 0.0f, 0.15625f, 0.84375f, 0.15625f, 0.84375f);
        } else {
            func_149676_a(0.15625f, 0.0f, 0.15625f, 0.84375f, 1.0f, 0.84375f);
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public IIcon getEggplantIcon(int i) {
        return tx_eggplant;
    }

    public int getEggplantNum(int i) {
        int num = 0;
        if (i >= 5 && i <= 7) {
            num = i - 5;
        }
        return num;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_vine = new IIcon[8];
        tx_vine[0] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_0");
        tx_vine[1] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_1");
        tx_vine[2] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_2");
        tx_vine[3] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_3");
        tx_vine[4] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_3");
        tx_vine[5] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_3");
        tx_vine[6] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_3");
        tx_vine[7] = par1IconRegister.func_94245_a("mapletree:eggplantLeaf_3");
        tx_eggplant = par1IconRegister.func_94245_a("mapletree:eggplantBlock");
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
    }
}
