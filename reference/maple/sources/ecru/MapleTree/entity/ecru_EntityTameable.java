package ecru.MapleTree.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.entity.ai.ecru_EntityAISit;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.world.World;

public abstract class ecru_EntityTameable extends EntityAnimal implements IEntityOwnable {
    protected ecru_EntityAISit aiSit;
    private static final String __OBFID = "CL_00001561";

    public ecru_EntityTameable(World p_i1604_1_) {
        super(p_i1604_1_);
        this.aiSit = new ecru_EntityAISit(this);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (byte) 0);
        this.field_70180_af.func_75682_a(17, "");
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        if (func_152113_b() == null) {
            p_70014_1_.func_74778_a("OwnerUUID", "");
        } else {
            p_70014_1_.func_74778_a("OwnerUUID", func_152113_b());
        }
        p_70014_1_.func_74757_a("Sitting", isSitting());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        String s;
        super.func_70037_a(p_70037_1_);
        if (p_70037_1_.func_150297_b("OwnerUUID", 8)) {
            s = p_70037_1_.func_74779_i("OwnerUUID");
        } else {
            String s1 = p_70037_1_.func_74779_i("Owner");
            s = PreYggdrasilConverter.func_152719_a(s1);
        }
        if (s.length() > 0) {
            func_152115_b(s);
            setTamed(true);
        }
        this.aiSit.setSitting(p_70037_1_.func_74767_n("Sitting"));
        setSitting(p_70037_1_.func_74767_n("Sitting"));
    }

    protected void playTameEffect(boolean p_70908_1_) {
        String s = "heart";
        if (!p_70908_1_) {
            s = "smoke";
        }
        for (int i = 0; i < 7; i++) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02d;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02d;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02d;
            this.field_70170_p.func_72869_a(s, (this.field_70165_t + ((this.field_70146_Z.nextFloat() * this.field_70130_N) * 2.0f)) - this.field_70130_N, this.field_70163_u + 0.5d + (this.field_70146_Z.nextFloat() * this.field_70131_O), (this.field_70161_v + ((this.field_70146_Z.nextFloat() * this.field_70130_N) * 2.0f)) - this.field_70130_N, d0, d1, d2);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_) {
        if (p_70103_1_ == 7) {
            playTameEffect(true);
        } else if (p_70103_1_ == 6) {
            playTameEffect(false);
        } else {
            super.func_70103_a(p_70103_1_);
        }
    }

    public boolean isTamed() {
        return (this.field_70180_af.func_75683_a(16) & 4) != 0;
    }

    public void setTamed(boolean p_70903_1_) {
        byte b0 = this.field_70180_af.func_75683_a(16);
        if (p_70903_1_) {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 & (-5))));
        }
    }

    public boolean isSitting() {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    public void setSitting(boolean p_70904_1_) {
        byte b0 = this.field_70180_af.func_75683_a(16);
        if (p_70904_1_) {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 & (-2))));
        }
    }

    public String func_152113_b() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void func_152115_b(String p_152115_1_) {
        this.field_70180_af.func_75692_b(17, p_152115_1_);
    }

    public EntityLivingBase func_70902_q() {
        try {
            UUID uuid = UUID.fromString(func_152113_b());
            if (uuid == null) {
                return null;
            }
            return this.field_70170_p.func_152378_a(uuid);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean func_152114_e(EntityLivingBase p_152114_1_) {
        return p_152114_1_ == func_70902_q();
    }

    public ecru_EntityAISit func_70907_r() {
        return this.aiSit;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        return true;
    }

    public Team func_96124_cp() {
        EntityLivingBase entitylivingbase;
        if (isTamed() && (entitylivingbase = func_70902_q()) != null) {
            return entitylivingbase.func_96124_cp();
        }
        return super.func_96124_cp();
    }

    public boolean func_142014_c(EntityLivingBase p_142014_1_) {
        if (isTamed()) {
            EntityLivingBase entitylivingbase1 = func_70902_q();
            if (p_142014_1_ == entitylivingbase1) {
                return true;
            }
            if (entitylivingbase1 != null) {
                return entitylivingbase1.func_142014_c(p_142014_1_);
            }
        }
        return super.func_142014_c(p_142014_1_);
    }
}
