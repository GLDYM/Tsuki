package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.block.machine.LighthouseIlluminationBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class LighthouseIlluminationGeoModel extends GeoModel<LighthouseIlluminationBlockEntity> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/lighthouse_illumination.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/block/lighthouse_illumination.png");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "animations/block/lighthouse_illumination.animation.json");

    @Override public ResourceLocation getModelResource(LighthouseIlluminationBlockEntity animatable) { return MODEL; }
    @Override public ResourceLocation getTextureResource(LighthouseIlluminationBlockEntity animatable) { return TEXTURE; }
    @Override public ResourceLocation getAnimationResource(LighthouseIlluminationBlockEntity animatable) { return ANIMATION; }

    @Override
    public void setCustomAnimations(LighthouseIlluminationBlockEntity entity, long instanceId,
            AnimationState<LighthouseIlluminationBlockEntity> animationState) {
        super.setCustomAnimations(entity, instanceId, animationState);
        GeoBone upper = getAnimationProcessor().getBone("upper");
        if (upper == null || entity.getLevel() == null) return;
        if (!entity.getBlockState().getValue(LighthouseIlluminationBlock.LIT)) {
            upper.setRotY(0.0F);
            return;
        }
        float degrees = (entity.getLevel().getGameTime() + animationState.getPartialTick()) * 2.0F;
        upper.setRotY(degrees * Mth.DEG_TO_RAD);
    }
}
