package cn.mcmod.tsuki.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SyrupDropParticle extends TextureSheetParticle {

    protected SyrupDropParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd) {
        super(level, x, y, z, xd, yd, zd);
        this.xd *= 0.1D;
        this.yd *= 0.1D;
        this.zd *= 0.1D;

        this.xd += xd;
        this.yd += yd;
        this.zd += zd;

        this.quadSize = 0.08F + level.random.nextFloat() * 0.03F;
        this.lifetime = 32 + level.random.nextInt(8);
        this.gravity = 0.12F;
        this.hasPhysics = true;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime || this.onGround) {
            this.remove();
            return;
        }

        this.move(this.xd, this.yd, this.zd);
        this.yd -= 0.02D;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                double xd, double yd, double zd) {
            SyrupDropParticle particle = new SyrupDropParticle(level, x, y, z, xd, yd, zd);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
