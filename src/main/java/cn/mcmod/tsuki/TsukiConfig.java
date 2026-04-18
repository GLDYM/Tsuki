package cn.mcmod.tsuki;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TsukiConfig {
    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec.IntValue IRON_SAND_AMOUNT;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");
        IRON_SAND_AMOUNT = COMMON_BUILDER
                .comment("Changes generate amount of Iron Sand. Increase value to gen more Iron Sand.")
                .defineInRange("iron_sand_amount", 128, 1, 5120);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
