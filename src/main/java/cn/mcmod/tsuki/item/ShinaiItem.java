package cn.mcmod.tsuki.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ShinaiItem extends SwordItem {
    public ShinaiItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, properties.attributes(SwordItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier)));
    }

    public ShinaiItem(Properties properties) {
        this(Tiers.WOOD, 2, -2.2F, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (level.isClientSide() || !(entity instanceof Player player)) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();
        boolean mainIsShinai = mainHand.getItem() instanceof ShinaiItem;
        boolean offIsShinai = offHand.getItem() instanceof ShinaiItem;

        if (mainIsShinai && !offHand.isEmpty()) {
            ItemStack dropped = offHand.copy();
            player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            player.drop(dropped, false);
            player.displayClientMessage(Component.translatable("tsuki.katana.wrong_duel_shinai"), false);
            return;
        }

        if (offIsShinai) {
            ItemStack dropped = offHand.copy();
            player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            player.drop(dropped, false);
            player.displayClientMessage(Component.translatable("tsuki.katana.wrong_duel_shinai"), false);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (!(entityLiving instanceof Player player) || level.isClientSide()) {
            return;
        }

        int ticksUsed = getUseDuration(stack, entityLiving) - timeLeft;
        if (ticksUsed < 3) {
            return;
        }

        float chargeRatio = ticksUsed / 15.0F;
        float damage = (chargeRatio * chargeRatio) + (chargeRatio * 2.0F);
        damage = Math.min(damage, 8.0F);
        if (damage < 0.5F) {
            return;
        }

        AABB aabb = player.getBoundingBox().inflate(3.0D, 0.75D, 3.0D);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, aabb,
                target -> target != player && !player.isAlliedTo(target) && player.distanceToSqr(target) < 16.0D);

        for (LivingEntity target : targets) {
            target.knockback(0.6F,
                    Math.sin(Math.toRadians(player.getYRot())),
                    -Math.cos(Math.toRadians(player.getYRot())));
            target.hurt(player.damageSources().playerAttack(player), damage);
        }

        if (!targets.isEmpty()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
            player.sweepAttack();
        }

        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }
}
