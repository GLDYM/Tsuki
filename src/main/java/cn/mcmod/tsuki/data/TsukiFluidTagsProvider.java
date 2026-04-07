package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.fluid.FluidRegistry;
import cn.mcmod.tsuki.tags.TsukiFluidTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TsukiFluidTagsProvider extends FluidTagsProvider {

    public TsukiFluidTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput,provider, modId, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(FluidTags.WATER).add(FluidRegistry.FOOD_OIL.get(),FluidRegistry.FOOD_OIL_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.DOBUROKU.get(),FluidRegistry.DOBUROKU_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.SAKE.get(),FluidRegistry.SAKE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.SHOUCHU.get(),FluidRegistry.SHOUCHU_FLOWING.get());
        
        tag(FluidTags.WATER).add(FluidRegistry.RED_WINE.get(),FluidRegistry.RED_WINE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.WHITE_WINE.get(),FluidRegistry.WHITE_WINE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.WHISKEY.get(),FluidRegistry.WHISKEY_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.BEER.get(),FluidRegistry.BEER_FLOWING.get());
        
        tag(FluidTags.WATER).add(FluidRegistry.RUM.get(),FluidRegistry.RUM.get());
        tag(FluidTags.WATER).add(FluidRegistry.CHAMPAGNE.get(),FluidRegistry.CHAMPAGNE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.BRANDY.get(),FluidRegistry.BRANDY.get());
        
        tag(TsukiFluidTags.WATER_WATER).add(Fluids.WATER, Fluids.FLOWING_WATER).addOptional(new ResourceLocation("tfc:river_water"));
        tag(TsukiFluidTags.BREWERS_ALCOHOL)
            .add(FluidRegistry.RUM.get(),FluidRegistry.RUM_FLOWING.get())
            .add(FluidRegistry.WHISKEY.get(),FluidRegistry.WHISKEY_FLOWING.get())
            .add(FluidRegistry.SHOUCHU.get(),FluidRegistry.SHOUCHU_FLOWING.get());
        tag(TsukiFluidTags.FOOD_OIL).addTag(TsukiFluidTags.PLANTOIL);
        tag(TsukiFluidTags.PLANTOIL).add(FluidRegistry.FOOD_OIL.get(),FluidRegistry.FOOD_OIL_FLOWING.get()).addOptional(new ResourceLocation("tfc:flowing_olive_oil")).addOptional(new ResourceLocation("tfc:olive_oil"));
    }
}
