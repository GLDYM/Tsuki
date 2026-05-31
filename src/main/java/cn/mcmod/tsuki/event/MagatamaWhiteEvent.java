package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaWhiteEvent {
    private static final ResourceLocation CREATIVE_FLIGHT_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "magatama_white_creative_flight");
    private static final ResourceLocation MAX_HEALTH_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "magatama_white_max_health");
    private static final AttributeModifier CREATIVE_FLIGHT_MODIFIER = new AttributeModifier(CREATIVE_FLIGHT_MODIFIER_ID,
            1.0D, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier MAX_HEALTH_MODIFIER = new AttributeModifier(MAX_HEALTH_MODIFIER_ID, -10.0D,
            AttributeModifier.Operation.ADD_VALUE);
    private static final int HOTBAR_SIZE = 9;
    private static final int CHECK_INTERVAL_TICKS = 20;
    private static final int SLOW_FALLING_DURATION_TICKS = 200;

    private MagatamaWhiteEvent() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (player.tickCount % CHECK_INTERVAL_TICKS != 0) {
            return;
        }

        boolean active = isWhiteMagatamaActive(player);
        updateCreativeFlight(player, active);
        updateMaxHealth(player, active);
    }

    private static boolean isWhiteMagatamaActive(Player player) {
        for (int slot = 0; slot < HOTBAR_SIZE; slot++) {
            ItemStack stack = player.getInventory().getItem(slot);
            if (stack.is(ArmorToolRegistry.MAGATAMA_WHITE.get())) {
                return true;
            }
        }
        return CuriosCompat.isEquipped(player, ArmorToolRegistry.MAGATAMA_WHITE.get());
    }

    private static void updateCreativeFlight(Player player, boolean active) {
        AttributeInstance creativeFlight = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        if (creativeFlight == null) {
            return;
        }

        if (active) {
            creativeFlight.addOrUpdateTransientModifier(CREATIVE_FLIGHT_MODIFIER);
            if (player.hasEffect(MobEffects.SLOW_FALLING)) {
                player.removeEffect(MobEffects.SLOW_FALLING);
            }
            return;
        }

        creativeFlight.removeModifier(CREATIVE_FLIGHT_MODIFIER_ID);
        if (!player.isCreative() && !player.isSpectator() && player.getAbilities().flying && !player.mayFly()) {
            stopFlying(player);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, SLOW_FALLING_DURATION_TICKS, 0, false,
                    false));
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

    private static boolean isMiningSpeedCompensationActive(Player player) {
        if (!TsukiCommonConfig.MAGATAMA_WHITE_ENABLE_MINING_SPEED_COMPENSATION.get()) {
            return false;
        }
        return isWhiteMagatamaActive(player) && player.mayFly() && !player.onGround();
    }

    private static float getMiningSpeedCompensationMultiplier() {
        return (float) Math.max(1.0D, TsukiCommonConfig.MAGATAMA_WHITE_MINING_SPEED_COMPENSATION_MULTIPLIER.get());
    }

    private static void updateMaxHealth(Player player, boolean active) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }

        if (active) {
            maxHealth.addOrUpdateTransientModifier(MAX_HEALTH_MODIFIER);
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
            return;
        }

        maxHealth.removeModifier(MAX_HEALTH_MODIFIER_ID);
        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }
}
