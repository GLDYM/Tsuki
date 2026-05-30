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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockWhiteFence extends Block {
    private ecru_numericConstant nc;
    public static IIcon[] tx_fence_vertical = new IIcon[2];
    public static IIcon[] tx_fence_horizontal = new IIcon[2];

    public ecru_BlockWhiteFence() {
        super(Material.field_151575_d);
        this.nc = new ecru_numericConstant();
        func_149675_a(false);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 8));
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

    public int func_149745_a(Random random) {
        return 1;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockWhiteFence);
    }

    public int func_149692_a(int i) {
        return i & 8;
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
        return mod_ecru_MapleTree.renderWhiteFenceID;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        return tx_fence_vertical[j >> 3];
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        int color = world.func_72805_g(i, j, k) & 8;
        int meta = world.func_72805_g(i, j, k);
        int bit2 = (meta & 1) << 2;
        int bit3 = (meta & 2) << 2;
        int meta2 = 0;
        boolean sn = !entityliving.func_70093_af();
        int direction = MathHelper.func_76128_c(((entityliving.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
        byte muki = 0;
        switch (direction) {
            case 0:
                muki = sn ? (byte) 0 : (byte) 2;
                meta2 = sn ? bit3 == 8 ? 4 : 0 : bit3 == 8 ? 0 : 4;
                break;
            case 1:
                muki = sn ? (byte) 1 : (byte) 3;
                meta2 = sn ? bit2 == 0 ? 4 : 0 : bit2 == 0 ? 0 : 4;
                break;
            case 2:
                muki = sn ? (byte) 2 : (byte) 0;
                meta2 = sn ? bit3 == 0 ? 4 : 0 : bit3 == 0 ? 0 : 4;
                break;
            case 3:
                muki = sn ? (byte) 3 : (byte) 1;
                meta2 = sn ? bit2 : bit2 == 4 ? 0 : 4;
                break;
        }
        world.func_72921_c(i, j, k, meta2 | muki | color, 3);
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int bit2 = 0;
        int bit3 = 0;
        switch (l) {
            case 0:
            case 1:
                if (par6 < 0.5d) {
                    bit2 = 0;
                } else {
                    bit2 = 4;
                }
                if (par8 < 0.5d) {
                    bit3 = 0;
                    break;
                } else {
                    bit3 = 8;
                    break;
                }
            case 2:
                bit2 = 4;
                bit3 = 8;
                break;
            case 3:
                bit2 = 0;
                bit3 = 0;
                break;
            case 4:
                bit2 = 4;
                bit3 = 0;
                break;
            case 5:
                bit2 = 0;
                bit3 = 8;
                break;
        }
        int meta = par9 | ((bit2 | bit3) >> 2);
        par1World.func_72921_c(i, j, k, meta, 3);
        return meta;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch (meta & 3) {
            case 0:
                if ((meta & 4) == 0) {
                    func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P04);
                    break;
                } else {
                    func_149676_a(this.nc.P00, this.nc.P00, this.nc.P28, this.nc.P32, this.nc.P32, this.nc.P32);
                    break;
                }
            case 1:
                if ((meta & 4) == 0) {
                    func_149676_a(this.nc.P28, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
                    break;
                } else {
                    func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P04, this.nc.P32, this.nc.P32);
                    break;
                }
            case 2:
                if ((meta & 4) == 0) {
                    func_149676_a(this.nc.P00, this.nc.P00, this.nc.P28, this.nc.P32, this.nc.P32, this.nc.P32);
                    break;
                } else {
                    func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P04);
                    break;
                }
            case 3:
                if ((meta & 4) == 0) {
                    func_149676_a(this.nc.P00, this.nc.P00, this.nc.P00, this.nc.P04, this.nc.P32, this.nc.P32);
                    break;
                } else {
                    func_149676_a(this.nc.P28, this.nc.P00, this.nc.P00, this.nc.P32, this.nc.P32, this.nc.P32);
                    break;
                }
            default:
                func_149676_a(this.nc.P12, this.nc.P00, this.nc.P12, this.nc.P20, this.nc.P32, this.nc.P20);
                break;
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        this.field_149756_F = 1.5d;
        return super.func_149668_a(world, i, j, k);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_fence_vertical[0] = par1IconRegister.func_94245_a("MapleTree:whiteFence_vertical");
        tx_fence_horizontal[0] = par1IconRegister.func_94245_a("MapleTree:whiteFence_horizontal");
        tx_fence_vertical[1] = par1IconRegister.func_94245_a("MapleTree:whiteFence_Ex_vertical");
        tx_fence_horizontal[1] = par1IconRegister.func_94245_a("MapleTree:whiteFence_Ex_horizontal");
    }
}
