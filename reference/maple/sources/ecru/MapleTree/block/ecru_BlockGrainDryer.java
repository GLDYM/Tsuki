package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrainDryer;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockGrainDryer extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_bottom;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_side;
    private final Random random;

    public ecru_BlockGrainDryer() {
        super(Material.field_151578_c);
        this.random = new Random();
        func_149675_a(true);
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        ecru_TileEntityGrainDryer tileentitychest = (ecru_TileEntityGrainDryer) par1World.func_147438_o(par2, par3, par4);
        if (tileentitychest != null) {
            for (int j1 = 0; j1 < tileentitychest.func_70302_i_(); j1++) {
                ItemStack itemstack = tileentitychest.func_70301_a(j1);
                if (itemstack != null) {
                    float f = (this.random.nextFloat() * 0.8f) + 0.1f;
                    float f1 = (this.random.nextFloat() * 0.8f) + 0.1f;
                    float f2 = (this.random.nextFloat() * 0.8f) + 0.1f;
                    while (itemstack.field_77994_a > 0) {
                        int k1 = this.random.nextInt(21) + 10;
                        if (k1 > itemstack.field_77994_a) {
                            k1 = itemstack.field_77994_a;
                        }
                        itemstack.field_77994_a -= k1;
                        EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                        entityitem.field_70159_w = ((float) this.random.nextGaussian()) * 0.05f;
                        entityitem.field_70181_x = (((float) this.random.nextGaussian()) * 0.05f) + 0.2f;
                        entityitem.field_70179_y = ((float) this.random.nextGaussian()) * 0.05f;
                        if (itemstack.func_77942_o()) {
                            entityitem.func_92059_d().func_77982_d(itemstack.func_77978_p().func_74737_b());
                        }
                        par1World.func_72838_d(entityitem);
                    }
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == 1 ? tx_top : par1 == 0 ? tx_bottom : tx_side;
    }

    @SideOnly(Side.CLIENT)
    public int func_149692_a(int par1) {
        return par1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderGrainDryerID;
    }

    public boolean func_149686_d() {
        return true;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!world.field_72995_K) {
            par5EntityPlayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdGrainDryer, world, i, j, k);
            return true;
        }
        return true;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockGrainDryer);
    }

    public ecru_TileEntityGrainDryer func_149915_a(World world, int metadata) {
        return new ecru_TileEntityGrainDryer();
    }

    public void func_149734_b(World world, int i, int j, int k, Random random) {
        if ((world.func_72805_g(i, j, k) & 8) == 8) {
            double d = 0.2d;
            while (true) {
                double x = d;
                if (x <= 0.8d) {
                    double d2 = 0.2d;
                    while (true) {
                        double z = d2;
                        if (z <= 0.8d) {
                            if (random.nextInt(4) == 0) {
                                world.func_72869_a("flame", i + x, j + 0.2d, k + z, 0.0d, 0.0d, 0.0d);
                            }
                            d2 = z + 0.2d;
                        }
                    }
                    d = x + 0.2d;
                } else {
                    return;
                }
            }
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int l = world.func_72805_g(x, y, z);
        if ((l & 8) == 8) {
            return 14;
        }
        return 0;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_top = par1IconRegister.func_94245_a("mapletree:grainDryer_top");
        tx_bottom = par1IconRegister.func_94245_a("mapletree:grainDryer_bottom");
        tx_side = par1IconRegister.func_94245_a("mapletree:grainDryer_side");
    }
}
