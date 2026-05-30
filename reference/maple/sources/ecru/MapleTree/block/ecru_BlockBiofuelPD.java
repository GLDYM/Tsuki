package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityBiofuelPD;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockBiofuelPD extends BlockContainer {
    private final Random random;
    private IIcon tx_body;

    public ecru_BlockBiofuelPD() {
        super(Material.field_151578_c);
        this.random = new Random();
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderBiofuelPDID;
    }

    public Item func_149650_a(int i, Random r, int meta) {
        return null;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public ecru_TileEntityBiofuelPD func_149915_a(World var1, int meta) {
        return new ecru_TileEntityBiofuelPD();
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!world.field_72995_K) {
            par5EntityPlayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdBiofuelPD, world, i, j, k);
            return true;
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        ecru_TileEntityBiofuelPD tileentitychest = (ecru_TileEntityBiofuelPD) par1World.func_147438_o(par2, par3, par4);
        if (tileentitychest != null) {
            ItemStack is = new ItemStack(mod_ecru_MapleTree.blockBiofuelPD, 1, par6 & 3);
            NBTTagCompound nbt = is.func_77978_p();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                is.func_77982_d(nbt);
            }
            int tank1 = tileentitychest.getTank1();
            int tank2 = tileentitychest.getTank2();
            nbt.func_74768_a("dt_tank1", tank1);
            nbt.func_74768_a("dt_tank2", tank2);
            EntityItem ei = new EntityItem(par1World, par2, par3, par4, is);
            par1World.func_72838_d(ei);
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

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.125f, 0.0f, 0.125f, 0.875f, 1.0f, 0.875f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("mapletree:deco_stone");
    }
}
