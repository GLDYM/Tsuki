package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.MenuTypeRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public class ScreensRegistry {
    @SubscribeEvent
    public static void screenRegistry(final RegisterMenuScreensEvent event) {
        event.register(MenuTypeRegistry.STONE_MORTAR.get(), StoneMortarScreen::new);
        event.register(MenuTypeRegistry.COOKING_POT.get(), CookingPotScreen::new);
        event.register(MenuTypeRegistry.FERMENTER.get(), FermenterScreen::new);
        event.register(MenuTypeRegistry.DISTILLER.get(), DistillerScreen::new);
    }
}
