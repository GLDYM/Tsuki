package cn.mcmod.tsuki.client.gui;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.container.ContainerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public class ScreensRegistry {
    @SubscribeEvent
    public static void screenRegistry(final RegisterMenuScreensEvent event) {
        event.register(ContainerRegistry.STONE_MORTAR.get(), StoneMortarScreen::new);
        event.register(ContainerRegistry.COOKING_POT.get(), CookingPotScreen::new);
        event.register(ContainerRegistry.FERMENTER.get(), FermenterScreen::new);
        event.register(ContainerRegistry.DISTILLER.get(), DistillerScreen::new);
    }
}


