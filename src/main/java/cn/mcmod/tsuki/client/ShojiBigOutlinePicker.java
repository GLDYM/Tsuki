package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.decoration.ShojiBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Extends the normal block pick result to the interaction shape of a nearby
 * open shōji panel. The previous implementation traced the whole view ray and
 * inspected a 3x3x3 area for every step; this version only inspects the block
 * the vanilla picker already hit and its immediate neighbours.
 */
public final class ShojiBigOutlinePicker {
    private static final double MAX_TRACE_DISTANCE = 16.0D;
    private static final double TRACE_MARGIN = 1.0D;

    private static boolean rangeWarned;

    private ShojiBigOutlinePicker() {
    }

    public static void pick() {
        Minecraft minecraft = Minecraft.getInstance();
        if (!(minecraft.cameraEntity instanceof LocalPlayer player) || minecraft.level == null) {
            return;
        }
        if (!(minecraft.hitResult instanceof BlockHitResult blockHit)
                || blockHit.getType() != HitResult.Type.BLOCK) {
            return;
        }

        Vec3 origin = player.getEyePosition();
        if (!isFinite(origin)) {
            return;
        }

        double limit = getPickLimit(minecraft, player, origin);
        if (!(limit > 0.0D)) {
            return;
        }

        Vec3 target = getTraceTarget(player, limit + TRACE_MARGIN, origin);
        if (!isFinite(target)) {
            return;
        }

        BlockHitResult result = findHit(minecraft.level, origin, target, blockHit.getBlockPos(), limit * limit);
        if (result != null) {
            minecraft.hitResult = result;
            minecraft.crosshairPickEntity = null;
        }
    }

    private static double getPickLimit(Minecraft minecraft, LocalPlayer player, Vec3 origin) {
        double range = player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);
        if (!Double.isFinite(range)) {
            warnOnce("Block interaction range is not finite ({}), shōji outline picking is disabled", range);
            return 0.0D;
        }
        if (range > MAX_TRACE_DISTANCE) {
            warnOnce("Block interaction range is {} blocks, capping shōji outline picking at {} blocks", range,
                    MAX_TRACE_DISTANCE);
        }

        double limit = Math.min(range, MAX_TRACE_DISTANCE);
        if (minecraft.hitResult != null) {
            double hitDistance = Math.sqrt(minecraft.hitResult.getLocation().distanceToSqr(origin));
            if (!Double.isFinite(hitDistance)) {
                return 0.0D;
            }
            limit = Math.min(limit, hitDistance + 0.5D);
        }
        return limit;
    }

    private static BlockHitResult findHit(Level level, Vec3 origin, Vec3 target, BlockPos hitPos, double limitSqr) {
        if (!Double.isFinite(limitSqr) || limitSqr <= 0.0D) {
            return null;
        }

        BlockHitResult bestResult = null;
        double bestDistance = limitSqr;

        for (BlockPos cursor : BlockPos.betweenClosed(hitPos.offset(-1, -1, -1), hitPos.offset(1, 1, 1))) {
            BlockPos candidatePos = new BlockPos(cursor);
            BlockState candidateState = level.getBlockState(candidatePos);
            if (!(candidateState.getBlock() instanceof ShojiBlock)
                    || !candidateState.getValue(ShojiBlock.OPEN)) {
                continue;
            }

            BlockHitResult candidateHit = candidateState.getInteractionShape(level, candidatePos)
                    .clip(origin, target, candidatePos);
            if (candidateHit == null) {
                continue;
            }

            double distance = candidateHit.getLocation().distanceToSqr(origin);
            if (distance >= bestDistance) {
                continue;
            }

            Vec3 hitLocation = clampInsideBlock(candidateHit);
            bestDistance = distance;
            bestResult = new BlockHitResult(hitLocation, candidateHit.getDirection(),
                    candidateHit.getBlockPos(), candidateHit.isInside());
        }

        return bestResult;
    }

    private static Vec3 clampInsideBlock(BlockHitResult hit) {
        BlockPos hitPos = hit.getBlockPos();
        Vec3 local = hit.getLocation().subtract(Vec3.atCenterOf(hitPos));
        return Vec3.atCenterOf(hitPos).add(Mth.clamp(local.x, -1.0D, 1.0D), Mth.clamp(local.y, -1.0D, 1.0D),
                Mth.clamp(local.z, -1.0D, 1.0D));
    }

    private static Vec3 getTraceTarget(LocalPlayer player, double range, Vec3 origin) {
        float xRot = player.getXRot();
        float yRot = player.getYRot();
        float yawCos = Mth.cos(-yRot * ((float) Math.PI / 180F) - (float) Math.PI);
        float yawSin = Mth.sin(-yRot * ((float) Math.PI / 180F) - (float) Math.PI);
        float pitchCos = -Mth.cos(-xRot * ((float) Math.PI / 180F));
        float pitchSin = Mth.sin(-xRot * ((float) Math.PI / 180F));
        float x = yawSin * pitchCos;
        float z = yawCos * pitchCos;
        return origin.add(x * range, pitchSin * range, z * range);
    }

    private static boolean isFinite(Vec3 vec) {
        return Double.isFinite(vec.x) && Double.isFinite(vec.y) && Double.isFinite(vec.z);
    }

    private static void warnOnce(String message, Object... args) {
        if (rangeWarned) {
            return;
        }
        rangeWarned = true;
        Tsuki.getLogger().warn(message, args);
    }
}
