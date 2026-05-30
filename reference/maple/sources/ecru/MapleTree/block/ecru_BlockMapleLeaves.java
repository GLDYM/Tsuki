package ecru.MapleTree.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntityMapleLeafFX;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ecru_BlockMapleLeaves extends BlockLeavesBase implements IShearable {
    int[] adjacentTreeBlocks;
    IIcon[] tx_leaves;
    public static IIcon tx_error;

    public ecru_BlockMapleLeaves() {
        super(Material.field_151584_j, false);
        this.tx_leaves = new IIcon[8];
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(new ItemStack(this, 1, world.func_72805_g(x, y, z) & 3));
        return ret;
    }

    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    protected ItemStack func_149644_j(int par1) {
        return new ItemStack(this, 1, par1 & 3);
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

    public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
        int i1 = 1 + 1;
        if (world.func_72904_c(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
            for (int j1 = -1; j1 <= 1; j1++) {
                for (int k1 = -1; k1 <= 1; k1++) {
                    for (int l1 = -1; l1 <= 1; l1++) {
                        Block i2 = world.func_147439_a(i + j1, j + k1, k + l1);
                        if (i2 == mod_ecru_MapleTree.blockMapleLeaves) {
                            int j2 = world.func_72805_g(i + j1, j + k1, k + l1);
                            world.func_72921_c(i + j1, j + k1, k + l1, j2 | 8, 3);
                        }
                    }
                }
            }
        }
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        if (world.field_72995_K) {
            return;
        }
        int l = world.func_72805_g(i, j, k);
        if ((l & 8) != 0 && (l & 4) == 0) {
            int i1 = 4 + 1;
            int j1 = 32 * 32;
            int k1 = 32 / 2;
            if (this.adjacentTreeBlocks == null) {
                this.adjacentTreeBlocks = new int[32 * 32 * 32];
            }
            if (world.func_72904_c(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
                for (int l1 = -4; l1 <= 4; l1++) {
                    for (int k2 = -4; k2 <= 4; k2++) {
                        for (int i3 = -4; i3 <= 4; i3++) {
                            Block k3 = world.func_147439_a(i + l1, j + k2, k + i3);
                            if (k3 == mod_ecru_MapleTree.blockMapleWood || k3 == mod_ecru_MapleTree.blockMapleWoodSyrup) {
                                this.adjacentTreeBlocks[((l1 + k1) * j1) + ((k2 + k1) * 32) + i3 + k1] = 0;
                            } else if (k3 == mod_ecru_MapleTree.blockMapleLeaves) {
                                this.adjacentTreeBlocks[((l1 + k1) * j1) + ((k2 + k1) * 32) + i3 + k1] = -2;
                            } else {
                                this.adjacentTreeBlocks[((l1 + k1) * j1) + ((k2 + k1) * 32) + i3 + k1] = -1;
                            }
                        }
                    }
                }
                for (int i2 = 1; i2 <= 4; i2++) {
                    for (int l2 = -4; l2 <= 4; l2++) {
                        for (int j3 = -4; j3 <= 4; j3++) {
                            for (int l3 = -4; l3 <= 4; l3++) {
                                if (this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1) * 32) + l3 + k1] == i2 - 1) {
                                    if (this.adjacentTreeBlocks[(((l2 + k1) - 1) * j1) + ((j3 + k1) * 32) + l3 + k1] == -2) {
                                        this.adjacentTreeBlocks[(((l2 + k1) - 1) * j1) + ((j3 + k1) * 32) + l3 + k1] = i2;
                                    }
                                    if (this.adjacentTreeBlocks[((l2 + k1 + 1) * j1) + ((j3 + k1) * 32) + l3 + k1] == -2) {
                                        this.adjacentTreeBlocks[((l2 + k1 + 1) * j1) + ((j3 + k1) * 32) + l3 + k1] = i2;
                                    }
                                    if (this.adjacentTreeBlocks[((l2 + k1) * j1) + (((j3 + k1) - 1) * 32) + l3 + k1] == -2) {
                                        this.adjacentTreeBlocks[((l2 + k1) * j1) + (((j3 + k1) - 1) * 32) + l3 + k1] = i2;
                                    }
                                    if (this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1 + 1) * 32) + l3 + k1] == -2) {
                                        this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1 + 1) * 32) + l3 + k1] = i2;
                                    }
                                    if (this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1) * 32) + ((l3 + k1) - 1)] == -2) {
                                        this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1) * 32) + ((l3 + k1) - 1)] = i2;
                                    }
                                    if (this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1) * 32) + l3 + k1 + 1] == -2) {
                                        this.adjacentTreeBlocks[((l2 + k1) * j1) + ((j3 + k1) * 32) + l3 + k1 + 1] = i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int j2 = this.adjacentTreeBlocks[(k1 * j1) + (k1 * 32) + k1];
            if (j2 >= 0) {
                world.func_72921_c(i, j, k, l & (-9), 3);
            } else {
                removeLeaves(world, i, j, k);
            }
        }
    }

    private void removeLeaves(World world, int i, int j, int k) {
        func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
        world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
    }

    public int func_149745_a(Random random) {
        return 0;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockMapleSapling);
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float f, int i1) {
        super.func_149690_a(world, i, j, k, l, f, i1);
        if (world.field_73012_v.nextInt(25) == 0) {
            Item id = func_149650_a(l, world.field_73012_v, i1);
            func_149642_a(world, i, j, k, new ItemStack(id, 1, func_149692_a(l)));
        }
        if (!world.field_72995_K && (l & 3) == 0 && world.field_73012_v.nextInt(200) == 0) {
            func_149642_a(world, i, j, k, new ItemStack(Items.field_151034_e, 1, 0));
        }
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public int func_149692_a(int i) {
        return i & 3;
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

    public boolean func_149686_d() {
        return true;
    }

    public int func_149701_w() {
        return 0;
    }

    public int func_149645_b() {
        return 0;
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

    @SideOnly(Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        int meta = iblockaccess.func_72805_g(i, j, k);
        if (mod_ecru_MapleTree.GraphicsLevel == 0) {
            return this.tx_leaves[(meta & 3) * 2];
        }
        if (mod_ecru_MapleTree.GraphicsLevel == 1) {
            return this.tx_leaves[((meta & 3) * 2) + 1];
        }
        return this.tx_leaves[((meta & 3) * 2) + (Blocks.field_150362_t.func_149662_c() ? 1 : 0)];
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
            if (i1 == mod_ecru_MapleTree.blockMapleLeaves) {
                return false;
            }
            return _shouldSideBeRendered(iblockaccess, i, j, k, l);
        }
        if (Blocks.field_150362_t.func_149662_c() && i1 == mod_ecru_MapleTree.blockMapleLeaves) {
            return false;
        }
        return _shouldSideBeRendered(iblockaccess, i, j, k, l);
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        Block id = world.func_147439_a(i, j, k);
        int meta = world.func_72805_g(i, j, k);
        if (id == mod_ecru_MapleTree.blockMapleLeaves) {
            world.func_72921_c(i, j, k, meta | 4, 3);
        }
    }

    public void func_149724_b(World world, int i, int j, int k, Entity entity) {
        super.func_149724_b(world, i, j, k, entity);
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (mod_ecru_MapleTree.FallenLeavesParticles && par5Random.nextInt(80) == 0 && par1World.func_147439_a(par2, par3 - 1, par4) == Blocks.field_150350_a) {
            int meta = par1World.func_72805_g(par2, par3, par4);
            ecru_EntityMapleLeafFX entityFX = new ecru_EntityMapleLeafFX(par1World, par2 + 0.5d, par3, par4 + 0.5d, 0.0d, 0.0d, 0.0d);
            switch (meta & 3) {
                case 0:
                default:
                    entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(1));
                    break;
                case 1:
                    entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(2));
                    break;
                case 2:
                    entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(3));
                    break;
                case 3:
                    entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(4));
                    break;
            }
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_leaves[0] = par1IconRegister.func_94245_a("mapletree:leaves_red");
        this.tx_leaves[1] = par1IconRegister.func_94245_a("mapletree:leaves_red_fast");
        this.tx_leaves[2] = par1IconRegister.func_94245_a("mapletree:leaves_yellow");
        this.tx_leaves[3] = par1IconRegister.func_94245_a("mapletree:leaves_yellow_fast");
        this.tx_leaves[4] = par1IconRegister.func_94245_a("mapletree:leaves_orange");
        this.tx_leaves[5] = par1IconRegister.func_94245_a("mapletree:leaves_orange_fast");
        this.tx_leaves[6] = par1IconRegister.func_94245_a("mapletree:leaves_lime");
        this.tx_leaves[7] = par1IconRegister.func_94245_a("mapletree:leaves_lime_fast");
        tx_error = par1IconRegister.func_94245_a("mapletree:error");
    }
}
