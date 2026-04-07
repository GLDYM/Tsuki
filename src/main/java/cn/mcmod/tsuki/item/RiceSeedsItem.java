package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RiceSeedsItem extends ItemNameBlockItem {

    public RiceSeedsItem() {
        super(BlockRegistry.RICE_CROP_ROOT.get(), Tsuki.defaultItemProperties());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = this.place(new BlockPlaceContext(context));
        if (result.equals(InteractionResult.FAIL)) {
            Player player = context.getPlayer();
            BlockState targetState = context.getLevel().getBlockState(context.getClickedPos());
            if (player != null && context.getClickedFace().equals(Direction.UP)
                    && (targetState.is(BlockTags.DIRT) || targetState.getBlock() instanceof FarmBlock)) {
                player.displayClientMessage(
                        Component.translatable(Tsuki.MODID + "." + "block.rice.invalid_placement"), true);
            }
        }
        return result;
    }
}
