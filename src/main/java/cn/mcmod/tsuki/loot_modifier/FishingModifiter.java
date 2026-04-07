package cn.mcmod.tsuki.loot_modifier;

import cn.mcmod.tsuki.item.enums.TsukiFoodSet;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import com.google.common.collect.Lists;

public class FishingModifiter extends LootModifier{

    protected FishingModifiter(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }
    public static final MapCodec<FishingModifiter> CODEC = RecordCodecBuilder
            .mapCodec(inst -> codecStart(inst).apply(inst, FishingModifiter::new));
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
      List<Item> seeds = Lists.newArrayList(TsukiFoodSet.SHRIMP.getItem().get(), TsukiFoodSet.BONITO.getItem().get());
      generatedLoot.clear();
      generatedLoot.add(new ItemStack(seeds.get(context.getRandom().nextInt(seeds.size()))));
        return generatedLoot;
    }


    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}

