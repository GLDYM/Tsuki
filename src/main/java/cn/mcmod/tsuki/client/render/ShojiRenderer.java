package cn.mcmod.tsuki.client.render;

import cn.mcmod.tsuki.Tsuki;
import cn.mcmod.tsuki.block.ShojiBlock;
import cn.mcmod.tsuki.block.entity.ShojiBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class ShojiRenderer implements BlockEntityRenderer<ShojiBlockEntity> {
    private static final int MAX_TYPES = 6;
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[MAX_TYPES];

    private static final float TW = 64f;
    private static final float TH = 64f;

    private static final float FU0 = 2f / TW, FU1 = 18f / TW;
    private static final float FV0 = 2f / TH, FV1 = 34f / TH;
    private static final float BU0 = 20f / TW, BU1 = 36f / TW;
    private static final float BV0 = 2f / TH, BV1 = 34f / TH;
    private static final float TU0 = 2f / TW, TU1 = 18f / TW;
    private static final float TV0 = 0f / TH, TV1 = 2f / TH;
    private static final float DU0 = 18f / TW, DU1 = 34f / TW;
    private static final float DV0 = 0f / TH, DV1 = 2f / TH;
    private static final float LU0 = 0f / TW, LU1 = 2f / TW;
    private static final float LV0 = 2f / TH, LV1 = 34f / TH;
    private static final float RU0 = 18f / TW, RU1 = 20f / TW;
    private static final float RV0 = 2f / TH, RV1 = 34f / TH;

    static {
        for (int i = 0; i < MAX_TYPES; i++) {
            TEXTURES[i] = ResourceLocation.fromNamespaceAndPath(Tsuki.MODID,
                    "textures/block/shoji/shoji_type_" + i + ".png");
        }
    }

    public ShojiRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public boolean shouldRenderOffScreen(ShojiBlockEntity blockEntity) {
        return true;
    }

    @Override
    public void render(ShojiBlockEntity blockEntity, float partialTicks, PoseStack poseStack,
            MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState state = blockEntity.getBlockState();
        if (!(state.getBlock() instanceof ShojiBlock)) {
            return;
        }

        Direction facing = state.getValue(ShojiBlock.FACING);
        int type = blockEntity.getShojiType();
        if (type < 0 || type >= MAX_TYPES) {
            type = 0;
        }

        float animProgress = blockEntity.getAnimationProgress(partialTicks);
        float openFactor = animProgress;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.0, 0.5);

        float yRot = switch (facing) {
            case SOUTH -> 0f;
            case WEST -> 90f;
            case NORTH -> 180f;
            case EAST -> 270f;
            default -> 0f;
        };
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.translate(-openFactor * 0.8, 0.0, 0.0);

        float panelLeft = -0.5f;
        float panelRight = 0.5f;
        float panelBottom = 0.0f;
        float panelTop = 2.0f;
        float halfThick = 1f / 16f;

        VertexConsumer builder = bufferSource.getBuffer(RenderType.entityTranslucent(TEXTURES[type]));
        Matrix4f matrix = poseStack.last().pose();
        Matrix3f normal = poseStack.last().normal();

        addVertex(builder, matrix, normal, panelLeft, panelBottom, halfThick, FU0, FV1, 0, 0, 1, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelTop, halfThick, FU0, FV0, 0, 0, 1, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelTop, halfThick, FU1, FV0, 0, 0, 1, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelBottom, halfThick, FU1, FV1, 0, 0, 1, packedLight,
                packedOverlay);

        addVertex(builder, matrix, normal, panelRight, panelBottom, -halfThick, BU0, BV1, 0, 0, -1, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelTop, -halfThick, BU0, BV0, 0, 0, -1, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelTop, -halfThick, BU1, BV0, 0, 0, -1, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelBottom, -halfThick, BU1, BV1, 0, 0, -1, packedLight,
                packedOverlay);

        addVertex(builder, matrix, normal, panelLeft, panelTop, -halfThick, TU0, TV0, 0, 1, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelTop, halfThick, TU0, TV1, 0, 1, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelTop, halfThick, TU1, TV1, 0, 1, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelTop, -halfThick, TU1, TV0, 0, 1, 0, packedLight,
                packedOverlay);

        addVertex(builder, matrix, normal, panelLeft, panelBottom, halfThick, DU0, DV0, 0, -1, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelBottom, -halfThick, DU0, DV1, 0, -1, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelBottom, -halfThick, DU1, DV1, 0, -1, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelBottom, halfThick, DU1, DV0, 0, -1, 0, packedLight,
                packedOverlay);

        addVertex(builder, matrix, normal, panelLeft, panelBottom, -halfThick, LU0, LV1, -1, 0, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelTop, -halfThick, LU0, LV0, -1, 0, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelTop, halfThick, LU1, LV0, -1, 0, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelLeft, panelBottom, halfThick, LU1, LV1, -1, 0, 0, packedLight,
                packedOverlay);

        addVertex(builder, matrix, normal, panelRight, panelBottom, halfThick, RU0, RV1, 1, 0, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelTop, halfThick, RU0, RV0, 1, 0, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelTop, -halfThick, RU1, RV0, 1, 0, 0, packedLight,
                packedOverlay);
        addVertex(builder, matrix, normal, panelRight, panelBottom, -halfThick, RU1, RV1, 1, 0, 0, packedLight,
                packedOverlay);

        poseStack.popPose();
    }

    private void addVertex(VertexConsumer builder, Matrix4f pose, Matrix3f normal,
            float x, float y, float z, float u, float v,
            float nx, float ny, float nz, int packedLight, int packedOverlay) {
        Vector3f transformedNormal = normal.transform(nx, ny, nz, new Vector3f());
        builder.addVertex(pose, x, y, z)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(packedOverlay)
                .setLight(packedLight)
                .setNormal(transformedNormal.x(), transformedNormal.y(), transformedNormal.z());
    }
}
