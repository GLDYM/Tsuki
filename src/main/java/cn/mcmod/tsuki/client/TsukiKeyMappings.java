package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class TsukiKeyMappings {
    public static final KeyMapping SHEATH_ACTION = new KeyMapping(
            "key.tsuki.sheath_in",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "key.categories.tsuki"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SHEATH_ACTION);
    }
}

