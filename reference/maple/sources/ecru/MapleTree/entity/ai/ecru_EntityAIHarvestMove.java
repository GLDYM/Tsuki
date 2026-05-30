package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.common.BlockPosComparator;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ecru_EntityAIHarvestMove extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private int SCOPE_XZ = 8;
    private int SCOPE_Y = 3;
    private boolean nearbyFarmland = true;
    private int nearbyFarmlandTime = 0;
    private boolean forcedTermination = false;
    private int givenUpTimer = -1;

    public ecru_EntityAIHarvestMove(ecru_EntityMomiji pEntityMomiji, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntityMomiji;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntityMomiji.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if ((this.entityMomiji.getAttackMode() == 0 || !this.entityMomiji.attackModeHarvest) && this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getShears() != -1 && this.entityMomiji.getInventoryFull() != -1) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        return false;
    }

    public void func_75249_e() {
        this.forcedTermination = false;
        this.givenUpTimer = 100;
        this.entityMomiji.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
    }

    public boolean func_75253_b() {
        if (!this.nearbyFarmland) {
            int i = this.nearbyFarmlandTime - 1;
            this.nearbyFarmlandTime = i;
            if (i <= 0) {
                this.nearbyFarmland = true;
                this.nearbyFarmlandTime = 0;
            }
        }
        if (this.forcedTermination) {
            return false;
        }
        boolean ret = this.entityMomiji.func_70661_as().func_75500_f();
        if (this.nearbyFarmland) {
            int i2 = this.givenUpTimer - 1;
            this.givenUpTimer = i2;
            if (i2 < 0 || ret) {
                this.nearbyFarmland = false;
                this.nearbyFarmlandTime = 100;
                return false;
            }
        }
        return !ret;
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
    }

    public boolean canEntityItemBeSeen(ecru_EntityMomijiBlockPos bp) {
        return this.entityMomiji.field_70170_p.func_72933_a(Vec3.func_72443_a(this.entityMomiji.field_70165_t, this.entityMomiji.field_70163_u + ((double) this.entityMomiji.func_70047_e()), this.entityMomiji.field_70161_v), Vec3.func_72443_a((double) bp.posX, ((double) bp.posY) + ((double) this.entityMomiji.func_70047_e()), (double) bp.posZ)) == null;
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        int li;
        List bList = new ArrayList();
        for (int y = j - 2; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    if (checkBlock(x, y, z)) {
                        ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
                        bp.posX = x;
                        bp.posY = y;
                        bp.posZ = z;
                        bp.distance = this.entityMomiji.func_70011_f(x, y, z);
                        bList.add(bp);
                    }
                }
            }
        }
        if (!bList.isEmpty()) {
            if (this.nearbyFarmland) {
                Collections.sort(bList, new BlockPosComparator());
                li = 0;
            } else {
                li = this.entityMomiji.func_70681_au().nextInt(bList.size());
            }
            ecru_EntityMomijiBlockPos bp2 = (ecru_EntityMomijiBlockPos) bList.get(li);
            this.xPosition = bp2.posX;
            this.yPosition = bp2.posY;
            this.zPosition = bp2.posZ;
            return true;
        }
        return false;
    }

    private boolean checkBlock(int x, int y, int z) {
        for (int i = 0; i < mod_ecru_MapleTree.shearsHarvestTargetBlock.length; i++) {
            Block block = this.worldObject.func_147439_a(x, y, z);
            String target = Block.field_149771_c.func_148750_c(block);
            if (target != null && target.equals(mod_ecru_MapleTree.shearsHarvestTargetBlock[i].name) && (mod_ecru_MapleTree.shearsHarvestTargetBlock[i].meta == -1 || this.worldObject.func_72805_g(x, y, z) == mod_ecru_MapleTree.shearsHarvestTargetBlock[i].meta)) {
                if (this.worldObject.func_147439_a(x, y, z) == Blocks.field_150436_aH) {
                    if (this.worldObject.func_147439_a(x, y - 1, z) == Blocks.field_150436_aH && this.worldObject.func_147439_a(x, y + 1, z) == Blocks.field_150436_aH) {
                        return true;
                    }
                    return false;
                }
                if (mod_ecru_MapleTree.shearsHarvestTargetBlock[i].beSeen != 1 || this.worldObject.func_147439_a(x + 1, y, z) == Blocks.field_150350_a || this.worldObject.func_147439_a(x - 1, y, z) == Blocks.field_150350_a || this.worldObject.func_147439_a(x, y, z + 1) == Blocks.field_150350_a || this.worldObject.func_147439_a(x, y, z - 1) == Blocks.field_150350_a || this.worldObject.func_147439_a(x, y + 1, z) == Blocks.field_150350_a || this.worldObject.func_147439_a(x, y - 1, z) == Blocks.field_150350_a) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
