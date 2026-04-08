package cn.mcmod.tsuki.block;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.crops.RiceCrop;
import cn.mcmod.tsuki.block.crops.RiceCropRoot;
import cn.mcmod.tsuki.block.foods.NabeBlock;
import cn.mcmod.tsuki.block.foods.TeishokuBlock;
import cn.mcmod.tsuki.block.foods.TeishokuFinishedBlock;
import cn.mcmod.tsuki.block.machines.ChoppingBoardBlock;
import cn.mcmod.tsuki.block.machines.CookingPotBlock;
import cn.mcmod.tsuki.block.machines.DistillerBlock;
import cn.mcmod.tsuki.block.machines.FermenterBlock;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.level.tree.TsukiTreeFeatures;
import cn.mcmod_mmf.mmlib.block.Age3CropBlock;
import cn.mcmod_mmf.mmlib.block.BaseCropBlock;
import cn.mcmod_mmf.mmlib.block.BaseHorizonBlock;
import cn.mcmod_mmf.mmlib.block.FacingSlab;
import cn.mcmod_mmf.mmlib.block.HighCropBlock;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import java.util.Optional;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
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
                    // TODO: 这树写的什么玩意？怎么写个鸡腿树？
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
            () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_SAKURA_DIAMOND_ORE = BLOCKS.register("deepslate_sakura_diamond_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));

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

    public static final DeferredBlock<Block> RICE_CROP_ROOT = BLOCKS.register("rice_crop_root",
            () -> new RiceCropRoot(BlockBehaviour.Properties.of().strength(0.2F)));
    public static final DeferredBlock<Block> RICE_CROP = BLOCKS.register("rice_crop",
            () -> new RiceCrop(BlockBehaviour.Properties.of().strength(0.2F)));

    public static final DeferredBlock<Block> CABBAGE_CROP = BLOCKS.register("cabbage_crop",
            () -> new BaseCropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.CABBAGE_SEEDS));

    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.register("radish_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.RADISH_SEEDS));

    public static final DeferredBlock<Block> ONION_CROP = BLOCKS.register("onion_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.ONION_SEEDS));

    public static final DeferredBlock<Block> REDBEAN_CROP = BLOCKS.register("redbean_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.RED_BEAN));
    
    public static final DeferredBlock<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.SOYBEAN));

    public static final DeferredBlock<Block> RAPESEED_CROP = BLOCKS.register("rapeseed_crop",
            () -> new BaseCropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.RAPESEEDS));

    public static final DeferredBlock<Block> BUCKWHEAT_CROP = BLOCKS.register("buckwheat_crop",
            () -> new BaseCropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.BUCKWHEAT));

    public static final DeferredBlock<Block> TARO_CROP = BLOCKS.register("taro_crop",
            () -> new Age3CropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.TARO));

    public static final DeferredBlock<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new HighCropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.TOMATO_SEEDS));

    public static final DeferredBlock<Block> EGGPLANT_CROP = BLOCKS.register("eggplant_crop",
            () -> new HighCropBlock(BlockBehaviour.Properties.of().strength(0.2F), ItemRegistry.EGGPLANT_SEEDS));

        public static final DeferredBlock<Block> STONE_MORTAR = BLOCKS.register("stone_mortar", () -> new StoneMortarBlock());
        public static final DeferredBlock<Block> COOKING_POT = BLOCKS.register("cooking_pot", () -> new CookingPotBlock());
        public static final DeferredBlock<Block> FERMENTER = BLOCKS.register("fermenter", () -> new FermenterBlock());
        public static final DeferredBlock<Block> DISTILLER = BLOCKS.register("distiller", () -> new DistillerBlock());
        public static final DeferredBlock<Block> OBON = BLOCKS.register("obon", () -> new ObonBlock());
        public static final DeferredBlock<Block> CHOPPING_BOARD = BLOCKS.register("chopping_board", () -> new ChoppingBoardBlock());
    public static final DeferredBlock<Block> TEISHOUKU_FINISHED = BLOCKS.register("teishoku_finished", TeishokuFinishedBlock::new);
    public static final DeferredBlock<Block> TEISHOUKU_FISH_SALT = BLOCKS.register("teishoku_fish_salt", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOUKU_FISH_COOKED = BLOCKS.register("teishoku_fish_cooked", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOUKU_FISH_RAW = BLOCKS.register("teishoku_fish_raw", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(6, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKO_TAMAGOYAKI = BLOCKS.register("teishoku_tamagoyaki", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(6, 0.8f).build()));
    public static final DeferredBlock<Block> TEISHOKO_YAKINIKU = BLOCKS.register("teishoku_yakiniku", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(10, 0.8f).build()));
    
    public static final DeferredBlock<Block> NABE_SUKIYAKI = BLOCKS.register("nabe_sukiyaki", 
            ()->new NabeBlock(FoodInfo.builder().amountAndCalories(12, 1f).build()));
    public static final DeferredBlock<Block> NABE_ODEN = BLOCKS.register("nabe_oden", 
            ()->new NabeBlock(FoodInfo.builder().amountAndCalories(12, 1f).build()));
    
    private static RotatedPillarBlock log(MapColor top, MapColor bark) {
        return new RotatedPillarBlock(BlockBehaviour.Properties
                                .of().mapColor( state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : bark))
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



