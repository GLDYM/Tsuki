package cn.mcmod.tsuki.data;

import cn.mcmod.tsuki.block.BlockRegistry;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TsukiBlockTagsProvider extends BlockTagsProvider {
   public TsukiBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, ExistingFileHelper existingFileHelper) {
      super(packOutput, lookupProvider, modId, existingFileHelper);
   }

   protected void addTags(HolderLookup.Provider provider) {
      this.tag(BlockTags.LOGS)
         .add(
            new Block[]{
               (Block)BlockRegistry.STRIPPED_SAKURA_WOOD.get(),
               (Block)BlockRegistry.STRIPPED_MAPLE_WOOD.get(),
               (Block)BlockRegistry.SAKURA_WOOD.get(),
               (Block)BlockRegistry.MAPLE_WOOD.get(),
               (Block)BlockRegistry.STRIPPED_SAKURA_LOG.get(),
               (Block)BlockRegistry.STRIPPED_MAPLE_LOG.get(),
               (Block)BlockRegistry.SAKURA_LOG.get(),
               (Block)BlockRegistry.MAPLE_LOG.get(),
               (Block)BlockRegistry.MAPLE_SAP_LOG.get()
            }
         );
      this.tag(BlockTags.LOGS_THAT_BURN)
         .add(
            new Block[]{
               (Block)BlockRegistry.STRIPPED_SAKURA_WOOD.get(),
               (Block)BlockRegistry.STRIPPED_MAPLE_WOOD.get(),
               (Block)BlockRegistry.SAKURA_WOOD.get(),
               (Block)BlockRegistry.MAPLE_WOOD.get(),
               (Block)BlockRegistry.STRIPPED_SAKURA_LOG.get(),
               (Block)BlockRegistry.STRIPPED_MAPLE_LOG.get(),
               (Block)BlockRegistry.SAKURA_LOG.get(),
               (Block)BlockRegistry.MAPLE_LOG.get(),
               (Block)BlockRegistry.MAPLE_SAP_LOG.get()
            }
         );
      this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add((Block)BlockRegistry.STONE_MORTAR.get());
      this.tag(BlockTags.LEAVES)
         .add(
            new Block[]{
               (Block)BlockRegistry.SAKURA_LEAVES.get(),
               (Block)BlockRegistry.MAPLE_LEAVES_RED.get(),
               (Block)BlockRegistry.MAPLE_LEAVES_GREEN.get(),
               (Block)BlockRegistry.MAPLE_LEAVES_ORANGE.get(),
               (Block)BlockRegistry.MAPLE_LEAVES_YELLOW.get()
            }
         );
      this.tag(BlockTags.SAPLINGS)
         .add(
            new Block[]{
               (Block)BlockRegistry.SAKURA_SAPLING.get(),
               (Block)BlockRegistry.MAPLE_SAPLING_RED.get(),
               (Block)BlockRegistry.MAPLE_SAPLING_GREEN.get(),
               (Block)BlockRegistry.MAPLE_SAPLING_ORANGE.get(),
               (Block)BlockRegistry.MAPLE_SAPLING_YELLOW.get()
            }
         );
      this.tag(BlockTags.CROPS)
         .add(
            new Block[]{
               (Block)BlockRegistry.RICE_CROP.get(),
               (Block)BlockRegistry.BUCKWHEAT_CROP.get(),
               (Block)BlockRegistry.CABBAGE_CROP.get(),
               (Block)BlockRegistry.EGGPLANT_CROP.get(),
               (Block)BlockRegistry.ONION_CROP.get(),
               (Block)BlockRegistry.RADISH_CROP.get(),
               (Block)BlockRegistry.RAPESEED_CROP.get(),
               (Block)BlockRegistry.REDBEAN_CROP.get(),
               (Block)BlockRegistry.RICE_CROP_ROOT.get(),
               (Block)BlockRegistry.TARO_CROP.get(),
               (Block)BlockRegistry.TOMATO_CROP.get()
            }
         );
      this.tag(BlockTags.PLANKS)
         .add(new Block[]{(Block)BlockRegistry.SAKURA_PLANK.get(), (Block)BlockRegistry.BAMBOO_PLANK.get(), (Block)BlockRegistry.MAPLE_PLANK.get()});
   }

   public String getName() {
      return "Tsuki Blocks' Tags";
   }
}
