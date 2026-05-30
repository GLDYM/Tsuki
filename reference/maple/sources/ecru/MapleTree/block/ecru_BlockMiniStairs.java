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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockMiniStairs extends Block {
    private ecru_numericConstant nc;
    private Random random;
    private int blockNum;
    public static IIcon[] tx_miniStairs;
    private boolean flg;

    public ecru_BlockMiniStairs(int j) {
        super(Material.field_151578_c);
        this.nc = new ecru_numericConstant();
        this.random = new Random();
        this.blockNum = j;
        this.flg = mod_ecru_MapleTree.MiniStairsLong;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderMiniStairs1ID;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(this);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int i, int j, int k, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, i, j, k, par6);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return tx_miniStairs[this.blockNum];
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        int rev;
        byte muki;
        int meta = world.func_72805_g(i, j, k);
        int direction = MathHelper.func_76128_c(((entityliving.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
        if (entityliving.func_70093_af()) {
            rev = 4;
        } else {
            rev = 0;
        }
        switch (direction) {
            case 0:
            default:
                muki = 0;
                break;
            case 1:
                muki = 1;
                break;
            case 2:
                muki = 2;
                break;
            case 3:
                muki = 3;
                break;
        }
        world.func_72921_c(i, j, k, meta | muki | rev, 3);
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        if (l == 1) {
            return par9;
        }
        if (l == 0) {
            return par9 | 8;
        }
        if (par7 < 0.5d) {
            return par9;
        }
        return par9 | 8;
    }

    public boolean func_149727_a(World world, int iii, int jjj, int kkk, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        switch (meta & 3) {
            case 0:
                renderDecorationJewel0(par1IBlockAccess, i, j, k, meta, 1);
                break;
            case 1:
                renderDecorationJewel1(par1IBlockAccess, i, j, k, meta, 1);
                break;
            case 2:
                renderDecorationJewel2(par1IBlockAccess, i, j, k, meta, 1);
                break;
            case 3:
                renderDecorationJewel3(par1IBlockAccess, i, j, k, meta, 1);
                break;
            default:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
        }
    }

    public boolean renderDecorationJewel0(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, int meta, int mode) {
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX, blockY, blockZ + 1) == this;
        int tg_meta = iblockaccess.func_72805_g(blockX, blockY, blockZ + 1);
        boolean tg_id_e = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ + 1) == this;
        int tg_meta_e = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ + 1);
        boolean tg_id_w = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ + 1) == this;
        int tg_meta_w = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ + 1);
        float stxDW = this.nc.P00;
        float enxDW = this.nc.P32;
        float stzDW = this.nc.P16;
        float enzDW = this.nc.P32;
        float f = this.nc.P00;
        float f2 = this.nc.P00;
        if (tg_id && (tg_meta & 3) == 3 && (meta & 12) == (tg_meta & 12) && (!tg_id_w || (tg_meta_w & 3) != 2 || (tg_meta_w & 12) != (tg_meta & 12))) {
            stxDW = this.nc.P16;
            enxDW = this.nc.P32;
        } else if (tg_id && (tg_meta & 3) == 1 && (meta & 12) == (tg_meta & 12) && (!tg_id_e || (tg_meta_e & 3) != 2 || (tg_meta_e & 12) != (tg_meta & 12))) {
            stxDW = this.nc.P00;
            enxDW = this.nc.P16;
        }
        if ((meta & 4) == 0) {
            enyDW = this.nc.P32;
            float f3 = (this.flg && (meta & 8) == 8) ? this.nc.P00 : this.nc.P16;
            styDW = f3;
        } else {
            float f4 = (this.flg && (meta & 8) != 8) ? this.nc.P32 + 0.5f : this.nc.P32;
            enyDW = f4;
            styDW = this.nc.P16;
        }
        if ((meta & 8) == 0) {
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        return true;
    }

    public boolean renderDecorationJewel1(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, int meta, int mode) {
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ) == this;
        int tg_meta = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ);
        boolean tg_id_s = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ + 1) == this;
        int tg_meta_s = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ + 1);
        boolean tg_id_n = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ - 1) == this;
        int tg_meta_n = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ - 1);
        float stxDW = this.nc.P00;
        float enxDW = this.nc.P16;
        float stzDW = this.nc.P00;
        float enzDW = this.nc.P32;
        float f = this.nc.P00;
        float f2 = this.nc.P00;
        if (tg_id && (tg_meta & 3) == 0 && (meta & 12) == (tg_meta & 12) && (!tg_id_n || (tg_meta_n & 3) != 3 || (tg_meta_n & 12) != (tg_meta & 12))) {
            stzDW = this.nc.P16;
            enzDW = this.nc.P32;
        } else if (tg_id && (tg_meta & 3) == 2 && (meta & 12) == (tg_meta & 12) && (!tg_id_s || (tg_meta_s & 3) != 3 || (tg_meta_s & 12) != (tg_meta & 12))) {
            stzDW = this.nc.P00;
            enzDW = this.nc.P16;
        }
        if ((meta & 4) == 0) {
            enyDW = this.nc.P32;
            float f3 = (this.flg && (meta & 8) == 8) ? this.nc.P00 : this.nc.P16;
            styDW = f3;
        } else {
            float f4 = (this.flg && (meta & 8) != 8) ? this.nc.P32 + 0.5f : this.nc.P32;
            enyDW = f4;
            styDW = this.nc.P16;
        }
        if ((meta & 8) == 0) {
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        return true;
    }

    private boolean renderDecorationJewel2(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, int meta, int mode) {
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX, blockY, blockZ - 1) == this;
        int tg_meta = iblockaccess.func_72805_g(blockX, blockY, blockZ - 1);
        boolean tg_id_w = iblockaccess.func_147439_a(blockX - 1, blockY, blockZ - 1) == this;
        int tg_meta_w = iblockaccess.func_72805_g(blockX - 1, blockY, blockZ - 1);
        boolean tg_id_e = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ - 1) == this;
        int tg_meta_e = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ - 1);
        float stxDW = this.nc.P00;
        float enxDW = this.nc.P32;
        float stzDW = this.nc.P00;
        float enzDW = this.nc.P16;
        float f = this.nc.P00;
        float f2 = this.nc.P00;
        if (tg_id && (tg_meta & 3) == 1 && (meta & 12) == (tg_meta & 12) && (!tg_id_e || (tg_meta_e & 3) != 0 || (tg_meta_e & 12) != (tg_meta & 12))) {
            stxDW = this.nc.P00;
            enxDW = this.nc.P16;
        } else if (tg_id && (tg_meta & 3) == 3 && (meta & 12) == (tg_meta & 12) && (!tg_id_w || (tg_meta_w & 3) != 0 || (tg_meta_w & 12) != (tg_meta & 12))) {
            stxDW = this.nc.P16;
            enxDW = this.nc.P32;
        }
        if ((meta & 4) == 0) {
            enyDW = this.nc.P32;
            float f3 = (this.flg && (meta & 8) == 8) ? this.nc.P00 : this.nc.P16;
            styDW = f3;
        } else {
            float f4 = (this.flg && (meta & 8) != 8) ? this.nc.P32 + 0.5f : this.nc.P32;
            enyDW = f4;
            styDW = this.nc.P16;
        }
        if ((meta & 8) == 0) {
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        return true;
    }

    public boolean renderDecorationJewel3(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ, int meta, int mode) {
        float enyDW;
        float styDW;
        boolean tg_id = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ) == this;
        int tg_meta = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ);
        boolean tg_id_n = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ - 1) == this;
        int tg_meta_n = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ - 1);
        boolean tg_id_s = iblockaccess.func_147439_a(blockX + 1, blockY, blockZ + 1) == this;
        int tg_meta_s = iblockaccess.func_72805_g(blockX + 1, blockY, blockZ + 1);
        float stxDW = this.nc.P16;
        float enxDW = this.nc.P32;
        float stzDW = this.nc.P00;
        float enzDW = this.nc.P32;
        float f = this.nc.P00;
        float f2 = this.nc.P00;
        if (tg_id && (tg_meta & 3) == 2 && (meta & 12) == (tg_meta & 12) && (!tg_id_s || (tg_meta_s & 3) != 1 || (tg_meta_s & 12) != (tg_meta & 12))) {
            stzDW = this.nc.P00;
            enzDW = this.nc.P16;
        } else if (tg_id && (tg_meta & 3) == 0 && (meta & 12) == (tg_meta & 12) && (!tg_id_n || (tg_meta_n & 3) != 1 || (tg_meta_n & 12) != (tg_meta & 12))) {
            stzDW = this.nc.P16;
            enzDW = this.nc.P32;
        }
        if ((meta & 4) == 0) {
            enyDW = this.nc.P32;
            float f3 = (this.flg && (meta & 8) == 8) ? this.nc.P00 : this.nc.P16;
            styDW = f3;
        } else {
            float f4 = (this.flg && (meta & 8) != 8) ? this.nc.P32 + 0.5f : this.nc.P32;
            enyDW = f4;
            styDW = this.nc.P16;
        }
        if ((meta & 8) == 0) {
            enyDW -= 0.5f;
            styDW -= 0.5f;
        }
        func_149676_a(stxDW, styDW, stzDW, enxDW, enyDW, enzDW);
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_miniStairs = new IIcon[4];
        tx_miniStairs[0] = par1IconRegister.func_94245_a("mapletree:marble");
        tx_miniStairs[1] = par1IconRegister.func_94245_a("mapletree:marble_black");
        tx_miniStairs[2] = par1IconRegister.func_94245_a("mapletree:wood1");
        tx_miniStairs[3] = par1IconRegister.func_94245_a("mapletree:stone1");
    }
}
