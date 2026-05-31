package cn.mcmod.tsuki.item.magatama;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class MagatamaWhiteItem extends Item {
    public MagatamaWhiteItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip,
            TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tsuki.tooltip.magatama_white.1").withStyle(ChatFormatting.GRAY));
        // tooltip.add(Component.translatable("tsuki.tooltip.magatama_white.2").withStyle(ChatFormatting.GRAY));
    }
}
