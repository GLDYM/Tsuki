package cn.mcmod.tsuki.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionContents;

public final class DrinkEffectHelper {
    private DrinkEffectHelper() {
    }

    public static void applyEffects(LivingEntity entity, MobEffectInstance[] effects) {
        for (MobEffectInstance effect : effects) {
            applyEffect(entity, effect);
        }
    }

    public static void appendEffectTooltip(List<Component> tooltip, MobEffectInstance[] effects,
            @Nullable Component extraTooltip) {
        if (extraTooltip != null) {
            tooltip.add(extraTooltip);
        }
        PotionContents.addPotionTooltip(List.of(effects), tooltip::add, 1.0F, 1.0F);
    }

    private static void applyEffect(LivingEntity entity, MobEffectInstance incoming) {
        if (incoming.getEffect().value().isInstantenous()) {
            entity.addEffect(new MobEffectInstance(incoming));
            return;
        }

        MobEffectInstance current = entity.getEffect(incoming.getEffect());
        if (current == null) {
            entity.addEffect(new MobEffectInstance(incoming));
            return;
        }

        int duration = current.isInfiniteDuration() || incoming.isInfiniteDuration()
                ? MobEffectInstance.INFINITE_DURATION
                : current.getDuration() + incoming.getDuration();
        int amplifier = Math.max(current.getAmplifier(), incoming.getAmplifier());
        boolean ambient = current.isAmbient() && incoming.isAmbient();
        boolean visible = current.isVisible() || incoming.isVisible();
        boolean showIcon = current.showIcon() || incoming.showIcon();

        entity.addEffect(new MobEffectInstance(
                incoming.getEffect(),
                duration,
                amplifier,
                ambient,
                visible,
                showIcon));
    }
}
