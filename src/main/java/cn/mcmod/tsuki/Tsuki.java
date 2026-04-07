package cn.mcmod.tsuki;

import cn.mcmod.tsuki.fluid.FluidTypeRegistry;
import cn.mcmod.tsuki.item.CreativeModeTabRegistry;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import cn.mcmod.tsuki.block.BlockItemRegistry;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.entity.BlockEntityRegistry;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import cn.mcmod.tsuki.container.ContainerRegistry;
import cn.mcmod.tsuki.fluid.BucketItemRegistry;
import cn.mcmod.tsuki.fluid.FluidBlockRegistry;
import cn.mcmod.tsuki.fluid.FluidRegistry;
import cn.mcmod.tsuki.item.ComposterRegistry;
import cn.mcmod.tsuki.item.FoodRegistry;
import cn.mcmod.tsuki.item.ItemRegistry;
import cn.mcmod.tsuki.loot_modifier.LootModifiterRegistry;
import cn.mcmod.tsuki.recipes.RecipeTypeRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Tsuki.MODID)
public class Tsuki {
    public static final String MODID = "tsuki";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties();
    }

    public Tsuki() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        BlockRegistry.BLOCKS.register(modEventBus);
        BlockItemRegistry.ITEMS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        FoodRegistry.ITEMS.register(modEventBus);
        FluidRegistry.FLUIDS.register(modEventBus);
        FluidBlockRegistry.BLOCKS.register(modEventBus);
        FluidTypeRegistry.FLUID_TYPES.register(modEventBus);
        BucketItemRegistry.ITEMS.register(modEventBus);
        ParticleRegistry.PARTICLE_TYPES.register(modEventBus);
        ContainerRegistry.CONTAINER_TYPES.register(modEventBus);
        LootModifiterRegistry.GLM.register(modEventBus);
        RecipeTypeRegistry.RECIPE_TYPES.register(modEventBus);
        RecipeTypeRegistry.RECIPE_SERIALIZERS.register(modEventBus);
        CreativeModeTabRegistry.TABS.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TsukiConfig.COMMON_CONFIG);

    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterRegistry.registerCompost();
        });
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
