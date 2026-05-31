package cn.mcmod.tsuki.network.payload;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record MagatamaBoostPayload() implements CustomPacketPayload {
    private static final Map<UUID, Long> LAST_BOOST_TICK = new HashMap<>();
    private static final long BOOST_COOLDOWN_TICKS = 20L;

    public static final MagatamaBoostPayload INSTANCE = new MagatamaBoostPayload();
    public static final CustomPacketPayload.Type<MagatamaBoostPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "magatama_boost"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MagatamaBoostPayload> STREAM_CODEC = StreamCodec
            .unit(INSTANCE);

    @Override
    public Type<MagatamaBoostPayload> type() {
        return TYPE;
    }

    public static void handle(MagatamaBoostPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                if (TsukiCommonConfig.DEBUG_MODE.get()) {
                    Tsuki.getLogger().info("[MagatamaWhite] Boost payload received without server player");
                }
                return;
            }

            boolean isFallFlying = player.isFallFlying();
            boolean elytraModeActive = MagatamaWhiteHelper.isElytraModeActive(player);
            boolean hasMagatama = MagatamaWhiteHelper.hasActiveWhiteMagatama(player);
            if (TsukiCommonConfig.DEBUG_MODE.get()) {
                Tsuki.getLogger().info(
                        "[MagatamaWhite] Server boost payload: player={}, fallFlying={}, elytraMode={}, hasMagatama={}, sprint={}, delta={}",
                        player.getGameProfile().getName(), isFallFlying, elytraModeActive, hasMagatama,
                        player.isSprinting(), player.getDeltaMovement());
            }

            if (!isFallFlying || !elytraModeActive) {
                if (TsukiCommonConfig.DEBUG_MODE.get()) {
                    Tsuki.getLogger().info(
                            "[MagatamaWhite] Server boost rejected: fallFlying={}, elytraMode={}", isFallFlying,
                            elytraModeActive);
                }
                return;
            }

            long gameTime = player.level().getGameTime();
            long lastTick = LAST_BOOST_TICK.getOrDefault(player.getUUID(), 0L);
            if (gameTime - lastTick < BOOST_COOLDOWN_TICKS) {
                if (TsukiCommonConfig.DEBUG_MODE.get()) {
                    Tsuki.getLogger().info("[MagatamaWhite] Server boost rejected by cooldown: now={}, last={}",
                            gameTime, lastTick);
                }
                return;
            }

            LAST_BOOST_TICK.put(player.getUUID(), gameTime);
            player.startFallFlying();
            ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET, 64);
            player.level().addFreshEntity(new FireworkRocketEntity(player.level(), rocket, player));
            if (TsukiCommonConfig.DEBUG_MODE.get()) {
                Tsuki.getLogger().info("[MagatamaWhite] Server boost applied: player={}, delta={}",
                        player.getGameProfile().getName(), player.getDeltaMovement());
            }
        });
    }
}
