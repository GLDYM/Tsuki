package cn.mcmod.tsuki.block.crop;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrapeSplintBlock extends Block {
    public static final MapCodec<GrapeSplintBlock> CODEC = simpleCodec(p -> new GrapeSplintBlock());
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(7.0D, 11.0D, 7.0D, 9.0D, 14.0D, 9.0D),
            Block.box(0.0D, 12.0D, 7.0D, 16.0D, 13.0D, 9.0D),
            Block.box(7.0D, 12.0D, 0.0D, 9.0D, 13.0D, 16.0D));

    public GrapeSplintBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
