package ecru.MapleTree.item;

import ecru.MapleTree.common.ecru_mushroomList;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityCompost;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_ItemMushroom extends Item {
    private String itemName;
    IIcon[] iIcon;
    public static int mushroomNum;

    public ecru_ItemMushroom(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        mushroomNum = mu.length;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        for (int i = 0; i < mu.length; i++) {
            if (mu[i].e_use) {
                par3List.add(new ItemStack(par1, 1, i));
            }
        }
    }

    public static int getMushroomNum() {
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        return mu.length;
    }

    public String func_77667_c(ItemStack i) {
        int dm = i.func_77960_j();
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        return "item.mapletree:item_" + mu[dm].e_itemName;
    }

    public IIcon func_77617_a(int i) {
        return this.iIcon[i];
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        this.iIcon = new IIcon[mu.length];
        for (int i = 0; i < mu.length; i++) {
            this.iIcon[i] = par1IconRegister.func_94245_a("mapletree:item_" + mu[i].e_itemName);
        }
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        int dm = itemstack.func_77960_j();
        ecru_mushroomList.mushroomList[] mu = ecru_mushroomList.mushroomList.values();
        if (!mu[dm].e_use) {
            list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("entity.text.attention1.name"));
        }
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
        if (!world.field_72995_K) {
            int type = itemstack.func_77960_j();
            if (type >= mushroomNum) {
                return false;
            }
            if (l == 0) {
                j--;
            }
            if (l == 1) {
                j++;
            }
            if (l == 2) {
                k--;
            }
            if (l == 3) {
                k++;
            }
            if (l == 4) {
                i--;
            }
            if (l == 5) {
                i++;
            }
            Block block = world.func_147439_a(i, j - 1, k);
            block.func_149688_o();
            if (world.func_147439_a(i, j, k) != Blocks.field_150350_a) {
                return false;
            }
            world.func_147449_b(i, j, k, mod_ecru_MapleTree.blockCompost);
            TileEntity _tile = world.func_147438_o(i, j, k);
            if (_tile instanceof ecru_TileEntityCompost) {
                ecru_TileEntityCompost tile = (ecru_TileEntityCompost) _tile;
                int s = entityplayer.func_70093_af() ? 1 : 7;
                tile.setMushroomType(type);
                world.func_72921_c(i, j, k, 8 + s, 3);
                itemstack.field_77994_a--;
                return true;
            }
            return true;
        }
        return true;
    }
}
