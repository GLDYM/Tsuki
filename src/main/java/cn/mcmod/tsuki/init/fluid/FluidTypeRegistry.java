package cn.mcmod.tsuki.init.fluid;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class FluidTypeRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister
            .create(NeoForgeRegistries.Keys.FLUID_TYPES, Tsuki.MODID);

    public static final DeferredHolder<FluidType, FluidType> FOOD_OIL = register("food_oil", 0xFFFFF050);
    public static final DeferredHolder<FluidType, FluidType> DOBUROKU = register("doburoku", 0xFFCCC299);
    public static final DeferredHolder<FluidType, FluidType> SAKE = register("sake", 0xDDFFF8CC);
    public static final DeferredHolder<FluidType, FluidType> SHOUCHU = register("shouchu", 0xBBFFFCF2);
    public static final DeferredHolder<FluidType, FluidType> BEER = register("beer", 0xFFF2A918);
    public static final DeferredHolder<FluidType, FluidType> WHISKEY = register("whiskey", 0xFFA52121);
    public static final DeferredHolder<FluidType, FluidType> RUM = register("rum", 0xFFFFAA32);
    public static final DeferredHolder<FluidType, FluidType> RED_WINE = register("red_wine", 0xFFA71844);
    public static final DeferredHolder<FluidType, FluidType> WHITE_WINE = register("white_wine", 0xFFFFF8B2);
    public static final DeferredHolder<FluidType, FluidType> CHAMPAGNE = register("champagne", 0xFFFFE772);
    public static final DeferredHolder<FluidType, FluidType> BRANDY = register("brandy", 0xFFBF2F00);
    public static final DeferredHolder<FluidType, FluidType> VODKA = register("vodka", 0xFFF2FBFF);
    public static final DeferredHolder<FluidType, FluidType> LIQUEUR = register("liqueur", 0xFFD4E020);
    public static final DeferredHolder<FluidType, FluidType> COCOA_LIQUEUR = register("cocoa_liqueur", 0xFF3A1E0F);
    public static final DeferredHolder<FluidType, FluidType> GIN = register("gin", 0xFFE4F6E8);
    public static final DeferredHolder<FluidType, FluidType> TEQUILA = register("tequila", 0xFFD4A34A);
    public static final DeferredHolder<FluidType, FluidType> MAPLE_SYRUP = register("maple_syrup", 0xFF2DFFD8);
    public static final DeferredHolder<FluidType, FluidType> HOT_SPRING_WATER = register("hot_spring_water", 0xFFE8A85A);

    @Deprecated
    private static DeferredHolder<FluidType, FluidType> register(String name, int color) {
        return register(name);
    }

    private static DeferredHolder<FluidType, FluidType> register(String name) {
        return FLUID_TYPES.register(name, () -> create(name));
    }

    private static FluidType create(String name) {
        return new FluidType(FluidType.Properties.create()
                .temperature(27)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .density(3000).viscosity(1000));
    }
}
