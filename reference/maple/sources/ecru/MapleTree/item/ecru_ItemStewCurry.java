package ecru.MapleTree.item;

import ecru.MapleTree.common.ecru_curryspiceList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ecru_ItemStewCurry extends ItemFood {
    private int meta;
    private int itemUseDuration;
    private int healAmount;
    private boolean isWolfsFavoriteMeat;
    private float saturationModifier;
    private String itemName;
    private int potionId;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;
    IIcon[] iIcon;

    public ecru_ItemStewCurry(String str) {
        super(1, 0.6f, false);
        this.potionId = 0;
        this.potionDuration = 0;
        this.potionAmplifier = 0;
        this.potionEffectProbability = 0.0f;
        func_77627_a(true);
        func_77656_e(0);
        this.itemUseDuration = 32;
        this.healAmount = 0;
        this.saturationModifier = 0.0f;
        this.isWolfsFavoriteMeat = false;
        func_77848_i();
        this.itemName = str;
        func_77655_b(str);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        ecru_curryspiceList.stewCurryList[] scl = ecru_curryspiceList.stewCurryList.values();
        for (int i = 0; i < scl.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int dm = par1ItemStack.func_77960_j();
        ecru_curryspiceList.stewCurryList[] scl = ecru_curryspiceList.stewCurryList.values();
        par1ItemStack.field_77994_a--;
        par3EntityPlayer.func_71024_bL().func_75122_a(scl[dm].healAmount, scl[dm].saturationModifier);
        par2World.func_72956_a(par3EntityPlayer, "random.burp", 0.5f, (par2World.field_73012_v.nextFloat() * 0.1f) + 0.9f);
        func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }

    protected void func_77849_c(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.field_72995_K && this.potionId > 0 && par2World.field_73012_v.nextFloat() < this.potionEffectProbability) {
            par3EntityPlayer.func_70690_d(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
        }
    }

    public String func_77667_c(ItemStack i) {
        int dm = i.func_77960_j();
        ecru_curryspiceList.stewCurryList[] scl = ecru_curryspiceList.stewCurryList.values();
        return "item." + scl[dm].stewCurryName;
    }

    public IIcon func_77617_a(int i) {
        return this.iIcon[i];
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        ecru_curryspiceList.stewCurryList[] scl = ecru_curryspiceList.stewCurryList.values();
        this.iIcon = new IIcon[scl.length];
        for (int i = 0; i < scl.length; i++) {
            this.iIcon[i] = par1IconRegister.func_94245_a(scl[i].stewCurryName);
        }
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
