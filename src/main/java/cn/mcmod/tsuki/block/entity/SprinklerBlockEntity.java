package cn.mcmod.tsuki.block.entity;

import cn.mcmod.tsuki.block.machine.SprinklerBlock;
import cn.mcmod.tsuki.block.crop.SunflowerCropBlock;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.common.ItemAbilities;

public class SprinklerBlockEntity extends BlockEntity {
    public SprinklerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SPRINKLER.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SprinklerBlockEntity sprinkler) {
        if (level.isClientSide) return;
        ServerLevel server = (ServerLevel) level;
        if (level.getGameTime() % 20 == 0) wetFarmland(server, pos);
        SprinklerBlock.Material material = state.getValue(SprinklerBlock.MATERIAL);
        if (server.random.nextInt(material.growthRate()) == 0) {
            accelerate(server, pos, material.growthStages());
        }
    }

    private static void wetFarmland(ServerLevel level, BlockPos center) {
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-4, -1, -4), center.offset(4, 1, 4))) {
            BlockState state = level.getBlockState(pos);
            if (state.hasProperty(FarmBlock.MOISTURE)) {
                level.setBlock(pos, state.setValue(FarmBlock.MOISTURE, 7), 2);
            }
        }
    }

    private static void accelerate(ServerLevel level, BlockPos center, int growthStages) {
        for (BlockPos pos : BlockPos.betweenClosed(center.offset(-4, -1, -4), center.offset(4, 1, 4))) {
            BlockState state = level.getBlockState(pos);
            growByAge(level, pos, state, growthStages);
        }
    }

    private static void growByAge(ServerLevel level, BlockPos pos, BlockState state, int growthStages) {
        if (state.getBlock() instanceof SunflowerCropBlock sunflower) {
            sunflower.growFromSprinkler(level, pos, state, growthStages);
            return;
        }

        if (state.hasProperty(BlockStateProperties.AGE_7)) {
            int currentAge = state.getValue(BlockStateProperties.AGE_7);
            if (currentAge < 7) {
                level.setBlock(pos, state.setValue(BlockStateProperties.AGE_7,
                        Math.min(currentAge + growthStages, 7)), 2);
            }
        } else if (state.hasProperty(BlockStateProperties.AGE_3)) {
            int currentAge = state.getValue(BlockStateProperties.AGE_3);
            if (currentAge < 3) {
                level.setBlock(pos, state.setValue(BlockStateProperties.AGE_3,
                        Math.min(currentAge + growthStages, 3)), 2);
            }
        } else if (state.hasProperty(BlockStateProperties.AGE_5)) {
            int currentAge = state.getValue(BlockStateProperties.AGE_5);
            if (currentAge < 5) {
                level.setBlock(pos, state.setValue(BlockStateProperties.AGE_5,
                        Math.min(currentAge + growthStages, 5)), 2);
            }
        }
    }

    public static int till(Level level, BlockPos center, Player player, InteractionHand hand, ItemStack hoe) {
        int converted = 0;
        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                for (int y = 1; y >= -1; y--) {
                    BlockPos pos = center.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    if (!level.isEmptyBlock(pos.above())) continue;
                    UseOnContext context = new UseOnContext(level, player, hand, hoe,
                            new BlockHitResult(pos.getCenter(), Direction.UP, pos, false));
                    BlockState tilled = state.getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
                    if (tilled != null && tilled != state) {
                        level.setBlock(pos, tilled, 3);
                        converted++;
                        break;
                    }
                }
            }
        }
        return converted;
    }
}
