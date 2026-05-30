package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ecru_ItemParticle extends Item {
    private IIcon[] icons;
    public int aaa;
    String[] iconNames = {"mapletree:drip", "mapletree:leaf_red", "mapletree:leaf_yellow", "mapletree:leaf_orange", "mapletree:leaf_lime", "mapletree:spray", "mapletree:spark", "mapletree:spark2", "mapletree:bubble", "mapletree:spore", "mapletree:zzz"};

    public IIcon func_77617_a(int i) {
        return this.icons[i];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconData(String iconName) {
        for (int i = 0; i < this.iconNames.length; i++) {
            if (iconName.equalsIgnoreCase(this.iconNames[i])) {
                return this.icons[i];
            }
        }
        return null;
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[this.iconNames.length];
        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = par1IconRegister.func_94245_a(this.iconNames[i]);
        }
    }

    public int getII() {
        return 1;
    }
}
