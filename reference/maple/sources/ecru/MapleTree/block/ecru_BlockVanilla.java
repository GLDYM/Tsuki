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
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockVanilla extends BlockBush {
    public static IIcon[] tx_vine;
    public static IIcon tx_wood;
    private Random random;
    private static int stackMax = 5;
    private final int GrowthRate1 = 5;
    private final int GrowthRate2 = 9;

    public ecru_BlockVanilla() {
        super(Material.field_151575_d);
        this.random = new Random();
        this.GrowthRate1 = 5;
        this.GrowthRate2 = 9;
        func_149675_a(true);
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        func_149711_c(0.0f);
        func_149672_a(field_149779_h);
        func_149649_H();
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
        int meta = world.func_72805_g(i, j, k);
        int dmeta = world.func_72805_g(i, j - 1, k);
        Block dbid = world.func_147439_a(i, j - 1, k);
        if (((meta & 7) == 1 || (meta & 7) == 2) && (dmeta & 7) == 0 && dbid == this) {
            world.func_72921_c(i, j, k, meta & 8, 3);
            return true;
        }
        return true;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        boolean light = false;
        if (world.func_72957_l(i, j + 1, k) >= 9) {
            light = true;
        }
        if (!light || (world.func_72805_g(i, j, k) & 7) != 2 || random.nextInt(5) != 0) {
            if (light && (world.func_72805_g(i, j, k) & 7) >= 3 && (world.func_72805_g(i, j, k) & 7) < 7 && random.nextInt(9) == 0) {
                int meta = world.func_72805_g(i, j, k) & 7;
                int m = world.func_72805_g(i, j, k) & 8;
                world.func_72921_c(i, j, k, m | (meta + 1), 3);
                return;
            } else {
                if ((world.func_72805_g(i, j, k) & 7) == 1) {
                    if ((world.func_147439_a(i, j + 1, k) == this && (world.func_72805_g(i, j + 1, k) & 7) != 1 && (world.func_72805_g(i, j + 1, k) & 7) != 2) || world.func_147439_a(i, j + 1, k) != this) {
                        int meta2 = world.func_72805_g(i, j, k);
                        world.func_72921_c(i, j, k, (meta2 & 8) | 2, 3);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        int meta3 = world.func_72805_g(i, j + 1, k);
        if (world.func_147439_a(i, j + 1, k) == this && (meta3 & 7) < 3) {
            world.func_72921_c(i, j + 1, k, (meta3 & 8) | 2, 3);
            int m2 = world.func_72805_g(i, j, k);
            world.func_72921_c(i, j, k, (m2 & 8) | 1, 3);
            return;
        }
        int y = j;
        int m3 = world.func_72805_g(i, y, k);
        world.func_147465_d(i, y, k, this, (m3 & 8) | 3, 3);
        while (true) {
            y--;
            if (world.func_147439_a(i, y, k) == this) {
                int m4 = world.func_72805_g(i, y, k);
                world.func_147465_d(i, y, k, this, (m4 & 8) | 3, 3);
            } else {
                return;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return (par2 == 0 || par2 == 8) ? tx_wood : tx_vine[par2 & 7];
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderVanillaID;
    }

    protected Item getSeedItem() {
        return mod_ecru_MapleTree.Item_vanillaSeed;
    }

    protected Item getCropItem() {
        return mod_ecru_MapleTree.Item_vanillaSheath;
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        super.func_149690_a(world, i, j, k, l, m, 0);
        int meta = l & 7;
        switch (meta) {
            case 0:
            case 1:
            case 2:
                break;
            default:
                if (meta >= 7) {
                    int dp = this.random.nextInt(3) + 2;
                    func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_vanillaSheath, dp, 0));
                    break;
                }
                break;
        }
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockVanilla);
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
        if ((meta & 7) == 0 && fieldCheck(dbid) && entityplayer.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_vanillaSeed) {
            world.func_72921_c(i, j, k, (meta & 8) | 2, 3);
            if (!entityplayer.field_71075_bZ.field_75098_d) {
                entityplayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (entityplayer.func_71045_bC().func_77973_b() instanceof ItemShears) {
            world.func_72921_c(i, j, k, (meta & 8) | 3, 3);
            world.func_72908_a(i, j, k, "step.grass", 1.0f, 1.2f);
            if ((meta & 7) >= 7) {
                int dp = this.random.nextInt(3) + 2;
                func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_vanillaSheath, dp, 0));
            }
            if ((meta & 7) != 0 && !entityplayer.field_71075_bZ.field_75098_d) {
                ItemStack iInfo = entityplayer.func_71045_bC();
                iInfo.func_77972_a(1, entityplayer);
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.15625f, 0.0f, 0.15625f, 0.84375f, 1.0f, 0.84375f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_vine = new IIcon[8];
        for (int i = 0; i < tx_vine.length; i++) {
            tx_vine[i] = par1IconRegister.func_94245_a("mapletree:vanilla_" + i);
        }
        tx_wood = par1IconRegister.func_94245_a("mapletree:deco_wood");
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, world.func_72805_g(x, y, z) & 8);
    }
}
