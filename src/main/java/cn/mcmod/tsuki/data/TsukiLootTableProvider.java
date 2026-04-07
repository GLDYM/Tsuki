package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.data.loot.TsukiBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class TsukiLootTableProvider extends LootTableProvider {

    public TsukiLootTableProvider(PackOutput packOutput) {
        super(packOutput, Set.of()
                ,List.of(new SubProviderEntry(
                TsukiBlockLoot::new,
                LootContextParamSets.BLOCK))
        );
    }
    
//    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables = ImmutableList.of(Pair.of(TsukiBlockLoot::new, LootContextParamSets.BLOCK));

//    @Override
//    public String getName() {
//        return "Tsuki's Loot Tables";
//    }
//
//    @Override
//    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
//        return tables;
//    }

}
