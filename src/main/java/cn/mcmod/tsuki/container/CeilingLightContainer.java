package cn.mcmod.tsuki.container;

import cn.mcmod.tsuki.block.entity.CeilingLightBlockEntity;
import cn.mcmod.tsuki.init.MenuTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CeilingLightContainer extends AbstractLightContainer<CeilingLightBlockEntity> {
    public CeilingLightContainer(int id, Inventory inventory, CeilingLightBlockEntity blockEntity) {
        super(MenuTypeRegistry.CEILING_LIGHT.get(), id, blockEntity, BlockRegistry.CEILING_LIGHT.get(),
                BlockRegistry.CEILING_LIGHT_WOOD.get());
    }

    public CeilingLightContainer(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getBlockEntity(inventory, data.readBlockPos(), CeilingLightBlockEntity.class,
                "ceiling light"));
    }
}
