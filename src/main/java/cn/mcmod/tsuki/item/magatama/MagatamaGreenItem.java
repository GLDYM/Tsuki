package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.config.TsukiCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MagatamaGreenItem extends Item {
    public MagatamaGreenItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResultHolder.sidedSuccess(stack, true);
        }

        return MagatamaGreenHelper.use(serverPlayer)
                ? applyCooldownAndSuccess(serverPlayer, stack)
                : InteractionResultHolder.fail(stack);
    }

    private InteractionResultHolder<ItemStack> applyCooldownAndSuccess(ServerPlayer player, ItemStack stack) {
        player.getCooldowns().addCooldown(this, MagatamaGreenHelper.COOLDOWN_TICKS);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip,
            TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.tsuki.magatama_green.tooltip",
                TsukiCommonConfig.MAGATAMA_GREEN_DRAW_COUNT.get()).withStyle(ChatFormatting.GRAY));
    }
}
