package ecru.MapleTree.tile;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntitySunFlower extends TileEntity {
    private final Random random = new Random();
    public int dt_SunPower = 0;
    private long TIMERCOUNT = 70;
    private long timer = this.TIMERCOUNT;
    private int META = 3;
    private int f_meta = 3;
    private int last_power = 0;
    boolean light = false;
    protected long tim = 0;
    protected long tim_last = 0;
    private float[] pistonLeaf = {0.0f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f, this.random.nextFloat() * 6.2831855f};
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    public int power1 = this.random.nextInt(360);

    public float getPLeaf(int i) {
        return this.pistonLeaf[i];
    }

    private void leafMove(World world, int i, int j, int k) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            for (int m = 1; m <= 8; m++) {
                float f = (float) (r0[r1] + 0.1d);
                this.pistonLeaf[m] = f;
                if (f > 6.283184d) {
                    this.pistonLeaf[m] = 0.0f;
                }
            }
        }
    }

    public void setPower(int po) {
        this.dt_SunPower = po;
    }

    public int getPower() {
        return this.dt_SunPower;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_SunPower = nbttagcompound.func_74762_e("dt_SunPower");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("dt_SunPower", this.dt_SunPower);
    }

    public void func_145845_h() {
        leafMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        if (world.func_72805_g(i, j, k) < 3) {
            return;
        }
        long ti = world.func_72820_D() % 24000;
        if (world.func_72957_l(i, j + 1, k) >= 15) {
            this.light = true;
        } else {
            this.light = false;
        }
        if (ti >= 0 && ti <= 12000 && !world.func_72896_J() && this.light) {
            if (this.dt_SunPower < 100000) {
                this.dt_SunPower++;
            }
            if (this.f_meta < 7 && this.last_power != this.dt_SunPower / 400) {
                this.f_meta = this.META + (this.dt_SunPower / 400);
                this.last_power = this.dt_SunPower / 400;
                if (this.f_meta > 7) {
                    this.f_meta = 7;
                }
                Block iiid = world.func_147439_a(i, j, k);
                ecru_TileEntitySunFlower tile = (ecru_TileEntitySunFlower) world.func_147438_o(i, j, k);
                world.func_147465_d(i, j, k, iiid, this.f_meta, 3);
                tile.func_145829_t();
                world.func_147455_a(i, j, k, tile);
            }
        }
        func_70296_d();
    }
}
