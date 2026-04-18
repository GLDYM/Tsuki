package cn.mcmod.tsuki.client.layers;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.client.render.StoneMortarRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.SubscribeEvent;

import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public class LayerRegistry {
    public static final ModelLayerLocation STONE_MORTAR = register("stone_mortar");

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(STONE_MORTAR, StoneMortarRenderer::createLayer);
    }

    private static ModelLayerLocation register(String path) {
        return register(path, "main");
    }

    private static ModelLayerLocation register(String path, String part) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, path), part);
    }
}
