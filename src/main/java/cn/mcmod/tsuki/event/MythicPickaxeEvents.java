package cn.mcmod.tsuki.event;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.init.EnchantmentRegistry;
import cn.mcmod.tsuki.item.tool.MythicPickaxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Tsuki.MODID)
public class MythicPickaxeEvents {
    private static final TagKey<Block> C_ORES_TAG = TagKey.create(Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath("c", "ores"));
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

    private MythicPickaxeEvents() {
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

        int freshFoodLevel = EnchantmentRegistry.getLevel(player.registryAccess(), EnchantmentRegistry.FRESH_FOOD,
                mainHand);
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
        int gainedExp = random.nextInt(3) + 1;

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

    private static int getOreExtraExperience(Block block, Level level, BlockPos pos, ServerPlayer player,
            ItemStack stack) {
        if (block instanceof DropExperienceBlock dropExperienceBlock) {
            int extra = dropExperienceBlock.getExpDrop(level.getBlockState(pos), level, pos, null, player, stack);
            return Math.max(0, extra);
        }
        return 0;
    }
}
