package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.tags.TsukiBlockTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TsukiBlockTagsProvider extends BlockTagsProvider {
    public TsukiBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider,
            String modId, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, modId, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.LOGS)
                .add(BlockRegistry.STRIPPED_SAKURA_WOOD.get())
                .add(BlockRegistry.STRIPPED_MAPLE_WOOD.get())
                .add(BlockRegistry.SAKURA_WOOD.get())
                .add(BlockRegistry.MAPLE_WOOD.get())
                .add(BlockRegistry.STRIPPED_SAKURA_LOG.get())
                .add(BlockRegistry.STRIPPED_MAPLE_LOG.get())
                .add(BlockRegistry.SAKURA_LOG.get())
                .add(BlockRegistry.MAPLE_LOG.get())
                .add(BlockRegistry.MAPLE_SAP_LOG.get())
                .add(BlockRegistry.UME_LOG.get())
                .add(BlockRegistry.STRIPPED_UME_LOG.get())
                .add(BlockRegistry.UME_WOOD.get())
                .add(BlockRegistry.STRIPPED_UME_WOOD.get());
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(BlockRegistry.STRIPPED_SAKURA_WOOD.get())
                .add(BlockRegistry.STRIPPED_MAPLE_WOOD.get())
                .add(BlockRegistry.SAKURA_WOOD.get())
                .add(BlockRegistry.MAPLE_WOOD.get())
                .add(BlockRegistry.STRIPPED_SAKURA_LOG.get())
                .add(BlockRegistry.STRIPPED_MAPLE_LOG.get())
                .add(BlockRegistry.SAKURA_LOG.get())
                .add(BlockRegistry.MAPLE_LOG.get())
                .add(BlockRegistry.MAPLE_SAP_LOG.get())
                .add(BlockRegistry.UME_LOG.get())
                .add(BlockRegistry.STRIPPED_UME_LOG.get())
                .add(BlockRegistry.UME_WOOD.get())
                .add(BlockRegistry.STRIPPED_UME_WOOD.get());
        this.tag(TsukiBlockTags.STRIPPED_LOG)
                .add(BlockRegistry.STRIPPED_SAKURA_LOG.get())
                .add(BlockRegistry.STRIPPED_MAPLE_LOG.get())
                .add(BlockRegistry.STRIPPED_UME_LOG.get());
        this.tag(TsukiBlockTags.STRIPPED_WOOD)
                .add(BlockRegistry.STRIPPED_SAKURA_WOOD.get())
                .add(BlockRegistry.STRIPPED_MAPLE_WOOD.get())
                .add(BlockRegistry.STRIPPED_UME_WOOD.get());
        this.tag(TsukiBlockTags.KAWARA_BLOCK)
                .add(BlockRegistry.KAWARA_BLOCK.get())
                .add(BlockRegistry.KAWARA_BLOCK_ALTER.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistry.STONE_MORTAR.get())
                .add(BlockRegistry.SAKURA_DIAMOND_ORE.get())
                .add(BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get())
                .add(BlockRegistry.SAKURA_DIAMOND_BLOCK.get())
                .add(BlockRegistry.IRON_SAND.get())
                .add(BlockRegistry.TATARA.get())
                .add(BlockRegistry.KAWARA_BLOCK.get())
                .add(BlockRegistry.KAWARA_BLOCK_ALTER.get())
                .add(BlockRegistry.KAWARA.get())
                .add(BlockRegistry.KAWARA_STAIRS.get())
                .add(BlockRegistry.KAWARA_STAIRS_ALTER.get())
                .add(BlockRegistry.KAWARA_SLAB.get())
                .add(BlockRegistry.KAWARA_SLAB_ALTER.get())
                .add(BlockRegistry.STONE_LANTERN.get())
                .add(BlockRegistry.COBBLESTONE_LANTERN.get())
                .add(BlockRegistry.MOSSY_STONE_LANTERN.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockRegistry.IRON_SAND.get())
                .add(BlockRegistry.TATAMI.get())
                .add(BlockRegistry.TATAMI_WAXED.get())
                .add(BlockRegistry.TATAMI_SUNBURNT.get())
                .add(BlockRegistry.TATAMI_SLAB.get())
                .add(BlockRegistry.TATAMI_SLAB_WAXED.get())
                .add(BlockRegistry.TATAMI_SLAB_SUNBURNT.get())
                .add(BlockRegistry.TATAMI_STAIRS.get())
                .add(BlockRegistry.TATAMI_STAIRS_WAXED.get())
                .add(BlockRegistry.TATAMI_STAIRS_SUNBURNT.get())
                .add(BlockRegistry.TATAMI_CARPET.get())
                .add(BlockRegistry.TATAMI_CARPET_NS.get())
                .add(BlockRegistry.TATAMI_CARPET_TAN.get())
                .add(BlockRegistry.TATAMI_CARPET_TAN_NS.get())
                .add(BlockRegistry.MUSHROOM_FALLEN_LEAVES.get())
                .add(BlockRegistry.MATSUTAKE_FALLEN_LEAVES.get())
                .add(BlockRegistry.ZABUTON.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockRegistry.SAKURA_PLANK.get())
                .add(BlockRegistry.MAPLE_PLANK.get())
                .add(BlockRegistry.UME_PLANK.get())
                .add(BlockRegistry.BAMBOO_PLANK.get())
                .add(BlockRegistry.SAKURA_PLANK_SLAB.get())
                .add(BlockRegistry.MAPLE_PLANK_SLAB.get())
                .add(BlockRegistry.UME_PLANK_SLAB.get())
                .add(BlockRegistry.BAMBOO_PLANK_SLAB.get())
                .add(BlockRegistry.SAKURA_PLANK_STAIRS.get())
                .add(BlockRegistry.MAPLE_PLANK_STAIRS.get())
                .add(BlockRegistry.UME_PLANK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_PLANK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_BLOCK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SUNBURNT_STAIRS.get())
                .add(BlockRegistry.STRAW_BLOCK_STAIRS.get())
                .add(BlockRegistry.FUTON.get())
                .add(BlockRegistry.PEPPER_SPLINT.get())
                .add(BlockRegistry.VANILLA_SPLINT.get())
                .add(BlockRegistry.GRAPE_SPLINT_STAND.get())
                .add(BlockRegistry.GRAPE_SPLINT.get());
        this.tag(TsukiBlockTags.MINEABLE_WITH_HAMMER)
                .add(BlockRegistry.TATARA.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegistry.SAKURA_DIAMOND_ORE.get())
                .add(BlockRegistry.DEEPSLATE_SAKURA_DIAMOND_ORE.get())
                .add(BlockRegistry.SAKURA_DIAMOND_BLOCK.get());
        this.tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(BlockRegistry.SAKURA_DIAMOND_BLOCK.get());
        this.tag(BlockTags.LEAVES)
                .add(BlockRegistry.SAKURA_LEAVES.get())
                .add(BlockRegistry.MAPLE_LEAVES_RED.get())
                .add(BlockRegistry.MAPLE_LEAVES_GREEN.get())
                .add(BlockRegistry.MAPLE_LEAVES_ORANGE.get())
                .add(BlockRegistry.MAPLE_LEAVES_YELLOW.get())
                .add(BlockRegistry.UME_LEAVES.get());
        this.tag(BlockTags.SAPLINGS)
                .add(BlockRegistry.SAKURA_SAPLING.get())
                .add(BlockRegistry.MAPLE_SAPLING_RED.get())
                .add(BlockRegistry.MAPLE_SAPLING_GREEN.get())
                .add(BlockRegistry.MAPLE_SAPLING_ORANGE.get())
                .add(BlockRegistry.MAPLE_SAPLING_YELLOW.get())
                .add(BlockRegistry.UME_SAPLING.get());
        this.tag(BlockTags.CROPS)
                .add(BlockRegistry.RICE_CROP.get())
                .add(BlockRegistry.BUCKWHEAT_CROP.get())
                .add(BlockRegistry.CABBAGE_CROP.get())
                .add(BlockRegistry.EGGPLANT_CROP.get())
                .add(BlockRegistry.ONION_CROP.get())
                .add(BlockRegistry.RADISH_CROP.get())
                .add(BlockRegistry.RAPESEED_CROP.get())
                .add(BlockRegistry.REDBEAN_CROP.get())
                .add(BlockRegistry.RICE_CROP_ROOT.get())
                .add(BlockRegistry.TARO_CROP.get())
                .add(BlockRegistry.TOMATO_CROP.get())
                .add(BlockRegistry.PEPPER_CROP.get())
                .add(BlockRegistry.VANILLA_CROP.get())
                .add(BlockRegistry.HOPS_CROP.get())
                .add(BlockRegistry.WILD_PEPPER.get())
                .add(BlockRegistry.WILD_VANILLA.get());
        this.tag(BlockTags.PLANKS)
                .add(BlockRegistry.SAKURA_PLANK.get())
                .add(BlockRegistry.BAMBOO_PLANK.get())
                .add(BlockRegistry.MAPLE_PLANK.get())
                .add(BlockRegistry.UME_PLANK.get());
        this.tag(BlockTags.WOODEN_STAIRS)
                .add(BlockRegistry.SAKURA_PLANK_STAIRS.get())
                .add(BlockRegistry.MAPLE_PLANK_STAIRS.get())
                .add(BlockRegistry.UME_PLANK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_PLANK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_BLOCK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SUNBURNT_STAIRS.get());
        this.tag(BlockTags.WOODEN_SLABS)
                .add(BlockRegistry.SAKURA_PLANK_SLAB.get())
                .add(BlockRegistry.MAPLE_PLANK_SLAB.get())
                .add(BlockRegistry.UME_PLANK_SLAB.get())
                .add(BlockRegistry.BAMBOO_PLANK_SLAB.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SLAB.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SUNBURNT_SLAB.get())
                .add(BlockRegistry.STRAW_BLOCK_SLAB.get());
        this.tag(BlockTags.STAIRS)
                .add(BlockRegistry.BAMBOO_BLOCK_STAIRS.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SUNBURNT_STAIRS.get())
                .add(BlockRegistry.STRAW_BLOCK_STAIRS.get())
                .add(BlockRegistry.TATAMI_STAIRS.get())
                .add(BlockRegistry.TATAMI_STAIRS_WAXED.get())
                .add(BlockRegistry.TATAMI_STAIRS_SUNBURNT.get());
        this.tag(BlockTags.SLABS)
                .add(BlockRegistry.TATAMI_SLAB.get())
                .add(BlockRegistry.TATAMI_SLAB_WAXED.get())
                .add(BlockRegistry.TATAMI_SLAB_SUNBURNT.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SLAB.get())
                .add(BlockRegistry.BAMBOO_BLOCK_SUNBURNT_SLAB.get())
                .add(BlockRegistry.STRAW_BLOCK_SLAB.get());
        this.tag(BlockTags.FENCES)
                .add(BlockRegistry.BAMBOO_FENCE.get())
                .add(BlockRegistry.BAMBOO_FENCE_SUNBURNT.get());
        this.tag(BlockTags.WOODEN_FENCES)
                .add(BlockRegistry.BAMBOO_FENCE.get())
                .add(BlockRegistry.BAMBOO_FENCE_SUNBURNT.get());
        this.tag(BlockTags.DOORS)
                .add(BlockRegistry.BAMBOO_DOOR.get());
        this.tag(BlockTags.WOODEN_DOORS)
                .add(BlockRegistry.BAMBOO_DOOR.get());
        this.tag(BlockTags.BEDS)
                .add(BlockRegistry.FUTON.get());
    }

    public String getName() {
        return "Tsuki Blocks' Tags";
    }
}
