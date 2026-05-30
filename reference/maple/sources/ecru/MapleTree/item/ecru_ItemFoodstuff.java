package ecru.MapleTree.item;

import ecru.MapleTree.common.ecru_foodstuffList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ecru_ItemFoodstuff extends Item {
    private String itemName;
    IIcon[] iIcon;

    public ecru_ItemFoodstuff(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ecru_foodstuffList.foodstuffList[] fl = ecru_foodstuffList.foodstuffList.values();
        for (int i = 0; i < fl.length; i++) {
            if (fl[i].e_use) {
                par3List.add(new ItemStack(par1, 1, i));
            }
        }
    }

    public String func_77667_c(ItemStack i) {
        int dm = i.func_77960_j();
        ecru_foodstuffList.foodstuffList[] fl = ecru_foodstuffList.foodstuffList.values();
        return "item." + fl[dm].e_itemName;
    }

    public IIcon func_77617_a(int i) {
        return this.iIcon[i];
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        ecru_foodstuffList.foodstuffList[] fl = ecru_foodstuffList.foodstuffList.values();
        this.iIcon = new IIcon[fl.length];
        for (int i = 0; i < fl.length; i++) {
            this.iIcon[i] = par1IconRegister.func_94245_a(fl[i].e_itemName);
        }
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public void func_77624_a(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        int dm = itemstack.func_77960_j();
        ecru_foodstuffList.foodstuffList[] fl = ecru_foodstuffList.foodstuffList.values();
        if (!fl[dm].e_use) {
            list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("entity.text.attention1.name"));
        }
    }
}
