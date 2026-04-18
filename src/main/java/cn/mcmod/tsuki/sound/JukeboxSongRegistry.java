package cn.mcmod.tsuki.sound;

import cn.mcmod.tsuki.Tsuki;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;

public class JukeboxSongRegistry {
    public static final ResourceKey<JukeboxSong> DISC_MUSIC_MIKO = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "miko"));

    public static void register() {
        // fuck.
    }
}
