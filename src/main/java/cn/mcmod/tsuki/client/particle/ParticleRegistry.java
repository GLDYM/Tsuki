package cn.mcmod.tsuki.client.particle;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
                        .create(Registries.PARTICLE_TYPE, Tsuki.MODID);
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SAKURA_LEAF = PARTICLE_TYPES.register("sakura",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RED_MAPLE_LEAF = PARTICLE_TYPES.register("red_maple",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_MAPLE_LEAF = PARTICLE_TYPES.register("yellow_maple",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GREEN_MAPLE_LEAF = PARTICLE_TYPES.register("green_maple",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORANGE_MAPLE_LEAF = PARTICLE_TYPES.register("orange_maple",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SYRUP_DROP = PARTICLE_TYPES.register("syrup_drop",
            () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> KITUNEBI_FIRE = PARTICLE_TYPES.register("kitunebi_fire",
            () -> new SimpleParticleType(false));
}


