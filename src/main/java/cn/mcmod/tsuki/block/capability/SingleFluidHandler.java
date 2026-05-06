package cn.mcmod.tsuki.block.capability;

import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class SingleFluidHandler implements IFluidHandler {
    private final FluidTank tank;

    public SingleFluidHandler(FluidTank tank) {
        this.tank = tank;
    }

    public IFluidHandler forSide(@Nullable Direction side) {
        return new IFluidHandler() {
            @Override
            public int getTanks() {
                return SingleFluidHandler.this.getTanks();
            }

            @Override
            public FluidStack getFluidInTank(int tankIndex) {
                return SingleFluidHandler.this.getFluidInTank(tankIndex);
            }

            @Override
            public int getTankCapacity(int tankIndex) {
                return SingleFluidHandler.this.getTankCapacity(tankIndex);
            }

            @Override
            public boolean isFluidValid(int tankIndex, FluidStack stack) {
                return SingleFluidHandler.this.isFluidValid(tankIndex, stack);
            }

            @Override
            public int fill(FluidStack resource, FluidAction action) {
                return SingleFluidHandler.this.fill(resource, side, action);
            }

            @Override
            public FluidStack drain(FluidStack resource, FluidAction action) {
                return SingleFluidHandler.this.drain(resource, side, action);
            }

            @Override
            public FluidStack drain(int maxDrain, FluidAction action) {
                return SingleFluidHandler.this.drain(maxDrain, side, action);
            }
        };
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return tank == 0 ? this.tank.getFluid() : FluidStack.EMPTY;
    }

    @Override
    public int getTankCapacity(int tank) {
        return tank == 0 ? this.tank.getCapacity() : 0;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return tank == 0 && this.tank.isFluidValid(stack);
    }

    public int fill(FluidStack resource, @Nullable Direction side, FluidAction action) {
        return tank.fill(resource, action);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return fill(resource, null, action);
    }

    public FluidStack drain(FluidStack resource, @Nullable Direction side, FluidAction action) {
        return tank.drain(resource, action);
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return drain(resource, null, action);
    }

    public FluidStack drain(int maxDrain, @Nullable Direction side, FluidAction action) {
        return tank.drain(maxDrain, action);
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return drain(maxDrain, null, action);
    }
}
