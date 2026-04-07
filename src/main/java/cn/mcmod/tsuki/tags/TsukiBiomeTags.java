package cn.mcmod.tsuki.tags;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TsukiBiomeTags {
    public static final TagKey<Biome> CAN_SPAWN_BAMBOO = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "can_spawn_bamboo"));
}
