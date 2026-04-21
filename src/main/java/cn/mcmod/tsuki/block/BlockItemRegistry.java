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
    public static final DeferredItem<Item> UME_LOG = ITEMS.register("ume_log",
            () -> new BlockItem(BlockRegistry.UME_LOG.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STRIPPED_UME_LOG = ITEMS.register("stripped_ume_log",
            () -> new BlockItem(BlockRegistry.STRIPPED_UME_LOG.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> UME_WOOD = ITEMS.register("ume_wood",
            () -> new BlockItem(BlockRegistry.UME_WOOD.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STRIPPED_UME_WOOD = ITEMS.register("stripped_ume_wood",
            () -> new BlockItem(BlockRegistry.STRIPPED_UME_WOOD.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> BAMBOO_BLOCK = ITEMS.register("bamboo_block",
            () -> new BlockItem(BlockRegistry.BAMBOO_BLOCK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_BLOCK_SUNBURNT = ITEMS.register("bamboo_block_sunburnt",
            () -> new BlockItem(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_CHARCOAL_BLOCK = ITEMS.register("bamboo_charcoal_block",
            () -> new BlockItem(BlockRegistry.BAMBOO_CHARCOAL_BLOCK.get(), Tsuki.defaultItemProperties()));

    // TODO: Bad. Need Data Fix.
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
    public static final DeferredItem<Item> TATAMI_CARPET = ITEMS.register("tatami_carpet",
            () -> new BlockItem(BlockRegistry.TATAMI_CARPET.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TATAMI_CARPET_WAXED = ITEMS.register("tatami_ns_carpet",
            () -> new BlockItem(BlockRegistry.TATAMI_CARPET_WAXED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TATAMI_CARPET_TAN = ITEMS.register("tatami_tan_carpet",
            () -> new BlockItem(BlockRegistry.TATAMI_CARPET_TAN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TATAMI_CARPET_TAN_WAXED = ITEMS.register("tatami_tan_ns_carpet",
            () -> new BlockItem(BlockRegistry.TATAMI_CARPET_TAN_WAXED.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> STRAW_BLOCK = ITEMS.register("straw_block",
            () -> new BlockItem(BlockRegistry.STRAW_BLOCK.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAKURA_DIAMOND_ORE = ITEMS.register("sakura_diamond_ore",
            () -> new BlockItem(BlockRegistry.SAKURA_DIAMOND_ORE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> DEEPSLATE_SAKURA_DIAMOND_ORE = ITEMS.register("deepslate_sakura_diamond_ore",
            () -> new BlockItem(BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAKURA_DIAMOND_BLOCK = ITEMS.register("sakura_diamond_block",
            () -> new BlockItem(BlockRegistry.SAKURA_DIAMOND_BLOCK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> IRON_SAND = ITEMS.register("iron_sand",
            () -> new BlockItem(BlockRegistry.IRON_SAND.get(), Tsuki.defaultItemProperties()));

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
    public static final DeferredItem<Item> UME_LEAVES = ITEMS.register("umeleaves",
            () -> new BlockItem(BlockRegistry.UME_LEAVES.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> FALLEN_LEAVES_RED = ITEMS.register("fallen_leaves_red",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_RED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FALLEN_LEAVES_ORANGE = ITEMS.register("fallen_leaves_orange",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_ORANGE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FALLEN_LEAVES_YELLOW = ITEMS.register("fallen_leaves_yellow",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_YELLOW.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FALLEN_LEAVES_GREEN = ITEMS.register("fallen_leaves_green",
            () -> new BlockItem(BlockRegistry.FALLEN_LEAVES_GREEN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MUSHROOM_FALLEN_LEAVES = ITEMS.register("fallen_leaves_mushroom",
            () -> new BlockItem(BlockRegistry.MUSHROOM_FALLEN_LEAVES.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MATSUTAKE_FALLEN_LEAVES = ITEMS.register("fallen_leaves_matsutake",
            () -> new BlockItem(BlockRegistry.MATSUTAKE_FALLEN_LEAVES.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> CHESTNUT_BURRS = ITEMS.register("chestnut_burrs",
            () -> new BlockItem(BlockRegistry.CHESTNUT_BURR.get(), Tsuki.defaultItemProperties()));

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
    public static final DeferredItem<Item> UME_SAPLING = ITEMS.register("ume_sapling",
            () -> new BlockItem(BlockRegistry.UME_SAPLING.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SHOJI = ITEMS.register("shoji",
            () -> new BlockItem(BlockRegistry.SHOJI.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SHOJI_1 = ITEMS.register("shoji_1",
            () -> new BlockItem(BlockRegistry.SHOJI_1.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SHOJI_2 = ITEMS.register("shoji_2",
            () -> new BlockItem(BlockRegistry.SHOJI_2.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SHOJI_3 = ITEMS.register("shoji_3",
            () -> new BlockItem(BlockRegistry.SHOJI_3.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SHOJI_4 = ITEMS.register("shoji_4",
            () -> new BlockItem(BlockRegistry.SHOJI_4.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SHOJI_5 = ITEMS.register("shoji_5",
            () -> new BlockItem(BlockRegistry.SHOJI_5.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA = ITEMS.register("kawara",
            () -> new BlockItem(BlockRegistry.KAWARA.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA_BLOCK = ITEMS.register("kawara_block",
            () -> new BlockItem(BlockRegistry.KAWARA_BLOCK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA_BLOCK_ALTER = ITEMS.register("kawara_block_alter",
            () -> new BlockItem(BlockRegistry.KAWARA_BLOCK_ALTER.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA_STAIRS = ITEMS.register("kawara_stairs",
            () -> new BlockItem(BlockRegistry.KAWARA_STAIRS.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA_STAIRS_ALTER = ITEMS.register("kawara_stairs_alter",
            () -> new BlockItem(BlockRegistry.KAWARA_STAIRS_ALTER.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA_SLAB = ITEMS.register("kawara_slab",
            () -> new BlockItem(BlockRegistry.KAWARA_SLAB.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KAWARA_SLAB_ALTER = ITEMS.register("kawara_slab_alter",
            () -> new BlockItem(BlockRegistry.KAWARA_SLAB_ALTER.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STONE_LANTERN = ITEMS.register("stone_lantern",
            () -> new BlockItem(BlockRegistry.STONE_LANTERN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> COBBLESTONE_LANTERN = ITEMS.register("cobblestone_lantern",
            () -> new BlockItem(BlockRegistry.COBBLESTONE_LANTERN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MOSSY_STONE_LANTERN = ITEMS.register("mossy_stone_lantern",
            () -> new BlockItem(BlockRegistry.MOSSY_STONE_LANTERN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> RED_LANTERN = ITEMS.register("red_lantern",
            () -> new BlockItem(BlockRegistry.RED_LANTERN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> WHITE_LANTERN = ITEMS.register("white_lantern",
            () -> new BlockItem(BlockRegistry.WHITE_LANTERN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_LANTERN = ITEMS.register("bamboo_lantern",
            () -> new BlockItem(BlockRegistry.BAMBOO_LANTERN.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> WINDBELL = ITEMS.register("windbell",
            () -> new BlockItem(BlockRegistry.WINDBELL.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> ANDON = ITEMS.register("andon",
            () -> new BlockItem(BlockRegistry.ANDON.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> ZABUTON = ITEMS.register("zabuton",
            () -> new BlockItem(BlockRegistry.ZABUTON.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> FUTON = ITEMS.register("futon",
            () -> new BlockItem(BlockRegistry.FUTON.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TAIKO = ITEMS.register("taiko",
            () -> new BlockItem(BlockRegistry.TAIKO.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> NOREN_WHITE = ITEMS.register("noren_white",
            () -> new BlockItem(BlockRegistry.NOREN_WHITE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> NOREN_BLUE = ITEMS.register("noren_blue",
            () -> new BlockItem(BlockRegistry.NOREN_BLUE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> NOREN_PINK = ITEMS.register("noren_pink",
            () -> new BlockItem(BlockRegistry.NOREN_PINK.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_FENCE = ITEMS.register("bamboo_fence",
            () -> new BlockItem(BlockRegistry.BAMBOO_FENCE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_FENCE_SUNBURNT = ITEMS.register("bamboo_fence_sunburnt",
            () -> new BlockItem(BlockRegistry.BAMBOO_FENCE_SUNBURNT.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> BAMBOO_DOOR = ITEMS.register("bamboo_door",
            () -> new BlockItem(BlockRegistry.BAMBOO_DOOR.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> KITUNEBI = ITEMS.register("kitunebi",
            () -> new BlockItem(BlockRegistry.KITUNEBI.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> TATARA = ITEMS.register("tatara",
            () -> new BlockItem(BlockRegistry.TATARA.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> MAPLE_SPILE = ITEMS.register("maple_spile",
            () -> new BlockItem(BlockRegistry.MAPLE_SPILE.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> MAPLE_CAULDRON = ITEMS.register("maple_cauldron",
            () -> new BlockItem(BlockRegistry.MAPLE_CAULDRON.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> PEPPER_SPLINT = ITEMS.register("pepper_splint",
            () -> new BlockItem(BlockRegistry.PEPPER_SPLINT.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> VANILLA_SPLINT = ITEMS.register("vanilla_splint",
            () -> new BlockItem(BlockRegistry.VANILLA_SPLINT.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> GRAPE_SPLINT_STAND = ITEMS.register("grape_splint_stand",
            () -> new BlockItem(BlockRegistry.GRAPE_SPLINT_STAND.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> GRAPE_SPLINT = ITEMS.register("grape_splint",
            () -> new BlockItem(BlockRegistry.GRAPE_SPLINT.get(), Tsuki.defaultItemProperties()));

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
    public static final DeferredItem<Item> TEISHOKU_TEMPURA = ITEMS.register("teishoku_tempura",
            () -> new BlockItem(BlockRegistry.TEISHOKU_TEMPURA.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TEISHOKU_FRIED = ITEMS.register("teishoku_fried",
            () -> new BlockItem(BlockRegistry.TEISHOKU_FRIED.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TEISHOKU_KATSU = ITEMS.register("teishoku_katsu",
            () -> new BlockItem(BlockRegistry.TEISHOKU_KATSU.get(), Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TEISHOKU_BURGER = ITEMS.register("teishoku_burger",
            () -> new BlockItem(BlockRegistry.TEISHOKU_BURGER.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> NABE_SUKIYAKI = ITEMS.register("nabe_sukiyaki",
            () -> new BlockItem(BlockRegistry.NABE_SUKIYAKI.get(), Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> NABE_ODEN = ITEMS.register("nabe_oden",
            () -> new BlockItem(BlockRegistry.NABE_ODEN.get(), Tsuki.defaultItemProperties()));
}
