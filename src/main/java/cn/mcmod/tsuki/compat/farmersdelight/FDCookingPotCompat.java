package cn.mcmod.tsuki.compat.farmersdelight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

public final class FDCookingPotCompat {
    private static final String FD_MODID = "farmersdelight";
    private static final ResourceLocation COMPAT_ID_PREFIX = ResourceLocation.fromNamespaceAndPath(FD_MODID, "cooking");

    private FDCookingPotCompat() {
    }

    public static Optional<cn.mcmod.tsuki.recipes.CookingPotRecipe> findMatching(Level level, RecipeWrapper wrapper, FluidStack fluid) {
        if (level == null || !isEnabled() || !fluid.isEmpty()) {
            return Optional.empty();
        }

        RecipeType<CookingPotRecipe> fdCookingType = getFdCookingType();
        if (fdCookingType == null) {
            return Optional.empty();
        }

        for (RecipeHolder<CookingPotRecipe> holder : level.getRecipeManager().getAllRecipesFor(fdCookingType)) {
            CookingPotRecipe recipe = holder.value();
            if (recipe.matches(wrapper, level)) {
                return Optional.of(transform(holder.id(), recipe, level));
            }
        }
        return Optional.empty();
    }

    public static List<cn.mcmod.tsuki.recipes.CookingPotRecipe> getAllForJei(Level level) {
        if (level == null || !isEnabled()) {
            return List.of();
        }

        RecipeType<CookingPotRecipe> fdCookingType = getFdCookingType();
        if (fdCookingType == null) {
            return List.of();
        }

        List<cn.mcmod.tsuki.recipes.CookingPotRecipe> recipes = new ArrayList<>();
        for (RecipeHolder<CookingPotRecipe> holder : level.getRecipeManager().getAllRecipesFor(fdCookingType)) {
            recipes.add(transform(holder.id(), holder.value(), level));
        }
        return recipes;
    }

    private static cn.mcmod.tsuki.recipes.CookingPotRecipe transform(ResourceLocation sourceId, CookingPotRecipe source, Level level) {
        cn.mcmod.tsuki.recipes.CookingPotRecipe recipe = new cn.mcmod.tsuki.recipes.CookingPotRecipe();
        recipe.setId(ResourceLocation.fromNamespaceAndPath(COMPAT_ID_PREFIX.getNamespace(), COMPAT_ID_PREFIX.getPath() + "/compat_" + sourceId.getPath()));

        NonNullList<Ingredient> inputs = NonNullList.create();
        for (Ingredient ingredient : source.getIngredients()) {
            if (!ingredient.isEmpty()) {
                inputs.add(ingredient);
            }
        }

        recipe.inputItems = inputs;
        recipe.fluidInput = FluidIngredient.EMPTY;
        recipe.output = source.getResultItem(level.registryAccess()).copy();
        recipe.container = source.getOutputContainer().copy();
        recipe.recipeTime = source.getCookTime();
        recipe.experience = source.getExperience();
        return recipe;
    }

    private static boolean isEnabled() {
        return ModList.get().isLoaded(FD_MODID);
    }

    private static RecipeType<CookingPotRecipe> getFdCookingType() {
        return ModRecipeTypes.COOKING.get();
    }
}
