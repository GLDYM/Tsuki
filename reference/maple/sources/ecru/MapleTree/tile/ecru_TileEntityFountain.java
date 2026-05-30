package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketFountain;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class ecru_TileEntityFountain extends TileEntity {
    private final Random random = new Random();
    private int dt_direction;
    private int dt_angle;
    private int dt_power;

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_direction = nbttagcompound.func_74762_e("dt_direction");
        this.dt_angle = nbttagcompound.func_74762_e("dt_angle");
        this.dt_power = nbttagcompound.func_74762_e("dt_power");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("dt_direction", this.dt_direction);
        nbttagcompound.func_74768_a("dt_angle", this.dt_angle);
        nbttagcompound.func_74768_a("dt_power", this.dt_power);
    }

    public int getPower() {
        return this.dt_power;
    }

    public void setPower(int sData) {
        this.dt_power = sData;
        sendItemInfo(this);
    }

    public int getAngle() {
        return this.dt_angle;
    }

    public void setAngle(int sData) {
        this.dt_angle = sData;
        sendItemInfo(this);
    }

    public int getDirection() {
        return this.dt_direction;
    }

    public void setDirection(int sData) {
        this.dt_direction = sData;
        sendItemInfo(this);
    }

    private void sendItemInfo(ecru_TileEntityFountain tileEntity) {
        if (!this.field_145850_b.field_72995_K) {
            int x = tileEntity.field_145851_c;
            int y = tileEntity.field_145848_d;
            int z = tileEntity.field_145849_e;
            ecru_PacketHandler.network.sendToAll(new ecru_PacketFountain(x, y, z, this.dt_direction, this.dt_angle, this.dt_power));
        }
    }
}
