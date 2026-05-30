package ecru.MapleTree.item;

import ecru.MapleTree.common.ecru_curryspiceList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemCurrySpice extends Item {
    private String itemName;
    IIcon[] iIcon;

    public ecru_ItemCurrySpice(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ecru_curryspiceList.curryspiceList[] csl = ecru_curryspiceList.curryspiceList.values();
        for (int i = 0; i < csl.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public String func_77667_c(ItemStack i) {
        int dm = i.func_77960_j();
        ecru_curryspiceList.curryspiceList[] csl = ecru_curryspiceList.curryspiceList.values();
        return "item." + csl[dm].currySpiceName;
    }

    public IIcon func_77617_a(int i) {
        return this.iIcon[i];
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        ecru_curryspiceList.curryspiceList[] csl = ecru_curryspiceList.curryspiceList.values();
        this.iIcon = new IIcon[csl.length];
        for (int i = 0; i < csl.length; i++) {
            this.iIcon[i] = par1IconRegister.func_94245_a(csl[i].currySpiceName);
        }
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
