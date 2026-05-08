package cn.mcmod.tsuki.tag;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TsukiBiomeTags {
    public static final TagKey<Biome> CAN_SPAWN_BAMBOO = TagKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "can_spawn_bamboo"));
    public static final TagKey<Biome> CAN_SPAWN_SAKURA_DIAMOND_ORE = TagKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "can_spawn_sakura_diamond_ore"));
    public static final TagKey<Biome> CAN_SPAWN_IRON_SAND_ORE = TagKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "can_spawn_iron_sand_ore"));
    public static final TagKey<Biome> HAS_SAMURAI_SPAWNS = TagKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "has_samurai_spawns"));
}
