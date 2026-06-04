package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PaintingVariantRegistry {
    public static final TagKey<PaintingVariant> KAKEZIKU_PLACEABLE = TagKey.create(
            Registries.PAINTING_VARIANT,
            ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "kakeziku_placeable"));

    public static final List<ResourceKey<PaintingVariant>> KAKEZIKU_1X2 = createSeries("kakeziku_1x2_", 12);
    public static final List<ResourceKey<PaintingVariant>> KAKEZIKU_1X3 = createSeries("kakeziku_1x3_", 12);
    public static final List<ResourceKey<PaintingVariant>> ALL = combine(KAKEZIKU_1X2, KAKEZIKU_1X3);

    private PaintingVariantRegistry() {
    }

    private static List<ResourceKey<PaintingVariant>> createSeries(String prefix, int count) {
        List<ResourceKey<PaintingVariant>> variants = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            variants.add(create(prefix + String.format("%02d", i)));
        }
        return Collections.unmodifiableList(variants);
    }

    private static List<ResourceKey<PaintingVariant>> combine(List<ResourceKey<PaintingVariant>> first,
            List<ResourceKey<PaintingVariant>> second) {
        List<ResourceKey<PaintingVariant>> variants = new ArrayList<>(first.size() + second.size());
        variants.addAll(first);
        variants.addAll(second);
        return Collections.unmodifiableList(variants);
    }

    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT,
                ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, name));
    }
}
