package cn.mcmod.tsuki.network;

import cn.mcmod.tsuki.network.payload.MagatamaBoostPayload;
import cn.mcmod.tsuki.network.payload.ToggleMagatamaModePayload;
import cn.mcmod.tsuki.network.payload.ConfigureLighthousePayload;
import cn.mcmod.tsuki.network.payload.ConfigureWallLightPayload;
import cn.mcmod.tsuki.network.payload.ConfigureCeilingLightPayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class TsukiNetwork {
    private TsukiNetwork() {
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(ToggleMagatamaModePayload.TYPE, ToggleMagatamaModePayload.STREAM_CODEC,
                ToggleMagatamaModePayload::handle);
        registrar.playToServer(MagatamaBoostPayload.TYPE, MagatamaBoostPayload.STREAM_CODEC,
                MagatamaBoostPayload::handle);
        registrar.playToServer(ConfigureLighthousePayload.TYPE, ConfigureLighthousePayload.STREAM_CODEC,
                ConfigureLighthousePayload::handle);
        registrar.playToServer(ConfigureWallLightPayload.TYPE, ConfigureWallLightPayload.STREAM_CODEC,
                ConfigureWallLightPayload::handle);
        registrar.playToServer(ConfigureCeilingLightPayload.TYPE, ConfigureCeilingLightPayload.STREAM_CODEC,
                ConfigureCeilingLightPayload::handle);
    }
}
