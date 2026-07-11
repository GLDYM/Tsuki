package cn.mcmod.tsuki.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class TsukiCommonConfig {
    public static final TsukiCommonConfig INSTANCE = new TsukiCommonConfig();
    public static final ModConfigSpec SPEC = INSTANCE.spec;
    public static final ModConfigSpec.IntValue IRON_SAND_AMOUNT = INSTANCE.ironSandAmount;
    public static final ModConfigSpec.IntValue MYTHIC_PICKAXE_EXP_NEEDED = INSTANCE.mythicPickaxeExpNeeded;
    public static final ModConfigSpec.BooleanValue MYTHIC_PICKAXE_ALLOW_BASIC_ENCHANT_UPGRADE = INSTANCE.mythicPickaxeAllowBasicEnchantUpgrade;
    public static final ModConfigSpec.BooleanValue GIVE_GUIDE_ON_FIRST_LOGIN = INSTANCE.giveGuideOnFirstLogin;
    public static final ModConfigSpec.BooleanValue MAGATAMA_WHITE_ENABLE_PENALTY = INSTANCE.magatamaWhiteEnablePenalty;
    public static final ModConfigSpec.DoubleValue MAGATAMA_WHITE_PENALTY_HEALTH = INSTANCE.magatamaWhitePenaltyHealth;
    public static final ModConfigSpec.DoubleValue MAGATAMA_BLUE_HEALTH_COST = INSTANCE.magatamaBlueHealthCost;
    public static final ModConfigSpec.DoubleValue MAGATAMA_GREEN_REMAINING_HEALTH = INSTANCE.magatamaGreenRemainingHealth;
    public static final ModConfigSpec.IntValue MAGATAMA_GREEN_DRAW_COUNT = INSTANCE.magatamaGreenDrawCount;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> MAGATAMA_GREEN_REWARD_TAGS = INSTANCE.magatamaGreenRewardTags;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> MAGATAMA_RED_TARGET_ENTITIES = INSTANCE.magatamaRedTargetEntities;
    public static final ModConfigSpec.BooleanValue MAGATAMA_WHITE_ENABLE_MINING_SPEED_COMPENSATION = INSTANCE.magatamaWhiteEnableMiningSpeedCompensation;
    public static final ModConfigSpec.DoubleValue MAGATAMA_WHITE_MINING_SPEED_COMPENSATION_MULTIPLIER = INSTANCE.magatamaWhiteMiningSpeedCompensationMultiplier;
    public static final ModConfigSpec.BooleanValue DEBUG_MODE = INSTANCE.debugMode;

    public final ModConfigSpec spec;
    public final ModConfigSpec.IntValue ironSandAmount;
    public final ModConfigSpec.IntValue mythicPickaxeExpNeeded;
    public final ModConfigSpec.BooleanValue mythicPickaxeAllowBasicEnchantUpgrade;
    public final ModConfigSpec.BooleanValue giveGuideOnFirstLogin;
    public final ModConfigSpec.BooleanValue magatamaWhiteEnablePenalty;
    public final ModConfigSpec.DoubleValue magatamaWhitePenaltyHealth;
    public final ModConfigSpec.DoubleValue magatamaBlueHealthCost;
    public final ModConfigSpec.DoubleValue magatamaGreenRemainingHealth;
    public final ModConfigSpec.IntValue magatamaGreenDrawCount;
    public final ModConfigSpec.ConfigValue<List<? extends String>> magatamaGreenRewardTags;
    public final ModConfigSpec.ConfigValue<List<? extends String>> magatamaRedTargetEntities;
    public final ModConfigSpec.BooleanValue magatamaWhiteEnableMiningSpeedCompensation;
    public final ModConfigSpec.DoubleValue magatamaWhiteMiningSpeedCompensationMultiplier;
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


        magatamaBlueHealthCost = builder
                .comment("Sets the Blue Magatama health cost per weather conversion.")
                .translation("tsuki.config.magatama_blue_health_cost")
                .defineInRange("magatama_blue_health_cost", 4.0D, 0.0D, 1024.0D);

        magatamaGreenRemainingHealth = builder
                .comment("Sets the remaining health after using the Green Magatama.")
                .translation("tsuki.config.magatama_green_remaining_health")
                .defineInRange("magatama_green_remaining_health", 1.0D, 0.1D, 1024.0D);

        magatamaGreenDrawCount = builder
                .comment("Sets how many crop or seed drops the Green Magatama draws per use.")
                .translation("tsuki.config.magatama_green_draw_count")
                .defineInRange("magatama_green_draw_count", 32, 1, 1024);

        magatamaGreenRewardTags = builder
                .comment("Sets the item tag list used by the Green Magatama reward pool.")
                .translation("tsuki.config.magatama_green_reward_tags")
                .defineListAllowEmpty(List.of("magatama_green_reward_tags"), () -> List.of("c:seeds",  "c:fishes", "c:raw_meat", "c:raw_meats", "c:eggs", "c:milk", "c:vegetables", "c:fruits", "c:crops", "c:mushrooms"),
                        () -> "c:seeds",
                        value -> value instanceof String string && !string.isBlank());

        magatamaRedTargetEntities = builder
                .comment("Sets the entity id list executed by the Red Magatama.")
                .translation("tsuki.config.magatama_red_target_entities")
                .defineListAllowEmpty(List.of("magatama_red_target_entities"), () -> List.of(
                        "minecraft:phantom",
                        "alexsmobs:seagull",
                        "alexsmobs:crimson_mosquit",
                        "naturalist:vulture",
                        "iceandfire:if_pixie",
                        "touhou_little_maid:fairy"),
                        () -> "minecraft:phantom",
                        value -> value instanceof String string && !string.isBlank());

        magatamaWhiteEnablePenalty = builder
                .comment("Enables the White Magatama max health penalty.")
                .translation("tsuki.config.magatama_white_enable_penalty")
                .define("magatama_white_enable_penalty", true);

        magatamaWhitePenaltyHealth = builder
                .comment("Sets the White Magatama target max health while the penalty is active.")
                .translation("tsuki.config.magatama_white_penalty_health")
                .defineInRange("magatama_white_penalty_health", 10.0D, 1.0D, 1024.0D);

        magatamaWhiteEnableMiningSpeedCompensation = builder
                .comment("Enables the White Magatama airborne mining speed compensation.")
                .translation("tsuki.config.magatama_white_enable_mining_speed_compensation")
                .define("magatama_white_enable_mining_speed_compensation", true);

        magatamaWhiteMiningSpeedCompensationMultiplier = builder
                .comment("Sets the White Magatama airborne mining speed compensation multiplier.")
                .translation("tsuki.config.magatama_white_mining_speed_compensation_multiplier")
                .defineInRange("magatama_white_mining_speed_compensation_multiplier", 5.0D, 1.0D, 64.0D);

        debugMode = builder
                .comment("Enables debug mode.")
                .translation("tsuki.config.debug_mode")
                .define("debug_mode", false);

        builder.pop();
        spec = builder.build();
    }
}
