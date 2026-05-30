package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAIGetChestItems extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private int process;
    private ItemStack checkItem;
    private int RUN_MODE_NON = 0;
    private int RUN_MODE_UDON = 1;
    private int RUN_MODE_BUCKET = 2;
    private int RunMode = this.RUN_MODE_NON;
    private int SCOPE_XZ = 10;
    private int SCOPE_Y = 3;
    private boolean forcedTermination = false;
    private int givenUpTimer = 0;
    private int coolTimeMax = 10;
    private int coolTime = this.coolTimeMax;

    public ecru_EntityAIGetChestItems(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntity.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if (this.entityMomiji.getAttackMode() == 1) {
            this.RunMode = this.RUN_MODE_UDON;
            if (this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_() && (this.entityMomiji.getTeuchi(1) == -1 || this.entityMomiji.getTeuchi(2) == -1)) {
                int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
                int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
                if (this.entityMomiji.getTeuchi(1) == -1) {
                    this.checkItem = this.entityMomiji.chestItem[0];
                    boolean ret = getBlockList(this.checkItem, this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
                    if (!ret && this.entityMomiji.getTeuchi(2) == -1) {
                        this.checkItem = this.entityMomiji.chestItem[1];
                        ret = getBlockList(this.checkItem, this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
                    }
                    return ret;
                }
                if (this.entityMomiji.getTeuchi(2) == -1) {
                    this.checkItem = this.entityMomiji.chestItem[1];
                    boolean ret2 = getBlockList(this.checkItem, this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
                    return ret2;
                }
            }
        }
        if (this.entityMomiji.getAttackMode() == 3) {
            this.RunMode = this.RUN_MODE_BUCKET;
            if (this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getBucket() == -1) {
                int offsetX2 = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
                int offsetZ2 = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
                this.checkItem = this.entityMomiji.chestItem[2];
                boolean ret3 = getBlockList(this.checkItem, this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX2, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ2);
                return ret3;
            }
        }
        this.RunMode = this.RUN_MODE_NON;
        return false;
    }

    public void func_75249_e() {
        this.forcedTermination = false;
        this.givenUpTimer = 1200;
    }

    public boolean func_75253_b() {
        if (this.forcedTermination) {
            return false;
        }
        int i = this.givenUpTimer - 1;
        this.givenUpTimer = i;
        if (i < 0 || this.worldObject.func_147439_a((int) this.xPosition, (int) this.yPosition, (int) this.zPosition) != Blocks.field_150486_ae) {
            return false;
        }
        return true;
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
        if (this.entityMomiji.func_70011_f(this.xPosition + 0.5d, this.yPosition + 0.5d, this.zPosition + 0.5d) > 2.8d) {
            int i = this.coolTime - 1;
            this.coolTime = i;
            if (i < 0) {
                this.entityMomiji.func_70661_as().func_75492_a(this.xPosition + 0.5d, this.yPosition + 0.5d, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
                this.coolTime = this.coolTimeMax;
                return;
            }
            return;
        }
        GameProfile gp = new GameProfile(UUID.randomUUID(), "chestOpen");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        ((EntityPlayer) fakePlayer).field_70165_t = this.entityMomiji.field_70165_t;
        ((EntityPlayer) fakePlayer).field_70163_u = this.entityMomiji.field_70163_u;
        ((EntityPlayer) fakePlayer).field_70161_v = this.entityMomiji.field_70161_v;
        ItemStack[] it = {this.checkItem};
        switch (this.RunMode) {
            case 1:
                this.entityMomiji.getChestMode(this.checkItem, 3, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, 1);
                break;
            case 2:
                this.entityMomiji.getChestMode(this.checkItem, 3, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, 0);
                break;
        }
        this.forcedTermination = true;
    }

    private boolean getBlockList(ItemStack it, World world, int i, int j, int k) {
        List bList = new ArrayList();
        for (int y = j - this.SCOPE_Y; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    if (checkBlock(it, x, y, z)) {
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

    private boolean checkBlock(ItemStack is, int x, int y, int z) {
        if (!this.worldObject.func_147437_c(x, y, z) && this.worldObject.func_147439_a(x, y, z) == Blocks.field_150486_ae) {
            TileEntityChest ti = this.worldObject.func_147438_o(x, y, z);
            if (this.entityMomiji.getCheckChest_takeOutContains(is, ti)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
