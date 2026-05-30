package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ecru_EntityAITreeMove extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private Block targetLogId;
    private int targetLogMeta;
    private int SCOPE_XZ = 10;
    private int SCOPE_Y = 3;
    private boolean forcedTermination = false;
    private int givenUpTimer = -1;
    private final int GIVENUP_TIME_COUNT = 600;

    public ecru_EntityAITreeMove(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntity.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if ((this.entityMomiji.getAttackMode() == 0 || !this.entityMomiji.attackModeHarvest) && this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && !this.entityMomiji.field_70128_L && this.entityMomiji.getAxe() != -1 && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getInventoryFull() != -1) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        return false;
    }

    public void func_75249_e() {
        if (!this.worldObject.field_72995_K) {
            this.forcedTermination = false;
            this.givenUpTimer = 600;
            this.entityMomiji.func_70661_as().func_75492_a(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
        }
    }

    public boolean func_75253_b() {
        if (this.forcedTermination) {
            return false;
        }
        int i = this.givenUpTimer - 1;
        this.givenUpTimer = i;
        if (i < 0) {
            return false;
        }
        if (600 - this.givenUpTimer > 200) {
            if (!this.entityMomiji.checkLogListMatch((int) this.xPosition, (int) this.yPosition, (int) this.zPosition)) {
                this.entityMomiji.checkLogListAdd((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
                return false;
            }
            return false;
        }
        return true;
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
        if (this.entityMomiji.func_70011_f(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d) > 1.82d) {
            return;
        }
        this.forcedTermination = true;
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        List bList = new ArrayList();
        for (int y = j - 1; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    if (!this.entityMomiji.checkLogListMatch(x, y, z) && checkBlock(x, y, z)) {
                        ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
                        bp.posX = x;
                        bp.posY = y;
                        bp.posZ = z;
                        bList.add(bp);
                    }
                }
            }
        }
        if (!bList.isEmpty()) {
            int li = this.entityMomiji.func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp2 = (ecru_EntityMomijiBlockPos) bList.get(li);
            EntityPlayer entityPlayerFunc_72890_a = this.entityMomiji.func_70902_q() != null ? (EntityPlayer) this.entityMomiji.func_70902_q() : this.entityMomiji.field_70170_p.func_72890_a(this.entityMomiji, 16.0d);
            this.xPosition = bp2.posX;
            this.yPosition = bp2.posY;
            this.zPosition = bp2.posZ;
            return true;
        }
        return false;
    }

    private boolean checkBlock(int x, int y, int z) {
        if (this.worldObject.func_147437_c(x, y, z)) {
            return false;
        }
        for (int i = 0; i < ecru_util.excludeLogList.length; i++) {
            Block block = this.worldObject.func_147439_a(x, y, z);
            String target = Block.field_149771_c.func_148750_c(block);
            if (target != null && target.equals(ecru_util.excludeCropsList[i])) {
                return false;
            }
        }
        boolean flg = false;
        int meta = this.worldObject.func_72805_g(x, y, z) & 3;
        Block block2 = this.worldObject.func_147439_a(x, y, z);
        String target2 = Block.field_149771_c.func_148750_c(block2);
        int s = 0;
        while (true) {
            if (s >= mod_ecru_MapleTree.logAndSapling.length) {
                break;
            }
            if (!target2.equals(mod_ecru_MapleTree.logAndSapling[s].logName) || meta != mod_ecru_MapleTree.logAndSapling[s].logMeta) {
                s++;
            } else {
                flg = true;
                break;
            }
        }
        if (!flg) {
            int s2 = 0;
            while (true) {
                if (s2 >= mod_ecru_MapleTree.logId.size()) {
                    break;
                }
                if (!target2.equals(mod_ecru_MapleTree.logId.get(s2)) || meta != mod_ecru_MapleTree.logMeta.get(s2).intValue()) {
                    s2++;
                } else {
                    flg = true;
                    break;
                }
            }
        }
        if (!flg) {
            return false;
        }
        if (!(this.worldObject.func_147439_a(x, y, z) instanceof BlockLog) && !this.worldObject.func_147439_a(x, y, z).isWood(this.worldObject, x, y, z)) {
            return false;
        }
        if (this.worldObject.func_147439_a(x, y - 1, z) == Blocks.field_150346_d || this.worldObject.func_147439_a(x, y - 1, z) == Blocks.field_150349_c) {
            this.targetLogId = this.worldObject.func_147439_a(x, y, z);
            this.targetLogMeta = this.worldObject.func_72805_g(x, y, z);
            return true;
        }
        return false;
    }
}
