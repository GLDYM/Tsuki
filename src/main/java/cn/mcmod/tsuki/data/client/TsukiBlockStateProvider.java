package cn.mcmod.tsuki.data.client;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.ChestnutBurrBlock;
import cn.mcmod.tsuki.block.FutonBlock;
import cn.mcmod.tsuki.block.UmeLeavesBlock;
import cn.mcmod.tsuki.block.foods.TeishokuBlock;
import cn.mcmod.tsuki.block.machines.TataraBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import java.util.function.IntFunction;

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
        simpleBlock(BlockRegistry.SAKURA_DIAMOND_BLOCK.get());
        simpleBlock(BlockRegistry.IRON_SAND.get());

        simpleBlock(BlockRegistry.MAPLE_LEAVES_RED.get());
        simpleBlock(BlockRegistry.MAPLE_LEAVES_YELLOW.get());
        simpleBlock(BlockRegistry.MAPLE_LEAVES_GREEN.get());
        simpleBlock(BlockRegistry.MAPLE_LEAVES_ORANGE.get());
        umeLeaves(BlockRegistry.UME_LEAVES.get());
        simpleBlock(BlockRegistry.FALLEN_LEAVES_RED.get(), models().getExistingFile(modLoc("block/fallen_leaves_red")));
        simpleBlock(BlockRegistry.FALLEN_LEAVES_ORANGE.get(),
                models().getExistingFile(modLoc("block/fallen_leaves_orange")));
        simpleBlock(BlockRegistry.FALLEN_LEAVES_YELLOW.get(),
                models().getExistingFile(modLoc("block/fallen_leaves_yellow")));
        simpleBlock(BlockRegistry.FALLEN_LEAVES_GREEN.get(),
                models().getExistingFile(modLoc("block/fallen_leaves_green")));
        simpleBlock(BlockRegistry.MUSHROOM_FALLEN_LEAVES.get(),
                models().getExistingFile(modLoc("block/fallen_leaves_mushroom")));
        simpleBlock(BlockRegistry.MATSUTAKE_FALLEN_LEAVES.get(),
                models().getExistingFile(modLoc("block/fallen_leaves_matsutake")));
        chestnutBurr(BlockRegistry.CHESTNUT_BURR.get());

        log(BlockRegistry.SAKURA_LOG.get());
        log(BlockRegistry.STRIPPED_SAKURA_LOG.get());
        log(BlockRegistry.MAPLE_LOG.get());
        log(BlockRegistry.STRIPPED_MAPLE_LOG.get());
        axisBlock(BlockRegistry.UME_LOG.get(), texture("ume_log"), texture("ume_log_top"));
        axisBlock(BlockRegistry.STRIPPED_UME_LOG.get(), texture("stripped_ume_log"), texture("stripped_ume_log_top"));
        axisBlock(BlockRegistry.UME_WOOD.get(), texture("ume_log"), texture("ume_log"));
        axisBlock(BlockRegistry.STRIPPED_UME_WOOD.get(), texture("stripped_ume_log"), texture("stripped_ume_log"));
        log(BlockRegistry.BAMBOO_BLOCK.get());
        log(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get());
        log(BlockRegistry.BAMBOO_CHARCOAL_BLOCK.get());

        horizontalBlock(BlockRegistry.FERMENTER.get(),
                models().getExistingFile(ResourceLocation.parse("tsuki:block/fermenter")));
        crossBlock(BlockRegistry.SAKURA_SAPLING.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_RED.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_YELLOW.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_GREEN.get());
        crossBlock(BlockRegistry.MAPLE_SAPLING_ORANGE.get());
        crossBlock(BlockRegistry.UME_SAPLING.get());

        simpleBlock(BlockRegistry.PEPPER_SPLINT.get(), models().getExistingFile(modLoc("block/pepper_splint")));
        simpleBlockItem(BlockRegistry.PEPPER_SPLINT.get(), models().getExistingFile(modLoc("block/pepper_splint")));
        simpleBlock(BlockRegistry.VANILLA_SPLINT.get(), models().getExistingFile(modLoc("block/vanilla_splint")));
        simpleBlockItem(BlockRegistry.VANILLA_SPLINT.get(), models().getExistingFile(modLoc("block/vanilla_splint")));
        simpleBlock(BlockRegistry.GRAPE_SPLINT_STAND.get(),
                models().getExistingFile(modLoc("block/grape_splint_stand")));
        simpleBlockItem(BlockRegistry.GRAPE_SPLINT_STAND.get(),
                models().getExistingFile(modLoc("block/grape_splint_stand")));
        simpleBlock(BlockRegistry.GRAPE_SPLINT.get(), models().getExistingFile(modLoc("block/grape_splint")));
        simpleBlockItem(BlockRegistry.GRAPE_SPLINT.get(), models().getExistingFile(modLoc("block/grape_splint")));

        ageModelBlock(BlockRegistry.PEPPER_CROP.get(), BlockStateProperties.AGE_7, age -> "pepper_" + age);
        ageModelBlock(BlockRegistry.VANILLA_CROP.get(), BlockStateProperties.AGE_7, age -> "vanilla_" + age);
        ageModelBlock(BlockRegistry.HOPS_CROP.get(), BlockStateProperties.AGE_7, age -> {
            if (age == 0) {
                return "hops_0";
            }
            if (age <= 3) {
                return "hops_1";
            }
            if (age <= 6) {
                return "hops_2";
            }
            return "hops_3";
        });
        ageModelBlock(BlockRegistry.GRAPE_VINE.get(), BlockStateProperties.AGE_7, age -> {
            if (age <= 2) {
                return "grape_vine_0";
            }
            if (age <= 6) {
                return "grape_vine_1";
            }
            return "grape_vine_2";
        });
        ageModelBlock(BlockRegistry.GRAPE_LEAVES.get(), BlockStateProperties.AGE_7, age -> "grape_leaves_" + age);
        ageModelBlock(BlockRegistry.WILD_PEPPER.get(), BlockStateProperties.AGE_7, age -> "pepper_" + age);
        ageModelBlock(BlockRegistry.WILD_VANILLA.get(), BlockStateProperties.AGE_7, age -> "vanilla_" + age);

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
                texture("tatami"));

        horizontalBlock(BlockRegistry.TATAMI_SUNBURNT.get(),
                texture("tatami_tan"),
                texture("tatami_tan"),
                texture("tatami_tan"));

        facingSlabBlock(BlockRegistry.TATAMI_SLAB.get(),
                texture("tatami"),
                texture("tatami"),
                texture("tatami"));
        facingSlabBlock(BlockRegistry.TATAMI_SLAB_SUNBURNT.get(),
                texture("tatami_tan"),
                texture("tatami_tan"),
                texture("tatami_tan"));
        carpet(BlockRegistry.TATAMI_CARPET.get(), "tatami");
        carpet(BlockRegistry.TATAMI_CARPET_WAXED.get(), "tatami_ns");
        carpet(BlockRegistry.TATAMI_CARPET_TAN.get(), "tatami_tan");
        carpet(BlockRegistry.TATAMI_CARPET_TAN_WAXED.get(), "tatami_tan_ns");

        ModelFile kawaraCube = models().cubeAll("kawara_block", texture("kawara"));
        ModelFile kawaraAlterCube = models().cubeAll("kawara_block_alter", texture("kawara_alter"));
        simpleBlock(BlockRegistry.KAWARA_BLOCK.get(), kawaraCube);
        simpleBlock(BlockRegistry.KAWARA_BLOCK_ALTER.get(), kawaraAlterCube);
        stairsBlock((StairBlock) BlockRegistry.KAWARA_STAIRS.get(),
                texture("kawara"), texture("kawara"), texture("kawara"));
        stairsBlock((StairBlock) BlockRegistry.KAWARA_STAIRS_ALTER.get(),
                texture("kawara_alter"), texture("kawara_alter"), texture("kawara_alter"));
        ModelFile kawaraSlab = models().slab("kawara_slab",
                texture("kawara"), texture("kawara"), texture("kawara"));
        ModelFile kawaraSlabTop = models().slabTop("kawara_slab_top",
                texture("kawara"), texture("kawara"), texture("kawara"));
        ModelFile kawaraAlterSlab = models().slab("kawara_slab_alter",
                texture("kawara_alter"), texture("kawara_alter"), texture("kawara_alter"));
        ModelFile kawaraAlterSlabTop = models().slabTop("kawara_slab_alter_top",
                texture("kawara_alter"), texture("kawara_alter"), texture("kawara_alter"));
        slabBlock((SlabBlock) BlockRegistry.KAWARA_SLAB.get(),
                kawaraSlab, kawaraSlabTop, kawaraCube);
        slabBlock((SlabBlock) BlockRegistry.KAWARA_SLAB_ALTER.get(),
                kawaraAlterSlab, kawaraAlterSlabTop, kawaraAlterCube);
        simpleBlockItem(BlockRegistry.KAWARA.get(), models().getExistingFile(modLoc("block/kawara")));
        simpleBlockItem(BlockRegistry.KAWARA_BLOCK_ALTER.get(),
                models().getExistingFile(modLoc("block/kawara_block_alter")));
        simpleBlockItem(BlockRegistry.KAWARA_STAIRS.get(), models().getExistingFile(modLoc("block/kawara_stairs")));
        simpleBlockItem(BlockRegistry.KAWARA_STAIRS_ALTER.get(),
                models().getExistingFile(modLoc("block/kawara_stairs_alter")));
        simpleBlockItem(BlockRegistry.KAWARA_SLAB.get(), models().getExistingFile(modLoc("block/kawara_slab")));
        simpleBlockItem(BlockRegistry.KAWARA_SLAB_ALTER.get(),
                models().getExistingFile(modLoc("block/kawara_slab_alter")));

        simpleBlock(BlockRegistry.STONE_LANTERN.get(), models().getExistingFile(modLoc("block/stone_lantern")));
        simpleBlock(BlockRegistry.COBBLESTONE_LANTERN.get(),
                models().getExistingFile(modLoc("block/cobblestone_lantern")));
        simpleBlock(BlockRegistry.MOSSY_STONE_LANTERN.get(),
                models().getExistingFile(modLoc("block/mossy_stone_lantern")));
        simpleBlock(BlockRegistry.RED_LANTERN.get(), models().getExistingFile(modLoc("block/red_lantern")));
        simpleBlock(BlockRegistry.WHITE_LANTERN.get(), models().getExistingFile(modLoc("block/white_lantern")));
        simpleBlock(BlockRegistry.BAMBOO_LANTERN.get(), models().getExistingFile(modLoc("block/bamboo_lantern")));
        simpleBlock(BlockRegistry.WINDBELL.get(), models().getExistingFile(modLoc("block/windbell")));
        simpleBlock(BlockRegistry.ANDON.get(), models().getExistingFile(modLoc("block/andon")));
        simpleBlock(BlockRegistry.ZABUTON.get(), models().getExistingFile(modLoc("block/zabuton")));
        futon(BlockRegistry.FUTON.get());
        simpleBlock(BlockRegistry.TAIKO.get(), models().getExistingFile(modLoc("block/taiko")));

        customFenceBlock(
                (FenceBlock) BlockRegistry.BAMBOO_FENCE.get(),
                "bamboo",
                "bamboo_fence");
        customFenceBlock(
                (FenceBlock) BlockRegistry.BAMBOO_FENCE_SUNBURNT.get(),
                "bamboo_sunburnt",
                "bamboo_fence_sunburnt");
        doorBlockWithRenderType((DoorBlock) BlockRegistry.BAMBOO_DOOR.get(),
                texture("bamboo_door_upper"), texture("bamboo_door_upper"), "cutout");

        shoji(BlockRegistry.SHOJI.get());
        shoji(BlockRegistry.SHOJI_1.get());
        shoji(BlockRegistry.SHOJI_2.get());
        shoji(BlockRegistry.SHOJI_3.get());
        shoji(BlockRegistry.SHOJI_4.get());
        shoji(BlockRegistry.SHOJI_5.get());
        noren(BlockRegistry.NOREN_WHITE.get(), "noren_white");
        noren(BlockRegistry.NOREN_BLUE.get(), "noren_blue");
        noren(BlockRegistry.NOREN_PINK.get(), "noren_pink");

        getVariantBuilder(BlockRegistry.TATARA.get()).forAllStates(state -> {
            boolean lit = state.getValue(TataraBlock.LIT);
            int timer = state.getValue(TataraBlock.TIMER);
            String modelName = !lit ? "tatara" : (timer >= 3 ? "tatara_finished" : "tatara_smelting");
            return ConfiguredModel.builder()
                    .modelFile(models().cubeAll(modelName, texture(modelName)))
                    .build();
        });

        teishoku(BlockRegistry.TEISHOUKU_FISH_COOKED.get(), "teishoku_fish_cooked", 3);
        teishoku(BlockRegistry.TEISHOUKU_FISH_RAW.get(), "teishoku_fish_raw", 3);
        teishoku(BlockRegistry.TEISHOUKU_FISH_SALT.get(), "teishoku_fish_salt", 3);
        teishoku(BlockRegistry.TEISHOKO_TAMAGOYAKI.get(), "teishoku_tamagoyaki", 3);
        teishoku(BlockRegistry.TEISHOKO_YAKINIKU.get(), "teishoku_yakiniku", 3);
        teishoku(BlockRegistry.TEISHOKU_TEMPURA.get(), "teishoku_tempura", 3);
        teishoku(BlockRegistry.TEISHOKU_FRIED.get(), "teishoku_fried", 3);
        teishoku(BlockRegistry.TEISHOKU_KATSU.get(), "teishoku_katsu", 3);
        teishoku(BlockRegistry.TEISHOKU_BURGER.get(), "teishoku_burger", 3);
    }

    private void log(RotatedPillarBlock block) {
        String name = name(block);
        axisBlock(block, texture(name), texture(name + "_top"));
    }

    private void stageBlock(Block crop, IntegerProperty ageProperty) {
        String cropName = name(crop);
        getVariantBuilder(crop).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(models().withExistingParent(
                        cropName + "_stage" + state.getValue(ageProperty),
                        modLoc("block/crop_lowered"))
                        .texture("crop", texture(cropName + "_stage" + state.getValue(ageProperty))))
                .build());
    }

    private void ageModelBlock(Block block, IntegerProperty ageProperty, IntFunction<String> modelNameByAge) {
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(
                        models().getExistingFile(modLoc("block/" + modelNameByAge.apply(state.getValue(ageProperty)))))
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

    private void carpet(Block block, String texture) {
        String name = name(block);
        simpleBlock(block, models().carpet(name, modLoc("block/" + texture)));
    }

    private void noren(Block block, String modelName) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = ((int) facing.toYRot() + 180) % 360;
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/" + modelName)))
                    .rotationY(yRot)
                    .build();
        });
    }

    private void shoji(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = ((int) facing.toYRot() + 180) % 360;
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/" + name(block) + "_empty")))
                    .rotationY(yRot)
                    .build();
        });
    }

    private void futon(Block block) {
        ModelFile foot = models().getExistingFile(modLoc("block/futon_bottom"));
        ModelFile head = models().getExistingFile(modLoc("block/futon_top"));
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(FutonBlock.FACING);
            int yRot = switch (facing) {
                case NORTH -> 180;
                case EAST -> 270;
                case WEST -> 90;
                default -> 0;
            };
            ModelFile model = state.getValue(FutonBlock.PART) == FutonBlock.BedPart.HEAD ? head : foot;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void chestnutBurr(Block block) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(ChestnutBurrBlock.AGE);
            String modelName = switch (age) {
                case 1, 2, 3 -> "chestnut_burrs_" + age;
                default -> "chestnut_burr_0";
            };
            ModelFile model = models().cross(modelName, texture(modelName)).renderType("cutout");
            return ConfiguredModel.builder().modelFile(model).build();
        });
    }

    private void umeLeaves(Block block) {
        ModelFile defaultLeaves = models().cubeAll("umeleaves", texture("ume_leaves"));
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(UmeLeavesBlock.AGE);
            String model = switch (age) {
                case 3 -> "ume_leave_3";
                case 4 -> "ume_leave_4";
                case 5 -> "ume_leave_5";
                default -> "umeleaves";
            };
            ModelFile modelFile = "umeleaves".equals(model)
                    ? defaultLeaves
                    : models().getExistingFile(modLoc("block/" + model));
            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }

    private void customFenceBlock(FenceBlock block, String postModelName, String sideModelName) {
        ModelFile postModel = models().getExistingFile(modLoc("block/" + postModelName));
        ModelFile sideModel = models().getExistingFile(modLoc("block/" + sideModelName));

        getMultipartBuilder(block)
                .part().modelFile(postModel).addModel().end()
                .part().modelFile(sideModel).addModel().condition(FenceBlock.NORTH, true).end()
                .part().modelFile(sideModel).rotationY(90).addModel().condition(FenceBlock.EAST, true).end()
                .part().modelFile(sideModel).rotationY(180).addModel().condition(FenceBlock.SOUTH, true).end()
                .part().modelFile(sideModel).rotationY(270).addModel().condition(FenceBlock.WEST, true).end();
    }

    private void teishoku(Block block, String baseModel, int maxBites) {
        getVariantBuilder(block).forAllStates(state -> {
            int bites = state.getValue(TeishokuBlock.BITES);
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = ((int) facing.toYRot() + 180) % 360;
            int modelBite = Math.min(bites, maxBites);
            String modelName = modelBite > 0 ? baseModel + "_" + modelBite : baseModel;
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/" + modelName)))
                    .rotationY(yRot)
                    .build();
        });
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + baseModel)));
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private ResourceLocation texture(String name) {
        return ResourceLocation.fromNamespaceAndPath(modid, "block/" + name);
    }

}
