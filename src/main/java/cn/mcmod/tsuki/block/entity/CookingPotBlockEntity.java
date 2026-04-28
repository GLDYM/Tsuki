package cn.mcmod.tsuki.block.entity;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cn.mcmod.mmlib.fluid.FluidIngredient;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.machines.CookingPotBlock;
import cn.mcmod.tsuki.compat.farmersdelight.FDCookingPotCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCCookingPotCompat;
import cn.mcmod.tsuki.container.CookingPotContainer;
import cn.mcmod.tsuki.inventory.CookingPotItemHandler;
import cn.mcmod.tsuki.recipes.CookingPotRecipe;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
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

public class CookingPotBlockEntity extends SyncedBlockEntity implements MenuProvider, HeatableBlockEntity {

    public static final int TANK_CAPACITY = 2000;
    public static final int SLOT_INPUT_START = 0;
    public static final int SLOT_INPUT_COUNT = 9;
    public static final int SLOT_MEAL_DISPLAY = 9;
    public static final int SLOT_CONTAINER_INPUT = 10;
    public static final int SLOT_OUTPUT = 11;
    public static final int SLOT_COUNT = 12;
    private final ItemStackHandler inventory;
    private final IItemHandler inputHandler;
    private final IItemHandler containerHandler;
    private final IItemHandler outputHandler;

    private final FluidTank fluidTank;
    protected final ContainerData blockData;
    private final Object2IntOpenHashMap<ResourceLocation> experienceTracker;

    private int recipeTime;
    private int recipeTimeTotal;
    private ItemStack mealContainer;

    private ResourceLocation lastRecipeID;
    private boolean checkNewRecipe;

    public CookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COOKING_POT.get(), pos, state);

        this.inventory = createHandler();
        this.inputHandler = new CookingPotItemHandler(inventory, Direction.UP);
        this.containerHandler = new CookingPotItemHandler(inventory, Direction.NORTH);
        this.outputHandler = new CookingPotItemHandler(inventory, Direction.DOWN);
        this.blockData = createIntArray();
        this.fluidTank = createFluidHandler();
        this.experienceTracker = new Object2IntOpenHashMap<>();
        this.checkNewRecipe = true;
        this.mealContainer = ItemStack.EMPTY;
    }

    public static void workingTick(Level level, BlockPos pos, BlockState state, CookingPotBlockEntity blockEntity) {
        boolean didInventoryChange = false;
        if (blockEntity.isHeated(level, pos) && blockEntity.hasInput()) {
            Optional<CookingPotRecipe> recipe = blockEntity.getMatchingRecipe(new RecipeWrapper(blockEntity.inventory));
            if (recipe.isPresent() && blockEntity.canWork(recipe.get(), level)) {
                didInventoryChange = blockEntity.processRecipe(recipe.get(), level);
            } else {
                blockEntity.recipeTime = 0;
                blockEntity.recipeTimeTotal = 0;
            }
        } else {
            if (state.is(BlockRegistry.COOKING_POT.get()))
                state.setValue(CookingPotBlock.OPEN, true);
            blockEntity.recipeTime = 0;
            blockEntity.recipeTimeTotal = 0;
        }

        if (blockEntity.moveMealToOutput()) {
            didInventoryChange = true;
        }

        if (didInventoryChange) {
            blockEntity.inventoryChanged();
        }
    }

    private boolean hasInput() {
        for (int i = SLOT_INPUT_START; i < SLOT_INPUT_START + SLOT_INPUT_COUNT; ++i) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Optional<CookingPotRecipe> getMatchingRecipe(RecipeWrapper inventoryWrapper) {
        if (level == null) {
            return Optional.empty();
        }

        if (lastRecipeID != null) {
            Optional<RecipeHolder<CookingPotRecipe>> recipeHolder = level.getRecipeManager()
                    .getAllRecipesFor(RecipeTypeRegistry.COOKING_RECIPE_TYPE.get()).stream()
                    .filter(now -> now.id().equals(lastRecipeID)).findFirst();
            if (recipeHolder.isPresent()) {
                CookingPotRecipe cookingRecipe = recipeHolder.get().value();
                if (cookingRecipe.matchesWithFluid(this.fluidTank.getFluid(), inventoryWrapper,
                        level)) {
                    return Optional.of(cookingRecipe);
                }
            }
        }

        if (checkNewRecipe) {
            List<RecipeHolder<CookingPotRecipe>> recipes = level.getRecipeManager().getRecipesFor(
                    RecipeTypeRegistry.COOKING_RECIPE_TYPE.get(),
                    inventoryWrapper, level);
            for (RecipeHolder<CookingPotRecipe> holder : recipes) {
                CookingPotRecipe recipe = holder.value();
                if (recipe.matchesWithFluid(this.fluidTank.getFluid(),
                        inventoryWrapper, level)) {
                    lastRecipeID = holder.id();
                    return Optional.of(recipe);
                }
            }

            Optional<CookingPotRecipe> fdCompatRecipe = FDCookingPotCompat.findMatching(level, inventoryWrapper,
                    this.fluidTank.getFluid());
            if (fdCompatRecipe.isPresent()) {
                // Compat recipe is generated at runtime and has no holder in this recipe
                // manager.
                lastRecipeID = null;
                return fdCompatRecipe;
            }

            Optional<CookingPotRecipe> kcCompatRecipe = KCCookingPotCompat.findMatching(level, inventoryWrapper,
                    this.fluidTank.getFluid());
            if (kcCompatRecipe.isPresent()) {
                // Compat recipe is generated at runtime and has no holder in this recipe
                // manager.
                lastRecipeID = null;
                return kcCompatRecipe;
            }
        }

        checkNewRecipe = false;
        return Optional.empty();
    }

    protected boolean canWork(CookingPotRecipe recipe, Level level) {
        if (hasInput()) {
            ItemStack resultStack = recipe.getResultItem(level.registryAccess());
            ItemStack requiredContainer = recipe.getContainer();
            if (resultStack.isEmpty()) {
                return false;
            } else {
                ItemStack outputStack = inventory.getStackInSlot(SLOT_MEAL_DISPLAY);
                if (outputStack.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(outputStack, resultStack)) {
                    return false;
                } else if (!ItemStack.isSameItemSameComponents(this.mealContainer, requiredContainer)) {
                    return false;
                } else if (outputStack.getCount() + resultStack.getCount() <= inventory
                        .getSlotLimit(SLOT_MEAL_DISPLAY)) {
                    return true;
                } else {
                    return outputStack.getCount() + resultStack.getCount() <= resultStack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private boolean processRecipe(CookingPotRecipe recipe, Level level) {
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
        ItemStack recipeContainer = recipe.getContainer();
        ItemStack outStack = inventory.getStackInSlot(SLOT_MEAL_DISPLAY);

        if (outStack.isEmpty()) {
            inventory.setStackInSlot(SLOT_MEAL_DISPLAY, resultStack.copy());
            this.mealContainer = recipeContainer.copy();
        } else if (ItemStack.isSameItem(outStack, resultStack)) {
            outStack.grow(resultStack.getCount());
        }
        if (recipe.getRequiredFluid() != FluidIngredient.EMPTY)
            this.fluidTank.drain(recipe.getRequiredFluid().getRequiredAmount(),
                    FluidAction.EXECUTE);

        if (lastRecipeID != null) {
            trackRecipeExperience(lastRecipeID);
        }

        for (int i = SLOT_INPUT_START; i < SLOT_INPUT_START + SLOT_INPUT_COUNT; ++i) {
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

    private boolean moveMealToOutput() {
        ItemStack mealStack = inventory.getStackInSlot(SLOT_MEAL_DISPLAY);
        if (mealStack.isEmpty()) {
            return false;
        }

        ItemStack requiredContainer = getServingContainerForMeal(mealStack);
        ItemStack containerStack = inventory.getStackInSlot(SLOT_CONTAINER_INPUT);
        if (!requiredContainer.isEmpty()) {
            if (containerStack.isEmpty() || !ItemStack.isSameItemSameComponents(containerStack, requiredContainer)) {
                return false;
            }
        }

        ItemStack outputStack = inventory.getStackInSlot(SLOT_OUTPUT);
        if (!outputStack.isEmpty() && !ItemStack.isSameItemSameComponents(outputStack, mealStack)) {
            return false;
        }

        int maxStackSize = Math.min(inventory.getSlotLimit(SLOT_OUTPUT), mealStack.getMaxStackSize());
        if (!outputStack.isEmpty() && outputStack.getCount() >= maxStackSize) {
            return false;
        }

        if (outputStack.isEmpty()) {
            ItemStack toOutput = mealStack.copy();
            toOutput.setCount(1);
            inventory.setStackInSlot(SLOT_OUTPUT, toOutput);
        } else {
            outputStack.grow(1);
        }

        mealStack.shrink(1);
        if (mealStack.isEmpty()) {
            this.mealContainer = ItemStack.EMPTY;
        }
        if (!requiredContainer.isEmpty()) {
            containerStack.shrink(1);
        }
        return true;
    }

    public boolean isServingContainer(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        ItemStack mealStack = inventory.getStackInSlot(SLOT_MEAL_DISPLAY);
        ItemStack requiredContainer = getServingContainerForMeal(mealStack);
        if (!requiredContainer.isEmpty()) {
            return ItemStack.isSameItemSameComponents(stack, requiredContainer);
        }
        return stack.is(Items.BOWL);
    }

    private ItemStack getServingContainerForMeal(ItemStack mealStack) {
        if (mealStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return mealContainer;
    }

    public ItemStack getCurrentMealContainer() {
        return this.mealContainer;
    }

    public boolean tryTakeMealWithContainer(Player player, InteractionHand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.isEmpty() || !isServingContainer(handStack)) {
            return false;
        }

        ItemStack mealStack = inventory.getStackInSlot(SLOT_MEAL_DISPLAY);
        if (mealStack.isEmpty()) {
            return false;
        }

        ItemStack requiredContainer = getServingContainerForMeal(mealStack);
        if (!requiredContainer.isEmpty() && !ItemStack.isSameItemSameComponents(handStack, requiredContainer)) {
            return false;
        }

        ItemStack servedMeal = mealStack.copy();
        servedMeal.setCount(1);
        if (!player.getInventory().add(servedMeal)) {
            player.drop(servedMeal, false);
        }

        if (!player.getAbilities().instabuild) {
            handStack.shrink(1);
        }

        mealStack.shrink(1);
        if (mealStack.isEmpty()) {
            this.mealContainer = ItemStack.EMPTY;
        }

        inventoryChanged();
        return true;
    }

    public boolean tryInsertHeldItem(Player player, InteractionHand hand) {
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.isEmpty()) {
            return false;
        }

        ItemStack single = handStack.copyWithCount(1);
        boolean inserted = false;

        if (isServingContainer(handStack)) {
            ItemStack remaining = inventory.insertItem(SLOT_CONTAINER_INPUT, single, false);
            inserted = remaining.isEmpty();
        } else {
            // TODO: Also for capability.
            for (int i = SLOT_INPUT_START; i < SLOT_INPUT_START + SLOT_INPUT_COUNT && !inserted; ++i) {
                if (!inventory.getStackInSlot(i).isEmpty()) {
                    continue;
                }
                ItemStack remaining = inventory.insertItem(i, single, false);
                inserted = remaining.isEmpty();
            }
            // TODO: Need a config.
            for (int i = SLOT_INPUT_START; i < SLOT_INPUT_START + SLOT_INPUT_COUNT && !inserted; ++i) {
                if (inventory.getStackInSlot(i).isEmpty()) {
                    continue;
                }
                ItemStack remaining = inventory.insertItem(i, single, false);
                inserted = remaining.isEmpty();
            }
        }

        if (!inserted) {
            return false;
        }

        if (!player.getAbilities().instabuild) {
            handStack.shrink(1);
        }
        player.setItemInHand(hand, handStack);
        inventoryChanged();
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
                if (holder.value() instanceof CookingPotRecipe recipe) {
                    LevelUtils.splitAndSpawnExperience(world, pos, entry.getIntValue(), recipe.getExperience());
                }
            });
        }
    }

    @Nonnull
    public IItemHandler getItemHandler(@Nullable Direction side) {
        if (side == null) {
            return inventory;
        }
        if (side == Direction.UP) {
            return inputHandler;
        }
        if (side == Direction.DOWN) {
            return outputHandler;
        }
        return containerHandler;
    }

    @Nonnull
    public FluidTank getFluidHandler(@Nullable Direction side) {
        return this.fluidTank;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        for (int i = 0; i < SLOT_COUNT; ++i) {
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
        this.mealContainer = ItemStack.EMPTY;
        if (compound.contains("MealContainer")) {
            this.mealContainer = ItemStack.parseOptional(registries, compound.getCompound("MealContainer"));
        }
        fluidTank.readFromNBT(registries, compound.getCompound("FluidTank"));
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
        if (!this.mealContainer.isEmpty()) {
            compound.put("MealContainer", this.mealContainer.saveOptional(registries));
        }
        compound.put("Inventory", inventory.serializeNBT(registries));
        compound.put("FluidTank", fluidTank.writeToNBT(registries, nbt));
        CompoundTag compoundRecipes = new CompoundTag();
        experienceTracker
                .forEach((recipeId, craftedAmount) -> compoundRecipes.putInt(recipeId.toString(), craftedAmount));
        compound.put("RecipesUsed", compoundRecipes);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(SLOT_COUNT) {
            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                if (slot == SLOT_MEAL_DISPLAY || slot == SLOT_OUTPUT) {
                    return false;
                }
                return true;
            }

            @Override
            protected void onContentsChanged(int slot) {
                if (slot >= SLOT_INPUT_START && slot < SLOT_INPUT_START + SLOT_INPUT_COUNT) {
                    checkNewRecipe = true;
                }
                inventoryChanged();
            }
        };
    }

    private FluidTank createFluidHandler() {
        return new FluidTank(TANK_CAPACITY) {
            @Override
            public void onContentsChanged() {
                checkNewRecipe = true;
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
                        return CookingPotBlockEntity.this.recipeTime;
                    case 1:
                        return CookingPotBlockEntity.this.recipeTimeTotal;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        CookingPotBlockEntity.this.recipeTime = value;
                        break;
                    case 1:
                        CookingPotBlockEntity.this.recipeTimeTotal = value;

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
        return new CookingPotContainer(id, player, this, this.blockData);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.tsuki.cooking_pot");
    }

    public boolean isHeated() {
        if (level == null) {
            return false;
        }
        return this.isHeated(level, worldPosition);
    }

    public FluidTank getFluidTank() {
        return fluidTank;
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public int getRecipeTimeTotal() {
        return recipeTimeTotal;
    }

    @Override
    public void inventoryChanged() {
        super.inventoryChanged();
    }

}
