package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.compat.curios.CuriosCompat;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.tag.TsukiItemTags;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaSoulboundEvent {
    private static final String SOULBOUND_MAGATAMAS_KEY = "TsukiSoulboundMagatamas";

    private MagatamaSoulboundEvent() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) {
            return;
        }

        List<ItemStack> toKeep = new ArrayList<>();
        Inventory inventory = player.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (isSoulbound(stack)) {
                toKeep.add(stack.copy());
                inventory.setItem(slot, ItemStack.EMPTY);
            }
        }
        toKeep.addAll(CuriosCompat.removeStacks(player, MagatamaSoulboundEvent::isSoulbound));

        if (toKeep.isEmpty()) {
            return;
        }

        storeSoulboundItems(player, toKeep);
    }

    private static boolean isSoulbound(ItemStack stack) {
        return stack.is(TsukiItemTags.MAGATAMAS) || stack.is(ArmorToolRegistry.YATA_NO_KAGAMI.get());
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
