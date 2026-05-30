package ecru.MapleTree.tile;

import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_TileEntityPowerShaftGear extends TileEntity {
    private final Random random = new Random();
    private int power = this.random.nextInt(360);
    private int dt_connectCount = 0;
    private int dt_torque = 0;
    private int gearPosX = 0;
    private int gearPosY = 0;
    private int gearPosZ = 0;
    private HashMap<String, Integer> map = new HashMap<>();
    private int CONNECT_ENGINE = 70;
    private int CONNECT_WHEEL = 50;
    private int CONNECT_HUMAN4 = 30;
    private int CONNECT_HUMAN3 = 24;
    private int CONNECT_HUMAN2 = 18;
    private int CONNECT_HUMAN1 = 15;
    private int TORQUE_ENGINE = 1;
    private int TORQUE_WHEEL = 2;
    private int TORQUE_HUMAN4 = 3;
    private int TORQUE_HUMAN3 = 4;
    private int TORQUE_HUMAN2 = 5;
    private int TORQUE_HUMAN1 = 6;
    private double wTime = 0.0d;
    private double nTime = 0.0d;
    private int SPEED = 3;

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

    public int func_145832_p() {
        return this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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

    public boolean setMap(String s) {
        if (!this.map.containsKey(s)) {
            this.map.put(s, 1);
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
            return true;
        }
        return false;
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
        if (!checkEnginePower(world, i, j, k) && !checkWheelPower(world, i, j, k) && !checkHumanPower(world, i, j, k) && !checkSsWindmill(world, i, j, k)) {
            checkConnectPower(world, i, j, k);
        }
        int meta = world.func_72805_g(i, j, k);
        if (this.dt_connectCount >= 1) {
            world.func_72921_c(i, j, k, meta | 8, 3);
        } else {
            world.func_72921_c(i, j, k, meta & 7, 3);
        }
    }

    private void checkConnectPower(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
                if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j, k + 1) & 15) != 10) {
                    ecru_TileEntityPowerShaftGear tile = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j, k + 1);
                    if (tile.getConnectCount() > this.dt_connectCount && tile.getConnectCount() >= 2) {
                        this.dt_connectCount = tile.getConnectCount() - 1;
                        this.dt_torque = tile.getTorque();
                        int cmeta = world.func_72805_g(i, j, k + 1) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta, 3);
                        this.gearPosX = tile.getGearPosX();
                        this.gearPosY = tile.getGearPosY();
                        this.gearPosZ = tile.getGearPosZ();
                        break;
                    }
                }
                if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockPowerShaft && (world.func_72805_g(i, j, k + 1) & 15) == 10) {
                    ecru_TileEntityPowerShaft tile2 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j, k + 1);
                    if (tile2.getConnectCount() > this.dt_connectCount && tile2.getConnectCount() >= 2) {
                        this.dt_connectCount = tile2.getConnectCount() - 1;
                        this.dt_torque = tile2.getTorque();
                        int cmeta2 = world.func_72805_g(i, j, k + 1) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta2, 3);
                        this.gearPosX = tile2.getGearPosX();
                        this.gearPosY = tile2.getGearPosY();
                        this.gearPosZ = tile2.getGearPosZ();
                        break;
                    }
                }
                break;
            case 1:
                if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i - 1, j, k) & 15) != 11) {
                    ecru_TileEntityPowerShaftGear tile3 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i - 1, j, k);
                    if (tile3.getConnectCount() > this.dt_connectCount && tile3.getConnectCount() >= 2) {
                        this.dt_connectCount = tile3.getConnectCount() - 1;
                        this.dt_torque = tile3.getTorque();
                        int cmeta3 = world.func_72805_g(i - 1, j, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta3, 3);
                        this.gearPosX = tile3.getGearPosX();
                        this.gearPosY = tile3.getGearPosY();
                        this.gearPosZ = tile3.getGearPosZ();
                        break;
                    }
                }
                if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockPowerShaft && (world.func_72805_g(i - 1, j, k) & 15) == 9) {
                    ecru_TileEntityPowerShaft tile4 = (ecru_TileEntityPowerShaft) world.func_147438_o(i - 1, j, k);
                    if (tile4.getConnectCount() > this.dt_connectCount && tile4.getConnectCount() >= 2) {
                        this.dt_connectCount = tile4.getConnectCount() - 1;
                        this.dt_torque = tile4.getTorque();
                        int cmeta4 = world.func_72805_g(i - 1, j, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta4, 3);
                        this.gearPosX = tile4.getGearPosX();
                        this.gearPosY = tile4.getGearPosY();
                        this.gearPosZ = tile4.getGearPosZ();
                        break;
                    }
                }
                break;
            case 2:
                if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j, k - 1) & 15) != 8) {
                    ecru_TileEntityPowerShaftGear tile5 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j, k - 1);
                    if (tile5.getConnectCount() > this.dt_connectCount && tile5.getConnectCount() >= 2) {
                        this.dt_connectCount = tile5.getConnectCount() - 1;
                        this.dt_torque = tile5.getTorque();
                        int cmeta5 = world.func_72805_g(i, j, k - 1) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta5, 3);
                        this.gearPosX = tile5.getGearPosX();
                        this.gearPosY = tile5.getGearPosY();
                        this.gearPosZ = tile5.getGearPosZ();
                        break;
                    }
                }
                if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockPowerShaft) {
                    if ((world.func_72805_g(i, j, k - 1) & 15) == 10 || (world.func_72805_g(i, j, k - 1) & 15) == 14) {
                        ecru_TileEntityPowerShaft tile6 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j, k - 1);
                        if (tile6.getConnectCount() > this.dt_connectCount && tile6.getConnectCount() >= 2) {
                            this.dt_connectCount = tile6.getConnectCount() - 1;
                            this.dt_torque = tile6.getTorque();
                            int cmeta6 = world.func_72805_g(i, j, k - 1) & 8;
                            world.func_72921_c(i, j, k, meta | cmeta6, 3);
                            this.gearPosX = tile6.getGearPosX();
                            this.gearPosY = tile6.getGearPosY();
                            this.gearPosZ = tile6.getGearPosZ();
                            break;
                        }
                    }
                }
                break;
            case 3:
                if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i + 1, j, k) & 15) != 9) {
                    ecru_TileEntityPowerShaftGear tile7 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i + 1, j, k);
                    if (tile7.getConnectCount() > this.dt_connectCount && tile7.getConnectCount() >= 2) {
                        this.dt_connectCount = tile7.getConnectCount() - 1;
                        this.dt_torque = tile7.getTorque();
                        int cmeta7 = world.func_72805_g(i + 1, j, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta7, 3);
                        this.gearPosX = tile7.getGearPosX();
                        this.gearPosY = tile7.getGearPosY();
                        this.gearPosZ = tile7.getGearPosZ();
                        break;
                    }
                }
                if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockPowerShaft) {
                    if ((world.func_72805_g(i + 1, j, k) & 15) == 9 || (world.func_72805_g(i + 1, j, k) & 15) == 13) {
                        ecru_TileEntityPowerShaft tile8 = (ecru_TileEntityPowerShaft) world.func_147438_o(i + 1, j, k);
                        if (tile8.getConnectCount() > this.dt_connectCount && tile8.getConnectCount() >= 2) {
                            this.dt_connectCount = tile8.getConnectCount() - 1;
                            this.dt_torque = tile8.getTorque();
                            int cmeta8 = world.func_72805_g(i + 1, j, k) & 8;
                            world.func_72921_c(i, j, k, meta | cmeta8, 3);
                            this.gearPosX = tile8.getGearPosX();
                            this.gearPosY = tile8.getGearPosY();
                            this.gearPosZ = tile8.getGearPosZ();
                            break;
                        }
                    }
                }
                break;
            case 4:
                if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j - 1, k) & 15) != 13) {
                    ecru_TileEntityPowerShaftGear tile9 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j - 1, k);
                    if (tile9.getConnectCount() > this.dt_connectCount && tile9.getConnectCount() >= 2) {
                        this.dt_connectCount = tile9.getConnectCount() - 1;
                        this.dt_torque = tile9.getTorque();
                        int cmeta9 = world.func_72805_g(i, j - 1, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta9, 3);
                        this.gearPosX = tile9.getGearPosX();
                        this.gearPosY = tile9.getGearPosY();
                        this.gearPosZ = tile9.getGearPosZ();
                        break;
                    }
                }
                if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockPowerShaft && (world.func_72805_g(i, j - 1, k) & 15) == 8) {
                    ecru_TileEntityPowerShaft tile10 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j - 1, k);
                    if (tile10.getConnectCount() > this.dt_connectCount && tile10.getConnectCount() >= 2) {
                        this.dt_connectCount = tile10.getConnectCount() - 1;
                        this.dt_torque = tile10.getTorque();
                        int cmeta10 = world.func_72805_g(i, j - 1, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta10, 3);
                        this.gearPosX = tile10.getGearPosX();
                        this.gearPosY = tile10.getGearPosY();
                        this.gearPosZ = tile10.getGearPosZ();
                        break;
                    }
                }
                break;
            case 5:
                if (world.func_147439_a(i, j + 1, k) == mod_ecru_MapleTree.blockPowerShaftGear && (world.func_72805_g(i, j + 1, k) & 15) != 12) {
                    ecru_TileEntityPowerShaftGear tile11 = (ecru_TileEntityPowerShaftGear) world.func_147438_o(i, j + 1, k);
                    if (tile11.getConnectCount() > this.dt_connectCount && tile11.getConnectCount() >= 2) {
                        this.dt_connectCount = tile11.getConnectCount() - 1;
                        this.dt_torque = tile11.getTorque();
                        int cmeta11 = world.func_72805_g(i, j + 1, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta11, 3);
                        this.gearPosX = tile11.getGearPosX();
                        this.gearPosY = tile11.getGearPosY();
                        this.gearPosZ = tile11.getGearPosZ();
                        break;
                    }
                }
                if (world.func_147439_a(i, j + 1, k) == mod_ecru_MapleTree.blockPowerShaft && (world.func_72805_g(i, j + 1, k) & 15) == 8) {
                    ecru_TileEntityPowerShaft tile12 = (ecru_TileEntityPowerShaft) world.func_147438_o(i, j + 1, k);
                    if (tile12.getConnectCount() > this.dt_connectCount && tile12.getConnectCount() >= 2) {
                        this.dt_connectCount = tile12.getConnectCount() - 1;
                        this.dt_torque = tile12.getTorque();
                        int cmeta12 = world.func_72805_g(i, j + 1, k) & 8;
                        world.func_72921_c(i, j, k, meta | cmeta12, 3);
                        this.gearPosX = tile12.getGearPosX();
                        this.gearPosY = tile12.getGearPosY();
                        this.gearPosZ = tile12.getGearPosZ();
                        break;
                    }
                }
                break;
        }
    }

    private boolean checkHumanPower(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 4:
                if (world.func_147439_a(i, j - 1, k) == mod_ecru_MapleTree.blockHumanPowerDrive && (world.func_72805_g(i, j - 1, k) & 8) == 8) {
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    manTorqueSet(world, i, j - 1, k);
                    return true;
                }
                break;
            case 5:
                if (world.func_147439_a(i, j + 1, k) == mod_ecru_MapleTree.blockHumanPowerDrive && (world.func_72805_g(i, j + 1, k) & 8) == 8) {
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    manTorqueSet(world, i, j + 1, k);
                    return true;
                }
                break;
        }
        this.dt_connectCount = 0;
        this.dt_torque = 0;
        this.gearPosX = 0;
        this.gearPosY = -1;
        this.gearPosZ = 0;
        return false;
    }

    private void manTorqueSet(World world, int i, int j, int k) {
        ecru_TileEntityHumanPowerDrive tile = (ecru_TileEntityHumanPowerDrive) world.func_147438_o(i, j, k);
        if (tile != null && (tile instanceof ecru_TileEntityHumanPowerDrive)) {
            switch (tile.getEntityNum()) {
                case 1:
                    this.dt_connectCount = this.CONNECT_HUMAN1;
                    this.dt_torque = this.TORQUE_HUMAN1;
                    break;
                case 2:
                    this.dt_connectCount = this.CONNECT_HUMAN2;
                    this.dt_torque = this.TORQUE_HUMAN2;
                    break;
                case 3:
                    this.dt_connectCount = this.CONNECT_HUMAN3;
                    this.dt_torque = this.TORQUE_HUMAN3;
                    break;
                case 4:
                    this.dt_connectCount = this.CONNECT_HUMAN4;
                    this.dt_torque = this.TORQUE_HUMAN4;
                    break;
            }
            return;
        }
        this.dt_connectCount = 0;
        this.dt_torque = 0;
    }

    private boolean checkEnginePower(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
                if (world.func_147439_a(i, j, k + 1) == mod_ecru_MapleTree.blockEngine && world.func_72805_g(i, j, k + 1) == 13) {
                    this.dt_connectCount = this.CONNECT_ENGINE;
                    this.dt_torque = this.TORQUE_ENGINE;
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    return true;
                }
                break;
            case 1:
                if (world.func_147439_a(i - 1, j, k) == mod_ecru_MapleTree.blockEngine && world.func_72805_g(i - 1, j, k) == 9) {
                    this.dt_connectCount = this.CONNECT_ENGINE;
                    this.dt_torque = this.TORQUE_ENGINE;
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    return true;
                }
                break;
            case 2:
                if (world.func_147439_a(i, j, k - 1) == mod_ecru_MapleTree.blockEngine && world.func_72805_g(i, j, k - 1) == 13) {
                    this.dt_connectCount = this.CONNECT_ENGINE;
                    this.dt_torque = this.TORQUE_ENGINE;
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    return true;
                }
                break;
            case 3:
                if (world.func_147439_a(i + 1, j, k) == mod_ecru_MapleTree.blockEngine && world.func_72805_g(i + 1, j, k) == 9) {
                    this.dt_connectCount = this.CONNECT_ENGINE;
                    this.dt_torque = this.TORQUE_ENGINE;
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    return true;
                }
                break;
        }
        this.dt_connectCount = 0;
        this.dt_torque = 0;
        this.gearPosX = 0;
        this.gearPosY = -1;
        this.gearPosZ = 0;
        return false;
    }

    private boolean checkWheelPower(World world, int i, int j, int k) {
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
            case 1:
            case 2:
            case 3:
                if (checkWheel(world, i, j, k, meta & 7)) {
                    this.dt_connectCount = this.CONNECT_WHEEL;
                    this.dt_torque = this.TORQUE_WHEEL;
                    this.gearPosX = i;
                    this.gearPosY = j;
                    this.gearPosZ = k;
                    return true;
                }
                break;
        }
        this.dt_connectCount = 0;
        this.dt_torque = 0;
        this.gearPosX = 0;
        this.gearPosY = -1;
        this.gearPosZ = 0;
        return false;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean checkWheel(net.minecraft.world.World r6, int r7, int r8, int r9, int r10) {
        for (int r17 = 0; r17 < r6.field_72996_f.size(); r17++) {
            net.minecraft.entity.Entity r0 = (net.minecraft.entity.Entity) r6.field_72996_f.get(r17);
            if (r0.getClass().getSimpleName().equals("EntityWindmill") || r0.getClass().getSimpleName().equals("EntityWaterwheel") || r0.getClass().getSimpleName().equals("EntityWheels")) {
                double r0 = r0.field_70165_t;
                double r0 = r0.field_70161_v;
                switch (r10) {
                    case 0:
                        if (r0 > r7 && r0 < r7 + 1 && r0 > r9 + 1 && r0 < r9 + 2) {
                            return true;
                        }
                        break;
                    case 1:
                        if (r0 > r7 - 1 && r0 < r7 && r0 > r9 && r0 < r9 + 1) {
                            return true;
                        }
                        break;
                        break;
                    case 2:
                        if (r0 > r7 && r0 < r7 + 1 && r0 > r9 - 1 && r0 < r9) {
                            return true;
                        }
                        break;
                    case 3:
                        if (r0 > r7 + 1 && r0 < r7 + 2 && r0 > r9 && r0 < r9 + 1) {
                            return true;
                        }
                        break;
                        break;
                }
            }
        }
        return false;
    }

    private boolean checkSsWindmill(World world, int i, int j, int k) {
        Block b;
        int meta = world.func_72805_g(i, j, k);
        switch (meta & 7) {
            case 0:
                b = world.func_147439_a(i, j, k + 1);
                break;
            case 1:
            default:
                b = world.func_147439_a(i - 1, j, k);
                break;
            case 2:
                b = world.func_147439_a(i, j, k - 1);
                break;
            case 3:
                b = world.func_147439_a(i + 1, j, k);
                break;
        }
        if (b.func_149739_a().equals("tile.ss.small_waterwheel") || b.func_149739_a().equals("tile.ss.small_windmill") || b.func_149739_a().equals("tile.ss.windmill") || b.func_149739_a().equals("tile.ss.large_windmill")) {
            this.dt_connectCount = this.CONNECT_WHEEL;
            this.dt_torque = this.TORQUE_WHEEL;
            this.gearPosX = i;
            this.gearPosY = j;
            this.gearPosZ = k;
            return true;
        }
        this.dt_connectCount = 0;
        this.dt_torque = 0;
        this.gearPosX = 0;
        this.gearPosY = -1;
        this.gearPosZ = 0;
        return false;
    }
}
