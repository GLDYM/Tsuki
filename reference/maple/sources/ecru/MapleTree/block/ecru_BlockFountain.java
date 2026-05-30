package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityFountain;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockFountain extends BlockContainer {
    private IIcon tx_body;

    public ecru_BlockFountain() {
        super(Material.field_151578_c);
        func_149675_a(false);
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
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderFountainID;
    }

    public ecru_TileEntityFountain func_149915_a(World var1, int metadata) {
        return new ecru_TileEntityFountain();
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        boolean flag = world.func_72864_z(i, j, k) || world.func_72864_z(i, j + 1, k);
        if (flag) {
            int meta = world.func_72805_g(i, j, k);
            world.func_147439_a(i, j, k);
            world.func_72921_c(i, j, k, meta | 8, 3);
        } else {
            int meta2 = world.func_72805_g(i, j, k);
            world.func_147439_a(i, j, k);
            world.func_72921_c(i, j, k, meta2 & 7, 3);
        }
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        boolean flag = world.func_72864_z(i, j, k) || world.func_72864_z(i, j + 1, k);
        if (flag) {
            int meta = world.func_72805_g(i, j, k);
            Block id = world.func_147439_a(i, j, k);
            world.func_147465_d(i, j, k, id, meta | 8, 3);
        } else {
            int meta2 = world.func_72805_g(i, j, k);
            Block id2 = world.func_147439_a(i, j, k);
            world.func_147465_d(i, j, k, id2, meta2 & 7, 3);
        }
        ecru_TileEntityFountain tile = (ecru_TileEntityFountain) world.func_147438_o(i, j, k);
        tile.setDirection(0);
        tile.setAngle(8);
        tile.setPower(10);
    }

    public int func_149660_a(World world, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        if (l == 0) {
            return par9 | 4;
        }
        return par9;
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        super.func_149674_a(world, i, j, k, random);
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockFountain);
    }

    public int func_149692_a(int i) {
        return i & 7;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int pow;
        int ang;
        int dire;
        if (par5EntityPlayer.func_71045_bC() == null) {
            int meta = world.func_72805_g(i, j, k);
            int bit = (meta ^ (-1)) & 8;
            world.func_72921_c(i, j, k, (meta & 7) | bit, 2);
            return true;
        }
        if (par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151055_y) {
            ecru_TileEntityFountain tile = (ecru_TileEntityFountain) world.func_147438_o(i, j, k);
            int dire2 = tile.getDirection();
            if (dire2 >= 360) {
                dire = 0;
            } else {
                dire = dire2 + 5;
            }
            if (!world.field_72995_K) {
                par5EntityPlayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.fountain.direction") + ":" + dire, new Object[0]));
            }
            tile.setDirection(dire);
            tile.func_70296_d();
            return true;
        }
        if (par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151017_I) {
            ecru_TileEntityFountain tile2 = (ecru_TileEntityFountain) world.func_147438_o(i, j, k);
            int ang2 = tile2.getAngle();
            if (ang2 >= 10) {
                ang = 1;
            } else {
                ang = ang2 + 1;
            }
            if (!world.field_72995_K) {
                par5EntityPlayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.fountain.angle") + ":" + ang, new Object[0]));
            }
            tile2.setAngle(ang);
            tile2.func_70296_d();
            return true;
        }
        if (par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151013_M) {
            ecru_TileEntityFountain tile3 = (ecru_TileEntityFountain) world.func_147438_o(i, j, k);
            int pow2 = tile3.getPower();
            if (pow2 >= 20) {
                pow = 1;
            } else {
                pow = pow2 + 1;
            }
            if (!world.field_72995_K) {
                par5EntityPlayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.fountain.distant") + ":" + pow, new Object[0]));
            }
            tile3.setPower(pow);
            tile3.func_70296_d();
            return true;
        }
        return true;
    }

    public void func_149699_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer) {
        int dire;
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151055_y) {
            ecru_TileEntityFountain tile = (ecru_TileEntityFountain) world.func_147438_o(i, j, k);
            int dire2 = tile.getDirection();
            if (dire2 <= 0) {
                dire = 360;
            } else {
                dire = dire2 - 5;
            }
            if (!world.field_72995_K) {
                par5EntityPlayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.fountain.direction") + ":" + dire, new Object[0]));
            }
            tile.setDirection(dire);
            tile.func_70296_d();
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.4f, 0.6875f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("iron_block");
    }
}
