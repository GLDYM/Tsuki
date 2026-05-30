package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityMortar;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockMortar extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_inner;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_bottom;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top2;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_side;
    public static IIcon[] tx_water = new IIcon[2];
    private final Random random;
    private IIcon tx_body;

    public ecru_BlockMortar() {
        super(Material.field_151578_c);
        this.random = new Random();
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderMortarID;
    }

    public ecru_TileEntityMortar func_149915_a(World var1, int metadata) {
        return new ecru_TileEntityMortar();
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!world.field_72995_K) {
            par5EntityPlayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdMortar, world, i, j, k);
            return true;
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        ecru_TileEntityMortar tile = (ecru_TileEntityMortar) par1World.func_147438_o(par2, par3, par4);
        if (tile != null) {
            for (int j1 = 0; j1 < tile.func_70302_i_(); j1++) {
                ItemStack itemstack = tile.func_70301_a(j1);
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

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == 1 ? tx_top : par1 == 0 ? tx_bottom : tx_side;
    }

    public IIcon getInnerIcon() {
        return tx_inner;
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World world, int i, int j, int k, Random random) {
        int meta = world.func_72805_g(i, j, k);
        if (random.nextInt(2) == 0 && (meta & 8) == 8) {
            int iid = Item.func_150891_b(mod_ecru_MapleTree.Item_foodstuff);
            world.func_72869_a("iconcrack_" + iid + "_2", i + 0.2d + (random.nextDouble() * 0.6d), j + 0.5d, k + 0.2d + (random.nextDouble() * 0.6d), 0.0d, 0.0d, 0.0d);
            world.func_72869_a("iconcrack_" + iid + "_2", i + 0.2d + (random.nextDouble() * 0.6d), j + 0.5d, k + 0.2d + (random.nextDouble() * 0.6d), 0.0d, 0.0d, 0.0d);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_inner = par1IconRegister.func_94245_a("mapletree:mortar_inner");
        tx_top = par1IconRegister.func_94245_a("mapletree:mortar_top");
        tx_bottom = par1IconRegister.func_94245_a("mapletree:mortar_bottom");
        tx_side = par1IconRegister.func_94245_a("mapletree:mortar_side");
        tx_top2 = par1IconRegister.func_94245_a("mapletree:mortar_top2");
    }
}
