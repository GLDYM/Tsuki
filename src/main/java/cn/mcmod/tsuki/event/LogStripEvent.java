package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.init.block.BlockRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent.BlockToolModificationEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber()
public class LogStripEvent {

    @SubscribeEvent
    public static void onAxeStrippingLog(BlockToolModificationEvent event) {
        if (ItemAbilities.AXE_STRIP.equals(event.getItemAbility())) {
            stripLog(event, BlockRegistry.SAKURA_LOG.get(), BlockRegistry.STRIPPED_SAKURA_LOG.get());
            stripLog(event, BlockRegistry.MAPLE_LOG.get(), BlockRegistry.STRIPPED_MAPLE_LOG.get());
            stripLog(event, BlockRegistry.MAPLE_SAP_LOG.get(), BlockRegistry.STRIPPED_MAPLE_LOG.get());
            stripLog(event, BlockRegistry.UME_LOG.get(), BlockRegistry.STRIPPED_UME_LOG.get());

            stripLog(event, BlockRegistry.SAKURA_WOOD.get(), BlockRegistry.STRIPPED_SAKURA_WOOD.get());
            stripLog(event, BlockRegistry.MAPLE_WOOD.get(), BlockRegistry.STRIPPED_MAPLE_WOOD.get());
            stripLog(event, BlockRegistry.UME_WOOD.get(), BlockRegistry.STRIPPED_UME_WOOD.get());
        }
    }

    private static void stripLog(BlockToolModificationEvent event, Block log, Block stripped_log) {
        BlockState origin = event.getState();
        if (origin.is(log)) {
            event.getLevel().playSound(event.getPlayer(), event.getPos(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS,
                    1.0F, 1.0F);
            event.setFinalState(stripped_log.withPropertiesOf(origin));
        }
    }
}
