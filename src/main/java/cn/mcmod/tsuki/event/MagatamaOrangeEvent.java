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
    private static final float SATURATION_DRAIN_RATE = 0.25F;
    private static final float SATURATION_DRAIN_MIN = 2.0F;
    private static final int HUNGER_DRAIN = 1;
    private static final int HUNGER_THRESHOLD = 10;
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
        if (saturation > 0.0F) {
            float drain = getSaturationDrain(saturation);
            float healAmount = drain * getSaturationToHealthRatio();
            if (tryRecover(player, healAmount)) {
                drainSaturation(player, saturation, drain);
            }
            return;
        }

        int hunger = player.getFoodData().getFoodLevel();
        if (hunger > HUNGER_THRESHOLD) {
            float drain = getHungerDrain();
            float healAmount = drain * getHungerToHealthRatio();
            if (tryRecover(player, healAmount)) {
                drainHunger(player, hunger, drain);
            }
        }
    }

    private static float getSaturationDrain(float saturation) {
        return Math.max(saturation * SATURATION_DRAIN_RATE, SATURATION_DRAIN_MIN);
    }

    private static void drainSaturation(ServerPlayer player, float saturation, float drain) {
        player.getFoodData().setSaturation(Math.max(0.0F, saturation - drain));
    }

    private static float getHungerDrain() {
        return HUNGER_DRAIN;
    }

    private static void drainHunger(ServerPlayer player, int hunger, float drain) {
        player.getFoodData().setFoodLevel(Math.max(0, hunger - Math.round(drain)));
    }

    private static boolean tryRecover(ServerPlayer player, float healAmount) {
        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();
        float absorption = player.getAbsorptionAmount();

        float healthGain = Math.min(healAmount, Math.max(0.0F, maxHealth - health));
        float overflow = healAmount - healthGain;
        float absorptionGain = Math.min(
                Math.max(0.0F, maxHealth - absorption),
                Math.max(0.0F, overflow * getAbsorptionRatio()));
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

    private static float getAbsorptionRatio() {
        return TsukiCommonConfig.MAGATAMA_ORANGE_ABSORPTION_RATIO.get().floatValue();
    }

    private static float getSaturationToHealthRatio() {
        return TsukiCommonConfig.MAGATAMA_ORANGE_SATURATION_TO_HEALTH_RATIO.get().floatValue();
    }

    private static float getHungerToHealthRatio() {
        return TsukiCommonConfig.MAGATAMA_ORANGE_HUNGER_TO_HEALTH_RATIO.get().floatValue();
    }
}
