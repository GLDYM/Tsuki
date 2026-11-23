package cn.mcmod.tsuki.compat.ageratum;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import dev.anvilcraft.resource.ageratum.Ageratum;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public final class TsukiAgeratumCompat {
    private static final ResourceLocation GUIDE_DOCUMENT = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "index");
    private static final ResourceLocation GUIDE_ROOT_ADVANCEMENT_ID = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "guide/root");

    private TsukiAgeratumCompat() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(TsukiAgeratumCompat::onAdvancementEarned);
        NeoForge.EVENT_BUS.addListener(TsukiAgeratumCompat::onRightClickGuide);
    }

    private static void onAdvancementEarned(AdvancementEvent.AdvancementEarnEvent event) {
        if (!event.getAdvancement().id().equals(GUIDE_ROOT_ADVANCEMENT_ID)) {
            return;
        }
        if (!TsukiCommonConfig.GIVE_GUIDE_ON_FIRST_LOGIN.get()) {
            return;
        }

        giveGuide(event.getEntity(), new ItemStack(ItemRegistry.SAKURA_GUIDE.get()));
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

    private static void giveGuide(Player player, ItemStack stack) {
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
