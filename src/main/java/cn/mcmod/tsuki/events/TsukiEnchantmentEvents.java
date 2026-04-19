package cn.mcmod.tsuki.events;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.enchantment.TsukiEnchantments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public class TsukiEnchantmentEvents {
    private static final int ANTI_FIRE_INTERVAL_TICKS = 80;
    private static final int ANTI_FIRE_DURATION_TICKS = 340;
    private static final int POWER_HASTE_DURATION_TICKS = 120;

    private TsukiEnchantmentEvents() {
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
        int antiFireLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.ANTI_FIRE, mainHand);
        if (antiFireLevel <= 0) {
            return;
        }

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, ANTI_FIRE_DURATION_TICKS, 0, true, false));
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int smashLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.SMASH, mainHand);
        if (smashLevel <= 0) {
            return;
        }

        float chance = Math.min(1.0F, smashLevel * 0.10F);
        RandomSource random = player.getRandom();
        if (random.nextFloat() <= chance) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, POWER_HASTE_DURATION_TICKS, 1, true, false));
        }
    }
}

