package cn.mcmod.tsuki.compat;

import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public final class EquipmentDyeDisplay {
    private EquipmentDyeDisplay() {
    }

    public static List<Entry> entries() {
        return List.of(
                new Entry("kimono_white", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.WHITE_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_WHITE.get())),
                new Entry("kimono_black", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_BLACK.get())),
                new Entry("kimono_cyan", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.CYAN_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_CYAN.get())),
                new Entry("kimono_purple", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.PURPLE_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_PURPLE.get())),
                new Entry("kimono_brown", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.BROWN_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_BROWN.get())),
                new Entry("kimono_green", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.GREEN_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_GREEN.get())),
                new Entry("kimono_sakura", List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.PINK_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_SAKURA.get())),
                new Entry("kimono_miko",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.WHITE_DYE),
                                Ingredient.of(Items.RED_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_MIKO.get())),
                new Entry("kimono_ene",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO), Ingredient.of(Items.NETHER_STAR),
                                Ingredient.of(Items.CYAN_DYE), Ingredient.of(Items.BLUE_DYE),
                                Ingredient.of(Items.PINK_DYE), Ingredient.of(Items.WHITE_DYE)),
                        new ItemStack(ArmorToolRegistry.KIMONO_ENE.get())),
                new Entry("yukata_red",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO),
                                Ingredient.of(TsukiNormalItemSet.SILK.getItem().get()), Ingredient.of(Items.RED_DYE)),
                        new ItemStack(ArmorToolRegistry.YUKATA_RED.get())),
                new Entry("yukata_magenta",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO),
                                Ingredient.of(TsukiNormalItemSet.SILK.getItem().get()),
                                Ingredient.of(Items.MAGENTA_DYE)),
                        new ItemStack(ArmorToolRegistry.YUKATA_MAGENTA.get())),
                new Entry("yukata_blue",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO),
                                Ingredient.of(TsukiNormalItemSet.SILK.getItem().get()), Ingredient.of(Items.BLUE_DYE)),
                        new ItemStack(ArmorToolRegistry.YUKATA_BLUE.get())),
                new Entry("yukata_lime",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO),
                                Ingredient.of(TsukiNormalItemSet.SILK.getItem().get()), Ingredient.of(Items.LIME_DYE)),
                        new ItemStack(ArmorToolRegistry.YUKATA_LIME.get())),
                new Entry("yukata_yellow",
                        List.of(Ingredient.of(TsukiItemTags.KIMONO),
                                Ingredient.of(TsukiNormalItemSet.SILK.getItem().get()),
                                Ingredient.of(Items.YELLOW_DYE)),
                        new ItemStack(ArmorToolRegistry.YUKATA_YELLOW.get())),
                new Entry("haori_black", List.of(Ingredient.of(TsukiItemTags.HAORI), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.HAORI_BLACK.get())),
                new Entry("haori_cyan", List.of(Ingredient.of(TsukiItemTags.HAORI), Ingredient.of(Items.CYAN_DYE)),
                        new ItemStack(ArmorToolRegistry.HAORI_CYAN.get())),
                new Entry("haori_brown", List.of(Ingredient.of(TsukiItemTags.HAORI), Ingredient.of(Items.BROWN_DYE)),
                        new ItemStack(ArmorToolRegistry.HAORI_BROWN.get())),
                new Entry("haori_light_blue",
                        List.of(Ingredient.of(TsukiItemTags.HAORI), Ingredient.of(Items.LIGHT_BLUE_DYE)),
                        new ItemStack(ArmorToolRegistry.HAORI_LIGHT_BLUE.get())),
                new Entry("haori_green", List.of(Ingredient.of(TsukiItemTags.HAORI), Ingredient.of(Items.GREEN_DYE)),
                        new ItemStack(ArmorToolRegistry.HAORI_GREEN.get())),
                new Entry("samurai_helmet_red",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_HELMET), Ingredient.of(Items.RED_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_HELMET_RED.get())),
                new Entry("samurai_chestplate_red",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_CHESTPLATE), Ingredient.of(Items.RED_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_CHESTPLATE_RED.get())),
                new Entry("samurai_leggings_red",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_LEGGINGS), Ingredient.of(Items.RED_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_LEGGINGS_RED.get())),
                new Entry("samurai_boots_red",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_BOOTS), Ingredient.of(Items.RED_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_BOOTS_RED.get())),
                new Entry("samurai_helmet_green",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_HELMET), Ingredient.of(Items.GREEN_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_HELMET_GREEN.get())),
                new Entry("samurai_chestplate_green",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_CHESTPLATE), Ingredient.of(Items.GREEN_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_CHESTPLATE_GREEN.get())),
                new Entry("samurai_leggings_green",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_LEGGINGS), Ingredient.of(Items.GREEN_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_LEGGINGS_GREEN.get())),
                new Entry("samurai_boots_green",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_BOOTS), Ingredient.of(Items.GREEN_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_BOOTS_GREEN.get())),
                new Entry("samurai_helmet_black",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_HELMET), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_HELMET_BLACK.get())),
                new Entry("samurai_chestplate_black",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_CHESTPLATE), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_CHESTPLATE_BLACK.get())),
                new Entry("samurai_leggings_black",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_LEGGINGS), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_LEGGINGS_BLACK.get())),
                new Entry("samurai_boots_black",
                        List.of(Ingredient.of(TsukiItemTags.SAMURAI_BOOTS), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SAMURAI_BOOTS_BLACK.get())),
                new Entry("soldier_helmet_gray",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_HELMET), Ingredient.of(Items.GRAY_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_HELMET_GRAY.get())),
                new Entry("soldier_chestplate_gray",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_CHESTPLATE), Ingredient.of(Items.GRAY_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_CHESTPLATE_GRAY.get())),
                new Entry("soldier_leggings_gray",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_LEGGINGS), Ingredient.of(Items.GRAY_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_LEGGINGS_GRAY.get())),
                new Entry("soldier_boots_gray",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_BOOTS), Ingredient.of(Items.GRAY_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_BOOTS_GRAY.get())),
                new Entry("soldier_helmet_black",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_HELMET), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_HELMET_BLACK.get())),
                new Entry("soldier_chestplate_black",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_CHESTPLATE), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_CHESTPLATE_BLACK.get())),
                new Entry("soldier_leggings_black",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_LEGGINGS), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_LEGGINGS_BLACK.get())),
                new Entry("soldier_boots_black",
                        List.of(Ingredient.of(TsukiItemTags.SOLDIER_BOOTS), Ingredient.of(Items.BLACK_DYE)),
                        new ItemStack(ArmorToolRegistry.SOLDIER_BOOTS_BLACK.get())));
    }

    public record Entry(String id, List<Ingredient> inputs, ItemStack output) {
        public ResourceLocation recipeId() {
            return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "equipment_dye/" + id);
        }

        public ShapelessRecipe toShapelessRecipe() {
            NonNullList<Ingredient> ingredients = NonNullList.create();
            ingredients.addAll(inputs);
            return new ShapelessRecipe("", CraftingBookCategory.EQUIPMENT, output, ingredients);
        }
    }
}
