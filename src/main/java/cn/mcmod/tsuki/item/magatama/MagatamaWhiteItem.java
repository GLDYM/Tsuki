package cn.mcmod.tsuki.item.magatama;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;

import java.util.List;

public class MagatamaWhiteItem extends Item {
    private static final String KEY_FLIGHT_MODE = "FlightMode";

    public MagatamaWhiteItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip,
            TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.tsuki.magatama_white.tooltip").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tsuki.tooltip.magatama_white.key",
                Component.keybind("key.tsuki.magatama_mode_toggle").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY));
        tooltip.add(Component.translatable("tsuki.tooltip.magatama_white.mode",
                Component.translatable(getFlightMode(stack).getTranslationKey()).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GRAY));
    }

    public static FlightMode getFlightMode(ItemStack stack) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        return FlightMode.fromSerializedName(tag.getString(KEY_FLIGHT_MODE));
    }

    public static FlightMode toggleFlightMode(ItemStack stack) {
        FlightMode nextMode = getFlightMode(stack).next();
        setFlightMode(stack, nextMode);
        return nextMode;
    }

    public static void setFlightMode(ItemStack stack, FlightMode mode) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (mode == FlightMode.CREATIVE) {
            tag.remove(KEY_FLIGHT_MODE);
        } else {
            tag.putString(KEY_FLIGHT_MODE, mode.getSerializedName());
        }
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }

    public enum FlightMode {
        CREATIVE("creative", "tsuki.tooltip.magatama_white.mode.creative"),
        ELYTRA("elytra", "tsuki.tooltip.magatama_white.mode.elytra");

        private final String serializedName;
        private final String translationKey;

        FlightMode(String serializedName, String translationKey) {
            this.serializedName = serializedName;
            this.translationKey = translationKey;
        }

        public String getSerializedName() {
            return serializedName;
        }

        public String getTranslationKey() {
            return translationKey;
        }

        public FlightMode next() {
            return this == CREATIVE ? ELYTRA : CREATIVE;
        }

        public static FlightMode fromSerializedName(String name) {
            for (FlightMode mode : values()) {
                if (mode.serializedName.equals(name)) {
                    return mode;
                }
            }
            return CREATIVE;
        }
    }
}
