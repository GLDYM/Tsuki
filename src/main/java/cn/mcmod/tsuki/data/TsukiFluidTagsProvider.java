package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.fluid.FluidRegistry;
import cn.mcmod.tsuki.tags.TsukiFluidTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TsukiFluidTagsProvider extends FluidTagsProvider {

    public TsukiFluidTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider,
            String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(FluidTags.WATER).add(FluidRegistry.FOOD_OIL.get(), FluidRegistry.FOOD_OIL_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.DOBUROKU.get(), FluidRegistry.DOBUROKU_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.SAKE.get(), FluidRegistry.SAKE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.SHOUCHU.get(), FluidRegistry.SHOUCHU_FLOWING.get());

        tag(FluidTags.WATER).add(FluidRegistry.RED_WINE.get(), FluidRegistry.RED_WINE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.WHITE_WINE.get(), FluidRegistry.WHITE_WINE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.WHISKEY.get(), FluidRegistry.WHISKEY_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.BEER.get(), FluidRegistry.BEER_FLOWING.get());

        tag(FluidTags.WATER).add(FluidRegistry.RUM.get(), FluidRegistry.RUM_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.CHAMPAGNE.get(), FluidRegistry.CHAMPAGNE_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.BRANDY.get(), FluidRegistry.BRANDY_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.VODKA.get(), FluidRegistry.VODKA_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.LIQUEUR.get(), FluidRegistry.LIQUEUR_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.COCOA_LIQUEUR.get(), FluidRegistry.COCOA_LIQUEUR_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.GIN.get(), FluidRegistry.GIN_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.TEQUILA.get(), FluidRegistry.TEQUILA_FLOWING.get());
        tag(FluidTags.WATER).add(FluidRegistry.MAPLE_SYRUP.get(), FluidRegistry.MAPLE_SYRUP_FLOWING.get());

        tag(TsukiFluidTags.WATER_WATER).add(Fluids.WATER, Fluids.FLOWING_WATER)
                .addOptional(ResourceLocation.parse("tfc:river_water"));
        tag(TsukiFluidTags.RICE_WINE)
                .add(FluidRegistry.DOBUROKU.get(), FluidRegistry.DOBUROKU_FLOWING.get())
                .add(FluidRegistry.SAKE.get(), FluidRegistry.SAKE_FLOWING.get())
                .add(FluidRegistry.SHOUCHU.get(), FluidRegistry.SHOUCHU_FLOWING.get());
        tag(TsukiFluidTags.GRAPE_WINE)
                .add(FluidRegistry.RED_WINE.get(), FluidRegistry.RED_WINE_FLOWING.get())
                .add(FluidRegistry.WHITE_WINE.get(), FluidRegistry.WHITE_WINE_FLOWING.get());
        tag(TsukiFluidTags.BREWERS_ALCOHOL)
                .add(FluidRegistry.RUM.get(), FluidRegistry.RUM_FLOWING.get())
                .add(FluidRegistry.WHISKEY.get(), FluidRegistry.WHISKEY_FLOWING.get())
                .add(FluidRegistry.SHOUCHU.get(), FluidRegistry.SHOUCHU_FLOWING.get())
                .add(FluidRegistry.VODKA.get(), FluidRegistry.VODKA_FLOWING.get())
                .add(FluidRegistry.BRANDY.get(), FluidRegistry.BRANDY_FLOWING.get());
        tag(TsukiFluidTags.FOOD_OIL).addTag(TsukiFluidTags.PLANTOIL);
        tag(TsukiFluidTags.PLANTOIL).add(FluidRegistry.FOOD_OIL.get(), FluidRegistry.FOOD_OIL_FLOWING.get())
                .addOptional(ResourceLocation.parse("tfc:flowing_olive_oil"))
                .addOptional(ResourceLocation.parse("tfc:olive_oil"));
    }
}
