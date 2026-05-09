package cn.mcmod.tsuki.item.drink;

import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.recipe.ShakerRecipe;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class ShakerItem extends BlockItem {
    public ShakerItem(Block block, Item.Properties properties) {
        super(block, properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            return super.useOn(context);
        }

        if (!(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof DrinkDisplayBlockEntity display)) {
            return InteractionResult.PASS;
        }

        if (serveToDisplay(context, display)) {
            return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            return InteractionResultHolder.pass(stack);
        }
        if (!level.isClientSide) {
            ValidationResult validationResult = validateShakeInputs(stack, player);
            if (!validationResult.valid()) {
                player.displayClientMessage(validationResult.message(), true);
                return InteractionResultHolder.fail(stack);
            }
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!(entity instanceof Player player) || level.isClientSide) {
            return stack;
        }

        ItemStackHandler inventory = ShakerDataHelper.createInventory();
        ShakerDataHelper.load(stack, inventory, player.registryAccess());

        RecipeWrapper recipeWrapper = new RecipeWrapper(inventory);
        int alcoholCount = countAlcoholInputs(inventory);
        int alcoholKinds = countDistinctAlcoholInputs(inventory);
        boolean hasNonAlcoholIngredient = hasNonAlcoholIngredient(inventory);
        if (alcoholCount <= 0) {
            player.displayClientMessage(Component.translatable("item.tsuki.shaker.no_alcohol"), true);
            ShakerDataHelper.save(stack, inventory, 0, "", false, player.registryAccess());
            return stack;
        }
        if (!hasNonAlcoholIngredient && alcoholKinds <= 1) {
            player.displayClientMessage(Component.translatable("item.tsuki.shaker.not_enough_ingredients"), true);
            ShakerDataHelper.save(stack, inventory, 0, "", false, player.registryAccess());
            return stack;
        }

        int shakeProgress = ShakerDataHelper.loadShakeProgress(stack);
        LockedTarget lockedTarget = resolveLockedTarget(level, recipeWrapper, inventory, stack, alcoholCount);
        if (lockedTarget.recipe().isPresent()) {
            SelectedRecipe selected = lockedTarget.recipe().get();
            shakeProgress++;
            if (shakeProgress >= selected.recipe().getShakeCount()) {
                craftRecipe(inventory, selected.recipe(), selected.matchedSlots(), alcoholCount, level);
                shakeProgress = 0;
                lockedTarget = LockedTarget.none();
                level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS,
                        0.4F, 1.0F);
            }
        } else if (lockedTarget.mysteryFallback()) {
            shakeProgress++;
            if (shakeProgress >= 12) {
                craftMysteryMix(inventory, alcoholCount);
                shakeProgress = 0;
                lockedTarget = LockedTarget.none();
                level.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS,
                        0.4F, 1.0F);
            }
        } else {
            shakeProgress = 0;
        }

        ShakerDataHelper.save(stack, inventory, shakeProgress, lockedTarget.recipeId(), lockedTarget.mysteryFallback(),
                player.registryAccess());
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 20;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        ItemStackHandler inventory = ShakerDataHelper.createInventory();
        ShakerDataHelper.load(stack, inventory, context.registries());

        Map<ItemStackKey, Integer> mergedInputs = new LinkedHashMap<>();
        for (int slot = ShakerDataHelper.SLOT_INPUT_START;
                slot < ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT;
                ++slot) {
            ItemStack slotStack = inventory.getStackInSlot(slot);
            if (!slotStack.isEmpty()) {
                mergedInputs.merge(new ItemStackKey(slotStack), slotStack.getCount(), Integer::sum);
            }
        }

        mergedInputs.forEach((key, count) ->
                tooltip.add(Component.literal("- " + count + "x ").append(key.displayName())));

        ItemStack output = inventory.getStackInSlot(ShakerDataHelper.SLOT_OUTPUT);
        if (!output.isEmpty()) {
            tooltip.add(Component.translatable("item.tsuki.shaker.tooltip.output")
                    .append(Component.literal(output.getCount() + "x "))
                    .append(output.getHoverName()));
        }
    }

    private boolean serveToDisplay(UseOnContext context, DrinkDisplayBlockEntity display) {
        int slot = getDisplaySlot(context, context.getClickedPos());
        if (slot < 0) {
            return false;
        }
        ItemStack displayStack = display.getStackInSlot(slot);
        if (!displayStack.is(DrinkRegistry.GLASS_CUP.get())) {
            return false;
        }

        ItemStack heldShaker = context.getItemInHand();
        ItemStackHandler inventory = ShakerDataHelper.createInventory();
        ShakerDataHelper.load(heldShaker, inventory, context.getPlayer().registryAccess());
        ItemStack outputStack = inventory.getStackInSlot(ShakerDataHelper.SLOT_OUTPUT);
        if (outputStack.isEmpty()) {
            return false;
        }
        ItemStack requiredContainer = ShakerDataHelper.getRequiredContainer(outputStack);
        if (requiredContainer.isEmpty() || !ItemStack.isSameItemSameComponents(displayStack, requiredContainer)) {
            return false;
        }
        if (context.getLevel().isClientSide) {
            return true;
        }

        if (!display.setItem(slot, outputStack.copyWithCount(1), display.getRotation(slot))) {
            return false;
        }
        outputStack.shrink(1);
        if (outputStack.isEmpty()) {
            inventory.setStackInSlot(ShakerDataHelper.SLOT_OUTPUT, ItemStack.EMPTY);
        }
        ShakerDataHelper.save(heldShaker, inventory, 0, context.getPlayer().registryAccess());
        context.getLevel().sendBlockUpdated(context.getClickedPos(), context.getLevel().getBlockState(context.getClickedPos()),
                context.getLevel().getBlockState(context.getClickedPos()), Block.UPDATE_CLIENTS);
        return true;
    }

    private boolean canOutput(ItemStackHandler inventory, ItemStack result) {
        if (result.isEmpty()) {
            return false;
        }
        ItemStack output = inventory.getStackInSlot(ShakerDataHelper.SLOT_OUTPUT);
        if (output.isEmpty()) {
            return true;
        }
        if (!ItemStack.isSameItemSameComponents(output, result)) {
            return false;
        }
        return output.getCount() + result.getCount() <= Math.min(output.getMaxStackSize(),
                inventory.getSlotLimit(ShakerDataHelper.SLOT_OUTPUT));
    }

    private ValidationResult validateShakeInputs(ItemStack shakerStack, Player player) {
        ItemStackHandler inventory = ShakerDataHelper.createInventory();
        ShakerDataHelper.load(shakerStack, inventory, player.registryAccess());
        if (countAlcoholInputs(inventory) <= 0) {
            return new ValidationResult(false, Component.translatable("item.tsuki.shaker.no_alcohol"));
        }
        if (!hasNonAlcoholIngredient(inventory) && countDistinctAlcoholInputs(inventory) <= 1) {
            return new ValidationResult(false, Component.translatable("item.tsuki.shaker.not_enough_ingredients"));
        }
        return new ValidationResult(true, Component.empty());
    }

    private boolean hasNonAlcoholIngredient(ItemStackHandler inventory) {
        for (int slot = ShakerDataHelper.SLOT_INPUT_START;
                slot < ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT;
                ++slot) {
            ItemStack slotStack = inventory.getStackInSlot(slot);
            if (!slotStack.isEmpty() && !isAlcoholInput(slotStack)) {
                return true;
            }
        }
        return false;
    }

    private int countDistinctAlcoholInputs(ItemStackHandler inventory) {
        Map<ItemStackKey, Boolean> distinctAlcohols = new LinkedHashMap<>();
        for (int slot = ShakerDataHelper.SLOT_INPUT_START;
                slot < ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT;
                ++slot) {
            ItemStack slotStack = inventory.getStackInSlot(slot);
            if (isAlcoholInput(slotStack)) {
                distinctAlcohols.putIfAbsent(new ItemStackKey(slotStack), Boolean.TRUE);
            }
        }
        return distinctAlcohols.size();
    }

    private int countAlcoholInputs(ItemStackHandler inventory) {
        int alcoholCount = 0;
        for (int slot = ShakerDataHelper.SLOT_INPUT_START;
                slot < ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT;
                ++slot) {
            ItemStack slotStack = inventory.getStackInSlot(slot);
            if (isAlcoholInput(slotStack)) {
                alcoholCount += slotStack.getCount();
            }
        }
        return alcoholCount;
    }

    private boolean isAlcoholInput(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getItem() instanceof DrinkItem drinkItem) {
            return drinkItem.isAlcoholic();
        }
        return stack.getItem() instanceof WineBottleItem;
    }

    private Optional<SelectedRecipe> selectRecipe(Level level, RecipeWrapper recipeWrapper, ItemStackHandler inventory) {
        List<net.minecraft.world.item.crafting.RecipeHolder<ShakerRecipe>> matches = level.getRecipeManager()
                .getRecipesFor(RecipeTypeRegistry.SHAKER_RECIPE_TYPE.get(), recipeWrapper, level);

        SelectedRecipe best = null;
        for (var holder : matches) {
            ShakerRecipe recipe = holder.value();
            int[] slots = recipe.findMatchingSlots(recipeWrapper);
            if (slots == null || !canOutput(inventory, recipe.getResultItem(level.registryAccess()))) {
                continue;
            }
            SelectedRecipe candidate = new SelectedRecipe(recipe, slots);
            if (best == null || candidate.ingredientCount() > best.ingredientCount()) {
                best = candidate;
            }
        }
        return Optional.ofNullable(best);
    }

    private LockedTarget resolveLockedTarget(Level level, RecipeWrapper recipeWrapper, ItemStackHandler inventory,
            ItemStack shakerStack, int alcoholCount) {
        String lockedRecipeId = ShakerDataHelper.loadLockedRecipe(shakerStack);
        boolean mysteryFallback = ShakerDataHelper.loadMysteryFallback(shakerStack);

        if (!lockedRecipeId.isEmpty()) {
            Optional<SelectedRecipe> lockedRecipe = selectRecipe(level, recipeWrapper, inventory)
                    .filter(selected -> lockedRecipeId.equals(selected.recipeId()));
            if (lockedRecipe.isPresent() && canOutput(inventory,
                    lockedRecipe.get().recipe().getResultItem(level.registryAccess()).copyWithCount(alcoholCount))) {
                return LockedTarget.recipe(lockedRecipe.get());
            }
        }

        if (mysteryFallback && canOutput(inventory, new ItemStack(DrinkRegistry.MYTHERY_MIX.get(), alcoholCount))) {
            return LockedTarget.mystery();
        }

        Optional<SelectedRecipe> selectedRecipe = selectRecipe(level, recipeWrapper, inventory);
        if (selectedRecipe.isPresent()) {
            SelectedRecipe selected = selectedRecipe.get();
            return LockedTarget.recipe(selected);
        }

        if (canOutput(inventory, new ItemStack(DrinkRegistry.MYTHERY_MIX.get(), alcoholCount))) {
            return LockedTarget.mystery();
        }
        return LockedTarget.none();
    }

    private void craftRecipe(ItemStackHandler inventory, ShakerRecipe recipe, int[] slots, int alcoholCount, Level level) {
        ItemStack result = recipe.getResultItem(level.registryAccess()).copyWithCount(alcoholCount);
        ItemStack output = inventory.getStackInSlot(ShakerDataHelper.SLOT_OUTPUT);
        if (output.isEmpty()) {
            inventory.setStackInSlot(ShakerDataHelper.SLOT_OUTPUT, result);
        } else {
            output.grow(result.getCount());
        }

        for (int slot : slots) {
            inventory.extractItem(slot, 1, false);
        }
    }

    private void craftMysteryMix(ItemStackHandler inventory, int alcoholCount) {
        ItemStack result = new ItemStack(DrinkRegistry.MYTHERY_MIX.get(), alcoholCount);
        ItemStack output = inventory.getStackInSlot(ShakerDataHelper.SLOT_OUTPUT);
        if (output.isEmpty()) {
            inventory.setStackInSlot(ShakerDataHelper.SLOT_OUTPUT, result);
        } else if (ItemStack.isSameItemSameComponents(output, result)) {
            output.grow(result.getCount());
        }

        for (int slot = ShakerDataHelper.SLOT_INPUT_START;
                slot < ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT;
                ++slot) {
            inventory.setStackInSlot(slot, ItemStack.EMPTY);
        }
    }

    private int getDisplaySlot(UseOnContext context, BlockPos pos) {
        if (context.getPlayer() == null) {
            return 0;
        }
        Vec3 projected = DrinkPlacementHelper.projectToPlane(context.getPlayer().getEyePosition(),
                context.getClickLocation(), pos.getY());
        double x = projected.x - (pos.getX() + 0.5D);
        double z = projected.z - (pos.getZ() + 0.5D);
        boolean west = x < 0.0D;
        boolean north = z < 0.0D;
        if (north) {
            return west ? 0 : 1;
        }
        return west ? 2 : 3;
    }

    private static final class ItemStackKey {
        private final ItemStack stack;

        private ItemStackKey(ItemStack stack) {
            this.stack = stack.copyWithCount(1);
        }

        private Component displayName() {
            return stack.getHoverName();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ItemStackKey other)) {
                return false;
            }
            return ItemStack.isSameItemSameComponents(this.stack, other.stack);
        }

        @Override
        public int hashCode() {
            return ItemStack.hashItemAndComponents(this.stack);
        }
    }

    private record SelectedRecipe(ShakerRecipe recipe, int[] matchedSlots) {
        private int ingredientCount() {
            return matchedSlots.length;
        }

        private String recipeId() {
            return recipe.getId().toString();
        }
    }

    private record LockedTarget(Optional<SelectedRecipe> recipe, String recipeId, boolean mysteryFallback) {
        private static LockedTarget none() {
            return new LockedTarget(Optional.empty(), "", false);
        }

        private static LockedTarget recipe(SelectedRecipe recipe) {
            return new LockedTarget(Optional.of(recipe), recipe.recipeId(), false);
        }

        private static LockedTarget mystery() {
            return new LockedTarget(Optional.empty(), "", true);
        }
    }

    private record ValidationResult(boolean valid, Component message) {
    }
}
