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
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class MagatamaSoulboundEvent {
    private static final String SOULBOUND_MAGATAMAS_KEY = "TsukiSoulboundMagatamas";
    private static final String SOULBOUND_CURIOS_KEY = "TsukiSoulboundCurios";
    private static final String CURIO_SLOT_ID_KEY = "SlotId";
    private static final String CURIO_SLOT_INDEX_KEY = "SlotIndex";
    private static final String CURIO_STACK_KEY = "Stack";

    private MagatamaSoulboundEvent() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.isCanceled()) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) {
            return;
        }
        if (player.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            return;
        }

        List<ItemStack> inventoryToKeep = new ArrayList<>();
        Inventory inventory = player.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack stack = inventory.getItem(slot);
            if (isSoulbound(stack)) {
                inventoryToKeep.add(stack.copy());
                inventory.setItem(slot, ItemStack.EMPTY);
            }
        }
        List<CuriosCompat.RemovedCurioStack> curiosToKeep = CuriosCompat.removeStacksWithSlots(
                player, MagatamaSoulboundEvent::isSoulbound);

        if (inventoryToKeep.isEmpty() && curiosToKeep.isEmpty()) {
            return;
        }

        appendSoulboundItems(player, inventoryToKeep, curiosToKeep);
    }

    private static boolean isSoulbound(ItemStack stack) {
        return stack.is(TsukiItemTags.MAGATAMAS) || stack.is(ArmorToolRegistry.YATA_NO_KAGAMI.get());
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) {
            return;
        }

        Player original = event.getOriginal();
        if (!event.isWasDeath()) {
            copyPendingSoulboundTags(original, player);
            return;
        }

        List<ItemStack> soulboundItems = consumeSoulboundItems(original);
        List<CuriosCompat.RemovedCurioStack> soulboundCurios = consumeSoulboundCurios(original);

        for (CuriosCompat.RemovedCurioStack entry : soulboundCurios) {
            if (!CuriosCompat.tryRestoreStack(player, entry.slotId(), entry.slotIndex(), entry.stack())) {
                giveOrDrop(player, entry.stack());
            }
        }
        for (ItemStack stack : soulboundItems) {
            giveOrDrop(player, stack);
        }
    }

    private static void giveOrDrop(ServerPlayer player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }

    private static void copyPendingSoulboundTags(Player original, Player target) {
        CompoundTag originalData = original.getPersistentData();
        CompoundTag targetData = target.getPersistentData();
        if (originalData.contains(SOULBOUND_MAGATAMAS_KEY, Tag.TAG_LIST)) {
            targetData.put(SOULBOUND_MAGATAMAS_KEY, originalData.getList(SOULBOUND_MAGATAMAS_KEY, Tag.TAG_COMPOUND).copy());
            originalData.remove(SOULBOUND_MAGATAMAS_KEY);
        }
        if (originalData.contains(SOULBOUND_CURIOS_KEY, Tag.TAG_LIST)) {
            targetData.put(SOULBOUND_CURIOS_KEY, originalData.getList(SOULBOUND_CURIOS_KEY, Tag.TAG_COMPOUND).copy());
            originalData.remove(SOULBOUND_CURIOS_KEY);
        }
    }

    private static void appendSoulboundItems(
            Player player,
            List<ItemStack> inventoryItems,
            List<CuriosCompat.RemovedCurioStack> curiosItems) {
        CompoundTag persistentData = player.getPersistentData();
        if (!inventoryItems.isEmpty()) {
            ListTag listTag = persistentData.getList(SOULBOUND_MAGATAMAS_KEY, Tag.TAG_COMPOUND);
            for (ItemStack stack : inventoryItems) {
                listTag.add(stack.saveOptional(player.registryAccess()));
            }
            persistentData.put(SOULBOUND_MAGATAMAS_KEY, listTag);
        }
        if (!curiosItems.isEmpty()) {
            ListTag curiosTag = persistentData.getList(SOULBOUND_CURIOS_KEY, Tag.TAG_COMPOUND);
            for (CuriosCompat.RemovedCurioStack entry : curiosItems) {
                CompoundTag entryTag = new CompoundTag();
                entryTag.putString(CURIO_SLOT_ID_KEY, entry.slotId());
                entryTag.putInt(CURIO_SLOT_INDEX_KEY, entry.slotIndex());
                entryTag.put(CURIO_STACK_KEY, entry.stack().saveOptional(player.registryAccess()));
                curiosTag.add(entryTag);
            }
            persistentData.put(SOULBOUND_CURIOS_KEY, curiosTag);
        }
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

    private static List<CuriosCompat.RemovedCurioStack> consumeSoulboundCurios(Player player) {
        List<CuriosCompat.RemovedCurioStack> soulboundCurios = new ArrayList<>();
        ListTag listTag = player.getPersistentData().getList(SOULBOUND_CURIOS_KEY, Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag entryTag = listTag.getCompound(i);
            ItemStack stack = ItemStack.parseOptional(player.registryAccess(), entryTag.getCompound(CURIO_STACK_KEY));
            if (!stack.isEmpty()) {
                soulboundCurios.add(new CuriosCompat.RemovedCurioStack(
                        entryTag.getString(CURIO_SLOT_ID_KEY),
                        entryTag.getInt(CURIO_SLOT_INDEX_KEY),
                        stack));
            }
        }
        player.getPersistentData().remove(SOULBOUND_CURIOS_KEY);
        return soulboundCurios;
    }
}
