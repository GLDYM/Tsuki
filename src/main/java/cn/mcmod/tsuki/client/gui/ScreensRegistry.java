package cn.mcmod.tsuki.client.gui;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.container.ContainerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Tsuki.MODID, value = Dist.CLIENT)
public class ScreensRegistry {
    @SubscribeEvent
    public static void screenRegistry(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegistry.STONE_MORTAR.get(), StoneMortarScreen::new);
            MenuScreens.register(ContainerRegistry.COOKING_POT.get(), CookingPotScreen::new);
            MenuScreens.register(ContainerRegistry.FERMENTER.get(), FermenterScreen::new);
            MenuScreens.register(ContainerRegistry.DISTILLER.get(), DistillerScreen::new);
        });
    }
}
