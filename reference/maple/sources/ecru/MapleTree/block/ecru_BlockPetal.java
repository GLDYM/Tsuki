package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
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

public class ecru_BlockPetal extends BlockLeavesBase {
    int[] adjacentTreeBlocks;
    IIcon[] tx_petal;

    public ecru_BlockPetal() {
        super(Material.field_151582_l, false);
        this.tx_petal = new IIcon[6];
        func_149675_a(true);
        func_149676_a(0.15625f, 0.15625f, 0.15625f, 0.84375f, 0.84375f, 0.84375f);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
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
        super.func_149749_a(world, i, j, k, par5, par6);
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        boolean xp = world.func_147439_a(i + 1, j, k).func_149688_o() == Material.field_151584_j;
        boolean xm = world.func_147439_a(i - 1, j, k).func_149688_o() == Material.field_151584_j;
        boolean zp = world.func_147439_a(i, j, k + 1).func_149688_o() == Material.field_151584_j;
        boolean zm = world.func_147439_a(i, j, k - 1).func_149688_o() == Material.field_151584_j;
        boolean yp = world.func_147439_a(i, j + 1, k).func_149688_o() == Material.field_151584_j;
        boolean ym = world.func_147439_a(i, j - 1, k).func_149688_o() == Material.field_151584_j;
        if (xp || xm || zp || zm || yp || ym) {
            return true;
        }
        return false;
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        if (!func_149742_c(world, i, j, k)) {
            func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 1);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
        }
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockPetal);
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float f, int i1) {
        super.func_149690_a(world, i, j, k, l, f, i1);
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public int func_149692_a(int i) {
        return i & 7;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderPetalID;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        int meta = j & 7;
        if (meta < 0 || meta > 5) {
            return this.tx_petal[0];
        }
        return this.tx_petal[meta];
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    public void func_149724_b(World world, int i, int j, int k, Entity entity) {
        super.func_149724_b(world, i, j, k, entity);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.15625f, 0.15625f, 0.15625f, 0.84375f, 0.84375f, 0.84375f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_petal[0] = par1IconRegister.func_94245_a("MapleTree:petal_red");
        this.tx_petal[1] = par1IconRegister.func_94245_a("MapleTree:petal_yellow");
        this.tx_petal[2] = par1IconRegister.func_94245_a("MapleTree:petal_purple");
        this.tx_petal[3] = par1IconRegister.func_94245_a("MapleTree:petal_blue");
        this.tx_petal[4] = par1IconRegister.func_94245_a("MapleTree:petal_white");
        this.tx_petal[5] = par1IconRegister.func_94245_a("MapleTree:petal_pink");
    }
}
