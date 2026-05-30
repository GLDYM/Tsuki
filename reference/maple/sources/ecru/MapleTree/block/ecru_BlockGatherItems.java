package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGatherItems;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockGatherItems extends BlockContainer {
    IIcon tx_top;
    IIcon tx_bottom;
    IIcon tx_side;
    private final Random random;
    private int ccx;
    private int ccy;
    private int ccz;
    private ItemStack[] filterList;
    private int filterListNum;

    public ecru_BlockGatherItems() {
        super(Material.field_151575_d);
        this.random = new Random();
        this.ccx = 0;
        this.ccy = 0;
        this.ccz = 0;
        this.filterList = new ItemStack[9];
        this.filterListNum = 0;
        func_149675_a(false);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public TileEntity func_149915_a(World world, int par2) {
        return new ecru_TileEntityGatherItems();
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderGatherItemsID;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int i, int j) {
        switch (i) {
            case 0:
                return this.tx_bottom;
            case 1:
                return this.tx_top;
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                return this.tx_side;
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        int meta = iblockaccess.func_72805_g(i, j, k);
        if ((meta & 4) == 0) {
            return func_149691_a(l, 0);
        }
        int meta2 = meta & 3;
        switch (l) {
            case 0:
            default:
                return this.tx_side;
            case 1:
                return this.tx_side;
            case 2:
                if (meta2 == 2) {
                    return this.tx_bottom;
                }
                if (meta2 == 0) {
                    return this.tx_top;
                }
                return this.tx_side;
            case 3:
                if (meta2 == 0) {
                    return this.tx_bottom;
                }
                if (meta2 == 2) {
                    return this.tx_top;
                }
                return this.tx_side;
            case 4:
                if (meta2 == 1) {
                    return this.tx_bottom;
                }
                if (meta2 == 3) {
                    return this.tx_top;
                }
                return this.tx_side;
            case 5:
                if (meta2 == 3) {
                    return this.tx_bottom;
                }
                if (meta2 == 1) {
                    return this.tx_top;
                }
                return this.tx_side;
        }
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        if (!world.field_72995_K) {
            entityplayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdGatherItems, world, i, j, k);
            return true;
        }
        return true;
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        ecru_TileEntityGatherItems tileentitychest = (ecru_TileEntityGatherItems) par1World.func_147438_o(par2, par3, par4);
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

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        int direction = MathHelper.func_76128_c(((entityliving.field_70177_z * 4.0f) / 360.0f) + 0.5d) & 3;
        int meta = world.func_72805_g(i, j, k);
        if ((meta & 4) == 0) {
            world.func_72921_c(i, j, k, meta | direction, 3);
        }
    }

    public int func_149660_a(World par1World, int i, int j, int k, int l, float par6, float par7, float par8, int par9) {
        int bit012 = 0;
        switch (l) {
            case 0:
            case 1:
                bit012 = 0;
                break;
            case 2:
                bit012 = 4;
                break;
            case 3:
                bit012 = 6;
                break;
            case 4:
                bit012 = 7;
                break;
            case 5:
                bit012 = 5;
                break;
        }
        int meta = (par9 & 8) | bit012;
        par1World.func_72921_c(i, j, k, meta, 3);
        return meta;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        int meta = par1IBlockAccess.func_72805_g(i, j, k);
        if ((meta & 4) == 0) {
            func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
            return;
        }
        switch (meta & 3) {
            case 0:
                func_149676_a(0.0f, 0.0f, 0.875f, 1.0f, 1.0f, 1.0f);
                break;
            case 1:
                func_149676_a(0.0f, 0.0f, 0.0f, 0.125f, 1.0f, 1.0f);
                break;
            case 2:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.125f);
                break;
            case 3:
                func_149676_a(0.875f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            default:
                func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
        }
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        func_149719_a(world, i, j, k);
        return super.func_149668_a(world, i, j, k);
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        boolean flag = par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4);
        if (flag) {
            par1World.func_147464_a(par2, par3, par4, this, func_149738_a(par1World));
        }
    }

    public void func_149674_a(World par1World, int i, int j, int k, Random par5Random) {
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_top = par1IconRegister.func_94245_a("mapletree:gatherItems_top");
        this.tx_bottom = par1IconRegister.func_94245_a("mapletree:gatherItems_bottom");
        this.tx_side = par1IconRegister.func_94245_a("mapletree:gatherItems_side");
    }
}
