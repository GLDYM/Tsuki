package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.machine.SprinklerBlock;
import cn.mcmod.tsuki.client.render.item.SprinklerItemRenderer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SprinklerItem extends BlockItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ResourceLocation model;

    public SprinklerItem(Block block, Properties properties) {
        super(block, properties);
        String material = ((SprinklerBlock) block).material().getSerializedName();
        model = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID, "geo/block/" + material + "_sprinkler.geo.json");
        GeoItem.registerSyncedAnimatable(this);
    }

    public ResourceLocation getModelResource() { return model; }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private SprinklerItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (renderer == null) renderer = new SprinklerItemRenderer();
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
}
