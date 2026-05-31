package cn.mcmod.tsuki.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TsukiCommonConfig {
    public static final TsukiCommonConfig INSTANCE = new TsukiCommonConfig();
    public static final ModConfigSpec SPEC = INSTANCE.spec;
    public static final ModConfigSpec.IntValue IRON_SAND_AMOUNT = INSTANCE.ironSandAmount;
    public static final ModConfigSpec.IntValue MYTHIC_PICKAXE_EXP_NEEDED = INSTANCE.mythicPickaxeExpNeeded;
    public static final ModConfigSpec.BooleanValue MYTHIC_PICKAXE_ALLOW_BASIC_ENCHANT_UPGRADE = INSTANCE.mythicPickaxeAllowBasicEnchantUpgrade;
    public static final ModConfigSpec.BooleanValue GIVE_GUIDE_ON_FIRST_LOGIN = INSTANCE.giveGuideOnFirstLogin;
    public static final ModConfigSpec.BooleanValue MAGATAMA_WHITE_ENABLE_MINING_SPEED_AMPLIFIER = INSTANCE.magatamaWhiteEnableMiningSpeedAmplifier;
    public static final ModConfigSpec.IntValue MAGATAMA_WHITE_MINING_SPEED_AMPLIFIER = INSTANCE.magatamaWhiteMiningSpeedAmplifier;
    public static final ModConfigSpec.BooleanValue DEBUG_MODE = INSTANCE.debugMode;

    public final ModConfigSpec spec;
    public final ModConfigSpec.IntValue ironSandAmount;
    public final ModConfigSpec.IntValue mythicPickaxeExpNeeded;
    public final ModConfigSpec.BooleanValue mythicPickaxeAllowBasicEnchantUpgrade;
    public final ModConfigSpec.BooleanValue giveGuideOnFirstLogin;
    public final ModConfigSpec.BooleanValue magatamaWhiteEnableMiningSpeedAmplifier;
    public final ModConfigSpec.IntValue magatamaWhiteMiningSpeedAmplifier;
    public final ModConfigSpec.BooleanValue debugMode;

    public TsukiCommonConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("General settings")
                .translation("tsuki.config.category.general")
                .push("general");

        ironSandAmount = builder
                .comment("Changes generate amount of Iron Sand. Increase value to gen more Iron Sand.")
                .translation("tsuki.config.iron_sand_amount")
                .defineInRange("iron_sand_amount", 128, 1, 5120);

        mythicPickaxeExpNeeded = builder
                .comment(
                        "Changes the experience needed to upgrade Mythic Pickaxe. Increase value to make it harder to upgrade.")
                .translation("tsuki.config.mythic_pickaxe_exp_needed")
                .defineInRange("mythic_pickaxe_exp_needed", 10000, 1, Integer.MAX_VALUE);

        mythicPickaxeAllowBasicEnchantUpgrade = builder
                .comment("Allows Mythic Pickaxe to upgrade Fortune or Silk Touch when it has neither enchantment.")
                .translation("tsuki.config.mythic_pickaxe_allow_basic_enchant_upgrade")
                .define("mythic_pickaxe_allow_basic_enchant_upgrade", true);

        giveGuideOnFirstLogin = builder
                .comment(
                        "Gives the Tsuki guide book to players the first time they join a world when GuideME is installed.")
                .translation("tsuki.config.give_guide_on_first_login")
                .define("give_guide_on_first_login", true);

        magatamaWhiteEnableMiningSpeedAmplifier = builder
                .comment("Enables the White Magatama mining speed amplifier while flying.")
                .translation("tsuki.config.magatama_white_enable_mining_speed_amplifier")
                .define("magatama_white_enable_mining_speed_amplifier", true);

        magatamaWhiteMiningSpeedAmplifier = builder
                .comment("Sets the White Magatama mining speed amplifier while flying.")
                .translation("tsuki.config.magatama_white_mining_speed_amplifier")
                .defineInRange("magatama_white_mining_speed_amplifier", 25, 1, 255);

        debugMode = builder
                .comment("Enables debug mode.")
                .translation("tsuki.config.debug_mode")
                .define("debug_mode", false);

        builder.pop();
        spec = builder.build();
    }
}
