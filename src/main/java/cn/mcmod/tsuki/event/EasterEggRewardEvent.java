package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.enums.TsukiCuisineSet;
import cn.mcmod.tsuki.init.item.enums.TsukiFoodSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@EventBusSubscriber(modid = Tsuki.MODID)
public final class EasterEggRewardEvent {
    private static final String DATA_NAME = Tsuki.MODID + "_easter_egg_rewards";
    private static final String REWARDED_PLAYERS_KEY = "RewardedPlayers";

    private static final List<RewardRule> SPECIAL_REWARDS = List.of(
            // Dev
            RewardRule.builder()
                    .names("Dev")
                    .rewards(
                        stack(ArmorToolRegistry.SAKURA_DIAMOND, 4)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build(),
            // Polaris_Light
            RewardRule.builder()
                    .names("Polaris_Light")
                    .uuids("cf6bcfbc-3e2a-4aa4-be23-b45d2482fd98")
                    .rewards(
                        stack(TsukiCuisineSet.RICE_CURRY_KATSU.getItem(), 64)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build(),
            RewardRule.builder()
                    // .names("Polaris_Light")
                    .uuids("befa7fff-4d8e-3ff9-a6ae-6f122a2e4bb8")
                    .rewards(
                        stack(TsukiCuisineSet.RICE_CURRY_KATSU.getItem(), 64)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build(),
            RewardRule.builder()
                    .names("Stargz_10")
                    .uuids("0302463f-0cdf-42a8-aea6-996b84fceffa")
                    .rewards(
                        stack(TsukiCuisineSet.RICE_CURRY_KATSU.getItem(), 64)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build(),
            RewardRule.builder()
                    // .names("Stargz_10")
                    .uuids("c0c97bf5-b51a-352f-bd10-3058da95118f")
                    .rewards(
                        stack(TsukiCuisineSet.RICE_CURRY_KATSU.getItem(), 64)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build(),
            RewardRule.builder()
                    .names("Syameimaru_Zheng")
                    .uuids("f1191641-34ab-4077-a6df-5c03a1a1170a")
                    .rewards(
                        stack(TsukiFoodSet.SUSHI.getItem(), 64)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build(),
            RewardRule.builder()
                    .names("bagu_chan")
                    .uuids("e1db1fa2-d0be-4075-8c16-9c489e3a2941")
                    .rewards(
                        stack(TsukiFoodSet.BUGGYS_MEAT.getItem(), 16)
                    )
                    .messageKey("tsuki.chat.easter_egg.rewarded")
                    .build()
    );

    private EasterEggRewardEvent() {
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }

        var player = event.getEntity();
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        var data = RewardedPlayersSavedData.get(serverLevel);
        String playerKey = player.getUUID().toString();
        if (data.hasReceivedReward(playerKey)) {
            return;
        }

        RewardRule matchedRule = findMatchingRule(player);
        if (matchedRule == null) {
            return;
        }

        data.markRewarded(playerKey);
        giveRewards(player, matchedRule);
    }

    private static RewardRule findMatchingRule(Player player) {
        String lowerName = player.getGameProfile().getName().toLowerCase(Locale.ROOT);
        UUID uuid = player.getUUID();
        for (RewardRule rule : SPECIAL_REWARDS) {
            if (rule.matches(lowerName, uuid)) {
                return rule;
            }
        }
        return null;
    }

    private static void giveRewards(Player player, RewardRule rule) {
        for (ItemStack stack : rule.createRewardStacks()) {
            giveItem(player, stack);
        }
        player.displayClientMessage(Component.translatable(rule.messageKey()), false);
    }

    private static void giveItem(Player player, ItemStack stack) {
        if (player.addItem(stack)) {
            return;
        }

        ItemEntity itemEntity = player.drop(stack, false);
        if (itemEntity != null) {
            itemEntity.setNoPickUpDelay();
            itemEntity.setTarget(player.getUUID());
        }
    }

    private static RewardStack stack(Supplier<? extends Item> item, int count) {
        return new RewardStack(item, count);
    }

    private record RewardRule(Set<String> names, Set<UUID> uuids, List<RewardStack> rewards, String messageKey) {
        private static Builder builder() {
            return new Builder();
        }

        private boolean matches(String lowerName, UUID uuid) {
            return names.contains(lowerName) || uuids.contains(uuid);
        }

        private List<ItemStack> createRewardStacks() {
            return rewards.stream().map(RewardStack::create).toList();
        }

        private static final class Builder {
            private Set<String> names = Set.of();
            private Set<UUID> uuids = Set.of();
            private List<RewardStack> rewards = List.of();
            private String messageKey = "tsuki.chat.easter_egg.rewarded";

            private Builder names(String... values) {
                this.names = Arrays.stream(values)
                        .map(name -> name.toLowerCase(Locale.ROOT))
                        .collect(Collectors.toUnmodifiableSet());
                return this;
            }

            private Builder uuids(String... values) {
                this.uuids = Arrays.stream(values)
                        .map(UUID::fromString)
                        .collect(Collectors.toUnmodifiableSet());
                return this;
            }

            private Builder rewards(RewardStack... values) {
                this.rewards = List.of(values);
                return this;
            }

            private Builder messageKey(String key) {
                this.messageKey = key;
                return this;
            }

            private RewardRule build() {
                return new RewardRule(names, uuids, rewards, messageKey);
            }
        }
    }

    private record RewardStack(Supplier<? extends Item> item, int count) {
        private ItemStack create() {
            return new ItemStack(item.get(), count);
        }
    }

    private static final class RewardedPlayersSavedData extends SavedData {
        private final Set<String> rewardedPlayers = new java.util.HashSet<>();

        private static final Factory<RewardedPlayersSavedData> FACTORY = new Factory<>(
                RewardedPlayersSavedData::new,
                RewardedPlayersSavedData::load);

        private static RewardedPlayersSavedData get(ServerLevel level) {
            return level.getServer()
                    .overworld()
                    .getDataStorage()
                    .computeIfAbsent(FACTORY, DATA_NAME);
        }

        private static RewardedPlayersSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
            RewardedPlayersSavedData data = new RewardedPlayersSavedData();
            ListTag players = tag.getList(REWARDED_PLAYERS_KEY, Tag.TAG_STRING);
            for (Tag entry : players) {
                data.rewardedPlayers.add(entry.getAsString());
            }
            return data;
        }

        private boolean hasReceivedReward(String playerKey) {
            return rewardedPlayers.contains(playerKey);
        }

        private void markRewarded(String playerKey) {
            if (rewardedPlayers.add(playerKey)) {
                setDirty();
            }
        }

        @Override
        public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
            ListTag players = new ListTag();
            for (String player : rewardedPlayers) {
                players.add(StringTag.valueOf(player));
            }
            tag.put(REWARDED_PLAYERS_KEY, players);
            return tag;
        }
    }
}
