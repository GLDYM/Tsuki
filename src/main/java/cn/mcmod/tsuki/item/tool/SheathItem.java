package cn.mcmod.tsuki.item.tool;

import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

public class SheathItem extends Item {
    private static final String TAG_SHEATH = "SheathItem";

    public SheathItem(Properties properties) {
        super(properties.durability(Tiers.WOOD.getUses()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack sheathStack = player.getItemInHand(hand);
        InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND
                : InteractionHand.MAIN_HAND;
        ItemStack otherStack = player.getItemInHand(otherHand);

        if (otherStack.getItem() instanceof KatanaItem) {
            if (!otherStack.is(ArmorToolRegistry.SAKURA_KATANA.get())
                    && !otherStack.is(ArmorToolRegistry.KATANA.get())) {
                player.displayClientMessage(Component.translatable("tsuki.katana.wrong_katana"), false);
                return InteractionResultHolder.fail(sheathStack);
            }
            if (!level.isClientSide()) {
                ItemStack sheathKatana = sheath(otherStack, sheathStack, player.registryAccess());

                otherStack.shrink(1);
                player.setItemInHand(hand, sheathKatana);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ARMOR_EQUIP_IRON, player.getSoundSource(), 1.0F, 1.2F);
            }
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(sheathStack);
    }

    public static ItemStack sheath(ItemStack katanaStack, ItemStack sheathStack,
            HolderLookup.Provider registries) {
        Item resultItem;
        if (katanaStack.is(ArmorToolRegistry.KATANA.get())) {
            resultItem = ArmorToolRegistry.KATANA_SHEATH.get();
        } else if (katanaStack.is(ArmorToolRegistry.SAKURA_KATANA.get())) {
            resultItem = ArmorToolRegistry.SAKURA_KATANA_SHEATH.get();
        } else {
            return ItemStack.EMPTY;
        }

        ItemStack sheathKatana = katanaStack.transmuteCopy(resultItem, 1);
        CompoundTag customTag = sheathKatana.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        customTag.put(TAG_SHEATH, sheathStack.copyWithCount(1).saveOptional(registries));
        sheathKatana.set(DataComponents.CUSTOM_DATA, CustomData.of(customTag));
        return sheathKatana;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
}
