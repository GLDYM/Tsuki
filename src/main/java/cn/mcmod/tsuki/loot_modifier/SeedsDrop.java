package cn.mcmod.tsuki.loot_modifier;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import cn.mcmod.tsuki.item.ItemRegistry;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class SeedsDrop {
    public static class SeedDropModifier extends LootModifier {
        public SeedDropModifier(LootItemCondition[] conditionsIn) {
            super(conditionsIn);
        }

        public static final MapCodec<SeedDropModifier> CODEC = RecordCodecBuilder
                .mapCodec(inst -> codecStart(inst).apply(inst, SeedDropModifier::new));

        @Nonnull
        @Override
        protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
            List<Item> seeds = Lists.newArrayList(ItemRegistry.CABBAGE_SEEDS.get(), ItemRegistry.EGGPLANT_SEEDS.get(),
                    ItemRegistry.ONION_SEEDS.get(), ItemRegistry.RADISH_SEEDS.get(), ItemRegistry.TOMATO_SEEDS.get(),
                    ItemRegistry.RICE_SEEDS.get(), ItemRegistry.RAPESEEDS.get(), ItemRegistry.TARO.get(),
                    ItemRegistry.BUCKWHEAT.get(), ItemRegistry.SOYBEAN.get(), ItemRegistry.RED_BEAN.get(),
                    ItemRegistry.GRAPE_SEEDS.get(), ItemRegistry.HOP_SEEDS.get(), ItemRegistry.VANILLA_SEEDS.get());
            generatedLoot.add(new ItemStack(seeds.get(context.getRandom().nextInt(seeds.size()))));
            return generatedLoot;
        }

        @Override
        public MapCodec<? extends IGlobalLootModifier> codec() {
            return CODEC;
        }
    }

}
