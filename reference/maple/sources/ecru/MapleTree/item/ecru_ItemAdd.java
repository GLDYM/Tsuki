package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemAdd extends Item {
    private String itemName;

    public ecru_ItemAdd(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.itemName);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77791_bV;
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
