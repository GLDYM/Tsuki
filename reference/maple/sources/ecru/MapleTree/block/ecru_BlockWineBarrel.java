package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
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
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_BlockWineBarrel extends BlockContainer {
    IIcon tx_top;
    public static IIcon tx_top2;
    IIcon tx_side;
    private final Random random;

    public ecru_BlockWineBarrel() {
        super(Material.field_151575_d);
        this.random = new Random();
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public ecru_TileEntityWineBarrel func_149915_a(World world, int meta) {
        return new ecru_TileEntityWineBarrel();
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderWineBarrelID;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        int muki = j & 12;
        if (muki == 4) {
            if (i == 5 || i == 4) {
                return this.tx_top;
            }
            return this.tx_side;
        }
        if (muki == 8) {
            if (i == 2 || i == 3) {
                return this.tx_top;
            }
            return this.tx_side;
        }
        if (i == 1 || i == 0) {
            return this.tx_top;
        }
        return this.tx_side;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        if (!world.field_72995_K) {
            entityplayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdWineBarrel, world, i, j, k);
            return true;
        }
        return true;
    }

    public Item func_149650_a(int i, Random r, int meta) {
        return null;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
        ecru_TileEntityWineBarrel tile = (ecru_TileEntityWineBarrel) world.func_147438_o(i, j, k);
        if (tile != null) {
            ItemStack is = new ItemStack(mod_ecru_MapleTree.blockWineBarrel, 1, 0 & 3);
            NBTTagCompound nbt = is.func_77978_p();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                is.func_77982_d(nbt);
            }
            int wineQuantity = tile.getWineQuantity();
            int wineFerment = tile.getWineFerment();
            nbt.func_74768_a("wineQuantity", wineQuantity);
            nbt.func_74768_a("wineFerment", wineFerment);
            EntityItem ei = new EntityItem(world, i, j, k, is);
            world.func_72838_d(ei);
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
                        EntityItem entityitem = new EntityItem(world, i + f, j + f1, k + f2, new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                        entityitem.field_70159_w = ((float) this.random.nextGaussian()) * 0.05f;
                        entityitem.field_70181_x = (((float) this.random.nextGaussian()) * 0.05f) + 0.2f;
                        entityitem.field_70179_y = ((float) this.random.nextGaussian()) * 0.05f;
                        if (itemstack.func_77942_o()) {
                            entityitem.func_92059_d().func_77982_d(itemstack.func_77978_p().func_74737_b());
                        }
                        world.func_72838_d(entityitem);
                    }
                }
            }
            world.func_147453_f(i, j, k, par5);
        }
        super.func_149749_a(world, i, j, k, par5, par6);
    }

    public void func_149674_a(World par1World, int i, int j, int k, Random par5Random) {
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_top = par1IconRegister.func_94245_a("mapletree:wineBarrel_top");
        tx_top2 = par1IconRegister.func_94245_a("mapletree:wineBarrel_top2");
        this.tx_side = par1IconRegister.func_94245_a("mapletree:wineBarrel_side");
    }
}
