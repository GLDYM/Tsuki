package cn.mcmod.tsuki.network.payload;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ConfigureLighthousePayload(BlockPos pos, int color, int length, int width, int transparency) implements CustomPacketPayload {
    public static final Type<ConfigureLighthousePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "configure_lighthouse"));
    public static final StreamCodec<ByteBuf, ConfigureLighthousePayload> STREAM_CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, ConfigureLighthousePayload::pos, ByteBufCodecs.VAR_INT, ConfigureLighthousePayload::color, ByteBufCodecs.VAR_INT, ConfigureLighthousePayload::length, ByteBufCodecs.VAR_INT, ConfigureLighthousePayload::width, ByteBufCodecs.VAR_INT, ConfigureLighthousePayload::transparency, ConfigureLighthousePayload::new);
    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
    public static void handle(ConfigureLighthousePayload payload, IPayloadContext context) { context.enqueueWork(() -> { if (context.player().level().getBlockEntity(payload.pos) instanceof LighthouseIlluminationBlockEntity lighthouse && context.player().distanceToSqr(payload.pos.getX() + .5, payload.pos.getY() + .5, payload.pos.getZ() + .5) <= 64) lighthouse.configure(payload.color, payload.length, payload.width, payload.transparency); }); }
}
