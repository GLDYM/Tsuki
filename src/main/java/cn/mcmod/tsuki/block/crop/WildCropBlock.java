package cn.mcmod.tsuki.block.crop;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WildCropBlock extends CropBlock {
    public static final MapCodec<WildCropBlock> CODEC = simpleCodec(WildCropBlock::new);

    public WildCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this == BlockRegistry.WILD_VANILLA.get()
                ? ItemRegistry.VANILLA_SEEDS.get()
                : ItemRegistry.PEPPER_SEEDS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        Block block = state.getBlock();
        return block == Blocks.FARMLAND
                || block == Blocks.DIRT
                || block == Blocks.GRASS_BLOCK
                || block == Blocks.COARSE_DIRT
                || block == Blocks.ROOTED_DIRT
                || block == Blocks.PODZOL
                || block == Blocks.MUD;
    }

    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }
}
