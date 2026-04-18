package cn.mcmod.tsuki.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * Periodically removes harmful effects from the entity.
 * Ticks every 2 ticks and clears all non-beneficial effects.
 */
public class GoldenHeartEffect extends MobEffect {
    public GoldenHeartEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFD700); // gold
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        entity.getActiveEffects().stream()
                .filter(e -> !e.getEffect().value().isBeneficial())
                .map(MobEffectInstance::getEffect)
                .toList()
                .forEach(entity::removeEffect);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return (duration & 1) == 0;
    }
}
