package ecru.MapleTree.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ecru_ItemCloveWood extends ItemBlockWithMetadata {
    public ecru_ItemCloveWood(Block i) {
        super(i, i);
        func_77627_a(true);
        func_77656_e(0);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
