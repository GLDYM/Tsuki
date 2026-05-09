package cn.mcmod.tsuki.init.item.enums;

import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

public enum TsukiWineBottleSet {
    BEER_BOTTLE("beer_bottle", FluidRegistry.BEER, TsukiAlcoholSet.GLASS_BEER.getEffects()),
    DOBUROKU_BOTTLE("doburoku_bottle", FluidRegistry.DOBUROKU, TsukiAlcoholSet.GLASS_DOBUROKU.getEffects()),
    SAKE_BOTTLE("sake_bottle", FluidRegistry.SAKE, TsukiAlcoholSet.GLASS_SAKE.getEffects()),
    SHOUCHU_BOTTLE("shouchu_bottle", FluidRegistry.SHOUCHU, TsukiAlcoholSet.GLASS_SHOUCHU.getEffects()),
    RED_WINE_BOTTLE("red_wine_bottle", FluidRegistry.RED_WINE, TsukiAlcoholSet.GLASS_RED_WINE.getEffects()),
    WHITE_WINE_BOTTLE("white_wine_bottle", FluidRegistry.WHITE_WINE, TsukiAlcoholSet.GLASS_WHITE_WINE.getEffects()),
    CHAMPAGNE_BOTTLE("champagne_bottle", FluidRegistry.CHAMPAGNE, TsukiAlcoholSet.GLASS_CHAMPAGNE.getEffects()),
    RUM_BOTTLE("rum_bottle", FluidRegistry.RUM, TsukiAlcoholSet.GLASS_RUM.getEffects()),
    VODKA_BOTTLE("vodka_bottle", FluidRegistry.VODKA, TsukiAlcoholSet.GLASS_VODKA.getEffects()),
    WHISKEY_BOTTLE("whiskey_bottle", FluidRegistry.WHISKEY, TsukiAlcoholSet.GLASS_WHISKEY.getEffects()),
    BRANDY_BOTTLE("brandy_bottle", FluidRegistry.BRANDY, TsukiAlcoholSet.GLASS_BRANDY.getEffects()),
    GIN_BOTTLE("gin_bottle", FluidRegistry.GIN, TsukiAlcoholSet.GLASS_GIN.getEffects()),
    TEQUILA_BOTTLE("tequila_bottle", FluidRegistry.TEQUILA, TsukiAlcoholSet.GLASS_TEQUILA.getEffects()),
    LIQUEUR_BOTTLE("liqueur_bottle", FluidRegistry.LIQUEUR, TsukiAlcoholSet.GLASS_LIQUEUR.getEffects()),
    COCOA_LIQUEUR_BOTTLE("cocoa_liqueur_bottle", FluidRegistry.COCOA_LIQUEUR,
            TsukiAlcoholSet.GLASS_COCOA_LIQUEUR.getEffects());

    private final String name;
    private final Supplier<? extends Fluid> fluidSupplier;
    private final MobEffectInstance[] effects;

    TsukiWineBottleSet(String name, Supplier<? extends Fluid> fluidSupplier, MobEffectInstance... effects) {
        this.name = name;
        this.fluidSupplier = fluidSupplier;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public Fluid getFluid() {
        return fluidSupplier.get();
    }

    public MobEffectInstance[] getEffects() {
        return effects;
    }

    public static TsukiWineBottleSet fromFluid(Fluid fluid) {
        for (TsukiWineBottleSet set : values()) {
            if (set.getFluid() == fluid) {
                return set;
            }
        }
        return null;
    }

    public static DeferredItem<Item> itemFromFluid(Fluid fluid) {
        TsukiWineBottleSet set = fromFluid(fluid);
        return set == null ? null : DrinkRegistry.WINE_BOTTLES.get(set);
    }

    public DeferredItem<Item> alcoholItem() {
        TsukiAlcoholSet[] alcohols = TsukiAlcoholSet.values();
        int index = ordinal();
        if (index < 0 || index >= alcohols.length) {
            return null;
        }
        return DrinkRegistry.ALCOHOLS.get(alcohols[index]);
    }

    public static DeferredItem<Item> alcoholItemFromBottle(Item item) {
        TsukiWineBottleSet set = fromItem(item);
        return set == null ? null : set.alcoholItem();
    }

    public static TsukiWineBottleSet fromItem(Item item) {
        for (TsukiWineBottleSet set : values()) {
            DeferredItem<Item> deferredItem = DrinkRegistry.WINE_BOTTLES.get(set);
            if (deferredItem != null && deferredItem.get() == item) {
                return set;
            }
        }
        return null;
    }
}
