package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.block.decoration.ShojiBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public final class ShojiBigOutlinePicker {
    private ShojiBigOutlinePicker() {
    }

    public static void pick() {
        Minecraft minecraft = Minecraft.getInstance();
        if (!(minecraft.cameraEntity instanceof LocalPlayer player) || minecraft.level == null) {
            return;
        }

        Vec3 origin = player.getEyePosition();
        double maxRange = minecraft.hitResult == null ? Double.MAX_VALUE
                : minecraft.hitResult.getLocation().distanceToSqr(origin) + 0.5D;
        double range = player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);
        Vec3 target = getTraceTarget(player, Math.min(maxRange, range) + 1.0D, origin);

        BlockHitResult result = findHit(minecraft, origin, target, maxRange);
        if (result != null) {
            minecraft.hitResult = result;
            minecraft.crosshairPickEntity = null;
        }
    }

    private static BlockHitResult findHit(Minecraft minecraft, Vec3 origin, Vec3 target, double maxRange) {
        BlockHitResult bestResult = null;
        double bestDistance = maxRange;

        TraceResult trace = traceUntil(origin, target);
        while (trace != null && !trace.missed()) {
            BlockPos pos = trace.pos();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos candidatePos = pos.offset(x, y, z);
                        BlockState candidateState = minecraft.level.getBlockState(candidatePos);
                        if (!(candidateState.getBlock() instanceof ShojiBlock)
                                || !candidateState.getValue(ShojiBlock.OPEN)) {
                            continue;
                        }

                        BlockHitResult candidateHit = candidateState.getInteractionShape(minecraft.level, candidatePos)
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
                }
            }

            if (bestResult != null) {
                return bestResult;
            }

            trace = trace.next();
        }

        return null;
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

    private static TraceResult traceUntil(Vec3 start, Vec3 end) {
        if (Double.isNaN(start.x) || Double.isNaN(start.y) || Double.isNaN(start.z)
                || Double.isNaN(end.x) || Double.isNaN(end.y) || Double.isNaN(end.z)) {
            return null;
        }

        return new TraceResult(start, end, Mth.floor(end.x), Mth.floor(end.y), Mth.floor(end.z),
                Mth.floor(start.x), Mth.floor(start.y), Mth.floor(start.z));
    }

    private record TraceResult(Vec3 start, Vec3 end, int dx, int dy, int dz, int x, int y, int z) {
        boolean missed() {
            return x == dx && y == dy && z == dz;
        }

        BlockPos pos() {
            return new BlockPos(x, y, z);
        }

        TraceResult next() {
            if (missed()) {
                return null;
            }

            boolean stepX = dx != x;
            boolean stepY = dy != y;
            boolean stepZ = dz != z;

            double nextX = 999.0D;
            double nextY = 999.0D;
            double nextZ = 999.0D;

            if (stepX) {
                nextX = dx > x ? x + 1.0D : x;
            }
            if (stepY) {
                nextY = dy > y ? y + 1.0D : y;
            }
            if (stepZ) {
                nextZ = dz > z ? z + 1.0D : z;
            }

            double deltaX = end.x - start.x;
            double deltaY = end.y - start.y;
            double deltaZ = end.z - start.z;
            double progressX = stepX ? (nextX - start.x) / deltaX : 999.0D;
            double progressY = stepY ? (nextY - start.y) / deltaY : 999.0D;
            double progressZ = stepZ ? (nextZ - start.z) / deltaZ : 999.0D;

            if (progressX == -0.0D) {
                progressX = -1.0E-4D;
            }
            if (progressY == -0.0D) {
                progressY = -1.0E-4D;
            }
            if (progressZ == -0.0D) {
                progressZ = -1.0E-4D;
            }

            Direction stepDirection;
            Vec3 nextStart;
            if (progressX < progressY && progressX < progressZ) {
                stepDirection = dx > x ? Direction.WEST : Direction.EAST;
                nextStart = new Vec3(nextX, start.y + deltaY * progressX, start.z + deltaZ * progressX);
            } else if (progressY < progressZ) {
                stepDirection = dy > y ? Direction.DOWN : Direction.UP;
                nextStart = new Vec3(start.x + deltaX * progressY, nextY, start.z + deltaZ * progressY);
            } else {
                stepDirection = dz > z ? Direction.NORTH : Direction.SOUTH;
                nextStart = new Vec3(start.x + deltaX * progressZ, start.y + deltaY * progressZ, nextZ);
            }

            int nextBlockX = Mth.floor(nextStart.x) - (stepDirection == Direction.EAST ? 1 : 0);
            int nextBlockY = Mth.floor(nextStart.y) - (stepDirection == Direction.UP ? 1 : 0);
            int nextBlockZ = Mth.floor(nextStart.z) - (stepDirection == Direction.SOUTH ? 1 : 0);
            return new TraceResult(nextStart, end, dx, dy, dz, nextBlockX, nextBlockY, nextBlockZ);
        }
    }
}
