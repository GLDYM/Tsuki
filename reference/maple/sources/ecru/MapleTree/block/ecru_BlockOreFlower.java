package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityOreFlower;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockOreFlower extends BlockContainer {
    private Random random;
    private int power;
    private int GENERATION_NUM;
    public static IIcon tx_ore;
    public static IIcon[] tx_num;
    public static IIcon tx_block0;
    public static IIcon tx_block1;
    public static IIcon[] tx_flower;
    private ecru_IdList blockInfo;

    public ecru_BlockOreFlower() {
        super(Material.field_151584_j);
        this.random = new Random();
        this.blockInfo = new ecru_IdList();
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public int func_149701_w() {
        return 0;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderOreFlowerID;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return tx_num[par2 & 7];
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        if ((world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPlanter && world.func_72805_g(i, j - 1, k) != 0) || world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockOreFlowerRed) {
            return true;
        }
        return false;
    }

    public void func_149695_a(World par1World, int i, int j, int k, Block l) {
        canOreFlowerStay(par1World, i, j, k);
    }

    private boolean canOreFlowerStay(World par1World, int i, int j, int k) {
        if (!func_149742_c(par1World, i, j, k)) {
            func_149697_b(par1World, i, j, k, par1World.func_72805_g(i, j, k), 0);
            par1World.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
            return false;
        }
        return true;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return true;
    }

    public int func_149738_a(World par1World) {
        return 1;
    }

    public void func_149674_a(World world, int i, int j, int k, Random par5Random) {
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
        super.func_149636_a(world, entityplayer, i, j, k, l);
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        int dp;
        super.func_149690_a(world, i, j, k, l, m, 0);
        int meta = l & 7;
        switch (meta) {
            case 0:
            default:
                return;
            case 1:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][1];
                break;
            case 2:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][2];
                break;
            case 3:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][3];
                break;
            case 4:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][4];
                break;
            case 5:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][5];
                break;
            case 6:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][6];
                break;
            case 7:
                dp = this.random.nextInt(1) + this.blockInfo.dropOreNum[0][7];
                break;
        }
        ecru_IdList ecru_idlist = this.blockInfo;
        func_149642_a(world, i, j, k, new ItemStack(ecru_IdList.dropItemId[0], dp, 0));
    }

    public void func_149734_b(World world, int i, int j, int k, Random par5Random) {
    }

    public ecru_TileEntityOreFlower func_149915_a(World world, int meta) {
        return new ecru_TileEntityOreFlower();
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.28125f, 0.0f, 0.28125f, 0.71875f, 1.0f, 0.71875f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_num = new IIcon[16];
        tx_flower = new IIcon[16];
        for (int i = 0; i < 8; i++) {
            tx_num[i] = par1IconRegister.func_94245_a("mapletree:oreLeaf_" + i);
        }
        for (int i2 = 0; i2 < 8; i2++) {
            tx_flower[i2] = par1IconRegister.func_94245_a("mapletree:red_flower_" + i2);
        }
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        Block.func_149634_a(item);
        return new ItemStack(item, 1, world.func_72805_g(x, y, z));
    }
}
