package cn.mcmod.tsuki.entity;

import cn.mcmod.tsuki.init.EntityTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SeatEntity extends Entity {
    private BlockPos seatPos = BlockPos.ZERO;

    public SeatEntity(EntityType<? extends SeatEntity> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    public void setSeatPos(BlockPos seatPos) {
        this.seatPos = seatPos;
    }

    public static SeatEntity create(Level level, BlockPos seatPos, double yOffset) {
        SeatEntity entity = EntityTypeRegistry.SEAT.get().create(level);
        if (entity == null) {
            return null;
        }
        entity.setSeatPos(seatPos);
        entity.setPos(seatPos.getX() + 0.5D, seatPos.getY() + yOffset, seatPos.getZ() + 0.5D);
        return entity;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("SeatPos")) {
            this.seatPos = BlockPos.of(tag.getLong("SeatPos"));
            this.setPos(seatPos.getX() + 0.5D, this.getY(), seatPos.getZ() + 0.5D);
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putLong("SeatPos", this.seatPos.asLong());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            boolean invalidBlock = !this.level().getBlockState(seatPos).is(BlockRegistry.ZABUTON.get());
            if (!this.isVehicle() || invalidBlock) {
                this.discard();
            }
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

}
