package cn.mcmod.tsuki.network.payload;

import cn.mcmod.tsuki.Tsuki;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Carries the client-side "allow stacking in cooking pot inputs" setting to the
 * server. The server owns the actual inventory mutation, so it needs a copy of
 * the client preference instead of reading the client-only config spec.
 */
public record CookingPotStackingPayload(boolean allowStacking) implements CustomPacketPayload {
    public static final String KEY = "TsukiAllowCookingPotStacking";
    public static final Type<CookingPotStackingPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "cooking_pot_stacking"));
    public static final StreamCodec<ByteBuf, CookingPotStackingPayload> STREAM_CODEC = StreamCodec.of(
            (buffer, payload) -> buffer.writeBoolean(payload.allowStacking()),
            buffer -> new CookingPotStackingPayload(buffer.readBoolean()));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CookingPotStackingPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                player.getPersistentData().putBoolean(KEY, payload.allowStacking());
            }
        });
    }
}
