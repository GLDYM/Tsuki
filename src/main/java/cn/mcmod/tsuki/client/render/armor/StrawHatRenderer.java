package cn.mcmod.tsuki.client.render.armor;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.armor.StrawHatItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class StrawHatRenderer extends GeoArmorRenderer<StrawHatItem> {
    public StrawHatRenderer() {
        super(new DefaultedItemGeoModel<>(
                ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "armor/straw_hat")));
    }
}
