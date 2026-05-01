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

public class CookingParticle extends TextureSheetParticle {
    protected CookingParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd,
            SpriteSet sprites) {
        super(level, x, y, z);
        this.scale(2.0F);
        this.setSize(0.25F, 0.25F);
        this.lifetime = this.random.nextInt(50) + 80;
        this.gravity = 3.0E-6F;
        this.xd = xd;
        this.yd = yd + (this.random.nextFloat() / 500.0F);
        this.zd = zd;
        this.setAlpha(0.6F);
        this.pickSprite(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && this.alpha > 0.0F) {
            this.xd += this.random.nextFloat() / 5000.0F * (this.random.nextBoolean() ? 1.0F : -1.0F);
            this.zd += this.random.nextFloat() / 5000.0F * (this.random.nextBoolean() ? 1.0F : -1.0F);
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.02F;
            }
        } else {
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                double xd, double yd, double zd) {
            return new CookingParticle(level, x, y + 0.3D, z, xd, yd, zd, this.sprites);
        }
    }
}
