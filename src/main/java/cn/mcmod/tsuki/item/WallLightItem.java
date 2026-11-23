package cn.mcmod.tsuki.item;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.machine.WallLightBlock;
import cn.mcmod.tsuki.client.render.item.WallLightItemRenderer;
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

public class WallLightItem extends BlockItem implements GeoItem {
    private static final ResourceLocation STONE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/block/wall_light_stone.png");
    private static final ResourceLocation WOOD_TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/block/wall_light_wood.png");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ResourceLocation texture;

    public WallLightItem(Block block, Properties properties) {
        super(block, properties);
        if (!(block instanceof WallLightBlock wallLight))
            throw new IllegalArgumentException("WallLightItem requires WallLightBlock");
        this.texture = wallLight.isWooden() ? WOOD_TEXTURE : STONE_TEXTURE;
        GeoItem.registerSyncedAnimatable(this);
    }

    public ResourceLocation getTextureResource() {
        return texture;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WallLightItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (renderer == null)
                    renderer = new WallLightItemRenderer();
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
