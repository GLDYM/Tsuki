package cn.mcmod.tsuki.loot;

import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;

public class FishingCatchLootModifier extends LootModifier {
    public static final MapCodec<FishingCatchLootModifier> CODEC = RecordCodecBuilder
            .mapCodec(instance -> codecStart(instance).apply(instance, FishingCatchLootModifier::new));

    private static final int MOD_FISHING_WEIGHT = 24;
    private static final int VANILLA_FISHING_WEIGHT = 100;

    public FishingCatchLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (!(entity instanceof FishingHook hook) || generatedLoot.isEmpty()) {
            return generatedLoot;
        }

        int fishIndex = this.findVanillaFishIndex(generatedLoot);
        if (fishIndex < 0) {
            return generatedLoot;
        }

        if (!hook.isOpenWaterFishing()) {
            return generatedLoot;
        }

        RandomSource random = context.getRandom();
        if (random.nextInt(VANILLA_FISHING_WEIGHT + MOD_FISHING_WEIGHT) >= MOD_FISHING_WEIGHT) {
            return generatedLoot;
        }

        generatedLoot.set(fishIndex, this.createModCatch(random));
        return generatedLoot;
    }

    private int findVanillaFishIndex(ObjectArrayList<ItemStack> generatedLoot) {
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);
            if (stack.is(Items.COD) || stack.is(Items.SALMON) || stack.is(Items.TROPICAL_FISH) || stack.is(Items.PUFFERFISH)) {
                return i;
            }
        }
        return -1;
    }

    private ItemStack createModCatch(RandomSource random) {
        return switch (random.nextInt(3)) {
            case 0 -> new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.SHRIMP).get());
            case 1 -> new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.BONITO).get());
            default -> new ItemStack(BlockItemRegistry.BAMBOOSHOOT.get());
        };
    }

    @Override
    public MapCodec<? extends LootModifier> codec() {
        return CODEC;
    }
}
