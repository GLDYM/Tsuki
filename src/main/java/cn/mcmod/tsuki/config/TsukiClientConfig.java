package cn.mcmod.tsuki.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TsukiClientConfig {
    public static final TsukiClientConfig INSTANCE = new TsukiClientConfig();
    public static final ModConfigSpec SPEC = INSTANCE.spec;
    public static final ModConfigSpec.BooleanValue ALLOW_COOKING_POT_INPUT_STACKING = INSTANCE.allowCookingPotInputStacking;

    public final ModConfigSpec spec;
    public final ModConfigSpec.BooleanValue allowCookingPotInputStacking;

    public TsukiClientConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("Client settings")
                .translation("tsuki.config.category.client")
                .push("client");

        allowCookingPotInputStacking = builder
                .comment("Allow inserting cooking pot input items into occupied input slots.")
                .translation("tsuki.config.allow_cooking_pot_input_stacking")
                .define("allow_cooking_pot_input_stacking", false);

        builder.pop();
        spec = builder.build();
    }
}
