package cn.mcmod.mmlib.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class I18nUtil {
    public static MutableComponent chanceComponent(float chance){
        return Component.translatable("mm_lib.jei.chance", chance < 0.01 ? "<1" : (int) (chance * 100))
                .withStyle(ChatFormatting.GOLD);
    }

}
