package ecru.MapleTree;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import ecru.MapleTree.client.ecru_GuiHandler;
import ecru.MapleTree.common.ecru_CreateReciprBook;
import ecru.MapleTree.common.ecru_IdList;
import ecru.MapleTree.common.ecru_LivingDeathEventHandler;
import ecru.MapleTree.common.ecru_Recipe;
import ecru.MapleTree.common.ecru_TradesVillagerFarmer;
import ecru.MapleTree.common.ecru_curryspiceList;
import ecru.MapleTree.common.ecru_fuelHandler;
import ecru.MapleTree.common.ecru_mapleCreativeTab;
import ecru.MapleTree.common.ecru_mapleCreativeTabCurry;
import ecru.MapleTree.common.ecru_mapleCreativeTabOther;
import ecru.MapleTree.entity.common.ecru_util;
import ecru.MapleTree.entity.ecru_EntityGacha;
import ecru.MapleTree.entity.ecru_EntityMomiji;
import ecru.MapleTree.gen.ecru_WorldGenBigMapleTree;
import ecru.MapleTree.gen.ecru_WorldGenBigPersimmonTree;
import ecru.MapleTree.gen.ecru_WorldGenMapleTrees;
import ecru.MapleTree.gen.ecru_WorldGenMinable;
import ecru.MapleTree.gen.ecru_WorldGenSunFlower;
import ecru.MapleTree.gen.ecru_WorldGenVanilla;
import ecru.MapleTree.gen.ecru_WorldGenerate;
import ecru.MapleTree.help.ecru_eventHandler;
import ecru.MapleTree.network.ecru_PacketHandler;
import ecru.MapleTree.tile.ecru_TileEntityCauldron;
import ecru.MapleTree.tile.ecru_TileEntityCompost;
import ecru.MapleTree.tile.ecru_TileEntityGatherItems;
import ecru.MapleTree.tile.ecru_TileEntityGrainDryer;
import ecru.MapleTree.tile.ecru_TileEntityMapleWoodSyrup;
import ecru.MapleTree.tile.ecru_TileEntityMortar;
import ecru.MapleTree.tile.ecru_TileEntityOreFlower;
import ecru.MapleTree.tile.ecru_TileEntityPersimmonWood;
import ecru.MapleTree.tile.ecru_TileEntityPlanter;
import ecru.MapleTree.tile.ecru_TileEntitySpile;
import ecru.MapleTree.tile.ecru_TileEntityTeuchiUdon;
import ecru.MapleTree.tile.ecru_TileEntityWineFaucet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.FishingHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.ChunkEvent;

@Mod(modid = mod_ecru_MapleTree.MODID, name = mod_ecru_MapleTree.NAME, version = mod_ecru_MapleTree.VERSION)
public class mod_ecru_MapleTree {
    public static final String MODID = "mod_ecru_MapleTree";
    public static final String VERSION = "1.1.33m";
    public static final String NAME = "MapleTree";

    @SidedProxy(clientSide = "ecru.MapleTree.client.ClientProxy", serverSide = "ecru.MapleTree.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static mod_ecru_MapleTree instance;
    public static int entityId;
    public static int entityGachaId;
    public static ecru_util.ShearsTargetBlock[] shearsHarvestTargetBlock;
    public static ecru_util.addCropsBlock[] addCropsTargetBlock;
    public static ecru_util.logAndSapling[] logAndSapling;
    public static Block blockMapleLeaves;
    public static Block blockMapleWood;
    public static Block blockMapleWoodSyrup;
    public static Block blockPersimmonWood;
    public static Block blockPersimmonLeaves;
    public static Block blockPersimmonSapling;
    public static Block blockMapleSapling;
    public static Block blockFallenLeaves;
    public static Block blockFallenLeavesFire;
    public static Block blockDecoration1;
    public static Block blockDecoration2;
    public static Block blockDecoration3;
    public static Block blockCauldron;
    public static Block blockSpile;
    public static Block blockVanilla;
    public static Block blockGrape;
    public static Block blockPlanter;
    public static Block blockOreFlowerRed;
    public static Block blockOreFlowerIron;
    public static Block blockOreFlowerGold;
    public static Block blockOreFlowerMarble;
    public static Block blockStoneMortar;
    public static Block blockEngine;
    public static Block blockChestnutsBurrs;
    public static Block blockLeafFence;
    public static Block blockSunFlower;
    public static Block blockPetal;
    public static Block blockWhiteFence;
    public static Block blockLighthouseIllumination;
    public static Block blockTomato;
    public static Block blockEggplant;
    public static Block blockOnion;
    public static Block blockSprinkler;
    public static Block blockFountain;
    public static Block blockOreBlock;
    public static Block blockMarbleJewel0;
    public static Block blockMarbleJewel1;
    public static Block blockMarbleJewel2;
    public static Block blockMarbleJewel3;
    public static Block blockMarbleJewel4;
    public static Block blockMarbleJewel5;
    public static Block blockMarbleJewel6;
    public static Block blockMarbleJewel7;
    public static Block blockMarble;
    public static Block blockJapaneseRadish;
    public static Block blockMiniStairs1;
    public static Block blockMiniStairs2;
    public static Block blockMiniStairs3;
    public static Block blockMiniStairs4;
    public static Block blockCookPot;
    public static Block blockAzuki;
    public static Block blockStickyRiceCrops;
    public static Block blockRapeseed;
    public static Block blockCabbage;
    public static Block blockSLight;
    public static Block blockSLight2;
    public static Block blockGrainHopper;
    public static Block blockBiofuelPD;
    public static Block blockPepper;
    public static Block blockSpice;
    public static Block blockHumanPowerDrive;
    public static Block blockPowerShaftGear;
    public static Block blockPowerShaft;
    public static Block blockGrapeStompTub;
    public static Block blockWineBarrel;
    public static Block blockWineFaucet;
    public static Block blockPersimmon;
    public static Block blockGatherItems;
    public static Block blockDriedPersimmons;
    public static Block blockMortar;
    public static Block blockCropsCardamon;
    public static Block blockCropsCumin;
    public static Block blockCropsCoriander;
    public static Block blockCropsTurmeric;
    public static Block blockCropsFennel;
    public static Block blockCropsChili_pepper;
    public static Block blockAllspiceWood;
    public static Block blockCloveWood;
    public static Block blockCinnamonWood;
    public static Block blockStar_aniseWood;
    public static Block blockNutmegWood;
    public static Block blockAllSpiceLeaves;
    public static Block blockCloveLeaves;
    public static Block blockCinnamonLeaves;
    public static Block blockStar_aniseLeaves;
    public static Block blockNutmegLeaves;
    public static Block blockThinSapling;
    public static Block blockGrainDryer;
    public static Block blockAllspiceThinPlanks;
    public static Block blockCinnamonThinPlanks;
    public static Block blockCloveThinPlanks;
    public static Block blockNutmegThinPlanks;
    public static Block blockStarAniseThinPlanks;
    public static Block blockKelp;
    public static Block blockSunDryingNet;
    public static Block blockTeuchiUdon;
    public static Block blockTeuchiSoba;
    public static Block blockBuckwheat;
    public static Block blockCompost;
    public static Item Item_particle;
    public static Item Item_tabIcon;
    public static Item Item_tabIconCurry;
    public static Item Item_tabIconOther;
    public static Item Item_mapleSyrup;
    public static Item Item_mapleSyrupPotion;
    public static Item Item_hotCake;
    public static Item Item_maplePudding;
    public static Item Item_vanillaSeed;
    public static Item Item_vanillaSheath;
    public static Item Item_vanillaBeans;
    public static Item Item_softCreamVanilla;
    public static Item Item_grape;
    public static Item Item_grapeSeed;
    public static Item Item_grapePoundCake;
    public static Item Item_mapleChip;
    public static Item Item_chestnutsBburrs;
    public static Item Item_chestnut;
    public static Item Item_roastChestnuts;
    public static Item Item_SunFlowerSeed;
    public static Item Item_treeManure;
    public static Item Item_tomato;
    public static Item Item_tomatoSeeds;
    public static Item Item_eggplant;
    public static Item Item_eggplantSeeds;
    public static Item Item_onion;
    public static Item Item_onionSeeds;
    public static Item Item_flour;
    public static Item Item_salt;
    public static Item Item_grilled_eggplant;
    public static Item Item_dough;
    public static Item Item_buns;
    public static Item Item_hamburger;
    public static Item Item_hamburger_meat;
    public static Item Item_cheese;
    public static Item Item_cheeseBurger;
    public static Item Item_jewel;
    public static Item Item_jewelMapleDia;
    public static Item Item_ReactivationChemicalAgents;
    public static Item Item_PickaxeMapleDiamond;
    public static Item Item_AxeMapleDiamond;
    public static Item Item_ShovelMapleDiamond;
    public static Item Item_SwordMapleDiamond;
    public static Item Item_HoeMapleDiamond;
    public static Item Item_NoodlesCutKnife;
    public static Item Item_foodsDish;
    public static Item Item_foodstuff;
    public static Item Item_normalItem;
    public static Item Item_alwaysFoods;
    public static Item Item_AzukiBeans;
    public static Item Item_unhulledStickyRice;
    public static Item Item_rapeSeeds;
    public static Item Item_cabbageSeeds;
    public static Item Item_japaneseRadishSeeds;
    public static Item Item_momijiShears;
    public static Item Item_momijiShearsMapleDiamond;
    public static Item Item_SpiceList;
    public static Item Item_Curryspice;
    public static Item Item_CurryRoux;
    public static Item Item_StewCurry;
    public static Item Item_CurryRice;
    public static Item Item_cardamonFruit;
    public static Item Item_cardamonSeed;
    public static Item Item_cuminSeed;
    public static Item Item_corianderSeed;
    public static Item Item_turmericRoot;
    public static Item Item_chili_pepperFruit;
    public static Item Item_chili_pepperSeed;
    public static Item Item_fennelSeed;
    public static Item Item_allspiceSeed;
    public static Item Item_cloveBud;
    public static Item Item_cloveFruit;
    public static Item Item_star_aniseFruit;
    public static Item Item_nutmegFruit;
    public static Item Item_cinnamonFruit;
    public static Item Item_cinnamonBark;
    public static Item Item_nutmegSeed;
    public static Item Item_star_aniseSeed;
    public static Item Item_machinedBonito;
    public static Item Item_BoiledBonito;
    public static Item Item_kelpSporophyte;
    public static Item Item_sunDryingBonito;
    public static Item Item_sunDryingCuttlefish;
    public static Item Item_sunDryingKelp;
    public static Item Item_buckwheatSeed;
    public static Item Item_gacha;
    public static Item Item_rake;
    public static Item Item_rakeTallgrass;
    public static Item Item_mushroom;
    public static Item Item_fireproofRing;
    public static String excludeSeedsList;
    public static String addLogList;
    public static int renderID;
    public static int renderBonfireID;
    public static int renderDecorationID;
    public static int renderLeafFenceID;
    public static int renderCauldronID;
    public static int renderSpileID;
    public static int renderVanillaID;
    public static int renderGrapeID;
    public static int renderPlanterID;
    public static int renderOreFlowerID;
    public static int renderStoneMortarID;
    public static int renderEngineID;
    public static int renderSunFlowerID;
    public static int renderPetalID;
    public static int renderWhiteFenceID;
    public static int renderLighthouseIlluminationID;
    public static int renderTomatoID;
    public static int renderEggplantID;
    public static int renderOnionID;
    public static int renderSprinklerID;
    public static int renderFountainID;
    public static int renderOreBlockID;
    public static int renderDecorationJewelID;
    public static int renderMiniStairs1ID;
    public static int renderCookPotID;
    public static int renderCabbageID;
    public static int renderSLightID;
    public static int renderGrainHopperID;
    public static int renderBiofuelPDID;
    public static int renderJapaneseRadishID;
    public static int renderPepperID;
    public static int renderSpiceID;
    public static int renderHumanPowerDriveID;
    public static int renderPowerShaftGearID;
    public static int renderPowerShaftID;
    public static int renderGrapeStompTubID;
    public static int renderWineBarrelID;
    public static int renderWineFaucetID;
    public static int renderPersimmonID;
    public static int renderGatherItemsID;
    public static int renderDriedPersimmonID;
    public static int renderMortarID;
    public static int renderThinWoodID;
    public static int renderGrainDryerID;
    public static int renderKelpID;
    public static int renderSunDryingID;
    public static int renderTeuchiUdonID;
    public static int renderCompostID;
    public static int decoTextureID1;
    public static int decoTextureID2;
    public static int decoTextureID3;
    public static ecru_TradesVillagerFarmer villager;
    public static final String MODID_SET = null;
    public static final CreativeTabs tabsMaple = new ecru_mapleCreativeTab("ecruMapleTree");
    public static final CreativeTabs tabsMapleCurry = new ecru_mapleCreativeTabCurry("ecruMapleTreeCurry");
    public static final CreativeTabs tabsMapleOther = new ecru_mapleCreativeTabOther("ecruMapleTreeOther");
    public static final mapleTreeRegistryBlock mapleTreeRegistryBlock = new mapleTreeRegistryBlock();
    public static final mapleTreeRegistryItem mapleTreeRegistryItem = new mapleTreeRegistryItem();
    public static final loadConfiguration loadConfiguration = new loadConfiguration();
    public static World lastWorld = null;
    public static ecru_curryspiceList.spiceList[] spiceList = ecru_curryspiceList.spiceList.values();
    public static ecru_curryspiceList.curryspiceList[] curryspiceList = ecru_curryspiceList.curryspiceList.values();
    public static List<String> excludeSeedsId = new ArrayList();
    public static List<Integer> excludeSeedsMeta = new ArrayList();
    public static List<String> logId = new ArrayList();
    public static List<Integer> logMeta = new ArrayList();
    public static List<String> saplingId = new ArrayList();
    public static List<Integer> saplingMeta = new ArrayList();
    public static String defaultExcludeSeeds = "mod_ecru_MapleTree:unhulledStickyRice;0";
    public static ecru_WorldGenMapleTrees worldgenmapletrees = new ecru_WorldGenMapleTrees(true);
    public static ecru_WorldGenBigMapleTree worldgenbigmapletree = new ecru_WorldGenBigMapleTree(true);
    public static ecru_WorldGenVanilla worldgenvanilla = new ecru_WorldGenVanilla(true);
    public static ecru_WorldGenSunFlower worldgenSunFlower = new ecru_WorldGenSunFlower(true);
    public static ecru_WorldGenMinable worldgenOreBlock = new ecru_WorldGenMinable();
    public static ecru_WorldGenBigPersimmonTree worldgenPersimmonTrees = new ecru_WorldGenBigPersimmonTree(true);
    public static boolean decorationNormalBlock = false;
    public static String[] texList1 = {"deco_wood", "planks_oak", "planks_spruce", "planks_birch", "planks_jungle", "log_oak"};
    public static String[] texList2 = {"deco_stone", "stone", "stonebrick", "brick", "stonebrick_mossy", "quartz_block_chiseled", "quartz_block_lines"};
    public static String[] texList3 = {"deco_wood", "iron_block", "sandstone_normal", "wool_colored_white", "wool_colored_orange", "wool_colored_magenta", "wool_colored_light_blue", "wool_colored_yellow", "wool_colored_lime", "wool_colored_pink", "wool_colored_gray", "wool_colored_silver", "wool_colored_cyan", "wool_colored_purple", "wool_colored_blue", "wool_colored_brown", "wool_colored_green", "wool_colored_red", "wool_colored_black", "sandstone_carved", "sandstone_smooth"};
    public static Block[] blockList = {Blocks.field_150466_ao, Blocks.field_150454_av, Blocks.field_150396_be};
    public static int GenerationRate = 10;
    public static boolean GenerationForest = true;
    public static int GraphicsLevel = 2;
    public static boolean FallenLeavesDropped = true;
    public static boolean FallenLeavesColorRed = false;
    public static boolean TreeDeleted = false;
    public static int _cou_ = 0;
    public static boolean FallenLeavesParticles = true;
    public static boolean GrassDropsSeeds = true;
    public static boolean MarbleBlockTranslucent = false;
    public static boolean MiniStairsLong = true;
    public static boolean GenerateOre = true;
    public static float EnvironmentSounds = 0.0f;
    public static float MomijiSounds = 0.5f;
    public static boolean plantSeedRandom = false;
    public static int momijiActivityField = 18;
    public static boolean registeredSeedPlant = true;
    public static boolean harvestLeaves = true;
    public static boolean GenerationSpiceTree = true;
    public static int walkingFrequency = 30;
    public static boolean magicalcropsEnable = true;
    public static int momijiDespawnTime = 2;
    public static int momijiSpawnRate = 20;
    public static boolean momijiBlinkTexture = true;
    public static boolean momijiLightTexture = true;
    public static int LogBlockFellingMAX = 200;
    public static int momijiCutAllMode = 0;
    public static boolean GenerationPersimmonWood = true;
    public static int updateIntervalTime = 5;
    public static boolean spiceReliefRecipe = false;
    public static boolean mushroomParticle = true;
    public static boolean villagerBarter_GrapeSeeds = true;
    public static boolean villagerBarter_PeppercornBunch = true;
    public static boolean villagerBarter_SoySauce = true;
    public static boolean villagerBarter_Miso = true;
    public static boolean villagerBarter_IronIngot = true;
    public static boolean villagerBarter_GoldIngot = true;
    public static boolean villagerBarter_MomenTofu = false;
    public static boolean villagerBarter_Scallion = false;
    public static boolean villagerBarter_KelpSporophyte = false;
    public static boolean villagerBarter_Compost = true;
    public static boolean helpTip = true;
    public static int helpTip_posY = 60;
    public static boolean modAdd_harvestcraft = false;
    public static String LighthouseIlluminationColorStr = "FFFF00";
    public static int LighthouseIlluminationColor = 16776960;
    public static int LighthouseIlluminationLength = 30;
    public static int LighthouseIlluminationWidth = 6;
    public static int LighthouseIlluminationTransparency = 255;
    public static int LighthouseIlluminationViewMode = 1;
    public static int LighthouseIlluminationPolygonCount = 5;
    public static String SLightColorStr = "FFFF00";
    public static int SLightColor = 16776960;
    public static int SLightLength = 12;
    public static int SLightWidth = 3;
    public static int SLightTransparency = 255;
    public static int guiIdButtonId = 1;
    public static int guiIdCauldron = 2;
    public static int guiIdPlanter = 3;
    public static int guiIdEngineId = 4;
    public static int guiIdStoneMortarId = 5;
    public static int guiIdLighthouseIllumination = 6;
    public static int guiIdCookPot = 7;
    public static int guiIdSLight = 8;
    public static int guiIdGrainHopper = 9;
    public static int guiIdBiofuelPD = 10;
    public static int guiIdWineBarrel = 11;
    public static int guiId_momiji = 12;
    public static int guiIdGatherItems = 13;
    public static int guiIdMortar = 14;
    public static int guiIdGrainDryer = 15;
    public static int guiFontColor = 4210752;
    public static int villagerProfession = 0;
    public static ecru_IdList idInfo = new ecru_IdList();
    public static Item.ToolMaterial toolMaterialMapleDiamond = EnumHelper.addToolMaterial("PICKAXE_MAPLE_DIAMOND", 3, 4000, 9.0f, 6.0f, 22);
    public static Item.ToolMaterial toolMaterialKnife = EnumHelper.addToolMaterial("NOODLES_CUT_KNIFE", 0, 20000, 0.0f, 0.0f, 5);

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (Loader.isModLoaded("harvestcraft")) {
            modAdd_harvestcraft = true;
        }
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        if (TreeDeleted) {
            MinecraftForge.EVENT_BUS.register(new cLoader());
        }
        villager = new ecru_TradesVillagerFarmer();
        VillagerRegistry.instance().registerVillageTradeHandler(villagerProfession, villager);
        MinecraftForge.EVENT_BUS.register(new ecru_LivingDeathEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ecru_GuiHandler());
        ecru_PacketHandler.init();
        FMLCommonHandler.instance().bus().register(new ecru_eventHandler());
        if (!TreeDeleted) {
            GameRegistry.registerWorldGenerator(new ecru_WorldGenerate(), 1);
        }
        GameRegistry.registerTileEntity(ecru_TileEntityMapleWoodSyrup.class, "ecru_TileEntityMapleWoodSyrup");
        GameRegistry.registerTileEntity(ecru_TileEntityCauldron.class, "ecru_TileEntityCauldron");
        GameRegistry.registerTileEntity(ecru_TileEntitySpile.class, "ecru_TileEntitySpile");
        GameRegistry.registerTileEntity(ecru_TileEntityPlanter.class, "ecru_TileEntityPlanter");
        GameRegistry.registerTileEntity(ecru_TileEntityOreFlower.class, "ecru_TileEntityOreFlower");
        GameRegistry.registerTileEntity(ecru_TileEntityWineFaucet.class, "ecru_TileEntityWineFaucet");
        GameRegistry.registerTileEntity(ecru_TileEntityPersimmonWood.class, "ecru_TileEntityPersimmonWood");
        GameRegistry.registerTileEntity(ecru_TileEntityGatherItems.class, "TileEntityGatherItems");
        GameRegistry.registerTileEntity(ecru_TileEntityMortar.class, "ecru_TileEntityMortar");
        GameRegistry.registerTileEntity(ecru_TileEntityGrainDryer.class, "ecru_TileEntityGrainDryer");
        GameRegistry.registerTileEntity(ecru_TileEntityTeuchiUdon.class, "ecru_TileEntityTeuchiUdon");
        GameRegistry.registerTileEntity(ecru_TileEntityCompost.class, "ecru_TileEntityCompost");
        proxy.registerRenderInformation();
        ecru_Recipe.recipeSet();
        proxy.registerRenderers();
        proxy.registerComponents();
    }

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event) throws NumberFormatException {
        loadConfiguration.load(event);
        if (decoTextureID1 < 0 || decoTextureID1 > texList1.length - 1) {
            decoTextureID1 = 0;
        }
        if (decoTextureID2 < 0 || decoTextureID2 > texList2.length - 1) {
            decoTextureID2 = 0;
        }
        if (decoTextureID3 < 0 || decoTextureID3 > texList3.length - 1) {
            decoTextureID3 = 0;
        }
        LighthouseIlluminationColor = colorCheck(LighthouseIlluminationColorStr);
        if (LighthouseIlluminationColor < 0 || LighthouseIlluminationColor > 16777215) {
            LighthouseIlluminationColor = 16776960;
        }
        if (LighthouseIlluminationLength < 5 || LighthouseIlluminationLength > 30) {
            LighthouseIlluminationLength = 30;
        }
        if (LighthouseIlluminationWidth < 1 || LighthouseIlluminationWidth > 10) {
            LighthouseIlluminationWidth = 6;
        }
        if (LighthouseIlluminationTransparency < 30 || LighthouseIlluminationTransparency > 255) {
            LighthouseIlluminationTransparency = 255;
        }
        if (LighthouseIlluminationViewMode < 0 || LighthouseIlluminationViewMode > 1) {
            LighthouseIlluminationViewMode = 1;
        }
        if (LighthouseIlluminationPolygonCount < 3 || LighthouseIlluminationPolygonCount > 10) {
            LighthouseIlluminationPolygonCount = 5;
        }
        SLightColor = colorCheck(SLightColorStr);
        if (SLightColor < 0 || SLightColor > 16777215) {
            SLightColor = 16776960;
        }
        if (SLightLength < 5 || SLightLength > 30) {
            SLightLength = 30;
        }
        if (SLightWidth < 1 || SLightWidth > 10) {
            SLightWidth = 6;
        }
        if (SLightTransparency < 30 || SLightTransparency > 255) {
            SLightTransparency = 255;
        }
        if (momijiActivityField < 10 || momijiActivityField > 22) {
            momijiActivityField = 18;
        }
        if (momijiDespawnTime < 2 || momijiDespawnTime > 20) {
            momijiDespawnTime = 2;
        }
        if (momijiSpawnRate < 0 || momijiSpawnRate > 50) {
            momijiSpawnRate = 20;
        }
        if (LogBlockFellingMAX < 1 || LogBlockFellingMAX > 3000) {
            LogBlockFellingMAX = 200;
        }
        if (momijiCutAllMode < 0 || momijiCutAllMode > 1) {
            momijiCutAllMode = 0;
        }
        if (walkingFrequency < 1 || walkingFrequency > 100) {
            walkingFrequency = 30;
        }
        if (helpTip_posY < 0 || helpTip_posY > 100) {
            helpTip_posY = 60;
        }
        renderID = proxy.getNewRenderType();
        renderBonfireID = proxy.getNewRenderType();
        renderDecorationID = proxy.getNewRenderType();
        renderLeafFenceID = proxy.getNewRenderType();
        renderCauldronID = proxy.getNewRenderType();
        renderSpileID = proxy.getNewRenderType();
        renderVanillaID = proxy.getNewRenderType();
        renderGrapeID = proxy.getNewRenderType();
        renderPlanterID = proxy.getNewRenderType();
        renderOreFlowerID = proxy.getNewRenderType();
        renderStoneMortarID = proxy.getNewRenderType();
        renderEngineID = proxy.getNewRenderType();
        renderSunFlowerID = proxy.getNewRenderType();
        renderPetalID = proxy.getNewRenderType();
        renderWhiteFenceID = proxy.getNewRenderType();
        renderLighthouseIlluminationID = proxy.getNewRenderType();
        renderTomatoID = proxy.getNewRenderType();
        renderEggplantID = proxy.getNewRenderType();
        renderOnionID = proxy.getNewRenderType();
        renderSprinklerID = proxy.getNewRenderType();
        renderFountainID = proxy.getNewRenderType();
        renderOreBlockID = proxy.getNewRenderType();
        renderMiniStairs1ID = proxy.getNewRenderType();
        renderCookPotID = proxy.getNewRenderType();
        renderCabbageID = proxy.getNewRenderType();
        renderSLightID = proxy.getNewRenderType();
        renderGrainHopperID = proxy.getNewRenderType();
        renderBiofuelPDID = proxy.getNewRenderType();
        renderDecorationJewelID = proxy.getNewRenderType();
        renderJapaneseRadishID = proxy.getNewRenderType();
        renderPepperID = proxy.getNewRenderType();
        renderSpiceID = proxy.getNewRenderType();
        renderHumanPowerDriveID = proxy.getNewRenderType();
        renderPowerShaftGearID = proxy.getNewRenderType();
        renderPowerShaftID = proxy.getNewRenderType();
        renderGrapeStompTubID = proxy.getNewRenderType();
        renderWineBarrelID = proxy.getNewRenderType();
        renderWineFaucetID = proxy.getNewRenderType();
        renderPersimmonID = proxy.getNewRenderType();
        renderGatherItemsID = proxy.getNewRenderType();
        renderDriedPersimmonID = proxy.getNewRenderType();
        renderMortarID = proxy.getNewRenderType();
        renderThinWoodID = proxy.getNewRenderType();
        renderGrainDryerID = proxy.getNewRenderType();
        renderKelpID = proxy.getNewRenderType();
        renderSunDryingID = proxy.getNewRenderType();
        renderTeuchiUdonID = proxy.getNewRenderType();
        renderCompostID = proxy.getNewRenderType();
        shearsHarvestTargetBlock = ecru_util.ShearsTargetBlock.values();
        addCropsTargetBlock = ecru_util.addCropsBlock.values();
        logAndSapling = ecru_util.logAndSapling.values();
        excludeSeedsListMake(excludeSeedsList);
        addLogInfo(addLogList);
        if (entityId == -1) {
            entityId = EntityRegistry.findGlobalUniqueEntityId();
        }
        EntityRegistry.registerGlobalEntityID(ecru_EntityMomiji.class, "ecru_EntityMomiji", entityId, 16711799, 16776960);
        EntityRegistry.registerModEntity(ecru_EntityMomiji.class, "ecru_EntityMomiji", entityId, this, 250, 1, true);
        if (momijiSpawnRate > 0) {
            EntityRegistry.addSpawn(ecru_EntityMomiji.class, momijiSpawnRate, 1, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.field_76772_c, BiomeGenBase.field_76770_e, BiomeGenBase.field_150580_W});
        }
        entityGachaId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(ecru_EntityGacha.class, "ecru_EntityGacha", entityGachaId, this, 250, 1, true);
        mapleTreeRegistryBlock.registry();
        mapleTreeRegistryItem.registry();
        GameRegistry.registerFuelHandler(new ecru_fuelHandler());
        if (GrassDropsSeeds) {
            MinecraftForge.addGrassSeed(new ItemStack(Item_tomatoSeeds), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_eggplantSeeds), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_onionSeeds), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_AzukiBeans), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_unhulledStickyRice), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_rapeSeeds), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_cabbageSeeds), 1);
            MinecraftForge.addGrassSeed(new ItemStack(Item_japaneseRadishSeeds), 1);
        }
        chest(new ItemStack(Item_foodstuff, 1, 2), 2, 4, 6);
        chest(new ItemStack(Item_jewel, 1, 0), 1, 1, 2);
        chest(new ItemStack(Item_foodstuff, 1, 11), 1, 1, 3);
        chest(new ItemStack(Item_foodstuff, 1, 12), 1, 1, 2);
        chest(new ItemStack(Item_foodstuff, 1, 31), 1, 1, 4);
        chest(new ItemStack(Item_foodsDish, 1, 58), 1, 1, 3);
        toolMaterialMapleDiamond.customCraftingMaterial = Item_jewelMapleDia;
        toolMaterialKnife.customCraftingMaterial = Item_jewelMapleDia;
        WeightedRandomFishable fishing = new WeightedRandomFishable(new ItemStack(Item_foodsDish, 1, 33), 25);
        FishingHooks.addFish(fishing);
        ItemStack iCurry12 = new ItemStack(Items.field_151164_bB, 1, 0);
        ecru_CreateReciprBook crb12 = new ecru_CreateReciprBook();
        crb12.writeRecipe(iCurry12, 0, 1, true, 12);
        WeightedRandomFishable fishing2 = new WeightedRandomFishable(iCurry12, 1);
        FishingHooks.addFish(fishing2);
        ItemStack iCurry13 = new ItemStack(Items.field_151164_bB, 1, 0);
        ecru_CreateReciprBook crb13 = new ecru_CreateReciprBook();
        crb13.writeRecipe(iCurry13, 0, 1, true, 13);
        WeightedRandomFishable fishing3 = new WeightedRandomFishable(iCurry13, 1);
        FishingHooks.addFish(fishing3);
        ItemStack iCurry14 = new ItemStack(Items.field_151164_bB, 1, 0);
        ecru_CreateReciprBook crb14 = new ecru_CreateReciprBook();
        crb14.writeRecipe(iCurry14, 0, 1, true, 14);
        WeightedRandomFishable fishing4 = new WeightedRandomFishable(iCurry14, 1);
        FishingHooks.addFish(fishing4);
    }

    private void chest(ItemStack item, int min, int max, int rare) {
        ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(item, min, max, rare));
        ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(item, min, max, rare));
        ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(item, min, max, rare));
        ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(item, min, max, rare));
        ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(item, min, max, rare));
    }

    public static class cLoader {
        @SubscribeEvent
        public void onChunkLoad(ChunkEvent.Load ld) {
            if (!mod_ecru_MapleTree.TreeDeleted) {
                return;
            }
            World world = ld.world;
            Chunk chunk = ld.getChunk();
            if (!world.field_72995_K && chunk.field_76636_d) {
                for (int y = 0; y < 255; y++) {
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            if (chkTree(chunk.func_150810_a(x, y, z))) {
                                chunk.func_150807_a(x, y, z, Blocks.field_150350_a, 0);
                                chunk.func_150805_f(x, y, z);
                            }
                        }
                    }
                }
            }
        }

        boolean chkTree(Block id) {
            if (id == mod_ecru_MapleTree.blockMapleWood || id == mod_ecru_MapleTree.blockMapleWoodSyrup || id == mod_ecru_MapleTree.blockMapleLeaves || id == mod_ecru_MapleTree.blockFallenLeaves || id == mod_ecru_MapleTree.blockChestnutsBurrs) {
                return true;
            }
            return false;
        }
    }

    private int colorCheck(String liColor) throws NumberFormatException {
        if (liColor.length() != 6) {
            liColor = "FFFF00";
        } else {
            Pattern p = Pattern.compile("^[0-9A-Fa-f]+$");
            Matcher m = p.matcher(liColor);
            if (!m.find()) {
                liColor = "FFFF00";
            }
        }
        String tmp = liColor.substring(0, 6);
        int Color = Integer.parseInt(tmp, 16);
        return Color;
    }

    private void excludeSeedsListMake(String strNumbers) throws NumberFormatException {
        int meta;
        String[] strValues = strNumbers.split(",");
        ArrayList id = new ArrayList();
        int[] iArr = new int[id.size()];
        for (String str : strValues) {
            String[] tmp = str.split(";", 2);
            String seedId = tmp[0];
            Pattern p = Pattern.compile("^[0-9A-Za-z-_.:]+$");
            Matcher m = p.matcher(tmp[0]);
            if (m.find()) {
                if (tmp.length == 1) {
                    meta = 0;
                } else {
                    Pattern patt = Pattern.compile("^[0-9]+$");
                    Matcher mat = patt.matcher(tmp[1]);
                    if (mat.find()) {
                        meta = Integer.parseInt(tmp[1], 10);
                    } else {
                        meta = 0;
                    }
                }
                excludeSeedsId.add(seedId);
                excludeSeedsMeta.add(Integer.valueOf(meta));
            }
        }
        boolean flg = false;
        for (int i = 0; i < excludeSeedsId.size(); i++) {
            if (excludeSeedsId.get(i).equals("mod_ecru_MapleTree:unhulledStickyRice")) {
                flg = true;
            }
        }
        if (!flg) {
            excludeSeedsId.add("mod_ecru_MapleTree:unhulledStickyRice");
            excludeSeedsMeta.add(0);
        }
        System.out.println("==== MapleTree exclude Seeds List ====");
        for (int i2 = 0; i2 < excludeSeedsId.size(); i2++) {
            System.out.println("seed:" + excludeSeedsId.get(i2) + "/ meta:" + excludeSeedsMeta.get(i2));
        }
        System.out.println("======================================");
    }

    private void addLogInfo(String strNumbers) {
        String[] strValues = strNumbers.split(",");
        ArrayList id = new ArrayList();
        int[] iArr = new int[id.size()];
        for (String str : strValues) {
            String[] tmp = str.split(";", 4);
            if (tmp.length >= 1 && tmp[0] != null) {
                Pattern p = Pattern.compile("^[0-9A-Za-z-_.:]+$");
                Matcher m = p.matcher(tmp[0]);
                if (m.find()) {
                    logId.add(tmp[0]);
                } else {
                    logId.add("");
                }
            } else {
                logId.add("");
            }
            if (tmp.length >= 2 && tmp[1] != null) {
                Pattern patt = Pattern.compile("^[0-9]+$");
                Matcher mat = patt.matcher(tmp[1]);
                if (mat.find()) {
                    logMeta.add(Integer.valueOf(Integer.parseInt(tmp[1], 10)));
                } else {
                    logMeta.add(0);
                }
            } else {
                logMeta.add(0);
            }
            if (tmp.length >= 3 && tmp[2] != null) {
                Pattern p2 = Pattern.compile("^[0-9A-Za-z-_.:]+$");
                Matcher m2 = p2.matcher(tmp[2]);
                if (m2.find()) {
                    saplingId.add(tmp[2]);
                } else {
                    saplingId.add("");
                }
            } else {
                saplingId.add("");
            }
            if (tmp.length >= 4 && tmp[3] != null) {
                Pattern patt2 = Pattern.compile("^[0-9]+$");
                Matcher mat2 = patt2.matcher(tmp[3]);
                if (mat2.find()) {
                    saplingMeta.add(Integer.valueOf(Integer.parseInt(tmp[3], 10)));
                } else {
                    saplingMeta.add(0);
                }
            } else {
                saplingMeta.add(0);
            }
        }
        System.out.println("==== MapleTree List of planting log and sapling ====");
        for (int i = 0; i < logId.size(); i++) {
            System.out.println("Log(" + logId.get(i) + "/" + logMeta.get(i) + ") sapling(" + saplingId.get(i) + "/" + saplingMeta.get(i) + ")");
        }
        System.out.println("====================================================");
    }
}
