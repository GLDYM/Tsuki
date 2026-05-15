package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.SunflowerCropBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SunflowerCropRenderer extends GeoBlockRenderer<SunflowerCropBlockEntity> {
    public SunflowerCropRenderer(BlockEntityRendererProvider.Context context) {
        super(new SunflowerCropGeoModel());
    }
}
