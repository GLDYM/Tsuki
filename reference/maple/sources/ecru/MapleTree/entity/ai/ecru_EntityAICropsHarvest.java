package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_cropHarvest;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAICropsHarvest extends EntityAIBase {
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
    private Random random = new Random();

    public ecru_EntityAICropsHarvest(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
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
        Item item;
        int num;
        if (!this.worldObject.field_72995_K) {
            this.entityMomiji.momijiHarvesting = true;
            Block b = this.worldObject.func_147439_a((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
            ecru_cropHarvest ch = new ecru_cropHarvest(this.worldObject, b, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
            ch.harvestBlock();
            this.entityMomiji.func_71038_i();
            if (mod_ecru_MapleTree.registeredSeedPlant && (item = b.func_149650_a(0, this.random, 0)) != null && (num = this.entityMomiji.getThisItem(item)) != -1 && this.entityMomiji.thisSeeds(num)) {
                GameProfile gp = new GameProfile(UUID.randomUUID(), "CropsHarvest");
                FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
                ItemStack is = this.entityMomiji.itemStacks[num];
                if (is.func_77973_b().func_77648_a(is, fakePlayer, this.worldObject, (int) this.xPosition, ((int) this.yPosition) - 1, (int) this.zPosition, 1, 0.0f, 0.0f, 0.0f)) {
                    this.entityMomiji.func_71038_i();
                    if (this.entityMomiji.func_70301_a(num).field_77994_a == 0) {
                        this.entityMomiji.func_70298_a(num, 1);
                    }
                }
            }
        }
    }

    public boolean func_75253_b() {
        return !this.entityMomiji.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
        this.entityMomiji.momijiHarvesting = false;
    }

    public void func_75246_d() {
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        List bList = new ArrayList();
        for (int y = j - 1; y <= j + this.SCOPE_Y; y++) {
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
        for (int i = 0; i < ecru_util.excludeCropsList.length; i++) {
            Block block = this.worldObject.func_147439_a(x, y, z);
            String target = Block.field_149771_c.func_148750_c(block);
            if (target != null && target.equals(ecru_util.excludeCropsList[i])) {
                return false;
            }
        }
        if (this.worldObject.func_147439_a(x, y, z).getClass().getSimpleName().equals("BlockPamCrop")) {
            return false;
        }
        if ((this.worldObject.func_147439_a(x, y, z) instanceof BlockCrops) && this.worldObject.func_147439_a(x, y + 1, z) == Blocks.field_150350_a) {
            BlockCrops b = this.worldObject.func_147439_a(x, y, z);
            boolean flg = !b.func_149851_a(this.worldObject, x, y, z, true);
            if (flg) {
                return true;
            }
        }
        for (int i2 = 0; i2 < mod_ecru_MapleTree.addCropsTargetBlock.length; i2++) {
            Block block2 = this.worldObject.func_147439_a(x, y, z);
            String target2 = Block.field_149771_c.func_148750_c(block2);
            if (mod_ecru_MapleTree.addCropsTargetBlock[i2].useFlg) {
                if (mod_ecru_MapleTree.addCropsTargetBlock[i2].perfectMatching) {
                    if (target2 != null && target2.equals(mod_ecru_MapleTree.addCropsTargetBlock[i2].name) && this.worldObject.func_72805_g(x, y, z) == mod_ecru_MapleTree.addCropsTargetBlock[i2].meta) {
                        return true;
                    }
                } else if (target2 != null && target2.startsWith(mod_ecru_MapleTree.addCropsTargetBlock[i2].name) && this.worldObject.func_72805_g(x, y, z) == mod_ecru_MapleTree.addCropsTargetBlock[i2].meta) {
                    return true;
                }
            }
        }
        return false;
    }
}
