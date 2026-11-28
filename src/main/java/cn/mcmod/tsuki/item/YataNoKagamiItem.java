package cn.mcmod.tsuki.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import cn.mcmod.tsuki.item.magatama.MagatamaEquipmentCache;

import java.util.List;

/** The Yata no Kagami is a Curios charm that grants player noclip. */
public class YataNoKagamiItem extends Item {
    public YataNoKagamiItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (entity instanceof Player player) {
            MagatamaEquipmentCache.mark(player, stack, slotId);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.tsuki.yata_no_kagami.tooltip").withStyle(ChatFormatting.GRAY));
    }
}
