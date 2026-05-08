package cn.mcmod.tsuki.block.drink;

import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.WineBottleItem;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class WineBottleDisplayBlock extends AbstractDrinkDisplayBlock {
    public static final MapCodec<WineBottleDisplayBlock> CODEC = simpleCodec(ignored -> new WineBottleDisplayBlock());

    public WineBottleDisplayBlock() {
        super(BlockBehaviour.Properties.of().noOcclusion().strength(0.2F));
    }

    @Override
    protected boolean canAccept(ItemStack stack) {
        return stack.is(DrinkRegistry.WINE_BOTTLE.get())
                || stack.getItem() instanceof WineBottleItem;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
