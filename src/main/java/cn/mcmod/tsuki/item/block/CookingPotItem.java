package cn.mcmod.tsuki.item.block;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class CookingPotItem extends BlockItem {
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
}
