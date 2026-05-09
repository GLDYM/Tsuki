package cn.mcmod.tsuki.recipe;

import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.ShakerDataHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ShakerPourRecipe extends CustomRecipe {
    public ShakerPourRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        ItemStack shaker = ItemStack.EMPTY;
        ItemStack container = ItemStack.EMPTY;

        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.isEmpty()) {
                continue;
            }
            if (stack.is(DrinkRegistry.SHAKER.get())) {
                if (!shaker.isEmpty()) {
                    return false;
                }
                shaker = stack;
            } else {
                if (!container.isEmpty()) {
                    return false;
                }
                container = stack;
            }
        }

        return !shaker.isEmpty()
                && !container.isEmpty()
                && ShakerDataHelper.canPourToContainer(shaker, container, level.registryAccess());
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(DrinkRegistry.SHAKER.get())) {
                return ShakerDataHelper.getOutput(stack, registries).copyWithCount(1);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int slot = 0; slot < input.size(); ++slot) {
            ItemStack stack = input.getItem(slot);
            if (stack.is(DrinkRegistry.SHAKER.get())) {
                remaining.set(slot, ShakerDataHelper.copyWithRemovedOneOutput(stack));
            }
        }
        return remaining;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.SHAKER_POUR_RECIPE_SERIALIZER.get();
    }
}
