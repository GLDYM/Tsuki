// package cn.mcmod.tsuki.events;

// import cn.mcmod.tsuki.Tsuki;
// import cn.mcmod.tsuki.block.ShojiBlock;
// import net.minecraft.core.BlockPos;
// import net.minecraft.core.Direction;
// import net.minecraft.world.InteractionResult;
// import net.minecraft.world.level.Level;
// import net.minecraft.world.level.block.state.BlockState;
// import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
// import net.minecraft.world.phys.AABB;
// import net.minecraft.world.phys.BlockHitResult;
// import net.minecraft.world.phys.Vec3;
// import net.neoforged.fml.common.EventBusSubscriber;
// import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

// TODO: Worng Impl! 
// @EventBusSubscriber(modid = Tsuki.MODID)
// public class ShojiInteractionHandler {
//     private static final double PLANE_MIN = 7.0D / 16.0D;
//     private static final double PLANE_MAX = 9.0D / 16.0D;
//     private static final double OPEN_STRIP = 3.0D / 16.0D;

//     @net.neoforged.bus.api.SubscribeEvent
//     public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
//         Level level = event.getLevel();
//         if (level.isClientSide()) {
//             return;
//         }

//         BlockHitResult hit = event.getHitVec();
//         Vec3 location = hit.getLocation();

//         for (BlockPos candidatePos : BlockPos.betweenClosed(event.getPos().offset(-1, -1, -1),
//                 event.getPos().offset(1, 1, 1))) {
//             BlockState state = level.getBlockState(candidatePos);
//             if (!(state.getBlock() instanceof ShojiBlock) || !state.getValue(ShojiBlock.OPEN)) {
//                 continue;
//             }

//             BlockPos basePos = state.getValue(ShojiBlock.HALF) == DoubleBlockHalf.UPPER ? candidatePos.below()
//                     : candidatePos.immutable();
//             BlockState baseState = level.getBlockState(basePos);
//             if (!(baseState.getBlock() instanceof ShojiBlock) || !baseState.getValue(ShojiBlock.OPEN)
//                     || baseState.getValue(ShojiBlock.HALF) != DoubleBlockHalf.LOWER) {
//                 continue;
//             }

//             if (!getExtendedHitbox(basePos, baseState.getValue(ShojiBlock.FACING)).contains(location)) {
//                 continue;
//             }

//             if (isInsideBaseColumn(basePos, location)) {
//                 continue;
//             }

//             ShojiBlock.toggle(level, basePos, baseState);
//             event.setCanceled(true);
//             event.setCancellationResult(InteractionResult.SUCCESS);
//             return;
//         }
//     }

//     private static boolean isInsideBaseColumn(BlockPos basePos, Vec3 hit) {
//         return hit.x >= basePos.getX() && hit.x <= basePos.getX() + 1
//                 && hit.z >= basePos.getZ() && hit.z <= basePos.getZ() + 1
//                 && hit.y >= basePos.getY() && hit.y <= basePos.getY() + 2;
//     }

//     private static AABB getExtendedHitbox(BlockPos basePos, Direction facing) {
//         double x1 = basePos.getX();
//         double y1 = basePos.getY();
//         double z1 = basePos.getZ();
//         double x2 = x1 + 1;
//         double y2 = y1 + 2;
//         double z2 = z1 + 1;

//         return switch (facing) {
//             case NORTH -> new AABB(x1 + 1.0D - OPEN_STRIP, y1, z1 + PLANE_MIN, x1 + 2.0D, y2, z1 + PLANE_MAX);
//             case SOUTH -> new AABB(x1 - 1.0D, y1, z1 + PLANE_MIN, x1 + OPEN_STRIP, y2, z1 + PLANE_MAX);
//             case WEST -> new AABB(x1 + PLANE_MIN, y1, z1 + 1.0D - OPEN_STRIP, x1 + PLANE_MAX, y2, z1 + 2.0D);
//             case EAST -> new AABB(x1 + PLANE_MIN, y1, z1 - 1.0D, x1 + PLANE_MAX, y2, z1 + OPEN_STRIP);
//             default -> new AABB(basePos);
//         };
//     }
// }
