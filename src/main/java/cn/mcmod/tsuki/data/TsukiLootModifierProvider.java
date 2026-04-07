package cn.mcmod.tsuki.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class TsukiLootModifierProvider extends GlobalLootModifierProvider {
    public TsukiLootModifierProvider(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
//        add(
//                "grass_drops",
//                new SeedsDrop.SeedDropModifier(new LootItemCondition[]{
//                        LootItemRandomChanceCondition.randomChance(0.0625F).build(),
//                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).build(),
//                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.FERN).build()
//                })
//        );
    }
}
