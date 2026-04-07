package cn.mcmod.mmlib.recipe;

import com.google.gson.annotations.Expose;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public record ChanceResult(@Expose ItemStack stack, @Expose float chance) {
    public static final ChanceResult EMPTY = new ChanceResult(ItemStack.EMPTY, 0);

    public ItemStack getStack() {
        return this.stack;
    }

    public float getChance() {
        return this.chance;
    }

    public ItemStack rollOutput(RandomSource random, int fortuneLevel) {
        if (this.stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int rollCount = this.stack.getCount();
        int successCount = 0;
        float adjustedChance = this.chance + (fortuneLevel * 0.05f);
        float clampedChance = Math.min(1.0f, adjustedChance);

        for (int i = 0; i < rollCount; i++) {
            if (random.nextFloat() < clampedChance) {
                successCount++;
            }
        }

        if (successCount <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack result = this.stack.copy();
        result.setCount(successCount);
        return result;
    }
}
