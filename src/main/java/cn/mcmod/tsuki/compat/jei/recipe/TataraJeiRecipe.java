package cn.mcmod.tsuki.compat.jei.recipe;

import net.minecraft.world.item.ItemStack;

public record TataraJeiRecipe(
        ItemStack furnace,
        ItemStack ignition,
        ItemStack ironResult,
        ItemStack tamahaganeResult
) {
    public static TataraJeiRecipe create(ItemStack furnace, ItemStack ignition, ItemStack ironResult, ItemStack tamahaganeResult) {
        return new TataraJeiRecipe(furnace, ignition, ironResult, tamahaganeResult);
    }
}

