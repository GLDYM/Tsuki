package cn.mcmod.tsuki.init.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.JukeboxSongRegistry;
import cn.mcmod.tsuki.item.SakuraDiamondItem;
import cn.mcmod.tsuki.item.armor.HaoriItem;
import cn.mcmod.tsuki.item.armor.KimonoItem;
import cn.mcmod.tsuki.item.armor.SamuraiItem;
import cn.mcmod.tsuki.item.armor.SoldierItem;
import cn.mcmod.tsuki.item.armor.StrawHatItem;
import cn.mcmod.tsuki.item.magatama.MagatamaBlueItem;
import cn.mcmod.tsuki.item.magatama.MagatamaOrangeItem;
import cn.mcmod.tsuki.item.magatama.MagatamaRedItem;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteItem;
import cn.mcmod.tsuki.item.tool.BroomItem;
import cn.mcmod.tsuki.item.tool.HammerItem;
import cn.mcmod.tsuki.item.tool.KatanaItem;
import cn.mcmod.tsuki.item.tool.KnifeItem;
import cn.mcmod.tsuki.item.tool.MythicPickaxeItem;
import cn.mcmod.tsuki.item.tool.SheathItem;
import cn.mcmod.tsuki.item.tool.SheathKatanaItem;
import cn.mcmod.tsuki.item.tool.ShinaiItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.component.DataComponents;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ArmorToolRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);

    public static final DeferredItem<Item> ZUKU = register("zuku", () -> new Item(Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> ZUKU_INGOT = register("zuku_ingot",
            () -> new Item(Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAGEGANE = register("sagegane",
            () -> new Item(Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> TAMAHAGANE = register("tamahagane",
            () -> new Item(Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> STEEL_INGOT = register("steel_ingot",
            () -> new Item(Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAKURA_DIAMOND = register("sakura_diamond", SakuraDiamondItem::new);

    public static final Tier SAKURA_TOOL_TIER = new SimpleTier(
            TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "incorrect_for_sakura_tool")),
            4063,
            10.0F,
            6.0F,
            25,
            () -> Ingredient.of(SAKURA_DIAMOND.get()));
    public static final Tier STEEL_TOOL_TIER = new SimpleTier(
            TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "incorrect_for_sakura_tool")),
            2031,
            8.0F,
            3.0F,
            18,
            () -> Ingredient.of(STEEL_INGOT.get()));

    public static final DeferredItem<Item> BROOM = register("broom",
            () -> new BroomItem(STEEL_TOOL_TIER, 1.0F, -2.4F, Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> SAKURA_AXE = register("sakura_axe",
            () -> new AxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties()
                            .attributes(AxeItem.createAttributes(SAKURA_TOOL_TIER, 5.0F, -3.0F))
                            .stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_PICKAXE = register("sakura_pickaxe",
            () -> new PickaxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties()
                            .attributes(PickaxeItem.createAttributes(SAKURA_TOOL_TIER, 1.0F, -2.8F))
                            .stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_HOE = register("sakura_hoe",
            () -> new HoeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties()
                            .attributes(HoeItem.createAttributes(SAKURA_TOOL_TIER, -6.0F, 0.0F))
                            .stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_SHOVEL = register("sakura_shovel",
            () -> new ShovelItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties()
                            .attributes(ShovelItem.createAttributes(SAKURA_TOOL_TIER, 1.5F, -3.0F))
                            .stacksTo(1)));
    public static final DeferredItem<Item> MYTHIC_PICKAXE = register("mythic_pickaxe",
            () -> new MythicPickaxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties()
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)
                            .component(DataComponents.UNBREAKABLE, new Unbreakable(true))
                            .attributes(PickaxeItem.createAttributes(SAKURA_TOOL_TIER, 1.0F, -2.8F))));

    public static final DeferredItem<Item> IRON_FISH_KNIFE = register("knife_fish",
            () -> new KnifeItem(Tiers.IRON, 1F, -2.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> IRON_NOODLE_KNIFE = register("knife_noodle",
            () -> new KnifeItem(Tiers.IRON, 2F, -3.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_FISH_KNIFE = register("sakura_knife_fish",
            () -> new KnifeItem(SAKURA_TOOL_TIER, 1F, -2.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_NOODLE_KNIFE = register("sakura_knife_noodle",
            () -> new KnifeItem(SAKURA_TOOL_TIER, 2F, -3.0F, Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> STONE_HAMMER = register("stone_hammer",
            () -> new HammerItem(Tiers.STONE, 1.0F, -2.8F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> IRON_HAMMER = register("iron_hammer",
            () -> new HammerItem(Tiers.IRON, 1.0F, -2.8F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> STEEL_HAMMER = register("steel_hammer",
            () -> new HammerItem(STEEL_TOOL_TIER, 1.0F, -2.8F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_HAMMER = register("sakura_hammer",
            () -> new HammerItem(SAKURA_TOOL_TIER, 1.0F, -2.8F, Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> SHINAI = register("shinai",
            () -> new ShinaiItem(Tiers.WOOD, 2, -2.2F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KATANA = register("katana",
            () -> new KatanaItem(Tiers.IRON, 3, -2.2F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_KATANA = register("sakura_katana",
            () -> new KatanaItem(SAKURA_TOOL_TIER, 3, -2.2F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KODACHI = register("kodachi",
            () -> new KatanaItem(Tiers.IRON, 1, -1.6F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_KODACHI = register("sakura_kodachi",
            () -> new KatanaItem(SAKURA_TOOL_TIER, -1, -1.6F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> TACHI = register("tachi",
            () -> new KatanaItem(Tiers.IRON, 5, -2.2F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SHEATH = register("sheath",
            () -> new SheathItem(Tsuki.defaultItemProperties().stacksTo(1).durability(59)));
    public static final DeferredItem<Item> KATANA_SHEATH = register("katana_sheath",
            () -> new SheathKatanaItem(
                    Tsuki.defaultItemProperties().stacksTo(1).durability(250),
                    () -> KATANA.get(),
                    () -> SHEATH.get()));
    public static final DeferredItem<Item> SAKURA_KATANA_SHEATH = register("sakura_katana_sheath",
            () -> new SheathKatanaItem(
                    Tsuki.defaultItemProperties().stacksTo(1).durability(1561),
                    () -> SAKURA_KATANA.get(),
                    () -> SHEATH.get()));

    public static final DeferredItem<Item> STRAW_HAT = register("straw_hat",
            () -> new StrawHatItem(ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.HELMET,
                    Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> KIMONO_WHITE = register("kimono_white",
            () -> new KimonoItem("kimono_white", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_BLACK = register("kimono_black",
            () -> new KimonoItem("kimono_black", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_BROWN = register("kimono_brown",
            () -> new KimonoItem("kimono_brown", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_GREEN = register("kimono_green",
            () -> new KimonoItem("kimono_green", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_CYAN = register("kimono_cyan",
            () -> new KimonoItem("kimono_cyan", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_PURPLE = register("kimono_purple",
            () -> new KimonoItem("kimono_purple", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_SAKURA = register("kimono_sakura",
            () -> new KimonoItem("kimono_sakura", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_MIKO = register("kimono_miko",
            () -> new KimonoItem("kimono_miko", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> KIMONO_ENE = register("kimono_ene",
            () -> new KimonoItem("kimono_ene", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> YUKATA_RED = register("yukata_red",
            () -> new KimonoItem("yukata_red", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> YUKATA_BLUE = register("yukata_blue",
            () -> new KimonoItem("yukata_blue", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> YUKATA_MAGENTA = register("yukata_magenta",
            () -> new KimonoItem("yukata_magenta", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> YUKATA_LIME = register("yukata_lime",
            () -> new KimonoItem("yukata_lime", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> YUKATA_YELLOW = register("yukata_yellow",
            () -> new KimonoItem("yukata_yellow", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> HAORI_BLACK = register("haori_black",
            () -> new HaoriItem("haori_black", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> HAORI_GREEN = register("haori_green",
            () -> new HaoriItem("haori_green", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> HAORI_CYAN = register("haori_cyan",
            () -> new HaoriItem("haori_cyan", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> HAORI_LIGHT_BLUE = register("haori_light_blue",
            () -> new HaoriItem("haori_light_blue", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> HAORI_BROWN = register("haori_brown",
            () -> new HaoriItem("haori_brown", ArmorMaterialRegistry.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> SOLDIER_HELMET_GRAY = register("soldier_helmet_gray",
            () -> new SoldierItem("soldier_gray", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.HELMET,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(363)));
    public static final DeferredItem<Item> SOLDIER_CHESTPLATE_GRAY = register("soldier_chestplate_gray",
            () -> new SoldierItem("soldier_gray", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(528)));
    public static final DeferredItem<Item> SOLDIER_LEGGINGS_GRAY = register("soldier_leggings_gray",
            () -> new SoldierItem("soldier_gray", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(495)));
    public static final DeferredItem<Item> SOLDIER_BOOTS_GRAY = register("soldier_boots_gray",
            () -> new SoldierItem("soldier_gray", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.BOOTS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(429)));

    public static final DeferredItem<Item> SOLDIER_HELMET_BLACK = register("soldier_helmet_black",
            () -> new SoldierItem("soldier_black", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.HELMET,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(363)));
    public static final DeferredItem<Item> SOLDIER_CHESTPLATE_BLACK = register("soldier_chestplate_black",
            () -> new SoldierItem("soldier_black", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(528)));
    public static final DeferredItem<Item> SOLDIER_LEGGINGS_BLACK = register("soldier_leggings_black",
            () -> new SoldierItem("soldier_black", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(495)));
    public static final DeferredItem<Item> SOLDIER_BOOTS_BLACK = register("soldier_boots_black",
            () -> new SoldierItem("soldier_black", ArmorMaterialRegistry.SOLDIER, ArmorItem.Type.BOOTS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(429)));

    public static final DeferredItem<Item> SAMURAI_HELMET_RED = register("samurai_helmet_red",
            () -> new SamuraiItem("samurai_red", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.HELMET,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(407)));
    public static final DeferredItem<Item> SAMURAI_CHESTPLATE_RED = register("samurai_chestplate_red",
            () -> new SamuraiItem("samurai_red", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(592)));
    public static final DeferredItem<Item> SAMURAI_LEGGINGS_RED = register("samurai_leggings_red",
            () -> new SamuraiItem("samurai_red", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(555)));
    public static final DeferredItem<Item> SAMURAI_BOOTS_RED = register("samurai_boots_red",
            () -> new SamuraiItem("samurai_red", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.BOOTS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(481)));

    public static final DeferredItem<Item> SAMURAI_HELMET_GREEN = register("samurai_helmet_green",
            () -> new SamuraiItem("samurai_green", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.HELMET,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(407)));
    public static final DeferredItem<Item> SAMURAI_CHESTPLATE_GREEN = register("samurai_chestplate_green",
            () -> new SamuraiItem("samurai_green", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(592)));
    public static final DeferredItem<Item> SAMURAI_LEGGINGS_GREEN = register("samurai_leggings_green",
            () -> new SamuraiItem("samurai_green", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(555)));
    public static final DeferredItem<Item> SAMURAI_BOOTS_GREEN = register("samurai_boots_green",
            () -> new SamuraiItem("samurai_green", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.BOOTS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(481)));

    public static final DeferredItem<Item> SAMURAI_HELMET_BLACK = register("samurai_helmet_black",
            () -> new SamuraiItem("samurai_black", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.HELMET,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(407)));
    public static final DeferredItem<Item> SAMURAI_CHESTPLATE_BLACK = register("samurai_chestplate_black",
            () -> new SamuraiItem("samurai_black", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.CHESTPLATE,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(592)));
    public static final DeferredItem<Item> SAMURAI_LEGGINGS_BLACK = register("samurai_leggings_black",
            () -> new SamuraiItem("samurai_black", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.LEGGINGS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(555)));
    public static final DeferredItem<Item> SAMURAI_BOOTS_BLACK = register("samurai_boots_black",
            () -> new SamuraiItem("samurai_black", ArmorMaterialRegistry.SAMURAI, ArmorItem.Type.BOOTS,
                    Tsuki.defaultItemProperties().stacksTo(1).durability(481)));

    public static final DeferredItem<Item> MAGATAMA_WHITE = register("magatama_white",
            () -> new MagatamaWhiteItem(Tsuki.defaultItemProperties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> MAGATAMA_BLUE = register("magatama_blue",
            () -> new MagatamaBlueItem(Tsuki.defaultItemProperties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> MAGATAMA_ORANGE = register("magatama_orange",
            () -> new MagatamaOrangeItem(Tsuki.defaultItemProperties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> MAGATAMA_RED = register("magatama_red",
            () -> new MagatamaRedItem(Tsuki.defaultItemProperties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));


    public static final DeferredItem<Item> MUSIC_DISC_MIKO = register("music_disc_miko",
            () -> new Item(Tsuki.defaultItemProperties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .jukeboxPlayable(JukeboxSongRegistry.DISC_MUSIC_MIKO)));

    private static <V extends Item> DeferredItem<V> register(String name, Supplier<V> item) {
        return ITEMS.register(name, item);
    }
}
