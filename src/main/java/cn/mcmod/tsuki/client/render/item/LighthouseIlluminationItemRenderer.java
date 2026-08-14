package cn.mcmod.tsuki.client.render.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.LighthouseIlluminationItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LighthouseIlluminationItemRenderer extends GeoItemRenderer<LighthouseIlluminationItem> {
    public LighthouseIlluminationItemRenderer() {
        super(new Model());
    }

    private static class Model extends GeoModel<LighthouseIlluminationItem> {
        private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "geo/block/lighthouse_illumination.geo.json");
        private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/block/lighthouse_illumination.png");

        @Override
        public ResourceLocation getModelResource(LighthouseIlluminationItem animatable) {
            return MODEL;
        }

        @Override
        public ResourceLocation getTextureResource(LighthouseIlluminationItem animatable) {
            return TEXTURE;
        }

        @Override
        public ResourceLocation getAnimationResource(LighthouseIlluminationItem animatable) {
            return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                    "animations/block/lighthouse_illumination.animation.json");
        }

        @Override
        public void setCustomAnimations(LighthouseIlluminationItem animatable, long instanceId,
                AnimationState<LighthouseIlluminationItem> animationState) {
            super.setCustomAnimations(animatable, instanceId, animationState);
            GeoBone upper = getAnimationProcessor().getBone("upper");
            if (upper != null) {
                upper.setRotX(0.0F);
                upper.setRotY(0.0F);
                upper.setRotZ(0.0F);
            }
        }
    }
}
