package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.BlockPosComparator;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAIPamCropsHarvest extends EntityAIBase {
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
    private boolean nearbyFarmland = true;

    public ecru_EntityAIPamCropsHarvest(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntity.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if ((this.entityMomiji.getAttackMode() == 0 || !this.entityMomiji.attackModeHarvest) && this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && !this.entityMomiji.field_70128_L && this.entityMomiji.getHoe() != -1 && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getInventoryFull() != -1) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        return false;
    }

    public void func_75249_e() {
        GameProfile gp = new GameProfile(UUID.randomUUID(), "PamCropsHarvest");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        int slotNum = this.entityMomiji.getHoe();
        if (slotNum != -1) {
            ItemStack itemStack = this.entityMomiji.itemStacks[slotNum];
            Block b = this.worldObject.func_147439_a((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
            b.func_149727_a(this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, fakePlayer, 1, 0.0f, 0.0f, 0.0f);
            this.worldObject.func_72956_a(this.entityMomiji, "mob.sheep.shear", 1.0f, 1.0f);
            this.entityMomiji.func_71038_i();
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
        int li;
        List bList = new ArrayList();
        for (int y = j - 1; y <= j + this.SCOPE_Y; y++) {
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
        if (this.worldObject.func_147437_c(x, y, z)) {
            return false;
        }
        for (int i = 0; i < ecru_util.excludeCropsList.length; i++) {
            Block block = this.worldObject.func_147439_a(x, y, z);
            String target = Block.field_149771_c.func_148750_c(block);
            if (target != null && target.equals(ecru_util.excludeCropsList[i])) {
                return false;
            }
        }
        if (!this.worldObject.func_147439_a(x, y, z).getClass().getSimpleName().equals("BlockPamCrop") || this.worldObject.func_147439_a(x, y + 1, z) != Blocks.field_150350_a) {
            return false;
        }
        if (this.worldObject.func_147439_a(x, y, z) instanceof BlockCrops) {
            BlockCrops b = this.worldObject.func_147439_a(x, y, z);
            boolean flg = !b.func_149851_a(this.worldObject, x, y, z, true);
            if (flg) {
                return true;
            }
            return false;
        }
        if ((this.worldObject.func_72805_g(x, y, z) & 7) == 7) {
            return true;
        }
        return false;
    }
}
