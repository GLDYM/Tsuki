package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public final class GuideBookEvent {
    private static final String DATA_NAME = Tsuki.MODID + "_guide_delivery";
    private static final String RECEIVED_GUIDE_PLAYERS_KEY = "ReceivedGuidePlayers";
    private static final String NOTIFIED_MISSING_MOD_PLAYERS_KEY = "NotifiedMissingModPlayers";
    private static final String LEGACY_RECEIVED_GUIDE_TAG = "TsukiReceivedGuide";
    private static final ResourceLocation GUIDE_ROOT_ADVANCEMENT_ID = ResourceLocation.fromNamespaceAndPath(
            Tsuki.MODID, "guide/root");

    private GuideBookEvent() {
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player) || player instanceof FakePlayer) {
            return;
        }
        if (!TsukiCommonConfig.GIVE_GUIDE_ON_FIRST_LOGIN.get()) {
            return;
        }
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        GuideDeliverySavedData data = GuideDeliverySavedData.get(serverLevel);
        String playerKey = player.getUUID().toString();
        boolean hasGuideMod = ModList.get().isLoaded("guideme") || ModList.get().isLoaded("ageratum");

        if (hasGuideMod) {
            if (data.hasReceivedGuide(playerKey)) {
                return;
            }
            if (hasLegacyGuideRecord(player)) {
                data.markReceivedGuide(playerKey);
                return;
            }
            data.markReceivedGuide(playerKey);
            giveGuide(player, new ItemStack(ItemRegistry.SAKURA_GUIDE.get()));
            return;
        }

        if (data.hasNotifiedMissingMod(playerKey)) {
            return;
        }
        data.markNotifiedMissingMod(playerKey);
        player.displayClientMessage(
                Component.translatable("tsuki.chat.sakura_guide.missing_guide_mod"),
                false);
    }

    private static boolean hasLegacyGuideRecord(ServerPlayer player) {
        if (player.getPersistentData().getBoolean(LEGACY_RECEIVED_GUIDE_TAG)) {
            return true;
        }
        if (player.getInventory().contains(new ItemStack(ItemRegistry.SAKURA_GUIDE.get()))) {
            return true;
        }
        if (player.getServer() == null) {
            return false;
        }
        AdvancementHolder holder = player.getServer().getAdvancements().get(GUIDE_ROOT_ADVANCEMENT_ID);
        return holder != null && player.getAdvancements().getOrStartProgress(holder).isDone();
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

    private static final class GuideDeliverySavedData extends SavedData {
        private final Set<String> receivedGuidePlayers = new HashSet<>();
        private final Set<String> notifiedMissingModPlayers = new HashSet<>();

        private static final Factory<GuideDeliverySavedData> FACTORY = new Factory<>(
                GuideDeliverySavedData::new,
                GuideDeliverySavedData::load);

        private static GuideDeliverySavedData get(ServerLevel level) {
            return level.getServer()
                    .overworld()
                    .getDataStorage()
                    .computeIfAbsent(FACTORY, DATA_NAME);
        }

        private static GuideDeliverySavedData load(CompoundTag tag, HolderLookup.Provider provider) {
            GuideDeliverySavedData data = new GuideDeliverySavedData();
            readStringSet(tag, RECEIVED_GUIDE_PLAYERS_KEY, data.receivedGuidePlayers);
            readStringSet(tag, NOTIFIED_MISSING_MOD_PLAYERS_KEY, data.notifiedMissingModPlayers);
            return data;
        }

        private static void readStringSet(CompoundTag tag, String key, Set<String> target) {
            ListTag list = tag.getList(key, Tag.TAG_STRING);
            for (Tag entry : list) {
                target.add(entry.getAsString());
            }
        }

        private static void writeStringSet(CompoundTag tag, String key, Set<String> source) {
            ListTag list = new ListTag();
            for (String value : source) {
                list.add(StringTag.valueOf(value));
            }
            tag.put(key, list);
        }

        private boolean hasReceivedGuide(String playerKey) {
            return receivedGuidePlayers.contains(playerKey);
        }

        private void markReceivedGuide(String playerKey) {
            if (receivedGuidePlayers.add(playerKey)) {
                setDirty();
            }
        }

        private boolean hasNotifiedMissingMod(String playerKey) {
            return notifiedMissingModPlayers.contains(playerKey);
        }

        private void markNotifiedMissingMod(String playerKey) {
            if (notifiedMissingModPlayers.add(playerKey)) {
                setDirty();
            }
        }

        @Override
        public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
            writeStringSet(tag, RECEIVED_GUIDE_PLAYERS_KEY, receivedGuidePlayers);
            writeStringSet(tag, NOTIFIED_MISSING_MOD_PLAYERS_KEY, notifiedMissingModPlayers);
            return tag;
        }
    }
}
