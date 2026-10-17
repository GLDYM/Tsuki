package cn.mcmod.tsuki.container;

import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.init.MenuTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class WallLightContainer extends AbstractLightContainer<WallLightBlockEntity> {
    public WallLightContainer(int id, Inventory inventory, WallLightBlockEntity blockEntity) {
        super(MenuTypeRegistry.WALL_LIGHT.get(), id, blockEntity, BlockRegistry.WALL_LIGHT.get(),
                BlockRegistry.WALL_LIGHT_WOOD.get());
    }

    public WallLightContainer(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getBlockEntity(inventory, data.readBlockPos(), WallLightBlockEntity.class, "wall light"));
    }
}
