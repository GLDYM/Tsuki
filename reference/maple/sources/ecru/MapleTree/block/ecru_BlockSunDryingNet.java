package ecru.MapleTree.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class ecru_BlockSunDryingNet extends ecru_BlockSunDrying {

    @SideOnly(Side.CLIENT)
    public static IIcon tx_top;

    @SideOnly(Side.CLIENT)
    public static IIcon tx_side;
    private final Random random;

    public ecru_BlockSunDryingNet() {
        super(Material.field_151578_c);
        this.random = new Random();
        func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
    }
}
