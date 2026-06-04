package cn.mcmod.tsuki.entity;

import cn.mcmod.tsuki.init.EntityTypeRegistry;
import cn.mcmod.tsuki.init.PaintingVariantRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KakezikuEntity extends Painting {
    public KakezikuEntity(EntityType<? extends KakezikuEntity> entityType, Level level) {
        super(entityType, level);
    }

    private KakezikuEntity(Level level, BlockPos pos, Direction direction, Holder<PaintingVariant> variant) {
        super(EntityTypeRegistry.KAKEZIKU.get(), level);
        this.pos = pos;
        this.setVariant(variant);
        this.setDirection(direction);
    }

    public static Optional<KakezikuEntity> createKakeziku(Level level, BlockPos topPos, Direction direction) {
        List<Holder<PaintingVariant>> variants = new ArrayList<>();
        level.registryAccess()
                .registryOrThrow(Registries.PAINTING_VARIANT)
                .getTagOrEmpty(PaintingVariantRegistry.KAKEZIKU_PLACEABLE)
                .forEach(variants::add);
        if (variants.isEmpty()) {
            return Optional.empty();
        }

        variants.removeIf(variant -> !createAtTop(level, topPos, direction, variant).survives());
        if (variants.isEmpty()) {
            return Optional.empty();
        }

        int maxArea = variants.stream().mapToInt(variant -> variant.value().area()).max().orElse(0);
        variants.removeIf(variant -> variant.value().area() < maxArea);

        return Util.getRandomSafe(variants, level.random)
                .map(variant -> createAtTop(level, topPos, direction, variant));
    }

    private static KakezikuEntity createAtTop(Level level, BlockPos topPos, Direction direction,
            Holder<PaintingVariant> variant) {
        BlockPos basePos = topPos.below(variant.value().height() / 2);
        return new KakezikuEntity(level, basePos, direction, variant);
    }

    @Override
    public void dropItem(@Nullable Entity brokenEntity) {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (brokenEntity instanceof Player player && player.hasInfiniteMaterials()) {
                return;
            }
            this.spawnAtLocation(ItemRegistry.KAKEZIKU.get());
        }
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(ItemRegistry.KAKEZIKU.get());
    }
}
