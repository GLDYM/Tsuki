package cn.mcmod.tsuki.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import cn.mcmod.tsuki.block.entity.ChoppingBoardBlockEntity;
import cn.mcmod.tsuki.block.machines.ChoppingBoardBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ChoppingBoardRender implements BlockEntityRenderer<ChoppingBoardBlockEntity> {
    
    public ChoppingBoardRender(BlockEntityRendererProvider.Context pContext) {
    }
    
    @Override
    public void render(ChoppingBoardBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
            int combinedLight, int combinedOverlay) {
        Direction direction = blockEntity.getBlockState().getValue(ChoppingBoardBlock.FACING).getOpposite();
        ItemStack boardStack = blockEntity.getStoredItem();
        int posLong = (int) blockEntity.getBlockPos().asLong();

        if (!boardStack.isEmpty()) {
            poseStack.pushPose();

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            boolean isBlockItem = itemRenderer.getModel(boardStack, blockEntity.getLevel(), null, 0).isGui3d();

            if (isBlockItem) {
                renderBlock(poseStack, direction);
            } else {
                renderItemLayingDown(poseStack, direction);
            }

            Minecraft.getInstance().getItemRenderer().renderStatic(boardStack, ItemDisplayContext.FIXED, combinedLight,
                    combinedOverlay, poseStack, buffer, blockEntity.getLevel(), posLong);
            poseStack.popPose();
        }
    }

    public void renderItemLayingDown(PoseStack matrixStackIn, Direction direction) {
        // Center item above the cutting board
        matrixStackIn.translate(0.5D, 0.16D, 0.5D);

        // Rotate item to face the cutting board's front side
        float f = -direction.toYRot();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));

        // Rotate item flat on the cutting board. Use X and Y from now on
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));

        // Resize the item
        matrixStackIn.scale(0.6F, 0.6F, 0.6F);
    }

    public void renderBlock(PoseStack matrixStackIn, Direction direction) {
        // Center block above the cutting board
        matrixStackIn.translate(0.5D, 0.27D, 0.5D);

        // Rotate block to face the cutting board's front side
        float f = -direction.toYRot();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));

        // Resize the block
        matrixStackIn.scale(0.8F, 0.8F, 0.8F);
    }
}
