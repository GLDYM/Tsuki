package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ecru_EntityAIChestMove extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private int moveMode;
    private int SCOPE_XZ = 15;
    private int SCOPE_Y = 3;
    private int MOVE_MODE_CHEST = 0;
    private int MOVE_MODE_HOPPER = 1;
    private int HOPPER_COOL_TIME = 1200;
    private int hopperGetCoolTime = 0;

    public ecru_EntityAIChestMove(ecru_EntityMomiji pEntityMomiji, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntityMomiji;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntityMomiji.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        this.moveMode = -1;
        if (this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.getInventoryFull() == -1 && !this.entityMomiji.func_70631_g_()) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            this.moveMode = this.MOVE_MODE_CHEST;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        this.hopperGetCoolTime = this.hopperGetCoolTime >= 0 ? this.hopperGetCoolTime - 1 : this.hopperGetCoolTime;
        if (this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && this.entityMomiji.getToolFull() != -1 && !this.entityMomiji.func_70631_g_() && this.hopperGetCoolTime < 0) {
            int offsetX2 = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ2 = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            this.moveMode = this.MOVE_MODE_HOPPER;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX2, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ2);
        }
        return false;
    }

    public void func_75249_e() {
        this.entityMomiji.func_70661_as().func_75492_a(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
        this.hopperGetCoolTime = this.HOPPER_COOL_TIME;
    }

    public boolean func_75253_b() {
        return !this.entityMomiji.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        List bList = new ArrayList();
        for (int y = j - 2; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    if (checkBlock(x, y, z)) {
                        ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
                        bp.posX = x;
                        bp.posY = this.moveMode == this.MOVE_MODE_CHEST ? y : y - 1;
                        bp.posZ = z;
                        bList.add(bp);
                    }
                }
            }
        }
        if (!bList.isEmpty()) {
            int li = this.entityMomiji.func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp2 = (ecru_EntityMomijiBlockPos) bList.get(li);
            this.xPosition = bp2.posX;
            this.yPosition = bp2.posY;
            this.zPosition = bp2.posZ;
            return true;
        }
        if (getMapList() && this.moveMode == this.MOVE_MODE_CHEST) {
            return true;
        }
        return false;
    }

    private boolean checkBlock(int x, int y, int z) {
        if (this.moveMode == this.MOVE_MODE_CHEST) {
            Block tergetBlock = Blocks.field_150486_ae;
            if (this.worldObject.func_147439_a(x, y, z) == tergetBlock) {
                TileEntity ti = this.worldObject.func_147438_o(x, y, z);
                if (this.entityMomiji.getCheckChest().contains(ti)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        if (this.moveMode == this.MOVE_MODE_HOPPER) {
            Block tergetBlock2 = Blocks.field_150438_bZ;
            if (this.worldObject.func_147439_a(x, y, z) == tergetBlock2 && this.worldObject.func_72805_g(x, y, z) == 0 && this.worldObject.func_147439_a(x, y - 1, z) == Blocks.field_150350_a) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean getMapList() {
        if (this.entityMomiji.useChest_key.size() > 0) {
            String s = getTheNearest();
            if (s.length() != 0 && this.entityMomiji.useChest.containsKey(s + "-x") && this.entityMomiji.useChest.containsKey(s + "-y") && this.entityMomiji.useChest.containsKey(s + "-z")) {
                int x = this.entityMomiji.useChest.get(s + "-x").intValue();
                int y = this.entityMomiji.useChest.get(s + "-y").intValue();
                int z = this.entityMomiji.useChest.get(s + "-z").intValue();
                double xx = Math.abs(this.entityMomiji.field_70165_t - x);
                double yy = Math.abs(this.entityMomiji.field_70163_u - y);
                double zz = Math.abs(this.entityMomiji.field_70161_v - z);
                if (Math.sqrt((xx * xx) + (yy * yy) + (zz * zz)) <= 16.0d) {
                    this.xPosition = x;
                    this.yPosition = y;
                    this.zPosition = z;
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private String getTheNearest() {
        List<Integer> deleteChest_x = new ArrayList<>();
        List<Integer> deleteChest_y = new ArrayList<>();
        List<Integer> deleteChest_z = new ArrayList<>();
        double distance = 100000.0d;
        String target = "";
        if (this.entityMomiji.useChest_key.isEmpty()) {
            return target;
        }
        for (String key : this.entityMomiji.useChest_key.keySet()) {
            String s = key.toString();
            if (this.entityMomiji.useChest.containsKey(s + "-x") && this.entityMomiji.useChest.containsKey(s + "-y") && this.entityMomiji.useChest.containsKey(s + "-z")) {
                int x = this.entityMomiji.useChest.get(s + "-x").intValue();
                int y = this.entityMomiji.useChest.get(s + "-y").intValue();
                int z = this.entityMomiji.useChest.get(s + "-z").intValue();
                if (this.worldObject.func_147439_a(x, y, z) != Blocks.field_150486_ae || Math.sqrt(this.entityMomiji.func_110172_bL().func_71569_e(x, y, z)) > this.entityMomiji.MAXIMUM_HOME_DISTANCE) {
                    deleteChest_x.add(new Integer(x));
                    deleteChest_y.add(new Integer(y));
                    deleteChest_z.add(new Integer(z));
                } else {
                    double xx = Math.abs(this.entityMomiji.field_70165_t - x);
                    double yy = Math.abs(this.entityMomiji.field_70163_u - y);
                    double zz = Math.abs(this.entityMomiji.field_70161_v - z);
                    if (Math.sqrt((xx * xx) + (yy * yy) + (zz * zz)) < distance) {
                        distance = Math.sqrt((xx * xx) + (yy * yy) + (zz * zz));
                        target = s;
                    }
                }
            }
        }
        if (deleteChest_x != null) {
            for (int n = 0; n < deleteChest_x.size(); n++) {
                this.entityMomiji.deleteUseChestList(deleteChest_x.get(n).intValue(), deleteChest_y.get(n).intValue(), deleteChest_z.get(n).intValue());
            }
        }
        return target;
    }
}
