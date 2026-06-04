package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.entity.KakezikuEntity;
import cn.mcmod.tsuki.entity.SamuraiIllagerEntity;
import cn.mcmod.tsuki.entity.SeatEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister
            .create(Registries.ENTITY_TYPE, Tsuki.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<SamuraiIllagerEntity>> SAMURAI_ILLAGER = ENTITY_TYPES
            .register(
                    "samurai_illager",
                    () -> EntityType.Builder.of(SamuraiIllagerEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .clientTrackingRange(90)
                            .build("tsuki:samurai_illager"));

    public static final DeferredHolder<EntityType<?>, EntityType<SeatEntity>> SEAT = ENTITY_TYPES.register("seat",
            () -> EntityType.Builder.<SeatEntity>of(SeatEntity::new, MobCategory.MISC)
                    .sized(0.01F, 0.01F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("tsuki:seat"));

    public static final DeferredHolder<EntityType<?>, EntityType<KakezikuEntity>> KAKEZIKU = ENTITY_TYPES.register(
            "kakeziku",
            () -> EntityType.Builder.<KakezikuEntity>of(KakezikuEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
                    .build("tsuki:kakeziku"));

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(SAMURAI_ILLAGER.get(), SamuraiIllagerEntity.createAttributes().build());
    }

    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(SAMURAI_ILLAGER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkAnyLightMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
