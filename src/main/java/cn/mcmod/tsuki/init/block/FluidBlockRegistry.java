package cn.mcmod.tsuki.init.block;

import net.minecraft.world.level.block.state.BlockBehaviour;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FluidBlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Tsuki.MODID);
    public static final DeferredBlock<LiquidBlock> FOOD_OIL_BLOCK = BLOCKS.register("food_oil",
            () -> new LiquidBlock(FluidRegistry.FOOD_OIL.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static final DeferredBlock<LiquidBlock> DOBUROKU_BLOCK = BLOCKS.register("doburoku",
            () -> new LiquidBlock(FluidRegistry.DOBUROKU.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> SAKE_BLOCK = BLOCKS.register("sake",
            () -> new LiquidBlock(FluidRegistry.SAKE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> SHOUCHU_BLOCK = BLOCKS.register("shouchu",
            () -> new LiquidBlock(FluidRegistry.SHOUCHU.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static final DeferredBlock<LiquidBlock> BEER_BLOCK = BLOCKS.register("beer",
            () -> new LiquidBlock(FluidRegistry.BEER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> WHISKEY_BLOCK = BLOCKS.register("whiskey",
            () -> new LiquidBlock(FluidRegistry.WHISKEY.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> RED_WINE_BLOCK = BLOCKS.register("red_wine",
            () -> new LiquidBlock(FluidRegistry.RED_WINE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> WHITE_WINE_BLOCK = BLOCKS.register("white_wine",
            () -> new LiquidBlock(FluidRegistry.WHITE_WINE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> BRANDY_BLOCK = BLOCKS.register("brandy",
            () -> new LiquidBlock(FluidRegistry.BRANDY.get(), BlockBehaviour.Properties.of()));
    public static final DeferredBlock<LiquidBlock> RUM_BLOCK = BLOCKS.register("rum",
            () -> new LiquidBlock(FluidRegistry.RUM.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> CHAMPAGNE_BLOCK = BLOCKS.register("champagne",
            () -> new LiquidBlock(FluidRegistry.CHAMPAGNE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> VODKA_BLOCK = BLOCKS.register("vodka",
            () -> new LiquidBlock(FluidRegistry.VODKA.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> LIQUEUR_BLOCK = BLOCKS.register("liqueur",
            () -> new LiquidBlock(FluidRegistry.LIQUEUR.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> COCOA_LIQUEUR_BLOCK = BLOCKS.register("cocoa_liqueur",
            () -> new LiquidBlock(FluidRegistry.COCOA_LIQUEUR.get(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> GIN_BLOCK = BLOCKS.register("gin",
            () -> new LiquidBlock(FluidRegistry.GIN.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> TEQUILA_BLOCK = BLOCKS.register("tequila",
            () -> new LiquidBlock(FluidRegistry.TEQUILA.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static final DeferredBlock<LiquidBlock> MAPLE_SYRUP_BLOCK = BLOCKS.register("maple_syrup",
            () -> new LiquidBlock(FluidRegistry.MAPLE_SYRUP.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
}
