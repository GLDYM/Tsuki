package cn.mcmod.tsuki.item.tool;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.config.TsukiCommonConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Type;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class MythicPickaxeItem extends PickaxeItem {
    private static final String KEY_MINING_EXPERIENCE = "mining_experience";
    private static final String KEY_MINING_LEVEL = "mining_level";
    private static final String ENCHANTMENT_TABLE_RESOURCE = "/data/tsuki/loot_table/mythic_pickaxe.json";
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocationDeserializer())
            .create();

    private static MythicEnchantmentTable CACHED_TABLE;
    private static boolean TABLE_LOADED;

    public MythicPickaxeItem(Tier tier, Item.Properties properties) {
        super(tier, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!level.isClientSide) {
            ensureInitialized(stack, level.getRandom());
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);
        if (!level.isClientSide) {
            initializeFresh(stack, level.getRandom());
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        int level = getMiningLevel(stack);
        int experience = getMiningExperience(stack);
        tooltip.add(Component.translatable("tsuki.tooltip.mythic_pickaxe.mining_level", level).withStyle(ChatFormatting.GOLD));
        tooltip.add(Component.translatable("tsuki.tooltip.mythic_pickaxe.mining_experience", experience, TsukiCommonConfig.MYTHIC_PICKAXE_EXP_NEEDED.get())
                .withStyle(ChatFormatting.AQUA));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    public static int addMiningExperience(ItemStack stack, int addAmount, RandomSource random, RegistryAccess registryAccess,
            ServerPlayer player) {
        if (addAmount <= 0) {
            return 0;
        }
        RandomSource useRandom = random != null ? random : RandomSource.create();
        ensureInitialized(stack, useRandom);

        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        int levelUps = 0;
        int currentExp = Math.max(0, tag.getInt(KEY_MINING_EXPERIENCE));
        int currentLevel = Math.max(1, tag.getInt(KEY_MINING_LEVEL));

        currentExp += addAmount;
        while (currentExp >= TsukiCommonConfig.MYTHIC_PICKAXE_EXP_NEEDED.get()) {
            currentLevel += 1;
            levelUps += 1;
            currentExp = randomRange(useRandom, 1, 30);
            rollLevelUpEnchantments(stack, useRandom, registryAccess, player);
        }

        tag.putInt(KEY_MINING_EXPERIENCE, currentExp);
        tag.putInt(KEY_MINING_LEVEL, currentLevel);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return levelUps;
    }

    public static int getMiningExperience(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return Math.max(0, tag.getInt(KEY_MINING_EXPERIENCE));
    }

    public static int getMiningLevel(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return Math.max(1, tag.contains(KEY_MINING_LEVEL) ? tag.getInt(KEY_MINING_LEVEL) : 1);
    }

    private static void ensureInitialized(ItemStack stack, RandomSource random) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        boolean changed = false;

        if (!tag.contains(KEY_MINING_LEVEL)) {
            tag.putInt(KEY_MINING_LEVEL, 1);
            changed = true;
        }
        if (!tag.contains(KEY_MINING_EXPERIENCE)) {
            RandomSource useRandom = random != null ? random : RandomSource.create();
            tag.putInt(KEY_MINING_EXPERIENCE, randomRange(useRandom, 1, 30));
            changed = true;
        }

        if (changed) {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        }
    }

    private static void initializeFresh(ItemStack stack, RandomSource random) {
        RandomSource useRandom = random != null ? random : RandomSource.create();
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        tag.putInt(KEY_MINING_LEVEL, 1);
        tag.putInt(KEY_MINING_EXPERIENCE, randomRange(useRandom, 1, 30));
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    private static int randomRange(RandomSource random, int min, int maxInclusive) {
        return min + random.nextInt(maxInclusive - min + 1);
    }

    private static void rollLevelUpEnchantments(ItemStack stack, RandomSource random, RegistryAccess registryAccess,
            ServerPlayer player) {
        Registry<Enchantment> enchantmentRegistry = registryAccess.registryOrThrow(Registries.ENCHANTMENT);
        Holder.Reference<Enchantment> fortuneHolder = enchantmentRegistry.getHolderOrThrow(Enchantments.FORTUNE);
        Holder.Reference<Enchantment> silkTouchHolder = enchantmentRegistry.getHolderOrThrow(Enchantments.SILK_TOUCH);
        int fortuneLevel = EnchantmentHelper.getTagEnchantmentLevel(fortuneHolder, stack);
        int silkTouchLevel = EnchantmentHelper.getTagEnchantmentLevel(silkTouchHolder, stack);

        if (fortuneLevel <= 0 && silkTouchLevel <= 0) {
            Holder.Reference<Enchantment> pick = random.nextBoolean() ? fortuneHolder : silkTouchHolder;
            EnchantRollResult result = tryUpgradeEnchantment(stack, pick, 1, pick.value().getMaxLevel());
            notifyEnchantRollResult(player, pick, result);
            return;
        }

        MythicEnchantmentTable table = loadTable();
        if (table == null || table.pool == null || table.pool.isEmpty()) {
            return;
        }

        int rolls = Math.max(0, table.rolls);
        for (int i = 0; i < rolls; i++) {
            WeightedEnchantmentRoll rolled = pickWeightedRoll(table.pool, random);
            if (rolled == null) {
                return;
            }

            Optional<Holder.Reference<Enchantment>> enchantmentHolder = getEnchantmentHolder(rolled.id, enchantmentRegistry);
            if (enchantmentHolder.isEmpty()) {
                table.pool.remove(rolled);
                Tsuki.getLogger().error("MYTHIC_PICKAXE table removed unknown enchantment id: {}", rolled.id);
                continue;
            }

            Holder<Enchantment> holder = enchantmentHolder.get();
            int maxLevel = Math.max(1, rolled.max);
            int addLevel = Math.max(1, rolled.count);
            EnchantRollResult result = tryUpgradeEnchantment(stack, holder, addLevel, maxLevel);
            notifyEnchantRollResult(player, holder, result);
        }
    }

    private static EnchantRollResult tryUpgradeEnchantment(ItemStack stack, Holder<Enchantment> target, int addLevel,
            int maxLevel) {
        final EnchantRollResult[] result = new EnchantRollResult[] {EnchantRollResult.DROP};
        EnchantmentHelper.updateEnchantments(stack, mutable -> {
            int currentLevel = mutable.getLevel(target);
            if (currentLevel <= 0) {
                for (Holder<Enchantment> existing : mutable.keySet()) {
                    if (!Enchantment.areCompatible(existing, target)) {
                        result[0] = EnchantRollResult.DROP;
                        return;
                    }
                }
            }

            int newLevel = currentLevel + addLevel;
            if (newLevel > maxLevel) {
                result[0] = EnchantRollResult.MAX;
                return;
            }

            mutable.set(target, newLevel);
            result[0] = EnchantRollResult.UP;
        });
        return result[0];
    }

    private static void notifyEnchantRollResult(ServerPlayer player, Holder<Enchantment> enchantment, EnchantRollResult result) {
        if (player == null || enchantment == null || result == null) {
            return;
        }

        String key = switch (result) {
            case UP -> "tsuki.chat.mythic_pickaxe.enchant_up";
            case MAX -> "tsuki.chat.mythic_pickaxe.enchant_max";
            case DROP -> "tsuki.chat.mythic_pickaxe.enchant_drop";
        };

        player.sendSystemMessage(Component.translatable(key, enchantment.value().description().copy()));
    }

    private static Optional<Holder.Reference<Enchantment>> getEnchantmentHolder(ResourceLocation id, Registry<Enchantment> registry) {
        if (id == null) {
            return Optional.empty();
        }
        return registry.getHolder(id);
    }

    private static WeightedEnchantmentRoll pickWeightedRoll(List<WeightedEnchantmentRoll> pool, RandomSource random) {
        List<WeightedEnchantmentRoll> valid = new ArrayList<>();
        int totalWeight = 0;
        for (WeightedEnchantmentRoll roll : pool) {
            if (roll == null || roll.weight <= 0) {
                continue;
            }
            valid.add(roll);
            totalWeight += roll.weight;
        }
        if (valid.isEmpty() || totalWeight <= 0) {
            return null;
        }

        int pick = random.nextInt(totalWeight);
        int cursor = 0;
        for (WeightedEnchantmentRoll roll : valid) {
            cursor += roll.weight;
            if (pick < cursor) {
                return roll;
            }
        }
        return valid.get(valid.size() - 1);
    }

    private static MythicEnchantmentTable loadTable() {
        if (TABLE_LOADED) {
            return CACHED_TABLE;
        }
        TABLE_LOADED = true;

        try (InputStream stream = MythicPickaxeItem.class.getResourceAsStream(ENCHANTMENT_TABLE_RESOURCE)) {
            if (stream == null) {
                Tsuki.getLogger().error("Missing MYTHIC_PICKAXE enchantment table: {}", ENCHANTMENT_TABLE_RESOURCE);
                return null;
            }

            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                CACHED_TABLE = GSON.fromJson(reader, MythicEnchantmentTable.class);
            }
            if (CACHED_TABLE == null) {
                Tsuki.getLogger().error("Failed to parse MYTHIC_PICKAXE enchantment table: {}", ENCHANTMENT_TABLE_RESOURCE);
                return null;
            }
            if (CACHED_TABLE.pool == null) {
                CACHED_TABLE.pool = new ArrayList<>();
            }
            return CACHED_TABLE;
        } catch (Exception exception) {
            Tsuki.getLogger().error("Error loading MYTHIC_PICKAXE enchantment table: {}", ENCHANTMENT_TABLE_RESOURCE, exception);
            return null;
        }
    }

    private static class MythicEnchantmentTable {
        @SerializedName("rolls")
        int rolls;
        @SerializedName("pool")
        List<WeightedEnchantmentRoll> pool;
    }

    private static class WeightedEnchantmentRoll {
        @SerializedName("id")
        ResourceLocation id;
        @SerializedName("count")
        int count;
        @SerializedName("max")
        int max;
        @SerializedName("weight")
        int weight;
    }

    private static class ResourceLocationDeserializer implements JsonDeserializer<ResourceLocation> {
        @Override
        public ResourceLocation deserialize(com.google.gson.JsonElement json, Type typeOfT,
                JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonPrimitive()) {
                throw new JsonParseException("Expected ResourceLocation string");
            }
            String raw = json.getAsString();
            ResourceLocation id = ResourceLocation.tryParse(raw);
            if (id == null) {
                throw new JsonParseException("Invalid ResourceLocation: " + raw);
            }
            return id;
        }
    }

    private enum EnchantRollResult {
        UP,
        MAX,
        DROP
    }
}

