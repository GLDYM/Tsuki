package cn.mcmod.tsuki.events;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.enchantment.TsukiEnchantments;
import cn.mcmod.tsuki.item.MythicPickaxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public class TsukiEnchantmentEvents {
    private static final int ANTI_FIRE_INTERVAL_TICKS = 80;
    private static final int ANTI_FIRE_DURATION_TICKS = 340;
    private static final float SMASH_MAX_BREAK_TICKS = 8.0F;
    private static final float OMNITOOL_NON_PICKAXE_SPEED_MULTIPLIER = 9.0F;
    private static final TagKey<Block> C_ORES_TAG = TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("c", "ores"));
    @SuppressWarnings("unchecked")
    private static final TagKey<Block>[] ORE_TAGS = new TagKey[] {
            BlockTags.COAL_ORES,
            BlockTags.IRON_ORES,
            BlockTags.GOLD_ORES,
            BlockTags.DIAMOND_ORES,
            BlockTags.EMERALD_ORES,
            BlockTags.REDSTONE_ORES,
            BlockTags.LAPIS_ORES,
            BlockTags.COPPER_ORES,
            C_ORES_TAG
    };

    private TsukiEnchantmentEvents() {
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (player.tickCount % ANTI_FIRE_INTERVAL_TICKS != 0) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int antiFireLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.ANTI_FIRE, mainHand);
        if (antiFireLevel <= 0) {
            return;
        }

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, ANTI_FIRE_DURATION_TICKS, 0, true, false));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player == null) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int smashLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.SMASH, mainHand);
        int omnitoolLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.OMNITOOL, mainHand);
        if (smashLevel <= 0 && omnitoolLevel <= 0) {
            return;
        }

        float hardness = event.getState().getDestroySpeed(player.level(), event.getPosition().orElse(player.blockPosition()));
        if (hardness <= 0.0F) {
            return;
        }

        float currentSpeed = event.getNewSpeed();
        if (currentSpeed <= 0.0F) {
            return;
        }

        BlockState state = event.getState();
        boolean requiresCorrectTool = state.requiresCorrectToolForDrops();
        boolean originalCorrectTool = player.getMainHandItem().isCorrectToolForDrops(state);
        float adjustedSpeed = currentSpeed;

        boolean mineableWithPickaxe = state.is(BlockTags.MINEABLE_WITH_PICKAXE);
        boolean mineableWithOtherTool = hasNonPickaxeMineableTag(state);

        if (omnitoolLevel > 0 && !mineableWithPickaxe && mineableWithOtherTool) {
            adjustedSpeed *= OMNITOOL_NON_PICKAXE_SPEED_MULTIPLIER;
        }


        boolean effectiveCorrectTool = !requiresCorrectTool || originalCorrectTool || omnitoolLevel > 0;
        float divisor = effectiveCorrectTool ? 30.0F : 100.0F;
        float currentBreakTicks = (hardness * divisor) / adjustedSpeed;
        if (smashLevel > 0 && currentBreakTicks > SMASH_MAX_BREAK_TICKS) {
            float targetSpeed = (hardness * divisor) / SMASH_MAX_BREAK_TICKS;
            event.setNewSpeed(Math.max(adjustedSpeed, targetSpeed));
            return;
        }

        event.setNewSpeed(adjustedSpeed);
    }

    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        int omnitoolLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.OMNITOOL, mainHand);
        if (omnitoolLevel <= 0) {
            return;
        }

        event.setCanHarvest(true);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        if (mainHand.getItem() instanceof MythicPickaxeItem) {
            addMythicPickaxeExperience(player, event, mainHand);
        }

        int freshFoodLevel = TsukiEnchantments.getLevel(player.registryAccess(), TsukiEnchantments.FRESH_FOOD, mainHand);
        if (freshFoodLevel <= 0) {
            return;
        }

        float chance = Math.min(1.0F, freshFoodLevel * 0.10F);
        RandomSource random = player.getRandom();
        if (random.nextFloat() <= chance) {
            player.getFoodData().eat(1, 0.5F);
        }
    }

    private static void addMythicPickaxeExperience(ServerPlayer player, BlockEvent.BreakEvent event, ItemStack stack) {
        RandomSource random = player.getRandom();
        int gainedExp = random.nextInt(30) + 1;

        float hardness = event.getState().getDestroySpeed(player.level(), event.getPos());
        if (hardness > 1.0F) {
            gainedExp = Math.max(1, Math.round(gainedExp * hardness));
        }

        Block block = event.getState().getBlock();
        if (isOreBlock(block)) {
            gainedExp += getOreExtraExperience(block, player.level(), event.getPos(), player, stack);
        }

        MythicPickaxeItem.addMiningExperience(stack, gainedExp, random, player.registryAccess(), player);
    }

    private static boolean isOreBlock(Block block) {
        ResourceKey<Block> key = BuiltInRegistries.BLOCK.getResourceKey(block).orElse(null);
        if (key == null) {
            return false;
        }
        Holder.Reference<Block> holder = BuiltInRegistries.BLOCK.getHolderOrThrow(key);
        for (TagKey<Block> oreTag : ORE_TAGS) {
            if (holder.is(oreTag)) {
                return true;
            }
        }
        return false;
    }

    private static int getOreExtraExperience(Block block, Level level, BlockPos pos, ServerPlayer player, ItemStack stack) {
        if (block instanceof DropExperienceBlock dropExperienceBlock) {
            int extra = dropExperienceBlock.getExpDrop(level.getBlockState(pos), level, pos, null, player, stack);
            return Math.max(0, extra);
        }
        return 0;
    }

    private static boolean hasNonPickaxeMineableTag(BlockState state) {
        return state.getTags().anyMatch(tag -> {
            String path = tag.location().getPath();
            return path.startsWith("mineable/") && !"mineable/pickaxe".equals(path);
        });
    }
}
