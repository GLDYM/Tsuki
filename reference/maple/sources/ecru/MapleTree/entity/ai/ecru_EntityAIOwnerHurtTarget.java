package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.entity.ecru_EntityTameable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class ecru_EntityAIOwnerHurtTarget extends EntityAITarget {
    ecru_EntityTameable theEntityTameable;
    EntityLivingBase theTarget;
    private int field_142050_e;
    private static final String __OBFID = "CL_00001625";
    private int TARGET_DISTANCE;

    public ecru_EntityAIOwnerHurtTarget(ecru_EntityTameable p_i1668_1_, int bit) {
        super(p_i1668_1_, false);
        this.TARGET_DISTANCE = 15;
        this.theEntityTameable = p_i1668_1_;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase;
        if (!this.theEntityTameable.isTamed() || (entitylivingbase = this.theEntityTameable.func_70902_q()) == null || (entitylivingbase.func_110144_aD() instanceof ecru_EntityMomiji)) {
            return false;
        }
        if (entitylivingbase.func_110144_aD() != null && entitylivingbase.func_110144_aD().getClass().getSimpleName().equals("LMM_EntityLittleMaid")) {
            return false;
        }
        this.theTarget = entitylivingbase.func_110144_aD();
        int i = entitylivingbase.func_142013_aG();
        if (this.theTarget == null) {
            return false;
        }
        boolean r1 = i != this.field_142050_e;
        boolean r2_ = func_75296_a(this.theTarget, false);
        getTargetDistance(this.theTarget);
        return r1 && r2_;
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.theTarget);
        EntityLivingBase entitylivingbase = this.theEntityTameable.func_70902_q();
        if (entitylivingbase != null) {
            this.field_142050_e = entitylivingbase.func_142013_aG();
        }
        super.func_75249_e();
    }

    private boolean getTargetDistance(EntityLivingBase entity) {
        if (getDistanceSquared(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v) > this.TARGET_DISTANCE * this.TARGET_DISTANCE) {
            return false;
        }
        return true;
    }

    public float getDistanceSquared(double x, double y, double z) {
        float f = (float) (this.theEntityTameable.field_70165_t - x);
        float f1 = (float) (this.theEntityTameable.field_70163_u - y);
        float f2 = (float) (this.theEntityTameable.field_70161_v - z);
        return (f * f) + (f1 * f1) + (f2 * f2);
    }
}
