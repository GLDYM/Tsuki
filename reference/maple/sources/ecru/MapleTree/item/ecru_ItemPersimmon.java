package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemPersimmon extends ItemBlockWithMetadata {
    IIcon tx_persimmon;

    public ecru_ItemPersimmon(Block i) {
        super(i, i);
        func_77627_a(true);
        func_77656_e(0);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a();
    }

    public IIcon func_77617_a(int i) {
        return this.tx_persimmon;
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tx_persimmon = par1IconRegister.func_94245_a("mapletree:i_persimmon");
    }
}
