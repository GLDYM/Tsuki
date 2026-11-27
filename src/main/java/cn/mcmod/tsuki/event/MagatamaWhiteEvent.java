package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaWhiteEvent {
    private static final ResourceLocation CREATIVE_FLIGHT_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(
            Tsuki.MODID,
            "magatama_white_creative_flight");
    private static final ResourceLocation MAX_HEALTH_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "magatama_white_max_health");
    private static final AttributeModifier CREATIVE_FLIGHT_MODIFIER = new AttributeModifier(CREATIVE_FLIGHT_MODIFIER_ID,
            1.0D, AttributeModifier.Operation.ADD_VALUE);
    private static final int CHECK_INTERVAL_TICKS = 5;

    private MagatamaWhiteEvent() {
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            syncWhiteMagatamaState(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            syncWhiteMagatamaState(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            syncWhiteMagatamaState(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (MagatamaWhiteHelper.hasActiveWhiteMagatama(player) && player.fallDistance > 0.0F) {
            player.fallDistance = 0.0F;
        }
        if (player.tickCount % CHECK_INTERVAL_TICKS != 0) {
            return;
        }

        syncWhiteMagatamaState(player);
    }

    private static void syncWhiteMagatamaState(ServerPlayer player) {
        updateCreativeFlight(player, MagatamaWhiteHelper.isCreativeModeActive(player));
        updateMaxHealth(player, MagatamaWhiteHelper.hasActiveWhiteMagatama(player));
    }

    private static void updateCreativeFlight(Player player, boolean active) {
        AttributeInstance creativeFlight = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        if (creativeFlight == null) {
            return;
        }

        if (active) {
            if (creativeFlight.getModifier(CREATIVE_FLIGHT_MODIFIER_ID) == null) {
                creativeFlight.addOrUpdateTransientModifier(CREATIVE_FLIGHT_MODIFIER);
            }
            return;
        }

        if (creativeFlight.getModifier(CREATIVE_FLIGHT_MODIFIER_ID) != null) {
            creativeFlight.removeModifier(CREATIVE_FLIGHT_MODIFIER_ID);
        }
        if (!player.isCreative() && !player.isSpectator() && player.getAbilities().flying && !player.mayFly()) {
            stopFlying(player);
        }
    }

    private static void stopFlying(Player player) {
        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player == null || !isMiningSpeedCompensationActive(player)) {
            return;
        }

        float currentSpeed = event.getNewSpeed();
        if (currentSpeed <= 0.0F) {
            return;
        }

        event.setNewSpeed(currentSpeed * getMiningSpeedCompensationMultiplier());
    }

    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player) || !MagatamaWhiteHelper.hasActiveWhiteMagatama(player)) {
            return;
        }

        if (event.getSource().is(DamageTypes.FALL) || event.getSource().is(DamageTypes.FLY_INTO_WALL)) {
            player.fallDistance = 0.0F;
            event.setCanceled(true);
        }
    }

    private static boolean isMiningSpeedCompensationActive(Player player) {
        if (!TsukiCommonConfig.MAGATAMA_WHITE_ENABLE_MINING_SPEED_COMPENSATION.get()) {
            return false;
        }
        return MagatamaWhiteHelper.isCreativeModeActive(player) && player.mayFly() && !player.onGround();
    }

    private static float getMiningSpeedCompensationMultiplier() {
        return (float) Math.max(1.0D, TsukiCommonConfig.MAGATAMA_WHITE_MINING_SPEED_COMPENSATION_MULTIPLIER.get());
    }

    private static void updateMaxHealth(Player player, boolean active) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }

        if (active && TsukiCommonConfig.MAGATAMA_WHITE_ENABLE_PENALTY.get()) {
            AttributeModifier modifier = createMaxHealthPenaltyModifier(maxHealth);
            AttributeModifier existing = maxHealth.getModifier(MAX_HEALTH_MODIFIER_ID);
            if (existing == null || Double.compare(existing.amount(), modifier.amount()) != 0) {
                maxHealth.addOrUpdateTransientModifier(modifier);
            }
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
            return;
        }

        if (maxHealth.getModifier(MAX_HEALTH_MODIFIER_ID) != null) {
            maxHealth.removeModifier(MAX_HEALTH_MODIFIER_ID);
        }
        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

    private static AttributeModifier createMaxHealthPenaltyModifier(AttributeInstance maxHealth) {
        double targetHealth = Math.max(1.0D, TsukiCommonConfig.MAGATAMA_WHITE_PENALTY_HEALTH.get());
        double penaltyAmount = targetHealth - maxHealth.getBaseValue();
        return new AttributeModifier(MAX_HEALTH_MODIFIER_ID, penaltyAmount, AttributeModifier.Operation.ADD_VALUE);
    }
}
