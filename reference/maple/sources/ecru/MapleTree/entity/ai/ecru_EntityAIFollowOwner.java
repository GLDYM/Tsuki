package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ecru_EntityAIFollowOwner extends EntityAIBase {
    private ecru_EntityMomiji thePet;
    private ecru_EntityMomiji freeCheck;
    private EntityLivingBase theOwner;
    World theWorld;
    private double field_75336_f;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;
    private static final String __OBFID = "CL_00001585";

    public ecru_EntityAIFollowOwner(ecru_EntityMomiji p_i1625_1_, double p_i1625_2_, float p_i1625_4_, float p_i1625_5_, int bit) {
        this.thePet = p_i1625_1_;
        this.theWorld = p_i1625_1_.field_70170_p;
        this.field_75336_f = p_i1625_2_;
        this.petPathfinder = p_i1625_1_.func_70661_as();
        this.minDist = p_i1625_4_;
        this.maxDist = p_i1625_5_;
        func_75248_a(bit);
        this.freeCheck = p_i1625_1_;
    }

    public boolean func_75250_a() {
        Entity owner = this.thePet.func_70902_q();
        if (this.thePet.isFreedom() || owner == null || this.thePet.isSitting() || this.thePet.func_70068_e(owner) < this.minDist * this.minDist) {
            return false;
        }
        this.theOwner = owner;
        return true;
    }

    public boolean func_75253_b() {
        return (this.petPathfinder.func_75500_f() || this.thePet.func_70068_e(this.theOwner) <= ((double) (this.maxDist * this.maxDist)) || this.thePet.isSitting()) ? false : true;
    }

    public void func_75249_e() {
        this.field_75343_h = 0;
        this.field_75344_i = this.thePet.func_70661_as().func_75486_a();
        this.thePet.func_70661_as().func_75491_a(false);
    }

    public void func_75251_c() {
        this.theOwner = null;
        this.petPathfinder.func_75499_g();
        this.thePet.func_70661_as().func_75491_a(this.field_75344_i);
    }

    public void func_75246_d() {
        this.thePet.func_70671_ap().func_75651_a(this.theOwner, 10.0f, this.thePet.func_70646_bf());
        if (!this.thePet.isSitting()) {
            int i = this.field_75343_h - 1;
            this.field_75343_h = i;
            if (i <= 0) {
                this.field_75343_h = 10;
                if (!this.petPathfinder.func_75497_a(this.theOwner, this.field_75336_f * this.thePet.getStateBonusSpeed()) && !this.thePet.func_110167_bD() && this.thePet.func_70068_e(this.theOwner) >= 144.0d) {
                    int i2 = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
                    int j = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
                    int k = MathHelper.func_76128_c(this.theOwner.field_70121_D.field_72338_b);
                    for (int l = 0; l <= 4; l++) {
                        for (int i1 = 0; i1 <= 4; i1++) {
                            if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.func_147466_a(this.theWorld, i2 + l, k - 1, j + i1) && !this.theWorld.func_147439_a(i2 + l, k, j + i1).func_149721_r() && !this.theWorld.func_147439_a(i2 + l, k + 1, j + i1).func_149721_r()) {
                                this.thePet.func_70012_b(i2 + l + 0.5f, k, j + i1 + 0.5f, this.thePet.field_70177_z, this.thePet.field_70125_A);
                                this.petPathfinder.func_75499_g();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
