package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityTameable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;

public class ecru_EntityAISit extends EntityAIBase {
    private ecru_EntityTameable theEntity;
    private boolean isSitting;
    private static final String __OBFID = "CL_00001613";

    public ecru_EntityAISit(ecru_EntityTameable p_i1654_1_) {
        this.theEntity = p_i1654_1_;
        func_75248_a(5);
    }

    public boolean func_75250_a() {
        if (!this.theEntity.isTamed() || this.theEntity.func_70090_H() || !this.theEntity.field_70122_E) {
            return false;
        }
        Entity owner = this.theEntity.func_70902_q();
        if (owner == null) {
            return this.isSitting;
        }
        if (this.theEntity.func_70068_e(owner) >= 144.0d || owner.func_70643_av() == null) {
            return this.isSitting;
        }
        return false;
    }

    public void func_75249_e() {
        this.theEntity.func_70661_as().func_75499_g();
        this.theEntity.setSitting(true);
    }

    public void func_75251_c() {
        this.theEntity.setSitting(false);
    }

    public void setSitting(boolean p_75270_1_) {
        this.isSitting = p_75270_1_;
    }
}
