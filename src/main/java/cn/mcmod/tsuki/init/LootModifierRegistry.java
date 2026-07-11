package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.loot.FishingCatchLootModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModifierRegistry {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister
            .create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Tsuki.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<FishingCatchLootModifier>> FISHING_CATCH = LOOT_MODIFIER_SERIALIZERS
            .register("fishing_catch", () -> FishingCatchLootModifier.CODEC);
}
