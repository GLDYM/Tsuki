package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiMobPos;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAICowsMilk extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private Entity targetEntity;
    private PathNavigate momijiPathfinder;
    private final Random random = new Random();
    private int SCOPE_XZ = 13;
    private int SCOPE_Y = 3;
    private boolean forcedTermination = false;
    private int givenUpTimer = -1;
    private int CowsMilkCoolTimeCount = 10;
    private int CowsMilkCoolTime = this.CowsMilkCoolTimeCount;
    private int nextChechTime = 30;

    public ecru_EntityAICowsMilk(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.momijiPathfinder = pEntity.func_70661_as();
        this.worldObject = pEntity.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        int i = this.nextChechTime - 1;
        this.nextChechTime = i;
        if (i < 0 && this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && this.entityMomiji.getAttackMode() == 3 && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getInventoryFull() != -1 && this.entityMomiji.getBucket() != -1) {
            return getMobList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + 0, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + 0);
        }
        return false;
    }

    public void func_75249_e() {
        this.forcedTermination = false;
        this.givenUpTimer = 600;
    }

    public boolean func_75253_b() {
        if (this.forcedTermination) {
            return false;
        }
        int i = this.givenUpTimer - 1;
        this.givenUpTimer = i;
        return i >= 0 && !this.momijiPathfinder.func_75500_f();
    }

    public void func_75251_c() {
        this.targetEntity = null;
        this.momijiPathfinder.func_75499_g();
    }

    public void func_75246_d() {
        if (Math.sqrt(this.entityMomiji.func_70068_e(this.targetEntity)) > 1.6d) {
            int i = this.CowsMilkCoolTime - 1;
            this.CowsMilkCoolTime = i;
            if (i < 0) {
                this.momijiPathfinder.func_75497_a(this.targetEntity, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
                this.CowsMilkCoolTime = this.CowsMilkCoolTimeCount;
                return;
            }
            return;
        }
        GameProfile gp = new GameProfile(UUID.randomUUID(), "CowsMilk");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        if (this.entityMomiji.getAttackMode() == 3) {
            this.entityMomiji.func_70671_ap().func_75650_a(this.xPosition + 0.5d, this.yPosition + 0.8d, this.zPosition + 0.5d, 10.0f, this.entityMomiji.func_70646_bf());
            int sSlot = this.entityMomiji.getBucket();
            if (sSlot != -1) {
                ItemStack it = this.entityMomiji.func_70301_a(sSlot);
                clearmainInventory(fakePlayer);
                ((EntityPlayer) fakePlayer).field_71071_by.field_70462_a[0] = it;
                ((EntityPlayer) fakePlayer).field_71071_by.field_70461_c = 0;
                this.worldObject.func_72956_a(this.entityMomiji, "game.player.swim", 0.2f, (((this.random.nextFloat() - this.random.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
                this.targetEntity.func_70085_c(fakePlayer);
                movePlayerToMomiji(fakePlayer);
                if (it.field_77994_a <= 0) {
                    this.entityMomiji.itemStacks[sSlot] = null;
                }
                this.nextChechTime = 30;
            }
        }
        this.forcedTermination = true;
    }

    private boolean getMobList(World world, int i, int j, int k) {
        List mobList = new ArrayList();
        for (int m = 0; m < world.field_72996_f.size(); m++) {
            EntityLivingBase entityLivingBase = (Entity) world.field_72996_f.get(m);
            if (((Entity) entityLivingBase).field_70165_t > i - this.SCOPE_XZ && ((Entity) entityLivingBase).field_70165_t < i + this.SCOPE_XZ && ((Entity) entityLivingBase).field_70163_u > j - this.SCOPE_Y && ((Entity) entityLivingBase).field_70163_u < j + this.SCOPE_Y && ((Entity) entityLivingBase).field_70161_v > k - this.SCOPE_XZ && ((Entity) entityLivingBase).field_70161_v < k + this.SCOPE_XZ && checkCow(entityLivingBase)) {
                ecru_EntityMomijiMobPos mp = new ecru_EntityMomijiMobPos();
                mp.entity = entityLivingBase;
                mobList.add(mp);
            }
        }
        if (!mobList.isEmpty()) {
            int li = this.entityMomiji.func_70681_au().nextInt(mobList.size());
            this.targetEntity = ((ecru_EntityMomijiMobPos) mobList.get(li)).entity;
            return true;
        }
        return false;
    }

    private boolean checkCow(Entity e) {
        if ((e instanceof EntityCow) && !((EntityCow) e).func_70631_g_()) {
            return true;
        }
        return false;
    }

    private void clearmainInventory(EntityPlayer player) {
        for (int a = 0; a < player.field_71071_by.field_70462_a.length; a++) {
            player.field_71071_by.field_70462_a[a] = null;
        }
    }

    private void movePlayerToMomiji(EntityPlayer player) {
        for (int a = 0; a < player.field_71071_by.field_70462_a.length; a++) {
            if (player.field_71071_by.func_70301_a(a) != null && player.field_71071_by.func_70301_a(a).func_77973_b() == Items.field_151117_aB) {
                this.entityMomiji.setItems(player.field_71071_by.func_70301_a(a));
                player.field_71071_by.field_70462_a[a] = null;
            }
        }
        for (int a2 = 0; a2 < player.field_71071_by.field_70462_a.length; a2++) {
            player.field_71071_by.field_70462_a[a2] = null;
        }
    }
}
