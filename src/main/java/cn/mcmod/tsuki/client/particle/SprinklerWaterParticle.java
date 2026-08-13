package cn.mcmod.tsuki.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

public class SprinklerWaterParticle extends TextureSheetParticle {
    private final double originY;

    private SprinklerWaterParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
        super(level, x, y, z, xd, yd, zd);
        this.originY = y + 1.0D;
        this.xd = xd;
        this.yd = 0.0D;
        this.zd = zd;
        this.quadSize = 0.08F + level.random.nextFloat() * 0.04F;
        this.lifetime = 30 + level.random.nextInt(15);
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        double progress = 10.0D * this.age / this.lifetime - 3.0D;
        this.x += this.xd;
        this.z += this.zd;
        this.y = this.originY - progress * progress * 0.1D;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                double xd, double yd, double zd) {
            SprinklerWaterParticle particle = new SprinklerWaterParticle(level, x, y, z, xd, yd, zd);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}
