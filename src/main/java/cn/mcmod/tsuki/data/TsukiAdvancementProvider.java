package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.data.advancement.TsukiGuideAdvancements;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiAdvancementProvider extends AdvancementProvider {
    public TsukiAdvancementProvider(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, existingFileHelper, List.of(new TsukiGuideAdvancements()));
    }
}
