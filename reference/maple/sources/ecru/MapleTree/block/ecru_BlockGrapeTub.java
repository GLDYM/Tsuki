package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityGrapeTub;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockGrapeTub extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_bottom;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_side1;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_side2;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_inner1;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_inner2;
    private Random random;
    private float Y_MAX;
    private ecru_numericConstant nc;

    public ecru_BlockGrapeTub() {
        super(Material.field_151567_E);
        this.random = new Random();
        this.Y_MAX = 0.25f;
        this.nc = new ecru_numericConstant();
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, this.Y_MAX, 1.0f);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 9));
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderGrapeStompTubID;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149692_a(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        return 9;
    }

    public Item func_149650_a(int i, Random r, int meta) {
        return Item.func_150898_a(mod_ecru_MapleTree.blockGrapeStompTub);
    }

    public int func_149701_w() {
        return 0;
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int i, int j, int k, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, i, j, k, par6);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == 1 ? tx_top : par1 == 0 ? tx_bottom : (par2 & 1) == 0 ? tx_side1 : tx_side2;
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k);
        if (tile != null) {
            tile.gst_uid = UUID.randomUUID();
            tile.gst_uid_most = tile.gst_uid.getMostSignificantBits();
            tile.gst_uid_least = tile.gst_uid.getLeastSignificantBits();
            tile.getClass();
            tile.setPosX(30000000);
            tile.getClass();
            tile.setPosX(30000000);
            tile.getClass();
            tile.setPosX(30000000);
        }
    }

    public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
        ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k);
        if (tile != null && world.func_147439_a(tile.getPosX(), tile.getPosY(), tile.getPosZ()) == mod_ecru_MapleTree.blockGrapeStompTub) {
            ecru_TileEntityGrapeTub tile2 = (ecru_TileEntityGrapeTub) world.func_147438_o(tile.getPosX(), tile.getPosY(), tile.getPosZ());
            String key = getStr(i) + getStr(j) + getStr(k);
            tile2.deleteMap(key);
            world.func_147475_p(i, j, k);
            int ret = tile2.blockCount(world, tile.getPosX(), tile.getPosY(), tile.getPosZ()) - 1;
            tile2.setTubNum(ret);
            int grapeNum = tile2.getGrapeNum();
            int grapeMax = tile2.getGrapeNumMax() * (tile2.getTubNum() + 1);
            if (grapeNum > grapeMax) {
                tile2.setGrapeNum(grapeMax);
                tile2.sendItemInfo(tile2);
            }
        }
        super.func_149749_a(world, i, j, k, par5, par6);
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        if ((par1IBlockAccess.func_72805_g(i, j, k) & 8) == 8) {
            return 16777215;
        }
        return 16711680;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        ecru_TileEntityGrapeTub tile;
        ecru_TileEntityGrapeTub tile2;
        if (!world.field_72995_K && entity.func_70005_c_().equals("item.item.mapletree:grape.0") && (tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k)) != null && world.func_147439_a(tile.getPosX(), tile.getPosY(), tile.getPosZ()) == mod_ecru_MapleTree.blockGrapeStompTub && (tile2 = (ecru_TileEntityGrapeTub) world.func_147438_o(tile.getPosX(), tile.getPosY(), tile.getPosZ())) != null && tile2.getWalkingFlg() && tile2.getGrapeNum() < tile2.getGrapeNumMax() * (tile2.getTubNum() + 1) && entity.func_70089_S()) {
            EntityItem eItem = (EntityItem) entity;
            int ss = eItem.func_92059_d().field_77994_a;
            if (ss > (tile2.getGrapeNumMax() * (tile2.getTubNum() + 1)) - tile2.getGrapeNum()) {
                int newss = (tile2.getGrapeNumMax() * (tile2.getTubNum() + 1)) - tile2.getGrapeNum();
                tile2.addGrape(newss);
                entity.func_70106_y();
                EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_grape, ss - newss, 0));
                world.func_72838_d(ei);
                tile2.subStompTime(newss * 50);
                return;
            }
            tile2.addGrape(ss);
            entity.func_70106_y();
            tile2.subStompTime(ss * 50);
        }
    }

    public void func_149724_b(World world, int i, int j, int k, Entity par5Entity) {
        ecru_TileEntityGrapeTub tile2;
        ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k);
        if (tile != null && world.func_147439_a(tile.getPosX(), tile.getPosY(), tile.getPosZ()) == mod_ecru_MapleTree.blockGrapeStompTub && (tile2 = (ecru_TileEntityGrapeTub) world.func_147438_o(tile.getPosX(), tile.getPosY(), tile.getPosZ())) != null) {
            tile2.setWalkingFlg(true);
        }
        super.func_149724_b(world, i, j, k, par5Entity);
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        ecru_TileEntityGrapeTub tub_tile;
        if (!world.field_72995_K) {
            return false;
        }
        if ((world.func_72805_g(i, j, k) & 1) == 1) {
            tub_tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k);
            if (tub_tile == null) {
                entityplayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.1"), new Object[0]));
                return false;
            }
        } else if ((world.func_72805_g(i, j, k) & 1) == 0) {
            if ((world.func_72805_g(i, j, k) & 8) == 8) {
                ecru_TileEntityGrapeTub _tile = (ecru_TileEntityGrapeTub) world.func_147438_o(i, j, k);
                if (_tile != null) {
                    TileEntity __tile = world.func_147438_o(_tile.getPosX(), _tile.getPosY(), _tile.getPosZ());
                    if (__tile != null && (__tile instanceof ecru_TileEntityGrapeTub)) {
                        tub_tile = (ecru_TileEntityGrapeTub) __tile;
                    } else {
                        return false;
                    }
                } else {
                    entityplayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.1"), new Object[0]));
                    return false;
                }
            } else {
                entityplayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.GrapeTub.error.2"), new Object[0]));
                return false;
            }
        } else {
            return false;
        }
        int tub_grapeNum = tub_tile.getGrapeNum();
        int tub_grapeNumMax = tub_tile.getGrapeNumMax();
        int tub_num = tub_tile.getTubNum() + 1;
        int t_time = tub_tile.getStompTime();
        int t_timeMax = tub_tile.getStompTimeMax();
        entityplayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.GrapeTub.1") + " : " + tub_grapeNum + "/" + (tub_grapeNumMax * tub_num), new Object[0]));
        entityplayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.GrapeTub.2") + " : " + tub_grapeNumMax, new Object[0]));
        entityplayer.func_146105_b(new ChatComponentTranslation(StatCollector.func_74838_a("MapleTree.text.GrapeTub.3") + " : " + (t_time / 20) + "/" + (t_timeMax / 20) + " Sec", new Object[0]));
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_top = par1IconRegister.func_94245_a("mapletree:grapeStompTub_top");
        tx_bottom = par1IconRegister.func_94245_a("mapletree:grapeStompTub_top");
        tx_side1 = par1IconRegister.func_94245_a("mapletree:grapeStompTub_side1");
        tx_side2 = par1IconRegister.func_94245_a("mapletree:grapeStompTub_side2");
        tx_inner1 = par1IconRegister.func_94245_a("mapletree:grapeStompTub_inner1");
        tx_inner2 = par1IconRegister.func_94245_a("mapletree:grapeStompTub_inner2");
    }

    public ecru_TileEntityGrapeTub func_149915_a(World world, int metadata) {
        return new ecru_TileEntityGrapeTub();
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, this.Y_MAX, 1.0f);
    }

    public void func_149743_a(World par1World, int i, int j, int k, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        Block pos_n = par1World.func_147439_a(i, j, k - 1);
        Block pos_s = par1World.func_147439_a(i, j, k + 1);
        Block pos_w = par1World.func_147439_a(i - 1, j, k);
        Block pos_e = par1World.func_147439_a(i + 1, j, k);
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, this.Y_MAX, 1.0f);
        super.func_149743_a(par1World, i, j, k, par5AxisAlignedBB, par6List, par7Entity);
        if (pos_w != this && pos_w != mod_ecru_MapleTree.blockHumanPowerDrive) {
            func_149676_a(0.0f, 0.0f, 0.0f, 0.0625f, 1.5f, 1.0f);
            super.func_149743_a(par1World, i, j, k, par5AxisAlignedBB, par6List, par7Entity);
        }
        if (pos_n != this && pos_n != mod_ecru_MapleTree.blockHumanPowerDrive) {
            func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 0.0625f);
            super.func_149743_a(par1World, i, j, k, par5AxisAlignedBB, par6List, par7Entity);
        }
        if (pos_e != this && pos_e != mod_ecru_MapleTree.blockHumanPowerDrive) {
            func_149676_a(1.0f - 0.0625f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
            super.func_149743_a(par1World, i, j, k, par5AxisAlignedBB, par6List, par7Entity);
        }
        if (pos_s != this && pos_s != mod_ecru_MapleTree.blockHumanPowerDrive) {
            func_149676_a(0.0f, 0.0f, 1.0f - 0.0625f, 1.0f, 1.5f, 1.0f);
            super.func_149743_a(par1World, i, j, k, par5AxisAlignedBB, par6List, par7Entity);
        }
    }

    public void func_149683_g() {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, this.Y_MAX, 1.0f);
    }

    private String getStr(int i) {
        String s = String.valueOf(i);
        if (i < 0) {
            s = s.substring(1, s.length());
        }
        String s2 = "000" + s;
        String r = s2.substring(s2.length() - 3, s2.length());
        if (i >= 0) {
            return "0" + r;
        }
        return "1" + r;
    }
}
