package cn.mcmod.tsuki.data;

import net.minecraft.data.PackOutput;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class TsukiLootModifierProvider extends GlobalLootModifierProvider {
    public TsukiLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
            String modid) {
        super(output, provider, modid);
    }

    @Override
    protected void start() {
        // add(
        // "grass_drops",
        // new SeedsDrop.SeedDropModifier(new LootItemCondition[]{
        // LootItemRandomChanceCondition.randomChance(0.0625F).build(),
        // MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).build(),
        // LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.FERN).build()
        // })
        // );
    }
}
