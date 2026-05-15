package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.crop.SunflowerCropBlock;
import cn.mcmod.tsuki.block.entity.SunflowerCropBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class SunflowerCropGeoModel extends GeoModel<SunflowerCropBlockEntity> {
    private static final ResourceLocation EMPTY_MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/sunflower_empty.geo.json");
    private static final ResourceLocation STAGE0_MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/sunflower_stage0.geo.json");
    private static final ResourceLocation STAGE1_MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/sunflower_stage1.geo.json");
    private static final ResourceLocation STAGE2_MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/sunflower_stage2.geo.json");
    private static final ResourceLocation STAGE3_LOW_MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/sunflower_stage3_low.geo.json");
    private static final ResourceLocation STAGE3_HIGH_MODEL = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "geo/block/sunflower_stage3_high.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "textures/block/sunflower.png");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
            "animations/block/sunflower_crop.animation.json");

    @Override
    public ResourceLocation getModelResource(SunflowerCropBlockEntity animatable) {
        BlockState state = animatable.getBlockState();

        if (!(state.getBlock() instanceof SunflowerCropBlock)) {
            return EMPTY_MODEL;
        }

        SunflowerCropBlock.Part part = state.getValue(SunflowerCropBlock.PART);
        int age = state.getValue(SunflowerCropBlock.AGE);

        return switch (part) {
            case LOWER -> switch (age) {
                case 0 -> STAGE0_MODEL;
                case 1 -> STAGE1_MODEL;
                case 2 -> STAGE2_MODEL;
                default -> EMPTY_MODEL;
            };
            case MIDDLE -> age == 2 ? STAGE2_MODEL : EMPTY_MODEL;
            case UPPER -> age <= 2 ? EMPTY_MODEL : age <= 4 ? STAGE3_LOW_MODEL : STAGE3_HIGH_MODEL;
        };
    }

    @Override
    public ResourceLocation getTextureResource(SunflowerCropBlockEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(SunflowerCropBlockEntity animatable) {
        return ANIMATION;
    }

    @Override
    public void setCustomAnimations(SunflowerCropBlockEntity animatable, long instanceId,
            AnimationState<SunflowerCropBlockEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        GeoBone flower = getAnimationProcessor().getBone("flower");
        if (flower == null) {
            return;
        }

        flower.setRotY(getSunTrackingYaw(animatable));
    }

    private static float getSunTrackingYaw(SunflowerCropBlockEntity blockEntity) {
        if (blockEntity.getLevel() == null) {
            return 0.0F;
        }

        long time = blockEntity.getLevel().getDayTime() % 24000L;
        float progress = Mth.positiveModulo((time - 6000L) / 24000.0F, 1.0F);
        float degrees = -progress * 360.0F;

        return degrees * Mth.DEG_TO_RAD;
    }
}
