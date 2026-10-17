package cn.mcmod.tsuki.container;

import cn.mcmod.tsuki.block.entity.AbstractLightBlockEntity;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public abstract class AbstractLightContainer<T extends AbstractLightBlockEntity> extends AbstractContainerMenu {
    public final T blockEntity;
    private final ContainerLevelAccess access;
    private final Block[] validBlocks;

    protected AbstractLightContainer(MenuType<?> menuType, int id, T blockEntity, Block... validBlocks) {
        super(menuType, id);
        this.blockEntity = blockEntity;
        this.validBlocks = validBlocks.clone();
        access = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
    }

    protected static <T extends AbstractLightBlockEntity> T getBlockEntity(Inventory inventory, BlockPos pos,
            Class<T> blockEntityClass, String description) {
        Objects.requireNonNull(inventory);
        var blockEntity = inventory.player.level().getBlockEntity(pos);
        if (blockEntityClass.isInstance(blockEntity))
            return blockEntityClass.cast(blockEntity);
        throw new IllegalStateException("Expected " + description + " block entity at " + pos);
    }

    @Override
    public boolean stillValid(Player player) {
        for (Block block : validBlocks) {
            if (stillValid(access, player, block))
                return true;
        }
        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        return ItemStack.EMPTY;
    }
}
