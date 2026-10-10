package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.block.machine.CeilingLightBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CeilingLightItem extends BlockItem {
    public CeilingLightItem(Block block, Properties properties) {
        super(block, properties);
        if (!(block instanceof CeilingLightBlock)) throw new IllegalArgumentException("CeilingLightItem requires CeilingLightBlock");
    }
}
