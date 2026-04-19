package cn.mcmod.tsuki.item.enums;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public enum TsukiTeaSet {
    BLACK_TEA("black_tea",
            new MobEffectInstance(MobEffects.HEAL, 1, 0)),
    GREEN_TEA("green_tea",
            new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0)),
    MILK_TEA("milk_tea",
            new MobEffectInstance(MobEffects.HEAL, 1, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 200, 0)),
    MILK_GREEN_TEA("milk_green_tea",
            new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 200, 0)),
    EARL_GREY("earl_grey",
            new MobEffectInstance(MobEffects.WATER_BREATHING, 600, 0)),
    MILK_EARL_GREY("milk_earl_grey",
            new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 1)),
    FRUIT_TEA("fruit_tea",
            new MobEffectInstance(MobEffects.JUMP, 600, 0)),
    MILK_FRUIT_TEA("milk_fruit_tea",
            new MobEffectInstance(MobEffects.JUMP, 600, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 200, 0)),
    LEMON_BLACK_TEA("lemon_black_tea",
            new MobEffectInstance(MobEffects.HEAL, 1, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0)),
    LEMON_GREEN_TEA("lemon_green_tea",
            new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0)),
    MINT_TEA("mint_tea",
            new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0)),
    BARLEY_TEA("barley_tea",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0)),
    BROWN_RICE_TEA("brown_rice_tea",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0));

    private final String name;
    private final MobEffectInstance[] effects;

    TsukiTeaSet(String name, MobEffectInstance... effects) {
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
