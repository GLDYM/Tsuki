package cn.mcmod.tsuki.container;

import java.util.Objects;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class CookingPotContainer extends AbstractContainerMenu {

    private static final int MACHINE_SLOT_COUNT = CookingPotBlockEntity.SLOT_COUNT;
    private static final int PLAYER_INV_START = MACHINE_SLOT_COUNT;
    private static final int PLAYER_INV_END = PLAYER_INV_START + 27;
    private static final int HOTBAR_START = PLAYER_INV_END;
    private static final int HOTBAR_END = HOTBAR_START + 9;

    public final CookingPotBlockEntity blockEntity;
    public final ItemStackHandler inventory;
    private final ContainerData containerData;
    private final ContainerLevelAccess canInteractWithCallable;

    public CookingPotContainer(final int windowId, final Inventory playerInventory,
            final CookingPotBlockEntity blockEntity, ContainerData cookingPotDataIn) {
        super(ContainerRegistry.COOKING_POT.get(), windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.containerData = cookingPotDataIn;
        this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        int startX = 8;
        int startY = 18;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                this.addSlot(new SlotItemHandler(inventory, (row * 3) + column, 36 + (column * 18), 17 + (row * 18)));
            }
        }

        this.addSlot(new CookingPotMealDisplaySlot(inventory, CookingPotBlockEntity.SLOT_MEAL_DISPLAY, 140, 27));
        this.addSlot(new SlotItemHandler(inventory, CookingPotBlockEntity.SLOT_CONTAINER_INPUT, 108, 53));
        this.addSlot(new CookingPotResultSlot(playerInventory.player, blockEntity, inventory, CookingPotBlockEntity.SLOT_OUTPUT, 140, 53));

        // Main Player Inventory
        int startPlayerInvY = startY * 4 + 12;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * 18),
                        startPlayerInvY + (row * 18)));
            }
        }

        // Hotbar
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * 18), 142));
        }

        this.addDataSlots(cookingPotDataIn);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();

            if (index == CookingPotBlockEntity.SLOT_OUTPUT) {
                if (!this.moveItemStackTo(itemStack1, PLAYER_INV_START, HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemStack1, itemStack);
            } else if (index >= PLAYER_INV_START) {
                boolean movedToContainer = blockEntity.isServingContainer(itemStack1)
                        && this.moveItemStackTo(itemStack1, CookingPotBlockEntity.SLOT_CONTAINER_INPUT,
                                CookingPotBlockEntity.SLOT_CONTAINER_INPUT + 1, false);
                if (!movedToContainer && !this.moveItemStackTo(itemStack1, CookingPotBlockEntity.SLOT_INPUT_START,
                        CookingPotBlockEntity.SLOT_INPUT_START + CookingPotBlockEntity.SLOT_INPUT_COUNT, false)) {
                    if (index >= PLAYER_INV_START && index < PLAYER_INV_END) {
                        if (!this.moveItemStackTo(itemStack1, HOTBAR_START, HOTBAR_END, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= HOTBAR_START && index < HOTBAR_END
                            && !this.moveItemStackTo(itemStack1, PLAYER_INV_START, PLAYER_INV_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemStack1, PLAYER_INV_START, HOTBAR_END, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemStack1);
        }

        return itemStack;
    }

    private static CookingPotBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final BlockEntity blockAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (blockAtPos instanceof CookingPotBlockEntity) {
            return (CookingPotBlockEntity) blockAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + blockAtPos);
    }

    public CookingPotContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(canInteractWithCallable, playerIn, BlockRegistry.COOKING_POT.get());
    }

    public int getCookProgressionScaled() {
        int i = this.containerData.get(0);
        int j = this.containerData.get(1);
        return j != 0 && i != 0 ? i * 34 / j : 0;
    }

    public boolean isHeated() {
        return this.blockEntity.isHeated();
    }
}

