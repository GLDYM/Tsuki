package cn.mcmod.tsuki.client.render.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.CeilingLightItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CeilingLightItemRenderer extends GeoItemRenderer<CeilingLightItem> {
    public CeilingLightItemRenderer() {
        super(new Model());
    }

    private static class Model extends GeoModel<CeilingLightItem> {
        private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "geo/block/ceiling_light.geo.json");
        private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "animations/block/ceiling_light.animation.json");

        @Override
        public ResourceLocation getModelResource(CeilingLightItem item) {
            return MODEL;
        }

        @Override
        public ResourceLocation getTextureResource(CeilingLightItem item) {
            return item.getTextureResource();
        }

        @Override
        public ResourceLocation getAnimationResource(CeilingLightItem item) {
            return ANIMATION;
        }
    }
}
