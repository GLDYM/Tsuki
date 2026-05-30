package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemChestnutsBurrs extends ItemBlockWithMetadata {
    IIcon[] tx_chestnutsBburrs;
    private final int GROWTH_MAX = 4;

    public ecru_ItemChestnutsBurrs(Block i) {
        super(i, i);
        this.GROWTH_MAX = 4;
        func_77627_a(true);
        func_77656_e(0);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public IIcon func_77617_a(int i) {
        return this.tx_chestnutsBburrs[i];
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tx_chestnutsBburrs = new IIcon[5];
        for (int i = 0; i < this.tx_chestnutsBburrs.length; i++) {
            this.tx_chestnutsBburrs[i] = par1IconRegister.func_94245_a("mapletree:chestnutsBburrs_" + i);
        }
    }
}
