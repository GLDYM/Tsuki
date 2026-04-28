package cn.mcmod.tsuki.block.entity;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.container.DistillerContainer;
import cn.mcmod.tsuki.block.capability.FermenterItemHandler;
import cn.mcmod.tsuki.block.machines.DistillerBlock;
import cn.mcmod.tsuki.recipes.DistillerRecipe;
import cn.mcmod.tsuki.recipes.RecipeTypeRegistry;
import cn.mcmod.mmlib.block.entity.HeatableBlockEntity;
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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public class DistillerBlockEntity extends SyncedBlockEntity implements MenuProvider, HeatableBlockEntity {

    public static final int TANK_CAPACITY = 4000;
    private final ItemStackHandler inventory;
    private final IItemHandler inputHandler;
    private final IItemHandler outputHandler;

    private final FluidTank inputfluidTank;
    private final FluidTank outputfluidTank;
    protected final ContainerData blockData;
    private final Object2IntOpenHashMap<ResourceLocation> experienceTracker;

    private int recipeTime;
    private int recipeTimeTotal;

    private ResourceLocation lastRecipeID;
    private boolean checkNewRecipe;

    public DistillerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.DISTILLER.get(), pos, state);

        this.inventory = createHandler();
        this.inputHandler = new FermenterItemHandler(inventory, Direction.UP);
        this.outputHandler = new FermenterItemHandler(inventory, Direction.DOWN);
        this.blockData = createIntArray();
        this.inputfluidTank = createInputFluidHandler();
        this.outputfluidTank = createFluidHandler();
        this.experienceTracker = new Object2IntOpenHashMap<>();
        this.checkNewRecipe = true;
    }

    public static void workingTick(Level level, BlockPos pos, BlockState state, DistillerBlockEntity blockEntity) {
        boolean didInventoryChange = false;
        if (blockEntity.isHeated(level, pos) && blockEntity.hasInput()) {
            Optional<DistillerRecipe> recipe = blockEntity.getMatchingRecipe(new RecipeWrapper(blockEntity.inventory));
            if (recipe.isPresent() && blockEntity.canWork(recipe.get())) {
                didInventoryChange = blockEntity.processRecipe(recipe.get());
            } else {
                blockEntity.recipeTime = 0;
                blockEntity.recipeTimeTotal = 0;
            }
        } else {
            blockEntity.recipeTime = 0;
            blockEntity.recipeTimeTotal = 0;
        }

        if (didInventoryChange) {
            blockEntity.inventoryChanged();
        }
    }

    private boolean hasInput() {
        if (!this.inputfluidTank.isEmpty()) {
            return true;
        }

        for (int i = 0; i < 3; ++i) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Optional<DistillerRecipe> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
        if (level == null) {
            return Optional.empty();
        }

        if (lastRecipeID != null) {
            Optional<RecipeHolder<DistillerRecipe>> recipeHolder = level.getRecipeManager()
                    .getAllRecipesFor(RecipeTypeRegistry.DISTILLER_RECIPE_TYPE.get()).stream()
                    .filter(now -> now.id().equals(lastRecipeID)).findFirst();
            if (recipeHolder.isPresent()) {
                DistillerRecipe cookingRecipe = recipeHolder.get().value();
                if (cookingRecipe.matchesWithFluid(this.inputfluidTank.getFluid(),
                        inventoryWrapper, level)) {
                    return Optional.of(cookingRecipe);
                }
            }
        }

        if (checkNewRecipe) {
            List<RecipeHolder<DistillerRecipe>> recipes = level.getRecipeManager()
                    .getRecipesFor(RecipeTypeRegistry.DISTILLER_RECIPE_TYPE.get(), inventoryWrapper, level);
            for (RecipeHolder<DistillerRecipe> holder : recipes) {
                DistillerRecipe recipe = holder.value();
                if (recipe.matchesWithFluid(this.inputfluidTank.getFluid(), inventoryWrapper, level)) {
                    lastRecipeID = holder.id();
                    return Optional.of(recipe);
                }
            }
        }

        checkNewRecipe = false;
        return Optional.empty();
    }

    protected boolean canWork(DistillerRecipe recipe) {
        if (hasInput()) {
            NonNullList<ItemStack> resultStacks = recipe.getResultItemList();
            boolean fluid_flag = !(recipe.getResultFluid().isEmpty());
            FluidTank outTank = this.outputfluidTank;
            fluid_flag = outTank.isEmpty() || recipe.getResultFluid().isEmpty() ||
                    (FluidStack.isSameFluidSameComponents(outTank.getFluid(), recipe.getResultFluid())
                            && outTank.getSpace() >= recipe.getResultFluid().getAmount());
            boolean flag = true;
            for (int i = 3; i < resultStacks.size() + 3; i++) {
                if (!flag)
                    break;
                ItemStack resultStack = resultStacks.get(i - 3);
                ItemStack outputStack = inventory.getStackInSlot(i);
                if (outputStack.isEmpty()) {
                    flag = true;
                } else if (!ItemStack.isSameItem(outputStack, resultStack)) {
                    flag = false;
                } else if (outputStack.getCount() + resultStack.getCount() <= inventory.getSlotLimit(i)) {
                    flag = true;
                } else {
                    flag = outputStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
                }
            }
            return fluid_flag && flag;
        }
        return false;
    }

    private boolean processRecipe(DistillerRecipe recipe) {
        if (level == null) {
            return false;
        }

        ++recipeTime;
        recipeTimeTotal = recipe.getRecipeTime();
        if (recipeTime < recipeTimeTotal) {
            return false;
        }

        recipeTime = 0;

        NonNullList<ItemStack> resultStacks = recipe.getResultItemList();
        for (int i = 3; i < resultStacks.size() + 3; i++) {
            ItemStack outStack = inventory.getStackInSlot(i);
            if (outStack.isEmpty()) {
                inventory.setStackInSlot(i, resultStacks.get(i - 3).copy());
            } else if (ItemStack.isSameItem(outStack, resultStacks.get(i - 3))) {
                outStack.grow(resultStacks.get(i - 3).getCount());
            }
        }

        if (recipe.getRequiredFluid() != FluidIngredient.EMPTY)
            this.inputfluidTank.drain(recipe.getRequiredFluid().getRequiredAmount(),
                    FluidAction.EXECUTE);
        if (!recipe.getResultFluid().isEmpty())
            this.outputfluidTank.fill(recipe.getResultFluid(), FluidAction.EXECUTE);

        if (lastRecipeID != null) {
            trackRecipeExperience(lastRecipeID);
        }

        for (int i = 0; i < 3; ++i) {
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
                if (holder.value() instanceof DistillerRecipe recipe) {
                    LevelUtils.splitAndSpawnExperience(world, pos, entry.getIntValue(), recipe.getExperience());
                }
            });
        }
    }

    @Nonnull
    public IItemHandler getItemHandler(@Nullable Direction side) {
        return side == null || side.equals(Direction.UP) ? inputHandler : outputHandler;
    }

    @Nonnull
    public FluidTank getFluidHandler(@Nullable Direction side) {
        if (side == null) {
            return this.inputfluidTank;
        }

        BlockState state = getBlockState();
        if (state.getBlock() instanceof DistillerBlock) {
            Direction facing = state.getValue(DistillerBlock.FACING);
            if (side == facing || side == facing.getOpposite() || side == Direction.DOWN) {
                return this.outputfluidTank;
            }
        }

        return this.inputfluidTank;
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
        inputfluidTank.readFromNBT(registries, compound.getCompound("InputFluidTank"));
        outputfluidTank.readFromNBT(registries, compound.getCompound("OutputFluidTank"));
        CompoundTag compoundRecipes = compound.getCompound("RecipesUsed");
        for (String key : compoundRecipes.getAllKeys()) {
            experienceTracker.put(ResourceLocation.parse(key), compoundRecipes.getInt(key));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        CompoundTag nbt = new CompoundTag();
        compound.putInt("RecipeTime", recipeTime);
        compound.putInt("RecipeTimeTotal", recipeTimeTotal);
        compound.put("Inventory", inventory.serializeNBT(registries));
        compound.put("InputFluidTank", inputfluidTank.writeToNBT(registries, nbt));
        CompoundTag nbt2 = new CompoundTag();
        compound.put("OutputFluidTank", outputfluidTank.writeToNBT(registries, nbt2));
        CompoundTag compoundRecipes = new CompoundTag();
        experienceTracker
                .forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
        compound.put("RecipesUsed", compoundRecipes);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            @Override
            protected void onContentsChanged(int slot) {
                if (slot >= 0 && slot < 3) {
                    checkNewRecipe = true;
                }
                inventoryChanged();
            }
        };
    }

    private FluidTank createInputFluidHandler() {
        return new FluidTank(TANK_CAPACITY) {
            @Override
            protected void onContentsChanged() {
                inventoryChanged();
                checkNewRecipe = true;
                super.onContentsChanged();
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return !stack.getFluid().getFluidType().isLighterThanAir();
            }
        };
    }

    private FluidTank createFluidHandler() {
        return new FluidTank(TANK_CAPACITY) {
            @Override
            protected void onContentsChanged() {
                inventoryChanged();
                super.onContentsChanged();
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return !stack.getFluid().getFluidType().isLighterThanAir();
            }
        };
    }

    private ContainerData createIntArray() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return DistillerBlockEntity.this.recipeTime;
                    case 1:
                        return DistillerBlockEntity.this.recipeTimeTotal;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        DistillerBlockEntity.this.recipeTime = value;
                        break;
                    case 1:
                        DistillerBlockEntity.this.recipeTimeTotal = value;
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
        return new DistillerContainer(id, player, this, this.blockData);
    }

    public boolean isHeated() {
        if (level == null) {
            return false;
        }
        return this.isHeated(level, worldPosition);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tsuki.distiller");
    }

    public FluidTank getInputFluidTank() {
        return inputfluidTank;
    }

    public FluidTank getOutputFluidTank() {
        return outputfluidTank;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public int getRecipeTimeTotal() {
        return recipeTimeTotal;
    }

}
