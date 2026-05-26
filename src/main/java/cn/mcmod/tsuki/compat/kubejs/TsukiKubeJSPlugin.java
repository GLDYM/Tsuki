package cn.mcmod.tsuki.compat.kubejs;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.compat.kubejs.recipe.ChoppingRecipeSchema;
import cn.mcmod.tsuki.compat.kubejs.recipe.CookingRecipeSchema;
import cn.mcmod.tsuki.compat.kubejs.recipe.DistillationRecipeSchema;
import cn.mcmod.tsuki.compat.kubejs.recipe.FermentingRecipeSchema;
import cn.mcmod.tsuki.compat.kubejs.recipe.ShakerRecipeSchema;
import cn.mcmod.tsuki.compat.kubejs.recipe.StoneMortarRecipeSchema;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;

public class TsukiKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        var namespace = registry.namespace(Tsuki.MODID);
        namespace.register(RecipeTypeRegistry.COOKING_RECIPE_SERIALIZER.getId().getPath(), CookingRecipeSchema.SCHEMA);
        namespace.register(RecipeTypeRegistry.STONE_MORTAR_RECIPE_SERIALIZER.getId().getPath(),
                StoneMortarRecipeSchema.SCHEMA);
        namespace.register(RecipeTypeRegistry.FERMENTER_RECIPE_SERIALIZER.getId().getPath(),
                FermentingRecipeSchema.SCHEMA);
        namespace.register(RecipeTypeRegistry.DISTILLER_RECIPE_SERIALIZER.getId().getPath(),
                DistillationRecipeSchema.SCHEMA);
        namespace.register(RecipeTypeRegistry.SHAKER_RECIPE_SERIALIZER.getId().getPath(), ShakerRecipeSchema.SCHEMA);
        namespace.register(RecipeTypeRegistry.CHOPPING_RECIPE_SERIALIZER.getId().getPath(),
                ChoppingRecipeSchema.SCHEMA);
    }
}
