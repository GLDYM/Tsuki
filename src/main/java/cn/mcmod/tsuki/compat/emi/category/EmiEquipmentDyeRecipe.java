package cn.mcmod.tsuki.compat.emi.category;

import java.util.List;

import cn.mcmod.tsuki.compat.EquipmentDyeDisplay;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;

public class EmiEquipmentDyeRecipe extends EmiCraftingRecipe {
    private EmiEquipmentDyeRecipe(List<EmiIngredient> input, EmiStack output,
            net.minecraft.resources.ResourceLocation id) {
        super(input, output, id, true);
    }

    public static EmiEquipmentDyeRecipe of(net.minecraft.resources.ResourceLocation id,
            EquipmentDyeDisplay.Entry entry) {
        return new EmiEquipmentDyeRecipe(
                entry.inputs().stream().map(EmiIngredient::of).toList(),
                EmiStack.of(entry.output()),
                id);
    }
}
