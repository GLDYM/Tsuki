package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.VillagerRegistry;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiPoiTypeTagsProvider extends TagsProvider<PoiType> {
    public TsukiPoiTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
            ExistingFileHelper existingFileHelper) {
        super(output, net.minecraft.core.registries.Registries.POINT_OF_INTEREST_TYPE, provider, Tsuki.MODID,
                existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(VillagerRegistry.WA_FARMER_POI.getKey())
                .add(VillagerRegistry.WA_TRADER_POI.getKey());
    }

    @Override
    public String getName() {
        return "Tsuki POI Type Tags";
    }
}
