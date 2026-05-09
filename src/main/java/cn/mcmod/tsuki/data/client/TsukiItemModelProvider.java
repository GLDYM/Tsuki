package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.block.tree.FallenLeavesBlock;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.BucketItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.tsuki.item.armor.HaoriItem;
import cn.mcmod.tsuki.item.armor.KimonoItem;
import cn.mcmod.tsuki.item.tool.BroomItem;
import cn.mcmod.tsuki.item.tool.KatanaItem;
import cn.mcmod.tsuki.item.tool.SheathItem;
import cn.mcmod.tsuki.item.tool.SheathKatanaItem;
import cn.mcmod.tsuki.item.tool.ShinaiItem;
import cn.mcmod.mmlib.data.AbstractItemModelProvider;
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
                if (blockItem.getBlock() == BlockRegistry.BAMBOO_FENCE.get()
                        || blockItem.getBlock() == BlockRegistry.BAMBOO_FENCE_SUNBURNT.get()
                        || blockItem.getBlock() == BlockRegistry.FUTON.get()) {
                    return;
                }
                if (blockItem.getBlock() instanceof FallenLeavesBlock) {
                    itemBlock(blockItem::getBlock);
                    return;
                }
                if (blockItem.getBlock() == BlockRegistry.KITUNEBI.get()) {
                    singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0",
                            modLoc("block/ghost_fire_0"));
                    return;
                }
                if (blockItem.getBlock() == BlockRegistry.SHAKER.get()) {
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

        BucketItemRegistry.ITEMS.getEntries().forEach(item -> {
            normalItem(item);
        });
        ArmorToolRegistry.ITEMS.getEntries().forEach(item -> {
            if (item.get() instanceof KimonoItem
                    || item.get() instanceof HaoriItem
                    || item.get() instanceof KatanaItem
                    || item.get() instanceof SheathItem
                    || item.get() instanceof SheathKatanaItem
                    || item.get() instanceof ShinaiItem
                    || item.get() instanceof BroomItem) {
                return;
            } else {
                normalItem(item);
            }
        });
        FoodRegistry.ITEMS.getEntries().forEach(item -> {
            if (item.get() == TsukiFoodSet.CABBAGE.getItem().get()
                    || item.get() == TsukiFoodSet.UME.getItem().get()
                    || item.get() == TsukiFoodSet.UMEBOSHI.getItem().get()
                    || item.get() == TsukiCuisineSet.DANANKO.getItem().get()
                    || item.get() == TsukiCuisineSet.DANMITARASHI.getItem().get()
                    || item.get() == TsukiCuisineSet.DANSANSYOKU.getItem().get()
                    || item.get() == TsukiCuisineSet.BEEF_STICK.getItem().get()
                    || item.get() == TsukiCuisineSet.CHICKEN_STICK.getItem().get()
                    || item.get() == TsukiCuisineSet.PORK_STICK.getItem().get()
                    || item.get() == TsukiCuisineSet.BENTO_STANDARD.getItem().get()
                    || item.get() == TsukiCuisineSet.BENTO_DELUXE.getItem().get()
                    || item.get() == TsukiCuisineSet.BENTO_PREMIUM.getItem().get()
                    || item.get() == TsukiCuisineSet.BENTO_SUPREME.getItem().get()) {
                return;
            }
            normalItem(item);
        });

        ItemRegistry.ITEMS.getEntries().forEach( item -> {
            if (item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_SUNBURNT).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO_CHARCOAL).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.RAMEN_BLOCK).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.UDON_BLOCK).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.SOBA_BLOCK).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.PASTA_BLOCK).get()
                || item.get() == ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BENTO_BOX).get()
                || item.get() == ItemRegistry.SAMURAI_ILLAGER_SPAWN_EGG.get())
                {
                return;
            } else {
                normalItem(item);
            }
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
