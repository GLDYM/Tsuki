package cn.mcmod.tsuki.compat.emi.category;

import java.util.List;

import cn.mcmod.tsuki.compat.KatanaSheathingDisplay;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;

public class EmiKatanaSheathingRecipe extends EmiCraftingRecipe {
    private EmiKatanaSheathingRecipe(List<EmiIngredient> input, EmiStack output, ResourceLocation id) {
        super(input, output, id, true);
    }

    public static EmiKatanaSheathingRecipe of(ResourceLocation id, KatanaSheathingDisplay.Entry entry) {
        return new EmiKatanaSheathingRecipe(
                entry.inputs().stream().map(EmiIngredient::of).toList(),
                EmiStack.of(entry.output()),
                id);
    }
}
