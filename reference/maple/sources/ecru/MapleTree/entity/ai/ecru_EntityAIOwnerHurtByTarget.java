package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityTameable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class ecru_EntityAIOwnerHurtByTarget extends EntityAITarget {
    ecru_EntityTameable theDefendingTameable;
    EntityLivingBase theOwnerAttacker;
    private int field_142051_e;
    private static final String __OBFID = "CL_00001624";
    private int TARGET_DISTANCE;

    public ecru_EntityAIOwnerHurtByTarget(ecru_EntityTameable p_i1667_1_, int bit) {
        super(p_i1667_1_, false);
        this.TARGET_DISTANCE = 15;
        this.theDefendingTameable = p_i1667_1_;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase;
        if (!this.theDefendingTameable.isTamed() || (entitylivingbase = this.theDefendingTameable.func_70902_q()) == null) {
            return false;
        }
        this.theOwnerAttacker = entitylivingbase.func_70643_av();
        if (this.theOwnerAttacker instanceof EntityPlayer) {
            return false;
        }
        if (this.theOwnerAttacker != null && this.theOwnerAttacker.getClass().getSimpleName().equals("LMM_EntityLittleMaid")) {
            return false;
        }
        int i = entitylivingbase.func_142015_aE();
        if (this.theOwnerAttacker == null) {
            return false;
        }
        boolean r1 = i != this.field_142051_e;
        boolean r2_ = func_75296_a(this.theOwnerAttacker, false);
        getTargetDistance(this.theOwnerAttacker);
        return r1 && r2_;
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.theOwnerAttacker);
        EntityLivingBase entitylivingbase = this.theDefendingTameable.func_70902_q();
        if (entitylivingbase != null) {
            this.field_142051_e = entitylivingbase.func_142015_aE();
        }
        super.func_75249_e();
    }

    private boolean getTargetDistance(EntityLivingBase entity) {
        if (getDistanceSquared(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v) > this.TARGET_DISTANCE * this.TARGET_DISTANCE) {
            return false;
        }
        return true;
    }

    private float getDistanceSquared(double x, double y, double z) {
        float f = (float) (this.theDefendingTameable.field_70165_t - x);
        float f1 = (float) (this.theDefendingTameable.field_70163_u - y);
        float f2 = (float) (this.theDefendingTameable.field_70161_v - z);
        return (f * f) + (f1 * f1) + (f2 * f2);
    }
}
