package cn.mcmod.mmlib.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;

import cn.mcmod.mmlib.util.DataGenUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class AbstractRecipeSerializer<T extends AbstractRecipe> implements RecipeSerializer<T> {
    private final Class<T> recipeClass;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

    public AbstractRecipeSerializer(Class<T> recipeClass) {
        this.recipeClass = recipeClass;

        Codec<T> gsonCodec = Codec.PASSTHROUGH.flatXmap(
                dynamic -> {
                    try {
                        JsonElement json = dynamic.convert(JsonOps.INSTANCE).getValue();
                        return DataResult.success(DataGenUtil.NETWORK_GSON.fromJson(json, recipeClass));
                    } catch (Exception e) {
                        return DataResult.error(e::getMessage);
                    }
                },
                recipe -> DataResult.success(new Dynamic<>(JsonOps.INSTANCE, DataGenUtil.NETWORK_GSON.toJsonTree(recipe)))
        );
        this.codec = MapCodec.assumeMapUnsafe(gsonCodec);
        this.streamCodec = StreamCodec.of(
                (buffer, recipe) -> buffer.writeUtf(DataGenUtil.NETWORK_GSON.toJson(toJson(recipe))),
                buffer -> DataGenUtil.NETWORK_GSON.fromJson(buffer.readUtf(), recipeClass)
        );
    }

    public JsonObject toJson(T recipe) {
        return DataGenUtil.NETWORK_GSON.toJsonTree(recipe).getAsJsonObject();
    }

    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        T recipe = DataGenUtil.NETWORK_GSON.fromJson(json, recipeClass);
        recipe.setId(recipeId);
        return recipe;
    }

    public T fromNetwork(ResourceLocation recipeId, RegistryFriendlyByteBuf buffer) {
        JsonObject json = DataGenUtil.NETWORK_GSON.fromJson(buffer.readUtf(), JsonObject.class);
        return fromJson(recipeId, json);
    }

    public void toNetwork(RegistryFriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(DataGenUtil.NETWORK_GSON.toJson(toJson(recipe)));
    }

    @Override
    public MapCodec<T> codec() {
        return this.codec;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
        return this.streamCodec;
    }
}
