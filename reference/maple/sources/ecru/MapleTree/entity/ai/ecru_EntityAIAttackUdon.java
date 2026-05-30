package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.tile.ecru_TileEntityTeuchiUdon;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAIAttackUdon extends EntityAIBase {
    protected ecru_EntityMomiji entityMomiji;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;
    private World worldObject;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private int process;
    private int SCOPE_XZ = 10;
    private int SCOPE_Y = 3;
    private boolean forcedTermination = false;
    private int givenUpTimer = 0;
    private int jumpCoolTimeCount = 5;
    private int jumpCoolTime = this.jumpCoolTimeCount;
    private int cutCoolTimeCount = 10;
    private int cutCoolTime = this.cutCoolTimeCount;

    public ecru_EntityAIAttackUdon(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
        this.entityMomiji = pEntity;
        this.moveSpeed = pmoveSpeed;
        this.worldObject = pEntity.field_70170_p;
        func_75248_a(bit);
    }

    public boolean func_75250_a() {
        if (this.entityMomiji.isTamed() && !this.entityMomiji.isSitting() && this.entityMomiji.isFreedom() && this.entityMomiji.getAttackMode() == 1 && !this.entityMomiji.field_70128_L && !this.entityMomiji.func_70631_g_()) {
            int offsetX = this.entityMomiji.field_70165_t < 0.0d ? -1 : 0;
            int offsetZ = this.entityMomiji.field_70161_v < 0.0d ? -1 : 0;
            return getBlockList(this.entityMomiji.field_70170_p, ((int) this.entityMomiji.field_70165_t) + offsetX, (int) this.entityMomiji.field_70163_u, ((int) this.entityMomiji.field_70161_v) + offsetZ);
        }
        return false;
    }

    public void func_75249_e() {
        this.forcedTermination = false;
        this.givenUpTimer = 1200;
        if (this.process == 0) {
            this.entityMomiji.func_70661_as().func_75492_a(this.xPosition + 0.5d, this.yPosition + 1.0d, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
        } else {
            this.entityMomiji.func_70661_as().func_75492_a(this.xPosition + 0.5d, this.yPosition, this.zPosition + 0.5d, this.moveSpeed * this.entityMomiji.getStateBonusSpeed());
        }
    }

    public boolean func_75253_b() {
        Block block;
        if (this.forcedTermination) {
            return false;
        }
        int i = this.givenUpTimer - 1;
        this.givenUpTimer = i;
        if (i < 0 || (block = this.worldObject.func_147439_a((int) this.xPosition, (int) this.yPosition, (int) this.zPosition)) == null) {
            return false;
        }
        if (block == mod_ecru_MapleTree.blockTeuchiUdon || block == mod_ecru_MapleTree.blockTeuchiSoba) {
            TileEntity _tile = this.worldObject.func_147438_o((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
            if (_tile != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
                ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
                if (this.process == 1 && tile.getProcess() == 2) {
                    return false;
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public void func_75251_c() {
    }

    public void func_75246_d() {
        if ((this.process == 0 && this.entityMomiji.func_70011_f(this.xPosition + 0.5d, this.yPosition + 1.0d, this.zPosition + 0.5d) > 1.0d) || this.entityMomiji.func_70011_f(this.xPosition + 0.5d, this.yPosition + 0.5d, this.zPosition + 0.5d) > 2.0d) {
            return;
        }
        GameProfile gp = new GameProfile(UUID.randomUUID(), "attackUdon");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        ((EntityPlayer) fakePlayer).field_70165_t = this.entityMomiji.field_70165_t;
        ((EntityPlayer) fakePlayer).field_70163_u = this.entityMomiji.field_70163_u;
        ((EntityPlayer) fakePlayer).field_70161_v = this.entityMomiji.field_70161_v;
        int swordSlotNum = this.entityMomiji.getSword();
        if (swordSlotNum != -1) {
            int udon = this.entityMomiji.getTeuchi(1);
            if (this.process == -1 && udon != -1) {
                this.entityMomiji.func_70671_ap().func_75650_a(this.xPosition + 0.5d, this.yPosition + 0.3d, this.zPosition + 0.5d, 10.0f, this.entityMomiji.func_70646_bf());
                ItemStack is = this.entityMomiji.itemStacks[udon];
                ((EntityPlayer) fakePlayer).field_70177_z = this.entityMomiji.field_70177_z;
                if (is.func_77973_b().func_77648_a(is, fakePlayer, this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, 1, 0.0f, 0.0f, 0.0f)) {
                    this.entityMomiji.func_71038_i();
                    if (this.entityMomiji.func_70301_a(udon).field_77994_a <= 0) {
                        this.entityMomiji.func_70298_a(udon, 1);
                    }
                }
                this.forcedTermination = true;
                return;
            }
            int soba = this.entityMomiji.getTeuchi(2);
            if (this.process == -2 && soba != -1) {
                this.entityMomiji.func_70671_ap().func_75650_a(this.xPosition + 0.5d, this.yPosition + 0.3d, this.zPosition + 0.5d, 10.0f, this.entityMomiji.func_70646_bf());
                ItemStack is2 = this.entityMomiji.itemStacks[soba];
                ((EntityPlayer) fakePlayer).field_70177_z = this.entityMomiji.field_70177_z;
                if (is2.func_77973_b().func_77648_a(is2, fakePlayer, this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, 1, 0.0f, 0.0f, 0.0f)) {
                    this.entityMomiji.func_71038_i();
                    if (this.entityMomiji.func_70301_a(soba).field_77994_a <= 0) {
                        this.entityMomiji.func_70298_a(soba, 1);
                    }
                }
                this.forcedTermination = true;
                return;
            }
            if (this.process == 0) {
                TileEntity _tile = this.worldObject.func_147438_o((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
                if (_tile != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
                    ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
                    this.entityMomiji.func_70683_ar().func_75660_a();
                    tile.setStepFlg(true);
                    if (tile.getStepCounter() >= tile.getStepCounterMax()) {
                        this.forcedTermination = true;
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.process == 2) {
                this.entityMomiji.func_70671_ap().func_75650_a(this.xPosition + 0.5d, this.yPosition + 0.3d, this.zPosition + 0.5d, 10.0f, this.entityMomiji.func_70646_bf());
                this.worldObject.func_72956_a(this.entityMomiji, "mapletree:entity.item", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                Block b = this.worldObject.func_147439_a((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
                b.func_149749_a(this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, b, 1);
                this.worldObject.func_147475_p((int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
                this.worldObject.func_147449_b((int) this.xPosition, (int) this.yPosition, (int) this.zPosition, Blocks.field_150350_a);
                this.entityMomiji.func_71038_i();
                this.forcedTermination = true;
                return;
            }
            if (this.process == 1 && this.entityMomiji.itemStacks[swordSlotNum] != null) {
                this.entityMomiji.func_70671_ap().func_75650_a(this.xPosition + 0.5d, this.yPosition + 0.3d, this.zPosition + 0.5d, 10.0f, this.entityMomiji.func_70646_bf());
                ((EntityPlayer) fakePlayer).field_71071_by.field_70462_a[0] = this.entityMomiji.itemStacks[swordSlotNum];
                ((EntityPlayer) fakePlayer).field_71071_by.field_70461_c = 0;
                int i = this.cutCoolTime - 1;
                this.cutCoolTime = i;
                if (i < 0) {
                    this.worldObject.func_147439_a((int) this.xPosition, (int) this.yPosition, (int) this.zPosition).func_149699_a(this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition, fakePlayer);
                    this.entityMomiji.func_71038_i();
                    this.cutCoolTime = this.cutCoolTimeCount;
                }
                if (((EntityPlayer) fakePlayer).field_71071_by.field_70462_a[0] != null) {
                    this.entityMomiji.itemStacks[swordSlotNum] = ((EntityPlayer) fakePlayer).field_71071_by.field_70462_a[0];
                } else {
                    this.entityMomiji.itemStacks[swordSlotNum] = null;
                }
            }
        }
    }

    private boolean getBlockList(World world, int i, int j, int k) {
        List bList = new ArrayList();
        List bList_put_udon = new ArrayList();
        List bList_put_soba = new ArrayList();
        this.process = -2;
        for (int y = j - this.SCOPE_Y; y <= j + this.SCOPE_Y; y++) {
            for (int x = i - this.SCOPE_XZ; x <= i + this.SCOPE_XZ; x++) {
                for (int z = k - this.SCOPE_XZ; z <= k + this.SCOPE_XZ; z++) {
                    int py = checkBlock(x, y, z);
                    if (py == 1) {
                        ecru_EntityMomijiBlockPos bp = new ecru_EntityMomijiBlockPos();
                        bp.posX = x;
                        bp.posY = y;
                        bp.posZ = z;
                        bList.add(bp);
                    } else if (py == 2) {
                        ecru_EntityMomijiBlockPos bp2 = new ecru_EntityMomijiBlockPos();
                        bp2.posX = x;
                        bp2.posY = y;
                        bp2.posZ = z;
                        bList_put_udon.add(bp2);
                    } else if (py == 3) {
                        ecru_EntityMomijiBlockPos bp3 = new ecru_EntityMomijiBlockPos();
                        bp3.posX = x;
                        bp3.posY = y;
                        bp3.posZ = z;
                        bList_put_soba.add(bp3);
                    }
                }
            }
        }
        if (!bList_put_udon.isEmpty() && this.entityMomiji.getTeuchi(1) != -1) {
            int li = this.entityMomiji.func_70681_au().nextInt(bList_put_udon.size());
            ecru_EntityMomijiBlockPos bp4 = (ecru_EntityMomijiBlockPos) bList_put_udon.get(li);
            this.xPosition = bp4.posX;
            this.yPosition = bp4.posY;
            this.zPosition = bp4.posZ;
            this.process = -1;
            return true;
        }
        if (!bList_put_soba.isEmpty() && this.entityMomiji.getTeuchi(2) != -1) {
            int li2 = this.entityMomiji.func_70681_au().nextInt(bList_put_soba.size());
            ecru_EntityMomijiBlockPos bp5 = (ecru_EntityMomijiBlockPos) bList_put_soba.get(li2);
            this.xPosition = bp5.posX;
            this.yPosition = bp5.posY;
            this.zPosition = bp5.posZ;
            this.process = -2;
            return true;
        }
        if (!bList.isEmpty()) {
            int li3 = this.entityMomiji.func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp6 = (ecru_EntityMomijiBlockPos) bList.get(li3);
            if (ecru_util.canEntityItemBeSeen(this.entityMomiji, bp6)) {
                this.xPosition = bp6.posX;
                this.yPosition = bp6.posY;
                this.zPosition = bp6.posZ;
                TileEntity _tile = this.worldObject.func_147438_o(bp6.posX, bp6.posY, bp6.posZ);
                if (_tile != null && (_tile instanceof ecru_TileEntityTeuchiUdon)) {
                    ecru_TileEntityTeuchiUdon tile = (ecru_TileEntityTeuchiUdon) _tile;
                    this.process = tile.getProcess();
                }
                return true;
            }
        }
        return false;
    }

    private int checkBlock(int x, int y, int z) {
        if (this.worldObject.func_147437_c(x, y, z)) {
            return -1;
        }
        Block block = this.worldObject.func_147439_a(x, y, z);
        int meta = this.worldObject.func_72805_g(x, y, z);
        Block blockUp = this.worldObject.func_147439_a(x, y + 1, z);
        if (block == mod_ecru_MapleTree.blockTeuchiUdon || block == mod_ecru_MapleTree.blockTeuchiSoba) {
            return 1;
        }
        if (block == mod_ecru_MapleTree.blockDecoration3 && meta == 1 && blockUp == Blocks.field_150350_a) {
            return 2;
        }
        if (block == mod_ecru_MapleTree.blockDecoration1 && meta == 1 && blockUp == Blocks.field_150350_a) {
            return 3;
        }
        return 0;
    }
}
