package ecru.MapleTree.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemJewel extends Item {
    private String[] itemName = {"mapletree:mapleDiamond", "mapletree:demantoidGarnet", "mapletree:demantoidGarnet_yellow", "mapletree:demantoidGarnet_blue", "mapletree:marble", "mapletree:marble_black", "mapletree:mapleDiamondBlock"};
    IIcon[] icon = new IIcon[7];
    private int[] iIndex = {0, 1, 2, 3, 16, 17, 32};

    public ecru_ItemJewel() {
        func_77627_a(true);
        func_77656_e(0);
        func_77655_b("mapletree:jewel");
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 16));
        par3List.add(new ItemStack(par1, 1, 17));
        par3List.add(new ItemStack(par1, 1, 32));
    }

    public IIcon func_77617_a(int par1) {
        return this.icon[getMeta(par1)];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item." + this.itemName[getMeta(par1ItemStack.func_77960_j())];
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.itemName.length; i++) {
            this.icon[i] = par1IconRegister.func_94245_a(this.itemName[i]);
        }
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    private int getMeta(int n) {
        for (int i = 0; i < this.iIndex.length; i++) {
            if (n == this.iIndex[i]) {
                return i;
            }
        }
        return 0;
    }
}
