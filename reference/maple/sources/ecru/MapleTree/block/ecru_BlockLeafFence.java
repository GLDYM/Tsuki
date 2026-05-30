package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockLeafFence extends Block {
    private ecru_numericConstant nc;
    private Random random;
    IIcon tx_fallen_leaves_red;
    IIcon tx_fallen_leaves_yellow;
    IIcon tx_fallen_leaves_orenge;
    IIcon tx_fallen_leaves_lime;
    IIcon tx_fallen_leaves_red_fast;
    IIcon tx_fallen_leaves_yellow_fast;
    IIcon tx_fallen_leaves_orenge_fast;
    IIcon tx_fallen_leaves_lime_fast;
    public static IIcon[] tx_fallen_leaves = new IIcon[10];
    public static IIcon tx_wood;

    public ecru_BlockLeafFence() {
        super(Material.field_151584_j);
        this.nc = new ecru_numericConstant();
        this.random = new Random();
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        func_149675_a(false);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int i) {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k) {
        return 16777215;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return true;
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockLeafFence);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public void func_149674_a(World par1World, int i, int j, int k, Random par5Random) {
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

    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        Block i1 = par1IBlockAccess.func_147439_a(par2, par3, par4);
        if (mod_ecru_MapleTree.GraphicsLevel == 0) {
            return _shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        }
        if (mod_ecru_MapleTree.GraphicsLevel == 1) {
            if (i1 == mod_ecru_MapleTree.blockFallenLeaves) {
                return false;
            }
            return _shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        }
        if (Blocks.field_150362_t.func_149662_c() && i1 == mod_ecru_MapleTree.blockFallenLeaves) {
            return false;
        }
        return _shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    public int func_149701_w() {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        if (mod_ecru_MapleTree.GraphicsLevel == 0) {
            return tx_fallen_leaves[(j & 3) * 2];
        }
        if (mod_ecru_MapleTree.GraphicsLevel == 1) {
            return tx_fallen_leaves[((j & 3) * 2) + 1];
        }
        return tx_fallen_leaves[((j & 3) * 2) + (Blocks.field_150362_t.func_149662_c() ? 1 : 0)];
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderLeafFenceID;
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public int func_149692_a(int i) {
        return i & 3;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public void func_149724_b(World world, int i, int j, int k, Entity entity) {
        super.func_149724_b(world, i, j, k, entity);
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        Block bidXp = par1IBlockAccess.func_147439_a(i + 1, j, k);
        Block bidXm = par1IBlockAccess.func_147439_a(i - 1, j, k);
        Block bidZp = par1IBlockAccess.func_147439_a(i, j, k + 1);
        Block bidZm = par1IBlockAccess.func_147439_a(i, j, k - 1);
        boolean normalXp = par1IBlockAccess.func_147439_a(i + 1, j, k).func_149721_r();
        boolean normalXm = par1IBlockAccess.func_147439_a(i - 1, j, k).func_149721_r();
        boolean normalZp = par1IBlockAccess.func_147439_a(i, j, k + 1).func_149721_r();
        boolean normalZm = par1IBlockAccess.func_147439_a(i, j, k - 1).func_149721_r();
        boolean xp = bidXp == this || normalXp || exceptionBlockChk(bidXp);
        boolean xm = bidXm == this || normalXm || exceptionBlockChk(bidXm);
        boolean zp = bidZp == this || normalZp || exceptionBlockChk(bidZp);
        boolean zm = bidZm == this || normalZm || exceptionBlockChk(bidZm);
        func_149676_a(this.nc.P14, this.nc.P08, this.nc.P00, this.nc.P20, this.nc.P32, this.nc.P17);
        func_149676_a(this.nc.P00, this.nc.P08, this.nc.P14, this.nc.P17, this.nc.P32, this.nc.P20);
        float xMin = this.nc.P11;
        float xMax = this.nc.P21;
        float zMin = this.nc.P11;
        float zMax = this.nc.P21;
        float yMax = 1.0f;
        if (zm) {
            zMin = 0.0f;
        }
        if (zp) {
            zMax = 1.0f;
        }
        if (xm) {
            xMin = 0.0f;
        }
        if (xp) {
            xMax = 1.0f;
        }
        if (zm && zp && !xm && !xp) {
            yMax = this.nc.P32;
            xMin = this.nc.P13;
            xMax = this.nc.P19;
        } else if (!zm && !zp && xm && xp) {
            yMax = this.nc.P32;
            zMin = this.nc.P13;
            zMax = this.nc.P19;
        }
        func_149676_a(xMin, 0.0f, zMin, xMax, yMax, zMax);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        this.field_149756_F = 1.5d;
        return super.func_149668_a(world, i, j, k);
    }

    private boolean exceptionBlockChk(Block id) {
        for (int i = 0; i < mod_ecru_MapleTree.blockList.length; i++) {
            if (id == mod_ecru_MapleTree.blockList[i]) {
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_fallen_leaves[0] = par1IconRegister.func_94245_a("mapletree:leaves_red");
        tx_fallen_leaves[1] = par1IconRegister.func_94245_a("mapletree:leaves_red_fast");
        tx_fallen_leaves[2] = par1IconRegister.func_94245_a("mapletree:leaves_yellow");
        tx_fallen_leaves[3] = par1IconRegister.func_94245_a("mapletree:leaves_yellow_fast");
        tx_fallen_leaves[4] = par1IconRegister.func_94245_a("mapletree:leaves_orange");
        tx_fallen_leaves[5] = par1IconRegister.func_94245_a("mapletree:leaves_orange_fast");
        tx_fallen_leaves[6] = par1IconRegister.func_94245_a("mapletree:leaves_lime");
        tx_fallen_leaves[7] = par1IconRegister.func_94245_a("mapletree:leaves_lime_fast");
        tx_wood = par1IconRegister.func_94245_a("mapletree:deco_wood");
    }
}
