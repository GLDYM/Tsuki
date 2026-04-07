package cn.mcmod.tsuki;

import net.minecraftforge.common.ForgeConfigSpec;

public class TsukiConfig {
    public static ForgeConfigSpec COMMON_CONFIG;


    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
