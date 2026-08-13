package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.client.particle.FallenLeafParticle;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import cn.mcmod.tsuki.client.particle.CookingParticle;
import cn.mcmod.tsuki.client.particle.SyrupDropParticle;
import cn.mcmod.tsuki.client.particle.SprinklerWaterParticle;
import cn.mcmod.tsuki.client.gui.CookingPotTooltip;
import cn.mcmod.tsuki.client.render.ChoppingBoardRender;
import cn.mcmod.tsuki.client.render.CookingPotRender;
import cn.mcmod.tsuki.client.render.DrinkDisplayRender;
import cn.mcmod.tsuki.client.render.ObonRender;
import cn.mcmod.tsuki.client.render.SeatEntityRenderer;
import cn.mcmod.tsuki.client.render.ShojiRenderer;
import cn.mcmod.tsuki.client.render.StoneMortarRenderer;
import cn.mcmod.tsuki.client.render.SunflowerCropRenderer;
import cn.mcmod.tsuki.client.render.entity.SamuraiIllagerRenderer;
import cn.mcmod.tsuki.init.EntityTypeRegistry;
import cn.mcmod.tsuki.init.block.BlockEntityRegistry;
import cn.mcmod.tsuki.init.block.BlockRegistry;
import cn.mcmod.tsuki.init.fluid.FluidRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.world.level.block.BushBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Tsuki.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void clientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SAKURA_SAPLING.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.RICE_CROP_ROOT.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BAMBOO_PLANT.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BAMBOOSHOOT.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COOKING_POT.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.KITUNEBI.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BAMBOO_DOOR.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHOJI.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHOJI_1.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHOJI_2.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHOJI_3.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHOJI_4.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SHOJI_5.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NOREN_WHITE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NOREN_BLUE.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NOREN_PINK.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.STONE_LANTERN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COBBLESTONE_LANTERN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.MOSSY_STONE_LANTERN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.RED_LANTERN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WHITE_LANTERN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BAMBOO_LANTERN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WINDBELL.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FUTON.get(), RenderType.cutoutMipped());

            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NABE_ODEN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NABE_SUKIYAKI.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SUNFLOWER_CROP.get(), RenderType.cutoutMipped());

            BlockRegistry.BLOCKS.getEntries().forEach(block -> {
                if (block.get() instanceof BushBlock) {
                    ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
                }
            });

            // Grape vine/leaves use thin crossed quads with transparency.
            // Force plain cutout to avoid solid/white alpha artifacts.
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.GRAPE_VINE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.GRAPE_LEAVES.get(), RenderType.cutout());

            FluidRegistry.FLUIDS.getEntries().forEach(fluid -> {
                ItemBlockRenderTypes.setRenderLayer(fluid.get(), RenderType.translucent());
            });

            BlockEntityRenderers.register(BlockEntityRegistry.STONE_MORTAR.get(), StoneMortarRenderer::new);
            BlockEntityRenderers.register(BlockEntityRegistry.CHOPPING_BOARD.get(), ChoppingBoardRender::new);
            BlockEntityRenderers.register(BlockEntityRegistry.COOKING_POT.get(), CookingPotRender::new);
            BlockEntityRenderers.register(BlockEntityRegistry.OBON.get(), ObonRender::new);
            BlockEntityRenderers.register(BlockEntityRegistry.DRINK_DISPLAY.get(), DrinkDisplayRender::new);
            BlockEntityRenderers.register(BlockEntityRegistry.SHOJI.get(), ShojiRenderer::new);
            BlockEntityRenderers.register(BlockEntityRegistry.SUNFLOWER_CROP.get(), SunflowerCropRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.SEAT.get(), SeatEntityRenderer::new);
            EntityRenderers.register(EntityTypeRegistry.KAKEZIKU.get(), context -> new PaintingRenderer(context));
            EntityRenderers.register(EntityTypeRegistry.SAMURAI_ILLAGER.get(), SamuraiIllagerRenderer::new);
        });
    }

    @SubscribeEvent
    public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.SAKURA_LEAF.get(),
                FallenLeafParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.RED_MAPLE_LEAF.get(),
                FallenLeafParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.YELLOW_MAPLE_LEAF.get(),
                FallenLeafParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.GREEN_MAPLE_LEAF.get(),
                FallenLeafParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.ORANGE_MAPLE_LEAF.get(),
                FallenLeafParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.SYRUP_DROP.get(),
                SyrupDropParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.SPRINKLER_WATER.get(),
                SprinklerWaterParticle.Factory::new);
        event.registerSpriteSet(ParticleRegistry.COOKING.get(),
                CookingParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerCustomTooltipRenderers(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CookingPotTooltip.CookingPotTooltipComponent.class, CookingPotTooltip::new);
    }

}
