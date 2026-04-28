package cn.mcmod.tsuki.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import java.util.List;
import java.util.function.Supplier;

public class WineBottleItem extends Item {
    public static final int MAX_SIP_DAMAGE = 5;

    private final MobEffectInstance[] effects;
    private final boolean alcoholic;
    private final Supplier<Item> containerItem;
    private final Supplier<Fluid> fluidSupplier;
    private final Component toolTip;

    public WineBottleItem(
            Properties properties,
            Supplier<Item> containerItem,
            Supplier<Fluid> fluidSupplier,
            boolean alcoholic,
            MobEffectInstance... effects) {
        this(properties, containerItem, fluidSupplier, alcoholic, null, effects);
    }

    public WineBottleItem(
            Properties properties,
            Supplier<Item> containerItem,
            Supplier<Fluid> fluidSupplier,
            boolean alcoholic,
            Component toolTip,
            MobEffectInstance... effects) {
        super(properties.stacksTo(1).durability(MAX_SIP_DAMAGE));
        this.effects = effects;
        this.alcoholic = alcoholic;
        this.containerItem = containerItem;
        this.fluidSupplier = fluidSupplier;
        this.toolTip = toolTip;
    }

    public Fluid getFluid() {
        return fluidSupplier.get();
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            for (MobEffectInstance effect : effects) {
                entity.addEffect(new MobEffectInstance(effect));
            }
            if (alcoholic && level.getRandom().nextFloat() < 0.7F) {
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            }
        }

        if (entity instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                int newDamage = stack.getDamageValue() + 1;
                if (newDamage >= stack.getMaxDamage()) {
                    return new ItemStack(containerItem.get());
                }
                stack.setDamageValue(newDamage);
            }
        }
        return stack;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        if (!copy.isDamageableItem()) {
            return copy;
        }
        int nextDamage = copy.getDamageValue() + 1;
        if (nextDamage >= copy.getMaxDamage()) {
            return new ItemStack(containerItem.get());
        }
        copy.setDamageValue(nextDamage);
        return copy;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        if (toolTip != null) {
            tooltip.add(toolTip);
        }
    }
}
