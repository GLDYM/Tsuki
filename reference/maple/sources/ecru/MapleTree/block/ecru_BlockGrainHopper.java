package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrainHopper;
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
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockGrainHopper extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_glass;
    public static IIcon tx_top;
    public static IIcon tx_side;
    public static IIcon tx_bottom;
    public static IIcon tx_contents;
    private final Random random;

    public ecru_BlockGrainHopper() {
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
        ecru_TileEntityGrainHopper tileentitychest = (ecru_TileEntityGrainHopper) par1World.func_147438_o(par2, par3, par4);
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

    public void func_149683_g() {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderGrainHopperID;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!world.field_72995_K) {
            par5EntityPlayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdGrainHopper, world, i, j, k);
            return true;
        }
        return true;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(this);
    }

    public ecru_TileEntityGrainHopper func_149915_a(World world, int meta) {
        return new ecru_TileEntityGrainHopper();
    }

    public void func_149734_b(World world, int i, int j, int k, Random par5Random) {
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_glass = par1IconRegister.func_94245_a("mapletree:grainHopper_glass");
        tx_top = par1IconRegister.func_94245_a("mapletree:grainHopper_top");
        tx_side = par1IconRegister.func_94245_a("mapletree:grainHopper_side");
        tx_bottom = par1IconRegister.func_94245_a("mapletree:grainHopper_bottom");
        tx_contents = par1IconRegister.func_94245_a("mapletree:grainHopper_contents");
    }
}
