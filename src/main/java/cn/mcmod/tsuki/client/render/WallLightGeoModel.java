package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.block.machine.WallLightBlock;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WallLightGeoModel extends GeoModel<WallLightBlockEntity> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/wall_light.geo.json");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "animations/block/wall_light.animation.json");

    @Override
    public ResourceLocation getModelResource(WallLightBlockEntity entity) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(WallLightBlockEntity entity) {
        boolean wood = ((WallLightBlock) entity.getBlockState().getBlock()).isWooden();
        return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                "textures/block/wall_light_" + (wood ? "wood" : "stone") + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(WallLightBlockEntity entity) {
        return ANIMATION;
    }
}
