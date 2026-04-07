package cn.mcmod.tsuki.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("deprecation")
public class BambooBlock extends RotatedPillarBlock {

    public BambooBlock() {
        super(Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.SAND : MapColor.PLANT;
        }).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F).sound(SoundType.BAMBOO).randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        super.randomTick(state, worldIn, pos, rand);
        if (worldIn.isDay()) {
            if (worldIn.canSeeSky(pos.above()) || worldIn.getBlockState(pos.above()).is(this)
                    || worldIn.getBlockState(pos.above()).is(BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get())) {
                worldIn.setBlockAndUpdate(pos, BlockRegistry.BAMBOO_BLOCK_SUNBURNT.get().withPropertiesOf(state));
            }
        }
    }
}
