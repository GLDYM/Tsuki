package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityPowerShaft extends TileEntity {
    private final Random random = new Random();
    private int power = 0;
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    private int SPEED = 3;
    private int dt_connectCount = 0;
    private int dt_torque = 0;
    private int gearPosX = 0;
    private int gearPosY = 0;
    private int gearPosZ = 0;

    public int getPower() {
        return this.power;
    }

    private void powerMove(World world) {
        this.nTime = world.func_72820_D();
        if (this.nTime != this.wTime) {
            this.wTime = world.func_72820_D();
            this.power += this.SPEED;
            if (this.power >= 360) {
                this.power -= 360;
            }
        }
    }

    public int getConnectCount() {
        return this.dt_connectCount;
    }

    public int getTorque() {
        return this.dt_torque;
    }

    public int getGearPosX() {
        return this.gearPosX;
    }

    public int getGearPosY() {
        return this.gearPosY;
    }

    public int getGearPosZ() {
        return this.gearPosZ;
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.dt_connectCount = nbttagcompound.func_74762_e("dt_connectCount");
        this.dt_torque = nbttagcompound.func_74762_e("dt_torque");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("dt_connectCount", this.dt_connectCount);
        nbttagcompound.func_74768_a("dt_torque", this.dt_torque);
    }

    public void func_145845_h() {
        if ((this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) & 8) == 8) {
            powerMove(this.field_145850_b);
        }
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        update(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void update(World world, int i, int j, int k) {
        checkConnectPower(world, i, j, k);
    }

    private void checkConnectPower(World world, int i, int j, int k) {
        int count_n = 0;
        int count_s = 0;
        int count_w = 0;
        int count_e = 0;
        int count_u = 0;
        int count_b = 0;
        int torque_n = 0;
        int torque_s = 0;
        int torque_w = 0;
        int torque_e = 0;
        int torque_u = 0;
        int torque_b = 0;
        int cmeta_n = 0;
        int cmeta_s = 0;
        int cmeta_w = 0;
        int cmeta_e = 0;
        int cmeta_u = 0;
        int cmeta_b = 0;
        int posX_n = 0;
        int posY_n = 0;
        int posZ_n = 0;
        int posX_s = 0;
        int posY_s = 0;
        int posZ_s = 0;
        int posX_w = 0;
        int posY_w = 0;
        int posZ_w = 0;
        int posX_e = 0;
        int posY_e = 0;
        int posZ_e = 0;
        int posX_u = 0;
        int posY_u = 0;
        int posZ_u = 0;
        int posX_b = 0;
        int posY_b = 0;
        int posZ_b = 0;
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 3) {
            case 0:
                if (world.func_147439_a(i, j + 1, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j + 1, k) & 7) != 4 && (world.func_72805_g(i, j + 1, k) & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j + 1, k);
                    count_u = tile.getConnectCount();
                    cmeta_u = world.func_72805_g(i, j + 1, k) & 8;
                    torque_u = tile.getTorque();
                    posX_u = tile.getGearPosX();
                    posY_u = tile.getGearPosY();
                    posZ_u = tile.getGearPosZ();
                } else if (world.func_147439_a(i, j + 1, k) == mod_ecru_MapleTree.blockPowerShaft && ((world.func_72805_g(i, j + 1, k) & 15) == 8 || (world.func_72805_g(i, j + 1, k) & 15) == 12)) {
                    ecru_TileEntityPowerShaft tile2 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j + 1, k);
                    count_u = tile2.getConnectCount();
                    cmeta_u = world.func_72805_g(i, j + 1, k) & 12;
                    torque_u = tile2.getTorque();
                    posX_u = tile2.getGearPosX();
                    posY_u = tile2.getGearPosY();
                    posZ_u = tile2.getGearPosZ();
                }
                if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j - 1, k) & 7) != 5 && (world.func_72805_g(i, j - 1, k) & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile3 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j - 1, k);
                    count_b = tile3.getConnectCount();
                    cmeta_b = world.func_72805_g(i, j - 1, k) & 8;
                    torque_b = tile3.getTorque();
                    posX_b = tile3.getGearPosX();
                    posY_b = tile3.getGearPosY();
                    posZ_b = tile3.getGearPosZ();
                } else if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPowerShaft && ((world.func_72805_g(i, j - 1, k) & 15) == 8 || (world.func_72805_g(i, j - 1, k) & 15) == 12)) {
                    ecru_TileEntityPowerShaft tile4 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j - 1, k);
                    count_b = tile4.getConnectCount();
                    cmeta_b = world.func_72805_g(i, j - 1, k) & 12;
                    torque_b = tile4.getTorque();
                    posX_b = tile4.getGearPosX();
                    posY_b = tile4.getGearPosY();
                    posZ_b = tile4.getGearPosZ();
                }
                if (count_u < 2 && count_b < 2) {
                    this.dt_connectCount = 0;
                    this.dt_torque = 0;
                    world.func_72921_c(i, j, k, meta & 3, 3);
                    this.gearPosX = 0;
                    this.gearPosY = -1;
                    this.gearPosZ = 0;
                    break;
                } else if (count_u <= this.dt_connectCount && count_b <= this.dt_connectCount) {
                    this.dt_connectCount = 0;
                    this.dt_torque = 0;
                    world.func_72921_c(i, j, k, meta & 3, 3);
                    this.gearPosX = 0;
                    this.gearPosY = -1;
                    this.gearPosZ = 0;
                    break;
                } else if (count_u >= count_b) {
                    if (count_u > this.dt_connectCount && count_u >= 2) {
                        this.dt_connectCount = count_u - 1;
                        this.dt_torque = torque_u;
                        world.func_72921_c(i, j, k, (meta & 3) | cmeta_u, 3);
                        this.gearPosX = posX_u;
                        this.gearPosY = posY_u;
                        this.gearPosZ = posZ_u;
                        break;
                    }
                } else if (count_b > this.dt_connectCount && count_b >= 2) {
                    this.dt_connectCount = count_b - 1;
                    this.dt_torque = torque_b;
                    world.func_72921_c(i, j, k, (meta & 3) | cmeta_b, 3);
                    this.gearPosX = posX_b;
                    this.gearPosY = posY_b;
                    this.gearPosZ = posZ_b;
                    break;
                }
                break;
            case 1:
                if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i + 1, j, k) & 7) != 1 && (world.func_72805_g(i + 1, j, k) & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile5 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i + 1, j, k);
                    count_e = tile5.getConnectCount();
                    cmeta_e = (world.func_72805_g(i + 1, j, k) & 8) | 4;
                    torque_e = tile5.getTorque();
                    posX_e = tile5.getGearPosX();
                    posY_e = tile5.getGearPosY();
                    posZ_e = tile5.getGearPosZ();
                } else if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockPowerShaft && ((world.func_72805_g(i + 1, j, k) & 15) == 9 || (world.func_72805_g(i + 1, j, k) & 15) == 13)) {
                    ecru_TileEntityPowerShaft tile6 = (ecru_TileEntityPowerShaft) world.func_147438_o(i + 1, j, k);
                    count_e = tile6.getConnectCount();
                    torque_e = tile6.getTorque();
                    cmeta_e = world.func_72805_g(i + 1, j, k) & 12;
                    posX_e = tile6.getGearPosX();
                    posY_e = tile6.getGearPosY();
                    posZ_e = tile6.getGearPosZ();
                }
                if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i - 1, j, k) & 7) != 3 && (world.func_72805_g(i - 1, j, k) & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile7 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i - 1, j, k);
                    count_w = tile7.getConnectCount();
                    cmeta_w = world.func_72805_g(i - 1, j, k) & 8;
                    torque_w = tile7.getTorque();
                    posX_w = tile7.getGearPosX();
                    posY_w = tile7.getGearPosY();
                    posZ_w = tile7.getGearPosZ();
                } else if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockPowerShaft && ((world.func_72805_g(i - 1, j, k) & 15) == 9 || (world.func_72805_g(i - 1, j, k) & 15) == 13)) {
                    ecru_TileEntityPowerShaft tile8 = (ecru_TileEntityPowerShaft) world.func_147438_o(i - 1, j, k);
                    count_w = tile8.getConnectCount();
                    cmeta_w = world.func_72805_g(i - 1, j, k) & 12;
                    torque_w = tile8.getTorque();
                    posX_w = tile8.getGearPosX();
                    posY_w = tile8.getGearPosY();
                    posZ_w = tile8.getGearPosZ();
                }
                if (count_e < 2 && count_w < 2) {
                    this.dt_connectCount = 0;
                    this.dt_torque = 0;
                    world.func_72921_c(i, j, k, meta & 3, 3);
                    this.gearPosX = 0;
                    this.gearPosY = -1;
                    this.gearPosZ = 0;
                    break;
                } else if (count_e <= this.dt_connectCount && count_w <= this.dt_connectCount) {
                    this.dt_connectCount = 0;
                    this.dt_torque = 0;
                    world.func_72921_c(i, j, k, meta & 3, 3);
                    this.gearPosX = 0;
                    this.gearPosY = -1;
                    this.gearPosZ = 0;
                    break;
                } else if (count_e >= count_w) {
                    if (count_e > this.dt_connectCount && count_e >= 2) {
                        this.dt_connectCount = count_e - 1;
                        this.dt_torque = torque_e;
                        world.func_72921_c(i, j, k, (meta & 3) | cmeta_e, 3);
                        this.gearPosX = posX_e;
                        this.gearPosY = posY_e;
                        this.gearPosZ = posZ_e;
                        break;
                    }
                } else if (count_w > this.dt_connectCount && count_w >= 2) {
                    this.dt_connectCount = count_w - 1;
                    this.dt_torque = torque_w;
                    world.func_72921_c(i, j, k, (meta & 3) | cmeta_w, 3);
                    this.gearPosX = posX_w;
                    this.gearPosY = posY_w;
                    this.gearPosZ = posZ_w;
                    break;
                }
                break;
            case 2:
                if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j, k + 1) & 7) != 2 && (world.func_72805_g(i, j, k + 1) & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile9 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j, k + 1);
                    count_s = tile9.getConnectCount();
                    torque_s = tile9.getTorque();
                    cmeta_s = world.func_72805_g(i, j, k + 1) & 8;
                    posX_s = tile9.getGearPosX();
                    posY_s = tile9.getGearPosY();
                    posZ_s = tile9.getGearPosZ();
                } else if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockPowerShaft && ((world.func_72805_g(i, j, k + 1) & 15) == 10 || (world.func_72805_g(i, j, k + 1) & 15) == 14)) {
                    ecru_TileEntityPowerShaft tile10 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j, k + 1);
                    count_s = tile10.getConnectCount();
                    torque_s = tile10.getTorque();
                    cmeta_s = world.func_72805_g(i, j, k + 1) & 12;
                    posX_s = tile10.getGearPosX();
                    posY_s = tile10.getGearPosY();
                    posZ_s = tile10.getGearPosZ();
                }
                if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j, k - 1) & 7) != 0 && (world.func_72805_g(i, j, k - 1) & 8) == 8) {
                    ecru_TileEntityPowerShaftGear tile11 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j, k - 1);
                    count_n = tile11.getConnectCount();
                    cmeta_n = (world.func_72805_g(i, j, k - 1) & 8) | 4;
                    torque_n = tile11.getTorque();
                    posX_n = tile11.getGearPosX();
                    posY_n = tile11.getGearPosY();
                    posZ_n = tile11.getGearPosZ();
                } else if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockPowerShaft && ((world.func_72805_g(i, j, k - 1) & 15) == 10 || (world.func_72805_g(i, j, k - 1) & 15) == 14)) {
                    ecru_TileEntityPowerShaft tile12 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j, k - 1);
                    count_n = tile12.getConnectCount();
                    cmeta_n = world.func_72805_g(i, j, k - 1) & 12;
                    torque_n = tile12.getTorque();
                    posX_n = tile12.getGearPosX();
                    posY_n = tile12.getGearPosY();
                    posZ_n = tile12.getGearPosZ();
                }
                if (count_n < 2 && count_s < 2) {
                    this.dt_connectCount = 0;
                    this.dt_torque = 0;
                    world.func_72921_c(i, j, k, meta & 3, 3);
                    this.gearPosX = 0;
                    this.gearPosY = -1;
                    this.gearPosZ = 0;
                    break;
                } else if (count_n <= this.dt_connectCount && count_s <= this.dt_connectCount) {
                    this.dt_connectCount = 0;
                    this.dt_torque = 0;
                    world.func_72921_c(i, j, k, meta & 3, 3);
                    this.gearPosX = 0;
                    this.gearPosY = -1;
                    this.gearPosZ = 0;
                    break;
                } else if (count_n >= count_s) {
                    if (count_n > this.dt_connectCount && count_n >= 2) {
                        this.dt_connectCount = count_n - 1;
                        this.dt_torque = torque_n;
                        world.func_72921_c(i, j, k, (meta & 3) | cmeta_n, 3);
                        this.gearPosX = posX_n;
                        this.gearPosY = posY_n;
                        this.gearPosZ = posZ_n;
                        break;
                    }
                } else if (count_s > this.dt_connectCount && count_s >= 2) {
                    this.dt_connectCount = count_s - 1;
                    this.dt_torque = torque_s;
                    world.func_72921_c(i, j, k, (meta & 3) | cmeta_s, 3);
                    this.gearPosX = posX_s;
                    this.gearPosY = posY_s;
                    this.gearPosZ = posZ_s;
                    break;
                }
                break;
        }
    }
}
