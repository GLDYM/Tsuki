package cn.mcmod.tsuki.recipe;

import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.item.tool.SheathItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class KatanaSheathingRecipe extends CustomRecipe {
    public KatanaSheathingRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return findIngredients(input) != null;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        Ingredients ingredients = findIngredients(input);
        if (ingredients == null) {
            return ItemStack.EMPTY;
        }
        return SheathItem.sheath(ingredients.katana(), ingredients.sheath(), registries);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.KATANA_SHEATHING_RECIPE_SERIALIZER.get();
    }

    private static Ingredients findIngredients(CraftingInput input) {
        ItemStack katana = ItemStack.EMPTY;
        ItemStack sheath = ItemStack.EMPTY;

        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (stack.is(ArmorToolRegistry.KATANA.get()) || stack.is(ArmorToolRegistry.SAKURA_KATANA.get())) {
                if (!katana.isEmpty()) {
                    return null;
                }
                katana = stack;
            } else if (stack.is(ArmorToolRegistry.SHEATH.get())) {
                if (!sheath.isEmpty()) {
                    return null;
                }
                sheath = stack;
            } else {
                return null;
            }
        }

        return katana.isEmpty() || sheath.isEmpty() ? null : new Ingredients(katana, sheath);
    }

    private record Ingredients(ItemStack katana, ItemStack sheath) {
    }
}
