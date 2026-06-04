package cn.mcmod.tsuki.block.drink;

import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiWineBottleSet;
import cn.mcmod.tsuki.item.drink.DrinkItem;
import cn.mcmod.tsuki.item.drink.WineBottleItem;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DrinkDisplayBlock extends AbstractDrinkDisplayBlock {
    public static final MapCodec<DrinkDisplayBlock> CODEC = simpleCodec(ignored -> new DrinkDisplayBlock());

    public DrinkDisplayBlock() {
        super(BlockBehaviour.Properties.of().noOcclusion().strength(0.2F));
    }

    @Override
    protected boolean canAccept(ItemStack stack) {
        return stack.is(TsukiItemTags.DRINK_CONTAINERS)
                || stack.getItem() instanceof DrinkItem
                || stack.getItem() instanceof WineBottleItem;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hit) {
        if (tryPourFromBottle(level, pos, state, player, hand, stack, hit)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }

    private boolean tryPourFromBottle(Level level, BlockPos pos, BlockState state, Player player, InteractionHand hand,
            ItemStack stack, BlockHitResult hit) {
        if (!(stack.getItem() instanceof WineBottleItem)) {
            return false;
        }
        // if (player != null && !player.isShiftKeyDown()) {
        // return false;
        // }
        if (!(level.getBlockEntity(pos) instanceof DrinkDisplayBlockEntity drinkDisplay)) {
            return false;
        }

        int slot = getSlotFromInteraction(player, hit, pos);
        ItemStack slotStack = drinkDisplay.getStackInSlot(slot);
        if (!slotStack.is(DrinkRegistry.GLASS_CUP.get())) {
            return false;
        }

        var filledDrink = TsukiWineBottleSet.alcoholItemFromBottle(stack.getItem());
        if (filledDrink == null) {
            return false;
        }
        if (level.isClientSide) {
            return true;
        }

        float rotation = drinkDisplay.getRotation(slot);
        if (!drinkDisplay.setItem(slot, new ItemStack(filledDrink.get()), rotation)) {
            return false;
        }

        if (player != null && !player.getAbilities().instabuild) {
            EquipmentSlot equipmentSlot = hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFFHAND
                    : EquipmentSlot.MAINHAND;
            stack.hurtAndBreak(1, player, equipmentSlot);
        }
        level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
        return true;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
