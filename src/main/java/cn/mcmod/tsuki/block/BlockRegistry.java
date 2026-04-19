package cn.mcmod.tsuki.block;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.crops.GrapeLeavesBlock;
import cn.mcmod.tsuki.block.crops.GrapeSplintBlock;
import cn.mcmod.tsuki.block.crops.GrapeSplintStandBlock;
import cn.mcmod.tsuki.block.crops.GrapeVineBlock;
import cn.mcmod.tsuki.block.crops.HopsCropBlock;
import cn.mcmod.tsuki.block.crops.PepperCropBlock;
import cn.mcmod.tsuki.block.crops.PepperSplintBlock;
import cn.mcmod.tsuki.block.crops.RiceCrop;
import cn.mcmod.tsuki.block.crops.RiceCropRoot;
import cn.mcmod.tsuki.block.crops.VanillaCropBlock;
import cn.mcmod.tsuki.block.crops.VanillaSplintBlock;
import cn.mcmod.tsuki.block.crops.WildCropBlock;
import cn.mcmod.tsuki.block.foods.NabeBlock;
import cn.mcmod.tsuki.block.foods.TeishokuBlock;
import cn.mcmod.tsuki.block.foods.TeishokuFinishedBlock;
import cn.mcmod.tsuki.block.machines.ChoppingBoardBlock;
import cn.mcmod.tsuki.block.machines.CookingPotBlock;
import cn.mcmod.tsuki.block.machines.DistillerBlock;
import cn.mcmod.tsuki.block.machines.FermenterBlock;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.block.machines.TataraBlock;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.level.tree.TsukiTreeFeatures;
import cn.mcmod.mmlib.block.Age3CropBlock;
import cn.mcmod.mmlib.block.BaseCropBlock;
import cn.mcmod_mmf.mmlib.block.BaseHorizonBlock;
import cn.mcmod_mmf.mmlib.block.FacingSlab;
import cn.mcmod.mmlib.block.HighCropBlock;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import java.util.Optional;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.util.valueproviders.UniformInt;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Tsuki.MODID);

    public static final DeferredBlock<Block> SAKURA_LEAVES = BLOCKS.register("sakuraleaves",
            () -> new TsukiLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.SAKURA_LEAF));

    public static final DeferredBlock<Block> MAPLE_LEAVES_RED = BLOCKS.register("mapleleaves_red",
            () -> new TsukiLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.RED_MAPLE_LEAF));
    public static final DeferredBlock<Block> MAPLE_LEAVES_GREEN = BLOCKS.register("mapleleaves_green",
            () -> new TsukiLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.GREEN_MAPLE_LEAF));
    public static final DeferredBlock<Block> MAPLE_LEAVES_YELLOW = BLOCKS.register("mapleleaves_yellow",
            () -> new TsukiLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.YELLOW_MAPLE_LEAF));
    public static final DeferredBlock<Block> MAPLE_LEAVES_ORANGE = BLOCKS.register("mapleleaves_orange",
            () -> new TsukiLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.ORANGE_MAPLE_LEAF));
    public static final DeferredBlock<Block> UME_LEAVES = BLOCKS.register("umeleaves",
            () -> new UmeLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.GREEN_MAPLE_LEAF));

    public static final DeferredBlock<RotatedPillarBlock> SAKURA_LOG = BLOCKS.register("sakura_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SAKURA_LOG = BLOCKS.register("stripped_sakura_log",
            () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final DeferredBlock<RotatedPillarBlock> SAKURA_WOOD = BLOCKS.register("sakura_wood",
            () -> log(MapColor.PODZOL, MapColor.PODZOL));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SAKURA_WOOD = BLOCKS
            .register("stripped_sakura_wood", () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final DeferredBlock<SaplingBlock> SAKURA_SAPLING = BLOCKS.register("sakura_sapling",
            () -> sapling(new TreeGrower(
                    "sakura",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(TsukiTreeFeatures.SAKURA_KEY),
                    Optional.of(TsukiTreeFeatures.FANCY_SAKURA_KEY),
                    Optional.empty(),
                    Optional.empty())));

    public static final DeferredBlock<RotatedPillarBlock> MAPLE_LOG = BLOCKS.register("maple_log",
            MapleTreeLogBlock::new);

    public static final DeferredBlock<RotatedPillarBlock> MAPLE_SAP_LOG = BLOCKS.register("maple_sap_log",
            MapleTreeSapLogBlock::new);

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_MAPLE_LOG = BLOCKS.register("stripped_maple_log",
            () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final DeferredBlock<RotatedPillarBlock> MAPLE_WOOD = BLOCKS.register("maple_wood",
            () -> log(MapColor.PODZOL, MapColor.PODZOL));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_MAPLE_WOOD = BLOCKS.register("stripped_maple_wood",
            () -> log(MapColor.WOOD, MapColor.WOOD));
    public static final DeferredBlock<RotatedPillarBlock> UME_LOG = BLOCKS.register("ume_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_UME_LOG = BLOCKS.register("stripped_ume_log",
            () -> log(MapColor.WOOD, MapColor.WOOD));
    public static final DeferredBlock<RotatedPillarBlock> UME_WOOD = BLOCKS.register("ume_wood",
            () -> log(MapColor.PODZOL, MapColor.PODZOL));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_UME_WOOD = BLOCKS.register("stripped_ume_wood",
            () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final DeferredBlock<RotatedPillarBlock> BAMBOO_BLOCK = BLOCKS.register("bamboo_block",
            BambooBlock::new);
    public static final DeferredBlock<RotatedPillarBlock> BAMBOO_BLOCK_SUNBURNT = BLOCKS
            .register("bamboo_block_sunburnt", () -> simplebambooBlock(MapColor.SAND, MapColor.WOOD));
    public static final DeferredBlock<RotatedPillarBlock> BAMBOO_CHARCOAL_BLOCK = BLOCKS.register(
            "bamboo_charcoal_block", () -> simplebambooBlock(MapColor.COLOR_GRAY, MapColor.COLOR_BLACK));

    public static final DeferredBlock<Block> MAPLE_SAPLING_RED = BLOCKS.register("maple_sapling_red",
            () -> sapling(new TreeGrower(
                    "maple_red",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(TsukiTreeFeatures.MAPLE_RED_KEY),
                    Optional.of(TsukiTreeFeatures.FANCY_MAPLE_RED_KEY),
                    Optional.empty(),
                    Optional.empty())));
    public static final DeferredBlock<Block> MAPLE_SAPLING_GREEN = BLOCKS.register("maple_sapling_green",
            () -> sapling(new TreeGrower(
                    "maple_green",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(TsukiTreeFeatures.MAPLE_GREEN_KEY),
                    Optional.of(TsukiTreeFeatures.FANCY_MAPLE_GREEN_KEY),
                    Optional.empty(),
                    Optional.empty())));
    public static final DeferredBlock<Block> MAPLE_SAPLING_YELLOW = BLOCKS.register("maple_sapling_yellow",
            () -> sapling(new TreeGrower(
                    "maple_yellow",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(TsukiTreeFeatures.MAPLE_YELLOW_KEY),
                    Optional.of(TsukiTreeFeatures.FANCY_MAPLE_YELLOW_KEY),
                    Optional.empty(),
                    Optional.empty())));
    public static final DeferredBlock<Block> MAPLE_SAPLING_ORANGE = BLOCKS.register("maple_sapling_orange",
            () -> sapling(new TreeGrower(
                    "maple_orange",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(TsukiTreeFeatures.MAPLE_ORANGE_KEY),
                    Optional.of(TsukiTreeFeatures.FANCY_MAPLE_ORANGE_KEY),
                    Optional.empty(),
                    Optional.empty())));
    public static final DeferredBlock<SaplingBlock> UME_SAPLING = BLOCKS.register("ume_sapling",
            () -> sapling(new TreeGrower(
                    "ume",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(TsukiTreeFeatures.UME_KEY),
                    Optional.of(TsukiTreeFeatures.FANCY_UME_KEY),
                    Optional.empty(),
                    Optional.empty())));

    public static final DeferredBlock<Block> FALLEN_LEAVES_RED = BLOCKS.register("fallen_leaves_red",
            () -> new FallenLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion()));
    public static final DeferredBlock<Block> FALLEN_LEAVES_ORANGE = BLOCKS.register("fallen_leaves_orange",
            () -> new FallenLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion()));
    public static final DeferredBlock<Block> FALLEN_LEAVES_YELLOW = BLOCKS.register("fallen_leaves_yellow",
            () -> new FallenLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion()));
    public static final DeferredBlock<Block> FALLEN_LEAVES_GREEN = BLOCKS.register("fallen_leaves_green",
            () -> new FallenLeavesBlock(BlockBehaviour.Properties.of().strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion()));
    public static final DeferredBlock<Block> MUSHROOM_FALLEN_LEAVES = BLOCKS.register("fallen_leaves_mushroom",
            () -> new FallenLeavesMushroomBlock(FallenLeavesMushroomBlock.Type.MUSHROOM));
    public static final DeferredBlock<Block> MATSUTAKE_FALLEN_LEAVES = BLOCKS.register("fallen_leaves_matsutake",
            () -> new FallenLeavesMushroomBlock(FallenLeavesMushroomBlock.Type.MATSUTAKE));
    public static final DeferredBlock<Block> CHESTNUT_BURR = BLOCKS.register("chestnut_burr",
            () -> new ChestnutBurrBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .randomTicks()));

    public static final DeferredBlock<Block> BAMBOO_PLANT = BLOCKS.register("bamboo_plant", () -> new BambooPlant());
    public static final DeferredBlock<Block> BAMBOOSHOOT = BLOCKS.register("bamboo_shoot", () -> new BambooShoot());

    public static final DeferredBlock<Block> SAKURA_PLANK = BLOCKS.register("plank_sakura",
            () -> plank(MapColor.WOOD));
    public static final DeferredBlock<Block> MAPLE_PLANK = BLOCKS.register("plank_maple",
            () -> plank(MapColor.SAND));
    public static final DeferredBlock<Block> BAMBOO_PLANK = BLOCKS.register("plank_bamboo",
            () -> plank(MapColor.SAND));

    public static final DeferredBlock<Block> STRAW_BLOCK = BLOCKS.register("straw_block",
            () -> new Block(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<DropExperienceBlock> SAKURA_DIAMOND_ORE = BLOCKS.register("sakura_diamond_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_SAKURA_DIAMOND_ORE = BLOCKS.register(
            "deepslate_sakura_diamond_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
    public static final DeferredBlock<Block> SAKURA_DIAMOND_BLOCK = BLOCKS.register("sakura_diamond_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> IRON_SAND = BLOCKS.register("iron_sand",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(MapColor.COLOR_GRAY)));

    public static final DeferredBlock<Block> TATAMI = BLOCKS.register("tatami",
            () -> new TatamiBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TATAMI_WAXED = BLOCKS.register("tatami_waxed",
            () -> new BaseHorizonBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TATAMI_SUNBURNT = BLOCKS.register("tatami_sunburnt",
            () -> new BaseHorizonBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<FacingSlab> TATAMI_SLAB = BLOCKS.register("tatami_slab",
            () -> new TatamiSlabBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<FacingSlab> TATAMI_SLAB_WAXED = BLOCKS.register("tatami_slab_waxed",
            () -> new FacingSlab(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<FacingSlab> TATAMI_SLAB_SUNBURNT = BLOCKS.register("tatami_slab_sunburnt",
            () -> new FacingSlab(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TATAMI_CARPET = BLOCKS.register("tatami_carpet",
            () -> new CarpetBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).mapColor(MapColor.SAND)
                            .sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> TATAMI_CARPET_WAXED = BLOCKS.register("tatami_ns_carpet",
            () -> new CarpetBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).mapColor(MapColor.SAND)
                            .sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> TATAMI_CARPET_TAN = BLOCKS.register("tatami_tan_carpet",
            () -> new CarpetBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).mapColor(MapColor.SAND)
                            .sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> TATAMI_CARPET_TAN_WAXED = BLOCKS.register("tatami_tan_ns_carpet",
            () -> new CarpetBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).mapColor(MapColor.SAND)
                            .sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> RICE_CROP_ROOT = BLOCKS.register("rice_crop_root",
            () -> new RiceCropRoot(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F)));
    public static final DeferredBlock<Block> RICE_CROP = BLOCKS.register("rice_crop",
            () -> new RiceCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F)));

    public static final DeferredBlock<Block> CABBAGE_CROP = BLOCKS.register("cabbage_crop",
            () -> new BaseCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.CABBAGE_SEEDS));

    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.register("radish_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.RADISH_SEEDS));

    public static final DeferredBlock<Block> ONION_CROP = BLOCKS.register("onion_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.ONION_SEEDS));

    public static final DeferredBlock<Block> REDBEAN_CROP = BLOCKS.register("redbean_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F),
                    ItemRegistry.RED_BEAN));

    public static final DeferredBlock<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F),
                    ItemRegistry.SOYBEAN));

    public static final DeferredBlock<Block> RAPESEED_CROP = BLOCKS.register("rapeseed_crop",
            () -> new BaseCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F),
                    ItemRegistry.RAPESEEDS));

    public static final DeferredBlock<Block> BUCKWHEAT_CROP = BLOCKS.register("buckwheat_crop",
            () -> new BaseCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F),
                    ItemRegistry.BUCKWHEAT));

    public static final DeferredBlock<Block> TARO_CROP = BLOCKS.register("taro_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F),
                    ItemRegistry.TARO));

    public static final DeferredBlock<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new HighCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.TOMATO_SEEDS));

    public static final DeferredBlock<Block> EGGPLANT_CROP = BLOCKS.register("eggplant_crop",
            () -> new HighCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.EGGPLANT_SEEDS));

    public static final DeferredBlock<Block> PEPPER_CROP = BLOCKS.register("pepper_crop",
            () -> new PepperCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.PEPPER_SEEDS));
    public static final DeferredBlock<Block> VANILLA_CROP = BLOCKS.register("vanilla_crop",
            () -> new VanillaCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F),
                    ItemRegistry.VANILLA_SEEDS));
    public static final DeferredBlock<Block> HOPS_CROP = BLOCKS.register("hops_crop",
            () -> new HopsCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).strength(0.2F),
                    ItemRegistry.MATERIALS.get(TsukiNormalItemSet.HOP)));

    public static final DeferredBlock<Block> PEPPER_SPLINT = BLOCKS.register("pepper_splint",
            PepperSplintBlock::new);
    public static final DeferredBlock<Block> VANILLA_SPLINT = BLOCKS.register("vanilla_splint",
            VanillaSplintBlock::new);
    public static final DeferredBlock<Block> GRAPE_SPLINT_STAND = BLOCKS.register("grape_splint_stand",
            GrapeSplintStandBlock::new);
    public static final DeferredBlock<Block> GRAPE_VINE = BLOCKS.register("grape_vine",
            GrapeVineBlock::new);
    public static final DeferredBlock<Block> GRAPE_SPLINT = BLOCKS.register("grape_splint",
            GrapeSplintBlock::new);
    public static final DeferredBlock<Block> GRAPE_LEAVES = BLOCKS.register("grape_leaves",
            GrapeLeavesBlock::new);

    public static final DeferredBlock<Block> WILD_PEPPER = BLOCKS.register("wild_pepper",
            () -> new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F)));
    public static final DeferredBlock<Block> WILD_VANILLA = BLOCKS.register("wild_vanilla",
            () -> new WildCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS).strength(0.2F)));

    public static final DeferredBlock<Block> SHOJI = BLOCKS.register("shoji", () -> new ShojiBlock(0));
    public static final DeferredBlock<Block> SHOJI_1 = BLOCKS.register("shoji_1", () -> new ShojiBlock(1));
    public static final DeferredBlock<Block> SHOJI_2 = BLOCKS.register("shoji_2", () -> new ShojiBlock(2));
    public static final DeferredBlock<Block> SHOJI_3 = BLOCKS.register("shoji_3", () -> new ShojiBlock(3));
    public static final DeferredBlock<Block> SHOJI_4 = BLOCKS.register("shoji_4", () -> new ShojiBlock(4));
    public static final DeferredBlock<Block> SHOJI_5 = BLOCKS.register("shoji_5", () -> new ShojiBlock(5));
    public static final DeferredBlock<Block> KAWARA_BLOCK = BLOCKS.register("kawara_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(1.5F, 6.0F)
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> KAWARA = BLOCKS.register("kawara",
            () -> new StairBlock(KAWARA_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS) // Weird
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(1.5F, 6.0F)
                            .sound(SoundType.STONE)
                            .isValidSpawn((state, level, pos, type) -> false)
                            .isRedstoneConductor((state, level, pos) -> false)
                            .isSuffocating((state, level, pos) -> false)
                            .isViewBlocking((state, level, pos) -> false)));
    // Kawara block variants
    public static final DeferredBlock<Block> KAWARA_BLOCK_ALTER = BLOCKS.register("kawara_block_alter",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(1.5F, 6.0F)
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> KAWARA_STAIRS = BLOCKS.register("kawara_stairs",
            () -> new StairBlock(KAWARA_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(1.5F, 6.0F)
                            .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> KAWARA_STAIRS_ALTER = BLOCKS.register("kawara_stairs_alter",
            () -> new StairBlock(KAWARA_BLOCK_ALTER.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(1.5F, 6.0F)
                            .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> KAWARA_SLAB = BLOCKS.register("kawara_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(1.5F, 6.0F)
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> KAWARA_SLAB_ALTER = BLOCKS.register("kawara_slab_alter",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(1.5F, 6.0F)
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> STONE_LANTERN = BLOCKS.register("stone_lantern",
            () -> new CustomLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 15),
                    Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
    public static final DeferredBlock<Block> COBBLESTONE_LANTERN = BLOCKS.register("cobblestone_lantern",
            () -> new CustomLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 15),
                    Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
    public static final DeferredBlock<Block> MOSSY_STONE_LANTERN = BLOCKS.register("mossy_stone_lantern",
            () -> new CustomLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 15),
                    Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
    public static final DeferredBlock<Block> RED_LANTERN = BLOCKS.register("red_lantern",
            () -> new CustomLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.COLOR_RED)
                    .lightLevel(state -> 15),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)));
    public static final DeferredBlock<Block> WHITE_LANTERN = BLOCKS.register("white_lantern",
            () -> new CustomLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.SNOW)
                    .lightLevel(state -> 15),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)));
    public static final DeferredBlock<Block> BAMBOO_LANTERN = BLOCKS.register("bamboo_lantern",
            () -> new FloorMountedLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.SAND)
                    .sound(SoundType.BAMBOO)
                    .lightLevel(state -> 15),
                    Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D)));
    public static final DeferredBlock<Block> WINDBELL = BLOCKS.register("windbell",
            () -> new WindBellBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .mapColor(MapColor.METAL)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .noCollission(),
                    Block.box(6.0D, 8.0D, 6.0D, 10.0D, 16.0D, 10.0D),
                    Shapes.empty()));
    public static final DeferredBlock<Block> ANDON = BLOCKS.register("andon",
            () -> new CustomLanternBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(0.5F)
                    .sound(SoundType.WOOD)
                    .lightLevel(state -> 14)
                    .noOcclusion(),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)));
    public static final DeferredBlock<Block> ZABUTON = BLOCKS.register("zabuton",
            () -> new ZabutonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET)
                    .mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> FUTON = BLOCKS.register("futon", FutonBlock::new);
    public static final DeferredBlock<Block> TAIKO = BLOCKS.register("taiko", TaikoBlock::new);
    public static final DeferredBlock<Block> NOREN_WHITE = BLOCKS.register("noren_white",
            () -> new NorenBlock(MapColor.SNOW));
    public static final DeferredBlock<Block> NOREN_BLUE = BLOCKS.register("noren_blue",
            () -> new NorenBlock(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> NOREN_PINK = BLOCKS.register("noren_pink",
            () -> new NorenBlock(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> BAMBOO_FENCE = BLOCKS.register("bamboo_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)));
    public static final DeferredBlock<Block> BAMBOO_FENCE_SUNBURNT = BLOCKS.register("bamboo_fence_sunburnt",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)));
    public static final DeferredBlock<Block> BAMBOO_DOOR = BLOCKS.register("bamboo_door",
            () -> new DoorBlock(
                    BlockSetType.BAMBOO,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR)));

    public static final DeferredBlock<Block> KITUNEBI = BLOCKS.register("kitunebi", () -> new KitunebiBlock());

    public static final DeferredBlock<Block> TATARA = BLOCKS.register("tatara", () -> new TataraBlock());

    public static final DeferredBlock<Block> MAPLE_SPILE = BLOCKS.register("maple_spile", () -> new MapleSpileBlock());
    public static final DeferredBlock<Block> MAPLE_CAULDRON = BLOCKS.register("maple_cauldron",
            () -> new MapleCauldronBlock());

    public static final DeferredBlock<Block> STONE_MORTAR = BLOCKS.register("stone_mortar",
            () -> new StoneMortarBlock());
    public static final DeferredBlock<Block> COOKING_POT = BLOCKS.register("cooking_pot", () -> new CookingPotBlock());
    public static final DeferredBlock<Block> FERMENTER = BLOCKS.register("fermenter", () -> new FermenterBlock());
    public static final DeferredBlock<Block> DISTILLER = BLOCKS.register("distiller", () -> new DistillerBlock());
    public static final DeferredBlock<Block> OBON = BLOCKS.register("obon", () -> new ObonBlock());
    public static final DeferredBlock<Block> CHOPPING_BOARD = BLOCKS.register("chopping_board",
            () -> new ChoppingBoardBlock());
    public static final DeferredBlock<Block> TEISHOUKU_FINISHED = BLOCKS.register("teishoku_finished",
            TeishokuFinishedBlock::new);
    public static final DeferredBlock<Block> TEISHOUKU_FISH_SALT = BLOCKS.register("teishoku_fish_salt",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOUKU_FISH_COOKED = BLOCKS.register("teishoku_fish_cooked",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOUKU_FISH_RAW = BLOCKS.register("teishoku_fish_raw",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(6, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKO_TAMAGOYAKI = BLOCKS.register("teishoku_tamagoyaki",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(6, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKO_YAKINIKU = BLOCKS.register("teishoku_yakiniku",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(10, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKU_TEMPURA = BLOCKS.register("teishoku_tempura",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKU_FRIED = BLOCKS.register("teishoku_fried",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKU_KATSU = BLOCKS.register("teishoku_katsu",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(10, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKU_BURGER = BLOCKS.register("teishoku_burger",
            () -> new TeishokuBlock(FoodInfo.builder().amountAndCalories(10, 0.8f).build()));

    public static final DeferredBlock<Block> NABE_SUKIYAKI = BLOCKS.register("nabe_sukiyaki",
            () -> new NabeBlock(FoodInfo.builder().amountAndCalories(12, 1f).build()));
    public static final DeferredBlock<Block> NABE_ODEN = BLOCKS.register("nabe_oden",
            () -> new NabeBlock(FoodInfo.builder().amountAndCalories(12, 1f).build()));

    private static RotatedPillarBlock log(MapColor top, MapColor bark) {
        return new RotatedPillarBlock(BlockBehaviour.Properties
                .of().mapColor(state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : bark))
                .strength(2.0F).sound(SoundType.WOOD));
    }

    private static SaplingBlock sapling(TreeGrower tree) {
        return new SaplingBlock(tree, BlockBehaviour.Properties.of().noCollission().randomTicks()
                .instabreak().sound(SoundType.GRASS));
    }

    private static RotatedPillarBlock simplebambooBlock(MapColor top, MapColor bark) {
        return new RotatedPillarBlock(BlockBehaviour.Properties
                .of().mapColor(state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : bark))

                .strength(2.0F).sound(SoundType.BAMBOO));
    }

    private static Block plank(MapColor material_color) {
        return new Block(
                BlockBehaviour.Properties.of().mapColor(material_color).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    }

}
