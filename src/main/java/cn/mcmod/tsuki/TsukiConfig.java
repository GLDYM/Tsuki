package cn.mcmod.tsuki;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TsukiConfig {
    public static ModConfigSpec COMMON_CONFIG;


    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}

