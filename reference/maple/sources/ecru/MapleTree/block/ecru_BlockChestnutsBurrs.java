package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ecru_BlockChestnutsBurrs extends Block {
    public static IIcon[] tx_chestnut;
    private final int GROWTH_MAX = 4;

    public ecru_BlockChestnutsBurrs() {
        super(Material.field_151584_j);
        this.GROWTH_MAX = 4;
        func_149675_a(true);
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 4));
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return true;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        Block id = world.func_147439_a(i, j + 1, k);
        int meta = world.func_72805_g(i, j + 1, k);
        if (id != mod_ecru_MapleTree.blockMapleLeaves || (meta & 3) != 3) {
            return false;
        }
        return true;
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        if (!func_149742_c(world, i, j, k)) {
            func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
            world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 3);
        }
    }

    public void func_149674_a(World world, int i, int j, int k, Random random) {
        boolean light = false;
        if (world.func_72957_l(i, j, k) >= 9) {
            light = true;
        }
        int meta = world.func_72805_g(i, j, k);
        if (meta < 0 || meta >= 4) {
            meta = 0;
        }
        if (meta < 4 && random.nextInt(5) == 0 && light) {
            meta++;
        }
        world.func_72921_c(i, j, k, meta, 3);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 >= 0 && par2 <= 4) {
            return tx_chestnut[par2];
        }
        return ecru_BlockMapleLeaves.tx_error;
    }

    public int func_149645_b() {
        return 1;
    }

    public void func_149690_a(World world, int i, int j, int k, int l, float m, int n) {
        int meta = l & 7;
        if (meta >= 4) {
            func_149642_a(world, i, j, k, new ItemStack(mod_ecru_MapleTree.Item_chestnutsBburrs, 1, 0));
        }
    }

    public void func_149664_b(World world, int i, int j, int k, int l) {
        world.func_147449_b(i, j, k, mod_ecru_MapleTree.blockChestnutsBurrs);
    }

    public Item func_149650_a(int i, Random r, int meta) {
        if (meta == 4) {
            return Item.func_150898_a(mod_ecru_MapleTree.blockChestnutsBurrs);
        }
        return null;
    }

    public int func_149692_a(int par1) {
        return 4;
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float o) {
        return false;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
        func_149676_a(0.15625f, 0.15625f, 0.15625f, 0.78125f, 1.0f, 0.78125f);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        if (world.func_72805_g(i, j, k) == 4 && !entity.getEntityData().equals("item.item.mapletree:chestnutsBburrs")) {
            entity.func_70097_a(DamageSource.field_76367_g, 1.0f);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_chestnut = new IIcon[5];
        for (int i = 0; i < tx_chestnut.length; i++) {
            tx_chestnut[i] = par1IconRegister.func_94245_a("mapletree:chestnutsBburrs_" + i);
        }
    }
}
