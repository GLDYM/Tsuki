package cn.mcmod.tsuki.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.server.level.ServerLevel;

import java.util.List;
import java.util.function.Supplier;

public class SheathKatanaItem extends Item {
    private static final String TAG_BLADE = "SheathBlade";
    private static final String TAG_SHEATH = "SheathItem";

    private final Supplier<Item> katanaSupplier;
    private final Supplier<Item> sheathSupplier;

    public SheathKatanaItem(Properties properties, Supplier<Item> katanaSupplier, Supplier<Item> sheathSupplier) {
        super(properties);
        this.katanaSupplier = katanaSupplier;
        this.sheathSupplier = sheathSupplier;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tsuki.tooltip.sheath_katana")
            .withStyle(ChatFormatting.GRAY)
            .withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) {
            return InteractionResultHolder.consume(stack);
        }
        if (!canUnsheathe(player, hand)) {
            player.displayClientMessage(Component.translatable("tsuki.katana.sheath.not_empty_hand"), false);
            return InteractionResultHolder.fail(stack);
        }

        unsheatheAndSweep(stack, player, hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 0;
    }

    private boolean canUnsheathe(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            return true;
        }
        return player.getMainHandItem().isEmpty();
    }

    private void unsheatheAndSweep(ItemStack sheathKatanaStack, Player player, InteractionHand hand) {
        CompoundTag tag = sheathKatanaStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();

        ItemStack blade;
        if (tag.contains(TAG_BLADE)) {
            blade = ItemStack.parseOptional(player.registryAccess(), tag.getCompound(TAG_BLADE));
        } else {
            blade = sheathKatanaStack.transmuteCopy(katanaSupplier.get(), 1);
        }

        ItemStack sheath = tag.contains(TAG_SHEATH)
                ? ItemStack.parseOptional(player.registryAccess(), tag.getCompound(TAG_SHEATH))
                : new ItemStack(sheathSupplier.get());
        if (sheath.isEmpty()) {
            sheath = new ItemStack(sheathSupplier.get());
        }

        CompoundTag bladeTag = blade.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (bladeTag.contains(TAG_SHEATH)) {
            bladeTag.remove(TAG_SHEATH);
            if (bladeTag.isEmpty()) {
                blade.remove(DataComponents.CUSTOM_DATA);
            } else {
                blade.set(DataComponents.CUSTOM_DATA, CustomData.of(bladeTag));
            }
        }

        // Unsheathe hand behavior:
        // - Main hand sheath-katana: blade to main hand; sheath to offhand or
        // inventory.
        // - Offhand sheath-katana: only trigger when main hand is empty; blade to main
        // hand and sheath stays offhand.
        if (hand == InteractionHand.MAIN_HAND) {
            player.setItemInHand(InteractionHand.MAIN_HAND, blade);
            if (player.getOffhandItem().isEmpty()) {
                player.setItemInHand(InteractionHand.OFF_HAND, sheath);
            } else if (!player.getInventory().add(sheath)) {
                player.drop(sheath, false);
            }
        } else {
            player.setItemInHand(InteractionHand.MAIN_HAND, blade);
            player.setItemInHand(InteractionHand.OFF_HAND, sheath);
        }

        executeUnsheatheSweep(player, blade);

        ItemStack bladeInMainHand = player.getMainHandItem();
        if (bladeInMainHand.getItem() instanceof KatanaItem) {
            bladeInMainHand.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ARMOR_EQUIP_IRON, player.getSoundSource(), 1.0F, 0.8F);
    }

    private void executeUnsheatheSweep(Player player, ItemStack blade) {
        Level level = player.level();
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE)
            + blade.getDamageValue() * 1.2F;
        AABB aabb = player.getBoundingBox().inflate(2.5D, 0.5D, 2.5D);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, aabb,
                target -> target != player && !player.isAlliedTo(target) && player.distanceToSqr(target) < 10.0D);
        // ItemStack blade = player.getMainHandItem();

        for (LivingEntity target : targets) {
            float enchantDamage = EnchantmentHelper.modifyDamage(serverLevel, blade, target,
                    player.damageSources().playerAttack(player), 0.0F);
            float sweepDamage = baseDamage + enchantDamage;
            if (target instanceof Player targetPlayer && targetPlayer.isBlocking()) {
                targetPlayer.disableShield();
            }
            target.knockback(0.5F,
                    Math.sin(Math.toRadians(player.getYRot())),
                    -Math.cos(Math.toRadians(player.getYRot())));
            target.hurt(player.damageSources().playerAttack(player), sweepDamage);
        }

        if (!targets.isEmpty()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
            player.sweepAttack();
        }
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
        return true;
    }
}
