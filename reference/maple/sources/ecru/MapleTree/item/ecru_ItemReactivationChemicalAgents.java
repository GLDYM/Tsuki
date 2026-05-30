package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_ItemReactivationChemicalAgents extends Item {
    IIcon icon;

    public boolean func_77648_a(ItemStack itemStack, EntityPlayer entityPlayer, World world, int i, int j, int k, int l, float ew, float tb, float sn) {
        Block block = world.func_147439_a(i, j, k);
        int meta = world.func_72805_g(i, j, k);
        if (block == mod_ecru_MapleTree.blockGrape && meta >= 11 && meta <= 15) {
            if (meta == 15 && !world.field_72995_K) {
                EntityItem ei = new EntityItem(world, i + 0.5d, j - 0.3d, k + 0.5d, new ItemStack(mod_ecru_MapleTree.Item_grape, 1, 0));
                world.func_72838_d(ei);
            }
            world.func_72921_c(i, j, k, 10, 3);
            itemStack.field_77994_a--;
            return true;
        }
        return false;
    }

    public IIcon func_77617_a(int i) {
        return this.icon;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("mapletree:reactivationChemicalAgents");
    }
}
