package cn.mcmod.tsuki.compat.ageratum;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import dev.anvilcraft.resource.ageratum.Ageratum;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class TsukiAgeratumCompat {
    private static final ResourceLocation GUIDE_DOCUMENT = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "index");

    private TsukiAgeratumCompat() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(TsukiAgeratumCompat::onRightClickGuide);
    }

    private static void onRightClickGuide(PlayerInteractEvent.RightClickItem event) {
        if (!event.getItemStack().is(ItemRegistry.SAKURA_GUIDE.get())) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        Ageratum.openGuide(player, GUIDE_DOCUMENT);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
    }
}
