package cn.mcmod.tsuki.container;

import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
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

public class LighthouseIlluminationContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    public final LighthouseIlluminationBlockEntity blockEntity;
    public LighthouseIlluminationContainer(int id, Inventory inventory, LighthouseIlluminationBlockEntity blockEntity) {
        super(MenuTypeRegistry.LIGHTHOUSE_ILLUMINATION.get(), id); this.blockEntity = blockEntity; access = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
    }
    public LighthouseIlluminationContainer(int id, Inventory inventory, FriendlyByteBuf data) { this(id, inventory, getBlockEntity(inventory, data.readBlockPos())); }
    private static LighthouseIlluminationBlockEntity getBlockEntity(Inventory inventory, BlockPos pos) { Objects.requireNonNull(inventory); if (inventory.player.level().getBlockEntity(pos) instanceof LighthouseIlluminationBlockEntity entity) return entity; throw new IllegalStateException("Expected lighthouse block entity at " + pos); }
    @Override public boolean stillValid(Player player) { return stillValid(access, player, BlockRegistry.LIGHTHOUSE_ILLUMINATION.get()); }
    @Override public ItemStack quickMoveStack(Player player, int slot) { return ItemStack.EMPTY; }
}
