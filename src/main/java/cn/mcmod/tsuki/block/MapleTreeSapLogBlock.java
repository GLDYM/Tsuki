package cn.mcmod.tsuki.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("deprecation")
public class MapleTreeSapLogBlock extends RotatedPillarBlock {
    public static final BooleanProperty EXHAUSTION = BooleanProperty.create("exhaustion");

    public MapleTreeSapLogBlock() {
        super(Properties.copy(Blocks.OAK_LOG).mapColor(
                state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.WOOD
                        : MapColor.PODZOL))
                .strength(2.0F).sound(SoundType.WOOD));
        this.registerDefaultState(this.defaultBlockState().setValue(EXHAUSTION, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, EXHAUSTION);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        super.randomTick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1) || state.getValue(EXHAUSTION)) {
            return;
        }
        if (rand.nextInt(36000) == 0) {
            worldIn.setBlockAndUpdate(pos, BlockRegistry.MAPLE_LOG.get().withPropertiesOf(state));
        }
    }
}
