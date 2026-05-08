package cn.mcmod.tsuki.effect;

import cn.mcmod.tsuki.event.EffectEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * Applies strong poison to targets when the player hits them in melee combat.
 * The on-hit logic is handled by {@link EffectEvent}.
 */
public class ScorpionEffect extends MobEffect {
    public ScorpionEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x5A3A1A); // dark brown
    }
}
