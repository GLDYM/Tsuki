package cn.mcmod.tsuki.tags;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod_mmf.mmlib.utils.TagUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class TsukiFluidTags {
    public static final TagKey<Fluid> WATER_WATER = TagUtils.forgeFluidTag("water/water");
    public static final TagKey<Fluid> FOOD_OIL = TagUtils.forgeFluidTag("food_oil");
    public static final TagKey<Fluid> PLANTOIL = TagUtils.forgeFluidTag("plantoil");
    public static final TagKey<Fluid> SOYSAUCE = TagUtils.forgeFluidTag("soysauce");

    public static final TagKey<Fluid> RICE_WINE = TagUtils.modFluidTag(Tsuki.MODID, "rice_wine");
    public static final TagKey<Fluid> GRAPE_WINE = TagUtils.modFluidTag(Tsuki.MODID, "grape_wine");
    public static final TagKey<Fluid> BREWERS_ALCOHOL = TagUtils.modFluidTag(Tsuki.MODID, "brewers_alcohol");
}
