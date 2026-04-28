package cn.mcmod.tsuki.compat.jade;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.entity.CookingPotBlockEntity;
import cn.mcmod.tsuki.block.entity.DistillerBlockEntity;
import cn.mcmod.tsuki.block.entity.FermenterBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.JadeIds;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.api.ui.ScreenDirection;
import snownee.jade.api.view.FluidView;
import snownee.jade.impl.ui.ProgressElement;
import snownee.jade.impl.ui.SimpleProgressStyle;

import java.util.ArrayList;
import java.util.List;

public class TsukiMachineComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    private static final IElementHelper ELEMENT = IElementHelper.get();
    private static final String DATA_RECIPE_TIME = "TsukiRecipeTime";
    private static final String DATA_RECIPE_TIME_TOTAL = "TsukiRecipeTimeTotal";

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig pluginConfig) {
        // Keep block name and mod name.
        tooltip.remove(JadeIds.UNIVERSAL_ITEM_STORAGE);
        tooltip.remove(JadeIds.UNIVERSAL_FLUID_STORAGE);
        tooltip.remove(JadeIds.UNIVERSAL_PROGRESS);
        tooltip.remove(JadeIds.UNIVERSAL_ENERGY_STORAGE);

        if (accessor.getBlockEntity() instanceof DistillerBlockEntity distiller) {
            int[] progress = readProgress(accessor, distiller.getRecipeTime(), distiller.getRecipeTimeTotal());
            addMachineRows(tooltip, distiller.getInventory(), distiller.getInputFluidTank(), distiller.getOutputFluidTank(),
                    progress[0], progress[1]);
            return;
        }

        if (accessor.getBlockEntity() instanceof FermenterBlockEntity fermenter) {
            int[] progress = readProgress(accessor, fermenter.getRecipeTime(), fermenter.getRecipeTimeTotal());
            addMachineRows(tooltip, fermenter.getInventory(), fermenter.getInputFluidTank(), fermenter.getOutputFluidTank(),
                    progress[0], progress[1]);
            return;
        }

        if (accessor.getBlockEntity() instanceof CookingPotBlockEntity cookingPot) {
            int[] progress = readProgress(accessor, cookingPot.getRecipeTime(), cookingPot.getRecipeTimeTotal());
            addCookingPotRows(tooltip, cookingPot, progress[0], progress[1]);
        }
    }

    private static void addMachineRows(ITooltip tooltip, ItemStackHandler inventory, FluidTank inputTank, FluidTank outputTank,
            int recipeTime, int recipeTimeTotal) {
        addItems(tooltip, List.of(
                inventory.getStackInSlot(0),
                inventory.getStackInSlot(1),
                inventory.getStackInSlot(2)));
        addItems(tooltip, List.of(
                inventory.getStackInSlot(3),
                inventory.getStackInSlot(4),
                inventory.getStackInSlot(5)));
        addFluidTank(tooltip, inputTank);
        addFluidTank(tooltip, outputTank);
        addProgressBar(tooltip, recipeTime, recipeTimeTotal);
    }

    private static void addCookingPotRows(ITooltip tooltip, CookingPotBlockEntity blockEntity, int recipeTime, int recipeTimeTotal) {
        ItemStackHandler inventory = blockEntity.getInventory();
        List<ItemStack> items = new ArrayList<>();
        for (int slot = 0; slot < 9; slot++) {
            ItemStack stack = inventory.getStackInSlot(slot);
            if (stack != null && !stack.isEmpty() && !stack.is(Items.AIR)) {
                items.add(stack);
            }
        }

        ItemStack display = inventory.getStackInSlot(CookingPotBlockEntity.SLOT_MEAL_DISPLAY);
        ItemStack output = inventory.getStackInSlot(CookingPotBlockEntity.SLOT_OUTPUT);
        ItemStack container = inventory.getStackInSlot(CookingPotBlockEntity.SLOT_CONTAINER_INPUT);
        items.add(display);
        items.add(container);
        items.add(output);

        if (items.size() > 6) {
            addItems(tooltip, items.subList(0, 6));
            addItems(tooltip, items.subList(6, items.size()));
        } else {
            addItems(tooltip, items);
        }


        if (!display.isEmpty() && !blockEntity.getCurrentMealContainer().isEmpty()) {
            ItemStack containerStack = blockEntity.getCurrentMealContainer();
            addItemWithLabel(tooltip, containerStack, Component.translatable("tsuki.jade.cooking_pot.container"));
        }
        // addItems(tooltip, List.of(display, container, output));

        addFluidTank(tooltip, blockEntity.getFluidTank());
        addProgressBar(tooltip, recipeTime, recipeTimeTotal);
    }

    private static void addItems(ITooltip tooltip, List<ItemStack> stacks) {
        addItems(tooltip, stacks, false);
    }

    private static void addItems(ITooltip tooltip, List<ItemStack> stacks, boolean showEmpty) {
        if (stacks == null || stacks.isEmpty()) {
            return;
        }

        boolean allEmptyOrAir = true;

        List<IElement> row = new ArrayList<>();
        for (ItemStack stack : stacks) {
            if (stack != null && (showEmpty || (!stack.isEmpty() && !stack.is(Items.AIR)))) {
                allEmptyOrAir = false;
                row.add(ELEMENT.item(stack));
            }
        }
        if (!allEmptyOrAir) {
            tooltip.add(row);
        }
    }

    private static void addItemWithLabel(ITooltip tooltip, ItemStack stack, MutableComponent label) {
        if (stack == null || stack.isEmpty() || stack.is(Items.AIR)) {
            return;
        }
        tooltip.add(List.of(
                ELEMENT.text(label),
                ELEMENT.item(stack).translate(new Vec2(0F, -5.5F))
            )
        );
        tooltip.setLineMargin(-1, ScreenDirection.UP, 4);
        tooltip.setLineMargin(-1, ScreenDirection.DOWN, -5);
    }

    private static void addFluidTank(ITooltip tooltip, FluidTank tank) {
        List<IElement> row = new ArrayList<>();
        FluidStack fluid = tank.getFluid();
        JadeFluidObject fluidObject = fluid.isEmpty()
                ? JadeFluidObject.empty()
                : JadeFluidObject.of(fluid.getFluid(), fluid.getAmount());
        FluidView fluidView = FluidView.readDefault(FluidView.writeDefault(fluidObject, tank.getCapacity()));
        Component amountText = Component.translatable("jade.fluid.with_capacity", fluidView.current, fluidView.max).withStyle(ChatFormatting.WHITE);
        Component fullText;
        if (fluidView.fluidName == null) {
            fullText = amountText;
        } else if (fluidObject.getType().isSame(Fluids.EMPTY)) {
            fullText = Component.translatable("jade.fluid",
                    Component.translatable("jade.fluid.empty"),
                    fluidView.max
                ).withStyle(ChatFormatting.GRAY);
        } else {
            fullText = Component.translatable("jade.fluid", fluidView.fluidName, amountText).withStyle(ChatFormatting.WHITE);
        }

        BoxStyle boxStyle = BoxStyle.getNestedBox();
        row.add(ELEMENT.progress(
                fluidView.ratio,
                fullText,
                ELEMENT.progressStyle().overlay(fluidView.overlay),
                boxStyle,
                true));
        tooltip.add(row);
    }

    private static void addProgressBar(ITooltip tooltip, int recipeTime, int recipeTimeTotal) {
        if (recipeTime <= 0 || recipeTimeTotal <= 0) {
            return;
        }
        float progress = 0.0F;
        progress = Math.min(1.0F, recipeTime / (float) recipeTimeTotal);
        SimpleProgressStyle progressStyle = new SimpleProgressStyle();
        progressStyle.color = 0xFF0E5487;
        progressStyle.color2 = 0xFF0E5487;
        progressStyle.textColor = 0xFFFFFFFF;
        tooltip.add(new ProgressElement(
                progress,
                Component.literal(String.format("%3.0f%%", progress * 100.0F)).withStyle(ChatFormatting.WHITE),
                progressStyle,
                BoxStyle.getNestedBox(),
                true));
    }

    private static int[] readProgress(BlockAccessor accessor, int fallbackTime, int fallbackTotal) {
        CompoundTag data = accessor.getServerData();
        if (data.contains(DATA_RECIPE_TIME) && data.contains(DATA_RECIPE_TIME_TOTAL)) {
            return new int[] { data.getInt(DATA_RECIPE_TIME), data.getInt(DATA_RECIPE_TIME_TOTAL) };
        }
        return new int[] { fallbackTime, fallbackTotal };
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        if (accessor.getBlockEntity() instanceof DistillerBlockEntity distiller) {
            data.putInt(DATA_RECIPE_TIME, distiller.getRecipeTime());
            data.putInt(DATA_RECIPE_TIME_TOTAL, distiller.getRecipeTimeTotal());
            return;
        }
        if (accessor.getBlockEntity() instanceof FermenterBlockEntity fermenter) {
            data.putInt(DATA_RECIPE_TIME, fermenter.getRecipeTime());
            data.putInt(DATA_RECIPE_TIME_TOTAL, fermenter.getRecipeTimeTotal());
            return;
        }
        if (accessor.getBlockEntity() instanceof CookingPotBlockEntity cookingPot) {
            data.putInt(DATA_RECIPE_TIME, cookingPot.getRecipeTime());
            data.putInt(DATA_RECIPE_TIME_TOTAL, cookingPot.getRecipeTimeTotal());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "machine_info");
    }
}
