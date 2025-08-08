package cn.mcmod.sakura.loot_modifier;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import cn.mcmod.sakura.item.ItemRegistry;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class SeedsDrop {
	public static class SeedDropModifier extends LootModifier {
		public SeedDropModifier(LootItemCondition[] conditionsIn) {
			super(conditionsIn);
		}

		public static final Codec<SeedDropModifier> CODEC = RecordCodecBuilder
				.create(inst -> codecStart(inst).apply(inst, SeedDropModifier::new));

		@Nonnull
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			List<Item> seeds = Lists.newArrayList(ItemRegistry.CABBAGE_SEEDS.get(), ItemRegistry.EGGPLANT_SEEDS.get(),
					ItemRegistry.ONION_SEEDS.get(), ItemRegistry.RADISH_SEEDS.get(), ItemRegistry.TOMATO_SEEDS.get(),
					ItemRegistry.RICE_SEEDS.get(), ItemRegistry.RAPESEEDS.get(), ItemRegistry.TARO.get(),
					ItemRegistry.BUCKWHEAT.get(), ItemRegistry.SOYBEAN.get(), ItemRegistry.RED_BEAN.get());
			generatedLoot.add(new ItemStack(seeds.get(context.getRandom().nextInt(seeds.size()))));
			return generatedLoot;
		}

		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			return CODEC;
		}
	}

}
