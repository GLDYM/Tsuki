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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockMarbleJewel extends Block {
    private Random random;
    public static IIcon[] tx_jewel = new IIcon[8];
    public static IIcon[] tx_stone = new IIcon[2];
    public static IIcon[] tx_stone_top = new IIcon[2];
    private int blockType;

    public ecru_BlockMarbleJewel(int type) {
        super(Material.field_151578_c);
        this.random = new Random();
        this.blockType = type;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderDecorationJewelID;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        return i & 3;
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

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149701_w() {
        return mod_ecru_MapleTree.MarbleBlockTranslucent ? 1 : 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        int muki = par2 & 12;
        int color = this.blockType <= 3 ? 0 : 1;
        if (muki == 4) {
            if (par1 == 5 || par1 == 4) {
                return tx_stone_top[color];
            }
            return tx_stone[color];
        }
        if (muki == 8) {
            if (par1 == 2 || par1 == 3) {
                return tx_stone_top[color];
            }
            return tx_stone[color];
        }
        if (par1 == 1 || par1 == 0) {
            return tx_stone_top[color];
        }
        return tx_stone[color];
    }

    public boolean func_149727_a(World world, int iii, int jjj, int kkk, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int direction;
        int meta = par9 & 3;
        switch (l) {
            case 0:
            case 1:
            default:
                direction = 0;
                break;
            case 2:
            case 3:
                direction = 8;
                break;
            case 4:
            case 5:
                direction = 4;
                break;
        }
        if (this.blockType == 3 || this.blockType == 7) {
            direction = 0;
        }
        par1World.func_72921_c(i, j, k, meta | direction, 3);
        return meta | direction;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_stone[0] = par1IconRegister.func_94245_a("mapletree:marble");
        tx_stone[1] = par1IconRegister.func_94245_a("mapletree:marble_black");
        tx_stone_top[0] = par1IconRegister.func_94245_a("mapletree:marble_top");
        tx_stone_top[1] = par1IconRegister.func_94245_a("mapletree:marble_black_top");
        tx_jewel[0] = par1IconRegister.func_94245_a("mapletree:ju_0");
        tx_jewel[1] = par1IconRegister.func_94245_a("mapletree:ju_1");
        tx_jewel[2] = par1IconRegister.func_94245_a("mapletree:ju_2");
        tx_jewel[3] = par1IconRegister.func_94245_a("mapletree:marble_black");
        tx_jewel[4] = par1IconRegister.func_94245_a("mapletree:ju_0");
        tx_jewel[5] = par1IconRegister.func_94245_a("mapletree:ju_1");
        tx_jewel[6] = par1IconRegister.func_94245_a("mapletree:ju_2");
        tx_jewel[7] = par1IconRegister.func_94245_a("mapletree:marble");
    }
}
