package ecru.MapleTree.tile;

import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketSLight;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntitySLight extends TileEntity {
    private final Random random = new Random();
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    private int power = this.random.nextInt(360);
    public int S_LiColor = -1;
    public int S_LiLength = -1;
    public int S_LiWidth = -1;
    public int S_LiTransparency = -1;
    private long TIMERCOUNT = 10;
    private long timer = this.TIMERCOUNT;
    public int S_LiColorRR = -1;
    public int S_LiColorGG = -1;
    public int S_LiColorBB = -1;
    private float lightAngle = this.random.nextFloat() * 6.2831855f;
    private int lightAngleType = this.random.nextInt(3);

    public int getLightAngleType() {
        return this.lightAngleType;
    }

    public float getLightAngle() {
        return this.lightAngle;
    }

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public Block getBlockId() {
        return this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
            float f = (float) (this.lightAngle + 0.1d);
            this.lightAngle = f;
            if (f > 6.283184d) {
                this.lightAngle = 0.0f;
            }
        }
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.S_LiColor = nbttagcompound.func_74762_e("S_LiColor");
        this.S_LiLength = nbttagcompound.func_74762_e("S_LiLength");
        this.S_LiWidth = nbttagcompound.func_74762_e("S_LiWidth");
        this.S_LiTransparency = nbttagcompound.func_74762_e("S_LiTransparency");
        this.S_LiColorRR = nbttagcompound.func_74762_e("S_LiColorRR");
        this.S_LiColorGG = nbttagcompound.func_74762_e("S_LiColorGG");
        this.S_LiColorBB = nbttagcompound.func_74762_e("S_LiColorBB");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("S_LiColor", this.S_LiColor);
        nbttagcompound.func_74768_a("S_LiLength", this.S_LiLength);
        nbttagcompound.func_74768_a("S_LiWidth", this.S_LiWidth);
        nbttagcompound.func_74768_a("S_LiTransparency", this.S_LiTransparency);
        nbttagcompound.func_74768_a("S_LiColorRR", this.S_LiColorRR);
        nbttagcompound.func_74768_a("S_LiColorGG", this.S_LiColorGG);
        nbttagcompound.func_74768_a("S_LiColorBB", this.S_LiColorBB);
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            powerMove(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else {
            if (this.field_145850_b.field_72995_K) {
                return;
            }
            update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
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

    private void sendItemInfo(ecru_TileEntitySLight tileEntity) {
        int x = tileEntity.field_145851_c;
        int y = tileEntity.field_145848_d;
        int z = tileEntity.field_145849_e;
        int color = tileEntity.S_LiColor;
        int length = tileEntity.S_LiLength;
        int width = tileEntity.S_LiWidth;
        int tra = tileEntity.S_LiTransparency;
        ecru_PacketHandler.network.sendToAll(new ecru_PacketSLight(x, y, z, color, length, width, tra));
    }
}
