package cn.mcmod.tsuki.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * Increases experience drops from killed mobs.
 * The actual exp boost logic is handled by {@link TsukiEffectEvents}.
 */
public class ExpUpEffect extends MobEffect {
    public ExpUpEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00); // green
    }
}
