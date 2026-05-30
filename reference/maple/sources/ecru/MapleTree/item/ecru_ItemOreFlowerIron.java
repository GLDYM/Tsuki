package ecru.MapleTree.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemOreFlowerIron extends ItemBlockWithMetadata {
    IIcon[] tx_flower;

    public ecru_ItemOreFlowerIron(Block i) {
        super(i, i);
        this.tx_flower = new IIcon[8];
        func_77627_a(true);
        func_77656_e(0);
    }

    public int getPlacedBlockMetadata(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
