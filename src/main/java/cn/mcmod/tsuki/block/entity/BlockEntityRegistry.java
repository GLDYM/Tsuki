package cn.mcmod.tsuki.block.entity;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CookingPotBlockEntity>> COOKING_POT = BLOCK_ENTITIES.register(
            "cooking_pot",
            () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, BlockRegistry.COOKING_POT.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FermenterBlockEntity>> FERMENTER = BLOCK_ENTITIES.register(
            "fermenter",
            () -> BlockEntityType.Builder.of(FermenterBlockEntity::new, BlockRegistry.FERMENTER.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DistillerBlockEntity>> DISTILLER = BLOCK_ENTITIES.register(
            "distiller",
            () -> BlockEntityType.Builder.of(DistillerBlockEntity::new, BlockRegistry.DISTILLER.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ObonBlockEntity>> OBON = BLOCK_ENTITIES.register(
            "obon",
            () -> BlockEntityType.Builder.of(ObonBlockEntity::new, BlockRegistry.OBON.get()).build(null));
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChoppingBoardBlockEntity>> CHOPPING_BOARD = BLOCK_ENTITIES.register(
            "chopping_board",
            () -> BlockEntityType.Builder.of(ChoppingBoardBlockEntity::new, BlockRegistry.CHOPPING_BOARD.get()).build(null));
}


