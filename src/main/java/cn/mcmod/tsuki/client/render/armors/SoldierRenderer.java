package cn.mcmod.tsuki.client.render.armors;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.armors.SoldierItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import javax.annotation.Nullable;

public class SoldierRenderer extends GeoArmorRenderer<SoldierItem> {
    protected GeoBone legs = null;

    public SoldierRenderer(String base) {
        super(new SoldierModel<>(
            base,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "armor/soldier")
        ));
    }

    @Nullable
    public GeoBone getLegsBone(GeoModel<SoldierItem> model) {
        return model.getBone("armorLegs").orElse(null);
    }

    @Override
    protected void grabRelevantBones(BakedGeoModel bakedModel) {
        if (this.lastModel == bakedModel)
            return;

        GeoModel<SoldierItem> model = getGeoModel();
        this.lastModel = bakedModel;
        this.head = getHeadBone(model);
        this.body = getBodyBone(model);
        this.rightArm = getRightArmBone(model);
        this.leftArm = getLeftArmBone(model);
        this.legs = getLegsBone(model);
        this.rightLeg = getRightLegBone(model);
        this.leftLeg = getLeftLegBone(model);
        this.rightBoot = getRightBootBone(model);
        this.leftBoot = getLeftBootBone(model);
    }

    @Override
    protected void applyBoneVisibilityBySlot(EquipmentSlot currentSlot) {
        setAllBonesVisible(false);
        HumanoidModel<?> model = this;

        switch (currentSlot) {
            case HEAD -> setBoneVisible(this.head, model.head.visible);
            case CHEST -> {
                setBoneVisible(this.body, model.body.visible);
                setBoneVisible(this.rightArm, model.rightArm.visible);
                setBoneVisible(this.leftArm, model.leftArm.visible);
            }
            case LEGS -> {
                setBoneVisible(this.legs, true);
                setBoneVisible(this.rightLeg, model.rightLeg.visible);
                setBoneVisible(this.leftLeg, model.leftLeg.visible);
            }
            case FEET -> {
                setBoneVisible(this.rightBoot, model.rightLeg.visible);
                setBoneVisible(this.leftBoot, model.leftLeg.visible);
            }
            default -> {}
        }
    }

    public static class SoldierModel<T extends GeoAnimatable> extends DefaultedItemGeoModel<T> {
        private final String base;

        public SoldierModel(String base, ResourceLocation modelLocation) {
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

    @Override
    protected void setAllBonesVisible(boolean visible) {
        setBoneVisible(this.head, visible);
        setBoneVisible(this.body, visible);
        setBoneVisible(this.rightArm, visible);
        setBoneVisible(this.leftArm, visible);
        setBoneVisible(this.legs, visible);
        setBoneVisible(this.rightLeg, visible);
        setBoneVisible(this.leftLeg, visible);
        setBoneVisible(this.rightBoot, visible);
        setBoneVisible(this.leftBoot, visible);
    }
}
