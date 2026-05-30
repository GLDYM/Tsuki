package ecru.MapleTree.item;

import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityWineBarrel;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_ItemWineBarrel extends ItemBlockWithMetadata {
    public ecru_ItemWineBarrel(Block i) {
        super(i, i);
        func_77627_a(true);
        func_77656_e(0);
        this.field_77777_bU = 1;
    }

    public void setNbtData(long a, int b, int c) {
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        itemList.add(itemStack);
    }

    public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        nbt.func_74768_a("wineQuantity", 0);
        nbt.func_74768_a("wineFerment", 0);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
        if (!world.field_72995_K) {
            NBTTagCompound nbt = itemstack.func_77978_p();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                itemstack.func_77982_d(nbt);
            }
            int wineQuantity = nbt.func_74762_e("wineQuantity");
            int wineFerment = nbt.func_74762_e("wineFerment");
            int meta = itemstack.func_77960_j() & 3;
            if (l == 0) {
                j--;
            }
            if (l == 1) {
                j++;
            }
            if (l == 2) {
                k--;
                meta |= 8;
            }
            if (l == 3) {
                k++;
                meta |= 8;
            }
            if (l == 4) {
                i--;
                meta |= 4;
            }
            if (l == 5) {
                i++;
                meta |= 4;
            }
            world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockWineBarrel, meta, 3);
            ecru_TileEntityWineBarrel tile = (ecru_TileEntityWineBarrel) world.func_147438_o(i, j, k);
            if (tile != null) {
                tile.setWineQuantity(wineQuantity);
                tile.setWineFerment(wineFerment);
            }
            entityplayer.func_71045_bC().func_77979_a(1);
            return true;
        }
        return true;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        NBTTagCompound nbt = itemstack.func_77978_p();
        if (nbt == null) {
            return;
        }
        int wineQuantity = nbt.func_74762_e("wineQuantity");
        int wineFerment = nbt.func_74762_e("wineFerment");
        int wineQuantityMax = 0;
        int wineFermentMax = 0;
        ecru_TileEntityWineBarrel tile = new ecru_TileEntityWineBarrel();
        if (tile != null) {
            wineQuantityMax = tile.getWineQuantityMax();
            wineFermentMax = tile.getWineFermentMax();
        }
        list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.GBAR_1") + " : " + wineQuantity + " / " + wineQuantityMax + " ml");
        list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.GBAR_2") + " : " + (wineFerment / 20) + " / " + (wineFermentMax / 20) + " Sec");
    }
}
