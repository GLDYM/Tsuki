package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.FishingHooks;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAIFishing extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double xPositionW;
    private double yPositionW;
    private double zPositionW;
    private PathNavigate momijiPathfinder;
    public static final int[] LV = {10, 30, 60, 120, 240, 500, 1000, 2000, 2500, 10000, 0};
    private final Random random = new Random();
    private int SCOPE_XZ = 10;
    private int SCOPE_Y = 3;
    private boolean forcedTermination = false;
    private int givenUpTimer = -1;
    private int fishingCoolTimeCount = 10;
    private int fishingCoolTime = this.fishingCoolTimeCount;
    private final int[] LVbonus = {5, 4, 4, 4, 3, 3, 3, 2, 2, 1, 1};
    private final int[] LVbonus2 = {10, 10, 9, 8, 8, 7, 6, 6, 5, 4, 2};

    public ecru_EntityAIFishing(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.momijiPathfinder = pEntity.func_70661_as();
        this.worldObject = pEntity.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if (this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && this.entityMomiji.getAttackMode() == 2 && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_() && this.entityMomiji.getInventoryFull() != -1) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        return false;
    }

    public void func_75249_e() {
        this.forcedTermination = false;
        this.givenUpTimer = 600;
        this.entityMomiji.func_70661_as().func_75492_a(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
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
        return true;
    }

    public void func_75251_c() {
        this.momijiPathfinder.func_75499_g();
    }

    public void func_75246_d() {
        char c = this.entityMomiji.field_70165_t < 0.0d ? (char) 65535 : (char) 0;
        char c2 = this.entityMomiji.field_70161_v < 0.0d ? (char) 65535 : (char) 0;
        MathHelper.func_76128_c(this.entityMomiji.field_70165_t);
        MathHelper.func_76128_c(this.entityMomiji.field_70163_u);
        MathHelper.func_76128_c(this.entityMomiji.field_70161_v);
        if (this.entityMomiji.func_70011_f(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d) > 0.7d) {
            int i = this.fishingCoolTime - 1;
            this.fishingCoolTime = i;
            if (i < 0) {
                this.fishingCoolTime = this.fishingCoolTimeCount;
                this.momijiPathfinder.func_75492_a(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
                return;
            }
            return;
        }
        GameProfile gp = new GameProfile(UUID.randomUUID(), "fishing");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        if (this.entityMomiji.getAttackMode() == 2) {
            this.entityMomiji.func_70671_ap().func_75650_a(this.xPositionW + 0.5d, this.yPositionW + 0.8d, this.zPositionW + 0.5d, 10.0f, this.entityMomiji.func_70646_bf());
            int fSlot = this.entityMomiji.getFishingRod();
            int i2 = this.fishingCoolTime - 1;
            this.fishingCoolTime = i2;
            if (i2 < 0 && fSlot != -1) {
                if (this.random.nextInt(this.LVbonus[this.entityMomiji.fishingLv]) == 0) {
                    this.entityMomiji.func_71038_i();
                    this.worldObject.func_72956_a(this.entityMomiji, "game.player.swim", 0.2f, (((this.random.nextFloat() - this.random.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
                    if (this.random.nextInt(this.LVbonus2[this.entityMomiji.fishingLv]) == 0) {
                        ItemStack it = this.entityMomiji.func_70301_a(fSlot);
                        ((EntityPlayer) fakePlayer).field_71071_by.field_70462_a[0] = it;
                        ((EntityPlayer) fakePlayer).field_71071_by.field_70461_c = 0;
                        putFish(this.worldObject, fakePlayer);
                        if (this.entityMomiji.fishingLv >= 10 && this.entityMomiji.fishingCount > LV[10]) {
                            this.entityMomiji.fishingCount = 0;
                        }
                        if (this.entityMomiji.fishingLv < 0 || this.entityMomiji.fishingLv > 10) {
                            this.entityMomiji.fishingLv = 0;
                        }
                        if (this.entityMomiji.fishingLv < 10) {
                            ecru_EntityMomiji ecru_entitymomiji = this.entityMomiji;
                            int i3 = ecru_entitymomiji.fishingCount + 1;
                            ecru_entitymomiji.fishingCount = i3;
                            if (i3 >= LV[this.entityMomiji.fishingLv]) {
                                this.entityMomiji.fishingLv++;
                                this.entityMomiji.fishingLv = this.entityMomiji.fishingLv > 10 ? 10 : this.entityMomiji.fishingLv;
                                this.entityMomiji.fishingCount = 0;
                            }
                        }
                        int dMax = it.func_77958_k();
                        int dNow = it.func_77960_j();
                        it.func_77972_a(1, fakePlayer);
                        if (dNow == dMax && !it.func_77951_h()) {
                            this.entityMomiji.func_70298_a(fSlot, 1);
                        }
                    }
                }
                this.fishingCoolTime = this.fishingCoolTimeCount;
            }
        }
    }

    private void putFish(World world, EntityPlayer player) {
        double posX = this.xPositionW + 0.5d;
        double posY = this.yPositionW + 1.1d;
        double posZ = this.zPositionW + 0.5d;
        double _muki = MathHelper.func_76128_c(this.entityMomiji.field_70759_as % 360.0f);
        double _muki2 = _muki < 0.0d ? 360.0d + _muki : _muki;
        double muki = _muki2 - 70.0d < 0.0d ? 360.0d - (70.0d - _muki2) : _muki2 - 70.0d;
        double mi = 6.2831855f * (((float) muki) / 360.0f);
        double xx = Math.cos(mi) * 1.8d;
        double zz = Math.sin(mi) * 1.8d;
        EntityItem entityitem = new EntityItem(world, posX, posY, posZ, fishingItem(world, player));
        double dx = (this.entityMomiji.field_70165_t + xx) - posX;
        double dz = (this.entityMomiji.field_70161_v + zz) - posZ;
        double distance = MathHelper.func_76133_a((dx * dx) + (0.5d * 0.5d) + (dz * dz));
        entityitem.field_70159_w = dx * 0.1d;
        entityitem.field_70181_x = (0.5d * 0.1d) + (MathHelper.func_76133_a(distance) * 0.08d);
        entityitem.field_70179_y = dz * 0.1d;
        world.func_72838_d(entityitem);
    }

    private ItemStack fishingItem(World world, EntityPlayer pleyer) {
        float f = world.field_73012_v.nextFloat();
        int i = EnchantmentHelper.func_151386_g(pleyer);
        int j = EnchantmentHelper.func_151387_h(pleyer);
        if (this.entityMomiji.fishingLv >= 10 && this.random.nextInt(100) == 0) {
            switch (this.random.nextInt(2)) {
                case 0:
                default:
                    return new ItemStack(Items.field_151045_i, 1, 0);
                case 1:
                    return new ItemStack(mod_ecru_MapleTree.Item_jewel, 1, 0);
            }
        }
        return FishingHooks.getRandomFishable(this.random, f, i, j);
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        List bList = new ArrayList();
        List wList = new ArrayList();
        for (int y = j - this.SCOPE_Y; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    ecru_EntityMomijiBlockPos py = checkBlock(x, y, z);
                    if (py != null) {
                        bList.add(py);
                        ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
                        bp.posX = x;
                        bp.posY = y;
                        bp.posZ = z;
                        wList.add(bp);
                    }
                }
            }
        }
        if (!bList.isEmpty()) {
            int li = this.entityMomiji.func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp2 = (ecru_EntityMomijiBlockPos) bList.get(li);
            ecru_EntityMomijiBlockPos wp = (ecru_EntityMomijiBlockPos) wList.get(li);
            this.xPosition = bp2.posX;
            this.yPosition = bp2.posY;
            this.zPosition = bp2.posZ;
            this.xPositionW = wp.posX;
            this.yPositionW = wp.posY;
            this.zPositionW = wp.posZ;
            return true;
        }
        return false;
    }

    private ecru_EntityMomijiBlockPos checkBlock(int x, int y, int z) {
        if (this.worldObject.func_147437_c(x, y, z)) {
            return null;
        }
        Block block = this.worldObject.func_147439_a(x, y, z);
        Block blockUp = this.worldObject.func_147439_a(x, y + 1, z);
        int meta = this.worldObject.func_72805_g(x, y, z);
        if (block == Blocks.field_150355_j && meta == 0 && blockUp == Blocks.field_150350_a) {
            ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
            bp.posX = x;
            bp.posY = y;
            bp.posZ = z;
            ecru_EntityMomijiBlockPos ppp = getSpace(bp);
            if (ppp != null) {
                return ppp;
            }
            return null;
        }
        return null;
    }

    private ecru_EntityMomijiBlockPos getSpace(ecru_EntityMomijiBlockPos pos) {
        int[] x = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] z = {-1, -1, -1, 0, 0, 1, 1, 1};
        List<Integer> spList = new ArrayList<>();
        ecru_EntityMomijiBlockPos ppp = new ecru_EntityMomijiBlockPos();
        for (int i = 0; i < 8; i++) {
            if (this.worldObject.func_147439_a(pos.posX + x[i], pos.posY + 1, pos.posZ + z[i]) == Blocks.field_150350_a && this.worldObject.func_147439_a(pos.posX + x[i], pos.posY, pos.posZ + z[i]).func_149721_r()) {
                spList.add(Integer.valueOf(i));
            }
        }
        if (spList.size() > 0) {
            int li = this.entityMomiji.func_70681_au().nextInt(spList.size());
            int num = spList.get(li).intValue();
            ppp.posX = pos.posX + x[num];
            ppp.posY = pos.posY + 1;
            ppp.posZ = pos.posZ + z[num];
            return ppp;
        }
        return null;
    }
}
