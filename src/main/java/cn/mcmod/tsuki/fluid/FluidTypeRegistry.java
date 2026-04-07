package cn.mcmod.tsuki.fluid;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class FluidTypeRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Tsuki.MODID);

    public static final RegistryObject<FluidType> FOOD_OIL = register("food_oil",0xFFFFF050);
    public static final RegistryObject<FluidType> DOBUROKU = register("doburoku",0xFFCCC299);
    public static final RegistryObject<FluidType> SAKE = register("sake",0xDDFFF8CC);
    public static final RegistryObject<FluidType> SHOUCHU =  register("shouchu",0xBBFFFCF2);
    public static final RegistryObject<FluidType> BEER = register("beer",0xFFF2A918);
    public static final RegistryObject<FluidType> WHISKEY = register("whiskey",0xFFA52121);
    public static final RegistryObject<FluidType> RUM = register("rum",0xFFFFAA32);
    public static final RegistryObject<FluidType> RED_WINE = register("red_wine",0xFFA71844);
    public static final RegistryObject<FluidType> WHITE_WINE = register("white_wine",0xFFFFF8B2);
    public static final RegistryObject<FluidType> CHAMPAGNE = register("champagne",0xFFFFE772);
    public static final RegistryObject<FluidType> BRANDY = register("brandy",0xFFBF2F00);
            
    private static RegistryObject<FluidType> register(String name,int color){
        return FLUID_TYPES.register(name,()->create(color));
    }

    private static FluidType create(int color){
        return new FluidType(FluidType.Properties.create()
                .temperature(27)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .density(3000).viscosity(1000)) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public int getTintColor() {
                        return color;
                    }

                    @Override
                    public ResourceLocation getStillTexture() {
                        return new ResourceLocation("block/water_still");
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return new ResourceLocation("block/water_flow");
                    }
                });
            }
        };
    }
}
