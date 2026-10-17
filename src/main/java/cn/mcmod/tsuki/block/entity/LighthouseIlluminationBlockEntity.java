package cn.mcmod.tsuki.block.entity;

import cn.mcmod.tsuki.container.LighthouseIlluminationContainer;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class LighthouseIlluminationBlockEntity extends AbstractLightBlockEntity {
    private static final Settings SETTINGS = new Settings(0x66CCFF, new ValueRange(16, 1, 31),
            new ValueRange(4, 1, 15), new ValueRange(160, 0, 255), new ValueRange(2, 2, 10));

    public LighthouseIlluminationBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.LIGHTHOUSE_ILLUMINATION.get(), pos, state, SETTINGS);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new LighthouseIlluminationContainer(id, inventory, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tsuki.lighthouse_illumination");
    }
}
