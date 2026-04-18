package cn.mcmod.tsuki.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * Sets targets on fire when the player hits them in melee combat.
 * The on-hit logic is handled by {@link TsukiEffectEvents}.
 */
public class FireBladeEffect extends MobEffect {
    public FireBladeEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFF4500); // red-orange
    }
}
