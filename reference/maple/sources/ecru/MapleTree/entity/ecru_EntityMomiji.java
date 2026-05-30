package ecru.MapleTree.entity;

import com.google.common.collect.Multimap;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ecru.MapleTree.client.ecru_EntitySporeFX;
import ecru.MapleTree.entity.ai.ecru_EntityAIAttackUdon;
import ecru.MapleTree.entity.ai.ecru_EntityAIBeg;
import ecru.MapleTree.entity.ai.ecru_EntityAIChestMove;
import ecru.MapleTree.entity.ai.ecru_EntityAICollect;
import ecru.MapleTree.entity.ai.ecru_EntityAICowsMilk;
import ecru.MapleTree.entity.ai.ecru_EntityAICropsHarvest;
import ecru.MapleTree.entity.ai.ecru_EntityAICropsMove;
import ecru.MapleTree.entity.ai.ecru_EntityAIFarmlandMove;
import ecru.MapleTree.entity.ai.ecru_EntityAIFishing;
import ecru.MapleTree.entity.ai.ecru_EntityAIFollowOwner;
import ecru.MapleTree.entity.ai.ecru_EntityAIGetChestItems;
import ecru.MapleTree.entity.ai.ecru_EntityAIHarvest;
import ecru.MapleTree.entity.ai.ecru_EntityAIHarvestMove;
import ecru.MapleTree.entity.ai.ecru_EntityAIJungleLogMove;
import ecru.MapleTree.entity.ai.ecru_EntityAIOwnerHurtByTarget;
import ecru.MapleTree.entity.ai.ecru_EntityAIOwnerHurtTarget;
import ecru.MapleTree.entity.ai.ecru_EntityAIPamCropsHarvest;
import ecru.MapleTree.entity.ai.ecru_EntityAIPamCropsMove;
import ecru.MapleTree.entity.ai.ecru_EntityAIPamFruitHarvest;
import ecru.MapleTree.entity.ai.ecru_EntityAIPamFruitMove;
import ecru.MapleTree.entity.ai.ecru_EntityAIPlantCacao;
import ecru.MapleTree.entity.ai.ecru_EntityAIShearWool;
import ecru.MapleTree.entity.ai.ecru_EntityAISowSeeds;
import ecru.MapleTree.entity.ai.ecru_EntityAITreeMove;
import ecru.MapleTree.entity.ai.ecru_EntityAITreePlanting;
import ecru.MapleTree.entity.ai.ecru_EntityAIWander;
import ecru.MapleTree.entity.ai.ecru_EntityAIWatchClosest;
import ecru.MapleTree.entity.common.ecru_EntityMomijiBlockPos;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.item.ecru_ItemMomijiShears;
import ecru.MapleTree.mod_ecru_MapleTree;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.network.packet.ecru_PacketMomiji;
import ecru.MapleTree.network.packet.ecru_PacketMomijiBootProcessing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ecru_EntityMomiji extends ecru_EntityMomijiInventory {
    public boolean field_82175_bq;
    public float field_70732_aI;
    public float field_70733_aJ;
    public int field_110158_av;
    public float attackDamage;
    public boolean attackModeHarvest;
    private int firstBoot;
    public int testParaX;
    public int testParaY;
    public int testParaZ;
    public int testParaT;
    private float field_70926_e;
    private float field_70924_f;
    private int count;
    private int voiceCoolTime;
    public int motion;
    private boolean isFreedom;
    private boolean wanderFlg;
    public int MAXIMUM_HOME_DISTANCE;
    private ChunkCoordinates spawnPosition;
    public HashMap<String, Integer> useChest;
    public HashMap<String, Integer> useChest_key;
    public final float[] stateBonusAttackData;
    public final float[] stateBonusDefenseData;
    public final float[] stateBonusSpeedData;
    public final int[] nextExpData;
    public int expPoint;
    public int stateBonusAttackLv;
    public int stateBonusDefenseLv;
    public int stateBonusSpeedLv;
    public final int stateBonusLvMax = 40;
    public int momijiLv;
    public final int momijiLvMax = 100;
    public int bonusPoint;
    public boolean momijiHarvesting;
    public int fishingLv;
    public int fishingCount;
    private int[] soundAge;
    private TileEntityChest touchChest;
    private TileEntityChest touchChest_takeOut;
    private int CHEST_ACCESS_FLG_COUNTER;
    private Random random;
    private List checkChest;
    private List checkLog;
    private final int CHEST_GET_ITEM_NUM = 3;
    private List[] checkChest_takeOut;
    public ItemStack[] chestItem;
    private int chestCoolTime;
    private int toleranceRing;

    public List getCheckChest() {
        return this.checkChest;
    }

    public ecru_EntityMomiji(World p_i1696_1_) {
        super(p_i1696_1_);
        this.attackDamage = 0.0f;
        this.attackModeHarvest = true;
        this.firstBoot = 1;
        this.testParaX = 40;
        this.testParaY = 140;
        this.testParaZ = 190;
        this.testParaT = 10;
        this.count = 0;
        this.voiceCoolTime = 3;
        this.isFreedom = false;
        this.wanderFlg = true;
        this.MAXIMUM_HOME_DISTANCE = mod_ecru_MapleTree.momijiActivityField;
        this.useChest = new HashMap<>();
        this.useChest_key = new HashMap<>();
        this.stateBonusAttackData = new float[]{1.0f, 1.0f, 1.2f, 1.4f, 1.6f, 1.9f, 2.3f, 2.7f, 3.0f, 3.3f, 3.9f, 4.2f, 4.4f, 4.6f, 4.8f, 5.0f, 5.3f, 5.6f, 6.0f, 6.4f, 7.2f, 7.8f, 8.8f, 9.8f, 11.0f, 12.0f, 13.0f, 14.0f, 15.5f, 17.0f, 18.5f, 20.0f, 21.0f, 22.5f, 23.0f, 24.0f, 25.0f, 26.0f, 28.0f, 30.0f, 34.0f, 34.0f};
        this.stateBonusDefenseData = new float[]{1.0f, 1.0f, 0.99f, 0.98f, 0.97f, 0.96f, 0.95f, 0.94f, 0.93f, 0.92f, 0.91f, 0.9f, 0.88f, 0.86f, 0.84f, 0.82f, 0.8f, 0.78f, 0.76f, 0.74f, 0.72f, 0.7f, 0.65f, 0.61f, 0.57f, 0.53f, 0.49f, 0.41f, 0.33f, 0.3f, 0.25f, 0.25f, 0.21f, 0.18f, 0.15f, 0.11f, 0.07f, 0.05f, 0.03f, 0.02f, 0.0f, 0.0f};
        this.stateBonusSpeedData = new float[]{1.0f, 1.0f, 1.032f, 1.048f, 1.064f, 1.08f, 1.096f, 1.112f, 1.128f, 1.144f, 1.16f, 1.176f, 1.192f, 1.208f, 1.224f, 1.24f, 1.256f, 1.272f, 1.288f, 1.304f, 1.32f, 1.336f, 1.352f, 1.368f, 1.384f, 1.4f, 1.416f, 1.432f, 1.448f, 1.464f, 1.5f, 1.502f, 1.504f, 1.506f, 1.508f, 1.51f, 1.512f, 1.514f, 1.518f, 1.52f, 1.522f, 1.522f};
        this.nextExpData = new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 40, 40, 40, 40, 45, 45, 45, 45, 50, 60, 60, 60, 70, 80, 80, 90, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 110, 110, 110, 110, 110, 120, 120, 120, 120, 120, 120, 120, 130, 130, 130, 130, 130, 130, 130, 130, 140, 140, 140, 150, 150, 150, 150, 160, 160, 160, 170, 170, 170, 180, 180, 190, 200, 400, 800, 800, 400};
        this.expPoint = 0;
        this.stateBonusAttackLv = 0;
        this.stateBonusDefenseLv = 0;
        this.stateBonusSpeedLv = 0;
        this.stateBonusLvMax = 40;
        this.momijiLv = 0;
        this.momijiLvMax = 100;
        this.bonusPoint = 0;
        this.momijiHarvesting = false;
        this.fishingLv = 0;
        this.fishingCount = 0;
        this.soundAge = new int[8];
        this.touchChest = null;
        this.touchChest_takeOut = null;
        this.CHEST_ACCESS_FLG_COUNTER = 12000;
        this.random = new Random();
        this.checkChest = new ArrayList();
        this.checkLog = new ArrayList();
        this.CHEST_GET_ITEM_NUM = 3;
        this.checkChest_takeOut = new ArrayList[3];
        this.chestItem = new ItemStack[3];
        this.chestCoolTime = 0;
        this.toleranceRing = 0;
        func_70105_a(0.8f, 0.98f);
        func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, this.aiSit);
        this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.1f));
        this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0d, true));
        this.field_70714_bg.func_75776_a(5, new ecru_EntityAIFollowOwner(this, 1.0d, 10.0f, 2.0f, 3));
        this.field_70714_bg.func_75776_a(6, new EntityAIMate(this, 1.0d));
        this.field_70714_bg.func_75776_a(7, new ecru_EntityAIWander(this, 1.0d, 1));
        this.field_70714_bg.func_75776_a(8, new ecru_EntityAIBeg(this, 8.0f, 2));
        this.field_70714_bg.func_75776_a(8, new ecru_EntityAIChestMove(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAIWatchClosest(this, EntityPlayer.class, 8.0f, 2));
        this.field_70714_bg.func_75776_a(9, new EntityAILookIdle(this));
        this.field_70714_bg.func_75776_a(10, new ecru_EntityAICollect(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAIHarvest(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAISowSeeds(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAICropsHarvest(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAIPlantCacao(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAITreePlanting(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(11, new ecru_EntityAICropsMove(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(11, new ecru_EntityAIHarvestMove(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(11, new ecru_EntityAIJungleLogMove(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(11, new ecru_EntityAITreeMove(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(11, new ecru_EntityAIFarmlandMove(this, 1.0f, 3));
        if (mod_ecru_MapleTree.modAdd_harvestcraft) {
            this.field_70714_bg.func_75776_a(9, new ecru_EntityAIPamCropsHarvest(this, 1.0f, 3));
            this.field_70714_bg.func_75776_a(9, new ecru_EntityAIPamFruitHarvest(this, 1.0f, 3));
            this.field_70714_bg.func_75776_a(11, new ecru_EntityAIPamCropsMove(this, 1.0f, 3));
            this.field_70714_bg.func_75776_a(11, new ecru_EntityAIPamFruitMove(this, 1.0f, 3));
        }
        this.field_70714_bg.func_75776_a(10, new ecru_EntityAIAttackUdon(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAIGetChestItems(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(10, new ecru_EntityAIFishing(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAIShearWool(this, 1.0f, 3));
        this.field_70714_bg.func_75776_a(9, new ecru_EntityAICowsMilk(this, 1.0f, 3));
        this.field_70715_bh.func_75776_a(1, new ecru_EntityAIOwnerHurtByTarget(this, 1));
        this.field_70715_bh.func_75776_a(2, new ecru_EntityAIOwnerHurtTarget(this, 1));
        this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true));
        setTamed(false);
        setTextureNum(this.random.nextInt(4));
        if (this.random.nextInt(50) == 0) {
            func_70299_a(0, new ItemStack(Items.field_151041_m, 1, 0));
            setAttackMode(1);
        }
        for (int i = 0; i < this.checkChest_takeOut.length; i++) {
            this.checkChest_takeOut[i] = new ArrayList();
        }
        this.chestItem[0] = new ItemStack(Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiUdon), 1, 0);
        this.chestItem[1] = new ItemStack(Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiSoba), 1, 0);
        this.chestItem[2] = new ItemStack(Items.field_151133_ar, 1, 0);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896d);
        if (isTamed()) {
            func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0d);
        } else {
            func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0d);
        }
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70069_a(float p_70069_1_) {
    }

    public void func_70624_b(EntityLivingBase p_70624_1_) {
        if (!(p_70624_1_ instanceof EntityPlayer)) {
            super.func_70624_b(p_70624_1_);
        }
    }

    protected void func_70629_bd() {
        this.field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, new Float(func_110143_aJ()));
        this.field_70180_af.func_75682_a(19, new Byte((byte) 0));
        this.field_70180_af.func_75682_a(20, new Byte((byte) BlockColored.func_150032_b(1)));
        this.field_70180_af.func_75682_a(21, new Byte((byte) 0));
        this.field_70180_af.func_75682_a(22, new String(""));
        this.field_70180_af.func_75682_a(23, new Byte((byte) 0));
        this.field_70180_af.func_75682_a(24, new Byte((byte) 30));
        this.field_70180_af.func_75682_a(25, new Byte((byte) 30));
        this.field_70180_af.func_75682_a(26, new Byte((byte) 30));
        this.field_70180_af.func_75682_a(28, new Byte((byte) 0));
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
    }

    @Override
    public void func_70014_b(NBTTagCompound par1nbtTagCompound) {
        super.func_70014_b(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a("homeX", func_110172_bL().field_71574_a);
        par1nbtTagCompound.func_74768_a("homeY", func_110172_bL().field_71572_b);
        par1nbtTagCompound.func_74768_a("homeZ", func_110172_bL().field_71573_c);
        par1nbtTagCompound.func_74757_a("Freedom", isFreedom());
        NBTTagList itemsTagList = new NBTTagList();
        byte b = 0;
        while (true) {
            byte slotIndex = b;
            if (slotIndex < this.itemStacks.length) {
                if (this.itemStacks[slotIndex] != null) {
                    NBTTagCompound itemTagCompound = new NBTTagCompound();
                    itemTagCompound.func_74774_a("Slot", slotIndex);
                    this.itemStacks[slotIndex].func_77955_b(itemTagCompound);
                    itemsTagList.func_74742_a(itemTagCompound);
                }
                b = (byte) (slotIndex + 1);
            } else {
                par1nbtTagCompound.func_74782_a("Items", itemsTagList);
                par1nbtTagCompound.func_74778_a("Owner", getOwnerName());
                par1nbtTagCompound.func_74768_a("textureNum", getTextureNum());
                par1nbtTagCompound.func_74768_a("stateBonusAttackLv", this.stateBonusAttackLv);
                par1nbtTagCompound.func_74768_a("stateBonusDefenseLv", this.stateBonusDefenseLv);
                par1nbtTagCompound.func_74768_a("stateBonusSpeedLv", this.stateBonusSpeedLv);
                par1nbtTagCompound.func_74768_a("stateBonusAttackLvCap", getStateBonusAttackLvCap());
                par1nbtTagCompound.func_74768_a("stateBonusDefenseLvCap", getStateBonusDefenseLvCap());
                par1nbtTagCompound.func_74768_a("stateBonusSpeedLvCap", getStateBonusSpeedLvCap());
                par1nbtTagCompound.func_74768_a("momijiLv", this.momijiLv);
                par1nbtTagCompound.func_74768_a("bonusPoint", this.bonusPoint);
                par1nbtTagCompound.func_74768_a("expPoint", this.expPoint);
                par1nbtTagCompound.func_74768_a("attackMode", getAttackMode());
                par1nbtTagCompound.func_74768_a("fishingLv", this.fishingLv);
                par1nbtTagCompound.func_74768_a("fishingCount", this.fishingCount);
                return;
            }
        }
    }

    @Override
    public void func_70037_a(NBTTagCompound par1nbtTagCompound) {
        super.func_70037_a(par1nbtTagCompound);
        int hx = par1nbtTagCompound.func_74762_e("homeX");
        int hy = par1nbtTagCompound.func_74762_e("homeY");
        int hz = par1nbtTagCompound.func_74762_e("homeZ");
        func_110172_bL().func_71571_b(hx, hy, hz);
        setFreedom(par1nbtTagCompound.func_74767_n("Freedom"));
        NBTTagList itemsTagList = par1nbtTagCompound.func_150295_c("Items", 10);
        this.itemStacks = new ItemStack[func_70302_i_()];
        for (int tagCounter = 0; tagCounter < itemsTagList.func_74745_c(); tagCounter++) {
            NBTTagCompound itemTagCompound = itemsTagList.func_150305_b(tagCounter);
            int slotIndex = itemTagCompound.func_74771_c("Slot") & 255;
            if (slotIndex >= 0 && slotIndex < this.itemStacks.length) {
                this.itemStacks[slotIndex] = ItemStack.func_77949_a(itemTagCompound);
            }
        }
        String name = par1nbtTagCompound.func_74779_i("Owner");
        setOwnerName(name);
        setTextureNum(par1nbtTagCompound.func_74762_e("textureNum"));
        this.stateBonusAttackLv = par1nbtTagCompound.func_74762_e("stateBonusAttackLv");
        this.stateBonusDefenseLv = par1nbtTagCompound.func_74762_e("stateBonusDefenseLv");
        this.stateBonusSpeedLv = par1nbtTagCompound.func_74762_e("stateBonusSpeedLv");
        if (par1nbtTagCompound.func_74762_e("stateBonusAttackLvCap") == 0) {
            setStateBonusAttackLvCap(30);
        } else {
            setStateBonusAttackLvCap(par1nbtTagCompound.func_74762_e("stateBonusAttackLvCap"));
        }
        if (par1nbtTagCompound.func_74762_e("stateBonusDefenseLvCap") == 0) {
            setStateBonusDefenseLvCap(30);
        } else {
            setStateBonusDefenseLvCap(par1nbtTagCompound.func_74762_e("stateBonusDefenseLvCap"));
        }
        if (par1nbtTagCompound.func_74762_e("stateBonusSpeedLvCap") == 0) {
            setStateBonusSpeedLvCap(30);
        } else {
            setStateBonusSpeedLvCap(par1nbtTagCompound.func_74762_e("stateBonusSpeedLvCap"));
        }
        this.momijiLv = par1nbtTagCompound.func_74762_e("momijiLv");
        this.bonusPoint = par1nbtTagCompound.func_74762_e("bonusPoint");
        this.expPoint = par1nbtTagCompound.func_74762_e("expPoint");
        setAttackMode(par1nbtTagCompound.func_74762_e("attackMode"));
        this.fishingLv = par1nbtTagCompound.func_74762_e("fishingLv");
        this.fishingLv = this.fishingLv < 0 ? 0 : this.fishingLv;
        this.fishingLv = this.fishingLv > 10 ? 10 : this.fishingLv;
        this.fishingCount = par1nbtTagCompound.func_74762_e("fishingCount");
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return null;
    }

    protected String func_70673_aS() {
        return null;
    }

    protected float func_70599_aP() {
        return mod_ecru_MapleTree.MomijiSounds;
    }

    protected Item func_146068_u() {
        return Item.func_150899_d(-1);
    }

    @SideOnly(Side.CLIENT)
    private void displayParticle() {
        double x = this.field_70165_t;
        double y = this.field_70163_u;
        double z = this.field_70161_v;
        ecru_EntitySporeFX entityFX = new ecru_EntitySporeFX(this.field_70170_p, x, y + 0.5d, z, 0.0d, 0.0d, 0.0d);
        entityFX.func_110125_a(mod_ecru_MapleTree.Item_particle.func_77617_a(10));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a(entityFX);
    }

    public void func_70636_d() {
        super.func_70636_d();
        func_82168_bl();
        if (isSitting() && func_110143_aJ() <= 10.0f && this.field_70170_p.field_73012_v.nextInt(70) == 0 && this.field_70170_p.field_72995_K) {
            displayParticle();
        }
        if (func_110143_aJ() < func_110138_aP()) {
            eat();
        }
        if (!this.field_70122_E && this.field_70181_x < 0.0d) {
            if (getAttackMode() == 0 || getAttackMode() == 3) {
                this.field_70181_x *= 0.6d;
            } else {
                this.field_70181_x *= 0.92d;
            }
        }
        if (this.field_70171_ac && this.field_70181_x < 0.0d) {
            this.field_70181_x = 0.1d;
        }
        if (this.field_70146_Z.nextInt(1200) == 0) {
            this.field_70170_p.func_72956_a(this, "mapletree:entity.idling", mod_ecru_MapleTree.MomijiSounds, 1.0f);
        }
        if (this.field_70173_aa % this.CHEST_ACCESS_FLG_COUNTER == 0) {
            this.checkChest.clear();
            for (int i = 0; i < this.checkChest_takeOut.length; i++) {
                this.checkChest_takeOut[i].clear();
            }
        }
        if (this.field_70173_aa % (this.CHEST_ACCESS_FLG_COUNTER / 2) == 0) {
            this.checkLog.clear();
        }
        levelUpCheck();
        if (!this.field_70170_p.field_72995_K && func_110143_aJ() > 0.0f) {
            List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(1.3d, 2.0d, 1.3d));
            if (list != null) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    EntityItem entityItem = (Entity) list.get(i2);
                    if (!((Entity) entityItem).field_70128_L && (entityItem instanceof EntityItem) && entityItem.field_70292_b >= 30) {
                        if (((entityItem.func_92059_d().func_77973_b() instanceof ItemShears) || (entityItem.func_92059_d().func_77973_b() instanceof ItemHoe) || entityItem.func_92059_d().func_77973_b() == Items.field_151111_aL || (entityItem.func_92059_d().func_77973_b() instanceof ItemFishingRod)) && itemsAreCollectedShears(entityItem)) {
                            if (soundCoolTime(0)) {
                                this.field_70170_p.func_72956_a(this, "mapletree:entity.item", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                            }
                        } else if (itemsAreCollected(entityItem) && soundCoolTime(0)) {
                            this.field_70170_p.func_72956_a(this, "mapletree:entity.item", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                        }
                    }
                }
                func_70784_b(null);
            }
            if (!this.field_70170_p.field_72995_K && !isSitting() && isTamed()) {
                if (this.touchChest != null) {
                    this.touchChest.func_70305_f();
                    this.touchChest = null;
                    return;
                }
                chestMode();
                if (this.touchChest_takeOut != null) {
                    if (this.chestCoolTime <= 0) {
                        this.touchChest_takeOut.func_70305_f();
                        this.touchChest_takeOut = null;
                    } else {
                        this.chestCoolTime--;
                    }
                }
            }
        }
    }

    private double getDistanceSqToBlock(TileEntity par1) {
        double d0 = this.field_70165_t - par1.field_145851_c;
        double d1 = this.field_70163_u - par1.field_145848_d;
        double d2 = this.field_70161_v - par1.field_145849_e;
        return (d0 * d0) + (d1 * d1) + (d2 * d2);
    }

    private void chestMode() {
        if (getInventoryFull() == -1) {
            putChestMode();
        }
    }

    private void putChestMode() {
        int seedSlotNum = getSeedSlotNum();
        List bList = new ArrayList();
        int i = (int) this.field_70165_t;
        int j = (int) this.field_70163_u;
        int k = (int) this.field_70161_v;
        for (int y = j - 1; y <= j + 3; y++) {
            for (int x = i - 2; x <= i + 2; x++) {
                for (int z = k - 2; z <= k + 2; z++) {
                    if (checkBlock(this.field_70170_p, x, y, z)) {
                        ecru_EntityMomijiBlockPos _bp = new ecru_EntityMomijiBlockPos();
                        _bp.posX = x;
                        _bp.posY = y;
                        _bp.posZ = z;
                        bList.add(_bp);
                    }
                }
            }
        }
        if (!bList.isEmpty()) {
            int li = func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp = (ecru_EntityMomijiBlockPos) bList.get(li);
            if (ecru_util.canEntityItemBeSeen(this, bp) && isTamed() && !isSitting()) {
                TileEntity ti = this.field_70170_p.func_147438_o(bp.posX, bp.posY, bp.posZ);
                if (ti instanceof TileEntityChest) {
                    this.touchChest = this.field_70170_p.func_147438_o(bp.posX, bp.posY, bp.posZ);
                    if (this.checkChest.contains(this.touchChest)) {
                        this.touchChest = null;
                        return;
                    }
                    if (this.touchChest == null) {
                        return;
                    }
                    int putCount = 0;
                    this.touchChest.func_70295_k_();
                    for (int ll = 20; ll > 2; ll--) {
                        boolean chkSeeds = thisSeeds(ll) || thisSapling(ll) || notPutItem(ll);
                        if (seedSlotNum > 9 && chkSeeds) {
                            chkSeeds = false;
                            seedSlotNum--;
                        }
                        if (!chkSeeds && this.itemStacks[ll].func_77973_b() != mod_ecru_MapleTree.Item_mapleSyrup) {
                            int num = putChestItem(this.itemStacks[ll], this.touchChest.func_70302_i_(), this.touchChest);
                            if (num > 0) {
                                this.itemStacks[ll].field_77994_a = num;
                            } else {
                                putCount++;
                                this.itemStacks[ll] = null;
                            }
                        }
                    }
                    if (putCount == 0) {
                        if (soundCoolTime(1)) {
                            this.field_70170_p.func_72956_a(this, "mapletree:entity.chest_full", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                        }
                        deleteUseChestList(bp.posX, bp.posY, bp.posZ);
                        this.checkChest.add(this.touchChest);
                        return;
                    }
                    addUseChestList(bp.posX, bp.posY, bp.posZ);
                    this.checkChest.clear();
                }
            }
        }
    }

    private boolean addUseChestList(int i, int j, int k) {
        if (this.useChest_key.size() > 20) {
            return false;
        }
        String key = getStr(i) + getStr(j) + getStr(k);
        if (!this.useChest_key.containsKey(key)) {
            this.useChest_key.put(key, 1);
            this.useChest.put(String.valueOf(key) + "-x", Integer.valueOf(i));
            this.useChest.put(String.valueOf(key) + "-y", Integer.valueOf(j));
            this.useChest.put(String.valueOf(key) + "-z", Integer.valueOf(k));
            return true;
        }
        return false;
    }

    public boolean deleteUseChestList(int i, int j, int k) {
        String key = getStr(i) + getStr(j) + getStr(k);
        if (this.useChest_key.containsKey(key)) {
            this.useChest_key.remove(key);
            this.useChest.remove(key + "-x");
            this.useChest.remove(key + "-y");
            this.useChest.remove(key + "-z");
            return true;
        }
        return false;
    }

    private int putChestItem(ItemStack it, int iSize, IInventory Chest) {
        for (int inv = 0; inv < iSize; inv++) {
            if (Chest.func_70301_a(inv) != null && Chest.func_70301_a(inv).func_77973_b() == it.func_77973_b() && Chest.func_70301_a(inv).func_77960_j() == it.func_77960_j()) {
                if (Chest.func_70301_a(inv).func_77976_d() == Chest.func_70301_a(inv).field_77994_a) {
                    continue;
                } else {
                    if (Chest.func_70301_a(inv).func_77976_d() - Chest.func_70301_a(inv).field_77994_a >= it.field_77994_a) {
                        Chest.func_70301_a(inv).func_77979_a(-it.field_77994_a);
                        return 0;
                    }
                    int add = Chest.func_70301_a(inv).func_77976_d() - Chest.func_70301_a(inv).field_77994_a;
                    int num = it.field_77994_a - (Chest.func_70301_a(inv).func_77976_d() - Chest.func_70301_a(inv).field_77994_a);
                    Chest.func_70301_a(inv).func_77979_a(-add);
                    it.field_77994_a = num;
                }
            } else if (Chest.func_70301_a(inv) == null) {
                Chest.func_70299_a(inv, it);
                return 0;
            }
        }
        return it.field_77994_a;
    }

    private boolean checkBlock(World world, int x, int y, int z) {
        Block tergetBlock = Blocks.field_150486_ae;
        if (world.func_147439_a(x, y, z) == tergetBlock) {
            return true;
        }
        return false;
    }

    private boolean itemsAreCollectedShears(Entity ei) {
        int s = getShearsEmptySlot();
        if (s != -1) {
            ItemStack it = ((EntityItem) ei).func_92059_d();
            func_70299_a(s, it);
            ei.func_70106_y();
            this.field_70170_p.func_72956_a(this, "random.pop", 0.2f, (((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
            return true;
        }
        return false;
    }

    private boolean itemsAreCollected(Entity ei) {
        boolean flg = false;
        ItemStack it = ((EntityItem) ei).func_92059_d();
        int inv = 3;
        while (true) {
            if (inv >= 21) {
                break;
            }
            if (func_70301_a(inv) != null && func_70301_a(inv).func_77973_b() == it.func_77973_b() && func_70301_a(inv).func_77960_j() == it.func_77960_j()) {
                if (func_70301_a(inv).func_77976_d() == func_70301_a(inv).field_77994_a) {
                    continue;
                } else {
                    if (func_70301_a(inv).func_77976_d() - func_70301_a(inv).field_77994_a >= it.field_77994_a) {
                        ei.func_70106_y();
                        func_70301_a(inv).func_77979_a(-it.field_77994_a);
                        flg = true;
                        this.field_70170_p.func_72956_a(this, "random.pop", 0.2f, (((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
                        break;
                    }
                    int add = func_70301_a(inv).func_77976_d() - func_70301_a(inv).field_77994_a;
                    int num = it.field_77994_a - (func_70301_a(inv).func_77976_d() - func_70301_a(inv).field_77994_a);
                    func_70301_a(inv).func_77979_a(-add);
                    ((EntityItem) ei).func_92058_a(new ItemStack(it.func_77973_b(), num, it.func_77960_j()));
                    flg = true;
                    this.field_70170_p.func_72956_a(this, "random.pop", 0.2f, (((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
                    if (((EntityItem) ei).func_92059_d().field_77994_a <= 0) {
                        ei.func_70106_y();
                        break;
                    }
                    it = ((EntityItem) ei).func_92059_d();
                }
                inv++;
            } else if (func_70301_a(inv) != null) {
                inv++;
            } else {
                ei.func_70106_y();
                func_70299_a(inv, it);
                flg = true;
                this.field_70170_p.func_72956_a(this, "random.pop", 0.2f, (((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
                break;
            }
        }
        return flg;
    }

    @SideOnly(Side.CLIENT)
    private void onUpdate_client() {
        ecru_PacketHandler.network.sendToServer(new ecru_PacketMomijiBootProcessing(func_145782_y(), Minecraft.func_71410_x().field_71439_g.func_145782_y()));
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70732_aI = this.field_70733_aJ;
        func_110159_bB();
        if (this.field_70170_p.field_72995_K && this.firstBoot == 1) {
            this.firstBoot = 0;
            onUpdate_client();
        }
        if (!isSitting()) {
            int i = this.motion + 5;
            this.motion = i;
            if (i >= 360) {
                this.motion = 0;
            }
        } else {
            this.motion = 0;
        }
        if (!this.field_70170_p.field_72995_K && isTamed() && isFreedom()) {
            ChunkCoordinates cc = func_110172_bL();
            float dis = func_110172_bL().func_71569_e(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
            if (Math.sqrt(dis) > this.MAXIMUM_HOME_DISTANCE) {
                func_70107_b(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                func_70661_as().func_75499_g();
            }
        }
        this.field_70924_f = this.field_70926_e;
        if (func_70922_bv()) {
            this.field_70926_e += (1.0f - this.field_70926_e) * 0.4f;
        } else {
            this.field_70926_e += (0.0f - this.field_70926_e) * 0.4f;
        }
        if (func_70922_bv()) {
            this.field_70700_bx = 10;
        }
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
    }

    public float func_70047_e() {
        return this.field_70131_O * 0.8f;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_) {
        return (this.field_70924_f + ((this.field_70926_e - this.field_70924_f) * p_70917_1_)) * 0.07f * 3.1415927f;
    }

    public int func_70646_bf() {
        if (isSitting()) {
            return 20;
        }
        return super.func_70646_bf();
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
        if (soundCoolTime(2)) {
            this.field_70170_p.func_72956_a(this, "mapletree:entity.damage", mod_ecru_MapleTree.MomijiSounds, 1.0f);
        }
        if (func_85032_ar()) {
            return false;
        }
        EntityPlayer entityPlayerFunc_76346_g = p_70097_1_.func_76346_g();
        if ((entityPlayerFunc_76346_g instanceof EntityPlayer) && func_152114_e(entityPlayerFunc_76346_g)) {
            this.aiSit.setSitting(false);
        }
        if (entityPlayerFunc_76346_g != null && !(entityPlayerFunc_76346_g instanceof EntityPlayer) && !(entityPlayerFunc_76346_g instanceof EntityArrow)) {
            p_70097_2_ = (p_70097_2_ + 1.0f) / 2.0f;
        } else if (p_70097_1_ == DamageSource.field_76368_d || p_70097_1_ == DamageSource.field_76369_e) {
            int i = (int) this.field_70163_u;
            while (i < ((int) this.field_70163_u) + 4) {
                if (!this.field_70170_p.func_147437_c((int) this.field_70165_t, i, (int) this.field_70161_v)) {
                    i++;
                } else {
                    func_70107_b(((int) this.field_70165_t) + 0.5d, i, ((int) this.field_70161_v) + 0.5d);
                    return false;
                }
            }
            func_70107_b(((int) this.field_70165_t) + 0.5d, i, ((int) this.field_70161_v) + 0.5d);
            return false;
        }
        if ((entityPlayerFunc_76346_g instanceof EntityPlayer) && func_152114_e(entityPlayerFunc_76346_g) && entityPlayerFunc_76346_g.func_71045_bC() != null && (entityPlayerFunc_76346_g.func_71045_bC().func_77973_b() instanceof ItemSword)) {
            p_70097_2_ = 0.0f;
        }
        if ((p_70097_1_ == DamageSource.field_76372_a || p_70097_1_ == DamageSource.field_76370_b || p_70097_1_ == DamageSource.field_76371_c) && (this.toleranceRing & 1) == 1) {
            p_70097_2_ = 0.0f;
        }
        if (p_70097_1_ == DamageSource.field_76380_i) {
            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
        return super.func_70097_a(p_70097_1_, p_70097_2_ * this.stateBonusDefenseData[this.stateBonusDefenseLv]);
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        if (soundCoolTime(3)) {
            this.field_70170_p.func_72956_a(this, "mapletree:entity.attack", mod_ecru_MapleTree.MomijiSounds, 1.0f);
        }
        func_71038_i();
        double dm = setTotalAttackDamage();
        return p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), (float) dm);
    }

    @Override
    public void setTamed(boolean p_70903_1_) {
        super.setTamed(p_70903_1_);
        if (p_70903_1_) {
            func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0d);
        } else {
            func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0d);
        }
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_) {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
        if (isTamed()) {
            if (itemstack != null) {
                if (itemstack.func_77973_b() instanceof ItemFood) {
                    ItemFood itemfood = itemstack.func_77973_b();
                    if (itemfood.func_77845_h() && this.field_70180_af.func_111145_d(18) < 20.0f) {
                        if (!p_70085_1_.field_71075_bZ.field_75098_d) {
                            itemstack.field_77994_a--;
                        }
                        func_70691_i(itemfood.func_150905_g(itemstack));
                        this.field_70170_p.func_72956_a(this, "mapletree:entity.eat", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                        if (itemstack.field_77994_a <= 0) {
                            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack) null);
                            return true;
                        }
                        return true;
                    }
                } else {
                    if (func_152114_e(p_70085_1_) && itemstack.func_77973_b() == Items.field_151008_G) {
                        if (!p_70085_1_.field_71075_bZ.field_75098_d) {
                            itemstack.field_77994_a--;
                        }
                        if (itemstack.field_77994_a <= 0) {
                            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack) null);
                        }
                        playSound("mob.endermen.portal");
                        setFreedom(!isFreedom());
                        freeEffect();
                        if (isFreedom()) {
                            func_110171_b(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), this.MAXIMUM_HOME_DISTANCE);
                        } else {
                            func_110177_bN();
                        }
                        this.field_70170_p.func_72960_a(this, isFreedom() ? (byte) 12 : (byte) 11);
                        return true;
                    }
                    if (func_152114_e(p_70085_1_) && itemstack.func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockMapleSapling)) {
                        int t = itemstack.func_77960_j() & 3;
                        setTextureNum(t);
                        if (!p_70085_1_.field_71075_bZ.field_75098_d) {
                            itemstack.field_77994_a--;
                        }
                        if (itemstack.field_77994_a <= 0) {
                            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack) null);
                            return true;
                        }
                        return true;
                    }
                }
                if (!func_152114_e(p_70085_1_) && itemstack.func_77973_b() == Items.field_151057_cb) {
                    return true;
                }
            }
            if (func_152114_e(p_70085_1_) && !this.field_70170_p.field_72995_K && p_70085_1_.func_70093_af()) {
                this.aiSit.setSitting(!isSitting());
                this.field_70703_bu = false;
                func_70778_a((PathEntity) null);
                func_70784_b((Entity) null);
                func_70624_b((EntityLivingBase) null);
                this.field_70170_p.func_72960_a(this, isSitting() ? (byte) 13 : isFreedom() ? (byte) 12 : (byte) 11);
                return true;
            }
            if (!func_70877_b(itemstack)) {
                func_70661_as().func_75499_g();
                if (!this.field_70170_p.field_72995_K) {
                    p_70085_1_.openGui(mod_ecru_MapleTree.instance, mod_ecru_MapleTree.guiId_momiji, this.field_70170_p, func_145782_y(), 0, 0);
                }
            }
        } else if (itemstack != null && itemstack.func_77973_b() == mod_ecru_MapleTree.Item_foodsDish && itemstack.func_77960_j() == 2) {
            if (!p_70085_1_.field_71075_bZ.field_75098_d) {
                itemstack.field_77994_a--;
            }
            if (itemstack.field_77994_a <= 0) {
                p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack) null);
            }
            if (!this.field_70170_p.field_72995_K) {
                setTamed(true);
                func_70778_a((PathEntity) null);
                func_70624_b((EntityLivingBase) null);
                this.aiSit.setSitting(true);
                func_70606_j(20.0f);
                func_152115_b(p_70085_1_.func_110124_au().toString());
                playTameEffect(true);
                this.field_70170_p.func_72960_a(this, (byte) 7);
                func_110171_b(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v), this.MAXIMUM_HOME_DISTANCE);
                func_110177_bN();
                setOwnerName(p_70085_1_.func_70005_c_());
                this.field_70170_p.func_72956_a(this, "mapletree:entity.tamed", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                return true;
            }
            return true;
        }
        return super.func_70085_c(p_70085_1_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte par1) {
        switch (par1) {
            case 11:
                this.field_70170_p.func_72869_a("note", 0.5d, 0.5d, 0.5d, 1.0d, 1.0d, 1.0d);
                break;
            case 12:
                this.field_70170_p.func_72869_a("reddust", 0.5d, 0.5d, 0.5d, 1.0d, 1.0d, 1.0d);
                break;
            case 13:
                this.field_70170_p.func_72869_a("smoke", 0.5d, 0.5d, 0.5d, 1.0d, 1.0d, 1.0d);
                break;
            default:
                super.func_70103_a(par1);
                break;
        }
    }

    public boolean func_70877_b(ItemStack p_70877_1_) {
        return p_70877_1_ != null && p_70877_1_.func_77973_b() == mod_ecru_MapleTree.Item_hotCake;
    }

    public int func_70641_bl() {
        return 8;
    }

    public int getCollarColor() {
        return this.field_70180_af.func_75683_a(20) & 15;
    }

    public void setCollarColor(int p_82185_1_) {
        this.field_70180_af.func_75692_b(20, Byte.valueOf((byte) (p_82185_1_ & 15)));
    }

    @Override
    public ecru_EntityMomiji func_90011_a(EntityAgeable p_90011_1_) {
        ecru_EntityMomiji target;
        ecru_EntityMomiji entitymomiji = new ecru_EntityMomiji(this.field_70170_p);
        if (this.random.nextInt(2) == 0) {
            target = this;
        } else {
            target = (ecru_EntityMomiji) p_90011_1_;
        }
        String s = target.func_152113_b();
        if (s != null && s.trim().length() > 0) {
            entitymomiji.func_152115_b(s);
            entitymomiji.setTamed(true);
            String name = target.getOwnerName();
            entitymomiji.setOwnerName(name);
            List<Integer> lvList = new ArrayList<>();
            if (target.stateBonusAttackLv >= target.getStateBonusAttackLvCap()) {
                int i = target.stateBonusAttackLv;
                target.getClass();
                if (i < 40) {
                    lvList.add(0);
                }
            }
            if (target.stateBonusDefenseLv >= target.getStateBonusDefenseLvCap()) {
                int i2 = target.stateBonusDefenseLv;
                target.getClass();
                if (i2 < 40) {
                    lvList.add(1);
                }
            }
            if (target.stateBonusSpeedLv >= target.getStateBonusSpeedLvCap()) {
                int i3 = target.stateBonusSpeedLv;
                target.getClass();
                if (i3 < 40) {
                    lvList.add(2);
                }
            }
            if (lvList.size() > 0) {
                int num = this.random.nextInt(lvList.size());
                int n = lvList.get(num).intValue();
                if (n == 0) {
                    entitymomiji.setStateBonusAttackLvCap(target.getStateBonusAttackLvCap() + 1);
                    entitymomiji.setStateBonusDefenseLvCap(target.getStateBonusDefenseLvCap());
                    entitymomiji.setStateBonusSpeedLvCap(target.getStateBonusSpeedLvCap());
                }
                if (n == 1) {
                    entitymomiji.setStateBonusAttackLvCap(target.getStateBonusAttackLvCap());
                    entitymomiji.setStateBonusDefenseLvCap(target.getStateBonusDefenseLvCap() + 1);
                    entitymomiji.setStateBonusSpeedLvCap(target.getStateBonusSpeedLvCap());
                }
                if (n == 2) {
                    entitymomiji.setStateBonusAttackLvCap(target.getStateBonusAttackLvCap());
                    entitymomiji.setStateBonusDefenseLvCap(target.getStateBonusDefenseLvCap());
                    entitymomiji.setStateBonusSpeedLvCap(target.getStateBonusSpeedLvCap() + 1);
                }
            } else {
                entitymomiji.setStateBonusAttackLvCap(target.getStateBonusAttackLvCap());
                entitymomiji.setStateBonusDefenseLvCap(target.getStateBonusDefenseLvCap());
                entitymomiji.setStateBonusSpeedLvCap(target.getStateBonusSpeedLvCap());
            }
        }
        return entitymomiji;
    }

    public void func_70918_i(boolean p_70918_1_) {
        if (p_70918_1_) {
            this.field_70180_af.func_75692_b(19, (byte) 1);
        } else {
            this.field_70180_af.func_75692_b(19, (byte) 0);
        }
    }

    public boolean func_70878_b(EntityAnimal p_70878_1_) {
        if (p_70878_1_ == this || !isTamed() || !(p_70878_1_ instanceof ecru_EntityMomiji)) {
            return false;
        }
        ecru_EntityMomiji entitymomiji = (ecru_EntityMomiji) p_70878_1_;
        return entitymomiji.isTamed() && !entitymomiji.isSitting() && func_70880_s() && entitymomiji.func_70880_s();
    }

    public boolean func_70922_bv() {
        return this.field_70180_af.func_75683_a(19) == 1;
    }

    protected boolean func_70692_ba() {
        return !isTamed() && this.field_70173_aa > (mod_ecru_MapleTree.momijiDespawnTime * 60) * 20;
    }

    @Override
    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof ecru_EntityMomiji) {
                ecru_EntityMomiji entitymomiji = (ecru_EntityMomiji) p_142018_1_;
                if (entitymomiji.isTamed() && entitymomiji.func_70902_q() == p_142018_2_) {
                    return false;
                }
            }
            if ((p_142018_1_ instanceof EntityPlayer) && (p_142018_2_ instanceof EntityPlayer) && !((EntityPlayer) p_142018_2_).func_96122_a((EntityPlayer) p_142018_1_)) {
                return false;
            }
            return ((p_142018_1_ instanceof EntityHorse) && ((EntityHorse) p_142018_1_).func_110248_bS()) ? false : true;
        }
        return false;
    }

    protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {
        this.field_70143_R = 0.0f;
    }

    public void setFreedom(boolean flg) {
        this.isFreedom = flg;
        if (flg) {
            this.field_70180_af.func_75692_b(21, (byte) 1);
        } else {
            this.field_70180_af.func_75692_b(21, (byte) 0);
        }
    }

    public boolean isFreedom() {
        return this.field_70180_af.func_75683_a(21) == 1;
    }

    public void setWanderFlg(boolean flg) {
        this.wanderFlg = flg;
    }

    public boolean getWanderFlg() {
        return this.wanderFlg;
    }

    public String getOwnerName() {
        String name = this.field_70180_af.func_75681_e(22);
        if (name == null || name.length() <= 0) {
            name = "";
        }
        return name;
    }

    public void setOwnerName(String name) {
        this.field_70180_af.func_75692_b(22, String.format(name, new Object[0]));
    }

    public void playSound(String pname) {
        func_85030_a(pname, 0.5f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f) + 1.0f);
    }

    protected void freeEffect() {
        for (int i = 0; i < 7; i++) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02d;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02d;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02d;
            this.field_70170_p.func_72869_a("portal", (this.field_70165_t + ((this.field_70146_Z.nextFloat() * this.field_70130_N) * 2.0f)) - this.field_70130_N, this.field_70163_u + 0.5d + (this.field_70146_Z.nextFloat() * this.field_70131_O), (this.field_70161_v + ((this.field_70146_Z.nextFloat() * this.field_70130_N) * 2.0f)) - this.field_70130_N, d0, d1, d2);
        }
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        this.field_70170_p.func_72956_a(this, "mapletree:entity.death", mod_ecru_MapleTree.MomijiSounds, 1.0f);
    }

    protected void func_70628_a(boolean par1, int par2) {
        dropItems(this);
    }

    private void dropItems(ecru_EntityMomiji par1) {
        if (par1 != null) {
            for (int i = 0; i < par1.func_70302_i_(); i++) {
                ItemStack itemstack = par1.func_70301_a(i);
                if (itemstack != null) {
                    func_70099_a(itemstack, 1.0f);
                }
            }
        }
    }

    public int getShears() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ecru_ItemMomijiShears)) {
                return i;
            }
        }
        return -1;
    }

    public int getAllShears() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ItemShears)) {
                return i;
            }
        }
        return -1;
    }

    public int getHoe() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ItemHoe)) {
                return i;
            }
        }
        return -1;
    }

    public int getAxe() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ItemAxe)) {
                return i;
            }
        }
        return -1;
    }

    public int getNoodlesCutKnife() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == mod_ecru_MapleTree.Item_NoodlesCutKnife) {
                return i;
            }
        }
        return -1;
    }

    public int getCompass() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == Items.field_151111_aL) {
                return i;
            }
        }
        return -1;
    }

    public int getSword() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ItemSword)) {
                return i;
            }
        }
        return -1;
    }

    public ItemStack getSwordItemStack() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ItemSword)) {
                return this.itemStacks[i];
            }
        }
        return null;
    }

    public int getFishingRod() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() instanceof ItemFishingRod)) {
                return i;
            }
        }
        return -1;
    }

    public int getShearsEmptySlot() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int getCowsMilk() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == Items.field_151117_aB) {
                return i;
            }
        }
        return -1;
    }

    public int getBucket() {
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == Items.field_151133_ar) {
                return i;
            }
        }
        return -1;
    }

    public int getCacaoSeeds() {
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == Items.field_151100_aR && this.itemStacks[i].func_77960_j() == 3) {
                return i;
            }
        }
        return -1;
    }

    public int getSeeds() {
        for (int i = 3; i < 21; i++) {
            if (thisSeeds(i)) {
                return i;
            }
        }
        return -1;
    }

    public boolean thisSeeds(int s) {
        if (this.itemStacks[s] != null) {
            this.itemStacks[s].func_77973_b();
            String target = Item.field_150901_e.func_148750_c(this.itemStacks[s].func_77973_b());
            for (int i = 0; i < mod_ecru_MapleTree.excludeSeedsId.size(); i++) {
                if (target == null) {
                    return false;
                }
                if (target.equals(mod_ecru_MapleTree.excludeSeedsId.get(i)) && this.itemStacks[s].func_77960_j() == mod_ecru_MapleTree.excludeSeedsMeta.get(i).intValue()) {
                    return false;
                }
            }
        }
        if (this.itemStacks[s] != null) {
            for (int i2 = 0; i2 < ecru_util.chestInSeedList.length; i2++) {
                this.itemStacks[s].func_77973_b();
                String target2 = Item.field_150901_e.func_148750_c(this.itemStacks[s].func_77973_b());
                if (target2.equals(ecru_util.chestInSeedList[i2])) {
                    return false;
                }
            }
        }
        if (this.itemStacks[s] != null && ((this.itemStacks[s].func_77973_b() instanceof ItemSeeds) || (this.itemStacks[s].func_77973_b() instanceof ItemSeedFood))) {
            return true;
        }
        if (this.itemStacks[s] != null) {
            for (int i3 = 0; i3 < ecru_util.addSeedList.length; i3++) {
                this.itemStacks[s].func_77973_b();
                String target3 = Item.field_150901_e.func_148750_c(this.itemStacks[s].func_77973_b());
                if (target3.equals(ecru_util.addSeedList[i3]) && this.itemStacks[s].func_77960_j() == ecru_util.addSeedMetaList[i3]) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean thisCacao(int s) {
        if (this.itemStacks[s] != null && this.itemStacks[s].func_77973_b() == Items.field_151100_aR && this.itemStacks[s].func_77960_j() == 3) {
            return true;
        }
        return false;
    }

    public int getSeedSlotNum() {
        int n = 0;
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] != null && (thisSeeds(i) || thisSapling(i) || (this.itemStacks[i].func_77973_b() == Items.field_151100_aR && this.itemStacks[i].func_77960_j() == 3))) {
                n++;
            }
        }
        return n;
    }

    public int getInventoryFull() {
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int getToolFull() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int getHealItem() {
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == mod_ecru_MapleTree.Item_mapleSyrup) {
                return i;
            }
        }
        return -1;
    }

    public boolean setItems(ItemStack item) {
        if (item.func_77976_d() == 1) {
            for (int i = 3; i < 21; i++) {
                if (this.itemStacks[i] == null) {
                    this.itemStacks[i] = item;
                    return true;
                }
            }
            return false;
        }
        for (int i2 = 3; i2 < 21; i2++) {
            if (this.itemStacks[i2] != null && this.itemStacks[i2].func_77973_b() == item.func_77973_b()) {
                if (this.itemStacks[i2].func_77976_d() <= this.itemStacks[i2].field_77994_a + item.field_77994_a) {
                    this.itemStacks[i2].func_77979_a(-item.field_77994_a);
                    return true;
                }
                this.itemStacks[i2].func_77979_a(-(this.itemStacks[i2].func_77976_d() - this.itemStacks[i2].field_77994_a));
                int ss = item.field_77994_a - (this.itemStacks[i2].func_77976_d() - this.itemStacks[i2].field_77994_a);
                item.func_77979_a(-ss);
            }
        }
        EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u + 0.5d, this.field_70161_v, item);
        this.field_70170_p.func_72838_d(entityitem);
        return false;
    }

    public void eat() {
        int num = getHealItem();
        if (num >= 0) {
            this.itemStacks[num].func_77979_a(1);
            if (this.itemStacks[num].field_77994_a <= 0) {
                this.itemStacks[num] = null;
            }
            func_70691_i(1.0f);
            playSound("random.pop");
        }
    }

    public void setTextureNum(int t) {
        this.field_70180_af.func_75692_b(23, Byte.valueOf((byte) t));
    }

    public int getTextureNum() {
        return this.field_70180_af.func_75683_a(23);
    }

    private boolean soundCoolTime(int s) {
        if (s >= 0 && s <= 3 && this.soundAge[s] + (20 * this.voiceCoolTime) < this.field_70173_aa) {
            this.soundAge[s] = this.field_70173_aa;
            return true;
        }
        return false;
    }

    public float getStateBonusSpeed() {
        return this.stateBonusSpeedData[this.stateBonusSpeedLv];
    }

    private void levelUpCheck() {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == mod_ecru_MapleTree.Item_normalItem && this.itemStacks[i].func_77960_j() == 2) {
                if (this.momijiLv < 100) {
                    this.expPoint++;
                    this.itemStacks[i].func_77979_a(1);
                    if (this.itemStacks[i].field_77994_a <= 0) {
                        this.itemStacks[i] = null;
                    }
                    this.field_70170_p.func_72956_a(this, "random.pop", 0.2f, (((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f) + 1.0f) * 2.0f);
                }
                if (this.expPoint >= this.nextExpData[this.momijiLv] && this.momijiLv < 100) {
                    this.bonusPoint++;
                    this.momijiLv++;
                    this.expPoint = 0;
                }
            }
        }
    }

    public int getStateBonusAttackLvCap() {
        return this.field_70180_af.func_75683_a(24);
    }

    public int getStateBonusDefenseLvCap() {
        return this.field_70180_af.func_75683_a(25);
    }

    public int getStateBonusSpeedLvCap() {
        return this.field_70180_af.func_75683_a(26);
    }

    public void setStateBonusAttackLvCap(int s) {
        this.field_70180_af.func_75692_b(24, Byte.valueOf((byte) s));
    }

    public void setStateBonusDefenseLvCap(int s) {
        this.field_70180_af.func_75692_b(25, Byte.valueOf((byte) s));
    }

    public void setStateBonusSpeedLvCap(int s) {
        this.field_70180_af.func_75692_b(26, Byte.valueOf((byte) s));
    }

    public int getAttackMode() {
        return this.field_70180_af.func_75683_a(28);
    }

    public void setAttackMode(int s) {
        this.field_70180_af.func_75692_b(28, Byte.valueOf((byte) s));
    }

    public int getTargetItem(ItemStack it) {
        for (int i = 3; i < this.itemStacks.length; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == it.func_77973_b()) {
                return i;
            }
        }
        return -1;
    }

    public int splitStack(int s) {
        this.itemStacks[s].func_77979_a(1);
        if (this.itemStacks[s].field_77994_a <= 0) {
            this.itemStacks[s] = null;
            return 0;
        }
        return this.itemStacks[s].field_77994_a;
    }

    private String getStr(int i) {
        String s = String.valueOf(i);
        if (i < 0) {
            s = s.substring(1, s.length());
        }
        String s2 = "000" + s;
        String r = s2.substring(s2.length() - 3, s2.length());
        if (i >= 0) {
            return "0" + r;
        }
        return "1" + r;
    }

    public int getSaplingSlot(String name, int meta) {
        for (int i = 3; i < this.itemStacks.length; i++) {
            if (this.itemStacks[i] != null) {
                this.itemStacks[i].func_77973_b();
                String target = Item.field_150901_e.func_148750_c(this.itemStacks[i].func_77973_b());
                if (target.equals(name) && this.itemStacks[i].func_77960_j() == meta) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getFirstSaplingSlot() {
        for (int i = 3; i < this.itemStacks.length; i++) {
            for (int s = 0; s < mod_ecru_MapleTree.logAndSapling.length; s++) {
                if (this.itemStacks[i] != null) {
                    String str = mod_ecru_MapleTree.logAndSapling[s].saplingNmae;
                    this.itemStacks[i].func_77973_b();
                    if (str.equals(Item.field_150901_e.func_148750_c(this.itemStacks[i].func_77973_b())) && mod_ecru_MapleTree.logAndSapling[s].saplingMeta == this.itemStacks[i].func_77960_j()) {
                        return i;
                    }
                }
            }
            for (int s2 = 0; s2 < mod_ecru_MapleTree.saplingId.size(); s2++) {
                if (this.itemStacks[i] != null) {
                    String str2 = mod_ecru_MapleTree.saplingId.get(s2);
                    this.itemStacks[i].func_77973_b();
                    if (str2.equals(Item.field_150901_e.func_148750_c(this.itemStacks[i].func_77973_b())) && mod_ecru_MapleTree.saplingMeta.get(s2).intValue() == this.itemStacks[i].func_77960_j()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public boolean thisSapling(int i) {
        for (int s = 0; s < mod_ecru_MapleTree.logAndSapling.length; s++) {
            if (this.itemStacks[i] != null) {
                String str = mod_ecru_MapleTree.logAndSapling[s].saplingNmae;
                this.itemStacks[i].func_77973_b();
                if (str.equals(Item.field_150901_e.func_148750_c(this.itemStacks[i].func_77973_b())) && mod_ecru_MapleTree.logAndSapling[s].saplingMeta == this.itemStacks[i].func_77960_j()) {
                    return true;
                }
            }
        }
        for (int s2 = 0; s2 < mod_ecru_MapleTree.saplingId.size(); s2++) {
            if (this.itemStacks[i] != null) {
                String str2 = mod_ecru_MapleTree.saplingId.get(s2);
                this.itemStacks[i].func_77973_b();
                if (str2.equals(Item.field_150901_e.func_148750_c(this.itemStacks[i].func_77973_b())) && mod_ecru_MapleTree.saplingMeta.get(s2).intValue() == this.itemStacks[i].func_77960_j()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean notPutItem(int ll) {
        if (this.itemStacks[ll].func_77973_b() == Items.field_151133_ar) {
            return true;
        }
        if (this.itemStacks[ll].func_77973_b() == Items.field_151100_aR && this.itemStacks[ll].func_77960_j() == 3) {
            return true;
        }
        if ((this.itemStacks[ll].func_77973_b() == Items.field_151100_aR && this.itemStacks[ll].func_77960_j() == 15) || this.itemStacks[ll].func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiSoba) || this.itemStacks[ll].func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiUdon)) {
            return true;
        }
        return false;
    }

    public int getEmptySlotNum() {
        int c = 0;
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] == null) {
                c++;
            }
        }
        return c;
    }

    public int getThisItem(Item item) {
        for (int i = 3; i < 21; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == item) {
                return i;
            }
        }
        return -1;
    }

    public int getTeuchi(int mode) {
        for (int i = 3; i < 21; i++) {
            switch (mode) {
                case 0:
                default:
                    if (this.itemStacks[i] != null && (this.itemStacks[i].func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiUdon) || this.itemStacks[i].func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiSoba))) {
                        return i;
                    }
                    break;
                case 1:
                    if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiUdon)) {
                        return i;
                    }
                    break;
                    break;
                case 2:
                    if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == Item.func_150898_a(mod_ecru_MapleTree.blockTeuchiSoba)) {
                        return i;
                    }
                    break;
                    break;
            }
        }
        return -1;
    }

    public ItemStack getFirstItem() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null) {
                return this.itemStacks[i];
            }
        }
        return null;
    }

    @Override
    public void updateInventory(int i, EntityPlayer entityPlayer) {
        if (getSword() != -1) {
            setAttackMode(1);
        } else if (getFishingRod() != -1) {
            setAttackMode(2);
        } else if (getCowsMilk() != -1) {
            setAttackMode(3);
        } else {
            setAttackMode(0);
        }
        checkRing();
        if (!this.field_70170_p.field_72995_K) {
            ItemStack[] is = {this.itemStacks[0], this.itemStacks[1], this.itemStacks[2]};
            if (entityPlayer != null) {
                ecru_PacketHandler.network.sendTo(new ecru_PacketMomiji(is, func_145782_y()), (EntityPlayerMP) entityPlayer);
            } else {
                ecru_PacketHandler.network.sendToAll(new ecru_PacketMomiji(is, func_145782_y()));
            }
        }
    }

    @Override
    public void func_70296_d() {
        super.func_70296_d();
    }

    private void checkRing() {
        if (checkFireproofRing()) {
            this.toleranceRing |= 1;
        } else {
            this.toleranceRing &= -2;
        }
    }

    private boolean checkFireproofRing() {
        for (int i = 0; i < 3; i++) {
            if (this.itemStacks[i] != null && this.itemStacks[i].func_77973_b() == mod_ecru_MapleTree.Item_fireproofRing) {
                return true;
            }
        }
        return false;
    }

    public double getAttackVSEntity(ItemStack itemStack) {
        double damage = 0.0d;
        try {
            Multimap<String, AttributeModifier> multimap = itemStack.func_111283_C();
            if (multimap.containsKey(SharedMonsterAttributes.field_111264_e.func_111108_a()) && multimap.get(SharedMonsterAttributes.field_111264_e.func_111108_a()).toArray().length > 0 && (multimap.get(SharedMonsterAttributes.field_111264_e.func_111108_a()).toArray()[0] instanceof AttributeModifier)) {
                AttributeModifier weaponModifier = (AttributeModifier) multimap.get(SharedMonsterAttributes.field_111264_e.func_111108_a()).toArray()[0];
                damage = weaponModifier.func_111164_d();
            }
        } catch (Exception e) {
        }
        return damage;
    }

    public double setTotalAttackDamage() {
        double dm;
        double dm2 = 0.0d;
        float i = isTamed() ? this.stateBonusAttackData[this.stateBonusAttackLv] : this.stateBonusAttackData[0];
        if (getAttackMode() == 1) {
            ItemStack it = getSwordItemStack();
            if (it != null) {
                dm2 = getAttackVSEntity(it);
            }
            dm = (i + (dm2 / 2.0d)) * 1.2d;
        } else {
            dm = i;
        }
        this.attackDamage = (float) dm;
        return dm;
    }

    public void func_71038_i() {
        if (!this.field_82175_bq || this.field_110158_av >= getArmSwingAnimationEnd() / 2 || this.field_110158_av < 0) {
            this.field_110158_av = -1;
            this.field_82175_bq = true;
            if (this.field_70170_p instanceof WorldServer) {
                this.field_70170_p.func_73039_n().func_151247_a(this, new S0BPacketAnimation(this, 0));
            }
        }
    }

    private int getArmSwingAnimationEnd() {
        return 12;
    }

    protected void func_82168_bl() {
        int i = getArmSwingAnimationEnd();
        if (this.field_82175_bq) {
            this.field_110158_av++;
            if (this.field_110158_av >= i) {
                this.field_110158_av = 0;
                this.field_82175_bq = false;
            }
        } else {
            this.field_110158_av = 0;
        }
        this.field_70733_aJ = this.field_110158_av / i;
    }

    @SideOnly(Side.CLIENT)
    public float func_70678_g(float p_70678_1_) {
        float f1 = this.field_70733_aJ - this.field_70732_aI;
        if (f1 < 0.0f) {
            f1 += 1.0f;
        }
        return this.field_70732_aI + (f1 * p_70678_1_);
    }

    public void getChestMode(ItemStack items, int itemsNum, int xx, int yy, int zz, int emptySlotNum) {
        List bList = new ArrayList();
        ecru_EntityMomijiBlockPos _bp = new ecru_EntityMomijiBlockPos();
        _bp.posX = xx;
        _bp.posY = yy;
        _bp.posZ = zz;
        bList.add(_bp);
        if (!bList.isEmpty()) {
            int li = func_70681_au().nextInt(bList.size());
            ecru_EntityMomijiBlockPos bp = (ecru_EntityMomijiBlockPos) bList.get(li);
            if (ecru_util.canEntityItemBeSeen(this, bp) && isTamed() && !isSitting() && isFreedom()) {
                TileEntity ti = this.field_70170_p.func_147438_o(bp.posX, bp.posY, bp.posZ);
                if ((ti instanceof TileEntityChest) && this.chestCoolTime <= 0 && this.touchChest_takeOut == null) {
                    this.touchChest_takeOut = this.field_70170_p.func_147438_o(bp.posX, bp.posY, bp.posZ);
                    boolean flg = getCheckChest_takeOutContains(items, this.touchChest_takeOut);
                    if (flg) {
                        this.touchChest_takeOut = null;
                        return;
                    }
                    if (this.touchChest_takeOut == null) {
                        return;
                    }
                    this.chestCoolTime = 7;
                    this.touchChest_takeOut.func_70295_k_();
                    func_71038_i();
                    if (!getChestItem(items, itemsNum, this.touchChest_takeOut, emptySlotNum)) {
                        if (soundCoolTime(1)) {
                            this.field_70170_p.func_72956_a(this, "mapletree:entity.chest_full", mod_ecru_MapleTree.MomijiSounds, 1.0f);
                        }
                        int ch1 = getCheckChest_takeOut_ItemIndex(items);
                        if (ch1 != -1) {
                            this.checkChest_takeOut[ch1].add(this.touchChest_takeOut);
                        }
                    } else {
                        int ch12 = getCheckChest_takeOut_ItemIndex(items);
                        if (ch12 != -1) {
                            this.checkChest_takeOut[ch12].remove(this.touchChest_takeOut);
                        }
                    }
                }
            }
        }
    }

    private boolean getChestItem(ItemStack items, int itemStackNum, TileEntityChest chest, int emptySlotNum) {
        int size = chest.func_70302_i_();
        int getNum = itemStackNum;
        int i = 0;
        loop0: while (true) {
            if (i >= size) {
                break;
            }
            if (chest.func_70301_a(i) != null && chest.func_70301_a(i).func_77973_b() == items.func_77973_b() && chest.func_70301_a(i).func_77960_j() == items.func_77960_j()) {
                while (true) {
                    if (getNum <= 0 || chest.func_70301_a(i).field_77994_a <= 0) {
                        break;
                    }
                    if (getMomjiInv(chest.func_70301_a(i), emptySlotNum)) {
                        chest.func_70301_a(i).field_77994_a--;
                        getNum--;
                        if (chest.func_70301_a(i).field_77994_a == 0) {
                            chest.func_70299_a(i, (ItemStack) null);
                            if (getNum <= 0) {
                                break;
                            }
                        } else if (getNum <= 0) {
                            break loop0;
                        }
                    } else if (getEmptySlotNum() <= emptySlotNum) {
                        return false;
                    }
                }
            }
            i++;
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (chest.func_70301_a(i2) != null && chest.func_70301_a(i2).func_77973_b() == items.func_77973_b() && chest.func_70301_a(i2).func_77960_j() == items.func_77960_j()) {
                return true;
            }
        }
        return false;
    }

    private boolean getMomjiInv(ItemStack it, int emptySlotNum) {
        for (int ms = 3; ms < 21; ms++) {
            if (this.itemStacks[ms] == null) {
                if (getEmptySlotNum() <= emptySlotNum) {
                    return false;
                }
                if (it.func_77976_d() == 1) {
                    this.itemStacks[ms] = it.func_77946_l();
                    return true;
                }
                this.itemStacks[ms] = new ItemStack(it.func_77973_b(), 1, it.func_77960_j());
                return true;
            }
            if (it.func_77973_b() == this.itemStacks[ms].func_77973_b() && 1 <= it.func_77976_d() - this.itemStacks[ms].field_77994_a) {
                this.itemStacks[ms].field_77994_a++;
                return true;
            }
        }
        return false;
    }

    public boolean getCheckChest_takeOutContains(ItemStack items, TileEntityChest tile) {
        return getCheckChest_takeOut(items) == null || getCheckChest_takeOut(items).contains(tile);
    }

    public int getCheckChest_takeOut_ItemIndex(ItemStack it) {
        for (int i = 0; i < this.chestItem.length; i++) {
            if (it == this.chestItem[i]) {
                return i;
            }
        }
        return -1;
    }

    public List getCheckChest_takeOut(ItemStack items) {
        int ch = getCheckChest_takeOut_ItemIndex(items);
        if (ch != -1) {
            return this.checkChest_takeOut[ch];
        }
        return null;
    }

    private String getPosString(int x, int y, int z) {
        String xx = Integer.toHexString(x);
        String yy = Integer.toHexString(y);
        String zz = Integer.toHexString(z);
        return xx + ":" + yy + ":" + zz;
    }

    public boolean checkLogListAdd(int x, int y, int z) {
        if (!this.checkLog.contains(getPosString(x, y, z))) {
            this.checkLog.add(getPosString(x, y, z));
            return true;
        }
        return false;
    }

    public boolean checkLogListMatch(int x, int y, int z) {
        if (this.checkLog.contains(getPosString(x, y, z))) {
            return true;
        }
        return false;
    }

    public boolean checkLogListDelete(int x, int y, int z) {
        if (this.checkLog.contains(getPosString(x, y, z))) {
            this.checkLog.remove(getPosString(x, y, z));
            return true;
        }
        return false;
    }
}
