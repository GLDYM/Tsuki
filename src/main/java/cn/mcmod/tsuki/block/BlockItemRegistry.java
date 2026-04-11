package cn.mcmod.tsuki.block;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.StoneMortarItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);

    public static final DeferredItem<Item> SAKURA_LOG = ITEMS.register("sakura_log",
            () -> new BlockItem(BlockRegistry.SAKURA_LOG.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> STRIPPED_SAKURA_LOG = ITEMS.register("stripped_sakura_log",
            () -> new BlockItem(BlockRegistry.STRIPPED_SAKURA_LOG.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_WOOD = ITEMS.register("sakura_wood",
            () -> new BlockItem(BlockRegistry.SAKURA_WOOD.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STRIPPED_SAKURA_WOOD = ITEMS.register("stripped_sakura_wood",
            () -> new BlockItem(BlockRegistry.STRIPPED_SAKURA_WOOD.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> MAPLE_LOG = ITEMS.register("maple_log",
            () -> new BlockItem(BlockRegistry.MAPLE_LOG.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_SAP_LOG = ITEMS.register("maple_sap_log",
            () -> new BlockItem(BlockRegistry.MAPLE_SAP_LOG.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STRIPPED_MAPLE_LOG = ITEMS.register("stripped_maple_log",
            () -> new BlockItem(BlockRegistry.STRIPPED_MAPLE_LOG.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> MAPLE_WOOD = ITEMS.register("maple_wood",
            () -> new BlockItem(BlockRegistry.MAPLE_WOOD.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STRIPPED_MAPLE_WOOD = ITEMS.register("stripped_maple_wood",
            () -> new BlockItem(BlockRegistry.STRIPPED_MAPLE_WOOD.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> BAMBOO_BLOCK = ITEMS.register("bamboo_block",
            () -> new BlockItem(BlockRegistry.BAMBOO_BLOCK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_BLOCK_SUNBURNT = ITEMS.register("bamboo_block_sunburnt",
            () -> new BlockItem(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_CHARCOAL_BLOCK = ITEMS.register("bamboo_charcoal_block",
            () -> new BlockItem(BlockRegistry.BAMBOO_CHARCOAL_BLOCK.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_PLANK = ITEMS.register("plank_sakura",
            () -> new BlockItem(BlockRegistry.SAKURA_PLANK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_PLANK = ITEMS.register("plank_maple",
            () -> new BlockItem(BlockRegistry.MAPLE_PLANK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_PLANK = ITEMS.register("plank_bamboo",
            () -> new BlockItem(BlockRegistry.BAMBOO_PLANK.get(), Tsuki.defaultItemProperties()));
    
    public static final DeferredItem<Item> TATAMI = ITEMS.register("tatami",
            () -> new BlockItem(BlockRegistry.TATAMI.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TATAMI_SLAB = ITEMS.register("tatami_slab",
            () -> new BlockItem(BlockRegistry.TATAMI_SLAB.get(), Tsuki.defaultItemProperties()));
    
    public static final DeferredItem<Item> TATAMI_WAXED = ITEMS.register("tatami_waxed",
            () -> new BlockItem(BlockRegistry.TATAMI_WAXED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TATAMI_SLAB_WAXED = ITEMS.register("tatami_slab_waxed",
            () -> new BlockItem(BlockRegistry.TATAMI_SLAB_WAXED.get(), Tsuki.defaultItemProperties()));
    
    public static final DeferredItem<Item> TATAMI_SUNBURNT = ITEMS.register("tatami_sunburnt",
            () -> new BlockItem(BlockRegistry.TATAMI_SUNBURNT.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TATAMI_SLAB_SUNBURNT = ITEMS.register("tatami_slab_sunburnt",
            () -> new BlockItem(BlockRegistry.TATAMI_SLAB_SUNBURNT.get(), Tsuki.defaultItemProperties()));
    
    public static final DeferredItem<Item> STRAW_BLOCK = ITEMS.register("straw_block",
            () -> new BlockItem(BlockRegistry.STRAW_BLOCK.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_DIAMOND_ORE = ITEMS.register("sakura_diamond_ore",
            () -> new BlockItem(BlockRegistry.SAKURA_DIAMOND_ORE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> DEEPSLATE_SAKURA_DIAMOND_ORE = ITEMS.register("deepslate_sakura_diamond_ore",
            () -> new BlockItem(BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_LEAVES = ITEMS.register("sakuraleaves",
            () -> new BlockItem(BlockRegistry.SAKURA_LEAVES.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> MAPLE_LEAVES_RED = ITEMS.register("mapleleaves_red",
            () -> new BlockItem(BlockRegistry.MAPLE_LEAVES_RED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_LEAVES_YELLOW = ITEMS.register("mapleleaves_yellow",
            () -> new BlockItem(BlockRegistry.MAPLE_LEAVES_YELLOW.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_LEAVES_ORANGE = ITEMS.register("mapleleaves_orange",
            () -> new BlockItem(BlockRegistry.MAPLE_LEAVES_ORANGE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_LEAVES_GREEN = ITEMS.register("mapleleaves_green",
            () -> new BlockItem(BlockRegistry.MAPLE_LEAVES_GREEN.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> FALLEN_LEAVES_RED = ITEMS.register("fallen_leaves_red",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_RED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FALLEN_LEAVES_ORANGE = ITEMS.register("fallen_leaves_orange",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_ORANGE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FALLEN_LEAVES_YELLOW = ITEMS.register("fallen_leaves_yellow",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_YELLOW.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FALLEN_LEAVES_GREEN = ITEMS.register("fallen_leaves_green",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_GREEN.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> BAMBOOSHOOT = ITEMS.register("bamboo_shoot",
            () -> new BlockItem(BlockRegistry.BAMBOOSHOOT.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_SAPLING = ITEMS.register("sakura_sapling",
            () -> new BlockItem(BlockRegistry.SAKURA_SAPLING.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> MAPLE_SAPLING_RED = ITEMS.register("maple_sapling_red",
            () -> new BlockItem(BlockRegistry.MAPLE_SAPLING_RED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_SAPLING_YELLOW = ITEMS.register("maple_sapling_yellow",
            () -> new BlockItem(BlockRegistry.MAPLE_SAPLING_YELLOW.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_SAPLING_ORANGE = ITEMS.register("maple_sapling_orange",
            () -> new BlockItem(BlockRegistry.MAPLE_SAPLING_ORANGE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_SAPLING_GREEN = ITEMS.register("maple_sapling_green",
            () -> new BlockItem(BlockRegistry.MAPLE_SAPLING_GREEN.get(), Tsuki.defaultItemProperties()));


    public static final DeferredItem<Item> KITUNEBI = ITEMS.register("kitunebi",
            () -> new BlockItem(BlockRegistry.KITUNEBI.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_SPILE = ITEMS.register("maple_spile",
            () -> new BlockItem(BlockRegistry.MAPLE_SPILE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_CAULDRON = ITEMS.register("maple_cauldron",
            () -> new BlockItem(BlockRegistry.MAPLE_CAULDRON.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STONE_MORTAR = ITEMS.register("stone_mortar", 
            StoneMortarItem::new);

    public static final DeferredItem<Item> COOKING_POT = ITEMS.register("cooking_pot",
            () -> new BlockItem(BlockRegistry.COOKING_POT.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> FERMENTER = ITEMS.register("fermenter",
            () -> new BlockItem(BlockRegistry.FERMENTER.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> DISTILLER = ITEMS.register("distiller",
            () -> new BlockItem(BlockRegistry.DISTILLER.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> OBON = ITEMS.register("obon",
            () -> new BlockItem(BlockRegistry.OBON.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> CHOPPING_BOARD = ITEMS.register("chopping_board",
            () -> new BlockItem(BlockRegistry.CHOPPING_BOARD.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> TEISHOUKU_FISH_RAW = ITEMS.register("teishoku_fish_raw",
            () -> new BlockItem(BlockRegistry.TEISHOUKU_FISH_RAW.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> TEISHOUKU_FISH_COOKED = ITEMS.register("teishoku_fish_cooked",
            () -> new BlockItem(BlockRegistry.TEISHOUKU_FISH_COOKED.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> TEISHOUKU_FISH_SALT = ITEMS.register("teishoku_fish_salt",
            () -> new BlockItem(BlockRegistry.TEISHOUKU_FISH_SALT.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> TEISHOKO_TAMAGOYAKI = ITEMS.register("teishoku_tamagoyaki",
            () -> new BlockItem(BlockRegistry.TEISHOKO_TAMAGOYAKI.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> TEISHOKO_YAKINIKU = ITEMS.register("teishoku_yakiniku",
            () -> new BlockItem(BlockRegistry.TEISHOKO_YAKINIKU.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> NABE_SUKIYAKI = ITEMS.register("nabe_sukiyaki",
            () -> new BlockItem(BlockRegistry.NABE_SUKIYAKI.get(), Tsuki.defaultItemProperties()));
    
        public static final DeferredItem<Item> NABE_ODEN = ITEMS.register("nabe_oden",
            () -> new BlockItem(BlockRegistry.NABE_ODEN.get(), Tsuki.defaultItemProperties()));
}


