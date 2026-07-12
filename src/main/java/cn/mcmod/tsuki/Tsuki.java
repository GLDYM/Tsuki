package cn.mcmod.tsuki;

import cn.mcmod.tsuki.config.TsukiCommonConfig;
import cn.mcmod.tsuki.config.TsukiClientConfig;

import cn.mcmod.tsuki.client.TsukiClient;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;

import cn.mcmod.tsuki.init.CreativeModeTabRegistry;
import cn.mcmod.tsuki.init.EntityTypeRegistry;
import cn.mcmod.tsuki.init.ItemDataComponentRegistry;
import cn.mcmod.tsuki.init.LootModifierRegistry;
import cn.mcmod.tsuki.init.MenuTypeRegistry;
import cn.mcmod.tsuki.init.RecipeTypeRegistry;
import cn.mcmod.tsuki.init.SoundEventRegistry;
import cn.mcmod.tsuki.init.TreeDecoratorTypeRegistry;
import cn.mcmod.tsuki.init.VillagerRegistry;
import cn.mcmod.tsuki.init.FeatureTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockEntityCapabilityRegistry;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.block.FluidBlockRegistry;
import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import cn.mcmod.tsuki.init.fluid.FluidTypeRegistry;
import cn.mcmod.tsuki.init.item.DrinkRegistry;
import cn.mcmod.tsuki.init.item.FoodRegistry;
import cn.mcmod.tsuki.init.item.ItemCapabilityRegistry;
import cn.mcmod.tsuki.init.item.ItemRegistry;
import cn.mcmod.tsuki.init.item.ArmorMaterialRegistry;
import cn.mcmod.tsuki.init.item.ArmorToolRegistry;
import cn.mcmod.tsuki.init.item.BlockItemRegistry;
import cn.mcmod.tsuki.init.item.BucketItemRegistry;
import cn.mcmod.tsuki.init.MobEffectRegistry;

import cn.mcmod.tsuki.compat.guideme.TsukiGuideMeCompat;
import cn.mcmod.tsuki.compat.guideme.TsukiGuideCompat;
import cn.mcmod.tsuki.compat.terrablender.TsukiTerraBlenderCompat;
import cn.mcmod.tsuki.network.TsukiNetwork;

import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(Tsuki.MODID)
public class Tsuki {
    public static final String MODID = "tsuki";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties();
    }

    public Tsuki(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(BlockEntityCapabilityRegistry::register);
        modEventBus.addListener(ItemCapabilityRegistry::register);
        modEventBus.addListener(EntityTypeRegistry::registerAttributes);
        modEventBus.addListener(EntityTypeRegistry::registerSpawnPlacements);
        modEventBus.addListener(TsukiNetwork::register);

        BlockRegistry.BLOCKS.register(modEventBus);
        FluidBlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);

        FluidRegistry.FLUIDS.register(modEventBus);
        FluidTypeRegistry.FLUID_TYPES.register(modEventBus);

        ItemRegistry.ITEMS.register(modEventBus);
        BlockItemRegistry.ITEMS.register(modEventBus);
        DrinkRegistry.ITEMS.register(modEventBus);
        BucketItemRegistry.ITEMS.register(modEventBus);
        FoodRegistry.ITEMS.register(modEventBus);
        ArmorToolRegistry.ITEMS.register(modEventBus);
        ArmorMaterialRegistry.ARMOR_MATERIALS.register(modEventBus);

        EntityTypeRegistry.ENTITY_TYPES.register(modEventBus);

        SoundEventRegistry.SOUND_EVENTS.register(modEventBus);
        ParticleRegistry.PARTICLE_TYPES.register(modEventBus);
        MenuTypeRegistry.CONTAINER_TYPES.register(modEventBus);
        MobEffectRegistry.MOB_EFFECTS.register(modEventBus);
        RecipeTypeRegistry.RECIPE_TYPES.register(modEventBus);
        RecipeTypeRegistry.RECIPE_SERIALIZERS.register(modEventBus);
        FeatureTypeRegistry.FEATURES.register(modEventBus);
        TreeDecoratorTypeRegistry.TREE_DECORATOR_TYPES.register(modEventBus);
        VillagerRegistry.POI_TYPES.register(modEventBus);
        VillagerRegistry.PROFESSIONS.register(modEventBus);
        CreativeModeTabRegistry.TABS.register(modEventBus);
        ItemDataComponentRegistry.DATA_COMPONENTS.register(modEventBus);
        LootModifierRegistry.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        if (ModList.get().isLoaded("guideme")) {
            TsukiGuideMeCompat.register();
        } else {
            TsukiGuideCompat.register();
        }

        modContainer.registerConfig(ModConfig.Type.COMMON, TsukiCommonConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, TsukiClientConfig.SPEC);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            TsukiClient.registerConfigScreen(modContainer);
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("terrablender")) {
            event.enqueueWork(TsukiTerraBlenderCompat::register);
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
