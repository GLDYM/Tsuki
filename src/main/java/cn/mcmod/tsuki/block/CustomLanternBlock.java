package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class CustomLanternBlock extends Block {
    public static final MapCodec<CustomLanternBlock> CODEC = simpleCodec(CustomLanternBlock::new);

    private final VoxelShape shape;
    private final VoxelShape collisionShape;

    public CustomLanternBlock(Properties properties) {
        this(properties, Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D));
    }

    public CustomLanternBlock(Properties properties, VoxelShape shape) {
        this(properties, shape, shape);
    }

    public CustomLanternBlock(Properties properties, VoxelShape shape, VoxelShape collisionShape) {
        super(properties);
        this.shape = shape;
        this.collisionShape = collisionShape;
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos,
            CollisionContext context) {
        return collisionShape;
    }
}
