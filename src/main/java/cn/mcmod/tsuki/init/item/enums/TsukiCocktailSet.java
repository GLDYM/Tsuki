package cn.mcmod.tsuki.init.item.enums;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.function.Supplier;

import cn.mcmod.tsuki.init.MobEffectRegistry;

public enum TsukiCocktailSet {
    GLASS_KIR("glass_kir", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.EXP_UP, 1000, 0)
    }),
    GLASS_KIR_ROYALE("glass_kir_royale", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 4000, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 2000, 0)
    }),
    GLASS_CASSIS_ORANGE("glass_cassis_orange", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0)
    }),
    GLASS_CASSIS_SODA("glass_cassis_soda", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0),
            new MobEffectInstance(MobEffects.JUMP, 2000, 0)
    }),
    GLASS_MIMOSA("glass_mimosa", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3000, 0),
            new MobEffectInstance(MobEffects.JUMP, 2000, 0)
    }),
    GLASS_SHANDY_GAFF("glass_shandy_gaff", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.SATURATION, 8, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0)
    }),
    GLASS_RED_EYE("glass_red_eye", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0),
            new MobEffectInstance(MobEffects.SATURATION, 4, 0)
    }),
    GLASS_SANGRIA("glass_sangria", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 3000, 0),
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000, 0)
    }),
    GLASS_KALIMOTXO("glass_kalimotxo", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0)
    }),
    GLASS_KITTY("glass_kitty", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0),
            new MobEffectInstance(MobEffects.LUCK, 2000, 0)
    }),
    GLASS_OPERATOR("glass_operator", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0),
            new MobEffectInstance(MobEffects.ABSORPTION, 2000, 0)
    }),
    GLASS_AMERICANO("glass_americano", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.SATURATION, 12, 0),
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)
    }),
    GLASS_GIN_TONIC("glass_gin_tonic", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0)
    }),
    GLASS_GIN_FIZZ("glass_gin_fizz", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0),
            new MobEffectInstance(MobEffects.JUMP, 2000, 0)
    }),
    GLASS_GIMLET("glass_gimlet", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0),
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)
    }),
    GLASS_SPRITZER("glass_spritzer", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0)
    }),
    GLASS_MARTINI("glass_martini", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 4000, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 0)
    }),
    GLASS_ALEXANDER("glass_alexander", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.REGENERATION, 1000, 0),
            new MobEffectInstance(MobEffects.HUNGER, 1000, 1)
    }),
    GLASS_BELLINI("glass_bellini", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 4000, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 1000, 0)
    }),
    GLASS_SCREWDRIVER("glass_screwdriver", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0)
    }),
    GLASS_SALTY_DOG("glass_salty_dog", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3000, 0),
            new MobEffectInstance(MobEffects.WATER_BREATHING, 1000, 0)
    }),
    GLASS_MATADOR("glass_matador", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0),
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)
    }),
    GLASS_HOT_TODDY("glass_hot_toddy", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0)
    }),
    GLASS_HOT_BUTTERED_RUM("glass_hot_buttered_rum", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.WATER_BREATHING, 3000, 0),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0)
    }),
    GLASS_GROG("glass_grog", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.WATER_BREATHING, 3000, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 0)
    }),
    GLASS_CUBA_LIBRE("glass_cuba_libre", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.WATER_BREATHING, 2000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3000, 0)
    }),
    GLASS_PINA_COLADA("glass_pina_colada", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.WATER_BREATHING, 2000, 0),
            new MobEffectInstance(MobEffects.ABSORPTION, 2000, 0)
    }),
    GLASS_DAIQUIRI("glass_daiquiri", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.WATER_BREATHING, 1000, 0),
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 0)
    }),
    GLASS_HIGHBALL("glass_highball", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)
    }),
    GLASS_MOSCOW_MULE("glass_moscow_mule", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0),
            new MobEffectInstance(MobEffects.SATURATION, 8, 0)
    }),
    GLASS_TEQUILA_SUNRISE("glass_tequila_sunrise", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.HEAL, 3, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0)
    }),
    GLASS_MARGARITA("glass_margarita", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0)
    }),
    GLASS_NEGRONI("glass_negroni", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.GOLDEN_HEART, 500, 0)
    }),
    GLASS_BLACK_RUSSIAN("glass_black_russian", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.FIRE_BLADE, 1000, 0)
    }),
    GLASS_GODFATHER("glass_godfather", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.EXP_UP, 1000, 0)
    }),
    GLASS_GODMOTHER("glass_godmother", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.EXP_UP, 1000, 0)
    }),
    GLASS_SIDECAR("glass_sidecar", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.GOLDEN_HEART, 500, 0)
    }),
    GLASS_BLOODY_MARY("glass_bloody_mary", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 1)
    }),
    GLASS_OLD_FASHIONED("glass_old_fashioned", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0)
    }),
    GLASS_WHISKEY_SOUR("glass_whiskey_sour", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0)
    }),
    GLASS_MOJITO("glass_mojito", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0)
    }),
    GLASS_RUSTY_NAIL("glass_rusty_nail", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.LUCK, 1000, 0)
    }),
    GLASS_SAKETINI("glass_saketini", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.HEAL, 3, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 1000, 0),
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 1000, 0)
    }),
    GLASS_STINGER("glass_stinger", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffectRegistry.POISONING, 1000, 0)
    }),
    GLASS_SCORPION("glass_scorpion", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffectRegistry.SCORPION, 1000, 0)
    });

    private final String name;
    private final Supplier<MobEffectInstance[]> effectsSupplier;

    TsukiCocktailSet(String name, Supplier<MobEffectInstance[]> effectsSupplier) {
        this.name = name;
        this.effectsSupplier = effectsSupplier;
    }

    public String getName() {
        return name;
    }

    public MobEffectInstance[] getEffects() {
        return effectsSupplier.get();
    }
}
