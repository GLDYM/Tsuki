package cn.mcmod.tsuki.client.screen;

import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.container.WallLightContainer;
import cn.mcmod.tsuki.network.payload.ConfigureWallLightPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class WallLightScreen extends AbstractLightScreen<WallLightBlockEntity, WallLightContainer> {
    public WallLightScreen(WallLightContainer menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void sendConfiguration(int color, int length, int width, int transparency, int polygonCount) {
        PacketDistributor.sendToServer(new ConfigureWallLightPayload(menu.blockEntity.getBlockPos(), color, length,
                width, transparency, polygonCount));
    }
}
