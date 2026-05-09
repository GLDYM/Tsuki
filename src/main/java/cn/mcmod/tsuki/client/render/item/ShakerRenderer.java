package cn.mcmod.tsuki.client.render.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.drink.ShakerItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShakerRenderer extends GeoItemRenderer<ShakerItem> {
    public ShakerRenderer() {
        super(new ShakerModel());
    }

    private static class ShakerModel extends DefaultedItemGeoModel<ShakerItem> {
        public ShakerModel() {
            super(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "shaker"));
        }
    }
}
