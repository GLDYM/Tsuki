package cn.mcmod.tsuki.data.loot;

import cn.mcmod.tsuki.block.crop.RiceCropRoot;
import cn.mcmod.tsuki.block.decoration.FutonBlock;
import cn.mcmod.tsuki.block.food.TeishokuBlock;
import cn.mcmod.tsuki.block.food.TeishokuFinishedBlock;
import cn.mcmod.tsuki.block.tree.BambooPlant;
import cn.mcmod.tsuki.block.tree.ChestnutBurrBlock;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import cn.mcmod.tsuki.init.item.enums.TsukiNormalItemSet;
import cn.mcmod.mmlib.data.loot.AbstartctBlockLoot;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class TsukiBlockLoot extends AbstartctBlockLoot {

    public TsukiBlockLoot(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    public void addTables() {
        dropSelf(BlockRegistry.BAMBOO_BLOCK.get());
        BlockRegistry.BLOCKS
                .getEntries()
                .forEach(
                        block -> {
                            if (!(block.get() instanceof LeavesBlock)
                                    && !(block.get() instanceof CropBlock)
                                    && !(block.get() instanceof TeishokuBlock)
                                    && !(block.get() instanceof RiceCropRoot)
                                    && block.get() != BlockRegistry.MAPLE_SAP_LOG.get()
                                    && block.get() != BlockRegistry.CHESTNUT_BURR.get()
                                    && block.get() != BlockRegistry.FUTON.get()
                                    && block.get() != BlockRegistry.GRAPE_VINE.get()
                                    && block.get() != BlockRegistry.GRAPE_LEAVES.get()
                                    && block.get() != BlockRegistry.SAKURA_DIAMOND_ORE.get()
                                    && block.get() != BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get()
                                    && block.get() != BlockRegistry.BAMBOO_DOOR.get()
                                    && block.get() != BlockRegistry.DRINK_DISPLAY.get()
                                    && block.get() != BlockRegistry.SHAKER.get()
                                    && block.get() != BlockRegistry.SHOJI.get()
                                    && block.get() != BlockRegistry.SHOJI_1.get()
                                    && block.get() != BlockRegistry.SHOJI_2.get()
                                    && block.get() != BlockRegistry.SHOJI_3.get()
                                    && block.get() != BlockRegistry.SHOJI_4.get()
                                    && block.get() != BlockRegistry.SHOJI_5.get()) {
                                if (block.get() instanceof BambooPlant) {
                                    this.dropOther(block.get(),
                                            ItemRegistry.MATERIALS.get(TsukiNormalItemSet.BAMBOO).get());
                                } else if (block.get() instanceof TeishokuFinishedBlock) {
                                    this.dropOther(block.get(), BlockItemRegistry.OBON.get());
                                } else {
                                    this.dropSelf(block.get());
                                }
                            }
                        });

        this.dropOther(BlockRegistry.MAPLE_SAP_LOG.get(), BlockItemRegistry.MAPLE_LOG.get());
        this.dropOther(BlockRegistry.GRAPE_VINE.get(), BlockItemRegistry.GRAPE_SPLINT_STAND.get());
        this.dropOther(BlockRegistry.GRAPE_LEAVES.get(), BlockItemRegistry.GRAPE_SPLINT.get());

        this.add(BlockRegistry.SAKURA_DIAMOND_ORE.get(),
                createOreDrop(BlockRegistry.SAKURA_DIAMOND_ORE.get(), ArmorToolRegistry.SAKURA_DIAMOND.get()));
        this.add(BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get(), createOreDrop(
                BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get(), ArmorToolRegistry.SAKURA_DIAMOND.get()));
        this.add(BlockRegistry.BAMBOO_DOOR.get(), createDoor(BlockRegistry.BAMBOO_DOOR.get()));
        this.add(BlockRegistry.SHOJI.get(), createDoor(BlockRegistry.SHOJI.get()));
        this.add(BlockRegistry.SHOJI_1.get(), createDoor(BlockRegistry.SHOJI_1.get()));
        this.add(BlockRegistry.SHOJI_2.get(), createDoor(BlockRegistry.SHOJI_2.get()));
        this.add(BlockRegistry.SHOJI_3.get(), createDoor(BlockRegistry.SHOJI_3.get()));
        this.add(BlockRegistry.SHOJI_4.get(), createDoor(BlockRegistry.SHOJI_4.get()));
        this.add(BlockRegistry.SHOJI_5.get(), createDoor(BlockRegistry.SHOJI_5.get()));
        this.add(BlockRegistry.FUTON.get(), createFutonDrops(BlockRegistry.FUTON.get()));
        this.add(BlockRegistry.DRINK_DISPLAY.get(), LootTable.lootTable());
        this.add(BlockRegistry.SHAKER.get(), LootTable.lootTable());
        this.add(BlockRegistry.CHESTNUT_BURR.get(), createChestnutBurrDrops(BlockRegistry.CHESTNUT_BURR.get()));

        this.add(BlockRegistry.MAPLE_LEAVES_RED.get(), createLeavesDrops(BlockRegistry.MAPLE_LEAVES_RED.get(),
                BlockRegistry.MAPLE_SAPLING_RED.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(BlockRegistry.MAPLE_LEAVES_ORANGE.get(), createLeavesDrops(BlockRegistry.MAPLE_LEAVES_ORANGE.get(),
                BlockRegistry.MAPLE_SAPLING_ORANGE.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(BlockRegistry.MAPLE_LEAVES_YELLOW.get(), createLeavesDrops(BlockRegistry.MAPLE_LEAVES_YELLOW.get(),
                BlockRegistry.MAPLE_SAPLING_YELLOW.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(BlockRegistry.MAPLE_LEAVES_GREEN.get(), createLeavesDrops(BlockRegistry.MAPLE_LEAVES_GREEN.get(),
                BlockRegistry.MAPLE_SAPLING_GREEN.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(BlockRegistry.SAKURA_LEAVES.get(), createLeavesDrops(BlockRegistry.SAKURA_LEAVES.get(),
                BlockRegistry.SAKURA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(BlockRegistry.UME_LEAVES.get(), createLeavesDrops(BlockRegistry.UME_LEAVES.get(),
                BlockRegistry.UME_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.createTeishoku(BlockRegistry.TEISHOUKU_FISH_COOKED.get());
        this.createTeishoku(BlockRegistry.TEISHOUKU_FISH_RAW.get());
        this.createTeishoku(BlockRegistry.TEISHOUKU_FISH_SALT.get());
        this.createTeishoku(BlockRegistry.TEISHOKO_TAMAGOYAKI.get());
        this.createTeishoku(BlockRegistry.TEISHOKO_YAKINIKU.get());
        this.createTeishoku(BlockRegistry.TEISHOKU_TEMPURA.get());
        this.createTeishoku(BlockRegistry.TEISHOKU_FRIED.get());
        this.createTeishoku(BlockRegistry.TEISHOKU_KATSU.get());
        this.createTeishoku(BlockRegistry.TEISHOKU_BURGER.get());

        createCrop(BlockRegistry.CABBAGE_CROP.get(), FoodRegistry.FOODSET.get(TsukiFoodSet.CABBAGE).get(),
                ItemRegistry.CABBAGE_SEEDS.get(), 7);

        createCrop(BlockRegistry.RADISH_CROP.get(), FoodRegistry.FOODSET.get(TsukiFoodSet.RADISH).get(),
                ItemRegistry.RADISH_SEEDS.get(), 3);

        createCrop(BlockRegistry.ONION_CROP.get(), FoodRegistry.FOODSET.get(TsukiFoodSet.ONION).get(),
                ItemRegistry.ONION_SEEDS.get(), 3);

        createCrop(BlockRegistry.REDBEAN_CROP.get(), ItemRegistry.RED_BEAN.get(), ItemRegistry.RED_BEAN.get(), 3);
        createCrop(BlockRegistry.SOYBEAN_CROP.get(), ItemRegistry.SOYBEAN.get(), ItemRegistry.SOYBEAN.get(), 3);

        createCrop(BlockRegistry.EGGPLANT_CROP.get(), FoodRegistry.FOODSET.get(TsukiFoodSet.EGGPLANT).get(),
                ItemRegistry.EGGPLANT_SEEDS.get(), 7);

        createCrop(BlockRegistry.TOMATO_CROP.get(), FoodRegistry.FOODSET.get(TsukiFoodSet.TOMATO).get(),
                ItemRegistry.TOMATO_SEEDS.get(), 7);

        createCrop(BlockRegistry.RICE_CROP.get(), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW).get(),
                ItemRegistry.RICE_SEEDS.get(), 7);

        createCrop(BlockRegistry.RICE_CROP_ROOT.get(), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.STRAW).get(),
                ItemRegistry.RICE_SEEDS.get(), 7);

        createCrop(BlockRegistry.RAPESEED_CROP.get(), ItemRegistry.RAPESEEDS.get(), ItemRegistry.RAPESEEDS.get(), 7);

        createCrop(BlockRegistry.TARO_CROP.get(), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.IMOGARA).get(),
                ItemRegistry.TARO.get(), 3);

        createCrop(BlockRegistry.BUCKWHEAT_CROP.get(), ItemRegistry.BUCKWHEAT.get(), ItemRegistry.BUCKWHEAT.get(), 7);
        createCropWithSupport(BlockRegistry.PEPPER_CROP.get(),
                ItemRegistry.MATERIALS.get(TsukiNormalItemSet.PEPPERCORN_RED).get(),
                ItemRegistry.PEPPER_SEEDS.get(), 7, BlockItemRegistry.PEPPER_SPLINT.get());
        createCropWithSupport(BlockRegistry.VANILLA_CROP.get(),
                ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VANILLA).get(),
                ItemRegistry.VANILLA_SEEDS.get(), 7, BlockItemRegistry.VANILLA_SPLINT.get());
        createCropWithSupport(BlockRegistry.HOPS_CROP.get(), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.HOP).get(),
                ItemRegistry.MATERIALS.get(TsukiNormalItemSet.HOP).get(), 7, BlockItemRegistry.GRAPE_SPLINT_STAND.get());
        createCrop(BlockRegistry.WILD_PEPPER.get(),
                ItemRegistry.MATERIALS.get(TsukiNormalItemSet.PEPPERCORN_GREEN).get(),
                ItemRegistry.PEPPER_SEEDS.get(), 7);
        createCrop(BlockRegistry.WILD_VANILLA.get(), ItemRegistry.MATERIALS.get(TsukiNormalItemSet.VANILLA).get(),
                ItemRegistry.VANILLA_SEEDS.get(), 7);
    }

    private void createTeishoku(Block block) {
        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TeishokuBlock.BITES, 0));
        this.add(block, createTeishokuDrops(block, BlockItemRegistry.OBON.get(), block.asItem(), builder));
    }

    protected LootTable.Builder createTeishokuDrops(Block p_124143_, Item p_124144_, Item p_124145_,
            LootItemCondition.Builder p_124146_) {
        return applyExplosionDecay(p_124143_, LootTable.lootTable()
                .withPool(LootPool.lootPool().add(
                        LootItem.lootTableItem(p_124145_).when(p_124146_)))
                .withPool(
                        LootPool.lootPool().when(p_124146_.invert()).add(LootItem.lootTableItem(p_124144_))));
    }

    private void createCrop(Block block, Item crop, Item seeds, int age) {
        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, age));
        this.add(block, createCropDrops(block, crop, seeds, builder));
    }

    private void createCropWithSupport(Block block, Item crop, Item seeds, int age, Item support) {
        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, age));
        this.add(block, createCropDrops(block, crop, seeds, builder)
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(support))));
    }

    private LootTable.Builder createFutonDrops(Block block) {
        LootItemCondition.Builder footPart = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FutonBlock.PART,
                        FutonBlock.BedPart.FOOT));
        return applyExplosionDecay(block, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(BlockItemRegistry.FUTON.get()).when(footPart))));
    }

    private LootTable.Builder createChestnutBurrDrops(Block block) {
        LootItemCondition.Builder mature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ChestnutBurrBlock.AGE, 3));
        return applyExplosionDecay(block, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(BlockItemRegistry.CHESTNUT_BURRS.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                                .when(mature))));
    }

    private LootTable.Builder createDoor(Block block) {
        LootItemCondition.Builder lowerHalf = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER));
        return applyExplosionDecay(block, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(block).when(lowerHalf))));
    }
}


