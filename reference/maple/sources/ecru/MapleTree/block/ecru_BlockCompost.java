package ecru.MapleTree.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntitySporeFX;
import ecru.MapleTree.common.ecru_mushroomList;
import ecru.MapleTree.item.ecru_ItemMushroom;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityCompost;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ecru_BlockCompost extends BlockContainer {
    private Random random;
    public static IIcon[] tx_mushroom = new IIcon[ecru_ItemMushroom.getMushroomNum()];
    public static IIcon[] tx_mushroom2 = new IIcon[ecru_ItemMushroom.getMushroomNum()];
    public static IIcon[] tx_otiba = new IIcon[3];
    private final int RATE1 = 7;
    private final int RATE2 = 5;
    private String[] affinityLv1;
    private String[] affinityLv3;
    private int affinityMagnification;

    public ecru_BlockCompost() {
        super(Material.field_151567_E);
        this.random = new Random();
        this.RATE1 = 7;
        this.RATE2 = 5;
        this.affinityLv1 = new String[]{"Swampland"};
        this.affinityLv3 = new String[]{"Desert", "Ice Plains", "Ice Mountains", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Mesa", "Mesa Plateau F", "Mesa Plateau", "FrozenOcean", "FrozenRiver"};
        this.affinityMagnification = 3;
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        Block block = par1World.func_147439_a(par2, par3 - 1, par4);
        block.func_149688_o();
        if ((par1World.func_72805_g(par2, par3, par4) & 8) == 8) {
            return true;
        }
        if (!block.func_149721_r() || block == Blocks.field_150350_a || block.func_149688_o() == Material.field_151586_h || block.func_149688_o() == Material.field_151587_i) {
            return false;
        }
        return true;
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        canFallenLeavesStay(par1World, par2, par3, par4);
    }

    private boolean canFallenLeavesStay(World par1World, int par2, int par3, int par4) {
        if (!func_149742_c(par1World, par2, par3, par4)) {
            func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
            par1World.func_147465_d(par2, par3, par4, Blocks.field_150350_a, 0, 3);
            return false;
        }
        return true;
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        int affinity;
        if (world.field_72995_K) {
            return;
        }
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 8) == 8 || world.func_72957_l(i, j + 1, k) >= 15) {
            return;
        }
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
        int rate1 = 7;
        int rate2 = 5;
        if (world.func_72896_J()) {
            rate1 = 3;
            rate2 = 2;
        }
        if (meta == 0) {
            if (random.nextInt(rate1 * affinity) == 0 && checkUnderBlock(world, i, j, k)) {
                ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
                int type = random.nextInt(ecru_ItemMushroom.getMushroomNum());
                int condition = mu[type].e_condition;
                boolean orCheck = mu[type].e_orCheck;
                if (condition != 0) {
                    boolean[] ret = new boolean[4];
                    ret[0] = (condition & 1) == 1 ? checkLogRange(world, i, j, k, 0) : !orCheck;
                    ret[1] = ((condition >> 1) & 1) == 1 ? checkLogRange(world, i, j, k, 1) : !orCheck;
                    ret[2] = ((condition >> 2) & 1) == 1 ? checkLogDown(world, i, j, k, 0) : !orCheck;
                    ret[3] = ((condition >> 3) & 1) == 1 ? checkLogDown(world, i, j, k, 1) : !orCheck;
                    boolean r = orCheck ? ret[0] | ret[1] | ret[2] | ret[3] : ret[0] & ret[1] & ret[2] & ret[3];
                    if (!r) {
                        return;
                    }
                } else if (world.func_147439_a(i, j - 1, k) instanceof BlockLog) {
                    return;
                }
                TileEntity _tile = world.func_147438_o(i, j, k);
                if (_tile instanceof ecru_TileEntityCompost) {
                    ecru_TileEntityCompost tile = (ecru_TileEntityCompost) _tile;
                    tile.setMushroomType(type);
                    world.func_72921_c(i, j, k, 1, 3);
                    return;
                }
                return;
            }
            return;
        }
        if ((meta & 7) >= 1 && (meta & 7) < 7 && random.nextInt(rate2 * affinity) == 0) {
            int g = (meta & 7) + 1;
            world.func_72921_c(i, j, k, (meta & 8) | g, 2);
        }
    }

    boolean checkUnderBlock(World world, int i, int j, int k) {
        Block ub = world.func_147439_a(i, j - 1, k);
        if (ub == Blocks.field_150391_bh || ub == Blocks.field_150349_c || ub == Blocks.field_150346_d || (ub instanceof BlockLog)) {
            return true;
        }
        return false;
    }

    boolean checkLogDown(World world, int i, int j, int k, int logType) {
        if (logType == 0) {
            if (world.func_147439_a(i, j - 1, k) == Blocks.field_150364_r && (world.func_72805_g(i, j - 1, k) & 3) == 1) {
                return true;
            }
            return false;
        }
        if (!(world.func_147439_a(i, j - 1, k) instanceof BlockLog)) {
            return false;
        }
        if (world.func_147439_a(i, j - 1, k) != Blocks.field_150364_r || (world.func_72805_g(i, j - 1, k) & 3) != 1) {
            return true;
        }
        return false;
    }

    boolean checkLogRange(World world, int i, int j, int k, int logType) {
        if (world.func_147439_a(i, j - 1, k) instanceof BlockLog) {
            return false;
        }
        for (int x = i - 4; x <= i + 4; x++) {
            for (int z = k - 4; z <= k + 4; z++) {
                for (int y = j - 1; y <= j + 1; y++) {
                    if (logType == 0) {
                        if (world.func_147439_a(x, y, z) == Blocks.field_150364_r && (world.func_72805_g(x, y, z) & 3) == 1) {
                            return true;
                        }
                    } else if ((world.func_147439_a(x, y, z) instanceof BlockLog) && (world.func_147439_a(x, y, z) != Blocks.field_150364_r || (world.func_72805_g(x, y, z) & 3) != 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        return tx_otiba[2];
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderCompostID;
    }

    public int func_149692_a(int i) {
        return i;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        int meta = world.func_72805_g(i, j, k);
        if (meta == 7) {
            boolean flg = world.func_147439_a(i, j - 1, k) == Blocks.field_150391_bh;
            int dp = this.random.nextInt(flg ? 3 : 1) + 1 + (flg ? 1 : 0);
            int id = getMushroomType(world, i, j, k);
            if (id >= 0) {
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_mushroom, dp, id));
                world.func_72921_c(i, j, k, 0, 3);
                return true;
            }
            return true;
        }
        if ((meta & 8) != 8 || (meta & 7) <= 0) {
        }
        return false;
    }

    public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
        int id;
        if ((par6 & 8) == 0) {
            EntityItem ei = new EntityItem(world, i, j + 0.5d, k, new ItemStack(this, 1, 0));
            world.func_72838_d(ei);
        }
        if ((((par6 & 8) == 0 && (par6 & 7) == 7) || ((par6 & 8) == 8 && (par6 & 7) >= 1)) && (id = getMushroomType(world, i, j, k)) >= 0) {
            boolean flg = world.func_147439_a(i, j - 1, k) == Blocks.field_150391_bh;
            int dp = this.random.nextInt(flg ? 3 : 1) + 1 + (flg ? 1 : 0);
            EntityItem ei2 = new EntityItem(world, i, j + 0.5d, k, new ItemStack(mod_ecru_MapleTree.Item_mushroom, (par6 & 8) == 0 ? dp : 1, id));
            world.func_72838_d(ei2);
        }
        super.func_149749_a(world, i, j, k, par5, par6);
    }

    public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int meta, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        return ret;
    }

    public void func_149724_b(World world, int i, int j, int k, Entity entity) {
        super.func_149724_b(world, i, j, k, entity);
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        if ((meta & 8) == 8) {
            int a = Math.abs((i + i) % 10) + 1;
            int b = Math.abs((k + k) % 10) + 1;
            int tmpx = Math.abs((((i * b) + j) + ((k + k) * b)) % 10);
            int tmpz = Math.abs(((((i + i) * a) + j) + (k * a)) % 10);
            float ii = (((tmpx / 10.0f) - 0.5f) * 0.8f) + 0.6f;
            float kk = (((tmpz / 10.0f) - 0.5f) * 0.8f) + 0.6f;
            float jj = (meta & 7) == 7 ? 0.7f : 0.5f;
            func_149676_a(ii - 0.25f, 0.0f, kk - 0.25f, ii + 0.25f, jj, kk + 0.25f);
            return;
        }
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        if ((world.func_72805_g(i, j, k) & 8) == 8) {
            return null;
        }
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World par1World, int i, int j, int k, Random par5Random) {
        if (mod_ecru_MapleTree.mushroomParticle && par5Random.nextInt(9) == 0 && (par1World.func_72805_g(i, j, k) & 7) == 7) {
            int a = Math.abs((i + i) % 10) + 1;
            int b = Math.abs((k + k) % 10) + 1;
            int tmpx = Math.abs((((i * b) + j) + ((k + k) * b)) % 10);
            int tmpz = Math.abs(((((i + i) * a) + j) + (k * a)) % 10);
            double ii = i + (((tmpx / 10.0d) - 0.5d) * 0.8d) + 0.6d;
            double kk = k + (((tmpz / 10.0d) - 0.5d) * 0.8d) + 0.6d;
            ecru_EntitySporeFX entityFX = new ecru_EntitySporeFX(par1World, ii, j + 0.5d, kk, 0.0d, 0.0d, 0.0d);
            entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(9));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if ((meta & 7) == 7) {
            return 3;
        }
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_otiba[0] = par1IconRegister.func_94245_a("mapletree:legend_fl0");
        tx_otiba[1] = par1IconRegister.func_94245_a("mapletree:legend_fl1");
        tx_otiba[2] = par1IconRegister.func_94245_a("mapletree:legend_fl2");
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        for (int i = 0; i < ecru_ItemMushroom.getMushroomNum(); i++) {
            tx_mushroom[i] = par1IconRegister.func_94245_a("mapletree:" + mu[i].e_itemName + "_s");
            tx_mushroom2[i] = par1IconRegister.func_94245_a("mapletree:" + mu[i].e_itemName + "");
        }
    }

    private boolean affinityCheck(int lv, String n) {
        if (lv == 1) {
            for (int i = 0; i < this.affinityLv1.length; i++) {
                if (this.affinityLv1[i].equals(n)) {
                    return true;
                }
            }
            return false;
        }
        if (lv == 3) {
            for (int i2 = 0; i2 < this.affinityLv3.length; i2++) {
                if (this.affinityLv3[i2].equals(n)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private int getMushroomType(World world, int i, int j, int k) {
        TileEntity _tile = world.func_147438_o(i, j, k);
        if (_tile instanceof ecru_TileEntityCompost) {
            ecru_TileEntityCompost tile = (ecru_TileEntityCompost) _tile;
            return tile.getMushroomType();
        }
        return -1;
    }

    public ecru_TileEntityCompost func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new ecru_TileEntityCompost();
    }
}
