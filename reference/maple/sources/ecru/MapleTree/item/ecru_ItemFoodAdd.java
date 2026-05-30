package ecru.MapleTree.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ecru_ItemFoodAdd extends ItemFood {
    private String itemName;
    private int potionId;
    private int potionTime;
    private int potionLv;

    public ecru_ItemFoodAdd(int j, float k, boolean l, String str) {
        super(j, k, l);
        this.potionId = 0;
        this.potionTime = 0;
        this.potionLv = 0;
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
    }

    public ecru_ItemFoodAdd(int j, float k, boolean l, String str, int id, int time, int lv) {
        super(j, k, l);
        this.potionId = 0;
        this.potionTime = 0;
        this.potionLv = 0;
        func_77627_a(true);
        func_77656_e(0);
        this.itemName = str;
        func_77655_b(str);
        this.potionId = id;
        this.potionTime = time;
        this.potionLv = lv;
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par1ItemStack.field_77994_a--;
        par3EntityPlayer.func_71024_bL().func_151686_a(this, par1ItemStack);
        par2World.func_72956_a(par3EntityPlayer, "random.burp", 0.5f, (par2World.field_73012_v.nextFloat() * 0.1f) + 0.9f);
        func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        if (this.potionId != 0) {
            par3EntityPlayer.func_70690_d(new PotionEffect(this.potionId, this.potionTime, this.potionLv));
        }
        return par1ItemStack;
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.itemName);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77658_a() + "." + itemStack.func_77960_j();
    }

    public int func_77647_b(int par1) {
        return par1;
    }
}
