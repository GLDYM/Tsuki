package cn.mcmod.tsuki.block.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class DrinkDisplayBlockEntity extends SyncedBlockEntity {
    public static final int SLOT_COUNT = 4;

    private final ItemStackHandler inventory;
    private final IItemHandler itemHandler;
    private final float[] rotations = new float[SLOT_COUNT];

    public DrinkDisplayBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.DRINK_DISPLAY.get(), pos, state);
        this.inventory = createHandler();
        this.itemHandler = inventory;
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
        for (int i = 0; i < SLOT_COUNT; i++) {
            rotations[i] = compound.getFloat("Rotation" + i);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("Inventory", inventory.serializeNBT(registries));
        for (int i = 0; i < SLOT_COUNT; i++) {
            compound.putFloat("Rotation" + i, rotations[i]);
        }
    }

    public boolean addItem(ItemStack stack, int slot, float rotation) {
        if (slot < 0 || slot >= SLOT_COUNT || stack.isEmpty() || !getStackInSlot(slot).isEmpty()) {
            return false;
        }
        inventory.setStackInSlot(slot, stack.split(1));
        rotations[slot] = rotation;
        inventoryChanged();
        return true;
    }

    public ItemStack removeItem(int slot) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = inventory.getStackInSlot(slot);
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack result = stack.split(1);
        rotations[slot] = 0.0F;
        inventoryChanged();
        return result;
    }

    public int findFirstEmptySlot() {
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public int findFirstOccupiedSlot() {
        for (int i = 0; i < SLOT_COUNT; i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    public ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            return ItemStack.EMPTY;
        }
        return inventory.getStackInSlot(slot);
    }

    public float getRotation(int slot) {
        if (slot < 0 || slot >= SLOT_COUNT) {
            return 0.0F;
        }
        return rotations[slot];
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    @Nonnull
    public IItemHandler getItemHandler(@Nullable Direction side) {
        return itemHandler;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(SLOT_COUNT) {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }
        };
    }
}
