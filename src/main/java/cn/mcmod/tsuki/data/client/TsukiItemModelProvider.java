package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.FallenLeavesBlock;
import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.block.machines.StoneMortarBlock;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.item.ShinaiItem;
import cn.mcmod.tsuki.item.KatanaItem;
import cn.mcmod.tsuki.item.SheathItem;
import cn.mcmod.tsuki.item.SheathKatanaItem;
import cn.mcmod.tsuki.item.armors.HaoriItem;
import cn.mcmod.tsuki.item.armors.KimonoItem;
import cn.mcmod.tsuki.item.armors.TsukiArmorToolRegistry;
import cn.mcmod.tsuki.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import cn.mcmod_mmf.mmlib.data.AbstractItemModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
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
                if (blockItem.getBlock() instanceof StoneMortarBlock
                    || blockItem.getBlock() == BlockRegistry.BAMBOO_FENCE.get()
                    || blockItem.getBlock() == BlockRegistry.BAMBOO_FENCE_SUNBURNT.get()
                    || blockItem.getBlock() == BlockRegistry.FUTON.get())
                {
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
                if (blockItem.getBlock() == BlockRegistry.KAWARA.get()) {
                    withExistingParent(item.getId().getPath(), modLoc("block/" + item.getId().getPath()))
                            .transforms()
                            .transform(ItemDisplayContext.GUI)
                            .rotation(30, 45, 0)
                            .translation(0, 0, 0)
                            .scale(0.5F)
                            .end()
                            .end();
                    return;
                }
                if (blockItem.getBlock() == BlockRegistry.SHOJI.get()
                    || blockItem.getBlock() == BlockRegistry.SHOJI_1.get()
                    || blockItem.getBlock() == BlockRegistry.SHOJI_2.get()
                    || blockItem.getBlock() == BlockRegistry.SHOJI_3.get()
                    || blockItem.getBlock() == BlockRegistry.SHOJI_4.get()
                    || blockItem.getBlock() == BlockRegistry.SHOJI_5.get()
                    || blockItem.getBlock() == BlockRegistry.BAMBOO_DOOR.get()
                    || blockItem.getBlock() == BlockRegistry.CHESTNUT_BURR.get()) {
                    normalItem(item);
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
            if (item.get() instanceof KimonoItem
                || item.get() instanceof HaoriItem
                || item.get() instanceof KatanaItem
                || item.get() instanceof SheathItem
                || item.get() instanceof SheathKatanaItem
                || item.get() instanceof ShinaiItem) {
                return;
            } else {
                normalItem(item);
            }
        });
        FoodRegistry.ITEMS.getEntries().forEach(item -> {
            if (item.get() == TsukiFoodSet.CABBAGE.getItem().get()
                || item.get() == TsukiFoodSet.UME.getItem().get()
                || item.get() == TsukiFoodSet.UMEBOSHI.getItem().get()
            ) {
                return;
            }
            if (item.get() == TsukiCuisineSet.DANANKO.getItem().get()
                || item.get() == TsukiCuisineSet.DANMITARASHI.getItem().get()
                || item.get() == TsukiCuisineSet.DANSANSYOKU.getItem().get()
                || item.get() == TsukiCuisineSet.BEEF_STICK.getItem().get()
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
