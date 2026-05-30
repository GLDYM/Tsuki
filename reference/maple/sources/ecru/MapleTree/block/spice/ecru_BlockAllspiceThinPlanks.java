package ecru.MapleTree.block.spice;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class ecru_BlockAllspiceThinPlanks extends ecru_BlockThinWood {
    public static IIcon[] tx_wood = new IIcon[2];

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        int muki = par2 & 12;
        int i = par2 & 3;
        if (muki == 4) {
            if (par1 == 5 || par1 == 4) {
                return tx_wood[1];
            }
            return tx_wood[0];
        }
        if (muki == 8) {
            if (par1 == 2 || par1 == 3) {
                return tx_wood[1];
            }
            return tx_wood[0];
        }
        if (par1 == 1 || par1 == 0) {
            return tx_wood[1];
        }
        return tx_wood[0];
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        tx_wood[0] = par1IconRegister.func_94245_a("mapletree:allspiceThinPlanks");
        tx_wood[1] = par1IconRegister.func_94245_a("mapletree:allspiceThinPlanks_top");
    }
}
