package cn.mcmod.tsuki.fluid;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FluidRegistry {
        public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Tsuki.MODID);

        public static final DeferredHolder<Fluid, FlowingFluid> FOOD_OIL = FLUIDS.register("food_oil",
            () -> new BaseFlowingFluid.Source(FluidRegistry.FOOD_OIL_PROP));
        public static final DeferredHolder<Fluid, FlowingFluid> FOOD_OIL_FLOWING = FLUIDS.register("food_oil_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.FOOD_OIL_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> DOBUROKU = FLUIDS.register("doburoku",
            () -> new BaseFlowingFluid.Source(FluidRegistry.DOBUROKU_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> DOBUROKU_FLOWING = FLUIDS.register("doburoku_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.DOBUROKU_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> SAKE = FLUIDS.register("sake",
            () -> new BaseFlowingFluid.Source(FluidRegistry.SAKE_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> SAKE_FLOWING = FLUIDS.register("sake_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.SAKE_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> SHOUCHU = FLUIDS.register("shouchu",
            () -> new BaseFlowingFluid.Source(FluidRegistry.SHOUCHU_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> SHOUCHU_FLOWING = FLUIDS.register("shouchu_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.SHOUCHU_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> BEER = FLUIDS.register("beer",
            () -> new BaseFlowingFluid.Source(FluidRegistry.BEER_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> BEER_FLOWING = FLUIDS.register("beer_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.BEER_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> WHISKEY = FLUIDS.register("whiskey",
            () -> new BaseFlowingFluid.Source(FluidRegistry.WHISKEY_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> WHISKEY_FLOWING = FLUIDS.register("whiskey_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.WHISKEY_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> RUM = FLUIDS.register("rum",
            () -> new BaseFlowingFluid.Source(FluidRegistry.RUM_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> RUM_FLOWING = FLUIDS.register("rum_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.RUM_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> RED_WINE = FLUIDS.register("red_wine",
            () -> new BaseFlowingFluid.Source(FluidRegistry.RED_WINE_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> RED_WINE_FLOWING = FLUIDS.register("red_wine_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.RED_WINE_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> WHITE_WINE = FLUIDS.register("white_wine",
            () -> new BaseFlowingFluid.Source(FluidRegistry.WHITE_WINE_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> WHITE_WINE_FLOWING = FLUIDS.register("white_wine_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.WHITE_WINE_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> CHAMPAGNE = FLUIDS.register("champagne",
            () -> new BaseFlowingFluid.Source(FluidRegistry.CHAMPAGNE_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> CHAMPAGNE_FLOWING = FLUIDS.register("champagne_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.CHAMPAGNE_PROP));
    
    public static final DeferredHolder<Fluid, FlowingFluid> BRANDY = FLUIDS.register("brandy",
            () -> new BaseFlowingFluid.Source(FluidRegistry.BRANDY_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> BRANDY_FLOWING = FLUIDS.register("brandy_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.BRANDY_PROP));

    public static final DeferredHolder<Fluid, FlowingFluid> MAPLE_SYRUP = FLUIDS.register("maple_syrup",
            () -> new BaseFlowingFluid.Source(FluidRegistry.MAPLE_SYRUP_PROP));
    public static final DeferredHolder<Fluid, FlowingFluid> MAPLE_SYRUP_FLOWING = FLUIDS.register("maple_syrup_flowing",
            () -> new BaseFlowingFluid.Flowing(FluidRegistry.MAPLE_SYRUP_PROP));
    

    private static final BaseFlowingFluid.Properties FOOD_OIL_PROP = 
            createProp(FOOD_OIL, FOOD_OIL_FLOWING, FluidTypeRegistry.FOOD_OIL, FluidBlockRegistry.FOOD_OIL_BLOCK,BucketItemRegistry.FOOD_OIL_BUCKET);

    private static final BaseFlowingFluid.Properties DOBUROKU_PROP = 
            createProp(DOBUROKU, DOBUROKU_FLOWING, FluidTypeRegistry.DOBUROKU, FluidBlockRegistry.DOBUROKU_BLOCK,BucketItemRegistry.DOBUROKU_BUCKET);
    
    private static final BaseFlowingFluid.Properties SAKE_PROP = 
            createProp(SAKE, SAKE_FLOWING, FluidTypeRegistry.SAKE, FluidBlockRegistry.SAKE_BLOCK,BucketItemRegistry.SAKE_BUCKET);
    
    private static final BaseFlowingFluid.Properties SHOUCHU_PROP = 
            createProp(SHOUCHU, SHOUCHU_FLOWING, FluidTypeRegistry.SHOUCHU, FluidBlockRegistry.SHOUCHU_BLOCK,BucketItemRegistry.SHOUCHU_BUCKET);
    
    private static final BaseFlowingFluid.Properties BEER_PROP = 
            createProp(BEER, BEER_FLOWING, FluidTypeRegistry.BEER, FluidBlockRegistry.BEER_BLOCK,BucketItemRegistry.BEER_BUCKET);
    
    private static final BaseFlowingFluid.Properties BRANDY_PROP = 
            createProp(BRANDY, BRANDY_FLOWING, FluidTypeRegistry.BRANDY, FluidBlockRegistry.BRANDY_BLOCK,BucketItemRegistry.BRANDY_BUCKET);
    
    private static final BaseFlowingFluid.Properties WHISKEY_PROP = 
            createProp(WHISKEY, WHISKEY_FLOWING, FluidTypeRegistry.WHISKEY, FluidBlockRegistry.WHISKEY_BLOCK,BucketItemRegistry.WHISKEY_BUCKET);
    
    private static final BaseFlowingFluid.Properties RUM_PROP = 
            createProp(RUM, RUM_FLOWING, FluidTypeRegistry.RUM, FluidBlockRegistry.RUM_BLOCK,BucketItemRegistry.RUM_BUCKET);
    
    private static final BaseFlowingFluid.Properties RED_WINE_PROP = 
            createProp(RED_WINE, RED_WINE_FLOWING, FluidTypeRegistry.RED_WINE, FluidBlockRegistry.RED_WINE_BLOCK,BucketItemRegistry.RED_WINE_BUCKET);
    
    private static final BaseFlowingFluid.Properties WHITE_WINE_PROP = 
            createProp(WHITE_WINE, WHITE_WINE_FLOWING, FluidTypeRegistry.WHITE_WINE, FluidBlockRegistry.WHITE_WINE_BLOCK,BucketItemRegistry.WHITE_WINE_BUCKET);
    
    private static final BaseFlowingFluid.Properties CHAMPAGNE_PROP = 
            createProp(CHAMPAGNE, CHAMPAGNE_FLOWING, FluidTypeRegistry.CHAMPAGNE, FluidBlockRegistry.CHAMPAGNE_BLOCK,BucketItemRegistry.CHAMPAGNE_BUCKET);

    private static final BaseFlowingFluid.Properties MAPLE_SYRUP_PROP =
            createProp(MAPLE_SYRUP, MAPLE_SYRUP_FLOWING, FluidTypeRegistry.MAPLE_SYRUP, FluidBlockRegistry.MAPLE_SYRUP_BLOCK, BucketItemRegistry.MAPLE_SYRUP_BUCKET);
    
    private static BaseFlowingFluid.Properties createProp(
            Supplier<? extends Fluid> still, 
            Supplier<? extends Fluid> flowing,
            Supplier<FluidType> fluidType,
            Supplier<? extends LiquidBlock> block,
            Supplier<? extends Item> bucket){

        UnaryOperator<BaseFlowingFluid.Properties> blockProperties = p->p.block(block).bucket(bucket).slopeFindDistance(3).explosionResistance(100F);
        return blockProperties.apply(new BaseFlowingFluid.Properties(fluidType ,still, flowing));
    }
}


