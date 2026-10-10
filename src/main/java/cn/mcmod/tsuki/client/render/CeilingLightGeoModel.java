package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.CeilingLightBlockEntity;
import cn.mcmod.tsuki.block.machine.CeilingLightBlock;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CeilingLightGeoModel extends GeoModel<CeilingLightBlockEntity> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/ceiling_light.geo.json");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "animations/block/ceiling_light.animation.json");

    @Override
    public ResourceLocation getModelResource(CeilingLightBlockEntity entity) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(CeilingLightBlockEntity entity) {
        boolean wood = ((CeilingLightBlock) entity.getBlockState().getBlock()).isWooden();
        return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/block/ceiling_light_" + (wood ? "wood" : "stone") + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(CeilingLightBlockEntity entity) {
        return ANIMATION;
    }
}
