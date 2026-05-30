package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketTeuchiUdon;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityTeuchiUdon extends TileEntity {
    private final Random random = new Random();
    private long TIMERCOUNT = 20;
    private long timer = this.TIMERCOUNT;
    private int process = 0;
    private int stepCounter = 0;
    private final int stepCounterMax = 3600;
    private boolean stepFlg = false;
    private char cutNum = 0;
    private int cutCounter = 0;
    private int cutCounterMax = 100;

    public Packet func_145844_m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        func_145841_b(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        func_145839_a(pkt.func_148857_g());
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.process = nbttagcompound.func_74762_e("process");
        this.stepCounter = nbttagcompound.func_74762_e("stepCounter");
        this.cutCounter = nbttagcompound.func_74762_e("cutCounter");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("process", this.process);
        nbttagcompound.func_74768_a("stepCounter", this.stepCounter);
        nbttagcompound.func_74768_a("cutCounter", this.cutCounter);
    }

    public void setStepFlg(boolean f) {
        this.stepFlg = f;
    }

    public boolean getStepFlg() {
        return this.stepFlg;
    }

    public void setProcess(int p) {
        this.process = p;
    }

    public int getProcess() {
        return this.process;
    }

    public void setStepCounter(int i) {
        this.stepCounter = i < 0 ? this.stepCounter + 1 : i;
    }

    public void setCutCounter(int i) {
        this.cutCounter = i < 0 ? this.cutCounter + 1 : i;
    }

    public int getCutCounter() {
        return this.cutCounter;
    }

    public int getCutCounterMax() {
        return this.cutCounterMax;
    }

    public int getStepCounter() {
        return this.stepCounter;
    }

    public int getStepCounterMax() {
        getClass();
        return 3600;
    }

    public char getCutNum() {
        return this.cutNum;
    }

    public void setCutNum(char c) {
        this.cutNum = c;
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        if (getStepFlg() && this.process == 0) {
            this.stepCounter++;
            int i2 = this.stepCounter;
            getClass();
            if (i2 >= 3600) {
                this.process = 1;
                sendItemInfo(this);
            }
            if (this.stepCounter % 5 == 0) {
                sendItemInfo(this);
            }
        }
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        setStepFlg(false);
        if (this.process == 1) {
            int muki = world.func_72805_g(i, j, k) & 8;
            double p = this.cutCounter / this.cutCounterMax;
            int m = (int) (p * 7.0d);
            int m2 = m > 7 ? 7 : m;
            int meta = muki | m2;
            this.cutNum = (char) meta;
            if (m2 >= 7) {
                this.process = 2;
                sendItemInfo(this);
            }
            world.func_72921_c(i, j, k, meta, 2);
        }
        func_70296_d();
    }

    public void sendItemInfo(ecru_TileEntityTeuchiUdon tileEntity) {
        if (!this.field_145850_b.field_72995_K) {
            int x = tileEntity.field_145851_c;
            int y = tileEntity.field_145848_d;
            int z = tileEntity.field_145849_e;
            ecru_PacketHandler.network.sendToAll(new ecru_PacketTeuchiUdon(x, y, z, this.cutNum, this.process, this.stepCounter, this.cutCounter));
        }
    }
}
