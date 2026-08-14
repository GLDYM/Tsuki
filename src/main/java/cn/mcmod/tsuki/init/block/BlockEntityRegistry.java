package cn.mcmod.tsuki.init.block;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.ChoppingBoardBlockEntity;
import cn.mcmod.tsuki.block.entity.DrinkDisplayBlockEntity;
import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.block.entity.DistillerBlockEntity;
import cn.mcmod.tsuki.block.entity.FermenterBlockEntity;
import cn.mcmod.tsuki.block.entity.MapleCauldronBlockEntity;
import cn.mcmod.tsuki.block.entity.ObonBlockEntity;
import cn.mcmod.tsuki.block.entity.ShakerBlockEntity;
import cn.mcmod.tsuki.block.entity.ShojiBlockEntity;
import cn.mcmod.tsuki.block.entity.StoneMortarBlockEntity;
import cn.mcmod.tsuki.block.entity.LighthouseIlluminationBlockEntity;
import cn.mcmod.tsuki.block.entity.SprinklerBlockEntity;
import cn.mcmod.tsuki.block.entity.SunflowerCropBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(Registries.BLOCK_ENTITY_TYPE, Tsuki.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StoneMortarBlockEntity>> STONE_MORTAR = BLOCK_ENTITIES
            .register("stone_mortar", () -> BlockEntityType.Builder
                    .of(StoneMortarBlockEntity::new, BlockRegistry.STONE_MORTAR.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LighthouseIlluminationBlockEntity>> LIGHTHOUSE_ILLUMINATION = BLOCK_ENTITIES
            .register("lighthouse_illumination", () -> BlockEntityType.Builder
                    .of(LighthouseIlluminationBlockEntity::new, BlockRegistry.LIGHTHOUSE_ILLUMINATION.get())
                    .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SprinklerBlockEntity>> SPRINKLER = BLOCK_ENTITIES
            .register("sprinkler", () -> BlockEntityType.Builder.of(SprinklerBlockEntity::new,
                    BlockRegistry.IRON_SPRINKLER.get(), BlockRegistry.GOLD_SPRINKLER.get(),
                    BlockRegistry.DIAMOND_SPRINKLER.get(), BlockRegistry.SAKURA_DIAMOND_SPRINKLER.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CookingPotBlockEntity>> COOKING_POT = BLOCK_ENTITIES
            .register(
                    "cooking_pot",
                    () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, BlockRegistry.COOKING_POT.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FermenterBlockEntity>> FERMENTER = BLOCK_ENTITIES
            .register(
                    "fermenter",
                    () -> BlockEntityType.Builder.of(FermenterBlockEntity::new, BlockRegistry.FERMENTER.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DistillerBlockEntity>> DISTILLER = BLOCK_ENTITIES
            .register(
                    "distiller",
                    () -> BlockEntityType.Builder.of(DistillerBlockEntity::new, BlockRegistry.DISTILLER.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ObonBlockEntity>> OBON = BLOCK_ENTITIES
            .register(
                    "obon",
                    () -> BlockEntityType.Builder.of(ObonBlockEntity::new, BlockRegistry.OBON.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DrinkDisplayBlockEntity>> DRINK_DISPLAY = BLOCK_ENTITIES
            .register(
                    "drink_display",
                    () -> BlockEntityType.Builder.of(DrinkDisplayBlockEntity::new, BlockRegistry.DRINK_DISPLAY.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ShakerBlockEntity>> SHAKER = BLOCK_ENTITIES
            .register(
                    "shaker",
                    () -> BlockEntityType.Builder.of(ShakerBlockEntity::new, BlockRegistry.SHAKER.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChoppingBoardBlockEntity>> CHOPPING_BOARD = BLOCK_ENTITIES
            .register(
                    "chopping_board",
                    () -> BlockEntityType.Builder.of(ChoppingBoardBlockEntity::new, BlockRegistry.CHOPPING_BOARD.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ShojiBlockEntity>> SHOJI = BLOCK_ENTITIES
            .register(
                    "shoji",
                    () -> BlockEntityType.Builder.of(
                            ShojiBlockEntity::new,
                            BlockRegistry.SHOJI.get(),
                            BlockRegistry.SHOJI_1.get(),
                            BlockRegistry.SHOJI_2.get(),
                            BlockRegistry.SHOJI_3.get(),
                            BlockRegistry.SHOJI_4.get(),
                            BlockRegistry.SHOJI_5.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MapleCauldronBlockEntity>> MAPLE_CAULDRON = BLOCK_ENTITIES
            .register(
                    "maple_cauldron",
                    () -> BlockEntityType.Builder.of(MapleCauldronBlockEntity::new, BlockRegistry.MAPLE_CAULDRON.get())
                            .build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SunflowerCropBlockEntity>> SUNFLOWER_CROP = BLOCK_ENTITIES
            .register(
                    "sunflower_crop",
                    () -> BlockEntityType.Builder.of(SunflowerCropBlockEntity::new, BlockRegistry.SUNFLOWER_CROP.get())
                            .build(null));
}
