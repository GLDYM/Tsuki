package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.EnchantmentRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public class EnchantmentEvent {
    private static final int ANTI_FIRE_INTERVAL_TICKS = 80;
    private static final int ANTI_FIRE_DURATION_TICKS = 340;
    private static final float SMASH_MAX_BREAK_TICKS = 8.0F;

    private EnchantmentEvent() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (player.tickCount % ANTI_FIRE_INTERVAL_TICKS != 0) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int antiFireLevel = EnchantmentRegistry.getLevel(player.registryAccess(), EnchantmentRegistry.ANTI_FIRE,
                mainHand);
        if (antiFireLevel <= 0) {
            return;
        }

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, ANTI_FIRE_DURATION_TICKS, 0, true, false));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player == null) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int smashLevel = EnchantmentRegistry.getLevel(player.registryAccess(), EnchantmentRegistry.SMASH, mainHand);
        int omnitoolLevel = EnchantmentRegistry.getLevel(player.registryAccess(), EnchantmentRegistry.OMNITOOL,
                mainHand);
        if (smashLevel <= 0) {
            return;
        }

        float hardness = event.getState().getDestroySpeed(player.level(),
                event.getPosition().orElse(player.blockPosition()));
        if (hardness <= 0.0F) {
            return;
        }

        float currentSpeed = event.getNewSpeed();
        if (currentSpeed <= 0.0F) {
            return;
        }

        BlockState state = event.getState();
        boolean requiresCorrectTool = state.requiresCorrectToolForDrops();
        boolean originalCorrectTool = player.getMainHandItem().isCorrectToolForDrops(state);
        float adjustedSpeed = currentSpeed;

        boolean effectiveCorrectTool = !requiresCorrectTool || originalCorrectTool || omnitoolLevel > 0;
        float divisor = effectiveCorrectTool ? 30.0F : 100.0F;
        float currentBreakTicks = (hardness * divisor) / adjustedSpeed;

        if (hardness <= 1.5F) {
            float targetSpeed = (hardness * divisor);
            event.setNewSpeed(Math.max(adjustedSpeed, targetSpeed));
            return;
        }

        if (currentBreakTicks > SMASH_MAX_BREAK_TICKS) {
            float targetSpeed = (hardness * divisor) / SMASH_MAX_BREAK_TICKS;
            event.setNewSpeed(Math.max(adjustedSpeed, targetSpeed));
            return;
        }
    }

    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int omnitoolLevel = EnchantmentRegistry.getLevel(player.registryAccess(), EnchantmentRegistry.OMNITOOL,
                mainHand);
        if (omnitoolLevel <= 0) {
            return;
        }

        event.setCanHarvest(true);
    }
}
