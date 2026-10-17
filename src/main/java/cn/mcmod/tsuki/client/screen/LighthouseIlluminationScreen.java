package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.container.LighthouseIlluminationContainer;
import cn.mcmod.tsuki.network.payload.ConfigureLighthousePayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class LighthouseIlluminationScreen
        extends AbstractLightScreen<LighthouseIlluminationBlockEntity, LighthouseIlluminationContainer> {
    public LighthouseIlluminationScreen(LighthouseIlluminationContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void sendConfiguration(int color, int length, int width, int transparency, int polygonCount) {
        PacketDistributor.sendToServer(new ConfigureLighthousePayload(menu.blockEntity.getBlockPos(), color, length,
                width, transparency, polygonCount));
    }

    @Override
    protected String translationGroup() {
        return "lighthouse";
    }

    @Override
    protected String polygonTranslationKey() {
        return "polygon_count";
    }
}
