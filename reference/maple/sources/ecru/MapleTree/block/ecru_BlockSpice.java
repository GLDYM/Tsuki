package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockSpice extends Block {
    private Random random;
    public static IIcon[][] tx_spice = new IIcon[3][3];

    public ecru_BlockSpice() {
        super(Material.field_151575_d);
        this.random = new Random();
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderSpiceID;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        return i & 3;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockSpice);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int i, int j, int k, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, i, j, k, par6);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        int meta = j & 3;
        if (meta < 0 || meta > 2) {
            return ecru_BlockMapleLeaves.tx_error;
        }
        switch (i) {
            case 0:
                return tx_spice[meta][2];
            case 1:
                return tx_spice[meta][0];
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                return tx_spice[meta][1];
        }
    }

    public boolean func_149727_a(World world, int iii, int jjj, int kkk, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, world.func_72805_g(x, y, z) & 3);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return AxisAlignedBB.func_72330_a(i + 0.0625f, j + 0.0625f, k + 0.0625f, (i + 1) - 0.0625f, (j + 1) - 0.0625f, (k + 1) - 0.0625f);
    }

    public AxisAlignedBB func_149633_g(World par1World, int i, int j, int k) {
        return AxisAlignedBB.func_72330_a(i + 0.0625f, j + 0.0625f, k + 0.0625f, (i + 1) - 0.0625f, (j + 1) - 0.0625f, (k + 1) - 0.0625f);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return true;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        int meta = world.func_72805_g(i, j, k);
        if ((entity instanceof EntityMob) || (entity instanceof EntityGhast) || (entity instanceof EntitySlime)) {
            entity.func_70097_a(DamageSource.field_76367_g, (meta + 1) * 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_spice[0][0] = par1IconRegister.func_94245_a("mapletree:spiceTop0");
        tx_spice[0][1] = par1IconRegister.func_94245_a("mapletree:spiceSide0");
        tx_spice[0][2] = par1IconRegister.func_94245_a("mapletree:spiceBottom0");
        tx_spice[1][0] = par1IconRegister.func_94245_a("mapletree:spiceTop1");
        tx_spice[1][1] = par1IconRegister.func_94245_a("mapletree:spiceSide0");
        tx_spice[1][2] = par1IconRegister.func_94245_a("mapletree:spiceBottom0");
        tx_spice[2][0] = par1IconRegister.func_94245_a("mapletree:spiceTop2");
        tx_spice[2][1] = par1IconRegister.func_94245_a("mapletree:spiceSide0");
        tx_spice[2][2] = par1IconRegister.func_94245_a("mapletree:spiceBottom0");
    }
}
