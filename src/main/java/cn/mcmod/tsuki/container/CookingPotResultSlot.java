package cn.mcmod.tsuki.container;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

@ParametersAreNonnullByDefault
public class CookingPotResultSlot extends SlotItemHandler {
    public final CookingPotBlockEntity blockEntity;
    private final Player player;
    private int removeCount;

    public CookingPotResultSlot(Player player, CookingPotBlockEntity block, IItemHandler inventoryIn, int index,
            int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.blockEntity = block;
        this.player = player;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    @Nonnull
    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    @Override
    public void onTake(Player thePlayer, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(thePlayer, stack);
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
        stack.onCraftedBy(this.player.level(), this.player, this.removeCount);

        if (!this.player.level().isClientSide()) {
            blockEntity.clearUsedRecipes(this.player);
        }

        this.removeCount = 0;
    }
}

