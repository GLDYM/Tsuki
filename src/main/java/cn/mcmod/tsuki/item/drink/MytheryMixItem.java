package cn.mcmod.tsuki.item.drink;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.MobEffectRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class MytheryMixItem extends DrinkItem {
    public MytheryMixItem() {
        super(
            Tsuki.defaultItemProperties().rarity(Rarity.EPIC),
            DrinkRegistry::cupBlock,
            DrinkRegistry::glassCupContainerItem,
            true,
            Component.translatable("item.tsuki.mythery_mix.tooltip").withStyle(ChatFormatting.GRAY),
            new MobEffectInstance(MobEffectRegistry.SEIRAN, 140, 0)
        );
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!target.isAlive()) {
            return InteractionResult.PASS;
        }
        if (target == player) {
            return InteractionResult.PASS;
        }
        if (!player.level().isClientSide) {
            DrinkEffectHelper.applyEffects(target, getEffects());
            if (isAlcoholic() && player.level().getRandom().nextFloat() < 0.7F) {
                DrinkEffectHelper.applyEffects(target, new MobEffectInstance[] {
                        new MobEffectInstance(MobEffects.CONFUSION, 200, 0)
                });
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                ItemStack containerStack = new ItemStack(getContainerItem().get());
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, containerStack);
                } else {
                    player.getInventory().add(containerStack);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
