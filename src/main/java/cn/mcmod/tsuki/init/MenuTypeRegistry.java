package cn.mcmod.tsuki.init;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.container.CookingPotContainer;
import cn.mcmod.tsuki.container.DistillerContainer;
import cn.mcmod.tsuki.container.FermenterContainer;
import cn.mcmod.tsuki.container.StoneMortarContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MenuTypeRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister
            .create(Registries.MENU, Tsuki.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<StoneMortarContainer>> STONE_MORTAR = CONTAINER_TYPES
            .register("stone_mortar", () -> IMenuTypeExtension.create(StoneMortarContainer::new));

    public static final DeferredHolder<MenuType<?>, MenuType<CookingPotContainer>> COOKING_POT = CONTAINER_TYPES
            .register("cooking_pot", () -> IMenuTypeExtension.create(CookingPotContainer::new));

    public static final DeferredHolder<MenuType<?>, MenuType<FermenterContainer>> FERMENTER = CONTAINER_TYPES
            .register("fermenter", () -> IMenuTypeExtension.create(FermenterContainer::new));

    public static final DeferredHolder<MenuType<?>, MenuType<DistillerContainer>> DISTILLER = CONTAINER_TYPES
            .register("distiller", () -> IMenuTypeExtension.create(DistillerContainer::new));
}
