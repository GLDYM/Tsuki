package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ecru_EntityAICollect extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private int age = 0;

    public ecru_EntityAICollect(ecru_EntityMomiji pEntityMomiji, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntityMomiji;
        this.moveSpeed = pmoveSpeed;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if (this.entityMomiji.getFirstEmptyStack() > -1) {
            List llist = this.entityMomiji.field_70170_p.func_72872_a(EntityItem.class, this.entityMomiji.field_70121_D.func_72314_b(8.0d, 2.0d, 8.0d));
            if (!llist.isEmpty()) {
                int li = this.entityMomiji.func_70681_au().nextInt(llist.size());
                EntityItem ei = (EntityItem) llist.get(li);
                EntityPlayer ep = this.entityMomiji.func_70902_q() != null ? (EntityPlayer) this.entityMomiji.func_70902_q() : this.entityMomiji.field_70170_p.func_72890_a(this.entityMomiji, 16.0d);
                if (ei.field_70128_L || !ei.field_70122_E || ei.field_145804_b > 0 || ei.func_70027_ad() || !canEntityItemBeSeen(ei)) {
                    return false;
                }
                if (ep == null || ep.func_70092_e(ei.field_70165_t + (MathHelper.func_76126_a(ep.field_70177_z * 0.017453292f) * 2.0d), ei.field_70163_u, ei.field_70161_v - (MathHelper.func_76134_b(ep.field_70177_z * 0.017453292f) * 2.0d)) > 4.0d) {
                    ei.func_92059_d();
                    this.targetItem = ei;
                    this.age = 0;
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void func_75249_e() {
        this.lastAvoidWater = this.entityMomiji.func_70661_as().func_75486_a();
        this.entityMomiji.func_70661_as().func_75491_a(true);
    }

    public boolean func_75253_b() {
        int i = this.age + 1;
        this.age = i;
        if (i <= 400) {
            return !this.targetItem.field_70128_L && this.entityMomiji.getFirstEmptyStack() > -1 && this.entityMomiji.func_70068_e(this.targetItem) < 100.0d;
        }
        this.age = 0;
        return false;
    }

    public void func_75251_c() {
        this.age = 0;
        this.targetItem = null;
        this.entityMomiji.func_70661_as().func_75499_g();
        this.entityMomiji.func_70661_as().func_75491_a(this.lastAvoidWater);
    }

    public void func_75246_d() {
        this.entityMomiji.func_70671_ap().func_75651_a(this.targetItem, 30.0f, this.entityMomiji.func_70646_bf());
        PathNavigate lnavigater = this.entityMomiji.func_70661_as();
        if (lnavigater.func_75500_f()) {
            if (this.targetItem.func_70090_H()) {
                lnavigater.func_75491_a(false);
            }
            PathEntity lpath = lnavigater.func_75488_a(this.targetItem.field_70165_t, this.targetItem.field_70163_u, this.targetItem.field_70161_v);
            lnavigater.func_75484_a(lpath, this.moveSpeed);
        }
    }

    public boolean canEntityItemBeSeen(Entity entity) {
        return this.entityMomiji.getCompass() != -1 || this.entityMomiji.field_70170_p.func_72933_a(Vec3.func_72443_a(this.entityMomiji.field_70165_t, this.entityMomiji.field_70163_u + ((double) this.entityMomiji.func_70047_e()), this.entityMomiji.field_70161_v), Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + ((entity.field_70121_D.field_72338_b - entity.field_70121_D.field_72338_b) / 2.0d), entity.field_70161_v)) == null;
    }
}
