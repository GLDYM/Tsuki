package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.entity.SeatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SeatEntityRenderer extends EntityRenderer<SeatEntity> {
    public SeatEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SeatEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
            MultiBufferSource bufferSource, int packedLight) {
        // Intentionally empty: seat entity is only a ride anchor.
    }

    @Override
    public ResourceLocation getTextureLocation(SeatEntity entity) {
        return ResourceLocation.withDefaultNamespace("textures/misc/white.png");
    }
}
