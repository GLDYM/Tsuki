package cn.mcmod.tsuki.client;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.BlockRegistry;
import cn.mcmod.tsuki.block.entity.BlockEntityRegistry;
import cn.mcmod.tsuki.client.particle.FallenLeafParticle;
import cn.mcmod.tsuki.client.particle.ParticleRegistry;
import cn.mcmod.tsuki.client.particle.SyrupDropParticle;
import cn.mcmod.tsuki.client.render.ChoppingBoardRender;
import cn.mcmod.tsuki.client.render.ObonRender;
import cn.mcmod.tsuki.client.render.StoneMortarRenderer;
import cn.mcmod.tsuki.fluid.FluidRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.BushBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
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
            
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NABE_ODEN.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.NABE_SUKIYAKI.get(), RenderType.cutoutMipped());
            
            BlockRegistry.BLOCKS.getEntries().forEach(block -> {
                if (block.get() instanceof BushBlock) {
                    ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
                }
            });
            FluidRegistry.FLUIDS.getEntries().forEach(fluid -> {
                ItemBlockRenderTypes.setRenderLayer(fluid.get(), RenderType.translucent());
            });
            
            BlockEntityRenderers.register(BlockEntityRegistry.STONE_MORTAR.get(), StoneMortarRenderer::new);
            BlockEntityRenderers.register(BlockEntityRegistry.CHOPPING_BOARD.get(), ChoppingBoardRender::new);
            BlockEntityRenderers.register(BlockEntityRegistry.OBON.get(), ObonRender::new);
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
    }

}


