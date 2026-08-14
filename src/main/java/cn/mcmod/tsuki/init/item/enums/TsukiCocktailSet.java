package cn.mcmod.tsuki.init.item.enums;

import cn.mcmod.tsuki.init.MobEffectRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;

public enum TsukiCocktailSet {
    GLASS_KIR("glass_kir", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0),
            new MobEffectInstance(MobEffectRegistry.EXP_UP, 1000, 0)
    }),
    GLASS_KIR_ROYALE("glass_kir_royale", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 4000, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 2000, 0)
    }),
    GLASS_CASSIS_ORANGE("glass_cassis_orange", false, () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.NIGHT_VISION, 2000, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0)
    }),
    GLASS_CASSIS_SODA("glass_cassis_soda", false, () -> new MobEffectInstance[] {
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
    GLASS_BOILERMAKER("glass_boilermaker", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0),
            new MobEffectInstance(MobEffects.SATURATION, 24, 0)
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
    }),
    GLASS_AVIATION("glass_aviation", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 0),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0)
    }),
    GLASS_BEER_MARGARITA("glass_beer_margarita", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0),
            new MobEffectInstance(MobEffects.SATURATION, 8, 0)
    }),
    GLASS_BETWEEN_THE_SHEETS("glass_between_the_sheets", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0)
    }),
    GLASS_EGGNOG("glass_eggnog", false, () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.REGENERATION, 1200, 0),
            new MobEffectInstance(MobEffects.ABSORPTION, 1200, 0)
    }),
    GLASS_FLYING_GRASSHOPPER("glass_flying_grasshopper", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.JUMP, 2400, 1),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0)
    }),
    GLASS_FRENCH_SEVENFIVE("glass_french_sevenfive", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2800, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0)
    }),
    GLASS_GRASSHOPPER("glass_grasshopper", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.JUMP, 2000, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 800, 0)
    }),
    GLASS_JOHN_COLLINS("glass_john_collins", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2200, 0),
            new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0)
    }),
    GLASS_LEMON_MARGARITA("glass_lemon_margarita", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 0),
            new MobEffectInstance(MobEffects.LUCK, 1200, 0)
    }),
    GLASS_LONG_ISLAND_ICED_TEA("glass_long_island_iced_tea", DrinkRegistry::collinsGlassContainerItem,
            () -> new MobEffectInstance[] {
                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 1),
                    new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0),
                    new MobEffectInstance(MobEffectRegistry.GOLDEN_HEART, 500, 0)
            }),
    GLASS_MINT_JULEP("glass_mint_julep", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.DIG_SPEED, 2200, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1600, 0)
    }),
    GLASS_PANACHE("glass_panache", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.SATURATION, 6, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0)
    }),
    GLASS_PARADISE("glass_paradise", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.LUCK, 2200, 0),
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0)
    }),
    GLASS_PORCHCRAWLER("glass_porchcrawler", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.CONFUSION, 600, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 0)
    }),
    GLASS_PORTO_FLIP("glass_porto_flip", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.REGENERATION, 1000, 0),
            new MobEffectInstance(MobEffects.ABSORPTION, 1000, 0)
    }),
    GLASS_RUSSIAN_SPRING("glass_russian_spring", () -> new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.JUMP, 2200, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2200, 0)
    });

    private final String name;
    private final boolean alcoholic;
    private final Supplier<Item> containerItem;
    private final Supplier<MobEffectInstance[]> effectsSupplier;

    TsukiCocktailSet(String name, boolean alcoholic, Supplier<Item> containerItem,
            Supplier<MobEffectInstance[]> effectsSupplier) {
        this.name = name;
        this.alcoholic = alcoholic;
        this.containerItem = containerItem;
        this.effectsSupplier = effectsSupplier;
    }

    TsukiCocktailSet(String name, Supplier<MobEffectInstance[]> effectsSupplier) {
        this(name, true, DrinkRegistry::glassCupContainerItem, effectsSupplier);
    }

    TsukiCocktailSet(String name, Supplier<Item> containerItem, Supplier<MobEffectInstance[]> effectsSupplier) {
        this(name, true, containerItem, effectsSupplier);
    }

    TsukiCocktailSet(String name, boolean alcoholic, Supplier<MobEffectInstance[]> effectsSupplier) {
        this(name, alcoholic, DrinkRegistry::glassCupContainerItem, effectsSupplier);
    }

    public String getName() {
        return name;
    }

    public MobEffectInstance[] getEffects() {
        return effectsSupplier.get();
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public Supplier<Item> getContainerItem() {
        return containerItem;
    }

    public Component getTooltip() {
        return Component.translatable("item.tsuki." + name + ".tooltip").withStyle(ChatFormatting.GRAY);
    }
}
