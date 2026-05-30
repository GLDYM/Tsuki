package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemFountain extends ItemBlockWithMetadata {
    IIcon[] tx_fountain;

    public ecru_ItemFountain(Block i) {
        super(i, i);
        this.tx_fountain = new IIcon[7];
        func_77627_a(true);
        func_77656_e(0);
    }

    public int getPlacedBlockMetadata(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public IIcon func_77617_a(int i) {
        return this.tx_fountain[i & 7];
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < this.tx_fountain.length; i++) {
            this.tx_fountain[i] = par1IconRegister.func_94245_a("mapletree:i_fountain_" + i);
        }
    }
}
