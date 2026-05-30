package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_ItemBoiledBonito extends Item {
    private String itemName;

    public ecru_ItemBoiledBonito(String str) {
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
        this.field_77777_bU = 1;
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        itemList.add(itemStack);
    }

    public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.func_77982_d(nbt);
        nbt.func_74768_a("ageCount", 0);
    }

    public void func_77663_a(ItemStack itemStack, World world, Entity entity, int l, boolean flg) {
    }

    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (entityItem.field_70170_p.field_72995_K) {
            return false;
        }
        ItemStack itemStack = entityItem.func_92059_d();
        NBTTagCompound nbt = itemStack.func_77978_p();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            itemStack.func_77982_d(nbt);
        }
        int ageCount = nbt.func_74762_e("ageCount");
        if (entityItem.field_70292_b > 1200) {
            ageCount++;
            entityItem.field_70292_b = 0;
        } else if (ageCount >= 60) {
            entityItem.field_70292_b = 6000;
        }
        nbt.func_74768_a("ageCount", ageCount);
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77791_bV;
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.itemName);
    }
}
