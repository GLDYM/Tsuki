package cn.mcmod.tsuki;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TsukiConfig {
    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec.IntValue IRON_SAND_AMOUNT;
    public static ModConfigSpec.IntValue MYTHIC_PICKAXE_EXP_NEEDED;
    public static ModConfigSpec.BooleanValue DEBUG_MODE;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");

        IRON_SAND_AMOUNT = COMMON_BUILDER
                .comment("Changes generate amount of Iron Sand. Increase value to gen more Iron Sand.")
                .defineInRange("iron_sand_amount", 128, 1, 5120);

        MYTHIC_PICKAXE_EXP_NEEDED = COMMON_BUILDER
                .comment("Changes the experience needed to upgrade Mythic Pickaxe. Increase value to make it harder to upgrade.")
                .defineInRange("mythic_pickaxe_exp_needed", 10000, 1, Integer.MAX_VALUE);

        DEBUG_MODE = COMMON_BUILDER
                .comment("Enables debug mode.")
                .define("debug_mode", false);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
