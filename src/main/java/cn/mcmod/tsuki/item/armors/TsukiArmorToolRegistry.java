package cn.mcmod.tsuki.item.armors;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.KnifeItem;
import cn.mcmod.tsuki.item.SakuraDiamondItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TsukiArmorToolRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Tsuki.MODID);

    public static final DeferredItem<Item> SAKURA_DIAMOND = register("sakura_diamond", SakuraDiamondItem::new);
    
    public static final Tier SAKURA_TOOL_TIER = new SimpleTier(
            TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "incorrect_for_sakura_tool")),
            4063,
            10.0F,
            6.0F,
            25,
            () -> Ingredient.of(SAKURA_DIAMOND.get())
        );

    public static final DeferredItem<Item> SAKURA_AXE = register("sakura_axe",
            () -> new AxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(AxeItem.createAttributes(SAKURA_TOOL_TIER, 5.0F, -3.0F))));
    public static final DeferredItem<Item> SAKURA_PICKAXE = register("sakura_pickaxe",
            () -> new PickaxeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(PickaxeItem.createAttributes(SAKURA_TOOL_TIER, 1.0F, -2.8F))));
    public static final DeferredItem<Item> SAKURA_HOE = register("sakura_hoe",
            () -> new HoeItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(HoeItem.createAttributes(SAKURA_TOOL_TIER, -6.0F, 0.0F))));
    public static final DeferredItem<Item> SAKURA_SHOVEL = register("sakura_shovel",
            () -> new ShovelItem(SAKURA_TOOL_TIER,
                    Tsuki.defaultItemProperties().attributes(ShovelItem.createAttributes(SAKURA_TOOL_TIER, 1.5F, -3.0F))));

    public static final DeferredItem<Item> IRON_FISH_KNIFE = register("knife_fish",
            () -> new KnifeItem(Tiers.IRON, 1F, -2.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> IRON_NOODLE_KNIFE = register("knife_noodle",
            () -> new KnifeItem(Tiers.IRON, 2F, -3.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_FISH_KNIFE = register("sakura_knife_fish",
            () -> new KnifeItem(SAKURA_TOOL_TIER, 1F, -2.0F, Tsuki.defaultItemProperties().stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_NOODLE_KNIFE = register("sakura_knife_noodle",
            () -> new KnifeItem(SAKURA_TOOL_TIER, 2F, -3.0F, Tsuki.defaultItemProperties().stacksTo(1)));

    public static final DeferredItem<Item> KIMONO_WHITE = register("kimono_white",
            () -> new KimonoItem("kimono_white", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_BLACK = register("kimono_black",
            () -> new KimonoItem("kimono_black", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_BROWN = register("kimono_brown",
            () -> new KimonoItem("kimono_brown", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_GREEN = register("kimono_green",
            () -> new KimonoItem("kimono_green", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_CYAN = register("kimono_cyan",
            () -> new KimonoItem("kimono_cyan", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_PURPLE = register("kimono_purple",
            () -> new KimonoItem("kimono_purple", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_SAKURA = register("kimono_sakura",
            () -> new KimonoItem("kimono_sakura", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_MIKO = register("kimono_miko",
            () -> new KimonoItem("kimono_miko", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> KIMONO_ENE = register("kimono_ene",
            () -> new KimonoItem("kimono_ene", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> YUKATA_RED = register("yukata_red",
            () -> new KimonoItem("yukata_red", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> YUKATA_BLUE = register("yukata_blue",
            () -> new KimonoItem("yukata_blue", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> YUKATA_MAGENTA = register("yukata_magenta",
            () -> new KimonoItem("yukata_magenta", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> YUKATA_LIME = register("yukata_lime",
            () -> new KimonoItem("yukata_lime", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> YUKATA_YELLOW = register("yukata_yellow",
            () -> new KimonoItem("yukata_yellow", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> HAORI_BLACK = register("haori_black",
            () -> new HaoriItem("haori_black", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> HAORI_GREEN = register("haori_green",
            () -> new HaoriItem("haori_green", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> HAORI_CYAN = register("haori_cyan",
            () -> new HaoriItem("haori_cyan", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> HAORI_LIGHT_BLUE = register("haori_light_blue",
            () -> new HaoriItem("haori_light_blue", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> HAORI_BROWN = register("haori_brown",
            () -> new HaoriItem("haori_brown", TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> STRAW_HAT = register("straw_hat",
            () -> new StrawHatItem(TsukiArmorMaterials.KIMONO_AND_HAORI, ArmorItem.Type.HELMET, Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAMURAI_HELMET_RED = register("samurai_helmet_red",
            () -> new SamuraiItem("samurai_red", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.HELMET, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_CHESTPLATE_RED = register("samurai_chestplate_red",
            () -> new SamuraiItem("samurai_red", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_LEGGINGS_RED = register("samurai_leggings_red",
            () -> new SamuraiItem("samurai_red", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_BOOTS_RED = register("samurai_boots_red",
            () -> new SamuraiItem("samurai_red", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.BOOTS, Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAMURAI_HELMET_GREEN = register("samurai_helmet_green",
            () -> new SamuraiItem("samurai_green", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.HELMET, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_CHESTPLATE_GREEN = register("samurai_chestplate_green",
            () -> new SamuraiItem("samurai_green", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_LEGGINGS_GREEN = register("samurai_leggings_green",
            () -> new SamuraiItem("samurai_green", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_BOOTS_GREEN = register("samurai_boots_green",
            () -> new SamuraiItem("samurai_green", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.BOOTS, Tsuki.defaultItemProperties()));

    public static final DeferredItem<Item> SAMURAI_HELMET_BLACK = register("samurai_helmet_black",
            () -> new SamuraiItem("samurai_black", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.HELMET, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_CHESTPLATE_BLACK = register("samurai_chestplate_black",
            () -> new SamuraiItem("samurai_black", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.CHESTPLATE, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_LEGGINGS_BLACK = register("samurai_leggings_black",
            () -> new SamuraiItem("samurai_black", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.LEGGINGS, Tsuki.defaultItemProperties()));
    public static final DeferredItem<Item> SAMURAI_BOOTS_BLACK = register("samurai_boots_black",
            () -> new SamuraiItem("samurai_black", TsukiArmorMaterials.SAMURAI, ArmorItem.Type.BOOTS, Tsuki.defaultItemProperties()));

    private static <V extends Item> DeferredItem<V> register(String name, Supplier<V> item) {
        return ITEMS.register(name, item);
    }
}
