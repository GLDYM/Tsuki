package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.magatama.MagatamaOrangeHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaOrangeEvent {
    private static final float HEAL_PER_TICK = 1.0F;
    private static final float SATURATION_DRAIN_RATE = 0.25F;
    private static final float SATURATION_DRAIN_MIN = 2.0F;
    private static final int HUNGER_DRAIN = 1;
    private static final int HUNGER_THRESHOLD = 10;
    private static final int DEBUG_LOG_INTERVAL_TICKS = 20;
    private static final ResourceLocation MAX_ABSORPTION_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(
            Tsuki.MODID, "magatama_orange_max_absorption");

    private MagatamaOrangeEvent() {
    }

    @SubscribeEvent
    public static void onFoodRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        if (!MagatamaOrangeHelper.hasActiveOrangeMagatama(event.getEntity())) {
            return;
        }
        FoodProperties food = event.getItemStack().getItem().getFoodProperties(event.getItemStack(), event.getEntity());
        if (food == null || event.getEntity().canEat(false)) {
            return;
        }

        event.getEntity().startUsingItem(event.getHand());
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.CONSUME);
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (!MagatamaOrangeHelper.hasActiveOrangeMagatama(player)) {
            removeMaxAbsorptionModifier(player);
            return;
        }
        updateMaxAbsorptionModifier(player);

        float saturation = player.getFoodData().getSaturationLevel();
        int hunger = player.getFoodData().getFoodLevel();
        float healthBefore = player.getHealth();
        float absorptionBefore = player.getAbsorptionAmount();
        float saturationBefore = saturation;
        int hungerBefore = hunger;
        boolean usedSaturation = false;
        boolean usedHunger = false;
        float saturationDrain = 0.0F;
        int hungerDrain = 0;
        if (saturation > 0.0F) {
            if (tryRecover(player, HEAL_PER_TICK)) {
                float drain = Math.max(saturation * SATURATION_DRAIN_RATE, SATURATION_DRAIN_MIN);
                player.getFoodData().setSaturation(Math.max(0.0F, saturation - drain));
                usedSaturation = true;
                saturationDrain = drain;
            }
            logDebug(player, healthBefore, absorptionBefore, saturationBefore, hungerBefore, usedSaturation,
                    usedHunger, saturationDrain, hungerDrain);
            return;
        }
        if (hunger > HUNGER_THRESHOLD) {
            if (tryRecover(player, HEAL_PER_TICK)) {
                player.getFoodData().setFoodLevel(Math.max(0, hunger - HUNGER_DRAIN));
                usedHunger = true;
                hungerDrain = HUNGER_DRAIN;
            }
        }
        logDebug(player, healthBefore, absorptionBefore, saturationBefore, hungerBefore, usedSaturation, usedHunger,
                saturationDrain, hungerDrain);
    }

    private static boolean tryRecover(ServerPlayer player, float healAmount) {
        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();
        float absorption = player.getAbsorptionAmount();

        float healthGain = Math.min(healAmount, Math.max(0.0F, maxHealth - health));
        float overflow = healAmount - healthGain;
        float absorptionGain = Math.min(Math.max(0.0F, maxHealth - absorption), Math.max(0.0F, overflow));
        float totalGain = healthGain + absorptionGain;
        if (totalGain <= 1.0E-4F) {
            return false;
        }

        if (healthGain > 0.0F) {
            player.heal(healthGain);
        }
        if (absorptionGain > 0.0F) {
            player.setAbsorptionAmount(absorption + absorptionGain);
        }
        return true;
    }

    private static void updateMaxAbsorptionModifier(ServerPlayer player) {
        AttributeInstance maxAbsorption = player.getAttribute(Attributes.MAX_ABSORPTION);
        if (maxAbsorption == null) {
            return;
        }
        double amount = Math.max(0.0D, player.getMaxHealth());
        maxAbsorption.addOrUpdateTransientModifier(new AttributeModifier(
                MAX_ABSORPTION_MODIFIER_ID, amount, AttributeModifier.Operation.ADD_VALUE));
    }

    private static void removeMaxAbsorptionModifier(ServerPlayer player) {
        AttributeInstance maxAbsorption = player.getAttribute(Attributes.MAX_ABSORPTION);
        if (maxAbsorption == null) {
            return;
        }
        maxAbsorption.removeModifier(MAX_ABSORPTION_MODIFIER_ID);
        if (player.getAbsorptionAmount() > player.getMaxAbsorption()) {
            player.setAbsorptionAmount(player.getMaxAbsorption());
        }
    }

    private static void logDebug(ServerPlayer player, float healthBefore, float absorptionBefore, float saturationBefore,
            int hungerBefore, boolean usedSaturation, boolean usedHunger, float saturationDrain, int hungerDrain) {
        if (!TsukiCommonConfig.DEBUG_MODE.get()) {
            return;
        }
        if (player.tickCount % DEBUG_LOG_INTERVAL_TICKS != 0) {
            return;
        }
        Tsuki.getLogger().info(
                "[MagatamaOrange] tick={} player={} before(health={},absorption={},saturation={},hunger={}) after(health={},absorption={},saturation={},hunger={}) usedSaturation={} saturationDrain={} usedHunger={} hungerDrain={}",
                player.tickCount,
                player.getGameProfile().getName(),
                healthBefore,
                absorptionBefore,
                saturationBefore,
                hungerBefore,
                player.getHealth(),
                player.getAbsorptionAmount(),
                player.getFoodData().getSaturationLevel(),
                player.getFoodData().getFoodLevel(),
                usedSaturation,
                saturationDrain,
                usedHunger,
                hungerDrain);
    }
}
