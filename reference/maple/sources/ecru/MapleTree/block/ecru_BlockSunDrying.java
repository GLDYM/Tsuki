package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySunDrying;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockSunDrying extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_side;
    private final Random random;

    public ecru_BlockSunDrying(Material m) {
        super(m);
        this.random = new Random();
        func_149647_a((CreativeTabs) null);
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderSunDryingID;
    }

    public ecru_TileEntitySunDrying func_149915_a(World var1, int metadata) {
        return new ecru_TileEntitySunDrying();
    }

    public Item func_149650_a(int i, Random r, int meta) {
        return null;
    }

    public void func_149749_a(World world, int i, int j, int k, Block b, int l) {
        ecru_TileEntitySunDrying tile = (ecru_TileEntitySunDrying) world.func_147438_o(i, j, k);
        if (tile == null) {
            super.func_149749_a(world, i, j, k, b, l);
            return;
        }
        if (tile.getFinished()) {
            ItemStack is = tile.getItemOut();
            if (is != null) {
                EntityItem ei = new EntityItem(world, i + this.random.nextDouble(), j, k + this.random.nextDouble(), is);
                world.func_72838_d(ei);
            }
        } else {
            ItemStack is2 = tile.getItemOrg();
            if (is2 != null) {
                NBTTagCompound nbt = is2.func_77978_p();
                if (nbt == null) {
                    nbt = new NBTTagCompound();
                    is2.func_77982_d(nbt);
                }
                int countTimer = tile.getCountTimer();
                int countTimerMax = tile.getCountTimerMax();
                nbt.func_74768_a("countTimer", countTimer);
                nbt.func_74768_a("countTimerMax", countTimerMax);
                EntityItem ei2 = new EntityItem(world, i, j, k, is2);
                world.func_72838_d(ei2);
            }
        }
        super.func_149749_a(world, i, j, k, b, l);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return (par1 == 1 || par1 == 0) ? tx_top : tx_side;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_top = par1IconRegister.func_94245_a("mapletree:sunDryingBonito_top");
        tx_side = par1IconRegister.func_94245_a("mapletree:sunDryingBonito_side");
    }
}
