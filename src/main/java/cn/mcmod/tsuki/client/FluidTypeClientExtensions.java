package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.fluid.FluidTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.fluids.FluidType;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public final class FluidTypeClientExtensions {
    private FluidTypeClientExtensions() {
    }

    @SubscribeEvent
    public static void register(RegisterClientExtensionsEvent event) {
        register(event, "food_oil", FluidTypeRegistry.FOOD_OIL.get());
        register(event, "doburoku", FluidTypeRegistry.DOBUROKU.get());
        register(event, "sake", FluidTypeRegistry.SAKE.get());
        register(event, "shouchu", FluidTypeRegistry.SHOUCHU.get());
        register(event, "beer", FluidTypeRegistry.BEER.get());
        register(event, "whiskey", FluidTypeRegistry.WHISKEY.get());
        register(event, "rum", FluidTypeRegistry.RUM.get());
        register(event, "red_wine", FluidTypeRegistry.RED_WINE.get());
        register(event, "white_wine", FluidTypeRegistry.WHITE_WINE.get());
        register(event, "champagne", FluidTypeRegistry.CHAMPAGNE.get());
        register(event, "brandy", FluidTypeRegistry.BRANDY.get());
        register(event, "vodka", FluidTypeRegistry.VODKA.get());
        register(event, "liqueur", FluidTypeRegistry.LIQUEUR.get());
        register(event, "cocoa_liqueur", FluidTypeRegistry.COCOA_LIQUEUR.get());
        register(event, "gin", FluidTypeRegistry.GIN.get());
        register(event, "tequila", FluidTypeRegistry.TEQUILA.get());
        register(event, "maple_syrup", FluidTypeRegistry.MAPLE_SYRUP.get());
        register(event, "hot_spring_water", FluidTypeRegistry.HOT_SPRING_WATER.get());
    }

    private static void register(RegisterClientExtensionsEvent event, String name, FluidType fluidType) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return -1;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "block/" + name + "_still");
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "block/" + name + "_flow");
            }
        }, fluidType);
    }
}
