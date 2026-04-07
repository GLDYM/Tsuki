package cn.mcmod.mmlib.fluid;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class FluidIngredient {
    public static final FluidIngredient EMPTY = new FluidIngredient(null, Fluids.EMPTY, 0);

    private final TagKey<Fluid> fluidTag;
    private final Fluid fluid;
    private final int amount;

    private FluidIngredient(TagKey<Fluid> fluidTag, Fluid fluid, int amount) {
        this.fluidTag = fluidTag;
        this.fluid = fluid;
        this.amount = amount;
    }

    public static FluidIngredient fromTag(TagKey<Fluid> tag, int amount) {
        return new FluidIngredient(tag, Fluids.EMPTY, amount);
    }

    public static FluidIngredient fromFluid(Fluid fluid, int amount) {
        return new FluidIngredient(null, fluid, amount);
    }

    public static boolean isFluidIngredient(JsonElement json) {
        if (json == null || !json.isJsonObject()) {
            return false;
        }
        JsonObject object = json.getAsJsonObject();
        return object.has("fluid") || object.has("tag") || object.has("fluidTag");
    }

    public boolean test(FluidStack stack) {
        if (this == EMPTY) {
            return stack.isEmpty();
        }
        if (stack.isEmpty() || stack.getAmount() < amount) {
            return false;
        }
        if (fluidTag != null) {
            return stack.getFluid().builtInRegistryHolder().is(fluidTag);
        }
        return stack.getFluid() == fluid;
    }

    public int getRequiredAmount() {
        return this.amount;
    }

    public List<FluidStack> getMatchingFluidStacks() {
        List<FluidStack> matches = new ArrayList<>();
        if (this == EMPTY || this.amount <= 0) {
            return matches;
        }

        if (this.fluidTag != null) {
            for (Fluid fluidEntry : BuiltInRegistries.FLUID) {
                if (fluidEntry.builtInRegistryHolder().is(this.fluidTag)) {
                    matches.add(new FluidStack(fluidEntry, this.amount));
                }
            }
            return matches;
        }

        if (this.fluid != Fluids.EMPTY) {
            matches.add(new FluidStack(this.fluid, this.amount));
        }
        return matches;
    }

    public JsonObject serialize() {
        JsonObject json = new JsonObject();
        if (this.fluidTag != null) {
            json.addProperty("fluidTag", this.fluidTag.location().toString());
        } else if (this.fluid != Fluids.EMPTY) {
            json.addProperty("fluid", BuiltInRegistries.FLUID.getKey(this.fluid).toString());
        }
        json.addProperty("amount", this.amount);
        return json;
    }

    public JsonObject toJson() {
        return this.serialize();
    }

    public static FluidIngredient deserialize(JsonElement json) {
        if (json == null || !json.isJsonObject()) {
            return EMPTY;
        }
        return fromJson(json.getAsJsonObject());
    }

    public static FluidIngredient fromJson(JsonObject json) {
        if (json == null || json.size() == 0) {
            return EMPTY;
        }
        int amount = json.has("amount") ? json.get("amount").getAsInt() : 0;
        if (json.has("fluidTag")) {
            ResourceLocation id = ResourceLocation.parse(json.get("fluidTag").getAsString());
            return fromTag(TagKey.create(BuiltInRegistries.FLUID.key(), id), amount);
        }
        if (json.has("tag")) {
            ResourceLocation id = ResourceLocation.parse(json.get("tag").getAsString());
            return fromTag(TagKey.create(BuiltInRegistries.FLUID.key(), id), amount);
        }
        if (json.has("fluid")) {
            ResourceLocation id = ResourceLocation.parse(json.get("fluid").getAsString());
            Fluid fluid = BuiltInRegistries.FLUID.get(id);
            return fromFluid(fluid, amount);
        }
        return EMPTY;
    }
}
