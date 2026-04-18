package cn.mcmod.tsuki.block;

import com.mojang.serialization.MapCodec;
import cn.mcmod.tsuki.entity.SeatEntity;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class ZabutonBlock extends CarpetBlock {
    public static final MapCodec<ZabutonBlock> CODEC = simpleCodec(ZabutonBlock::new);

    public ZabutonBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends CarpetBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
            BlockHitResult hitResult) {
        if (player.isShiftKeyDown() || player.isPassenger()) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        SeatEntity seat = findSeat(level, pos);
        if (seat == null) {
            seat = SeatEntity.create(level, pos, 0.25D);
            if (seat == null) {
                return InteractionResult.PASS;
            }
            if (!level.addFreshEntity(seat)) {
                return InteractionResult.PASS;
            }
        }

        if (seat.getPassengers().isEmpty()) {
            player.startRiding(seat, false);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            SeatEntity seat = findSeat(level, pos);
            if (seat != null) {
                seat.ejectPassengers();
                seat.discard();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    private static SeatEntity findSeat(Level level, BlockPos pos) {
        AABB area = new AABB(pos).inflate(0.3D, 0.5D, 0.3D);
        List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, area,
                e -> e.blockPosition().equals(pos));
        return seats.isEmpty() ? null : seats.get(0);
    }
}
