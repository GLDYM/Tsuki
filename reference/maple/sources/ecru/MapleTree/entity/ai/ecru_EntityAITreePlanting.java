package ecru.MapleTree.entity.ai;

import com.mojang.authlib.GameProfile;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_cropHarvest;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.mod_ecru_MapleTree;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class ecru_EntityAITreePlanting extends EntityAIBase {
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
    private int SCOPE_XZ = 1;
    private int SCOPE_Y = 3;
    private int breakBlockCounter = 0;
    private int RANGE_BREAK_LEAVES = 3;

    public ecru_EntityAITreePlanting(ecru_EntityMomiji pEntity, float pmoveSpeed, int bit) {
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
            logBreak2(this.worldObject, (int) this.xPosition, (int) this.yPosition, (int) this.zPosition);
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

    private boolean checkWoodMeta(World world, int x, int y, int z, Block b, int meta) {
        if (world.func_147439_a(x, y, z) == b && world.func_72805_g(x, y, z) == meta) {
            return true;
        }
        return false;
    }

    private boolean logBreak2(World world, int i, int j, int k) {
        Block block = world.func_147439_a(i, j, k);
        int meta = world.func_72805_g(i, j, k) & 3;
        String target = Block.field_149771_c.func_148750_c(block);
        GameProfile gp = new GameProfile(UUID.randomUUID(), "momijiTreePlanting");
        FakePlayer fakePlayer = FakePlayerFactory.get(this.worldObject, gp);
        int num = mod_ecru_MapleTree.logAndSapling[0].getsapling(target, meta);
        int bigTree = -1;
        int[] iArr = {new int[]{1, 0, 1}, new int[]{-1, 0, -1}, new int[]{-1, 0, -1}, new int[]{1, 0, 1}};
        int[] iArr2 = {new int[]{0, 1, 1}, new int[]{0, 1, 1}, new int[]{0, -1, -1}, new int[]{0, -1, -1}};
        if (checkWoodMeta(world, i + 1, j, k, block, meta) && checkWoodMeta(world, i, j, k + 1, block, meta) && checkWoodMeta(world, i + 1, j, k + 1, block, meta)) {
            bigTree = 0;
        } else if (checkWoodMeta(world, i - 1, j, k, block, meta) && checkWoodMeta(world, i, j, k + 1, block, meta) && checkWoodMeta(world, i - 1, j, k + 1, block, meta)) {
            bigTree = 1;
        } else if (checkWoodMeta(world, i - 1, j, k, block, meta) && checkWoodMeta(world, i, j, k - 1, block, meta) && checkWoodMeta(world, i - 1, j, k - 1, block, meta)) {
            bigTree = 2;
        } else if (checkWoodMeta(world, i + 1, j, k, block, meta) && checkWoodMeta(world, i, j, k - 1, block, meta) && checkWoodMeta(world, i + 1, j, k - 1, block, meta)) {
            bigTree = 3;
        }
        this.entityMomiji.func_71038_i();
        this.breakBlockCounter = 0;
        breakBlock(world, i, j, k, block, meta, fakePlayer);
        int slot = -1;
        if (num >= 0) {
            slot = this.entityMomiji.getSaplingSlot(mod_ecru_MapleTree.logAndSapling[num].saplingNmae, mod_ecru_MapleTree.logAndSapling[num].saplingMeta);
            if (slot == -1) {
                slot = this.entityMomiji.getFirstSaplingSlot();
            }
        } else {
            int s = 0;
            while (true) {
                if (s >= mod_ecru_MapleTree.logId.size()) {
                    break;
                }
                if (!target.equals(mod_ecru_MapleTree.logId.get(s)) || meta != (mod_ecru_MapleTree.logMeta.get(s).intValue() & 3)) {
                    s++;
                } else {
                    slot = this.entityMomiji.getSaplingSlot(mod_ecru_MapleTree.saplingId.get(s), mod_ecru_MapleTree.saplingMeta.get(s).intValue());
                    break;
                }
            }
            if (slot == -1) {
                slot = this.entityMomiji.getFirstSaplingSlot();
            }
        }
        if (slot != -1) {
            ItemStack is = this.entityMomiji.itemStacks[slot];
            if (is.func_77973_b().func_77648_a(is, fakePlayer, this.worldObject, i, j - 1, k, 1, 0.0f, 0.0f, 0.0f) && this.entityMomiji.func_70301_a(slot).field_77994_a == 0) {
                this.entityMomiji.func_70298_a(slot, 1);
                if (bigTree != -1) {
                    int useItemSlot = getUseItemSlot(num, target, meta);
                    slot = useItemSlot;
                    if (useItemSlot != -1) {
                        is = this.entityMomiji.itemStacks[slot];
                    }
                }
            }
            if (bigTree != -1) {
                for (int t = 0; t < 3; t++) {
                    boolean ret = is.func_77973_b().func_77648_a(is, fakePlayer, this.worldObject, i + iArr[bigTree][t], j - 1, k + iArr2[bigTree][t], 1, 0.0f, 0.0f, 0.0f);
                    if (ret && this.entityMomiji.func_70301_a(slot).field_77994_a == 0) {
                        this.entityMomiji.func_70298_a(slot, 1);
                        int useItemSlot2 = getUseItemSlot(num, target, meta);
                        slot = useItemSlot2;
                        if (useItemSlot2 == -1) {
                            break;
                        }
                        is = this.entityMomiji.itemStacks[slot];
                    }
                }
            }
        }
        return true;
    }

    private int getUseItemSlot(int num, String target, int meta) {
        int slot = -1;
        if (num >= 0) {
            slot = this.entityMomiji.getSaplingSlot(mod_ecru_MapleTree.logAndSapling[num].saplingNmae, mod_ecru_MapleTree.logAndSapling[num].saplingMeta);
            if (slot == -1) {
                slot = this.entityMomiji.getFirstSaplingSlot();
            }
        } else {
            int s = 0;
            while (true) {
                if (s < mod_ecru_MapleTree.logId.size()) {
                    if (!target.equals(mod_ecru_MapleTree.logId.get(s)) || meta != (mod_ecru_MapleTree.logMeta.get(s).intValue() & 3)) {
                        s++;
                    } else {
                        slot = this.entityMomiji.getSaplingSlot(mod_ecru_MapleTree.saplingId.get(s), mod_ecru_MapleTree.saplingMeta.get(s).intValue());
                        break;
                    }
                } else {
                    break;
                }
            }
            if (slot == -1) {
                slot = this.entityMomiji.getFirstSaplingSlot();
            }
        }
        return slot;
    }

    private int breakBlock(World world, int i, int j, int k, Block block, int meta, EntityPlayer player) {
        boolean flg = false;
        if (block == mod_ecru_MapleTree.blockMapleWood || block == mod_ecru_MapleTree.blockMapleWoodSyrup) {
            if ((world.func_147439_a(i, j, k) == mod_ecru_MapleTree.blockMapleWood && (world.func_72805_g(i, j, k) & 3) == meta) || world.func_147439_a(i, j, k) == mod_ecru_MapleTree.blockMapleWoodSyrup) {
                flg = true;
            }
        } else if (world.func_147439_a(i, j, k) == block && (world.func_72805_g(i, j, k) & 3) == meta) {
            flg = true;
        }
        if (flg) {
            if (axeConsume()) {
                Block b = this.worldObject.func_147439_a(i, j, k);
                ecru_cropHarvest ch = new ecru_cropHarvest(this.worldObject, b, i, j, k);
                ch.harvestBlock();
                if (mod_ecru_MapleTree.harvestLeaves) {
                    breakLeaves(world, i, j, k, player);
                }
                int i2 = this.breakBlockCounter + 1;
                this.breakBlockCounter = i2;
                if (i2 >= mod_ecru_MapleTree.LogBlockFellingMAX) {
                    return -1;
                }
            } else {
                return -1;
            }
        }
        for (int y = j - 1; y <= j + 1; y++) {
            for (int x = i - 1; x <= i + 1; x++) {
                for (int z = k - 1; z <= k + 1; z++) {
                    if (x != i || y != j || z != k) {
                        boolean flg2 = false;
                        if (block == mod_ecru_MapleTree.blockMapleWood || block == mod_ecru_MapleTree.blockMapleWoodSyrup) {
                            if ((world.func_147439_a(x, y, z) == mod_ecru_MapleTree.blockMapleWood && (world.func_72805_g(x, y, z) & 3) == meta) || world.func_147439_a(x, y, z) == mod_ecru_MapleTree.blockMapleWoodSyrup) {
                                flg2 = true;
                            }
                        } else if (world.func_147439_a(x, y, z) == block && (world.func_72805_g(x, y, z) & 3) == meta) {
                            flg2 = true;
                        }
                        if (flg2 && this.breakBlockCounter < mod_ecru_MapleTree.LogBlockFellingMAX && breakBlock(world, x, y, z, block, meta, player) == -1) {
                            return -1;
                        }
                    }
                }
            }
        }
        return this.breakBlockCounter;
    }

    private void breakLeaves(World world, int i, int j, int k, EntityPlayer player) {
        Block b;
        for (int y = j - this.RANGE_BREAK_LEAVES; y <= j + this.RANGE_BREAK_LEAVES; y++) {
            for (int x = i - this.RANGE_BREAK_LEAVES; x <= i + this.RANGE_BREAK_LEAVES; x++) {
                for (int z = k - this.RANGE_BREAK_LEAVES; z <= k + this.RANGE_BREAK_LEAVES; z++) {
                    if ((x != i || y != j || z != k) && (((b = world.func_147439_a(x, y, z)) != Blocks.field_150350_a && (b.isLeaves(world, x, y, z) || (b instanceof BlockLeaves))) || b.func_149688_o() == Material.field_151584_j)) {
                        int meta = world.func_72805_g(x, y, z);
                        if (world.func_147449_b(x, y, z, Blocks.field_150350_a)) {
                            b.func_149636_a(world, player, x, y, z, meta);
                        }
                    }
                }
            }
        }
    }

    private boolean axeConsume() {
        int axeSlot = this.entityMomiji.getAxe();
        if (axeSlot == -1) {
            if (mod_ecru_MapleTree.momijiCutAllMode == 1) {
                return false;
            }
            return true;
        }
        GameProfile gp = new GameProfile(UUID.randomUUID(), "momijiTreePlanting");
        this.entityMomiji.itemStacks[axeSlot].func_77972_a(1, FakePlayerFactory.get(this.worldObject, gp));
        if (this.entityMomiji.itemStacks[axeSlot].field_77994_a == 0) {
            this.entityMomiji.func_70298_a(axeSlot, 1);
        }
        return true;
    }
}
