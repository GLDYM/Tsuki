package cn.mcmod.tsuki.block.entity;

import cn.mcmod.tsuki.item.DrinkRegistry;
import cn.mcmod.tsuki.item.WineBottleFluidHandlerItem;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public final class BlockEntityCapabilityRegistry {
    private BlockEntityCapabilityRegistry() {
    }

    public static void register(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.COOKING_POT.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                BlockEntityRegistry.COOKING_POT.get(),
                (blockEntity, side) -> blockEntity.getFluidHandler(side));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.FERMENTER.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                BlockEntityRegistry.FERMENTER.get(),
                (blockEntity, side) -> blockEntity.getFluidHandler(side));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.DISTILLER.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                BlockEntityRegistry.DISTILLER.get(),
                (blockEntity, side) -> blockEntity.getFluidHandler(side));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.STONE_MORTAR.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.CHOPPING_BOARD.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.OBON.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));

        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntityRegistry.MAPLE_CAULDRON.get(),
                (blockEntity, side) -> blockEntity.getItemHandler(side));
        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                BlockEntityRegistry.MAPLE_CAULDRON.get(),
                (blockEntity, side) -> blockEntity.getFluidHandler(side));

        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> new WineBottleFluidHandlerItem(stack),
                DrinkRegistry.WINE_BOTTLE.get());
        DrinkRegistry.WINE_BOTTLES.values().forEach(item -> event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, context) -> new WineBottleFluidHandlerItem(stack),
                item.get()));
    }
}
