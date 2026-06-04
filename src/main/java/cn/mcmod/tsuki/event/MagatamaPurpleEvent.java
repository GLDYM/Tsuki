package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.item.magatama.MagatamaPurpleHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaPurpleEvent {
    private static final int HATE_CLEAR_INTERVAL_TICKS = 20;

    private MagatamaPurpleEvent() {
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (!(event.getTarget() instanceof LivingEntity)) {
            return;
        }
        if (!MagatamaPurpleHelper.hasActivePurpleMagatama(player)) {
            return;
        }
        MagatamaPurpleHelper.triggerAttackCooldown(player);
        syncDisplayedCooldown(player);
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        syncDisplayedCooldown(player);
        if (!MagatamaPurpleHelper.hasActivePurpleMagatama(player)
                || MagatamaPurpleHelper.isInAttackCooldown(player)
                || player.tickCount % HATE_CLEAR_INTERVAL_TICKS != 0) {
            return;
        }
        MagatamaPurpleHelper.clearNearbyMobTargets(player);
    }

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        if (!(event.getOriginalAboutToBeSetTarget() instanceof ServerPlayer player)) {
            return;
        }
        if (!(event.getEntity() instanceof Mob)
                || !MagatamaPurpleHelper.hasActivePurpleMagatama(player)
                || MagatamaPurpleHelper.isInAttackCooldown(player)) {
            return;
        }
        event.setNewAboutToBeSetTarget(null);
    }

    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (!MagatamaPurpleHelper.hasActivePurpleMagatama(player)
                || !MagatamaPurpleHelper.isInAttackCooldown(player)) {
            return;
        }
        event.setAmount(event.getAmount() * 2.0F);
    }

    private static void syncDisplayedCooldown(ServerPlayer player) {
        int remainingTicks = MagatamaPurpleHelper.getRemainingAttackCooldownTicks(player);
        if (remainingTicks > 0) {
            player.getCooldowns().addCooldown(ArmorToolRegistry.MAGATAMA_PURPLE.get(), remainingTicks);
        }
    }
}
