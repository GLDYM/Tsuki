package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.item.enums.TsukiWineBottleSet;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;

public class WineBottleFluidHandlerItem implements IFluidHandlerItem {
    private static final int MB_PER_UNIT = 20;
    private ItemStack container;

    public WineBottleFluidHandlerItem(ItemStack container) {
        this.container = container;
    }

    @Override
    public ItemStack getContainer() {
        return container;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        if (tank != 0 || !(container.getItem() instanceof WineBottleItem wineBottleItem)) {
            return FluidStack.EMPTY;
        }
        int units = container.getMaxDamage() - container.getDamageValue();
        if (units <= 0) {
            return FluidStack.EMPTY;
        }
        return new FluidStack(wineBottleItem.getFluid(), units * MB_PER_UNIT);
    }

    @Override
    public int getTankCapacity(int tank) {
        if (tank != 0) {
            return 0;
        }
        if (container.is(DrinkRegistry.WINE_BOTTLE.get())) {
            return WineBottleItem.MAX_SIP_DAMAGE * MB_PER_UNIT;
        }
        return container.getMaxDamage() * MB_PER_UNIT;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        if (tank != 0 || stack.isEmpty()) {
            return false;
        }
        if (container.is(DrinkRegistry.WINE_BOTTLE.get())) {
            return TsukiWineBottleSet.fromFluid(stack.getFluid()) != null;
        }
        if (container.getItem() instanceof WineBottleItem wineBottleItem) {
            return wineBottleItem.getFluid().isSame(stack.getFluid());
        }
        return false;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || !container.is(DrinkRegistry.WINE_BOTTLE.get())) {
            return 0;
        }
        TsukiWineBottleSet set = TsukiWineBottleSet.fromFluid(resource.getFluid());
        if (set == null) {
            return 0;
        }

        int maxFill = WineBottleItem.MAX_SIP_DAMAGE * MB_PER_UNIT;
        int fillAmount = Math.min(resource.getAmount(), maxFill);
        fillAmount -= fillAmount % MB_PER_UNIT;
        if (fillAmount <= 0) {
            return 0;
        }

        if (action.execute()) {
            container = new ItemStack(DrinkRegistry.WINE_BOTTLES.get(set).get());
            int units = fillAmount / MB_PER_UNIT;
            container.setDamageValue(container.getMaxDamage() - units);
        }
        return fillAmount;
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || !(container.getItem() instanceof WineBottleItem wineBottleItem)) {
            return FluidStack.EMPTY;
        }
        if (!wineBottleItem.getFluid().isSame(resource.getFluid())) {
            return FluidStack.EMPTY;
        }
        return drain(resource.getAmount(), action);
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (!(container.getItem() instanceof WineBottleItem wineBottleItem) || maxDrain <= 0) {
            return FluidStack.EMPTY;
        }
        int available = (container.getMaxDamage() - container.getDamageValue()) * MB_PER_UNIT;
        int drainAmount = Math.min(maxDrain, available);
        drainAmount -= drainAmount % MB_PER_UNIT;
        if (drainAmount <= 0) {
            return FluidStack.EMPTY;
        }

        FluidStack drained = new FluidStack(wineBottleItem.getFluid(), drainAmount);
        if (action.execute()) {
            int units = drainAmount / MB_PER_UNIT;
            int nextDamage = container.getDamageValue() + units;
            if (nextDamage >= container.getMaxDamage()) {
                container = new ItemStack(DrinkRegistry.WINE_BOTTLE.get());
            } else {
                container.setDamageValue(nextDamage);
            }
        }
        return drained;
    }
}
