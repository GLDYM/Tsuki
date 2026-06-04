package cn.mcmod.tsuki.item;

import cn.mcmod.mmlib.item.ItemFoodBase;
import cn.mcmod.mmlib.item.info.FoodInfo;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class BuggysMeatItem extends ItemFoodBase {
    public BuggysMeatItem(Item.Properties properties, FoodInfo info) {
        super(properties, info);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip,
            TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("tsuki.tooltip.buggys_meat.1").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tsuki.tooltip.buggys_meat.2").withStyle(ChatFormatting.GRAY));
    }
}
