package cn.mcmod.tsuki.client.render.entity;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.entity.SamuraiIllagerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class SamuraiIllagerRenderer extends GeoEntityRenderer<SamuraiIllagerEntity> {
    public SamuraiIllagerRenderer(EntityRendererProvider.Context context) {
        super(context, new SamuraiIllagerModel());
        this.shadowRadius = 0.5F;
        this.addRenderLayer(new SamuraiHeldItemLayer(this));
    }

    private static class SamuraiHeldItemLayer extends BlockAndItemGeoLayer<SamuraiIllagerEntity> {
        public SamuraiHeldItemLayer(GeoEntityRenderer<SamuraiIllagerEntity> renderer) {
            super(renderer);
        }

        @Override
        protected ItemStack getStackForBone(GeoBone bone, SamuraiIllagerEntity animatable) {
            String boneName = bone.getName();

            if ("right_arm".equals(boneName) && animatable.isAggressive()) {
                return animatable.getMainHandItem();
            }

            if ("left_arm".equals(boneName) && animatable.isAggressive()) {
                return animatable.getOffhandItem();
            }

            return ItemStack.EMPTY;
        }

        @Override
        protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack,
                SamuraiIllagerEntity animatable) {
            return "left_arm".equals(bone.getName())
                    ? ItemDisplayContext.THIRD_PERSON_LEFT_HAND
                    : ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
        }

        @Override
        protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack,
                SamuraiIllagerEntity animatable,
                MultiBufferSource bufferSource,
                float partialTick, int packedLight, int packedOverlay) {
            poseStack.pushPose();
            // Magic numbers.
            poseStack.translate(0.27D, -0.1D, 0.5D);
            poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            poseStack.mulPose(Axis.YP.rotationDegrees(-40.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees(-22.5F));
            poseStack.scale(1.0F, 1.0F, 1.0F);
            super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight,
                    packedOverlay);
            poseStack.popPose();
        }
    }

    public static class SamuraiIllagerModel extends DefaultedEntityGeoModel<SamuraiIllagerEntity> {
        public SamuraiIllagerModel() {
            super(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "samurai_illager"));
        }

        // This Animation is a little weird
        // @Override
        // public void setCustomAnimations(SamuraiIllagerEntity animatable, long
        // instanceId,
        // AnimationState<SamuraiIllagerEntity> animationState) {
        // super.setCustomAnimations(animatable, instanceId, animationState);
        // GeoBone head = this.getAnimationProcessor().getBone("head");
        // if (head != null) {
        // EntityModelData entityModelData =
        // animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        // head.setRotX(entityModelData.headPitch() * Mth.DEG_TO_RAD);
        // head.setRotY(entityModelData.netHeadYaw() * Mth.DEG_TO_RAD);
        // }
        // }
    }
}
