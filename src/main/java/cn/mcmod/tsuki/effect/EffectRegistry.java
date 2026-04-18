package cn.mcmod.tsuki.effect;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Tsuki.MODID);

    public static final Holder<MobEffect> EXP_UP =
            MOB_EFFECTS.register("exp_up", ExpUpEffect::new);
    public static final Holder<MobEffect> CANNON =
            MOB_EFFECTS.register("cannon", CannonEffect::new);
    public static final Holder<MobEffect> FIRE_BLADE =
            MOB_EFFECTS.register("fire_blade", FireBladeEffect::new);
    public static final Holder<MobEffect> GOLDEN_HEART =
            MOB_EFFECTS.register("golden_heart", GoldenHeartEffect::new);
    public static final Holder<MobEffect> POISONING =
            MOB_EFFECTS.register("poisoning", PoisoningEffect::new);
    public static final Holder<MobEffect> SCORPION =
            MOB_EFFECTS.register("scorpion", ScorpionEffect::new);
}
