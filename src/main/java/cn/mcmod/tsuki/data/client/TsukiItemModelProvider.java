package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod_mmf.mmlib.data.AbstractItemModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.BushBlock;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TsukiItemModelProvider extends AbstractItemModelProvider {

    public TsukiItemModelProvider(PackOutput packOutput, String modid, ExistingFileHelper existingFileHelper) {
        super(packOutput, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        BlockItemRegistry.ITEMS.getEntries().forEach(item -> {
            if (item.get() instanceof BlockItem) {
                BlockItem blockItem = (BlockItem) item.get();
                if (blockItem.getBlock() instanceof StoneMortarBlock)
                    return;
                if (blockItem.getBlock() instanceof BushBlock)
                    bushItem(item);
                else
                    itemBlock(blockItem::getBlock);
            } else {
                normalItem(item);
            }
        });
        
        BucketItemRegistry.ITEMS.getEntries().forEach((item)->{
            normalItem(item);
            
        });

    }

}
