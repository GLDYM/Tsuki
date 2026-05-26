package cn.mcmod.tsuki.compat.terrablender;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public final class TsukiTerraBlenderCompat {
    private TsukiTerraBlenderCompat() {
    }

    public static void register() {
        Regions.register(new TsukiOverworldRegion(
                ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "overworld"),
                5));
    }
}
