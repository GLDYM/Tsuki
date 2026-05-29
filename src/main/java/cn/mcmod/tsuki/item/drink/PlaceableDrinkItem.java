package cn.mcmod.tsuki.item.drink;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public abstract class PlaceableDrinkItem extends Item {
    private final Supplier<? extends Block> placementBlock;

    protected PlaceableDrinkItem(Properties properties, Supplier<? extends Block> placementBlock) {
        super(properties);
        this.placementBlock = placementBlock;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && !context.getPlayer().isShiftKeyDown()) {
            return InteractionResult.PASS;
        }
        Level level = context.getLevel();
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        return DrinkPlacementHelper.place(context, placementBlock.get());
    }

    protected Supplier<? extends Block> getPlacementBlock() {
        return placementBlock;
    }
}
