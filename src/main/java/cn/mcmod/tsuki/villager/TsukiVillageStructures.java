package cn.mcmod.tsuki.villager;

import cn.mcmod.tsuki.Tsuki;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class TsukiVillageStructures {
    public static final ResourceLocation PLAINS_HOUSES_POOL =
            ResourceLocation.parse("minecraft:village/plains/houses");
    public static final ResourceLocation TAIGA_HOUSES_POOL =
            ResourceLocation.parse("minecraft:village/taiga/houses");
    public static final ResourceLocation JAPANESE_HOUSE_TEMPLATE =
            ResourceLocation.parse("tsuki:village/houses/japanese_house");

    @SubscribeEvent
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        Registry<StructureTemplatePool> templatePools =
                event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();
        Registry<StructureProcessorList> processorLists =
                event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).orElseThrow();

        addBuildingToPool(templatePools, processorLists, PLAINS_HOUSES_POOL, JAPANESE_HOUSE_TEMPLATE.toString(), 4);
        addBuildingToPool(templatePools, processorLists, TAIGA_HOUSES_POOL, JAPANESE_HOUSE_TEMPLATE.toString(), 4);
    }

    private static void addBuildingToPool(
            Registry<StructureTemplatePool> templatePoolRegistry,
            Registry<StructureProcessorList> processorListRegistry,
            ResourceLocation poolId,
            String nbtPieceId,
            int weight) {
        StructureTemplatePool pool = templatePoolRegistry.get(poolId);
        if (pool == null) {
            Tsuki.getLogger().warn("Village pool {} not found, skipping {}", poolId, nbtPieceId);
            return;
        }

        Holder<StructureProcessorList> emptyProcessor = processorListRegistry.getHolderOrThrow(
                ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.withDefaultNamespace("empty")));

        SinglePoolElement piece = SinglePoolElement.single(nbtPieceId, emptyProcessor)
                .apply(StructureTemplatePool.Projection.RIGID);

        addElementToPool(pool, piece, weight);
    }

    @SuppressWarnings("unchecked")
    private static void addElementToPool(StructureTemplatePool pool, StructurePoolElement piece, int weight) {
        try {
            Field templatesField = ObfuscationReflectionHelper.findField(StructureTemplatePool.class, "templates");
            List<StructurePoolElement> currentTemplates = (List<StructurePoolElement>) templatesField.get(pool);
            ObjectArrayList<StructurePoolElement> mutableTemplates = new ObjectArrayList<>(currentTemplates);
            for (int i = 0; i < weight; i++) {
                mutableTemplates.add(piece);
            }
            templatesField.set(pool, mutableTemplates);

            Field rawTemplatesField = ObfuscationReflectionHelper.findField(StructureTemplatePool.class, "rawTemplates");
            List<Pair<StructurePoolElement, Integer>> currentRaw =
                    (List<Pair<StructurePoolElement, Integer>>) rawTemplatesField.get(pool);
            List<Pair<StructurePoolElement, Integer>> mutableRaw = new ArrayList<>(currentRaw);
            mutableRaw.add(Pair.of(piece, weight));
            rawTemplatesField.set(pool, mutableRaw);
        } catch (Exception e) {
            Tsuki.getLogger().error("Failed to add village structure {} to pool {}", piece, pool, e);
        }
    }

    private TsukiVillageStructures() {
    }
}
