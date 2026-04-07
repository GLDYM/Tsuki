package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod_mmf.mmlib.data.AbstractItemModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

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

    private void normalItem(DeferredHolder<Item, ? extends Item> item) {
        singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private void bushItem(DeferredHolder<Item, ? extends Item> item) {
        singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0",
                modLoc("block/" + item.getId().getPath()));
    }

    private void itemBlock(Supplier<? extends Block> blockSupplier) {
        Block block = blockSupplier.get();
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        withExistingParent(name, modLoc("block/" + name));
    }

}

