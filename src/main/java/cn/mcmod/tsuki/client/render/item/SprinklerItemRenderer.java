package cn.mcmod.tsuki.client.render.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.SprinklerItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SprinklerItemRenderer extends GeoItemRenderer<SprinklerItem> {
    public SprinklerItemRenderer() { super(new Model()); }

    private static class Model extends GeoModel<SprinklerItem> {
        private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/block/sprinkler.png");
        private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "animations/block/sprinkler.animation.json");

        @Override public ResourceLocation getModelResource(SprinklerItem item) { return item.getModelResource(); }
        @Override public ResourceLocation getTextureResource(SprinklerItem item) { return TEXTURE; }
        @Override public ResourceLocation getAnimationResource(SprinklerItem item) { return ANIMATION; }

        @Override
        public void setCustomAnimations(SprinklerItem item, long instanceId,
                AnimationState<SprinklerItem> animationState) {
            super.setCustomAnimations(item, instanceId, animationState);
            GeoBone rotor = getAnimationProcessor().getBone("rotor");
            if (rotor != null) rotor.setRotY(0.0F);
        }
    }
}
