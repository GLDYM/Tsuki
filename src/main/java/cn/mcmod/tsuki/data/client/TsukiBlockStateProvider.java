package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.block.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiBlockStateProvider extends BlockStateProvider {
        private final String modid;

    public TsukiBlockStateProvider(PackOutput packOutput, String modid, ExistingFileHelper exFileHelper) {
        super(packOutput, modid, exFileHelper);
                this.modid = modid;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegistry.SAKURA_LEAVES.get());
        simpleBlock(BlockRegistry.SAKURA_PLANK.get());
        simpleBlock(BlockRegistry.MAPLE_PLANK.get());
        simpleBlock(BlockRegistry.BAMBOO_PLANK.get());
        
        simpleBlock(BlockRegistry.STRAW_BLOCK.get());
        simpleBlock(BlockRegistry.SAKURA_DIAMOND_ORE.get());
        simpleBlock(BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get());

        simpleBlock(BlockRegistry.MAPLE_LEAVES_RED.get());
        simpleBlock(BlockRegistry.MAPLE_LEAVES_YELLOW.get());
        simpleBlock(BlockRegistry.MAPLE_LEAVES_GREEN.get());
        simpleBlock(BlockRegistry.MAPLE_LEAVES_ORANGE.get());

        log(BlockRegistry.SAKURA_LOG.get());
        log(BlockRegistry.STRIPPED_SAKURA_LOG.get());
        log(BlockRegistry.MAPLE_LOG.get());
        log(BlockRegistry.STRIPPED_MAPLE_LOG.get());
        log(BlockRegistry.BAMBOO_BLOCK.get());
        log(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get());
        log(BlockRegistry.BAMBOO_CHARCOAL_BLOCK.get());

        horizontalBlock(BlockRegistry.FERMENTER.get(), models().getExistingFile(ResourceLocation.parse("tsuki:block/fermenter")));
        crossBlock(BlockRegistry.SAKURA_SAPLING.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_RED.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_YELLOW.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_GREEN.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_ORANGE.get());

        stageBlock(BlockRegistry.BUCKWHEAT_CROP.get(), BlockStateProperties.AGE_7);
        stageBlock(BlockRegistry.RAPESEED_CROP.get(), BlockStateProperties.AGE_7);
        stageBlock(BlockRegistry.REDBEAN_CROP.get(), BlockStateProperties.AGE_3);
        stageBlock(BlockRegistry.TARO_CROP.get(), BlockStateProperties.AGE_3);
        
        horizontalBlock(BlockRegistry.TATAMI.get(), 
                texture("tatami"), 
                texture("tatami"), 
                texture("tatami"));
        
        horizontalBlock(BlockRegistry.TATAMI_WAXED.get(), 
                texture("tatami"), 
                texture("tatami"), 
                texture("tatami"));
        
        facingSlabBlock(BlockRegistry.TATAMI_SLAB_WAXED.get(), 
                texture("tatami"), 
                texture("tatami"), 
                texture("tatami")
        );
        
        horizontalBlock(BlockRegistry.TATAMI_SUNBURNT.get(), 
                texture("tatami_tan"), 
                texture("tatami_tan"), 
                texture("tatami_tan"));
        
        facingSlabBlock(BlockRegistry.TATAMI_SLAB.get(), 
                texture("tatami"), 
                texture("tatami"), 
                texture("tatami")
        );
        facingSlabBlock(BlockRegistry.TATAMI_SLAB_SUNBURNT.get(), 
                texture("tatami_tan"), 
                texture("tatami_tan"), 
                texture("tatami_tan")
        );
    }

        private void log(RotatedPillarBlock block) {
                String name = name(block);
                axisBlock(block, texture(name), texture(name + "_top"));
        }

        private void stageBlock(Block crop, IntegerProperty ageProperty) {
                String cropName = name(crop);
                getVariantBuilder(crop).forAllStates(state -> ConfiguredModel.builder()
                                .modelFile(models().crop(cropName + "_stage" + state.getValue(ageProperty),
                                                texture(cropName + "_stage" + state.getValue(ageProperty))))
                                .build());
        }

        private void facingSlabBlock(Block slab,
                                                                 ResourceLocation side,
                                                                 ResourceLocation top,
                                                                 ResourceLocation bottom) {
                simpleBlock(slab, models().slab(name(slab), side, bottom, top));
                simpleBlockItem(slab, models().slab(name(slab), side, bottom, top));
        }

        private void crossBlock(Block block) {
                String name = name(block);
                ResourceLocation texture = texture(name);
                simpleBlock(block, models().cross(name, texture));
                itemModels().getBuilder(name)
                                .parent(itemModels().getExistingFile(mcLoc("item/generated")))
                                .texture("layer0", texture);
        }

        private String name(Block block) {
                return BuiltInRegistries.BLOCK.getKey(block).getPath();
        }

        private ResourceLocation texture(String name) {
                return ResourceLocation.fromNamespaceAndPath(modid, "block/" + name);
        }

}

