package cn.mcmod.tsuki.block.crops;

import cn.mcmod.tsuki.block.BlockRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class GrapeVineBlock extends Block implements BonemealableBlock {
    public static final MapCodec<GrapeVineBlock> CODEC = simpleCodec(p -> new GrapeVineBlock());
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D),
            Block.box(0.0D, 12.0D, 7.0D, 16.0D, 13.0D, 9.0D),
            Block.box(7.0D, 12.0D, 0.0D, 9.0D, 13.0D, 16.0D));

    public GrapeVineBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(BlockRegistry.GRAPE_SPLINT_STAND.get());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) {
            return;
        }
        if (level.getRawBrightness(pos.above(), 0) >= 9) {
            int age = state.getValue(AGE);
            spreadToNeighbors(level, pos, age);
            if (age < 7 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(26) == 0)) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 2);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }
        }
    }

    private void spreadToNeighbors(Level level, BlockPos pos, int age) {
        if (age >= 2 && level.getBlockState(pos.above()).is(BlockRegistry.GRAPE_SPLINT_STAND.get())) {
            level.setBlock(pos.above(), this.defaultBlockState().setValue(AGE, 0), 2);
        }
        if (age >= 5) {
            BlockPos[] horizontalNeighbors = { pos.east(), pos.north(), pos.west(), pos.south() };
            for (BlockPos neighbor : horizontalNeighbors) {
                if (level.getBlockState(neighbor).is(BlockRegistry.GRAPE_SPLINT.get())) {
                    level.setBlock(neighbor,
                            BlockRegistry.GRAPE_LEAVES.get().defaultBlockState().setValue(GrapeLeavesBlock.AGE, 0), 2);
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 7;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int newAge = Math.min(7, state.getValue(AGE) + Mth.nextInt(random, 2, 5));
        spreadToNeighbors(level, pos, newAge);
        level.setBlock(pos, state.setValue(AGE, newAge), 2);
    }
}
