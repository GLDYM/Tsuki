package cn.mcmod.mmlib.recipe;

import com.google.gson.annotations.Expose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public abstract class AbstractRecipe implements Recipe<RecipeWrapper> {
    protected ResourceLocation id;
    @Expose
    public String group;
    @Expose
    public float experience;

    public void setId(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public String getGroup() {
        return this.group == null ? "" : this.group;
    }

    public float getExperience() {
        return experience;
    }
}
