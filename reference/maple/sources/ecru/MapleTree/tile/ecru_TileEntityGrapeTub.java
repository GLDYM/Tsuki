package ecru.MapleTree.tile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntityBubbleFX;
import ecru.MapleTree.client.ecru_EntitySparkFX;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketGrapeTub;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityGrapeTub extends TileEntity {
    public UUID gst_uid;
    public long gst_uid_most;
    public long gst_uid_least;
    public long target_uid_most;
    public long target_uid_least;
    private int target_x;
    private int target_y;
    private int target_z;
    private int stomp_time;
    private int tubNum;
    private UUID target_uid = null;
    private int stomp_time_max = 0;
    private int ADD_STOMP_TIME = 6000;
    private int ADD_ONE_TIME = 5;
    public final int OUT_POS = 30000000;
    private HashMap<String, Integer> map = new HashMap<>();
    private long TIMERCOUNT = 60;
    private long timer = 0;
    private final Random random = new Random();
    private boolean walkingFlg = false;
    private int grapeNum = 0;
    private int grapeNumMax = 200;
    private int animeCounter = 0;
    private int RADIUS = 5;
    private int DEPTH = 0;
    private int SEARCH_DISTANCE_LV = 30;
    private int ARRAY_NUM_MAX = (((this.RADIUS * 2) + 1) * ((this.RADIUS * 2) + 1)) * (this.DEPTH + 1);
    private int[] chkX = new int[this.ARRAY_NUM_MAX + 1];
    private int[] chkY = new int[this.ARRAY_NUM_MAX + 1];
    private int[] chkZ = new int[this.ARRAY_NUM_MAX + 1];
    private int[] chk = new int[this.ARRAY_NUM_MAX + 1];
    private int chkCounter = 0;

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.gst_uid_most = nbttagcompound.func_74763_f("gst_uid_most");
        this.gst_uid_least = nbttagcompound.func_74763_f("gst_uid_least");
        this.target_x = nbttagcompound.func_74762_e("target_x");
        this.target_y = nbttagcompound.func_74762_e("target_y");
        this.target_z = nbttagcompound.func_74762_e("target_z");
        this.grapeNum = nbttagcompound.func_74762_e("grapeNum");
        this.tubNum = nbttagcompound.func_74762_e("tubNum");
        this.stomp_time = nbttagcompound.func_74762_e("stomp_time");
        this.stomp_time_max = nbttagcompound.func_74762_e("stomp_time_max");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74772_a("gst_uid_most", this.gst_uid_most);
        nbttagcompound.func_74772_a("gst_uid_least", this.gst_uid_least);
        nbttagcompound.func_74768_a("target_x", this.target_x);
        nbttagcompound.func_74768_a("target_y", this.target_y);
        nbttagcompound.func_74768_a("target_z", this.target_z);
        nbttagcompound.func_74768_a("grapeNum", this.grapeNum);
        nbttagcompound.func_74768_a("tubNum", this.tubNum);
        nbttagcompound.func_74768_a("stomp_time", this.stomp_time);
        nbttagcompound.func_74768_a("stomp_time_max", this.stomp_time_max);
    }

    public boolean setMap(String s) {
        if (!this.map.containsKey(s)) {
            this.map.put(s, 1);
            this.tubNum = this.map.size();
            sendItemInfo(this);
            return true;
        }
        return false;
    }

    public int getMapSize() {
        return this.map.size();
    }

    public boolean deleteMap(String s) {
        if (this.map.containsKey(s)) {
            this.map.remove(s);
            this.tubNum = this.map.size();
            sendItemInfo(this);
            return true;
        }
        return false;
    }

    public UUID getUid() {
        return this.gst_uid;
    }

    public boolean getActive() {
        return this.target_uid != null;
    }

    public int getPosX() {
        return this.target_x;
    }

    public int getPosY() {
        return this.target_y;
    }

    public int getPosZ() {
        return this.target_z;
    }

    public void setPosX(int n) {
        this.target_x = n;
    }

    public void setPosY(int n) {
        this.target_y = n;
    }

    public void setPosZ(int n) {
        this.target_z = n;
    }

    public boolean getWalkingFlg() {
        return this.walkingFlg;
    }

    public void setWalkingFlg(boolean flg) {
        this.walkingFlg = flg;
    }

    public int addGrape(int num) {
        if (this.grapeNum + num <= this.grapeNumMax * (this.tubNum + 1)) {
            this.grapeNum += num;
            this.stomp_time_max = (this.grapeNum * this.ADD_ONE_TIME) + this.ADD_STOMP_TIME;
        } else {
            this.grapeNum = this.grapeNumMax * (this.tubNum + 1);
            this.stomp_time_max = (this.grapeNum * this.ADD_ONE_TIME) + this.ADD_STOMP_TIME;
        }
        sendItemInfo(this);
        return this.grapeNum;
    }

    public int getGrapeNum() {
        return this.grapeNum;
    }

    public void setGrapeNum(int n) {
        if (n < 0) {
            this.grapeNum = 0;
        } else if (n > this.grapeNumMax * (this.tubNum + 1)) {
            this.grapeNum = this.grapeNumMax;
        } else {
            this.grapeNum = n;
        }
        this.stomp_time_max = (this.grapeNum * this.ADD_ONE_TIME) + this.ADD_STOMP_TIME;
        sendItemInfo(this);
    }

    public int getGrapeNumMax() {
        return this.grapeNumMax;
    }

    public void subGrapeNum(int n) {
        if (this.grapeNum - n >= 0) {
            this.grapeNum -= n;
        } else {
            this.grapeNum = 0;
        }
        this.stomp_time_max = (this.grapeNum * this.ADD_ONE_TIME) + this.ADD_STOMP_TIME;
        sendItemInfo(this);
    }

    public int getAnimeCounter() {
        return this.animeCounter;
    }

    public int getTubNum() {
        return this.tubNum;
    }

    public void setTubNum(int n) {
        this.tubNum = n;
    }

    public int getStompTime() {
        return this.stomp_time;
    }

    public int getStompTimeMax() {
        return this.stomp_time_max;
    }

    public void setStompTime(int t) {
        if (t <= this.stomp_time_max) {
            this.stomp_time = t;
        } else {
            this.stomp_time = this.stomp_time_max;
        }
    }

    public void addStompTime(int t) {
        if (this.stomp_time + t <= this.stomp_time_max) {
            this.stomp_time += t;
        } else {
            this.stomp_time = this.stomp_time_max;
        }
    }

    public void subStompTime(int t) {
        if (this.stomp_time - t < 0) {
            this.stomp_time = 0;
        } else {
            this.stomp_time -= t;
        }
    }

    public Packet func_145844_m() {
        sendItemInfo(this);
        return null;
    }

    public void sendItemInfo(ecru_TileEntityGrapeTub tileEntity) {
        if (!this.field_145850_b.field_72995_K) {
            int x = tileEntity.field_145851_c;
            int y = tileEntity.field_145848_d;
            int z = tileEntity.field_145849_e;
            if (!this.field_145850_b.field_72995_K) {
                ecru_PacketHandler.network.sendToAll(new ecru_PacketGrapeTub(x, y, z, this.target_x, this.target_y, this.target_z, this.grapeNum, this.tubNum, this.stomp_time, this.walkingFlg));
            }
        }
    }

    public void func_145845_h() {
        if (this.animeCounter < 31) {
            this.animeCounter++;
        } else {
            this.animeCounter = 0;
        }
        if (this.field_145850_b.field_72995_K) {
            particles(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else {
            update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public void update(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        if (getWalkingFlg() && getGrapeNum() > 0 && (world.func_72805_g(i, j, k) & 1) == 1 && this.stomp_time < this.stomp_time_max) {
            this.stomp_time++;
        }
        if (this.stomp_time > this.stomp_time_max) {
            this.stomp_time = this.stomp_time_max;
        }
        if (this.timer > 0) {
            this.timer--;
            return;
        }
        this.timer = this.TIMERCOUNT;
        setWalkingFlg(false);
        if ((world.func_72805_g(i, j, k) & 1) == 0) {
            int ret = blockCheck(world, i, j, k);
            if (ret != 0) {
                ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) world.func_147438_o(this.target_x, this.target_y, this.target_z);
                if (tile != null) {
                    String key = getStr(i) + getStr(j) + getStr(k);
                    tile.deleteMap(key);
                }
                this.target_uid = null;
                this.target_uid_least = 0L;
                this.target_uid_most = 0L;
                this.target_x = 30000000;
                this.target_y = 30000000;
                this.target_z = 30000000;
                world.func_72921_c(i, j, k, meta & 7, 3);
            } else {
                world.func_72921_c(i, j, k, meta | 8, 3);
            }
            sendItemInfo(this);
        } else {
            this.tubNum = blockCount(world, i, j, k) - 1;
            this.target_x = i;
            this.target_y = j;
            this.target_z = k;
            this.stomp_time_max = (getGrapeNum() * 5) + this.ADD_STOMP_TIME;
            if (getGrapeNum() <= 0) {
                setStompTime(0);
            }
            int grapeNumMax = getGrapeNumMax() * (getTubNum() + 1);
            if (getGrapeNum() > grapeNumMax) {
                setGrapeNum(grapeNumMax);
            }
            sendItemInfo(this);
        }
        for (int q = 0; q < this.ARRAY_NUM_MAX + 1; q++) {
            this.chkX[q] = 0;
            this.chkY[q] = 0;
            this.chkZ[q] = 0;
            this.chk[q] = -1;
        }
        this.chkCounter = 0;
        func_70296_d();
    }

    @SideOnly(Side.CLIENT)
    private void particles(World world, int i, int j, int k) {
        ecru_TileEntityGrapeTub tile2 = (ecru_TileEntityGrapeTub) world.func_147438_o(getPosX(), getPosY(), getPosZ());
        if (tile2 != null && this.random.nextInt(60) == 0 && tile2.getWalkingFlg() && tile2.getGrapeNum() > 0) {
            double pe = tile2.getGrapeNum() / (tile2.getGrapeNumMax() * (tile2.tubNum + 1));
            double yy = ((j + 0.25f) + (pe * 0.5d)) - 0.03125d;
            ecru_EntityBubbleFX entityFX = new ecru_EntityBubbleFX(world, i + this.random.nextDouble(), yy, k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(8));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
            ecru_EntityBubbleFX entityFX2 = new ecru_EntityBubbleFX(world, i + this.random.nextDouble(), yy, k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX2.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(8));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX2);
        }
        if (tile2 != null && this.random.nextInt(100) == 0 && tile2.getStompTime() >= tile2.getStompTimeMax()) {
            double pe2 = tile2.getGrapeNum() / (tile2.getGrapeNumMax() * (tile2.tubNum + 1));
            ecru_EntitySparkFX entityFX3 = new ecru_EntitySparkFX(world, i + this.random.nextDouble(), ((j + 0.25f) + (pe2 * 0.5d)) - 0.03125d, k + this.random.nextDouble(), 0.0d, 0.0d, 0.0d);
            entityFX3.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(6));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX3);
        }
    }

    private int blockCheck(World world, int i, int j, int k) {
        this.chkX[0] = i;
        this.chkY[0] = j;
        this.chkZ[0] = k;
        this.chk[0] = 0;
        this.chkCounter = 1;
        int cou = 0;
        int[] addX = {-1, 0, 1, 0};
        int[] addY = {0, 0, 0, 0};
        int[] addZ = {0, 1, 0, -1};
        for (int m = 0; m < this.SEARCH_DISTANCE_LV; m++) {
            while (this.chk[cou] == m) {
                int i2 = this.chkX[cou];
                int j2 = this.chkY[cou];
                int k2 = this.chkZ[cou];
                for (int n = 0; n < addX.length; n++) {
                    int xx = i2 + addX[n];
                    int yy = j2 + addY[n];
                    int zz = k2 + addZ[n];
                    boolean ret = world.func_147439_a(xx, yy, zz) == mod_ecru_MapleTree.blockGrapeStompTub;
                    if (ret && xx >= i - this.RADIUS && xx <= i + this.RADIUS && yy == j && zz >= k - this.RADIUS && zz <= k + this.RADIUS && !check(xx, yy, zz)) {
                        if (this.chkCounter > this.ARRAY_NUM_MAX) {
                            return 2;
                        }
                        this.chkX[this.chkCounter] = xx;
                        this.chkY[this.chkCounter] = yy;
                        this.chkZ[this.chkCounter] = zz;
                        this.chk[this.chkCounter] = m + 1;
                        this.chkCounter++;
                        if ((world.func_72805_g(xx, yy, zz) & 1) == 1) {
                            ecru_TileEntityGrapeTub tile = (ecru_TileEntityGrapeTub) world.func_147438_o(xx, yy, zz);
                            if (tile != null && this.target_uid_least != tile.gst_uid_least && this.target_uid_most != tile.gst_uid_most) {
                                this.target_uid_least = tile.gst_uid_least;
                                this.target_uid_most = tile.gst_uid_most;
                                this.target_x = xx;
                                this.target_y = yy;
                                this.target_z = zz;
                                String key = getStr(this.field_145851_c) + getStr(this.field_145848_d) + getStr(this.field_145849_e);
                                tile.setMap(key);
                                this.tubNum = tile.getTubNum();
                                return 0;
                            }
                            return 0;
                        }
                    }
                }
                cou++;
                if (cou > this.ARRAY_NUM_MAX) {
                    return 2;
                }
            }
        }
        return 1;
    }

    public int blockCount(World world, int i, int j, int k) {
        ecru_TileEntityGrapeTub tile;
        this.chkX[0] = i;
        this.chkY[0] = j;
        this.chkZ[0] = k;
        this.chk[0] = 0;
        this.chkCounter = 1;
        int cou = 0;
        int[] addX = {-1, 0, 1, 0};
        int[] addY = {0, 0, 0, 0};
        int[] addZ = {0, 1, 0, -1};
        for (int m = 0; m < this.SEARCH_DISTANCE_LV; m++) {
            while (this.chk[cou] == m) {
                int i2 = this.chkX[cou];
                int j2 = this.chkY[cou];
                int k2 = this.chkZ[cou];
                for (int n = 0; n < addX.length; n++) {
                    int xx = i2 + addX[n];
                    int yy = j2 + addY[n];
                    int zz = k2 + addZ[n];
                    boolean ret = world.func_147439_a(xx, yy, zz) == mod_ecru_MapleTree.blockGrapeStompTub && (world.func_72805_g(xx, yy, zz) & 1) == 0;
                    if (ret && (tile = (ecru_TileEntityGrapeTub) world.func_147438_o(xx, yy, zz)) != null && xx >= i - this.RADIUS && xx <= i + this.RADIUS && yy == j && zz >= k - this.RADIUS && zz <= k + this.RADIUS && (((tile.getPosX() == i && tile.getPosY() == j && tile.getPosZ() == k) || (tile.getPosX() == 30000000 && tile.getPosY() == 30000000 && tile.getPosZ() == 30000000)) && !check(xx, yy, zz))) {
                        if (this.chkCounter > this.ARRAY_NUM_MAX) {
                            return this.chkCounter;
                        }
                        this.chkX[this.chkCounter] = xx;
                        this.chkY[this.chkCounter] = yy;
                        this.chkZ[this.chkCounter] = zz;
                        this.chk[this.chkCounter] = m + 1;
                        this.chkCounter++;
                    }
                }
                cou++;
                if (cou > this.ARRAY_NUM_MAX) {
                    return this.chkCounter;
                }
            }
        }
        return this.chkCounter;
    }

    private boolean check(int i, int j, int k) {
        for (int m = 0; m < this.chkCounter; m++) {
            if (this.chkX[m] == i && this.chkY[m] == j && this.chkZ[m] == k) {
                return true;
            }
        }
        return false;
    }

    private String getStr(int i) {
        String s = String.valueOf(i);
        if (i < 0) {
            s = s.substring(1, s.length());
        }
        String s2 = "000" + s;
        String r = s2.substring(s2.length() - 3, s2.length());
        if (i >= 0) {
            return "0" + r;
        }
        return "1" + r;
    }
}
