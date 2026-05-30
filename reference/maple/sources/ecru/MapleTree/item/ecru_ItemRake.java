package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.common.ecru_cropHarvest;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ecru_ItemRake extends Item {
    private Random random = new Random();
    private IIcon tex;
    private String name;
    private int type;

    public ecru_ItemRake(int d, String s, int t) {
        func_77625_d(1);
        func_77656_e(d);
        func_77655_b(s);
        this.type = t;
        this.name = s;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77662_d() {
        return true;
    }

    public int getPlacedBlockMetadata(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a();
    }

    public IIcon func_77617_a(int i) {
        return this.tex;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tex = par1IconRegister.func_94245_a(this.name);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int i, int j, int k, int par7, float par8, float par9, float par10) {
        if (par3World.field_72995_K) {
            return true;
        }
        int enchantedLv = getToolTipLv(par1ItemStack, 34);
        double _muki = MathHelper.func_76128_c(par2EntityPlayer.field_70759_as % 360.0f);
        double _muki2 = _muki < 0.0d ? 360.0d + _muki : _muki;
        double muki = _muki2 - 270.0d < 0.0d ? 360.0d - (270.0d - _muki2) : _muki2 - 270.0d;
        double rr = 6.0d;
        int diff = 2;
        if (enchantedLv > 0) {
            rr = 10.0d;
            diff = 1;
        }
        double mi = 6.2831855f * (((float) muki) / 360.0f);
        double xx = Math.cos(mi) * rr;
        double zz = Math.sin(mi) * rr;
        int myX = (int) par2EntityPlayer.field_70165_t;
        int myZ = (int) par2EntityPlayer.field_70161_v;
        DrawCircle(par3World, (myX < 0 ? myX - 1 : myX) + ((int) xx), j, (myZ < 0 ? myZ - 1 : myZ) + ((int) zz), ((int) rr) - diff);
        par1ItemStack.func_77972_a(1, par2EntityPlayer);
        return true;
    }

    public NBTTagList getNBTTagList(ItemStack p_92110_1_) {
        return (p_92110_1_.field_77990_d == null || !p_92110_1_.field_77990_d.func_150297_b("ench", 9)) ? new NBTTagList() : p_92110_1_.field_77990_d.func_74781_a("ench");
    }

    private int getToolTipLv(ItemStack is, int id) {
        int lv = 0;
        NBTTagList nbttaglist = getNBTTagList(is);
        if (nbttaglist != null) {
            for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
                short short1 = nbttaglist.func_150305_b(i).func_74765_d("id");
                int short2 = nbttaglist.func_150305_b(i).func_74765_d("lvl");
                if (Enchantment.field_77331_b[short1] != null && short1 == id && lv < short2) {
                    lv = short2;
                }
            }
        }
        return lv;
    }

    private void blockBreak(World world, Block block, int i, int j, int k) {
        if (!world.field_72995_K) {
            ecru_cropHarvest ch = new ecru_cropHarvest(world, block, i, j, k);
            ch.harvestBlock();
        }
    }

    private void DrawCircle(World world, int i, int j, int k, int r) {
        int x = r;
        int df = ((-2) * r) + 3;
        for (int z = 0; x >= z; z++) {
            line(world, -z, -x, x, i, j, k);
            line(world, z, -x, x, i, j, k);
            if (df > 0) {
                line(world, x, -z, z, i, j, k);
                line(world, -x, -z, z, i, j, k);
                df = df + (4 * ((-x) + z)) + 10;
                x--;
            } else {
                df = df + (4 * z) + 6;
            }
        }
    }

    private void line(World world, int z, int x0, int x1, int xx, int yy, int zz) {
        if (x0 > x1) {
            x1 = x0;
            x0 = x1;
        }
        for (int j = -1; j <= 2; j++) {
            for (int i = x0; i <= x1; i++) {
                Block block = world.func_147439_a(i + xx, j + yy, z + zz);
                if (this.type == 0 && block == mod_ecru_MapleTree.blockFallenLeaves) {
                    blockBreak(world, block, i + xx, j + yy, z + zz);
                }
                if (this.type == 1 && (block == Blocks.field_150329_H || block == Blocks.field_150330_I || block == Blocks.field_150327_N || block == Blocks.field_150328_O || block == Blocks.field_150398_cm || block == Blocks.field_150431_aC)) {
                    blockBreak(world, block, i + xx, j + yy, z + zz);
                }
            }
        }
    }
}
