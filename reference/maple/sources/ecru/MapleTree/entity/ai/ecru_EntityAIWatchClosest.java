package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class ecru_EntityAIWatchClosest extends EntityAIBase {
    private ecru_EntityMomiji theWatcher;
    protected Entity closestEntity;
    private float maxDistanceForPlayer;
    private int lookTime;
    private float field_75331_e;
    private Class watchedClass;
    private static final String __OBFID = "CL_00001592";

    public ecru_EntityAIWatchClosest(ecru_EntityMomiji p_i1631_1_, Class p_i1631_2_, float p_i1631_3_, int bit) {
        this.theWatcher = p_i1631_1_;
        this.watchedClass = p_i1631_2_;
        this.maxDistanceForPlayer = p_i1631_3_;
        this.field_75331_e = 0.02f;
        func_75248_a(bit);
    }

    public ecru_EntityAIWatchClosest(ecru_EntityMomiji p_i1632_1_, Class p_i1632_2_, float p_i1632_3_, float p_i1632_4_, int bit) {
        this.theWatcher = p_i1632_1_;
        this.watchedClass = p_i1632_2_;
        this.maxDistanceForPlayer = p_i1632_3_;
        this.field_75331_e = p_i1632_4_;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if ((this.theWatcher.isSitting() && this.theWatcher.func_110143_aJ() <= 10.0f) || this.theWatcher.func_70681_au().nextFloat() >= this.field_75331_e) {
            return false;
        }
        if (this.theWatcher.func_70638_az() != null) {
            this.closestEntity = this.theWatcher.func_70638_az();
        }
        if (this.watchedClass == EntityPlayer.class) {
            this.closestEntity = this.theWatcher.field_70170_p.func_72890_a(this.theWatcher, this.maxDistanceForPlayer);
        } else {
            this.closestEntity = this.theWatcher.field_70170_p.func_72857_a(this.watchedClass, this.theWatcher.field_70121_D.func_72314_b(this.maxDistanceForPlayer, 3.0d, this.maxDistanceForPlayer), this.theWatcher);
        }
        return this.closestEntity != null;
    }

    public boolean func_75253_b() {
        return this.closestEntity.func_70089_S() && this.theWatcher.func_70068_e(this.closestEntity) <= ((double) (this.maxDistanceForPlayer * this.maxDistanceForPlayer)) && this.lookTime > 0;
    }

    public void func_75249_e() {
        this.lookTime = 40 + this.theWatcher.func_70681_au().nextInt(40);
    }

    public void func_75251_c() {
        this.closestEntity = null;
    }

    public void func_75246_d() {
        this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0f, this.theWatcher.func_70646_bf());
        this.lookTime--;
    }
}
