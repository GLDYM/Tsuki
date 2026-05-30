package ecru.MapleTree.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ecru_ItemMapleSyrupPotion extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon tx_potion;

    @SideOnly(Side.CLIENT)
    private IIcon tx_potion_contents;

    public ecru_ItemMapleSyrupPotion() {
        func_77627_a(true);
        func_77656_e(0);
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.field_72995_K) {
            switch (par1ItemStack.func_77960_j()) {
                case 0:
                    par3EntityPlayer.func_70690_d(new PotionEffect(Potion.field_76432_h.field_76415_H, 1, 0));
                    break;
                case 1:
                    par3EntityPlayer.func_70690_d(new PotionEffect(Potion.field_76432_h.field_76415_H, 1, 1));
                    break;
                case 2:
                    par3EntityPlayer.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 3600, 0));
                    break;
                case 3:
                    par3EntityPlayer.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 3600, 1));
                    break;
            }
        }
        if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
            par1ItemStack.field_77994_a--;
        }
        if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
            if (par1ItemStack.field_77994_a <= 0) {
                return new ItemStack(Items.field_151069_bo);
            }
            par3EntityPlayer.field_71071_by.func_70441_a(new ItemStack(Items.field_151069_bo));
        }
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.drink;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.tx_potion;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return par2 == 0 ? this.tx_potion_contents : super.func_77618_c(par1, par2);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromDamage(int par1) {
        switch (par1) {
            case 0:
                return 16746496;
            case 1:
                return 16729088;
            case 2:
                return 16711935;
            case 3:
                return 10027229;
            default:
                return 255;
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par2 > 0) {
            return 16777215;
        }
        return getColorFromDamage(par1ItemStack.func_77960_j());
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean isEffectInstant(int par1) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(ItemStack par1ItemStack) {
        return false;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        switch (par1ItemStack.func_77960_j()) {
            case 0:
                par3List.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.HEAL1"));
                break;
            case 1:
                par3List.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.HEAL2"));
                break;
            case 2:
                par3List.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.SPEED1"));
                break;
            case 3:
                par3List.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("MapleTree.text.SPEED2"));
                break;
            default:
                par3List.add(EnumChatFormatting.GRAY + "error");
                break;
        }
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    @SideOnly(Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        super.func_150895_a(par1, par2CreativeTabs, par3List);
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.tx_potion = par1IconRegister.func_94245_a("potion_bottle_empty");
        this.tx_potion_contents = par1IconRegister.func_94245_a("potion_overlay");
    }
}
