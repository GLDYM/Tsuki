package cn.mcmod.tsuki.effect;

import cn.mcmod.tsuki.event.EffectEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * Applies Poison I to targets when the player hits them in melee combat.
 * The on-hit logic is handled by {@link EffectEvent}.
 */
public class PoisoningEffect extends MobEffect {
    public PoisoningEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x4E9331); // dark green
    }
}
