package cn.mcmod.tsuki.enchantment;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public final class TsukiEnchantments {
    public static final ResourceKey<Enchantment> ANTI_FIRE = key("anti_fire");
    public static final ResourceKey<Enchantment> SMASH = key("smash");
    public static final ResourceKey<Enchantment> OMNITOOL = key("omnitool");
    public static final ResourceKey<Enchantment> FRESH_FOOD = key("fresh_food");

    private TsukiEnchantments() {
    }

    private static ResourceKey<Enchantment> key(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, path));
    }

    public static int getLevel(RegistryAccess registryAccess, ResourceKey<Enchantment> enchantmentKey, ItemStack stack) {
        Holder<Enchantment> holder = registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantmentKey);
        return EnchantmentHelper.getTagEnchantmentLevel(holder, stack);
    }
}
