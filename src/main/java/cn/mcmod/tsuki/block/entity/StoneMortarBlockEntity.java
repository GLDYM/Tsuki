package cn.mcmod.tsuki.block.entity;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cn.mcmod.tsuki.container.StoneMortarContainer;
import cn.mcmod.tsuki.block.capability.StoneMortarItemHandler;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.recipes.RecipeTypeRegistry;
import cn.mcmod.tsuki.recipes.StoneMortarRecipe;
import cn.mcmod_mmf.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod_mmf.mmlib.utils.LevelUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class StoneMortarBlockEntity extends SyncedBlockEntity implements MenuProvider {

    private final ItemStackHandler inventory;
    private final IItemHandler inputHandler;
    private final IItemHandler outputHandler;

    protected final ContainerData blockData;
    private final Object2IntOpenHashMap<ResourceLocation> experienceTracker;

    private int recipeTime;
    private int recipeTimeTotal;

    private ResourceLocation lastRecipeID;
    private boolean checkNewRecipe;

    public StoneMortarBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.STONE_MORTAR.get(), pos, state);

        this.inventory = createHandler();
        this.inputHandler = new StoneMortarItemHandler(inventory, Direction.UP);
        this.outputHandler = new StoneMortarItemHandler(inventory, Direction.DOWN);
        this.blockData = createIntArray();
        this.experienceTracker = new Object2IntOpenHashMap<>();
        this.checkNewRecipe = true;
    }

    public static void workingTick(Level level, BlockPos pos, BlockState state, StoneMortarBlockEntity blockEntity) {
        boolean didInventoryChange = false;
        boolean isWorking = false;

        if (blockEntity.hasInput()) {
            Optional<StoneMortarRecipe> recipe = blockEntity
                    .getMatchingRecipe(new RecipeWrapper(blockEntity.inventory), level);
            if (recipe.isPresent() && blockEntity.canWork(recipe.get(), level)) {
                isWorking = true;
                didInventoryChange = blockEntity.processRecipe(recipe.get(), level);
            } else {
                blockEntity.recipeTime = 0;
            }
        } else if (blockEntity.recipeTime > 0) {
            blockEntity.recipeTime = 0;
        }

        blockEntity.updateWorkingState(level, pos, state, isWorking);

        if (didInventoryChange) {
            blockEntity.inventoryChanged();
        }
    }

    private void updateWorkingState(Level level, BlockPos pos, BlockState state, boolean isWorking) {
        if (level.isClientSide() || !(state.getBlock() instanceof StoneMortarBlock)) {
            return;
        }
        if (state.getValue(StoneMortarBlock.WORKING) != isWorking) {
            level.setBlock(pos, state.setValue(StoneMortarBlock.WORKING, isWorking), 3);
        }
    }

    private boolean hasInput() {
        for (int i = 0; i < 4; ++i) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Optional<StoneMortarRecipe> getMatchingRecipe(RecipeWrapper inventoryWrapper, Level level) {
        if (level == null) {
            return Optional.empty();
        }

        if (lastRecipeID != null) {
            Optional<RecipeHolder<?>> holder = level.getRecipeManager().byKey(lastRecipeID);
            if (holder.isPresent() && holder.get().value() instanceof StoneMortarRecipe recipe
                    && recipe.matches(inventoryWrapper, level)) {
                return Optional.of(recipe);
            }
        }

        if (!checkNewRecipe) {
            return Optional.empty();
        }

        Optional<RecipeHolder<StoneMortarRecipe>> recipeHolder = level.getRecipeManager()
                .getRecipeFor(RecipeTypeRegistry.STONE_MORTAR_RECIPE_TYPE.get(), inventoryWrapper, level);
        checkNewRecipe = false;
        if (recipeHolder.isPresent()) {
            lastRecipeID = recipeHolder.get().id();
            return Optional.of(recipeHolder.get().value());
        }

        lastRecipeID = null;
        checkNewRecipe = false;
        return Optional.empty();
    }

    protected boolean canWork(StoneMortarRecipe recipe, Level level) {
        if (hasInput()) {
            boolean check_extra = false;
            ItemStack resultStack = recipe.getResultItem(level.registryAccess());

            if (resultStack.isEmpty()) {
                return false;
            } else {
                ItemStack outStack = inventory.getStackInSlot(4);
                if (outStack.isEmpty()) {
                    check_extra = true;
                } else if (!ItemStack.isSameItem(outStack, resultStack)) {
                    return false;
                } else {
                    check_extra = outStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
                }

                ItemStack resultExtraStack = recipe.getResultItemList().size() > 1 ? recipe.getResultItemList().get(1)
                        : ItemStack.EMPTY;
                if (resultExtraStack.isEmpty()) {
                    return check_extra;
                } else if (check_extra) {
                    ItemStack extraStack = inventory.getStackInSlot(5);
                    if (extraStack.isEmpty()) {
                        return true;
                    } else if (!ItemStack.isSameItem(extraStack, resultExtraStack)) {
                        return false;
                    } else {
                        return extraStack.getCount() + resultExtraStack.getCount() <= resultExtraStack
                                .getMaxStackSize();
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private boolean processRecipe(StoneMortarRecipe recipe, Level level) {
        if (level == null) {
            return false;
        }

        ++recipeTime;
        recipeTimeTotal = recipe.getRecipeTime();
        if (recipeTime < recipeTimeTotal) {
            return false;
        }

        recipeTime = 0;

        ItemStack resultStack = recipe.getResultItem(level.registryAccess());
        ItemStack outStack = inventory.getStackInSlot(4);
        ItemStack extraStack = inventory.getStackInSlot(5);
        ItemStack resultExtraStack = recipe.getResultItemList().size() > 1 ? recipe.getResultItemList().get(1)
                : ItemStack.EMPTY;
        if (outStack.isEmpty()) {
            inventory.setStackInSlot(4, resultStack.copy());
        } else if (ItemStack.isSameItem(outStack, resultStack)) {
            outStack.grow(resultStack.getCount());
        }
        if (!resultExtraStack.isEmpty()) {
            if (extraStack.isEmpty()) {
                inventory.setStackInSlot(5, resultExtraStack.copy());
            } else if (ItemStack.isSameItem(extraStack, resultExtraStack)) {
                extraStack.grow(resultExtraStack.getCount());
            }
        }

        if (lastRecipeID != null) {
            trackRecipeExperience(lastRecipeID);
        }

        for (int i = 0; i < 4; ++i) {
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (slotStack.hasCraftingRemainingItem()) {
                double x = worldPosition.getX() + 0.5;
                double y = worldPosition.getY() + 0.7;
                double z = worldPosition.getZ() + 0.5;
                LevelUtils.spawnItemEntity(level, inventory.getStackInSlot(i).getCraftingRemainingItem(), x, y, z, 0F,
                        0.25F,
                        0F);
            }
            if (!slotStack.isEmpty()) {
                slotStack.shrink(1);
            }
        }
        return true;
    }

    public void trackRecipeExperience(@Nullable ResourceLocation recipeId) {
        if (recipeId != null) {
            experienceTracker.addTo(recipeId, 1);
        }
    }

    public void clearUsedRecipes(Player player) {
        grantStoredRecipeExperience(player.level(), player.position());
        experienceTracker.clear();
    }

    public void grantStoredRecipeExperience(Level world, Vec3 pos) {
        for (Object2IntMap.Entry<ResourceLocation> entry : experienceTracker.object2IntEntrySet()) {
            world.getRecipeManager().byKey(entry.getKey()).ifPresent(holder -> {
                if (holder.value() instanceof StoneMortarRecipe recipe) {
                    LevelUtils.splitAndSpawnExperience(world, pos, entry.getIntValue(), recipe.getExperience());
                }
            });
        }
    }

    @Nonnull
    public IItemHandler getItemHandler(@Nullable Direction side) {
        return side == null || side.equals(Direction.UP) ? inputHandler : outputHandler;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        for (int i = 0; i < 6; ++i) {
            drops.add(inventory.getStackInSlot(i));
        }
        return drops;
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
        recipeTime = compound.getInt("RecipeTime");
        recipeTimeTotal = compound.getInt("RecipeTimeTotal");
        CompoundTag compoundRecipes = compound.getCompound("RecipesUsed");
        for (String key : compoundRecipes.getAllKeys()) {
            experienceTracker.put(ResourceLocation.parse(key), compoundRecipes.getInt(key));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.putInt("RecipeTime", recipeTime);
        compound.putInt("RecipeTimeTotal", recipeTimeTotal);
        compound.put("Inventory", inventory.serializeNBT(registries));
        CompoundTag compoundRecipes = new CompoundTag();
        experienceTracker
                .forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
        compound.put("RecipesUsed", compoundRecipes);
    }

    private CompoundTag writeItems(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("Inventory", inventory.serializeNBT(registries));
        return compound;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return writeItems(new CompoundTag(), registries);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            @Override
            protected void onContentsChanged(int slot) {
                if (slot >= 0 && slot < 4) {
                    checkNewRecipe = true;
                }
                inventoryChanged();
            }
        };
    }

    private ContainerData createIntArray() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return StoneMortarBlockEntity.this.recipeTime;
                    case 1:
                        return StoneMortarBlockEntity.this.recipeTimeTotal;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        StoneMortarBlockEntity.this.recipeTime = value;
                        break;
                    case 1:
                        StoneMortarBlockEntity.this.recipeTimeTotal = value;
                        break;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
        return new StoneMortarContainer(id, player, this, blockData);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tsuki.stone_mortar");
    }

}
