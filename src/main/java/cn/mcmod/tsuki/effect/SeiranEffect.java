package cn.mcmod.tsuki.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SeiranEffect extends MobEffect {
    public SeiranEffect() {
        super(MobEffectCategory.NEUTRAL, 0x6B4FA3);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) {
            return true;
        }
        if (entity.getHealth() <= 0.5F) {
            return true;
        }
        float damage = Math.max(entity.getMaxHealth() * 0.1F, 1.5F);
        if (entity.getHealth() - damage <= 0.5F) {
            entity.setHealth(0.5F);
        } else {
            entity.hurt(entity.damageSources().outOfBorder(), damage);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
