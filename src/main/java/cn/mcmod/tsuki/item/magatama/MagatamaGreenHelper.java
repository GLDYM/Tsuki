package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.config.TsukiCommonConfig;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class MagatamaGreenHelper {
    private static final float MIN_ALLOWED_REMAINING_HEALTH = 0.1F;
    public static final int COOLDOWN_TICKS = 6000;

    private MagatamaGreenHelper() {
    }

    public static boolean use(ServerPlayer player) {
        List<Item> pool = getRewardPool();
        if (pool.isEmpty()) {
            return false;
        }

        float remainingHealth = Math.max(MIN_ALLOWED_REMAINING_HEALTH,
                TsukiCommonConfig.MAGATAMA_GREEN_REMAINING_HEALTH.get().floatValue());
        int drawCount = TsukiCommonConfig.MAGATAMA_GREEN_DRAW_COUNT.get();

        player.getFoodData().setFoodLevel(0);
        player.getFoodData().setSaturation(0.0F);
        if (player.getHealth() > remainingHealth) {
            player.setHealth(remainingHealth);
        }

        for (int i = 0; i < drawCount; i++) {
            Item reward = pool.get(player.getRandom().nextInt(pool.size()));
            player.drop(new ItemStack(reward), false);
        }
        return true;
    }

    private static List<Item> getRewardPool() {
        Set<Item> uniqueItems = new LinkedHashSet<>();
        for (String tagId : TsukiCommonConfig.MAGATAMA_GREEN_REWARD_TAGS.get()) {
            TagKey<Item> tag = parseItemTag(tagId);
            if (tag != null) {
                addTagItems(uniqueItems, tag);
            }
        }
        return new ArrayList<>(uniqueItems);
    }

    private static TagKey<Item> parseItemTag(String tagId) {
        ResourceLocation location = ResourceLocation.tryParse(tagId);
        return location == null ? null : TagKey.create(BuiltInRegistries.ITEM.key(), location);
    }

    private static void addTagItems(Set<Item> items, TagKey<Item> tag) {
        BuiltInRegistries.ITEM.getTag(tag)
                .ifPresent(holders -> holders.stream()
                        .map(Holder::value)
                        .forEach(items::add));
    }
}
