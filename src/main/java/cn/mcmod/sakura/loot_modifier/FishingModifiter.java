package cn.mcmod.sakura.loot_modifier;

import cn.mcmod.sakura.item.enums.SakuraFoodSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;
import com.google.common.collect.Lists;
import cn.mcmod.sakura.loot_modifier.SeedsDrop.SeedDropModifier;

public class FishingModifiter extends LootModifier{

    protected FishingModifiter(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }
	public static final Codec<SeedDropModifier> CODEC = RecordCodecBuilder
			.create(inst -> codecStart(inst).apply(inst, SeedDropModifier::new));
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
      List<Item> seeds = Lists.newArrayList(SakuraFoodSet.SHRIMP.getItem().get(), SakuraFoodSet.BONITO.getItem().get());
      generatedLoot.clear();
      generatedLoot.add(new ItemStack(seeds.get(context.getRandom().nextInt(seeds.size()))));
        return generatedLoot;
    }


    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
