
package cn.mcmod.tsuki.block.tree;

import net.minecraft.world.level.block.state.BlockBehaviour;

import com.mojang.serialization.MapCodec;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class BambooShoot extends BushBlock implements BonemealableBlock {
    public static final MapCodec<BambooShoot> CODEC = simpleCodec(BambooShoot::new);
    private static final VoxelShape SHAPE = Block.box(6D, 0.0D, 6D, 10D, 4.0D, 10D);

    public BambooShoot(Properties properties) {
        super(properties);
    }

    public BambooShoot() {
        this(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SAPLING).randomTicks().noCollission().instabreak()
                .sound(BlockRegistry.BAMBOO_PLANT.get().defaultBlockState().getSoundType()));
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter levelIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        super.randomTick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        if (worldIn.getRawBrightness(pos.above(), 0) > 6) {
            if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 0) {
                if (rand.nextInt(3) == 0) {
                    if (CommonHooks.canCropGrow(worldIn, pos, state, true)) {
                        growBamboo(worldIn, pos);
                        CommonHooks.fireCropGrowPost(worldIn, pos, state);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState ground = worldIn.getBlockState(pos.below());
        return ground.is(BlockTags.BAMBOO_PLANTABLE_ON) && !(ground.is(Blocks.BAMBOO))
                && !(ground.is(Blocks.BAMBOO_SAPLING)) && !(ground.is(BlockRegistry.BAMBOO_PLANT.get()))
                && !(ground.is(this));
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_50897_, BlockPos p_50898_, BlockState p_50899_) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level p_50901_, RandomSource p_50902_, BlockPos p_50903_, BlockState p_50904_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        growBamboo(worldIn, pos);
    }

    private void growBamboo(ServerLevel worldIn, BlockPos pos) {
        if (!worldIn.isEmptyBlock(pos.above())) {
            return;
        }
        if (worldIn.isEmptyBlock(pos.above(2))) {
            worldIn.setBlockAndUpdate(pos.above(2), BlockRegistry.BAMBOO_PLANT.get().defaultBlockState()
                    .setValue(BambooStalkBlock.LEAVES, BambooLeaves.LARGE));
        }
        worldIn.setBlockAndUpdate(pos.above(),
                BlockRegistry.BAMBOO_PLANT.get().defaultBlockState().setValue(BambooStalkBlock.LEAVES,
                        BambooLeaves.SMALL));
        worldIn.setBlockAndUpdate(pos, BlockRegistry.BAMBOO_PLANT.get().defaultBlockState());
    }
}
