package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityHumanPowerDrive extends TileEntity {
    private final Random random = new Random();
    private Entity[] en = new Entity[4];
    private double piInit = this.random.nextDouble() * 1.5707963267948966d;
    public double[] pi = {this.piInit, this.piInit + 1.5707963267948966d, this.piInit + 3.141592653589793d, this.piInit + 4.71238898038469d};
    private double[] mx = new double[4];
    private double[] mz = new double[4];
    public long[] dt_entityIdM = new long[4];
    public long[] dt_entityIdL = new long[4];

    public ecru_TileEntityHumanPowerDrive() {
        this.en[0] = null;
        this.en[1] = null;
        this.en[2] = null;
        this.en[3] = null;
    }

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_entityIdM[0] = nbttagcompound.func_74763_f("dt_entityIdM0");
        this.dt_entityIdM[1] = nbttagcompound.func_74763_f("dt_entityIdM1");
        this.dt_entityIdM[2] = nbttagcompound.func_74763_f("dt_entityIdM2");
        this.dt_entityIdM[3] = nbttagcompound.func_74763_f("dt_entityIdM3");
        this.dt_entityIdL[0] = nbttagcompound.func_74763_f("dt_entityIdL0");
        this.dt_entityIdL[1] = nbttagcompound.func_74763_f("dt_entityIdL1");
        this.dt_entityIdL[2] = nbttagcompound.func_74763_f("dt_entityIdL2");
        this.dt_entityIdL[3] = nbttagcompound.func_74763_f("dt_entityIdL3");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74772_a("dt_entityIdM0", this.dt_entityIdM[0]);
        nbttagcompound.func_74772_a("dt_entityIdM1", this.dt_entityIdM[1]);
        nbttagcompound.func_74772_a("dt_entityIdM2", this.dt_entityIdM[2]);
        nbttagcompound.func_74772_a("dt_entityIdM3", this.dt_entityIdM[3]);
        nbttagcompound.func_74772_a("dt_entityIdL0", this.dt_entityIdL[0]);
        nbttagcompound.func_74772_a("dt_entityIdL1", this.dt_entityIdL[1]);
        nbttagcompound.func_74772_a("dt_entityIdL2", this.dt_entityIdL[2]);
        nbttagcompound.func_74772_a("dt_entityIdL3", this.dt_entityIdL[3]);
    }

    public Packet func_145844_m() {
        mobCheck(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this);
        return super.func_145844_m();
    }

    public void func_145845_h() {
        if ((this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) & 8) == 8) {
            setCirclePos();
        }
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setEntity(Entity e, int i) {
        this.en[i] = e;
        this.dt_entityIdM[i] = this.en[i].func_110124_au().getMostSignificantBits();
        this.dt_entityIdL[i] = this.en[i].func_110124_au().getLeastSignificantBits();
    }

    public Entity getEntity(int i) {
        return this.en[i];
    }

    public boolean getEntityUid(int i) {
        if (this.dt_entityIdM[i] != 0 && this.dt_entityIdL[i] != 0) {
            return true;
        }
        return false;
    }

    public void deleteEntity(int i) {
        this.en[i] = null;
        this.dt_entityIdM[i] = 0;
        this.dt_entityIdL[i] = 0;
    }

    public int getEntityId(int i) {
        return this.en[i].func_145782_y();
    }

    private void setCirclePos() {
        for (int i = 0; i < 4; i++) {
            this.pi[i] = this.pi[i] + 0.05d;
            if (this.pi[i] > 6.28d) {
                this.pi[i] = 0.0d;
            }
            this.mx[i] = Math.sin(this.pi[i]) * 1.3d;
            this.mz[i] = Math.cos(this.pi[i]) * 1.3d;
        }
    }

    public int getEntityNum() {
        int u = 0;
        for (int i = 0; i < 4; i++) {
            if (this.en[i] != null) {
                u++;
            }
        }
        return u;
    }

    public void update(World world, int i, int j, int k) {
        for (int m = 0; m < 4; m++) {
            if (this.en[m] != null) {
                this.en[m].func_70080_a(i + 0.5d + this.mx[m], this.en[m].field_70163_u, k + 0.5d + this.mz[m], -((((float) this.pi[m]) * 57.32484f) + 90.0f), 0.1f);
                if (Math.abs(j - this.en[m].field_70163_u) > 1.5d) {
                    deleteEntity(m);
                }
                if (this.en[m] != null && this.en[m].field_70128_L) {
                    deleteEntity(m);
                }
            }
        }
        int meta = world.func_72805_g(i, j, k);
        if (this.en[0] != null || this.en[1] != null || this.en[2] != null || this.en[3] != null) {
            if (meta != (meta | 8)) {
                world.func_72921_c(i, j, k, meta | 8, 3);
                world.func_147459_d(i, j - 1, k, mod_ecru_MapleTree.blockHumanPowerDrive);
                world.func_147459_d(i, j + 1, k, mod_ecru_MapleTree.blockHumanPowerDrive);
                return;
            }
            return;
        }
        if (meta != (meta & 7)) {
            world.func_72921_c(i, j, k, meta & 7, 3);
            world.func_147459_d(i, j - 1, k, mod_ecru_MapleTree.blockHumanPowerDrive);
            world.func_147459_d(i, j + 1, k, mod_ecru_MapleTree.blockHumanPowerDrive);
        }
    }

    public int mobCheck(World par1World, int par2, int par3, int par4, ecru_TileEntityHumanPowerDrive tile) {
        int[] flg = new int[4];
        flg[0] = 0;
        flg[1] = 0;
        flg[2] = 0;
        flg[3] = 0;
        for (int m = 0; m < par1World.field_72996_f.size(); m++) {
            Entity ei = (Entity) par1World.field_72996_f.get(m);
            for (int q = 0; q < 4; q++) {
                if (ei.func_110124_au().getMostSignificantBits() == tile.dt_entityIdM[q] && ei.func_110124_au().getLeastSignificantBits() == tile.dt_entityIdL[q]) {
                    tile.setEntity(ei, q);
                    flg[q] = 1;
                }
            }
        }
        for (int m2 = 0; m2 < 4; m2++) {
            if (flg[m2] == 0) {
                tile.deleteEntity(m2);
            }
        }
        return 0;
    }
}
