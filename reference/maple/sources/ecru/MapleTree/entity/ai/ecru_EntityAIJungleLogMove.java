package ecru.MapleTree.entity.ai;

import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ecru_EntityAIJungleLogMove extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private int SCOPE_XZ = 8;
    private int SCOPE_Y = 3;
    private double xPosition;
    private double yPosition;
    private double zPosition;

    public ecru_EntityAIJungleLogMove(ecru_EntityMomiji pEntityMomiji, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntityMomiji;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntityMomiji.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if ((this.entityMomiji.getAttackMode() == 0 || !this.entityMomiji.attackModeHarvest) && this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getShears() != -1 && this.entityMomiji.getInventoryFull() != -1 && this.entityMomiji.getCacaoSeeds() != -1) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        return false;
    }

    public void func_75249_e() {
        this.entityMomiji.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
    }

    public boolean func_75253_b() {
        return !this.entityMomiji.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
    }

    public boolean canEntityItemBeSeen(ecru_EntityMomijiBlockPos bp) {
        return this.entityMomiji.field_70170_p.func_72933_a(Vec3.func_72443_a(this.entityMomiji.field_70165_t, this.entityMomiji.field_70163_u + ((double) this.entityMomiji.func_70047_e()), this.entityMomiji.field_70161_v), Vec3.func_72443_a((double) bp.posX, ((double) bp.posY) + ((double) this.entityMomiji.func_70047_e()), (double) bp.posZ)) == null;
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        List bList = new ArrayList();
        for (int y = j - 2; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    if (checkBlock(x, y, z)) {
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
            this.xPosition = bp2.posX;
            this.yPosition = bp2.posY;
            this.zPosition = bp2.posZ;
            return true;
        }
        return false;
    }

    private boolean checkBlock(int x, int y, int z) {
        if (this.worldObject.func_147439_a(x, y, z) == Blocks.field_150364_r && (this.worldObject.func_72805_g(x, y, z) & 3) == 3) {
            if (this.worldObject.func_147439_a(x, y, z + 1) == Blocks.field_150350_a || this.worldObject.func_147439_a(x, y, z - 1) == Blocks.field_150350_a || this.worldObject.func_147439_a(x + 1, y, z) == Blocks.field_150350_a || this.worldObject.func_147439_a(x - 1, y, z) == Blocks.field_150350_a) {
                return true;
            }
            return false;
        }
        return false;
    }
}
