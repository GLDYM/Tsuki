package cn.mcmod.tsuki.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.particles.ParticleTypes;

public class HotSpringWaterBlock extends LiquidBlock {
    public HotSpringWaterBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        if (!level.isClientSide && entity instanceof LivingEntity living && living.tickCount % 20 == 0) {
            living.heal(0.5F);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (level.isEmptyBlock(pos.above())) {
            level.addParticle(ParticleTypes.POOF, pos.getX() + random.nextDouble(),
                    pos.getY() + 0.5D + random.nextDouble() * 0.5D,
                    pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}
