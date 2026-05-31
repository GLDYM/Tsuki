package cn.mcmod.tsuki.network.payload;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.magatama.MagatamaBlueHelper;
import cn.mcmod.tsuki.item.magatama.MagatamaBlueItem;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteHelper;
import cn.mcmod.tsuki.item.magatama.MagatamaWhiteItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ToggleMagatamaModePayload() implements CustomPacketPayload {
    public static final ToggleMagatamaModePayload INSTANCE = new ToggleMagatamaModePayload();
    public static final CustomPacketPayload.Type<ToggleMagatamaModePayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "toggle_magatama_mode"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleMagatamaModePayload> STREAM_CODEC = StreamCodec
            .unit(INSTANCE);

    @Override
    public Type<ToggleMagatamaModePayload> type() {
        return TYPE;
    }

    public static void handle(ToggleMagatamaModePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                MagatamaBlueItem.WeatherMode weatherMode = MagatamaBlueHelper.toggleMainHandMode(player);
                if (weatherMode != null) {
                    player.displayClientMessage(
                            Component.translatable("tsuki.tooltip.magatama_blue.mode",
                                    Component.translatable(weatherMode.getTranslationKey()).withStyle(ChatFormatting.AQUA)),
                            true);
                    return;
                }

                MagatamaWhiteItem.FlightMode mode = MagatamaWhiteHelper.toggleActiveMode(player);
                if (mode != null) {
                    player.displayClientMessage(
                            Component.translatable("tsuki.tooltip.magatama_white.mode",
                                    Component.translatable(mode.getTranslationKey()).withStyle(ChatFormatting.AQUA)),
                            true);
                }
            }
        });
    }
}
