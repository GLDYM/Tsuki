package cn.mcmod.tsuki.tags;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.mmlib.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class TsukiFluidTags {
    public static final TagKey<Fluid> WATER_WATER = TagUtil.forgeFluidTag("water/water");
    public static final TagKey<Fluid> FOOD_OIL = TagUtil.forgeFluidTag("food_oil");
    public static final TagKey<Fluid> PLANTOIL = TagUtil.forgeFluidTag("plantoil");
    public static final TagKey<Fluid> SOYSAUCE = TagUtil.forgeFluidTag("soysauce");

    public static final TagKey<Fluid> RICE_WINE = TagUtil.modFluidTag(Tsuki.MODID, "rice_wine");
    public static final TagKey<Fluid> GRAPE_WINE = TagUtil.modFluidTag(Tsuki.MODID, "grape_wine");
    public static final TagKey<Fluid> BREWERS_ALCOHOL = TagUtil.modFluidTag(Tsuki.MODID, "brewers_alcohol");
}
