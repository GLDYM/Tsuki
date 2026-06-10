package cn.mcmod.tsuki.block.tree;

import java.util.function.Supplier;

import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.particles.SimpleParticleType;

public class UmeLeavesBlock extends TsukiLeavesBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private static final int FRUIT_START_AGE = 5;

    public UmeLeavesBlock(Properties builder, Supplier<SimpleParticleType> particle) {
        super(builder, particle);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return (state.getValue(DISTANCE) == 7 || state.getValue(AGE) < 5)
                && !(Boolean) state.getValue(PERSISTENT);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean persistent = state.getValue(PERSISTENT);
        int age = state.getValue(AGE);

        if (!persistent && state.getValue(DISTANCE) == 7) {
            if (age >= FRUIT_START_AGE) {
                int count = age - (FRUIT_START_AGE - 1);
                popResource(level, pos, new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.UME).get(), count));
            }
            super.randomTick(state, level, pos, random);
            return;
        }

        super.randomTick(state, level, pos, random);

        BlockState currentState = level.getBlockState(pos);
        if (!currentState.is(this)) {
            return;
        }

        if (random.nextInt(5) == 0 && !persistent && age < FRUIT_START_AGE) {
            currentState = level.getBlockState(pos);
            level.setBlock(pos, currentState.setValue(AGE, age + 1), 2);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        if (age < FRUIT_START_AGE) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            int count = age - (FRUIT_START_AGE - 1);
            popResource(level, pos, new ItemStack(FoodRegistry.FOODSET.get(TsukiFoodSet.UME).get(), count));
            level.setBlock(pos, state.setValue(AGE, 0), 2);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.9F);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 5;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int current = state.getValue(AGE);
        int next = Mth.clamp(current + Mth.nextInt(random, 1, 2), 0, 5);
        level.setBlock(pos, state.setValue(AGE, next), 2);
    }
}
