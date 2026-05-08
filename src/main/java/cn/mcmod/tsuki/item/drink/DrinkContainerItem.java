package cn.mcmod.tsuki.item.drink;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class DrinkContainerItem extends PlaceableDrinkItem {
    public DrinkContainerItem(Properties properties, Supplier<? extends Block> placementBlock) {
        super(properties, placementBlock);
    }
}
