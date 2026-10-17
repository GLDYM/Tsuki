package cn.mcmod.tsuki.block.entity;

import cn.mcmod.tsuki.container.CeilingLightContainer;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class CeilingLightBlockEntity extends AbstractLightBlockEntity {
    private static final Settings SETTINGS = new Settings(0xFFFF00, new ValueRange(12, 5, 30),
            new ValueRange(3, 1, 10), new ValueRange(255, 30, 255), new ValueRange(3, 2, 32));

    public CeilingLightBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CEILING_LIGHT.get(), pos, state, SETTINGS);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CeilingLightContainer(id, inventory, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tsuki.ceiling_light");
    }
}
