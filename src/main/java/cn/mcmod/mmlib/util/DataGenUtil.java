package cn.mcmod.mmlib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import com.mojang.serialization.JsonOps;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.mmlib.recipe.ChanceResult;
import cn.mcmod.mmlib.recipe.CountedIngredient;
import net.neoforged.neoforge.fluids.FluidStack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class DataGenUtil {
    public static final Gson NETWORK_GSON = createGson();
    public static final Gson DATA_GSON = createGson();

    private DataGenUtil() {
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Ingredient.class, new IngredientAdapter())
                .registerTypeAdapter(ItemStack.class, new ItemStackAdapter())
                .registerTypeAdapter(ChanceResult.class, new ChanceResultAdapter())
                .registerTypeAdapter(FluidStack.class, new FluidStackAdapter())
                .registerTypeAdapter(FluidIngredient.class, new FluidIngredientAdapter())
                .registerTypeAdapter(CountedIngredient.class, new CountedIngredientAdapter())
                .registerTypeAdapter(NonNullList.class, new NonNullListAdapter())
                .create();
    }

    private static final class IngredientAdapter implements JsonSerializer<Ingredient>, JsonDeserializer<Ingredient> {
        @Override
        public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return Ingredient.CODEC.parse(JsonOps.INSTANCE, json).getOrThrow(JsonParseException::new);
        }

        @Override
        public JsonElement serialize(Ingredient src, Type typeOfSrc, JsonSerializationContext context) {
            return Ingredient.CODEC.encodeStart(JsonOps.INSTANCE, src).getOrThrow(JsonParseException::new);
        }
    }

    private static final class ItemStackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
        @Override
        public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            String itemId = object.has("id") ? object.get("id").getAsString() : object.get("item").getAsString();
            Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId));
            int count = object.has("count") ? object.get("count").getAsInt() : 1;
            return new ItemStack(item, count);
        }

        @Override
        public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("id", BuiltInRegistries.ITEM.getKey(src.getItem()).toString());
            if (src.getCount() != 1) {
                object.addProperty("count", src.getCount());
            }
            return object;
        }
    }

    private static final class FluidStackAdapter implements JsonSerializer<FluidStack>, JsonDeserializer<FluidStack> {
        @Override
        public FluidStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            Fluid fluid = BuiltInRegistries.FLUID.get(ResourceLocation.parse(object.get("fluid").getAsString()));
            int amount = object.has("amount") ? object.get("amount").getAsInt() : 0;
            return new FluidStack(fluid, amount);
        }

        @Override
        public JsonElement serialize(FluidStack src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("fluid", BuiltInRegistries.FLUID.getKey(src.getFluid()).toString());
            object.addProperty("amount", src.getAmount());
            return object;
        }
    }

    private static final class ChanceResultAdapter
            implements JsonSerializer<ChanceResult>, JsonDeserializer<ChanceResult> {
        @Override
        public ChanceResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            float chance = object.has("chance") ? object.get("chance").getAsFloat() : 1.0F;

            ItemStack stack;
            if (object.has("stack")) {
                stack = context.deserialize(object.get("stack"), ItemStack.class);
            } else {
                String itemId = object.has("id") ? object.get("id").getAsString() : object.get("item").getAsString();
                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId));
                int count = object.has("count") ? object.get("count").getAsInt() : 1;
                stack = new ItemStack(item, count);
            }
            return new ChanceResult(stack, chance);
        }

        @Override
        public JsonElement serialize(ChanceResult src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("id", BuiltInRegistries.ITEM.getKey(src.stack().getItem()).toString());
            if (src.stack().getCount() != 1) {
                object.addProperty("count", src.stack().getCount());
            }
            if (src.chance() != 1.0F) {
                object.addProperty("chance", src.chance());
            }
            return object;
        }
    }

    private static final class FluidIngredientAdapter
            implements JsonSerializer<FluidIngredient>, JsonDeserializer<FluidIngredient> {
        @Override
        public FluidIngredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return FluidIngredient.deserialize(json);
        }

        @Override
        public JsonElement serialize(FluidIngredient src, Type typeOfSrc, JsonSerializationContext context) {
            return src.serialize();
        }
    }

    private static final class NonNullListAdapter
            implements JsonSerializer<NonNullList<?>>, JsonDeserializer<NonNullList<?>> {
        @Override
        public NonNullList<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            if (!json.isJsonArray()) {
                throw new JsonParseException("Expected array for NonNullList, got: " + json);
            }

            Type elementType = Object.class;
            if (typeOfT instanceof ParameterizedType parameterizedType) {
                elementType = parameterizedType.getActualTypeArguments()[0];
            }

            NonNullList<Object> list = NonNullList.create();
            for (JsonElement element : json.getAsJsonArray()) {
                list.add(context.deserialize(element, elementType));
            }
            return list;
        }

        @Override
        public JsonElement serialize(NonNullList<?> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonArray array = new JsonArray();
            for (Object element : src) {
                array.add(context.serialize(element));
            }
            return array;
        }
    }

    private static final class CountedIngredientAdapter implements JsonSerializer<CountedIngredient>,
            JsonDeserializer<CountedIngredient> {
        @Override
        public CountedIngredient deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonObject()) {
                Ingredient ingredient = context.deserialize(json, Ingredient.class);
                return new CountedIngredient(ingredient, 1);
            }

            JsonObject object = json.getAsJsonObject();
            int count = object.has("count") ? object.get("count").getAsInt() : 1;

            JsonObject ingredientObject = object.deepCopy();
            ingredientObject.remove("count");
            Ingredient ingredient = context.deserialize(ingredientObject, Ingredient.class);
            return new CountedIngredient(ingredient, count);
        }

        @Override
        public JsonElement serialize(CountedIngredient src, Type typeOfSrc,
                JsonSerializationContext context) {
            JsonElement ingredientJson = context.serialize(src.ingredient(), Ingredient.class);
            JsonObject object;
            if (ingredientJson != null && ingredientJson.isJsonObject()) {
                object = ingredientJson.getAsJsonObject().deepCopy();
            } else {
                object = new JsonObject();
                if (ingredientJson != null) {
                    object.add("ingredient", ingredientJson);
                }
            }
            if (src.count() != 1) {
                object.addProperty("count", src.count());
            }
            return object;
        }
    }
}
