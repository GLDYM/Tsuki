package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ecru_ItemCabbage extends ItemBlockWithMetadata {
    public ecru_ItemCabbage(Block i) {
        super(i, i);
        func_77627_a(true);
        func_77656_e(0);
    }

    public int getPlacedBlockMetadata(int i) {
        return i;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par1) {
        return 16777215;
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
