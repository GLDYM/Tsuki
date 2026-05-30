package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockFallenLeavesFire extends Block {
    private int[] png;
    private Random random;
    private String[] inItem;
    private String[] outItem;
    private Object[] dropItem;
    public static IIcon[] tx_fire = new IIcon[4];

    public ecru_BlockFallenLeavesFire() {
        super(Material.field_151581_o);
        this.png = new int[5];
        this.random = new Random();
        this.inItem = new String[]{"item.item.porkchopRaw", "item.item.chickenRaw", "item.item.beefRaw", "item.item.fish.cod.raw", "item.item.potato", "item.item.mapletree:chestnut.0"};
        this.outItem = new String[]{"item.item.porkchopCooked", "item.item.chickenCooked", "item.item.beefCooked", "item.item.fish.cod.cooked", "item.item.potatoBaked", "item.item.mapletree:roastChestnuts.0", "item.tile.mapletree:Bonfire.0", "item.tile.mapletree:Bonfire.1", "item.tile.mapletree:Bonfire.2", "item.tile.mapletree:Bonfire.3", "item.tile.blockIron", "item.tile.mapletree:SyrupCauldron.0", "item.tile.mapletree:SyrupCauldron.1", "item.tile.mapletree:SyrupCauldron.2", "item.tile.mapletree:SyrupCauldron.3", "item.tile.mapletree:SyrupCauldron.4", "item.tile.mapletree:SyrupCauldron.5", "item.tile.mapletree:SyrupCauldron.6", "item.tile.mapletree:SyrupCauldron.7", "item.tile.mapletree:SyrupCauldron.8", "item.tile.mapletree:SyrupCauldron.9", "item.tile.mapletree:SyrupCauldron.10"};
        this.dropItem = new Object[]{Items.field_151157_am, Items.field_151077_bg, Items.field_151083_be, Items.field_151101_aQ, Items.field_151168_bH, mod_ecru_MapleTree.Item_roastChestnuts};
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.png[0] = 16;
        this.png[1] = 18;
        this.png[2] = 20;
        this.png[3] = 22;
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderBonfireID;
    }

    public boolean func_149686_d() {
        return true;
    }

    public int func_149635_D() {
        return 16777215;
    }

    public int func_149741_i(int i) {
        return 16777215;
    }

    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k) {
        return 16777215;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.315f, 1.0f);
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        Block i = par1World.func_147439_a(par2, par3 - 1, par4);
        if (i == Blocks.field_150350_a) {
            return false;
        }
        if (i != Blocks.field_150359_w && i != Blocks.field_150442_at && i != mod_ecru_MapleTree.blockMapleLeaves && !i.func_149662_c()) {
            return false;
        }
        return par1World.func_147439_a(par2, par3 - 1, par4).func_149688_o().func_76230_c();
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

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public Item func_149650_a(int i, Random r, int l) {
        return null;
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public int func_149738_a(World par1World) {
        return 10;
    }

    public void func_149674_a(World par1World, int i, int j, int k, Random par5Random) {
        if (par1World.func_72896_J() && (par1World.func_72951_B(i, j, k) || par1World.func_72951_B(i - 1, j, k) || par1World.func_72951_B(i + 1, j, k) || par1World.func_72951_B(i, j, k - 1) || par1World.func_72951_B(i, j, k + 1))) {
            int meta = par1World.func_72805_g(i, j, k);
            par1World.func_147465_d(i, j, k, mod_ecru_MapleTree.blockFallenLeaves, meta & 7, 3);
        } else {
            downBlock(par1World, i, j, k, par5Random);
        }
    }

    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    public int func_149701_w() {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        return tx_fire[2];
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public int func_149692_a(int i) {
        return i & 3;
    }

    public void func_149726_b(World world, int i, int j, int k) {
        world.func_147464_a(i, j, k, mod_ecru_MapleTree.blockFallenLeavesFire, 10);
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return true;
    }

    public void func_149724_b(World world, int i, int j, int k, Entity entity) {
        super.func_149724_b(world, i, j, k, entity);
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par5Random.nextInt(24) == 0) {
            par1World.func_72980_b(par2 + 0.5d, par3 + 0.5d, par4 + 0.5d, "fire.fire", 1.0f + par5Random.nextFloat(), (par5Random.nextFloat() * 0.7f) + 0.3f, true);
        }
        par1World.func_72869_a("smoke", par2 + 0.5d, par3 + 0.7d, par4 + 0.5d, 0.0d, 0.0d, 0.0d);
        int m = par1World.func_72805_g(par2, par3 + 1, par4);
        BlockCauldron blockCauldronFunc_147439_a = par1World.func_147439_a(par2, par3 + 1, par4);
        if (m > 0 && blockCauldronFunc_147439_a == Blocks.field_150383_bp) {
            par1World.func_72869_a("explode", par2 + 0.5d, par3 + 1.8d, par4 + 0.5d, 0.0d, 0.0d, 0.0d);
            par1World.func_72869_a("splash", par2 + 0.5d, par3 + 1.8d, par4 + 0.5d, 0.0d, 0.0d, 0.0d);
        }
        int[] px = {0, -1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] pz = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
        int[] c1 = {0, 2, 2, 2, 4, 5, 6, 4, 8, 6};
        int[] c2 = {0, 4, 2, 6, 4, 5, 6, 8, 8, 8};
        if (blockCauldronFunc_147439_a == Blocks.field_150339_S) {
            for (int p = 1; p <= 9; p++) {
                if (par1World.func_147439_a(par2 + px[p], par3 + 1, par4 + pz[p]) == Blocks.field_150339_S && (par1World.func_147439_a(par2 + px[c1[p]], par3 + 1, par4 + pz[c1[p]]) == Blocks.field_150339_S || par1World.func_147439_a(par2 + px[c2[p]], par3 + 1, par4 + pz[c2[p]]) == Blocks.field_150339_S)) {
                    if (par1World.func_147439_a(par2 + px[p], par3 + 2, par4 + pz[p]) == Blocks.field_150355_j || par1World.func_147439_a(par2 + px[p], par3 + 2, par4 + pz[p]) == Blocks.field_150358_i) {
                        if (this.random.nextInt(2) == 0) {
                            par1World.func_72869_a("explode", par2 + px[p] + 0.5d, par3 + 3.4d, par4 + pz[p] + 0.5d, 0.0d, 0.0d, 0.0d);
                            par1World.func_72869_a("bubble", par2 + px[p] + 0.5d, par3 + 2.2d, par4 + pz[p] + 0.5d, 0.0d, 0.0d, 0.0d);
                            par1World.func_72869_a("bubble", par2 + px[p] + 0.5d, par3 + 2.6d, par4 + pz[p] + 0.5d, 0.0d, 0.0d, 0.0d);
                        }
                    } else if (this.random.nextInt(2) == 0) {
                        par1World.func_72869_a("smoke", par2 + px[p] + this.random.nextDouble(), par3 + 2.0d, par4 + pz[p] + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
                    }
                }
            }
        }
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        if (world.field_72995_K) {
            return;
        }
        int id = -1;
        int id2 = -1;
        int m = 0;
        while (true) {
            if (m >= this.inItem.length) {
                break;
            }
            if (!entity.func_70005_c_().equals(this.inItem[m])) {
                m++;
            } else {
                id = m;
                break;
            }
        }
        int m2 = 0;
        while (true) {
            if (m2 >= this.outItem.length) {
                break;
            }
            if (!entity.func_70005_c_().equals(this.outItem[m2])) {
                m2++;
            } else {
                id2 = m2;
                break;
            }
        }
        if (id == -1) {
            if (id2 == -1) {
                entity.func_70015_d(1);
            }
        } else if (entity.func_70089_S()) {
            EntityItem eItem = (EntityItem) entity;
            int ss = eItem.func_92059_d().field_77994_a;
            if ((this.dropItem[id] instanceof Item) && id >= 0 && id <= 4) {
                EntityItem ei = new EntityItem(world, i, j, k, new ItemStack((Item) this.dropItem[id], ss, 0));
                world.func_72838_d(ei);
            } else if (id == 5) {
                world.func_72876_a((Entity) null, i + 0.5d, j + 0.5d, k + 0.5d, 0.0f, false);
                EntityItem ei2 = new EntityItem(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_roastChestnuts, ss, 0));
                world.func_72838_d(ei2);
            }
            entity.func_70106_y();
        }
    }

    private boolean downBlock(World world, int i, int j, int k, Random random) {
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 8) == 0) {
            return true;
        }
        Block bid = world.func_147439_a(i, j - 1, k);
        if (bid == Blocks.field_150347_e && random.nextInt(6) == 0) {
            world.func_147465_d(i, j - 1, k, Blocks.field_150348_b, 0, 3);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
        }
        if (bid == Blocks.field_150351_n && random.nextInt(6) == 0) {
            world.func_147465_d(i, j - 1, k, Blocks.field_150354_m, 0, 3);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
        }
        if (bid == Blocks.field_150354_m && random.nextInt(6) == 0) {
            world.func_147465_d(i, j - 1, k, Blocks.field_150351_n, 0, 3);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
        }
        if (bid == Blocks.field_150435_aG && random.nextInt(6) == 0) {
            world.func_147465_d(i, j - 1, k, Blocks.field_150336_V, 0, 3);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
        }
        if (bid == Blocks.field_150425_aM && random.nextInt(6) == 0) {
            world.func_147465_d(i, j - 1, k, Blocks.field_150391_bh, 0, 3);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
        }
        if (bid == Blocks.field_150343_Z && random.nextInt(6) == 0) {
            world.func_147465_d(i, j - 1, k, Blocks.field_150365_q, 0, 3);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            world.func_72908_a(i, j, k, "step.gravel", 1.0f, 1.2f);
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public static IIcon texId(int i) {
        return tx_fire[i];
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_fire[0] = par1IconRegister.func_94245_a("mapletree:fire0");
        tx_fire[1] = par1IconRegister.func_94245_a("mapletree:fire0");
        tx_fire[2] = par1IconRegister.func_94245_a("mapletree:bonfire_wood");
        tx_fire[3] = par1IconRegister.func_94245_a("mapletree:bonfireTable");
    }
}
