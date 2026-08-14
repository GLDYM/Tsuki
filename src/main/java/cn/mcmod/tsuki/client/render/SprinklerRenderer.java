package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.block.entity.SprinklerBlockEntity;
import cn.mcmod.tsuki.block.machine.SprinklerBlock;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SprinklerRenderer extends GeoBlockRenderer<SprinklerBlockEntity> {
    public SprinklerRenderer(BlockEntityRendererProvider.Context context) {
        super(new SprinklerGeoModel());
    }

    @Override
    public void render(SprinklerBlockEntity entity, float partialTick, PoseStack poseStack,
            MultiBufferSource buffers, int light, int overlay) {
        super.render(entity, partialTick, poseStack, buffers, light, overlay);
        if (!entity.getBlockState().getValue(SprinklerBlock.ENABLED) || entity.getLevel() == null
                || !entity.markParticlesEmitted(entity.getLevel().getGameTime())) return;

        // Match the rotor's local +X/-X nozzle directions and its Y rotation.
        double angle = Math.toRadians((entity.getLevel().getGameTime() + partialTick) * 12.0D);
        double velocityX = Math.cos(angle) * 0.18D;
        double velocityZ = -Math.sin(angle) * 0.18D;
        spawnWaterJet(entity, velocityX, velocityZ);
        spawnWaterJet(entity, -velocityX, -velocityZ);
    }

    private static void spawnWaterJet(SprinklerBlockEntity entity, double velocityX, double velocityZ) {
        entity.getLevel().addParticle(ParticleRegistry.SPRINKLER_WATER.get(),
                entity.getBlockPos().getX() + 0.5D, entity.getBlockPos().getY() + 0.3D,
                entity.getBlockPos().getZ() + 0.5D, velocityX, 0.0D, velocityZ);
    }
}
