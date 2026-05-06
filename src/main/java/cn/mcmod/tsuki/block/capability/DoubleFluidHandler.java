package cn.mcmod.tsuki.block.capability;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class DoubleFluidHandler implements IFluidHandler {
    private final FluidTank inputTank;
    private final FluidTank outputTank;
    private final Predicate<Direction> outputSidePredicate;

    public DoubleFluidHandler(FluidTank inputTank, FluidTank outputTank, Predicate<Direction> outputSidePredicate) {
        this.inputTank = inputTank;
        this.outputTank = outputTank;
        this.outputSidePredicate = outputSidePredicate;
    }

    public IFluidHandler forSide(@Nullable Direction side) {
        return new IFluidHandler() {
            @Override
            public int getTanks() {
                return DoubleFluidHandler.this.getTanks();
            }

            @Override
            public FluidStack getFluidInTank(int tank) {
                return DoubleFluidHandler.this.getFluidInTank(tank);
            }

            @Override
            public int getTankCapacity(int tank) {
                return DoubleFluidHandler.this.getTankCapacity(tank);
            }

            @Override
            public boolean isFluidValid(int tank, FluidStack stack) {
                return DoubleFluidHandler.this.isFluidValid(tank, stack);
            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {
                return DoubleFluidHandler.this.fill(resource, side, action);
            }

            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                return DoubleFluidHandler.this.drain(resource, side, action);
            }

            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return DoubleFluidHandler.this.drain(maxDrain, side, action);
            }
        };
    }

    @Override
    public int getTanks() {
        return 2;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        if (tank == 0) {
            return inputTank.getFluid();
        }
        if (tank == 1) {
            return outputTank.getFluid();
        }
        return FluidStack.EMPTY;
    }

    @Override
    public int getTankCapacity(int tank) {
        if (tank == 0) {
            return inputTank.getCapacity();
        }
        if (tank == 1) {
            return outputTank.getCapacity();
        }
        return 0;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        if (tank == 0) {
            return inputTank.isFluidValid(stack);
        }
        if (tank == 1) {
            return outputTank.isFluidValid(stack);
        }
        return false;
    }

    private boolean isOutputSide(@Nullable Direction side) {
        return side != null && outputSidePredicate.test(side);
    }

    public int fill(FluidStack resource, @Nullable Direction side, FluidAction action) {
        return isOutputSide(side) ? 0 : inputTank.fill(resource, action);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return fill(resource, null, action);
    }

    public FluidStack drain(FluidStack resource, @Nullable Direction side, FluidAction action) {
        return isOutputSide(side) ? outputTank.drain(resource, action) : inputTank.drain(resource, action);
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return drain(resource, null, action);
    }

    public FluidStack drain(int maxDrain, @Nullable Direction side, FluidAction action) {
        return isOutputSide(side) ? outputTank.drain(maxDrain, action) : inputTank.drain(maxDrain, action);
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return drain(maxDrain, null, action);
    }

    public FluidTank getInputTank() {
        return inputTank;
    }

    public FluidTank getOutputTank() {
        return outputTank;
    }
}
