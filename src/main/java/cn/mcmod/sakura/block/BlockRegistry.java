package cn.mcmod.sakura.block;

import cn.mcmod.sakura.SakuraMod;
import cn.mcmod.sakura.block.crops.RiceCrop;
import cn.mcmod.sakura.block.crops.RiceCropRoot;
import cn.mcmod.sakura.block.foods.NabeBlock;
import cn.mcmod.sakura.block.foods.TeishokuBlock;
import cn.mcmod.sakura.block.foods.TeishokuFinishedBlock;
import cn.mcmod.sakura.block.machines.ChoppingBoardBlock;
import cn.mcmod.sakura.block.machines.CookingPotBlock;
import cn.mcmod.sakura.block.machines.DistillerBlock;
import cn.mcmod.sakura.block.machines.FermenterBlock;
import cn.mcmod.sakura.block.machines.StoneMortarBlock;
import cn.mcmod.sakura.client.particle.ParticleRegistry;
import cn.mcmod.sakura.item.ItemRegistry;
import cn.mcmod.sakura.level.tree.MapleTreeGrower;
import cn.mcmod.sakura.level.tree.SakuraTreeFeatures;
import cn.mcmod.sakura.level.tree.SakuraTreeGrower;
import cn.mcmod_mmf.mmlib.block.Age3CropBlock;
import cn.mcmod_mmf.mmlib.block.BaseCropBlock;
import cn.mcmod_mmf.mmlib.block.BaseHorizonBlock;
import cn.mcmod_mmf.mmlib.block.FacingSlab;
import cn.mcmod_mmf.mmlib.block.HighCropBlock;
import cn.mcmod_mmf.mmlib.item.info.FoodInfo;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            SakuraMod.MODID);

    public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakuraleaves",
            () -> new SakuraLeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.SAKURA_LEAF));

    public static final RegistryObject<Block> MAPLE_LEAVES_RED = BLOCKS.register("mapleleaves_red",
            () -> new SakuraLeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.RED_MAPLE_LEAF));
    public static final RegistryObject<Block> MAPLE_LEAVES_GREEN = BLOCKS.register("mapleleaves_green",
            () -> new SakuraLeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.GREEN_MAPLE_LEAF));
    public static final RegistryObject<Block> MAPLE_LEAVES_YELLOW = BLOCKS.register("mapleleaves_yellow",
            () -> new SakuraLeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.YELLOW_MAPLE_LEAF));
    public static final RegistryObject<Block> MAPLE_LEAVES_ORANGE = BLOCKS.register("mapleleaves_orange",
            () -> new SakuraLeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).randomTicks()
                    .sound(SoundType.GRASS).noOcclusion(), ParticleRegistry.ORANGE_MAPLE_LEAF));

    public static final RegistryObject<RotatedPillarBlock> SAKURA_LOG = BLOCKS.register("sakura_log",
            () -> log(MapColor.WOOD, MapColor.PODZOL));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SAKURA_LOG = BLOCKS.register("stripped_sakura_log",
            () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final RegistryObject<RotatedPillarBlock> SAKURA_WOOD = BLOCKS.register("sakura_wood",
            () -> log(MapColor.PODZOL, MapColor.PODZOL));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SAKURA_WOOD = BLOCKS
            .register("stripped_sakura_wood", () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final RegistryObject<SaplingBlock> SAKURA_SAPLING = BLOCKS.register("sakura_sapling",
            () -> sapling(new SakuraTreeGrower()));

    public static final RegistryObject<RotatedPillarBlock> MAPLE_LOG = BLOCKS.register("maple_log",
            MapleTreeLogBlock::new);

    public static final RegistryObject<RotatedPillarBlock> MAPLE_SAP_LOG = BLOCKS.register("maple_sap_log",
            MapleTreeSapLogBlock::new);

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_MAPLE_LOG = BLOCKS.register("stripped_maple_log",
            () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final RegistryObject<RotatedPillarBlock> MAPLE_WOOD = BLOCKS.register("maple_wood",
            () -> log(MapColor.PODZOL, MapColor.PODZOL));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_MAPLE_WOOD = BLOCKS.register("stripped_maple_wood",
            () -> log(MapColor.WOOD, MapColor.WOOD));

    public static final RegistryObject<RotatedPillarBlock> BAMBOO_BLOCK = BLOCKS.register("bamboo_block",
            BambooBlock::new);
    public static final RegistryObject<RotatedPillarBlock> BAMBOO_BLOCK_SUNBURNT = BLOCKS
            .register("bamboo_block_sunburnt", () -> simplebambooBlock(MapColor.SAND, MapColor.WOOD));
    public static final RegistryObject<RotatedPillarBlock> BAMBOO_CHARCOAL_BLOCK = BLOCKS.register(
            "bamboo_charcoal_block", () -> simplebambooBlock(MapColor.COLOR_GRAY, MapColor.COLOR_BLACK));

    public static final RegistryObject<Block> MAPLE_SAPLING_RED = BLOCKS.register("maple_sapling_red",
            () -> sapling(new MapleTreeGrower(SakuraTreeFeatures.MAPLE_RED_KEY, SakuraTreeFeatures.FANCY_MAPLE_RED_KEY)));
    public static final RegistryObject<Block> MAPLE_SAPLING_GREEN = BLOCKS.register("maple_sapling_green",
            () -> sapling(new MapleTreeGrower(SakuraTreeFeatures.MAPLE_GREEN_KEY, SakuraTreeFeatures.FANCY_MAPLE_GREEN_KEY)));
    public static final RegistryObject<Block> MAPLE_SAPLING_YELLOW = BLOCKS.register("maple_sapling_yellow",
            () -> sapling(new MapleTreeGrower(SakuraTreeFeatures.MAPLE_YELLOW_KEY, SakuraTreeFeatures.FANCY_MAPLE_YELLOW_KEY)));
    public static final RegistryObject<Block> MAPLE_SAPLING_ORANGE = BLOCKS.register("maple_sapling_orange",
            () -> sapling(new MapleTreeGrower(SakuraTreeFeatures.MAPLE_ORANGE_KEY, SakuraTreeFeatures.FANCY_MAPLE_ORANGE_KEY)));

    public static final RegistryObject<Block> BAMBOO_PLANT = BLOCKS.register("bamboo_plant", BambooPlant::new);
    public static final RegistryObject<Block> BAMBOOSHOOT = BLOCKS.register("bamboo_shoot", BambooShoot::new);

    public static final RegistryObject<Block> SAKURA_PLANK = BLOCKS.register("plank_sakura",
            () -> plank(MapColor.WOOD));
    public static final RegistryObject<Block> MAPLE_PLANK = BLOCKS.register("plank_maple",
            () -> plank(MapColor.SAND));
    public static final RegistryObject<Block> BAMBOO_PLANK = BLOCKS.register("plank_bamboo",
            () -> plank(MapColor.SAND));
    
    public static final RegistryObject<Block> STRAW_BLOCK = BLOCKS.register("straw_block",
            () -> new Block(Block.Properties.copy(Blocks.HAY_BLOCK)));

    public static final RegistryObject<Block> TATAMI = BLOCKS.register("tatami",
            () -> new TatamiBlock(Block.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistryObject<Block> TATAMI_WAXED = BLOCKS.register("tatami_waxed",
            () -> new BaseHorizonBlock(Block.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistryObject<Block> TATAMI_SUNBURNT = BLOCKS.register("tatami_sunburnt",
            () -> new BaseHorizonBlock(Block.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistryObject<FacingSlab> TATAMI_SLAB = BLOCKS.register("tatami_slab",
            () -> new TatamiSlabBlock(Block.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistryObject<FacingSlab> TATAMI_SLAB_WAXED = BLOCKS.register("tatami_slab_waxed",
            () -> new FacingSlab(Block.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistryObject<FacingSlab> TATAMI_SLAB_SUNBURNT = BLOCKS.register("tatami_slab_sunburnt",
            () -> new FacingSlab(Block.Properties.copy(Blocks.HAY_BLOCK)));

    public static final RegistryObject<Block> RICE_CROP_ROOT = BLOCKS.register("rice_crop_root",
            () -> new RiceCropRoot(Block.Properties.copy(Blocks.WHEAT).strength(0.2F)));
    public static final RegistryObject<Block> RICE_CROP = BLOCKS.register("rice_crop",
            () -> new RiceCrop(Block.Properties.copy(Blocks.WHEAT).strength(0.2F)));

    public static final RegistryObject<Block> CABBAGE_CROP = BLOCKS.register("cabbage_crop",
            () -> new BaseCropBlock(Block.Properties.copy(Blocks.CARROTS).strength(0.2F), ItemRegistry.CABBAGE_SEEDS));

    public static final RegistryObject<Block> RADISH_CROP = BLOCKS.register("radish_crop",
            () -> new Age3CropBlock(Block.Properties.copy(Blocks.CARROTS).strength(0.2F), ItemRegistry.RADISH_SEEDS));

    public static final RegistryObject<Block> ONION_CROP = BLOCKS.register("onion_crop",
            () -> new Age3CropBlock(Block.Properties.copy(Blocks.CARROTS).strength(0.2F), ItemRegistry.ONION_SEEDS));

    public static final RegistryObject<Block> REDBEAN_CROP = BLOCKS.register("redbean_crop",
            () -> new Age3CropBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F), ItemRegistry.RED_BEAN));
    
    public static final RegistryObject<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop",
            () -> new Age3CropBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F), ItemRegistry.SOYBEAN));

    public static final RegistryObject<Block> RAPESEED_CROP = BLOCKS.register("rapeseed_crop",
            () -> new BaseCropBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F), ItemRegistry.RAPESEEDS));

    public static final RegistryObject<Block> BUCKWHEAT_CROP = BLOCKS.register("buckwheat_crop",
            () -> new BaseCropBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F), ItemRegistry.BUCKWHEAT));

    public static final RegistryObject<Block> TARO_CROP = BLOCKS.register("taro_crop",
            () -> new Age3CropBlock(Block.Properties.copy(Blocks.WHEAT).strength(0.2F), ItemRegistry.TARO));

    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new HighCropBlock(Block.Properties.copy(Blocks.CARROTS).strength(0.2F), ItemRegistry.TOMATO_SEEDS));

    public static final RegistryObject<Block> EGGPLANT_CROP = BLOCKS.register("eggplant_crop",
            () -> new HighCropBlock(Block.Properties.copy(Blocks.CARROTS).strength(0.2F), ItemRegistry.EGGPLANT_SEEDS));

    public static final RegistryObject<Block> STONE_MORTAR = BLOCKS.register("stone_mortar", StoneMortarBlock::new);
    public static final RegistryObject<Block> COOKING_POT = BLOCKS.register("cooking_pot", CookingPotBlock::new);
    public static final RegistryObject<Block> FERMENTER = BLOCKS.register("fermenter", FermenterBlock::new);
    public static final RegistryObject<Block> DISTILLER = BLOCKS.register("distiller", DistillerBlock::new);
    public static final RegistryObject<Block> OBON = BLOCKS.register("obon", ObonBlock::new);
    public static final RegistryObject<Block> CHOPPING_BOARD = BLOCKS.register("chopping_board", ChoppingBoardBlock::new);
    public static final RegistryObject<Block> TEISHOUKU_FINISHED = BLOCKS.register("teishoku_finished", TeishokuFinishedBlock::new);
    public static final RegistryObject<Block> TEISHOUKU_FISH_SALT = BLOCKS.register("teishoku_fish_salt", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final RegistryObject<Block> TEISHOUKU_FISH_COOKED = BLOCKS.register("teishoku_fish_cooked", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(8, 0.8f).build()));
    public static final RegistryObject<Block> TEISHOUKU_FISH_RAW = BLOCKS.register("teishoku_fish_raw", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(6, 0.8f).build()));
    public static final RegistryObject<Block> TEISHOKO_TAMAGOYAKI = BLOCKS.register("teishoku_tamagoyaki", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(6, 0.8f).build()));
    public static final RegistryObject<Block> TEISHOKO_YAKINIKU = BLOCKS.register("teishoku_yakiniku", 
            ()->new TeishokuBlock(FoodInfo.builder().amountAndCalories(10, 0.8f).build()));
    
    public static final RegistryObject<Block> NABE_SUKIYAKI = BLOCKS.register("nabe_sukiyaki", 
            ()->new NabeBlock(FoodInfo.builder().amountAndCalories(12, 1f).build()));
    public static final RegistryObject<Block> NABE_ODEN = BLOCKS.register("nabe_oden", 
            ()->new NabeBlock(FoodInfo.builder().amountAndCalories(12, 1f).build()));
    
    private static RotatedPillarBlock log(MapColor top, MapColor bark) {
        return new RotatedPillarBlock(BlockBehaviour.Properties
                .copy(Blocks.OAK_LOG).mapColor( state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : bark))
                .strength(2.0F).sound(SoundType.WOOD));
    }

    private static SaplingBlock sapling(AbstractTreeGrower tree) {
        return new SaplingBlock(tree, BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().randomTicks()
                .instabreak().sound(SoundType.GRASS));
    }

    private static RotatedPillarBlock simplebambooBlock(MapColor top, MapColor bark) {
        return new RotatedPillarBlock(BlockBehaviour.Properties
                .copy(Blocks.BAMBOO).mapColor(state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : bark))

                .strength(2.0F).sound(SoundType.BAMBOO));
    }

    private static Block plank(MapColor material_color) {
        return new Block(
                BlockBehaviour.Properties.copy(Blocks.OAK_LOG).mapColor(material_color).strength(2.0F, 3.0F).sound(SoundType.WOOD));
    }

}
