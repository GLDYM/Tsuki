package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.common.ecru_numericConstant;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityHumanPowerDrive;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ecru_BlockHumanPowerDrive extends BlockContainer {
    private final Random random;
    private IIcon tx_body;
    private ecru_numericConstant nc;

    public ecru_BlockHumanPowerDrive() {
        super(Material.field_151578_c);
        this.random = new Random();
        this.nc = new ecru_numericConstant();
        func_149675_a(false);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149692_a(int i) {
        return 0;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return mod_ecru_MapleTree.renderHumanPowerDriveID;
    }

    public ecru_TileEntityHumanPowerDrive func_149915_a(World var1, int meta) {
        return new ecru_TileEntityHumanPowerDrive();
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        float f = this.nc.P08;
        return AxisAlignedBB.func_72330_a(i + f, j, k + f, (i + 1) - f, (j + 1) - this.nc.P02, (k + 1) - f);
    }

    public AxisAlignedBB func_149633_g(World par1World, int i, int j, int k) {
        float f = this.nc.P08;
        return AxisAlignedBB.func_72330_a(i + f, j, k + f, (i + 1) - f, (j + 1) - this.nc.P02, (k + 1) - f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.tx_body;
    }

    public boolean isSideSolid(IBlockAccess world, int i, int j, int k, ForgeDirection side) {
        return true;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        if (world.field_72995_K) {
            return;
        }
        world.func_72805_g(i, j, k);
        if (entity instanceof EntityOcelot) {
            return;
        }
        if ((entity instanceof EntityMob) || (entity instanceof EntityAnimal) || (entity instanceof EntityTameable) || (entity instanceof EntityVillager)) {
            ecru_TileEntityHumanPowerDrive tile = (ecru_TileEntityHumanPowerDrive) world.func_147438_o(i, j, k);
            for (int n = 0; n < 4; n++) {
                if (entity.func_110124_au().getMostSignificantBits() == tile.dt_entityIdM[n] && entity.func_110124_au().getLeastSignificantBits() == tile.dt_entityIdL[n]) {
                    return;
                }
            }
            if (tile.getEntity(0) == null && !tile.getEntityUid(0)) {
                tile.setEntity(entity, 0);
                return;
            }
            if (tile.getEntity(2) == null && !tile.getEntityUid(2)) {
                tile.setEntity(entity, 2);
                return;
            }
            if (tile.getEntity(1) == null && !tile.getEntityUid(1)) {
                tile.setEntity(entity, 1);
            } else if (tile.getEntity(3) == null && !tile.getEntityUid(3)) {
                tile.setEntity(entity, 3);
            }
        }
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par5EntityPlayer.func_71045_bC() == null) {
            ecru_TileEntityHumanPowerDrive tile = (ecru_TileEntityHumanPowerDrive) world.func_147438_o(i, j, k);
            if (tile != null) {
                for (int m = 0; m < 4; m++) {
                    if (tile.getEntity(m) != null) {
                        tile.deleteEntity(m);
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public int func_149709_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return (par1IBlockAccess.func_72805_g(par2, par3, par4) & 8) == 8 ? 15 : 0;
    }

    public int func_149748_c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return (par1IBlockAccess.func_72805_g(par2, par3, par4) & 8) == 8 ? 15 : 0;
    }

    public boolean func_149744_f() {
        return true;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.tx_body = par1IconRegister.func_94245_a("mapletree:deco_wood");
    }
}
