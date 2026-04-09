package cn.mcmod.tsuki.level.tree;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TsukiTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(
            Registries.TREE_DECORATOR_TYPE,
            Tsuki.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<MapleSapLogDecorator>> MAPLE_SAP_LOG =
            TREE_DECORATOR_TYPES.register("maple_sap_log",
                    () -> new TreeDecoratorType<>(MapleSapLogDecorator.CODEC));
}
