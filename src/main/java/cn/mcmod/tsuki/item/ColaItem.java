package cn.mcmod.tsuki.item;

import cn.mcmod.mmlib.item.ItemFoodBase;
import cn.mcmod.mmlib.item.info.FoodInfo;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;

import java.util.Optional;

public class ColaItem extends ItemFoodBase {
    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
        false, false, Optional.of(1.22F), Optional.empty()
    );

    public ColaItem(Properties properties, FoodInfo info) {
        super(properties, info);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide && entity instanceof Player player) {
            level.explode(
                null,
                null,
                EXPLOSION_DAMAGE_CALCULATOR,
                player.getX(),
                player.getY(),
                player.getZ(),
                1.2F,
                false,
                Level.ExplosionInteraction.NONE,
                ParticleTypes.GUST_EMITTER_SMALL,
                ParticleTypes.GUST_EMITTER_LARGE,
                SoundEvents.WIND_CHARGE_BURST
            );
        }
        return result;
    }
}
