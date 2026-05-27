package cn.mcmod.tsuki.compat.emi;

import java.util.ArrayList;
import java.util.List;

import cn.mcmod.tsuki.compat.EquipmentDyeDisplay;
import cn.mcmod.tsuki.compat.emi.category.EmiChoppingRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiCookingPotRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiDistillerRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiEquipmentDyeRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiFermenterRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiShakerRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiStoneMortarRecipe;
import cn.mcmod.tsuki.compat.emi.category.EmiTataraRecipe;
import cn.mcmod.tsuki.compat.farmersdelight.FDChoppingBoardCompat;
import cn.mcmod.tsuki.compat.farmersdelight.FDCookingPotCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCChoppingBoardCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCPotCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCCookingPotCompat;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import cn.mcmod.tsuki.recipe.CookingPotRecipe;
import cn.mcmod.tsuki.recipe.DistillerRecipe;
import cn.mcmod.tsuki.recipe.FermenterRecipe;
import cn.mcmod.tsuki.recipe.ShakerRecipe;
import cn.mcmod.tsuki.recipe.StoneMortarRecipe;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.Items;

@EmiEntrypoint
public class ModEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) {
            return;
        }

        EmiCookingPotRecipe.register(registry);
        EmiStoneMortarRecipe.register(registry);
        EmiFermenterRecipe.register(registry);
        EmiDistillerRecipe.register(registry);
        EmiShakerRecipe.register(registry);
        EmiChoppingRecipe.register(registry);
        EmiTataraRecipe.register(registry);

        for (RecipeHolder<CookingPotRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeTypeRegistry.COOKING_RECIPE_TYPE.get())) {
            registry.addRecipe(EmiCookingPotRecipe.of(holder.id(), holder.value()));
        }

        List<CookingPotRecipe> fdRecipes = new ArrayList<>(FDCookingPotCompat.getAllForJei(mc.level));
        for (CookingPotRecipe recipe : fdRecipes) {
            registry.addRecipe(EmiCookingPotRecipe.of(toSyncId(recipe.getId()), recipe));
        }

        List<CookingPotRecipe> kcRecipes = new ArrayList<>(KCCookingPotCompat.getAllForJei(mc.level));
        for (CookingPotRecipe recipe : kcRecipes) {
            registry.addRecipe(EmiCookingPotRecipe.of(toSyncId(recipe.getId()), recipe));
        }

        List<CookingPotRecipe> kcPotRecipes = new ArrayList<>(KCPotCompat.getAllForJei(mc.level));
        for (CookingPotRecipe recipe : kcPotRecipes) {
            registry.addRecipe(EmiCookingPotRecipe.of(toSyncId(recipe.getId()), recipe));
        }

        for (RecipeHolder<StoneMortarRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeTypeRegistry.STONE_MORTAR_RECIPE_TYPE.get())) {
            registry.addRecipe(EmiStoneMortarRecipe.of(holder.id(), holder.value()));
        }

        for (RecipeHolder<FermenterRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeTypeRegistry.FERMENTER_RECIPE_TYPE.get())) {
            registry.addRecipe(EmiFermenterRecipe.of(holder.id(), holder.value()));
        }

        for (RecipeHolder<DistillerRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeTypeRegistry.DISTILLER_RECIPE_TYPE.get())) {
            registry.addRecipe(EmiDistillerRecipe.of(holder.id(), holder.value()));
        }

        for (RecipeHolder<ShakerRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeTypeRegistry.SHAKER_RECIPE_TYPE.get())) {
            registry.addRecipe(EmiShakerRecipe.of(holder.id(), holder.value()));
        }

        for (RecipeHolder<ChoppingRecipe> holder : registry.getRecipeManager()
                .getAllRecipesFor(RecipeTypeRegistry.CHOPPING_RECIPE_TYPE.get())) {
            registry.addRecipe(EmiChoppingRecipe.of(holder.id(), holder.value()));
        }
        List<ChoppingRecipe> fdChoppingRecipes = new ArrayList<>(FDChoppingBoardCompat.getAllForJei(mc.level));
        for (ChoppingRecipe recipe : fdChoppingRecipes) {
            registry.addRecipe(EmiChoppingRecipe.of(toSyncId(recipe.getId()), recipe));
        }
        List<ChoppingRecipe> kcChoppingRecipes = new ArrayList<>(KCChoppingBoardCompat.getAllForJei(mc.level));
        for (ChoppingRecipe recipe : kcChoppingRecipes) {
            registry.addRecipe(EmiChoppingRecipe.of(toSyncId(recipe.getId()), recipe));
        }
        registry.addRecipe(EmiTataraRecipe.create(
                new ItemStack(BlockRegistry.TATARA.get()),
                new ItemStack(Items.FLINT_AND_STEEL),
                new ItemStack(Items.IRON_INGOT),
                new ItemStack(ArmorToolRegistry.TAMAHAGANE.get())));
        for (EquipmentDyeDisplay.Entry entry : EquipmentDyeDisplay.entries()) {
            registry.addRecipe(EmiEquipmentDyeRecipe.of(toSyncId(entry.recipeId()), entry));
        }

        registry.addWorkstation(EmiCookingPotRecipe.CATEGORY, EmiStack.of(BlockRegistry.COOKING_POT.get()));
        registry.addWorkstation(EmiStoneMortarRecipe.CATEGORY, EmiStack.of(BlockRegistry.STONE_MORTAR.get()));
        registry.addWorkstation(EmiFermenterRecipe.CATEGORY, EmiStack.of(BlockRegistry.FERMENTER.get()));
        registry.addWorkstation(EmiDistillerRecipe.CATEGORY, EmiStack.of(BlockRegistry.DISTILLER.get()));
        registry.addWorkstation(EmiShakerRecipe.CATEGORY, EmiStack.of(BlockRegistry.SHAKER.get()));
        registry.addWorkstation(EmiChoppingRecipe.CATEGORY, EmiStack.of(BlockRegistry.CHOPPING_BOARD.get()));
        registry.addWorkstation(EmiTataraRecipe.CATEGORY, EmiStack.of(BlockRegistry.TATARA.get()));
        registry.addWorkstation(EmiTataraRecipe.CATEGORY, EmiStack.of(ArmorToolRegistry.STONE_HAMMER.get()));
        registry.addWorkstation(EmiTataraRecipe.CATEGORY, EmiStack.of(ArmorToolRegistry.IRON_HAMMER.get()));
        registry.addWorkstation(EmiTataraRecipe.CATEGORY, EmiStack.of(ArmorToolRegistry.STEEL_HAMMER.get()));
        registry.addWorkstation(EmiTataraRecipe.CATEGORY, EmiStack.of(ArmorToolRegistry.SAKURA_HAMMER.get()));
        registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(Items.CRAFTING_TABLE));
    }

    private static ResourceLocation toSyncId(ResourceLocation id) {
        String path = id.getPath();
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), path);
    }
}
