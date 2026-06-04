package cn.mcmod.tsuki.item.magatama;

import cn.mcmod.tsuki.config.TsukiCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;

public class MagatamaBlueItem extends Item {
    private static final String KEY_WEATHER_MODE = "WeatherMode";
    private static final int WEATHER_DURATION_TICKS = 12000;
    private static final int COOLDOWN_TICKS = 1200;

    public MagatamaBlueItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!(entity instanceof ServerPlayer player) || !(level instanceof ServerLevel serverLevel)) {
            return stack;
        }
        if (player.getCooldowns().isOnCooldown(this)) {
            return stack;
        }
        float healthCost = getConfiguredHealthCost();
        if (player.getHealth() <= healthCost) {
            return stack;
        }

        switch (getWeatherMode(stack)) {
            case CLEAR -> serverLevel.setWeatherParameters(WEATHER_DURATION_TICKS, 0, false, false);
            case RAIN -> serverLevel.setWeatherParameters(0, WEATHER_DURATION_TICKS, true, false);
            case THUNDER -> serverLevel.setWeatherParameters(0, WEATHER_DURATION_TICKS, true, true);
        }

        player.hurt(player.damageSources().magic(), healthCost);
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        return stack;
    }

    private static float getConfiguredHealthCost() {
        return (float) Math.max(0.0D, TsukiCommonConfig.MAGATAMA_BLUE_HEALTH_COST.get());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.tsuki.magatama_blue.tooltip").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tsuki.tooltip.magatama_blue.key",
                Component.keybind("key.tsuki.magatama_mode_toggle").withStyle(ChatFormatting.GRAY))
                .withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable("tsuki.tooltip.magatama_blue.mode",
                Component.translatable(getWeatherMode(stack).getTranslationKey()).withStyle(ChatFormatting.AQUA))
                .withStyle(ChatFormatting.GRAY));
    }

    public static WeatherMode getWeatherMode(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return WeatherMode.fromSerializedName(tag.getString(KEY_WEATHER_MODE));
    }

    public static WeatherMode toggleWeatherMode(ItemStack stack) {
        WeatherMode nextMode = getWeatherMode(stack).next();
        setWeatherMode(stack, nextMode);
        return nextMode;
    }

    public static void setWeatherMode(ItemStack stack, WeatherMode mode) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (mode == WeatherMode.CLEAR) {
            tag.remove(KEY_WEATHER_MODE);
        } else {
            tag.putString(KEY_WEATHER_MODE, mode.getSerializedName());
        }
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public enum WeatherMode {
        CLEAR("clear", "tsuki.tooltip.magatama_blue.mode.clear"),
        RAIN("rain", "tsuki.tooltip.magatama_blue.mode.rain"),
        THUNDER("thunder", "tsuki.tooltip.magatama_blue.mode.thunder");

        private final String serializedName;
        private final String translationKey;

        WeatherMode(String serializedName, String translationKey) {
            this.serializedName = serializedName;
            this.translationKey = translationKey;
        }

        public String getSerializedName() {
            return serializedName;
        }

        public String getTranslationKey() {
            return translationKey;
        }

        public WeatherMode next() {
            return switch (this) {
                case CLEAR -> RAIN;
                case RAIN -> THUNDER;
                case THUNDER -> CLEAR;
            };
        }

        public static WeatherMode fromSerializedName(String name) {
            for (WeatherMode mode : values()) {
                if (mode.serializedName.equals(name)) {
                    return mode;
                }
            }
            return CLEAR;
        }
    }
}
