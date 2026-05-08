package cn.mcmod.tsuki.villager;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.block.BlockRegistry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class VillagerRegistry {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Tsuki.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS =
            DeferredRegister.create(Registries.VILLAGER_PROFESSION, Tsuki.MODID);

    public static final DeferredHolder<PoiType, PoiType> WA_FARMER_POI = POI_TYPES.register("wa_farmer",
            () -> new PoiType(ImmutableSet.copyOf(
                    BlockRegistry.STONE_MORTAR.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final DeferredHolder<PoiType, PoiType> WA_TRADER_POI = POI_TYPES.register("wa_trader",
            () -> new PoiType(ImmutableSet.copyOf(
                    BlockRegistry.CHOPPING_BOARD.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final DeferredHolder<VillagerProfession, VillagerProfession> WA_FARMER = PROFESSIONS.register(
            "wa_farmer",
            () -> new VillagerProfession("tsuki:wa_farmer",
                    x -> x.value() == WA_FARMER_POI.get(),
                    x -> x.value() == WA_FARMER_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_FARMER));

    public static final DeferredHolder<VillagerProfession, VillagerProfession> WA_TRADER = PROFESSIONS.register(
            "wa_trader",
            () -> new VillagerProfession("tsuki:wa_trader",
                    x -> x.value() == WA_TRADER_POI.get(),
                    x -> x.value() == WA_TRADER_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_LEATHERWORKER));

    private VillagerRegistry() {
    }
}
