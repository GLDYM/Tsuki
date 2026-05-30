package ecru.MapleTree.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ecru_mapleCreativeTab extends CreativeTabs {
    public ecru_mapleCreativeTab(String type) {
        super(type);
    }

    @SideOnly(Side.CLIENT)
    public Item func_78016_d() {
        return mod_ecru_MapleTree.Item_tabIcon;
    }
}
