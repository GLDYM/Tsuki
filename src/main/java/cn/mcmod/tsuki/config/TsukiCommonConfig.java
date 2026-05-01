package cn.mcmod.tsuki.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TsukiCommonConfig {
    public static final TsukiCommonConfig INSTANCE = new TsukiCommonConfig();
    public static final ModConfigSpec SPEC = INSTANCE.spec;
    public static final ModConfigSpec.IntValue IRON_SAND_AMOUNT = INSTANCE.ironSandAmount;
    public static final ModConfigSpec.IntValue MYTHIC_PICKAXE_EXP_NEEDED = INSTANCE.mythicPickaxeExpNeeded;
    public static final ModConfigSpec.BooleanValue DEBUG_MODE = INSTANCE.debugMode;

    public final ModConfigSpec spec;
    public final ModConfigSpec.IntValue ironSandAmount;
    public final ModConfigSpec.IntValue mythicPickaxeExpNeeded;
    public final ModConfigSpec.BooleanValue debugMode;

    public TsukiCommonConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("General settings").push("general");

        ironSandAmount = builder
                .comment("Changes generate amount of Iron Sand. Increase value to gen more Iron Sand.")
                .defineInRange("iron_sand_amount", 128, 1, 5120);

        mythicPickaxeExpNeeded = builder
                .comment("Changes the experience needed to upgrade Mythic Pickaxe. Increase value to make it harder to upgrade.")
                .defineInRange("mythic_pickaxe_exp_needed", 10000, 1, Integer.MAX_VALUE);

        debugMode = builder
                .comment("Enables debug mode.")
                .define("debug_mode", false);

        builder.pop();
        spec = builder.build();
    }
}
