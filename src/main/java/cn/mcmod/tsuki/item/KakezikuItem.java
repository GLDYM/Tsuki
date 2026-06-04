package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.entity.KakezikuEntity;
import cn.mcmod.tsuki.init.EntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class KakezikuItem extends HangingEntityItem {
    public KakezikuItem(Properties properties) {
        super(EntityTypeRegistry.KAKEZIKU.get(), properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos placementPos = context.getClickedPos().relative(context.getClickedFace());
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        if (player != null && !this.mayPlace(player, context.getClickedFace(), stack, placementPos)) {
            return InteractionResult.FAIL;
        }

        Level level = context.getLevel();
        HangingEntity entity = KakezikuEntity.createKakeziku(level, placementPos, context.getClickedFace()).orElse(null);
        if (entity == null) {
            return InteractionResult.CONSUME;
        }

        CustomData customData = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
        if (!customData.isEmpty()) {
            EntityType.updateCustomEntityTag(level, player, entity, customData);
        }

        if (!entity.survives()) {
            return InteractionResult.CONSUME;
        }

        if (!level.isClientSide) {
            entity.playPlacementSound();
            level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position());
            level.addFreshEntity(entity);
        }

        stack.shrink(1);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
