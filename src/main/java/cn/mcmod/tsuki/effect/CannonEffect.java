package cn.mcmod.tsuki.effect;

import cn.mcmod.tsuki.event.EffectEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * Increases bow charge power / projectile damage.
 * The actual boost logic is handled by {@link EffectEvent}.
 */
public class CannonEffect extends MobEffect {
    public CannonEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF6600); // orange
    }
}
