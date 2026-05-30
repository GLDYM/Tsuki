package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketLighthouse;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityLighthouseIllumination extends TileEntity {
    private final Random random = new Random();
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    private int power = this.random.nextInt(360);
    public int LiColor = -1;
    public int LiLength = -1;
    public int LiWidth = -1;
    public int LiTransparency = -1;
    private long TIMERCOUNT = 20;
    private long timer = this.TIMERCOUNT;
    public int LiColorRR = -1;
    public int LiColorGG = -1;
    public int LiColorBB = -1;

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int getPower() {
        return this.power;
    }

    private void powerMove(World world, int i, int j, int k) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            int i2 = this.power + 1;
            this.power = i2;
            if (i2 >= 360) {
                this.power = 0;
            }
        }
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.LiColor = nbttagcompound.func_74762_e("LiColor");
        this.LiLength = nbttagcompound.func_74762_e("LiLength");
        this.LiWidth = nbttagcompound.func_74762_e("LiWidth");
        this.LiTransparency = nbttagcompound.func_74762_e("LiTransparency");
        this.LiColorRR = nbttagcompound.func_74762_e("LiColorRR");
        this.LiColorGG = nbttagcompound.func_74762_e("LiColorGG");
        this.LiColorBB = nbttagcompound.func_74762_e("LiColorBB");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("LiColor", this.LiColor);
        nbttagcompound.func_74768_a("LiLength", this.LiLength);
        nbttagcompound.func_74768_a("LiWidth", this.LiWidth);
        nbttagcompound.func_74768_a("LiTransparency", this.LiTransparency);
        nbttagcompound.func_74768_a("LiColorRR", this.LiColorRR);
        nbttagcompound.func_74768_a("LiColorGG", this.LiColorGG);
        nbttagcompound.func_74768_a("LiColorBB", this.LiColorBB);
    }

    public void func_145845_h() {
        powerMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        if (this.timer > 0) {
            this.timer--;
        } else {
            this.timer = this.TIMERCOUNT;
            func_70296_d();
        }
    }

    public boolean shouldRenderInPass(int pass) {
        return true;
    }

    private void sendItemInfo(ecru_TileEntityLighthouseIllumination tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        int color = tileEntity.LiColor;
        int length = tileEntity.LiLength;
        int width = tileEntity.LiWidth;
        int tra = tileEntity.LiTransparency;
        ecru_PacketHandler.network.sendToAll(new ecru_PacketLighthouse(x, y, z, color, length, width, tra));
    }
}
