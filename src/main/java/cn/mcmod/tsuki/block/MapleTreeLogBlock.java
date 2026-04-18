
package cn.mcmod.tsuki.block;

import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;

public class MapleTreeLogBlock extends RotatedPillarBlock {

    public MapleTreeLogBlock() {
        super(BlockBehaviour.Properties.of().mapColor(
                state -> (state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.WOOD
                        : MapColor.PODZOL))
                .strength(2.0F).sound(SoundType.WOOD));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemstack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitresult) {
        if (itemstack.canPerformAction(ItemAbilities.SHEARS_CARVE)) {
            if (!level.isClientSide) {
                level.playSound((Player) null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, BlockRegistry.MAPLE_SAP_LOG.get().withPropertiesOf(state)
                        .setValue(MapleTreeSapLogBlock.EXHAUSTION, false), 11);
                itemstack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                level.gameEvent(player, GameEvent.SHEAR, pos);
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility ItemAbility,
            boolean simulate) {
        if (context.getItemInHand().canPerformAction(ItemAbilities.SHEARS_CARVE)) {
            return BlockRegistry.MAPLE_SAP_LOG.get().withPropertiesOf(state).setValue(MapleTreeSapLogBlock.EXHAUSTION,
                    false);
        }
        return super.getToolModifiedState(state, context, ItemAbility, simulate);
    }
}



