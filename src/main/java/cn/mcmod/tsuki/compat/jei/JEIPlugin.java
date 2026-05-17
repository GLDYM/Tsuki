package cn.mcmod.tsuki.compat.jei;

import java.util.List;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.client.screen.CookingPotScreen;
import cn.mcmod.tsuki.client.screen.DistillerScreen;
import cn.mcmod.tsuki.client.screen.FermenterScreen;
import cn.mcmod.tsuki.client.screen.StoneMortarScreen;
import cn.mcmod.tsuki.compat.farmersdelight.FDCookingPotCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCPotCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCCookingPotCompat;
import cn.mcmod.tsuki.compat.jei.category.ChoppingCategory;
import cn.mcmod.tsuki.compat.jei.category.CookingPotCategory;
import cn.mcmod.tsuki.compat.jei.category.DistillerCategory;
import cn.mcmod.tsuki.compat.jei.category.FermenterCategory;
import cn.mcmod.tsuki.compat.jei.category.ShakerCategory;
import cn.mcmod.tsuki.compat.jei.category.StoneMortarCategory;
import cn.mcmod.tsuki.compat.jei.category.TataraCategory;
import cn.mcmod.tsuki.compat.jei.recipe.TataraJeiRecipe;
import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import cn.mcmod.tsuki.recipe.CookingPotRecipe;
import cn.mcmod.tsuki.recipe.DistillerRecipe;
import cn.mcmod.tsuki.recipe.FermenterRecipe;
import cn.mcmod.tsuki.recipe.ShakerRecipe;
import cn.mcmod.tsuki.recipe.StoneMortarRecipe;
import cn.mcmod.tsuki.container.CookingPotContainer;
import cn.mcmod.tsuki.container.DistillerContainer;
import cn.mcmod.tsuki.container.FermenterContainer;
import cn.mcmod.tsuki.container.StoneMortarContainer;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "jei_plugin");

    private static final Minecraft MC = Minecraft.getInstance();

    private static <I extends RecipeInput, T extends Recipe<I>> List<T> findRecipesByType(RecipeType<T> type) {
        if (MC.level == null) {
            return List.of();
        }
        return MC.level.getRecipeManager().getAllRecipesFor(type).stream()
                .map(RecipeHolder::value)
                .toList();
    }

    public static final mezz.jei.api.recipe.RecipeType<CookingPotRecipe> COOKING_POT_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "cooking", CookingPotRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<StoneMortarRecipe> STONE_MORTAR_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "stone_mortar", StoneMortarRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<FermenterRecipe> FERMENTER_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "fermenting", FermenterRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<DistillerRecipe> DISTILLER_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "distillation", DistillerRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<ShakerRecipe> SHAKER_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "shaker", ShakerRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<ChoppingRecipe> CHOPPING_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "chopping", ChoppingRecipe.class);

    public static final mezz.jei.api.recipe.RecipeType<TataraJeiRecipe> TATARA_JEI_TYPE = mezz.jei.api.recipe.RecipeType
            .create(Tsuki.MODID, "tatara", TataraJeiRecipe.class);

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new CookingPotCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new StoneMortarCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new FermenterCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new DistillerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ShakerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ChoppingCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new TataraCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<CookingPotRecipe> cookingRecipes = new java.util.ArrayList<>(
                findRecipesByType(RecipeTypeRegistry.COOKING_RECIPE_TYPE.get()));
        cookingRecipes.addAll(FDCookingPotCompat.getAllForJei(MC.level));
        cookingRecipes.addAll(KCCookingPotCompat.getAllForJei(MC.level));
        cookingRecipes.addAll(KCPotCompat.getAllForJei(MC.level));
        registration.addRecipes(COOKING_POT_JEI_TYPE, cookingRecipes);
        registration.addRecipes(STONE_MORTAR_JEI_TYPE,
                findRecipesByType(RecipeTypeRegistry.STONE_MORTAR_RECIPE_TYPE.get()));
        registration.addRecipes(FERMENTER_JEI_TYPE, findRecipesByType(RecipeTypeRegistry.FERMENTER_RECIPE_TYPE.get()));
        registration.addRecipes(DISTILLER_JEI_TYPE, findRecipesByType(RecipeTypeRegistry.DISTILLER_RECIPE_TYPE.get()));
        registration.addRecipes(SHAKER_JEI_TYPE, findRecipesByType(RecipeTypeRegistry.SHAKER_RECIPE_TYPE.get()));
        registration.addRecipes(CHOPPING_JEI_TYPE, findRecipesByType(RecipeTypeRegistry.CHOPPING_RECIPE_TYPE.get()));
        registration.addRecipes(TATARA_JEI_TYPE, List.of(TataraJeiRecipe.create(
                new ItemStack(BlockRegistry.TATARA.get()),
                new ItemStack(Items.FLINT_AND_STEEL),
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(ArmorToolRegistry.TAMAHAGANE.get()))));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.COOKING_POT.get()), COOKING_POT_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.STONE_MORTAR.get()), STONE_MORTAR_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.FERMENTER.get()), FERMENTER_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.DISTILLER.get()), DISTILLER_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.SHAKER.get()), SHAKER_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.CHOPPING_BOARD.get()), CHOPPING_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.TATARA.get()), TATARA_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ArmorToolRegistry.STONE_HAMMER.get()), TATARA_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ArmorToolRegistry.IRON_HAMMER.get()), TATARA_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ArmorToolRegistry.STEEL_HAMMER.get()), TATARA_JEI_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ArmorToolRegistry.SAKURA_HAMMER.get()), TATARA_JEI_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CookingPotScreen.class, 94, 16, 34, 29, COOKING_POT_JEI_TYPE);
        registration.addRecipeClickArea(StoneMortarScreen.class, 79, 32, 18, 24, STONE_MORTAR_JEI_TYPE);
        registration.addRecipeClickArea(FermenterScreen.class, 75, 25, 24, 36, FERMENTER_JEI_TYPE);
        registration.addRecipeClickArea(DistillerScreen.class, 75, 25, 24, 36, DISTILLER_JEI_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CookingPotContainer.class, null, COOKING_POT_JEI_TYPE, 0, 9, 10, 36);
        registration.addRecipeTransferHandler(StoneMortarContainer.class, null, STONE_MORTAR_JEI_TYPE, 0, 4, 6, 36);
        registration.addRecipeTransferHandler(FermenterContainer.class, null, FERMENTER_JEI_TYPE, 0, 3, 6, 36);
        registration.addRecipeTransferHandler(DistillerContainer.class, null, DISTILLER_JEI_TYPE, 0, 3, 6, 36);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

}
