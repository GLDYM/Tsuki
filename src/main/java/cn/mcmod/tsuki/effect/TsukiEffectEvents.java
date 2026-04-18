package cn.mcmod.tsuki.effect;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ArrowLooseEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public class TsukiEffectEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) {
            return;
        }

        if (player.hasEffect(EffectRegistry.FIRE_BLADE)) {
            event.getEntity().igniteForSeconds(600);
        }

        if (player.hasEffect(EffectRegistry.POISONING)) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 300, 1));
        }

        if (player.hasEffect(EffectRegistry.SCORPION)) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 900, 3));
        }
    }

    @SubscribeEvent
    public static void onExpDrop(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (player != null && player.hasEffect(EffectRegistry.EXP_UP)) {
            int amplifier = player.getEffect(EffectRegistry.EXP_UP).getAmplifier();
            int originalExp = event.getOriginalExperience();
            int bonus = (originalExp / 2) * amplifier;
            event.setDroppedExperience(originalExp + bonus);
        }
    }

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        Player player = event.getEntity();
        if (player.hasEffect(EffectRegistry.CANNON)) {
            int amplifier = player.getEffect(EffectRegistry.CANNON).getAmplifier();
            event.setCharge(event.getCharge() + amplifier * 25);
        }
    }
}
