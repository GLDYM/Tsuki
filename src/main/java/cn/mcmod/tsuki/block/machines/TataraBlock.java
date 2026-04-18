package cn.mcmod.tsuki.block.machines;

import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.item.armors.TsukiArmorToolRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

public class TataraBlock extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty TIMER = IntegerProperty.create("timer", 0, 3);

    public TataraBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(1.75F, 10.0F)
                .sound(SoundType.METAL)
                .lightLevel(state -> state.getValue(LIT) ? 13 : 0)
                .randomTicks());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, false)
                .setValue(TIMER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, TIMER);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
            Player player,
            InteractionHand hand, BlockHitResult hitResult) {
        if (hand != InteractionHand.MAIN_HAND || state.getValue(LIT)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        boolean isFlintAndSteel = stack.is(Items.FLINT_AND_STEEL);
        boolean isFireCharge = stack.is(Items.FIRE_CHARGE);
        if (!isFlintAndSteel && !isFireCharge) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (level.isClientSide()) {
            return ItemInteractionResult.SUCCESS;
        }

        level.setBlock(pos, state.setValue(LIT, true).setValue(TIMER, 0), 3);
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.8F);

        if (isFlintAndSteel) {
            stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
        } else if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return ItemInteractionResult.CONSUME;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT) || !level.isAreaLoaded(pos, 1) || state.getValue(TIMER) >= 3) {
            return;
        }

        spreadSmelting(level, pos);
        int timer = state.getValue(TIMER);
        if (timer < 3) {
            level.setBlock(pos, state.setValue(TIMER, timer + 1), 2);
        }
    }

    private void spreadSmelting(ServerLevel level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = pos.relative(direction);
            BlockState adjacentState = level.getBlockState(adjacentPos);
            if (adjacentState.is(this) && !adjacentState.getValue(LIT)) {
                level.setBlock(adjacentPos, adjacentState.setValue(LIT, true).setValue(TIMER, 0), 2);
            }
            // if (adjacentState.is(this) && adjacentState.getValue(LIT) &&
            // adjacentState.getValue(TIMER) < 3) {
            // level.setBlock(adjacentPos, adjacentState.setValue(TIMER, 3), 2);
            // }
        }
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> drops = new ArrayList<>();
        if (!state.getValue(LIT) || state.getValue(TIMER) < 3) {
            drops.add(new ItemStack(BlockRegistry.TATARA.get()));
            return drops;
        }

        RandomSource random = params.getLevel().getRandom();
        if (random.nextInt(5) == 0) {
            for (int i = 0; i < 2; ++i) {
                if (random.nextInt(2) == 0) {
                    drops.add(new ItemStack(TsukiArmorToolRegistry.TAMAHAGANE.get()));
                }
            }
        }
        for (int i = 0; i < 9; ++i) {
            if (random.nextInt(9) <= 7) {
                drops.add(new ItemStack(Items.IRON_INGOT));
            }
        }
        return drops;
    }
}
