package cn.mcmod.tsuki.container;

import cn.mcmod.tsuki.block.entity.WallLightBlockEntity;
import cn.mcmod.tsuki.init.MenuTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class WallLightContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    public final WallLightBlockEntity blockEntity;
    public WallLightContainer(int id, Inventory inventory, WallLightBlockEntity entity) {
        super(MenuTypeRegistry.WALL_LIGHT.get(), id); blockEntity = entity;
        access = ContainerLevelAccess.create(entity.getLevel(), entity.getBlockPos());
    }
    public WallLightContainer(int id, Inventory inventory, FriendlyByteBuf data) {
        this(id, inventory, getEntity(inventory, data.readBlockPos()));
    }
    private static WallLightBlockEntity getEntity(Inventory inventory, BlockPos pos) {
        Objects.requireNonNull(inventory);
        if (inventory.player.level().getBlockEntity(pos) instanceof WallLightBlockEntity entity) return entity;
        throw new IllegalStateException("Expected wall light block entity at " + pos);
    }
    @Override public boolean stillValid(Player player) {
        return stillValid(access, player, BlockRegistry.WALL_LIGHT.get())
                || stillValid(access, player, BlockRegistry.WALL_LIGHT_WOOD.get());
    }
    @Override public ItemStack quickMoveStack(Player player, int slot) { return ItemStack.EMPTY; }
}
