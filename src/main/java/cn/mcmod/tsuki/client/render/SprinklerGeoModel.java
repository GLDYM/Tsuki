package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.SprinklerBlockEntity;
import cn.mcmod.tsuki.block.machine.SprinklerBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class SprinklerGeoModel extends GeoModel<SprinklerBlockEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/block/sprinkler.png");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "animations/block/sprinkler.animation.json");

    @Override
    public ResourceLocation getModelResource(SprinklerBlockEntity entity) {
        String material = entity.getBlockState().getValue(SprinklerBlock.MATERIAL).getSerializedName();
        return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "geo/block/" + material + "_sprinkler.geo.json");
    }

    @Override public ResourceLocation getTextureResource(SprinklerBlockEntity entity) { return TEXTURE; }
    @Override public ResourceLocation getAnimationResource(SprinklerBlockEntity entity) { return ANIMATION; }

    @Override
    public void setCustomAnimations(SprinklerBlockEntity entity, long instanceId,
            AnimationState<SprinklerBlockEntity> animationState) {
        super.setCustomAnimations(entity, instanceId, animationState);
        GeoBone rotor = getAnimationProcessor().getBone("rotor");
        if (rotor == null || entity.getLevel() == null) return;
        if (!entity.getBlockState().getValue(SprinklerBlock.ENABLED)) {
            rotor.setRotY(0.0F);
            return;
        }
        float degrees = (entity.getLevel().getGameTime() + animationState.getPartialTick()) * 12.0F;
        rotor.setRotY(degrees * Mth.DEG_TO_RAD);
    }
}
