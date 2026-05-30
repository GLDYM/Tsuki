package ecru.MapleTree.tile;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ecru_TileEntityMapleWoodSyrup extends TileEntity {
    private long tim;
    private long timMax;
    private long stack = 0;
    private long TIMERCOUNT = 10;
    private long timer = this.TIMERCOUNT;
    private final Random random = new Random();

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.tim = nbttagcompound.func_74763_f("tim");
        this.timMax = nbttagcompound.func_74763_f("timMax");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74772_a("tim", this.tim);
        nbttagcompound.func_74772_a("timMax", this.timMax);
    }

    public long getTime() {
        return this.tim;
    }

    public long getTimeMax() {
        return this.timMax;
    }

    public void setTime(long byte0) {
        this.tim = byte0;
    }

    public void setTimeMax(long byte0) {
        this.timMax = byte0;
    }

    public long SubtractionTime(long data) {
        this.tim -= data;
        return this.tim;
    }

    public long addTime(long data) {
        this.tim += data;
        return this.tim;
    }

    public boolean chargeTime(long data) {
        if (this.timMax == 0) {
            this.timMax = 4000 - this.random.nextInt(1500);
        }
        this.tim += data;
        if (this.tim > this.timMax) {
            return true;
        }
        return false;
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if ((meta & 3) == 3) {
            ecru_TileEntityMapleWoodSyrup tileentity = (ecru_TileEntityMapleWoodSyrup) this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (tileentity.chargeTime(tileentity.getTimeMax() > 10000 ? 2L : 1L)) {
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 3);
            }
        }
    }
}
