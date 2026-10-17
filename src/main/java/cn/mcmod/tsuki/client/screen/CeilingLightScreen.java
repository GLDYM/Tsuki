package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.block.entity.CeilingLightBlockEntity;
import cn.mcmod.tsuki.container.CeilingLightContainer;
import cn.mcmod.tsuki.network.payload.ConfigureCeilingLightPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class CeilingLightScreen extends AbstractLightScreen<CeilingLightBlockEntity, CeilingLightContainer> {
    public CeilingLightScreen(CeilingLightContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void sendConfiguration(int color, int length, int width, int transparency, int polygonCount) {
        PacketDistributor.sendToServer(new ConfigureCeilingLightPayload(menu.blockEntity.getBlockPos(), color,
                length, width, transparency, polygonCount));
    }
}
