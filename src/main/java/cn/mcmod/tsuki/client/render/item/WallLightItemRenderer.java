package cn.mcmod.tsuki.client.render.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.WallLightItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WallLightItemRenderer extends GeoItemRenderer<WallLightItem> {
    public WallLightItemRenderer() {
        super(new Model());
    }

    private static class Model extends GeoModel<WallLightItem> {
        private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "geo/block/wall_light.geo.json");
        private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "animations/block/wall_light.animation.json");

        @Override
        public ResourceLocation getModelResource(WallLightItem item) {
            return MODEL;
        }

        @Override
        public ResourceLocation getTextureResource(WallLightItem item) {
            return item.getTextureResource();
        }

        @Override
        public ResourceLocation getAnimationResource(WallLightItem item) {
            return ANIMATION;
        }
    }
}
