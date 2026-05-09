package cn.mcmod.tsuki.block.entity;

import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.ShakerDataHelper;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class ShakerBlockEntity extends SyncedBlockEntity {
    private final ItemStackHandler inventory;
    private int shakeProgress;

    public ShakerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SHAKER.get(), pos, state);
        this.inventory = createHandler();
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
        shakeProgress = compound.getInt("ShakeProgress");
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("Inventory", inventory.serializeNBT(registries));
        if (shakeProgress > 0) {
            compound.putInt("ShakeProgress", shakeProgress);
        }
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public IItemHandler getItemHandler(Direction side) {
        return inventory;
    }

    public int getShakeProgress() {
        return shakeProgress;
    }

    public void setShakeProgress(int shakeProgress) {
        this.shakeProgress = Math.max(0, shakeProgress);
        inventoryChanged();
    }

    public int findFirstEmptyInputSlot() {
        for (int slot = ShakerDataHelper.SLOT_INPUT_START;
                slot < ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT;
                ++slot) {
            if (inventory.getStackInSlot(slot).isEmpty()) {
                return slot;
            }
        }
        return -1;
    }

    public int findLastOccupiedInputSlot() {
        for (int slot = ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT - 1;
                slot >= ShakerDataHelper.SLOT_INPUT_START;
                --slot) {
            if (!inventory.getStackInSlot(slot).isEmpty()) {
                return slot;
            }
        }
        return -1;
    }

    public ItemStack takeOne(int slot) {
        return inventory.extractItem(slot, 1, false);
    }

    public ItemStack getOutputStack() {
        return inventory.getStackInSlot(ShakerDataHelper.SLOT_OUTPUT);
    }

    public ItemStack toItemStack(HolderLookup.Provider registries) {
        ItemStack stack = new ItemStack(DrinkRegistry.SHAKER.get());
        ShakerDataHelper.save(stack, inventory, shakeProgress, registries);
        return stack;
    }

    public void loadFromItemStack(ItemStack stack, HolderLookup.Provider registries) {
        ShakerDataHelper.load(stack, inventory, registries);
        this.shakeProgress = ShakerDataHelper.loadShakeProgress(stack);
        inventoryChanged();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(ShakerDataHelper.SLOT_COUNT) {
            @Override
            public int getSlotLimit(int slot) {
                return slot == ShakerDataHelper.SLOT_OUTPUT ? 64 : 1;
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return slot != ShakerDataHelper.SLOT_OUTPUT && !stack.is(TsukiItemTags.INGREDIENT_BLACKLIST);
            }

            @Override
            protected void onContentsChanged(int slot) {
                if (slot != ShakerDataHelper.SLOT_OUTPUT) {
                    shakeProgress = 0;
                }
                inventoryChanged();
            }
        };
    }
}
