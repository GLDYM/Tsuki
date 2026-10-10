package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.block.machine.WallLightBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class WallLightItem extends BlockItem {
    public WallLightItem(Block block, Properties properties) {
        super(block, properties);
        if (!(block instanceof WallLightBlock)) throw new IllegalArgumentException("WallLightItem requires WallLightBlock");
    }
}
