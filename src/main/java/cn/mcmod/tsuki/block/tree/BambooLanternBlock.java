package cn.mcmod.tsuki.block.tree;

import com.mojang.serialization.MapCodec;

import cn.mcmod.tsuki.block.decoration.CustomLanternBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BambooLanternBlock extends CustomLanternBlock {
    public static final MapCodec<BambooLanternBlock> CODEC = simpleCodec(BambooLanternBlock::new);

    public BambooLanternBlock(Properties properties) {
        super(properties);
    }

    public BambooLanternBlock(Properties properties, VoxelShape shape) {
        super(properties, shape);
    }

    public BambooLanternBlock(Properties properties, VoxelShape shape, VoxelShape collisionShape) {
        super(properties, shape, collisionShape);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double centerX = pos.getX() + 0.5D;
        double centerY = pos.getY() + 0.45D;
        double centerZ = pos.getZ() + 0.5D;
        level.addParticle(ParticleTypes.SMOKE, centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
        level.addParticle(ParticleTypes.FLAME, centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
    }
}
