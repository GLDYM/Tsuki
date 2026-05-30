package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ecru_EntityAIBeg extends EntityAIBase {
    private ecru_EntityMomiji theMomiji;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;
    private static final String __OBFID = "CL_00001576";

    public ecru_EntityAIBeg(ecru_EntityMomiji p_i1617_1_, float p_i1617_2_, int bit) {
        this.theMomiji = p_i1617_1_;
        this.worldObject = p_i1617_1_.field_70170_p;
        this.minPlayerDistance = p_i1617_2_;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        this.thePlayer = this.worldObject.func_72890_a(this.theMomiji, this.minPlayerDistance);
        if (this.thePlayer == null) {
            return false;
        }
        return hasPlayerGotAppleInHand(this.thePlayer);
    }

    public boolean func_75253_b() {
        return this.thePlayer.func_70089_S() && this.theMomiji.func_70068_e(this.thePlayer) <= ((double) (this.minPlayerDistance * this.minPlayerDistance)) && this.field_75384_e > 0 && hasPlayerGotAppleInHand(this.thePlayer);
    }

    public void func_75249_e() {
        this.theMomiji.func_70918_i(true);
        this.field_75384_e = 40 + this.theMomiji.func_70681_au().nextInt(40);
    }

    public void func_75251_c() {
        this.theMomiji.func_70918_i(false);
        this.thePlayer = null;
    }

    public void func_75246_d() {
        this.theMomiji.func_70671_ap().func_75650_a(this.thePlayer.field_70165_t, this.thePlayer.field_70163_u + this.thePlayer.func_70047_e(), this.thePlayer.field_70161_v, 10.0f, this.theMomiji.func_70646_bf());
        this.field_75384_e--;
    }

    private boolean hasPlayerGotAppleInHand(EntityPlayer p_75382_1_) {
        ItemStack itemstack = p_75382_1_.field_71071_by.func_70448_g();
        if (itemstack == null) {
            return false;
        }
        return (!this.theMomiji.isTamed() && itemstack.func_77973_b() == mod_ecru_MapleTree.Item_foodsDish && itemstack.func_77960_j() == 2) || this.theMomiji.func_70877_b(itemstack) || itemstack.func_77973_b() == mod_ecru_MapleTree.Item_mapleSyrup;
    }
}
