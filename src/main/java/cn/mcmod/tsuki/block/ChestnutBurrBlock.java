package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ChestnutBurrBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<ChestnutBurrBlock> CODEC = simpleCodec(ChestnutBurrBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public ChestnutBurrBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
    }

    @Override
    public MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) >= 3) {
            return;
        }
        if (level.getRawBrightness(pos, 0) < 9) {
            return;
        }
        if (random.nextInt(8) != 0) {
            return;
        }
        level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(net.minecraft.tags.BlockTags.LEAVES);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
                                               BlockHitResult hitResult) {
        if (state.getValue(AGE) < 3) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            popResource(level, pos, new ItemStack(BlockItemRegistry.CHESTNUT_BURRS.get(), 1));
            level.setBlock(pos, state.setValue(AGE, 1), 2);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.9F);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int current = state.getValue(AGE);
        int next = Mth.clamp(current + Mth.nextInt(random, 1, 2), 0, 3);
        level.setBlock(pos, state.setValue(AGE, next), 2);
    }
}
