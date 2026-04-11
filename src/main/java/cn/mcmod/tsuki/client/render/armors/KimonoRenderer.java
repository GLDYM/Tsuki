package cn.mcmod.tsuki.client.render.armors;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.armors.KimonoItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class KimonoRenderer extends GeoArmorRenderer<KimonoItem> {
    public KimonoRenderer(String base) {
        super(new KimonoModel<>(
            base,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "armor/kimono")
        ));
    }

    @Override
	protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        // no-op
	}

    public static class KimonoModel<T extends GeoAnimatable> extends DefaultedItemGeoModel<T> {
        private final String base;
        
        public KimonoModel(String base, ResourceLocation modelLocation) {
            super(modelLocation);
            this.base = base;
        }

        @Override
        public ResourceLocation getTextureResource(T animatable) {
            return ResourceLocation.fromNamespaceAndPath(
                Tsuki.MODID,
                "textures/item/armor/" + base + ".png"
            );
        }
    }
}