package cn.mcmod.tsuki.block.machine;

import cn.mcmod.tsuki.block.entity.ShakerBlockEntity;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet;
import cn.mcmod.tsuki.item.drink.DrinkItem;
import cn.mcmod.tsuki.item.drink.ShakerDataHelper;
import cn.mcmod.tsuki.item.drink.WineBottleItem;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import com.mojang.serialization.MapCodec;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShakerBlock extends BaseEntityBlock {
    public static final MapCodec<ShakerBlock> CODEC = simpleCodec(ShakerBlock::new);
    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public ShakerBlock(Properties properties) {
        super(properties);
    }

    public ShakerBlock() {
        this(BlockBehaviour.Properties.of().strength(0.5F, 6.0F).sound(SoundType.LANTERN).noOcclusion());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistry.SHAKER.get().create(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof ShakerBlockEntity shaker)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (!stack.isEmpty()) {
            if (takeOutputWithContainer(level, pos, player, hand, shaker)) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            if (takeInputWithContainer(level, pos, player, hand, shaker)) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        if (!shaker.getOutputStack().isEmpty() && !stack.isEmpty() && !stack.is(DrinkRegistry.SHAKER.get())) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.translatable("item.tsuki.shaker.output_not_empty"), true);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        if (stack.isEmpty() || stack.is(DrinkRegistry.SHAKER.get())) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if (insertIngredient(level, pos, player, hand, shaker)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof ShakerBlockEntity shaker)) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        int slot = findLastLooseInputSlot(shaker);
        if (slot >= 0 && takeOne(shaker, player, slot, false)) {
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.3F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
            ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof ShakerBlockEntity shaker) {
            shaker.loadFromItemStack(stack, level.registryAccess());
        }
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (!player.isShiftKeyDown() || level.isClientSide) {
            super.attack(state, level, pos, player);
            return;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof ShakerBlockEntity shaker)) {
            super.attack(state, level, pos, player);
            return;
        }

        ItemStack drop = shaker.toItemStack(level.registryAccess());
        level.removeBlockEntity(pos);
        level.removeBlock(pos, false);
        if (!player.getInventory().add(drop)) {
            Block.popResource(level, pos, drop);
        }
        level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_CHAIN.value(), SoundSource.BLOCKS, 0.4F, 1.2F);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ShakerBlockEntity shaker) {
                Block.popResource(level, pos, shaker.toItemStack(level.registryAccess()));
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    private boolean insertIngredient(Level level, BlockPos pos, Player player, InteractionHand hand,
            ShakerBlockEntity shaker) {
        ItemStack heldStack = player.getItemInHand(hand);
        int emptySlot = shaker.findFirstEmptyInputSlot();
        if (emptySlot < 0 || heldStack.isEmpty() || heldStack.is(TsukiItemTags.INGREDIENT_BLACKLIST)) {
            return false;
        }

        ItemStack storedIngredient;
        ItemStack returnedContainer = ItemStack.EMPTY;

        if (heldStack.getItem() instanceof WineBottleItem) {
            var filledDrink = TsukiWineBottleSet.alcoholItemFromBottle(heldStack.getItem());
            if (filledDrink == null) {
                return false;
            }
            storedIngredient = new ItemStack(filledDrink.get());
        } else {
            storedIngredient = heldStack.copyWithCount(1);
            if (heldStack.getItem() instanceof DrinkItem drinkItem) {
                returnedContainer = new ItemStack(drinkItem.getContainerItem().get());
            } else if (heldStack.hasCraftingRemainingItem()) {
                returnedContainer = heldStack.getCraftingRemainingItem();
            }
        }

        shaker.getInventory().setStackInSlot(emptySlot, storedIngredient);
        shaker.setShakeProgress(0);

        if (!player.getAbilities().instabuild) {
            if (heldStack.getItem() instanceof WineBottleItem) {
                player.setItemInHand(hand, heldStack.getCraftingRemainingItem());
            } else {
                heldStack.shrink(1);
                if (!returnedContainer.isEmpty()) {
                    if (heldStack.isEmpty()) {
                        player.setItemInHand(hand, returnedContainer);
                    } else if (!player.getInventory().add(returnedContainer)) {
                        Block.popResource(level, pos, returnedContainer);
                    }
                }
            }
        }

        level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 0.6F, 1.0F);
        return true;
    }

    private boolean takeOne(ShakerBlockEntity shaker, Player player, int slot, boolean resetProgress) {
        ItemStack extracted = shaker.takeOne(slot);
        if (extracted.isEmpty()) {
            return false;
        }
        if (!player.getInventory().add(extracted)) {
            player.drop(extracted, false);
        }
        if (resetProgress) {
            shaker.clearRecipeProgress();
        }
        return true;
    }

    private boolean takeOutputWithContainer(Level level, BlockPos pos, Player player, InteractionHand hand,
            ShakerBlockEntity shaker) {
        return takeFilledItemWithContainer(level, pos, player, hand, shaker, ShakerDataHelper.SLOT_OUTPUT);
    }

    private boolean takeInputWithContainer(Level level, BlockPos pos, Player player, InteractionHand hand,
            ShakerBlockEntity shaker) {
        for (int slot = ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT - 1;
                slot >= ShakerDataHelper.SLOT_INPUT_START;
                --slot) {
            if (takeFilledItemWithContainer(level, pos, player, hand, shaker, slot)) {
                return true;
            }
        }
        return false;
    }

    private boolean takeFilledItemWithContainer(Level level, BlockPos pos, Player player, InteractionHand hand,
            ShakerBlockEntity shaker, int slot) {
        ItemStack filledStack = shaker.getInventory().getStackInSlot(slot);
        if (filledStack.isEmpty()) {
            return false;
        }

        ItemStack requiredContainer = ShakerDataHelper.getRequiredContainer(filledStack);
        if (requiredContainer.isEmpty()) {
            return false;
        }

        ItemStack heldStack = player.getItemInHand(hand);
        if (!ItemStack.isSameItemSameComponents(heldStack, requiredContainer) || heldStack.getCount() <= 0) {
            return false;
        }
        if (level.isClientSide) {
            return true;
        }

        ItemStack extracted = shaker.takeOne(slot);
        if (extracted.isEmpty()) {
            return false;
        }
        giveFilledByReplacingContainer(player, hand, extracted);
        shaker.clearRecipeProgress();
        level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.3F, 1.0F);
        return true;
    }

    private void giveFilledByReplacingContainer(Player player, InteractionHand hand, ItemStack filledStack) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (player.getAbilities().instabuild) {
            if (!player.getInventory().add(filledStack)) {
                player.drop(filledStack, false);
            }
            return;
        }

        if (heldStack.getCount() == 1) {
            player.setItemInHand(hand, filledStack);
            return;
        }

        heldStack.shrink(1);
        if (!player.getInventory().add(filledStack)) {
            player.drop(filledStack, false);
        }
    }

    private int findLastLooseInputSlot(ShakerBlockEntity shaker) {
        for (int slot = ShakerDataHelper.SLOT_INPUT_START + ShakerDataHelper.SLOT_INPUT_COUNT - 1;
                slot >= ShakerDataHelper.SLOT_INPUT_START;
                --slot) {
            ItemStack stack = shaker.getInventory().getStackInSlot(slot);
            if (!stack.isEmpty() && ShakerDataHelper.getRequiredContainer(stack).isEmpty()) {
                return slot;
            }
        }
        return -1;
    }
}
