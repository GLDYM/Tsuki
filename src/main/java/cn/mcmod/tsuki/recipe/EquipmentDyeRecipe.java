package cn.mcmod.tsuki.recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class EquipmentDyeRecipe extends CustomRecipe {
    private static final Map<Item, Supplier<Item>> KIMONO_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> HAORI_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SAMURAI_HELMET_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SAMURAI_CHESTPLATE_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SAMURAI_LEGGINGS_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SAMURAI_BOOTS_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SOLDIER_HELMET_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SOLDIER_CHESTPLATE_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SOLDIER_LEGGINGS_RESULTS = new HashMap<>();
    private static final Map<Item, Supplier<Item>> SOLDIER_BOOTS_RESULTS = new HashMap<>();

    static {
        KIMONO_RESULTS.put(Items.WHITE_DYE, ArmorToolRegistry.KIMONO_WHITE);
        KIMONO_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.KIMONO_BLACK);
        KIMONO_RESULTS.put(Items.CYAN_DYE, ArmorToolRegistry.KIMONO_CYAN);
        KIMONO_RESULTS.put(Items.PURPLE_DYE, ArmorToolRegistry.KIMONO_PURPLE);
        KIMONO_RESULTS.put(Items.BROWN_DYE, ArmorToolRegistry.KIMONO_BROWN);
        KIMONO_RESULTS.put(Items.GREEN_DYE, ArmorToolRegistry.KIMONO_GREEN);
        KIMONO_RESULTS.put(Items.PINK_DYE, ArmorToolRegistry.KIMONO_SAKURA);

        HAORI_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.HAORI_BLACK);
        HAORI_RESULTS.put(Items.CYAN_DYE, ArmorToolRegistry.HAORI_CYAN);
        HAORI_RESULTS.put(Items.BROWN_DYE, ArmorToolRegistry.HAORI_BROWN);
        HAORI_RESULTS.put(Items.LIGHT_BLUE_DYE, ArmorToolRegistry.HAORI_LIGHT_BLUE);
        HAORI_RESULTS.put(Items.GREEN_DYE, ArmorToolRegistry.HAORI_GREEN);

        SAMURAI_HELMET_RESULTS.put(Items.RED_DYE, ArmorToolRegistry.SAMURAI_HELMET_RED);
        SAMURAI_HELMET_RESULTS.put(Items.GREEN_DYE, ArmorToolRegistry.SAMURAI_HELMET_GREEN);
        SAMURAI_HELMET_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SAMURAI_HELMET_BLACK);
        SAMURAI_CHESTPLATE_RESULTS.put(Items.RED_DYE, ArmorToolRegistry.SAMURAI_CHESTPLATE_RED);
        SAMURAI_CHESTPLATE_RESULTS.put(Items.GREEN_DYE, ArmorToolRegistry.SAMURAI_CHESTPLATE_GREEN);
        SAMURAI_CHESTPLATE_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SAMURAI_CHESTPLATE_BLACK);
        SAMURAI_LEGGINGS_RESULTS.put(Items.RED_DYE, ArmorToolRegistry.SAMURAI_LEGGINGS_RED);
        SAMURAI_LEGGINGS_RESULTS.put(Items.GREEN_DYE, ArmorToolRegistry.SAMURAI_LEGGINGS_GREEN);
        SAMURAI_LEGGINGS_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SAMURAI_LEGGINGS_BLACK);
        SAMURAI_BOOTS_RESULTS.put(Items.RED_DYE, ArmorToolRegistry.SAMURAI_BOOTS_RED);
        SAMURAI_BOOTS_RESULTS.put(Items.GREEN_DYE, ArmorToolRegistry.SAMURAI_BOOTS_GREEN);
        SAMURAI_BOOTS_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SAMURAI_BOOTS_BLACK);

        SOLDIER_HELMET_RESULTS.put(Items.GRAY_DYE, ArmorToolRegistry.SOLDIER_HELMET_GRAY);
        SOLDIER_HELMET_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SOLDIER_HELMET_BLACK);
        SOLDIER_CHESTPLATE_RESULTS.put(Items.GRAY_DYE, ArmorToolRegistry.SOLDIER_CHESTPLATE_GRAY);
        SOLDIER_CHESTPLATE_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SOLDIER_CHESTPLATE_BLACK);
        SOLDIER_LEGGINGS_RESULTS.put(Items.GRAY_DYE, ArmorToolRegistry.SOLDIER_LEGGINGS_GRAY);
        SOLDIER_LEGGINGS_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SOLDIER_LEGGINGS_BLACK);
        SOLDIER_BOOTS_RESULTS.put(Items.GRAY_DYE, ArmorToolRegistry.SOLDIER_BOOTS_GRAY);
        SOLDIER_BOOTS_RESULTS.put(Items.BLACK_DYE, ArmorToolRegistry.SOLDIER_BOOTS_BLACK);
    }

    public EquipmentDyeRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return !assemble(input, level.registryAccess()).isEmpty();
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack equipment = ItemStack.EMPTY;
        Map<Item, Integer> ingredients = new HashMap<>();

        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) {
                continue;
            }

            if (isSupportedEquipment(stack)) {
                if (!equipment.isEmpty()) {
                    return ItemStack.EMPTY;
                }
                equipment = stack;
            } else {
                // Crafting recipes consume one item per occupied slot, regardless of stack size.
                ingredients.merge(stack.getItem(), 1, Integer::sum);
            }
        }

        if (equipment.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int itemCount = 0;
        for (int slot = 0; slot < input.size(); ++slot) {
            if (!input.getItem(slot).isEmpty()) {
                itemCount++;
            }
        }

        if (itemCount != ingredientCount(ingredients) + 1) {
            return ItemStack.EMPTY;
        }

        Supplier<Item> result = getResult(equipment.getItem(), ingredients);
        if (result == null) {
            return ItemStack.EMPTY;
        }

        Item targetItem = result.get();
        if (equipment.is(targetItem)) {
            return ItemStack.EMPTY;
        }

        return equipment.transmuteCopy(targetItem, 1);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.EQUIPMENT_DYE_RECIPE_SERIALIZER.get();
    }

    private static boolean isSupportedEquipment(ItemStack stack) {
        Item item = stack.getItem();
        return isInMap(item, KIMONO_RESULTS)
                || isInMap(item, HAORI_RESULTS)
                || isInMap(item, SAMURAI_HELMET_RESULTS)
                || isInMap(item, SAMURAI_CHESTPLATE_RESULTS)
                || isInMap(item, SAMURAI_LEGGINGS_RESULTS)
                || isInMap(item, SAMURAI_BOOTS_RESULTS)
                || isInMap(item, SOLDIER_HELMET_RESULTS)
                || isInMap(item, SOLDIER_CHESTPLATE_RESULTS)
                || isInMap(item, SOLDIER_LEGGINGS_RESULTS)
                || isInMap(item, SOLDIER_BOOTS_RESULTS)
                || item == ArmorToolRegistry.KIMONO_MIKO.get()
                || item == ArmorToolRegistry.KIMONO_ENE.get()
                || item == ArmorToolRegistry.YUKATA_RED.get()
                || item == ArmorToolRegistry.YUKATA_BLUE.get()
                || item == ArmorToolRegistry.YUKATA_MAGENTA.get()
                || item == ArmorToolRegistry.YUKATA_LIME.get()
                || item == ArmorToolRegistry.YUKATA_YELLOW.get();
    }

    private static boolean isInMap(Item item, Map<Item, Supplier<Item>> resultMap) {
        return resultMap.values().stream().anyMatch(supplier -> supplier.get() == item);
    }

    private static int ingredientCount(Map<Item, Integer> ingredients) {
        return ingredients.values().stream().mapToInt(Integer::intValue).sum();
    }

    private static Supplier<Item> getResult(Item equipment, Map<Item, Integer> ingredients) {
        Supplier<Item> simpleResult = getSimpleResult(equipment, ingredients);
        if (simpleResult != null) {
            return simpleResult;
        }

        if (isKimono(equipment)) {
            if (matches(ingredients, Items.WHITE_DYE, 1, Items.RED_DYE, 1)) {
                return ArmorToolRegistry.KIMONO_MIKO;
            }
            if (matches(ingredients,
                    Items.NETHER_STAR, 1,
                    Items.CYAN_DYE, 1,
                    Items.BLUE_DYE, 1,
                    Items.PINK_DYE, 1,
                    Items.WHITE_DYE, 1)) {
                return ArmorToolRegistry.KIMONO_ENE;
            }
            if (matches(ingredients, TsukiNormalItemSet.SILK.getItem().get(), 1, Items.RED_DYE, 1)) {
                return ArmorToolRegistry.YUKATA_RED;
            }
            if (matches(ingredients, TsukiNormalItemSet.SILK.getItem().get(), 1, Items.MAGENTA_DYE, 1)) {
                return ArmorToolRegistry.YUKATA_MAGENTA;
            }
            if (matches(ingredients, TsukiNormalItemSet.SILK.getItem().get(), 1, Items.BLUE_DYE, 1)) {
                return ArmorToolRegistry.YUKATA_BLUE;
            }
            if (matches(ingredients, TsukiNormalItemSet.SILK.getItem().get(), 1, Items.LIME_DYE, 1)) {
                return ArmorToolRegistry.YUKATA_LIME;
            }
            if (matches(ingredients, TsukiNormalItemSet.SILK.getItem().get(), 1, Items.YELLOW_DYE, 1)) {
                return ArmorToolRegistry.YUKATA_YELLOW;
            }
        }

        return null;
    }

    private static Supplier<Item> getSimpleResult(Item equipment, Map<Item, Integer> ingredients) {
        if (ingredients.size() != 1) {
            return null;
        }
        Item ingredient = ingredients.keySet().iterator().next();
        if (ingredients.get(ingredient) != 1) {
            return null;
        }

        if (isKimono(equipment)) {
            return KIMONO_RESULTS.get(ingredient);
        }
        if (isHaori(equipment)) {
            return HAORI_RESULTS.get(ingredient);
        }
        if (isSamuraiHelmet(equipment)) {
            return SAMURAI_HELMET_RESULTS.get(ingredient);
        }
        if (isSamuraiChestplate(equipment)) {
            return SAMURAI_CHESTPLATE_RESULTS.get(ingredient);
        }
        if (isSamuraiLeggings(equipment)) {
            return SAMURAI_LEGGINGS_RESULTS.get(ingredient);
        }
        if (isSamuraiBoots(equipment)) {
            return SAMURAI_BOOTS_RESULTS.get(ingredient);
        }
        if (isSoldierHelmet(equipment)) {
            return SOLDIER_HELMET_RESULTS.get(ingredient);
        }
        if (isSoldierChestplate(equipment)) {
            return SOLDIER_CHESTPLATE_RESULTS.get(ingredient);
        }
        if (isSoldierLeggings(equipment)) {
            return SOLDIER_LEGGINGS_RESULTS.get(ingredient);
        }
        if (isSoldierBoots(equipment)) {
            return SOLDIER_BOOTS_RESULTS.get(ingredient);
        }
        return null;
    }

    private static boolean matches(Map<Item, Integer> ingredients, Object... entries) {
        if (entries.length % 2 != 0) {
            return false;
        }
        if (ingredients.size() != entries.length / 2) {
            return false;
        }
        for (int i = 0; i < entries.length; i += 2) {
            Item item = (Item) entries[i];
            int count = (Integer) entries[i + 1];
            if (ingredients.getOrDefault(item, 0) != count) {
                return false;
            }
        }
        return true;
    }

    private static boolean isKimono(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.KIMONO);
    }

    private static boolean isHaori(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.HAORI);
    }

    private static boolean isSamuraiHelmet(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SAMURAI_HELMET);
    }

    private static boolean isSamuraiChestplate(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SAMURAI_CHESTPLATE);
    }

    private static boolean isSamuraiLeggings(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SAMURAI_LEGGINGS);
    }

    private static boolean isSamuraiBoots(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SAMURAI_BOOTS);
    }

    private static boolean isSoldierHelmet(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SOLDIER_HELMET);
    }

    private static boolean isSoldierChestplate(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SOLDIER_CHESTPLATE);
    }

    private static boolean isSoldierLeggings(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SOLDIER_LEGGINGS);
    }

    private static boolean isSoldierBoots(Item item) {
        return BuiltInRegistries.ITEM.wrapAsHolder(item).is(TsukiItemTags.SOLDIER_BOOTS);
    }
}
