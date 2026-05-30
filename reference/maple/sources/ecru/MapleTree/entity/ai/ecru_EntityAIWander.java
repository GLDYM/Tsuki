package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class ecru_EntityAIWander extends EntityAIBase {
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private static final String __OBFID = "CL_00001608";
    private ecru_EntityMomiji entitymomiji;

    public ecru_EntityAIWander(EntityCreature p_i1648_1_, double p_i1648_2_, int bit) {
        this.entity = p_i1648_1_;
        this.speed = p_i1648_2_;
        func_75248_a(bit);
        this.entitymomiji = (ecru_EntityMomiji) p_i1648_1_;
    }

    public boolean func_75250_a() {
        Vec3 vec3;
        if (this.entitymomiji.isSitting()) {
        }
        if (this.entity.func_70654_ax() >= 100 || this.entity.func_70681_au().nextInt(20 + ((int) (((100 - mod_ecru_MapleTree.walkingFrequency) / 100.0d) * 100.0d))) != 0 || (vec3 = RandomPositionGenerator.func_75463_a(this.entity, 10, 7)) == null) {
            return false;
        }
        this.xPosition = vec3.field_72450_a;
        this.yPosition = vec3.field_72448_b;
        this.zPosition = vec3.field_72449_c;
        return true;
    }

    public boolean func_75253_b() {
        return !this.entity.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.speed * this.entitymomiji.getStateBonusSpeed());
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
    }
}
