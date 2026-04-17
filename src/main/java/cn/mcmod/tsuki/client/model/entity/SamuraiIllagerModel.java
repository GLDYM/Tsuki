package cn.mcmod.tsuki.client.model.entity;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.entity.SamuraiIllagerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.animation.AnimationState;

public class SamuraiIllagerModel extends DefaultedEntityGeoModel<SamuraiIllagerEntity> {
    public SamuraiIllagerModel() {
        super(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai_illager"));
    }

    @Override
    public void setCustomAnimations(SamuraiIllagerEntity animatable, long instanceId,
                                    AnimationState<SamuraiIllagerEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        GeoBone head = this.getAnimationProcessor().getBone("head");
        if (head != null) {
            EntityModelData entityModelData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
