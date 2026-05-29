package cn.mcmod.tsuki.block.entity;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cn.mcmod.tsuki.block.machine.ChoppingBoardBlock;
import cn.mcmod.tsuki.compat.farmersdelight.FDChoppingBoardCompat;
import cn.mcmod.tsuki.compat.kaleidoscope.KCChoppingBoardCompat;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.recipe.ChoppingRecipe;
import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod.mmlib.util.LevelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class ChoppingBoardBlockEntity extends SyncedBlockEntity {
    private final ItemStackHandler inventory;
    private final IItemHandler inputHandler;
    private ResourceLocation lastRecipeID;

    private int recipeTime;
    private int recipeTimeTotal;

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CHOPPING_BOARD.get(), pos, state);
        inventory = createHandler();
        inputHandler = inventory;
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        inventory.deserializeNBT(registries, compound.getCompound("Inventory"));
        recipeTime = compound.getInt("RecipeTime");
        recipeTimeTotal = compound.getInt("RecipeTimeTotal");
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.put("Inventory", inventory.serializeNBT(registries));
        compound.putInt("RecipeTime", this.recipeTime);
        compound.putInt("RecipeTimeTotal", this.recipeTimeTotal);
    }

    public int getRecipeTime() {
        return recipeTime;
    }

    public boolean processStoredItemUsingTool(ItemStack toolStack, @Nullable Player player) {
        if (level == null)
            return false;

        Optional<ChoppingRecipe> matchingRecipe = getMatchingRecipe(new RecipeWrapper(inventory), toolStack, player);

        matchingRecipe.ifPresent(recipe -> {
            this.recipeTimeTotal = recipe.getRecipeTime();
            if (player != null) {
                toolStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            } else {
                if (toolStack.isDamageableItem()) {
                    toolStack.setDamageValue(toolStack.getDamageValue() + 1);
                }
                if (toolStack.getDamageValue() >= toolStack.getMaxDamage()) {
                    toolStack.setCount(0);
                }
            }
            playProcessingSound(toolStack, getStoredItem());
            if (this.recipeTime < recipeTimeTotal - 1) {
                this.recipeTime++;
            } else {
                finishRecipe(recipe);
            }
        });

        return matchingRecipe.isPresent();
    }

    private Optional<ChoppingRecipe> getMatchingRecipe(RecipeWrapper recipeWrapper, ItemStack toolStack,
            @Nullable Player player) {
        if (level == null)
            return Optional.empty();

        if (lastRecipeID != null) {
            Optional<RecipeHolder<ChoppingRecipe>> recipeHolder = level.getRecipeManager()
                    .getAllRecipesFor(RecipeTypeRegistry.CHOPPING_RECIPE_TYPE.get()).stream()
                    .filter(now -> now.id().equals(lastRecipeID)).findFirst();
            if (recipeHolder.isPresent()) {
                ChoppingRecipe recipe = recipeHolder.get().value();
                if (recipe.matches(recipeWrapper, level) && recipe.getTool().test(toolStack)) {
                    return Optional.of(recipe);
                }
            }
        }

        List<RecipeHolder<ChoppingRecipe>> recipeList = level.getRecipeManager()
                .getRecipesFor(RecipeTypeRegistry.CHOPPING_RECIPE_TYPE.get(), recipeWrapper, level);
        Optional<RecipeHolder<ChoppingRecipe>> recipe = recipeList.stream()
                .filter(holder -> holder.value().getTool().test(toolStack)).findFirst();
        if (recipe.isPresent()) {
            lastRecipeID = recipe.get().id();
            return Optional.of(recipe.get().value());
        }

        Optional<ChoppingRecipe> fdCompatRecipe = FDChoppingBoardCompat.findMatching(level, recipeWrapper,
                toolStack);
        if (fdCompatRecipe.isPresent()) {
            lastRecipeID = null;
            return fdCompatRecipe;
        }

        Optional<ChoppingRecipe> kcCompatRecipe = KCChoppingBoardCompat.findMatching(level, recipeWrapper.getItem(0),
                toolStack);
        if (kcCompatRecipe.isPresent()) {
            lastRecipeID = null;
            return kcCompatRecipe;
        }

        if (player != null) {
            Component message = recipeList.isEmpty()
                    ? Component.translatable("tsuki.block.chopping_board.invalid_item")
                    : Component.translatable("tsuki.block.chopping_board.invalid_tool");
            player.displayClientMessage(message, true);
        }
        return Optional.empty();
    }

    public void playProcessingSound(ItemStack tool, ItemStack boardItem) {
        if (tool.is(Tags.Items.TOOLS_SHEAR)) {
            playSound(SoundEvents.SHEEP_SHEAR, 1.0F, 1.0F);
        } else if (boardItem.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            BlockState blockState = block.defaultBlockState();
            SoundType soundType = block.getSoundType(blockState, level, worldPosition, null);
            playSound(soundType.getBreakSound(), 1.0F, 0.8F);
        } else {
            playSound(SoundEvents.WOOD_HIT, 1.0F, 0.8F);
        }
    }

    public void playSound(SoundEvent sound, float volume, float pitch) {
        if (level != null)
            level.playSound(null, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F,
                    sound, SoundSource.BLOCKS, volume, pitch);
    }

    public boolean addItem(ItemStack itemStack) {
        if (isEmpty() && !itemStack.isEmpty()) {
            inventory.setStackInSlot(0, itemStack.split(1));
            inventoryChanged();
            return true;
        }
        return false;
    }

    private void finishRecipe(ChoppingRecipe recipe) {
        if (level == null) {
            return;
        }

        ItemStack resultItem = recipe.getResultItem(level.registryAccess()).copy();
        if (!resultItem.isEmpty()) {
            spawnResultStack(resultItem);
        }

        List<ItemStack> byproducts = recipe.rollByproducts(level.random, 0);
        for (ItemStack byproduct : byproducts) {
            spawnResultStack(byproduct.copy());
        }

        removeItem();
    }

    private void spawnResultStack(ItemStack stack) {
        if (level == null || stack.isEmpty()) {
            return;
        }

        Direction direction = getBlockState().getValue(ChoppingBoardBlock.FACING).getCounterClockWise();
        LevelUtil.spawnItemEntity(level, stack, worldPosition.getX() + 0.5 + (direction.getStepX() * 0.2),
                worldPosition.getY() + 0.2, worldPosition.getZ() + 0.5 + (direction.getStepZ() * 0.2),
                direction.getStepX() * 0.2F, 0.0F, direction.getStepZ() * 0.2F);
    }

    public ItemStack removeItem() {
        if (!isEmpty()) {
            ItemStack item = getStoredItem().split(1);
            inventoryChanged();
            return item;
        }
        return ItemStack.EMPTY;
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    public ItemStack getStoredItem() {
        return inventory.getStackInSlot(0);
    }

    public boolean isEmpty() {
        return inventory.getStackInSlot(0).isEmpty();
    }

    @Nonnull
    public IItemHandler getItemHandler(@Nullable Direction side) {
        return inputHandler;
    }

    @Override
    protected void inventoryChanged() {
        this.recipeTime = 0;
        super.inventoryChanged();
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler() {
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
