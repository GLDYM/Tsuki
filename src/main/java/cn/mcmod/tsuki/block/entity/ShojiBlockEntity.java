package cn.mcmod.tsuki.block.entity;

import cn.mcmod.mmlib.block.entity.SyncedBlockEntity;
import cn.mcmod.tsuki.block.decoration.ShojiBlock;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ShojiBlockEntity extends SyncedBlockEntity {
    private static final float ANIMATION_SPEED = 0.1F;
    private int type = 0;
    private float animationProgress = 0.0F;
    private float animationProgressPrev = 0.0F;

    public ShojiBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SHOJI.get(), pos, state);
        if (state.getBlock() instanceof ShojiBlock shojiBlock) {
            this.type = shojiBlock.getType();
        }
        this.animationProgress = state.getValue(ShojiBlock.OPEN) ? 1.0F : 0.0F;
        this.animationProgressPrev = this.animationProgress;
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, ShojiBlockEntity blockEntity) {
        blockEntity.animationProgressPrev = blockEntity.animationProgress;
        float target = state.getValue(ShojiBlock.OPEN) ? 1.0F : 0.0F;
        blockEntity.animationProgress = Mth.approach(blockEntity.animationProgress, target, ANIMATION_SPEED);
    }

    public int getShojiType() {
        return type;
    }

    public void setShojiType(int type) {
        this.type = type;
        inventoryChanged();
    }

    public float getAnimationProgress(float partialTicks) {
        return Mth.lerp(partialTicks, animationProgressPrev, animationProgress);
    }

    @Override
    public void loadAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.loadAdditional(compound, registries);
        if (compound.contains("type")) {
            type = compound.getInt("type");
        }
        if (compound.contains("animation_progress")) {
            animationProgress = compound.getFloat("animation_progress");
            animationProgressPrev = animationProgress;
        } else if (compound.contains("animation")) {
            // Compat with previous int-tick animation data.
            int animation = compound.getInt("animation");
            animationProgress = 1.0F - Mth.clamp(animation / 10.0F, 0.0F, 1.0F);
            animationProgressPrev = animationProgress;
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
        super.saveAdditional(compound, registries);
        compound.putInt("type", type);
        compound.putFloat("animation_progress", animationProgress);
    }
}
