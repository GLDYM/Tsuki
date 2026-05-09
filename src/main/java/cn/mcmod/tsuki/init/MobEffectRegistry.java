package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.effect.CannonEffect;
import cn.mcmod.tsuki.effect.ExpUpEffect;
import cn.mcmod.tsuki.effect.FireBladeEffect;
import cn.mcmod.tsuki.effect.GoldenHeartEffect;
import cn.mcmod.tsuki.effect.PoisoningEffect;
import cn.mcmod.tsuki.effect.SeiranEffect;
import cn.mcmod.tsuki.effect.ScorpionEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT,
            Tsuki.MODID);

    public static final Holder<MobEffect> EXP_UP = MOB_EFFECTS.register("exp_up", ExpUpEffect::new);
    public static final Holder<MobEffect> CANNON = MOB_EFFECTS.register("cannon", CannonEffect::new);
    public static final Holder<MobEffect> FIRE_BLADE = MOB_EFFECTS.register("fire_blade", FireBladeEffect::new);
    public static final Holder<MobEffect> GOLDEN_HEART = MOB_EFFECTS.register("golden_heart", GoldenHeartEffect::new);
    public static final Holder<MobEffect> POISONING = MOB_EFFECTS.register("poisoning", PoisoningEffect::new);
    public static final Holder<MobEffect> SEIRAN = MOB_EFFECTS.register("seiran", SeiranEffect::new);
    public static final Holder<MobEffect> SCORPION = MOB_EFFECTS.register("scorpion", ScorpionEffect::new);
}
