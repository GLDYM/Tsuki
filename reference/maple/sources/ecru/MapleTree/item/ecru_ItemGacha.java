package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.ecru_EntityGacha;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_ItemGacha extends Item {
    private String itemName;

    public ecru_ItemGacha(String str) {
        this.itemName = str;
        func_77655_b(str);
        func_77656_e(0);
        func_77627_a(true);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!entityPlayer.field_71075_bZ.field_75098_d) {
            itemStack.field_77994_a--;
        }
        world.func_72956_a(entityPlayer, "random.bow", 0.5f, 0.4f / ((field_77697_d.nextFloat() * 0.4f) + 0.8f));
        if (!world.field_72995_K) {
            world.func_72838_d(new ecru_EntityGacha(world, entityPlayer, itemStack.func_77960_j()));
        }
        return itemStack;
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.itemName);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77791_bV;
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
