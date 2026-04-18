package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.data.client.TsukiBlockStateProvider;
import cn.mcmod.tsuki.data.client.TsukiItemModelProvider;
import cn.mcmod.tsuki.data.compat.TsukiTFCFoodCompatProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Tsuki.MODID)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        dataGenerator.addProvider(event.includeClient(),
                new TsukiBlockStateProvider(packOutput, Tsuki.MODID, existingFileHelper));
        dataGenerator.addProvider(event.includeClient(),
                new TsukiItemModelProvider(packOutput, Tsuki.MODID, existingFileHelper));
        TsukiBlockTagsProvider block_tag = new TsukiBlockTagsProvider(packOutput, provider, Tsuki.MODID,
                existingFileHelper);
        dataGenerator.addProvider(event.includeServer(), block_tag);
        dataGenerator.addProvider(event.includeServer(),
                new TsukiItemTagsProvider(packOutput, provider, block_tag, Tsuki.MODID, existingFileHelper));
        dataGenerator.addProvider(event.includeServer(),
                new TsukiFluidTagsProvider(packOutput, provider, Tsuki.MODID, existingFileHelper));
        dataGenerator.addProvider(event.includeServer(),
                new TsukiBiomeTagProvider(packOutput, provider, Tsuki.MODID, existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new TsukiRecipeProvider(packOutput, provider));
        dataGenerator.addProvider(event.includeServer(), new TsukiLootTableProvider(packOutput, provider));
        dataGenerator.addProvider(event.includeServer(), new TsukiFeatureProvider(packOutput, provider));
        dataGenerator.addProvider(event.includeServer(), new TsukiCompostableDataMapProvider(packOutput, provider));
        // dataGenerator.addProvider(event.includeServer(),new
        // TsukiLootModifierProvider(packOutput, provider, Tsuki.MODID));
        dataGenerator.addProvider(event.includeServer(),
                new TsukiTFCFoodCompatProvider(packOutput, existingFileHelper));
    }
}
