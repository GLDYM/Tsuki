package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.sounds.SoundEvent;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT,
            Tsuki.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> MIKO = SOUND_EVENTS.register("music_disc.miko",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "music_disc.miko")));

    public static final DeferredHolder<SoundEvent, SoundEvent> TAIKO = SOUND_EVENTS.register("taiko",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "taiko")));
}
