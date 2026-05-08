package cn.mcmod.tsuki.block.drink;

import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.item.drink.DrinkItem;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DrinkCupBlock extends AbstractDrinkDisplayBlock {
    public static final MapCodec<DrinkCupBlock> CODEC = simpleCodec(ignored -> new DrinkCupBlock());

    public DrinkCupBlock() {
        super(BlockBehaviour.Properties.of().noOcclusion().strength(0.2F));
    }

    @Override
    protected boolean canAccept(ItemStack stack) {
        return stack.is(DrinkRegistry.CUP.get()) || stack.is(DrinkRegistry.GLASS_CUP.get())
                || stack.getItem() instanceof DrinkItem;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
