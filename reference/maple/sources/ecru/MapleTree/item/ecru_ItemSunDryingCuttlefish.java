package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntitySunDrying;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_ItemSunDryingCuttlefish extends Item {
    private String itemName;
    private final int COUNT = 1800;

    public ecru_ItemSunDryingCuttlefish(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.field_77777_bU = 1;
        this.itemName = str;
        func_77655_b(str);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("countTimer", 0);
        nbt.func_74768_a("countTimerMax", 1800);
        itemStack.func_77982_d(nbt);
        itemList.add(itemStack);
    }

    public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("countTimer", 0);
        nbt.func_74768_a("countTimerMax", 1800);
        itemStack.func_77982_d(nbt);
        super.func_77622_d(itemStack, world, player);
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float par8, float par9, float par10) {
        if (!world.field_72995_K) {
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
            NBTTagCompound nbt = itemstack.func_77978_p();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                itemstack.func_77982_d(nbt);
                nbt.func_74768_a("countTimer", 0);
                nbt.func_74768_a("countTimerMax", 1800);
            }
            int countTimer = nbt.func_74762_e("countTimer");
            int countTimerMax = nbt.func_74762_e("countTimerMax");
            world.func_147465_d(i, j, k, mod_ecru_MapleTree.blockSunDryingNet, 0, 3);
            ecru_TileEntitySunDrying tile = (ecru_TileEntitySunDrying) world.func_147438_o(i, j, k);
            if (tile != null) {
                tile.setCountTimer(countTimer);
                tile.setCountTimerMax(countTimerMax);
                tile.setItemIn(new ItemStack(mod_ecru_MapleTree.Item_foodstuff, 1, 18));
                tile.setItemOut(new ItemStack(mod_ecru_MapleTree.Item_foodsDish, 9, 29));
                tile.setItemOrg(new ItemStack(this, 1, 0));
            }
            entityplayer.func_71045_bC().func_77979_a(1);
            return true;
        }
        return true;
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        NBTTagCompound nbt = itemstack.func_77978_p();
        if (nbt == null) {
            return;
        }
        int countTimer = nbt.func_74762_e("countTimer");
        int countTimerMax = nbt.func_74762_e("countTimerMax");
        if (countTimerMax != 0) {
            double integrity = countTimer / countTimerMax;
            String s = String.format("%.1f", Double.valueOf(integrity * 100.0d));
            list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.bonito.integrity") + " : " + s + " %");
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77791_bV;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.itemName);
    }
}
