package ecru.MapleTree.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ecru_Particles {
    private static ecru_Particles instance;
    String[] iconNames = {"mapletree:drip", "mapletree:leaf_red", "mapletree:leaf_yellow", "mapletree:leaf_orange", "mapletree:leaf_lime", "mapletree:spray", "mapletree:spark", "mapletree:spark2"};
    IIcon[] icons;
    IIcon nnn;

    public static ecru_Particles getInstance() {
        if (instance == null) {
            instance = new ecru_Particles();
        }
        return instance;
    }

    @SideOnly(Side.CLIENT)
    public void handleTextureRemap(TextureStitchEvent.Pre event) {
        if (event.map.func_130086_a() == 1) {
            getInstance().registerIcons(event.map);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[this.iconNames.length];
        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = par1IconRegister.func_94245_a(this.iconNames[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(String iconName) {
        for (int i = 0; i < this.iconNames.length; i++) {
            if (iconName.equalsIgnoreCase(this.iconNames[i])) {
                return this.icons[i];
            }
        }
        return null;
    }
}
