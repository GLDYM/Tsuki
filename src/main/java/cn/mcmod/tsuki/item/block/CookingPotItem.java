package cn.mcmod.tsuki.item.block;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.client.gui.CookingPotTooltip;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class CookingPotItem extends BlockItem {
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public CookingPotItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return CookingPotBlockEntity.getMealFromItem(stack).getCount() > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int servings = CookingPotBlockEntity.getMealFromItem(stack).getCount();
        return servings <= 0 ? 0 : Math.min(1 + 12 * servings / 64, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        ItemStack mealStack = CookingPotBlockEntity.getMealFromItem(stack);
        if (mealStack.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new CookingPotTooltip.CookingPotTooltipComponent(mealStack));
    }
}
