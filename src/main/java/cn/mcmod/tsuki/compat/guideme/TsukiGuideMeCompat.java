package cn.mcmod.tsuki.compat.guideme;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.item.ItemRegistry;
import guideme.Guide;
import guideme.GuidesCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class TsukiGuideMeCompat {
    private static final String RECEIVED_GUIDE_TAG = "TsukiReceivedGuide";
    public static final ResourceLocation GUIDE_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "guide");
    public static final Guide GUIDE = Guide.builder(GUIDE_ID).build();

    private TsukiGuideMeCompat() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(TsukiGuideMeCompat::onPlayerLoggedIn);
        NeoForge.EVENT_BUS.addListener(TsukiGuideMeCompat::onRightClickGuide);
    }

    private static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        if (!TsukiCommonConfig.GIVE_GUIDE_ON_FIRST_LOGIN.get()) {
            return;
        }

        var persistentData = event.getEntity().getPersistentData();
        if (persistentData.getBoolean(RECEIVED_GUIDE_TAG)) {
            return;
        }

        persistentData.putBoolean(RECEIVED_GUIDE_TAG, true);
        giveGuide(event.getEntity(), new ItemStack(ItemRegistry.SAKURA_GUIDE.get()));
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

    private static void giveGuide(net.minecraft.world.entity.player.Player player, ItemStack stack) {
        if (player.addItem(stack)) {
            return;
        }

        ItemEntity itemEntity = player.drop(stack, false);
        if (itemEntity != null) {
            itemEntity.setNoPickUpDelay();
            itemEntity.setTarget(player.getUUID());
        }
    }
}
