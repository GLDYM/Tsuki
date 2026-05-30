package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityOreFlower;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockPlanter extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_inner;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_bottom;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top2;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_water;

    @SideOnly(Side.CLIENT)
    public static IIcon[] tx_field;
    private ecru_IdList blockInfo;
    private final Random random;
    public static ecru_IdList idInfo = new ecru_IdList();

    public ecru_BlockPlanter() {
        super(Material.field_151575_d);
        this.blockInfo = new ecru_IdList();
        this.random = new Random();
        func_149675_a(true);
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
        ecru_TileEntityPlanter planterInventory = (ecru_TileEntityPlanter) world.func_147438_o(i, j, k);
        planterInventory.init();
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        ecru_TileEntityPlanter tileentitychest = (ecru_TileEntityPlanter) par1World.func_147438_o(par2, par3, par4);
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
        return par1 == 1 ? tx_top : par1 == 0 ? tx_bottom : this.field_149761_L;
    }

    @SideOnly(Side.CLIENT)
    public int func_149692_a(int par1) {
        return 0;
    }

    public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.3125f, 1.0f);
        super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        func_149676_a(0.0f, 0.0f, 0.0f, 0.125f, 1.0f, 1.0f);
        super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.125f);
        super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        func_149676_a(1.0f - 0.125f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        func_149676_a(0.0f, 0.0f, 1.0f - 0.125f, 1.0f, 1.0f, 1.0f);
        super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        func_149683_g();
    }

    @SideOnly(Side.CLIENT)
    public static IIcon func_94375_b(String par0Str) {
        if (par0Str == "planter_inner") {
            return tx_inner;
        }
        if (par0Str == "planter_bottom") {
            return tx_bottom;
        }
        return null;
    }

    public void func_149683_g() {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderPlanterID;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        Block id = world.func_147439_a(i, j + 1, k);
        int meta = world.func_72805_g(i, j, k);
        ecru_TileEntityPlanter tile = (ecru_TileEntityPlanter) world.func_147438_o(i, j, k);
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151137_ax && id == Blocks.field_150350_a && meta != 0 && tile.getSoilId() != null) {
            world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockOreFlowerRed, 0, 3);
            ecru_TileEntityOreFlower tileentity = (ecru_TileEntityOreFlower) world.func_147438_o(i, j + 1, k);
            long sTime = world.func_82737_E();
            tileentity.setTime(sTime);
            tileentity.setNou(0L);
            tileentity.setCount(1);
            tileentity.setType(0);
            if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                par5EntityPlayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151042_j && id == Blocks.field_150350_a && tile.getSoilId() != null) {
            world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockOreFlowerIron, 0, 3);
            ecru_TileEntityOreFlower tileentity2 = (ecru_TileEntityOreFlower) world.func_147438_o(i, j + 1, k);
            long sTime2 = world.func_82737_E();
            tileentity2.setTime(sTime2);
            tileentity2.setNou(0L);
            tileentity2.setCount(1);
            tileentity2.setType(1);
            if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                par5EntityPlayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151043_k && id == Blocks.field_150350_a && tile.getSoilId() != null) {
            world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockOreFlowerGold, 0, 3);
            ecru_TileEntityOreFlower tileentity3 = (ecru_TileEntityOreFlower) world.func_147438_o(i, j + 1, k);
            long sTime3 = world.func_82737_E();
            tileentity3.setTime(sTime3);
            tileentity3.setNou(0L);
            tileentity3.setCount(1);
            tileentity3.setType(2);
            if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                par5EntityPlayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() == mod_ecru_MapleTree.Item_jewel && par5EntityPlayer.func_71045_bC().func_77960_j() == 16 && id == Blocks.field_150350_a && tile.getSoilId() != null) {
            world.func_147465_d(i, j + 1, k, mod_ecru_MapleTree.blockOreFlowerMarble, 0, 3);
            ecru_TileEntityOreFlower tileentity4 = (ecru_TileEntityOreFlower) world.func_147438_o(i, j + 1, k);
            long sTime4 = world.func_82737_E();
            tileentity4.setTime(sTime4);
            tileentity4.setNou(0L);
            tileentity4.setCount(1);
            tileentity4.setType(3);
            if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                par5EntityPlayer.func_71045_bC().func_77979_a(1);
                return true;
            }
            return true;
        }
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() == Items.field_151131_as) {
            ecru_TileEntityPlanter tileentity5 = (ecru_TileEntityPlanter) world.func_147438_o(i, j, k);
            int waterMax = tileentity5.getWaterMax();
            int water = tileentity5.getWater();
            if (waterMax - water > 1) {
                tileentity5.addWater(this.blockInfo.waterBucket);
                if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                    par5EntityPlayer.func_70062_b(0, new ItemStack(Items.field_151133_ar, 1, 0));
                    return true;
                }
                return true;
            }
        }
        if (!world.field_72995_K) {
            par5EntityPlayer.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiIdPlanter, world, i, j, k);
            return true;
        }
        return true;
    }

    public Item func_149650_a(int i, Random r, int l) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockPlanter);
    }

    public ecru_TileEntityPlanter func_149915_a(World world, int meta) {
        return new ecru_TileEntityPlanter();
    }

    public void func_149734_b(World world, int i, int j, int k, Random par5Random) {
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int l = world.func_72805_g(x, y, z);
        if (l == 13) {
            return 14;
        }
        return 0;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_inner = par1IconRegister.func_94245_a("mapletree:planter_inner");
        tx_top = par1IconRegister.func_94245_a("mapletree:planter_top");
        tx_bottom = par1IconRegister.func_94245_a("mapletree:planter_bottom");
        this.field_149761_L = par1IconRegister.func_94245_a("mapletree:planter_side");
        tx_top2 = par1IconRegister.func_94245_a("mapletree:planter_top2");
        ecru_IdList id = new ecru_IdList();
        tx_field = new IIcon[id.blockId[0].length];
        for (int i = 1; i < id.blockId[0].length; i++) {
            tx_field[i] = par1IconRegister.func_94245_a(id.blockName[0][i]);
        }
    }
}
