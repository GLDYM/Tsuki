package cn.mcmod.tsuki.block.decoration.tatami;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TatamiStairBlock extends StairBlock {

    public TatamiStairBlock(BlockState baseState, Properties properties) {
        super(baseState, properties.randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        super.randomTick(state, worldIn, pos, rand);
        if (worldIn.isDay() && worldIn.canSeeSky(pos)) {
            worldIn.setBlockAndUpdate(pos, BlockRegistry.TATAMI_STAIRS_SUNBURNT.get().withPropertiesOf(state));
        }
    }
}
