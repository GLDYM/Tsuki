package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAIHarvest extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private int SCOPE_XZ = 1;
    private int SCOPE_Y = 3;

    public ecru_EntityAIHarvest(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntity.field_70170_p;
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
        GameProfile gp = new GameProfile(UUID.randomUUID(), "grapeHarvest");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        ((EntityPlayer) fakePlayer).field_70165_t = this.entityMomiji.field_70165_t;
        ((EntityPlayer) fakePlayer).field_70163_u = this.entityMomiji.field_70163_u;
        ((EntityPlayer) fakePlayer).field_70161_v = this.entityMomiji.field_70161_v;
        int slotNum = this.entityMomiji.getShears();
        if (slotNum != -1) {
            ItemStack is = this.entityMomiji.itemStacks[slotNum];
            if (is.func_77973_b().func_77648_a(is, fakePlayer, this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, 0, 0.0f, 0.0f, 0.0f)) {
                this.entityMomiji.func_71038_i();
                if (this.entityMomiji.func_70301_a(slotNum).field_77994_a == 0) {
                    this.entityMomiji.func_70298_a(slotNum, 1);
                }
                this.worldObject.func_72956_a(this.entityMomiji, "mob.sheep.shear", 1.0f, 1.0f);
            }
        }
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
        for (int y = j - 1; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    int ret = checkBlock(x, y, z);
                    boolean beSeenCheck = (ret & 1) == 1;
                    if ((ret & 4) == 4) {
                        ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
                        bp.posX = x;
                        bp.posY = y;
                        bp.posZ = z;
                        bp.beSeenCheck = beSeenCheck;
                        bList.add(bp);
                    }
                }
            }
        }
        if (!bList.isEmpty()) {
            int li = this.entityMomiji.func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp2 = (ecru_EntityMomijiBlockPos) bList.get(li);
            if (!bp2.beSeenCheck || ecru_util.canEntityItemBeSeen(this.entityMomiji, bp2)) {
                this.xPosition = bp2.posX;
                this.yPosition = bp2.posY;
                this.zPosition = bp2.posZ;
                return true;
            }
        }
        return false;
    }

    private int checkBlock(int x, int y, int z) {
        for (int i = 0; i < mod_ecru_MapleTree.shearsHarvestTargetBlock.length; i++) {
            Block block = this.worldObject.func_147439_a(x, y, z);
            String target = Block.field_149771_c.func_148750_c(block);
            int be = mod_ecru_MapleTree.shearsHarvestTargetBlock[i].beSeen;
            if (target != null && target.equals(mod_ecru_MapleTree.shearsHarvestTargetBlock[i].name) && ((mod_ecru_MapleTree.shearsHarvestTargetBlock[i].meta == -1 || this.worldObject.func_72805_g(x, y, z) == mod_ecru_MapleTree.shearsHarvestTargetBlock[i].meta) && mod_ecru_MapleTree.shearsHarvestTargetBlock[i].harvest)) {
                if (this.worldObject.func_147439_a(x, y, z) == Blocks.field_150436_aH) {
                    if (this.worldObject.func_147439_a(x, y - 1, z) == Blocks.field_150436_aH && this.worldObject.func_147439_a(x, y + 1, z) == Blocks.field_150436_aH) {
                        return 4 | be;
                    }
                    return 0;
                }
                return 4 | be;
            }
        }
        return 0;
    }
}
