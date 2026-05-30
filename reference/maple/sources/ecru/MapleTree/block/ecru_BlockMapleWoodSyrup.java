package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityMapleWoodSyrup;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockMapleWoodSyrup extends BlockContainer {
    private int pngWood;
    private int pngStubble;
    private Random random;
    public static IIcon[] tx_wood = new IIcon[3];

    public ecru_BlockMapleWoodSyrup() {
        super(Material.field_151575_d);
        this.random = new Random();
        this.pngWood = 0;
        this.pngStubble = 1;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int func_149720_d(IBlockAccess iblockaccess, int i, int j, int k) {
        int meta = iblockaccess.func_72805_g(i, j, k);
        if ((meta & 3) != 2 && (meta & 3) == 3) {
            return -1061109568;
        }
        return 16777215;
    }

    public int func_149645_b() {
        return 31;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockMapleWood);
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int i, int j, int k, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, i, j, k, par6);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        int i = 4 + 1;
        if (par1World.func_72904_c(par2 - i, par3 - i, par4 - i, par2 + i, par3 + i, par4 + i)) {
            for (int j = -4; j <= 4; j++) {
                for (int k = -4; k <= 4; k++) {
                    for (int l = -4; l <= 4; l++) {
                        Block i1 = par1World.func_147439_a(par2 + j, par3 + k, par4 + l);
                        if (i1 == mod_ecru_MapleTree.blockMapleLeaves) {
                            int j1 = par1World.func_72805_g(par2 + j, par3 + k, par4 + l);
                            if ((j1 & 8) == 0) {
                                par1World.func_72921_c(par2 + j, par3 + k, par4 + l, j1 | 8, 3);
                            }
                        }
                    }
                }
            }
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        int muki = par2 & 12;
        int i = par2 & 3;
        if (muki == 4) {
            if (par1 == 5 || par1 == 4) {
                return tx_wood[1];
            }
            return tx_wood[2];
        }
        if (muki == 8) {
            if (par1 == 2 || par1 == 3) {
                return tx_wood[1];
            }
            return tx_wood[2];
        }
        if (par1 == 1 || par1 == 0) {
            return tx_wood[1];
        }
        return tx_wood[2];
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
        par1World.func_72921_c(i, j, k, meta | direction, 3);
        return meta | direction;
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        world.func_72805_g(i, j, k);
        long data = 4000 - this.random.nextInt(1500);
        ecru_TileEntityMapleWoodSyrup tileentity = (ecru_TileEntityMapleWoodSyrup) world.func_147438_o(i, j, k);
        if (tileentity != null) {
            if (this.random.nextInt(20) == 0) {
                tileentity.setTime(data * 5);
                tileentity.setTimeMax(data * 5);
                return;
            } else if (this.random.nextInt(10) == 0) {
                tileentity.setTime(data / 5);
                tileentity.setTimeMax(data / 5);
                return;
            } else {
                tileentity.setTime(data);
                tileentity.setTimeMax(data);
                return;
            }
        }
        System.out.println("tileentity NULL");
    }

    public boolean func_149727_a(World world, int iii, int jjj, int kkk, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_wood[0] = par1IconRegister.func_94245_a("mapletree:wood");
        tx_wood[1] = par1IconRegister.func_94245_a("mapletree:stump");
        tx_wood[2] = par1IconRegister.func_94245_a("mapletree:woodSpile");
    }

    public ecru_TileEntityMapleWoodSyrup func_149915_a(World world, int meta) {
        return new ecru_TileEntityMapleWoodSyrup();
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = func_149694_d(world, x, y, z);
        if (item == null) {
            return null;
        }
        return new ItemStack(item, 1, 2);
    }
}
