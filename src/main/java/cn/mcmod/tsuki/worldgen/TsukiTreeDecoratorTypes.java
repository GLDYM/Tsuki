package cn.mcmod.tsuki.worldgen;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TsukiTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(
            Registries.TREE_DECORATOR_TYPE,
            Tsuki.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<MapleSapLogDecorator>> MAPLE_SAP_LOG = TREE_DECORATOR_TYPES
            .register("maple_sap_log",
                    () -> new TreeDecoratorType<>(MapleSapLogDecorator.CODEC));

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<MapleFallenLeavesDecorator>> MAPLE_FALLEN_LEAVES = TREE_DECORATOR_TYPES
            .register("maple_fallen_leaves",
                    () -> new TreeDecoratorType<>(MapleFallenLeavesDecorator.CODEC));

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<ChestnutBurrDecorator>> CHESTNUT_BURR = TREE_DECORATOR_TYPES
            .register("chestnut_burr",
                    () -> new TreeDecoratorType<>(ChestnutBurrDecorator.CODEC));
}
