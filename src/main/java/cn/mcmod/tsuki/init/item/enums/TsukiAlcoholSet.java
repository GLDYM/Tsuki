package cn.mcmod.tsuki.init.item.enums;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public enum TsukiAlcoholSet {
    GLASS_BEER("glass_beer",
            new MobEffectInstance(MobEffects.SATURATION, 8, 0)),
    GLASS_DOBUROKU("glass_doburoku",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 0)),
    GLASS_SAKE("glass_sake",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2000, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 500, 0)),
    GLASS_SHOUCHU("glass_shouchu",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 1000, 0)),
    GLASS_RED_WINE("glass_red_wine",
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0)),
    GLASS_WHITE_WINE("glass_white_wine",
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0)),
    GLASS_CHAMPAGNE("glass_champagne",
            new MobEffectInstance(MobEffects.NIGHT_VISION, 3000, 1)),
    GLASS_RUM("glass_rum",
            new MobEffectInstance(MobEffects.WATER_BREATHING, 2000, 0)),
    GLASS_VODKA("glass_vodka",
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 0)),
    GLASS_WHISKEY("glass_whiskey",
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)),
    GLASS_BRANDY("glass_brandy",
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)),
    GLASS_GIN("glass_gin",
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)),
    GLASS_TEQUILA("glass_tequila",
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0)),
    GLASS_LIQUEUR("glass_liqueur",
            new MobEffectInstance(MobEffects.JUMP, 2000, 0)),
    GLASS_COCOA_LIQUEUR("glass_cocoa_liqueur",
            new MobEffectInstance(MobEffects.JUMP, 2000, 0));

    private final String name;
    private final MobEffectInstance[] effects;

    TsukiAlcoholSet(String name, MobEffectInstance... effects) {
        this.name = name;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public MobEffectInstance[] getEffects() {
        return effects;
    }
}
