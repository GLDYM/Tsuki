package cn.mcmod.tsuki.init;

import com.mojang.serialization.MapCodec;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.loot.AddLootTableModifier;
import cn.mcmod.tsuki.loot.FishingModifiter;
import cn.mcmod.tsuki.loot.SeedDropModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModifiterRegistry {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLM = DeferredRegister
            .create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Tsuki.MODID);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> SEEDSDROP = GLM
            .register("grass_drops",
                    () -> SeedDropModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> FISHING = GLM
            .register("fishing_modifiter",
                    () -> FishingModifiter.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE = GLM
            .register("add_loot_table",
                    () -> AddLootTableModifier.CODEC);
}
