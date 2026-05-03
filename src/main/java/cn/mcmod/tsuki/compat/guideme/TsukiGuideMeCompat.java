package cn.mcmod.tsuki.compat.guideme;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.item.ItemRegistry;
import guideme.Guide;
import guideme.GuidesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.common.NeoForge;

public final class TsukiGuideMeCompat {
    public static final ResourceLocation GUIDE_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "guide");
    public static final Guide GUIDE = Guide.builder(GUIDE_ID).build();

    private TsukiGuideMeCompat() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(TsukiGuideMeCompat::onRightClickGuide);
    }

    private static void onRightClickGuide(PlayerInteractEvent.RightClickItem event) {
        if (!event.getItemStack().is(ItemRegistry.SAKURA_GUIDE.get())) {
            return;
        }
        if (event.getEntity().level().isClientSide()) {
            return;
        }

        GuidesCommon.openGuide(event.getEntity(), GUIDE_ID);
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
    }
}
