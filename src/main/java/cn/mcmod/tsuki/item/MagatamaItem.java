package cn.mcmod.tsuki.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MagatamaItem extends Item {
    public MagatamaItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
