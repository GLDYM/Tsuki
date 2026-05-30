package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ecru_ItemMapleSapling extends ItemBlockWithMetadata {
    IIcon[] tx_sapling;

    public ecru_ItemMapleSapling(Block i) {
        super(i, i);
        this.tx_sapling = new IIcon[5];
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

    public IIcon func_77617_a(int i) {
        return this.tx_sapling[i & 3];
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tx_sapling[0] = par1IconRegister.func_94245_a("mapletree:sapling_red");
        this.tx_sapling[1] = par1IconRegister.func_94245_a("mapletree:sapling_yellow");
        this.tx_sapling[2] = par1IconRegister.func_94245_a("mapletree:sapling_orange");
        this.tx_sapling[3] = par1IconRegister.func_94245_a("mapletree:sapling_lime");
    }
}
