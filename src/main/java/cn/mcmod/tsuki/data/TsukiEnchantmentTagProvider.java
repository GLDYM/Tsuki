package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.Tsuki;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiEnchantmentTagProvider extends TagsProvider<Enchantment> {
    public TsukiEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
            ExistingFileHelper existingFileHelper) {
        super(output, Registries.ENCHANTMENT, provider, Tsuki.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(EnchantmentTags.TREASURE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "omnitool"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fresh_food"));
        this.tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .remove(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "omnitool"))
                .remove(ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "fresh_food"));
    }

    @Override
    public String getName() {
        return "Tsuki Enchantment Tags";
    }
}
