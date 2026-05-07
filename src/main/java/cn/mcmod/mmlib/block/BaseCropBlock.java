package cn.mcmod.mmlib.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseCropBlock extends CropBlock {
    private final Supplier<? extends ItemLike> seedItem;

    public BaseCropBlock(BlockBehaviour.Properties properties, Supplier<? extends ItemLike> seedSupplier) {
        super(properties);
        this.seedItem = seedSupplier;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Move the outline down by 1px so crop visuals/selection align better with
        // farmland top (15/16 block).
        return super.getShape(state, level, pos, context).move(0.0D, -0.0625D, 0.0D);
    }

    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
            LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canSurvive(level, currentPos)) {
            if (neighborState.is(Blocks.DIRT)) {
                level.setBlock(neighborPos, Blocks.FARMLAND.defaultBlockState(), 3);
                return state;
            }
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.seedItem.get();
    }

    public BlockState withAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }
}
