package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.tags.TsukiBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;

public class TsukiBiomeTagProvider extends BiomeTagsProvider {
    public TsukiBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modId,
            ExistingFileHelper existingFileHelper) {
        super(output, provider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        TagAppender<Biome> tagAppender = this.tag(TsukiBiomeTags.CAN_SPAWN_BAMBOO);

        tagAppender.add(Biomes.MEADOW);
        tagAppender.add(Biomes.CHERRY_GROVE);
        tagAppender.add(Biomes.BIRCH_FOREST);
        tagAppender.add(Biomes.OLD_GROWTH_BIRCH_FOREST);
        tagAppender.add(Biomes.FOREST);
        tagAppender.add(Biomes.FLOWER_FOREST);
        tagAppender.add(Biomes.DARK_FOREST);
        tagAppender.add(Biomes.PLAINS);
        tagAppender.add(Biomes.SUNFLOWER_PLAINS);
        tagAppender.add(Biomes.MANGROVE_SWAMP);
        tagAppender.add(Biomes.SWAMP);
        tagAppender.add(Biomes.JUNGLE);
        tagAppender.add(Biomes.BAMBOO_JUNGLE);
        tagAppender.add(Biomes.SPARSE_JUNGLE);

        TagAppender<Biome> sakuraDiamondOreTag = this.tag(TsukiBiomeTags.CAN_SPAWN_SAKURA_DIAMOND_ORE);
        sakuraDiamondOreTag.add(Biomes.BAMBOO_JUNGLE);
        sakuraDiamondOreTag.add(Biomes.CHERRY_GROVE);

        TagAppender<Biome> ironSandOreTag = this.tag(TsukiBiomeTags.CAN_SPAWN_IRON_SAND_ORE);
        ironSandOreTag.addTag(BiomeTags.IS_BEACH);
        ironSandOreTag.addTag(BiomeTags.IS_RIVER);

        TagAppender<Biome> samuraiSpawnTag = this.tag(TsukiBiomeTags.HAS_SAMURAI_SPAWNS);
        samuraiSpawnTag.add(Biomes.BAMBOO_JUNGLE);
    }
}
