package cn.mcmod.tsuki.block;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class ShojiBlock extends DoorBlock {
    private static final VoxelShape CLOSED_Z_AXIS = box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    private static final VoxelShape CLOSED_X_AXIS = box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
    private static final VoxelShape OPEN_WEST = box(0.0D, 0.0D, 7.0D, 3.2D, 16.0D, 9.0D);
    private static final VoxelShape OPEN_EAST = box(12.8D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    private static final VoxelShape OPEN_NORTH = box(7.0D, 0.0D, 12.8D, 9.0D, 16.0D, 16.0D);
    private static final VoxelShape OPEN_SOUTH = box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 3.2D);

    public ShojiBlock() {
        super(BlockSetType.BAMBOO, BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(0.5F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean isOpen = state.getValue(OPEN);
        return switch (state.getValue(FACING)) {
            case NORTH -> isOpen ? OPEN_EAST : CLOSED_Z_AXIS;
            case SOUTH -> isOpen ? OPEN_WEST : CLOSED_Z_AXIS;
            case WEST -> isOpen ? OPEN_SOUTH : CLOSED_X_AXIS;
            case EAST -> isOpen ? OPEN_NORTH : CLOSED_X_AXIS;
            default -> CLOSED_Z_AXIS;
        };
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) {
        return getShape(state, level, pos, context);
    }
}
