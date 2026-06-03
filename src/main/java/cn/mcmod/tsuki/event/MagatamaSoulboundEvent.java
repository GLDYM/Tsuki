package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaSoulboundEvent {
    private static final String SOULBOUND_MAGATAMAS_KEY = "TsukiSoulboundMagatamas";

    private MagatamaSoulboundEvent() {
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) {
            return;
        }

        List<ItemEntity> toRemove = new ArrayList<>();
        List<ItemStack> toKeep = new ArrayList<>();

        for (ItemEntity drop : event.getDrops()) {
            ItemStack stack = drop.getItem();
            if (!stack.is(TsukiItemTags.MAGATAMAS)) {
                continue;
            }

            toKeep.add(stack.copy());
            toRemove.add(drop);
        }

        if (toKeep.isEmpty()) {
            return;
        }

        toRemove.forEach(event.getDrops()::remove);
        storeSoulboundItems(player, toKeep);
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) {
            return;
        }

        List<ItemStack> soulboundItems = consumeSoulboundItems(event.getOriginal());
        if (soulboundItems.isEmpty()) {
            return;
        }

        for (ItemStack stack : soulboundItems) {
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
        }
    }

    private static void storeSoulboundItems(Player player, List<ItemStack> soulboundItems) {
        ListTag listTag = new ListTag();
        for (ItemStack stack : soulboundItems) {
            listTag.add(stack.saveOptional(player.registryAccess()));
        }
        player.getPersistentData().put(SOULBOUND_MAGATAMAS_KEY, listTag);
    }

    private static List<ItemStack> consumeSoulboundItems(Player player) {
        List<ItemStack> soulboundItems = new ArrayList<>();
        ListTag listTag = player.getPersistentData().getList(SOULBOUND_MAGATAMAS_KEY, Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag stackTag = listTag.getCompound(i);
            ItemStack stack = ItemStack.parseOptional(player.registryAccess(), stackTag);
            if (!stack.isEmpty()) {
                soulboundItems.add(stack);
            }
        }
        player.getPersistentData().remove(SOULBOUND_MAGATAMAS_KEY);
        return soulboundItems;
    }
}
