package cn.mcmod.tsuki.loot_modifier;

import com.mojang.serialization.MapCodec;

import cn.mcmod.tsuki.Tsuki;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModifiterRegistry {
        public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLM = DeferredRegister
            .create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Tsuki.MODID);
        public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> SEEDSDROP = GLM.register("grass_drops",
            () -> SeedsDrop.SeedDropModifier.CODEC);
        public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> FISHING = GLM.register("fishing_modifiter",
            () -> FishingModifiter.CODEC);
}


