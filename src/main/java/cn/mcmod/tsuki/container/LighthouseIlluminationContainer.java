package cn.mcmod.tsuki.container;

import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.init.MenuTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class LighthouseIlluminationContainer extends AbstractLightContainer<LighthouseIlluminationBlockEntity> {
    public LighthouseIlluminationContainer(int id, Inventory inventory,
            LighthouseIlluminationBlockEntity blockEntity) {
        super(MenuTypeRegistry.LIGHTHOUSE_ILLUMINATION.get(), id, blockEntity,
                BlockRegistry.LIGHTHOUSE_ILLUMINATION.get());
    }

    public LighthouseIlluminationContainer(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getBlockEntity(inventory, data.readBlockPos(), LighthouseIlluminationBlockEntity.class,
                "lighthouse"));
    }
}
