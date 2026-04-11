package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.FallenLeavesBlock;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.armors.KimonoItem;
import cn.mcmod.tsuki.item.armors.TsukiArmorToolRegistry;
import cn.mcmod.tsuki.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
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
                if (blockItem.getBlock() instanceof StoneMortarBlock) {
                    return;
                }
                if (blockItem.getBlock() instanceof FallenLeavesBlock) {
                    itemBlock(blockItem::getBlock);
                    return;
                }
                if (blockItem.getBlock() == BlockRegistry.KITUNEBI.get()) {
                    singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("block/ghost_fire_0"));
                    return;
                }
                if (blockItem.getBlock() instanceof BushBlock)
                    bushItem(item);
                else
                    itemBlock(blockItem::getBlock);
            } else {
                normalItem(item);
            }
        });
        
        BucketItemRegistry.ITEMS.getEntries().forEach(this::normalItem);
        TsukiArmorToolRegistry.ITEMS.getEntries().forEach(item -> {
            if (item.get() instanceof KimonoItem) {
                return;
            } else {
                normalItem(item);
            }
        });
        FoodRegistry.ITEMS.getEntries().forEach(item -> {
            if (item.get() == TsukiFoodSet.CABBAGE.getItem().get()
                || item.get() == TsukiFoodSet.DANANKO.getItem().get()
                || item.get() == TsukiFoodSet.DANMITARASHI.getItem().get()
                || item.get() == TsukiFoodSet.DANSANSYOKU.getItem().get()
            ) {
                return;
            }
            if (item.get() == TsukiCuisineSet.BEEF_STICK.getItem().get()
                    || item.get() == TsukiCuisineSet.CHICKEN_STICK.getItem().get()
                    || item.get() == TsukiCuisineSet.PORK_STICK.getItem().get()
            ) {
                return;
            }
            normalItem(item);
        });

        // ItemRegistry.ITEMS.getEntries().forEach(this::normalItem);
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

