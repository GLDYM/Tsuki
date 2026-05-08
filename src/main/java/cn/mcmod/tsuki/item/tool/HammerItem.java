package cn.mcmod.tsuki.item.tool;

import cn.mcmod.tsuki.tag.TsukiBlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, TsukiBlockTags.MINEABLE_WITH_HAMMER,
                properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public boolean hasCraftingRemainingItem() {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        if (!copy.isDamageableItem()) {
            return copy;
        }
        if (copy.getDamageValue() >= copy.getMaxDamage() - 1) {
            return ItemStack.EMPTY;
        }
        copy.setDamageValue(copy.getDamageValue() + 1);
        return copy;
    }
}
