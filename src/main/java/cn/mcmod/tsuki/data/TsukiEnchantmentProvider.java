package cn.mcmod.tsuki.data;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.enchantment.TsukiEnchantments;
import cn.mcmod.tsuki.tags.TsukiItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

public class TsukiEnchantmentProvider extends DatapackBuiltinEntriesProvider {

    public TsukiEnchantmentProvider(PackOutput output, CompletableFuture<net.minecraft.core.HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder().add(Registries.ENCHANTMENT, TsukiEnchantmentProvider::bootstrap),
                Set.of(Tsuki.MODID));
    }

    private static void bootstrap(BootstrapContext<Enchantment> bootstrap) {
        HolderGetter<Item> items = bootstrap.lookup(Registries.ITEM);

        HolderSet<Item> pickaxes = items.getOrThrow(TsukiItemTags.TOOLS_PICKAXES);
        HolderSet<Item> cantEnchantable = HolderSet.direct();

        HolderSet<Enchantment> antiFireExclusive = HolderSet.direct();
        HolderSet<Enchantment> smashExclusive = HolderSet.direct();
        HolderSet<Enchantment> omnitoolExclusive = HolderSet.direct();
        HolderSet<Enchantment> freshFoodExclusive = HolderSet.direct();

        bootstrap.register(TsukiEnchantments.ANTI_FIRE,
                new Enchantment(
                        Component.translatable("enchantment.tsuki.anti_fire"),
                        Enchantment.definition(
                                pickaxes,
                                cantEnchantable,
                                1,
                                1,
                                Enchantment.dynamicCost(20, 0),
                                Enchantment.dynamicCost(50, 0),
                                8,
                                EquipmentSlotGroup.MAINHAND),
                        antiFireExclusive,
                        DataComponentMap.EMPTY));

        bootstrap.register(TsukiEnchantments.SMASH,
                new Enchantment(
                        Component.translatable("enchantment.tsuki.smash"),
                        Enchantment.definition(
                                pickaxes,
                                cantEnchantable,
                                1,
                                1,
                                Enchantment.dynamicCost(15, 0),
                                Enchantment.dynamicCost(35, 0),
                                2,
                                EquipmentSlotGroup.MAINHAND),
                        smashExclusive,
                        DataComponentMap.EMPTY));

        bootstrap.register(TsukiEnchantments.OMNITOOL,
                new Enchantment(
                        Component.translatable("enchantment.tsuki.omnitool"),
                        Enchantment.definition(
                                pickaxes,
                                cantEnchantable,
                                1,
                                1,
                                Enchantment.dynamicCost(20, 0),
                                Enchantment.dynamicCost(45, 0),
                                4,
                                EquipmentSlotGroup.MAINHAND),
                        omnitoolExclusive,
                        DataComponentMap.EMPTY));

        bootstrap.register(TsukiEnchantments.FRESH_FOOD,
                new Enchantment(
                        Component.translatable("enchantment.tsuki.fresh_food"),
                        Enchantment.definition(
                                pickaxes,
                                cantEnchantable,
                                10,
                                10,
                                Enchantment.dynamicCost(8, 4),
                                Enchantment.dynamicCost(28, 6),
                                2,
                                EquipmentSlotGroup.MAINHAND),
                        freshFoodExclusive,
                        DataComponentMap.EMPTY));
    }

    public String getName() {
        return "Tsuki - Enchantments";
    }
}
