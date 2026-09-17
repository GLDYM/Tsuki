package cn.mcmod.tsuki.compat;

import java.util.List;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public final class KatanaSheathingDisplay {
    private KatanaSheathingDisplay() {
    }

    public static List<Entry> entries() {
        return List.of(
                new Entry("katana",
                        List.of(Ingredient.of(ArmorToolRegistry.KATANA.get()),
                                Ingredient.of(ArmorToolRegistry.SHEATH.get())),
                        new ItemStack(ArmorToolRegistry.KATANA_SHEATH.get())),
                new Entry("sakura_katana",
                        List.of(Ingredient.of(ArmorToolRegistry.SAKURA_KATANA.get()),
                                Ingredient.of(ArmorToolRegistry.SHEATH.get())),
                        new ItemStack(ArmorToolRegistry.SAKURA_KATANA_SHEATH.get())));
    }

    public record Entry(String id, List<Ingredient> inputs, ItemStack output) {
        public ResourceLocation recipeId() {
            return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "katana_sheathing/" + id);
        }

        public ShapelessRecipe toShapelessRecipe() {
            NonNullList<Ingredient> ingredients = NonNullList.create();
            ingredients.addAll(inputs);
            return new ShapelessRecipe("", CraftingBookCategory.EQUIPMENT, output, ingredients);
        }
    }
}
