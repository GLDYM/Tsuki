package cn.mcmod.tsuki.network.payload;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.container.AbstractLightContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ConfigureWallLightPayload(BlockPos pos, int color, int length, int width, int transparency,
        int polygonCount) implements CustomPacketPayload {
    public static final Type<ConfigureWallLightPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "configure_wall_light"));
    public static final StreamCodec<ByteBuf, ConfigureWallLightPayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, ConfigureWallLightPayload::pos, ByteBufCodecs.VAR_INT,
            ConfigureWallLightPayload::color,
            ByteBufCodecs.VAR_INT, ConfigureWallLightPayload::length, ByteBufCodecs.VAR_INT,
            ConfigureWallLightPayload::width,
            ByteBufCodecs.VAR_INT, ConfigureWallLightPayload::transparency, ByteBufCodecs.VAR_INT,
            ConfigureWallLightPayload::polygonCount,
            ConfigureWallLightPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ConfigureWallLightPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }
            if (!(player.containerMenu instanceof AbstractLightContainer<?> menu)
                    || !menu.blockEntity.getBlockPos().equals(payload.pos)
                    || !menu.stillValid(player)) {
                return;
            }
            if (player.distanceToSqr(payload.pos.getX() + 0.5D, payload.pos.getY() + 0.5D,
                    payload.pos.getZ() + 0.5D) > 64.0D
                    || !player.level().isLoaded(payload.pos)) {
                return;
            }
            if (player.level().getBlockEntity(payload.pos) instanceof WallLightBlockEntity light) {
                light.configure(payload.color, payload.length, payload.width, payload.transparency,
                        payload.polygonCount);
            }
        });
    }
}
